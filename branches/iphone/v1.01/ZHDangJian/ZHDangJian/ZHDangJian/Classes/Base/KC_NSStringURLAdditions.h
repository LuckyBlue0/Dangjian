
#import "RequstResult.h"


@interface NSString (KC_NSStringURLAdditions)

//加密
-(ASIFormDataRequest*)URLRequest;
-(ASIFormDataRequest*)request_with_json:(NSString*)json;
-(ASIFormDataRequest*)URLPicRequest;

//解密
+(RequstResult*)parseReturnData:(NSData*)aData;

@end


