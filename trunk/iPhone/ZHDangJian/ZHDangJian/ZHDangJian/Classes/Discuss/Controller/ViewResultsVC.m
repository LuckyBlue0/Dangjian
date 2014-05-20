//
//  ViewResultsVC.m
//  ZHDangJian
//
//  Created by do1 on 13-11-13.
//
//

#import "ViewResultsVC.h"

@interface ViewResultsVC ()

@end

@implementation ViewResultsVC

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
    
    [self creatExcellentMemListModel];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)viewDidUnload
{
    [self setTableView:nil];
    [self setTopicLabel:nil];
    [self setAttendNumLabel:nil];
    [super viewDidUnload];
}

- (void)dealloc
{
    [_topicId release];
    _excellentMemListModel.delegate = nil;
    DO_RELEASE_SAFELY(_excellentMemListModel);
    [_tableView release];
    [_topicLabel release];
    [_attendNumLabel release];
    [super dealloc];
}


#pragma mark - UITableView Delegate and DataSource

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return self.tableView.datas.count;
}

- (UITableViewCell *)tableView:(UITableView *)aTableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    UITableViewCell *cell = [aTableView dequeueReusableCellWithIdentifier: @"ViewResultListCellID"];
    
    DONetworkImageView *imageView = (DONetworkImageView *)[cell viewWithTag:1];
    imageView.defaultImage = [UIImage imageNamed:@"discuss_default头像@2x.png"];
    [imageView setURLPath:[NSString stringWithFormat:@"%@%@", KImageURL, [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"userImgPath"]]];
    imageView.layer.cornerRadius = 4;
    imageView.layer.masksToBounds = YES;
    [imageView setImageContentMode:UIViewContentModeScaleToFill];
    
    UILabel *nameLabel = (UILabel *)[cell viewWithTag:2];
    nameLabel.text = [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"userName"];
    
    UIImageView *img1 = (UIImageView *)[cell viewWithTag:4];
    NSString *resuleStr = [[[[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"result2"] componentsSeparatedByString:@"."] objectAtIndex:0];
    img1.size = CGSizeMake([resuleStr intValue], img1.size.height);
    
    UILabel *voteNumLabel = (UILabel *)[cell viewWithTag:5];
    voteNumLabel.text = [NSString stringWithFormat:@"%@票", [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"result1"]];
    
    UILabel *supportLabel = (UILabel *)[cell viewWithTag:6];
    supportLabel.text = [NSString stringWithFormat:@"%@支持率", [[self.tableView.datas objectAtIndex:[indexPath row]] objectForKey:@"result2"]];
    
    [self setCellDefaultBg:cell];
    
    return cell;
}



#pragma mark - BaseTableViewDelegate

-(void)baseTableViewHeaderAction:(BaseTableView*)aBaseTableView
{
    [self.tableView resetData];
    [self creatExcellentMemListModel];
}

-(void)baseTableViewFooterAction:(BaseTableView*)aBaseTableView
{
    [self creatExcellentMemListModel];
}

- (void)scrollViewDidScroll:(UIScrollView *)scrollView
{
    [self.tableView scrollViewDidScrollTOBaseTableViewAction];
}

- (void)scrollViewDidEndDragging:(UIScrollView *)scrollView willDecelerate:(BOOL)decelerate
{
    [self.tableView scrollViewDidEndDraggingTOBaseTableViewAction];
}



#pragma mark - Model

- (void)creatExcellentMemListModel
{
    if (_excellentMemListModel) {
        _excellentMemListModel.delegate = nil;
        DO_RELEASE_SAFELY(_excellentMemListModel);
    }
    _excellentMemListModel = [[BaseModel alloc] init];
    _excellentMemListModel.delegate = self;
    
    NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
    [aDic setObject:[DES3Util enDES3:self.topicId] forKey:@"id"];
    [aDic setObject:[self.topicId MD5] forKey:@"digest"];
    
    NSString *requestUrl = [NSString stringWithFormat:KExcellentMemListUrlFormat, KURL, [BaseVC buildJson:aDic]];
    [_excellentMemListModel startRequest:requestUrl];
}

- (void)modelDidStartLoad:(DOModel *)model
{
    [self initHUBTitle:nil subTitle:nil];
}

- (void)modelDidFinishLoad:(DOModel *)model
{
    [self removeHub];
    RequstResult *aRR = [((BaseModel *)model).dataDic objectForKey:@"RR"];
    if (model == self.excellentMemListModel) {
        if (aRR.code) {
            
            self.topicLabel.text = [[aRR.dataDic objectForKey:@"result"] objectForKey:@"voteTopic"];
            self.attendNumLabel.text = [NSString stringWithFormat:@"参与人数：%@人", [[aRR.dataDic objectForKey:@"result"] objectForKey:@"totalCount"]];
            
            if ([[aRR.dataDic objectForKey:@"list"] isKindOfClass:[NSArray class]]) {
                [self.tableView finishLoadingDataAction:[aRR.dataDic objectForKey:@"list"]];
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

@end
