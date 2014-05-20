//
//  PartyMembersReviewVC.h
//  ZHDangJian
//
//  Created by do1 on 13-11-12.
//
//

#import "BaseVC.h"
#import "BaseTableView.h"
#import "BaseModel.h"

@interface PartyMembersReviewVC : BaseVC<UITableViewDataSource, UITableViewDelegate, BaseTableViewDelegate, UISearchBarDelegate, DOModelDelegate>

@property (retain, nonatomic) IBOutlet UISearchBar *mySeachBar;
@property (retain, nonatomic) IBOutlet BaseTableView *myBaseTableView;

@property (retain, nonatomic) NSString *searchKeyWorld;
@property (retain, nonatomic) BaseModel *partyMemberModel;
@property (assign, nonatomic) int selectedTableIndex;

- (IBAction)closeKeyboard:(UIButton *)sender;

@end
