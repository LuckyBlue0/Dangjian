
#import <Foundation/Foundation.h>
#import "ASIHTTPRequest.h"
#import "DOModel.h"


/**
 * 实现(重载)网络加载
 */
@protocol  DOTableViewDataSource;

@interface DOTableModel : DOModel{
    
    ASIHTTPRequest *_loadingRequest;
    
    BOOL            _isLoadingMore;
    BOOL            _hasMore;
    BOOL            _isFirstLoad;//该model第一次加载
    BOOL            _isOutdate;//是否可以重载
    
    /**
     * 0:带参数分页
     * 1:不带参数分页
     * 2:不分页
     */
    NSInteger _isPage;//是否分页
    
    NSString        *_loadQuery;
    NSString        *_loadQueryFormat;
    NSMutableArray  *_datas;
    NSInteger       _page;//当前页
    NSInteger       _numPerPage;//一页记录数
    
    id<DOTableViewDataSource> _dataSourceDelegate;
}

@property (nonatomic,assign) BOOL               hasMore;
@property (nonatomic,assign) BOOL               isFirstLoad;
@property (nonatomic,retain) NSMutableArray     *datas;
@property (nonatomic,retain) ASIHTTPRequest     *loadingRequest;

@property (nonatomic,assign) id<DOTableViewDataSource> dataSourceDelegate;


-(id)initWithQueryFormat:(NSString *)aQuery 
          withNumPerPage:(NSInteger)aNumPerPage;

-(id)initWithQueryFormat:(NSString *)aQuery;

-(void)loadMore:(BOOL)more;

@end


