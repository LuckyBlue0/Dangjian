//
//  DemocraticListVC.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-12-27.
//
//

#import "DemocraticListVC.h"
#import "DemocraticAppraisalDetailVC.h"

@interface DemocraticListVC ()

@end

@implementation DemocraticListVC

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
    [self initData];
    [self creatListModel];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(refreshList) name:@"submitDiscuss" object:nil];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_totalButton release];
    [_haveVoteButton release];
    [_notVoteButton release];
    [_tableView release];
    [_activityID release];
    [_haveVoteNum release];
    [_notVoteNum release];
    [_voteStatue release];
    _listModel.delegate = nil;
    DO_RELEASE_SAFELY(_listModel);
    [super dealloc];
}

- (void)viewDidUnload {
    [self setTotalButton:nil];
    [self setHaveVoteButton:nil];
    [self setNotVoteButton:nil];
    [self setTableView:nil];
    [super viewDidUnload];
}

#pragma mark - Data

- (void)initData
{
    _totalButton.selected = YES;
    [_haveVoteButton setTitle:[NSString stringWithFormat:@"已评议(%@)", _haveVoteNum] forState:UIControlStateNormal];
    [_haveVoteButton setTitle:[NSString stringWithFormat:@"已评议(%@)", _haveVoteNum] forState:UIControlStateSelected];
    [_notVoteButton setTitle:[NSString stringWithFormat:@"未评议(%@)", _notVoteNum] forState:UIControlStateNormal];
    [_notVoteButton setTitle:[NSString stringWithFormat:@"未评议(%@)", _notVoteNum] forState:UIControlStateSelected];
    _voteStatue = [[NSString alloc] initWithFormat:@"0"];
    _tableView.baseDelegate = self;
}


#pragma mark - Action

- (void)refreshList
{
    [_haveVoteButton setTitle:[NSString stringWithFormat:@"已评议(%i)", [_haveVoteNum intValue]+1] forState:UIControlStateNormal];
    [_haveVoteButton setTitle:[NSString stringWithFormat:@"已评议(%i)", [_haveVoteNum intValue]+1] forState:UIControlStateSelected];
    [_notVoteButton setTitle:[NSString stringWithFormat:@"未评议(%i)", [_notVoteNum intValue]-1] forState:UIControlStateNormal];
    [_notVoteButton setTitle:[NSString stringWithFormat:@"未评议(%i)", [_notVoteNum intValue]-1] forState:UIControlStateSelected];
    
    [self.tableView resetData];
    [self creatListModel];
}

- (IBAction)statueButtonAction:(id)sender {
    switch (((UIButton *)sender).tag) {
        case 1:
        {
            self.voteStatue = @"0";
            self.totalButton.selected = YES;
            self.haveVoteButton.selected = NO;
            self.notVoteButton.selected = NO;
        }
            break;
        case 2:
        {
            self.voteStatue = @"2";
            self.totalButton.selected = NO;
            self.haveVoteButton.selected = YES;
            self.notVoteButton.selected = NO;
        }
            break;
        case 3:
        {
            self.voteStatue = @"1";
            self.totalButton.selected = NO;
            self.haveVoteButton.selected = NO;
            self.notVoteButton.selected = YES;
        }
            break;
        default:
            break;
    }
    [self.tableView resetData];
    [self creatListModel];

}


#pragma mark - Model

- (void)creatListModel
{
    if (_listModel) {
        _listModel.delegate = nil;
        DO_RELEASE_SAFELY(_listModel);
    }
    _listModel = [[BaseModel alloc] init];
    _listModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:[UserInfo sharedInstance].userId] forKey:@"voteUserId"];
    [aDic setObject:[DES3Util enDES3:self.activityID] forKey:@"voteId"];
    [aDic setObject:[DES3Util enDES3:[NSString stringWithFormat:@"%i", self.tableView.currentPage]] forKey:@"pageIndex"];
    [aDic setObject:[DES3Util enDES3:@"10"] forKey:@"pageSize"];
    [aDic setObject:[DES3Util enDES3:self.voteStatue] forKey:@"status"];
    [aDic setObject:[[NSString stringWithFormat:@"%@%@%i10%@", [UserInfo sharedInstance].userId, self.activityID, self.tableView.currentPage, self.voteStatue] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KDiscussededMemListUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_listModel startRequest:requestUrl];
}

- (void)modelDidStartLoad:(DOModel *)model
{
    [obj_message_box loading];
}

- (void)modelDidFinishLoad:(DOModel *)model
{
    [obj_message_box hidden];
    RequstResult *aRR = [((BaseModel*)model).dataDic objectForKey:@"RR"];
    if (model == _listModel) {
        if (aRR.code) {
            if ([[aRR.dataDic objectForKey:@"totalPage"] isKindOfClass:[NSNumber class]]) {
                self.tableView.totalPage = [[aRR.dataDic objectForKey:@"totalPage"] intValue];
            }
            
            if ([[aRR.dataDic objectForKey:@"pageData"] isKindOfClass:[NSArray class]]) {
                [self.tableView finishLoadingDataAction:[aRR.dataDic objectForKey:@"pageData"]];
            }
            return;
        }
        [UIAlertView showTip:aRR.desc];
    }
}

- (void)model:(DOModel *)model didFailLoadWithError:(NSError *)error
{
    [obj_message_box hidden];
    [UIAlertView showTip:NetWorkFaild];
}


#pragma mark - BaseTableViewDelegate

- (void)baseTableViewHeaderAction:(BaseTableView*)aBaseTableView{
    //刷新请求
    [self.tableView resetData];
    [self creatListModel];
}

- (void)baseTableViewFooterAction:(BaseTableView *)aBaseTableView
{
    [self creatListModel];
}

- (void)scrollViewDidScroll:(UIScrollView *)scrollView{
    [self.tableView scrollViewDidScrollTOBaseTableViewAction];
}

- (void)scrollViewDidEndDragging:(UIScrollView *)scrollView willDecelerate:(BOOL)decelerate{
    [self.tableView scrollViewDidEndDraggingTOBaseTableViewAction];
}


#pragma mark - UITableView Delegate and DataSource

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.tableView.datas.count;
}

- (UITableViewCell *)tableView:(UITableView *)aTableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    static NSString *identifier = @"listCellID";
    UITableViewCell *cell = [aTableView dequeueReusableCellWithIdentifier:identifier];
    
    int index = [indexPath row];
    UILabel *organLabel = (UILabel *)[cell viewWithTag:4];
    organLabel.text = [[self.tableView.datas objectAtIndex:index] objectForKey:@"voteOrg"];
    
    UILabel *statueLabel = (UILabel *)[cell viewWithTag:5];
    if ([self.voteStatue intValue] == 0) {
        statueLabel.text = [[[self.tableView.datas objectAtIndex:index] objectForKey:@"isVote"] intValue]==0?@"已评议":@"未评议";
        statueLabel.textColor = [[[self.tableView.datas objectAtIndex:index] objectForKey:@"isVote"] intValue]==0?[UIColor darkGrayColor]:RED_COLOR;
    } else if ([self.voteStatue intValue] == 1){
        statueLabel.text = @"未评议";
        statueLabel.textColor = RED_COLOR;
    } else {
        statueLabel.text = @"已评议";
        statueLabel.textColor = [UIColor darkGrayColor];
    }
    
    [self setCellDefaultBg:cell];
    
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    selectIndex = [indexPath row];
    [self performSegueWithIdentifier:@"DemocraticAppraisalDetailVCID" sender:nil];
}


#pragma mark - Segue

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([segue.identifier isEqualToString:@"DemocraticAppraisalDetailVCID"]) {
        DemocraticAppraisalDetailVC *vc = (DemocraticAppraisalDetailVC *)segue.destinationViewController;
        if ([self.voteStatue intValue] == 0) {
            vc.voteStatue = [[self.tableView.datas objectAtIndex:selectIndex] objectForKey:@"isVote"];
        } else if ([self.voteStatue intValue] == 1){
            vc.voteStatue = @"1";
        } else {
            vc.voteStatue = @"0";
        }
        vc.activityID = [[self.tableView.datas objectAtIndex:selectIndex] objectForKey:@"id"];
        vc.govName = [[self.tableView.datas objectAtIndex:selectIndex] objectForKey:@"voteOrg"];
    }
}

@end
