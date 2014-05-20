//
//  ModifyPwdVC.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-14.
//
//

#import "ModifyPwdVC.h"

@interface ModifyPwdVC ()

@end

@implementation ModifyPwdVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view.
    
    [self showBack];
    [self showNavBtn:60 target:self action:@selector(saveAction) title:@"保存"];
    
    [[NSNotificationCenter defaultCenter] addObserver:self
                                             selector:@selector(calculator)
                                                 name:UITextFieldTextDidChangeNotification
                                               object:nil];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_oldPwdTxt release];
    [_newPwdTxt release];
    [_surePwdTxt release];
    [_noticeLabel release];
    [super dealloc];
}

- (void)viewDidUnload {
    [self setOldPwdTxt:nil];
    [self setNewPwdTxt:nil];
    [self setSurePwdTxt:nil];
    [self setNoticeLabel:nil];
    [super viewDidUnload];
}


#pragma mark - Action

- (void)saveAction
{
    [self keyBoardDisappear:nil];
    if ((!(self.newPwdTxt.text.length >=6 && self.newPwdTxt.text.length <= 16))||(!(self.surePwdTxt.text.length >=6 && self.surePwdTxt.text.length <= 16))) {
        [UIAlertView showTip:@"密码长度为6-16位，请重新输入！"];
        return;
    }
    if (![self.newPwdTxt.text isEqualToString:self.surePwdTxt.text]) {
        [UIAlertView showTip:@"确认密码与新密码不一致，请重新输入！"];
        return;
    }
    [self creatModel];

}

- (void)calculator
{
    if ((self.newPwdTxt.text.length > 0) && (self.surePwdTxt.text.length > 0)) {
        if ([self.surePwdTxt.text isEqualToString:self.newPwdTxt.text]) {
            self.noticeLabel.text = @"密码可用";
        } else {
            self.noticeLabel.text = @"新密码和确认密码不一致，请重新输入";
        }
        
    }
}

- (IBAction)keyBoardDisappear:(id)sender {
    
    [self.oldPwdTxt resignFirstResponder];
    [self.newPwdTxt resignFirstResponder];
    [self.surePwdTxt resignFirstResponder];
}


#pragma mark - model

- (void)creatModel
{
    if (_modifyPwdModel) {
        _modifyPwdModel.delegate = nil;
        DO_RELEASE_SAFELY(_modifyPwdModel)
    }
    _modifyPwdModel = [[BaseModel alloc] init];
    _modifyPwdModel.delegate =self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:[UserInfo sharedInstance].userId] forKey:@"userId"];
    [aDic setObject:[DES3Util enDES3:self.newPwdTxt.text] forKey:@"newPwd"];
    [aDic setObject:[DES3Util enDES3:self.oldPwdTxt.text] forKey:@"oldPwd"];
    [aDic setObject:[[NSString stringWithFormat:@"%@%@%@", [UserInfo sharedInstance].userId, self.newPwdTxt.text, self.oldPwdTxt.text] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KModifyPwdUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_modifyPwdModel startRequest:requestUrl];
}


- (void)modelDidStartLoad:(DOModel*)model{
    [self initHUBTitle:@"正在保存" subTitle:@"请稍后..."];

}

- (void)modelDidFinishLoad:(DOModel*)model{
    [self removeHub];
    RequstResult *aRR = [((BaseModel*)model).dataDic objectForKey:@"RR"];
    if (model == self.modifyPwdModel) {
        if (aRR.code==1) {
            [UIAlertView showTip:@"密码修改成功!"];
            [self.navigationController popViewControllerAnimated:YES];
            return;
        }
        [UIAlertView showTip:aRR.desc];
        return;
    }
    
}



- (void)model:(DOModel*)model didFailLoadWithError:(NSError*)error{
    [self removeHub];
    [UIAlertView showTip:NetWorkFaild];
}


@end
