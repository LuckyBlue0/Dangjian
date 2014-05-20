
#import "DORequestModel.h"
#import "DOMacro.h"

@implementation DORequestModel


#pragma mark -
#pragma mark init
-(id)init{
    if (self=[super init]) {
    }
    return self;
}
- (void)dealloc {
    [_request clearDelegatesAndCancel];
    DO_RELEASE_SAFELY(_request);
    
    [super dealloc];
}
- (void)reset {
}



#pragma mark -
#pragma mark LLModel && public
- (BOOL)isLoaded {
    return !_request;
}

- (BOOL)isLoading {
    return !!_request;
}

- (BOOL)isOutdated{
    return _outdate;
}

-(BOOL)isEmpty{
    return YES;
}

-(void)setOutdate:(BOOL)outdate{
    _outdate = outdate;
}

- (void)cancel {
    [_request cancel];
}

#pragma mark -
#pragma mark ASIHTTPRequest_DO
-(void)requestStarted:(ASIHTTPRequest *)aASIHTTPRequest_DO{
    //发起请求request为autorelease...
    [_request release];
    _request = [aASIHTTPRequest_DO retain];
    
    _outdate = YES;
    
    //回调父类DOModel
    [self didStartLoad];
}
- (void)requestFinished:(ASIHTTPRequest *)aASIHTTPRequest_DO{
    
    //sleep(2);//测试
    
    DO_RELEASE_SAFELY(_request);
    
    _outdate = NO;
    
    //回调父类DOModel
    [self didFinishLoad];
    
}
- (void)requestFailed:(ASIHTTPRequest *)aASIHTTPRequest_DO{
    
    sleep(2);//测试
    
    DO_RELEASE_SAFELY(_request);
    
    _outdate = YES;
    
    //回调父类DOModel
    [self didFailLoadWithError:aASIHTTPRequest_DO.error];
}


@end


