//
//  URLImage.m
//  DLCCardPacket
//
//  Created by daoyi on 3/12/13.
//
//

#import "obj_url_image.h" 
#import <objc/message.h>

#define URL_IMG_TIME_OUT   60*60 //time out
#define URL_IMG_IGNOR_EXPIRATION  TRUE

@interface obj_url_image()
{
    NSURLConnection *m_connection;
    NSMutableData *m_data;
    
    UIImage *m_img;
}
@end

@implementation obj_url_image
/***************************************************/
#pragma mark - init dealloc
/***************************************************/
-(void)dealloc
{
    [self cancel_request];
    
    RN(m_img);
    
    RN(_url);
    self.delegate = nil;
    
    [super dealloc];
}
-(id)init
{
    self = [super init];
    if (self)
    {
        self.time_out = URL_IMG_TIME_OUT;
        self.ignor_expiration = URL_IMG_IGNOR_EXPIRATION;
        self.is_loading = FALSE;
    }
    return self;
}
/***************************************************/
#pragma mark - property
/***************************************************/
-(void)set_url:(NSString*)url
{
    [self cancel_request];
    
    RN(m_img);
    RETAIN(_url, url);
}
-(UIImage*)get_img
{
    return m_img;
}
/***************************************************/
#pragma mark - net work
/***************************************************/
-(void)cancel_request
{
    //RN(m_img);
    [m_connection cancel];
    RN(m_connection);
    RN(m_data);
    self.is_loading = FALSE;
}
-(void)start_request
{
    [self cancel_request];
    
//    if (self.is_loading)
//        return;
    
    if (IS_EMPTY_NSSTR(_url))
        return;
    
    int cache_type = (self.ignor_expiration ? NSURLRequestReturnCacheDataElseLoad : NSURLRequestUseProtocolCachePolicy);
    
    NSURLRequest* request = [NSURLRequest requestWithURL:[NSURL URLWithString:[self.url utf8_str]]
                                             cachePolicy:cache_type
                                         timeoutInterval:self.time_out];
    
    NSCachedURLResponse *cache = [[NSURLCache sharedURLCache] cachedResponseForRequest:request];
    if (cache)
    {
        RN(m_img);
        m_img = [[UIImage alloc]initWithData:[cache data]];
        METHOD_SAFE(self.delegate, self.call_back_sucess);
        [self cancel_request];
        //DB(@"have cache");
        return;
    }
    
    self.is_loading = TRUE;
    
	m_connection = [[NSURLConnection alloc] initWithRequest:request delegate:self];
    m_data = [[NSMutableData alloc] init];
}
/***************************************************/
#pragma mark - net delegate
/***************************************************/
- (void)connection:(NSURLConnection *)connection didReceiveData:(NSData *)data
{
	[m_data appendData:data];
}
- (void)connectionDidFinishLoading:(NSURLConnection*)connection
{
    RN(m_img);
    m_img = [[UIImage alloc] initWithData:m_data];
    [self cancel_request];
	METHOD_SAFE(self.delegate, self.call_back_sucess);
}
- (void)connection:(NSURLConnection *)connection didFailWithError:(NSError *)error
{
    RN(m_img);
    [self cancel_request];
    METHOD_SAFE(self.delegate, self.call_back_fail);
}
@end
