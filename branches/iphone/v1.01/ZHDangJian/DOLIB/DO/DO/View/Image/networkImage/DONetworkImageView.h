/*
 * 用于网络异步加载图片
 * Created by LL on 12-5-24.
 * V1.0
 */

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

typedef enum{
	LLNetworkImageViewUseProtocolCachePolicy = 0,//默认缓存
	LLNetworkImageViewReloadIgnoringCacheData,//重载
}LLNetworkImageViewCachePolicy;


@protocol DONetworkImageViewDelegate;
@interface DONetworkImageView : UIView {
	UIImageView*                _imageView;
	UIActivityIndicatorView*    _activityView;
	
	NSURLConnection*    _connection;
	NSMutableData*      _data;
    UIImage*            _image;
	UIImage*            _defaultImage;
    
	id<DONetworkImageViewDelegate>	_delegate;
}
@property(nonatomic,assign)id<DONetworkImageViewDelegate> delegate;
@property(nonatomic,retain)UIImage *defaultImage;

-(id)initWithFrame:(CGRect)frame withURLPath:(NSString*)aURLStr;

- (UIImage*)image;
- (void)unsetImage;
- (void)setImage:(UIImage*)aImage;
- (void)setImageContentMode:(UIViewContentMode)aMode;

/**
 * 设置加载的图片URL,默认缓存,60秒延时
 */
-(void)setURLPath:(NSString*)aURLStr;
-(void)setURLPath:(NSString*)aURLStr withCachePolicy:(LLNetworkImageViewCachePolicy)aCache;
-(void)setURLPath:(NSString*)aURLStr withCachePolicy:(LLNetworkImageViewCachePolicy)aCache withTimeout:(CGFloat)aTimeout;
@end




/**
 * LLNetworkImageViewDelegate
 */
@protocol DONetworkImageViewDelegate<NSObject>
@optional
-(void)didFinishLoadNetworkImage:(DONetworkImageView*)aNetworkImageView;
-(void)didFailLoadNetworkImage:(DONetworkImageView*)aNetworkImageView;
-(void)imageViewTouchesEnded:(DONetworkImageView*)aNetworkImageView;
@end




