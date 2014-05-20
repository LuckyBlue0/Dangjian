//
//  LifestyleSatueVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-14.
//
//

#import "BaseVC.h"
#import "BaseModel.h"

@interface LifestyleSatueVC : BaseVC<DOModelDelegate>

@property (retain, nonatomic) IBOutlet UILabel *noMeetingLabel;
@property (retain, nonatomic) IBOutlet UIView *signView;
@property (retain, nonatomic) IBOutlet UILabel *titleLabel;
@property (retain, nonatomic) IBOutlet UIButton *signNumBtn;
@property (retain, nonatomic) IBOutlet UILabel *haveSignLabel;
@property (retain, nonatomic) IBOutlet UIButton *bottomBtn;
@property (retain, nonatomic) IBOutlet UIView *experienceView;

@property (retain, nonatomic) NSDictionary *dataDic;
@property (retain, nonatomic) BaseModel *lifestyleDetailModel;
@property (retain, nonatomic) NSMutableDictionary *detailDataDic;

- (IBAction)checkMeetingDeatilAction:(id)sender;
- (IBAction)bottomBtnAction:(id)sender;
- (IBAction)checkExperienceAction:(id)sender;

@end
