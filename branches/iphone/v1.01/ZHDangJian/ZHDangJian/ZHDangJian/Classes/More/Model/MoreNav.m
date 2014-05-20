//
//  MoreNav.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-11.
//
//

#import "MoreNav.h"

@interface MoreNav ()

@end

@implementation MoreNav

-(id)initWithCoder:(NSCoder *)aDecoder{
    if (self=[super initWithCoder:aDecoder]) {
        UIStoryboard *stryBoard=[UIStoryboard storyboardWithName:@"More" bundle:nil];
        NSArray *vcs = [NSArray arrayWithObjects:[stryBoard instantiateInitialViewController], nil];
        [self setViewControllers:vcs];
        
    }
    return self;
}

@end
