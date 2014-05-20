//
//  LLTabStrolView.h
//  ShenzhenShopping
//
//  Created by kllmctrl on 12-6-19.
//  Copyright 2012 广州市道一信息技术有限公司. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>


@protocol DOTabStrolViewDelegate;
@interface DOTabStrolView : UIView {
	UIScrollView				*_sv;
	NSMutableArray				*_itemsArray;
	id<DOTabStrolViewDelegate>	_delegate;
	NSInteger					_selectedItem;
}
@property(nonatomic,assign)id<DOTabStrolViewDelegate> delegate;
@property(nonatomic,assign)NSInteger selectedItem;
-(void)setItemsObjects:(NSArray*)aItems;
@end


@protocol DOTabStrolViewDelegate<NSObject>
-(void)tabStrolSelected:(DOTabStrolView*)aLLTabStrolView;

@end



/*
 
 _pageStrolView = [[LLTabStrolView alloc] initWithFrame:CGRectMake(0, 460-44-43, 320, 43)];
 _pageStrolView.delegate = self;
 [self.view addSubview:_pageStrolView];
 
 
 
 
 #pragma mark -
 #pragma mark LLTabStrolViewDelegate
 -(void)tabStrolSelected:(LLTabStrolView*)aLLTabStrolView{
 [_pageView setCenterPageIndex:aLLTabStrolView.selectedItem];
 }
 
 
 
 
 */