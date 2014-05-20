//
//  NewsSubVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-12.
//
//

#import "BaseVC.h"
#import "BaseTableView.h"

@interface NewsSubVC : BaseVC<UITableViewDataSource, UITableViewDelegate, BaseTableViewDelegate, DOModelDelegate>

@property (retain, nonatomic) IBOutlet BaseTableView *tableView;

@property (retain, nonatomic) NSString *newsInfoType;
@property (retain, nonatomic) BaseModel *newsListModel;
@property (assign, nonatomic) int selectTableIndex;

@end
