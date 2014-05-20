//
//  LifestyleResultVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-14.
//
//

#import "BaseVC.h"
#import "BaseModel.h"

@interface LifestyleResultVC : BaseVC<DOModelDelegate>

@property (retain, nonatomic) IBOutlet UILabel *titleLabel;

@property (retain, nonatomic) NSString *resultStr;
@property (retain, nonatomic) NSString *titleString;
@property (retain, nonatomic) NSString *idString;
@property (retain, nonatomic) BaseModel *signModel;

- (IBAction)signAction:(id)sender;
- (IBAction)resweepAction:(id)sender;

@end
