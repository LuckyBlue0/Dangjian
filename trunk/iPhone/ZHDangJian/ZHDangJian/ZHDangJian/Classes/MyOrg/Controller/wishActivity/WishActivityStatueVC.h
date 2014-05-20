//
//  WishActivityStatueVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-14.
//
//

#import "BaseVC.h"
#import "BaseModel.h"

@interface WishActivityStatueVC : BaseVC<DOModelDelegate>

@property (retain, nonatomic) IBOutlet UILabel *titleLabel;
@property (retain, nonatomic) IBOutlet UIButton *signNumBtn;
@property (retain, nonatomic) IBOutlet UIButton *signBtn;

@property (retain, nonatomic) NSDictionary *dataDic;
@property (retain, nonatomic) NSMutableDictionary *detailDataDic;
@property (retain, nonatomic) BaseModel *wishDetailModel;
@property (retain, nonatomic) BaseModel *wishSignModel;

- (IBAction)checkMeetingDeatilAction:(id)sender;
- (IBAction)bottomBtnAction:(id)sender;
- (IBAction)checkWishAction:(id)sender;

@end
