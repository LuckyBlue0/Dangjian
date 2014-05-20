//
//  CandidatesInformationVC.m
//  ZHDangJian
//
//  Created by do1 on 13-11-13.
//
//

#import "CandidatesInformationVC.h"

@interface CandidatesInformationVC ()

@end

@implementation CandidatesInformationVC

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
    
    _dataDic = [[NSMutableDictionary alloc] init];
    
    [self showBack];
    self.myScrollView.contentSize = CGSizeMake(320, 600);
    
    self.headSculptureImageView.image = [UIImage imageNamed:@""];
    self.headSculptureImageView.layer.cornerRadius = 6;
    self.headSculptureImageView.layer.masksToBounds = YES;
    [self.headSculptureImageView setImageContentMode:UIViewContentModeScaleToFill];
    
    [self creatPartyMemInfoModel];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_myScrollView release];
    [_topImageView release];
    [_headSculptureImageView release];
    [_nameLabel release];
    [_advancedDeedsView release];
    [_advancedDeedsDetailView release];
    [_advancedDeedsDetailImageView release];
    [_advancedDeedsDetailLabel release];
    [_PartyWorkView release];
    [_PartyWorkDetailView release];
    [_PartyWorkDetailImageView release];
    [_PartyWorkDetailLabel release];
    [_topicId release];
    [_partyMemId release];
    _partyMemInfoModel.delegate = nil;
    DO_RELEASE_SAFELY(_partyMemInfoModel);
    [_dataDic release];
    [super dealloc];
}

- (void)viewDidUnload {
    [self setMyScrollView:nil];
    [self setTopImageView:nil];
    [self setHeadSculptureImageView:nil];
    [self setNameLabel:nil];
    [self setAdvancedDeedsView:nil];
    [self setAdvancedDeedsDetailView:nil];
    [self setAdvancedDeedsDetailImageView:nil];
    [self setAdvancedDeedsDetailLabel:nil];
    [self setPartyWorkView:nil];
    [self setPartyWorkDetailView:nil];
    [self setPartyWorkDetailImageView:nil];
    [self setPartyWorkDetailLabel:nil];
    [super viewDidUnload];
}


#pragma mark - Data

- (void)setData
{
    self.headSculptureImageView.defaultImage = [UIImage imageNamed:@"discuss_default头像@2x.png"];
    [self.headSculptureImageView setURLPath:[NSString stringWithFormat:@"%@%@", KImageURL, [[self.dataDic objectForKey:@"member"] objectForKey:@"userImgPath"]]];
    self.nameLabel.text = [[self.dataDic objectForKey:@"member"] objectForKey:@"userName"];
    self.advancedDeedsDetailLabel.text = [[self.dataDic objectForKey:@"member"] objectForKey:@"advancedDeeds"];
    if (1) {
        CGSize labelSize = {0 , 0};
        labelSize = [self.advancedDeedsDetailLabel.text sizeWithFont:FONT(14) constrainedToSize:CGSizeMake(290, 9999) lineBreakMode:UILineBreakModeWordWrap];
        self.advancedDeedsDetailLabel.numberOfLines = 0;
        self.advancedDeedsDetailLabel.lineBreakMode = UILineBreakModeWordWrap;
        self.advancedDeedsDetailLabel.size = CGSizeMake(self.advancedDeedsDetailLabel.size.width, labelSize.height);
    }
    self.advancedDeedsDetailImageView.size = CGSizeMake(self.advancedDeedsDetailImageView.size.width, self.advancedDeedsDetailLabel.size.height+10);
    self.advancedDeedsDetailView.size = CGSizeMake(self.advancedDeedsDetailView.size.width, self.advancedDeedsDetailImageView.size.height);
    self.advancedDeedsView.size = CGSizeMake(self.advancedDeedsView.size.width, 25+self.advancedDeedsDetailView.size.height);
    
    self.PartyWorkDetailLabel.text = [[self.dataDic objectForKey:@"member"] objectForKey:@"partyWork"];
    if (1) {
        CGSize labelSize = {0 , 0};
        labelSize = [self.PartyWorkDetailLabel.text sizeWithFont:FONT(14) constrainedToSize:CGSizeMake(290, 9999) lineBreakMode:UILineBreakModeWordWrap];
        self.PartyWorkDetailLabel.numberOfLines = 0;
        self.PartyWorkDetailLabel.lineBreakMode = UILineBreakModeWordWrap;
        self.PartyWorkDetailLabel.size = CGSizeMake(self.PartyWorkDetailLabel.size.width, labelSize.height);
    }
    self.PartyWorkDetailImageView.size = CGSizeMake(self.PartyWorkDetailImageView.size.width, self.PartyWorkDetailLabel.size.height+10);
    self.PartyWorkDetailView.size = CGSizeMake(self.PartyWorkDetailView.size.width, self.PartyWorkDetailImageView.size.height);
    self.PartyWorkView.frame = XYWH(self.PartyWorkView.origin.x, self.advancedDeedsView.origin.y+self.advancedDeedsView.size.height+20, self.PartyWorkView.size.width, 25+self.PartyWorkDetailView.size.height);
    
    if (self.PartyWorkView.origin.y+self.PartyWorkView.size.height+20 > self.view.size.height) {
        self.myScrollView.contentSize = CGSizeMake(self.myScrollView.size.width, self.PartyWorkView.origin.y+self.PartyWorkView.size.height+20);
        self.myScrollView.scrollEnabled = YES;
    } else {
        self.myScrollView.contentSize = CGSizeMake(self.myScrollView.size.width, self.view.size.height);
        self.myScrollView.scrollEnabled = NO;
    }
}


#pragma mark - Model

- (void)creatPartyMemInfoModel
{
    if (_partyMemInfoModel){
        _partyMemInfoModel.delegate = nil;
        DO_RELEASE_SAFELY(_partyMemInfoModel);
    }
    _partyMemInfoModel = [[BaseModel alloc] init];
    _partyMemInfoModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:self.topicId] forKey:@"id"];
    [aDic setObject:[DES3Util enDES3:self.partyMemId] forKey:@"userId"];
    [aDic setObject:[[NSString stringWithFormat:@"%@%@", self.topicId, self.partyMemId] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KExcellentPartyMemInfoUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_partyMemInfoModel startRequest:requestUrl];
}

- (void)modelDidStartLoad:(DOModel *)model
{
    [self initHUBTitle:nil subTitle:nil];
}

- (void)modelDidFinishLoad:(DOModel *)model
{
    [self removeHub];
    RequstResult *aRR = [((BaseModel *)model).dataDic objectForKey:@"RR"];
    if (model == self.partyMemInfoModel) {
        if (aRR.code) {
            if (aRR.dataDic) {
                self.dataDic = [NSMutableDictionary dictionaryWithDictionary:aRR.dataDic];
                [self setData];
                return;
            }
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


@end
