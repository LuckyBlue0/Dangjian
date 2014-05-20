//
//  obj_add_view.m
//  THJianDang
//
//  Created by daoyi on 13-4-27.
//
//

#import "obj_view.h" 

static void g_enum_all_sub_view(UIView* view, NSString* prefix_str)
{ 
    printf("%s\n",[[NSString stringWithFormat:@"%@|--%@: frame = (%.0f %.0f;  %.0f %.0f)",
                    prefix_str,
                    [view class],
                    X(view),Y(view),W(view),H(view)] UTF8String]);
    
    NSString *sub_prefix_str = [NSString stringWithFormat:@"%@    ",prefix_str]; 
    for (UIView* sub_vew in view.subviews) 
        g_enum_all_sub_view(sub_vew,sub_prefix_str);
}

@implementation UIView(obj)

- (UIView*)first_responder
{
    if ([self isFirstResponder])
        return self;
    
    UIView* t_ret_responder = nil;
    for (UIView* sub_vew in self.subviews)
    {
        if ([sub_vew isFirstResponder])
            return sub_vew;
        
        t_ret_responder = [sub_vew first_responder];
        if (nil != t_ret_responder)
            return t_ret_responder;
    }
    return nil;
}
-(void)remove_top_view
{
    if(self.subviews.count > 0)
        [[self.subviews objectAtIndex:(self.subviews.count - 1)] removeFromSuperview];
}
-(void)remove_bottomv_iew
{
    if(self.subviews.count > 0)
        [[self.subviews objectAtIndex:0] removeFromSuperview];
} 
-(void)move_to_top
{
    [self.superview bringSubviewToFront:self];
}
-(void)move_to_bottom
{
    [self.superview sendSubviewToBack:self];
}
-(void)move_to_above:(UIView*)view
{
    [self.superview insertSubview:self aboveSubview:view]; 
}
-(void)move_to_below:(UIView*)view
{
    [self.superview insertSubview:self belowSubview:view];
}


-(void)enum_all_sub_view
{ 
    g_enum_all_sub_view(self,@"");
}
@end
