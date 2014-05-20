package com.do1.zhdj.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 图片压缩处理
 * auth:YanFangqin
 * data:2013-4-22
 * thzhd
 */
public class ImageCompress {
	
	/**
	 * 质量压缩方法
	 * @param image
	 * @return
	 */
	public static Bitmap compressImage(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		int options = 100;
		// 循环判断如果压缩后图片是否大于100kb,大于继续压缩
		while (baos.toByteArray().length / 1024 > 100) { 
			// 重置baos即清空baos
			baos.reset();
			// 这里压缩options%，把压缩后的数据存放到baos中
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);
			// 每次都减少10
			options -= 10;
		}
		// 把压缩后的数据baos存放到ByteArrayInputStream中
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		// 把ByteArrayInputStream数据生成图片
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
		if(image != null && !image.isRecycled()){
			image.recycle();
			System.gc();
		}
		return bitmap;
	}
	
	/**
	 * 图片按比例大小压缩方法（根据路径获取图片并压缩)
	 * @param srcPath
	 * @return
	 */
	public static  Bitmap getimage(String srcPath) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		//开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath,newOpts);//此时返回bm为空
		
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		float hh = Constants.SCR_HEIGHT;
		float ww = Constants.SCR_WIDTH;
		//缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;//be=1表示不缩放
		if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;//设置缩放比例
		//重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
	}
	
	/**
	 * 根据路径获取图片bitmap
	 */
	public static Bitmap getBitmapByPath(String path){
		BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;//图片宽高都为原来的二分之一，即图片为原来的四分之一
        Bitmap b = BitmapFactory.decodeFile(path, options);
        return compressImage(b);
	}
	
	/**
	 * 根据路径获取图片bitmap
	 */
	public static Bitmap getBitmapByPath2(String path){
		BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;//图片宽高都为原来的二分之一，即图片为原来的四分之一
        Bitmap b = BitmapFactory.decodeFile(path, options);
        return compressImage(b);
	}
	
	/**
	 * 图片按比例大小压缩方法（根据Bitmap图片压缩)
	 * @param image
	 * @return
	 */
	public static  Bitmap comp(Bitmap image) {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();		
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		if( baos.toByteArray().length / 1024 > 256) {//判断如果图片大于256K,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出	
			baos.reset();//重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		//开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		//按屏幕高宽度
		float hh = Constants.SCR_HEIGHT;
		float ww = Constants.SCR_WIDTH;
		//缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;//be=1表示不缩放
		if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;//设置缩放比例
//		newOpts.inSampleSize = 2;
		//重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		isBm = new ByteArrayInputStream(baos.toByteArray());
		bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
	}
}
