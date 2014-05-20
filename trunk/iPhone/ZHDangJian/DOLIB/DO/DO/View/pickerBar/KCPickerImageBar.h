//
//  KCPickerImageBar.h
//  DLCShopCard
//  选择图片器
//  Created by kllmctrl on 13-1-18.
//
//

#import <UIKit/UIKit.h>
#import "DOCore.h"

@protocol KCPickerImageBarDelegate;

@interface KCPickerImageBar : NSObject<UIActionSheetDelegate,UIImagePickerControllerDelegate,UINavigationControllerDelegate>

@property(nonatomic,retain)UIImage *image;
@property(nonatomic,assign)id<KCPickerImageBarDelegate> delegate;
@property(nonatomic,assign)NSInteger tag;

@property(nonatomic,retain)UIPopoverController *popover;

-(void)showInView:(UIView*)aView;

@end


/////////////////////////////////////////////////////

@protocol KCPickerImageBarDelegate <NSObject>

-(void)didKCPickerImageBarFinishedSeleted:(KCPickerImageBar*)aBar;
-(void)didKCPickerImageBarFailedSeleted:(KCPickerImageBar*)aBar;
-(void)didKCPickerImageBarFinishedCanceled:(KCPickerImageBar*)aBar;

@end