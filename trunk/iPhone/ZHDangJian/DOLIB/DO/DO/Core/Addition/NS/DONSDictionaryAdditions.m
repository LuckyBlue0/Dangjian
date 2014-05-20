
#import "DONSDictionaryAdditions.h"

@implementation NSDictionary (DONSDictionaryAdditions)

-(NSString*)stringForKey:(NSString*)aStr{
    //doubleValue(floatValue,intValue) 都可以NSNumber/NSString
    //stringValue 只能NSString
    if ([[self objectForKey:aStr] isKindOfClass:[NSString class]]) {
        return [self objectForKey:aStr];
    }else{
        return [[self objectForKey:aStr] stringValue];
    }
}

@end
