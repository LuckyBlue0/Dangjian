#import "DOUILabelAdditions.h"
#import "DOUIViewAdditions.h"
#import "DONSStringAdditions.h"

@implementation UILabel(DOUILabelAdditions)

#pragma mark -
#pragma mark method

-(void)setText:(NSString *)aText 
		  font:(CGFloat)aFont 
		color1:(UIColor*)aColor1 
		color2:(UIColor*)aColor2{
	if ([aText isEmpty]) {
		aText=@"";
	}
	self.text = aText;
	self.font = [UIFont systemFontOfSize:aFont];
	self.textColor = aColor1;
	self.highlightedTextColor = aColor2;
	self.backgroundColor = [UIColor clearColor];  
}
-(void)setAlignment:(UITextAlignment)aAlignment{
	self.textAlignment = aAlignment;
	self.contentMode = UIViewContentModeTop;
	self.lineBreakMode = UILineBreakModeWordWrap;
}
-(void)resizeHeight{
	//**** 动态设定高度 ****
	[self setNumberOfLines:0];
	CGSize maxSize=self.frame.size;
	maxSize.height=100000;
	CGSize autoResize = [self.text sizeWithFont: self.font 
							  constrainedToSize: maxSize 
								  lineBreakMode: UILineBreakModeWordWrap];
	if(autoResize.height>self.height){
		[self setHeight:autoResize.height];
	}
}
-(void)resizeWidth{
	//**** 一行之内 ****
	[self setNumberOfLines:1];
	CGSize maxSize=self.frame.size;
	CGSize autoResize = [self.text sizeWithFont: self.font 
							  constrainedToSize: maxSize 
								  lineBreakMode: UILineBreakModeWordWrap];
	if(autoResize.width<self.width){
		[self setWidth:autoResize.width];
	}
}


@end
