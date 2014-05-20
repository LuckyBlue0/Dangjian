#import "DOTableFooterBar.h"
#import "DOAddition.h"
#import "DOMacro.h"

@implementation DOTableFooterBar
@synthesize hasMoreData = _hasMoreData;

- (id)initWithFrame:(CGRect)frame {
	if ((self = [super initWithFrame:frame])) {
		_activityView = [[UIActivityIndicatorView alloc] initWithActivityIndicatorStyle:UIActivityIndicatorViewStyleGray];
		_activityView.autoresizingMask = UIViewAutoresizingFlexibleLeftMargin | UIViewAutoresizingFlexibleRightMargin;
		[self addSubview:_activityView];
        
        _tipLabel = [[UILabel alloc] initWithFrame:CGRectMake(0, 10, 320, 20)];
        //[_tipLabel setText:@"更多..."];
        [_tipLabel setText:@""];
        [_tipLabel setFont:[UIFont systemFontOfSize:14]];
        [_tipLabel setTextColor:[UIColor grayColor]];
		_tipLabel.textAlignment = NSTextAlignmentCenter;
		_tipLabel.backgroundColor = [UIColor clearColor];
		[self addSubview:_tipLabel];
		_hasMoreData = YES;
        
        self.backgroundColor = [UIColor clearColor];
	}
	return self;
}

- (void)dealloc{
    DO_RELEASE_SAFELY(_tipLabel);
    DO_RELEASE_SAFELY(_activityView);
    
	[super dealloc];
}

- (void)drawRect:(CGRect)rect{
	CGContextRef contextRef = UIGraphicsGetCurrentContext();
	CGContextSetRGBFillColor(contextRef, 1, 1, 1, 0);
	CGContextFillRect(contextRef, rect);
	if (!_loading) {
		CGFloat dotSize = 0.0f;
		CGFloat x = roundf((self.frame.size.width / 2) - (dotSize / 2));
		CGFloat y = roundf((self.frame.size.height / 2) - (dotSize / 2));
//		CGContextSetRGBFillColor(contextRef, 0.75, 0.75, 0.75, 1.0);
        CGContextSetRGBFillColor(contextRef, 1, 1, 1, 0);
		CGContextFillEllipseInRect(contextRef, CGRectMake(x, y, dotSize, dotSize));
	}
	if (_hasMoreData) {
//		[_tipLabel setText:@"更多..."];
        [_tipLabel setText:@""];
	}else {
		[_tipLabel setText:@""];
	}

}

- (void)layoutSubviews {
	[super layoutSubviews];
	[_activityView setFrame:CGRectMake(roundf((self.frame.size.width / 2) - (_activityView.frame.size.width / 2))-60, 
									   roundf((self.frame.size.height / 2) - (_activityView.frame.size.height / 2)),
									   _activityView.frame.size.width, _activityView.frame.size.height)];
}

- (void)setLoading:(BOOL)loading {
	_loading = loading;
	if (_loading) {
		[_activityView startAnimating];
		
	} else {
		[_activityView stopAnimating];
	}
	[self setNeedsDisplay];
}

@end
