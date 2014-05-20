//
//  KCImagesView.h
//  DO
//  背景视图---上,中,下图片组合
//  Created by kllmctrl on 13-1-22.
//  Copyright (c) 2013年 kllmctrl. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface KCImagesView : UIView {
	
	UIImage *_topImage;
	UIImage *_midImage;
	UIImage *_bottomImage;
	
	
	UIImageView *_topImageView;
	UIImageView *_midImageView;
	UIImageView *_bottomImageView;
	
	BOOL _ishightLevel;
}

@property(nonatomic,assign)UIImage *topImage;
@property(nonatomic,assign)UIImage *midImage;
@property(nonatomic,assign)UIImage *bottomImage;
@property(nonatomic,assign)BOOL ishightLevel;
@end

