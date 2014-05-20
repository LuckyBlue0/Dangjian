//
//  MyOrgQRVC.m
//  THJianDang
//
//  Created by Alex Pong on 13-5-2.
//
//

#import "MyOrgQRVC.h"
#import <QRCodeReader.h>
#import "MeetingSignResultVC.h"
//#import "MyOrgCodeResultSegue.h"
//#import "QRCodeOverlayView.h"

@interface MyOrgQRVC ()

@end

@implementation MyOrgQRVC



- (void)viewDidLoad
{
    [super viewDidLoad];
    
    [self showBack];
     self.title = @"二维码签到";
    if (_ZWController == nil) {
        _ZWController = [[ZXingWidgetController alloc] initWithDelegate:self showCancel:YES OneDMode:NO];
    }
    
    OverlayView *myOverLayView = [[[OverlayView alloc] initWithFrame:[UIScreen mainScreen].bounds cancelEnabled:YES oneDMode:NO] autorelease];
    [myOverLayView setDelegate:_ZWController];
    _ZWController.overlayView = myOverLayView;
    QRCodeReader* qrcodeReader = [[[QRCodeReader alloc] init] autorelease];
    NSSet *myreaders = [[[NSSet alloc] initWithObjects:qrcodeReader,nil] autorelease];
    _ZWController.readers = myreaders;
    NSBundle *mainBundle = [NSBundle mainBundle];
    _ZWController.soundToPlay = [NSURL fileURLWithPath:[mainBundle pathForResource:@"beep-beep" ofType:@"aiff"] isDirectory:NO];
    [self presentModalViewController:_ZWController animated:YES];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(reScan) name:@"reScan" object:nil];
    return;
//    [self.view addSubview:_ZWController.view];
//    [_ZWController viewDidAppear:NO];
    	// Do any additional setup after loading the view.
}

- (void)reScan
{
    [self presentModalViewController:_ZWController animated:YES];
}

-(void)viewDidAppear:(BOOL)animated{
    
    [[UIApplication sharedApplication] setStatusBarHidden:YES];    
    [super viewDidAppear:animated];
}


#pragma mark -
#pragma mark ZXingDelegateMethods
- (void)zxingController:(ZXingWidgetController*)controller didScanResult:(NSString *)result {
	//////////////////////////////////////////////////////////////////////////////
	[self dismissModalViewControllerAnimated:NO];
//    	NSArray *array = [result componentsSeparatedByString:@"\n"];
	self.codeImage = controller.cImage;
    self.codeResult = result;
    if (result) {
        if ([result rangeOfString:@";"].length > 0) {
            //跳到显示结果
            [self dismissModalViewControllerAnimated:YES];
            [self performSegueWithIdentifier:@"MeetingSignResultVCID" sender:self];
        } else {
            [UIAlertView showTip:@"该二维码不可用，请更换二维码重新扫描！"];
            [self dismissModalViewControllerAnimated:YES];
            [self.navigationController popViewControllerAnimated:YES];
        }
    }
 	
	//////////////////////////////////////////////////////////////////////////////
}
- (void)zxingControllerDidCancel:(ZXingWidgetController*)controller {
	[self dismissModalViewControllerAnimated:YES];
    [self.navigationController popViewControllerAnimated:YES];
	
}

#pragma mark - segue



- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    
    if ([segue.identifier isEqualToString:@"MeetingSignResultVCID"]) {
        
        MeetingSignResultVC *vc = (MeetingSignResultVC *)segue.destinationViewController;
        vc.resultStr = self.codeResult;
//        vc.codeImage = self.codeImage;
    }
}

-(void)backAction{
    
    [self.navigationController popViewControllerAnimated:YES];
}
#pragma mark -dealloc
- (void)dealloc {

    [_meetingID release];
    [_codeResult release];
    [_codeImage release];
    [_ZWController release];
    [_type release];
    [super dealloc];
}

- (void)viewDidUnload {

   
    [super viewDidUnload];
}


@end
