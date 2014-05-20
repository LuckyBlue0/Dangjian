#import "KCWebVC.h"
#import "DOMacro.h"
#import "DOAddition.h"

@implementation KCWebVC

@synthesize delegate    = _delegate;
@synthesize headerView  = _headerView;




#pragma mark - init 

- (id)initWithCoder:(NSCoder *)aDecoder{
    if ((self = [super initWithCoder:aDecoder])){
        self.hidesBottomBarWhenPushed = YES;
    }
    return self;
}

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
	self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
	if (self) {
		self.hidesBottomBarWhenPushed = YES;
	}
	
	return self;
}

- (id)initWithURL:(NSURL*)aURL{
	if (self=[super initWithNibName:nil bundle:nil]) {
		[self openURL:aURL];
	}
	return self;
}

- (id)init {
	self = [self initWithNibName:nil bundle:nil];
	if (self) {
	}
	
	return self;
}

- (void)dealloc {
    _delegate = nil;
    _webView.delegate = nil;
    DO_RELEASE_SAFELY(_webView);
    DO_RELEASE_SAFELY(_toolbar);
    DO_RELEASE_SAFELY(_backButton);
    DO_RELEASE_SAFELY(_forwardButton);
    DO_RELEASE_SAFELY(_refreshButton);
    DO_RELEASE_SAFELY(_activityItem);
    DO_RELEASE_SAFELY(_loadingURL);
    DO_RELEASE_SAFELY(_headerView);

    [super dealloc];
}





#pragma mark -action

- (void)backAction {
	[_webView goBack];
}

- (void)forwardAction {
	[_webView goForward];
}

- (void)refreshAction {
	[_webView reload];
}





#pragma mark -UIViewController

- (void)loadView {
    [super loadView];
    
    _webView = [[UIWebView alloc] initWithFrame:self.view.bounds];
    _webView.delegate = self;
    _webView.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;
    _webView.scalesPageToFit = YES;
    [self.view addSubview:_webView];

    UIActivityIndicatorView* spinner =[[[UIActivityIndicatorView alloc] initWithActivityIndicatorStyle:UIActivityIndicatorViewStyleWhite]autorelease];
    [spinner startAnimating];
    _activityItem = [[UIBarButtonItem alloc] initWithCustomView:spinner];
	
    _backButton = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemRewind target:self action:@selector(backAction)];
    _backButton.tag = 2;
    _backButton.enabled = NO;
    
    _forwardButton = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemFastForward target:self action:@selector(forwardAction)];
    _forwardButton.tag = 1;
    _forwardButton.enabled = NO;
    
    _refreshButton = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemRefresh target:self action:@selector(refreshAction)];
    _refreshButton.tag = 3;
    
    UIBarItem* space = [[[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemFlexibleSpace target:nil action:nil] autorelease];
    
    _toolbar = [[UIToolbar alloc] initWithFrame:CGRectMake(0, self.view.frame.size.height - 49,self.view.frame.size.width, 49)];
    _toolbar.autoresizingMask =UIViewAutoresizingFlexibleTopMargin | UIViewAutoresizingFlexibleWidth;
    _toolbar.items = [NSArray arrayWithObjects:_backButton,space,_forwardButton,space,_refreshButton,nil];
    
    [self.view addSubview:_toolbar];
    [self.navigationItem setHidesBackButton:NO];
}

- (void)viewDidUnload {
    
	[super viewDidUnload];
	
	_delegate = nil;
	_webView.delegate = nil;
	
	DO_RELEASE_SAFELY(_webView);
	DO_RELEASE_SAFELY(_toolbar);
	DO_RELEASE_SAFELY(_backButton);
	DO_RELEASE_SAFELY(_forwardButton);
	DO_RELEASE_SAFELY(_refreshButton);
	DO_RELEASE_SAFELY(_activityItem);
}

- (void)viewWillAppear:(BOOL)animated {
	[super viewWillAppear:animated];
	[self.navigationController setNavigationBarHidden:NO animated:YES];
}

- (void)viewWillDisappear:(BOOL)animated {
	[super viewWillDisappear:animated];
	[self.navigationController setNavigationBarHidden:NO animated:YES];
}






#pragma mark -
#pragma mark UIWebViewDelegate

- (BOOL)webView:(UIWebView*)webView shouldStartLoadWithRequest:(NSURLRequest*)request navigationType:(UIWebViewNavigationType)navigationType{
    
    if ([_delegate respondsToSelector:@selector(webController:webView:shouldStartLoadWithRequest:navigationType:)]
      &&![_delegate webController:self webView:webView shouldStartLoadWithRequest:request navigationType:navigationType]) {
			return NO;
		}
    
    [_loadingURL release];
    _loadingURL = [request.URL retain];
    _backButton.enabled = [_webView canGoBack];
    _forwardButton.enabled = [_webView canGoForward];
    return YES;
}

- (void)webViewDidStartLoad:(UIWebView*)webView {
    
    if ([_delegate respondsToSelector:@selector(webController:webViewDidStartLoad:)]) {
        [_delegate webController:self webViewDidStartLoad:webView];
    }
    self.title = @"正在加载...";
    if (!self.navigationItem.rightBarButtonItem) {
        [self.navigationItem setRightBarButtonItem:_activityItem animated:YES];
    }
    _backButton.enabled = [_webView canGoBack];
    _forwardButton.enabled = [_webView canGoForward];
}

- (void)webViewDidFinishLoad:(UIWebView*)webView {
    if ([_delegate respondsToSelector:@selector(webController:webViewDidFinishLoad:)]) {
        [_delegate webController:self webViewDidFinishLoad:webView];
    }
    
    DO_RELEASE_SAFELY(_loadingURL);
    self.title = [_webView stringByEvaluatingJavaScriptFromString:@"document.title"];
    if (self.navigationItem.rightBarButtonItem == _activityItem) {
        [self.navigationItem setRightBarButtonItem:nil animated:YES];
    }
    _backButton.enabled = [_webView canGoBack];
    _forwardButton.enabled = [_webView canGoForward];
}

- (void)webView:(UIWebView*)webView didFailLoadWithError:(NSError*)error {
    
    if ([_delegate respondsToSelector:@selector(webController:webView:didFailLoadWithError:)]) {
        [_delegate webController:self webView:webView didFailLoadWithError:error];
    }
    
    DO_RELEASE_SAFELY(_loadingURL);
    [self webViewDidFinishLoad:webView];
}






#pragma mark - public

- (NSURL*)URL {
    return _loadingURL ? _loadingURL : _webView.request.URL;
}

- (void)openURL:(NSURL*)URL {
    NSMutableURLRequest* request = [NSMutableURLRequest requestWithURL:URL];
    [self openRequest:request];
}

- (void)openRequest:(NSURLRequest*)request {
    [self view];
    [_webView loadRequest:request];
}

@end





