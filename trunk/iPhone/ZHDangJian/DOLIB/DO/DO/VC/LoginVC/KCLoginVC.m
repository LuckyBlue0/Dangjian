
#import "KCLoginVC.h"
#import "ASIHTTPRequest.h"
#import "KCSaveData.h"


@interface KCLoginVC ()
@end


@implementation KCLoginVC

@synthesize userName;
@synthesize userPwd;
@synthesize userNameTextField;
@synthesize userPwdTextField;


-(void)dealloc{
    [userPwd release];
    [userName release];
    [userNameTextField release];
    [userPwdTextField release];
    
    [super dealloc];
}


-(void)loadView{
    [super loadView];
    
//    self.userNameTextField = [[UITextField alloc] initWithFrame:CGRectMake(10, 50, 300, 30)];
//    userNameTextField.backgroundColor = [UIColor grayColor];
//    [userNameTextField addTarget:self action:@selector(editingChanged) forControlEvents:UIControlEventEditingChanged];
//    [self.view addSubview:userNameTextField];
//    
//    self.userPwdTextField = [[UITextField alloc] initWithFrame:CGRectMake(10, 100, 300, 30)];
//    userPwdTextField.backgroundColor = [UIColor grayColor];
//    [userPwdTextField setSecureTextEntry:YES];
//    [self.view addSubview:userPwdTextField];
    
}



#pragma mark -
#pragma mark public

-(NSString*)loginURLStr{
    return [NSString stringWithFormat:@"%@/login!login.action?mobile=%@&password=%@",@"http://219.136.91.63:7071/itswap/client",self.userName,self.userPwd];
}

-(NSString*)networkError{
    return @"网络错误";
}

-(BOOL)isSaveInfo{
    return YES;
}


//判断合法
-(BOOL)isUserName{
    return YES;
}

-(BOOL)isUserPwd{
    return YES;
}


//等待视图
-(void)showWaittingView{
    
}

-(void)dismissWaittingView{
    
}

-(void)showUserError{
    
}

-(void)showPwdError{
    
}

//解析
-(void)loginParser:(NSData*)aData{
    
    [self dismissWaittingView];
    [self loginSuccess];
}

-(void)loginFailed:(NSString*)aError{
    
    [self dismissWaittingView];
}


-(NSString*)userName{
    return userNameTextField.text;
}

-(NSString*)userPwd{
    return userPwdTextField.text;
}


//登陆成功
-(void)loginSuccess{
    
    //判断是否保存用户和密码
    if ([self isSaveInfo]) {
        //**** 判断是否第一次启动页面(执行引导) ****
        KCSaveData *aKCSaveData = [[[KCSaveData alloc] initWithFileName:@"USER_INFO"] autorelease];
        [aKCSaveData saveString:[self userPwd] forKey:[self userName]];
        
    }
    
}

//请求
-(void)loginRequest{
    
    [self showWaittingView];
    
    NSString *aRequestStr = [self loginURLStr];
	ASIHTTPRequest *aASIHTTPRequest_DO=[[ASIHTTPRequest alloc] initWithURL:[NSURL URLWithString:[aRequestStr stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]]];
	[aASIHTTPRequest_DO setDelegate:self];
    [aASIHTTPRequest_DO setDidFinishSelector:@selector(requestDataFinished:)];
    [aASIHTTPRequest_DO setDidFailSelector:@selector(requestDataFailed:)];
    [aASIHTTPRequest_DO startSynchronous];//登陆用同步
}




#pragma mark -
#pragma mark action

-(void)loginAction{
    
    [self loginSuccess];
    
    return;
    
    self.userName = [self userName];
    self.userPwd = [self userPwd];
    
    //判断是否合法
    if (![self isUserName]) {
        [self showUserError];
        return;
    }
    if (![self isUserPwd]) {
        [self showPwdError];
        return;
    }
    
    //发送登陆请求
    [self loginRequest];
    
}




#pragma mark -
#pragma mark ASIHTTPRequest_DO

- (void)requestDataFinished:(ASIHTTPRequest *)aASIHTTPRequest_DO{
    
    int statusCode = [aASIHTTPRequest_DO responseStatusCode];
    if(statusCode == 200){
        NSData* aData=[aASIHTTPRequest_DO responseData];
        [self loginParser:aData];
        
    }else{
        
        [self loginFailed:[self networkError]];
    }
}

- (void)requestDataFailed:(ASIHTTPRequest *)aASIHTTPRequest_DO{
    
    [self loginFailed:[self networkError]];
}


- (void)editingChanged{
    //**** 判断是否第一次启动页面(执行引导) ****
    KCSaveData *aKCSaveData = [[[KCSaveData alloc] initWithFileName:@"USER_INFO"] autorelease];
    
    if ([aKCSaveData getString:[self userName]] && ![[aKCSaveData getString:[self userName]] isEqualToString:@""]) {
        
        [self.userPwdTextField setText:[aKCSaveData getString:[self userName]]];
    }else{
        [self.userPwdTextField setText:@""];
    }
}


@end




