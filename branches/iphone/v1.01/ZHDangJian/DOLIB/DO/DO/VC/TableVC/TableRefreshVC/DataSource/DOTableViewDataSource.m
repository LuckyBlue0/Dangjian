#import "DOTableViewDataSource.h"
#import "DOTableItem.h"
#import "DOTableViewCell.h"
#import <objc/runtime.h>
#import "DOAddition.h"
#import "DOMacro.h"
#import "JSONKit.h"

@implementation DOTableViewDataSource

@synthesize items = _items;
@synthesize model=_model;


#pragma mark -
#pragma mark init
-(id)initWithController:(DOTableModelVC*)controller 
              withItems:(NSArray*)items{
    if (self = [super init]) {
        _items = [items mutableCopy];
        _controller = controller;
        _controller.tableView.dataSource=self;
    }
    return self;
}

-(id)initWithController:(DOTableModelVC*)controller 
            withObjects:(id)object,...{
    
    NSMutableArray* items = [NSMutableArray array];
    va_list ap;
    va_start(ap, object);
    while (object) {
        [items addObject:object];
        object = va_arg(ap, id);
    }
    va_end(ap);
    
    return [self initWithController:controller withItems:items];
}

/**
 * Class public model网络请求
 */
- (id)initWithController:(DOTableModelVC*)controller
         withQueryFormat:(NSString*)aQuery 
          withNumPerPage:(NSInteger)aNumPerPage{
    if (self = [super init]) {
        _items = [[NSMutableArray alloc] init];
        
        _model= [[DOTableModel alloc] initWithQueryFormat:aQuery withNumPerPage:aNumPerPage];
        
        _model.delegate=self;
        _model.dataSourceDelegate = self;
        [_model loadMore:NO];

        _controller = controller;
        _controller.tableView.dataSource=self;
    }
    return self;
}

-(id)initWithController:(DOTableModelVC*)controller 
               withFile:(NSString*)aFile{
    NSString *filePath = [[NSBundle mainBundle] pathForResource:aFile ofType:nil];
    NSDictionary *fileDic = [[[NSDictionary alloc] initWithContentsOfFile:filePath] autorelease];
    NSArray *fileArray = [fileDic objectForKey:@"data"];

    NSMutableArray *aItems = [NSMutableArray array];
    //**** 封装成item ****
    for (int i=0; i<[fileArray count]; i++) {
        DOTableItem *aLLTableItem = [[DOTableItem alloc] initWithData:[fileArray objectAtIndex:i]];
        [aItems addObject:aLLTableItem];
        [aLLTableItem release];
    }
    return [self initWithController:controller withItems:aItems];
}

-(void)dealloc{
    DO_RELEASE_SAFELY(_items);
    _model.delegate = nil;
    DO_RELEASE_SAFELY(_model);
    _controller=nil;
    _tableViewDelegate=nil;
    
    [super dealloc];
}

-(NSMutableArray*)items {
    if (!_items) {
        _items = [[NSMutableArray alloc] init];
    }
    return _items;
}

-(DOTableModel*)model{
    return _model?_model:nil;
}

-(id<DOTableViewDelegate>)tableViewDelegate{
    if (nil==_tableViewDelegate) {
        _tableViewDelegate = (id<DOTableViewDelegate>)_controller.tableView.delegate;
    }
    return _tableViewDelegate;
}




#pragma mark -
#pragma mark LLTableModelDelegate model获取数据后处理

- (void)modelDidStartLoad:(DOModel*)model {
    
    /**
     * 更新视图
     */
    if (model == self.model && [_model isFirstLoad]) {
        [_controller invalidateView];
    }
    
    [[self tableViewDelegate] dataSourceModelDidStartLoad];
}

- (void)modelDidFinishLoad:(DOModel*)model {
    
    [[self tableViewDelegate] dataSourceModelDidFinishLoad];
    
    /**
     * 保存数据到_items
     * model保存总的数据
     */
    [_items removeAllObjects];
    for (int i=0; i<[((DOTableModel*)_model).datas count]; i++) {
        DOTableItem *aLLTableItem = [[DOTableItem alloc] initWithData:[((DOTableModel*)_model).datas objectAtIndex:i]];
        [_items addObject:aLLTableItem];
        [aLLTableItem release];
    }
    [_controller.tableView reloadData];
    
    /**
     * 更新视图
     */
    if (model == self.model) {
        [_controller clearModelError];
        [_controller invalidateView];
    }
}

- (void)model:(DOModel*)model didFailLoadWithError:(NSError*)error {
    
    
    [_controller.tableView reloadData];
    /**
     * 更新视图
     */
    if (model == _model) {
        _controller.modelError = error;
        [_controller invalidateView];
    }
    
    [[self tableViewDelegate] dataSourceModelDidFailLoad];
}

- (void)modelDidCancelLoad:(DOModel*)model {
    [[self tableViewDelegate] dataSourceModelDidCancelLoad];
}




#pragma mark -
#pragma mark UITableViewDataSource
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return _items.count;
}

- (id)tableView:(UITableView*)tableView objectForRowAtIndexPath:(NSIndexPath*)indexPath {
    if (indexPath.row < _items.count) {
        return [_items objectAtIndex:indexPath.row];
        
    } else {
        return nil;
    }
}

- (Class)tableView:(UITableView*)tableView cellClassForObject:(id)object{
    if ([object isKindOfClass:[DOTableItem class]]) {
        return [DOTableViewCell class];
    }
    return nil;
}

- (void)tableView:(UITableView*)tableView cell:(UITableViewCell*)cell willAppearAtIndexPath:(NSIndexPath*)indexPath {
}

- (UITableViewCell*)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    id object = [self tableView:tableView objectForRowAtIndexPath:indexPath];
	Class cellClass = [self tableView:tableView cellClassForObject:object];
    const char* className = class_getName(cellClass);
    NSString* identifier = [[NSString alloc] initWithBytesNoCopy:(char*)className
                                                          length:strlen(className)
                                                        encoding:NSASCIIStringEncoding freeWhenDone:NO];
    UITableViewCell* cell =(UITableViewCell*)[tableView dequeueReusableCellWithIdentifier:identifier];
    if (cell == nil) {
        cell = [[[cellClass alloc] initWithStyle:UITableViewCellStyleDefault
                                 reuseIdentifier:identifier] autorelease];
    }
    [identifier release];
    
    if ([cell isKindOfClass:[DOTableViewCell class]]) {
        [(DOTableViewCell*)cell setObject:object];
    }
    [self tableView:tableView cell:cell willAppearAtIndexPath:indexPath];
    return cell;
}


/**
 * 删除特殊继承实现
 */
- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath{
}



//代理继承请求model
-(ASIHTTPRequest*)customRequest:(NSString*)aURLStr{
    
    ASIHTTPRequest  *aASIHTTPRequest_DO=[[[ASIHTTPRequest alloc] initWithURL:[NSURL URLWithString:[aURLStr stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]]] autorelease];
    [aASIHTTPRequest_DO setPersistentConnectionTimeoutSeconds:120];
    
    return aASIHTTPRequest_DO;
}


//代理继承解析model
-(NSDictionary*)parseData:(NSData*)aData{
    
    NSMutableArray *aNSMutableArray = [[[NSMutableArray alloc] init] autorelease];
    BOOL isMore = YES;
    DOLOGDATA(aData);
    id a= [aData objectFromJSONData];
    if (nil!=a && [a isKindOfClass:[NSDictionary class]]) {
        
        if (nil!=[a objectForKey:@"datas"]
            && [[a objectForKey:@"datas"] isKindOfClass:[NSArray class]]) {
            NSArray *aArray = (NSArray*)[a objectForKey:@"datas"];
            for (int i=0; i<[aArray count]; i++) {
                if ([[aArray objectAtIndex:i] isKindOfClass:[NSDictionary class]]) {
                    [aNSMutableArray addObject:[aArray objectAtIndex:i]];
                }
            }
            
            NSString *ahaveNextPage = [a objectForKey:@"haveNextPage"];
            if (ahaveNextPage!=nil
                && [ahaveNextPage isKindOfClass:[NSString class]] && ![ahaveNextPage isEqualToString:@"0"]) {
                isMore = YES;
            }else{
                isMore = NO;
            }
            
        }
    }
    
    NSString *aRetStr = @"1";
    if (!isMore) {
        aRetStr = @"0";
    }
    
    //返回数据 和是否有更多字典给Model处理
    NSDictionary *aDic = [NSDictionary dictionaryWithObjectsAndKeys:aNSMutableArray,@"datas",aRetStr,@"hasMore", nil];
    
    return aDic;
    
}


@end

