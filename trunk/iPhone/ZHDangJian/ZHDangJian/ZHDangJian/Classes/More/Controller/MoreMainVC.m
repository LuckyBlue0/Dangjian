//
//  MoreMainVC.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-11.
//
//

#import "MoreMainVC.h"
#import "BootVC.h"

@interface MoreMainVC ()

@end

@implementation MoreMainVC

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
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_versionLabel release];
    [super dealloc];
}
- (void)viewDidUnload {
    [self setVersionLabel:nil];
    [super viewDidUnload];
}

- (void)viewWillAppear:(BOOL)animated
{
    self.navigationController.navigationBarHidden = NO;
    [[UIApplication sharedApplication] setStatusBarHidden:NO];
    [super viewWillAppear:animated];
}


- (IBAction)welcomePageButton:(UIButton *)sender {
    UIStoryboard *stryBoard=[UIStoryboard storyboardWithName:@"Main" bundle:nil];
    
    BootVC *aBootVC = (BootVC*)[stryBoard instantiateInitialViewController];
    aBootVC.isBootVC = NO;
    aBootVC.wantsFullScreenLayout = YES;
    
    aBootVC.modalTransitionStyle = UIModalTransitionStyleCrossDissolve;
    aBootVC.modalPresentationStyle = UIModalPresentationFormSheet;

    [self.navigationController presentModalViewController:aBootVC animated:YES];
}

- (IBAction)aboutUsButton:(UIButton *)sender {
    [self performSegueWithIdentifier:@"AboutUsVCID" sender:nil];
}

- (IBAction)userFeedbackButton:(UIButton *)sender {
    [self performSegueWithIdentifier:@"UserFeedbackVCID" sender:nil];
}

- (IBAction)cacheCacheButton:(UIButton *)sender {
    NSURLCache *cache = [NSURLCache sharedURLCache];
    if (nil == cache || ![cache currentMemoryUsage]) {
        [UIAlertView showTip:@"缓存为空!"];
        return;
    }
    [cache removeAllCachedResponses];
    [cache setDiskCapacity:0];
    [cache setMemoryCapacity:0];
    [UIAlertView showTip:@"缓存清除成功!"];
}

@end
