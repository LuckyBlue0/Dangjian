#import "DOTableViewCell.h"
#import "DOTableItem.h"

@implementation DOTableViewCell
@synthesize object=_object;

-(UIView*)titleLabel{
    if (!_titleLabel) {
        _titleLabel = [[UILabel alloc] initWithFrame:CGRectMake(10, 10, 100, 20)];
        [self.contentView addSubview:_titleLabel];  
    }
    return _titleLabel;
}


- (id)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString*)identifier {
	if (self= [super initWithStyle:UITableViewCellStyleValue2 reuseIdentifier:identifier]) {
        [self titleLabel];
	}
	return self;
}

- (void)dealloc {  
    [_titleLabel release];  
    [super dealloc];
}

+ (CGFloat)tableView:(UITableView*)tableView rowHeightForObject:(id)object {
    return 60;
}

- (id)object {
    return nil;
}

- (void)setObject:(id)anObject {
    if (_object != anObject) {
        [_object release];
        _object = [anObject retain];
    }
    
    DOTableItem *item = anObject;
    [_titleLabel setText:[item.dataDic objectForKey:@"name"]];
}



#pragma mark -
#pragma mark view

- (void)layoutSubviews {
    [super layoutSubviews];
}  


#pragma mark -
#pragma mark UITableViewCell

- (void)prepareForReuse {
    self.object = nil;
    self.textLabel.text = nil;
    self.detailTextLabel.text = nil;
    [super prepareForReuse];
}


@end
