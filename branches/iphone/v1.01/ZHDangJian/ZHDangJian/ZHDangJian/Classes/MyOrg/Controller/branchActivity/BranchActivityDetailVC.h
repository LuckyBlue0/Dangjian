//
//  BranchActivityDetailVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-13.
//
//

#import "BaseVC.h"

@interface BranchActivityDetailVC : BaseVC

@property (retain, nonatomic) IBOutlet UIScrollView *myScrollView;
@property (retain, nonatomic) IBOutlet UIImageView *backImageView;
@property (retain, nonatomic) IBOutlet UILabel *titleLabel;
@property (retain, nonatomic) IBOutlet UIView *topView;
@property (retain, nonatomic) IBOutlet UIView *activityDetailView;
@property (retain, nonatomic) IBOutlet UILabel *contentLabel;
@property (retain, nonatomic) IBOutlet UILabel *typeLabel;
@property (retain, nonatomic) IBOutlet UILabel *personLabel;
@property (retain, nonatomic) IBOutlet UILabel *startLabel;
@property (retain, nonatomic) IBOutlet UILabel *endLabel;
@property (retain, nonatomic) IBOutlet UILabel *addressLabel;

@property (retain, nonatomic) NSDictionary *dataDic;

@end
