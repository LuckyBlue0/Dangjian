/**
 * 
 */
package cn.com.do1.component.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import cn.com.do1.common.util.DateUtil;
import cn.com.do1.dqdp.core.ConfigMgr;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;



/**
 * 图片压缩处理工具类
 * 
 * @author Administrator
 * 
 */
public class ImageUtil {


	private Image srcImage = null;
	 private File destFile = null;
	 private String fileSuffix = null;
	    
	 private int imageWidth = 0;
	 private int imageHeight = 0;
	 private String smallIcon = "temp"; //压缩图片后,添加的扩展名（在图片后缀名前添加）

	 public ImageUtil(String fileName) throws IOException {
	      this(new File(fileName));
	 }

	 public ImageUtil(File _file) throws IOException {
	  _file.setReadOnly();
	  
	  this.fileSuffix = _file.getName().substring(
	    (_file.getName().indexOf(".") + 1),
	    (_file.getName().length()));
	  this.destFile = new File(_file.getPath().substring(0,
	    (_file.getPath().lastIndexOf(".")))
	    + this.smallIcon +"."+ this.fileSuffix);
	
	  srcImage = javax.imageio.ImageIO.read(_file);
	  
	  //得到图片的原始大小， 以便按比例压缩。
	  imageWidth = srcImage.getWidth(null);
	  imageHeight = srcImage.getHeight(null);

	 }
	 
	 public ImageUtil(File _file,String smallIcon) throws IOException {
		  _file.setReadOnly();
		  
		  this.fileSuffix = _file.getName().substring(
		    (_file.getName().indexOf(".") + 1),
		    (_file.getName().length()));
		  this.destFile = new File(_file.getPath().substring(0,
		    (_file.getPath().lastIndexOf(".")))
		    + smallIcon +"."+ this.fileSuffix);
		
		  srcImage = javax.imageio.ImageIO.read(_file);
		  
		  //得到图片的原始大小， 以便按比例压缩。
		  imageWidth = srcImage.getWidth(null);
		  imageHeight = srcImage.getHeight(null);

		 }

	 /**
	  * 强制压缩/放大图片到固定的大小
	  * @param w int 新宽度
	  * @param h int 新高度
	  * @throws IOException
	  */
	 public void resize(int w, int h) throws IOException {
	  //得到合适的压缩大小，按比例。
	  if ( imageWidth >= imageHeight){
	   h = (int)Math.round((imageHeight * w * 1.0 / imageWidth));
	  }else {
	   w = (int)Math.round((imageWidth * h * 1.0 / imageHeight));
	  }
	  if(w>imageWidth){
		  w=imageWidth;
	  }
	  if(h>imageHeight){
		  h=imageHeight;
	  }
	  

	  //构建图片对象
	  BufferedImage _image = new BufferedImage(w, h,
	    BufferedImage.TYPE_INT_RGB);
	  //绘制缩小后的图
	  _image.getGraphics().drawImage(srcImage, 0, 0, w, h, null);
	  //输出到文件流
	  FileOutputStream out = new FileOutputStream(destFile);
	  JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	  encoder.encode(_image);
	  FileUploadUtil up=new FileUploadUtil();
	  try {
		up.uploadFileBySMB(destFile, "upload/news/photoes", destFile.getName());
	  } catch (Exception e) {
		e.printStackTrace();
	  }finally{
		  out.close();
		  destFile.delete();
		  
	  }
	 }
	 
	 /**
	  * 强制压缩/放大图片到固定的大小
	  * @param w int 新宽度
	  * @param h int 新高度
	  * @param filePath 存放的路径
	  * @throws IOException
	  */
	 public void resize(int w, int h,String filePath) throws IOException {
	  //得到合适的压缩大小，按比例。
	  if ( imageWidth >= imageHeight){
	   h = (int)Math.round((imageHeight * w * 1.0 / imageWidth));
	  }else {
	   w = (int)Math.round((imageWidth * h * 1.0 / imageHeight));
	  }
	  if(w>imageWidth){
		  w=imageWidth;
	  }
	  if(h>imageHeight){
		  h=imageHeight;
	  }
	  

	  //构建图片对象
	  BufferedImage _image = new BufferedImage(w, h,
	    BufferedImage.TYPE_INT_RGB);
	  //绘制缩小后的图
	  _image.getGraphics().drawImage(srcImage, 0, 0, w, h, null);
	  //输出到文件流
	  FileOutputStream out = new FileOutputStream(destFile);
	  JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	  encoder.encode(_image);
	  FileUploadUtil up=new FileUploadUtil();
	  try {
		up.uploadFileBySMB(destFile, filePath, destFile.getName());
	  } catch (Exception e) {
		e.printStackTrace();
	  }finally{
		  out.close();
		  destFile.delete();
		  
	  }
	 }
	 /**
	  * 强制压缩/放大图片到固定的大小
	  * @param filePath 存放的路径
	  * @throws IOException
	  */
	 public void resetSize(String filePath) throws IOException {
	  int w = ConfigMgr.getIntCfg("news","resetWidth",200);
	  int h = ConfigMgr.getIntCfg("news","resetHeight",200);
	  //得到合适的压缩大小，按比例。
	  if ( imageWidth >= imageHeight){
	   h = (int)Math.round((imageHeight * w * 1.0 / imageWidth));
	  }else {
	   w = (int)Math.round((imageWidth * h * 1.0 / imageHeight));
	  }
	  if(w>imageWidth){
		  w=imageWidth;
	  }
	  if(h>imageHeight){
		  h=imageHeight;
	  }
	  //构建图片对象
	  BufferedImage _image = new BufferedImage(w, h,
	    BufferedImage.TYPE_INT_RGB);
	  //绘制缩小后的图
	  _image.getGraphics().drawImage(srcImage, 0, 0, w, h, null);
	  //输出到文件流
	  FileOutputStream out = new FileOutputStream(destFile);
	  JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	  encoder.encode(_image);
	  try {
		  FileUploadUtil.uploadFileBySMB(destFile, filePath, destFile.getName());
	  } catch (Exception e) {
		e.printStackTrace();
	  }finally{
		  out.close();
		  //destFile.delete();
		  
	  }
	 }
	 /**
	 * 压缩图片(按系统设置的高宽压缩)
	 * @param file 源文件
	 * @param smallIcon 压缩图片后,添加的扩展名（在图片后缀名前添加）
	 * @param filePath 存放到文件服务器的路径
	 * @param percentage 是否等比压缩 若true宽高比率将将自动调整
	 * @param delTempFile 是否删除临时文件
	 * @throws Exception 
	 */
	public static void resetSize(File file,String smallIcon,String filePath, boolean percentage,boolean delTempFile) throws Exception {
		float quality = 1.0F;//压缩清晰度 <b>建议为1.0</b>
		int width = ConfigMgr.getIntCfg("news", "resetWidth", 200);
		int height = ConfigMgr.getIntCfg("news", "resetHeight", 200);
		if(!file.exists()){
			return;
		}
		String oldFile = file.getAbsolutePath(); 
		Image srcFile = null;
		String newImage = null;
		try {
			/* 读取图片信息 */
			srcFile = ImageIO.read(file);
			int new_w = width;
			int new_h = height;
			if (percentage) {
				// 为等比缩放计算输出的图片宽度及高度
				double rate1 = ((double) srcFile.getWidth(null)) / (double) width + 0.1;
				double rate2 = ((double) srcFile.getHeight(null)) / (double) height + 0.1;
				double rate = rate1 > rate2 ? rate1 : rate2;
				new_w = (int) (((double) srcFile.getWidth(null)) / rate);
				new_h = (int) (((double) srcFile.getHeight(null)) / rate);
			}
			/* 宽高设定 */
			BufferedImage tag = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(srcFile, 0, 0, new_w, new_h, null);

			/* 压缩后的文件名 */
			String filePrex = oldFile.substring(0, oldFile.lastIndexOf('.'));
			newImage = filePrex + smallIcon + oldFile.substring(filePrex.length());

			/* 压缩之后临时存放位置 */
			FileOutputStream out = new FileOutputStream(newImage);

			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);

			/* 压缩质量 */
			jep.setQuality(quality, true);
			encoder.encode(tag, jep);
			out.close();
			
			//上传到文件服务器
			File uploadFile = new File(newImage);
			if(uploadFile.exists()){
				FileUploadUtil.uploadFileBySMB(uploadFile, filePath, uploadFile.getName());
				uploadFile.delete();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			srcFile.flush();
			if(delTempFile && file.exists()){
				file.delete();
			}
		}
	}
	
	 /**
	 * 压缩图片
	 * @param file 源文件
	 * @param smallIcon 压缩图片后,添加的扩展名（在图片后缀名前添加）
	 * @param filePath 存放到文件服务器的路径
	 * @param delTempFile 是否删除临时文件
	 * @throws Exception 
	 */
	public static void resetSize(File originalFile, String smallIcon, String filePath, boolean delTempFile) {
		float quality = ConfigMgr.getFloatCfg("news", "resetQuality", 1);
		int newWidth = ConfigMgr.getIntCfg("news", "resetWidth", 200);
		String oldFile = originalFile.getAbsolutePath();
		String newImage = null;
		if (quality > 1) {
			throw new IllegalArgumentException("Quality has to be between 0 and 1");
		}

		try {
			ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());
			Image i = ii.getImage();
			Image resizedImage = null;

			int iWidth = i.getWidth(null);
			int iHeight = i.getHeight(null);

			if (iWidth > iHeight) {
				resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight) / iWidth, Image.SCALE_SMOOTH);
			} else {
				resizedImage = i.getScaledInstance((newWidth * iWidth) / iHeight, newWidth, Image.SCALE_SMOOTH);
			}

			// This code ensures that all the pixels in the image are loaded.
			Image temp = new ImageIcon(resizedImage).getImage();

			// Create the buffered image.
			BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null), temp.getHeight(null), BufferedImage.TYPE_INT_RGB);

			// Copy image to buffered image.
			Graphics g = bufferedImage.createGraphics();

			// Clear background and paint the image.
			g.setColor(Color.white);
			g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
			g.drawImage(temp, 0, 0, null);
			g.dispose();

			// Soften.
			float softenFactor = 0.05f;
			float[] softenArray = { 0, softenFactor, 0, softenFactor, 1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };
			Kernel kernel = new Kernel(3, 3, softenArray);
			ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
			bufferedImage = cOp.filter(bufferedImage, null);

			String filePrex = oldFile.substring(0, oldFile.lastIndexOf('.'));
			newImage = filePrex + smallIcon + oldFile.substring(filePrex.length());

			// Write the jpeg to a file.
			FileOutputStream out = new FileOutputStream(newImage);

			// Encodes image as a JPEG data stream
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage);

			param.setQuality(quality, true);

			encoder.setJPEGEncodeParam(param);
			encoder.encode(bufferedImage);
			out.close();

			// 上传到文件服务器
			File uploadFile = new File(newImage);
			if (uploadFile.exists()) {
				FileUploadUtil.uploadFileBySMB(uploadFile, filePath, uploadFile.getName());
				uploadFile.delete();
			}
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} catch (Exception e) {
		} finally {
			if (delTempFile && originalFile.exists()) {
				originalFile.delete();
			}
		}
	}

    /**
     * 重置图片路径
     * @param oldImgPath 图片原路径
     * @param smallIcon 压缩图片后,添加的扩展名(在图片后缀名前添加)
     * return 修改后的图片路径
     */
    public static String setImgPath(String oldImgPath,String smallIcon){
		if (!oldImgPath.isEmpty()) {
			String fileSuffix = oldImgPath.substring((oldImgPath.indexOf(".") + 1), (oldImgPath.length()));
			oldImgPath = oldImgPath.substring(0, oldImgPath.lastIndexOf(".")) + smallIcon + "." + fileSuffix;
		}
		return oldImgPath;
    }
    
    /*
    * 压缩图片文件<br>  
    * 先保存原文件，再压缩、上传  
    *   
    * @param oldFile  
    *            要进行压缩的文件全路径  
    * @param width  
    *            宽度  
    * @param height  
    *            高度  
    * @param quality  
    *            质量  
    * @param smallIcon  
    *            小图片的后缀  
    * @return 返回压缩后的文件的全路径  
    */  
   public static String zipImageFile(File oldFile, int w, int h,   
           float quality, String fileRealName,String fileName) {   
       if (oldFile == null) {   
           return null;   
       }   
       String newImage = null;   
       try {   
           /** 对服务器上的临时文件进行处理 */  
           Image srcFile = ImageIO.read(oldFile);
           int imageWidth = srcFile.getWidth(null);
           System.out.println(imageWidth);
           int imageHeight = srcFile.getHeight(null);
           System.out.println(imageHeight);
//           w = imageWidth/4;
//           h = imageHeight/4;
			if ( imageWidth >= imageHeight)    
		            {    
		                w = w;    
		                h = (int)Math.round((imageHeight * w * 1.0 / imageWidth));    
		            }    
		            else     
		            {    
		                h = h;    
		                w = (int)Math.round((imageWidth * h * 1.0 / imageHeight));    
		            }
           System.out.println(h);
           System.out.println(w);
           /** 宽,高设定 */  
           BufferedImage tag = new BufferedImage(w, h,   
                   BufferedImage.TYPE_INT_RGB);   
           tag.getGraphics().drawImage(srcFile, 0, 0, w, h, null);     
           /** 压缩后的文件名 */   
           newImage = fileName +"\\"+ fileRealName;
                       
          /**将图片以流形式写入文件*/
			FileOutputStream out = new FileOutputStream(newImage);

			
           JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);   
           JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);   
           /** 压缩质量 */  
           jep.setQuality(quality, true);   
           encoder.encode(tag, jep);          
           out.flush();
           out.close(); 
//			in.close();
 
       } catch (FileNotFoundException e) {   
           e.printStackTrace();   
       } catch (IOException e) {   
           e.printStackTrace();   
       }   
       return newImage;   
   } 
   public static File download(String urlString, String filename,String savePath) throws Exception {  
       // 构造URL  
       URL url = new URL(urlString);  
       // 打开连接  
       URLConnection con = url.openConnection();  
       //设置请求超时为5s  
       con.setConnectTimeout(5*1000);  
       // 输入流  
       InputStream is = con.getInputStream();  
     
       // 1K的数据缓冲  
       byte[] bs = new byte[1024];  
       // 读取到的数据长度  
       int len;  
       // 输出的文件流  
      File sf=new File(savePath);  
      if(!sf.exists()){  
          sf.mkdirs();  
      }  
      OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);  
       // 开始读取  
       while ((len = is.read(bs)) != -1) {  
         os.write(bs, 0, len);  
       }  
       // 完毕，关闭所有链接  
       os.close();  
       is.close();  
       return sf;
   }   

	public static void main(String[] args) {
//		try {
//			File file = new File("D:\\3cbd1af9-28f4-4542-a595-cb33b782df53.jpg");
////			resize(file, new File("D:\\xxx.jpg"), 150, 0.7f);
//			try {
//				ImageUtil.resetSize(file, "langri", "D:", true, false);
////				ImageUtil.resetSize(file, "langri", "D:", false, false);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
			File file = new File("D:\\2-1305220UF1Q1.jpg");
			
			try {
				ImageUtil.resetSize(file, ConfigMgr.get("news", "small", "_small"),"upload/news/image/"+DateUtil.formatCurrent("yyyyMM"), false, false);
//				resize(file, new File("D:\\qiqi.jpg"), 250, 1f);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		String path=ImageUtil.zipImageFile(new File("D:\\3cbd1af9-28f4-4542-a595-cb33b782df53.jpg"), 128, 128, 1f, "xxx.jpg","D:\\");
	}



}
