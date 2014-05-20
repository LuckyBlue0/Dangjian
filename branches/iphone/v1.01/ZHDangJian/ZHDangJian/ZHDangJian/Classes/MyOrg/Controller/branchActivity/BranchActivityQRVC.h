//
//  BranchActivityQRVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-13.
//
//

#import "BaseVC.h"
#import "ZXingWidgetController.h"

@interface BranchActivityQRVC : BaseVC<ZXingDelegate>
@property (retain, nonatomic) NSString *meetingID;
@property (retain, nonatomic) NSString *codeResult;
@property (retain, nonatomic) UIImage *codeImage;
@property (retain, nonatomic) NSString *type;
@property (retain, nonatomic) ZXingWidgetController *ZWController;

@end
