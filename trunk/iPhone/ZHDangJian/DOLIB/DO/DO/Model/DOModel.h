//
//  LLModel.h
//  LLBar
//  数据模型--数据控制中心
//  Created by kllmctrl on 12-10-30.
//
//



/*
 数据读取，保存、更新（上传），删除
 1)plist
 2)http(json+xml)
 3)dataCore数据库  includeCoreData在exc工程另外写，因为要加的额外的framework
 */


#import <Foundation/Foundation.h>
#import "DOModelDelegate.h"


@interface DOModel : NSObject<DOModelDelegate>{
    
    id<DOModelDelegate> _delegate;
}
@property(nonatomic,assign) id<DOModelDelegate> delegate;


- (BOOL)isLoaded;
- (BOOL)isLoading;
- (BOOL)isOutdated;
- (BOOL)isEmpty;
- (void)reset;


//private
- (void)didStartLoad;
- (void)didFinishLoad;
- (void)didFailLoadWithError:(NSError*)error;

@end




