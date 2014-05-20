



/*
 
 
 NSMutableArray *stationsArray = [[NSMutableArray alloc] initWithArray:[resultDic objectForKey:KEY_ARRAY]];
 if (_isCurrentChooseStartStation) {
 self.startStationArray = stationsArray;
 if ([stationsArray count]) {
 [[DataCacheManager sharedManager] addObject:stationsArray
 forKey:[self getStartStationKey]];
 }
 }

 
 
 if ([[DataCacheManager sharedManager] getCachedObjectByKey:@"DefaultCoachStartCity"]){
 RELEASE_SAFELY(_startCity);
 _startCity = [(City *)[[DataCacheManager sharedManager] getCachedObjectByKey:@"DefaultCoachStartCity"] retain];
 }
 
 
 
 
 - (void)applicationDidEnterBackground:(UIApplication *)application
 {
 [[DataCacheManager sharedManager] doSave];
 }
 
 */

#import <Foundation/Foundation.h>

#define UD_KEY_DATA_CACHE_KEYS @"UD_KEY_DATA_CACHE_KEYS"
#define UD_KEY_DATA_CACHE_OBJECTS @"UD_KEY_DATA_CACHE_OBJECTS"

#define DATA_CACHE_KEY_ALL_TOPIC_LIST @"DATA_CACHE_KEY_ALL_TOPIC_LIST"
#define DATA_CACHE_KEY_MY_TOPIC_LIST @"DATA_CACHE_KEY_MY_TOPIC_LIST"
#define DATA_CACHE_KEY_UPDATED_TOPIC_LIST @"DATA_CACHE_KEY_UPDATED_TOPIC_LIST"

typedef enum{
	DataCacheManagerCacheTypeMemory,
	DataCacheManagerCacheTypePersist
} DataCacheManagerCacheType;

@interface DataCacheManager : NSObject {
    NSMutableArray *_memoryCacheKeys;     // keys for objects only cached in memory
    NSMutableDictionary *_memoryCachedObjects;     // objects only cached in memory 
    NSMutableArray *_keys;          // keys for keys not managed by queue
    NSMutableDictionary *_cachedObjects;
}
+ (DataCacheManager *)sharedManager;
- (void)clearAllCache;
- (void)clearMemoryCache;
- (void)addObject:(NSObject*)obj forKey:(NSString*)key;
- (void)addObjectToMemory:(NSObject*)obj forKey:(NSString*)key;
- (NSObject*)getCachedObjectByKey:(NSString*)key;
- (BOOL)hasObjectInCacheByKey:(NSString*)key;
- (void)removeObjectInCacheByKey:(NSString*)key;
- (void)doSave;
@end
