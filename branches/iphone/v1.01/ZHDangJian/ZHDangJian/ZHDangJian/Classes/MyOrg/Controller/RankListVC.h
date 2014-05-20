//
//  RankListVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-12.
//
//

#import "BaseVC.h"
#import "BaseTableView.h"
#import "BaseModel.h"

@interface RankListVC : BaseVC<UITableViewDataSource, UITableViewDelegate, BaseTableViewDelegate, DOModelDelegate>
{
    NSIndexPath *myIndexPath;
}

@property (retain, nonatomic) IBOutlet BaseTableView *tableView;
@property (retain, nonatomic) IBOutlet UIButton *ascOrderBtn;
@property (retain, nonatomic) IBOutlet UIButton *mineOrderBtn;
@property (retain, nonatomic) IBOutlet UIButton *desOrderBtn;

@property (retain, nonatomic) NSString *type;    // 1:个人积分排名   2:所在支部排名
@property (retain, nonatomic) NSString *orderType;   // 1:顺序  2:倒序  3:我的
@property (retain, nonatomic) BaseModel *rankListModel;
@property (retain, nonatomic) NSString *branchId;

- (IBAction)desOrderAction:(id)sender;
- (IBAction)mineOrderAction:(id)sender;
- (IBAction)ascOrderAction:(id)sender;


@end
