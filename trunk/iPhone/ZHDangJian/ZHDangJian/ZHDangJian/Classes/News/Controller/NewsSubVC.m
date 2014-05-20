//
//  NewsSubVC.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-12.
//
//

#import "NewsSubVC.h"
#import "NewsDetailVC.h"

@interface NewsSubVC ()

@end

@implementation NewsSubVC

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
    
    [self creatNewsListModel];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_tableView release];
    [_newsInfoType release];
    _newsListModel.delegate = nil;
    DO_RELEASE_SAFELY(_newsListModel);
    [super dealloc];
}

- (void)viewDidUnload {
    [self setTableView:nil];
    [super viewDidUnload];
}


#pragma mark - Model

- (void)creatNewsListModel
{
    if (_newsListModel) {
        _newsListModel.delegate = nil;
        DO_RELEASE_SAFELY(_newsListModel);
    }
    _newsListModel = [[BaseModel alloc] init];
    _newsListModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:@"1"] forKey:@"parentType"];
    [aDic setObject:[DES3Util enDES3:self.newsInfoType] forKey:@"newsInfoType"];
    [aDic setObject:[DES3Util enDES3:[NSString stringWithFormat:@"%i", self.tableView.currentPage]] forKey:@"pageIndex"];
    [aDic setObject:[DES3Util enDES3:@"10"] forKey:@"pageSize"];
    [aDic setObject:[[NSString stringWithFormat:@"1%@%i10", self.newsInfoType, self.tableView.currentPage] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KNewsListUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_newsListModel startRequest:requestUrl];
}

- (void)modelDidStartLoad:(DOModel *)model
{
    [self initHUBTitle:nil subTitle:nil];
}

- (void)modelDidFinishLoad:(DOModel *)model
{
    [self removeHub];
    RequstResult *aRR = [((BaseModel *)model).dataDic objectForKey:@"RR"];
    if (model == self.newsListModel) {
        if (aRR.code) {
            if (aRR.dataDic) {
                
                self.tableView.totalPage = [[aRR.dataDic objectForKey:@"totalPage"] intValue];
                
                if ([[aRR.dataDic objectForKey:@"newsInfos"] isKindOfClass:[NSArray class]]) {
                    [self.tableView finishLoadingDataAction:[aRR.dataDic objectForKey:@"newsInfos"]];
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


#pragma mark - BaseTableViewDelegate

- (void)baseTableViewHeaderAction:(BaseTableView*)aBaseTableView{
    //刷新请求
    [self.tableView resetData];
    [self creatNewsListModel];
    
}

- (void)baseTableViewFooterAction:(BaseTableView *)aBaseTableView
{
    [self creatNewsListModel];
}

- (void)scrollViewDidScroll:(UIScrollView *)scrollView{
    [self.tableView scrollViewDidScrollTOBaseTableViewAction];
}

- (void)scrollViewDidEndDragging:(UIScrollView *)scrollView willDecelerate:(BOOL)decelerate{
    [self.tableView scrollViewDidEndDraggingTOBaseTableViewAction];
}


#pragma mark - UITableView Delegate and DataSource

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    float rowHeight = 0;
    
    UILabel *titleLabel = [[[UILabel alloc] initWithFrame:XYWH(0, 0, 260, 21)] autorelease];
    titleLabel.text = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"title"];
    if (1) {
        CGSize labelSize = {0 , 0};
        labelSize = [titleLabel.text sizeWithFont:FONT(15) constrainedToSize:CGSizeMake(titleLabel.size.width, 999) lineBreakMode:UILineBreakModeWordWrap];
        titleLabel.numberOfLines = 0;
        titleLabel.lineBreakMode = UILineBreakModeWordWrap;
        titleLabel.size = CGSizeMake(titleLabel.size.width, labelSize.height);
    }
    
    rowHeight += titleLabel.origin.y+titleLabel.size.height+8+21+8+138;
    
    UILabel *briefLabel = [[[UILabel alloc] initWithFrame:XYWH(0, 0, 260, 21)] autorelease];
    briefLabel.text = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"digest"];
    if (1) {
        CGSize labelSize = {0 , 0};
        labelSize = [briefLabel.text sizeWithFont:FONT(15) constrainedToSize:CGSizeMake(briefLabel.size.width, 999) lineBreakMode:UILineBreakModeWordWrap];
        briefLabel.numberOfLines = 0;
        briefLabel.lineBreakMode = UILineBreakModeWordWrap;
        briefLabel.size = CGSizeMake(briefLabel.size.width, labelSize.height);
    }
    
    rowHeight += 8+briefLabel.size.height+8+1+8+21+15+15+20;
    
    return rowHeight;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.tableView.datas.count;
}

- (UITableViewCell *)tableView:(UITableView *)aTableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    static NSString *identifier = @"NewsSubCellID";
    UITableViewCell *cell = [aTableView dequeueReusableCellWithIdentifier:identifier];
    
    
    UILabel *titleLabel = (UILabel *)[cell viewWithTag:1];
    titleLabel.text = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"title"];
    if (1) {
        CGSize labelSize = {0 , 0};
        labelSize = [titleLabel.text sizeWithFont:FONT(15) constrainedToSize:CGSizeMake(titleLabel.size.width, 999) lineBreakMode:UILineBreakModeWordWrap];
        titleLabel.numberOfLines = 0;
        titleLabel.lineBreakMode = UILineBreakModeWordWrap;
        titleLabel.size = CGSizeMake(titleLabel.size.width, labelSize.height);

    }
    
    UILabel *timeLabel = (UILabel *)[cell viewWithTag:2];
    timeLabel.origin = CGPointMake(timeLabel.origin.x, titleLabel.origin.y+titleLabel.size.height+8);
    NSString *timeString = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"pushTime"];
    NSDateFormatter *formatter = [[[NSDateFormatter alloc] init] autorelease];
    formatter.dateFormat = @"yyyy-MM-dd HH:mm:ss";
    NSDate *timeDate = [formatter dateFromString:timeString];
    formatter.dateFormat = @"yyyy年MM月dd日";
    timeLabel.text = [formatter stringFromDate:timeDate];
    
    DONetworkImageView *imageView = (DONetworkImageView *)[cell viewWithTag:3];
    imageView.origin = CGPointMake(imageView.origin.x, timeLabel.origin.y+timeLabel.size.height+8);
    imageView.image = [UIImage imageNamed:@""];
    imageView.defaultImage = [UIImage imageNamed:@"PartyNews_图片@2x.png"];
    [imageView setURLPath:[NSString stringWithFormat:@"%@%@", KImageURL, [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"imgPath"]]];
    imageView.layer.masksToBounds = YES;
    [imageView setImageContentMode:UIViewContentModeScaleToFill];
    
    UILabel *briefLabel = (UILabel *)[cell viewWithTag:4];
    briefLabel.origin = CGPointMake(briefLabel.origin.x, imageView.origin.y+imageView.size.height+8);
    briefLabel.text = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"digest"];
    if (1) {
        CGSize labelSize = {0 , 0};
        labelSize = [briefLabel.text sizeWithFont:FONT(15) constrainedToSize:CGSizeMake(briefLabel.size.width, 999) lineBreakMode:UILineBreakModeWordWrap];
        briefLabel.numberOfLines = 0;
        briefLabel.lineBreakMode = UILineBreakModeWordWrap;
        briefLabel.size = CGSizeMake(briefLabel.size.width, labelSize.height);
    }
    
    UIImageView *lineImage = (UIImageView *)[cell viewWithTag:5];
    lineImage.origin = CGPointMake(lineImage.origin.x, briefLabel.origin.y+briefLabel.size.height+8);
    
    UILabel *aLabel = (UILabel *)[cell viewWithTag:6];
    aLabel.origin = CGPointMake(aLabel.origin.x, lineImage.origin.y+lineImage.size.height+8);
    
    UIImageView *arrowImage = (UIImageView *)[cell viewWithTag:7];
    arrowImage.origin = CGPointMake(arrowImage.origin.x, aLabel.origin.y+6);
    
    UIImageView *backImage = (UIImageView *)[cell viewWithTag:8];
    backImage.size = CGSizeMake(backImage.size.width, aLabel.origin.y+aLabel.size.height+15);
    
    UIImageView *topImage = (UIImageView *)[cell viewWithTag:9];
    if ([[[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"buyTop"]intValue]) {
        topImage.hidden = NO;
    } else {
        topImage.hidden = YES;
    }
    
    return cell;
}

- (void)tableView:(UITableView *)aTableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    
    self.selectTableIndex = [indexPath row];
    [self performSegueWithIdentifier:@"NewsDetailVCID" sender:nil];
}



#pragma mark - Segue

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([segue.identifier isEqualToString:@"NewsDetailVCID"]) {
        NewsDetailVC *vc = (NewsDetailVC *)segue.destinationViewController;
        vc.newsInfoId = [[self.tableView.datas objectAtIndex:self.selectTableIndex] objectForKey:@"newsInfoId"];
        vc.newsInfoType = self.newsInfoType;
        vc.title = [NSString stringWithFormat:@"%@详情", self.title];
    }
}

@end
