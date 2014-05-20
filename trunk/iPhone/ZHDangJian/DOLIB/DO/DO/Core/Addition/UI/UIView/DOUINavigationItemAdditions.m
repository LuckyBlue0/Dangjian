#import "DOUINavigationItemAdditions.h"
#import "DOUILabelAdditions.h"


@implementation UINavigationItem (DOUINavigationItemAdditions)

//**** 自定义标题 ****
-(void)setTitle:(NSString*)aTitle 
		  color:(UIColor*)aColor 
		   font:(CGFloat)aFont{
	UILabel *label = [[[UILabel alloc] initWithFrame:CGRectZero] autorelease];
	label.backgroundColor = [UIColor clearColor];
	label.font = [UIFont boldSystemFontOfSize:aFont];
	label.textAlignment = UITextAlignmentCenter;
	label.textColor = aColor;
	self.titleView = label;
	label.text = aTitle;
	[label sizeToFit];
}

//**** 自定义左边按钮 ****
-(void)setLeftBarItem:(id)aTarget 
			   action:(SEL)aAction 
				image:(NSString*)aImage 
		   focusImage:(NSString*)aFocusImage{
	UIImage *image = [UIImage imageNamed:aImage];
	UIImage *focusImage = [UIImage imageNamed:aFocusImage];
	UIButton *aButton = [[[UIButton alloc] initWithFrame:
							 CGRectMake(0, 0, (int)image.size.width,(int)image.size.height)] autorelease];
	[aButton setImage:image forState:UIControlStateNormal];
	[aButton setImage:focusImage forState:UIControlStateHighlighted];
	[aButton addTarget:aTarget action:aAction forControlEvents:UIControlEventTouchUpInside];
	UIBarButtonItem *aBarButtonItem = [[UIBarButtonItem alloc] initWithCustomView:aButton];
	self.leftBarButtonItem = aBarButtonItem;
	[aBarButtonItem release];
}

//**** 自定义右边按钮 ****
-(void)setRightBarItem:(id)aTarget 
				 action:(SEL)aAction 
				  image:(NSString*)aImage 
			 focusImage:(NSString*)aFocusImage{
	UIImage *image = [UIImage imageNamed:aImage];
	UIImage *focusImage = [UIImage imageNamed:aFocusImage];
	UIButton *aButton = [[[UIButton alloc] initWithFrame:
						  CGRectMake(0, 0, (int)image.size.width,(int)image.size.height)] autorelease];
	[aButton setImage:image forState:UIControlStateNormal];
	[aButton setImage:focusImage forState:UIControlStateHighlighted];
	[aButton addTarget:aTarget action:aAction forControlEvents:UIControlEventTouchUpInside];
	UIBarButtonItem *aBarButtonItem = [[UIBarButtonItem alloc] initWithCustomView:aButton];
	self.rightBarButtonItem = aBarButtonItem;
	[aBarButtonItem release];
}

@end




