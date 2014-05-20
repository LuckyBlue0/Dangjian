#import "DONetworkImageView.h"
#import "DOCore.h"

@interface DONetworkImageView ()
-(void)requestImage:(NSString*)aURLStr withCachePolicy:(LLNetworkImageViewCachePolicy)aCache withTimeout:(CGFloat)aTimeout;
@end



@implementation DONetworkImageView
@synthesize delegate = _delegate;
@synthesize defaultImage = _defaultImage;



#pragma mark -
#pragma mark init

-(void)awakeFromNib{
    [super awakeFromNib];
    
    _activityView = [[UIActivityIndicatorView alloc] initWithActivityIndicatorStyle:UIActivityIndicatorViewStyleGray];
    _activityView.frame = CGRectMake((self.frame.size.width-20)/2, (self.frame.size.height-20)/2, 20.0f, 20.0f);
    [self addSubview:_activityView];
    [_activityView setHidesWhenStopped:YES];
    [_activityView startAnimating];
    
}


- (id)initWithFrame:(CGRect)frame{
	if (self=[super initWithFrame:frame]) {
		_activityView = [[UIActivityIndicatorView alloc] initWithActivityIndicatorStyle:UIActivityIndicatorViewStyleGray];
		_activityView.frame = CGRectMake((frame.size.width-20)/2, (frame.size.height-20)/2, 20.0f, 20.0f);
		[self addSubview:_activityView];
        [_activityView setHidesWhenStopped:YES];
		[_activityView startAnimating];
	}
	return self;
}
- (id)initWithFrame:(CGRect)frame withURLPath:(NSString*)aURLStr{
    self=[self initWithFrame:frame];
    
    [self requestImage:aURLStr withCachePolicy:LLNetworkImageViewUseProtocolCachePolicy withTimeout:60.0f];
	return self;
}
- (void)dealloc {
    [_activityView release];
    [_imageView release];
    [_defaultImage release];
    [_image release];
    
	if (!!_connection) {
		[_connection cancel];
	}
    DO_RELEASE_SAFELY(_connection);
    [_data release];
    [super dealloc];
}



#pragma mark -
#pragma mark private
-(UIImageView*)imageView{
    
    if (!_imageView) {
        UIImageView *aImageView =[[[UIImageView alloc]initWithFrame:self.bounds] autorelease];
        aImageView.contentMode = UIViewContentModeScaleAspectFit;
        aImageView.autoresizingMask = (UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight);
        [self addSubview:aImageView];
        _imageView = [aImageView retain];
    }
    return _imageView;
}

-(void)finishCacheAction{
    
    if ([[self subviews] count]>0) {
        //**** 移除多余图片(default) ****
        if ([[self subviews] objectAtIndex:0] == _activityView) {
            [_activityView setHidden:YES];
        }
        else {
            [[[self subviews] objectAtIndex:0] removeFromSuperview];
        }
    }
    
    [self imageView].image = _image;
}
-(void)requestImage:(NSString*)aURLStr withCachePolicy:(LLNetworkImageViewCachePolicy)aCache withTimeout:(CGFloat)aTimeout{
    
    
    NSURL *aURL = [[[NSURL alloc] initWithString:[aURLStr stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]] autorelease];
	if (nil != _connection)
        [_connection cancel];
        
    if (nil != _data){
        [_data release];
        _data=nil;
    }
    NSURLRequestCachePolicy aRequestCachePolicy;
    if (aCache==LLNetworkImageViewUseProtocolCachePolicy) {
        aRequestCachePolicy = NSURLRequestUseProtocolCachePolicy;        
    }else{
        aRequestCachePolicy = NSURLRequestReloadIgnoringCacheData;
    }
	NSURLRequest* aRequest = [NSURLRequest requestWithURL:aURL 
                                              cachePolicy:aRequestCachePolicy //缓存
                                          timeoutInterval:aTimeout];
    
    //使用缓存机制
    NSCachedURLResponse *aCachedResponse = [[NSURLCache sharedURLCache] cachedResponseForRequest:aRequest];
    if (aCachedResponse) {
        
        NSData *aCacheData = [aCachedResponse data];
        if (nil!=_image) {
            [_image release];
            _image=nil;
        }

        _image = [[UIImage alloc] initWithData:aCacheData];
        if (!_image) {
            _image = [_defaultImage retain];
        }
        [_data release];
        _data=nil;
        
        [self performSelectorOnMainThread:@selector(finishCacheAction) withObject:nil waitUntilDone:NO];
        
        //**** 下载完成时候,触发回调代理 ****
        if ([_delegate respondsToSelector:@selector(didFinishLoadNetworkImage:)]) {
            [_delegate performSelector:@selector(didFinishLoadNetworkImage:) withObject:self];
        }
        return;
    }
    
	_connection = [[NSURLConnection alloc] initWithRequest:aRequest 
												  delegate:self];
	[_activityView setHidden:NO];
}



#pragma mark -
#pragma mark public
-(void)setURLPath:(NSString*)aURLStr{
    [self requestImage:aURLStr withCachePolicy:LLNetworkImageViewUseProtocolCachePolicy withTimeout:60.0f];
}
-(void)setURLPath:(NSString*)aURLStr withCachePolicy:(LLNetworkImageViewCachePolicy)aCache{
    [self requestImage:aURLStr withCachePolicy:aCache withTimeout:60.0f];
}
-(void)setURLPath:(NSString*)aURLStr withCachePolicy:(LLNetworkImageViewCachePolicy)aCache withTimeout:(CGFloat)aTimeout{
    [self requestImage:aURLStr withCachePolicy:aCache withTimeout:aTimeout];
}
- (UIImage*)image {
	return _image?_image:nil;
}
- (void)unsetImage {
    if (!!_connection) {
        [_connection cancel];
        DO_RELEASE_SAFELY(_connection);
    }
    if (_data) {
        [_data release];
        _data=nil;
    }
    
    self.image = nil;
    [_activityView stopAnimating];
    
}
- (void)setImage:(UIImage*)aImage{

    if (!!_connection) {
        [_connection cancel];
        DO_RELEASE_SAFELY(_connection);
    }
    if (_data) {
        [_data release];
        _data=nil;
    }
    
    if (nil!=_image) {
        [_image release];
        _image=nil;
	}
	_image = [[UIImage alloc] initWithCGImage:aImage.CGImage];
    if (!_image) {
        _image = [_defaultImage retain];
    }

    [self imageView].image = _image;
    
	
	//**** 下载完成时候,触发回调代理 ****
	if ([_delegate respondsToSelector:@selector(didFinishLoadNetworkImage:)]) {
		[_delegate performSelector:@selector(didFinishLoadNetworkImage:) withObject:self];
	}
    
}

- (void)setImageContentMode:(UIViewContentMode)aMode{
    [self imageView].contentMode = aMode;
}


#pragma mark -
#pragma mark request Delegate
- (void)connection:(NSURLConnection *)theConnection didReceiveData:(NSData *)incrementalData{
    [_connection release];
    _connection = [theConnection retain];
    
	if (nil==_data){ 
		_data = [[NSMutableData alloc] initWithCapacity:2048];
	} 
	[_data appendData:incrementalData];
    
}

-(void)finishAction{
    if ([[self subviews] count]>0) {
		//**** 移除多余图片(default) ****
		if ([[self subviews] objectAtIndex:0] == _activityView) {
			[_activityView setHidden:YES];
		}
		else {
			[[[self subviews] objectAtIndex:0] removeFromSuperview];
		}
	}
	
	//**** 生成下载图片 ****
	if (nil!=_image) {
        [_image release];
        _image=nil;
	}
	_image = [[UIImage alloc] initWithData:_data];
    if (!_image) {
        _image = [_defaultImage retain];
    }
    [_data release];
    _data=nil;
    
    [self imageView].image = _image;
}

-(void)failedAction{
    [self imageView].image = _defaultImage;
}

- (void)connectionDidFinishLoading:(NSURLConnection*)theConnection{
    DO_RELEASE_SAFELY(_connection);
    
	[_activityView stopAnimating];
    
    [self performSelectorOnMainThread:@selector(finishAction) withObject:nil waitUntilDone:NO];
    
	
	//**** 下载完成时候,触发回调代理 ****
	if ([_delegate respondsToSelector:@selector(didFinishLoadNetworkImage:)]) {
		[_delegate performSelector:@selector(didFinishLoadNetworkImage:) withObject:self];
	}
}
- (void)connection:(NSURLConnection *)connection didFailWithError:(NSError *)error{
    DO_RELEASE_SAFELY(_connection);
    
	[_activityView stopAnimating];
    
    [self performSelectorOnMainThread:@selector(failedAction) withObject:nil waitUntilDone:NO];
	
	//**** 触发回调代理 ****
	if ([_delegate respondsToSelector:@selector(didFailLoadNetworkImage:)]) {
		[_delegate performSelector:@selector(didFailLoadNetworkImage:) withObject:self];
	}
}

@end

