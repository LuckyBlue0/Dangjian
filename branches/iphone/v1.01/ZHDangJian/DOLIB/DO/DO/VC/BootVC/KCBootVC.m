
#import "KCBootVC.h"
#import "DOScrollView.h"
#import "KCSaveData.h"
#import "ASIHTTPRequest.h"


@interface KCBootVC ()<DOScrollViewDataSource,DOScrollViewDelegate>

@end

@implementation KCBootVC

#pragma mark -
#pragma mark public


-(NSArray*)images{
    return nil;
}

-(void)bootFinished{
}


//检测版本请求
-(ASIHTTPRequest*)versionCheckRequest{
    return nil;
}

//检测版本解析
-(void)versionCheck:(NSData*)aData{
    
}





#pragma mark -
#pragma mark UIViewController

-(void)loadView{
    [super loadView];
    
    if (![self versionCheckRequest]) {
        //同步检测版本
        ASIHTTPRequest *aRequest = [self versionCheckRequest];
        [aRequest startSynchronous];
        
        NSError *error = [aRequest error];
        if (!error) {
            //解析处理
            [self versionCheck:[aRequest responseData]];
            
            return;
        }
    }
    
    [self checkVersionFinish];
    
}





#pragma mark - action

-(void)gotoDirector{
    
    DOScrollView *aLLScrollView = [[[DOScrollView alloc] initWithFrame:self.view.bounds] autorelease];
    aLLScrollView.delegate = self;
    aLLScrollView.dataSource = self;
    aLLScrollView.rotateEnabled = NO;
    aLLScrollView.pageSpacing=0;
    aLLScrollView.zoomEnabled = NO;
    aLLScrollView.backgroundColor = [UIColor clearColor];
    aLLScrollView.autoresizingMask = UIViewAutoresizingFlexibleWidth|UIViewAutoresizingFlexibleHeight;
    [self.view addSubview:aLLScrollView];
}

-(void)checkVersionFinish{
    
    //**** 判断是否第一次启动页面(执行引导) ****
    KCSaveData *aKCSaveData = [[[KCSaveData alloc] initWithFileName:@"BOOT_APP_INFO"] autorelease];
    if (![aKCSaveData getBOOL:@"unappFirstRun"]) {
        //**** 执行引导 ****
        [aKCSaveData saveBOOL:YES forKey:@"unappFirstRun"];
        [self gotoDirector];
    }
    else{
        [self bootFinished];
    }
}


#pragma mark -DOScrollView

- (void)scrollView:(DOScrollView*)scrollView didMoveToPageAtIndex:(NSInteger)pageIndex {
}

- (NSInteger)numberOfPagesInScrollView:(DOScrollView*)scrollView {
    if ([self respondsToSelector:@selector(images)]) {
        return [[self performSelector:@selector(images) withObject:nil] count];
    }
    return 0;
}

- (UIView*)scrollView:(DOScrollView*)scrollView pageAtIndex:(NSInteger)pageIndex {
    
    if ([self respondsToSelector:@selector(images)]) {
        NSArray *aArray = [self performSelector:@selector(images) withObject:nil];
        
        if ([aArray count] < pageIndex) {
            return nil;
        }
        
        UIImageView *photoView = [[[UIImageView alloc] initWithFrame:scrollView.bounds] autorelease];
        photoView.backgroundColor = [UIColor clearColor];
        photoView.image = [UIImage imageNamed:[aArray objectAtIndex:pageIndex]];
        
        if (pageIndex==[aArray count]-1) {
            photoView.userInteractionEnabled = YES;
            UIButton *aBtn = [[[UIButton alloc] initWithFrame:CGRectMake(0, scrollView.frame.size.height-200, scrollView.frame.size.width, 200)] autorelease];
            aBtn.backgroundColor = [UIColor clearColor];
            [aBtn addTarget:self action:@selector(bootFinished) forControlEvents:UIControlEventTouchUpInside];
            [photoView addSubview:aBtn];
        }
        
        return photoView;
    }
    
    return nil;
}

- (CGSize)scrollView:(DOScrollView*)scrollView sizeOfPageAtIndex:(NSInteger)pageIndex {
    return scrollView.frame.size;
}



@end

