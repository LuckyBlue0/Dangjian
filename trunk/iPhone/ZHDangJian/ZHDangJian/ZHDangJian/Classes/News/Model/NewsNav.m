
#import "NewsNav.h"

@implementation NewsNav


-(id)initWithCoder:(NSCoder *)aDecoder{
    if (self=[super initWithCoder:aDecoder]) {
        UIStoryboard *stryBoard=[UIStoryboard storyboardWithName:@"News" bundle:nil];
        NSArray *vcs = [NSArray arrayWithObjects:[stryBoard instantiateInitialViewController], nil];
        [self setViewControllers:vcs];
    }
    return self;
}



@end



