//
//  MainVC.m
//  ZHDangJian
//  
//  Created by kevin_yby on 13-11-11.
//  Copyright 广州市道一信息技术有限公司 2013年. All rights reserved.
//

#import "MainVC.h"

@implementation MainVC

-(id)initWithCoder:(NSCoder *)aDecoder{
    if (self=[super initWithCoder:aDecoder]) {
        
        [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(notificationAction:) name:@"RemoteNotification" object:nil];
        
        [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(notificationAction:) name:@"ExitAppNotification" object:nil];
    }
    return self;
}

- (void)dealloc{
    
    [[NSNotificationCenter defaultCenter] removeObserver:self];
    [super dealloc];
}


#pragma mark - notification

-(void)notificationAction:(NSNotification*)aNotification{
    
    if ([aNotification.name isEqualToString:@"RemoteNotification"]) {
        
        self.selectedIndex = 0;
    }
    
    //注销登陆
    else if ([aNotification.name isEqualToString:@"ExitAppNotification"]) {
        KCSaveData *aSaveData = [[[KCSaveData alloc] initWithFileName:@"login_users"] autorelease];
        [aSaveData saveString:@"" forKey:@"name"];
        [aSaveData saveString:@"" forKey:@"password"];
        [self performSegueWithIdentifier:@"MainLogOutSegueID" sender:self];
    }
    
}



@end



