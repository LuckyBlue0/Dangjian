//
//  WishActivityListVC.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-14.
//
//

#import "WishActivityListVC.h"
#import "WishActivityStatueVC.h"

@interface WishActivityListVC ()

@end

@implementation WishActivityListVC

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
    
    _searchKeyWorld = [[NSString alloc] initWithFormat:@""];
    _statueString = [[NSString alloc] initWithFormat:@"0"];
    
    [self creatWishListModel];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(refeshTableView) name:@"wishSignSuccess" object:nil];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_mySearchBar release];
    [_tableView release];
    _wishListModel.delegate = nil;
    DO_RELEASE_SAFELY(_wishListModel);
    [_searchKeyWorld release];
    [_statueString release];
    [[NSNotificationCenter defaultCenter] removeObserver:self];
    [super dealloc];
}

- (void)viewDidUnload {
    [self setMySearchBar:nil];
    [self setTableView:nil];
    [super viewDidUnload];
}


#pragma mark - Action

- (void)refeshTableView
{
    [self.tableView resetData];
    [self creatWishListModel];
}

- (IBAction)needTodoAction:(id)sender {
    self.searchKeyWorld = @"";
    self.statueString = @"0";
    [self.tableView resetData];
    [self creatWishListModel];
}

- (IBAction)haveDoneAction:(id)sender {
    self.searchKeyWorld = @"";
    self.statueString = @"1";
    [self.tableView resetData];
    [self creatWishListModel];
}


#pragma mark - UISearchBar Delegate

- (void)searchBarSearchButtonClicked:(UISearchBar *)searchBar
{
    [searchBar resignFirstResponder];
    self.searchKeyWorld = searchBar.text;
    [self.tableView resetData];
    [self creatWishListModel];
}


#pragma mark - UITableView DataSource and Delegate

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.tableView.datas.count;
}

- (UITableViewCell *)tableView:(UITableView *)aTableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    static NSString *identifier = @"wishActivityListCellID";
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
    [self performSegueWithIdentifier:@"WishActivityStatueVCID" sender:nil];
}


#pragma mark - BaseTableViewDelegate

- (void)baseTableViewHeaderAction:(BaseTableView*)aBaseTableView{
    //刷新请求
    [self.tableView resetData];
    [self creatWishListModel];
}

- (void)baseTableViewFooterAction:(BaseTableView *)aBaseTableView
{
    [self creatWishListModel];
}

- (void)scrollViewDidScroll:(UIScrollView *)scrollView{
    [self.tableView scrollViewDidScrollTOBaseTableViewAction];
}

- (void)scrollViewDidEndDragging:(UIScrollView *)scrollView willDecelerate:(BOOL)decelerate{
    [self.tableView scrollViewDidEndDraggingTOBaseTableViewAction];
}


#pragma mark - Model

- (void)creatWishListModel
{
    if (_wishListModel) {
        _wishListModel.delegate = nil;
        DO_RELEASE_SAFELY(_wishListModel);
    }
    _wishListModel = [[BaseModel alloc] init];
    _wishListModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:[UserInfo sharedInstance].userId] forKey:@"userId"];
    [aDic setObject:[DES3Util enDES3:self.searchKeyWorld] forKey:@"keyword"];
    [aDic setObject:[DES3Util enDES3:self.statueString] forKey:@"status"];
    [aDic setObject:[DES3Util enDES3:[NSString stringWithFormat:@"%i", self.tableView.currentPage]] forKey:@"pageIndex"];
    [aDic setObject:[DES3Util enDES3:@"10"] forKey:@"pageSize"];
    [aDic setObject:[[NSString stringWithFormat:@"%@%@%@%i10", [UserInfo sharedInstance].userId, self.searchKeyWorld, self.statueString, self.tableView.currentPage] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KWishActiveListUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_wishListModel startRequest:requestUrl];
}

- (void)modelDidStartLoad:(DOModel *)model
{
    [self initHUBTitle:nil subTitle:nil];
}

- (void)modelDidFinishLoad:(DOModel *)model
{
    [self removeHub];
    RequstResult *aRR = [((BaseModel *)model).dataDic objectForKey:@"RR"];
    if (model == self.wishListModel) {
        if (aRR.code) {
            if (aRR.dataDic){
                
                self.tableView.totalPage = [[aRR.dataDic objectForKey:@"totalPage"] intValue];
                
                if ([[aRR.dataDic objectForKey:@"pageData"] isKindOfClass:[NSArray class]]) {
                    [self.tableView finishLoadingDataAction:[aRR.dataDic objectForKey:@"pageData"]];
                    return;
                }
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
    if ([segue.identifier isEqualToString:@"WishActivityStatueVCID"]) {
        WishActivityStatueVC *vc = (WishActivityStatueVC *)segue.destinationViewController;
        vc.dataDic = [self.tableView.datas objectAtIndex:self.selectedTableIndex];
    }
}


@end
