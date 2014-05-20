//
//  NewsMainVC.h
//  ZHDangJian
//
//  Created by kevin_yby on 13-11-11.
//
//

#import "BaseVC.h"
#import "BaseTableView.h"
#import "ScrollViewAutoScroll_kcBar.h"

@interface NewsMainVC : BaseVC<UITableViewDataSource, UITableViewDelegate, DOModelDelegate, UIGestureRecognizerDelegate,UIScrollViewDelegate, BaseTableViewDelegate>

@property (retain, nonatomic) IBOutlet UIView *tableHeadView;
@property (retain, nonatomic) IBOutlet ScrollViewAutoScroll_kcBar *newsScrollView;
@property (retain, nonatomic) IBOutlet UIPageControl *newsPageControl;
@property (retain, nonatomic) IBOutlet BaseTableView *tableView;

@property (nonatomic, retain) BaseModel *newsInfoModel;
@property (nonatomic, retain) NSMutableArray *lastListArray;
@property (nonatomic, retain) NSMutableArray *imagesArray;
@property (nonatomic, retain) NSMutableArray *listImageArray;
@property (nonatomic, assign) NSInteger selectedImageViewIndex;
@property (nonatomic, assign) NSInteger selectedTableIndex;

@end
