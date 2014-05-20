//
//  MeetingDetailVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-13.
//
//

#import "BaseVC.h"

@interface MeetingDetailVC : BaseVC

@property (retain, nonatomic) IBOutlet UIView *topView;
@property (retain, nonatomic) IBOutlet UIImageView *topBackImage;
@property (retain, nonatomic) IBOutlet UILabel *titleLabel;
@property (retain, nonatomic) IBOutlet UILabel *meetingTypeLabel;
@property (retain, nonatomic) IBOutlet UILabel *meetingPersonLabel;
@property (retain, nonatomic) IBOutlet UILabel *meetingStartTimeLabel;
@property (retain, nonatomic) IBOutlet UILabel *meetingEndTimeLabel;
@property (retain, nonatomic) IBOutlet UILabel *meetingAddressLabel;
@property (retain, nonatomic) IBOutlet UILabel *contentLabel;
@property (retain, nonatomic) IBOutlet UIView *detailView;
@property (retain, nonatomic) IBOutlet UIScrollView *myScrollView;

@property (retain, nonatomic) NSDictionary *dataDic;

@end
