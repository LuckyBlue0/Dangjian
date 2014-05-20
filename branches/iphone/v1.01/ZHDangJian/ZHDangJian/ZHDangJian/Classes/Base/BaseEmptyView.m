

#import "BaseEmptyView.h"

@implementation BaseEmptyView

@synthesize textLabel;

-(void)initView{
    textLabel = [[UILabel alloc] init];
    textLabel.text = @"数据为空";
    textLabel.backgroundColor = CLEAR_COLOR;
    textLabel.lineBreakMode = NSLineBreakByTruncatingTail;
    textLabel.textAlignment = UITextAlignmentCenter;
    textLabel.font = FONT(14);
    textLabel.textColor = GRAY_COLOR;
    [self addSubview:textLabel];
}

-(id)initWithCoder:(NSCoder *)aDecoder{
    if (self=[super initWithCoder:aDecoder]) {
        
        [self initView];
    }
    return self;
}


-(id)initWithFrame:(CGRect)frame{
    if (self=[super initWithFrame:frame]) {
        [self initView];
        
    }
    
    return self;
}


-(void)dealloc{
    DO_RELEASE_SAFELY(textLabel);
    [super dealloc];
}

- (void)layoutSubviews {
    [super layoutSubviews];
    
    textLabel.frame = XYWH(0, self.height/2-10, 320, 20);
    
}



@end
