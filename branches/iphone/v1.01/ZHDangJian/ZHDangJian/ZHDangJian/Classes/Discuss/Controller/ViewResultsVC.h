//
//  ViewResultsVC.h
//  ZHDangJian
//
//  Created by do1 on 13-11-13.
//
//

#import "BaseVC.h"
#import "BaseTableView.h"
#import "BaseModel.h"

@interface ViewResultsVC : BaseVC<UITableViewDataSource, UITableViewDelegate, BaseTableViewDelegate, DOModelDelegate>

@property (retain, nonatomic) IBOutlet BaseTableView *tableView;
@property (retain, nonatomic) IBOutlet UILabel *topicLabel;
@property (retain, nonatomic) IBOutlet UILabel *attendNumLabel;

@property (retain, nonatomic) NSString *topicId;
@property (retain, nonatomic) BaseModel *excellentMemListModel;

@end
