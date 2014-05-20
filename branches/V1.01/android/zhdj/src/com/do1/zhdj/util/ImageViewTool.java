package com.do1.zhdj.util;



import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.BitmapAjaxCallback;
import com.do1.zhdj.R;

public class ImageViewTool {
	public static AQuery aq;
	
	public static void getAsyncImageSet(Object obj,String imgPath,int imgId){
		ImageView imgView = null;
		if(obj instanceof Activity){
			imgView =(ImageView) ((Activity)obj).findViewById(imgId);
		}else if(obj instanceof View){
			imgView =(ImageView) ((View)obj).findViewById(imgId);
		}
		
		getAsyncImageSet(imgPath,imgView,R.drawable.default_m_104);
	}
	
	public static void getAsyncImageSet(String imgPath,final ImageView imgView,int defaultImg){
		try {
			
			if(null==aq){
				aq = new AQuery(imgView.getContext());
			}
			//判断图片
			Bitmap bm = aq.getCachedImage(defaultImg);
			if (!RegUtil.isPictureUrl(imgPath)) {
				aq.id(imgView).image(bm);
				return ;
			}
			
			if(imgView.getContext()!=aq.getContext())
				aq = new AQuery(imgView.getContext());
			
			//绑定图片
//			aq.id(imgView).image(imgPath, true, true,bm.getWidth(), R.drawable.list_item_bg,bm,AQuery.FADE_IN,AQuery.RATIO_PRESERVE);
			aq.id(imgView).image(imgPath, true, true,0, defaultImg,bm,0,31.0f / 40.0f);
		} catch (Exception e) {
		}
	}
	public static void getAsyncImageNormal(String imgPath,final ImageView imgView,int defaultImg){
		try {
			
			if(null==aq){
				aq = new AQuery(imgView.getContext());
			}
			Bitmap bm = aq.getCachedImage(defaultImg);
			if (!RegUtil.isPictureUrl(imgPath)) {
				aq.id(imgView).image(bm);
				return ;
			}
			
			if(imgView.getContext()!=aq.getContext())
				aq = new AQuery(imgView.getContext());
			
			//绑定图片
			aq.id(imgView).image(imgPath, true, true,0, defaultImg,bm,0,AQuery.RATIO_PRESERVE);
		} catch (Exception e) {
		}
	}
	//绑定背景图片
	public static void getAsyncImageBg(String imgPath,final ImageView imgView,final int defaultImg)
	{
		//判断图片
//		if (!RegUtil.isPictureUrl(imgPath)) {
//			return ;
//		}
		
		if(null==aq)
			aq = new AQuery(imgView.getContext());
		
		if(aq.getCachedImage(imgPath) != null){
			imgView.setImageDrawable(new BitmapDrawable(aq.getCachedImage(imgPath)));
			return;
		}
		
		if(imgView.getContext()!=aq.getContext())
			aq = new AQuery(imgView.getContext());
		System.out.println("imgurl: " + Constants.SERVER_IMG+imgPath);
		aq.id(imgView).image(Constants.SERVER_IMG+imgPath, true, true, 0, defaultImg, new BitmapAjaxCallback(){     
			@Override
			protected void callback(String url, ImageView iv, Bitmap bm,AjaxStatus status) {
				if(null != bm && bm.getHeight() > 10 && bm.getWidth() > 0){
					iv.setVisibility(View.VISIBLE);
//					bm = zoomBitmap(bm,700,420);
					iv.setImageDrawable(new BitmapDrawable(bm));
				}else{
					if(defaultImg > 0){//当默认图片大于0，则设置默认图片，等于0则不做任何设置
						iv.setVisibility(View.VISIBLE);
						iv.setImageResource(defaultImg);
					}else if(defaultImg == -1){//当默认图片为-1，表示需要隐藏该图片位置
						iv.setVisibility(View.GONE);
					}
				}
			}
		});
	}
	
	public static  Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) width / w);
		float scaleHeight = ((float) height / h);
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
		return newbmp;
	}
	
	//绑定背景图片
		public static void getAsyncImageBgReal(String imgPath,final View imgView,final int defaultImg)
		{
			//判断图片
			/*if (Constants.isImgCache()||!RegUtil.isPictureUrl(imgPath)) {
				return ;
			}*/
			
			if(null==aq)
				aq = new AQuery(imgView.getContext());
			
			if(imgView.getContext()!= aq.getContext())
				aq = new AQuery(imgView.getContext());
			
			aq.id(imgView).image(imgPath, true, true, 0, defaultImg, new BitmapAjaxCallback(){     
				@Override
				protected void callback(String url, ImageView iv, Bitmap bm,
						AjaxStatus status) {
					if(null!=bm&&bm.getHeight()>10&&bm.getWidth()>0)
						iv.setBackgroundDrawable(new BitmapDrawable(bm));
					else
						iv.setBackgroundResource(defaultImg);
				}
			});
		}
}
