
#import "KC_NSStringURLAdditions.h"
#import "Constant.h"
#import "URLConstant.h"
#import "UserInfo.h"
#import "DES3Util.h"
#import "CJSONSerializer.h"

@implementation NSString (KC_NSStringURLAdditions)


//-(NSDictionary*)URLDic{
//    //首先过滤kurl
//    NSMutableArray *aNewArray = [[[NSMutableArray alloc] init] autorelease];
//    
//    //获取url??
//    NSArray *aURLArray = [self componentsSeparatedByString:@"?"];
//    if ([aURLArray count]==0) {
//        return nil;
//    }
//    NSString *aURL = [aURLArray objectAtIndex:0];
//    
//    //读取数据后封装
//    NSString *urlStrTest=[self replace:aURL to:@""];
//    
//    NSArray *aArray = [urlStrTest componentsSeparatedByString:@"&"];
//    for (int i=0; i<[aArray count]; i++) {
//        
//        NSString *aSubStr = [aArray objectAtIndex:i];
//        if (aSubStr) {
//            NSArray *aSubArray = [aSubStr componentsSeparatedByString:@"="];
//            if ([aSubArray count]>=2) {
//                
//                if ([aSubArray count]>2) {
//                    //特殊处理，value包含了=号
//                    
//                    NSString *keyTemp = [aSubArray objectAtIndex:0];
//                    NSString *ValueTemp = [aSubStr replace:[NSString stringWithFormat:@"%@=",keyTemp] to:@""];
//                    [aNewArray addObject:[NSDictionary dictionaryWithObjectsAndKeys:ValueTemp,keyTemp, nil]];
//                    
//                }else{
//                    [aNewArray addObject:[NSDictionary dictionaryWithObjectsAndKeys:[aSubArray objectAtIndex:1],[aSubArray objectAtIndex:0],nil]];
//                }
//                
//            }
//        }
//    }
//    
//    return [NSDictionary dictionaryWithObjectsAndKeys:aURL,@"url",aNewArray,@"valueAndKeys", nil];
//}


- (NSDictionary *)URLDic {
    //首先过滤kurl
    NSMutableArray *aNewArray = [[[NSMutableArray alloc] init] autorelease];
    
    //获取url??
    NSArray *aURLArray = [self componentsSeparatedByString:@"?"];
    if ([aURLArray count] == 0) {
        return nil;
    }
    NSString *aURL = [aURLArray objectAtIndex:0];
    
    //读取数据后封装
    NSString *urlStrTest = [self replace:[aURL stringByAppendingString:@"?"] to:@""];
    
    NSArray *aArray = [urlStrTest componentsSeparatedByString:@"&"];
    for (int i = 0; i < [aArray count]; i++) {
        
        NSString *aSubStr = [aArray objectAtIndex:i];
        if (aSubStr) {
            NSArray *aSubArray = [aSubStr componentsSeparatedByString:@"="];
            if ([aSubArray count] >= 2) {
                
                if ([aSubArray count] > 2) {
                    //特殊处理，value包含了=号
                    
                    NSString *keyTemp = [aSubArray objectAtIndex:0];
                    NSString *ValueTemp = [aSubStr replace:[NSString stringWithFormat:@"%@=", keyTemp] to:@""];
                    [aNewArray addObject:[NSDictionary dictionaryWithObjectsAndKeys:ValueTemp, keyTemp, nil]];
                    
                } else {
                    [aNewArray addObject:[NSDictionary dictionaryWithObjectsAndKeys:[aSubArray objectAtIndex:1], [aSubArray objectAtIndex:0], nil]];
                }
                
            }
        }
    }
    
    return [NSDictionary dictionaryWithObjectsAndKeys:aURL, @"url", aNewArray, @"valueAndKeys", [aURLArray objectAtIndex:1], @"paramBody", nil];
}

//普通URL
- (ASIFormDataRequest *)URLRequest {
    
    NSDictionary *aResultDic = [self URLDic];
    //开始请求
    NSString *aURL = [aResultDic objectForKey:@"url"];
    NSArray *aValueAndKeys = [aResultDic objectForKey:@"valueAndKeys"];
    
    ASIFormDataRequest *aASIFormDataRequest = [ASIFormDataRequest requestWithURL:[NSURL URLWithString:aURL]];
    [aASIFormDataRequest setRequestMethod:@"POST"];
    for (NSUInteger i = 0; i < [aValueAndKeys count]; i++) {
        NSDictionary *aDic = [aValueAndKeys objectAtIndex:i];
        for (NSUInteger j = 0; j < [aDic count]; j++) {
            NSArray *keys = [aDic allKeys];
            NSArray *values = [aDic allValues];
            [aASIFormDataRequest setPostValue:[values objectAtIndex:j] forKey:[keys objectAtIndex:j]];
        }
        
    }
    [aASIFormDataRequest setPersistentConnectionTimeoutSeconds:120];
    [aASIFormDataRequest setTimeOutSeconds:180];
    
    return aASIFormDataRequest;
    
}

//-(ASIFormDataRequest*)URLRequest{
//    
//    NSDictionary *aResultDic = [self URLDic];
//    //开始请求
//    NSString *aURL = [aResultDic objectForKey:@"url"];
//    NSArray *aValueAndKeys =[aResultDic objectForKey:@"valueAndKeys"];
//    
//    //封装成json
//    NSMutableDictionary *aValueDics = [[[NSMutableDictionary alloc] init] autorelease];
//    for (int i=0; i<[aValueAndKeys count]; i++) {
//        NSDictionary *aDic = [aValueAndKeys objectAtIndex:i];
//        for (int j=0; j<[aDic count]; j++) {
//            NSArray *keys = [aDic allKeys];
//            NSArray *values = [aDic allValues];
//            [aValueDics setObject:[values objectAtIndex:j] forKey:[keys objectAtIndex:j]];
//        }
//        
//    }
//    
//    NSString *jsonParameters = [[CJSONSerializer serializer] serializeDictionary:aValueDics];
//    NSMutableData *postData = [[[NSMutableData alloc] initWithData:[[DES3Util enDES3:jsonParameters] dataUsingEncoding:NSUTF8StringEncoding]] autorelease];
//    
//    ASIFormDataRequest *aASIFormDataRequest = [ASIFormDataRequest requestWithURL:[NSURL URLWithString:aURL]];
//    [aASIFormDataRequest setRequestMethod:@"POST"];
//    [aASIFormDataRequest setPostBody:postData];
//    
//    [aASIFormDataRequest setPersistentConnectionTimeoutSeconds:120];
//    aASIFormDataRequest.shouldAttemptPersistentConnection = NO;
//    
//    return aASIFormDataRequest;
//    
//}

//url and json
-(ASIFormDataRequest*)request_with_json:(NSString*)json
{
    NSMutableData *postData = [[[NSMutableData alloc] initWithData:[[DES3Util enDES3:json] dataUsingEncoding:NSUTF8StringEncoding]] autorelease];
    
    ASIFormDataRequest *aASIFormDataRequest = [ASIFormDataRequest requestWithURL:[NSURL URLWithString:self]];
    [aASIFormDataRequest setRequestMethod:@"POST"];
    [aASIFormDataRequest setPostBody:postData];
    
    [aASIFormDataRequest setPersistentConnectionTimeoutSeconds:120];
    aASIFormDataRequest.shouldAttemptPersistentConnection = NO;
    
    return aASIFormDataRequest;
    
}


//带图片请求
-(ASIHTTPRequest*)URLPicRequest{
    return nil;
}


+(RequstResult*)parseReturnData:(NSData*)aData{
    
    NSString *aReturnString = [[[NSString alloc] initWithData:aData encoding:NSUTF8StringEncoding] autorelease];
    RequstResult *aRequstResult = [[[RequstResult alloc] init] autorelease];
//    NSString *aString = [DES3Util deDES3:aReturnString];
//    LOG(@"返回数据=%@",aString);
    LOG(@"返回数据=%@",aReturnString);
    
//    id jsonData= [aString objectFromJSONString];
    id jsonData= [aReturnString objectFromJSONString];
    
    if (nil!=jsonData && [jsonData isKindOfClass:[NSDictionary class]]) {
        //code 不为 1
        if ([[jsonData objectForKey:@"code"] isEqualToString:@"0"]) {
            aRequstResult.code = 1;
        }else{
            aRequstResult.code = 0;
        }
        
        if ([[jsonData objectForKey:@"desc"] isKindOfClass:[NSString class]]) {
            aRequstResult.desc = [jsonData objectForKey:@"desc"];
        }else{
            aRequstResult.desc = @"";
        }
        
        if ([[jsonData objectForKey:@"data"] isKindOfClass:[NSDictionary class]]) {
            aRequstResult.dataDic = [jsonData objectForKey:@"data"];
        }else{
            
//            if ([jsonData objectForKey:@"Address"] && [jsonData objectForKey:@"Content"] && [jsonData objectForKey:@"CreateTime"] && [jsonData objectForKey:@"Reply_info"] && [jsonData objectForKey:@"Title"] && [jsonData objectForKey:@"phone"] && [jsonData objectForKey:@"username"]) {
//                NSMutableDictionary *aDic = [[[NSMutableDictionary alloc] init] autorelease];
//                [aDic setObject:[jsonData objectForKey:@"Address"] forKey:@"Address"];
//                [aDic setObject:[jsonData objectForKey:@"Content"] forKey:@"Content"];
//                [aDic setObject:[jsonData objectForKey:@"CreateTime"] forKey:@"CreateTime"];
//                [aDic setObject:[jsonData objectForKey:@"Title"] forKey:@"Title"];
//                [aDic setObject:[jsonData objectForKey:@"phone"] forKey:@"phone"];
//                [aDic setObject:[jsonData objectForKey:@"username"] forKey:@"username"];
//                [aDic setObject:[jsonData objectForKey:@"Reply_info"] forKey:@"Reply_info"];
//                
//                aRequstResult.dataDic = aDic;
//
//            } else {
//                aRequstResult.dataDic = nil;
//            }
            
        }
        
    }
    
    return aRequstResult;
}

@end
