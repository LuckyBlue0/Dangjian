//
//  BootVC.m
//  ZHDangJian
//  
//  Created by kevin_yby on 13-11-11.
//  Copyright 广州市道一信息技术有限公司 2013年. All rights reserved.
//

#import "BootVC.h"

#define directorNum 4
static NSString* directorImages[directorNum] = {@"index1@2x.png",@"index2@2x.png",@"index3@2x.png",@"index4@2x.png"};

@implementation BootVC

-(id)initWithCoder:(NSCoder *)aDecoder{
    if (self=[super initWithCoder:aDecoder]) {
        _isBootVC = YES;
    }
    return self;
}

- (void)dealloc{
    
    [_bootImageView release];
    [_imageScrollView release];
    [_imagePageControl release];
    _loginModel.delegate = nil;
    DO_RELEASE_SAFELY(_loginModel);
    
    [super dealloc];
}


- (void)viewDidLoad{
    [super viewDidLoad];
    
    //背景图，根据i5或者i5以下选择背景图
    UIImageView* bgImageView = (UIImageView*)[self.view viewWithTag:1000];
    if (bgImageView) {
        
        //IPhone5
        if ([UIScreen instancesRespondToSelector:@selector(currentMode)] ? CGSizeEqualToSize(CGSizeMake(640, 1136), [[UIScreen mainScreen] currentMode].size) : NO) {
            
            bgImageView.image = [UIImage imageNamed:@"Default-568h@2x.png"];
        }
        //Iphone5以下
        else{
            bgImageView.image = [UIImage imageNamed:@"Default@2x.png"];
            
        }
    }
    
    if (_isBootVC == NO) {
        [[UIApplication sharedApplication] setStatusBarHidden:YES];
        self.wantsFullScreenLayout = YES;
    }
    
    
    if (_isBootVC == NO) {
        _bootImageView.hidden = YES;
        
        [self showDirector];
        
        return;
    }
    
    //是否第一次启动
    KCSaveData *aSaveData = [[[KCSaveData alloc] initWithFileName:@"boot_director"] autorelease];
    if ([aSaveData getBOOL:@"unfirst"]) {
        [self hideDirector];
        
        _bootImageView.hidden = NO;
        
        //登陆
        [self bootFinished];
        
    }else{
        
        _bootImageView.hidden = YES;
        
        [self showDirector];
        
        [aSaveData saveBOOL:YES forKey:@"unfirst"];
    }

    
//    [NSTimer scheduledTimerWithTimeInterval:1 target:self selector:@selector(bootFinishAction) userInfo:nil repeats:NO];
    
}

-(void)hideDirector{
    _imageScrollView.hidden = YES;
    _imagePageControl.hidden = YES;
    
}

-(void)showDirector{
    
    _imageScrollView.hidden = NO;
    _imagePageControl.hidden = YES;
    
    //加入图片视图
    for (int i=0; i<directorNum; i++) {
        
        UIImageView *aImageView = [[[UIImageView alloc] initWithFrame:XYWH(i*_imageScrollView.width, 0, _imageScrollView.width, _imageScrollView.height)] autorelease];
        aImageView.image = [UIImage imageNamed:directorImages[i]];
        [_imageScrollView addSubview:aImageView];
        
    }
    [_imageScrollView setContentSize:CGSizeMake(directorNum*_imageScrollView.width, _imageScrollView.height)];
    
    //page Controller
    [self.view bringSubviewToFront:_imagePageControl];
    _imagePageControl.numberOfPages = directorNum;
}


-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    [[UIApplication sharedApplication] setStatusBarHidden:YES];
}


-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    
    [[UIApplication sharedApplication] setStatusBarHidden:NO];
}

-(void)bootFinishAction{
    [self performSegueWithIdentifier:@"BootSegueTimer" sender:self];
}

#pragma mark -action

-(void)loginFail{
    [self performSegueWithIdentifier:@"BootSegueTimer" sender:self];
}

-(void)loginSuccess{
    
//    //--> 到生日提醒
//    if ([UserInfo sharedInstance].birthdays.count>0) {
//        [self performSegueWithIdentifier:@"BootToMainBirthDaySegueID" sender:self];
//    }
//    
//    //---> 主页面
//    else{
//        [self performSegueWithIdentifier:@"BootToMainVCSegueID" sender:self];
//    }
    
    [self performSegueWithIdentifier:@"BootToMainVCSegueID" sender:self];
}

-(void)bootFinished{
    
    
    if (_isBootVC == NO) {
        
        [self dismissModalViewControllerAnimated:YES];
        return;
    }
    
//    [NSTimer scheduledTimerWithTimeInterval:1 target:self selector:@selector(bootFinishAction) userInfo:nil repeats:NO];

    
    //判断是否自动登陆(成功跳到主页面,失败跳到登陆页面)
    KCSaveData *aSaveData = [[[KCSaveData alloc] initWithFileName:@"login_users"] autorelease];
    
    //可以自动登陆
    if (![[aSaveData getString:@"name"] isEmpty]
        && ![[aSaveData getString:@"password"] isEmpty]) {
        [self creatModel];
        
    }
    
    //不可以自动登陆，跳到登陆页面
    else{
        [self performSegueWithIdentifier:@"BootSegueTimer" sender:self];
    }
}



- (void)creatModel
{
    if (_loginModel) {
        _loginModel.delegate = nil;
        DO_RELEASE_SAFELY(_loginModel);
    }
    _loginModel = [[BaseModel alloc] init];
    _loginModel.delegate = self;
    
    KCSaveData *aSaveData = [[[KCSaveData alloc] initWithFileName:@"login_users"] autorelease];
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:@"2"] forKey:@"platformType"];
    [aDic setObject:[DES3Util enDES3:[aSaveData getString:@"name"]] forKey:@"username"];
    [aDic setObject:[DES3Util enDES3:[aSaveData getString:@"password"]] forKey:@"userPwd"];
    [aDic setObject:[DES3Util enDES3:[UIDevice currentDevice].uniqueDeviceIdentifier] forKey:@"deviceId"];
    [aDic setObject:[[NSString stringWithFormat:@"2%@%@%@", [aSaveData getString:@"name"], [aSaveData getString:@"password"], [UIDevice currentDevice].uniqueDeviceIdentifier] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KLoginUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_loginModel startRequest:requestUrl];
}

- (void)modelDidStartLoad:(DOModel *)model
{
    
}

- (void)modelDidFinishLoad:(DOModel *)model
{
    RequstResult *aRR = [((BaseModel*)model).dataDic objectForKey:@"RR"];
    if (model == self.loginModel) {
        if (aRR.code) {
            
            if (aRR.dataDic == nil) {
                [UserInfo reset];
                return;
            }
            [UserInfo sharedInstance].userId = [[aRR.dataDic objectForKey:@"loginUserInfo"] objectForKey:@"userId"];
            [UserInfo sharedInstance].userName = [[aRR.dataDic objectForKey:@"loginUserInfo"] objectForKey:@"username"];
            [UserInfo sharedInstance].name = [[aRR.dataDic objectForKey:@"loginUserInfo"] objectForKey:@"name"];
            [UserInfo sharedInstance].organizationId = [[aRR.dataDic objectForKey:@"loginUserInfo"] objectForKey:@"organizationId"];
            [UserInfo sharedInstance].userType = [[aRR.dataDic objectForKey:@"loginUserInfo"] objectForKey:@"userType"];
            
            [self loginSuccess];
            return;
        }
        [self loginFail];
        return;
    }
}

- (void)model:(DOModel *)model didFailLoadWithError:(NSError *)error
{
    if (model == _loginModel) {
        
    }
}


#pragma mark - UIScrollView Delegate

- (void)scrollViewDidEndDragging:(UIScrollView *)aUIScrollView willDecelerate:(BOOL)decelerate{
    
    if (aUIScrollView.contentOffset.x>aUIScrollView.contentSize.width-300) {
        aUIScrollView.scrollEnabled = NO;
        [self bootFinished];
    }
}

- (void)scrollViewDidEndDecelerating:(UIScrollView *)aUIScrollView{
    
    _imagePageControl.currentPage = aUIScrollView.contentOffset.x/aUIScrollView.width;
    [_imagePageControl updateCurrentPageDisplay];
}


@end



