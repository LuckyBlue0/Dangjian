
#import "KCPhotosVC.h"

@implementation KCPhotosVC

@synthesize scrollView=_scrollView;
@synthesize defaultImage=_defaultImage;


- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil{
    if (self=[super initWithNibName:nibNameOrNil bundle:nibBundleOrNil]) {
        
        _dataSource = [[NSDictionary alloc] init];
        _curPage=0;
    }
    return self;
}

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil dataSouce:(NSDictionary*)aDataSource{
    if (self=[super initWithNibName:nibNameOrNil bundle:nibBundleOrNil]) {
        
        _dataSource = [[NSDictionary alloc] initWithDictionary:aDataSource];
        _curPage=0;
    }
    return self;
}

- (id)initWithDataSource:(NSDictionary*)aDataSource{
    if (self=[super initWithNibName:nil bundle:nil]) {
        _dataSource = [[NSDictionary alloc] initWithDictionary:aDataSource];
        _curPage=0;
    }
    return self;
}

-(void)dealloc{
    [_navBar release];
    _scrollView.delegate=nil;
    _scrollView.dataSource=nil;
    [_scrollView release];
    [_dataSource release];
    
    [super dealloc];
}


-(NSDictionary*)creatDataSource{
    
    return nil;
}


-(void)loadView{
    _curPage=0;
    _dataSource = [[self creatDataSource] retain];
    _defaultImage = [UIImage imageNamed:@"11.png"];
    
    
    CGRect screenFrame = [UIScreen mainScreen].bounds;
    self.view = [[[UIView alloc] initWithFrame:screenFrame] autorelease];
    self.view.backgroundColor = [UIColor whiteColor];
    
    _scrollView = [[DOScrollView alloc] initWithFrame:screenFrame];
    _scrollView.delegate = self;
    _scrollView.dataSource = self;
    _scrollView.rotateEnabled = NO;
    _scrollView.pageSpacing=0;
    _scrollView.backgroundColor = [UIColor blackColor];
    _scrollView.autoresizingMask = UIViewAutoresizingFlexibleWidth|UIViewAutoresizingFlexibleHeight;
    [self.view addSubview:_scrollView];
}



#pragma mark -
#pragma mark UIViewController
-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    _curStyle = [UIApplication sharedApplication].statusBarStyle;
    _curStatusBarHidden= [UIApplication sharedApplication].statusBarHidden;
    [[UIApplication sharedApplication] setStatusBarHidden:YES withAnimation:UIStatusBarAnimationFade];
}
-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    [[UIApplication sharedApplication] setStatusBarStyle:_curStyle];
    [[UIApplication sharedApplication] setStatusBarHidden:_curStatusBarHidden withAnimation:UIStatusBarAnimationFade];
}




#pragma mark -
#pragma mark private

-(void)setNavTitle{
    NSString *aTitleStr=@"第 0 张 (共 0 张)";
    if (nil!=[_dataSource objectForKey:@"type"] 
        && ([[_dataSource objectForKey:@"type"] isEqualToString:@"0"]|| [[_dataSource objectForKey:@"type"] isEqualToString:@"1"] ||[[_dataSource objectForKey:@"type"] isEqualToString:@"2"])
        && nil!=[_dataSource objectForKey:@"data"]
        && [[_dataSource objectForKey:@"data"] isKindOfClass:[NSArray class]]) {
        aTitleStr = [NSString stringWithFormat:@"第 %d 张 (共 %d 张)",_curPage+1,[[_dataSource objectForKey:@"data"] count]];
    }
    
    UILabel *label = [[[UILabel alloc] initWithFrame:CGRectZero] autorelease];
	label.text = aTitleStr;
    label.font = [UIFont boldSystemFontOfSize:20.0];
    label.backgroundColor = [UIColor clearColor];
	label.shadowColor =     [UIColor colorWithWhite:0.0 alpha:0.5];
	label.textColor =       [UIColor colorWithRed:255/255.0f green:255/255.0f blue:255/255.0f alpha:1.0];
    label.textAlignment = NSTextAlignmentCenter;
	[label sizeToFit];
    NSArray *aItems = [_navBar items];
    UINavigationItem *navigationItem = (UINavigationItem*)[aItems objectAtIndex:0];
    navigationItem.titleView = label;
}

- (BOOL)isShowingNav {
    if (!_navBar) {
        _navBar = [[UINavigationBar alloc] initWithFrame:CGRectMake(0, 20, 320, 44)];
        UINavigationItem *navigationItem = [[UINavigationItem alloc] initWithTitle:nil];
        UIBarButtonItem *leftButton = [[UIBarButtonItem alloc] initWithTitle:@"返回"     
                                                                       style:UIBarButtonItemStylePlain     
                                                                      target:self     
                                                                      action:@selector(backAction)];
        [_navBar pushNavigationItem:navigationItem animated:NO];
        [navigationItem setLeftBarButtonItem:leftButton];
        [navigationItem release];    
        [leftButton release];
        
        [self.view addSubview:_navBar];
    }
    [_navBar setTintColor:[UIColor blackColor]];
    [self setNavTitle];
    if (_navBar.alpha==0) {
        _navBar.alpha=1;
        [[UIApplication sharedApplication] setStatusBarStyle:UIStatusBarStyleBlackTranslucent];
        [[UIApplication sharedApplication] setStatusBarHidden:NO withAnimation:UIStatusBarAnimationFade];
        return YES;
    }else{
        _navBar.alpha=0;
        [[UIApplication sharedApplication] setStatusBarHidden:YES withAnimation:UIStatusBarAnimationFade];
        return NO;
    }
}




#pragma mark -
#pragma mark public




#pragma mark -
#pragma mark LLScrollView Delegate

- (void)scrollView:(DOScrollView*)scrollView didMoveToPageAtIndex:(NSInteger)pageIndex {
    _curPage=pageIndex;
    [self setNavTitle];
}

- (void)scrollView:(DOScrollView*)scrollView tapped:(UITouch*)touch {
    if ([self isShowingNav]) {
        //自定义其他按钮
    }
    else {
    }
}



#pragma mark -
#pragma mark DataSource

- (NSInteger)numberOfPagesInScrollView:(DOScrollView*)scrollView {
    if (nil!=[_dataSource objectForKey:@"type"] 
        && ([[_dataSource objectForKey:@"type"] isEqualToString:@"0"]|| [[_dataSource objectForKey:@"type"] isEqualToString:@"1"]||[[_dataSource objectForKey:@"type"] isEqualToString:@"2"])
        && nil!=[_dataSource objectForKey:@"data"]
        && [[_dataSource objectForKey:@"data"] isKindOfClass:[NSArray class]]) {
        return [[_dataSource objectForKey:@"data"] count];
    }
    return 1;
}

- (UIView*)scrollView:(DOScrollView*)scrollView pageAtIndex:(NSInteger)pageIndex {
    
    if (nil!=[_dataSource objectForKey:@"type"] 
        && ([[_dataSource objectForKey:@"type"] isEqualToString:@"0"]|| [[_dataSource objectForKey:@"type"] isEqualToString:@"1"]||[[_dataSource objectForKey:@"type"] isEqualToString:@"2"])
        && nil!=[_dataSource objectForKey:@"data"]
        && [[_dataSource objectForKey:@"data"] isKindOfClass:[NSArray class]]
        && [[_dataSource objectForKey:@"data"] count]>=pageIndex) {
        //**** type:0 url ****
        if ([[_dataSource objectForKey:@"type"] isEqualToString:@"0"]
            && [[(NSArray*)[_dataSource objectForKey:@"data"] objectAtIndex:pageIndex] isKindOfClass:[NSString class]]) {
            NSString *aStr = [(NSArray*)[_dataSource objectForKey:@"data"] objectAtIndex:pageIndex];
            DONetworkImageView *aLLNetworkImageView = [[[DONetworkImageView alloc] initWithFrame:self.view.bounds withURLPath:aStr] autorelease];
            aLLNetworkImageView.defaultImage = _defaultImage;
            
            return aLLNetworkImageView;
        }
        //**** type:1 boudle ****
        else if ([[_dataSource objectForKey:@"type"] isEqualToString:@"1"]
                 && [[(NSArray*)[_dataSource objectForKey:@"data"] objectAtIndex:pageIndex] isKindOfClass:[NSString class]]) {
            NSString *aStr = [(NSArray*)[_dataSource objectForKey:@"data"] objectAtIndex:pageIndex];
            UIImageView *aImageView = [[[UIImageView alloc] initWithFrame:self.view.bounds] autorelease];
            aImageView.contentMode = UIViewContentModeScaleAspectFit;
            aImageView.autoresizingMask = (UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight);
            aImageView.backgroundColor = [UIColor clearColor];
            aImageView.image = [UIImage imageNamed:aStr];
            return aImageView;
        }
        //**** type:2 图片 ****
        else if ([[_dataSource objectForKey:@"type"] isEqualToString:@"2"]
                 && [[(NSArray*)[_dataSource objectForKey:@"data"] objectAtIndex:pageIndex] isKindOfClass:[UIImage class]]) {
            UIImage *aImage = [(NSArray*)[_dataSource objectForKey:@"data"] objectAtIndex:pageIndex];
            UIImageView *aImageView = [[[UIImageView alloc] initWithFrame:self.view.bounds] autorelease];
            aImageView.contentMode = UIViewContentModeScaleAspectFit;
            aImageView.autoresizingMask = (UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight);
            aImageView.backgroundColor = [UIColor clearColor];
            aImageView.image = aImage;
            return aImageView;
        }
    }
    UILabel *aLabel = [[[UILabel alloc] initWithFrame:CGRectMake(0, self.view.bounds.size.height/2, self.view.bounds.size.width, 20)] autorelease];
    aLabel.textAlignment = NSTextAlignmentCenter;
    aLabel.text = @"暂无图片";
    aLabel.backgroundColor = [UIColor clearColor];
    aLabel.textColor = [UIColor whiteColor];
    return aLabel;
    
}

- (CGSize)scrollView:(DOScrollView*)scrollView sizeOfPageAtIndex:(NSInteger)pageIndex {
    return CGSizeMake(self.view.bounds.size.width, self.view.bounds.size.height);
}





#pragma mark -
#pragma mark action

-(void)backAction{
    //[self dismissModalViewControllerAnimated:NO];
    [self dismissViewControllerAnimated:YES completion:^{
        
    }];
}


@end
