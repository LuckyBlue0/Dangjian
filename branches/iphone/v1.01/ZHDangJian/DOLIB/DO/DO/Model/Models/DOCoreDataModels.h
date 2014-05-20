//
//  LLCoreDataModels.h
//  LLBar
//  有关CoreData数据库的数据模型
//  Created by kllmctrl on 12-10-31.
//
//

#import <Foundation/Foundation.h>
#import "DOModel.h"
#import <CoreData/CoreData.h>



//子类为静态的全局类---》- (void)applicationWillTerminate:(UIApplication *)application保存数据

@interface DOCoreDataModels : DOModel{
    NSManagedObjectModel *managedObjectModel;//数据模型对象
    NSManagedObjectContext *managedObjectContext;//上下文对象
    NSPersistentStoreCoordinator *persistentStoreCoordinator;//持久性存储区
}

@property(retain,nonatomic) NSManagedObjectModel *managedObjectModel;
@property(retain,nonatomic) NSManagedObjectContext *managedObjectContext;
@property(retain,nonatomic) NSPersistentStoreCoordinator *persistentStoreCoordinator;

//初始化Core Data使用的数据库
-(NSPersistentStoreCoordinator *)persistentStoreCoordinator;
//managedObjectModel的初始化赋值函数
-(NSManagedObjectModel *)managedObjectModel;
//managedObjectContext的初始化赋值函数
-(NSManagedObjectContext *)managedObjectContext;

-(void)saveApplicationWillTerminate;


@end




/**
 
 DEMO
 创建Model.xcdatamodeld
 Entry:
 @property (nonatomic, retain) NSString * title;
 @property (nonatomic, retain) NSDate * date;
 @property (nonatomic, retain) NSString * body;
 
 实现VCModel如下
 
 */


//  
////
////  VCModel.h
////  Demo
////
////  Created by kllmctrl on 12-10-30.
////  Copyright (c) 2012年 LL. All rights reserved.
////
//
//#import <Foundation/Foundation.h>
//#import "LLCoreDataModels.h"
//
////静态的全局类
//
//@interface VCModel : LLCoreDataModels{
//}
//
//-(void)addEntry;
//-(void)queryEntry;
///**
// * 全局
// */
//+(VCModel *)sharedInstance;
//
//@end

//#import "VCModel.h"
//#import "Entry.h"
//
//
//static VCModel *g_VCModel = nil;
//
//@implementation VCModel
//
//
//#pragma mark -
//#pragma mark 继承实现
//-(NSString*)modelSqlite{
//    return @"model.sqlite";
//}
//
//#pragma mark -
//#pragma mark public
//+(VCModel *)sharedInstance{
//    @synchronized(self) {
//        if(!g_VCModel) {
//            g_VCModel = [[VCModel alloc] init];
//        }
//    }
//    return g_VCModel;
//}
//
//
///**
// * 增加操作
// */
//-(void)addEntry{
//    
//    //让CoreData在上下文中创建一个新对象(托管对象)
//    Entry *entry = (Entry *)[NSEntityDescription insertNewObjectForEntityForName:@"Entry"
//                                                          inManagedObjectContext:[self managedObjectContext]];
//    
//    [entry setTitle:@"title"];
//    [entry setBody:@"body"];
//    [entry setDate:[NSDate date]];
//    
//    NSError *error;
//    
//    //托管对象准备好后，调用托管对象上下文的save方法将数据写入数据库
//    BOOL isSaveSuccess = [[self managedObjectContext] save:&error];
//    
//    if (!isSaveSuccess) {
//        NSLog(@"Error: %@,%@",error,[error userInfo]);
//    }else {
//        NSLog(@"Save successful!");
//    }
//}
///**
// * 查询操作
// */
//-(void)queryEntry{
//    
//    //创建取回数据请求
//    NSFetchRequest *request = [[NSFetchRequest alloc] init];
//    //设置要检索哪种类型的实体对象
//    NSEntityDescription *entity = [NSEntityDescription entityForName:@"Entry"
//                                              inManagedObjectContext:[self managedObjectContext]];
//    
//    //设置请求实体
//    [request setEntity:entity];
//    //指定对结果的排序方式
//    NSSortDescriptor *sortDescriptor = [[NSSortDescriptor alloc] initWithKey:@"date"ascending:NO];
//    NSArray *sortDescriptions = [[NSArray alloc]initWithObjects:sortDescriptor, nil];
//    [request setSortDescriptors:sortDescriptions];
//    [sortDescriptions release];
//    [sortDescriptor release];
//    
//    NSError *error = nil;
//    //执行获取数据请求，返回数组
//    NSMutableArray *mutableFetchResult = [[[self managedObjectContext] executeFetchRequest:request error:&error] mutableCopy];
//    if (mutableFetchResult == nil) {
//        NSLog(@"Error: %@,%@",error,[error userInfo]);
//    }
//    //self.entries = mutableFetchResult;
//    
//    NSLog(@"The count of entry:%i",[mutableFetchResult count]);
//    
//    for (Entry *entry in mutableFetchResult) {
//        NSLog(@"Title:%@---Content:%@---Date:%@",entry.title,entry.body,entry.date);
//    }
//    
//    [mutableFetchResult release];
//    [request release];
//}
//
//
//@end





