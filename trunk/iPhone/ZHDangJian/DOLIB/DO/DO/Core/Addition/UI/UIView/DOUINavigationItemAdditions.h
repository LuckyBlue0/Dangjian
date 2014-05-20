/*
 * 扩充 UINavigationItem
 * Created by kllmctrl on 12-7-20.
 */
#import <UIKit/UIKit.h>


/*************************************************************************************
 *
 *@class UINavigationItem (LLUINavigationItemAdditions)
 *
 *************************************************************************************
 */
@interface UINavigationItem (DOUINavigationItemAdditions)


/**
 * NAV 自定义标题
 */
-(void)setTitle:(NSString*)aTitle 
		  color:(UIColor*)aColor 
		   font:(CGFloat)aFont;

/**
 * NAV 自定义左边按钮 @2x高清图
 */
-(void)setLeftBarItem:(id)aTarget 
			   action:(SEL)aAction 
				image:(NSString*)aImage 
		   focusImage:(NSString*)aFocusImage;

/**
 * NAV 自定义右边按钮 @2x高清图
 */
-(void)setRightBarItem:(id)aTarget 
				action:(SEL)aAction 
				 image:(NSString*)aImage 
			focusImage:(NSString*)aFocusImage;


@end
