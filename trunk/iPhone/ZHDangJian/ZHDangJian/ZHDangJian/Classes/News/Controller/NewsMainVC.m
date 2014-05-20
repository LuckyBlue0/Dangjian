//
//  NewsMainVC.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-11.
//
//

#import "NewsMainVC.h"
#import "NewsSubVC.h"
#import "NewsDetailVC.h"

@interface NewsMainVC ()

@end

@implementation NewsMainVC

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
    
    _tableView.baseDelegate = self;
    _tableView.hasFooterView = NO;
    _tableView.loadingView.hidden = YES;
    
    _lastListArray = [[NSMutableArray alloc] initWithObjects:@"通知公告", @"工作动态", @"组织风采", @"党员先锋", @"群众路线教育实践", nil];
    _listImageArray = [[NSMutableArray alloc] initWithObjects:@"PartyNews_通知公告@2x.png", @"PartyNews_工作动态@2x.png", @"PartyNews_组织风采@2x.png", @"PartyNews_党员先锋@2x.png", @"PartyNews_群众路线教育实践@2x", nil];
    _imagesArray = [[NSMutableArray alloc] init];
    
    [self creatNewsInfoModel];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(notificationAction:) name:@"RemoteNotification" object:nil];
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    if (_tableView) {
        [_tableView reloadData];
    }
    
    if (_newsScrollView) {
        [_newsScrollView start];
    }
    
}


-(void)viewDidAppear:(BOOL)animated{
    [super viewDidAppear:animated];
    if (_tableView) {
        [_tableView reloadData];
    }
}

-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    if (_newsScrollView) {
        [_newsScrollView stop];
    }
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_tableHeadView release];
    [_newsScrollView release];
    [_newsPageControl release];
    _tableView.dataSource = nil;
    _tableView.delegate = nil;
    _tableView.baseDelegate = nil;
    [_tableView release];
    [_imagesArray release];
    [_lastListArray release];
    [_listImageArray release];
    _newsInfoModel.delegate = nil;
    DO_RELEASE_SAFELY(_newsInfoModel);
    [super dealloc];
}

- (void)viewDidUnload {
    [self setTableHeadView:nil];
    [self setNewsScrollView:nil];
    [self setNewsPageControl:nil];
    [self setTableView:nil];
    [super viewDidUnload];
}


#pragma mark - Layout


-(UIView*)aImageView:(NSInteger)index{
    
    UIView *aView = [[[UIView alloc] initWithFrame:_newsScrollView.bounds] autorelease];
    aView.tag = 10+index;
    UITapGestureRecognizer *aKeyword = [[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(imageAction:)]autorelease];
    [aKeyword setNumberOfTouchesRequired:1];
    aKeyword.cancelsTouchesInView =NO;
    aKeyword.delegate=self;
    [aView addGestureRecognizer:aKeyword];
    
    
    
    //图片
    DONetworkImageView *photoView = [[[DONetworkImageView alloc] initWithFrame:XYWH(0, 0, aView.width, aView.height)] autorelease];
    photoView.backgroundColor = [UIColor clearColor];
    photoView.tag = index;
    
    NSDictionary *currentDic = (NSDictionary*)[self.imagesArray objectAtIndex:index];
    photoView.defaultImage = [UIImage imageNamed:@"PartyNews_图片@2x.png"];
    [photoView setURLPath:[NSString stringWithFormat:@"%@%@", KImageURL, [currentDic objectForKey:@"imgPath"]]];
    [photoView setImageContentMode:UIViewContentModeScaleToFill];
    photoView.layer.cornerRadius = 4;
    photoView.layer.masksToBounds = YES;
    [photoView setImageContentMode:UIViewContentModeScaleToFill];
    
    [aView addSubview:photoView];
    
    //bg
    UIImageView *aImageView = [[[UIImageView alloc] initWithFrame:XYWH(0, aView.height-25, aView.width, 25)] autorelease];
    aImageView.backgroundColor = BLACK_COLOR;
    aImageView.alpha = 0.5;
    [aView addSubview:aImageView];
    
    
    //label
    UILabel *aLabel = [[[UILabel alloc] initWithFrame:XYWH(10, aView.height-23, aView.width-20-70, 20)] autorelease];
    aLabel.textColor = WHITE_COLOR;
    aLabel.backgroundColor =CLEAR_COLOR;
    aLabel.textAlignment = UITextAlignmentLeft;
    aLabel.font = FONT(13);
    aLabel.text = [NSString stringWithFormat:@"%@",[currentDic objectForKey:@"title"]];
    [aView addSubview:aLabel];
    
    
    return aView;
}

-(void)reloadScrollViewData{
    
    NSMutableArray *aViews = [[[NSMutableArray alloc] init] autorelease];
    if (self.imagesArray.count>=1) {
        UIView *aImageView = [self aImageView:self.imagesArray.count-1];
        aImageView.tag = 10+(self.imagesArray.count-1);
        [aImageView setLeft:_newsScrollView.width*(self.imagesArray.count-1)];
        [aViews addObject:aImageView];
    }
    //加入图片视图
    for (int i=0; i<[self.imagesArray count]; i++) {
        
        UIView *aImageView = [self aImageView:i];
        aImageView.tag = 10+i;
        [aViews addObject:aImageView];
        
    }
    if (self.imagesArray.count>=1) {
        UIView *aImageView = [self aImageView:0];
        aImageView.tag = 10+(0);
        [aViews addObject:aImageView];
    }
    _newsScrollView.contentViews = aViews;
    _newsScrollView.pageControl = _newsPageControl;
    [_newsScrollView reloadView];
    [_newsScrollView start];
    
    _newsPageControl.numberOfPages = self.imagesArray.count;
    CGSize pageControlSize = [_newsPageControl sizeForNumberOfPages:self.imagesArray.count];
    [_newsPageControl setWidth:pageControlSize.width];
    [_newsPageControl setLeft:_newsScrollView.width-10-_newsPageControl.width];
    
}


#pragma mark - Action

-(void)imageAction:(UIGestureRecognizer*)aGR{
    int index = aGR.view.tag-10;
    self.selectedImageViewIndex = index;
    [self performSegueWithIdentifier:@"NewsDetailVCID" sender:self];
}

-(void)notificationAction:(NSNotification*)aNotification{
    
    if ([aNotification.name isEqualToString:@"RemoteNotification"]) {
        
        if (_tableView) {
            [_tableView reloadData];
        }
    }
    
}


#pragma mark - Model

- (void)creatNewsInfoModel
{
    if (_newsInfoModel) {
        _newsInfoModel.delegate = nil;
        DO_RELEASE_SAFELY(_newsInfoModel);
    }
    _newsInfoModel = [[BaseModel alloc] init];
    _newsInfoModel.delegate = self;
    
    NSString *requestUrl = [NSString stringWithFormat:KNewsInfoUrlFormat, KURL];
    [_newsInfoModel startRequest:requestUrl];
}

- (void)modelDidStartLoad:(DOModel *)model
{
    [self initHUBTitle:nil subTitle:nil];
}

- (void)modelDidFinishLoad:(DOModel *)model
{
    [self removeHub];
    RequstResult *aRR = [((BaseModel *)model).dataDic objectForKey:@"RR"];
    if (model == self.newsInfoModel) {
        if (aRR.code) {
            if (aRR.dataDic) {
                if ([[aRR.dataDic objectForKey:@"topImgInfos"] isKindOfClass:[NSArray class]]) {
                    self.imagesArray = [NSMutableArray arrayWithArray:[aRR.dataDic objectForKey:@"topImgInfos"]];
                    [self reloadScrollViewData];
                }
                
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

-(void)baseTableViewHeaderAction:(BaseTableView*)aBaseTableView{
    //刷新请求
    [self.tableView resetData];
    [self creatNewsInfoModel];
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
    
    static NSString *identifier = @"NewsMainCellID";
    UITableViewCell *cell = [aTableView dequeueReusableCellWithIdentifier:identifier];
    
    UIImageView *imageView = (UIImageView *)[cell viewWithTag:1];
    imageView.image = [UIImage imageNamed:[self.listImageArray objectAtIndex:[indexPath row]]];
    
    UILabel *titleLabel = (UILabel *)[cell viewWithTag:4];
    titleLabel.text = [self.lastListArray objectAtIndex:[indexPath row]];
    
    UILabel *subTitleLabel = (UILabel *)[cell viewWithTag:101];
    subTitleLabel.text = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"title"];
    
    UILabel *timeLabel = (UILabel *)[cell viewWithTag:100];
    NSString *timeStr = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"pushTime"];
    timeLabel.text = timeStr.length>=10?[timeStr substringToIndex:10]:timeStr;
    
    //背景
    [self setCellDefaultBg:cell];
    
    return cell;
}

- (void)tableView:(UITableView *)aTableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    
    self.selectedTableIndex = [indexPath row];
    [self performSegueWithIdentifier:@"NewsSubVCID" sender:self];
    
}


#pragma mark - Segue

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([segue.identifier isEqualToString:@"NewsSubVCID"]) {
        NewsSubVC *vc = (NewsSubVC *)segue.destinationViewController;
        vc.title = [self.lastListArray objectAtIndex:self.selectedTableIndex];
        vc.newsInfoType = [[self.tableView.datas objectAtIndex:self.selectedTableIndex] objectForKey:@"typeId"];
    }
    if ([segue.identifier isEqualToString:@"NewsDetailVCID"]) {
        NewsDetailVC *vc = (NewsDetailVC *)segue.destinationViewController;
        vc.title = @"热点新闻详情";
        vc.newsInfoId = [[self.imagesArray objectAtIndex:self.selectedImageViewIndex] objectForKey:@"newsInfoId"];
        vc.newsInfoType = @"0";
    }
}


@end
