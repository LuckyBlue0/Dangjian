//
//  obj_communication.m
//  obj
//
//  Created by daoyi on 13-5-8.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//

#import "obj_communication.h"

@implementation obj_communication
+(void)call:(NSString*)mobile
{
    [[UIApplication sharedApplication]openURL:[NSURL URLWithString:[NSString stringWithFormat:@"tel://%@",mobile]]];
}
+(void)send_msg:(NSString*)mobile
{ 
    [[UIApplication sharedApplication]openURL:[NSURL URLWithString:[NSString stringWithFormat:@"sms://%@",mobile]]];
}
@end
