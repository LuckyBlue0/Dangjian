//
//  obj_page_scroll_view.h
//  obj
//
//  Created by daoyi on 13-5-3.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//

#import "obj_ui.h"

@interface obj_page_scroll_view : UIView<UIScrollViewDelegate>

@property(nonatomic,assign)BOOL is_show_page;// default true
@property(nonatomic,assign)BOOL is_over_show;// default true: page control show over the custom view

@property(nonatomic,assign,readonly,getter = get_page_num)int  page_num;
@property(nonatomic,readonly,getter = current_page)int current_page;
@property(nonatomic,assign)UIPageControl* m_page_controller;
@end
