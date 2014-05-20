

#import "ScrollViewAutoScroll_kcBar.h"

@implementation ScrollViewAutoScroll_kcBar

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
    _scrollView.delegate=nil;
    DO_RELEASE_SAFELY(_scrollView);
    
    [_contentViews release];
    _pageControl=nil;
    
    [_timer invalidate];
    _timer = nil;
    
    [super dealloc];
}

-(void)initView{
    UIScrollView *scrollView=[[[UIScrollView alloc]initWithFrame:self.bounds] autorelease];
    scrollView.pagingEnabled = YES;
    scrollView.showsHorizontalScrollIndicator = NO;
    scrollView.showsVerticalScrollIndicator = NO;
    scrollView.scrollsToTop = NO;
    scrollView.delegate = self;
    
    [self addSubview:scrollView];
    
    self.scrollView = scrollView;
}


-(void)timerAction{
    
    CGPoint pt = _scrollView.contentOffset;
    int count = [_contentViews count];
    if(pt.x == 320 * count){
        [_scrollView setContentOffset:CGPointMake(0, 0)];
        [_scrollView scrollRectToVisible:CGRectMake(320,0,320,self.height) animated:YES];
    }else{
        [_scrollView scrollRectToVisible:CGRectMake(pt.x+320,0,320,self.height) animated:YES];
    }
    
}


-(void)reloadView{
    NSArray *aSubViews = [_scrollView subviews];
    for (int i=0; i<aSubViews.count; i++) {
        UIView *aView = [aSubViews objectAtIndex:i];
        if (aView) {
            [aView removeFromSuperview];
        }
    }
    
    _pageControl.currentPage = 0;
    [_pageControl updateCurrentPageDisplay];
    
    
    
    _scrollView.contentSize = CGSizeMake(_scrollView.width * _contentViews.count,_scrollView.height);
    
    //contentViews
    for (int i=0; i<[_contentViews count]; i++) {
        
        UIView *aView = [_contentViews objectAtIndex:i];
        [aView setWidth:self.width];
        [aView setLeft:self.width*i];
        [aView setHeight:self.height];
        [aView setTop:0];
        [_scrollView addSubview:aView];
    }
    
    [_scrollView setContentOffset:CGPointMake(320, 0)];
}

-(void)start{
    
    [_timer invalidate];
    [_timer release];
    _timer = nil;
    _timer = [[NSTimer scheduledTimerWithTimeInterval:7 target:self selector:@selector(timerAction) userInfo:nil repeats:YES] retain];
    self.scrollView.delegate = self;
}


-(void)stop{
    
    [_timer invalidate];
    self.scrollView.delegate = nil;
}


- (void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView{
    
    int currentPage = floor((scrollView.contentOffset.x - scrollView.width / 2) / scrollView.width) + 1;
    
    if (currentPage==0) {
        [scrollView setContentOffset:CGPointMake(([_contentViews count]-2)*self.width, 0)];
    }
    if (currentPage==([_contentViews count]-1)) {
        [scrollView setContentOffset:CGPointMake(320, 0)];
        
    }
    
    if (_pageControl) {
        _pageControl.currentPage = scrollView.contentOffset.x/scrollView.width-1;
        [_pageControl updateCurrentPageDisplay];
    }
}


- (void)scrollViewDidEndScrollingAnimation:(UIScrollView *)scrollView{
    int currentPage = floor((scrollView.contentOffset.x - scrollView.width / 2) / scrollView.width) + 1;
    
    if (currentPage==0) {
        [scrollView setContentOffset:CGPointMake(([_contentViews count]-2)*self.width, 0)];
    }
    if (currentPage==([_contentViews count]-1)) {
        [scrollView setContentOffset:CGPointMake(320, 0)];
        
    }
    
    if (_pageControl) {
        _pageControl.currentPage = scrollView.contentOffset.x/scrollView.width-1;
        [_pageControl updateCurrentPageDisplay];
    }
    
}


@end



