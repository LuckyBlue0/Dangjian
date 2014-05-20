/*
 * 扩充 UINavigationBar
 * Created by kllmctrl on 12-7-20.
 */

#import <Foundation/Foundation.h>


/**
 * 包含该文件 #import
 * ios5.0以下重绘nav UINavigationBar
 
 - (void)drawRect:(CGRect)rect {
 UIImage *image = [UIImage imageNamed:@"nav"];
 if (nil!=image) {
 [image drawInRect:CGRectMake(0, 0, self.frame.size.width, self.frame.size.height)];
 }
 }
 

 */

