//
//  obj_macro_uiview.h
//  obj
//
//  Created by daoyi on 13-4-29.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//

#ifndef obj_macro_uiview_h
#define obj_macro_uiview_h

/*****************************************************/
/*****************************************************/
#define X(p) (((UIView*)(p)).frame.origin.x)
#define Y(p) (((UIView*)(p)).frame.origin.y)
#define W(p) (((UIView*)(p)).bounds.size.width)
#define H(p) (((UIView*)(p)).bounds.size.height)
/*****************************************************/
/*****************************************************/
#define SETX(p,x)               [(p) setCenter:CGPointMake((x) + W(p)/2, (p).center.y)]
#define SETY(p,y)               [(p) setCenter:CGPointMake((p).center.x, (y) + H(p)/2)]
#define SETW(p,w)               ((p).bounds = CGRectMake(0, 0, w, H(p)))
#define SETH(p,h)               ((p).bounds = CGRectMake(0, 0, W(p), h))
#define ADDX(p,_x)              [(p) setCenter:CGPointMake((p).center.x + (_x), (p).center.y)]
#define ADDY(p,_y)              [(p) setCenter:CGPointMake((p).center.x, (p).center.y + (_y))]
#define ADDW(p,w)               ((p).bounds = CGRectMake(0, 0, W(p) + w, H(p)))
#define ADDH(p,h)               ((p).bounds = CGRectMake(0, 0, W(p), H(p) + h))
#define SETXY(p,_x,_y)          [(p) setCenter:CGPointMake((_x) + W(p)/2, (_y) + H(p)/2)]
#define SETWH(p,w,h)            ((p).bounds = CGRectMake(0, 0, w, h))
#define SETFRAME(p,x,y,w,h)    ((p).frame = CGRectMake(x, y, w, h))
/*****************************************************/
/*****************************************************/
#import <QuartzCore/QuartzCore.h>
#define VIEW_RADIUS(p,x) (((UIView*)(p)).layer.cornerRadius = (x),\
                          ((UIView*)(p)).layer.masksToBounds = YES)
/*****************************************************/
/*****************************************************/
#define VIEW_TAG(x) ((UIView*)(x)).tag
/*****************************************************/
/*****************************************************/
#define VIEW_ANIMATION(t,x) {[UIView beginAnimations:nil context:nil];\
                             [UIView setAnimationDuration:(t)];\
                             {x}\
                             [UIView commitAnimations];}
/*****************************************************/

#endif
