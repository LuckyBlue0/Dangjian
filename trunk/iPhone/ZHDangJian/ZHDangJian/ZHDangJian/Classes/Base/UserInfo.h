

#import <Foundation/Foundation.h>

@interface UserInfo : NSObject

@property (nonatomic, retain) NSString *userId;          // 用户唯一标识
@property (nonatomic, retain) NSString *userName;        // 用户昵称，即登录名
@property (nonatomic, retain) NSString *name;            // 姓名
@property (nonatomic, retain) NSString *organizationId;  // userType=1才有值
@property (nonatomic, retain) NSString *userType;        // 1：党员账号、2：发展党员账号
@property (nonatomic, retain) NSString *deviceTokenString;

+(UserInfo *)sharedInstance;

+(void)reset;



@end



