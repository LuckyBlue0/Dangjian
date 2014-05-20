//
//  obj_edge_table_view.m
//  obj
//
//  Created by daoyi on 13-5-7.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//

#import "obj_edge_table_view.h"
#import "obj.h"

#define EDGE_H 400
#define DRAG_RATE 1
#define LOADING_RATE 1.3
#define ANIMATION_TIME 0.35

@interface obj_edge_table_view ()
{
    BOOL m_is_loading;
    CGFloat m_content_frame_diff;
}
@end

@implementation obj_edge_table_view
/***************************************************/
#pragma mark - init and dealloc
/***************************************************/
-(void)obj_edge_table_view_init
{
    m_is_loading = FALSE;
    
    self.header = AIFRM(obj_table_header, 0, -EDGE_H, W(self), EDGE_H);
    [self addSubview:self.header];
    
    
    self.tailer = AIFRM(obj_table_tailer,
                        0,
                        MAX(H(self),
                            self.contentSize.height),
                        W(self), EDGE_H);
    
    self.tailer.backgroundColor =COLOR_BLUE;
    [self addSubview:self.tailer];
}
- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self)
    {
        [self obj_edge_table_view_init];
    }
    return self;
}
-(void)awakeFromNib
{
    [super awakeFromNib];
    [self obj_edge_table_view_init];
}
/***************************************************/
#pragma mark - UITableView fu
/***************************************************/
-(void)reloadData
{
    [super reloadData];
    m_content_frame_diff = H(self) - self.contentSize.height;
    DB(@"%f", m_content_frame_diff);
    if (m_content_frame_diff > 0)
        self.tailer.hidden = YES;
}
/***************************************************/
#pragma mark - UIScrollView delegate
/***************************************************/
- (void)scrollViewDidScroll:(UIScrollView *)scrollView
{
    [super scrollViewDidScroll:scrollView];
    //add code here
    {
        if (scrollView.contentOffset.y < 0 && !m_is_loading)
        {
            if(self.header.height * DRAG_RATE  < -scrollView.contentOffset.y)
                [self.header set_dragging];
            else
                [self.header set_normal];
        }
        else
        {
            if(self.header.height * DRAG_RATE  < -scrollView.contentOffset.y)
                [self.header set_dragging];
            else
                [self.header set_normal];
        }
        
    }
    if([self isMemberOfClass:[obj_edge_table_view class]])
        METHOD_SAFE(m_delegate, @selector(scrollViewDidScroll:),scrollView);
}
- (void)scrollViewWillEndDragging:(UIScrollView *)scrollView
                     withVelocity:(CGPoint)velocity
              targetContentOffset:(inout CGPoint *)targetContentOffset
{
    if (scrollView.contentOffset.y < - self.header.height)
    {
        m_is_loading = TRUE;
        [self.header set_loading];
        VIEW_ANIMATION(ANIMATION_TIME, scrollView.contentInset = UIEdgeInsetsMake(self.header.height,0,0,0););
    }
    
}
@end
