//
//  BootVC.h
//  ZHDangJian
//  启动VC
//  Created by kevin_yby on 13-11-11.
//  Copyright 广州市道一信息技术有限公司 2013年. All rights reserved.
//



#import <UIKit/UIKit.h>
#import "BaseVC.h"

@interface BootVC : BaseVC<UIScrollViewDelegate, DOModelDelegate>

@property (retain, nonatomic) IBOutlet UIImageView *bootImageView;
@property (retain, nonatomic) IBOutlet UIScrollView *imageScrollView;
@property (retain, nonatomic) IBOutlet UIPageControl *imagePageControl;

@property (nonatomic, retain) BaseModel *loginModel;

@property(nonatomic,assign)BOOL isBootVC;

@end