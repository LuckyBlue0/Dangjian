
#import "BaseLoadingView.h"

@implementation BaseLoadingView
@synthesize activityIndicator,textLabel;


-(void)initView{
    textLabel = [[UILabel alloc] init];
    textLabel.text = @"正在加载...";
    textLabel.backgroundColor = CLEAR_COLOR;
    textLabel.lineBreakMode = NSLineBreakByTruncatingTail;
    textLabel.textAlignment = UITextAlignmentLeft;
    textLabel.font = FONT(14);
    textLabel.textColor = GRAY_COLOR;
    [self addSubview:textLabel];
    
    
    activityIndicator = [[UIActivityIndicatorView alloc] initWithActivityIndicatorStyle:
                         UIActivityIndicatorViewStyleGray];
    [activityIndicator startAnimating];
    [self addSubview:activityIndicator];
    
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
    DO_RELEASE_SAFELY(activityIndicator);
    [super dealloc];
}


- (void)layoutSubviews {
    [super layoutSubviews];
    
    textLabel.frame = XYWH(160-10, self.height/2-10, 160-20, 20);
    activityIndicator.frame = XYWH(0, 0, 30, 30);
    activityIndicator.center = CGPointMake(160-25, self.height/2);
    
}





@end
