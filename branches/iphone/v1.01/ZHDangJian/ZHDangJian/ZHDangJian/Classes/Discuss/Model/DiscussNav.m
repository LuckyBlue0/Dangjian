//
//  DiscussNav.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-11.
//
//

#import "DiscussNav.h"

@interface DiscussNav ()

@end

@implementation DiscussNav

-(id)initWithCoder:(NSCoder *)aDecoder{
    if (self=[super initWithCoder:aDecoder]) {
        UIStoryboard *stryBoard=[UIStoryboard storyboardWithName:@"Discuss" bundle:nil];
        NSArray *vcs = [NSArray arrayWithObjects:[stryBoard instantiateInitialViewController], nil];
        [self setViewControllers:vcs];
        
    }
    return self;
}


@end
