//
//  obj_communication.h
//  obj
//
//  Created by daoyi on 13-5-8.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//

#import "obj_obj.h"

@interface obj_communication : NSObject
+(void)call:(NSString*)mobile;
+(void)send_msg:(NSString*)mobile;
@end
