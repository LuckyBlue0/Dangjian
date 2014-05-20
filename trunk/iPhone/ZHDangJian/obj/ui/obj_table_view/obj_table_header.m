//
//  obj_table_section_header.m
//  obj
//
//  Created by daoyi on 13-5-6.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//

#import "obj_table_header.h"
#import "obj.h"

#define TIP_Y     5
#define TIP_NORMAL      @"下拉可以刷新"
#define TIP_DRAGGING    @"松开即可刷新"
#define TIP_LOADING     @"正在加载"

#define TIME_Y    23.5
#define TIME_FONT_SIZE    11
#define TIME_FORMAT     @"最后更新: dd日hh:mm:ss"
 
@implementation obj_table_header

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self)
    {
        SETY(self.content, H(self) - TABLE_EDGE_H);
        //tip
        self.tip.text = TIP_NORMAL;
        SETY(self.tip, TIP_Y);
        
        //time
        UIFont *t_font = FONT(TIME_FONT_SIZE);
        self.time_tip = AIFRMAR(UILabel,0,TIME_Y,
                              W(self),[t_font lineHeight]);
        self.time_tip.font = t_font; 
        CLEAR(self.time_tip);
        self.time_tip.textAlignment = UITextAlignmentCenter;
        [self.content addSubview:self.time_tip];
}
    return self;
}
/*****************************************/
#pragma mark - change status event
/*****************************************/
-(void)update_time
{
    NSDateFormatter *formatter = AIAR(NSDateFormatter);
    formatter.dateFormat = TIME_FORMAT;
    self.time_tip.text = [formatter stringFromDate:[NSDate date]];
}
-(void)set_loading
{
    [self update_time];
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
