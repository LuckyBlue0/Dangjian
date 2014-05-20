package com.do1.aqzhdj.widght.pager;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;
import cn.com.do1.component.util.Log;

import com.do1.aqzhdj.util.ImageViewTool;

/**
 * 提供图片加载、RatingBar的绑定
 * @author Administrator
 *
 */
public class SimpleViewBinder implements ViewBinder{
	private Bitmap mPresetBitmap = null;//预览或加载失败时显示的图片
	
	
	public SimpleViewBinder(Bitmap presetBitmap) {
		super();
		this.mPresetBitmap = presetBitmap;
	}

	@Override
	public boolean setViewValue(View view, Object data,
			String textRepresentation) {
		if (view instanceof RatingBar){//RatingBar
			
			if (data instanceof Integer){
				setRatingBar((RatingBar)view,(Integer) data);
			}else if (data instanceof Float){
				setRatingBar((RatingBar)view,(Float) data);
			}
			return true;
		}else if(view instanceof ImageView){//加图片
			if (textRepresentation.startsWith("http://")){
				loadImage((ImageView) view,textRepresentation);
			}
			return true;
		}else if (view instanceof TextView){
			if (textRepresentation.matches("^\\d{11}$")){//是手机号，中间几个字要加*
				setMobleTextView((TextView) view,textRepresentation);
				return true;
			}
			
		}
		return false;
	}
	
	private void setMobleTextView(TextView textView,String mobile){
		String _moblie = mobile.substring(0, 3)+"****"+ mobile.substring(7, mobile.length());
		textView.setText(_moblie);
	}
	
	private void setRatingBar(RatingBar ratingBar,float rate){
		ratingBar.setRating(rate);
	}

	private void loadImage(ImageView imageView,String url){
		Log.d("加载图片:"+url);
//		new AQuery(imageView).image(url,true,true,0,0,mPresetBitmap,0);
		ImageViewTool.getAsyncImageBg(url, imageView, 0);
	}

}
