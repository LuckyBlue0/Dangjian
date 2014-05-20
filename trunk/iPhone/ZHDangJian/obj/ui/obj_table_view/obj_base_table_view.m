//
//  obj_base_table_view.m
//  obj
//
//  Created by daoyi on 13-5-7.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//

#import "obj_base_table_view.h"
#import "obj_ui.h"
#import <Foundation/NSObjCRuntime.h>

@implementation obj_base_table_view
/***************************************************/
#pragma mark - init and dealloc
/***************************************************/
-(void)dealloc
{
    self.data = nil;
    RN(m_delegate);
    [super dealloc];
}
-(void)obj_base_table_view_init
{
    self.delegate = self;
}
- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self)
    {
        [self obj_base_table_view_init];
    }
    return self;
}
-(void)awakeFromNib
{
    [self obj_base_table_view_init];
}
/***************************************************/
#pragma mark - property
/***************************************************/
-(void)setDelegate:(id<UITableViewDelegate>)delegate
{
    if ([delegate isEqual:self])
        [super setDelegate:self];
    else
    {
        RN(m_delegate);
        RETAIN(m_delegate, delegate);
    }
}
/***************************************************/
#pragma mark - UIScrollView delegate
/***************************************************/
- (void)scrollViewDidScroll:(UIScrollView *)scrollView
{
    //add code here
    {
        
    }
    if([self isMemberOfClass:[obj_base_table_view class]])
        METHOD_SAFE(m_delegate, @selector(scrollViewDidScroll:),scrollView);
}
/***************************************************/
#pragma mark - UITableViewDataSource delegate
/***************************************************/
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    //add code here
    {
        
    }
    if([self isMemberOfClass:[obj_base_table_view class]])
        METHOD_SAFE(m_delegate, @selector(numberOfRowsInSection:),section);
    return 0;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return nil;
}
@end
