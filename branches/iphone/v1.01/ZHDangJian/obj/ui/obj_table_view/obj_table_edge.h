//
//  obj_table_section.h
//  obj
//
//  Created by daoyi on 13-5-5.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//
#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

#define TABLE_EDGE_H         40

@interface obj_table_edge : UIView

@property(retain,nonatomic)UIView *content;

@property(retain,nonatomic)UILabel *tip;
@property(retain,nonatomic)UIImageView *arrow;
@property(retain,nonatomic)UIActivityIndicatorView *indicator;
 
@property(assign,nonatomic)CGFloat arrow_rotate;
@property(assign,nonatomic)CGFloat height;

-(void)set_loading;
-(void)set_dragging;
-(void)set_normal;
@end


