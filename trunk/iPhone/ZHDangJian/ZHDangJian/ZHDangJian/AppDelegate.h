//
//  AppDelegate.h
//  ZHDangJian
//  程序总代理
//  Created by kevin_yby on 13-11-11.
//  Copyright 广州市道一信息技术有限公司 2013年. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BaseModel.h"

@interface AppDelegate : UIResponder <UIApplicationDelegate, DOModelDelegate> {
    UIWindow *_window;
}

@property (retain, nonatomic) UIWindow *window;
@property(nonatomic,retain) BaseModel *deviceLogModel;

-(void)animatedIn;
-(void)animatedOut;

@end
