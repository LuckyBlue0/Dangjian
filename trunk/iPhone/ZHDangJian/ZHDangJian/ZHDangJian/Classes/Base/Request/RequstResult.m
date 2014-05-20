

#import "RequstResult.h"

@implementation RequstResult

@synthesize code;
@synthesize desc;
@synthesize dataDic;


-(void)dealloc{
    [desc release];
    [dataDic release];
    
    [super dealloc];
}


@end
