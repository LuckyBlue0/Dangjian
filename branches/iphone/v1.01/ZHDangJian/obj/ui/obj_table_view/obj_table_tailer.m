//
//  obj_table_section_tailer.m
//  obj
//
//  Created by daoyi on 13-5-6.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//

#import "obj_table_tailer.h"
#import "obj.h"

#define TIP_NORMAL     @"上拉加载更多"
#define TIP_DRAGGING   @"松开即可加载"
#define TIP_LOADING    @"正在加载"

#define TIP_Y 12

@implementation obj_table_tailer

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self)
    {
        SETY(self.content, 0);
        
        self.tip.text = TIP_NORMAL;
        SETY(self.tip, TIP_Y);
        
        self.arrow_rotate = PI;
        self.arrow.transform = CGAffineTransformMakeRotation(self.arrow_rotate);
    }
    return self;
} 
/*****************************************/
#pragma mark - change status event
/*****************************************/
-(void)set_loading
{ 
    self.tip.text = TIP_LOADING;
    [super set_loading];
}
-(void)set_dragging
{
    self.tip.text = TIP_DRAGGING;
    [super set_dragging];
}
-(void)set_normal
{
    self.tip.text = TIP_NORMAL;
    [super set_normal];
}
@end
