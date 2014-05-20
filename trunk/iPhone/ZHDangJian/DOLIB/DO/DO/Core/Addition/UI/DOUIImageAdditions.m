#import "DOUIImageAdditions.h"
#import <QuartzCore/QuartzCore.h>


CGFloat LLDegreesToRadians(CGFloat degrees) {return degrees * M_PI / 180;};
CGFloat LLRadiansToDegrees(CGFloat radians) {return radians * 180/M_PI;};


@implementation UIImage (DOUIImageAdditions)


//**** 保存图片 ****
-(BOOL)saveWithPath:(NSString*)aPath{
	return [UIImagePNGRepresentation(self)writeToFile:aPath
										   atomically:YES];
}

//**** Init图片 ****
+(UIImage*)imageWithPath:(NSString*)aPath{
	NSData *imageData = [NSData dataWithContentsOfFile:aPath];
	if (nil==imageData){
		return nil;
	}
	return [[[UIImage alloc] initWithData:imageData] autorelease];
}
+(UIImage*)imageWithBundle:(NSString *)aImageName{
	//**** 从资源文件加载图片,减少内存使用 ****
	return [UIImage imageWithPath:
			[NSString stringWithFormat:@"%@/%@",[[NSBundle mainBundle] bundlePath],aImageName]];
}



//**** 保存当前视图到相册 ****
+(void)saveCurrentView:(UIView*)aView{
	UIGraphicsBeginImageContext(aView.bounds.size);
	
	[aView.layer renderInContext:UIGraphicsGetCurrentContext()];
	
	UIImage *viewImage = UIGraphicsGetImageFromCurrentImageContext();
	UIGraphicsEndImageContext();
	UIImageWriteToSavedPhotosAlbum(viewImage, nil, nil, nil);
}




/**
 * 缩放图片
 */
+ (UIImage *) imageNamed: (NSString *)imageStr size: (CGSize) size{
    UIImage *aImage = [UIImage imageNamed:imageStr];
    UIGraphicsBeginImageContext(size);
    [aImage drawInRect:CGRectMake(0, 0, size.width/2, size.height/2)];
    UIImage *newImage = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    return newImage;
}
+ (UIImage *)scaleImage:(UIImage *)image maxWidth:(float)maxWidth maxHeight:(float)maxHeight{
    
    CGImageRef imgRef = image.CGImage;
    CGFloat width = CGImageGetWidth(imgRef);
    CGFloat height = CGImageGetHeight(imgRef);
    
    if (width <= maxWidth && height <= maxHeight){
        return image;
    }
    
    CGAffineTransform transform = CGAffineTransformIdentity;
    CGRect bounds = CGRectMake(0, 0, width, height);
    if (width > maxWidth || height > maxHeight){
        CGFloat ratio = width/height;
        if (ratio > 1){
            bounds.size.width = maxWidth;
            bounds.size.height = bounds.size.width / ratio;
        }
        else{
            bounds.size.height = maxHeight;
            bounds.size.width = bounds.size.height * ratio;
        }
    }
    
    CGFloat scaleRatio = bounds.size.width / width;
    UIGraphicsBeginImageContext(bounds.size);
    CGContextRef context = UIGraphicsGetCurrentContext();
    CGContextScaleCTM(context, scaleRatio, -scaleRatio);
    CGContextTranslateCTM(context, 0, -height);
    CGContextConcatCTM(context, transform);
    CGContextDrawImage(UIGraphicsGetCurrentContext(), CGRectMake(0, 0, width, height), imgRef);
    
    UIImage *imageCopy = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    return imageCopy;
}



/**
 * 旋转图片
 */
- (UIImage *)imageRotatedByDegrees:(CGFloat)degrees{  
    // calculate the size of the rotated view's containing box for our drawing space
    UIView *rotatedViewBox = [[UIView alloc] initWithFrame:CGRectMake(0,0,self.size.width, self.size.height)];
    CGAffineTransform t = CGAffineTransformMakeRotation(LLDegreesToRadians(degrees));
    rotatedViewBox.transform = t;
    CGSize rotatedSize = rotatedViewBox.frame.size;
    [rotatedViewBox release];
    
    // Create the bitmap context
    UIGraphicsBeginImageContext(rotatedSize);
    CGContextRef bitmap = UIGraphicsGetCurrentContext();
    
    // Move the origin to the middle of the image so we will rotate and scale around the center.
    CGContextTranslateCTM(bitmap, rotatedSize.width/2, rotatedSize.height/2);
    
    //   // Rotate the image context
    CGContextRotateCTM(bitmap, LLDegreesToRadians(degrees));
    
    // Now, draw the rotated/scaled image into the context
    CGContextScaleCTM(bitmap, 1.0, -1.0);
    CGContextDrawImage(bitmap, CGRectMake(-self.size.width / 2, -self.size.height / 2, self.size.width, self.size.height), [self CGImage]);
    
    UIImage *newImage = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    return newImage;
}
- (UIImage *)imageRotatedByRadians:(CGFloat)radians{
    return [self imageRotatedByDegrees:LLRadiansToDegrees(radians)];
}

+(UIImage *)fixOrientation:(UIImage *)aImage{
	if (aImage == nil) {
		return nil;
	}
	
	CGImageRef imgRef = aImage.CGImage;
	CGFloat width = CGImageGetWidth(imgRef);
	CGFloat height = CGImageGetHeight(imgRef);
	
	CGAffineTransform transform = CGAffineTransformIdentity;
	CGRect bounds = CGRectMake(0, 0, width, height);
	CGFloat scaleRatio = 1;
	CGFloat boundHeight;
	
	UIImageOrientation orient = aImage.imageOrientation;
	switch(orient)
	{
		case UIImageOrientationUp: //EXIF = 1
		{
			transform = CGAffineTransformIdentity;
			break;
		}
		case UIImageOrientationUpMirrored: //EXIF = 2
		{
			transform = CGAffineTransformMakeTranslation(width, 0.0);
			transform = CGAffineTransformScale(transform, -1.0, 1.0);
			break;
		}
		case UIImageOrientationDown: //EXIF = 3
		{
			transform = CGAffineTransformMakeTranslation(width, height);
			transform = CGAffineTransformRotate(transform, M_PI);
			break;
		}
		case UIImageOrientationDownMirrored: //EXIF = 4
		{
			transform = CGAffineTransformMakeTranslation(0.0, height);
			transform = CGAffineTransformScale(transform, 1.0, -1.0);
			break;
		}
		case UIImageOrientationLeftMirrored: //EXIF = 5
		{
			boundHeight = bounds.size.height;
			bounds.size.height = bounds.size.width;
			bounds.size.width = boundHeight;
			transform = CGAffineTransformMakeTranslation(height, width);
			transform = CGAffineTransformScale(transform, -1.0, 1.0);
			transform = CGAffineTransformRotate(transform, 3.0 * M_PI / 2.0);
			break;
		}
		case UIImageOrientationLeft: //EXIF = 6
		{
			boundHeight = bounds.size.height;
			bounds.size.height = bounds.size.width;
			bounds.size.width = boundHeight;
			transform = CGAffineTransformMakeTranslation(0.0, width);
			transform = CGAffineTransformRotate(transform, 3.0 * M_PI / 2.0);
			break;
		}
		case UIImageOrientationRightMirrored: //EXIF = 7
		{
			boundHeight = bounds.size.height;
			bounds.size.height = bounds.size.width;
			bounds.size.width = boundHeight;
			transform = CGAffineTransformMakeScale(-1.0, 1.0);
			transform = CGAffineTransformRotate(transform, M_PI / 2.0);
			break;
		}
		case UIImageOrientationRight: //EXIF = 8
		{
			boundHeight = bounds.size.height;
			bounds.size.height = bounds.size.width;
			bounds.size.width = boundHeight;
			transform = CGAffineTransformMakeTranslation(height, 0.0);
			transform = CGAffineTransformRotate(transform, M_PI / 2.0);
			break;
		}
		default:
		{
			[NSException raise:NSInternalInconsistencyException format:@"Invalid image orientation"];
			break;
		}
	}
	
	UIGraphicsBeginImageContext(bounds.size);
	CGContextRef context = UIGraphicsGetCurrentContext();
	if (orient == UIImageOrientationRight || orient == UIImageOrientationLeft) {
		CGContextScaleCTM(context, -scaleRatio, scaleRatio);
		CGContextTranslateCTM(context, -height, 0);
	}
	else {
		CGContextScaleCTM(context, scaleRatio, -scaleRatio);
		CGContextTranslateCTM(context, 0, -height);
	}
	
	CGContextConcatCTM(context, transform);
	CGContextDrawImage(UIGraphicsGetCurrentContext(), CGRectMake(0, 0, width, height), imgRef);
	UIImage *imageCopy = UIGraphicsGetImageFromCurrentImageContext();
	UIGraphicsEndImageContext();
	
	return imageCopy;
}

@end
