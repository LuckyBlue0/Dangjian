/**
 *
 * @class:LLTextViewBar
 *
 * @带有placeHolder提示的TextView
 *
 */

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

@interface DOTextViewBar : UITextView {
    NSString *placeholder;
    UIColor *placeholderColor;
    
@private
    UILabel *placeHolderLabel;
}

@property(nonatomic, retain) UILabel *placeHolderLabel;
@property(nonatomic, retain) NSString *placeholder;
@property(nonatomic, retain) UIColor *placeholderColor;

-(void)textChanged:(NSNotification*)notification;

@end

