
#import "KCPickerImageBar.h"

@implementation KCPickerImageBar

@synthesize image;
@synthesize delegate;
@synthesize tag;
@synthesize popover;


-(void)dealloc{
    
    DO_RELEASE_SAFELY(popover);
    DO_RELEASE_SAFELY(image);
    
    delegate=nil;
    
    [super dealloc];
}

-(void)showInView:(UIView*)aView{
    [self retain];
    
    //弹出actionSheet
    UIActionSheet *aAS = [[[UIActionSheet alloc] initWithTitle:@"" delegate:self cancelButtonTitle:@"取消" destructiveButtonTitle:@"拍照" otherButtonTitles:@"本地上传", nil] autorelease];
    [aAS showInView:aView];
    
}


#pragma mark -
#pragma mark UIActionSheet

- (void)actionSheet:(UIActionSheet *)actionSheet clickedButtonAtIndex:(NSInteger)buttonIndex{
    
    //拍照
    if (buttonIndex==0){
        
        if (![UIImagePickerController isSourceTypeAvailable: UIImagePickerControllerSourceTypeCamera]) {
            //没有照相机
            if ([self.delegate respondsToSelector:@selector(didKCPickerImageBarFailedSeleted:)]) {
                [self.delegate performSelector:@selector(didKCPickerImageBarFailedSeleted:) withObject:self];
            }else{
                [self release];
            }
            return;
        }
        
        UIImagePickerController *imagePicker = [[[UIImagePickerController alloc] init] autorelease];
        imagePicker.delegate = self;
        imagePicker.sourceType = UIImagePickerControllerSourceTypeCamera;
        imagePicker.modalTransitionStyle = UIModalTransitionStyleCoverVertical;
        imagePicker.showsCameraControls = YES;
        [[UIApplication sharedApplication] setStatusBarHidden:YES];
        imagePicker.wantsFullScreenLayout = YES;
        [(UIViewController*)delegate presentModalViewController:imagePicker animated:YES];
    }
    //本地上传
    else if(buttonIndex==1) {
        
        UIImagePickerController *imagePicker = [[UIImagePickerController alloc] init];
        imagePicker.delegate = self;
        imagePicker.sourceType = UIImagePickerControllerSourceTypePhotoLibrary;
        imagePicker.modalTransitionStyle = UIModalTransitionStyleCoverVertical;
        
        
        
        //ipad
        if (([[UIDevice currentDevice] respondsToSelector:@selector(userInterfaceIdiom)]
             &&[[UIDevice currentDevice] userInterfaceIdiom] == UIUserInterfaceIdiomPad)) {
            
            UIPopoverController *aPOP = [[UIPopoverController alloc] initWithContentViewController:imagePicker];
            self.popover = aPOP;
            [self.popover presentPopoverFromRect:CGRectMake(0, 0, 300, 300) inView:((UIViewController*)delegate).view permittedArrowDirections:UIPopoverArrowDirectionAny animated:YES];
            [aPOP release];
        }else{
            imagePicker.wantsFullScreenLayout = YES;
            [(UIViewController*)delegate presentModalViewController:imagePicker animated:YES];
        }
        
        [imagePicker release];
        imagePicker=nil;
    }
}


#pragma mark -
#pragma mark UIImagePickerController

- (void)imagePickerController:(UIImagePickerController *)picker
        didFinishPickingImage:(UIImage *)aImage
				  editingInfo:(NSDictionary *)editingInfo{
    self.image = aImage;
    
    [[UIApplication sharedApplication] setStatusBarHidden:NO];
    
    //ipad
    if (([[UIDevice currentDevice] respondsToSelector:@selector(userInterfaceIdiom)]
         &&[[UIDevice currentDevice] userInterfaceIdiom] == UIUserInterfaceIdiomPad)) {
        
        
        if (picker.sourceType == UIImagePickerControllerSourceTypeCamera) {
            
            [picker dismissModalViewControllerAnimated:YES];
        }else{
            [self.popover dismissPopoverAnimated:YES];
        }
        
    }else{
        [picker dismissModalViewControllerAnimated:YES];
    }
    
    
    if ([self.delegate respondsToSelector:@selector(didKCPickerImageBarFinishedSeleted:)]) {
        [self.delegate performSelector:@selector(didKCPickerImageBarFinishedSeleted:) withObject:self];
    }else{
        [self release];
    }
	
}

//- (void)imagePickerController:(UIImagePickerController *)picker didFinishPickingMediaWithInfo:(NSDictionary *)info;




- (void)imagePickerControllerDidCancel:(UIImagePickerController *)picker{
    
    [[UIApplication sharedApplication] setStatusBarHidden:NO];
    
    //ipad
    if (([[UIDevice currentDevice] respondsToSelector:@selector(userInterfaceIdiom)]
         &&[[UIDevice currentDevice] userInterfaceIdiom] == UIUserInterfaceIdiomPad)) {
        
        
        if (picker.sourceType == UIImagePickerControllerSourceTypeCamera) {
            
            [picker dismissModalViewControllerAnimated:YES];
        }else{
            [self.popover dismissPopoverAnimated:YES];
        }
        
    }else{
        [picker dismissModalViewControllerAnimated:YES];
    }

    
    
    
    if ([self.delegate respondsToSelector:@selector(didKCPickerImageBarFinishedCanceled:)]) {
        [self.delegate performSelector:@selector(didKCPickerImageBarFinishedCanceled:) withObject:self];
    }else{
        [self release];
    }
}


@end
