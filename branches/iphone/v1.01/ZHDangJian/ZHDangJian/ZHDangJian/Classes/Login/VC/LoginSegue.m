#import "LoginSegue.h"
#import "AppDelegate.h"

@implementation LoginSegue

- (void)perform{
    UIViewController *next = self.destinationViewController;
    
    //登陆-->主页面
    AppDelegate *aDelegate =(AppDelegate*)[UIApplication sharedApplication].delegate;
    [aDelegate animatedIn];
    [aDelegate.window setRootViewController:next];
}

@end
