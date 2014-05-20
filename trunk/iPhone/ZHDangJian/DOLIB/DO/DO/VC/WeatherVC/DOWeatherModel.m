
#import "DOWeatherModel.h"
#import "DOMacro.h"
#import "JSONKit.h"

@implementation DOWeatherModel

-(NSDictionary*)parserJSON:(NSData*)aNSData{
    id a= [aNSData objectFromJSONData];
    if (nil!=a && [a isKindOfClass:[NSDictionary class]]) {
        
        
        if (nil!=[a objectForKey:@"weatherinfo"]
            && [[a objectForKey:@"weatherinfo"] isKindOfClass:[NSDictionary class]]) {
            
            return (NSDictionary*)[a objectForKey:@"weatherinfo"];
        }
    }
    return nil;
}

-(void)startRequest{
    ASIHTTPRequest *aASIHTTPRequest_DO = [ASIHTTPRequest requestWithURL:[NSURL URLWithString:[@"http://weather.com.cn/data/cityinfo/101281001.html" stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]]];
    
    [aASIHTTPRequest_DO setDelegate:self];
    [aASIHTTPRequest_DO setPersistentConnectionTimeoutSeconds:120];
    [aASIHTTPRequest_DO startAsynchronous];
}

@end

