
#import "DOModelVC.h"
#import "DOMacro.h"
#import "DOAddition.h"


@interface DOModelVC (protected)

- (void)createModel;

@end


@implementation DOModelVC

@synthesize model       = _model;
@synthesize modelError  = _modelError;


- (id)initWithCoder:(NSCoder *)aDecoder{
    if ((self = [super initWithCoder:aDecoder])){
        _flags.isViewInvalid = YES;
        _flags.isShowModelFirstTimeInvalid = YES;
    }
    return self;
}

-(id)init{
    if (self=[super init]) {
        _flags.isViewInvalid = YES;
        _flags.isShowModelFirstTimeInvalid = YES;
    }
    return self;
}
-(id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil{
    if (self=[super initWithNibName:nibNameOrNil bundle:nibBundleOrNil]) {
        _flags.isViewInvalid = YES;
        _flags.isShowModelFirstTimeInvalid = YES;
    }
    return self;
}

-(void)dealloc{
    _model.delegate=nil;
    DO_RELEASE_SAFELY(_model);
    DO_RELEASE_SAFELY(_modelError);
    
    DO_RELEASE_SAFELY(_loadingView);
    DO_RELEASE_SAFELY(_emptyView);
    DO_RELEASE_SAFELY(_errorView);
    
    [super dealloc];
}




#pragma mark -
#pragma mark UIViewController

- (void)viewWillAppear:(BOOL)animated {
    /**
     * 更新视图
     */
    [self updateView];
    
    [super viewWillAppear:animated];
}




#pragma mark -
#pragma mark Private

-(BOOL)canShowModel{
    //NSLog(@"!_flags.isShowModelFirstTimeInvalid=%d",!_flags.isShowModelFirstTimeInvalid);
    return !_flags.isShowModelFirstTimeInvalid && !_modelError;
}

/**
 * 更新状态
 */
- (void)updateViewStates {
    if (!self.model) {
        return;
    }
    
    BOOL showModel = NO, showLoading = NO, showError = NO, showEmpty = NO;
    
    if ([_model isLoaded]) {
        
        if ([self canShowModel]) {
            showModel = !_flags.isShowingModel;
            _flags.isShowingModel = YES;
            
        }else{
            if (_flags.isShowingModel) {
                [self showModel:NO];
                _flags.isShowingModel = NO;
            }
        }
        
    } else {
        if (_flags.isShowingModel) {
            [self showModel:NO];
            _flags.isShowingModel = NO;
        }
    }
    
    
    /**
     * 加载.....
     */
    if (_model.isLoading) {
        showLoading = !_flags.isShowingLoading;
        _flags.isShowingLoading = YES;
        
    }else {
        if (_flags.isShowingLoading) {
            [self showLoading:NO];
            _flags.isShowingLoading = NO;
        }
    }
    
    
    /**
     * 加载出错....
     */
    if (_modelError) {
        showError = !_flags.isShowingError;
        _flags.isShowingError = YES;
        
    } else {
        if (_flags.isShowingError) {
            [self showError:NO];
            _flags.isShowingError = NO;
        }
        
    }
    
    
    /**
     * 加载为空....
     */
    if (!_flags.isShowingLoading && !_flags.isShowingModel && !_flags.isShowingError && !_flags.isShowModelFirstTimeInvalid) {
        showEmpty = !_flags.isShowingEmpty;
        _flags.isShowingEmpty = YES;
        
    } else {
        if (_flags.isShowingEmpty) {
            [self showEmpty:NO];
            _flags.isShowingEmpty = NO;
        }
        
        _flags.isShowModelFirstTimeInvalid = NO;
    }
    
    
    
    /**
     * 更新视图
     */
    if (showModel) {
        [self showModel:YES];
        showLoading = NO;
        [self showLoading:NO];
        
    }
    if (showEmpty) {
        [self showEmpty:YES];
    }
    if (showError) {
        [self showError:YES];
    }
    if (showLoading) {
        [self showLoading:YES];
    }
}

- (DOModel*)model {
    if (!_model) {
        
        /**
         * 创建model
         */
        [self createModel];
        if (!_model) {
            self.model = [[[DOModel alloc] init] autorelease];
        }
    }
    return _model;
}

- (BOOL)shouldReload {
    return !_modelError && [self.model isOutdated];
}




#pragma mark -
#pragma mark LLModelDelegate

/**
 * 有数据更新时候调用
 */
- (void)invalidateView {
    
    _flags.isViewInvalid = YES;
    [self updateView];
}

- (void)modelDidStartLoad:(DOModel*)model {
    if (model == self.model) {
        
        [self invalidateView];
    }
}

- (void)modelDidFinishLoad:(DOModel*)model {
    
    if (model == _model) {
        
        DO_RELEASE_SAFELY(_modelError);
        [self invalidateView];
    }
}

- (void)model:(DOModel*)model didFailLoadWithError:(NSError*)error {
    if (model == _model) {
        self.modelError = error;
        [self invalidateView];
    }
}




#pragma mark -
#pragma mark Public

/**
 * 创建model
 */
- (void)createModel {
    //TODO :加载model.....
    //NSLog(@"createModel-1");
    
}

/**
 * 更新视图
 */
- (void)updateView {
    if (_flags.isViewInvalid && !_flags.isUpdatingView) {
        
        /**
         * 设置更新状态
         */
        _flags.isUpdatingView = YES;
        
        // Ensure the model and view is created
        self.model=[self model];
        self.view=self.view;
        
        
        /**
         * 更新视图
         */
        [self updateViewStates];
        
        
        /**
         * 重置更新状态
         */
        _flags.isViewInvalid = NO;
        _flags.isUpdatingView = NO;
        
        
        /**
         * 是否重新加载
         */
        [self reloadIfNeeded];
    }
    
}

/**
 * 重新加载
 */
- (void)reload {
    if (!_flags.isShowingModel) {
        [self showEmpty:NO];
        [self showError:NO];
        [self showLoading:YES];
    }

    _flags.isViewInvalid = YES;
    
    [self.model reset];
    [self createModel];
}

- (void)reloadIfNeeded {
    
    /**
     * 错误刷新无效
     */
    if ([self shouldReload] && ![self.model isLoading]) {        
        [self reload];
    }
}


-(void)clearModelError{
    DO_RELEASE_SAFELY(_modelError);
}



#pragma mark -
#pragma mark 各种状态下视图执行

- (void)showModel:(BOOL)show {
    
    /**
     * 内容视图
     */
}


-(UIView *)loadingView{
    return _loadingView;
}

-(UIView *)emptyView{
    return _emptyView;
}

-(UIView *)errorView{
    return _errorView;
}


/**
 * 各种状态下视图执行
 */
- (void)showLoading:(BOOL)show {
    
    /**
     * 加载视图
     */
    if (show) {
        _flags.isShowingLoading = YES;
        [self.view addSubview:[self loadingView]];
        
    }else{
        //_flags.isShowingLoading = NO;
        [_loadingView removeFromSuperview];
        DO_RELEASE_SAFELY(_loadingView);
    }
}

- (void)showEmpty:(BOOL)show {
    /**
     * 空视图
     */
    if (show) {
        _flags.isShowingEmpty = YES;
        [self.view addSubview:[self emptyView]];
    }else{
        [_emptyView removeFromSuperview];
        DO_RELEASE_SAFELY(_emptyView);
    }
}

- (void)showError:(BOOL)show {
    /**
     * 出错视图
     */
    if (show) {
        _flags.isShowingError = YES;
        [self.view addSubview:[self errorView]];
    }else{
        [_errorView removeFromSuperview];
        DO_RELEASE_SAFELY(_errorView);
    }
}


@end


