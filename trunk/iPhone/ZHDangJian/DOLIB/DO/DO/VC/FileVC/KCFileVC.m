
#import "KCFileVC.h"

@implementation KCFileVC

@synthesize docVC;




#pragma mark init

-(id)initWithURL:(NSURL *)aURL{
    if (self=[super init]) {
        _isShowing = NO;
        _docURL = [aURL retain];
    }
    return self;
}

-(void)dealloc{
    
    docVC.delegate = nil;
    [docVC release];
    [_docURL release];
    
    [super dealloc];
}





#pragma mark VC

-(void)showFile{
    if (_docURL) {
        
        self.docVC = [UIDocumentInteractionController interactionControllerWithURL:_docURL];
        self.docVC.delegate = self;
        if (![self.docVC presentPreviewAnimated:YES]){
            // show file error
        }
    }
}

-(void)viewDidAppear:(BOOL)animated{
    if (!_isShowing) {
        [self showFile];
        
    }else{
        
        
#if __IPHONE_OS_VERSION_MAX_ALLOWED >= __IPHONE_6_0
        [self dismissViewControllerAnimated:NO completion:^{
        }];
#else
        [self dismissModalViewControllerAnimated:NO];
#endif
        
        
    }
    [super viewDidAppear:animated];
}





#pragma mark UIDocumentInteractionControllerDelegate

- (UIViewController *)documentInteractionControllerViewControllerForPreview:(UIDocumentInteractionController *)controller{
    return self;
}

- (UIView *)documentInteractionControllerViewForPreview:(UIDocumentInteractionController *)controller{
    return self.view;
}

- (BOOL)documentInteractionController:(UIDocumentInteractionController *)controller canPerformAction:(SEL)action{
    BOOL canPerform = NO;
    if (action == @selector(copy:))
        canPerform = YES;
    return canPerform;
}

- (BOOL)documentInteractionController:(UIDocumentInteractionController *)controller performAction:(SEL)action{
    BOOL handled = NO;
    if (action == @selector(copy:)){
        handled = YES;
    }
    return handled;
}

- (CGRect)documentInteractionControllerRectForPreview:(UIDocumentInteractionController*)controller{
	return self.view.frame;
}

- (void)documentInteractionControllerWillBeginPreview:(UIDocumentInteractionController *)controller{
    _isShowing = YES;
}

@end
