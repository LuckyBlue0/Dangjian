#import "DOModel.h"


@implementation DOModel

@synthesize delegate = _delegate;


- (void)dealloc {
    _delegate=nil;
    
    [super dealloc];
}


#pragma mark -
#pragma mark public
- (BOOL)isLoaded {
    return NO;
}

- (BOOL)isLoading {
    return NO;
}

- (BOOL)isOutdated{
    return NO;
}

-(BOOL)isEmpty{
    return YES;
}

- (void)reset{
    _delegate=nil;
}


#pragma mark -
#pragma mark private

/**
 * 回调代理 LLModelDelegate
 */
- (void)didStartLoad {
    if ([_delegate respondsToSelector:@selector(modelDidStartLoad:)]) {
        [_delegate performSelector:@selector(modelDidStartLoad:) withObject:self];
    }
}

- (void)didFinishLoad {
    if ([_delegate respondsToSelector:@selector(modelDidFinishLoad:)]) {
        [_delegate performSelector:@selector(modelDidFinishLoad:) withObject:self];
    }
}

- (void)didFailLoadWithError:(NSError*)error {
    if ([_delegate respondsToSelector:@selector(model:didFailLoadWithError:)]) {
        [_delegate performSelector:@selector(model:didFailLoadWithError:) withObject:self withObject:error];
    }
}

@end

