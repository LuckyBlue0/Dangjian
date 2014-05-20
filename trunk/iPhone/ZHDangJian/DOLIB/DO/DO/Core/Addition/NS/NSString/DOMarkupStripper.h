/*
 * 隐藏该文件
 * @class LLMarkupStripper
 * @描述:剥离标签
 *Created by liming on 12-06-08.
 */


#import <Foundation/Foundation.h>

#if __IPHONE_4_0 && __IPHONE_4_0 <= __IPHONE_OS_VERSION_MAX_ALLOWED
@interface DOMarkupStripper : NSObject <NSXMLParserDelegate> {
#else
	@interface DOMarkupStripper : NSObject {
#endif
	@private
		NSMutableArray* _strings;
	}
	
	/**
	 * Strips markup from the given string and returns the result.
	 */
	- (NSString*)parse:(NSString*)string;
	
	@end