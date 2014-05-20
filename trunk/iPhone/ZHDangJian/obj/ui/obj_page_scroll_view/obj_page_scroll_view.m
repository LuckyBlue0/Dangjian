//
//  obj_page_scroll_view.m
//  obj
//
//  Created by daoyi on 13-5-3.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//

#import "obj_page_scroll_view.h"

#define PAGE_CONTROL_H 15//36
#define PAGE_CONTROL_W 100//36
#define VIEW_FILL 

@interface obj_page_scroll_view()
{
    BOOL           m_b_has_init;
    int            m_page_num;
    
    UIScrollView*  m_scroll_view;
}
@end

@implementation obj_page_scroll_view
/***************************************************/
#pragma mark - all init funs
/***************************************************/
-(void)dealloc
{
    RN(self.m_page_controller);
    RN(m_scroll_view);
    [super dealloc];
}
/***************************************************/
#pragma mark - all init funs
/***************************************************/
-(void)my_init
{
    //self.backgroundColor = COLOR_DARKGRAY;
    
    self.is_show_page = FALSE;
    self.is_over_show = TRUE;
    
    m_page_num = 0;
    
    m_scroll_view = AIFRM(UIScrollView, 0, 0, W(self), H(self));
    m_scroll_view.pagingEnabled = YES;
    m_scroll_view.showsVerticalScrollIndicator = NO;//|
    m_scroll_view.showsHorizontalScrollIndicator = NO;//--
    m_scroll_view.delegate = self;
    m_scroll_view.clipsToBounds = TRUE;
    [self addSubview:m_scroll_view];
    
    //assume H(self) > PAGE_CONTROL_H
    self.m_page_controller = AIFRM(UIPageControl,
                                 (W(self)-PAGE_CONTROL_W)/2,
                                 H(self) - PAGE_CONTROL_H,
                                 PAGE_CONTROL_W,
                                 PAGE_CONTROL_H); 
    self.m_page_controller.clipsToBounds = TRUE;
    self.m_page_controller.userInteractionEnabled = NO;
    self.m_page_controller.currentPage = 0;
    [self addSubview:self.m_page_controller];
    
    m_b_has_init = TRUE;
}
- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) [self my_init];
    return self;
}
-(void)awakeFromNib
{
    [self my_init];
}
/***************************************************/
#pragma mark - property
/***************************************************/
-(int)current_page
{
    return self.m_page_controller.currentPage;
}
-(int)get_page_num
{
    return m_page_num;
}
/***************************************************/
#pragma mark - funs
/***************************************************/
-(void)addSubview:(UIView *)view
{
    if (m_b_has_init)
    {
        if (self.is_show_page)
        {
            self.m_page_controller.hidden = NO;
            if (self.is_over_show)
                [view setCenter:CGPointMake(W(m_scroll_view)*m_page_num + W(m_scroll_view)/2,
                                            H(m_scroll_view)/2)];
            else
                [view setCenter:CGPointMake(W(m_scroll_view)*m_page_num + W(m_scroll_view)/2,
                                            (H(m_scroll_view) - PAGE_CONTROL_H)/2)];
        }
        else
        {
            self.m_page_controller.hidden = YES;
            [view setCenter:CGPointMake(W(m_scroll_view)*m_page_num + W(m_scroll_view)/2,
                                        H(m_scroll_view)/2)];
        } 
        
        [m_scroll_view addSubview:view];
        m_page_num++;
        m_scroll_view.contentSize = CGSizeMake(m_page_num*W(m_scroll_view),H(m_scroll_view));
        self.m_page_controller.numberOfPages = m_page_num;
    }
    else
        [super addSubview:view];
}
/***************************************************/
#pragma mark - scroll view delegate
/***************************************************/
//call when scrollView stop moving
- (void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView
{
    self.m_page_controller.currentPage = fabs(scrollView.contentOffset.x / scrollView.frame.size.width);
}
@end
