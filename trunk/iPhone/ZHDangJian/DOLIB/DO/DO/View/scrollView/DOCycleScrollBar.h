/*
 * @class LLCycleScrollBar
 *
 * 可循环scrollView
 * Created by kllmctrl on 12-07-23.
 * V1.0
 */

#import <UIKit/UIKit.h>

typedef enum {
    CycleDirectionPortait,          // 垂直滚动
    CycleDirectionLandscape         // 水平滚动
}CycleDirection;



@protocol DOCycleScrollBarDelegate;
@interface DOCycleScrollBar : UIView <UIScrollViewDelegate> {
    
    UIScrollView *scrollView;
    UIImageView *curImageView;
    
    int totalPage;  
    int curPage;
    CGRect scrollFrame;
    
    CycleDirection scrollDirection;     // scrollView滚动的方向
    NSArray *imagesArray;               // 存放所有需要滚动的图片 UIImage
    NSMutableArray *curImages;          // 存放当前滚动的三张图片
    
    id <DOCycleScrollBarDelegate>delegate;
}

@property (nonatomic, assign) id <DOCycleScrollBarDelegate>delegate;

- (int)validPageValue:(NSInteger)value;
- (id)initWithFrame:(CGRect)frame cycleDirection:(CycleDirection)direction pictures:(NSArray *)pictureArray;
- (NSArray *)getDisplayImagesWithCurpage:(int)page;
- (void)refreshScrollView;

@end

@protocol DOCycleScrollBarDelegate <NSObject>
@optional
- (void)cycleScrollViewDelegate:(DOCycleScrollBar *)cycleScrollView didSelectImageView:(int)index;
- (void)cycleScrollViewDelegate:(DOCycleScrollBar *)cycleScrollView didScrollImageView:(int)index;

@end