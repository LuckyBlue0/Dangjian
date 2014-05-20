/**
 * base item
 */

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>


@interface DOTableItem : NSObject{
    id _userInfo;
    
    NSDictionary *_dataDic;
}

@property (nonatomic, retain) id userInfo;
@property (nonatomic, retain) NSDictionary *dataDic;

-(id)initWithData:(id)aData;
@end
