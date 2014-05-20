//
//  ExperienceListVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-14.
//
//

#import "BaseVC.h"
#import "BaseTableView.h"
#import "BaseModel.h"

@interface ExperienceListVC : BaseVC<UITableViewDataSource, UITableViewDelegate, BaseTableViewDelegate, DOModelDelegate>

@property (retain, nonatomic) IBOutlet BaseTableView *tableView;
@property (retain, nonatomic) NSString *idString;
@property (retain, nonatomic) BaseModel *experienceListModel;
@property (retain, nonatomic) NSString *titleString;

@end
