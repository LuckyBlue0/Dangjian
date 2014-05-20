
#import "DOWeatherVC.h"
#import "DOMacro.h"
#import "DOWeatherModel.h"
#import "DOAddition.h"
#import "DOErrorView.h"
#import "DOLoadingView.h"

@implementation DOWeatherVC

@synthesize dataDic=_dataDic;

-(UIView *)contentView{
    if (!_contentView) {
        _contentView = [[UIView alloc] initWithFrame:self.view.bounds];
    }
    return _contentView;
}

-(UIView *)loadingView{
    if (!_loadingView) {
        _loadingView = [[DOLoadingView alloc] initWithFrame:self.view.bounds style:TTActivityLabelStyleGray text:@""];
    }
    return _loadingView;
}

-(UIView *)emptyView{
    if (!_emptyView) {
        _emptyView = [[DOErrorView alloc] initWithTitle:@"" subtitle:@"" image:nil];
        _emptyView.frame = self.view.bounds;
    }
    return _emptyView;
}

-(UIView *)errorView{
    if (!_errorView) {
        _errorView = [[DOErrorView alloc] initWithTitle:@"" subtitle:@"" image:nil];
        _errorView.frame = self.view.bounds;
    }
    return _errorView;
}


/**
 * 创建model
 */
- (void)createModel {
    self.model = [[[DOWeatherModel alloc] init] autorelease];
    self.model.delegate = self;
    [(DOWeatherModel*)self.model startRequest];
}


#pragma mark -
#pragma mark LLModelDelegate

- (void)modelDidFinishLoad:(DOModel*)model{
    self.dataDic = ((DOWeatherModel*)model).dataDic;
    
    [super modelDidFinishLoad:model];
}


@end

