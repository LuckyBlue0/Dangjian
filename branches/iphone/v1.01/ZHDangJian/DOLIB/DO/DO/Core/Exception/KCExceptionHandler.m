//
//  KCExceptionHandler.m
//  KC
//
//  Created by kllmctrl on 13-3-26.
//  Copyright (c) 2013年 kllmctrl. All rights reserved.
//

#import "KCExceptionHandler.h"

NSString *applicationDocumentsDirectory() {
    return [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject];
}

void kcExceptionHandler(NSException *exception) {
    NSArray *arr = [exception callStackSymbols];
    NSString *reason = [exception reason];
    NSString *name = [exception name];
    
    NSString *url = [NSString stringWithFormat:@"=============异常崩溃报告=============\nname:\n%@\nreason:\n%@\ncallStackSymbols:\n%@",
                     name,reason,[arr componentsJoinedByString:@"\n"]];
    NSString *path = [applicationDocumentsDirectory() stringByAppendingPathComponent:@"Exception.txt"];
    [url writeToFile:path atomically:YES encoding:NSUTF8StringEncoding error:nil];
    //除了可以选择写到应用下的某个文件，通过后续处理将信息发送到服务器等
    //还可以选择调用发送邮件的的程序，发送信息到指定的邮件地址
    //或者调用某个处理程序来处理这个信息
}

@implementation KCExceptionHandler

-(NSString *)applicationDocumentsDirectory {
    return [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject];
}

+ (void)setDefaultHandler
{
    NSSetUncaughtExceptionHandler (&kcExceptionHandler);
}

+ (NSUncaughtExceptionHandler*)getHandler
{
    return NSGetUncaughtExceptionHandler();
}

@end

