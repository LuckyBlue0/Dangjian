/**
 * dataSource
 */

#import <Foundation/Foundation.h>
#import "DOTableModelVC.h"
#import "DOTableViewDelegate.h"
#import "DOTableModel.h"


@protocol DOTableViewDataSource <UITableViewDataSource>

-(DOTableModel*)model;

- (id)tableView:(UITableView*)tableView objectForRowAtIndexPath:(NSIndexPath*)indexPath;

- (Class)tableView:(UITableView*)tableView cellClassForObject:(id)object;

- (void)tableView:(UITableView*)tableView cell:(UITableViewCell*)cell willAppearAtIndexPath:(NSIndexPath*)indexPath;


//继承
-(ASIHTTPRequest*)customRequest:(NSString*)aURLStr;

-(NSDictionary*)parseData:(NSData*)aData;

@end


/**
 * LLTableViewDataSource
 */
@interface DOTableViewDataSource : NSObject<DOTableViewDataSource,DOModelDelegate>{
    
    NSMutableArray* _items;//对应的cell中的item数组
    DOTableModel*     _model;//请求model
    
    /**
     * 关联
     */
    DOTableModelVC*          _controller;
    id<DOTableViewDelegate> _tableViewDelegate;
}

@property(nonatomic,retain)NSMutableArray*  items;

@property(nonatomic,retain)DOTableModel*    model;

-(id)initWithController:(DOTableModelVC*)controller 
              withItems:(NSArray*)items;

-(id)initWithController:(DOTableModelVC*)controller 
            withObjects:(id)object,...;

- (id)initWithController:(DOTableModelVC*)controller
         withQueryFormat:(NSString*)aQuery 
          withNumPerPage:(NSInteger)aNumPerPage;

-(id)initWithController:(DOTableModelVC*)controller 
               withFile:(NSString*)aFile;

-(id<DOTableViewDelegate>)tableViewDelegate;

@end




