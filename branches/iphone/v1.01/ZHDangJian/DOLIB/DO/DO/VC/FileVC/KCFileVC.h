/*
 *
 * make by kllmctrl @2012-12-25
 * v 1.0
 *
 */


#import <UIKit/UIKit.h>


/*
 *
 * 浏览文档：
 1. UIDocumentInteractionController :多用于全屏显示
 2. UIWebView                       :多用于局部显示
 *
 *
 */



@interface KCFileVC : UIViewController<UIDocumentInteractionControllerDelegate>{
    
    BOOL    _isShowing;
    NSURL*  _docURL;
}

@property (nonatomic, retain) UIDocumentInteractionController *docVC;

-(id)initWithURL:(NSURL*)aURL;

@end


