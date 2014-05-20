//
//  ModifyPwdVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-14.
//
//

#import "BaseVC.h"
#import "BaseModel.h"

@interface ModifyPwdVC : BaseVC<DOModelDelegate>

@property (retain, nonatomic) IBOutlet UITextField *oldPwdTxt;
@property (retain, nonatomic) IBOutlet UITextField *newPwdTxt;
@property (retain, nonatomic) IBOutlet UITextField *surePwdTxt;
@property (retain, nonatomic) IBOutlet UILabel *noticeLabel;

@property (retain, nonatomic) BaseModel *modifyPwdModel;

- (IBAction)keyBoardDisappear:(id)sender;

@end
