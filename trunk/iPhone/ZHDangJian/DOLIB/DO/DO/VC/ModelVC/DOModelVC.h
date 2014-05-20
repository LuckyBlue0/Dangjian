/**
 *
 * 具有model 的VC的基类
 * Created by kllmctrl on 12-11-8.
 *
 */


#import <UIKit/UIKit.h>
#import "DOModel.h"
#import "DOModelDelegate.h"

@interface DOModelVC : UIViewController <DOModelDelegate>{
    
    DOModel*    _model;
    NSError*    _modelError;
    
    struct {
        unsigned int isShowModelFirstTimeInvalid:1;//第一次不加载model
        unsigned int isViewInvalid:1;//是否有效（相对model更新）,第一次或者有数据更新为YES，其他返回按钮操作都为NO
        unsigned int isUpdatingView:1;
        unsigned int isShowingEmpty:1;
        unsigned int isShowingLoading:1;
        unsigned int isShowingModel:1;
        unsigned int isShowingError:1;
    } _flags;
    
    
    UIView *    _loadingView;
    UIView *    _emptyView;
    UIView *    _errorView;
}

@property (nonatomic, retain) DOModel* model;

@property (nonatomic, retain) NSError* modelError;


/**
 * 更新视图
 */
- (void)updateView;

/**
 * 重新加载
 */
- (void)reloadIfNeeded;

- (void)invalidateView;

-(void)clearModelError;

/**
 * VC要显示的视图
 */
- (void)showModel:(BOOL)show;

- (void)showLoading:(BOOL)show;

- (void)showEmpty:(BOOL)show;

- (void)showError:(BOOL)show;


@end











///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
//demo

//#import <UIKit/UIKit.h>
//#import "LLModel.h"
//#import "LLModelDelegate.h"
//#import "LLModelVC.h"
//
//@interface TestVC : LLModelVC<LLModelDelegate>{
//    
//}
//
//-(IBAction)fffreloadAction:(id)sender;
//
//@end

///////////////////////////////////////////////////////////////////////////////////////////////////
//
//#import "TestVC.h"
//#import <LLMacro.h>
//#import "TestModel.h"
//#import <LLAdditions.h>
//
//@implementation TestVC
//
//
///**
// * 创建model
// */
//- (void)createModel {
//    self.model = [[[TestModel alloc] init] autorelease];
//    self.model.delegate = self;
//    [(TestModel*)self.model testModel];
//}
//
//
//-(IBAction)fffreloadAction:(id)sender{
//    
//    [(TestModel*)self.model setOutdate:YES];
//    
//    [self reloadIfNeeded];
//}
//
//#pragma mark -
//#pragma mark 各种状态下视图执行
///**
// * 各种状态下视图执行
// */
//
//- (void)showLoading:(BOOL)show {
//    /**
//     * 加载视图
//     */
//    if (show) {
//        NSLog(@"showLoading");
//        UIView *aView = [[[UIView alloc] initWithFrame:XYWH(0, 44, 320, 460-44)] autorelease];
//        aView.backgroundColor = YELLOW_COLOR;
//        aView.tag = 1;
//        
//        UILabel *aLabel = [[[UILabel alloc] initWithFrame:XYWH(20, 60, 200, 20)] autorelease];
//        [aLabel setText:@"加载中...." font:14 color1:RGB(255, 0, 0) color2:nil];
//        [aView addSubview:aLabel];
//        
//        [self.view addSubview:aView];
//        
//    }else{
//        
//        NSLog(@"showLoading = NO");
//        [self.view removeSubview:1];
//    }
//}
//
//- (void)showModel:(BOOL)show {
//    
//    /**
//     * 内容视图
//     */
//    if (show) {
//        
//        NSLog(@"showModel");
//        
//    }else{
//        
//        NSLog(@"showModel = NO");
//    }
//}
//
//- (void)showEmpty:(BOOL)show {
//    /**
//     * 空视图
//     */
//    
//    if (show) {
//        
//        NSLog(@"showEmpty");
//        
//    }else{
//        
//        NSLog(@"showEmpty = NO");
//    }
//}
//
//- (void)showError:(BOOL)show {
//    /**
//     * 出错视图
//     */
//    if (show) {
//        NSLog(@"showError");
//    }else{
//        NSLog(@"showError = NO");
//    }
//}
//
//
//@end





///////////////////////////////////////////////////////////////////////////////////////////////////

//#import <Foundation/Foundation.h>
//#import "LLModel.h"
//
//@interface TestModel : LLModel{
//    
//    BOOL _loading;
//    BOOL _outdate;
//}
//
//
//-(void)testModel;
//-(void)setOutdate:(BOOL)outdate;
//@end



///////////////////////////////////////////////////////////////////////////////////////////////////
//#import "TestModel.h"
//#import "LLMacro.h"
//
//@implementation TestModel
//
//#pragma mark -
//#pragma mark LLModel && public
//- (BOOL)isLoaded {
//    return !_loading;
//}
//- (BOOL)isLoading {
//    return _loading;
//}
//- (BOOL)isOutdated{
//    return _outdate;
//}
//- (void)cancel {
//}
//
//-(void)setOutdate:(BOOL)outdate{
//    _outdate = YES;
//}
///**
// * 测试model 加载demo
// */
//-(void)testModel{
//    
//    _loading = YES;
//    _outdate = YES;
//    
//    [self didStartLoad];
//    [NSTimer scheduledTimerWithTimeInterval:5 target:self selector:@selector(timerAction) userInfo:nil repeats:NO];
//    
//}
//-(void)timerAction{
//    _loading = NO;
//    _outdate = NO;
//    
//    [self didFinishLoad];
//}
//
//@end


