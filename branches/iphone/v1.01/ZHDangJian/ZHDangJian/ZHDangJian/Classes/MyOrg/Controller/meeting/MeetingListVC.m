//
//  MeetingListVC.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-13.
//
//

#import "MeetingListVC.h"
#import "MeetingStatueVC.h"

@interface MeetingListVC ()

@end

@implementation MeetingListVC

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
    
    _tableView.baseDelegate = self;
    _tableView.loadingView.hidden = YES;
    
    [self.needTodoBtn setImageEdgeInsets:UIEdgeInsetsMake(18, 120, 0, 0)];
    
    _searchKeyWorld = [[NSString alloc] initWithFormat:@""];
    _typeString = [[NSString alloc] initWithFormat:@"1"];
    _statueString = [[NSString alloc] initWithFormat:@"0"];
    
    [self creatMeetingListModel];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(refeshTableList) name:@"meetingLeaveSuccess" object:nil];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_mySearchBar release];
    [_tableView release];
    [_popView release];
    [_needTodoBtn release];
    _meetingListModel.delegate = nil;
    DO_RELEASE_SAFELY(_meetingListModel);
    [_searchKeyWorld release];
    [_typeString release];
    [_statueString release];
    [super dealloc];
}

- (void)viewDidUnload {
    [self setMySearchBar:nil];
    [self setTableView:nil];
    [self setPopView:nil];
    [self setNeedTodoBtn:nil];
    [super viewDidUnload];
}


#pragma mark - Action

- (void)refeshTableList
{
    [self.tableView resetData];
    [self creatMeetingListModel];
}

- (IBAction)needTodoAction:(id)sender {
    
    self.popView.hidden = NO;
    self.searchKeyWorld = @"";
}

- (IBAction)haveDoneAction:(id)sender {
    self.statueString = @"1";
    self.searchKeyWorld = @"";
    [self.tableView resetData];
    [self creatMeetingListModel];
}

- (IBAction)filterAction:(id)sender {
    
    int filterIndex = ((UIButton *)sender).tag;
    self.popView.hidden = YES;
    switch (filterIndex) {
        case 10:
            // 全部
            break;
        case 11:
            // 我的会议
        {
            self.typeString = @"1";
            self.statueString = @"0";
            [self.tableView resetData];
            [self creatMeetingListModel];
        }
            break;
        case 12:
            // 我的党课
        {
            self.typeString = @"2";
            self.statueString = @"0";
            [self.tableView resetData];
            [self creatMeetingListModel];
        }
            break;
        default:
            break;
    }
}


#pragma mark - UISearchBar Delegate

- (void)searchBarSearchButtonClicked:(UISearchBar *)searchBar
{
    [searchBar resignFirstResponder];
    self.searchKeyWorld = searchBar.text;
    [self.tableView resetData];
    [self creatMeetingListModel];
}


#pragma mark - UITableView DataSource and Delegate

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.tableView.datas.count;
}

- (UITableViewCell *)tableView:(UITableView *)aTableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    static NSString *identifier = @"meetingListCellID";
    UITableViewCell *cell = [aTableView dequeueReusableCellWithIdentifier:identifier];
    
    UILabel *titleLabel = (UILabel *)[cell viewWithTag:1];
    titleLabel.text = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"title"];
    
    UILabel *personLabel = (UILabel *)[cell viewWithTag:2];
    personLabel.text = [NSString stringWithFormat:@"发起人：%@", [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"createUserName"]];
    
    UILabel *timeLabel = (UILabel *)[cell viewWithTag:3];
    timeLabel.text = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"createTime"];
    
    UIImageView *statueImage = (UIImageView *)[cell viewWithTag:4];
    if ([[[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"carryOutStatus"] intValue] == 0) {
        statueImage.image = [UIImage imageNamed:@"meeting_未开始@2x.png"];
    } else if ([[[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"carryOutStatus"] intValue] == 1){
        statueImage.image = [UIImage imageNamed:@"meeting_进行中@2x.png"];
    } else if ([[[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"carryOutStatus"] intValue] == 2){
        statueImage.image = [UIImage imageNamed:@"meeting_已结束@2x.png"];
    }
    
    
    [self setCellDefaultBg:cell];
    
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    self.selectedTableIndex = [indexPath row];
    [self performSegueWithIdentifier:@"MeetingStatueVCID" sender:nil];
}


#pragma mark - BaseTableViewDelegate

- (void)baseTableViewHeaderAction:(BaseTableView*)aBaseTableView{
    //刷新请求
    [self.tableView resetData];
    [self creatMeetingListModel];
}

- (void)baseTableViewFooterAction:(BaseTableView *)aBaseTableView
{
    [self creatMeetingListModel];
}

- (void)scrollViewDidScroll:(UIScrollView *)scrollView{
    [self.tableView scrollViewDidScrollTOBaseTableViewAction];
}

- (void)scrollViewDidEndDragging:(UIScrollView *)scrollView willDecelerate:(BOOL)decelerate{
    [self.tableView scrollViewDidEndDraggingTOBaseTableViewAction];
}


#pragma mark - Model

- (void)creatMeetingListModel
{
    if (_meetingListModel) {
        _meetingListModel.delegate = nil;
        DO_RELEASE_SAFELY(_meetingListModel);
    }
    _meetingListModel = [[BaseModel alloc] init];
    _meetingListModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:[UserInfo sharedInstance].userId] forKey:@"userId"];
    [aDic setObject:[DES3Util enDES3:self.searchKeyWorld] forKey:@"keyword"];
    [aDic setObject:[DES3Util enDES3:self.typeString] forKey:@"type"];
    [aDic setObject:[DES3Util enDES3:self.statueString] forKey:@"status"];
    [aDic setObject:[DES3Util enDES3:[NSString stringWithFormat:@"%i", self.tableView.currentPage]] forKey:@"pageIndex"];
    [aDic setObject:[DES3Util enDES3:@"10"] forKey:@"pageSize"];
    [aDic setObject:[[NSString stringWithFormat:@"%@%@%@%@%i10", [UserInfo sharedInstance].userId, self.searchKeyWorld, self.typeString, self.statueString, self.tableView.currentPage] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KMeetingListUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_meetingListModel startRequest:requestUrl];
}

- (void)modelDidStartLoad:(DOModel *)model
{
    [self initHUBTitle:nil subTitle:nil];
}

- (void)modelDidFinishLoad:(DOModel *)model
{
    [self removeHub];
    RequstResult *aRR = [((BaseModel *)model).dataDic objectForKey:@"RR"];
    if (model == self.meetingListModel){
        if (aRR.code){
            if (aRR.dataDic){
                
                self.tableView.totalPage = [[aRR.dataDic objectForKey:@"totalPage"] intValue];
                
                if ([[aRR.dataDic objectForKey:@"pageData"] isKindOfClass:[NSArray class]]){
                    [self.tableView finishLoadingDataAction:[aRR.dataDic objectForKey:@"pageData"]];
                }
                
                if ([[aRR.dataDic objectForKey:@"toDoCount"] intValue] > 0) {
                    self.title = [NSString stringWithFormat:@"三会一课（%i）", [[aRR.dataDic objectForKey:@"toDoCount"] intValue]];
                } else {
                    self.title = @"三会一课";
                }
                return;
            }
            [self.tableView finishLoadingDataAction:nil];
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
    if ([segue.identifier isEqualToString:@"MeetingStatueVCID"]) {
        MeetingStatueVC *vc = (MeetingStatueVC *)segue.destinationViewController;
        vc.dataDic = [self.tableView.datas objectAtIndex:self.selectedTableIndex];
    }
}


@end
