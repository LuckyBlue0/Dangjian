/*
 * 异常报告打印
 */

#import <Foundation/Foundation.h>

@interface KCExceptionHandler : NSObject

+ (void)setDefaultHandler;
+ (NSUncaughtExceptionHandler*)getHandler;

@end




/*
 
 - (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
 
 // Override point for customization after application launch.
 
 [window makeKeyAndVisible];
 [NdUncaughtExceptionHandler setDefaultHandler];
 NSArray *array = [NSArray arrayWithObject:@"there is only one objective in this arary,call index one, app will crash and throw an exception!"];
 NSLog(@"%@", [array objectAtIndex:1]);
 
 return YES;
 }
 */

