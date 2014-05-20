package cn.com.do1.component.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class FileRW {
	private final static Logger logger = Logger.getLogger(FileRW.class.getName());


	private static int maxBufferSize = 1024;

	private static int fileLength = -1;
	
	public static String getRemoteString(String destUrl){
		byte[] bytes = getRemoteFileData(destUrl) ;
		if(bytes!=null){
			return new BASE64Encoder().encode(bytes); 
		}else{
			return new String() ;
		}
	}
	
	public static byte[] getRemoteFileData(String destUrl) {
		boolean result = false;
		ByteArrayOutputStream bos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		byte[] data = null;
		byte[] buf = new byte[4096];
		int size = 0;
		int retryTimes = 0;
		while (!result && retryTimes < 3) {
			try {
				// 建立链接
				url = new URL(destUrl);
				httpUrl = (HttpURLConnection)url.openConnection();
				// 连接指定的资源
				httpUrl.connect();
				// 获取网络输入流
				bis = new BufferedInputStream(httpUrl.getInputStream());
				// 建立文件
				bos = new ByteArrayOutputStream();
				
				// 保存文件
				while (-1 != (size = bis.read(buf, 0, buf.length))) {
					bos.write(buf, 0, size);
				}
				data = bos.toByteArray();
				
				result = true;
			} catch (MalformedURLException e) {
				logger.error("非法的url地址：" + e.getMessage());
				retryTimes++;
				if(retryTimes==3) {
					logger.warn("重试三次下载文件失败: " + e.getMessage());
				}
			} catch (FileNotFoundException e) {
				logger.error("无法找到文件：" + e.getMessage());
				retryTimes++;
				if(retryTimes==3) {
					logger.warn("重试三次下载文件失败: " + e.getMessage());
				}					
				//e.printStackTrace();
			} catch (IOException e) {
				logger.error("写文件IO异常：" + e.getMessage());
				retryTimes++;
				if(retryTimes==3) {
					logger.warn("重试三次下载文件失败: " + e.getMessage());
				}
			}finally {
				try {
					if(bos!=null) {
						bos.close();
					}
					if(bis!=null) {
						bis.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(httpUrl!=null) {
				    httpUrl.disconnect();
				}
			}
		}
		return data;
	}

	/*
	 * 写文件的操作String---->File
	 */
	public String writeStringToFile(String basePath, String picType, String picData,
			String linkid) throws Exception {
		logger.info("realpath=" + basePath);
		String picname = linkid + "." + picType;
		String picurl = basePath + "/" + picname;
		logger.info("picurl=" + picurl);
		OutputStream out = null;

		out = new FileOutputStream(picurl);
		byte[] buf = new BASE64Decoder().decodeBuffer(picData);

		fileLength = -1;

		if (buf != null) {
			out.write(buf);
			fileLength = buf.length;
			new BASE64Encoder().encode(buf);
		}

		if (out != null) {
			try {
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return picurl;
	}

	/*
	 * 读文件的操作File--->String
	 */
	public static String getFileToString(String path) {
		String str = new String();
		File file = new File(path);
		InputStream in = null ;
		try {
			in = new FileInputStream(file);
			byte buffer[] = new byte[maxBufferSize];
			int read = 0;
			fileLength = 0;
			while ((read = in.read(buffer)) != -1) {
				fileLength += read;

				if (read < maxBufferSize) // trick 17, if buffer size < max. size :-)
				{
					byte[] buf = new byte[read];
					System.arraycopy(buffer, 0, buf, 0, read);
					buffer = buf;
				}
				str += new BASE64Encoder().encode(buffer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(in!=null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return str;
	}

}
