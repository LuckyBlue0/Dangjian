//
//  obj_base_table_view.h
//  obj
//
//  Created by daoyi on 13-5-7.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//
#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

 

//UITableView is a subclass of UIScrollView, so it delegate the callback event
//is also include of scrollview
@interface obj_base_table_view : UITableView<UITableViewDelegate>
{
@protected
    id<UITableViewDelegate>m_delegate;
}

@property(retain,nonatomic)NSArray* data;
@end
