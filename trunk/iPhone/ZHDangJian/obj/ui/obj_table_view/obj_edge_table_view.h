//
//  obj_edge_table_view.h
//  obj
//
//  Created by daoyi on 13-5-7.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

#import "obj_base_table_view.h"
#import "obj_table_header.h"
#import "obj_table_tailer.h"

@interface obj_edge_table_view : obj_base_table_view
@property(retain,nonatomic)obj_table_header* header;
@property(retain,nonatomic)obj_table_tailer* tailer;


@end
