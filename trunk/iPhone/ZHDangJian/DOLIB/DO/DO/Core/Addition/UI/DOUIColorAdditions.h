/*
 * 扩充 UIColor
 * Created by  kllmctrl on 12-7-20.
 */
#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>

/**
 * UIColor 宏
 */

#define RGB(__R,__G,__B)		[UIColor colorWithRed:((__R)/255.0f) green:((__G)/255.0f) blue:((__B)/255.0f) alpha:1.0]
#define RGBA(__R,__G,__B,__A)	[UIColor colorWithRed:(__R)/255.0f green:(__G)/255.0f blue:(__B)/255.0f alpha:__A/1.0]
#define CLEAR_COLOR				[UIColor clearColor]
#define WHITE_COLOR				[UIColor whiteColor]
#define BLACK_COLOR				[UIColor blackColor]
#define GRAY_COLOR				[UIColor grayColor]
#define BLUE_COLOR				[UIColor blueColor]
#define RED_COLOR				[UIColor redColor]
#define GREEN_COLOR				[UIColor greenColor]
#define YELLOW_COLOR			[UIColor yellowColor]

#define HEXCOLOR(rgbValue) [UIColor colorWithRed:((float)((rgbValue & 0xFF0000) >> 16))/255.0 green:((float)((rgbValue & 0xFF00) >> 8))/255.0 blue:((float)(rgbValue & 0xFF))/255.0 alpha:1.0]

@interface UIColor (DOUIColorAdditions)



@end