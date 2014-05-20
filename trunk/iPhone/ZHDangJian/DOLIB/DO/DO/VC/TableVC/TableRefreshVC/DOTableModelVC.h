/**
 * table VC
 */

#import <Foundation/Foundation.h>
#import "DOTableViewDelegate.h"
#import "DOTableViewDataSource.h"
#import "DOModelVC.h"
#import "DOTableItem.h"
#import "DOTableViewCell.h"

@protocol DOTableViewDelegate;
@protocol DOTableViewDataSource;

@interface DOTableModelVC : DOModelVC{
    
    UITableView *   _tableView;
    
    id<DOTableViewDataSource> _dataSource;
    id<DOTableViewDelegate>   _tableDelegate;
    
}

@property (nonatomic, retain) UITableView* tableView;

@property (nonatomic, retain) id<DOTableViewDataSource> dataSource;

-(void)releaseSafeError;

@end



 
// 
// 
// 
// #import "TestVC.h"
// #import <LLMacro.h>
// #import "TestModel.h"
// #import <LLAdditions.h>
// 
// static NSString *const KUrlFormat = @"http://120.196.125.11:26146/szproj/newoutface!excuteShopCenter.action?pageNo=%d&pageCount=%d";
// //static NSString *const KUrlFormat = @"http://1cm.sg169.com:8080/sghome1/mobileArea!listMobileArea.action?isview=%d%d";
// 
// //http://cm.sg169.com:8080/sghome1/mobileArea!listMobileArea.action?isview=3
// 
// @implementation TestVC
// 
// 
// -(UIView *)loadingView{
// if (!_loadingView) {
// 
// _loadingView = [[UIView alloc] initWithFrame:XYWH(0, 0, 320, 460)];
// _loadingView.backgroundColor = YELLOW_COLOR;
// UILabel *aLabel = [[[UILabel alloc] initWithFrame:XYWH(20, 60, 200, 20)] autorelease];
// [aLabel setText:@"加载中...." font:14 color1:RGB(255, 0, 0) color2:nil];
// [_loadingView addSubview:aLabel];
// }
// return _loadingView;
// }
// -(UIView *)emptyView{
// if (!_emptyView) {
// 
// _emptyView = [[UIView alloc] initWithFrame:XYWH(0, 0, 320, 460)];
// _emptyView.backgroundColor = RED_COLOR;
// UILabel *aLabel = [[[UILabel alloc] initWithFrame:XYWH(20, 60, 200, 20)] autorelease];
// [aLabel setText:@"内容为空" font:14 color1:RGB(255, 255, 255) color2:nil];
// [_emptyView addSubview:aLabel];
// }
// return _emptyView;
// }
// 
// -(UIView *)errorView{
// if (!_errorView) {
// 
// _errorView = [[UIView alloc] initWithFrame:XYWH(0, 0, 320, 460)];
// _errorView.backgroundColor = RED_COLOR;
// UILabel *aLabel = [[[UILabel alloc] initWithFrame:XYWH(20, 60, 200, 20)] autorelease];
// [aLabel setText:@"错误" font:14 color1:RGB(255, 255, 255) color2:nil];
// [_errorView addSubview:aLabel];
// }
// return _errorView;
// }
// 
// -(void)createDataSource{
// self.dataSource = [[[LLTableViewDataSource alloc] initWithController:self withQueryFormat:KUrlFormat withNumPerPage:10]autorelease];
// }
// - (id<LLTableViewDelegate>)createDelegate {
// return [[[LLTableViewDelegate alloc] initWithController:self withHeader:YES withFooter:YES] autorelease];
// }
// -(void)didSelectRowAtIndexPath:(NSIndexPath *)indexPath withDataDic:(NSDictionary*)aDic{
// NSLog(@"aDic=%@",aDic);
// }
//
// @end
