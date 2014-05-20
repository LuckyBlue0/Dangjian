//
//  LifestyleDetailVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-14.
//
//

#import "BaseVC.h"

@interface LifestyleDetailVC : BaseVC

@property (retain, nonatomic) IBOutlet UIScrollView *myScrollView;
@property (retain, nonatomic) IBOutlet UIImageView *backImageView;
@property (retain, nonatomic) IBOutlet UILabel *titleLabel;
@property (retain, nonatomic) IBOutlet UIView *topView;
@property (retain, nonatomic) IBOutlet UIView *activityDetailView;
@property (retain, nonatomic) IBOutlet UILabel *contentLabel;
@property (retain, nonatomic) IBOutlet UILabel *activityTypeLabel;
@property (retain, nonatomic) IBOutlet UILabel *sendPersonLabel;
@property (retain, nonatomic) IBOutlet UILabel *startTimeLabel;
@property (retain, nonatomic) IBOutlet UILabel *endTimeLabel;
@property (retain, nonatomic) IBOutlet UILabel *addressLabel;


@property (retain, nonatomic) NSDictionary *dataDic;

@end
