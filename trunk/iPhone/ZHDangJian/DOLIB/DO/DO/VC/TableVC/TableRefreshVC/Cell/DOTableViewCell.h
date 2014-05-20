/**
 * base cell
 */


#import <UIKit/UIKit.h>

@interface DOTableViewCell : UITableViewCell{
    UILabel *_titleLabel;
    
    id _object;
}
@property (nonatomic, retain) id object;
+ (CGFloat)tableView:(UITableView*)tableView rowHeightForObject:(id)object;
@end
