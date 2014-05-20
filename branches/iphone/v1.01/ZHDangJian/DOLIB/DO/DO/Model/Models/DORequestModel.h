//
//  LLNetworkModels.h
//  LLBar
//  普通一次网络请求交互
//  Created by kllmctrl on 12-11-1.
//
//


#import <Foundation/Foundation.h>
#import "DOModel.h"
#import "ASIHTTPRequest.h"

@interface DORequestModel : DOModel{
    
    ASIHTTPRequest *_request;
    BOOL _outdate;
}

- (void)requestFinished:(ASIHTTPRequest *)aASIHTTPRequest_DO;
- (void)setOutdate:(BOOL)outdate;
- (void)cancel ;
@end





/*
 
 demo
 
 #import "MainModel.h"
 
 @implementation MainModel
 
 
 -(void)startRequest{
 ASIHTTPRequest_DO *aASIHTTPRequest_DO = [ASIHTTPRequest_DO requestWithURL:[NSURL URLWithString:[@"http://cm.sg169.com:8080/sghome1/mobileArea!listMobileArea.action?isview=1" stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]]];
 
 [aASIHTTPRequest_DO setDelegate:self];
 [aASIHTTPRequest_DO startAsynchronous];
 }
 
 @end
 */



