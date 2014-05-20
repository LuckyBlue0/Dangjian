#import "DOTableItem.h"

@implementation DOTableItem

@synthesize userInfo = _userInfo;
@synthesize dataDic=_dataDic;

-(id)initWithData:(id)aData{
    if (self=[super init]) {
        if (nil!=aData && [aData isKindOfClass:[NSDictionary class]]) {
            _dataDic=[[NSDictionary alloc] initWithDictionary:aData];
        }
    }
    return self;
}

- (void)dealloc {
    [_dataDic release];
    _dataDic=nil;
    
    [_userInfo release];
    _userInfo=nil;
    [super dealloc];
}
@end



