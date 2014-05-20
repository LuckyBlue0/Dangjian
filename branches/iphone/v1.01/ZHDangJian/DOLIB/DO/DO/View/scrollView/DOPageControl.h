/*
 * 页视图-点导航
 * Created by kllmctrl on 12-06-13.
 * V1.0
 */

//#import <Foundation/Foundation.h>
//#import <UIKit/UIKit.h>
//
//@interface DOPageControl : UIControl {
//	NSInteger _numberOfPages;
//	NSInteger _currentPage;
//	
//@private
//	NSMutableArray *_imageViewArray;
//    UIImage *_image;
//    UIImage *_focusImage;
//}
//
//@property (nonatomic)       NSInteger numberOfPages;
//@property (nonatomic)       NSInteger currentPage;
//
//- (id)initWithFrame:(CGRect)frame withImage:(UIImage*)aImage withFoucsImage:(UIImage*)aImage2;
//    
//@end


#import <UIKit/UIKit.h>

typedef NS_ENUM(NSUInteger, SMPageControlAlignment) {
	SMPageControlAlignmentLeft = 1,
	SMPageControlAlignmentCenter,
	SMPageControlAlignmentRight
};

typedef NS_ENUM(NSUInteger, SMPageControlVerticalAlignment) {
	SMPageControlVerticalAlignmentTop = 1,
	SMPageControlVerticalAlignmentMiddle,
	SMPageControlVerticalAlignmentBottom
};

@interface DOPageControl : UIControl

@property (nonatomic) NSInteger numberOfPages;
@property (nonatomic) NSInteger currentPage;
@property (nonatomic) CGFloat indicatorMargin							UI_APPEARANCE_SELECTOR; // deafult is 10
@property (nonatomic) CGFloat indicatorDiameter							UI_APPEARANCE_SELECTOR; // deafult is 6
@property (nonatomic) SMPageControlAlignment alignment					UI_APPEARANCE_SELECTOR; // deafult is Center
@property (nonatomic) SMPageControlVerticalAlignment verticalAlignment	UI_APPEARANCE_SELECTOR;	// deafult is Middle

@property (nonatomic, retain) UIImage *pageIndicatorImage				UI_APPEARANCE_SELECTOR;
@property (nonatomic, retain) UIColor *pageIndicatorTintColor			UI_APPEARANCE_SELECTOR; // ignored if pageIndicatorImage is set
@property (nonatomic, retain) UIImage *currentPageIndicatorImage		UI_APPEARANCE_SELECTOR;
@property (nonatomic, retain) UIColor *currentPageIndicatorTintColor	UI_APPEARANCE_SELECTOR; // ignored if currentPageIndicatorImage is set

@property (nonatomic) BOOL hidesForSinglePage;			// hide the the indicator if there is only one page. default is NO
@property (nonatomic) BOOL defersCurrentPageDisplay;	// if set, clicking to a new page won't update the currently displayed page until -updateCurrentPageDisplay is called. default is NO

- (void)updateCurrentPageDisplay;						// update page display to match the currentPage. ignored if defersCurrentPageDisplay is NO. setting the page value directly will update immediately

- (CGRect)rectForPageIndicator:(NSInteger)pageIndex;
- (CGSize)sizeForNumberOfPages:(NSInteger)pageCount;

- (void)setImage:(UIImage *)image forPage:(NSInteger)pageIndex;
- (void)setCurrentImage:(UIImage *)image forPage:(NSInteger)pageIndex;
- (UIImage *)imageForPage:(NSInteger)pageIndex;
- (UIImage *)currentImageForPage:(NSInteger)pageIndex;

- (void)updatePageNumberForScrollView:(UIScrollView *)scrollView;

@end

