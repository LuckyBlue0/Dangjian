//
//  KCChineseToPinyin.h
//  KC
//  汉字转拼音
//  Created by kllmctrl on 13-3-29.
//  Copyright (c) 2013年 kllmctrl. All rights reserved.
//

#import <Foundation/Foundation.h>

char pinyinFirstLetter(unsigned short hanzi);
BOOL isCapitalLetter(char letter);
BOOL isLowercaseLetter(char letter);
BOOL isLetter(char letter);
void capitalLetter(char *letter);
NSString* FindLetter(int nCode);

@interface KCChineseToPinyin : NSObject {
    
}

//汉字转拼音字母
+ (NSString *) pinyinFromChiniseString:(NSString *)string;
+ (char) sortSectionTitle:(NSString *)string;
@end

