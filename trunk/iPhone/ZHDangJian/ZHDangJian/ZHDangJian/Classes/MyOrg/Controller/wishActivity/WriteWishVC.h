//
//  WriteWishVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-27.
//
//

#import "BaseVC.h"
#import "BaseModel.h"

@interface WriteWishVC : BaseVC<DOModelDelegate, UITextViewDelegate>

@property (retain, nonatomic) IBOutlet UILabel *titleLabel;
@property (retain, nonatomic) IBOutlet DOTextViewBar *myTextView;

@property (retain, nonatomic) NSString *activtyId;
@property (retain, nonatomic) NSString *titleString;
@property (retain, nonatomic) BaseModel *submitModel;

- (IBAction)keyBoardDisappear:(id)sender;

@end
