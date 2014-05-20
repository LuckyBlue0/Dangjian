//
//  MyClass.m
//  hupan
//
//  Created by lian jie on 6/8/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "DataCacheManager.h"
#import "DOCore.h"

@interface DataCacheManager()
- (void)restore;
- (BOOL)isValidKey:(NSString*)key;
- (void)removeKey:(NSString*)key fromKeyArray:(NSMutableArray*)keyArray;
@end

static DataCacheManager *sharedInst = nil;
@implementation DataCacheManager

#pragma mark - singleton lifecycle

#pragma mark - private methods
- (void)clearMemoryCache
{
    [_memoryCacheKeys removeAllObjects];
    [_memoryCachedObjects removeAllObjects];
}
- (void)registerMemoryWarningNotification{
#if TARGET_OS_IPHONE
    // Subscribe to app events
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(clearMemoryCache)
                                                 name:UIApplicationDidReceiveMemoryWarningNotification
                                               object:nil];    
#ifdef __IPHONE_4_0
    UIDevice *device = [UIDevice currentDevice];
    if ([device respondsToSelector:@selector(isMultitaskingSupported)] && device.multitaskingSupported){
        // When in background, clean memory in order to have less chance to be killed
        [[NSNotificationCenter defaultCenter] addObserver:self
                                                 selector:@selector(clearMemoryCache)
                                                     name:UIApplicationDidEnterBackgroundNotification
                                                   object:nil];
    }
#endif
#endif        
}
+ (DataCacheManager *)sharedManager
{    
	@synchronized(sharedInst)
    {
		if (sharedInst == nil) 
        {
            sharedInst = [[super allocWithZone:NULL] init];            
		}
	}	
	return sharedInst;
}

- (id)init
{
    self = [super init];
    if (self)
    {
        [self restore];
        [self registerMemoryWarningNotification];
    }
	return self;
}

/*
 * It overrides the allocWithZone: method to ensure that another instance is not allocated
 * if someone tries to allocate and initialize an instance of your class directly instead of using the
 * class factory method. Instead, it just returns the shared object.
 *
 * on the other hand, If you want a singleton instance (created and controlled by the class factory method) 
 * but also have the ability to create other instances as needed through allocation and initialization, 
 * do not override allocWithZone method
 */
+ (id)allocWithZone:(NSZone *)zone
{
    return [[self sharedManager] retain];
}

- (id)copyWithZone:(NSZone *)zone
{
    return self;
}

- (NSUInteger)retainCount{
	return NSUIntegerMax;
}

- (oneway void)release{
}

- (id)retain{
	return sharedInst;
}

- (id)autorelease{
	return sharedInst;
}

- (void)dealloc{
    DO_RELEASE_SAFELY(_memoryCacheKeys);
    DO_RELEASE_SAFELY(_memoryCachedObjects);
    DO_RELEASE_SAFELY(_keys);
    DO_RELEASE_SAFELY(_cachedObjects);
    
	[super dealloc];
}
#pragma mark - private methods
- (void)restore{
    if ([USER_DEFAULT objectForKey:UD_KEY_DATA_CACHE_KEYS]) {
        NSArray *keysArray = (NSArray*)[NSKeyedUnarchiver unarchiveObjectWithData:[USER_DEFAULT objectForKey:UD_KEY_DATA_CACHE_KEYS]];
        _keys = [[NSMutableArray alloc] initWithArray:keysArray];
    }else{
        _keys = [[NSMutableArray alloc] init];
    }
    
    if([USER_DEFAULT objectForKey:UD_KEY_DATA_CACHE_OBJECTS]){
        NSDictionary *objDic = (NSDictionary*)[NSKeyedUnarchiver unarchiveObjectWithData:[USER_DEFAULT objectForKey:UD_KEY_DATA_CACHE_OBJECTS]];
        _cachedObjects = [[NSMutableDictionary alloc] initWithDictionary:objDic];
    }else{
        _cachedObjects = [[NSMutableDictionary alloc] init];
    }
    _memoryCacheKeys = [[NSMutableArray alloc] init];
    _memoryCachedObjects = [[NSMutableDictionary alloc] init];
}
- (BOOL)isValidKey:(NSString*)key{
    if (!key || [key length] == 0 || (NSNull*)key == [NSNull null]) {
        return NO;
    }
    return YES;
}
- (void)removeKey:(NSString*)key fromKeyArray:(NSMutableArray*)keyArray{
    int indexInKeys = NSNotFound;
    for (int i=0;i < [keyArray count];i++) {
        if ([[keyArray objectAtIndex:i] isEqualToString:key]) {
            indexInKeys = i;
            break;
        }
    }
    if (indexInKeys != NSNotFound) {
        [keyArray removeObjectAtIndex:indexInKeys];
    }
}
#pragma mark - public methods
- (void)doSave{
    [USER_DEFAULT setObject:[NSKeyedArchiver archivedDataWithRootObject:_keys] forKey:UD_KEY_DATA_CACHE_KEYS];
    [USER_DEFAULT setObject:[NSKeyedArchiver archivedDataWithRootObject:_cachedObjects] forKey:UD_KEY_DATA_CACHE_OBJECTS];
    [USER_DEFAULT synchronize];
}

- (void)clearAllCache{
    [self clearMemoryCache];
    [_keys removeAllObjects];
    [_cachedObjects removeAllObjects];
    [self doSave];
}
- (void)addObject:(NSObject*)obj forKey:(NSString*)key{
    if (![self isValidKey:key]) {
        return;
    }
    if (!obj || (NSNull*)obj == [NSNull null]) 
    {
        return;
    }
    if ([self hasObjectInCacheByKey:key]) {
        [self removeObjectInCacheByKey:key];
    }
    [_keys addObject:key];
    [_cachedObjects setObject:obj forKey:key];
}
- (void)addObjectToMemory:(NSObject*)obj forKey:(NSString*)key{
    if (![self isValidKey:key]) {
        return;
    }
    if (!obj || (NSNull*)obj == [NSNull null]) {
        return;
    }
    if ([self hasObjectInCacheByKey:key]) {
        [self removeObjectInCacheByKey:key];
    }
    [_memoryCacheKeys addObject:key];
    [_memoryCachedObjects setObject:obj forKey:key];
}
- (NSObject*)getCachedObjectByKey:(NSString*)key{
    if (![self isValidKey:key]) {
        return nil;
    }
    if ([_memoryCachedObjects objectForKey:key]) {
        return [_memoryCachedObjects objectForKey:key];
    }else{
        return [_cachedObjects objectForKey:key];
    }
}

- (BOOL)hasObjectInCacheByKey:(NSString*)key{
    return [self getCachedObjectByKey:key] != nil;
}

- (void)removeObjectInCacheByKey:(NSString*)key{
    if (![self isValidKey:key]) {
        return;
    }
    [_cachedObjects removeObjectForKey:key];
    [self removeKey:key fromKeyArray:_keys];
    [_memoryCachedObjects removeObjectForKey:key];
    [self removeKey:key fromKeyArray:_memoryCacheKeys];
}
@end
