//
//  clib_macro.h
//  obj
//
//  Created by daoyi on 13-4-29.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//

#ifndef clib_macro_h
#define clib_macro_h



//expand macro
#define STR(x) _STR(x)
#define _STR(x) #x

#define PI 3.14159265358979323846264338327950288
#define SHRINK_RATE(x,y) (((x)>(y))?((y)/(x)):((x)/(y)))

#endif
