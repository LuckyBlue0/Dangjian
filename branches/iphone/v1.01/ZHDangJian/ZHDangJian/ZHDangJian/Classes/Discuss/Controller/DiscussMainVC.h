//
//  DiscussMainVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-11.
//
//

#import "BaseVC.h"

@interface DiscussMainVC : BaseVC
@property (retain, nonatomic) IBOutlet UIScrollView *myScrollView;
- (IBAction)PartyMembersReviewButton:(UIButton *)sender;
- (IBAction)democraticAppraisalButton:(UIButton *)sender;

@end
