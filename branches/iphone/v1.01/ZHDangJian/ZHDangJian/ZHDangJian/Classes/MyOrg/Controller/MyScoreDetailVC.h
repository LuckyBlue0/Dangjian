//
//  MyScoreDetailVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-12.
//
//

#import "BaseVC.h"
#import "MutilColorLable.h"
#import "BaseTableView.h"
#import "BaseModel.h"

@interface MyScoreDetailVC : BaseVC<UITableViewDataSource, UITableViewDelegate, BaseTableViewDelegate, DOModelDelegate>

@property (retain, nonatomic) IBOutlet MutilColorLable *myScoreLabel;
@property (retain, nonatomic) IBOutlet BaseTableView *tableView;

@property (retain, nonatomic) BaseModel *scoreDetailModel;
@property (retain, nonatomic) NSString *totalScoreString;

@end
