/*
 * 扩充 UIImage
 * Created by kllmctrl on 12-7-11.
 */
#import <UIKit/UIKit.h>

CGFloat LLDegreesToRadians(CGFloat);
CGFloat LLRadiansToDegrees(CGFloat);


/*************************************************************************************
 *
 *@class UIImage (LLUIImageAdditions)
 *
 *************************************************************************************
 */

@interface UIImage (DOUIImageAdditions)

/**
 * 保存图片到路径下
 */
-(BOOL)saveWithPath:(NSString*)aPath;


/**
 * 初始化图片
 */
+(UIImage*)imageWithPath:(NSString*)aPath;
+(UIImage*)imageWithBundle:(NSString *)aImageName;


/**
 * 保存当前视图截图到默认目录下
 */
+(void)saveCurrentView:(UIView*)aView;



/**
 * 缩放图片
 */
+ (UIImage *)imageNamed: (NSString *)imageStr size:(CGSize)size;
+ (UIImage *)scaleImage:(UIImage *)image maxWidth:(float)maxWidth maxHeight:(float)maxHeight;



/**
 * 旋转图片
 */
- (UIImage *)imageRotatedByDegrees:(CGFloat)degrees;
- (UIImage *)imageRotatedByRadians:(CGFloat)radians;
+ (UIImage *)fixOrientation:(UIImage *)aImage;

@end
  

