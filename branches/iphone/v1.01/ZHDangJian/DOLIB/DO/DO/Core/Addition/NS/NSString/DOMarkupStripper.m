#import "DOMarkupStripper.h"
//#import "LLAdditions.h"

@implementation DOMarkupStripper
- (void)dealloc {
    [_strings release];
    _strings=nil;
	[super dealloc];
}

#pragma mark -
#pragma mark NSXMLParserDelegate
- (void)parser:(NSXMLParser*)parser foundCharacters:(NSString*)string {
	[_strings addObject:string];
}

#pragma mark -
#pragma mark Public
- (NSString*)parse:(NSString*)text {
	_strings = [[NSMutableArray alloc] init];

	NSString*     document  = [NSString stringWithFormat:@"<x>%@</x>", text];
	NSData*       data      = [document dataUsingEncoding:text.fastestEncoding];
	NSXMLParser*  parser    = [[NSXMLParser alloc] initWithData:data];
	parser.delegate = self;
	[parser parse];
    [parser release];
    parser=nil;
	
	NSString* result = [_strings componentsJoinedByString:@""];
    [_strings release];
    _strings=nil;
	
	return result;
}

@end
