package cn.com.do1.component.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * 提供IO流处理的工具类.
 * 
 * @author 刘宇明
 * @author 张岳文
 *
 */
public class IOUtil {
	
	/**
	 * 获得文件的扩展名. 
	 * 
	 * @param file 文件
	 * @return 返回文件的扩展名. 如果文件不存在或者不是文件是目录,返回null. 如果文件没有扩展名也返回null.
	 */
	public static String getFileExtension(File file){
		if(isFile(file)){
			return file.getName().substring(file.getName().indexOf(".")+1);
		}
		return null;
	}
	/**
	 * 获取文件的名称，不包容扩展名
	 * @param file
	 * @return 返回文件的扩展名. 如果文件不存在或者不是文件是目录,返回null. 如果文件没有扩展名也返回null.
	 */
	public static String getFileNameNoExt(File file){
		if(isFile(file)){
			return file.getName().substring(0,file.getName().indexOf("."));
		}
		return null;
	}
	/**
	 * 获取文件的当前文件夹绝对目录.
	 * @param file
	 * @return 返回文件的当前文件夹绝对目录，如果文件不存在或者不是文件是目录,返回null. 如果文件没有扩展名也返回null.
	 */
	public static String getFileParent(File file){
		if(isFile(file)){
			return file.getParent();
		}
		return null;
	}
	/**
	 * 文件不存在或者不是文件是目录,文件没有扩展名，返回false，否则返回true
	 * @param file
	 * @return
	 */
	public static boolean isFile(File file) {
		if(file!=null && file.isFile() && file.getName().indexOf(".")!=-1)
			return true;
		return false;
	}
	/**
	 * 复制输入流中的字节数据到输出流.
	 * 
	 * @param input 数据源
	 * @param output 数据存储目标
	 * @return 复制的字节数
	 * @throws RuntimeException 如果发生IO异常
	 */
	public static long copy(InputStream input, OutputStream output) {
		byte[] buffer = new byte[1024 * 4];
		long count = 0;
		int n = 0;
		try {
			while (-1 != (n = input.read(buffer))) {
				output.write(buffer, 0, n);
				count += n;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return count;
	}
	
	/**
	 * 无条件关闭InputStream. 此方法通常被用于finally内.
	 * 
	 * @param input 要被关闭的InputStream,可以为null或者已经关闭.
	 */
	public static void close(InputStream input){
		if(input!=null){
			try {
				input.close();
			} catch (IOException e) {
			}
		}
	}
	
	/**
	 * 无条件关闭OutputStream. 此方法通常被用于finally内.
	 * 
	 * @param output 要被关闭的OutputStream,可以为null或者已经关闭.
	 */
	public static void close(OutputStream output){
		if(output!=null){
			try {
				output.close();
			} catch (IOException e) {
			}
		}
	}
	
	/**
	 * 无条件关闭Writer. 此方法通常被用于finally内.
	 * 
	 * @param output 要被关闭的Writer,可以为null或者已经关闭.
	 */
	public static void close(Writer output){
		if(output!=null){
			try {
				output.close();
			} catch (IOException e) {
			}
		}
	}
	
	/**
	 * 无条件关闭Reader. 此方法通常被用于finally内.
	 * 
	 * @param input 要被关闭的Reader,可以为null或者已经关闭.
	 */
	public static void close(Reader input){
		if(input!=null){
			try {
				input.close();
			} catch (IOException e) {
			}
		}
	}
}
