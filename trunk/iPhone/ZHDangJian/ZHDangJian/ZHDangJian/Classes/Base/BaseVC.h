/*
 
 vc 基类
 
 */

#import <Foundation/Foundation.h>
#import "Constant.h"
#import "BaseModel.h"
#import <obj/obj.h>
#import "RequstResult.h"
#import "UserInfo.h"
#import "DES3Util.h"


@interface BaseVC : UIViewController<MBProgressHUDDelegate,UIGestureRecognizerDelegate,DOModelDelegate>

@property (nonatomic,retain)MBProgressHUD *HUD;


-(void)removeHub;
-(void)initHUBTitle:(NSString *)title subTitle:(NSString *)subTitle;
-(void)changeHUBTitle:(NSString *)title subTitle:(NSString *)subTitle;





//显示返回按钮
-(void)showBack;

-(void)showNavLeftBtn:(CGFloat)width title:(NSString*)title;

-(void)showNavBtn:(CGFloat)width target:(id)target action:(SEL)action title:(NSString*)title;

//返回事件
-(void)backAction;


//默认的列表背景和点击背景
-(void)setCellDefaultBg:(UITableViewCell*)cell;


//点击背景触发事件
-(void)setTapHideKeyboard:(SEL)action;

//字典键值为nsstring为nil空判断并赋值@“”
-(NSDictionary *)checkDictionaryData:(NSDictionary *)a_dic;

+ (NSString *)buildJson:(NSDictionary *)dic;

@end
