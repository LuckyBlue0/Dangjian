//
//  WriteExperienceVC.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-14.
//
//

#import "WriteExperienceVC.h"

@interface WriteExperienceVC ()

@end

@implementation WriteExperienceVC

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
    
    self.textViewBar.placeholder = @"输入心得体会";
    
    self.titleLabel.text = self.titleString;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_textViewBar release];
    [_activtyId release];
    _submitModel.delegate = nil;
    DO_RELEASE_SAFELY(_submitModel);
    [_titleLabel release];
    [_titleString release];
    [super dealloc];
}

- (void)viewDidUnload {
    [self setTextViewBar:nil];
    [self setTitleLabel:nil];
    [super viewDidUnload];
}


#pragma mark - Action

- (void)submitAction
{
    if ([self.textViewBar.text isEqualToString:@"(null)"] || [self.textViewBar.text isEmpty]) {
        [UIAlertView showTip:@"请输入心得体会"];
        return;
    }
    if (self.textViewBar.text.length < 70) {
        [UIAlertView showTip:@"心得体会字数不得少于70字"];
        return;
    }
    [self creatSubmitModel];
}

- (IBAction)keyBoardDisappear:(id)sender {
    [self.textViewBar resignFirstResponder];
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
    [aDic setObject:[DES3Util enDES3:@"0"] forKey:@"type"];
    [aDic setObject:[DES3Util enDES3:self.activtyId] forKey:@"activityId"];
    [aDic setObject:[DES3Util enDES3:[UserInfo sharedInstance].userId] forKey:@"userId"];
    [aDic setObject:[DES3Util enDES3:@"2"] forKey:@"source"];
    [aDic setObject:[DES3Util enDES3:self.textViewBar.text] forKey:@"content"];
    [aDic setObject:[[NSString stringWithFormat:@"0%@%@2%@", self.activtyId, [UserInfo sharedInstance].userId, self.textViewBar.text] MD5] forKey:@"digest"];
    
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
            [[NSNotificationCenter defaultCenter] postNotificationName:@"submitExperience" object:nil];
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
