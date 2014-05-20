//
//  obj_add_uifont.m
//  obj
//
//  Created by daoyi on 13-4-30.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//

#import "obj_font.h" 

@implementation UIFont (obj)
+(void)print_all_fonts
{
    for (int i = 0 ; i < [UIFont familyNames].count; i++)
        DB(@"%03d:%@", i,[[UIFont familyNames] objectAtIndex:i]);
}
@end
