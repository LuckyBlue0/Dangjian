
#import "KCImagesView.h"
#import "DOCore.h"

@implementation KCImagesView

@synthesize topImage = _topImage;
@synthesize midImage = _midImage;
@synthesize bottomImage = _bottomImage;
@synthesize ishightLevel = _ishightLevel;


-(id)initWithFrame:(CGRect)frame{
    if((self = [super initWithFrame:frame])){
		
		self.backgroundColor = [UIColor clearColor];
		_topImageView = [[UIImageView alloc] initWithFrame:CGRectZero];
		[self addSubview:_topImageView];
		_midImageView = [[UIImageView alloc] initWithFrame:CGRectZero];
		[self addSubview:_midImageView];
		_bottomImageView = [[UIImageView alloc] initWithFrame:CGRectZero];
		[self addSubview:_bottomImageView];
		_ishightLevel = YES;
    }
    return self;
}
- (void)dealloc{
    DO_RELEASE_SAFELY(_topImageView);
    DO_RELEASE_SAFELY(_midImageView);
    DO_RELEASE_SAFELY(_bottomImageView);
    
    [super dealloc];
}

- (void)drawRect:(CGRect)rect{
    [super drawRect:rect];
	
	int hightLevel = 1;
	if (_ishightLevel) {
		hightLevel=2;
	}
	else {
		hightLevel=1;
	}
    
	
	_topImageView.image = _topImage;
	[_topImageView setFrame:CGRectMake((int)(self.frame.size.width-_topImage.size.width/hightLevel)/2, 0, (int)_topImage.size.width/hightLevel, (int)_topImage.size.height/hightLevel)];
	
	_bottomImageView.image = _bottomImage;
	[_bottomImageView setFrame:CGRectMake((int)(self.frame.size.width-_bottomImage.size.width/hightLevel)/2, 0, (int)_bottomImage.size.width/hightLevel,(int) _bottomImage.size.height/hightLevel)];
	
	_midImageView.image = _midImage;
	[_midImageView setFrame:CGRectMake((int)(self.frame.size.width-_midImage.size.width/hightLevel)/2, (int)_topImage.size.height/hightLevel,
									   _midImage.size.width/hightLevel,
									   self.frame.size.height-_topImageView.frame.size.height-_bottomImageView.frame.size.height)];
	
	[_bottomImageView setFrame:CGRectMake((int)(self.frame.size.width-_bottomImage.size.width/hightLevel)/2, _midImageView.frame.origin.y+_midImageView.frame.size.height,
                                          (int)_bottomImage.size.width/hightLevel, (int)_bottomImage.size.height/hightLevel)];
}


@end

