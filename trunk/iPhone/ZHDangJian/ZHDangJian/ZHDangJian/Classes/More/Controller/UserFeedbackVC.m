//
//  UserFeedbackVC.m
//  ZHDangJian
//
//  Created by do1 on 13-11-12.
//
//

#import "UserFeedbackVC.h"

@interface UserFeedbackVC ()

@end

@implementation UserFeedbackVC

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
    self.title = @"意见反馈";
    [self showNavBtn:48 target:self action:@selector(submitAction) title:@"提交"];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_suggestTextView release];
    [_telephoneTextField release];
    [_remainingWordsLabel release];
    [super dealloc];
}

- (void)viewDidUnload {
    [self setSuggestTextView:nil];
    [self setTelephoneTextField:nil];
    [self setRemainingWordsLabel:nil];
    [super viewDidUnload];
}


#pragma mark - Action

- (void)submitAction
{
    [self closeKeyboardButton:nil];
    if ([self.suggestTextView.text isEmpty]) {
        [UIAlertView showTip:@"反馈信息不可为空"];
        return;
    }
    if (!(remainingWords>=0)) {
        [UIAlertView showTip:@"输入字数不能超过140"];
        return;
    }
//    if ([self.telephoneTextField.text isEmpty]) {
//        [UIAlertView showTip:@"联系方式不可为空"];
//        return;
//    }
//    if (![self.telephoneTextField.text isEmpty] && !([self.telephoneTextField.text validetePhoneNumber:YES]||[self.telephoneTextField.text validateEmail])) {
//        [UIAlertView showTip:@"请输入正确的联系方式"];
//        return;
//    }
    [self creatFeedBackModel];
}

- (IBAction)emptyButton:(UIButton *)sender {
    _suggestTextView.text = @"";
    _remainingWordsLabel.text = [NSString stringWithFormat:@"剩余140"];
}

- (IBAction)closeKeyboardButton:(UIButton *)sender {
    [self.suggestTextView resignFirstResponder];
    [self.telephoneTextField resignFirstResponder];
}

#pragma mark - UITextViewDelegate

-(BOOL) textView :(UITextView *) textView shouldChangeTextInRange:(NSRange)range replacementText:(NSString *) text {
    if ([text isEqualToString:@"\n"]) {
        [self closeKeyboardButton:nil];
    }
    
    if (range.location>=140)
    {
        return  NO;
    }
    else
    {
        return YES;
    }
}

- (void)textViewDidChange:(UITextView *)textView
{
    NSString  * nsTextContent=textView.text;
    int   existTextNum=[nsTextContent length];
    remainingWords = 140-existTextNum;
    _remainingWordsLabel.text = [NSString stringWithFormat:@"剩余%d",140-existTextNum];
}


#pragma mark - UITextFieldDelegate

- (void)textFieldDidBeginEditing:(UITextField *)textField
{
    [self.view setFrame:XYWH(self.view.origin.x, self.view.origin.y-80, self.view.frame.size.width, self.view.frame.size.height)];
}

- (void)textFieldDidEndEditing:(UITextField *)textField
{
    [self.view setFrame:XYWH(self.view.origin.x, self.view.origin.y+80, self.view.frame.size.width, self.view.frame.size.height)];
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField
{
    [self closeKeyboardButton:nil];
    return  YES;
}

#pragma mark - Model

- (void)creatFeedBackModel
{
    if (_feedBackModel) {
        _feedBackModel.delegate=nil;
        DO_RELEASE_SAFELY(_feedBackModel);
    }
    _feedBackModel = [[BaseModel alloc] init];
    _feedBackModel.delegate =self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:@"2"] forKey:@"type"];
    [aDic setObject:[DES3Util enDES3:[UserInfo sharedInstance].userName] forKey:@"userName"];
    [aDic setObject:[DES3Util enDES3:self.suggestTextView.text] forKey:@"suggestion"];
    [aDic setObject:[DES3Util enDES3:[self.telephoneTextField.text isEqualToString:@"(null)"]?@"":self.telephoneTextField.text] forKey:@"mobile"];
    [aDic setObject:[[NSString stringWithFormat:@"2%@%@%@", [UserInfo sharedInstance].userName, self.suggestTextView.text, [self.telephoneTextField.text isEqualToString:@"(null)"]?@"":self.telephoneTextField.text] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KFeedBackUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_feedBackModel startRequest:requestUrl];
}




- (void)modelDidStartLoad:(DOModel*)model{
    if (model == self.feedBackModel) {
        [self initHUBTitle:@"正在提交..." subTitle:nil];
    }
}

- (void)modelDidFinishLoad:(DOModel*)model{
    [self removeHub];
    RequstResult *aRR = [((BaseModel*)model).dataDic objectForKey:@"RR"];
    LOG(@"aRR.code=%i",aRR.code);
    LOG(@"aRR.desc=%@",aRR.desc);
    LOG(@"aRR.dataDic=%@",aRR.dataDic);
    if (model == self.feedBackModel) {
        if (aRR.code==1) {
            [UIAlertView showTip:aRR.desc];
            self.suggestTextView.text = @"";
            self.telephoneTextField.text = @"";
            [self backAction];
            return;
        }
    }
    
    [UIAlertView showTip:aRR.desc];
    
}

- (void)model:(DOModel*)model didFailLoadWithError:(NSError*)error{
    [self removeHub];
    [UIAlertView showTip:NetWorkFaild];
    
}


@end
