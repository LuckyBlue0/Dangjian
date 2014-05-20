

#import "BaseVC.h"

@implementation BaseVC

@synthesize HUD;


-(void)dealloc{
    HUD.delegate = nil;
    DO_RELEASE_SAFELY(HUD);
    [super dealloc];
}


-(void)viewDidLoad{
    [super viewDidLoad];
    
    [self.navigationController.navigationBar setBackgroundImage:[UIImage imageNamed:@"public_头部背景1px"] forBarMetrics:0];
    
}

#pragma mark - net error
- (void)model:(DOModel*)model didFailLoadWithError:(NSError*)error
{
    [obj_message_box show_message:NetWorkFaild last:1.5];
}


#pragma mark - tipHub

-(void)removeHub{
//    [obj_message_box hidden];
    if (nil!=HUD) {
        [HUD removeFromSuperview];
        DO_RELEASE_SAFELY(HUD);
    }
}

-(void)initHUBTitle:(NSString *)title subTitle:(NSString *)subTitle{
//    [obj_message_box loading_with_message:title];
    if (nil!=HUD) {
        [HUD show:NO];
        DO_RELEASE_SAFELY(HUD);
    }
    HUD = [[MBProgressHUD alloc] initWithView:self.view];
    HUD.delegate = self;
    if (self.navigationController) {
        [self.navigationController.view addSubview:HUD];
    }else{
        [self.view addSubview:HUD];
    }  
    
    
    if (title==nil) {
        HUD.labelText = @"正在加载...";
    }
    else HUD.labelText = title;
    if (subTitle==nil) {
        HUD.detailsLabelText = @"请稍后!";
    }
    else HUD.detailsLabelText = subTitle;
    HUD.square = YES;
    [HUD show:YES];
}

-(void)changeHUBTitle:(NSString *)title subTitle:(NSString *)subTitle{
    
//    [obj_message_box show_message:title last:1.5];
    
    HUD.mode = MBProgressHUDModeText;
    HUD.labelText = title;
    HUD.detailsLabelText=subTitle;
    HUD.removeFromSuperViewOnHide = YES;
    [HUD hide:YES afterDelay:1];
    
}




-(void)showBack{
    //back
    UIButton *aButton = [[[UIButton alloc] initWithFrame:
						  CGRectMake(0, 0, 42,31)] autorelease];
    [aButton.titleLabel setFont:[UIFont systemFontOfSize:12]];
    [aButton setImage1:@"notify_返回" Image2:@"notify_返回hover"];
	[aButton addTarget:self action:@selector(backAction) forControlEvents:UIControlEventTouchUpInside];
	UIBarButtonItem *aBarButtonItem = [[UIBarButtonItem alloc] initWithCustomView:aButton];
	self.navigationItem.leftBarButtonItem = aBarButtonItem;
	[aBarButtonItem release];
}

-(void)showNavLeftBtn:(CGFloat)width title:(NSString*)title{
    UIButton *aButton = [[[UIButton alloc] initWithFrame:
						  CGRectMake(0, 0, width,31)] autorelease];
    [aButton.titleLabel setFont:[UIFont systemFontOfSize:13]];
    [aButton setBGImage1:@"public_四字按钮" BGImage2:@"public_四字按钮_hover"];
    [aButton setTitleColor:RGB(170, 118, 0) forState:UIControlStateNormal];
    [aButton setTitleColor:RGB(170, 118, 0) forState:UIControlStateHighlighted];
    [aButton setTitle:title forState:UIControlStateNormal];
	[aButton addTarget:self action:@selector(backAction) forControlEvents:UIControlEventTouchUpInside];
	UIBarButtonItem *aBarButtonItem = [[UIBarButtonItem alloc] initWithCustomView:aButton];
	self.navigationItem.leftBarButtonItem = aBarButtonItem;
	[aBarButtonItem release];
}

-(void)showNavBtn:(CGFloat)width target:(id)target action:(SEL)action title:(NSString*)title{
    UIButton *aButton = [[[UIButton alloc] initWithFrame:
						  CGRectMake(0, 0, width,31)] autorelease];
    [aButton setBGImage1:@"public_四字按钮" BGImage2:@"public_四字按钮_hover"];
    [aButton.titleLabel setFont:[UIFont systemFontOfSize:13]];
    [aButton setTitleColor:RGB(170, 118, 0) forState:UIControlStateNormal];
    [aButton setTitleColor:RGB(170, 118, 0) forState:UIControlStateHighlighted];
    [aButton setTitle:title forState:UIControlStateNormal];
	[aButton addTarget:target action:action forControlEvents:UIControlEventTouchUpInside];
	UIBarButtonItem *aBarButtonItem = [[UIBarButtonItem alloc] initWithCustomView:aButton];
	self.navigationItem.rightBarButtonItem = aBarButtonItem;
	[aBarButtonItem release];
}


-(void)setCellDefaultBg:(UITableViewCell*)cell{
    //背景
    UIImageView *aBackGroundView = [[[UIImageView alloc] initWithImage:[UIImage imageNamed:@"public_列表背景"]] autorelease];
    cell.backgroundView = aBackGroundView;
    UIImageView *aHBackGroundView = [[[UIImageView alloc] initWithImage:[UIImage imageNamed:@"public_列表背景（选中）"]] autorelease];
    cell.selectedBackgroundView = aHBackGroundView;
}


-(void)setTapHideKeyboard:(SEL)action{
    UITapGestureRecognizer *aKeyword = [[[UITapGestureRecognizer alloc] initWithTarget:self action:action]autorelease];
    [aKeyword setNumberOfTouchesRequired:1];
    aKeyword.cancelsTouchesInView =NO;
    aKeyword.delegate=self;
    [self.view addGestureRecognizer:aKeyword];
}


#pragma mark -  UIGestureRecognizerDelegate
- (BOOL)gestureRecognizer:(UIGestureRecognizer *)gestureRecognizer shouldReceiveTouch:(UITouch *)touch{
    if ( [touch.view isKindOfClass:[UIButton class]]){
        return NO;
    }
    return YES;
}





-(void)backAction{
    [self.navigationController popViewControllerAnimated:YES];
}
#pragma mark - other
//字典string为nil设为@“”
-(NSDictionary *)checkDictionaryData:(NSDictionary *)a_dic{
    NSArray *key =[[[NSArray alloc] initWithArray:[a_dic allKeys]] autorelease];
    NSMutableDictionary *returnDic = [[[NSMutableDictionary alloc] initWithDictionary:a_dic] autorelease];
    for (int i=0; i<[key count]; i++) {
        if ([[a_dic objectForKey:[key objectAtIndex:i]] isKindOfClass:[NSString class]]) {
            NSString *temStr = [a_dic objectForKey:[key objectAtIndex:i]];
            if ([temStr isEmpty]) {
                [returnDic setObject:@"" forKey:[key objectAtIndex:i]];
            }
        }
    }
    
    return (NSDictionary *)returnDic;
}

+ (NSString *)buildJson:(NSDictionary *)dic {
    return [BaseVC buildJson:dic encode:NSUTF8StringEncoding];
}

+ (NSString *)buildJson:(NSDictionary *)dic encode:(NSStringEncoding)encode {
    NSData *data = [NSJSONSerialization dataWithJSONObject:dic options:0 error:nil];
    NSString *str = [[NSString alloc] initWithData:data encoding:encode];
    return [str autorelease];
}



@end
