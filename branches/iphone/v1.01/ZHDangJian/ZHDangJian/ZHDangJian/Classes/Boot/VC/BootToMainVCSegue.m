
#import "BootToMainVCSegue.h"
#import "AppDelegate.h"

@implementation BootToMainVCSegue

- (void)perform{
    
    UIViewController *next = self.destinationViewController;
    
    //登陆-->主页面
    AppDelegate *aDelegate =(AppDelegate*)[UIApplication sharedApplication].delegate;
    [aDelegate animatedIn];
    [aDelegate.window setRootViewController:next];
    
}

@end
