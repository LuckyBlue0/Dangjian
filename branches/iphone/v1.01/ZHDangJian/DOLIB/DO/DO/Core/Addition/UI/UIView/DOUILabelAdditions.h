/*
 * 扩充 UILabel
 * Created by kllmctrl on 12-4-16.
 */
#import <UIKit/UIKit.h>


/*************************************************************************************
 *
 *@class UILabel (LLUILabelAdditions)
 *
 *************************************************************************************
 */

@interface UILabel (DOUILabelAdditions)


/**
 * 设置背景文字,字体,颜色(normal,hilight)
 */
-(void)setText:(NSString *)aText 
		  font:(CGFloat)aFont 
		color1:(UIColor*)aColor1 
		color2:(UIColor*)aColor2;

/**
 * 设置对齐方式(并默认设置UIViewContentModeTop,UILineBreakModeWordWrap)
 */
-(void)setAlignment:(UITextAlignment)aAlignment;

/**
 * 自适应高度
 */
-(void)resizeHeight;

/**
 * 自适应宽度
 */
-(void)resizeWidth;


@end

