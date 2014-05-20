/*
 * 扩充 NSFileManager
 * Created by kllmctrl on 12-7-11.
 */
#import <Foundation/Foundation.h>


/*************************************************************************************
 *
 *@class NSFileManager (LLNSFileManagerAdditions)
 *
 *************************************************************************************
 */

@interface NSFileManager (DONSFileManagerAdditions)

/**
 * 获取沙盒路径
 */
-(NSString*)getDocumentsPath;

@end