/**
 * 浏览图片--VC
 * 1.浏览多张图片
 * 2.对象:1)UIImage 2)URL 3)boudle
 * 3.全屏
 * 
 * presentModalViewController
 *
 * make by kllmctrl @2013-01-06
 *
 */

#import <UIKit/UIKit.h>
#import "DOScrollView.h"
#import "DONetworkImageView.h"

@interface KCPhotosVC : UIViewController<DOScrollViewDelegate,DOScrollViewDataSource>{

    UINavigationBar*    _navBar;
    DOScrollView*       _scrollView;
    UIImage*            _defaultImage;
    
    /*
     * type:
     * 0:URL -->[url1,url2,....]
     * 1:Boundle -->[name1,name2,....]
     * 2:Images -->[image1,image2,....]
     *
     * -->data:
     */
    NSDictionary* _dataSource;
    NSInteger _curPage;
    
    UIStatusBarStyle    _curStyle;
    BOOL                _curStatusBarHidden;
}

@property(nonatomic,retain)DOScrollView*    scrollView;
@property(nonatomic,retain)UIImage*         defaultImage;

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil dataSouce:(NSDictionary*)aDataSource;

- (id)initWithDataSource:(NSDictionary*)aDataSource;

-(NSDictionary*)creatDataSource;

@end

