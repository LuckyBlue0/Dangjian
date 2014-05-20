/*
 * 扩充 UILabel
 * Created by kllmctrl on 12-06-19.
 */

#import <UIKit/UIKit.h>


/*************************************************************************************
 *
 *@class UIButton (LLUIButtonAdditions)
 *
 *************************************************************************************
 */

@interface UIButton (DOUIButtonAdditions)

/**
 * 设置标题,字体,颜色(normal,hilight(selected))
 */
-(void)setTitle:(NSString *)title 
		   font:(CGFloat)aFont 
		 color1:(UIColor*)aColor1 
		 color2:(UIColor*)aColor2;

/**
 * 设置图片
 */
-(void)setImage1:(NSString*)aImage1 
		  Image2:(NSString*)aImage2;

/**
 * 设置背景图片
 */
-(void)setBGImage1:(NSString*)aImage1 
		  BGImage2:(NSString*)aImage2;

/**
 * 设置事件 UIControlEventTouchUpInside
 */
-(void)setTarget:(id)aTarget 
		  action:(SEL)aAction;

@end





