//
//  VoteVC.m
//  ZHDangJian
//
//  Created by do1 on 13-11-13.
//
//

#import "VoteVC.h"
#import "CandidatesInformationVC.h"
#import "ViewResultsVC.h"

@interface VoteVC ()

@end

@implementation VoteVC

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
    
    _dataArray = [[NSMutableArray alloc] init];
    _selectedArray = [[NSMutableArray alloc] init];
    
    voteNum = 0;
    
    self.nameNumLabel.text = [NSString stringWithFormat:@"候选成员名单（%i人）", self.dataArray.count];
    if (1) {
        NSRange numRange = NSMakeRange(7, [[NSString stringWithFormat:@"%i",self.dataArray.count] length]);
        self.nameNumLabel.textColor = RGB(56, 56, 56);
        NSMutableDictionary *d = [[NSMutableDictionary alloc] init];
        [d setObject:RGB(0xc9, 0x01, 0x00) forKey:[NSValue valueWithRange:numRange]];
        self.nameNumLabel.attributedDic = d;
        [d release];
    }
    
    self.voteNumLabel.text = [NSString stringWithFormat:@"剩余投票名额：%i人", 0];
    if (1) {
        NSRange numRange = NSMakeRange(7, 1);
        self.voteNumLabel.textColor = RGB(56, 56, 56);
        NSMutableDictionary *d = [[NSMutableDictionary alloc] init];
        [d setObject:RGB(0xc9, 0x01, 0x00) forKey:[NSValue valueWithRange:numRange]];
        self.voteNumLabel.attributedDic = d;
        [d release];
    }
    
    self.titleLabel.text = [self.dataDic objectForKey:@"voteTopic"];
    
    [self showBack];
    [self creatPartyMemListModel];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)viewDidUnload
{
    [self setTitleLabel:nil];
    [self setNameNumLabel:nil];
    [self setMyScrollView:nil];
    [self setNameBackView:nil];
    [self setVoteNumLabel:nil];
    [super viewDidUnload];
}

- (void)dealloc
{
    [_dataDic release];
    [_titleLabel release];
    [_nameNumLabel release];
    [_myScrollView release];
    [_nameBackView release];
    _partyMemListModel.delegate = nil;
    DO_RELEASE_SAFELY(_partyMemListModel);
    [_dataArray release];
    [_selectedArray release];
    [_voteNumLabel release];
    [super dealloc];
}


#pragma mark - Layout

- (void)showLayout
{
    for (UIView *vw in self.nameBackView.subviews) {
        [vw removeFromSuperview];
    }
    for (int i = 0; i < self.dataArray.count; i ++) {
        UIView *backView = [[UIView alloc] initWithFrame:XYWH(105*(i%3), 15+125*(i/3), 90, 110)];
        backView.tag = 100+i;
        backView.backgroundColor = CLEAR_COLOR;
        [self.nameBackView addSubview:backView];
        
        UIImageView *aImageView = [[UIImageView alloc] initWithFrame:XYWH(1, 2, 88, 108)];
        aImageView.image = [UIImage imageNamed:@"discuss_vote图片背景@2x.png"];
        [backView addSubview:aImageView];
        [aImageView release];
        
        DONetworkImageView *personImage = [[DONetworkImageView alloc] initWithFrame:XYWH(2, 2, 86, 86)];
        personImage.backgroundColor = CLEAR_COLOR;
        personImage.defaultImage = [UIImage imageNamed:@"discuss_default头像@2x.png"];
        [personImage setURLPath:[NSString stringWithFormat:@"%@%@", KImageURL, [[self.dataArray objectAtIndex:i] objectForKey:@"userImgPath"]]];
        [backView addSubview:personImage];
        personImage.layer.masksToBounds = YES;
        [personImage setImageContentMode:UIViewContentModeScaleToFill];
        [personImage release];
        
        UIImageView *haveVoteImage = [[UIImageView alloc] initWithFrame:XYWH(44, 56, 42, 30)];
        haveVoteImage.image = [UIImage imageNamed:@"discuss_haveVote@2x.png"];
        [backView addSubview:haveVoteImage];
        if ([[[self.dataArray objectAtIndex:i] objectForKey:@"isVote"] intValue]) {
            haveVoteImage.hidden = NO;
        } else {
            haveVoteImage.hidden = YES;
        }
        
        
        UIButton *voteButton = [[UIButton alloc] initWithFrame:XYWH(0, 0, 90, 88)];
        voteButton.alpha = 0.5;
        voteButton.tag = 1000+i;
        voteButton.backgroundColor = CLEAR_COLOR;
        [voteButton setBackgroundImage:[UIImage imageNamed:@"discuss_选中@2x.png"] forState:UIControlStateSelected];
        [backView addSubview:voteButton];
        [voteButton setTarget:self action:@selector(voteButtonAction:)];
        if ([[self.dataDic objectForKey:@"voteStatus"] intValue] == 2) {
            voteButton.userInteractionEnabled = NO;
        } else if ([[self.dataDic objectForKey:@"voteStatus"] intValue] == 1){
            if (canVoteCount == 0) {
                voteButton.userInteractionEnabled = NO;
            } else {
                if ([[[self.dataArray objectAtIndex:i] objectForKey:@"isVote"] intValue]) {
                    voteButton.userInteractionEnabled = NO;
                } else {
                    voteButton.userInteractionEnabled = YES;
                }
            }
        }
        
        [voteButton release];
        [haveVoteImage release];
        
        UILabel *nameLabel = [[UILabel alloc] initWithFrame:XYWH(2, 86, 86, 24)];
        nameLabel.text = [[self.dataArray objectAtIndex:i] objectForKey:@"userName"];
        nameLabel.textAlignment = UITextAlignmentCenter;
        nameLabel.textColor = RGB(68, 68, 68);
        nameLabel.font = FONT(13);
        nameLabel.backgroundColor = CLEAR_COLOR;
        [backView addSubview:nameLabel];
        [nameLabel release];
        
        UIButton *personDetailBtn = [[UIButton alloc] initWithFrame:XYWH(1, 86, 88, 24)];
        personDetailBtn.tag = 10000+i;
        personDetailBtn.backgroundColor = CLEAR_COLOR;
        [personDetailBtn setTarget:self action:@selector(personDetailBtnAction:)];
        [backView addSubview:personDetailBtn];
        [personDetailBtn release];
        
        [backView release];
    }
    
    if (self.dataArray.count%3 == 0) {
        self.nameBackView.size = CGSizeMake(self.nameBackView.size.width, self.dataArray.count/3*(110+15));
        self.myScrollView.contentSize = CGSizeMake(self.myScrollView.size.width, self.nameBackView.origin.y+self.nameBackView.size.height+20);
        if (self.myScrollView.contentSize.height > self.view.size.height) {
            self.myScrollView.scrollEnabled = YES;
        } else {
            self.myScrollView.scrollEnabled = NO;
        }
    } else {
        self.nameBackView.size = CGSizeMake(self.nameBackView.size.width, (self.dataArray.count/3+1)*(110+15));
        self.myScrollView.contentSize = CGSizeMake(self.myScrollView.size.width, self.nameBackView.origin.y+self.nameBackView.size.height+20);
        if (self.myScrollView.contentSize.height > self.view.size.height) {
            self.myScrollView.scrollEnabled = YES;
        } else {
            self.myScrollView.scrollEnabled = NO;
        }

    }
    
    self.nameNumLabel.text = [NSString stringWithFormat:@"候选成员名单（%i人）", self.dataArray.count];
    if (1) {
        NSRange numRange = NSMakeRange(7, [[NSString stringWithFormat:@"%i",self.dataArray.count] length]);
        self.nameNumLabel.textColor = RGB(56, 56, 56);
        NSMutableDictionary *d = [[NSMutableDictionary alloc] init];
        [d setObject:RGB(0xc9, 0x01, 0x00) forKey:[NSValue valueWithRange:numRange]];
        self.nameNumLabel.attributedDic = d;
        [d release];
    }
    
    self.voteNumLabel.text = [NSString stringWithFormat:@"剩余投票名额：%i人", canVoteCount];
    if (1) {
        NSRange numRange = NSMakeRange(7, [[NSString stringWithFormat:@"%i",canVoteCount] length]);
        self.voteNumLabel.textColor = RGB(56, 56, 56);
        NSMutableDictionary *d = [[NSMutableDictionary alloc] init];
        [d setObject:RGB(0xc9, 0x01, 0x00) forKey:[NSValue valueWithRange:numRange]];
        self.voteNumLabel.attributedDic = d;
        [d release];
    }
    
    if ([[self.dataDic objectForKey:@"voteStatus"] intValue] == 2 || canVoteCount == 0) {
        [self showNavBtn:72 target:self action:@selector(checkResultAction:) title:@"查看结果"];
    } else if ([[self.dataDic objectForKey:@"voteStatus"] intValue] == 1 || canVoteCount > 0){
        [self showNavBtn:45 target:self action:@selector(voteAction:) title:@"投票"];
    }
    
}



#pragma mark - Action

- (void)mostNumAttention
{
    [UIAlertView showTip:[NSString stringWithFormat:@"最多可投%i个党员", canVoteCount]];
}

- (void)voteButtonAction:(id)sender
{
    UIButton *btn = (UIButton *)sender;
    for (UIView *vw in self.nameBackView.subviews) {
        UIView *backView = (UIView *)vw;
        if (backView.tag >= 100 && backView.tag < 1000) {
            for (id tt in backView.subviews) {
                if ([tt isKindOfClass:[UIButton class]]) {
                    UIButton *voteButton = (UIButton *)tt;
                    if (voteButton.tag == btn.tag) {
                        
                        if (voteNum >= canVoteCount) {
                            if (voteButton.selected) {
                                voteButton.selected = !voteButton.selected;
                            } else {
                                [self mostNumAttention];
                                return;
                            }
                        } else {
                            voteButton.selected = !voteButton.selected;
                        }
                        
                        if (!voteButton.selected) {
                            voteNum--;
                        } else {
                            voteNum++;
                        }
                        
                    } else {
                        voteButton.selected = voteButton.selected;
                    }
                    
                }
            }
        }
    }
}

- (void)personDetailBtnAction:(id)sender
{
    UIButton *btn = (UIButton *)sender;
    self.detailSelectedIndex = btn.tag - 10000;
    [self performSegueWithIdentifier:@"CandidatesInformationVCID" sender:nil];
}

- (IBAction)voteAction:(id)sender {
    
    for (UIView *vw in self.nameBackView.subviews) {
        UIView *backView = (UIView *)vw;
        if (backView.tag >= 100 && backView.tag < 1000) {
            for (id tt in backView.subviews) {
                if ([tt isKindOfClass:[UIButton class]]) {
                    UIButton *voteButton = (UIButton *)tt;
                    if (voteButton.selected) {
                        [self.selectedArray addObject:[DES3Util enDES3:[[self.dataArray objectAtIndex:voteButton.tag-1000] objectForKey:@"userId"]]];
                    }
                }
            }
        }
    }
    
    if (!self.selectedArray.count) {
        [UIAlertView showTip:@"请选择投票人"];
        return;
    }
    [self creatVoteModel];
}

- (IBAction)checkResultAction:(id)sender {
    
    [self performSegueWithIdentifier:@"ViewResultsVCID" sender:nil];
}


#pragma mark - Model

- (void)creatPartyMemListModel
{
    if (_partyMemListModel) {
        _partyMemListModel.delegate = nil;
        DO_RELEASE_SAFELY(_partyMemListModel);
    }
    _partyMemListModel = [[BaseModel alloc] init];
    _partyMemListModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:[self.dataDic objectForKey:@"id"]] forKey:@"id"];
    [aDic setObject:[DES3Util enDES3:[UserInfo sharedInstance].userId] forKey:@"voteUserId"];
    [aDic setObject:[[NSString stringWithFormat:@"%@%@", [self.dataDic objectForKey:@"id"], [UserInfo sharedInstance].userId] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KExcellentPartyMemListUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_partyMemListModel startRequest:requestUrl];
}

- (void)creatVoteModel
{
    if (_voteModel) {
        _voteModel.delegate = nil;
        DO_RELEASE_SAFELY(_voteModel);
    }
    _voteModel = [[BaseModel alloc] init];
    _voteModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:[UserInfo sharedInstance].userId] forKey:@"voteUserId"];
    [aDic setObject:[DES3Util enDES3:[self.dataDic objectForKey:@"id"]] forKey:@"id"];
    [aDic setObject:self.selectedArray forKey:@"userIds"];
    [aDic setObject:[[NSString stringWithFormat:@"%@%@%i", [UserInfo sharedInstance].userId, [self.dataDic objectForKey:@"id"], self.selectedArray.count] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KPartyMemVoteUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_voteModel startRequest:requestUrl];
}

- (void)modelDidStartLoad:(DOModel *)model
{
    [self initHUBTitle:nil subTitle:nil];
}

- (void)modelDidFinishLoad:(DOModel *)model
{
    [self removeHub];
    RequstResult *aRR = [((BaseModel *)model).dataDic objectForKey:@"RR"];
    if (model == self.partyMemListModel) {
        if (aRR.code) {
            
            canVoteCount = [[aRR.dataDic objectForKey:@"toDoCount"] intValue];
            
            if ([[aRR.dataDic objectForKey:@"pageData"] isKindOfClass:[NSArray class]]) {
                self.dataArray = [NSMutableArray arrayWithArray:[aRR.dataDic objectForKey:@"pageData"]];
                [self showLayout];
            }
            
            return;
        }
        [UIAlertView showTip:aRR.desc];
        return;
    }
    if (model == self.voteModel) {
        if (aRR.code) {
            [self creatPartyMemListModel];
            return;
        }
        [UIAlertView showTip:aRR.desc];
        return;
    }
}

- (void)model:(DOModel *)model didFailLoadWithError:(NSError *)error
{
    [self removeHub];
    [UIAlertView showTip:NetWorkFaild];
}


#pragma mark - Segue

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([segue.identifier isEqualToString:@"CandidatesInformationVCID"]) {
        CandidatesInformationVC *vc = (CandidatesInformationVC *)segue.destinationViewController;
        vc.topicId = [self.dataDic objectForKey:@"id"];
        vc.partyMemId = [[self.dataArray objectAtIndex:self.detailSelectedIndex] objectForKey:@"userId"];
    }
    if ([segue.identifier isEqualToString:@"ViewResultsVCID"]) {
        ViewResultsVC *vc = (ViewResultsVC *)segue.destinationViewController;
        vc.topicId = [self.dataDic objectForKey:@"id"];
    }
}

@end
