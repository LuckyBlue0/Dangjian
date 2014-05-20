//
//  obj_add_view.h
//  THJianDang
//
//  Created by daoyi on 13-4-27.
//
//
#import "obj_ui.h"

@interface UIView (obj)
- (UIView*)first_responder;
-(void)remove_top_view;
-(void)remove_bottomv_iew;
-(void)move_to_top;
-(void)move_to_bottom;
-(void)move_to_above:(UIView*)view;
-(void)move_to_below:(UIView*)view;
-(void)enum_all_sub_view;
@end
