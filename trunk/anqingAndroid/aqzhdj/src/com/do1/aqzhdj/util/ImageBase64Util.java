package com.do1.aqzhdj.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import com.do1.aqzhdj.widght.Base64;

/**
 * 讲图片转为Base64
 * auth:YanFangqin
 * data:2013-4-22
 * thzhd 天河智慧党
 */
public class ImageBase64Util {
	
	public String getBitmapStrBase64(File bitmap) throws IOException {
		InputStream input = new FileInputStream(bitmap);
		// 创建一个新的写入流，讲读取到的图像数据写入到文件中
		FileOutputStream fos = new FileOutputStream(bitmap);
		// 已写入流作为参数创建一个带有缓冲的写入流
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		int read;
		byte[] buffer = new byte[1024];
		while ((read = input.read(buffer)) != -1) {
			bos.write(buffer, 0, read);
		}
		bos.flush();
		bos.close();
		fos.flush();
		fos.close();
		input.close();
		return Base64.encode(buffer);
	}
	
	public String getBitmapStrBase64(Bitmap bitmap) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(CompressFormat.PNG, 100, baos);
		byte[] bytes = baos.toByteArray();
		
		if (bitmap != null && !bitmap.isRecycled()){
			bitmap.recycle();
			System.gc();
		}
		return Base64.encode(bytes);
	}
	
	public Bitmap drawableToBitmap(Drawable drawable) {		
		// 取 drawable 的长宽   
        int w = drawable.getIntrinsicWidth();   
        int h = drawable.getIntrinsicHeight();   
  
        // 取 drawable 的颜色格式   
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888   
                : Bitmap.Config.RGB_565;   
        // 建立对应 bitmap   
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        
        // 建立对应 bitmap 的画布   
        Canvas canvas = new Canvas(bitmap);   
        drawable.setBounds(0, 0, w, h);   
        // 把 drawable 内容画到画布中   
        drawable.draw(canvas);   
        return bitmap;   
    }
}
