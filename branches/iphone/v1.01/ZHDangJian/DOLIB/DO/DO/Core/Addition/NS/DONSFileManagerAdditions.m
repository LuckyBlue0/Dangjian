#import "DONSFileManagerAdditions.h"

@implementation NSFileManager(DONSFileManagerAdditions)


#pragma mark -
#pragma mark Documents 沙盒
-(NSString*)getDocumentsPath{
	return [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0];
}


@end