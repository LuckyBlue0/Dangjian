//
//  MyOrgInfoVC.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-14.
//
//

#import "MyOrgInfoVC.h"

@interface MyOrgInfoVC ()

@end

@implementation MyOrgInfoVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view.
    
    [self showBack];
    [self showNavBtn:50 target:self action:@selector(editSaveAction:) title:@"编辑"];
    isEdit = NO;
    
    if ([[UIScreen mainScreen] bounds].size.height == 480) {
        [self.myScrollView setContentSize:CGSizeMake(320, 788-220)];
    } else if ([[UIScreen mainScreen] bounds].size.height == 568){
        [self.myScrollView setContentSize:CGSizeMake(320, 738-220)];
    }
    
    _base64Image = [[NSString alloc] initWithFormat:@""];
    
    [self performSelectorInBackground:@selector(setUserImage) withObject:nil];
    [self setshowData];
    [self canEdit];
    _userImageBtn.userInteractionEnabled = isEdit;

}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_myScrollView release];
    [_personNameLabel release];
    [_userNameTxt release];
    [_telephoneTxt release];
    [_positionTxt release];
    [_attendPartyTimeTxt release];
    [_partyAgeTxt release];
    [_partyBranchTxt release];
    [_userImageBtn release];
    [_photoBtn release];
    [_base64Image release];
    _saveUserInfoModel.delegate = nil;
    DO_RELEASE_SAFELY(_saveUserInfoModel);
    [super dealloc];
}
- (void)viewDidUnload {
    [self setMyScrollView:nil];
    [self setPersonNameLabel:nil];
    [self setUserNameTxt:nil];
    [self setTelephoneTxt:nil];
    [self setPositionTxt:nil];
    [self setAttendPartyTimeTxt:nil];
    [self setPartyAgeTxt:nil];
    [self setPartyBranchTxt:nil];
    [self setUserImageBtn:nil];
    [self setPhotoBtn:nil];
    [super viewDidUnload];
}


#pragma mark - Action

-(void)editSaveAction:(UIButton *)sender
{
    [self keyBoardDisappear:nil];
    
    sender.selected = !sender.selected;
    isEdit = sender.selected;
    [self canEdit];
    [self.userImageBtn setUserInteractionEnabled:isEdit];
    if (isEdit) {
        [sender setTitle:@"保存" forState:UIControlStateNormal];
        self.photoBtn.hidden = NO;
        self.userNameTxt.background = [UIImage imageNamed:@"personalInfo_输入框@2x.png"];
        self.telephoneTxt.background = [UIImage imageNamed:@"personalInfo_输入框@2x.png"];
    } else {
        [sender setTitle:@"编辑" forState:UIControlStateNormal];
        self.userNameTxt.background = [UIImage imageNamed:@""];
        self.telephoneTxt.background = [UIImage imageNamed:@""];
        _photoBtn.hidden = YES;
        if (![self.telephoneTxt.text validetePhoneNumber:YES]) {
            [UIAlertView showTip:@"手机号码格式不正确"];
            return;
        }
        [self creatSaveInfoModel];
    }
}

-(void)canEdit{
    _userNameTxt.enabled = isEdit;
    _telephoneTxt.enabled = isEdit;
}

- (IBAction)uploadPhotoAction:(id)sender {
    
    if (!isEdit) {
        return;
    }
    UIActionSheet *as = [[[UIActionSheet alloc] initWithTitle:@""
                                                     delegate:self
                                            cancelButtonTitle:@"取消"
                                       destructiveButtonTitle:@"拍照"
                                            otherButtonTitles:@"本地上传", nil] autorelease];
    [as showInView:self.view];
}

- (IBAction)keyBoardDisappear:(id)sender {
    
    UIButton *btn = (UIButton *)sender;
    [self.userNameTxt resignFirstResponder];
    [self.telephoneTxt resignFirstResponder];
    if (btn.tag != 888) {
        [self.myScrollView setContentOffset:CGPointMake(0, 0) animated:YES];
    }
}


#pragma mark - Data

-(void)setUserImage{
    UIImage *image = [UIImage imageWithData:[NSData dataWithContentsOfURL:[NSURL URLWithString:[NSString stringWithFormat:@"%@%@", KImageURL, [[self.dataDic objectForKey:@"partyMenberInfo"] objectForKey:@"portraitPic"]]]]];
    if (image) {
        [_userImageBtn setImage:image forState:UIControlStateNormal];
        _userImageBtn.layer.cornerRadius = 4;
        _userImageBtn.layer.masksToBounds = YES;
        NSData *data=UIImageJPEGRepresentation(image,0.05);
        self.base64Image = [data toBase64Encoding];
    } else {
        self.base64Image = @"";
    }
}

-(void)setshowData{
    
    self.personNameLabel.text = [[self.dataDic objectForKey:@"partyMenberInfo"] objectForKey:@"name"];
    self.userNameTxt.text = [[self.dataDic objectForKey:@"partyMenberInfo"] objectForKey:@"userName"];
    self.telephoneTxt.text = [[self.dataDic objectForKey:@"partyMenberInfo"] objectForKey:@"mobile"];
    self.positionTxt.text = [[self.dataDic objectForKey:@"partyMenberInfo"] objectForKey:@"position"];
    self.attendPartyTimeTxt.text = [[self.dataDic objectForKey:@"partyMenberInfo"] objectForKey:@"partyTime"];
    self.partyAgeTxt.text = [NSString stringWithFormat:@"%@", [[self.dataDic objectForKey:@"partyMenberInfo"] objectForKey:@"partyAge"]];
    self.partyBranchTxt.text = [[self.dataDic objectForKey:@"partyMenberInfo"] objectForKey:@"organizationName"];
    
}


#pragma mark- scrollView delegate methods
- (void)scrollViewWillBeginDragging:(UIScrollView *)scrollView{
    
    [self.userNameTxt resignFirstResponder];
    [self.telephoneTxt resignFirstResponder];
}


#pragma mark- textField delegate methods

- (void)textFieldDidBeginEditing:(UITextField *)textField{
    if ([[UIScreen mainScreen] bounds].size.height == 480) {
        [self.myScrollView setContentOffset:CGPointMake(0, (100-99)*44) animated:YES];
    } 
}


#pragma mark - ActionSheet delegate
- (void)actionSheet:(UIActionSheet *)actionSheet clickedButtonAtIndex:(NSInteger)buttonIndex{
    if (buttonIndex == 2)
        return;
    
    if (buttonIndex == 0
        && ![UIImagePickerController isSourceTypeAvailable: UIImagePickerControllerSourceTypeCamera])
    {
        [obj_message_box show_message:@"没有可用设备!" last:1.5];
        return;
    }
    
    m_i_choice_photo_type = buttonIndex;
    [self performSegueWithIdentifier:@"UpLoadImageID" sender:nil];
}


#pragma mark - Camera View Delegate Methods

- (void)imagePickerController:(UIImagePickerController *)picker
didFinishPickingMediaWithInfo:(NSDictionary *)info {
    [picker dismissModalViewControllerAnimated:YES];
    UIImage *image = [info valueForKey:UIImagePickerControllerEditedImage];
    
    if (image == nil)
    {
        return;
    }
    else {
        UIGraphicsBeginImageContext(CGSizeMake(320, 150));
        [image drawInRect:CGRectMake(0, 0,320, 150)];
        [_userImageBtn setImageEdgeInsets:UIEdgeInsetsMake(0, 0, 0, 0)];
        [_userImageBtn setImage:image forState:UIControlStateNormal];
        _userImageBtn.layer.cornerRadius = 4;
        _userImageBtn.layer.masksToBounds = YES;
        NSData *data=UIImageJPEGRepresentation(image,0.05);
        self.base64Image = [data toBase64Encoding];
        
    }
    
}

- (void)imagePickerControllerDidCancel:(UIImagePickerController *)picker {
    [picker dismissModalViewControllerAnimated:YES];
}



#pragma mark - model

-(void)creatSaveInfoModel{
    
    if (_saveUserInfoModel) {
        _saveUserInfoModel.delegate = nil;
        DO_RELEASE_SAFELY(_saveUserInfoModel);
    }
    _saveUserInfoModel = [[BaseModel alloc] init];
    _saveUserInfoModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:@"1"] forKey:@"userType"];
    [aDic setObject:[DES3Util enDES3:[UserInfo sharedInstance].userId] forKey:@"userId"];
    [aDic setObject:[DES3Util enDES3:self.userNameTxt.text] forKey:@"userName"];
    [aDic setObject:[DES3Util enDES3:self.base64Image] forKey:@"portraitPic"];
    [aDic setObject:[DES3Util enDES3:self.telephoneTxt.text] forKey:@"mobile"];
    [aDic setObject:[DES3Util enDES3:[[self.dataDic objectForKey:@"partyMenberInfo"] objectForKey:@"email"]] forKey:@"email"];
    [aDic setObject:[[NSString stringWithFormat:@"1%@%@%@%@%@", [UserInfo sharedInstance].userId, self.userNameTxt.text, self.base64Image, self.telephoneTxt.text, [[self.dataDic objectForKey:@"partyMenberInfo"] objectForKey:@"email"]] MD5] forKey:@"digest"];
    
//    NSMutableDictionary *bDic = [[[NSMutableDictionary alloc] init] autorelease];
//    [bDic setObject:aDic forKey:@"modifyUserInfo"];
    
    NSString *requestUrl = [NSString stringWithFormat:KEditPersonalInfoUrlFormat, KURL, [BaseVC buildJson:aDic]];
    
    [_saveUserInfoModel startRequest:requestUrl];
}

- (void)modelDidStartLoad:(DOModel*)model{
    
    [self initHUBTitle:@"正在保存" subTitle:@"请稍后..."];
}

- (void)modelDidFinishLoad:(DOModel*)model{
    [self removeHub];
    RequstResult *aRR = [((BaseModel*)model).dataDic objectForKey:@"RR"];
    
    if (model == self.saveUserInfoModel) {
        if (aRR.code) {
            [self.navigationController popViewControllerAnimated:YES];
            [[NSNotificationCenter defaultCenter] postNotificationName:@"myOrgInfChanged" object:nil userInfo:nil];
            return;
        }
        [UIAlertView showTip:aRR.desc];
        return;
    }
}

- (void)model:(DOModel*)model didFailLoadWithError:(NSError*)error{
    [self removeHub];
    [UIAlertView showTip:NetWorkFaild];
    
}


- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if([@"UpLoadImageID" isEqualToString:segue.identifier])
    {
        UIImagePickerController *vc = (UIImagePickerController *)segue.destinationViewController;
        vc.delegate = self;
        vc.allowsEditing = true;
        if (m_i_choice_photo_type == 0)
            vc.sourceType = UIImagePickerControllerSourceTypeCamera;
        else
            vc.sourceType = UIImagePickerControllerSourceTypePhotoLibrary;
    }
}




@end
