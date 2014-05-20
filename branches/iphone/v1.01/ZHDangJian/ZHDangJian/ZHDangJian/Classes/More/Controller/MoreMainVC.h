//
//  MoreMainVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-11.
//
//

#import "BaseVC.h"

@interface MoreMainVC : BaseVC
@property (retain, nonatomic) IBOutlet UILabel *versionLabel;
- (IBAction)welcomePageButton:(UIButton *)sender;
- (IBAction)aboutUsButton:(UIButton *)sender;
- (IBAction)userFeedbackButton:(UIButton *)sender;
- (IBAction)cacheCacheButton:(UIButton *)sender;

@end
