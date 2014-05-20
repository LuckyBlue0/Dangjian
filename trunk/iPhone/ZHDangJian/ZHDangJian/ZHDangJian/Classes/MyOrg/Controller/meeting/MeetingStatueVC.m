//
//  MeetingStatueVC.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-13.
//
//

#import "MeetingStatueVC.h"
#import "MeetingDetailVC.h"

@interface MeetingStatueVC ()

@end

@implementation MeetingStatueVC

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
    
    [self creatMeetingDetailModel];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(creatMeetingDetailModel) name:@"signSuccess" object:nil];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_noMeetingLabel release];
    [_bottomButton release];
    [_signView release];
    [_dataDic release];
    _meetingDetailModel.delegate = nil;
    DO_RELEASE_SAFELY(_meetingDetailModel);
    [_detailDataDic release];
    [_titleLabel release];
    [_signNumBtn release];
    [_haveSignLabel release];
    _leaveModel.delegate = nil;
    DO_RELEASE_SAFELY(_leaveModel);
    [[NSNotificationCenter defaultCenter] removeObserver:self];
    [super dealloc];
}

- (void)viewDidUnload {
    [self setNoMeetingLabel:nil];
    [self setBottomButton:nil];
    [self setSignView:nil];
    [self setTitleLabel:nil];
    [self setSignNumBtn:nil];
    [self setHaveSignLabel:nil];
    [super viewDidUnload];
}


#pragma mark - Action

- (IBAction)checkMeetingDeatilAction:(id)sender {
    
    [self performSegueWithIdentifier:@"MeetingDetailVCID" sender:nil];
}

- (IBAction)bottomBtnAction:(id)sender {
    
    if ([[self.dataDic objectForKey:@"carryOutStatus"] intValue] == 0){
        [self creatMeetingLeaveModel];
    } else {
        if ([[[self.detailDataDic objectForKey:@"details"] objectForKey:@"signInStatus"] intValue] == 0) {
            [self performSegueWithIdentifier:@"MyOrgQRVCID" sender:nil];
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

- (void)creatMeetingDetailModel
{
    if (_meetingDetailModel) {
        _meetingDetailModel.delegate = nil;
        DO_RELEASE_SAFELY(_meetingDetailModel);
    }
    _meetingDetailModel = [[BaseModel alloc] init];
    _meetingDetailModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:[self.dataDic objectForKey:@"id"]] forKey:@"id"];
    [aDic setObject:[DES3Util enDES3:[UserInfo sharedInstance].userId] forKey:@"userId"];
    [aDic setObject:[DES3Util enDES3:@"1"] forKey:@"type"];
    [aDic setObject:[[NSString stringWithFormat:@"%@%@1", [self.dataDic objectForKey:@"id"], [UserInfo sharedInstance].userId] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KActivityDetailUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_meetingDetailModel startRequest:requestUrl];
 
}

- (void)creatMeetingLeaveModel
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
    if (model == self.meetingDetailModel) {
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
            [[NSNotificationCenter defaultCenter] postNotificationName:@"meetingLeaveSuccess" object:nil];
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
    if ([segue.identifier isEqualToString:@"MeetingDetailVCID"]) {
        MeetingDetailVC *vc = (MeetingDetailVC *)segue.destinationViewController;
        vc.dataDic = self.detailDataDic;
    }
}

@end
