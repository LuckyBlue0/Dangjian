//
//  LoginVC.m
//  ZHDangJian
//  
//  Created by kevin_yby on 13-11-11.
//  Copyright 广州市道一信息技术有限公司 2013年. All rights reserved.
//

#import "LoginVC.h"

@implementation LoginVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil{
    if (self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil]) {
    }
    return self;
}

- (void)dealloc{
    
    [_userNameTxt release];
    [_pwdTxt release];
    _loginModel.delegate = nil;
    DO_RELEASE_SAFELY(_loginModel);
    [super dealloc];
}

- (void)loadView{
    [super loadView];
    
    KCSaveData *aSaveData = [[[KCSaveData alloc] initWithFileName:@"login_users"] autorelease];
    self.userNameTxt.text = [aSaveData getString:@"name"];
    self.pwdTxt.text = [aSaveData getString:@"password"];
    
    if (![self.userNameTxt.text isEmpty] && ![self.pwdTxt.text isEmpty]) {
        [self loginAction:nil];
    }
}


- (void)viewDidUnload
{
    [self setUserNameTxt:nil];
    [self setPwdTxt:nil];
    [super viewDidUnload];
}


#pragma mark - Action

- (IBAction)keyBoardDisappear:(id)sender {
    
    [self.userNameTxt resignFirstResponder];
    [self.pwdTxt resignFirstResponder];
}

- (IBAction)loginAction:(id)sender {
    
    [self keyBoardDisappear:nil];
    
    if ([self.userNameTxt.text isEmpty]) {
        [UIAlertView showTip:@"请输入用户名"];
        return;
    }
    if ([self.pwdTxt.text isEmpty]) {
        [UIAlertView showTip:@"请输入密码"];
        return;
    }
    
    [self creatLoginModel];
}


#pragma mark - UITextFieldDelegate

- (void)textFieldDidBeginEditing:(UITextField *)textField
{
    [self.view setFrame:XYWH(self.view.origin.x, self.view.origin.y-60, self.view.size.width, self.view.size.height)];
}

- (void)textFieldDidEndEditing:(UITextField *)textField
{
    [self.view setFrame:XYWH(self.view.origin.x, self.view.origin.y+60, self.view.size.width, self.view.size.height)];
}


#pragma mark - Model

- (void)creatLoginModel
{
    if (_loginModel) {
        _loginModel.delegate = nil;
        DO_RELEASE_SAFELY(_loginModel);
    }
    _loginModel = [[BaseModel alloc] init];
    _loginModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:@"2"] forKey:@"platformType"];
    [aDic setObject:[DES3Util enDES3:self.userNameTxt.text] forKey:@"username"];
    [aDic setObject:[DES3Util enDES3:self.pwdTxt.text] forKey:@"userPwd"];
    [aDic setObject:[DES3Util enDES3:[UIDevice currentDevice].uniqueDeviceIdentifier] forKey:@"deviceId"];
    [aDic setObject:[[NSString stringWithFormat:@"2%@%@%@", self.userNameTxt.text, self.pwdTxt.text, [UIDevice currentDevice].uniqueDeviceIdentifier] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KLoginUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_loginModel startRequest:requestUrl];
}

- (void)modelDidStartLoad:(DOModel *)model
{
    [self initHUBTitle:@"正在登录..." subTitle:nil];
}

- (void)modelDidFinishLoad:(DOModel *)model
{
    [self removeHub];
    RequstResult *aRR = [((BaseModel*)model).dataDic objectForKey:@"RR"];
    if (model == self.loginModel) {
        if (aRR.code) {
            
            if (aRR.dataDic == nil) {
                [UserInfo reset];
                return;
            }
            [UserInfo sharedInstance].userId = [[aRR.dataDic objectForKey:@"loginUserInfo"] objectForKey:@"userId"];
            [UserInfo sharedInstance].userName = [[aRR.dataDic objectForKey:@"loginUserInfo"] objectForKey:@"username"];
            [UserInfo sharedInstance].name = [[aRR.dataDic objectForKey:@"loginUserInfo"] objectForKey:@"name"];
            [UserInfo sharedInstance].organizationId = [[aRR.dataDic objectForKey:@"loginUserInfo"] objectForKey:@"organizationId"];
            [UserInfo sharedInstance].userType = [[aRR.dataDic objectForKey:@"loginUserInfo"] objectForKey:@"userType"];
            
            KCSaveData *aSaveData = [[[KCSaveData alloc] initWithFileName:@"login_users"] autorelease];
            [aSaveData saveString:self.userNameTxt.text forKey:@"name"];
            [aSaveData saveString:self.pwdTxt.text forKey:@"password"];
            
            [self performSegueWithIdentifier:@"LoginSegueID" sender:nil];
            return;
        }
        [UIAlertView showTip:aRR.desc];
    }
}

- (void)model:(DOModel *)model didFailLoadWithError:(NSError *)error
{
    [self removeHub];
    [UIAlertView showTip:NetWorkFaild];
}

@end



