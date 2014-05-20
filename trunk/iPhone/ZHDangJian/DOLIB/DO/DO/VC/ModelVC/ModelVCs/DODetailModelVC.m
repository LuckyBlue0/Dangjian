
#import "DODetailModelVC.h"
#import "DOMacro.h"
#import "DOAddition.h"

@implementation DODetailModelVC


-(void)dealloc{
    DO_RELEASE_SAFELY(_contentView);
    
    [super dealloc];
}


-(void)viewWillAppear:(BOOL)animated{
    _isValid = YES;
    [super viewWillAppear:animated];
    
}

-(void)viewWillDisappear:(BOOL)animated{
    _isValid = NO;
    [super viewWillDisappear:animated];
}


#pragma mark -
#pragma mark 子类继承实现

-(UIView *)contentView{
    return _contentView;
}


#pragma mark -
#pragma mark 各种状态下视图执行

- (void)showModel:(BOOL)show {
    /**
     * 内容视图
     */
    if (show) {
        [self.view addSubview:[self contentView]];
    }else{
        [_contentView removeFromSuperview];
        DO_RELEASE_SAFELY(_contentView);
    }
}


- (void)updateView {
    
    if (!_isValid) {
        return;
    }
    
    [super updateView];
}

@end



