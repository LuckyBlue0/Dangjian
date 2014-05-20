//
//  obj_date.m
//  obj
//
//  Created by daoyi on 13-5-7.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//

#import "obj_date.h"

@implementation NSDate(obj)
-(NSString*)description_for_date:(NSDate*)date
{ 
    CGFloat dif_time = [self timeIntervalSinceDate:date];
    
#define RT(f,t) if(dif_time>t) return [NSString stringWithFormat:f,dif_time/t]
    
    RT(@"%.0f年之前",YEAR);
    RT(@"%.0f个月之前",MONTH);
    RT(@"%.0f天之前",DAY);
    RT(@"%.0f小时之前",HOUR);
    RT(@"%.0f分钟之前",MINUTE);
     
    return @"刚刚";
}
@end
