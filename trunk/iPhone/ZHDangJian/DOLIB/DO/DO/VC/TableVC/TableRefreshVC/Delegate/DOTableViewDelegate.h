/**
 * table delegate
 */

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import "DOTableModel.h"

@class DOTableModelVC;
@class DOTableRefreshBar;
@class DOTableFooterBar;

@protocol DOTableViewDelegate <UITableViewDelegate>

-(void)dataSourceModelDidStartLoad;
-(void)dataSourceModelDidFinishLoad;
-(void)dataSourceModelDidFailLoad;
-(void)dataSourceModelDidCancelLoad;

-(BOOL)hasLoadMore;
-(BOOL)hasRefresh;
@end


/**
 *
 * LLTableViewDelegate
 */
@interface DOTableViewDelegate : NSObject<DOTableViewDelegate>{

    DOTableRefreshBar*  _headerView;
    DOTableFooterBar*   _footerView;
    
    /**
     * 关联
     */
    DOTableModelVC*  _controller;
    DOTableModel*   _dataSourceModel;
    
    struct {
        unsigned int isHeaderEnable:1;
        unsigned int isFooterEnable:1;
    } _flags;
    
    
    BOOL _hasLoadMore;//加载过更多
    BOOL _hasRefresh;//加载过更多
    
}

- (id)initWithController:(DOTableModelVC*)controller 
              withHeader:(BOOL)aHeader 
              withFooter:(BOOL)aFooter;

@property (nonatomic, readonly) DOTableModelVC* controller;

@property (nonatomic, retain) DOTableRefreshBar* headerView;

@property (nonatomic, retain) DOTableFooterBar* footerView;


@property (nonatomic, assign) BOOL hasLoadMore;
@property (nonatomic, assign) BOOL hasRefresh;
@end
