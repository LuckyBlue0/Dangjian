//
//  CandidatesInformationVC.h
//  ZHDangJian
//
//  Created by do1 on 13-11-13.
//
//

#import "BaseVC.h"
#import "BaseModel.h"

@interface CandidatesInformationVC : BaseVC<DOModelDelegate>

@property (retain, nonatomic) IBOutlet UIScrollView *myScrollView;
@property (retain, nonatomic) IBOutlet UIImageView *topImageView;
@property (retain, nonatomic) IBOutlet DONetworkImageView *headSculptureImageView;
@property (retain, nonatomic) IBOutlet UILabel *nameLabel;
@property (retain, nonatomic) IBOutlet UIView *advancedDeedsView;
@property (retain, nonatomic) IBOutlet UIView *advancedDeedsDetailView;
@property (retain, nonatomic) IBOutlet UIImageView *advancedDeedsDetailImageView;
@property (retain, nonatomic) IBOutlet UILabel *advancedDeedsDetailLabel;
@property (retain, nonatomic) IBOutlet UIView *PartyWorkView;
@property (retain, nonatomic) IBOutlet UIView *PartyWorkDetailView;
@property (retain, nonatomic) IBOutlet UIImageView *PartyWorkDetailImageView;
@property (retain, nonatomic) IBOutlet UILabel *PartyWorkDetailLabel;

@property (retain, nonatomic) NSString *topicId;
@property (retain, nonatomic) NSString *partyMemId;
@property (retain, nonatomic) BaseModel *partyMemInfoModel;
@property (retain, nonatomic) NSMutableDictionary *dataDic;

@end
