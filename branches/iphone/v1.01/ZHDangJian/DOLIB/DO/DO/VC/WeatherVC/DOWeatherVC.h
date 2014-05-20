//
//  DOWeatherVC.h
//  DO
//
//  Created by kllmctrl on 12-11-13.
//  Copyright (c) 2012年 kllmctrl. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "DODetailModelVC.h"
#import "DOModelDelegate.h"

@interface DOWeatherVC : DODetailModelVC<DOModelDelegate>{
    
    NSDictionary *_dataDic;
}

@property(nonatomic,retain)NSDictionary *dataDic;

@end


