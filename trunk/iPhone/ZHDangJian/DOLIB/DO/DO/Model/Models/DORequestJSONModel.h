//
//  DORequestJSONModel.h
//  DO
//  请求JSON处理
//  Created by kllmctrl on 12-11-2.
//  Copyright (c) 2012年 kllmctrl. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "DORequestModel.h"

@interface DORequestJSONModel : DORequestModel{
    
    NSDictionary *_dataDic;
}

-(NSDictionary*)dataDic;

@end




/*
 *demo
 
 
 
 
 #import "MainModel.h"
 #import <DOMacro.h>
 #import <JSONKit.h>
 
 @implementation MainModel
 
 -(NSDictionary*)parserJSON:(NSData*)aNSData{
 
 NSMutableArray *aRetArray = [[[NSMutableArray alloc] init] autorelease];
 
 DOLOGDATA(aNSData);
 id a= [aNSData objectFromJSONData];
 if (nil!=a && [a isKindOfClass:[NSDictionary class]]) {
 
 
 if (nil!=[a objectForKey:@"data"]
 && [[a objectForKey:@"data"] isKindOfClass:[NSArray class]]) {
 NSArray *aArray = (NSArray*)[a objectForKey:@"data"];
 for (int i=0; i<[aArray count]; i++) {
 if ([[aArray objectAtIndex:i] isKindOfClass:[NSDictionary class]]) {
 [aRetArray addObject:[aArray objectAtIndex:i]];
 }
 }
 }
 }
 
 NSDictionary *aRetDic = [NSDictionary dictionaryWithObjectsAndKeys:@"data",aRetArray, nil];
 return aRetDic;
 }
 
 -(void)startRequest{
 ASIHTTPRequest_DO *aASIHTTPRequest_DO = [ASIHTTPRequest_DO requestWithURL:[NSURL URLWithString:[@"http://cm.sg169.com:8080/sghome1/mobileArea!listMobileArea.action?isview=1" stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]]];
 
 [aASIHTTPRequest_DO setDelegate:self];
 [aASIHTTPRequest_DO startAsynchronous];
 }
 
 
 @end
 

 
 
 
 
 //- (void)modelDidFinishLoad:(LLModel*)model{
 //    //self.dataDic = ((TestModel*)model).dataDic;
 //
 
 */


