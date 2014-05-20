
#import "MainLogOutSegue.h"
#import "AppDelegate.h"


@implementation MainLogOutSegue

- (void)perform{
    
    [[UIApplication sharedApplication] setStatusBarHidden:NO];
    
    UIViewController *next = self.destinationViewController;
    
    //启动-->登陆
    AppDelegate *aDelegate =(AppDelegate*)[UIApplication sharedApplication].delegate;
    
    [aDelegate animatedOut];
    
    [aDelegate.window setRootViewController:next];
    
}
@end
