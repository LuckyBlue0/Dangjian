
#import "KCPickerTimeBar.h"

@implementation KCPickerTimeBar


@synthesize date;

@synthesize actionSheet;
@synthesize datePicker;

@synthesize delegate;
@synthesize tag;


-(void)dealloc{
    DO_RELEASE_SAFELY(date);
    DO_RELEASE_SAFELY(actionSheet);
    DO_RELEASE_SAFELY(datePicker);
    
    delegate=nil;
    
    [super dealloc];
}

-(void)showInView:(UIView*)aView title:(NSString*)aTitle date:(NSDate*)aDate mode:(UIDatePickerMode)aMode{
    [self retain];
    
    //弹出actionSheet
    self.actionSheet = [[UIActionSheet alloc] initWithTitle:@"\n\n\n\n\n\n\n\n\n\n\n\n\n" delegate:self cancelButtonTitle:nil destructiveButtonTitle:nil otherButtonTitles:nil, nil];
    [self.actionSheet showInView:aView];
    
    
    
    //////////
    //bar
    
    UIViewController *aVC = [[[UIViewController alloc] init] autorelease];
    UINavigationBar *aBar = [[[UINavigationBar alloc] initWithFrame:XYWH(0, 0, 320, 44)] autorelease];
    aBar.tintColor = BLACK_COLOR;
    [aVC.view addSubview:aBar];
    
    
    UINavigationItem *aItem = [[[UINavigationItem alloc] initWithTitle:aTitle] autorelease];
    
    //left
    UIBarButtonItem *aLeftItem = [[[UIBarButtonItem alloc] initWithTitle:@"取消" style:UIBarButtonItemStyleDone target:self action:@selector(cancelAction)] autorelease];
    [aItem setLeftBarButtonItem:aLeftItem];
    
    //right
    UIBarButtonItem *aRightItem = [[[UIBarButtonItem alloc] initWithTitle:@"确定" style:UIBarButtonItemStyleDone target:self action:@selector(OKAction)] autorelease];
    [aItem setRightBarButtonItem:aRightItem];

    
    [aBar setItems:[NSArray arrayWithObjects:aItem, nil]];
    
    
    UIDatePicker *aPicker = [[[UIDatePicker alloc] initWithFrame:XYWH(0, 44, self.actionSheet.width, self.actionSheet.height)] autorelease];
    aPicker.datePickerMode = aMode;
    if (aDate) {
        [aPicker setDate:aDate];
        self.date=aDate;
    }
    [aVC.view addSubview:aPicker];
    self.datePicker = aPicker;
    
    [aVC.view setFrame:XYWH(0, 0, self.actionSheet.width, self.actionSheet.height)];
    [self.actionSheet addSubview:aVC.view];
    
}



#pragma mark -
#pragma mark action

-(void)cancelAction{
    
    [self.actionSheet dismissWithClickedButtonIndex:0 animated:YES];
    
    
    if ([self.delegate respondsToSelector:@selector(didKCPickerTimeBarFinishedCanceled:)]) {
        [self.delegate performSelector:@selector(didKCPickerTimeBarFinishedCanceled:) withObject:self];
    }else{
        [self release];
    }
    
    
}

-(void)OKAction{
    
    [self.actionSheet dismissWithClickedButtonIndex:0 animated:YES];
    
    
    self.date = self.datePicker.date;
    
    if ([self.delegate respondsToSelector:@selector(didKCPickerTimeBarFinishedSeleted:)]) {
        [self.delegate performSelector:@selector(didKCPickerTimeBarFinishedSeleted:) withObject:self];
    }else{
        [self release];
    }
}

@end


