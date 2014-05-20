//
//  DemocraticAppraisalVC.m
//  ZHDangJian
//
//  Created by do1 on 13-11-12.
//
//

#import "DemocraticAppraisalVC.h"
#import "DemocraticListVC.h"

@interface DemocraticAppraisalVC ()

@end

@implementation DemocraticAppraisalVC

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
    
    _myBaseTableView.baseDelegate = self;
    _myBaseTableView.loadingView.hidden = YES;
    
    [self creatModel];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(refreshList) name:@"submitDiscuss" object:nil];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_myBaseTableView release];
    _democraticAppraisalmodel.delegate = nil;
    DO_RELEASE_SAFELY(_democraticAppraisalmodel);
    [super dealloc];
}

- (void)viewDidUnload {
    [self setMyBaseTableView:nil];
    [super viewDidUnload];
}


#pragma mark - Action

- (void)refreshList
{
    [self.myBaseTableView resetData];
    [self creatModel];
}

- (void)discussAction:(id)sender {
    self.selectedTableIndex = [((UILabel *)[[(UIView *)sender superview] viewWithTag:14]).text intValue];
    [self performSegueWithIdentifier:@"DemocraticListVCID" sender:nil];
}


#pragma mark - Model

- (void)creatModel
{
    if (_democraticAppraisalmodel) {
        _democraticAppraisalmodel.delegate = nil;
        DO_RELEASE_SAFELY(_democraticAppraisalmodel);
    }
    _democraticAppraisalmodel = [[BaseModel alloc] init];
    _democraticAppraisalmodel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:[UserInfo sharedInstance].userId] forKey:@"voteUserId"];
    [aDic setObject:[DES3Util enDES3:[NSString stringWithFormat:@"%i", self.myBaseTableView.currentPage]] forKey:@"pageIndex"];
    [aDic setObject:[DES3Util enDES3:@"10"] forKey:@"pageSize"];
    [aDic setObject:[[NSString stringWithFormat:@"%@%i10", [UserInfo sharedInstance].userId,self.myBaseTableView.currentPage] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KGovernDiscussListUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_democraticAppraisalmodel startRequest:requestUrl];
}

- (void)modelDidStartLoad:(DOModel *)model
{
    [self initHUBTitle:nil subTitle:nil];
}

- (void)modelDidFinishLoad:(DOModel*)model
{
    [self removeHub];
    RequstResult *aRR = [((BaseModel*)model).dataDic objectForKey:@"RR"];
    if (model == self.democraticAppraisalmodel) {
        if (aRR.dataDic) {
            
            self.myBaseTableView.totalPage = [[aRR.dataDic objectForKey:@"totalPage"] intValue];
            
            if ([[aRR.dataDic objectForKey:@"pageData"] isKindOfClass:[NSArray class]]) {
                [self.myBaseTableView finishLoadingDataAction:[aRR.dataDic objectForKey:@"pageData"]];
            }
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

#pragma mark - UITableView Delegate and DataSource

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    UILabel *aLabel = [[[UILabel alloc] initWithFrame:XYWH(0, 0, 280, 10)] autorelease];
    aLabel.text = [[_myBaseTableView.datas objectAtIndex:[indexPath row]] objectForKey:@"topic"];
    if (1) {
        CGSize labelSize = {0,0};
        labelSize = [aLabel.text sizeWithFont:FONT(17) constrainedToSize:CGSizeMake(280, 100) lineBreakMode:UILineBreakModeWordWrap];
        aLabel.numberOfLines = 0;
        aLabel.lineBreakMode = UILineBreakModeCharacterWrap;
        aLabel.frame =XYWH(aLabel.origin.x, aLabel.origin.y, aLabel.size.width, labelSize.height);
    }
    return aLabel.size.height+214;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return self.myBaseTableView.datas.count;
}

- (UITableViewCell *)tableView:(UITableView *)aTableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    UITableViewCell *cell = [aTableView dequeueReusableCellWithIdentifier: @"democraticListCellID"];
    
    int index = [indexPath row];
    
    UIView *backView = (UIView *)[cell viewWithTag:1];
    
    UIImageView *topImage = (UIImageView *)[backView viewWithTag:2];
    
    UIImageView *middleImage = (UIImageView *)[backView viewWithTag:3];
    
    UIImageView *bottomImage = (UIImageView *)[backView viewWithTag:4];
    
    UILabel *titleLabel = (UILabel *)[backView viewWithTag:5];
    titleLabel.text = [[_myBaseTableView.datas objectAtIndex:index] objectForKey:@"topic"];
    
    UIImageView *lineImage = (UIImageView *)[backView viewWithTag:6];
    
    UIView *activeDetailView = (UIView *)[backView viewWithTag:7];
    
    NSDateFormatter *formatter = [[[NSDateFormatter alloc] init] autorelease];
    formatter.dateFormat = @"yyyy-MM-dd HH:mm:ss";
    
    UILabel *startTimeLabel = (UILabel *)[activeDetailView viewWithTag:8];
    startTimeLabel.text = [[_myBaseTableView.datas objectAtIndex:index] objectForKey:@"startTime"];
    NSDate *startDate = [formatter dateFromString:startTimeLabel.text];
    
    UILabel *endTimeLabel = (UILabel *)[activeDetailView viewWithTag:9];
    endTimeLabel.text = [[_myBaseTableView.datas objectAtIndex:index] objectForKey:@"endTime"];
    NSDate *endDate = [formatter dateFromString:endTimeLabel.text];
    
    formatter.dateFormat = @"yyyy-MM-dd HH:mm";
    startTimeLabel.text = [formatter stringFromDate:startDate];
    endTimeLabel.text = [formatter stringFromDate:endDate];
    
    UILabel *discussNumLabel = (UILabel *)[activeDetailView viewWithTag:10];
    discussNumLabel.text = [NSString stringWithFormat:@"%@个", [[_myBaseTableView.datas objectAtIndex:index] objectForKey:@"orgVoteCount"]];
    
    UIView *discussView = (UIView *)[backView viewWithTag:11];
    
    MutilColorLable *notDisnumLabel = (MutilColorLable *)[discussView viewWithTag:12];
    notDisnumLabel.text = [NSString stringWithFormat:@"我要评议（%@个未评议）", [[_myBaseTableView.datas objectAtIndex:index] objectForKey:@"nonCount"]];
    NSRange numRange = NSMakeRange(5, [[[_myBaseTableView.datas objectAtIndex:index] objectForKey:@"nonCount"] length]+4);
    notDisnumLabel.textColor = RGB(56, 56, 56);
    NSMutableDictionary *d = [[NSMutableDictionary alloc] init];
    [d setObject:RGB(0xc9, 0x01, 0x00) forKey:[NSValue valueWithRange:numRange]];
    notDisnumLabel.attributedDic = d;
    [d release];
    
    
    UIButton *discussButton = (UIButton *)[discussView viewWithTag:13];
    [discussButton setTarget:self action:@selector(discussAction:)];
    
    UILabel *indexLabel = (UILabel *)[discussView viewWithTag:14];
    indexLabel.text = [NSString stringWithFormat:@"%i", index];
    
    
    if (1) {
        CGSize labelSize = {0,0};
        labelSize = [titleLabel.text sizeWithFont:FONT(17) constrainedToSize:CGSizeMake(280, 100) lineBreakMode:UILineBreakModeWordWrap];
        titleLabel.numberOfLines = 0;
        titleLabel.lineBreakMode = UILineBreakModeCharacterWrap;
        titleLabel.frame =XYWH(titleLabel.origin.x, titleLabel.origin.y, titleLabel.size.width, labelSize.height);
    }
    lineImage.origin = CGPointMake(lineImage.origin.x, titleLabel.origin.y+titleLabel.size.height+10);
    activeDetailView.origin = CGPointMake(activeDetailView.origin.x, lineImage.origin.y+lineImage.size.height+10);
    discussView.origin = CGPointMake(discussView.origin.x, activeDetailView.origin.y+activeDetailView.size.height);
    backView.size = CGSizeMake(backView.size.width, discussView.origin.y+discussView.size.height);
    middleImage.size = CGSizeMake(middleImage.size.width, backView.size.height-topImage.size.height-bottomImage.size.height);
    bottomImage.origin = CGPointMake(bottomImage.origin.x, middleImage.origin.y+middleImage.size.height);
    
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    self.selectedTableIndex = [indexPath row];
    [self performSegueWithIdentifier:@"DemocraticAppraisalDetailVCID" sender:nil];
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


#pragma mark - Segue

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([segue.identifier isEqualToString:@"DemocraticListVCID"]) {
        DemocraticListVC *vc = (DemocraticListVC *)segue.destinationViewController;
        vc.activityID = [[self.myBaseTableView.datas objectAtIndex:self.selectedTableIndex] objectForKey:@"id"];
        vc.haveVoteNum = [[self.myBaseTableView.datas objectAtIndex:self.selectedTableIndex] objectForKey:@"alreadyCount"];
        vc.notVoteNum = [[self.myBaseTableView.datas objectAtIndex:self.selectedTableIndex] objectForKey:@"nonCount"];
    }
}


@end
