/*
 *
 * 加载更多table提示控件
 * Created by liming on 12-5-23.
 * V 1.0
 *
 */

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>


@interface DOTableFooterBar : UIView {
	UILabel						*_tipLabel;
	UIActivityIndicatorView		*_activityView;
	BOOL						_loading;
	BOOL						_hasMoreData;
}
@property(nonatomic,assign)BOOL hasMoreData;
- (void)setLoading:(BOOL)isLoad;
@end