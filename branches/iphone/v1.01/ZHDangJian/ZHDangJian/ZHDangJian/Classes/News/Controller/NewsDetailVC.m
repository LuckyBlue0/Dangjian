//
//  NewsDetailVC.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-12.
//
//

#import "NewsDetailVC.h"

@interface NewsDetailVC ()

@end

@implementation NewsDetailVC

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
    
    [self creatNewsDetailModel];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_myWebView release];
    [_newsInfoId release];
    [_newsInfoType release];
    _newsDetailModel.delegate = nil;
    DO_RELEASE_SAFELY(_newsDetailModel);
    [super dealloc];
}

- (void)viewDidUnload {
    [self setMyWebView:nil];
    [super viewDidUnload];
}


#pragma mark - UIWebView Delegate

- (void)webViewDidStartLoad:(UIWebView *)webView
{
//    [self initHUBTitle:nil subTitle:nil];
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


#pragma mark - Model

- (void)creatNewsDetailModel
{
    if (_newsDetailModel) {
        _newsDetailModel.delegate = nil;
        DO_RELEASE_SAFELY(_newsDetailModel);
    }
    _newsDetailModel = [[BaseModel alloc] init];
    _newsDetailModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:self.newsInfoId] forKey:@"newsInfoId"];
    [aDic setObject:[DES3Util enDES3:self.newsInfoType] forKey:@"newsInfoType"];
    [aDic setObject:[[NSString stringWithFormat:@"%@%@", self.newsInfoId, self.newsInfoType] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KNewsDetailUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_newsDetailModel startRequest:requestUrl];
}


- (void)modelDidStartLoad:(DOModel *)model
{
    [self initHUBTitle:nil subTitle:nil];
}

- (void)modelDidFinishLoad:(DOModel *)model
{
//    [self removeHub];
    RequstResult *aRR = [((BaseModel *)model).dataDic objectForKey:@"RR"];
    if (model == self.newsDetailModel) {
        if (aRR.code) {
            if (aRR.dataDic) {
                NSString *urlString = [NSString stringWithFormat:@"%@%@", KWebViewUrl, [aRR.dataDic objectForKey:@"contentUrl"]];
                NSURL *myURL = [NSURL URLWithString:urlString];
                NSURLRequest *request =[NSURLRequest requestWithURL:myURL];
                [_myWebView loadRequest:request];
                return;
            }
        }
        [UIAlertView showTip:aRR.desc];
    }
}

- (void)model:(DOModel *)model didFailLoadWithError:(NSError *)error
{
    [self removeHub];
    [UIAlertView showTip:NetWorkFaild];
}


@end
