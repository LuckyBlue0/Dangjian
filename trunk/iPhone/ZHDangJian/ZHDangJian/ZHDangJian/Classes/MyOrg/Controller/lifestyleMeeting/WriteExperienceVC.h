//
//  WriteExperienceVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-14.
//
//

#import "BaseVC.h"
#import "BaseModel.h"

@interface WriteExperienceVC : BaseVC<DOModelDelegate, UITextViewDelegate>

@property (retain, nonatomic) IBOutlet UILabel *titleLabel;
@property (retain, nonatomic) IBOutlet DOTextViewBar *textViewBar;

@property (retain, nonatomic) NSString *activtyId;
@property (retain, nonatomic) BaseModel *submitModel;
@property (retain, nonatomic) NSString *titleString;

- (IBAction)keyBoardDisappear:(id)sender;

@end
