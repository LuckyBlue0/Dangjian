

#import <UIKit/UIKit.h>

@class HPGrowingTextView;
@class HPTextViewInternal;

@protocol HPGrowingTextViewDelegate

@optional
- (BOOL)growingTextViewShouldBeginEditing:(HPGrowingTextView *)growingTextView;
- (BOOL)growingTextViewShouldEndEditing:(HPGrowingTextView *)growingTextView;

- (void)growingTextViewDidBeginEditing:(HPGrowingTextView *)growingTextView;
- (void)growingTextViewDidEndEditing:(HPGrowingTextView *)growingTextView;

- (BOOL)growingTextView:(HPGrowingTextView *)growingTextView shouldChangeTextInRange:(NSRange)range replacementText:(NSString *)text;
- (void)growingTextViewDidChange:(HPGrowingTextView *)growingTextView;

- (void)growingTextView:(HPGrowingTextView *)growingTextView willChangeHeight:(float)height;
- (void)growingTextView:(HPGrowingTextView *)growingTextView didChangeHeight:(float)height;

- (void)growingTextViewDidChangeSelection:(HPGrowingTextView *)growingTextView;
- (BOOL)growingTextViewShouldReturn:(HPGrowingTextView *)growingTextView;
@end

@interface HPGrowingTextView : UIView <UITextViewDelegate> {
	HPTextViewInternal *internalTextView;	
	
	int minHeight;
	int maxHeight;
	
	//class properties
	int maxNumberOfLines;
	int minNumberOfLines;
	
	BOOL animateHeightChange;
	
	//uitextview properties
	NSObject <HPGrowingTextViewDelegate> *delegate;
	NSString *text;
	UIFont *font;
	UIColor *textColor;
	UITextAlignment textAlignment; 
	NSRange selectedRange;
	BOOL editable;
	UIDataDetectorTypes dataDetectorTypes;
	UIReturnKeyType returnKeyType;
    
    UIEdgeInsets contentInset;
}

//real class properties
@property int maxNumberOfLines;
@property int minNumberOfLines;
@property BOOL animateHeightChange;
@property (retain) UITextView *internalTextView;	


//uitextview properties
@property(assign) NSObject<HPGrowingTextViewDelegate> *delegate;
@property(nonatomic,assign) NSString *text;
@property(nonatomic,assign) UIFont *font;
@property(nonatomic,assign) UIColor *textColor;
@property(nonatomic) UITextAlignment textAlignment;    // default is UITextAlignmentLeft
@property(nonatomic) NSRange selectedRange;            // only ranges of length 0 are supported
@property(nonatomic,getter=isEditable) BOOL editable;
@property(nonatomic) UIDataDetectorTypes dataDetectorTypes __OSX_AVAILABLE_STARTING(__MAC_NA, __IPHONE_3_0);
@property (nonatomic) UIReturnKeyType returnKeyType;
@property (assign) UIEdgeInsets contentInset;

//uitextview methods

-(void)setPlaceholder:(NSString*)aPlaceholder;
-(void)setPlaceholderColor:(UIColor*)aColor;

//need others? use .internalTextView
- (BOOL)becomeFirstResponder;
- (BOOL)resignFirstResponder;

- (BOOL)hasText;
- (void)scrollRangeToVisible:(NSRange)range;

@end


//
//
//HPGrowingTextView *textView = [[HPGrowingTextView alloc] initWithFrame:CGRectMake(6, 3, 240, 40)];
//textView.contentInset = UIEdgeInsetsMake(0, 5, 0, 5);
//
//textView.minNumberOfLines = 1;
//textView.maxNumberOfLines = 6;
//textView.returnKeyType = UIReturnKeyGo; //just as an example
//textView.font = [UIFont systemFontOfSize:15.0f];
////textView.delegate = self;
//textView.internalTextView.scrollIndicatorInsets = UIEdgeInsetsMake(5, 0, 5, 0);
//textView.backgroundColor = WHITE_COLOR;
//
//
//[textView setPlaceholder:@"请输入"];
//[textView setPlaceholderColor:RED_COLOR];
//
//textView.text = @"33333333333333333333333333333333333333333";
//textView.userInteractionEnabled = NO;
//
//[self.view addSubview:textView];
//
