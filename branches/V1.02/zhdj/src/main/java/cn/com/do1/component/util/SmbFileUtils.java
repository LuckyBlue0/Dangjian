package cn.com.do1.component.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import cn.com.do1.dqdp.core.ConfigMgr;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

/**
 * 基于SmbFile文件管理，
 * 
 * @author thinkpad
 * 
 */
public class SmbFileUtils {
	String url = null;
	String dirpath = null;
	String filename =null;
	String filepath=null;

	SmbFile smbFile=null;
	/**
	 * 
	 * @param dir
	 *            目标目录 
	 * @param filename
	 *            目标文件名
	 * @throws Exception
	 */
	public SmbFileUtils(String dirpath, String filename) throws Exception {
		url =  ConfigMgr.get("news", "share_file_path",Config.getInstance().getProperty("share_file_path")).replaceAll("\\\\", "/");
		
		this.filename=filename;
		if (dirpath != null && !dirpath.equals("")) {
			filepath = url + File.separator + dirpath;
		}else{
			filepath=url;
		}
		
		if (filename != null && !filename.equals("")) {
			filepath=filepath + File.separator + filename;
			this.filepath=filepath.replaceAll("\\\\", "/");
		}
		String[] arrpath=parserPath(this.filepath);
		this.dirpath=arrpath[0];
		smbFile=new SmbFile(this.filepath);
	}
	
	/**
	 * 文件路径，可以包涵文件所属目录名和文件名如：path1\path2\1.txt
	 * @param filepath
	 * @throws Exception
	 */
	public SmbFileUtils(String path) throws Exception {
		url =  ConfigMgr.get("news", "share_file_path",Config.getInstance().getProperty("share_file_path")).replaceAll("\\\\", "/");
		if (path != null && !path.equals("")) {
			filepath = url + File.separator + path;
		}else{
			throw new IOException("指定文件路径！");
		}
		this.filepath=filepath.replaceAll("\\\\", "/");
		String[] arrpath=parserPath(this.filepath);
		this.dirpath=arrpath[0];
		smbFile=new SmbFile(this.filepath);
	}
	
	/**
	 * 取得一个smbfile输出流
	 * @return
	 * @throws IOException
	 */
	public OutputStream getOutputStream() throws IOException{
		//if(sfilename==null || sfilename.equals(""))throw new IOException("指定源文件名!");
		if (this.filename == null || this.filename.equals(""))  throw new IOException("需要指定目标文件名!");
		//判断目标文件目录是否存在
		this.isexistAndCreateDir(new SmbFile(this.dirpath));
		//判断目标文件是否存在
		this.isexistAndCreateFile(smbFile);
		
		SmbFileOutputStream out = new SmbFileOutputStream(smbFile);
		return out;
	}
	
	/**
	 * 解析路径，返回一个数组：0，目录名;1,文件名
	 * @param filepath
	 * @return
	 */
	private static String[] parserPath(String filepath){
		int index0=filepath.lastIndexOf("/");
		int index1=filepath.lastIndexOf(".");
		if(index0>=index1){
			return new String[]{filepath,null};
		}else if(index0<index1){
			if(index0<0)index0=0;
			return new String[]{filepath.substring(0, index0),filepath.substring(index0+1)};
		}
		return null;
	}
	
	/**
	 * 返回smbFile文件对象
	 * @return
	 */
	public SmbFile getSmbFile(){
		return smbFile;
	}
	/**
	 * 根据转入的目录和文件名，生成一个完整的路径
	 * @param dir
	 * @param filename
	 * @return
	 */
	private String bulidPath(String dir, String filename){
		String filepath=url;
		if (dir != null && !dir.equals("")) {
			filepath = filepath + File.separator + dir;
		}

		if (filename != null && !filename.equals("")) {
				filepath = filepath + File.separator + filename;
		}
		return filepath.replaceAll("\\\\", "/");
	}

	/**
	 * 根据构造函数的文件路径，读取文件
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws SmbException
	 * @throws UnknownHostException
	 */
	public InputStream readFile() throws MalformedURLException, SmbException,
			UnknownHostException {
		//SmbFile fileobj=new SmbFile(filepath);
		if (smbFile != null && isexistFile(smbFile)) {
			SmbFileInputStream in = new SmbFileInputStream(smbFile);
			return new BufferedInputStream(in);
		}
		return null;
	}

	/**
	 * 根据输入流写文件到指定路径
	 * 
	 * @param inputStream
	 *            输入流
	 * @return
	 * @throws IOException
	 */
	public void writeFile(InputStream inputStream) throws IOException {
		// ConstConfig.SHAREFILEPATH
		byte[] b = new byte[1024];
		int i = 0;
		if (this.filename == null || this.filename.equals("")) {
			throw new IOException("需要指定文件名");
		}
		//判断目标文件目录是否存在
		this.isexistAndCreateDir(new SmbFile(this.dirpath));
		//判断目标文件是否存在
		//this.isexistAndCreateFile(oriSmbFile);
		
		SmbFileOutputStream output = new SmbFileOutputStream(smbFile);
		while ((i = inputStream.read(b)) != -1) {
			output.write(b, 0, i);
		}
		try {
			if (output != null)
				output.flush();
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			if (output != null)
				try {
					output.close();
				} catch (IOException e) {
					throw new IOException(e.getMessage());
				}
		}
	}

	
	/**
	 * 根据源目录和源文件写文件到指定目录下
	 * @param dir 源目录
	 * @param sfilename 源文件名
	 * @throws IOException
	 */
	public void writeFile(String dir, String sfilename) throws IOException {
		if(sfilename==null || sfilename.equals(""))throw new IOException("指定源文件名!");
		if (this.filename == null || this.filename.equals(""))  throw new IOException("需要指定目标文件名!");
		SmbFile srcSmbFile = new SmbFile(this.bulidPath(dir, sfilename));//
		SmbFile oriSmbFile = smbFile;//
		byte[] b = new byte[1024];
		int i = 0;
		
		SmbFileInputStream fileInputStream = null;
		SmbFileOutputStream output = null;
		try {
			//判断目标文件目录是否存在
			this.isexistAndCreateDir(new SmbFile(this.dirpath));
			//判断目标文件是否存在
			this.isexistAndCreateFile(oriSmbFile);
			
			fileInputStream = new SmbFileInputStream(srcSmbFile);
			output = new SmbFileOutputStream(oriSmbFile);
			while ((i = fileInputStream.read(b)) != -1) {
				output.write(b, 0, i);
			}
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			//关闭流
			if (fileInputStream != null)
				try {
					fileInputStream.close();
				} catch (IOException e) {
					throw new IOException(e.getMessage());
				}
			try {
				if (output != null)
					output.flush();
			} catch (IOException e) {
				throw new IOException(e.getMessage());
			} finally {
				if (output != null)
					try {
						output.close();
					} catch (IOException e) {
						throw new IOException(e.getMessage());
					}
			}

		}

	}

	/**
	 * 将给定的文件对象写到指定文件中
	 * @param fileobj 要写出的文件对象
	 * @throws IOException
	 */
	public void writeFile(File fileobj) throws IOException {
		if(fileobj==null)throw new IOException("指定源文件!");
		if (this.filename == null || this.filename.equals(""))  throw new IOException("需要指定目标文件名!");
		//SmbFile oriSmbFile = new SmbFile(this.filepath);//
		byte[] b = new byte[1024];
		int i = 0;
		
		BufferedInputStream fileInputStream = null;
		SmbFileOutputStream output = null;
		try {
			//判断目标文件目录是否存在
			this.isexistAndCreateDir(new SmbFile(this.dirpath));
			//判断目标文件是否存在
			this.isexistAndCreateFile(smbFile);
			
			fileInputStream = new BufferedInputStream(new FileInputStream(fileobj));
			output = new SmbFileOutputStream(smbFile);
			while ((i = fileInputStream.read(b)) != -1) {
				output.write(b, 0, i);
			}
		} catch (IOException e) {
			throw new IOException("上传文件失败!"+e.getMessage());
		} finally {
			//关闭流
			if (fileInputStream != null)
				try {
					fileInputStream.close();
				} catch (IOException e) {
					throw new IOException("文件已经上传成功，关闭输入流失败"+e.getMessage());
				}
			try {
				if (output != null)
					output.flush();
			} catch (IOException e) {
				throw new IOException("文件已经上传成功，关闭输出流失败"+e.getMessage());
			} finally {
				if (output != null)
					try {
						output.close();
					} catch (IOException e) {
						throw new IOException("文件已经上传成功，关闭输出流失败"+e.getMessage());
					}
			}

		}

	}
	
	/**
	 * 将给定的文件对象写到指定文件中
	 * @param fileobj 要写出的文件对象
	 * @throws IOException
	 */
	public void writeFile(byte[] content) throws IOException {
		if (this.filename == null || this.filename.equals(""))  throw new IOException("需要指定目标文件名!");
		//SmbFile oriSmbFile = new SmbFile(this.filepath);//
		byte[] b = new byte[1024];
		int i = 0;
		
		ByteArrayInputStream fileInputStream = null;
		SmbFileOutputStream output = null;
		try {
			//判断目标文件目录是否存在
			this.isexistAndCreateDir(new SmbFile(this.dirpath));
			//判断目标文件是否存在
			this.isexistAndCreateFile(smbFile);
			
			fileInputStream = new ByteArrayInputStream(content);
			output = new SmbFileOutputStream(smbFile);
			while ((i = fileInputStream.read(b)) != -1) {
				output.write(b, 0, i);
			}
		} catch (IOException e) {
			throw new IOException("上传文件失败!"+e.getMessage());
		} finally {
			//关闭流
			if (fileInputStream != null)
				try {
					fileInputStream.close();
				} catch (IOException e) {
					throw new IOException("文件已经上传成功，关闭输入流失败"+e.getMessage());
				}
			try {
				if (output != null)
					output.flush();
			} catch (IOException e) {
				throw new IOException("文件已经上传成功，关闭输出流失败"+e.getMessage());
			} finally {
				if (output != null)
					try {
						output.close();
					} catch (IOException e) {
						throw new IOException("文件已经上传成功，关闭输出流失败"+e.getMessage());
					}
			}

		}

	}
	
	/**
	 * 判断传入的file是否存在
	 * 
	 * @param obj
	 *            SmbFile文件对象
	 * @throws MalformedURLException
	 * @throws SmbException
	 */
	public boolean isexistFile(SmbFile obj) throws MalformedURLException,
			SmbException {
		return obj.exists();
	}

	/**
	 * 判断当前构造的文件是否存在
	 * @return
	 * @throws IOException
	 */
	public boolean isexistFile() throws   IOException{
		//SmbFile obj=new SmbFile(this.filepath);
		return smbFile.exists();
	}
	/**
	 * 创建目录，如果不存在
	 * 
	 * @param SmbFile 文件对象
	 * @throws MalformedURLException
	 * @throws SmbException
	 */
	public void isexistAndCreateDir(SmbFile obj) throws MalformedURLException,
			SmbException {
		// SmbFile file = new SmbFile(url + File.separator + dir);
		if (!isexistFile(obj))
			obj.mkdirs();
	}

	/**
	 * 创建文件，如果文件不存在
	 * 
	 * @throws IOException
	 */
	public void isexistAndCreateFile(SmbFile obj) throws IOException {
		if (!isexistFile(obj))
			obj.createNewFile();
	}

	
	/**
	 * 根据目录名和文件名判断是否为文件
	 * @param dir
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public boolean isFile(String dir,String name) throws IOException{
		if (name == null || name.equals(""))  throw new IOException("需要指定目标文件名!");
		SmbFile oriSmbFile = new SmbFile(this.bulidPath(dir, name));//
		return oriSmbFile.isFile();
	}
	
	/**
	 * 判断当前构造的文件是否为文件类型
	 * @return
	 * @throws IOException
	 */
	public boolean isFile() throws IOException{
		
		return this.smbFile.isFile();
	}
	/**
	 * 删除当前指定的文件
	 * @throws IOException
	 */
	public void delete() throws IOException{
		if (this.filename == null || this.filename.equals(""))  throw new IOException("需要指定目标文件名!");
		//SmbFile oriSmbFile = new SmbFile(this.filepath);//
		smbFile.delete();
	}
	
	/**
	 * 根据文件夹名和文件名删除文件
	 * @param dir 目录名 
	 * @param name 文件名
	 * @throws IOException
	 */
	public void delete(String dir,String name) throws IOException{
		if (name == null || name.equals(""))  throw new IOException("需要指定目标文件名!");
		SmbFile oriSmbFile = new SmbFile(this.bulidPath(dir, name));//
		oriSmbFile.delete();
	}
	/**
	 * 根据smb完整的协议路径删除文件
	 * @param path
	 * @throws IOException
	 */
	public static void delete(String path) throws IOException{
		if (path == null || path.equals(""))  throw new IOException("需要指定目标文件!");
		SmbFile oriSmbFile = new SmbFile(path.replaceAll("\\\\", "/"));//
		oriSmbFile.delete();
	}
	
	
	public static void main(String[] ar) throws MalformedURLException,
			SmbException, Exception {
		/*
		 * SmbFileUtils obj = new SmbFileUtils();
		 * obj.isexistAndCreateFile("vote/logo/main.java");
		 */
		byte[] b = new byte[1024];
		int i = 0;
		/*
		SmbFileInputStream inputStream=new SmbFileInputStream(new SmbFile("E:\\liqinqin.jpg"));
		BufferedInputStream inputStream = new BufferedInputStream(
				new FileInputStream(new File("E:\\liqinqin.jpg")));
		BufferedOutputStream output = new BufferedOutputStream(
				new FileOutputStream(new File("E:\\liqinqin12.jpg")));
		while ((i = inputStream.read(b)) != -1) {
			output.write(b, 0, i);
		}
		output.flush();
		output.close();*/
		String[] s=parserPath("E:/mysteelosft/my/liqinqin12.jpg");
		System.out.println(s[0]+"---"+s[1]);
	}

}
