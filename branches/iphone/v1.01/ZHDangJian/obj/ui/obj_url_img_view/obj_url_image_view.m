//
//  Created by daoyi on 3/12/13.
//
//

#import "obj_url_image_view.h"
#import "obj.h"

#define AIV_TYPE UIActivityIndicatorViewStyleGray

@interface obj_url_image_view()
{
    obj_url_image*              m_url_img;
    UIActivityIndicatorView*    m_aiv_tip;
}
@end

@implementation obj_url_image_view
/*******************************************************/
#pragma mark - init and dealloc
/*******************************************************/
-(void)dealloc
{
    self.loading_img = nil;
    self.empty_img = nil;
    self.fail_img = nil;
    
    RN(_url);
    
    RN(m_url_img);
    RN(m_aiv_tip);
    
    [super dealloc];
}
-(void)my_init
{
    m_aiv_tip = [[UIActivityIndicatorView alloc] initWithActivityIndicatorStyle:AIV_TYPE];
    [m_aiv_tip setCenter:CGPointMake(W(self)/2, H(self)/2)];
    [self addSubview:m_aiv_tip];
    
    m_url_img = [[obj_url_image alloc] init];
    m_url_img.delegate = self;
    m_url_img.call_back_sucess = @selector(sucess);
    m_url_img.call_back_fail = @selector(fail);
}
- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) [self my_init];
    return self;
}
-(void)awakeFromNib
{
    [self my_init];
}
/***************************************************/
#pragma mark - property
/***************************************************/
-(void)set_url:(NSString*)url
{
    if ([_url isEqualToString:url])
        return;
     
    RETAIN(_url, url);
    self.image = self.empty_img;
    m_url_img.url = self.url;
}
/*******************************************************/
#pragma mark - net work
/*******************************************************/
-(void)cancel_request
{
    [m_url_img cancel_request];
    [m_aiv_tip stopAnimating];
    self.image = self.empty_img;
}
-(void)start_request
{
    [m_aiv_tip startAnimating];
    self.image = self.loading_img;
    [m_url_img start_request];
}
/*******************************************************/
#pragma mark - call back
/*******************************************************/
-(void)sucess
{ 
    [m_aiv_tip stopAnimating];
    self.image = m_url_img.img;
}
-(void)fail
{ 
    [m_aiv_tip stopAnimating];
    self.image = self.fail_img;
}
@end
