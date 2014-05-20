//
//  PartyMembersReviewVC.m
//  ZHDangJian
//
//  Created by do1 on 13-11-12.
//
//

#import "PartyMembersReviewVC.h"
#import "MutilColorLable.h"
#import "VoteVC.h"

@interface PartyMembersReviewVC ()

@end

@implementation PartyMembersReviewVC

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
    
    self.myBaseTableView.baseDelegate = self;
    self.myBaseTableView.loadingView.hidden = YES;
    
    _searchKeyWorld = [[NSString alloc] initWithFormat:@""];
    
    [self creatModel];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_mySeachBar release];
    [_myBaseTableView release];
    [_searchKeyWorld release];
    _partyMemberModel.delegate = nil;
    DO_RELEASE_SAFELY(_partyMemberModel);
    [super dealloc];
}

- (void)viewDidUnload {
    [self setMySeachBar:nil];
    [self setMyBaseTableView:nil];
    [super viewDidUnload];
}


#pragma mark - Action

- (IBAction)closeKeyboard:(UIButton *)sender {
    
    [self.mySeachBar resignFirstResponder];
}

- (void)bottomButtonAction:(id)sender
{
    [self.mySeachBar resignFirstResponder];
    self.selectedTableIndex = [((UILabel *)[(UIView *)[sender superview] viewWithTag:10]).text intValue];
    [self performSegueWithIdentifier:@"VoteVCID" sender:nil];
}

#pragma mark - UITableView Delegate and DataSource

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    float rowHeight = 0;
    
    UILabel *label1 = [[[UILabel alloc] initWithFrame:XYWH(0, 0, 260, 40)] autorelease];
    label1.text = [[self.myBaseTableView.datas objectAtIndex:[indexPath row]] objectForKey:@"voteTopic"];
    if (1) {
        CGSize labelSize = {0, 0};
        labelSize = [label1.text sizeWithFont:FONT(15) constrainedToSize:CGSizeMake(label1.size.width, 999) lineBreakMode:UILineBreakModeWordWrap];
        label1.numberOfLines = 0;
        label1.lineBreakMode = UILineBreakModeWordWrap;
        label1.size = CGSizeMake(label1.size.width, labelSize.height);
    }
    
    UILabel *label2 = [[[UILabel alloc] initWithFrame:XYWH(0, 0, 240, 20)] autorelease];
    label2.text = [[self.myBaseTableView.datas objectAtIndex:[indexPath row]] objectForKey:@"remark"];
    if (1) {
        CGSize labelSize = {0, 0};
        labelSize = [label2.text sizeWithFont:FONT(14) constrainedToSize:CGSizeMake(label2.size.width, 999) lineBreakMode:UILineBreakModeWordWrap];
        label2.numberOfLines = 0;
        label2.lineBreakMode = UILineBreakModeWordWrap;
        label2.size = CGSizeMake(label2.size.width, labelSize.height);
    }
    rowHeight += 10+label1.size.height+10+138+10+21+label2.size.height+10+63+10+40+15+15;
    return rowHeight;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return self.myBaseTableView.datas.count;
}

- (UITableViewCell *)tableView:(UITableView *)aTableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    UITableViewCell *cell = [aTableView dequeueReusableCellWithIdentifier: @"partyMemberCellID"];
    
    UIImageView *backImage = (UIImageView *)[cell viewWithTag:1];
    
    UILabel *titleLabel = (UILabel *)[cell viewWithTag:2];
    titleLabel.text = [[self.myBaseTableView.datas objectAtIndex:[indexPath row]] objectForKey:@"voteTopic"];
    if (1) {
        CGSize labelSize = {0, 0};
        labelSize = [titleLabel.text sizeWithFont:FONT(15) constrainedToSize:CGSizeMake(titleLabel.size.width, 999) lineBreakMode:UILineBreakModeWordWrap];
        titleLabel.numberOfLines = 0;
        titleLabel.lineBreakMode = UILineBreakModeWordWrap;
        titleLabel.size = CGSizeMake(titleLabel.size.width, labelSize.height);
    }
    
    DONetworkImageView *imageView = (DONetworkImageView *)[cell viewWithTag:3];
    imageView.origin = CGPointMake(imageView.origin.x, titleLabel.origin.y+titleLabel.size.height+10);
    imageView.image = [UIImage imageNamed:@""];
    imageView.defaultImage = [UIImage imageNamed:@"discuss_info头像@2x.png"];
    [imageView setURLPath:[NSString stringWithFormat:@"%@%@", KImageURL, [[self.myBaseTableView.datas objectAtIndex:[indexPath row]] objectForKey:@"voteImgPath"]]];
//    imageView.layer.cornerRadius = 4;
    imageView.layer.masksToBounds = YES;
    [imageView setImageContentMode:UIViewContentModeScaleToFill];
    
    UILabel *activityRuleLabel = (UILabel *)[cell viewWithTag:4];
    activityRuleLabel.origin = CGPointMake(activityRuleLabel.origin.x, imageView.origin.y+imageView.size.height+10);
    
    UILabel *ruleDetailLabel = (UILabel *)[cell viewWithTag:5];
    ruleDetailLabel.origin = CGPointMake(ruleDetailLabel.origin.x, activityRuleLabel.origin.y+activityRuleLabel.size.height);
    ruleDetailLabel.text = [[self.myBaseTableView.datas objectAtIndex:[indexPath row]] objectForKey:@"remark"];
    if (1) {
        CGSize labelSize = {0, 0};
        labelSize = [ruleDetailLabel.text sizeWithFont:FONT(14) constrainedToSize:CGSizeMake(ruleDetailLabel.size.width, 999) lineBreakMode:UILineBreakModeWordWrap];
        ruleDetailLabel.numberOfLines = 0;
        ruleDetailLabel.lineBreakMode = UILineBreakModeWordWrap;
        ruleDetailLabel.size = CGSizeMake(ruleDetailLabel.size.width, labelSize.height);
    }
    
    UIView *timeView = (UIView *)[cell viewWithTag:6];
    timeView.origin = CGPointMake(timeView.origin.x, ruleDetailLabel.origin.y+ruleDetailLabel.size.height+10);
    
    UILabel *startTimeLabel = (UILabel *)[cell viewWithTag:7];
    NSString *startTime = [[self.myBaseTableView.datas objectAtIndex:[indexPath row]] objectForKey:@"startTime"];
    if (startTime.length >= 16) {
        startTimeLabel.text = [startTime substringToIndex:16];
    }
  
    UILabel *endTimeLabel = (UILabel *)[cell viewWithTag:8];
    NSString *endTime = [[self.myBaseTableView.datas objectAtIndex:[indexPath row]] objectForKey:@"endTime"];
    if (endTime.length >= 16) {
        endTimeLabel.text = [endTime substringToIndex:16];
    }
    
    UIButton *bottomButton = (UIButton *)[cell viewWithTag:9];
    [bottomButton setTitle:[NSString stringWithFormat:@"马上评选投票（%i人参与）", [[[self.myBaseTableView.datas objectAtIndex:[indexPath row]] objectForKey:@"totalCount"] intValue]] forState:UIControlStateNormal];
    [bottomButton setTitle:[NSString stringWithFormat:@"马上评选投票（%i人参与）", [[[self.myBaseTableView.datas objectAtIndex:[indexPath row]] objectForKey:@"totalCount"] intValue]] forState:UIControlStateHighlighted];
    bottomButton.origin = CGPointMake(bottomButton.origin.x, timeView.origin.y+timeView.size.height+10);
    [bottomButton addTarget:self action:@selector(bottomButtonAction:) forControlEvents:UIControlEventTouchUpInside];
    
    UILabel *indexLabel = (UILabel *)[cell viewWithTag:10];
    indexLabel.text = [NSString stringWithFormat:@"%i", [indexPath row]];
    
    backImage.size = CGSizeMake(backImage.size.width, bottomButton.origin.y+bottomButton.size.height+15);
    
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [self.mySeachBar resignFirstResponder];
    self.selectedTableIndex = [indexPath row];
    [self performSegueWithIdentifier:@"VoteVCID" sender:nil];
}


#pragma mark - BaseTableViewDelegate

-(void)baseTableViewHeaderAction:(BaseTableView*)aBaseTableView
{
    [self.myBaseTableView resetData];
    [self creatModel];
}

-(void)baseTableViewFooterAction:(BaseTableView*)aBaseTableView
{
    [self creatModel];
}

- (void)scrollViewDidScroll:(UIScrollView *)scrollView
{
    [self.myBaseTableView scrollViewDidScrollTOBaseTableViewAction];
}

- (void)scrollViewDidEndDragging:(UIScrollView *)scrollView willDecelerate:(BOOL)decelerate
{
    [self.myBaseTableView scrollViewDidEndDraggingTOBaseTableViewAction];
}


#pragma mark - UISearchBar Delegate

- (void)searchBarSearchButtonClicked:(UISearchBar *)searchBar
{
    [searchBar resignFirstResponder];
    self.searchKeyWorld = searchBar.text;
    [self.myBaseTableView resetData];
    [self creatModel];
}


#pragma mark - Model

-(void)creatModel
{
    if (_partyMemberModel) {
        _partyMemberModel.delegate = nil;
        DO_RELEASE_SAFELY(_partyMemberModel);
    }
    _partyMemberModel = [[BaseModel alloc] init];
    _partyMemberModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:[NSString stringWithFormat:@"%i", self.myBaseTableView.currentPage]] forKey:@"pageIndex"];
    [aDic setObject:[DES3Util enDES3:@"10"] forKey:@"pageSize"];
    [aDic setObject:[[NSString stringWithFormat:@"%i10", self.myBaseTableView.currentPage] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KPartyMemThemeListUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_partyMemberModel startRequest:requestUrl];
}

- (void)modelDidFinishLoad:(DOModel*)model
{
    [self removeHub];
    RequstResult *aRR = [((BaseModel*)model).dataDic objectForKey:@"RR"];
    if (model == self.partyMemberModel) {
        if (aRR.code) {
            
            self.myBaseTableView.totalPage = [[aRR.dataDic objectForKey:@"totalPage"] intValue];
            
            if (aRR.dataDic) {
                if ([[aRR.dataDic objectForKey:@"pageData"] isKindOfClass:[NSArray class]]) {
                    [self.myBaseTableView finishLoadingDataAction:[aRR.dataDic objectForKey:@"pageData"]];
                }
                return;
            }
            [self.myBaseTableView finishLoadingDataAction:nil];
            return;
        }
        [UIAlertView showTip:aRR.desc];
    }
}

- (void)modelDidStartLoad:(DOModel *)model
{
    [self initHUBTitle:nil subTitle:nil];
}

- (void)model:(DOModel *)model didFailLoadWithError:(NSError *)error
{
    [self removeHub];
    [UIAlertView showTip:NetWorkFaild];
}


#pragma mark - Segue

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([segue.identifier isEqualToString:@"VoteVCID"]) {
        VoteVC *vc = (VoteVC *)segue.destinationViewController;
        vc.dataDic = [self.myBaseTableView.datas objectAtIndex:self.selectedTableIndex];
    }
}

@end
