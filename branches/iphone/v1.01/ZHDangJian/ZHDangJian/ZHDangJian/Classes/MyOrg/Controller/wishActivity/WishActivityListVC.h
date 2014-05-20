//
//  WishActivityListVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-14.
//
//

#import "BaseVC.h"
#import "BaseTableView.h"
#import "BaseModel.h"

@interface WishActivityListVC : BaseVC<UISearchBarDelegate, UITableViewDataSource, UITableViewDelegate, BaseTableViewDelegate, DOModelDelegate>

@property (retain, nonatomic) IBOutlet UISearchBar *mySearchBar;
@property (retain, nonatomic) IBOutlet BaseTableView *tableView;

@property (retain, nonatomic) BaseModel *wishListModel;
@property (retain, nonatomic) NSString *searchKeyWorld;
@property (retain, nonatomic) NSString *statueString;
@property (assign, nonatomic) int selectedTableIndex;

- (IBAction)needTodoAction:(id)sender;
- (IBAction)haveDoneAction:(id)sender;

@end
