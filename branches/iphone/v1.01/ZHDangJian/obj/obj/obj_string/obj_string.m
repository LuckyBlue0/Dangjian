//
//  obj_add_nsstring.m
//  obj
//
//  Created by daoyi on 13-4-29.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//

#import "obj_string.h" 

@implementation NSString (obj)
-(BOOL)is_empty
{
    if (self.length < 1)
        return TRUE;
    
    NSString *t_self = [self valid_str];
    if (self.length < 1)
        return TRUE;
    if([t_self isEqual:[NSNull null]])
        return TRUE; 
    if([t_self isEqualToString:@"nil"])
        return TRUE;
    if([t_self isEqualToString:@"null"])
        return TRUE;
    
    return FALSE;
}
-(NSString*)valid_str
{
    return [self stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
}
-(NSString*)utf8_str
{
    return [self stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
}
-(NSString*)without:(NSString*)str
{
    return [self stringByReplacingOccurrencesOfString:str withString:@""];
}
-(CGFloat)width_for_font:(UIFont*)font
{
    return [self sizeWithFont:font
            constrainedToSize:CGSizeMake(CGFLOAT_MAX,[font lineHeight])
                lineBreakMode:NSLineBreakByWordWrapping].width;
}
-(CGFloat)height_for_font:(UIFont*)font and_width:(CGFloat)width
{
    return [self sizeWithFont:font
            constrainedToSize:CGSizeMake(width,CGFLOAT_MAX)
                lineBreakMode:NSLineBreakByWordWrapping].height;
}
- (BOOL)is_mobile
{
    /**
     * 手机号码
     * 移动：134[0-8],135,136,137,138,139,150,151,157,158,159,182,187,188
     * 联通：130,131,132,152,155,156,185,186
     * 电信：133,1349,153,180,189
     */
    NSString * MOBILE = @"^1(3[0-9]|5[0-35-9]|8[025-9])\\d{8}$";
    /**
     10         * 中国移动：China Mobile
     11         * 134[0-8],135,136,137,138,139,150,151,157,158,159,182,187,188
     12         */
    NSString * CM = @"^1(34[0-8]|(3[5-9]|5[017-9]|8[278])\\d)\\d{7}$";
    /**
     15         * 中国联通：China Unicom
     16         * 130,131,132,152,155,156,185,186
     17         */
    NSString * CU = @"^1(3[0-2]|5[256]|8[56])\\d{8}$";
    /**
     20         * 中国电信：China Telecom
     21         * 133,1349,153,180,189
     22         */
    NSString * CT = @"^1((33|53|8[09])[0-9]|349)\\d{7}$";
    /**
     25         * 大陆地区固话及小灵通
     26         * 区号：010,020,021,022,023,024,025,027,028,029
     27         * 号码：七位或八位
     28         */
    NSString * PHS = @"^0(10|2[0-5789]|\\d{3})\\d{7,8}$";
    
    
    NSPredicate *regextestmobile = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", MOBILE];
    NSPredicate *regextestcm = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", CM];
    NSPredicate *regextestcu = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", CU];
    NSPredicate *regextestct = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", CT];
    NSPredicate *regextestphs = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", PHS];
    
    if (([regextestmobile evaluateWithObject:self] == YES)
        || ([regextestcm evaluateWithObject:self] == YES)
        || ([regextestct evaluateWithObject:self] == YES)
        || ([regextestcu evaluateWithObject:self] == YES)
        || ([regextestphs evaluateWithObject:self] == YES))
        return YES;
    else
        return NO;
}
-(BOOL)is_email
{
    // @"[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
    NSString *str_reg = @"^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";
    NSPredicate *reg = [NSPredicate predicateWithFormat:@"SELF MATCHES %@",str_reg];
    return [reg evaluateWithObject:self];
}

-(NSDate*)date_with_format:(NSString*)format
{
    NSDateFormatter *formatter = AIAR(NSDateFormatter);
    formatter.dateFormat = format;
    
    NSDate* t_date =[formatter dateFromString:self];
    
    DBIF(!t_date, @"can convert to nsdate!");
        
    return t_date;
}
@end