//
//  KCPickerTimeBar.h
//  DLCShopCard
//  选择时间器
//  Created by kllmctrl on 13-1-18.
//
//

#import <UIKit/UIKit.h>
#import "DOCore.h"

@protocol KCPickerTimeBarDelegate;

@interface KCPickerTimeBar : NSObject<UIActionSheetDelegate>


@property(nonatomic,retain)NSDate *date;

@property(nonatomic,retain)UIActionSheet *actionSheet;
@property(nonatomic,retain)UIDatePicker *datePicker;

@property(nonatomic,assign)id<KCPickerTimeBarDelegate> delegate;
@property(nonatomic,assign)NSInteger tag;

-(void)showInView:(UIView*)aView title:(NSString*)aTitle date:(NSDate*)aDate mode:(UIDatePickerMode)aMode;
@end


////////////////////////////////////////////////////

@protocol KCPickerTimeBarDelegate <NSObject>

-(void)didKCPickerTimeBarFinishedSeleted:(KCPickerTimeBar*)aBar;
-(void)didKCPickerTimeBarFinishedCanceled:(KCPickerTimeBar*)aBar;

@end