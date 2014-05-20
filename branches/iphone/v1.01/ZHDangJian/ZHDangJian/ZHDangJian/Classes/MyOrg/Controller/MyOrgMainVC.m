//
//  MyOrgMainVC.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-11.
//
//

#import "MyOrgMainVC.h"
#import "MyScoreDetailVC.h"
#import "RankListVC.h"
#import "MyOrgInfoVC.h"

@interface MyOrgMainVC ()

@end

@implementation MyOrgMainVC

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
    
    [self showNavBtn:72 target:self action:@selector(quitAction) title:@"注销登录"];
    
    _headerView = [[BaseHeaderView alloc] initWithFrame:CGRectMake(0.0f, -_myScrollView.height, _myScrollView.width, _myScrollView.height) isHeader:YES];
    _headerView.delegate = self;
    [_myScrollView addSubview:_headerView];
    [_headerView refreshLastUpdatedDate];
    
    if ([UIScreen mainScreen].bounds.size.height == 480) {
        _myScrollView.contentSize = CGSizeMake(320, 460);
    } else {
        _myScrollView.contentSize = CGSizeMake(320, 480);
    }
    
    _dataDic = [[NSMutableDictionary alloc] init];
    _rangkListType = [[NSString alloc] init];
    
    [self creatMyUserInfoModel];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(creatMyUserInfoModel) name:@"myOrgInfChanged" object:nil];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_myScrollView release];
    [_userImage release];
    [_userNameLabel release];
    [_myScoreBtn release];
    [_totalRankBtn release];
    [_branchRankBtn release];
    [_meetingNumBtn release];
    [_activeNumBtn release];
    _myUserInfoModel.delegate = nil;
    DO_RELEASE_SAFELY(_myUserInfoModel);
    [_dataDic release];
    [_rangkListType release];
    [_lifestyleNumBtn release];
    [super dealloc];
}

- (void)viewDidUnload {
    [self setMyScrollView:nil];
    [self setUserImage:nil];
    [self setUserNameLabel:nil];
    [self setMyScoreBtn:nil];
    [self setTotalRankBtn:nil];
    [self setBranchRankBtn:nil];
    [self setMeetingNumBtn:nil];
    [self setActiveNumBtn:nil];
    [self setLifestyleNumBtn:nil];
    [super viewDidUnload];
}


#pragma mark - Action

// 注销
- (void)quitAction
{
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:nil message:@"确定要退出登录吗？" delegate:self cancelButtonTitle:@"取消" otherButtonTitles:@"确定", nil];
    alert.tag = 1;
    [alert show];
    [alert release];
}

- (IBAction)clickButton:(id)sender {
    
    int btnIndex = ((UIButton *)sender).tag;
    switch (btnIndex) {
        case 1:
            // 我的积分
            [self performSegueWithIdentifier:@"MyScoreDetailVCID" sender:nil];
            break;
        case 2:
            // 总排名
        {
            self.rangkListType = @"1";
            [self performSegueWithIdentifier:@"RankListVCID" sender:nil];
        }
            break;
        case 3:
            // 支部内排名
        {
            self.rangkListType = @"2";
            [self performSegueWithIdentifier:@"RankListVCID" sender:nil];
        }
            break;
        case 4:
            // 三会一课
            [self performSegueWithIdentifier:@"MeetingListVCID" sender:nil];
            break;
        case 5:
            // 支部活动
            [self performSegueWithIdentifier:@"BranchActivityListVCID" sender:nil];
            break;
        case 6:
            // 民主生活会
            [self performSegueWithIdentifier:@"LifestyleListVCID" sender:nil];
            break;
        case 7:
            // 志愿活动
            [self performSegueWithIdentifier:@"WishActivityListVCID" sender:nil];
            break;
        case 8:
            // 我的资料
            [self performSegueWithIdentifier:@"MyOrgInfoVCID" sender:nil];
            break;
        case 9:
            // 我的支部
        {
            [UIAlertView showTip:@"该模块正在建设中，请继续关注！"];
        }
            break;
        case 10:
            // 修改密码
            [self performSegueWithIdentifier:@"ModifyPwdVCID" sender:nil];
            break;
        default:
            break;
    }
}

- (void)setDataAction
{
    self.userImage.defaultImage = [UIImage imageNamed:@"Myhomepage_头像背景@2x.png"];
    [self.userImage setURLPath:[NSString stringWithFormat:@"%@%@", KImageURL, [[self.dataDic objectForKey:@"partyMenberInfo"] objectForKey:@"portraitPic"]]];
    self.userImage.layer.cornerRadius = 4;
    self.userImage.layer.masksToBounds = YES;
    [self.userImage setImageContentMode:UIViewContentModeScaleToFill];
    self.userNameLabel.text = [[self.dataDic objectForKey:@"partyMenberInfo"] objectForKey:@"name"];
    [self.myScoreBtn setTitle:[[self.dataDic objectForKey:@"partyMenberInfo"] objectForKey:@"integralTotal"] forState:UIControlStateNormal];
    [self.totalRankBtn setTitle:[[self.dataDic objectForKey:@"partyMenberInfo"] objectForKey:@"integralRank"] forState:UIControlStateNormal];
    [self.branchRankBtn setTitle:[[self.dataDic objectForKey:@"partyMenberInfo"] objectForKey:@"branchRanking"] forState:UIControlStateNormal];
    if ([[[self.dataDic objectForKey:@"partyMenberInfo"] objectForKey:@"meetingCount"] intValue] > 0) {
        [self.meetingNumBtn setTitle:[NSString stringWithFormat:@"%i", [[[self.dataDic objectForKey:@"partyMenberInfo"] objectForKey:@"meetingCount"] intValue]] forState:UIControlStateNormal];
        self.meetingNumBtn.hidden = NO;
    } else {
        self.meetingNumBtn.hidden = YES;
    }
    if ([[[self.dataDic objectForKey:@"partyMenberInfo"] objectForKey:@"activityCount"] intValue] > 0) {
        [self.activeNumBtn setTitle:[NSString stringWithFormat:@"%i", [[[self.dataDic objectForKey:@"partyMenberInfo"] objectForKey:@"activityCount"] intValue]] forState:UIControlStateNormal];
        self.activeNumBtn.hidden = NO;
    } else {
        self.activeNumBtn.hidden = YES;
    }
    if ([[[self.dataDic objectForKey:@"partyMenberInfo"] objectForKey:@"democrticlifeCount"] intValue] > 0) {
        [self.lifestyleNumBtn setTitle:[NSString stringWithFormat:@"%i", [[[self.dataDic objectForKey:@"partyMenberInfo"] objectForKey:@"democrticlifeCount"] intValue]] forState:UIControlStateNormal];
        self.lifestyleNumBtn.hidden = NO;
    } else {
        self.lifestyleNumBtn.hidden = YES;
    }
    
}


#pragma mark - Model

- (void)creatMyUserInfoModel
{
    _isLoading = NO;
    
    if (_myUserInfoModel) {
        _myUserInfoModel.delegate = nil;
        DO_RELEASE_SAFELY(_myUserInfoModel);
    }
    _myUserInfoModel = [[BaseModel alloc] init];
    _myUserInfoModel.delegate = self;
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:[UserInfo sharedInstance].userId] forKey:@"userId"];
    [aDic setObject:[DES3Util enDES3:@"1"] forKey:@"userType"];
    [aDic setObject:[[NSString stringWithFormat:@"%@1", [UserInfo sharedInstance].userId] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KMyUserInfoUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_myUserInfoModel startRequest:requestUrl];
}

- (void)modelDidStartLoad:(DOModel *)model
{
    [self initHUBTitle:nil subTitle:nil];
    _isLoading = YES;
}

- (void)modelDidFinishLoad:(DOModel *)model
{
    [self removeHub];
    _isLoading = NO;
    [_headerView egoRefreshScrollViewDataSourceDidFinishedLoading:_myScrollView];
    
    RequstResult *aRR = [((BaseModel *)model).dataDic objectForKey:@"RR"];
    if (model == self.myUserInfoModel) {
        if (aRR.code) {
            if (aRR.dataDic) {
                self.dataDic = [NSMutableDictionary dictionaryWithDictionary:aRR.dataDic];
                [self setDataAction];
                return;
            }
        }
        [UIAlertView showTip:aRR.desc];
        return;
    }
}

- (void)model:(DOModel *)model didFailLoadWithError:(NSError *)error
{
    [self removeHub];
    [UIAlertView showTip:NetWorkFaild];
    
    _isLoading = NO;
    [_headerView egoRefreshScrollViewDataSourceDidFinishedLoading:_myScrollView];
}


#pragma mark - UIAlertViewDelegate
- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex
{
    if (alertView.tag == 1) {
        if (buttonIndex == 1) {
            [UserInfo reset];
            [[NSNotificationCenter defaultCenter] postNotificationName:@"ExitAppNotification" object:nil];
        }
    }
}


#pragma mark - ScrollViewDelegate

-(void)scrollViewDidScroll:(UIScrollView*)aScrollView{
    
    if(aScrollView.contentOffset.y < 0){
        [_headerView egoRefreshScrollViewDidScroll:aScrollView];
    }
}

- (void)scrollViewDidEndDragging:(UIScrollView *)aScrollView willDecelerate:(BOOL)decelerate{
    
    
    if(aScrollView.contentOffset.y < 0){
        
        [_headerView egoRefreshScrollViewDidEndDragging:aScrollView];
        
    }
}


- (void)egoRefreshTableHeaderDidTriggerRefresh:(BaseHeaderView*)view{
    
    //头部触发加载
    if (!_isLoading) {
        
        [self creatMyUserInfoModel];
    }
}


- (BOOL)egoRefreshTableHeaderDataSourceIsLoading:(BaseHeaderView*)view{
    
	return _isLoading;
}

- (NSDate*)egoRefreshTableHeaderDataSourceLastUpdated:(BaseHeaderView*)view{
	
	return [NSDate date];
}



#pragma mark - Segue

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([segue.identifier isEqualToString:@"MyScoreDetailVCID"]) {
        MyScoreDetailVC *vc = (MyScoreDetailVC *)segue.destinationViewController;
        vc.totalScoreString = self.myScoreBtn.titleLabel.text;
    }
    if ([segue.identifier isEqualToString:@"RankListVCID"]) {
        RankListVC *vc = (RankListVC *)segue.destinationViewController;
        vc.type = self.rangkListType;
        vc.branchId = [[self.dataDic objectForKey:@"partyMenberInfo"] objectForKey:@"organizationId"];
    }
    if ([segue.identifier isEqualToString:@"MyOrgInfoVCID"]) {
        MyOrgInfoVC *vc = (MyOrgInfoVC *)segue.destinationViewController;
        vc.dataDic = self.dataDic;
    }
}

@end
