//
//  obj_table_section.m
//  obj
//
//  Created by daoyi on 13-5-5.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//

#import "obj_table_edge.h"
#import "obj.h"

#define TIP_FONT_SIZE  14

#define ARROW_NAME    @"obj_table_view_black_arrow@2x.png"
#define ARROW_X       30
#define ARROW_MARGIN  2 

#define ANIMATION_TIME 0.4

@implementation obj_table_edge

-(void)dealloc
{
    self.content = nil;
    
    self.indicator = nil;
    self.tip = nil;
    self.arrow = nil;
    [super dealloc];
}

-(id)initWithFrame:(CGRect)frame
{
    if (frame.size.height < TABLE_EDGE_H)
    {
        DB(@"the height of table edge is too small.");
        return nil;
    } 
    
    self = [super initWithFrame:frame];
    if (self)
    { 
        self.arrow_rotate = 0;
        self.height = TABLE_EDGE_H;
        
        self.content = AIFRMAR(UIView, 0, 0, W(self), TABLE_EDGE_H);
        CLEAR(self.content);
        //self.content.backgroundColor = COLOR_RED;
        [self addSubview:self.content];
        
        //tip
        UIFont *t_font = FONT(TIP_FONT_SIZE);
        self.tip = AIFRMAR(UILabel,0,0,W(self),[t_font lineHeight]);
        CLEAR(self.tip);
        self.tip.font = t_font; 
        self.tip.textAlignment = UITextAlignmentCenter;
        [self.content addSubview:self.tip];
        
        //arrow
        CGFloat t_arrow_l = H(self.content) - ARROW_MARGIN * 2;
        self.arrow = AIFRMAR(UIImageView, ARROW_X, ARROW_MARGIN,
                           t_arrow_l, t_arrow_l);
        self.arrow.contentMode = UIViewContentModeScaleAspectFit;
        self.arrow.image = IMG_FROM_OBJ_BUNDLE(ARROW_NAME);
        [self.content addSubview:self.arrow];
        
        //indicator
        self.indicator = INDICATOR_GRAY;
        [self.indicator setCenter:self.arrow.center];
        [self.content addSubview:self.indicator];
    }
    return self;
} 
/*****************************************/
#pragma mark - change status event
/*****************************************/
-(void)set_loading
{
    [self.indicator startAnimating];
    self.arrow.hidden = YES;
    self.arrow.transform = CGAffineTransformMakeRotation(self.arrow_rotate);
}
-(void)set_dragging
{
    VIEW_ANIMATION(ANIMATION_TIME, self.arrow.transform = CGAffineTransformMakeRotation(self.arrow_rotate+PI););
}
-(void)set_normal
{
    [self.indicator stopAnimating];
    self.arrow.hidden = NO;
    VIEW_ANIMATION(ANIMATION_TIME, self.arrow.transform = CGAffineTransformMakeRotation(self.arrow_rotate););
}
@end

 
 
