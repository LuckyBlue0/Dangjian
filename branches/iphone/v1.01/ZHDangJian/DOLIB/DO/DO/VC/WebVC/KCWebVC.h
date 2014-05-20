/*
 *
 * make by kllmctrl @2012-12-25
 * v 1.0
 *
 */


#import <UIKit/UIKit.h>


/*
 * 浏览webView
 * 全屏浏览VC控件
 *
 */


@protocol KCWebVCDelegate;
@interface KCWebVC : UIViewController <UIWebViewDelegate> {
@protected
    UIWebView       *_webView;
    UIToolbar       *_toolbar;
    UIView          *_headerView;
    UIBarButtonItem *_backButton;
    UIBarButtonItem *_forwardButton;
    UIBarButtonItem *_refreshButton;
    UIBarButtonItem *_activityItem;
    
    NSURL               *_loadingURL;
    id<KCWebVCDelegate> _delegate;
}

@property (nonatomic, readonly) NSURL*  URL;

@property (nonatomic, retain)   UIView* headerView;

@property (nonatomic, assign)   id<KCWebVCDelegate> delegate;

- (void)openURL:(NSURL*)URL;

- (void)openRequest:(NSURLRequest*)request;

- (id)initWithURL:(NSURL*)aURL;

@end






////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 *
 * KCWebVCDelegate
 *
 */
@protocol KCWebVCDelegate <NSObject>

@optional

- (BOOL)webController:(KCWebVC *)controller webView:(UIWebView *)webView shouldStartLoadWithRequest:(NSURLRequest *)request navigationType:(UIWebViewNavigationType)navigationType;

- (void)webController:(KCWebVC *)controller webViewDidStartLoad:(UIWebView *)webView;

- (void)webController:(KCWebVC *)controller webViewDidFinishLoad:(UIWebView *)webView;

- (void)webController:(KCWebVC *)controller webView:(UIWebView *)webView didFailLoadWithError:(NSError *)error;

@end



