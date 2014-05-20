//
//  MyOrgInfoVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-14.
//
//

#import "BaseVC.h"
#import "BaseModel.h"

@interface MyOrgInfoVC : BaseVC<UITextFieldDelegate, UIScrollViewDelegate, UIActionSheetDelegate, UIImagePickerControllerDelegate, UINavigationControllerDelegate, DOModelDelegate>
{
    BOOL isEdit;
    int m_i_choice_photo_type;
}

@property (retain, nonatomic) IBOutlet UIScrollView *myScrollView;
@property (retain, nonatomic) IBOutlet UILabel *personNameLabel;
@property (retain, nonatomic) IBOutlet UITextField *userNameTxt;
@property (retain, nonatomic) IBOutlet UITextField *telephoneTxt;
@property (retain, nonatomic) IBOutlet UITextField *positionTxt;
@property (retain, nonatomic) IBOutlet UITextField *attendPartyTimeTxt;
@property (retain, nonatomic) IBOutlet UITextField *partyAgeTxt;
@property (retain, nonatomic) IBOutlet UITextField *partyBranchTxt;
@property (retain, nonatomic) IBOutlet UIButton *userImageBtn;
@property (retain, nonatomic) IBOutlet UIButton *photoBtn;

@property (retain, nonatomic) NSString *base64Image;
@property (retain, nonatomic) NSDictionary *dataDic;
@property (retain, nonatomic) BaseModel *saveUserInfoModel;

- (IBAction)uploadPhotoAction:(id)sender;
- (IBAction)keyBoardDisappear:(id)sender;

@end
