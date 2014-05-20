//
//  AboutUsVC.m
//  ZHDangJian
//
//  Created by do1 on 13-11-12.
//
//

#import "AboutUsVC.h"

@interface AboutUsVC ()

@end

@implementation AboutUsVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view.
    
    [self showBack];
    self.title = @"关于我们";
    
    [self loadDocument:@"关于我们.html"];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_myWebView release];
    [super dealloc];
}
- (void)viewDidUnload {
    [self setMyWebView:nil];
    [super viewDidUnload];
}

- (void)viewWillAppear:(BOOL)animated
{
    self.navigationController.navigationBarHidden = NO;
    [super viewWillAppear:animated];
}

#pragma mark - Action

- (void)loadDocument:(NSString*)docName {
    
    NSString *mainBundleDirectory = [[NSBundle mainBundle] bundlePath];
    
    NSString *path = [mainBundleDirectory  stringByAppendingPathComponent:docName];
    
    NSURL *url = [NSURL fileURLWithPath:path];
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    
    self.myWebView.scalesPageToFit = YES;
    
    [self.myWebView loadRequest:request];
}



#pragma mark - webView delegate

- (void)webViewDidStartLoad:(UIWebView *)webView
{
    [self initHUBTitle:@"正在加载..." subTitle:nil];
}

- (void)webViewDidFinishLoad:(UIWebView *)webView
{
    [self removeHub];
}

- (void)webView:(UIWebView *)webView didFailLoadWithError:(NSError *)error
{
    [self removeHub];
    [UIAlertView showTip:NetWorkFaild];
}

@end
