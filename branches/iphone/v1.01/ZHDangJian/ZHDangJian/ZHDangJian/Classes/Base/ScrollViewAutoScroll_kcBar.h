//
//  ScrollViewAutoScroll_kcBar.h
//  THJianDang
//  循环滚动的scrollView
//  Created by kllmctrl on 13-5-27.
//
//

#import <Foundation/Foundation.h>

@interface ScrollViewAutoScroll_kcBar : UIView<UIScrollViewDelegate>{
    
    NSTimer* _timer;
}


@property(nonatomic,retain)UIScrollView *scrollView;
@property(nonatomic,retain)NSArray* contentViews;
@property(nonatomic,assign)UIPageControl* pageControl;

-(void)reloadView;

-(void)start;
-(void)stop;

@end











