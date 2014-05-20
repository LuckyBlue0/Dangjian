
#import "BaseModel.h"
#import "Constant.h"
#import "UserInfo.h"
#import "KC_NSStringURLAdditions.h"
#import "DES3Util.h"
#import "RequstResult.h"

@implementation BaseModel

@synthesize modelType;


-(NSDictionary*)parserJSON:(NSData*)aNSData{
    
    RequstResult *aRR = [NSString parseReturnData:aNSData];
    NSDictionary *aRetDic = [NSDictionary dictionaryWithObjectsAndKeys:aRR,@"RR",nil];
    return aRetDic;
    
}


-(void)startRequest:(NSString*)aURLStr{
    
    LOG(@"请求:%@",aURLStr);
    
    ASIFormDataRequest *aRequest = [aURLStr URLRequest];
	[aRequest setDelegate:self];
    [aRequest startAsynchronous];//异步
}
-(void)start_request:(NSString*)url with_json:(NSString*)json{
    
    LOG(@"请求:url:%@ \n json:%@",url,json);
    
    ASIFormDataRequest *aRequest = [url request_with_json:json];
	[aRequest setDelegate:self];
    [aRequest startAsynchronous];//异步
}


-(void)startSynRequest:(NSString*)aURLStr{
    
    LOG(@"请求:%@",aURLStr);
    
    ASIFormDataRequest *aRequest = [aURLStr URLRequest];
	[aRequest setDelegate:self];
    [aRequest startSynchronous];//同步
}

@end
