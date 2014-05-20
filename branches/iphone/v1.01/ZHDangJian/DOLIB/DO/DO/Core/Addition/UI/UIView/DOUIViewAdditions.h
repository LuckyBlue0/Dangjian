/*
 * 扩展 UIView
 * Created by kllmctrl on 12-4-16.
 */
#import <UIKit/UIKit.h>


/**
 * UIView 宏
 */
#define ALLOC(__Class) [[__Class alloc]init]
#define ALLOCF(__Class,__Frame) [[__Class alloc]initWithFrame:__Frame]
#define ALLOC_XYWH(__Class,__X,__Y,__W,__H) [[__Class alloc]initWithFrame:XYWH(__X,__Y,__W,__H)]
#define ALLOC_XYWHI(__Class,__X,__Y,__W,__H) [[__Class alloc]initWithFrame:XYWHI(__X,__Y,__W,__H)]
#define XYWH(__X,__Y,__W,__H) CGRectMake(__X, __Y, __W, __H)
#define XYWHI(__X,__Y,__W,__H) CGRectMake((int)__X, (int)__Y, (int)__W, (int)__H)




/*************************************************************************************
 *
 *@class UIView (LLUIViewAdditions)
 *
 *************************************************************************************
 */

@interface UIView (DOUIViewAdditions)

/**
 * 设置背景,图片名称,直接从资源文件加载
 */
-(void)setBackgroundImage:(NSString*)aImageStr;


/**
 * 移除子视图
 */
-(BOOL)removeSubview:(NSInteger)aTag;



/********************************************
 *
 *@fuction frame center
 *
 */

/**
 * frame.origin.x
 */
- (CGFloat)left;
- (void)setLeft:(CGFloat)x;


/**
 * frame.origin.y
 */
- (CGFloat)top;
- (void)setTop:(CGFloat)y;


/**
 * frame.origin.x + frame.size.width
 */
- (CGFloat)right;
/**
 * 调整frame.origin.x(width不变)
 */
- (void)setRight:(CGFloat)right;


/**
 * frame.origin.y + frame.size.height
 */
- (CGFloat)bottom;
/**
 * 调整frame.origin.y(height不变)
 */
- (void)setBottom:(CGFloat)bottom;



/**
 * frame.size.width
 */
- (CGFloat)width;
- (void)setWidth:(CGFloat)width;

/**
 * frame.size.height
 */
- (CGFloat)height;
- (void)setHeight:(CGFloat)height;

/**
 * frame.origin
 */
- (CGPoint)origin;
- (void)setOrigin:(CGPoint)origin;

/**
 * frame.size
 */
- (CGSize)size;
- (void)setSize:(CGSize)size;


/**
 * center.x
 */
- (CGFloat)centerX;
- (void)setCenterX:(CGFloat)centerX;

/**
 * center.y
 */
- (CGFloat)centerY;
- (void)setCenterY:(CGFloat)centerY;



@end



