

/*
 *
 * make by kllmctrl @2013-1-6
 * v 1.0
 *
 *
 * 登陆页面功能描述：
 * 1.登陆请求
 * 2.登陆保存用户信息（列表匹配）
 *
 */



#import <UIKit/UIKit.h>

@interface KCLoginVC : UIViewController

@property(nonatomic,copy)NSString *userName;
@property(nonatomic,copy)NSString *userPwd;

@property(nonatomic,retain)UITextField *userNameTextField;
@property(nonatomic,retain)UITextField *userPwdTextField;


@end


