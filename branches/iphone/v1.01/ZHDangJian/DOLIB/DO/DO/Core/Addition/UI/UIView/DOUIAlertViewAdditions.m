#import "DOUIAlertViewAdditions.h"


@implementation UIAlertView (DOUIAlertViewAdditions)

+(void)showTip:(NSString*)aTipStr{
	UIAlertView *aAlert = [[[UIAlertView alloc] initWithTitle:nil 
													  message:aTipStr 
													 delegate:nil 
											cancelButtonTitle:@"确定" 
											otherButtonTitles:nil] autorelease];
	[aAlert show];
}

+(void)showTip:(NSString*)aTipStr 
	  delegate:(id)aDelegate 
		   tag:(NSInteger)aTag{
	UIAlertView *aAlert = [[[UIAlertView alloc] initWithTitle:nil 
													  message:aTipStr 
													 delegate:aDelegate 
											cancelButtonTitle:@"确定" 
											otherButtonTitles:nil] autorelease];
	aAlert.tag = aTag;
	[aAlert show];
}

@end
