#import "DOTableRefreshBar.h"
#import <QuartzCore/QuartzCore.h>
#import "DOAddition.h"
#import "DOMacro.h"

#define TEXT_COLOR			[UIColor colorWithRed:0x4E/255.0f green:0x4E/255.0f blue:0x4E/255.0f alpha:1.0]
#define BG_COLOR			[UIColor colorWithRed:0xf3/255.0f green:0xf2/255.0f blue:0xf2/255.0f alpha:1.0]
#define ANIMATION_DURATION	0.18f
#define REFRESH_HIGHT		65.0f
#define BG_HIGHT			300.0f

@interface DOTableRefreshBar()

- (void)setState:(EPullRefreshState)aState;

- (void)updateLastDate;

- (void)showActivity:(BOOL)shouldShow animated:(BOOL)animated;

- (void)setImageFlipped:(BOOL)flipped;

@end



@implementation DOTableRefreshBar
@synthesize delegate = _delegate;

-(id)initWithFrame:(CGRect)frame{
	if (self=[super initWithFrame:frame]) {
		self.backgroundColor = BG_COLOR;
		//**** 状态 ****
        _stateLabel = [[UILabel alloc] initWithFrame:CGRectMake(0, frame.size.height-48, self.frame.size.width, 20)];
        [_stateLabel setText:nil];
        [_stateLabel setFont:[UIFont systemFontOfSize:13]];
        [_stateLabel setTextColor:TEXT_COLOR];
        _stateLabel.backgroundColor = [UIColor clearColor];
        _stateLabel.textAlignment = NSTextAlignmentCenter;
		[self addSubview:_stateLabel];
		
		//**** 更新时间 ****
        _updateLabel = [[UILabel alloc] initWithFrame:CGRectMake(0, frame.size.height-28, self.frame.size.width, 20)];
        [_updateLabel setText:nil];
        [_updateLabel setFont:[UIFont systemFontOfSize:13]];
        [_updateLabel setTextColor:TEXT_COLOR];
        _updateLabel.backgroundColor = [UIColor clearColor];
        _updateLabel.textAlignment = NSTextAlignmentCenter;
		[self addSubview:_updateLabel];
		
		//**** 动画 ****
		_activityView = [[UIActivityIndicatorView alloc] initWithActivityIndicatorStyle:UIActivityIndicatorViewStyleGray];
		_activityView.frame = CGRectMake(30, frame.size.height-48, 20, 20);
		[self addSubview:_activityView];
		
		//**** 箭头 ****
//        _arrowImageView = [[UIImageView alloc] initWithFrame:CGRectMake(30,  frame.size.height-70, 23, 60)];
        _arrowImageView = [[UIImageView alloc] initWithFrame:CGRectMake(frame.size.width/2-115,  frame.size.height-70, 40, 60)];
		[_arrowImageView setImage:[UIImage imageNamed:@"blueArrow.png"]];
		[_arrowImageView layer].transform = CATransform3DMakeRotation(M_PI, 0.0f, 0.0f, 1.0f);
		[self addSubview:_arrowImageView];
		
		//**** 设置开始状态 ****
		[self setState:EPullRefreshNormal];
	}
	return self;
}

-(void)dealloc{
    DO_RELEASE_SAFELY(_stateLabel);
    DO_RELEASE_SAFELY(_updateLabel);
    DO_RELEASE_SAFELY(_arrowImageView);
    DO_RELEASE_SAFELY(_activityView);
    
    [super dealloc];
}


//**** 对外 ****
- (void)setLoading:(BOOL)isLoad{
    if (isLoad) {
        _state = EPullRefreshLoading;
    }
    else {
        _state = EPullRefreshNormal;
    }
}

-(BOOL)getLoading{
	if (_state == EPullRefreshLoading) {
		return YES;
	}
	return NO;
}

-(void)setState:(EPullRefreshState)aState{
	//**** 设置视图状态 ****
	switch(aState){
		case EPullRefreshPulling:
			_stateLabel.text = NSLocalizedString(@"松开即可刷新...", @"松开即可刷新...");
			[self showActivity:NO animated:NO];
			[self setImageFlipped:YES];
			break;
		case EPullRefreshNormal:{
			_stateLabel.text = NSLocalizedString(@"下拉可以刷新...", @"下拉可以刷新...");
			[self showActivity:NO animated:NO];
			[self setImageFlipped:NO];
			break;
		}
		case EPullRefreshLoading:{
			_stateLabel.text = NSLocalizedString(@"加载中...", @"加载中...");
			[self showActivity:YES animated:YES];
			[self setImageFlipped:NO];
			[self updateLastDate];
			break;
		}
		default:
			break;
	}
	_state = aState;
}

//**** 加载提示动画 ****
-(void)showActivity:(BOOL)shouldShow animated:(BOOL)animated {
	if (shouldShow) {
		[_activityView startAnimating];
	} else {
		[_activityView stopAnimating];
	}
	[UIView beginAnimations:nil context:nil];
	[UIView setAnimationDuration:(animated ? ANIMATION_DURATION : 0.0)];
	_arrowImageView.alpha = (shouldShow ? 0.0 : 1.0);
	[UIView commitAnimations];
}

//**** 箭头动画 ****
- (void)setImageFlipped:(BOOL)flipped {
	[UIView beginAnimations:nil context:NULL];
	[UIView setAnimationDuration:ANIMATION_DURATION];
	[_arrowImageView layer].transform = (flipped ?
									 CATransform3DMakeRotation(M_PI * 2, 0.0f, 0.0f, 1.0f) :
									 CATransform3DMakeRotation(M_PI, 0.0f, 0.0f, 1.0f));
	[UIView commitAnimations];
}

- (void)updateLastDate {
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    [formatter setDateFormat:@"MM月dd日 HH:mm"];
    _updateLabel.text = [NSString stringWithFormat:@"%@", [formatter stringFromDate:[NSDate date]]];
    [formatter release];
}

-(void)refreshScrollViewDidScroll:(UIScrollView *)aScrollView{
	if (_state == EPullRefreshLoading){
		//**** 正在加载,不做任何处理 ****
		return;
	}
	else if(aScrollView.isDragging){
		//**** 正在拖动,可以修改状态 ****
		if (_state == EPullRefreshPulling
			&& aScrollView.contentOffset.y > -REFRESH_HIGHT 
			&& aScrollView.contentOffset.y < 0.0f){
			//&& !aLoading){
			[self setState:EPullRefreshNormal];
		} else if (_state == EPullRefreshNormal
				   && aScrollView.contentOffset.y < -REFRESH_HIGHT){
				   //&& !aLoading){
			[self setState:EPullRefreshPulling];
		}
		
	}
}

-(void)refreshScrollViewDidEndDragging:(UIScrollView *)aScrollView{
	//**** 停止拖动时候刷新处理 ****
	if (aScrollView.contentOffset.y < 0){
		if (aScrollView.contentOffset.y < -REFRESH_HIGHT 
			&& _state != EPullRefreshLoading){
			//**** 开始加载 ****
			if ([_delegate respondsToSelector:@selector(refreshTableData)]) {
				[_delegate refreshTableData];
			}
			
			[self setState:EPullRefreshLoading];
			[UIView beginAnimations:nil context:NULL];
			[UIView setAnimationDuration:ANIMATION_DURATION];
			aScrollView.contentInset = UIEdgeInsetsMake(REFRESH_HIGHT, 0.0f, 0.0f, 0.0f);
			[UIView commitAnimations];
		}
		
	}
	
}

-(void)refreshScrollViewDidFinishedLoading:(UIScrollView *)scrollView {	
	//**** 加载完成 ****
	[UIView beginAnimations:nil context:NULL];
	[UIView setAnimationDuration:ANIMATION_DURATION];
	[scrollView setContentInset:UIEdgeInsetsMake(0.0f, 0.0f, 0.0f, 0.0f)];
	[UIView commitAnimations];
	
	//**** 成功时候刷新日期 ****
	//[self updateLastDate];
	
	[self setState:EPullRefreshNormal];
}


@end
