//
//  LifestyleSatueVC.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-14.
//
//

#import "LifestyleSatueVC.h"
#import "LifestyleDetailVC.h"
#import "ExperienceListVC.h"

@interface LifestyleSatueVC ()

@end

@implementation LifestyleSatueVC

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
    
    [self creatLifestyleDetailModel];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(creatLifestyleDetailModel) name:@"signSuccess" object:nil];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_noMeetingLabel release];
    [_signView release];
    [_titleLabel release];
    [_signNumBtn release];
    [_haveSignLabel release];
    [_bottomBtn release];
    [_experienceView release];
    _lifestyleDetailModel.delegate = nil;
    DO_RELEASE_SAFELY(_lifestyleDetailModel);
    [_detailDataDic release];
    [[NSNotificationCenter defaultCenter] removeObserver:self];
    [super dealloc];
}

- (void)viewDidUnload {
    [self setNoMeetingLabel:nil];
    [self setSignView:nil];
    [self setTitleLabel:nil];
    [self setSignNumBtn:nil];
    [self setHaveSignLabel:nil];
    [self setBottomBtn:nil];
    [self setExperienceView:nil];
    [super viewDidUnload];
}


#pragma mark - Action

- (IBAction)checkMeetingDeatilAction:(id)sender {
    
    [self performSegueWithIdentifier:@"LifestyleDetailVCID" sender:nil];
}

- (IBAction)bottomBtnAction:(id)sender {
    
    [self performSegueWithIdentifier:@"LifestyleQRVCID" sender:nil];
}

- (IBAction)checkExperienceAction:(id)sender {
    
    [self performSegueWithIdentifier:@"ExperienceListVCID" sender:nil];
}

- (void)setDataAndShowLayout
{
    self.titleLabel.text = [[self.detailDataDic objectForKey:@"details"] objectForKey:@"title"];
    [self.signNumBtn setTitle:[[self.detailDataDic objectForKey:@"details"] objectForKey:@"signInCount"] forState:UIControlStateNormal];
    if ([[self.dataDic objectForKey:@"carryOutStatus"] intValue] == 0) {
        self.noMeetingLabel.hidden = NO;
        self.signView.hidden = YES;
        self.bottomBtn.hidden = YES;
        self.experienceView.hidden = YES;
    } else {
        self.noMeetingLabel.hidden = YES;
        self.signView.hidden = NO;
        self.experienceView.hidden = NO;
        if ([[[self.detailDataDic objectForKey:@"details"] objectForKey:@"signInStatus"] intValue]) {
            self.bottomBtn.hidden = YES;
            self.haveSignLabel.hidden = NO;
        } else {
            self.bottomBtn.hidden = NO;
            self.haveSignLabel.hidden = YES;
        }
    }
}



#pragma mark - Model

- (void)creatLifestyleDetailModel
{
    if (_lifestyleDetailModel) {
        _lifestyleDetailModel.delegate = nil;
        DO_RELEASE_SAFELY(_lifestyleDetailModel);
    }
    _lifestyleDetailModel = [[BaseModel alloc] init];
    _lifestyleDetailModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:[self.dataDic objectForKey:@"id"]] forKey:@"id"];
    [aDic setObject:[DES3Util enDES3:[UserInfo sharedInstance].userId] forKey:@"userId"];
    [aDic setObject:[DES3Util enDES3:@"3"] forKey:@"type"];
    [aDic setObject:[[NSString stringWithFormat:@"%@%@3", [self.dataDic objectForKey:@"id"], [UserInfo sharedInstance].userId] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KActivityDetailUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_lifestyleDetailModel startRequest:requestUrl];
    
}

- (void)modelDidStartLoad:(DOModel *)model
{
    [self initHUBTitle:nil subTitle:nil];
}

- (void)modelDidFinishLoad:(DOModel *)model
{
    [self removeHub];
    RequstResult *aRR = [((BaseModel *)model).dataDic objectForKey:@"RR"];
    if (model == self.lifestyleDetailModel) {
        if (aRR.code) {
            self.detailDataDic = [NSMutableDictionary dictionaryWithDictionary:aRR.dataDic];
            [self setDataAndShowLayout];
            return;
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
    if ([segue.identifier isEqualToString:@"LifestyleDetailVCID"]) {
        LifestyleDetailVC *vc = (LifestyleDetailVC *)segue.destinationViewController;
        vc.dataDic = self.detailDataDic;
    }
    if ([segue.identifier isEqualToString:@"ExperienceListVCID"]) {
        ExperienceListVC *vc = (ExperienceListVC *)segue.destinationViewController;
        vc.idString = [[self.detailDataDic objectForKey:@"details"] objectForKey:@"id"];
        vc.titleString = [[self.detailDataDic objectForKey:@"details"] objectForKey:@"title"];
    }
}


@end
