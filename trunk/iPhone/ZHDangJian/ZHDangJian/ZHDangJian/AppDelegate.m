//
//  AppDelegate.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-11.
//  Copyright 广州市道一信息技术有限公司 2013年. All rights reserved.
//

#import "AppDelegate.h"
#import <QuartzCore/QuartzCore.h>
#import "BaseVC.h"


@implementation AppDelegate

@synthesize window=_window;

- (void)dealloc
{
    [_window release];
    [_deviceLogModel release];
    [super dealloc];
}

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    // Override point for customization after application launch.
    
    UIImage *tabBackground = [[UIImage imageNamed:@"public_foot_bg_1px"] resizableImageWithCapInsets:UIEdgeInsetsMake(0, 0, -10, 0)];
    [[UITabBar appearance] setBackgroundImage:tabBackground];
    [[UITabBar appearance] setSelectionIndicatorImage:[UIImage imageNamed:@"public_foot_hover_bg"]];
    
    //232,150.26
    [[UITabBar appearance] setSelectedImageTintColor:RGB(232, 150, 26)];
    
    [[UITabBarItem appearance] setTitleTextAttributes:
     [NSDictionary dictionaryWithObjectsAndKeys:
      RGB(167, 167, 167),UITextAttributeTextColor, nil] forState:UIControlStateNormal];
    
    [[UITabBarItem appearance] setTitleTextAttributes:
     [NSDictionary dictionaryWithObjectsAndKeys:
      RGB(232, 150, 26),UITextAttributeTextColor, nil] forState:UIControlStateSelected];
    
    //推送
    [[UIApplication sharedApplication] registerForRemoteNotificationTypes:
     (UIRemoteNotificationTypeBadge | UIRemoteNotificationTypeSound | UIRemoteNotificationTypeAlert)];

    
    return YES;
}

- (void)applicationWillResignActive:(UIApplication *)application
{
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
}

- (void)applicationDidEnterBackground:(UIApplication *)application
{
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
}

- (void)applicationWillEnterForeground:(UIApplication *)application
{
    // Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
}

- (void)applicationDidBecomeActive:(UIApplication *)application
{
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}

- (void)applicationWillTerminate:(UIApplication *)application
{
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
}


#pragma mark - push delegate

- (void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken{
    NSString *deviceTokenString=[NSString stringWithFormat:@"%@",deviceToken];
    deviceTokenString = [deviceTokenString stringByReplacingOccurrencesOfString:@"<" withString:@""];
    deviceTokenString = [deviceTokenString stringByReplacingOccurrencesOfString:@">" withString:@""];
    deviceTokenString = [deviceTokenString stringByReplacingOccurrencesOfString:@" " withString:@""];
    
    [UserInfo sharedInstance].deviceTokenString = deviceTokenString;
    //[UserInfo sharedInstance].pushImei = deviceTokenString;
    LOG(@"\n\n\n推送token:%@\n\n\n",deviceTokenString);
    
    //第一次启动调用后台设备信息记录接口
    KCSaveData *aSaveData2 = [[[KCSaveData alloc] initWithFileName:@"first_device_log"] autorelease];
    if (![aSaveData2 getBOOL:@"unfirst"]) {
        [self createDeviceLogModel];
        return;
    }
}

- (void)application:(UIApplication *)application didFailToRegisterForRemoteNotificationsWithError:(NSError *)error{
}

- (void)application:(UIApplication *)application didReceiveLocalNotification:(UILocalNotification *)notif {
    LOG(@"推送3:%@",notif);
    application.applicationIconBadgeNumber = notif.applicationIconBadgeNumber-1;
}

- (void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo{
    
    LOG(@"推送:%@",userInfo);
    
    //保存一个数据数字集
    KCSaveData *aSaveData = [[[KCSaveData alloc] initWithFileName:@"badges"] autorelease];
    
    
    //    //******************************************************************************
    //    //****************************测试
    //    [aSaveData saveInt:12 forKey:@"badge1"];
    //    [aSaveData saveInt:8 forKey:@"badge2"];
    //    [aSaveData saveInt:10 forKey:@"badge3"];
    //    [aSaveData saveInt:2 forKey:@"badge4"];
    //    [aSaveData saveInt:0 forKey:@"badge5"];
    //    [aSaveData saveInt:0 forKey:@"badge6"];
    //    [aSaveData saveInt:0 forKey:@"badge7"];
    //    //******************************************************************************
    
    
    
    /*
     推送:{
     aps =     {
     alert = test;
     badge = 1;
     sound = default;
     };
     type = 1;
     }
     
     */
    
    NSInteger getBadge = 0;
    NSDictionary *aApsDic = [userInfo objectForKey:@"aps"];
    NSString* str1 = [[aApsDic objectForKey:@"badge"] objToString];
    getBadge = [str1 intValue];
    
    NSInteger badgeType = 0;
    NSString* str2 = [[userInfo objectForKey:@"type"] objToString];
    badgeType = [str2 intValue];
    
    NSString *badgeKey = @"";
    switch (badgeType) {
        case 1:
            badgeKey = @"badge1";
            break;
        case 2:
            badgeKey = @"badge2";
            break;
        case 3:
            badgeKey = @"badge3";
            break;
        case 4:
            badgeKey = @"badge4";
            break;
        case 5:
            badgeKey = @"badge5";
            break;
        case 6:
            badgeKey = @"badge6";
            break;
        case 7:
            badgeKey = @"badge7";
            break;
            
        default:
            break;
    }
    NSInteger beforeBadge = [aSaveData getInt:badgeKey];
    beforeBadge = beforeBadge + getBadge;
    [aSaveData saveInt:beforeBadge forKey:badgeKey];
    
    
    //程序badge数量
    NSInteger allBadgeNumber = 0;
    allBadgeNumber = allBadgeNumber + [aSaveData getInt:@"badge1"];
    //allBadgeNumber = allBadgeNumber + [aSaveData getInt:@"badge2"];//去掉热点新闻
    allBadgeNumber = allBadgeNumber + [aSaveData getInt:@"badge3"];
    allBadgeNumber = allBadgeNumber + [aSaveData getInt:@"badge4"];
    allBadgeNumber = allBadgeNumber + [aSaveData getInt:@"badge5"];
    allBadgeNumber = allBadgeNumber + [aSaveData getInt:@"badge6"];
    allBadgeNumber = allBadgeNumber + [aSaveData getInt:@"badge7"];
    [UIApplication sharedApplication].applicationIconBadgeNumber = allBadgeNumber;
    //LOG(@"allBadgeNumber==%d",allBadgeNumber);
    
    
    NSString *infoPlist = [[NSBundle mainBundle] pathForResource:@"NewsMainListData" ofType:@"plist"];
    NSArray *aArray = [[[NSArray alloc] initWithContentsOfFile:infoPlist] autorelease];
    NSString *alertTilte = @"";//
    
    if (aArray.count>badgeType) {
        alertTilte = [[aArray objectAtIndex:badgeType] objectForKey:@"name"];
    }
    
    //弹出框
    UIAlertView* alert = [[[UIAlertView alloc] initWithTitle:alertTilte
                                                     message:[[userInfo objectForKey:@"aps"] objectForKey:@"alert"]
                                                    delegate:self
                                           cancelButtonTitle:@"关闭"
                                           otherButtonTitles:@"确定",nil] autorelease];
	alert.tag = 100;
	[alert show];
}

//消息推送确定查看后跳转
- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex{
    if (alertView.tag == 100) {
        if (buttonIndex == 1) {
            //发消息处理跳转
            LOG(@"消息推送-->跳转");
            
            [[NSNotificationCenter defaultCenter] postNotificationName:@"RemoteNotification" object:nil];
        }
    }
}


#pragma mark - model

-(void)createDeviceLogModel{
    
    if ([[UserInfo sharedInstance].deviceTokenString isEmpty]) {
        [self deviceLogFail];
        return;
    }
    
    //deviceLogModel
    if (_deviceLogModel) {
        _deviceLogModel.delegate=nil;
        DO_RELEASE_SAFELY(_deviceLogModel);
    }
    _deviceLogModel = [[BaseModel alloc] init];
    _deviceLogModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:@"2"] forKey:@"type"];
    [aDic setObject:[DES3Util enDES3:[UIDevice currentDevice].uniqueDeviceIdentifier] forKey:@"equipmentNum"];
    [aDic setObject:[DES3Util enDES3:[UIDevice currentDevice].systemVersion] forKey:@"versionNum"];
    [aDic setObject:[DES3Util enDES3:[UIDevice currentDevice].appVersion] forKey:@"appVersionNum"];
    [aDic setObject:[DES3Util enDES3:[UIDevice currentDevice].model] forKey:@"deviceModel"];
    [aDic setObject:[[NSString stringWithFormat:@"2%@%@%@%@", [UIDevice currentDevice].uniqueDeviceIdentifier, [UIDevice currentDevice].systemVersion, [UIDevice currentDevice].appVersion, [UIDevice currentDevice].model] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KRecordDeviceUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_deviceLogModel startRequest:requestUrl];
}

- (void)modelDidStartLoad:(DOModel *)model
{
    
}

- (void)modelDidFinishLoad:(DOModel *)model
{
    RequstResult *aRR = [((BaseModel*)model).dataDic objectForKey:@"RR"];
    if (model == _deviceLogModel) {
        if (aRR.code) {
            [self deviceLogSuc];
            return;
        }
        [self deviceLogFail];
        return;
    }
}

- (void)model:(DOModel *)model didFailLoadWithError:(NSError *)error
{
    [self deviceLogFail];
}


-(void)deviceLogSuc{
    LOG(@"入库成功");
    //[self deviceLogFinish];
    
    //第一次启动调用后台设备信息记录接口
    KCSaveData *aSaveData2 = [[[KCSaveData alloc] initWithFileName:@"first_device_log"] autorelease];
    [aSaveData2 saveBOOL:YES forKey:@"unfirst"];
    
    
}
-(void)deviceLogFail{
    LOG(@"入库失败");
    //[self deviceLogFinish];
    
    //第一次启动调用后台设备信息记录接口
    KCSaveData *aSaveData2 = [[[KCSaveData alloc] initWithFileName:@"first_device_log"] autorelease];
    [aSaveData2 saveBOOL:NO forKey:@"unfirst"];
    
}




#pragma mark - animate

-(void)animatedIn{
    
    CATransition *transition = [CATransition animation];
    transition.duration = 1.0f;
    transition.timingFunction = [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionEaseInEaseOut];
    transition.type = @"oglFlip";
    //@"cube" @"moveIn" @"reveal" @"fade"(default) @"pageCurl" @"pageUnCurl" @"suckEffect" @"rippleEffect" @"oglFlip"
    
    transition.subtype = kCATransitionFromRight;
    transition.delegate = self;
    [self.window.layer addAnimation:transition forKey:nil];
    
    
}

-(void)animatedOut{
    
    CATransition *transition = [CATransition animation];
    transition.duration = 1.0f;
    transition.timingFunction = [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionEaseInEaseOut];
    transition.type = @"oglFlip";
    //@"cube" @"moveIn" @"reveal" @"fade"(default) @"pageCurl" @"pageUnCurl" @"suckEffect" @"rippleEffect" @"oglFlip"
    
    transition.subtype = kCATransitionFromLeft;
    transition.delegate = self;
    [self.window.layer addAnimation:transition forKey:nil];
}


@end
