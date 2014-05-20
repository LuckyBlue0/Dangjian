#import "DOTableModel.h"
#import "JSONKit.h"
#import "DOMacro.h"
#import "DOTableViewDataSource.h"

@interface DOTableModel()

-(void)resetModel;

-(void)requestData;

@end

@implementation DOTableModel
@synthesize hasMore   = _hasMore;
@synthesize isFirstLoad=_isFirstLoad;
@synthesize datas=_datas;
@synthesize dataSourceDelegate = _dataSourceDelegate;
@synthesize loadingRequest=_loadingRequest;

-(id)initWithQueryFormat:(NSString *)aQuery 
          withNumPerPage:(NSInteger)aNumPerPage{
    if (self=[super init]) {
        
        //不需要分页
        if (aNumPerPage==-1) {
            _loadQueryFormat = [[NSString alloc] initWithFormat:@"%@",aQuery];
            _isPage = 2;
        }

        //不带参数分页
        else if (aNumPerPage==0) {
            _loadQueryFormat = [[NSString alloc] initWithFormat:@"%@",aQuery];
            _isPage = 1;
        }
        else{
            _loadQueryFormat = [[NSString alloc] initWithFormat:aQuery,@"%d",aNumPerPage];
            _isPage = 0;
        }
        
        _isFirstLoad = YES;
        _numPerPage = aNumPerPage;
    }
    [self resetModel];
    return self;
}

-(id)initWithQueryFormat:(NSString *)aQuery{
    return [self initWithQueryFormat:aQuery withNumPerPage:0];
}

- (void)dealloc {
    _dataSourceDelegate = nil;
    
    DO_RELEASE_SAFELY(_datas);
    [_loadingRequest clearDelegatesAndCancel];
    DO_RELEASE_SAFELY(_loadingRequest);
    
    [super dealloc];
}

- (void)reset {
}



#pragma mark -
#pragma mark LLModel

- (BOOL)isLoaded {
    return !_loadingRequest;
}

- (BOOL)isLoading {
    return !!_loadingRequest;
}

- (BOOL)isLoadingMore {
    return _isLoadingMore;
}

- (BOOL)isOutdated {
    return _isOutdate;
}

- (BOOL)isFirstLoad {
    return _isFirstLoad;
}

-(BOOL)isEmpty{
    if ([_datas count]==0) {
        return YES;
    }
    return NO;
}

-(void)loadMore:(BOOL)more{
    
    if (!self.isLoading) {
		
		if (more) {
            if (!_hasMore) {
                return;
            }
			_page += 1;
		}
        else {
            [self resetModel];
		}
        
		[self requestData];
    }
    
}

- (void)resetModel {
    _hasMore = YES;
    _page = 1;
    if (_datas!=nil) {
        [_datas removeAllObjects];
        DO_RELEASE_SAFELY(_datas);
    }
    _datas = [[NSMutableArray alloc] init];
}

-(ASIHTTPRequest*)customRequest:(NSString*)aURLStr{
    
    if (_dataSourceDelegate) {
        
        return [_dataSourceDelegate customRequest:aURLStr];
    }
    return _loadingRequest;
    
}

-(void)requestData{
    if (nil!=_loadQuery) {
        DO_RELEASE_SAFELY(_loadQuery);
    }
    
    //不需要分页
    if (_isPage==2) {
        _loadQuery = [[NSString alloc] initWithFormat:@"%@",_loadQueryFormat];
    }else{
        _loadQuery = [[NSString alloc] initWithFormat:_loadQueryFormat,_page];
    }
    
    [_loadingRequest clearDelegatesAndCancel];
    DO_RELEASE_SAFELY(_loadingRequest);
    
    self.loadingRequest = [self customRequest:_loadQuery];
    [_loadingRequest setDelegate:self];
    [_loadingRequest setDidFinishSelector:@selector(requestDataFinished:)];
    [_loadingRequest setDidFailSelector:@selector(requestDataFailed:)];
    [_loadingRequest startAsynchronous];
}

- (void)cancel {
    [_loadingRequest cancel];
}

- (void)invalidate:(BOOL)erase {
}

-(BOOL)hasMore{
    return _hasMore;
}

-(NSMutableArray*)dataArray{
    return _datas;
}




#pragma mark -
#pragma mark ASIHTTPRequest_DO

-(void)requestStarted:(ASIHTTPRequest *)aASIHTTPRequest_DO{
    if (_isFirstLoad) {
        _isOutdate = YES;
    }
    
    [_loadingRequest release];
    _loadingRequest = [aASIHTTPRequest_DO retain];
    [self didStartLoad];
}

-(NSDictionary*)parseData:(NSData*)aData{
    
    if (_dataSourceDelegate) {
        return [_dataSourceDelegate parseData:aData];
    }
    return nil;
    
}

- (void)requestDataFinished:(ASIHTTPRequest *)aASIHTTPRequest_DO{
    _isFirstLoad =NO;
    int statusCode = [aASIHTTPRequest_DO responseStatusCode];
    if(statusCode == 200){
        
        NSData* aData=[[[aASIHTTPRequest_DO responseData] retain] autorelease];
        NSDictionary *aDic = [self parseData:aData];
        
        NSArray *aArray = (NSArray*)[aDic objectForKey:@"datas"];
        NSString *aHasMore = (NSString*)[aDic objectForKey:@"hasMore"];
        
        if (aArray) {
            for (int i=0; i<[aArray count]; i++) {
                [_datas addObject:[aArray objectAtIndex:i]];
            }
        }
        
        //判断是否有更多,如果没有判断是否有下一页，dic直接赋值为“1”传给[aArray count]==_numPerPage判断
        if (aHasMore) {
            if ([aHasMore isEqualToString:@"1"] && [aArray count]==_numPerPage) {
                _hasMore = YES;
            }else{
                _hasMore = NO;
            }
        }else{
            _hasMore = NO;
        }
    }
    
    if (_isPage==2) {
        _hasMore = NO;
    }
    
    if ([_datas count]>0) {
        _isOutdate = NO;
    }else{
        _isOutdate = YES;
    }
    
    DO_RELEASE_SAFELY(_loadingRequest);
    [self didFinishLoad];
}

- (void)requestDataFailed:(ASIHTTPRequest *)aASIHTTPRequest_DO{
    _isFirstLoad =NO;
    _isOutdate = YES;
    DO_RELEASE_SAFELY(_loadingRequest);
    [self didFailLoadWithError:aASIHTTPRequest_DO.error];
}

@end



