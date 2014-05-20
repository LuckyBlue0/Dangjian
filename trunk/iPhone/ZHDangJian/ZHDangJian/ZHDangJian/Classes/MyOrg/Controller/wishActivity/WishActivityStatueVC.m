//
//  WishActivityStatueVC.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-14.
//
//

#import "WishActivityStatueVC.h"
#import "WishDetailVC.h"
#import "WishXinYuListVC.h"

@interface WishActivityStatueVC ()

@end

@implementation WishActivityStatueVC

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
    
    [self creatWishDetailModel];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)viewDidUnload
{
    [self setTitleLabel:nil];
    [self setSignNumBtn:nil];
    [self setSignBtn:nil];
    [super viewDidUnload];
}

- (void)dealloc
{
    [_dataDic release];
    [_detailDataDic release];
    _wishDetailModel.delegate = nil;
    DO_RELEASE_SAFELY(_wishDetailModel);
    [_titleLabel release];
    [_signNumBtn release];
    [_signBtn release];
    _wishSignModel.delegate = nil;
    DO_RELEASE_SAFELY(_wishSignModel);
    [super dealloc];
}


#pragma mark - Action

- (IBAction)checkMeetingDeatilAction:(id)sender {
    
    [self performSegueWithIdentifier:@"WishDetailVCID" sender:nil];
}

- (IBAction)bottomBtnAction:(id)sender {
    [self creatWishSignModel];
}

- (IBAction)checkWishAction:(id)sender {
    [self performSegueWithIdentifier:@"WishXinYuListVCID" sender:nil];
}

- (void)setDataAndShowLayout
{
    self.titleLabel.text = [[self.detailDataDic objectForKey:@"details"] objectForKey:@"title"];
    [self.signNumBtn setTitle:[[self.detailDataDic objectForKey:@"details"] objectForKey:@"signUpCount"] forState:UIControlStateNormal];
    if ([[[self.detailDataDic objectForKey:@"details"] objectForKey:@"signUpStatus"] intValue]) {
        [self.signBtn setTitle:@"取消报名" forState:UIControlStateNormal];
        [self.signBtn setTitle:@"取消报名" forState:UIControlStateHighlighted];
        [self.signBtn setBackgroundImage:[UIImage imageNamed:@"meeting_蓝色小按钮@2x.png"] forState:UIControlStateNormal];
        [self.signBtn setBackgroundImage:[UIImage imageNamed:@"meeting_蓝色小按钮_hover@2x.png"] forState:UIControlStateHighlighted];
    } else {
        [self.signBtn setTitle:@"我要报名" forState:UIControlStateNormal];
        [self.signBtn setTitle:@"我要报名" forState:UIControlStateHighlighted];
        [self.signBtn setBackgroundImage:[UIImage imageNamed:@"meeting_绿色小按钮@2x.png"] forState:UIControlStateNormal];
        [self.signBtn setBackgroundImage:[UIImage imageNamed:@"meeting_绿色小按钮_hover@2x.png"] forState:UIControlStateHighlighted];
    }
}


#pragma mark - Model

- (void)creatWishDetailModel
{
    if (_wishDetailModel) {
        _wishDetailModel.delegate = nil;
        DO_RELEASE_SAFELY(_wishDetailModel);
    }
    _wishDetailModel = [[BaseModel alloc] init];
    _wishDetailModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:[self.dataDic objectForKey:@"id"]] forKey:@"id"];
    [aDic setObject:[DES3Util enDES3:[UserInfo sharedInstance].userId] forKey:@"userId"];
    [aDic setObject:[DES3Util enDES3:@"4"] forKey:@"type"];
    [aDic setObject:[[NSString stringWithFormat:@"%@%@4", [self.dataDic objectForKey:@"id"], [UserInfo sharedInstance].userId] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KActivityDetailUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_wishDetailModel startRequest:requestUrl];
}

- (void)creatWishSignModel
{
    if (_wishSignModel) {
        _wishSignModel.delegate = nil;
        DO_RELEASE_SAFELY(_wishSignModel);
    }
    _wishSignModel = [[BaseModel alloc] init];
    _wishSignModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:[UserInfo sharedInstance].userId] forKey:@"userId"];
    [aDic setObject:[DES3Util enDES3:[[self.detailDataDic objectForKey:@"details"] objectForKey:@"id"]] forKey:@"id"];
    [aDic setObject:[DES3Util enDES3:[NSString stringWithFormat:@"%i", [[[self.detailDataDic objectForKey:@"details"] objectForKey:@"signUpStatus"] intValue]+1]] forKey:@"type"];
    [aDic setObject:[NSString stringWithFormat:@"%@%@%i", [UserInfo sharedInstance].userId, [[self.detailDataDic objectForKey:@"details"] objectForKey:@"id"], [[[self.detailDataDic objectForKey:@"details"] objectForKey:@"signUpStatus"] intValue]+1] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KSignUpOrNotUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_wishSignModel startRequest:requestUrl];
}

- (void)modelDidStartLoad:(DOModel *)model
{
    [self initHUBTitle:nil subTitle:nil];
}

- (void)modelDidFinishLoad:(DOModel *)model
{
    [self removeHub];
    RequstResult *aRR = [((BaseModel *)model).dataDic objectForKey:@"RR"];
    if (model == self.wishDetailModel) {
        if (aRR.code) {
            self.detailDataDic = [NSMutableDictionary dictionaryWithDictionary:aRR.dataDic];
            [self setDataAndShowLayout];
            return;
        }
        [UIAlertView showTip:aRR.desc];
        return;
    }
    if (model == self.wishSignModel) {
        if (aRR.code) {
            [[NSNotificationCenter defaultCenter] postNotificationName:@"wishSignSuccess" object:nil];
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
    if ([segue.identifier isEqualToString:@"WishDetailVCID"]){
        WishDetailVC *vc = (WishDetailVC *)segue.destinationViewController;
        vc.dataDic = self.detailDataDic;
    }
    if ([segue.identifier isEqualToString:@"WishXinYuListVCID"]) {
        WishXinYuListVC *vc = (WishXinYuListVC *)segue.destinationViewController;
        vc.idString = [[self.detailDataDic objectForKey:@"details"] objectForKey:@"id"];
        vc.titleString = [[self.detailDataDic objectForKey:@"details"] objectForKey:@"title"];
    }
}


@end
