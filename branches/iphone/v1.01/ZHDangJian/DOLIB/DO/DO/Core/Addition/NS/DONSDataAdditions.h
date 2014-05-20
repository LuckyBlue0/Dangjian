/*
 * 扩充 NSData
 * Created by kllmctrl on 12-4-16.
 */

#import <Foundation/Foundation.h>

extern size_t EstimateBase64EncodedDataSize(size_t inDataSize);
extern size_t EstimateBase64DecodedDataSize(size_t inDataSize);

extern bool Base64EncodeData(const void *inInputData, size_t inInputDataSize, char *outOutputData, size_t *ioOutputDataSize, BOOL wrapped);
extern bool Base64DecodeData(const void *inInputData, size_t inInputDataSize, void *ioOutputData, size_t *ioOutputDataSize);



/*************************************************************************************
 *
 *@class NSData (LLNSDataAdditions)
 *
 *************************************************************************************
 */
@interface NSData (DONSDataAdditions)

- (NSString*)md5Hash;

//base64 转化
+ (NSData*)dataWithBase64EncodedString:(NSString *)string;     //  Padding '=' characters are optional. Whitespace is ignored.
- (NSString *)toBase64Encoding;

//AES加密解密
- (NSData *)AES256EncryptWithKey:(NSString *)key;   //加密
- (NSData *)AES256DecryptWithKey:(NSString *)key;   //解密


@end

