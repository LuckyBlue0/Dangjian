//
//  BranchSignResultVC.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-13.
//
//

#import "BranchSignResultVC.h"

@interface BranchSignResultVC ()

@end

@implementation BranchSignResultVC

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
    
    _titleString = [[NSString alloc] init];
    _idString = [[NSString alloc] init];
    
    NSArray *array0 = [[[NSArray alloc] initWithArray:[self.resultStr componentsSeparatedByString:@";"]] autorelease];
    
    if (array0.count >= 2) {
        self.idString = [array0 objectAtIndex:0];
        self.titleString = [array0 objectAtIndex:1];
    }
    
    self.titleLabel.text = self.titleString;

}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_titleLabel release];
    [super dealloc];
}

- (void)viewDidUnload {
    [self setTitleLabel:nil];
    [super viewDidUnload];
}

#pragma mark - Action

- (void)backAction
{
    [self.navigationController popToViewController:[self.navigationController.viewControllers objectAtIndex:self.navigationController.viewControllers.count-3] animated:YES];
}

- (IBAction)signAction:(id)sender {
    
    [self creatSignModel];
}

- (IBAction)resweepAction:(id)sender {
    
    [[NSNotificationCenter defaultCenter] postNotificationName:@"reScan" object:nil userInfo:nil];
    [self.navigationController popViewControllerAnimated:YES];
}


#pragma mark - Model

- (void)creatSignModel
{
    if (_signModel) {
        _signModel.delegate = nil;
        DO_RELEASE_SAFELY(_signModel);
    }
    _signModel = [[BaseModel alloc] init];
    _signModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:self.idString] forKey:@"id"];
    [aDic setObject:[DES3Util enDES3:[UserInfo sharedInstance].userId] forKey:@"userId"];
    [aDic setObject:[DES3Util enDES3:@"2"] forKey:@"type"];
    [aDic setObject:[[NSString stringWithFormat:@"%@%@2", self.idString, [UserInfo sharedInstance].userId] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KSignUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_signModel startRequest:requestUrl];
}

- (void)modelDidStartLoad:(DOModel *)model
{
    [self initHUBTitle:@"正在提交..." subTitle:nil];
}

- (void)modelDidFinishLoad:(DOModel *)model
{
    [self removeHub];
    RequstResult *aRR = [((BaseModel *)model).dataDic objectForKey:@"RR"];
    if (aRR.code) {
        [[NSNotificationCenter defaultCenter] postNotificationName:@"signSuccess" object:nil];
        [self.navigationController popToViewController:[self.navigationController.viewControllers objectAtIndex:self.navigationController.viewControllers.count - 3] animated:YES];
    }
    [UIAlertView showTip:aRR.desc];
}

- (void)model:(DOModel *)model didFailLoadWithError:(NSError *)error
{
    [self removeHub];
    [UIAlertView showTip:NetWorkFaild];
}

@end
