//
//  WishXinYuListVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-27.
//
//

#import "BaseVC.h"
#import "BaseTableView.h"
#import "BaseModel.h"

@interface WishXinYuListVC : BaseVC<DOModelDelegate, UITableViewDataSource,UITableViewDelegate, BaseTableViewDelegate>

@property (retain, nonatomic) IBOutlet BaseTableView *tableView;

@property (retain, nonatomic) NSString *idString;
@property (retain, nonatomic) NSString *titleString;
@property (retain, nonatomic) BaseModel *listModel;

@end
