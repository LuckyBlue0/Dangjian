//
//  URLImage.h
//  DLCCardPacket
//
//  Created by daoyi on 3/12/13.
//
//

#import "obj_ui.h"

@interface obj_url_image : NSObject
@property(assign,nonatomic,setter = set_url:) NSString* url;
@property(retain,nonatomic)id delegate;

@property(assign,nonatomic)         SEL         call_back_sucess;
@property(assign,nonatomic)         SEL         call_back_fail;

@property(assign,nonatomic)         int         time_out;
@property(assign,nonatomic)         BOOL        ignor_expiration;
@property(nonatomic,assign)BOOL is_loading;

@property(nonatomic,readonly,getter = get_img)UIImage* img;

-(void)start_request;
-(void)cancel_request;
@end
