/*
 * 扩充 UIAlertView
 * Created by kllmctrl on 12-4-16.
 */
#import <UIKit/UIKit.h>


/*************************************************************************************
 *
 *@class UIAlertView (LLUIAlertViewAdditions)
 *
 *************************************************************************************
 */
@interface UIAlertView (DOUIAlertViewAdditions)


/**
 * 设置标题(确定)
 */
+(void)showTip:(NSString*)aTipStr;

/**
 * 设置标题,事件(确定)
 */
+(void)showTip:(NSString*)aTipStr delegate:(id)aDelegate tag:(NSInteger)aTag;

@end


