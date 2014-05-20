/*
 *扩充 NSDictionary
 *Created by liming on 12-8-31.
 */

#import <Foundation/Foundation.h>

@interface NSDictionary (DONSDictionaryAdditions)



/**
 * 获取一个关键字(如果是doubleValue,floatValue)
 */

-(NSString*)stringForKey:(NSString*)aStr;


@end



