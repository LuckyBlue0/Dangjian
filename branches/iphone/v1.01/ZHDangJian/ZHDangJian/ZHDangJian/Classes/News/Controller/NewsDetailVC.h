//
//  NewsDetailVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-12.
//
//

#import "BaseVC.h"

@interface NewsDetailVC : BaseVC<UIWebViewDelegate, DOModelDelegate>

@property (retain, nonatomic) IBOutlet UIWebView *myWebView;

@property (retain, nonatomic) NSString *newsInfoId;
@property (retain, nonatomic) NSString *newsInfoType;
@property (retain, nonatomic) BaseModel *newsDetailModel;

@end
