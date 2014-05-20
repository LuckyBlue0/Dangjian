//
//  obj_add_nsstring.h
//  obj
//
//  Created by daoyi on 13-4-29.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import "obj.h"

@interface NSString (obj)
-(BOOL)is_empty;
-(NSString*)valid_str;
-(NSString*)utf8_str;
-(NSString*)without:(NSString*)str;
-(CGFloat)width_for_font:(UIFont*)font;
-(CGFloat)height_for_font:(UIFont*)font and_width:(CGFloat)width;
-(BOOL)is_mobile;
-(BOOL)is_email;

-(NSDate*)date_with_format:(NSString*)format;
@end
