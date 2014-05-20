//
//  obj_macro_ui.h
//  obj
//
//  Created by daoyi on 13-4-29.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//

#ifndef obj_ui_h
#define obj_ui_h

#import "obj.h"

#import "obj_macro_color.h"
#import "obj_macro_font.h"
#import "obj_macro_view.h"
#import "obj_macro_indicator.h"
#import "obj_macro_lable.h"
 
#import "obj_edge_table_view.h"
#import "obj_font.h"
#import "obj_message_box.h"
#import "obj_page_scroll_view.h"  
#import "obj_table_header.h" 
#import "obj_table_tailer.h"
#import "obj_url_image.h"
#import "obj_url_image_view.h"
#import "obj_view.h"

#define IMG_FROM_OBJ_BUNDLE(x)  IMG_FROM_BUNDLE(@"obj_bundle",x)

#define AIF(t,f)              [[t alloc] initWithFrame:f]
#define AIFAR(t,f)            [AIF(t,f) autorelease]
#define AIFRM(t,x,y,w,h)       AIF(t,CGRectMake(x,y,w,h))
#define AIFRMAR(t,x,y,w,h)    [AIFRM(t,x,y,w,h) autorelease]

#define KEY_WINDOW     [UIApplication sharedApplication].keyWindow

#define SCREEN_W       (KEY_WINDOW.bounds.size.width)
#define SCREEN_H       (KEY_WINDOW.bounds.size.height)
#define SCREEN_BOUNDS   KEY_WINDOW.bounds

#define i4W                     320
#define i4H                     480
#define i5H                     568
#define STATUSBARH              20
#define NAVH                    44
//#define KEYBOARDH               216
/****************************************************************************/
/*********************************UIImage************************************/
#define IMG(x) [UIImage imageNamed:(x)]
#define SETIMG(_p,_str) (_p).image = IMG(_str)
/****************************************************************************/
/**************************viewcontroller************************************/
#define PUSH(x) [self.navigationController pushViewController:x animated:YES]
#define POP [self.navigationController popViewControllerAnimated:YES]
#define PRE_CONTROLLER [self.navigationController.viewControllers objectAtIndex:self.navigationController.viewControllers.count - 2]
/****************************************************************************/
/***********************************btn**************************************/
//add
#define BTNTD(_p,_t,_s) \
[(_p) addTarget:(_t) action:@selector(_s) forControlEvents:UIControlEventTouchDown]

#define BTNTUI(_p,_t,_s) \
[(_p) addTarget:(_t) action:@selector(_s) forControlEvents:UIControlEventTouchUpInside]

#define BTNTUO(_p,_t,_s) \
[(_p) addTarget:(_t) action:@selector(_s) forControlEvents:UIControlEventTouchUpOutside]

#define BTNTDI(_p,_t,_s) \
[(_p) addTarget:(_t) action:@selector(_s) forControlEvents:UIControlEventTouchDragInside]

#define BTNTDO(_p,_t,_s) \
[(_p) addTarget:(_t) action:@selector(_s) forControlEvents:UIControlEventTouchDragOutside]

//remove
#define RBTNTD(_p,_t,_s) \
[(_p) removeTarget:(_t) action:@selector(_s) forControlEvents:UIControlEventTouchDown]

#define RBTNTUI(_p,_t,_s) \
[(_p) removeTarget:(_t) action:@selector(_s) forControlEvents:UIControlEventTouchUpInside]

#define RBTNTUO(_p,_t,_s) \
[(_p) removeTarget:(_t) action:@selector(_s) forControlEvents:UIControlEventTouchUpOutside]

#define RBTNTDI(_p,_t,_s) \
[(_p) removeTarget:(_t) action:@selector(_s) forControlEvents:UIControlEventTouchDragInside]

#define RBTNTDO(_p,_t,_s) \
[(_p) removeTarget:(_t) action:@selector(_s) forControlEvents:UIControlEventTouchDragOutside]
/*********************************************************************************/
#define BTNNIMG(_t,x) [_t setImage:IMG(x) forState:UIControlStateNormal]

#define BTNHIMG(_t,x) [_t setImage:IMG(x) forState:UIControlStateHighlighted]

#define BTNIMG(_t,strN,strH) (BTNNIMG(_t,strN),BTNHIMG(_t,strH))
/*********************************************************************************/
#endif
