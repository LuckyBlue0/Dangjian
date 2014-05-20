#import "DOCoreDataModels.h"

@implementation DOCoreDataModels
@synthesize managedObjectModel=_managedObjectModel;
@synthesize managedObjectContext=_managedObjectContext;
@synthesize persistentStoreCoordinator=_persistentStoreCoordinator;


#pragma mark -
#pragma mark 继承实现
-(NSString*)modelSqlite{
    return @"";
}



#pragma mark -
#pragma mark public
/**
 * 程序挂起或者退出调用
 */
-(void)saveApplicationWillTerminate{
    NSError *error;
    if (_managedObjectContext != nil) {
        //hasChanges方法是检查是否有未保存的上下文更改，如果有，则执行save方法保存上下文
        if ([_managedObjectContext hasChanges] && ![_managedObjectContext save:&error]) {
            //NSLog(@"Error: %@,%@",error,[error userInfo]);
            abort();
        }
    }

}




#pragma mark -
#pragma mark private
-(NSManagedObjectModel *)managedObjectModel{
    if (!_managedObjectModel) {
        _managedObjectModel = [[NSManagedObjectModel mergedModelFromBundles:nil] retain];
    }
    return _managedObjectModel;
}
-(NSPersistentStoreCoordinator *)persistentStoreCoordinator{
    if (!_persistentStoreCoordinator) {
        //得到数据库的路径
        NSString *docs = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject];
        //CoreData是建立在SQLite之上的，数据库名称需与Xcdatamodel文件同名
        NSURL *storeUrl = [NSURL fileURLWithPath:[docs stringByAppendingPathComponent:[self modelSqlite]]];
        NSError *error = nil;
        _persistentStoreCoordinator = [[NSPersistentStoreCoordinator alloc]initWithManagedObjectModel:[self managedObjectModel]];
        
        if (![_persistentStoreCoordinator addPersistentStoreWithType:NSSQLiteStoreType configuration:nil URL:storeUrl options:nil error:&error]){
            //NSLog(@"Error: %@,%@",error,[error userInfo]);
        }
    }
    return _persistentStoreCoordinator;
}
-(NSManagedObjectContext *)managedObjectContext{
    if (!_managedObjectContext) {
        NSPersistentStoreCoordinator *coordinator =[self persistentStoreCoordinator];
        if (coordinator != nil) {
            _managedObjectContext = [[NSManagedObjectContext alloc]init];
            [_managedObjectContext setPersistentStoreCoordinator:coordinator];
        }
    }
    return _managedObjectContext;
}


@end


