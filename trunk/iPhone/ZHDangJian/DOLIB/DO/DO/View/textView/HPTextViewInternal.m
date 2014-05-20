
#import "HPTextViewInternal.h"


@implementation HPTextViewInternal

@synthesize placeHolderLabel;
@synthesize placeholderColor;
@synthesize placeholder;

@synthesize shouldStrikeOut,
            shouldUnderline,
            underLineOffset;


-(void)awakeFromNib
{
    [super awakeFromNib];
}

- (id)initWithFrame:(CGRect)frame
{
    if( (self = [super initWithFrame:frame]) )
    {
        [self setPlaceholder:@""];
        [self setPlaceholderColor:[UIColor lightGrayColor]];
        //        [self setPlaceholderColor:[UIColor blackColor]];
        [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(textChanged:) name:UITextViewTextDidChangeNotification object:nil];
        
        
        
        //默认
        shouldStrikeOut = YES;
        
        underLineOffset = 10;
        
        
    }
    return self;
}

- (void)dealloc {
    
    [[NSNotificationCenter defaultCenter] removeObserver:self];
    [placeHolderLabel release]; placeHolderLabel = nil;
    [placeholderColor release]; placeholderColor = nil;
    [placeholder release]; placeholder = nil;
    
    [super dealloc];
}



- (void)textChanged:(NSNotification *)notification
{
    [self setNeedsDisplay];
    
    
    if([[self placeholder] length] == 0)
    {
        return;
    }
    
    if([[self text] length] == 0)
    {
        [[self viewWithTag:999] setAlpha:1];
    }
    else
    {
        [[self viewWithTag:999] setAlpha:0];
    }
    
    
    
    
}




-(void)setText:(NSString *)text
{
    BOOL originalValue = self.scrollEnabled;
    //If one of GrowingTextView's superviews is a scrollView, and self.scrollEnabled == NO,
    //setting the text programatically will cause UIKit to search upwards until it finds a scrollView with scrollEnabled==yes
    //then scroll it erratically. Setting scrollEnabled temporarily to YES prevents this.
    [self setScrollEnabled:YES];
    [super setText:text];
    [self setScrollEnabled:originalValue];
    
    [self textChanged:nil];
    
}

-(void)setContentOffset:(CGPoint)s
{
	if(self.tracking || self.decelerating){
		//initiated by user...
        
        UIEdgeInsets insets = self.contentInset;
        insets.bottom = 0;
        insets.top = 0;
        self.contentInset = insets;
        
	} else {

		float bottomOffset = (self.contentSize.height - self.frame.size.height + self.contentInset.bottom);
		if(s.y < bottomOffset && self.scrollEnabled){            
            UIEdgeInsets insets = self.contentInset;
            insets.bottom = 8;
            insets.top = 0;
            self.contentInset = insets;            
        }
	}
    	
	[super setContentOffset:s];
}

-(void)setContentInset:(UIEdgeInsets)s
{
	UIEdgeInsets insets = s;
	
	if(s.bottom>8) insets.bottom = 0;
	insets.top = 0;

	[super setContentInset:insets];
}

-(void)setContentSize:(CGSize)contentSize
{
    // is this an iOS5 bug? Need testing!
    if(self.contentSize.height > contentSize.height)
    {
        UIEdgeInsets insets = self.contentInset;
        insets.bottom = 0;
        insets.top = 0;
        self.contentInset = insets;
    }
    
    [super setContentSize:contentSize];
}


- (void)drawRect:(CGRect)rect
{
    if( [[self placeholder] length] > 0 ){
        if ( placeHolderLabel == nil )
        {
            placeHolderLabel = [[UILabel alloc] initWithFrame:CGRectMake(8,8,self.bounds.size.width - 16,0)];
            
#if __IPHONE_OS_VERSION_MAX_ALLOWED > __IPHONE_6_0
            placeHolderLabel.lineBreakMode = NSLineBreakByWordWrapping;
#else
            placeHolderLabel.lineBreakMode = UILineBreakModeWordWrap;
#endif
            
            placeHolderLabel.numberOfLines = 0;
            placeHolderLabel.font = self.font;
            placeHolderLabel.backgroundColor = [UIColor clearColor];
            placeHolderLabel.textColor = self.placeholderColor;
            
            placeHolderLabel.alpha = 0;
            placeHolderLabel.tag = 999;
            [self addSubview:placeHolderLabel];
        }
        
        placeHolderLabel.text = self.placeholder;
        [placeHolderLabel sizeToFit];
        [self sendSubviewToBack:placeHolderLabel];
    }
    
    if( [[self text] length] == 0 && [[self placeholder] length] > 0 ){
        [[self viewWithTag:999] setAlpha:1];
    }
    
    
//    
//    
//    
//    
//    if (shouldUnderline || shouldStrikeOut)
//    {
//        
//        NSLog(@"draw");
//        CGContextRef ctx = UIGraphicsGetCurrentContext();
//        
//        CGContextSetStrokeColorWithColor(ctx, self.textColor.CGColor);
//        
//        CGContextSetLineWidth(ctx, 1.5f);
//        
//        
//        //calculate line height for some random simbol using its own font.
//        int lineHeight = [@"a" sizeWithFont:self.font constrainedToSize:self.frame.size].height;
//        
//        float mPartOfTextStringWidth = 0.0;
//        
//        //text part between two spaces, for checking if it is in one line.
//        NSString* mPartOfTextString = @"";
//        
//        
//        //we add a space for easier calculations
//        NSString* mTotalTextString = [NSString stringWithFormat:@"%@ ", self.text];
//        
//        
//        //corresponding line we are in.
//        int mCurrentLine = 1;
//        
//        
//        //space char counter
//        int mSpaceChar = 0;
//        
//        
//        //break char counter
//        int mBreakChar = 0;
//        
//        
//        //in case its not Aligned to left side
//        int extraSpaceFromBeginning = 0;
//        
//        
//        //topOffset, if label height is bigger than text height
//        int topOffset = (self.frame.size.height - [mTotalTextString sizeWithFont:self.font
//                                                               constrainedToSize:self.frame.size].height) / 2;
//        
//        if (shouldStrikeOut)//offset to top by 1/3 of line height (but its not 100% perfect..)
//        {
//            topOffset -= lineHeight / 3;
//        }
//        
//        //--- go through text and search for spaces
//        for (int i = 0; i < [mTotalTextString length]; i++)
//        {
//            if ([mTotalTextString characterAtIndex:i] == '\n')
//            {
//                NSLog(@"---1");
//                mSpaceChar = i;
//                
//                //get string from last break char to last space char
//                mPartOfTextString = [[mTotalTextString substringToIndex:i] substringFromIndex:mBreakChar];
//                
//                //calculate precise width
//                mPartOfTextStringWidth = [mPartOfTextString sizeWithFont:self.font
//                                                       constrainedToSize:CGSizeMake(self.frame.size.width, 9999)].width;
//                
//                //--- set extra space from beginning
//                if (self.textAlignment == UITextAlignmentCenter)
//                {
//                    extraSpaceFromBeginning = (self.frame.size.width - mPartOfTextStringWidth) / 2;
//                }
//                else if (self.textAlignment == UITextAlignmentRight)
//                {
//                    extraSpaceFromBeginning = self.frame.size.width - mPartOfTextStringWidth;
//                }
//                //===
//                
//                CGContextMoveToPoint(ctx, extraSpaceFromBeginning,
//                                     lineHeight * mCurrentLine - 1 + underLineOffset + topOffset);
//                
//                CGContextAddLineToPoint(ctx, extraSpaceFromBeginning + mPartOfTextStringWidth,
//                                        lineHeight * mCurrentLine - 1 + underLineOffset + topOffset);
//                
//                mCurrentLine++;
//                
//                mBreakChar = mSpaceChar; //break char is last space char +1.
//                
//                i = mBreakChar; //go back to check again from last breakpoint.
//            }
//            else if ([mTotalTextString characterAtIndex:i] == ' ')
//            {
//                
//                
//                //get string from break char to current character (break char is when new line encountered
//                mPartOfTextString = [[mTotalTextString substringToIndex:i] substringFromIndex:mBreakChar];
//                
//                
//                NSLog(@"---2:==%d:%@",i,mTotalTextString);
//                
//                //calculate width (total width - so we know, it should break!
//                mPartOfTextStringWidth = [mPartOfTextString sizeWithFont:self.font
//                                                       constrainedToSize:CGSizeMake(9999, 9999)].width;
//                
//                //this means that it will not break
//                if (mPartOfTextStringWidth < self.frame.size.width - 20)
//                {
//                    NSLog(@"---2-1");
//                    
//                    mSpaceChar = i;
//                }
//                else //it breaks!!!!
//                {
//                    NSLog(@"---2-2");
//                    //in case a word is longer than label width - disable underlines.
//                    if (mSpaceChar == mBreakChar - 1)
//                    {
//                        mPartOfTextString = @"";
//                    }
//                    else
//                    {
//                        //get string from last break char to last space char
//                        mPartOfTextString = [[mTotalTextString substringToIndex:mSpaceChar] substringFromIndex:mBreakChar];
//                    }
//                    
//                    //calculate precise width
//                    mPartOfTextStringWidth = [mPartOfTextString sizeWithFont:self.font
//                                                           constrainedToSize:CGSizeMake(self.frame.size.width, 9999)].width;
//                    
//                    //--- set extra space from beginning
//                    if (self.textAlignment == UITextAlignmentCenter)
//                    {
//                        extraSpaceFromBeginning = (self.frame.size.width - mPartOfTextStringWidth) / 2;
//                    }
//                    else if (self.textAlignment == UITextAlignmentRight)
//                    {
//                        extraSpaceFromBeginning = self.frame.size.width - mPartOfTextStringWidth;
//                    }
//                    //===
//                    
//                    CGContextMoveToPoint(ctx, extraSpaceFromBeginning,
//                                         lineHeight * mCurrentLine - 1 + underLineOffset + topOffset);
//                    
//                    CGContextAddLineToPoint(ctx, extraSpaceFromBeginning + mPartOfTextStringWidth,
//                                            lineHeight * mCurrentLine - 1 + underLineOffset + topOffset);
//                    
//                    mCurrentLine++;
//                    
//                    mBreakChar = mSpaceChar + 1; //break char is last space char +1.
//                    
//                    i = mBreakChar; //go back to check again from last breakpoint.
//                    
//                    NSLog(@"mCurrentLine:%d",mCurrentLine);
//                    NSLog(@"mBreakChar:%d",mBreakChar);
//                    NSLog(@"mSpaceChar:%d",mSpaceChar);
//                    
//                }
//            }
//            
//            
//            if (i == [mTotalTextString length] - 1)//last line - draw from last break to this char.
//            {
//                
//                NSLog(@"---3");
//                //get string from last break char to last space char
//                mPartOfTextString = [[mTotalTextString substringToIndex:i] substringFromIndex:mBreakChar];
//                
//                
//                //calculate precise width
//                mPartOfTextStringWidth = [mPartOfTextString sizeWithFont:self.font
//                                                       constrainedToSize:CGSizeMake(self.frame.size.width, 9999)].width;
//                
//                
//                //--- set extra space from beginning
//                if (self.textAlignment == UITextAlignmentCenter)
//                {
//                    extraSpaceFromBeginning = (self.frame.size.width - mPartOfTextStringWidth) / 2;
//                }
//                else if (self.textAlignment == UITextAlignmentRight)
//                {
//                    extraSpaceFromBeginning = self.frame.size.width - mPartOfTextStringWidth;
//                }
//                //===
//                
//                CGContextMoveToPoint(ctx, extraSpaceFromBeginning,
//                                     lineHeight * mCurrentLine - 1 + underLineOffset + topOffset);
//                
//                CGContextAddLineToPoint(ctx, extraSpaceFromBeginning + mPartOfTextStringWidth,
//                                        lineHeight * mCurrentLine - 1 + underLineOffset + topOffset);
//            }
//        }
//        //===
//        
//        
//        CGContextStrokePath(ctx);
//    }
//
//    
    
    
    [super drawRect:rect];
    
}





@end
