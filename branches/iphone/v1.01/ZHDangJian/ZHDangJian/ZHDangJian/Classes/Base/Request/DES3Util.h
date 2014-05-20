
#import <Foundation/Foundation.h>

@interface DES3Util : NSObject

// 加密方法
+ (NSString*)enDES3:(NSString*)plainText;

// 解密方法
+ (NSString*)deDES3:(NSString*)encryptText;


@end
