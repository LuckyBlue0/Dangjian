//
//  LoginVC.h
//  ZHDangJian
//  登陆VC
//  Created by kevin_yby on 13-11-11.
//  Copyright 广州市道一信息技术有限公司 2013年. All rights reserved.
//



#import <UIKit/UIKit.h>
#import "BaseVC.h"
#import "BaseModel.h"

@interface LoginVC : BaseVC<UITextFieldDelegate, DOModelDelegate>

@property (nonatomic, retain) IBOutlet UITextField *userNameTxt;
@property (nonatomic, retain) IBOutlet UITextField *pwdTxt;

@property (nonatomic, retain) BaseModel *loginModel;

- (IBAction)keyBoardDisappear:(id)sender;
- (IBAction)loginAction:(id)sender;

@end