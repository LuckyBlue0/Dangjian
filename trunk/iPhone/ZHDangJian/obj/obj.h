//
//  obj.h
//  obj
//
//  Created by daoyi on 13-4-29.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//
#ifndef obj_h
#define obj_h

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import <Foundation/NSObjCRuntime.h>

#import "obj_obj.h"
#import "obj_ui.h"
#import "clib_macro.h"

#define DB(s,args...)  printf("Line:%d  %s  %s\n\n",\
                            __LINE__,\
                            sel_getName(_cmd),\
                            [[NSString stringWithFormat:s,##args] UTF8String])

#define DBFF printf("FILE:%s \nLine:%d  %s \n\n",\
                                __FILE__,\
                                __LINE__,\
                                sel_getName(_cmd));

#define DBIF(x,f,a...) if((x))DB(f,##a)

#endif//obj_h