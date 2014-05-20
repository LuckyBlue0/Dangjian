/*
 
 多颜色字体label

 
 */

#import <Foundation/Foundation.h>

@interface MutilColorLable : UILabel{
    
    NSMutableDictionary *_attributedDic;
    NSMutableAttributedString *_attributedText;
}

@property (nonatomic, copy) NSMutableDictionary *attributedDic;

@end
