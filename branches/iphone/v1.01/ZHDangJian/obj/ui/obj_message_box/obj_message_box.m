//
//  obj_message_box.m
//  THJianDang
//
//  Created by daoyi on 13-4-27.
//
//

#import "obj_message_box.h" 
static UIView*                  g_v_main          = nil;//main content view that added to keywindow
static UIView*                  g_v_middle        = nil;//middle back alpha radius view
static UIActivityIndicatorView* g_aiv_cycle       = nil;//for waiting operation
static UILabel*                 g_lbl_tip         = nil;//show message lable
static NSTimer*                 g_timer_delay     = nil;

#define SCREEN_BLACK_ALPHA       0.05

#define MESSAGE_BOX_RADIUS       8
#define MESSAGE_BOX_ALPHA        0.8

#define MESSAGE_LABLE_FONT_SIZE  15
#define MESSAGE_LABLE_COLOR      COLOR_WHITE
#define MESSAGE_LABLE_FONT       FONT(MESSAGE_LABLE_FONT_SIZE)

#define MESSAGE_LABLE_X 15
#define MESSAGE_LABLE_Y 15
#define MESSAGE_LABLE_MIN_W 35
#define MESSAGE_LABLE_MAX_W 220

#define MESSAGE_AIV_C_Y 35
#define MESSAGE_AIV_H 45

@implementation obj_message_box
/*******************************************************************/
#pragma mark - init
/*******************************************************************/
+(void)static_init
{
    g_v_main = AIF(UIView, SCREEN_BOUNDS);
    CLEAR(g_v_main);
    
    //screen alpha view
    {
        UIView *t_v_screen_alpha = AIFAR(UIView, g_v_main.bounds);
        t_v_screen_alpha.backgroundColor = COLOR_BLACK;
        t_v_screen_alpha.alpha = SCREEN_BLACK_ALPHA;
        [g_v_main addSubview:t_v_screen_alpha];
    }
    
    //middle message box
    {
        g_v_middle = [[UIView alloc] init];
        CLEAR(g_v_middle);
        VIEW_RADIUS(g_v_middle, MESSAGE_BOX_RADIUS);
        [g_v_main addSubview:g_v_middle];
        
        UIView *t_v_middle_alpha = AIFAR(UIView, SCREEN_BOUNDS);
        t_v_middle_alpha.backgroundColor = COLOR_BLACK;
        t_v_middle_alpha.alpha = MESSAGE_BOX_ALPHA;
        [g_v_middle addSubview:t_v_middle_alpha];
    }
    
    g_aiv_cycle = INDICATOR_WHITELARGE;
    [g_v_middle addSubview:g_aiv_cycle];
   
    //lable
    {
        g_lbl_tip = AIFRM(UILabel,MESSAGE_LABLE_X,MESSAGE_LABLE_Y,0,0);
        g_lbl_tip.font = MESSAGE_LABLE_FONT;
        g_lbl_tip.textColor = MESSAGE_LABLE_COLOR;
        g_lbl_tip.textAlignment = UITextAlignmentCenter;
        g_lbl_tip.numberOfLines = 0;
        CLEAR(g_lbl_tip);
        [g_v_middle addSubview:g_lbl_tip];
    }
}
/*******************************************************************/
#pragma mark - change message 
//base uicontrol setting
/*******************************************************************/
+(void)change_message:(NSString *)message
{
    if (IS_EMPTY_NSSTR(message))
        return;
    
    [[KEY_WINDOW first_responder] resignFirstResponder];
    [KEY_WINDOW  addSubview:g_v_main];
    
    NSString *t_msg = [message valid_str];
    g_lbl_tip.text = t_msg;
    
    CGFloat t_str_w = MAX(MIN([t_msg width_for_font:MESSAGE_LABLE_FONT], MESSAGE_LABLE_MAX_W), MESSAGE_LABLE_MIN_W);
    CGFloat t_str_h = [t_msg height_for_font:MESSAGE_LABLE_FONT and_width:t_str_w];
    
    CGFloat t_f_w = t_str_w + MESSAGE_LABLE_X * 2;
    CGFloat t_f_h =  Y(g_lbl_tip) + t_str_h + MESSAGE_LABLE_Y;
    CGFloat t_f_x = (SCREEN_W - t_f_w) / 2;
    CGFloat t_f_y = (SCREEN_H - t_f_h) / 2;
    
    SETFRAME(g_v_middle,t_f_x,t_f_y,t_f_w,t_f_h);
    [g_aiv_cycle setCenter: CGPointMake(W(g_v_middle)/2,MESSAGE_AIV_C_Y)];
    SETFRAME(g_lbl_tip, X(g_lbl_tip), Y(g_lbl_tip), t_str_w, t_str_h);
}
/*******************************************************************/
#pragma mark - show message
/*******************************************************************/
+(void)show_message:(NSString *)message
{
    if (g_v_main == nil)
        [self static_init];
    
    g_aiv_cycle.hidden = YES;
    [g_aiv_cycle stopAnimating];
    SETY(g_lbl_tip, MESSAGE_LABLE_Y);
    [self change_message:message];
}
/*******************************************************************/
+(void)show_message:(NSString *)message last:(CGFloat)last_time
{
    [self show_message:message];
    
    g_timer_delay = [NSTimer scheduledTimerWithTimeInterval:last_time
                                                     target:self
                                                   selector:@selector(hidden)
                                                   userInfo:nil
                                                    repeats:NO];
}
/*******************************************************************/
#pragma mark - loading
/*******************************************************************/
+(void)loading
{
    [self loading_with_message:@"正在加载..."];
}
/*******************************************************************/
+(void)loading_with_message:(NSString*)message
{
    if (g_v_main == nil)
        [self static_init];
     
    [self change_message:message];
   
    g_aiv_cycle.hidden = NO;
    [g_aiv_cycle startAnimating];
    if (Y(g_lbl_tip) < MESSAGE_AIV_H)
    {
        ADDH(g_v_middle, MESSAGE_AIV_H);
        ADDY(g_lbl_tip, MESSAGE_AIV_H);
    }
}
/*******************************************************************/
//hidden 
/*******************************************************************/
+(void)hidden
{
    [g_v_main removeFromSuperview];
    [g_aiv_cycle stopAnimating];
}

@end
