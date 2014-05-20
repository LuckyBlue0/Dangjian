//
//  KCAnimationFactory.m
//  KC
//
//  Created by kllmctrl on 13-3-29.
//  Copyright (c) 2013年 kllmctrl. All rights reserved.
//

#import "KCAnimationFactory.h"
#import "DOAddition.h"


@implementation KCAnimationFactory

+ (CAKeyframeAnimation*) scaleAnimation:(BOOL)show
{
    /*
  UIView *aView = [[UIView alloc] initWithFrame:XYWH(60, 100, 200, 200)];
  aView.backgroundColor = RED_COLOR;
  [self.view addSubview:aView];
  
  
  //隐藏
  [UIView animateWithDuration:0.3 animations:^{
  aView.alpha = 0.0;
  [aView.layer addAnimation:[KCAnimationFactory scaleAnimation:FALSE] forKey:@"LOGINVIEWWILLDISAPPEAR"];
  } completion:^(BOOL finished){
  [aView removeFromSuperview];
  }];
  
  //
  //
  //    //显示
  //    [UIView animateWithDuration:0.5 animations:^{
  //        aView.alpha = 1.0;
  //        [aView.layer addAnimation:[KCAnimationFactory scaleAnimation:TRUE] forKey:@"LOGINVIEWWILLAPPEAR"];
  //    } completion:^(BOOL finished){
  //    }];
  //
  */
    CAKeyframeAnimation *scaleAnimation = nil;
    scaleAnimation = [CAKeyframeAnimation animationWithKeyPath:@"transform"];
    scaleAnimation.delegate = self;
    scaleAnimation.fillMode = kCAFillModeForwards;
    
    NSMutableArray *values = [NSMutableArray array];
    if (show)
    {
        scaleAnimation.duration = 0.5;
        [values addObject:[NSValue valueWithCATransform3D:CATransform3DMakeScale(0.8, 0.8, 1.0)]];
        [values addObject:[NSValue valueWithCATransform3D:CATransform3DMakeScale(1.0, 1.0, 1.0)]];
        [values addObject:[NSValue valueWithCATransform3D:CATransform3DMakeScale(0.9, 0.9, 0.9)]];
        [values addObject:[NSValue valueWithCATransform3D:CATransform3DMakeScale(1.0, 1.0, 1.0)]];
    }
    else
    {
        scaleAnimation.duration = 0.3;
        [values addObject:[NSValue valueWithCATransform3D:CATransform3DMakeScale(1.0, 1.0, 1.0)]];
        [values addObject:[NSValue valueWithCATransform3D:CATransform3DMakeScale(0.9, 0.9, 0.9)]];
        [values addObject:[NSValue valueWithCATransform3D:CATransform3DMakeScale(0.8, 0.8, 1.0)]];
        [values addObject:[NSValue valueWithCATransform3D:CATransform3DMakeScale(0.6, 0.6, 1.0)]];
    }
    scaleAnimation.values = values;
    scaleAnimation.timingFunction = [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionEaseInEaseOut];
    scaleAnimation.removedOnCompletion = TRUE;
    return scaleAnimation;
}

+ (void) addMarqueeAnimationForLabel:(UILabel *)label{
    /*
     UILabel *aView = [[UILabel alloc] initWithFrame:XYWH(60, 100, 200, 20)];
     aView.text = @"2222222222222222222jkdjfkjdkjfkdjkff999999999999999999999";
     aView.backgroundColor = WHITE_COLOR;
     [self.view addSubview:aView];
     [aView.layer removeAllAnimations];
     [KCAnimationFactory addMarqueeAnimationForLabel:aView];
     */
    [label.layer removeAllAnimations];
    label.left = 0;
    
    
    int modeType = 0;
#if __IPHONE_OS_VERSION_MAX_ALLOWED > __IPHONE_6_0
    modeType = NSLineBreakByCharWrapping;
#else
    modeType = UILineBreakModeCharacterWrap;
#endif
    
    CGSize boundingSize = CGSizeMake(CGFLOAT_MAX, 20);
    CGFloat oldLabelWidth = [label.text sizeWithFont:label.font constrainedToSize:boundingSize lineBreakMode:modeType].width;
    if (oldLabelWidth<=label.superview.width) {
        return;
    }
    CGFloat moveWidth = [[NSString stringWithFormat:@"%@     ",label.text] sizeWithFont:label.font constrainedToSize:boundingSize lineBreakMode:modeType].width;
    label.text = [NSString stringWithFormat:@"%@     %@",label.text,label.text];
	CGFloat labelWidth = [label.text sizeWithFont:label.font constrainedToSize:boundingSize lineBreakMode:modeType].width;
    
    label.width = labelWidth;
    
    [UIView animateWithDuration:label.width/60 delay:0.5 options:UIViewAnimationOptionRepeat|UIViewAnimationOptionAllowUserInteraction|UIViewAnimationOptionOverrideInheritedDuration animations:^
     {
         label.left  = -moveWidth+1;
     } completion:^(BOOL finished)
     {
         if (finished)
         {
             label.left = 0;
         }
     }];
}


@end
