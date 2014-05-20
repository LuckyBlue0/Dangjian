//
//  DemocraticAppraisalVC.h
//  ZHDangJian
//
//  Created by do1 on 13-11-12.
//
//

#import "BaseVC.h"
#import "BaseTableView.h"
#import "BaseModel.h"
#import "MutilColorLable.h"

@interface DemocraticAppraisalVC : BaseVC <UITableViewDataSource,UITableViewDelegate,BaseTableViewDelegate,DOModelDelegate>

@property (retain, nonatomic) IBOutlet BaseTableView *myBaseTableView;

@property (retain, nonatomic) BaseModel *democraticAppraisalmodel;
@property (assign, nonatomic) int selectedTableIndex;

@end
