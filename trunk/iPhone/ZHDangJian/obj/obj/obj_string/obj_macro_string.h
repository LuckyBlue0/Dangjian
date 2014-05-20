//
//  obj_macro_nsstring.h
//  obj
//
//  Created by daoyi on 13-4-29.
//  Copyright (c) 2013年 daoyi. All rights reserved.
//

#ifndef obj_macro_string_h
#define obj_macro_string_h

#import "obj.h"

#define IS_EMPTY_NSSTR(p) ((p)?([(p) is_empty]):TRUE)

#define NSSTR_COPY(d,s) ((d) = [[NSString alloc] initWithString:(s)])

//implement in macro for property restrict(retain copy)
#define NSSTR_REPLACE(p,t,d) ((p)=[(p) stringByReplacingOccurrencesOfString:(t) withString:(d)])




#define NSSTR_LIMIT(s,r,d,l) \
(([(s) stringByReplacingCharactersInRange:(r) withString:(d)].length<(l))?TRUE:FALSE)

//for uitextview and uitextfield char nums limit
#define NSSTR_LIMIT_TV(x) NSSTR_LIMIT(textView.text, range, text, (x))
#define NSSTR_LIMIT_TFL(x) NSSTR_LIMIT(textField.text, range, string, (x))

#endif
