

/*
 *
 * 启动页面功能描述：
 *
 * 1.包含首次提示概要导航
 * 2.全屏启动--默认动画版本信息
 *
 * 重写三个方法:
 * 1)-(NSArray*)images;
 * 2)-(void)bootFinished
 *
 */

#import <UIKit/UIKit.h>

@interface KCBootVC : UIViewController

-(void)checkVersionFinish;

-(void)bootFinished;

@end



