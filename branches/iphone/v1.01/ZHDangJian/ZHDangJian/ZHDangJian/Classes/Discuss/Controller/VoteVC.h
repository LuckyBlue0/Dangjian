//
//  VoteVC.h
//  ZHDangJian
//
//  Created by do1 on 13-11-13.
//
//

#import "BaseVC.h"
#import "MutilColorLable.h"
#import "BaseModel.h"

@interface VoteVC : BaseVC<DOModelDelegate>
{
    int voteNum;
    int canVoteCount;
}

@property (retain, nonatomic) IBOutlet UILabel *titleLabel;
@property (retain, nonatomic) IBOutlet MutilColorLable *nameNumLabel;
@property (retain, nonatomic) IBOutlet MutilColorLable *voteNumLabel;
@property (retain, nonatomic) IBOutlet UIScrollView *myScrollView;
@property (retain, nonatomic) IBOutlet UIView *nameBackView;

@property (retain, nonatomic) NSDictionary *dataDic;
@property (retain, nonatomic) BaseModel *partyMemListModel;
@property (retain, nonatomic) NSMutableArray *dataArray;
@property (assign, nonatomic) int detailSelectedIndex;
@property (retain, nonatomic) BaseModel *voteModel;
@property (retain, nonatomic) NSMutableArray *selectedArray;
@property (retain, nonatomic) UIButton *rightNavButton;

- (IBAction)voteAction:(id)sender;
- (IBAction)checkResultAction:(id)sender;

@end
