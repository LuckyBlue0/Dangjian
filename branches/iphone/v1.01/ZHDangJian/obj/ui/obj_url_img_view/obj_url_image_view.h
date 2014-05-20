//
//  URLUIImageView.h
//  DLCCardPacket
//
//  Created by daoyi on 3/12/13.
//
//
#import "obj_ui.h"

@interface obj_url_image_view : UIImageView
@property(retain,nonatomic)UIImage*  loading_img;
@property(retain,nonatomic)UIImage*  fail_img;
@property(retain,nonatomic)UIImage*  empty_img;

@property(assign,nonatomic,setter = set_url:)NSString* url;

-(void)start_request;
-(void)cancel_request;
@end
