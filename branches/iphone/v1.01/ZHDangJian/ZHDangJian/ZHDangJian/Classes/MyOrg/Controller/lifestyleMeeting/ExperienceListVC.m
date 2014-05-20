//
//  ExperienceListVC.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-14.
//
//

#import "ExperienceListVC.h"
#import "WriteExperienceVC.h"

@interface ExperienceListVC ()

@end

@implementation ExperienceListVC

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
    [self showNavBtn:72 target:self action:@selector(gotoWriteExperiencePage) title:@"写心得"];
    
    _tableView.baseDelegate = self;
    _tableView.loadingView.hidden = YES;
    
    [self creatListModel];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(refeshTableList) name:@"submitExperience" object:nil];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_tableView release];
    [_idString release];
    _experienceListModel.delegate = nil;
    DO_RELEASE_SAFELY(_experienceListModel);
    [_titleString release];
    [[NSNotificationCenter defaultCenter] removeObserver:self];
    [super dealloc];
}

- (void)viewDidUnload {
    [self setTableView:nil];
    [super viewDidUnload];
}


#pragma mark - Action

- refeshTableList
{
    [self.tableView resetData];
    [self creatListModel];
}

- (void)gotoWriteExperiencePage
{
    [self performSegueWithIdentifier:@"WriteExperienceVCID" sender:nil];
}


#pragma mark - UITableView DataSource and Delegate

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.tableView.datas.count;
}

- (UITableViewCell *)tableView:(UITableView *)aTableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    static NSString *identifier = @"experienceListCellID";
    UITableViewCell *cell = [aTableView dequeueReusableCellWithIdentifier:identifier];
    
    DONetworkImageView *photoImage = (DONetworkImageView *)[cell viewWithTag:2];
    photoImage.image = [UIImage imageNamed:@""];
    photoImage.defaultImage = [UIImage imageNamed:@""];
    [photoImage setURLPath:[NSString stringWithFormat:@"%@%@", KImageURL, [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"portraitPic"]]];
    photoImage.layer.cornerRadius = 4;
    photoImage.layer.masksToBounds = YES;
    [photoImage setImageContentMode:UIViewContentModeScaleToFill];
    
    UILabel *nameLabel = (UILabel *)[cell viewWithTag:3];
    nameLabel.text = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"userName"];
    
    UILabel *timeLabel = (UILabel *)[cell viewWithTag:4];
    timeLabel.text = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"createTime"];
    NSDateFormatter *formatter = [[[NSDateFormatter alloc] init] autorelease];
    formatter.dateFormat = @"yyyy-MM-dd HH:mm:ss";
    NSDate *aDate = [formatter dateFromString:timeLabel.text];
    formatter.dateFormat = @"yyyy/MM/dd HH:mm";
    timeLabel.text = [formatter stringFromDate:aDate];
    
    UILabel *sourceLabel = (UILabel *)[cell viewWithTag:5];
    sourceLabel.text = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"sourceDesc"];
    
    UILabel *contentLabel = (UILabel *)[cell viewWithTag:6];
    contentLabel.text = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"content"];
    if (1) {
        CGSize labelSize = { 0 , 0 };
        labelSize = [contentLabel.text sizeWithFont:FONT(12) constrainedToSize:CGSizeMake(280, 9999) lineBreakMode:UILineBreakModeWordWrap];
        contentLabel.numberOfLines = 0;
        contentLabel.lineBreakMode = UILineBreakModeWordWrap;
        contentLabel.size = CGSizeMake(contentLabel.size.width, labelSize.height);
    }
    
    UIImageView *backImage = (UIImageView *)[cell viewWithTag:1];
    backImage.size = CGSizeMake(backImage.size.width, contentLabel.origin.y+contentLabel.size.height+10);
    
    
    return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    float rowHeight = 0;
    UILabel *aLabel = [[[UILabel alloc] initWithFrame:XYWH(0, 0, 280, 21)] autorelease];
    aLabel.text = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"content"];
    if (1) {
        CGSize labelSize = { 0 , 0 };
        labelSize = [aLabel.text sizeWithFont:FONT(12) constrainedToSize:CGSizeMake(280, 9999) lineBreakMode:UILineBreakModeWordWrap];
        aLabel.numberOfLines = 0;
        aLabel.lineBreakMode = UILineBreakModeWordWrap;
        aLabel.size = CGSizeMake(aLabel.size.width, labelSize.height);
    }
    
    rowHeight += 10+60+9+aLabel.size.height+10+10;
    return rowHeight;
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


#pragma mark - Model

- (void)creatListModel
{
    if (_experienceListModel) {
        _experienceListModel.delegate = nil;
        DO_RELEASE_SAFELY(_experienceListModel);
    }
    _experienceListModel = [[BaseModel alloc] init];
    _experienceListModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:self.idString] forKey:@"activityId"];
    [aDic setObject:[DES3Util enDES3:@"0"] forKey:@"type"];
    [aDic setObject:[DES3Util enDES3:[NSString stringWithFormat:@"%i", self.tableView.currentPage]] forKey:@"pageIndex"];
    [aDic setObject:[DES3Util enDES3:@"10"] forKey:@"pageSize"];
    [aDic setObject:[[NSString stringWithFormat:@"%@0%i10", self.idString, self.tableView.currentPage] MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KActivityStudyListUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_experienceListModel startRequest:requestUrl];
}

- (void)modelDidStartLoad:(DOModel *)model
{
    [self initHUBTitle:nil subTitle:nil];
}

- (void)modelDidFinishLoad:(DOModel *)model
{
    [self removeHub];
    RequstResult *aRR = [((BaseModel *)model).dataDic objectForKey:@"RR"];
    if (model == self.experienceListModel) {
        if (aRR.code) {
            
            self.tableView.totalPage = [[aRR.dataDic objectForKey:@"totalPage"] intValue];
            
            if ([[aRR.dataDic objectForKey:@"pageData"] isKindOfClass:[NSArray class]]) {
                [self.tableView finishLoadingDataAction:[aRR.dataDic objectForKey:@"pageData"]];
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


#pragma mark - Segue

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([segue.identifier isEqualToString:@"WriteExperienceVCID"]) {
        WriteExperienceVC *vc = (WriteExperienceVC *)segue.destinationViewController;
        vc.activtyId = self.idString;
        vc.titleString = self.titleString;
    }
}

@end
