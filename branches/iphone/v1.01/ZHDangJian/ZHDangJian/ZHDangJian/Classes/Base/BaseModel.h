

#import <Foundation/Foundation.h>

@interface BaseModel : DORequestJSONModel

@property(nonatomic,assign)NSInteger modelType;

-(void)startRequest:(NSString*)aURLStr;
-(void)start_request:(NSString*)url with_json:(NSString*)json;
-(void)startSynRequest:(NSString*)aURLStr;

@end
