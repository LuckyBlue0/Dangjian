//
//  DemocraticAppraisalDetailVC.h
//  ZHDangJian
//
//  Created by do1 on 13-11-12.
//
//

#import "BaseVC.h"

@interface DemocraticAppraisalDetailVC : BaseVC <UITextViewDelegate,UITextFieldDelegate,DOModelDelegate>

@property (retain, nonatomic) BaseModel *feedBackModel;
@property (retain, nonatomic) BaseModel *checkContentModel;
@property (retain, nonatomic) IBOutlet UIButton *discussButton1;
@property (retain, nonatomic) IBOutlet UIButton *discussButton2;
@property (retain, nonatomic) IBOutlet UIButton *discussButton3;
@property (retain, nonatomic) IBOutlet UIButton *discussButton4;
@property (retain, nonatomic) IBOutlet DOTextViewBar *myTextView;
@property (retain, nonatomic) NSString *voteSelection;
@property (retain, nonatomic) IBOutlet UILabel *titleLabel;
@property (retain, nonatomic) IBOutlet UILabel *discussDesLabel;
@property (retain, nonatomic) NSString *voteStatue;
@property (retain, nonatomic) NSString *activityID;
@property (retain, nonatomic) NSString *govName;

- (IBAction)buttonAction1:(UIButton *)sender;
- (IBAction)buttonAction2:(UIButton *)sender;
- (IBAction)buttonAction3:(UIButton *)sender;
- (IBAction)buttonAction4:(UIButton *)sender;
- (IBAction)closeKeyboardButton:(UIButton *)sender;

@end
