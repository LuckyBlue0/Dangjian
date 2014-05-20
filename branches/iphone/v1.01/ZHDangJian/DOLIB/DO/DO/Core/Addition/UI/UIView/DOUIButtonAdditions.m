#import "DOUIButtonAdditions.h"
#import "DONSStringAdditions.h"

@implementation UIButton (DOUIButtonAdditions)

-(void)setTitle:(NSString *)title 
		   font:(CGFloat)aFont 
		 color1:(UIColor*)aColor1 
		 color2:(UIColor*)aColor2{
	if ([title isEmpty]) {
		title=@"";
	}
	[self setTitle:title forState:UIControlStateNormal];
	self.titleLabel.font = [UIFont systemFontOfSize:aFont];
	[self setTitleColor:aColor1 forState:UIControlStateNormal];
	[self setTitleColor:aColor2 forState:UIControlStateHighlighted];
	[self setTitleColor:aColor2 forState:UIControlStateSelected];
}
-(void)setImage1:(NSString*)aImage1 
		  Image2:(NSString*)aImage2{
	[self setImage:[UIImage imageNamed:aImage1] forState:UIControlStateNormal];
	[self setImage:[UIImage imageNamed:aImage2] forState:UIControlStateHighlighted];
	[self setImage:[UIImage imageNamed:aImage2] forState:UIControlStateSelected];
}
-(void)setBGImage1:(NSString*)aImage1 
		  BGImage2:(NSString*)aImage2{
	[self setBackgroundImage:[UIImage imageNamed:aImage1] forState:UIControlStateNormal];
	[self setBackgroundImage:[UIImage imageNamed:aImage2] forState:UIControlStateHighlighted];
	[self setBackgroundImage:[UIImage imageNamed:aImage2] forState:UIControlStateSelected];
}
-(void)setTarget:(id)aTarget 
		  action:(SEL)aAction{
	[self addTarget:aTarget action:aAction forControlEvents:UIControlEventTouchUpInside];
}
@end
