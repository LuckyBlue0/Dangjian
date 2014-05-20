//
//  RankListVC.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-12.
//
//

#import "RankListVC.h"

@interface RankListVC ()

@end

@implementation RankListVC

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
    
    myIndexPath = nil;
    
    _orderType = [[NSString alloc] initWithFormat:@"3"];
    self.mineOrderBtn.selected = YES;
    
    [self creatRankListModel];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_tableView release];
    [_type release];
    [_orderType release];
    [_ascOrderBtn release];
    [_mineOrderBtn release];
    [_desOrderBtn release];
    _rankListModel.delegate = nil;
    DO_RELEASE_SAFELY(_rankListModel);
    [_branchId release];
    [super dealloc];
}

- (void)viewDidUnload {
    [self setTableView:nil];
    [self setAscOrderBtn:nil];
    [self setMineOrderBtn:nil];
    [self setDesOrderBtn:nil];
    [super viewDidUnload];
}


#pragma mark - Action

- (IBAction)desOrderAction:(id)sender {
    
    self.desOrderBtn.selected = YES;
    self.ascOrderBtn.selected = NO;
    self.mineOrderBtn.selected = NO;
    self.orderType = @"2";
    [self.tableView resetData];
    [self creatRankListModel];
}

- (IBAction)mineOrderAction:(id)sender {
    
    self.mineOrderBtn.selected = YES;
    self.desOrderBtn.selected = NO;
    self.ascOrderBtn.selected = NO;
    self.orderType = @"3";
    [self.tableView resetData];
    [self creatRankListModel];
}

- (IBAction)ascOrderAction:(id)sender {
    
    self.ascOrderBtn.selected = YES;
    self.mineOrderBtn.selected = NO;
    self.desOrderBtn.selected = NO;
    self.orderType = @"1";
    [self.tableView resetData];
    [self creatRankListModel];
}


#pragma mark - BaseTableViewDelegate

- (void)baseTableViewHeaderAction:(BaseTableView*)aBaseTableView{
    //刷新请求
    [self.tableView resetData];
    [self creatRankListModel];
}

- (void)baseTableViewFooterAction:(BaseTableView *)aBaseTableView
{
    [self creatRankListModel];
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
    
    static NSString *identifier = @"rankListCellID";
    UITableViewCell *cell = [aTableView dequeueReusableCellWithIdentifier:identifier];
    
    UILabel *nameLabel = (UILabel *)[cell viewWithTag:1];
    if ([self.type intValue] == 1) {
        nameLabel.text = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"name"];
    } else {
//        nameLabel.text = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"organizationName"];
        nameLabel.text = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"name"];
    }
    
    UILabel *scoreLabel = (UILabel *)[cell viewWithTag:2];
    scoreLabel.text = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"accumulativeScore"];
    
    UILabel *rankLabel = (UILabel *)[cell viewWithTag:3];
    if ([self.type intValue] == 1) {
        rankLabel.text = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"ranking"];
    } else {
        rankLabel.text = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"branchRanking"];
    }
    
    
    if ([self.type intValue] == 1) {
        if ([[UserInfo sharedInstance].userId isEqualToString:[[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"userId"]]) {
            nameLabel.textColor = RED_COLOR;
            scoreLabel.textColor = RED_COLOR;
            rankLabel.textColor = RED_COLOR;
            nameLabel.backgroundColor = [UIColor colorWithPatternImage:[UIImage imageNamed:@"ranking list_表格选中@2x.png"]];
            scoreLabel.backgroundColor = [UIColor colorWithPatternImage:[UIImage imageNamed:@"ranking list_表格选中@2x.png"]];
            rankLabel.backgroundColor = [UIColor colorWithPatternImage:[UIImage imageNamed:@"ranking list_表格选中@2x.png"]];
            myIndexPath = indexPath;
        } else {
            nameLabel.textColor = RGB(87, 87, 87);
            scoreLabel.textColor = RGB(87, 87, 87);
            rankLabel.textColor = RGB(87, 87, 87);
            nameLabel.backgroundColor = RGB(255, 255, 255);
            scoreLabel.backgroundColor = RGB(255, 255, 255);
            rankLabel.backgroundColor = RGB(255, 255, 255);
        }
    } else {
        if ([self.branchId isEqualToString:[[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"organizationId"]]) {
            nameLabel.textColor = RED_COLOR;
            scoreLabel.textColor = RED_COLOR;
            rankLabel.textColor = RED_COLOR;
            nameLabel.backgroundColor = [UIColor colorWithPatternImage:[UIImage imageNamed:@"ranking list_表格选中@2x.png"]];
            scoreLabel.backgroundColor = [UIColor colorWithPatternImage:[UIImage imageNamed:@"ranking list_表格选中@2x.png"]];
            rankLabel.backgroundColor = [UIColor colorWithPatternImage:[UIImage imageNamed:@"ranking list_表格选中@2x.png"]];
            myIndexPath = indexPath;
        } else {
            nameLabel.textColor = RGB(87, 87, 87);
            scoreLabel.textColor = RGB(87, 87, 87);
            rankLabel.textColor = RGB(87, 87, 87);
            nameLabel.backgroundColor = RGB(255, 255, 255);
            scoreLabel.backgroundColor = RGB(255, 255, 255);
            rankLabel.backgroundColor = RGB(255, 255, 255);
        }
    }
    
    if (!myIndexPath && [self.orderType intValue] == 3) {
        [self.tableView scrollToRowAtIndexPath:indexPath atScrollPosition:UITableViewScrollPositionTop animated:YES];
    }

    
    return cell;
}



#pragma mark - Model

- (void)creatRankListModel
{
    if (_rankListModel) {
        _rankListModel.delegate = nil;
        DO_RELEASE_SAFELY(_rankListModel);
    }
    _rankListModel = [[BaseModel alloc] init];
    _rankListModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:self.type] forKey:@"type"];
    [aDic setObject:[DES3Util enDES3:self.orderType] forKey:@"orderType"];
    [aDic setObject:[DES3Util enDES3:[UserInfo sharedInstance].userId] forKey:@"userId"];
    if ([self.type intValue] == 2) {
        [aDic setObject:[DES3Util enDES3:self.branchId] forKey:@"organizationId"];
    }
    [aDic setObject:[DES3Util enDES3:[NSString stringWithFormat:@"%i", self.tableView.currentPage]] forKey:@"pageIndex"];
    [aDic setObject:[DES3Util enDES3:@"10"] forKey:@"pageSize"];
    if ([self.type intValue] == 2) {
        [aDic setObject:[[NSString stringWithFormat:@"%@%@%@%@%i10", self.type, self.orderType, [UserInfo sharedInstance].userId, self.branchId, self.tableView.currentPage] MD5] forKey:@"digest"];
    } else if ([self.type intValue] == 1){
        [aDic setObject:[[NSString stringWithFormat:@"%@%@%@%i10", self.type, self.orderType, [UserInfo sharedInstance].userId, self.tableView.currentPage] MD5] forKey:@"digest"];
    }
    
    NSString *requestUrl = [NSString stringWithFormat:KRankListUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_rankListModel startRequest:requestUrl];
}

- (void)modelDidStartLoad:(DOModel *)model
{
    [self initHUBTitle:nil subTitle:nil];
}

- (void)modelDidFinishLoad:(DOModel *)model
{
    [self removeHub];
    RequstResult *aRR = [((BaseModel *)model).dataDic objectForKey:@"RR"];
    if (model == self.rankListModel) {
        if (aRR.code) {
            if (aRR.dataDic) {
                
                self.tableView.totalPage = [[aRR.dataDic objectForKey:@"maxPage"] intValue];
                
                if ([[aRR.dataDic objectForKey:@"pageData"] isKindOfClass:[NSArray class]]) {
                    [self.tableView finishLoadingDataAction:[aRR.dataDic objectForKey:@"pageData"]];
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


@end
