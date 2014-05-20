//弹出的全屏空白视图 -类似UIAlertView(自定义布局)

#import <UIKit/UIKit.h>

static CGRect KCScreenBounds();
@protocol KCBlankAlertViewDelegate;
@class KCBlankAlertVC;
@interface KCBlankAlertView : NSObject{
    
    UIView *_contentView;
    id<KCBlankAlertViewDelegate> _delegate;
}


@property(nonatomic,retain)KCBlankAlertVC *vc;

@property(nonatomic,retain)UIView *contentView;
@property(nonatomic,assign)id<KCBlankAlertViewDelegate> delegate;

//弹出
-(void)show;

//消失
-(void)dismiss;

@end



/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
@protocol KCBlankAlertViewDelegate <NSObject>
@optional
-(void)KCBlankAlertViewDidShow:(KCBlankAlertView*)aKCBlankAlertView;
@end



/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
@interface KCBlankAlertVC : UIViewController

@property(nonatomic,retain)UIView *contentView;

-(void)willshowAnimate;
-(void)showAnimate;
-(void)dismssAnimate;

@end

