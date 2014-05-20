
#import "UserInfo.h"

static UserInfo *g_UserInfo = nil;

@implementation UserInfo

+ (UserInfo *)sharedInstance {
    @synchronized(self) {
        if(!g_UserInfo) {
			g_UserInfo = [[UserInfo alloc] init];
            [UserInfo reset];
		}
    }
	
    return g_UserInfo;
}

-(void)dealloc{
	[_userId release];
    [_userName release];
    [_name release];
    [_organizationId release];
    [_userType release];
    [_deviceTokenString release];
    [super dealloc];
}

+(void)reset{
	g_UserInfo.userId = @"";
    g_UserInfo.userName = @"";
    g_UserInfo.name = @"";
    g_UserInfo.organizationId = @"";
    g_UserInfo.userType = @"1";
}




@end

