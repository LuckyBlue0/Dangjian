//
//  BranchActivityStatueVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-13.
//
//

#import "BaseVC.h"
#import "BaseModel.h"

@interface BranchActivityStatueVC : BaseVC<DOModelDelegate>

@property (retain, nonatomic) IBOutlet UILabel *noMeetingLabel;
@property (retain, nonatomic) IBOutlet UIView *signView;

@property (retain, nonatomic) NSDictionary *dataDic;
@property (retain, nonatomic) BaseModel *branchDetailModel;
@property (retain, nonatomic) NSMutableDictionary *detailDataDic;
@property (retain, nonatomic) IBOutlet UILabel *titleLabel;
@property (retain, nonatomic) IBOutlet UIButton *signNumBtn;
@property (retain, nonatomic) IBOutlet UIButton *bottomButton;
@property (retain, nonatomic) IBOutlet UILabel *haveSignLabel;
@property (retain, nonatomic) BaseModel *leaveModel;

- (IBAction)checkMeetingDeatilAction:(id)sender;
- (IBAction)bottomBtnAction:(id)sender;

@end
