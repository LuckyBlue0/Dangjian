//
//  MyScoreDetailVC.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-12.
//
//

#import "MyScoreDetailVC.h"

@interface MyScoreDetailVC ()

@end

@implementation MyScoreDetailVC

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
    
    _myScoreLabel.text = [NSString stringWithFormat:@"%@的个人总积分：%@", [UserInfo sharedInstance].name, self.totalScoreString];
    NSRange numRange = NSMakeRange(_myScoreLabel.text.length-self.totalScoreString.length, self.totalScoreString.length);
    _myScoreLabel.textColor = RGB(56, 56, 56);
    _myScoreLabel.font = [UIFont systemFontOfSize:14];
    _myScoreLabel.backgroundColor = [UIColor clearColor];
    NSMutableDictionary *d = [[NSMutableDictionary alloc] init];
    [d setObject:RGB(0xc9, 0x01, 0x00) forKey:[NSValue valueWithRange:numRange]];
    _myScoreLabel.attributedDic = d;
    [d release];
    
    [self creatScoreDetailModel];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_myScoreLabel release];
    [_tableView release];
    _scoreDetailModel.delegate = nil;
    DO_RELEASE_SAFELY(_scoreDetailModel);
    [_totalScoreString release];
    [super dealloc];
}

- (void)viewDidUnload {
    [self setMyScoreLabel:nil];
    [self setTableView:nil];
    [super viewDidUnload];
}


#pragma mark - BaseTableViewDelegate

- (void)baseTableViewHeaderAction:(BaseTableView*)aBaseTableView{
    //刷新请求
    [self.tableView resetData];
    [self creatScoreDetailModel];
}

- (void)baseTableViewFooterAction:(BaseTableView *)aBaseTableView
{
    [self creatScoreDetailModel];
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
    
    static NSString *identifier = @"myScoreDetailCellID";
    UITableViewCell *cell = [aTableView dequeueReusableCellWithIdentifier:identifier];
    
    UILabel *infoLabel = (UILabel *)[cell viewWithTag:1];
    infoLabel.text = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"scoreFrom"];
    
    UILabel *scoreLabel = (UILabel *)[cell viewWithTag:2];
    scoreLabel.text = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"score"];
    
    UILabel *timeLabel = (UILabel *)[cell viewWithTag:3];
    timeLabel.text = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"getTime"];
    
    return cell;
}


#pragma mark - Model

- (void)creatScoreDetailModel
{
    if (_scoreDetailModel) {
        _scoreDetailModel.delegate = nil;
        DO_RELEASE_SAFELY(_scoreDetailModel);
    }
    _scoreDetailModel = [[BaseModel alloc] init];
    _scoreDetailModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:@"1"] forKey:@"type"];
    [aDic setObject:[DES3Util enDES3:[UserInfo sharedInstance].userId] forKey:@"userId"];
    [aDic setObject:[DES3Util enDES3:[NSString stringWithFormat:@"%i", self.tableView.currentPage]] forKey:@"pageIndex"];
    [aDic setObject:[DES3Util enDES3:@"10"] forKey:@"pageSize"];
    [aDic setObject:[[NSString stringWithFormat:@"1%@%i10", [UserInfo sharedInstance].userId, self.tableView.currentPage] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KScoreDetailUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_scoreDetailModel startRequest:requestUrl];
}

- (void)modelDidStartLoad:(DOModel *)model
{
    [self initHUBTitle:nil subTitle:nil];
}

- (void)modelDidFinishLoad:(DOModel *)model
{
    [self removeHub];
    RequstResult *aRR = [((BaseModel *)model).dataDic objectForKey:@"RR"];
    if (model == self.scoreDetailModel) {
        if (aRR.code) {
            if (aRR.dataDic) {
                
                self.tableView.totalPage = [[aRR.dataDic objectForKey:@"totalPage"] intValue];
                
                if ([[aRR.dataDic objectForKey:@"pageData"] isKindOfClass:[NSArray class]]) {
                    [self.tableView finishLoadingDataAction:[aRR.dataDic objectForKey:@"pageData"]];
                }
                
                return;
            }
            [self.tableView finishLoadingDataAction:nil];
            return;
        }
        [UIAlertView showTip:aRR.desc];
    }
}

- (void)model:(DOModel *)model didFailLoadWithError:(NSError *)error
{
    [self removeHub];
    [UIAlertView showTip:NetWorkFaild];
}


@end
