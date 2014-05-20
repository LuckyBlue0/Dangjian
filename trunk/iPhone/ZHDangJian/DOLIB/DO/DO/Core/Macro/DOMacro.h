/*
 *Created by liming on 12-3-13.
 */


//**** 调试输出 ****
#if DEBUG
#define DOLOG(_format,args...)  printf("%s\n",[[NSString stringWithFormat:(_format),##args] UTF8String])
#define LOG(xx, ...)  DOLOG(@"\n##%s(%d)##" xx, __PRETTY_FUNCTION__, __LINE__, ##__VA_ARGS__)
#define DOLOGDATA(__DATA) LOG(@"%@",[[[NSString alloc]initWithData:__DATA encoding:NSUTF8StringEncoding]autorelease])
#else
#define DOLOG(_format,args...)
#define LOG(xx, ...)
#define DOLOGDATA(__DATA)
#endif


//**** 安全释放 ****
#define DO_RELEASE_SAFELY(__POINTER) [__POINTER release];__POINTER = nil;


#define USER_DEFAULT [NSUserDefaults standardUserDefaults]



