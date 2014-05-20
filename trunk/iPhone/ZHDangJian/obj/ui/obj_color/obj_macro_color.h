//
//  obj_macro_uicolor.h
//  obj
//
//  Created by daoyi on 13-4-29.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//

#ifndef obj_macro_color_h
#define obj_macro_color_h

#define RGBA(r,g,b,a) [UIColor colorWithRed:(r)/255.0f green:(g)/255.0f blue:(b)/255.0f alpha:(a)/1.0f]
#define RGB(r,g,b)     RGBA(r,g,b,1)

#define CLEAR(p)  ((p).backgroundColor = [UIColor clearColor])
 
#define COLOR_RED         [UIColor redColor]
#define COLOR_BLUE        [UIColor blueColor]
#define COLOR_GREEN       [UIColor greenColor]
#define COLOR_WHITE       [UIColor whiteColor]
#define COLOR_BLACK       [UIColor blackColor]
#define COLOR_MAGENTA     [UIColor magentaColor]
#define COLOR_PURPLE      [UIColor purpleColor]
#define COLOR_YELLOW      [UIColor yellowColor]
#define COLOR_DARKGRAY    [UIColor darkGrayColor]
#define COLOR_LIGHTGRAY   [UIColor lightGrayColor]
#define COLOR_SILVER       RGB(213,213,213)

#endif
