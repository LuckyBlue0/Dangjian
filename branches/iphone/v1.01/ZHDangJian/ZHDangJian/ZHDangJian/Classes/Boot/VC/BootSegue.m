#import "BootSegue.h"
#import "AppDelegate.h"

@implementation BootSegue

- (void)perform{
    [[UIApplication sharedApplication] setStatusBarHidden:NO];
    
    UIViewController *next = self.destinationViewController;
    
    //启动-->登陆
    AppDelegate *aDelegate =(AppDelegate*)[UIApplication sharedApplication].delegate;
    [aDelegate animatedIn];
    [aDelegate.window setRootViewController:next];
}

@end