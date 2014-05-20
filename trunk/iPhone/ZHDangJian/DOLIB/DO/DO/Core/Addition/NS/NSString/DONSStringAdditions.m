#import "DONSStringAdditions.h"

//**** >>>>html标签
#import "DOMarkupStripper.h"
//***** <<<<<

//**** >>>>MD5编码
#import <CommonCrypto/CommonDigest.h>
//***** <<<<<


#import "DONSDataAdditions.h"

@implementation NSString (DONSStringAdditions)



//**** 可以显示的object-->string ****
+(NSString*)stringFromObject:(id)object{
	if ([object isKindOfClass:[NSString class]]) {
		if (![object isEmpty]) {
			return object;
		}
	}
	return @"";
}

//**** 可以打印的字符 ****
-(id)showString{
	if ([self isEmpty]) {
		return @"";
	}
	return self;
}
//**** 判断字符串是否为空 ****
- (BOOL)isEmpty {
	if ((NSNull *)self == [NSNull null]){
        return YES;
    }
    if (self == nil) {
        return YES;
    } 
	else if ([self length] == 0) {
        return YES;
    }
    else if (self == NULL)
        return YES;
	else {
        self = [self stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]];
        if ([self length] == 0) {
            return YES;
        }
    }
    return NO;
}

//UTF8编码
-(id)toUTF8String{
	return [self stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
}

//**** 替换字符 ****
-(NSString*)replace:(NSString*)aReplace 
				 to:(NSString*)aTo{
	NSMutableString *aMutableString = [NSMutableString stringWithString:self];
	[aMutableString replaceOccurrencesOfString:aReplace 
									withString:aTo 
									   options:NSLiteralSearch 
										 range:NSMakeRange(0, [aMutableString length])];
	return aMutableString;
}


//**** 去除html标签 ****
- (NSString*)removingHTMLTags {
	DOMarkupStripper* stripper = [[[DOMarkupStripper alloc] init] autorelease];
	return [stripper parse:self];
}


-(NSString *) MD52
{
    // Create byte array of unsigned chars
    unsigned char md5Buffer[CC_MD5_DIGEST_LENGTH];
    
    // Create 16 byte MD5 hash value, store in buffer
    CC_MD5(self, self.length, md5Buffer);
    
    // Convert MD5 value in the buffer to NSString of hex values
    NSMutableString *output = [NSMutableString stringWithCapacity:CC_MD5_DIGEST_LENGTH * 2];
    for(int i = 0; i < CC_MD5_DIGEST_LENGTH; i++)
        [output appendFormat:@"%02x",md5Buffer[i]];
    
    return output;
}


//**** MD5编码 ****
-(NSString*)MD5{
	if ([self isEmpty]) {
		return nil;
	}
    const char *value = [self UTF8String];
    unsigned char outputBuffer[CC_MD5_DIGEST_LENGTH];
    CC_MD5(value, strlen(value), outputBuffer);
    
    NSMutableString *outputString = [[NSMutableString alloc] initWithCapacity:CC_MD5_DIGEST_LENGTH * 2];
    for(NSInteger count = 0; count < CC_MD5_DIGEST_LENGTH; count++){
        [outputString appendFormat:@"%02x",outputBuffer[count]];
    }
	
    return [outputString autorelease];
}

- (NSString*)md5Hash:(NSStringEncoding)enc{
    return [[self dataUsingEncoding:enc] md5Hash];
}

- (id)urlEncoded {
    CFStringRef cfUrlEncodedString = CFURLCreateStringByAddingPercentEscapes(NULL,
                                                                             (CFStringRef)self,NULL,
                                                                             (CFStringRef)@"!#$%&'()*+,/:;=?@[]",
                                                                             kCFStringEncodingUTF8);
    
    NSString *urlEncoded = [NSString stringWithString:(NSString *)cfUrlEncodedString];
    CFRelease(cfUrlEncodedString);
    return urlEncoded;
}

//过来非法json字符
-(NSString*)filterUnKnowJson{
	if (nil==self 
		|| [self isEqualToString:@""] 
		|| [self length]==0) {
		return self;
	}
	NSString *aString = [self stringByReplacingOccurrencesOfString:@"\\" withString:@"/"];
	NSCharacterSet *controlChars = [NSCharacterSet controlCharacterSet]; 
	NSRange range = [aString rangeOfCharacterFromSet:controlChars]; 
	if (range.location != NSNotFound) { 
		NSMutableString *mutable = [NSMutableString stringWithString:aString]; 
		while (range.location != NSNotFound) { 
			[mutable deleteCharactersInRange:range]; 
			range = [mutable rangeOfCharacterFromSet:controlChars]; 
		}
		return mutable; 
	} 
	return aString;
}

- (BOOL)validateEmail{
	NSString *emailRegex = @"[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
	NSPredicate *emailTest = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", emailRegex]; 
	return [emailTest evaluateWithObject:self];
}
-(BOOL)validetePhoneNumber:(BOOL)isMobile{
    NSString *PhoneNumberRegex = @"";
    if (isMobile) {
        //**** 手机 ****
        PhoneNumberRegex = @"^(1(([35][0-9])|(47)|[8][01236789]))\\d{8}$";
    }else{
        //**** 固话 ****
        PhoneNumberRegex = @"^0\\d{2,3}(\\-)?\\d{7,8}$";
    }
	NSPredicate *PhoneNumberTest = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", PhoneNumberRegex]; 
	return [PhoneNumberTest evaluateWithObject:self];
}

//身份证
-(BOOL)valideteManCardID{
    NSString *manCardIDRegex = @"d{15}|d{18}";
	NSPredicate *manCardIDTest = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", manCardIDRegex];
	return [manCardIDTest evaluateWithObject:self];
}

-(int)lengthCount{
    int strlength = 0;
    char* p = (char*)[self cStringUsingEncoding:NSUnicodeStringEncoding];
    for (int i=0 ; i<[self lengthOfBytesUsingEncoding:NSUnicodeStringEncoding] ;i++) {
        if (*p) {
            p++;
            strlength++;
        }
        else {
            p++;
        }
    }
    return strlength;
}


/*
 -  (int)convertToInt:(NSString*)strtemp {
 
 int strlength = 0;
 char* p = (char*)[strtemp cStringUsingEncoding:NSUnicodeStringEncoding];
 for (int i=0 ; i<[strtemp lengthOfBytesUsingEncoding:NSUnicodeStringEncoding] ;i++) {
 if (*p) {
 p++;
 strlength++;
 }
 else {
 p++;
 }
 }
 return (strength+1)/2;
 
 }
 */


@end





////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////

@implementation NSObject (DONSStringAdditions_NSObject)


-(NSString*)objToString{
    
    if ([self isKindOfClass:[NSNull class]]) {
        return @"";
    }
    
    else if ([self isKindOfClass:[NSString class]]) {
        return (NSString*)self;
    }
    else if ([self isKindOfClass:[NSNumber class]]) {
        return [(NSNumber*)self stringValue];
    }
    else{
        return @"";
    }
    
}

@end



