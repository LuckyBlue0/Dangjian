//
//  MyOrgMainVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-11.
//
//

#import "BaseVC.h"
#import "BaseModel.h"
#import "BaseHeaderView.h"

@interface MyOrgMainVC : BaseVC<UIAlertViewDelegate, DOModelDelegate, UIScrollViewDelegate, BaseHeaderDelegate>
{
    BaseHeaderView  *_headerView;
    BOOL _isLoading;
}

@property (retain, nonatomic) IBOutlet UIScrollView *myScrollView;
@property (retain, nonatomic) IBOutlet DONetworkImageView *userImage;
@property (retain, nonatomic) IBOutlet UILabel *userNameLabel;
@property (retain, nonatomic) IBOutlet UIButton *myScoreBtn;
@property (retain, nonatomic) IBOutlet UIButton *totalRankBtn;
@property (retain, nonatomic) IBOutlet UIButton *branchRankBtn;
@property (retain, nonatomic) IBOutlet UIButton *meetingNumBtn;
@property (retain, nonatomic) IBOutlet UIButton *activeNumBtn;
@property (retain, nonatomic) IBOutlet UIButton *lifestyleNumBtn;

@property (retain, nonatomic) BaseModel *myUserInfoModel;
@property (retain, nonatomic) NSMutableDictionary *dataDic;
@property (retain, nonatomic) NSString *rangkListType;


- (IBAction)clickButton:(id)sender;


@end
