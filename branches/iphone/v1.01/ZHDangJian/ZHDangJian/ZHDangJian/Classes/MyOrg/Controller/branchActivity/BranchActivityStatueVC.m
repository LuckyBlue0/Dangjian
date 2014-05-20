//
//  BranchActivityStatueVC.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-13.
//
//

#import "BranchActivityStatueVC.h"
#import "BranchActivityDetailVC.h"

@interface BranchActivityStatueVC ()

@end

@implementation BranchActivityStatueVC

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
    
    _detailDataDic = [[NSMutableDictionary alloc] init];
    
    [self creatBranchDetailModel];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(creatBranchDetailModel) name:@"signSuccess" object:nil];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_noMeetingLabel release];
    [_signView release];
    [_dataDic release];
    _branchDetailModel.delegate = nil;
    DO_RELEASE_SAFELY(_branchDetailModel);
    [_detailDataDic release];
    [_titleLabel release];
    [_signNumBtn release];
    [_bottomButton release];
    [_haveSignLabel release];
    _leaveModel.delegate = nil;
    DO_RELEASE_SAFELY(_leaveModel);
    [[NSNotificationCenter defaultCenter] removeObserver:self];
    [super dealloc];
}

- (void)viewDidUnload {
    [self setNoMeetingLabel:nil];
    [self setSignView:nil];
    [self setTitleLabel:nil];
    [self setSignNumBtn:nil];
    [self setBottomButton:nil];
    [self setHaveSignLabel:nil];
    [super viewDidUnload];
}


#pragma mark - Action

- (IBAction)checkMeetingDeatilAction:(id)sender {
    
    [self performSegueWithIdentifier:@"BranchActivityDetailVCID" sender:nil];
}

- (IBAction)bottomBtnAction:(id)sender {
    
    if ([[self.dataDic objectForKey:@"carryOutStatus"] intValue] == 0){
        [self creatBranchLeaveModel];
    } else {
        if ([[[self.detailDataDic objectForKey:@"details"] objectForKey:@"signInStatus"] intValue] == 0) {
            [self performSegueWithIdentifier:@"BranchActivityQRVCID" sender:nil];
        }
    }
}

- (void)setDataAndShowLayout
{
    self.titleLabel.text = [[self.detailDataDic objectForKey:@"details"] objectForKey:@"title"];
    [self.signNumBtn setTitle:[[self.detailDataDic objectForKey:@"details"] objectForKey:@"signInCount"] forState:UIControlStateNormal];
    if ([[self.dataDic objectForKey:@"carryOutStatus"] intValue] == 0) {
        self.noMeetingLabel.hidden = NO;
        self.signView.hidden = YES;
        if ([[[self.detailDataDic objectForKey:@"details"] objectForKey:@"forLeaveStatus"] intValue]) {
            [self.bottomButton setTitle:@"取消请假" forState:UIControlStateNormal];
            [self.bottomButton setTitle:@"取消请假" forState:UIControlStateHighlighted];
        } else {
            [self.bottomButton setTitle:@"我要请假" forState:UIControlStateNormal];
            [self.bottomButton setTitle:@"我要请假" forState:UIControlStateHighlighted];
        }
        
    } else {
        self.noMeetingLabel.hidden = YES;
        self.signView.hidden = NO;
        if ([[[self.detailDataDic objectForKey:@"details"] objectForKey:@"signInStatus"] intValue]) {
            self.bottomButton.hidden = YES;
            self.haveSignLabel.hidden = NO;
        } else {
            self.bottomButton.hidden = NO;
            self.haveSignLabel.hidden = YES;
            [self.bottomButton setTitle:@"我要签到" forState:UIControlStateNormal];
            [self.bottomButton setTitle:@"我要签到" forState:UIControlStateHighlighted];
        }
    }
}



#pragma mark - Model

- (void)creatBranchDetailModel
{
    if (_branchDetailModel) {
        _branchDetailModel.delegate = nil;
        DO_RELEASE_SAFELY(_branchDetailModel);
    }
    _branchDetailModel = [[BaseModel alloc] init];
    _branchDetailModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:[self.dataDic objectForKey:@"id"]] forKey:@"id"];
    [aDic setObject:[DES3Util enDES3:[UserInfo sharedInstance].userId] forKey:@"userId"];
    [aDic setObject:[DES3Util enDES3:@"2"] forKey:@"type"];
    [aDic setObject:[[NSString stringWithFormat:@"%@%@2", [self.dataDic objectForKey:@"id"], [UserInfo sharedInstance].userId] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KActivityDetailUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_branchDetailModel startRequest:requestUrl];
}

- (void)creatBranchLeaveModel
{
    if (_leaveModel) {
        _leaveModel.delegate = nil;
        DO_RELEASE_SAFELY(_leaveModel);
    }
    _leaveModel = [[BaseModel alloc] init];
    _leaveModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:[UserInfo sharedInstance].userId] forKey:@"userId"];
    [aDic setObject:[DES3Util enDES3:[[self.detailDataDic objectForKey:@"details"] objectForKey:@"id"]] forKey:@"id"];
    [aDic setObject:[DES3Util enDES3:[NSString stringWithFormat:@"%i", [[[self.detailDataDic objectForKey:@"details"] objectForKey:@"forLeaveStatus"] intValue]+1]] forKey:@"type"];
    [aDic setObject:[NSString stringWithFormat:@"%@%@%i", [UserInfo sharedInstance].userId, [[self.detailDataDic objectForKey:@"details"] objectForKey:@"id"], [[[self.detailDataDic objectForKey:@"details"] objectForKey:@"forLeaveStatus"] intValue]+1] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KLeaveOrNotUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_leaveModel startRequest:requestUrl];
}


- (void)modelDidStartLoad:(DOModel *)model
{
    [self initHUBTitle:nil subTitle:nil];
}

- (void)modelDidFinishLoad:(DOModel *)model
{
    [self removeHub];
    RequstResult *aRR = [((BaseModel *)model).dataDic objectForKey:@"RR"];
    if (model == self.branchDetailModel) {
        if (aRR.code) {
            self.detailDataDic = [NSMutableDictionary dictionaryWithDictionary:aRR.dataDic];
            [self setDataAndShowLayout];
            return;
        }
        [UIAlertView showTip:aRR.desc];
        return;
    }
    if (model == self.leaveModel) {
        if (aRR.code) {
            [[NSNotificationCenter defaultCenter] postNotificationName:@"branchLeaveSuccess" object:nil];
            [self.navigationController popViewControllerAnimated:YES];
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


#pragma mark - Segue

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([segue.identifier isEqualToString:@"BranchActivityDetailVCID"]) {
        BranchActivityDetailVC *vc = (BranchActivityDetailVC *)segue.destinationViewController;
        vc.dataDic = self.detailDataDic;
    }
}


@end
