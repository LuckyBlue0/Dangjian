/**
 * 可以分页的视图
 * OK
 * 
 */

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

@protocol DOScrollViewDelegate;
@protocol DOScrollViewDataSource;

@interface DOScrollView : UIView {
	NSInteger       _centerPageIndex;
	NSInteger       _visiblePageIndex;
	BOOL            _scrollEnabled;
	BOOL            _zoomEnabled;
	BOOL            _rotateEnabled;
	CGFloat         _pageSpacing;
	NSTimeInterval  _holdsAfterTouchingForInterval;
	
	UIInterfaceOrientation  _orientation;
	
	id<DOScrollViewDelegate>    _delegate;
	id<DOScrollViewDataSource>  _dataSource;
	
	NSMutableArray* _pages;
	NSMutableArray* _pageQueue;
	NSInteger       _maxPages;
	NSInteger       _pageArrayIndex;
	NSTimer*        _tapTimer;
	NSTimer*        _holdingTimer;
	NSTimer*        _animationTimer;
	NSDate*         _animationStartTime;
	NSTimeInterval  _animationDuration;
	UIEdgeInsets    _animateEdges;
	
	// Speed for Inertia.
	CGPoint      _inertiaSpeed;
	CGPoint      _renewPosition;
	
	// A floating-point value that specifies the maximum scale factor that can be applied to
	// the scroll view's content.
	CGFloat _maximumZoomScale;
	CGFloat _zoomScale;
	
	// Middle point bewteen fingers, is used to zoom using fingers positon and not the center
	// of the image.
	CGPoint centerOfFingers;
	
	// Distance between fingers, used to calculate zoom scale and zoom speed rate.
	CGFloat actualDistanceBetweenFingers;
	CGFloat distanceBetweenFingers;
	
	// A floating-point value that determines the rate of deceleration after the user lifts
	// their finger.
	CGFloat _decelerationRate;
	
	// The offset of the page edges from the edge of the screen.
	UIEdgeInsets    _pageEdges;
	
	// At the beginning of an animation, the page edges are cached within this member.
	UIEdgeInsets    _pageStartEdges;
	
	UIEdgeInsets    _touchEdges;
	UIEdgeInsets    _touchStartEdges;
	NSUInteger      _touchCount;
	CGFloat         _overshoot;
	
	// Scroll animation.
	// Set the engine to animate the next relayout.
	BOOL _nextLayoutAnimated;
	NSTimeInterval _centerPageAnimationDuration;
	
    // The first touch in this view.&The second touch in this view.
	UITouch*        _touch1;
	UITouch*        _touch2;
	
	BOOL            _dragging;
	BOOL            _decelerating;
	BOOL            _zooming;
	BOOL            _executingZoomGesture;
	BOOL            _holding;
}


@property (nonatomic) NSInteger centerPageIndex;//中页
@property (assign) NSTimeInterval centerPageAnimationDuration;//动画
@property (nonatomic, readonly) BOOL zoomed;
@property (nonatomic, readonly) BOOL zooming;
@property (readonly) BOOL isDragging;
@property (nonatomic, readonly) BOOL holding;
@property (nonatomic,readonly,getter=isDecelerating) BOOL decelerating;
@property (nonatomic) BOOL scrollEnabled;
@property (nonatomic) BOOL zoomEnabled;
@property (nonatomic) BOOL rotateEnabled;
@property (nonatomic) CGFloat pageSpacing;

@property (nonatomic)           UIInterfaceOrientation  orientation;
@property (nonatomic, readonly) NSInteger               numberOfPages;
@property (nonatomic, readonly) UIView*                 centerPage;
@property (nonatomic) NSTimeInterval holdsAfterTouchingForInterval;
@property CGFloat decelerationRate;
@property (nonatomic,assign) CGFloat zoomScale;
@property (nonatomic) CGFloat maximumZoomScale;

@property (nonatomic, assign) id<DOScrollViewDelegate>    delegate;
@property (nonatomic, assign) id<DOScrollViewDataSource>  dataSource;
@property (nonatomic, readonly) NSDictionary* visiblePages;


- (void)setOrientation:(UIInterfaceOrientation)orientation animated:(BOOL)animated;
- (void)setZoomScale:(CGFloat)newScale animated:(BOOL)animated;
- (void)setZoomScale:(CGFloat)newScale withPoint:(CGPoint)withPoint animated:(BOOL)animated;
- (UIView*)dequeueReusablePage;

- (void)reloadData;

- (UIView*)pageAtIndex:(NSInteger)pageIndex;

- (void)zoomToFit;
- (void)zoomToDistance:(CGFloat)distance;
- (void)setCenterPageIndex:(NSInteger)centerPageIndex animated:(BOOL)animated;
- (void)cancelTouches;
@end




@protocol DOScrollViewDelegate <NSObject>

@required
- (void)scrollView:(DOScrollView*)scrollView didMoveToPageAtIndex:(NSInteger)pageIndex;
@optional

- (void)scrollViewWillRotate: (DOScrollView*)scrollView
               toOrientation: (UIInterfaceOrientation)orientation;
- (void)scrollViewDidRotate:(DOScrollView*)scrollView;
- (void)scrollViewWillBeginDragging:(DOScrollView*)scrollView;
- (void)scrollViewDidEndDragging:(DOScrollView*)scrollView willDecelerate:(BOOL)willDecelerate;
- (void)scrollViewWillBeginDecelerating:(DOScrollView*)scrollView;
- (void)scrollViewDidEndDecelerating:(DOScrollView*)scrollView;

- (BOOL)scrollViewShouldZoom:(DOScrollView*)scrollView;
- (void)scrollViewDidBeginZooming:(DOScrollView*)scrollView;
- (void)scrollViewDidEndZooming:(DOScrollView*)scrollView;

- (void)scrollView:(DOScrollView*)scrollView touchedDown:(UITouch*)touch;
- (void)scrollView:(DOScrollView*)scrollView touchedUpInside:(UITouch*)touch;
- (void)scrollView:(DOScrollView*)scrollView tapped:(UITouch*)touch;

- (void)scrollViewDidBeginHolding:(DOScrollView*)scrollView;
- (void)scrollViewDidEndHolding:(DOScrollView*)scrollView;

- (BOOL)scrollView:(DOScrollView*)scrollView 
shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)orientation;
@end



@protocol DOScrollViewDataSource <NSObject>
- (NSInteger)numberOfPagesInScrollView:(DOScrollView*)scrollView;
- (UIView*)scrollView:(DOScrollView*)scrollView pageAtIndex:(NSInteger)pageIndex;
- (CGSize)scrollView:(DOScrollView*)scrollView sizeOfPageAtIndex:(NSInteger)pageIndex;
@end


/**
 LLScrollView *aLLScrollView = [[LLScrollView alloc] initWithFrame:CGRectMake(10, 10, 300, 400)];
 aLLScrollView.delegate = self;
 aLLScrollView.dataSource = self;
 aLLScrollView.rotateEnabled = NO;
 aLLScrollView.backgroundColor = [UIColor blackColor];
 aLLScrollView.autoresizingMask = UIViewAutoresizingFlexibleWidth|UIViewAutoresizingFlexibleHeight;
 [self.view addSubview:aLLScrollView];
 
 *
 *代理
 #pragma mark -
 #pragma mark Delegate
 - (void)scrollView:(LLScrollView*)scrollView didMoveToPageAtIndex:(NSInteger)pageIndex {
 NSLog(@"第%d页",pageIndex);
 }
 #pragma mark -
 #pragma mark DataSource
 - (NSInteger)numberOfPagesInScrollView:(LLScrollView*)scrollView {
 return 2;
 }
 - (UIView*)scrollView:(LLScrollView*)scrollView pageAtIndex:(NSInteger)pageIndex {
 UIImageView *photoView = [[[UIImageView alloc] initWithFrame:CGRectMake(20, 20, 100, 100)] autorelease];
 photoView.backgroundColor = RED_COLOR;
 photoView.image = [UIImage imageNamed:@"a.png"];
 return photoView;
 }
 - (CGSize)scrollView:(LLScrollView*)scrollView sizeOfPageAtIndex:(NSInteger)pageIndex {
 return CGSizeMake(100, 100);
 }

 }
 *
 */


