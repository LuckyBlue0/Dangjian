/*
 
 请求返回数据
 
 */

#import <Foundation/Foundation.h>

@interface RequstResult : NSObject

@property(nonatomic,assign)NSInteger code;
@property(nonatomic,retain)NSString *desc;
@property(nonatomic,retain)NSDictionary *dataDic;


@end
