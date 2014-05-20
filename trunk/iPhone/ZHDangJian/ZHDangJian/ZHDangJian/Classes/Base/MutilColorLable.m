

#import "MutilColorLable.h"
#import <CoreText/CoreText.h>

@implementation MutilColorLable


@synthesize attributedDic = _attributedDic;

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        // Initialization code
    }
    return self;
}

- (void) dealloc
{
    [_attributedDic release];
    [_attributedText release];
    
    [super dealloc];
}


#pragma -mark -property
- (void)setAttributedDic:(NSMutableDictionary *)attributedDic {
    _attributedDic = [attributedDic copy];
    
    _attributedText = [[NSMutableAttributedString alloc] initWithString:self.text];
    
    UIColor *colorAll = self.textColor;
    [_attributedText addAttribute:(NSString*)(kCTForegroundColorAttributeName) value:(id)[colorAll CGColor] range:NSMakeRange(0, self.text.length)];
    
    for (NSValue *value in [_attributedDic allKeys]){
        [_attributedText addAttribute:(NSString*)(kCTForegroundColorAttributeName)
                                value:(id)[[_attributedDic objectForKey:value]CGColor]
                                range:[value rangeValue]];
    }

    CTFontRef font_world = CTFontCreateWithName((CFStringRef)[self.font familyName],[self.font pointSize],NULL);
    [_attributedText addAttribute: (NSString*)(kCTFontAttributeName) value:(id)font_world range:NSMakeRange(0,self.text.length)];
    
    CTLineBreakMode lineBreakMode = kCTLineBreakByWordWrapping;//换行模式
    CTTextAlignment alignment = kCTLeftTextAlignment;//对齐方式
    float lineSpacing = 1.0;//行间距
    CTParagraphStyleSetting paraStyles[3] = {
        {.spec = kCTParagraphStyleSpecifierLineBreakMode,.valueSize = sizeof(CTLineBreakMode), .value = (const void*)&lineBreakMode},
        {.spec = kCTParagraphStyleSpecifierAlignment,.valueSize = sizeof(CTTextAlignment), .value = (const void*)&alignment},
        {.spec = kCTParagraphStyleSpecifierLineSpacing,.valueSize = sizeof(CGFloat), .value = (const void*)&lineSpacing},
    };
    CTParagraphStyleRef style = CTParagraphStyleCreate(paraStyles,3);
    [_attributedText addAttribute:(NSString*)(kCTParagraphStyleAttributeName) value:(id)style range:NSMakeRange(0,[self.text length])];
    CFRelease(style);
}

- (void) drawTextInRect:(CGRect)rect {
    CGContextRef context = UIGraphicsGetCurrentContext();
    CGContextSetTextMatrix(context,CGAffineTransformIdentity);//重置
    CGContextTranslateCTM(context,0,self.bounds.size.height); //y轴高度
    CGContextScaleCTM(context,1.0,-1.0);//y轴翻转 它丫的，coortext是反的。
    
    CTFramesetterRef framesetter = CTFramesetterCreateWithAttributedString((CFAttributedStringRef)_attributedText);
    CGMutablePathRef path = CGPathCreateMutable();
    CGPathAddRect(path,NULL,self.bounds);
    CTFrameRef frame = CTFramesetterCreateFrame(framesetter,CFRangeMake(0,0),path,NULL);
    CGContextSetTextPosition(context,0,0);
    CTFrameDraw(frame,context);
    CFRelease(framesetter);
    CFRelease(frame);
    CFRelease(path);
}

@end
