/*
 * 扩充 UIFont
 * Created by kllmctrl on 12-7-20.
 */

#import <UIKit/UIKit.h>

/**
 * UIFont 宏
 */
#define FONT(_SIZE) [UIFont systemFontOfSize:_SIZE]



@interface UIFont (DOUIFontAdditions)


- (CGFloat)LLLineHeight;

@end