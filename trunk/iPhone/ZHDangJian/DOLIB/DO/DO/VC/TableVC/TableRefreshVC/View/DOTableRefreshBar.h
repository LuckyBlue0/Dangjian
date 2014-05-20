/*
 *
 * 刷新table提示控件
 * Created by liming on 12-5-10.
 * V 1.0
 *
 */


//**** 加载状态 ****
typedef enum{
	EPullRefreshPulling = 0,
	EPullRefreshLoading,
	EPullRefreshNormal,
}EPullRefreshState;


#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

@protocol DOTableRefreshBarDelegate;
@interface DOTableRefreshBar : UIView {
	EPullRefreshState			_state;
	UILabel						*_stateLabel;
	UILabel						*_updateLabel;
	UIImageView					*_arrowImageView;
	UIActivityIndicatorView		*_activityView;
	id<DOTableRefreshBarDelegate>	_delegate;
}

@property(nonatomic,assign)id<DOTableRefreshBarDelegate> delegate;

-(void)setState:(EPullRefreshState)aState;

-(void)updateLastDate;

-(void)setLoading:(BOOL)isLoad;

-(BOOL)getLoading;

-(void)refreshScrollViewDidScroll:(UIScrollView *)aScrollView;

-(void)refreshScrollViewDidEndDragging:(UIScrollView *)aScrollView;

-(void)refreshScrollViewDidFinishedLoading:(UIScrollView *)scrollView;

@end


@protocol DOTableRefreshBarDelegate <NSObject>

@required

-(void)refreshTableData;

@end

