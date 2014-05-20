
#import <Foundation/Foundation.h>

@interface NSString (DONSStringAdditions)


//可以显示的object-->string
+ (NSString*)stringFromObject:(id)object;
- (id)showString;
- (BOOL)isEmpty;

//过滤
- (NSString*)replace:(NSString*)aReplace 
				  to:(NSString*)aTo;
- (NSString*)filterUnKnowJson;
- (NSString*)removingHTMLTags;


//编码
- (id)toUTF8String;
- (NSString*)MD5;
- (NSString*)md5Hash:(NSStringEncoding)enc;
- (id)urlEncoded;

//邮箱合法
- (BOOL)validateEmail;

//电话号码合法
- (BOOL)validetePhoneNumber:(BOOL)isMobile;

//身份证
-(BOOL)valideteManCardID;


-(int)lengthCount;


/*
 -  (int)convertToInt:(NSString*)strtemp {
 
 int strlength = 0;
 char* p = (char*)[strtemp cStringUsingEncoding:NSUnicodeStringEncoding];
 for (int i=0 ; i<[strtemp lengthOfBytesUsingEncoding:NSUnicodeStringEncoding] ;i++) {
 if (*p) {
 p++;
 strlength++;
 }
 else {
 p++;
 }
 }
 return (strength+1)/2;
 
 }
 */

@end





////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@interface NSObject (DONSStringAdditions_NSObject)

-(NSString*)objToString;

@end








