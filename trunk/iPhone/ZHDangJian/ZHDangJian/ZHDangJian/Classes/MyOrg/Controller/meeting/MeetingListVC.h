//
//  MeetingListVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-13.
//
//

#import "BaseVC.h"
#import "BaseTableView.h"
#import "BaseModel.h"

@interface MeetingListVC : BaseVC<UISearchBarDelegate, UITableViewDataSource, UITableViewDelegate, BaseTableViewDelegate, DOModelDelegate>

@property (retain, nonatomic) IBOutlet UISearchBar *mySearchBar;
@property (retain, nonatomic) IBOutlet BaseTableView *tableView;
@property (retain, nonatomic) IBOutlet UIView *popView;
@property (retain, nonatomic) IBOutlet UIButton *needTodoBtn;

@property (retain, nonatomic) BaseModel *meetingListModel;
@property (retain, nonatomic) NSString *searchKeyWorld;
@property (retain, nonatomic) NSString *typeString;
@property (retain, nonatomic) NSString *statueString;
@property (assign, nonatomic) int selectedTableIndex;

- (IBAction)needTodoAction:(id)sender;
- (IBAction)haveDoneAction:(id)sender;
- (IBAction)filterAction:(id)sender;


@end
