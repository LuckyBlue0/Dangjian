//
//  DiscussMainVC.m
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-11.
//
//

#import "DiscussMainVC.h"

@interface DiscussMainVC ()

@end

@implementation DiscussMainVC

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
    
    self.myScrollView.frame = CGRectMake(0, 0, 320,self.view.size.height);
    _myScrollView.contentSize = CGSizeMake(320, 444);
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)dealloc {
    [_myScrollView release];
    [super dealloc];
}
- (void)viewDidUnload {
    [self setMyScrollView:nil];
    [super viewDidUnload];
}
- (IBAction)PartyMembersReviewButton:(UIButton *)sender {
    [self performSegueWithIdentifier:@"PartyMembersReviewVCID" sender:nil];
}

- (IBAction)democraticAppraisalButton:(UIButton *)sender {
    [self performSegueWithIdentifier:@"DemocraticAppraisalVCID" sender:nil];
}
@end
