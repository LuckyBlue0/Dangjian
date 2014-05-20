//
//  DemocraticAppraisalDetailVC.m
//  ZHDangJian
//
//  Created by do1 on 13-11-12.
//
//

#import "DemocraticAppraisalDetailVC.h"

@interface DemocraticAppraisalDetailVC ()

@end

@implementation DemocraticAppraisalDetailVC

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
    
    _voteSelection = [[NSString alloc] initWithFormat:@""];
    if ([self.voteStatue intValue] == 1) {
        [self showNavBtn:48 target:self action:@selector(completeAction) title:@"完成"];
        self.myTextView.userInteractionEnabled = YES;
        self.discussDesLabel.text = @"您还没对该机关评议，马上做出选择吧！";
    } else {
        _discussButton1.userInteractionEnabled = NO;
        _discussButton2.userInteractionEnabled = NO;
        _discussButton3.userInteractionEnabled = NO;
        _discussButton4.userInteractionEnabled = NO;
        self.myTextView.userInteractionEnabled = NO;
        self.discussDesLabel.text = @"您已评议过该机关，感谢您的评议！";
    }
    self.titleLabel.text = self.govName;
    self.myTextView.placeholder = @"输入评论意见和评议不满意说明";

    [self creatCheckContentModel];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_discussButton1 release];
    [_discussButton2 release];
    [_discussButton3 release];
    [_discussButton4 release];
    [_myTextView release];
    _feedBackModel.delegate = nil;
    DO_RELEASE_SAFELY(_feedBackModel);
    _checkContentModel.delegate = nil;
    DO_RELEASE_SAFELY(_checkContentModel);
    [_voteSelection release];
    [_titleLabel release];
    [_voteStatue release];
    [_discussDesLabel release];
    [super dealloc];
}

- (void)viewDidUnload {
    [self setDiscussButton1:nil];
    [self setDiscussButton2:nil];
    [self setDiscussButton3:nil];
    [self setDiscussButton4:nil];
    [self setMyTextView:nil];
    [self setTitleLabel:nil];
    [self setDiscussDesLabel:nil];
    [super viewDidUnload];
}


#pragma mark - Action

- (IBAction)buttonAction1:(UIButton *)sender {
    _discussButton1.selected = YES;
    _discussButton2.selected = NO;
    _discussButton3.selected = NO;
    _discussButton4.selected = NO;
    self.voteSelection = @"1";
}

- (IBAction)buttonAction2:(UIButton *)sender {
    _discussButton1.selected = NO;
    _discussButton2.selected = YES;
    _discussButton3.selected = NO;
    _discussButton4.selected = NO;
}

- (IBAction)buttonAction3:(UIButton *)sender {
    _discussButton1.selected = NO;
    _discussButton2.selected = NO;
    _discussButton3.selected = YES;
    _discussButton4.selected = NO;
}

- (IBAction)buttonAction4:(UIButton *)sender {
    _discussButton1.selected = NO;
    _discussButton2.selected = NO;
    _discussButton3.selected = NO;
    _discussButton4.selected = YES;
}

- (IBAction)closeKeyboardButton:(UIButton *)sender {
    [_myTextView resignFirstResponder];
}

- (void)completeAction
{
    [self closeKeyboardButton:nil];
    
    if ([self.voteSelection isEmpty]) {
        [UIAlertView showTip:@"请选择评议选项"];
        return;
    }
    
    if ([self.voteSelection isEqualToString:@"4"] && ([self.myTextView.text isEmpty] || [self.myTextView.text isEqualToString:@"(null)"])) {
        [UIAlertView showTip:@"评论意见不可为空"];
        return;
    }
    [self creatFeedBackModel];
}


#pragma mark - UITextViewDelegate

-(BOOL) textView :(UITextView *) textView shouldChangeTextInRange:(NSRange)range replacementText:(NSString *) text {
    if ([text isEqualToString:@"\n"]) {
        [self closeKeyboardButton:nil];
    }
    return YES;
}

-(void)textViewDidBeginEditing:(UITextView *)textView
{
    [self.view setFrame:XYWH(self.view.origin.x, self.view.origin.y-180, self.view.frame.size.width, self.view.frame.size.height)];
}

-(void)textViewDidEndEditing:(UITextView *)textView
{
     [self.view setFrame:XYWH(self.view.origin.x, self.view.origin.y+180, self.view.frame.size.width, self.view.frame.size.height)];
}

#pragma mark - Model

- (void)creatCheckContentModel
{
    if (_checkContentModel) {
        _checkContentModel.delegate = nil;
        DO_RELEASE_SAFELY(_checkContentModel);
    }
    _checkContentModel = [[BaseModel alloc] init];
    _checkContentModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:self.activityID] forKey:@"id"];
    [aDic setObject:[DES3Util enDES3:[UserInfo sharedInstance].userId] forKey:@"userId"];
    [aDic setObject:[[NSString stringWithFormat:@"%@%@", self.activityID, [UserInfo sharedInstance].userId] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KGovernVoteContentUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_checkContentModel startRequest:requestUrl];
}

- (void)creatFeedBackModel
{
    if (_feedBackModel) {
        _feedBackModel.delegate=nil;
        DO_RELEASE_SAFELY(_feedBackModel);
    }
    _feedBackModel = [[BaseModel alloc] init];
    _feedBackModel.delegate =self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:self.activityID] forKey:@"id"];
    [aDic setObject:[DES3Util enDES3:[UserInfo sharedInstance].userId] forKey:@"userId"];
    [aDic setObject:[DES3Util enDES3:self.voteSelection] forKey:@"voteNum"];
    [aDic setObject:[DES3Util enDES3:[self.myTextView.text isEqualToString:@"(null)"]?@"":self.myTextView.text] forKey:@"reason"];
    [aDic setObject:[[NSString stringWithFormat:@"%@%@%@%@", self.activityID, [UserInfo sharedInstance].userId, self.voteSelection, [self.myTextView.text isEqualToString:@"(null)"]?@"":self.myTextView.text] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KGovDiscussVoteUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_feedBackModel startRequest:requestUrl];
}

- (void)modelDidStartLoad:(DOModel*)model{
    if (model == self.feedBackModel) {
        [self initHUBTitle:@"正在提交..." subTitle:nil];
    } else if (model == self.checkContentModel){
        [self initHUBTitle:nil subTitle:nil];
    }
}

- (void)modelDidFinishLoad:(DOModel*)model{
    [self removeHub];
    RequstResult *aRR = [((BaseModel*)model).dataDic objectForKey:@"RR"];
    LOG(@"aRR.code=%i",aRR.code);
    LOG(@"aRR.desc=%@",aRR.desc);
    LOG(@"aRR.dataDic=%@",aRR.dataDic);
    if (model == self.feedBackModel) {
        if (aRR.code) {
            [[NSNotificationCenter defaultCenter] postNotificationName:@"submitDiscuss" object:nil];
            [self backAction];
            return;
        }
    }
    if (model == self.checkContentModel) {
        if (aRR.code) {
            self.myTextView.text = [[aRR.dataDic objectForKey:@"voteContent"] objectForKey:@"reason"];
            self.voteSelection = [[aRR.dataDic objectForKey:@"voteContent"] objectForKey:@"voteResult"];
            switch ([self.voteSelection intValue]) {
                case 1:
                {
                    _discussButton1.selected = YES;
                    _discussButton2.selected = NO;
                    _discussButton3.selected = NO;
                    _discussButton4.selected = NO;
                }
                    
                    break;
                case 2:
                {
                    _discussButton1.selected = NO;
                    _discussButton2.selected = YES;
                    _discussButton3.selected = NO;
                    _discussButton4.selected = NO;
                }
                    break;
                case 3:
                {
                    _discussButton1.selected = NO;
                    _discussButton2.selected = NO;
                    _discussButton3.selected = YES;
                    _discussButton4.selected = NO;
                }
                    break;
                case 4:
                {
                    _discussButton1.selected = NO;
                    _discussButton2.selected = NO;
                    _discussButton3.selected = NO;
                    _discussButton4.selected = YES;
                }
                    break;
                default:
                    break;
            }
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
