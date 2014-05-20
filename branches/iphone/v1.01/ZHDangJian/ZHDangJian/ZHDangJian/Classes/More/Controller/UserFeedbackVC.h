//
//  UserFeedbackVC.h
//  ZHDangJian
//
//  Created by do1 on 13-11-12.
//
//

#import "BaseVC.h"

@interface UserFeedbackVC : BaseVC <UITextFieldDelegate,UITextViewDelegate,DOModelDelegate>
{
    int remainingWords;
}
@property (retain, nonatomic) BaseModel *feedBackModel;
@property (retain, nonatomic) IBOutlet DOTextViewBar *suggestTextView;
@property (retain, nonatomic) IBOutlet UITextField *telephoneTextField;
@property (retain, nonatomic) IBOutlet UILabel *remainingWordsLabel;
- (IBAction)emptyButton:(UIButton *)sender;
- (IBAction)closeKeyboardButton:(UIButton *)sender;


@end
