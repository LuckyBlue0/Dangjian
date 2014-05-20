
#import <UIKit/UIKit.h>


@interface HPTextViewInternal : UITextView {
}

@property(nonatomic, retain) UILabel *placeHolderLabel;
@property(nonatomic, retain) NSString *placeholder;
@property(nonatomic, retain) UIColor *placeholderColor;



@property (nonatomic) BOOL shouldStrikeOut;

@property (nonatomic) BOOL shouldUnderline;

@property (nonatomic) int underLineOffset;


@end
