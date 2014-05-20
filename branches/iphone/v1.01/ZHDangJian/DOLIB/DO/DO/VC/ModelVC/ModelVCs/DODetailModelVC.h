
/**
 *
 * 具有详情model 的VC的基类
 * Created by kllmctrl on 12-11-8.
 *
 */


#import <UIKit/UIKit.h>
#import "DOModel.h"
#import "DOModelDelegate.h"
#import "DOModelVC.h"

@interface DODetailModelVC : DOModelVC<DOModelDelegate>{
    
    UIView *    _contentView;
    BOOL _isValid;//是否更新视图---如果中途取消，即取消更新视图
}

@end









///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
//demo


//#import <UIKit/UIKit.h>
//#import "LLModelDelegate.h"
//#import "LLDetailModelVC.h"
//
//@interface TestVC : LLDetailModelVC<LLModelDelegate>{
//    
//    NSDictionary *_dataDic;
//}
//
//@property(nonatomic,retain)NSDictionary *dataDic;
//
//-(IBAction)fffreloadAction:(id)sender;
//
//@end




///////////////////////////////////////////////////////////////////////////////////////////////////
//#import "TestVC.h"
//#import <LLMacro.h>
//#import "TestModel.h"
//#import <LLAdditions.h>
//
//@implementation TestVC
//
//@synthesize dataDic=_dataDic;
//
//-(UIView *)contentView{
//    if (!_contentView) {
//        _contentView = [[UIView alloc] initWithFrame:XYWH(0, 0, 320, 460)];
//        _contentView.backgroundColor = [UIColor greenColor];
//        
//        NSArray *aDataArray = (NSArray*)[_dataDic objectForKey:@"data"];
//        
//        NSDictionary *aDic = (NSDictionary*)[aDataArray objectAtIndex:0];
//        
//        UILabel *aLabel = [[[UILabel alloc] initWithFrame:XYWH(20, 60, 200, 20)] autorelease];
//        [aLabel setText:[aDic objectForKey:@"mobilename"] font:14 color1:RGB(255, 0, 0) color2:nil];
//        [_contentView addSubview:aLabel];
//        
//    }
//    
//    
//    return _contentView;
//}
//
//-(UIView *)loadingView{
//    if (!_loadingView) {
//        
//        _loadingView = [[UIView alloc] initWithFrame:XYWH(0, 0, 320, 460)];
//        _loadingView.backgroundColor = YELLOW_COLOR;
//        UILabel *aLabel = [[[UILabel alloc] initWithFrame:XYWH(20, 60, 200, 20)] autorelease];
//        [aLabel setText:@"加载中...." font:14 color1:RGB(255, 0, 0) color2:nil];
//        [_loadingView addSubview:aLabel];
//    }
//    return _loadingView;
//}
//
//-(UIView *)emptyView{
//    if (!_emptyView) {
//        
//        _emptyView = [[UIView alloc] initWithFrame:XYWH(0, 0, 320, 460)];
//        _emptyView.backgroundColor = RED_COLOR;
//        UILabel *aLabel = [[[UILabel alloc] initWithFrame:XYWH(20, 60, 200, 20)] autorelease];
//        [aLabel setText:@"内容为空" font:14 color1:RGB(255, 0, 0) color2:nil];
//        [_emptyView addSubview:aLabel];
//    }
//    return _emptyView;
//}
//
//-(UIView *)errorView{
//    if (!_errorView) {
//        
//        _errorView = [[UIView alloc] initWithFrame:XYWH(0, 0, 320, 460)];
//        _errorView.backgroundColor = RED_COLOR;
//        UILabel *aLabel = [[[UILabel alloc] initWithFrame:XYWH(20, 60, 200, 20)] autorelease];
//        [aLabel setText:@"错误" font:14 color1:RGB(255, 0, 0) color2:nil];
//        [_errorView addSubview:aLabel];
//    }
//    return _errorView;
//}
//
//
///**
// * 创建model
// */
//- (void)createModel {
//    self.model = [[[TestModel alloc] init] autorelease];
//    self.model.delegate = self;
//    [(TestModel*)self.model startRequest];
//}
//
//
//
//#pragma mark -
//#pragma mark LLModelDelegate
//
//- (void)modelDidFinishLoad:(LLModel*)model{
//    self.dataDic = ((TestModel*)model).dataDic;
//    
//    [super modelDidFinishLoad:model];
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
//
//@end




///////////////////////////////////////////////////////////////////////////////////////////////////
//#import <Foundation/Foundation.h>
//#import "LLRequestJSONModel.h"
//
//@interface TestModel : LLRequestJSONModel{
//}
//-(void)startRequest;
//@end






///////////////////////////////////////////////////////////////////////////////////////////////////
//#import "TestModel.h"
//#import "LLMacro.h"
//#import <JSONKit.h>
//
//@implementation TestModel
//
//-(NSDictionary*)parserJSON:(NSData*)aNSData{
//    
//    NSMutableArray *aRetArray = [[[NSMutableArray alloc] init] autorelease];
//    
//    //DOLOGDATA(aNSData);
//    id a= [aNSData objectFromJSONData];
//    if (nil!=a && [a isKindOfClass:[NSDictionary class]]) {
//        
//        
//        if (nil!=[a objectForKey:@"data"]
//            && [[a objectForKey:@"data"] isKindOfClass:[NSArray class]]) {
//            NSArray *aArray = (NSArray*)[a objectForKey:@"data"];
//            for (int i=0; i<[aArray count]; i++) {
//                if ([[aArray objectAtIndex:i] isKindOfClass:[NSDictionary class]]) {
//                    [aRetArray addObject:[aArray objectAtIndex:i]];
//                }
//            }
//        }
//    }
//    
//    NSDictionary *aRetDic = [NSDictionary dictionaryWithObjectsAndKeys:aRetArray,@"data", nil];
//    return aRetDic;
//}
//
//-(void)startRequest{
//    ASIHTTPRequest_DO *aASIHTTPRequest_DO = [ASIHTTPRequest_DO requestWithURL:[NSURL URLWithString:[@"http://cm.sg169.com:8080/sghome1/mobileArea!listMobileArea.action?isview=1" stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]]];
//    
//    [aASIHTTPRequest_DO setDelegate:self];
//    [aASIHTTPRequest_DO startAsynchronous];
//}
//
//@end






