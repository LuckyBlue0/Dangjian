
#import "MyOrgNav.h"
#import "UserInfo.h"
@implementation MyOrgNav


-(id)initWithCoder:(NSCoder *)aDecoder{
    if (self=[super initWithCoder:aDecoder]) {
        UIStoryboard *stryBoard=[UIStoryboard storyboardWithName:@"MyOrg" bundle:nil];
        NSArray *vcs = [NSArray arrayWithObjects:[stryBoard instantiateInitialViewController], nil];
        [self setViewControllers:vcs];

    }
    return self;
}



@end
