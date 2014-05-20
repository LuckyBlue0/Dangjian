//
//  KCAnimationFactory.h
//  KC
//
//  Created by kllmctrl on 13-3-29.
//  Copyright (c) 2013年 kllmctrl. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import <QuartzCore/QuartzCore.h>


@interface KCAnimationFactory : NSObject


//动画显示隐藏
+ (CAKeyframeAnimation*) scaleAnimation:(BOOL)show;


//自动滚动label
+ (void) addMarqueeAnimationForLabel:(UILabel*)label;

@end
