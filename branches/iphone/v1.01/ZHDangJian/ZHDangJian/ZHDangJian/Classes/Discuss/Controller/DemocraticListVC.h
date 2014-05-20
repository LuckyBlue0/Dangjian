//
//  DemocraticListVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-12-27.
//
//

#import "BaseVC.h"
#import "BaseTableView.h"

@interface DemocraticListVC : BaseVC<DOModelDelegate, UITableViewDataSource, UITableViewDelegate, BaseTableViewDelegate>
{
    int selectIndex;
}

@property (retain, nonatomic) IBOutlet UIButton *totalButton;
@property (retain, nonatomic) IBOutlet UIButton *haveVoteButton;
@property (retain, nonatomic) IBOutlet UIButton *notVoteButton;
@property (retain, nonatomic) IBOutlet BaseTableView *tableView;

@property (retain, nonatomic) NSString *activityID;
@property (retain, nonatomic) NSString *haveVoteNum;
@property (retain, nonatomic) NSString *notVoteNum;
@property (retain, nonatomic) NSString *voteStatue;
@property (retain, nonatomic) BaseModel *listModel;

- (IBAction)statueButtonAction:(id)sender;

@end
