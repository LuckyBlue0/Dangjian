#import "KCSearchBar.h"
#import "DOCore.h"

@interface KCSearchBar(private)
- (void)reWriteSearchBar;
@end

@implementation KCSearchBar

@synthesize textField;
@synthesize backgroundView;

- (id)initWithFrame:(CGRect)frame{
	if (self=[super initWithFrame:frame]){
		[self reWriteSearchBar];
	}
	return self;
}

-(void)dealloc{
    DO_RELEASE_SAFELY(textField);
    DO_RELEASE_SAFELY(backgroundView);
    
    [super dealloc];
}

- (void)reWriteSearchBar{
	UITextField *searchField;
	UIView *bgView;
	NSUInteger numViews = [self.subviews count];
	for(int i = 0; i < numViews; i++) {
		if([[self.subviews objectAtIndex:i] isKindOfClass:[UITextField class]]) {
			searchField = [self.subviews objectAtIndex:i];
			if(searchField) {
                self.textField = searchField;
				searchField.textColor = [UIColor blackColor];
				searchField.placeholder = @"";
				searchField.backgroundColor = [UIColor clearColor];
			}
		}
		if ([[self.subviews objectAtIndex:i] isKindOfClass:NSClassFromString(@"UISearchBarBackground")]){
			bgView = [self.subviews objectAtIndex:i];
			if (bgView) {
				[bgView removeFromSuperview];
				UIView *aView = [[[UIView alloc] initWithFrame:self.bounds] autorelease];
				aView.backgroundColor = [UIColor clearColor];
				[self insertSubview:aView atIndex:0];
                self.backgroundView = aView;
			}
			
		}
		
	}
}
@end
