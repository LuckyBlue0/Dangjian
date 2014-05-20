//
//  obj_date.h
//  obj
//
//  Created by daoyi on 13-5-7.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//

#import "obj.h"

#define MINUTE 60
#define HOUR   (60*MINUTE)
#define DAY    (24*HOUR)
#define MONTH  (30*DAY)
#define YEAR   (12*MONTH)

@interface NSDate(obj)
-(NSString*)description_for_date:(NSDate*)date;
@end
