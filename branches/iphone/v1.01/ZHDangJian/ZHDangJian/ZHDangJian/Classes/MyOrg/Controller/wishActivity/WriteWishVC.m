//
//  WriteWishVC.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-27.
//
//

#import "WriteWishVC.h"

@interface WriteWishVC ()

@end

@implementation WriteWishVC

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
    [self showNavBtn:72 target:self action:@selector(submitAction) title:@"提交"];
    
    self.myTextView.placeholder = @"输入志愿心语";
    
    self.titleLabel.text = self.titleString;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_titleLabel release];
    [_myTextView release];
    [_activtyId release];
    [_titleString release];
    [super dealloc];
}
- (void)viewDidUnload {
    [self setTitleLabel:nil];
    [self setMyTextView:nil];
    [super viewDidUnload];
}

#pragma mark - Action

- (void)submitAction
{
    if ([self.myTextView.text isEqualToString:@"(null)"] || [self.myTextView.text isEmpty]) {
        [UIAlertView showTip:@"请输入心得体会"];
        return;
    }
    if (self.myTextView.text.length < 70) {
        [UIAlertView showTip:@"心得体会字数不得少于70字"];
        return;
    }
    [self creatSubmitModel];
}

- (IBAction)keyBoardDisappear:(id)sender {
    [self.myTextView resignFirstResponder];
}

#pragma mark - Model

- (void)creatSubmitModel
{
    if (_submitModel) {
        _submitModel.delegate = nil;
        DO_RELEASE_SAFELY(_submitModel);
    }
    _submitModel = [[BaseModel alloc] init];
    _submitModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:@"1"] forKey:@"type"];
    [aDic setObject:[DES3Util enDES3:self.activtyId] forKey:@"activityId"];
    [aDic setObject:[DES3Util enDES3:[UserInfo sharedInstance].userId] forKey:@"userId"];
    [aDic setObject:[DES3Util enDES3:@"2"] forKey:@"source"];
    [aDic setObject:[DES3Util enDES3:self.myTextView.text] forKey:@"content"];
    [aDic setObject:[[NSString stringWithFormat:@"1%@%@2%@", self.activtyId, [UserInfo sharedInstance].userId, self.myTextView.text] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KSubmitStudyUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_submitModel startRequest:requestUrl];
}

- (void)modelDidStartLoad:(DOModel *)model
{
    [self initHUBTitle:nil subTitle:nil];
}

- (void)modelDidFinishLoad:(DOModel *)model
{
    [self removeHub];
    RequstResult *aRR = [((BaseModel *)model).dataDic objectForKey:@"RR"];
    if (model == self.submitModel) {
        if (aRR.dataDic) {
            [[NSNotificationCenter defaultCenter] postNotificationName:@"submitWishXinYu" object:nil];
            [self backAction];
        }
        [UIAlertView showTip:aRR.desc];
        return;
    }
}

- (void)model:(DOModel *)model didFailLoadWithError:(NSError *)error
{
    [self removeHub];
    [UIAlertView showTip:NetWorkFaild];
}


#pragma mark - UITextViewDelegate

- (BOOL)textView :(UITextView *) textView shouldChangeTextInRange:(NSRange)range replacementText:(NSString *) text {
    if ([text isEqualToString:@"\n"]) {
        [textView resignFirstResponder];
    }
    return YES;
}

- (void)textViewDidBeginEditing:(UITextView *)textView
{
    self.view.origin = CGPointMake(self.view.origin.x, self.view.origin.y-100);
}

- (void)textViewDidEndEditing:(UITextView *)textView
{
    self.view.origin = CGPointMake(self.view.origin.x, self.view.origin.y+100);
}

@end
