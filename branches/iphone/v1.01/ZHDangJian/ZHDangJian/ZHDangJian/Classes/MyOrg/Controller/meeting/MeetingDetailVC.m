//
//  MeetingDetailVC.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-13.
//
//

#import "MeetingDetailVC.h"

@interface MeetingDetailVC ()

@end

@implementation MeetingDetailVC

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
    [_titleLabel release];
    [_topView release];
    [_topBackImage release];
    [_meetingTypeLabel release];
    [_meetingPersonLabel release];
    [_meetingStartTimeLabel release];
    [_meetingEndTimeLabel release];
    [_meetingAddressLabel release];
    [_contentLabel release];
    [_detailView release];
    [_myScrollView release];
    [super dealloc];
}

- (void)viewDidUnload {
    [self setTitleLabel:nil];
    [self setTopView:nil];
    [self setTopBackImage:nil];
    [self setMeetingTypeLabel:nil];
    [self setMeetingPersonLabel:nil];
    [self setMeetingStartTimeLabel:nil];
    [self setMeetingEndTimeLabel:nil];
    [self setMeetingAddressLabel:nil];
    [self setContentLabel:nil];
    [self setDetailView:nil];
    [self setMyScrollView:nil];
    [super viewDidUnload];
}


#pragma mark - Action

- (void)setDataAndLayout
{
    self.titleLabel.text = [[self.dataDic objectForKey:@"details"] objectForKey:@"title"];
    if (1) {
        CGSize labelSize = {0 , 0};
        labelSize = [self.titleLabel.text sizeWithFont:FONT(15) constrainedToSize:CGSizeMake(300, 9999) lineBreakMode:UILineBreakModeWordWrap];
        self.titleLabel.numberOfLines = 0;
        self.titleLabel.lineBreakMode = UILineBreakModeWordWrap;
        self.titleLabel.size = CGSizeMake(self.titleLabel.size.width, labelSize.height);

    }
    self.detailView.origin = CGPointMake(self.detailView.origin.x, self.titleLabel.origin.y+self.titleLabel.size.height+10);
    self.topBackImage.size = CGSizeMake(320, self.detailView.origin.y+self.detailView.size.height);
    self.contentLabel.origin = CGPointMake(self.contentLabel.origin.x, self.detailView.origin.y+self.detailView.size.height);
    self.meetingTypeLabel.text = [NSString stringWithFormat:@"会议类型：%@", [[self.dataDic objectForKey:@"details"] objectForKey:@"typeDesc"]];
    self.meetingPersonLabel.text = [NSString stringWithFormat:@"发起人：%@", [[self.dataDic objectForKey:@"details"] objectForKey:@"createUserName"]];
    
    NSString *startTimeStr = [[self.dataDic objectForKey:@"details"] objectForKey:@"startTime"];
    NSString *endTimeStr = [[self.dataDic objectForKey:@"details"] objectForKey:@"endTime"];
    NSDateFormatter *dateFormatter = [[[NSDateFormatter alloc] init] autorelease];
    dateFormatter.dateFormat = @"yyyy-MM-dd HH:mm:ss";
    NSDate *startDate = [dateFormatter dateFromString:startTimeStr];
    NSDate *endDate = [dateFormatter dateFromString:endTimeStr];
    dateFormatter.dateFormat = @"yyyy-MM-dd HH:mm";
    self.meetingStartTimeLabel.text = [NSString stringWithFormat:@"开始时间：%@", [dateFormatter stringFromDate:startDate]];
    self.meetingEndTimeLabel.text = [NSString stringWithFormat:@"结束时间：%@", [dateFormatter stringFromDate:endDate]];
    
    self.meetingAddressLabel.text = [NSString stringWithFormat:@"会议地点：%@", [[self.dataDic objectForKey:@"details"] objectForKey:@"address"]];
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
