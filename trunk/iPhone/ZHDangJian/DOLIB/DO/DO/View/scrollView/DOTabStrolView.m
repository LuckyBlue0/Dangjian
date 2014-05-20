#import "DOTabStrolView.h"

@implementation DOTabStrolView
@synthesize delegate = _delegate;
@synthesize selectedItem =_selectedItem;

-(id)initWithFrame:(CGRect)frame{
	if (self=[super initWithFrame:frame]) {
		UIImageView *abgImageView = [[UIImageView alloc] initWithFrame:self.bounds];
		abgImageView.image = [UIImage imageNamed:@"navCenter_floor_bg.png"];
		[self addSubview:abgImageView];
        [abgImageView release];
        abgImageView=nil;
		//**** 滑动 ****
		_sv = [[UIScrollView alloc] initWithFrame:self.bounds];
		[self addSubview:_sv];
	}
	return self;
}
-(void)dealloc{
    [_itemsArray release];
    _itemsArray=nil;
    [_sv release];
    _sv=nil;
	[super dealloc];
}


-(void)setItemsObjects:(NSArray*)aItems{
	if (nil==_itemsArray) {
		_itemsArray = [[NSMutableArray alloc] init];
	}
	for (int i=0; i<[aItems count]; i++) {
		[_itemsArray addObject:[aItems objectAtIndex:i]];
	}
	
	//**** add items ****
	int sum = [_itemsArray count];
	CGFloat item_x = 2.0f;
	CGFloat item_y = 5.0f;
	
	for (int i=0; i<sum; i++) {
		NSString *aImageStr1;
		NSString *aImageStr2;
		if (i==0) {
			aImageStr1 = @"navCenter_floor_left.png";
			aImageStr2 = @"navCenter_floor_left_on.png";
		}else if (i==sum-1) {
			aImageStr1 = @"navCenter_floor_right.png";
			aImageStr2 = @"navCenter_floor_right_on.png";
		}else {
			aImageStr1 = @"navCenter_floor_middle.png";
			aImageStr2 = @"navCenter_floor_middle_on.png";
		}
        
        UIButton *aBtn = [[UIButton alloc] initWithFrame:CGRectMake(item_x, item_y, 58, 32)];
        [aBtn setImage:[UIImage imageNamed:aImageStr1] forState:UIControlStateNormal];
        [aBtn setImage:[UIImage imageNamed:aImageStr2] forState:UIControlStateHighlighted];
        [aBtn setImage:[UIImage imageNamed:aImageStr2] forState:UIControlStateSelected];
        [aBtn setTitle:[_itemsArray objectAtIndex:i] forState:UIControlStateNormal];
        //[aBtn setFont:[UIFont systemFontOfSize:14]];
        [aBtn addTarget:self action:@selector(itemsAction:) forControlEvents:UIControlEventTouchUpInside];
		
		
		CGSize autoResize = [[_itemsArray objectAtIndex:i] sizeWithFont: [UIFont systemFontOfSize:14] 
													  constrainedToSize: CGSizeMake(320, 29) 
														  lineBreakMode: NSLineBreakByWordWrapping];
		if(autoResize.width>41 
		   && autoResize.width<120){
			[aBtn setFrame:CGRectMake(aBtn.frame.origin.x, aBtn.frame.origin.y, 
									  autoResize.width, aBtn.frame.size.height)];
		}
		
		aBtn.tag = i+1;
		[_sv addSubview:aBtn];
		item_x = item_x +aBtn.frame.size.width;
        [aBtn release];
        aBtn=nil;
		
		
		if (item_x > _sv.frame.size.width) {
			[_sv setContentSize:CGSizeMake(item_x+20, _sv.contentSize.height)];
			[_sv setFrame:CGRectMake(0, _sv.frame.origin.y, _sv.frame.size.width, _sv.frame.size.height)];
		}else {
			[_sv setFrame:CGRectMake((_sv.frame.size.width-item_x)/2, _sv.frame.origin.y, _sv.frame.size.width, _sv.frame.size.height)];
		}
	}
}


#pragma mark -
#pragma mark action
-(void)itemsAction:(id)sender{
	for (int i=0; i<[_itemsArray count]; i++) {
		UIButton *aBtn = (UIButton*)[_sv viewWithTag:i+1];
		if (nil!=aBtn) {
			[aBtn setSelected:NO];
		}
	}
	
	UIButton *aBtn = (UIButton*)sender;
	_selectedItem = aBtn.tag-1;
	if (nil!=aBtn) {
		[aBtn setSelected:YES];
	}
	if ([_delegate respondsToSelector:@selector(tabStrolSelected:)]) {
		[_delegate performSelector:@selector(tabStrolSelected:) withObject:self];
	}
	
}



@end
