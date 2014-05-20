#import "DOTableModelVC.h"
#import "DOMacro.h"
#import "DOAddition.h"

@interface DOTableModelVC()
-(void)createDataSource;
- (id<DOTableViewDelegate>)createDelegate;
@end



/**
 * LLTableModelVC
 */
@implementation DOTableModelVC
@synthesize tableView       = _tableView;
@synthesize dataSource      = _dataSource;


-(void)dealloc {
    _tableView.delegate = nil;
    _tableView.dataSource = nil;
    
    DO_RELEASE_SAFELY(_tableView);
    
    [super dealloc];
}


-(void)releaseSafeError{
    [_modelError release];
    _modelError=nil;
}

#pragma mark - 
#pragma mark content view

-(UITableView*)tableView{
    if (!_tableView) {
        UITableView *aTV = [[[UITableView alloc] initWithFrame:self.view.bounds style:UITableViewStylePlain] autorelease];
        aTV.autoresizingMask =  UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;
        aTV.delegate=[[self createDelegate] retain];
        [self.view addSubview:aTV];
        
        self.tableView = aTV;
        
    }
    return _tableView;
}

- (DOModel*)model {
    if (!_model) {
        
        /**
         * 创建model 数据源
         */
        [self createDataSource];
        self.model = _dataSource.model;
        if (!_model) {
            self.model = [[[DOModel alloc] init] autorelease];
        }
    }
    return _model;
}


//- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
//    return _items.count;
//}


- (BOOL)canShowModel {
    
    if ([_dataSource respondsToSelector:@selector(numberOfSectionsInTableView:)]) {
        NSInteger numberOfSections = [_dataSource numberOfSectionsInTableView:_tableView];
        if (!numberOfSections) {
            return NO;
            
        } else if (numberOfSections == 1) {
            NSInteger numberOfRows = [_dataSource tableView:_tableView numberOfRowsInSection:0];
            return numberOfRows > 0;
            
        } else {
            return YES;
        }
        
    } else {
        NSInteger numberOfRows = [_dataSource tableView:_tableView numberOfRowsInSection:0];
        return numberOfRows >0;
    }
}

- (BOOL)canShowEmpty {
    return [_dataSource model].isEmpty &&![_dataSource model].isLoading;
}

- (void)updateView {
    
    if ([self canShowModel]) {
        
        if (_flags.isShowingEmpty) {
            [self showEmpty:NO];
        }
        
        if (_flags.isShowingLoading) {
            [self showLoading:NO];
        }
        
        if (_flags.isShowingError) {
            [self showError:NO];
        }
        
        [self showModel:YES];
        return;
    }
    //为空
    else{
        if ([self canShowEmpty]) {
            _flags.isUpdatingView = NO;
            _flags.isShowingLoading = NO;
            _flags.isShowingModel = NO;
            _flags.isShowingEmpty = YES;
            [self showLoading:NO];
            [self showEmpty:YES];

            return;
        }
    }
    
    [super updateView];
}

#pragma mark -
#pragma mark 继承 delegate&&datasource
- (id<DOTableViewDelegate>)createDelegate {
    return nil;
}
-(void)createDataSource{
}
-(void)didSelectRowAtIndexPath:(NSIndexPath *)indexPath withDataDic:(NSDictionary*)aDic{
    //NSLog(@"aDic=%@",aDic);
}




#pragma mark -
#pragma mark 各种状态下视图执行

- (void)showModel:(BOOL)show {
    /**
     * 内容视图
     */
    if (show) {
        [self.view addSubview:[self tableView]];
    }else{
        [_tableView removeFromSuperview];
        DO_RELEASE_SAFELY(_tableView);
    }
}


@end


