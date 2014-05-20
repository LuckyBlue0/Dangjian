//
//  obj_message_box.h
//  THJianDang
//
//  Created by daoyi on 13-4-27.
//
//
#import "obj_ui.h"

@interface obj_message_box : NSObject

+(void)change_message:(NSString *)message;

+(void)show_message:(NSString *)message;
+(void)show_message:(NSString *)message last:(CGFloat)last_time;

+(void)loading;
+(void)loading_with_message:(NSString*)message;

+(void)hidden;

@end
