#import "DOTableViewDelegate.h"
#import "DOTableModelVC.h"
#import "DOTableRefreshBar.h"
#import "DOTableFooterBar.h"
#import "DOTableViewDataSource.h"
#import "DOTableViewCell.h"
#import "DOTableItem.h"
#import "DOCore.h"

static const CGFloat kRefreshDeltaY = -65.0f;
static const CGFloat kHeaderVisibleHeight = 60.0f;
static const CGFloat kInfiniteScrollFooterHeight = 40.0f;
static const CGFloat kInfiniteScrollThreshold = 0.99f;


@implementation DOTableViewDelegate
@synthesize controller = _controller;
@synthesize headerView=_headerView;
@synthesize footerView =_footerView;

@synthesize hasLoadMore = _hasLoadMore;
@synthesize hasRefresh = _hasRefresh;


- (id)initWithController:(DOTableModelVC*)controller 
              withHeader:(BOOL)aHeader 
              withFooter:(BOOL)aFooter{
    if (self= [super init]) {
        _flags.isHeaderEnable = aHeader;
        _flags.isFooterEnable = aFooter;
        _controller = controller;
        
        if (_flags.isHeaderEnable) {
            _headerView = [[DOTableRefreshBar alloc] initWithFrame:XYWH(0,
                                                                        -_controller.tableView.bounds.size.height,
                                                                        _controller.tableView.bounds.size.width,
                                                                        _controller.tableView.bounds.size.height)];
            _headerView.autoresizingMask = UIViewAutoresizingFlexibleWidth;
            [_headerView setState:EPullRefreshNormal];
            _headerView.tag = 9999;
            [_controller.tableView addSubview:_headerView];
        }
        
        if (_flags.isFooterEnable) {
            _footerView = [[DOTableFooterBar alloc] initWithFrame:XYWH(0,
                                                                       0,
                                                                       _controller.tableView.bounds.size.width,
                                                                       kInfiniteScrollFooterHeight)];
            _headerView.tag = 9998;
            _footerView.autoresizingMask = UIViewAutoresizingFlexibleWidth;
            _controller.tableView.tableFooterView = _footerView;
        }
        
        
        _hasLoadMore = NO;
        _hasRefresh = NO;
    }
    return self;
}

- (void)dealloc {
    _controller.tableView.tableFooterView = nil;
    [_headerView removeFromSuperview];
    DO_RELEASE_SAFELY(_headerView);
    DO_RELEASE_SAFELY(_footerView);
    
    [super dealloc];
}


-(DOTableModel*)dataSourceModel{
    if (nil==_dataSourceModel) {
        [_dataSourceModel release];
    }
    id<DOTableViewDataSource> datasource = (id<DOTableViewDataSource>)_controller.tableView.dataSource;
    _dataSourceModel=[datasource.model retain];
    
    return _dataSourceModel;
}



-(BOOL)hasLoadMore{
    return _hasLoadMore;
}

-(BOOL)hasRefresh{
    return _hasRefresh;
}


#pragma mark -
#pragma mark UIScrollViewDelegate

- (BOOL)scrollViewShouldScrollToTop:(UIScrollView *)scrollView {
    return YES;
}

- (void)scrollViewDidScrollToTop:(UIScrollView *)scrollView {
}

- (void)scrollViewDidScroll:(UIScrollView *)scrollView {
    
    if (_flags.isHeaderEnable) {
        
        if (scrollView.dragging && ![self dataSourceModel].isLoading) {
            if (scrollView.contentOffset.y > kRefreshDeltaY
                && scrollView.contentOffset.y < 0.0f) {
                [_headerView setState:EPullRefreshNormal];
                
            } else if (scrollView.contentOffset.y < kRefreshDeltaY) {
                [_headerView setState:EPullRefreshPulling];
            }
        }
        if ([self dataSourceModel].isLoading) {
            if (scrollView.contentOffset.y >= 0) {
                _controller.tableView.contentInset = UIEdgeInsetsZero;
                
            } else if (scrollView.contentOffset.y < 0) {
                _controller.tableView.contentInset = UIEdgeInsetsMake(kHeaderVisibleHeight, 0, 0, 0);
            }
        }
        
    }
    
    //****  加载更多 ****
    if (_flags.isFooterEnable && ![self dataSourceModel].isLoading) {
        CGFloat scrollRatio = scrollView.contentOffset.y/(scrollView.contentSize.height - scrollView.frame.size.height);
        scrollRatio = MAX(MIN(scrollRatio, 1),0);
        BOOL shouldLoad = scrollRatio > kInfiniteScrollThreshold;
        if (shouldLoad) {
            if ([[self dataSourceModel] hasMore]) {
                [[self dataSourceModel] loadMore:YES];
                [_footerView setLoading:YES];
                _footerView.hasMoreData = YES;
                _hasLoadMore = YES;
                _hasRefresh = NO;
            }else{
                _footerView.hasMoreData = NO;
                [_footerView setLoading:NO];
            }
            
        }
    }
}

- (void)scrollViewWillBeginDragging:(UIScrollView *)scrollView {
}

- (void)scrollViewDidEndDragging:(UIScrollView *)scrollView willDecelerate:(BOOL)decelerate {
    if (_flags.isHeaderEnable) {
        if (scrollView.contentOffset.y <= kRefreshDeltaY && ![self dataSourceModel].isLoading) {
            [[self dataSourceModel] loadMore:NO];
            _hasLoadMore = NO;
            _hasRefresh = YES;
        }
    }
}

- (void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView {
}




#pragma mark -
#pragma mark public dataSoure 回调更新状态

-(void)dataSourceModelDidStartLoad{
    if (_flags.isHeaderEnable) {
        [_headerView setState:EPullRefreshLoading];
        [UIView beginAnimations:nil context:NULL];
        [UIView setAnimationDuration:0.7];
        if (_controller.tableView.contentOffset.y < 0) {
            _controller.tableView.contentInset = UIEdgeInsetsMake(kHeaderVisibleHeight, 0.0f, 0.0f, 0.0f);
        }
        [UIView commitAnimations];
    }
}

-(void)dataSourceModelDidFinishLoad{
    if (_flags.isHeaderEnable) {
        [_headerView setState:EPullRefreshNormal];
        [UIView beginAnimations:nil context:NULL];
        [UIView setAnimationDuration:0.7];
        _controller.tableView.contentInset = UIEdgeInsetsZero;
        [UIView commitAnimations];
    }
    if (_flags.isFooterEnable) {
        [_footerView setLoading:NO];
    }
    
    if (![[self dataSourceModel] hasMore]) {
        _footerView.hasMoreData = NO;
        [_footerView setLoading:NO];
    }
}

-(void)dataSourceModelDidFailLoad{
    if (_flags.isHeaderEnable) {
        [_headerView setState:EPullRefreshNormal];
        [UIView beginAnimations:nil context:NULL];
        [UIView setAnimationDuration:0.7];
        _controller.tableView.contentInset = UIEdgeInsetsZero;
        [UIView commitAnimations];
    }
    if (_flags.isFooterEnable) {
        
        [_footerView setLoading:NO];
    }
}

-(void)dataSourceModelDidCancelLoad{
    if (_flags.isHeaderEnable) {
        [_headerView setState:EPullRefreshNormal];
        [UIView beginAnimations:nil context:NULL];
        [UIView setAnimationDuration:0.7];
        _controller.tableView.contentInset = UIEdgeInsetsZero;
        [UIView commitAnimations];
    }
    if (_flags.isFooterEnable) {
        
        [_footerView setLoading:NO];
    }
}

/**
 * 高度---传递给dataSource控制
 */
- (CGFloat)tableView:(UITableView*)tableView heightForRowAtIndexPath:(NSIndexPath*)indexPath {
    
    id<DOTableViewDataSource> dataSource = (id<DOTableViewDataSource>)tableView.dataSource;
    id object = [dataSource tableView:tableView objectForRowAtIndexPath:indexPath];
    
    if (!object) {
        return 60;
    }
    
    //**** cell ****
    Class cls = [dataSource tableView:tableView cellClassForObject:object];
    return [cls tableView:tableView rowHeightForObject:object];
    
    
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    id<DOTableViewDataSource> dataSource = (id<DOTableViewDataSource>)tableView.dataSource;
    id object = [dataSource tableView:tableView objectForRowAtIndexPath:indexPath];
    
    DOTableItem *aItem = object;
    if ([_controller respondsToSelector:@selector(didSelectRowAtIndexPath:withDataDic:)]) {
        [_controller performSelector:@selector(didSelectRowAtIndexPath:withDataDic:) withObject:indexPath withObject:aItem.dataDic];
    }
}

/**
 * 删除特殊继承实现
 */
-(BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath{
    return NO;
}

-(NSString *)tableView:(UITableView *)tableView titleForDeleteConfirmationButtonForRowAtIndexPath:(NSIndexPath *)indexPath{
	return  @"";
}

-(UITableViewCellEditingStyle)tableView:(UITableView *)tableView editingStyleForRowAtIndexPath:(NSIndexPath *)indexPath{
    return UITableViewCellEditingStyleNone;
}

- (BOOL)tableView:(UITableView *)tableView canMoveRowAtIndexPath:(NSIndexPath *)indexPath {
	return NO;
}

@end

