#import "DOUIFontAdditions.h"

@implementation UIFont(DOUIFontAdditions)

- (CGFloat)LLLineHeight {
    return (self.ascender - self.descender) + 1;
}
@end
