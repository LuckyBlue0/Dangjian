//
//  LifestyleDetailVC.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-14.
//
//

#import "LifestyleDetailVC.h"

@interface LifestyleDetailVC ()

@end

@implementation LifestyleDetailVC

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
    
    [self setDataAndLayout];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_myScrollView release];
    [_backImageView release];
    [_titleLabel release];
    [_topView release];
    [_activityDetailView release];
    [_contentLabel release];
    [_dataDic release];
    [_activityTypeLabel release];
    [_sendPersonLabel release];
    [_startTimeLabel release];
    [_endTimeLabel release];
    [_addressLabel release];
    [super dealloc];
}

- (void)viewDidUnload {
    [self setMyScrollView:nil];
    [self setBackImageView:nil];
    [self setTitleLabel:nil];
    [self setTopView:nil];
    [self setActivityDetailView:nil];
    [self setContentLabel:nil];
    [self setActivityTypeLabel:nil];
    [self setSendPersonLabel:nil];
    [self setStartTimeLabel:nil];
    [self setEndTimeLabel:nil];
    [self setAddressLabel:nil];
    [super viewDidUnload];
}


#pragma mark - Action

- (void)setDataAndLayout
{
    self.titleLabel.text = [[self.dataDic objectForKey:@"details"] objectForKey:@"title"];
    self.activityDetailView.origin = CGPointMake(self.activityDetailView.origin.x, self.titleLabel.origin.y+self.titleLabel.size.height+10);
    self.backImageView.size = CGSizeMake(320, self.activityDetailView.origin.y+self.activityDetailView.size.height);
    self.contentLabel.origin = CGPointMake(self.contentLabel.origin.x, self.activityDetailView.origin.y+self.activityDetailView.size.height);
    self.activityTypeLabel.text = [NSString stringWithFormat:@"会议类型：%@", [[self.dataDic objectForKey:@"details"] objectForKey:@"typeDesc"]];
    self.sendPersonLabel.text = [NSString stringWithFormat:@"发起人：%@", [[self.dataDic objectForKey:@"details"] objectForKey:@"createUserName"]];
    
    NSString *startTimeStr = [[self.dataDic objectForKey:@"details"] objectForKey:@"startTime"];
    NSString *endTimeStr = [[self.dataDic objectForKey:@"details"] objectForKey:@"endTime"];
    NSDateFormatter *dateFormatter = [[[NSDateFormatter alloc] init] autorelease];
    dateFormatter.dateFormat = @"yyyy-MM-dd HH:mm:ss";
    NSDate *startDate = [dateFormatter dateFromString:startTimeStr];
    NSDate *endDate = [dateFormatter dateFromString:endTimeStr];
    dateFormatter.dateFormat = @"yyyy-MM-dd HH:mm";
    self.startTimeLabel.text = [NSString stringWithFormat:@"开始时间：%@", [dateFormatter stringFromDate:startDate]];
    self.endTimeLabel.text = [NSString stringWithFormat:@"结束时间：%@", [dateFormatter stringFromDate:endDate]];
    
    self.addressLabel.text = [NSString stringWithFormat:@"会议地点：%@", [[self.dataDic objectForKey:@"details"] objectForKey:@"address"]];
    self.contentLabel.text = [[self.dataDic objectForKey:@"details"] objectForKey:@"content"];
    if (1) {
        CGSize labelSize = {0 , 0};
        labelSize = [self.contentLabel.text sizeWithFont:FONT(15) constrainedToSize:CGSizeMake(300, 9999) lineBreakMode:UILineBreakModeWordWrap];
        self.contentLabel.numberOfLines = 0;
        self.contentLabel.lineBreakMode = UILineBreakModeWordWrap;
        self.contentLabel.size = CGSizeMake(self.contentLabel.size.width, labelSize.height);
    }
    if (self.contentLabel.origin.y+self.contentLabel.size.height > self.view.size.height) {
        self.myScrollView.contentSize = CGSizeMake(320, self.contentLabel.origin.y+self.contentLabel.size.height);
        self.myScrollView.scrollEnabled = YES;
    } else {
        self.myScrollView.contentSize = CGSizeMake(320, self.view.size.height);
        self.myScrollView.scrollEnabled = NO;
    }
    
}
@end
