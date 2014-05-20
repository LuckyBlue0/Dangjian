//
//  LLModelDelegate.h
//  LLBar
//
//  Created by kllmctrl on 12-10-30.
//
//

#import <Foundation/Foundation.h>

@class  DOModel;

@protocol DOModelDelegate <NSObject>

@optional

- (void)modelDidStartLoad:(DOModel*)model;

- (void)modelDidFinishLoad:(DOModel*)model;

- (void)model:(DOModel*)model didFailLoadWithError:(NSError*)error;

@end


