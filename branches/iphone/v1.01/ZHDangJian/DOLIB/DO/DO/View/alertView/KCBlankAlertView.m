
#import "KCBlankAlertView.h"

@interface KCBlankAlertView ()
-(void)showWithAnimation;
@end


@implementation KCBlankAlertView
@synthesize vc;
@synthesize contentView=_contentView;
@synthesize delegate=_delegate;

-(void)dealloc{
    [vc release];
    vc=nil;
    [_contentView release];
    _contentView=nil;
    [super dealloc];
}


#pragma mark -
#pragma mark public
-(void)show{
    if (nil==_contentView) {
        return;
    }
    [self showWithAnimation];
    
}
-(void)dismiss{
    [UIView animateWithDuration:0.2f
                     animations:
     ^{
         [vc dismssAnimate];
     }
                     completion:^(BOOL finished)
     {
         
         [vc dismissModalViewControllerAnimated:NO];
     }];
}




#pragma mark -
#pragma mark private

-(void)showWithAnimation{
    
    if (!vc) {
        vc = [[KCBlankAlertVC alloc] init];
        vc.contentView = _contentView;
    }
    
    UIWindow *keyWindow = [UIApplication sharedApplication].keyWindow;
    UIViewController* controller = keyWindow.rootViewController;
    controller.modalPresentationStyle = UIModalPresentationCurrentContext;
    [controller presentModalViewController:vc animated:NO];
    

    [vc willshowAnimate];
    
    [UIView animateWithDuration:0.2f animations:^{
        
        [vc showAnimate];
    }
                     completion:^(BOOL finished) {
                         
                         
                         if (finished) {
                             if ([_delegate respondsToSelector:@selector(KCBlankAlertViewDidShow:)]) {
                                 [_delegate performSelector:@selector(KCBlankAlertViewDidShow:) withObject:self];
                             }
                         }
                     }];
}

@end





/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////

@implementation KCBlankAlertVC

@synthesize contentView;

-(void)dealloc{
    [contentView release];
    contentView=nil;
    [super dealloc];
}


-(void)loadView{
    [super loadView];
    self.view.backgroundColor = [UIColor clearColor];
    
    UIView *aBGView = [[[UIView alloc] initWithFrame:self.view.bounds] autorelease];
    aBGView.backgroundColor = [UIColor blackColor];
    aBGView.opaque = YES;
    aBGView.alpha = 0.5f;
    [aBGView setAutoresizingMask:UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight];
    aBGView.userInteractionEnabled = YES;
    [self.view addSubview:aBGView];
    
    [self.view addSubview:self.contentView];
}

-(void)willshowAnimate{
    self.contentView.center = CGPointMake(self.view.bounds.size.width / 2, 0);
}
-(void)showAnimate{
    self.contentView.center = CGPointMake(self.view.bounds.size.width / 2, self.view.bounds.size.height / 2);
}
-(void)dismssAnimate{
    self.contentView.center = CGPointMake(self.view.bounds.size.width / 2, 0);
}

-(BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}


@end




