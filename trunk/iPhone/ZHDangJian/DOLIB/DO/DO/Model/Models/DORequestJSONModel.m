//
//  DORequestJSONModel.m
//  DO
//
//  Created by kllmctrl on 12-11-2.
//  Copyright (c) 2012年 kllmctrl. All rights reserved.
//

#import "DORequestJSONModel.h"
#import "DOMacro.h"

@implementation DORequestJSONModel


#pragma mark -
#pragma mark 继承实现
-(NSDictionary*)parserJSON:(NSData*)aNSData{
    
    return nil;
}



#pragma mark -
#pragma mark public

-(BOOL)isEmpty{
    if (!_dataDic) {
        return YES;
    }
    return NO;
}
-(NSDictionary*)dataDic{
    return _dataDic;
}




#pragma mark -
#pragma mark ASIHTTPRequest_DO
/**
 * 开始解析JSON
 */
- (void)requestFinished:(ASIHTTPRequest *)aASIHTTPRequest_DO{
    
    int statusCode = [aASIHTTPRequest_DO responseStatusCode];
    NSData* aResponseData=[aASIHTTPRequest_DO responseData];
    if(statusCode != 200){
        DO_RELEASE_SAFELY(_request);
        
        //回调父类DOModel
        [self didFailLoadWithError:nil];
        return;
    }
    
    if (_dataDic) {
        DO_RELEASE_SAFELY(_dataDic);
    }
    _dataDic = [[self parserJSON:aResponseData] retain];
    
    //解析过程中当做请求  isLoading==YES;
    
    [super requestFinished:aASIHTTPRequest_DO];
}


@end

