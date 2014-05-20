//
//  obj_obj.h
//  obj
//
//  Created by daoyi on 13-5-5.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//

#ifndef obj_obj_h
#define obj_obj_h

#import "obj.h"
#import <objc/message.h>

#import "obj_macro_string.h"

#import "obj_string.h"
#import "obj_date.h"
#import "obj_communication.h"


#define IMG_FROM_BUNDLE(b,x) [UIImage imageWithContentsOfFile:[[NSBundle bundleWithPath:[[NSBundle mainBundle] pathForResource:(b) ofType:@"bundle"]] pathForResource:(x) ofType:nil]]

#define AIAR(x) [[[x alloc]init]autorelease]

//release and set nil
#define RN(x) ((x)?([(x) release],(x)=nil):0)



#define RETAIN(d,s) (RN(d),(d)=(s),[(d) retain])

#define METHOD_EXIST(t,s) class_respondsToSelector([(t) class],(s))
#define METHOD(t,s,args...) objc_msgSend((t),(s),##args)
#define METHOD_SAFE(t,s,args...) (METHOD_EXIST(t,s)?METHOD((t),(s),##args):0)

//沙盒Document文件夹
#define DocumentsDIR [NSHomeDirectory() stringByAppendingPathComponent:@"Documents"]

//是否存在文件
#define isExitsFile(_filename) [[NSFileManager defaultManager] fileExistsAtPath:(_filename)]

#endif
