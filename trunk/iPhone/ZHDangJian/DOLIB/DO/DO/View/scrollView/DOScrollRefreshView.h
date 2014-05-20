//
//  DOScrollRefreshView.h
//  DO
//
//  Created by kllmctrl on 12-11-14.
//  Copyright (c) 2012年 kllmctrl. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
//
//@interface DOScrollRefreshView : NSObject
//
//@end


@class DOScrollRefreshView;
@protocol DOScrollRefreshViewDelegate
-(UIView*) viewForScrollView:(DOScrollRefreshView*)scrollView atPage:(NSUInteger)page;
-(NSUInteger) pageCount;
@optional
-(void) headerLoadedInScrollView:(DOScrollRefreshView*)scrollView;
-(void) headerUnloadedInScrollView:(DOScrollRefreshView*)scrollView;
-(void) footerLoadedInScrollView:(DOScrollRefreshView*)scrollView;
-(void) footerUnloadedInScrollView:(DOScrollRefreshView*)scrollView;
@end

@interface DOScrollRefreshView : UIScrollView <UIScrollViewDelegate>
{
    id<DOScrollRefreshViewDelegate,UIScrollViewDelegate> externalDelegate;
    
    IBOutlet UIView* headerView;
    IBOutlet UIView* footerView;
    
    BOOL _headerLoaded;
    BOOL _footerLoaded;
    
    NSUInteger currentPageIndex;
    UIView* currentPageView;
    
    
    CGFloat _currentPageHeight;
}

@property (nonatomic, assign) id<DOScrollRefreshViewDelegate,UIScrollViewDelegate> externalDelegate;
@property (nonatomic, retain) IBOutlet UIView* headerView;
@property (nonatomic, retain) IBOutlet UIView* footerView;
@property (nonatomic) NSUInteger currentPageIndex;
@property (nonatomic, retain) UIView* currentPageView;

- (id) initWithFrame:(CGRect)frame headerView:(UIView*)headerView footerView:(UIView*)footerView startingAt:(NSUInteger)pageIndex delegate:(id<DOScrollRefreshViewDelegate,UIScrollViewDelegate>)verticalSwipeDelegate;

-(void)resetCurrentPageHeight:(CGFloat)aFloat;
-(void) setCurrentPageIndex:(NSUInteger)newValue;

@end

