//
//  MyOrgQRVC.h
//  THJianDang
//
//  Created by Alex Pong on 13-5-2.
//
//

#import "BaseVC.h"
#import "ZXingWidgetController.h"

@interface MyOrgQRVC : BaseVC<ZXingDelegate>
@property (retain, nonatomic) NSString *meetingID;
@property (retain, nonatomic) NSString *codeResult;
@property (retain, nonatomic) UIImage *codeImage;
@property (retain, nonatomic) NSString *type;
@property (retain, nonatomic) ZXingWidgetController *ZWController;

@end
