/**
 * 
 */
package cn.com.do1.component.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.util.ImageTools;
import cn.com.do1.common.util.string.StringUtil;

/**
 * 通用文件处理类
 * 
 * @author thinkpad
 * 
 */
public class CommonFileUtils {

	SmbFile smbFile;
	File fileobj;
	static String TMPDIR = "tmp_file";
	static int protocol = 0;// 0代表一般文件协议,1代表smb文件协议
	 private static List<String> photoSize = Collections.synchronizedList(new ArrayList<String>());
	 private transient final static Logger log = LoggerFactory.getLogger(FileUploadUtil.class);
	    static {
	    	photoSize.add("312x312");
	        photoSize.add("167x105");
	        photoSize.add("129x129");
	        photoSize.add("57x57");
	        photoSize.add("83x83");
	        photoSize.add("96x96");
	        photoSize.add("91x91");
	        photoSize.add("195x128");
	    }
	    
	static {
		if (Config.getInstance().getProperty("share_file_path").indexOf("smb:") != -1) {
			System.out.println(Config.getInstance().getProperty("share_file_path"));
			protocol = 1;
		}
	}

	/**
	 * 复制文件
	 * @param srcdir
	 * @param srcname
	 * @param oridir
	 * @param oriname
	 * @throws Exception
	 */
	public static void copyFile(String srcdir,String srcname,String oridir,String oriname) throws Exception{
		if (protocol == 1) {
			SmbFileUtils smbUtils = new SmbFileUtils(oridir, oriname);
			smbUtils.writeFile(srcdir, srcname);
			//return smbUtils.readFile();
		} else {
			/*return new BufferedInputStream(new FileInputStream(new File(
					bulidpath(dir, name))));*/
		}
	}
	/**
	 * 根据文件目录名和文件名取得文件流(调用流对象，注意要关闭流对象)
	 * 
	 * @param dir
	 *            目录名可以为空
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static InputStream getInputStream(String dir, String name)
			throws Exception {
		if (protocol == 1) {
			name=name.substring(1,name.length());
			SmbFileUtils smbUtils = new SmbFileUtils(dir, name);
			return smbUtils.readFile();
		} else {
			return new BufferedInputStream(new FileInputStream(new File(
					bulidpath(dir, name))));
		}
	}

	/**
	 * 写字符串内容到文件
	 * @param dir 文件夹目录
	 * @param name 文件名
	 * @param data 数据
	 * @param encoding 编码
	 * @throws Exception
	 */
	public static void writeStringToFile(String dir, String name, String data,
			String encoding) throws Exception {
		OutputStream out = getOutputStream(dir, name);
		try {
			out.write(data.getBytes(encoding));
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	/**
	 * 取得一个输出流,可以向这个流中写内容,用户需要手动关闭OutputStream流对象
	 * 
	 * @param dir
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static OutputStream getOutputStream(String dir, String name)
			throws Exception {
		if (protocol == 1) {
			SmbFileUtils smbUtils = new SmbFileUtils(dir, name);
			return smbUtils.getOutputStream();
		} else {
			String localfilepath = ConstConfig.TOMCAT_CONTEXT_PATH;
			if (StringUtils.isNotBlank(dir)) {
				localfilepath += File.separator + dir;
			}
			localfilepath += File.separator + name;
			File fileObj = new File(localfilepath);// 文件对象
			createLocalFileDir(localfilepath);
			return new BufferedOutputStream(new FileOutputStream(fileObj));
		}
	}

	private static boolean fileequal(SmbFile smbFile, File localFile)
			throws SmbException {
		if (smbFile.lastModified() == localFile.lastModified()) {
			if (smbFile.length() == localFile.length()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 将流写到指定文件,用户需要手动关闭inputStream流
	 * 
	 * @param inputStream
	 * @param dir
	 *            要保存的目录
	 * @param filename
	 *            文件名
	 * @throws Exception
	 */
	public static void writeFile(InputStream inputStream, String dir,
			String filename) throws Exception {
		if (protocol == 1) {
			SmbFileUtils smbUtils = new SmbFileUtils(dir, filename);
			smbUtils.writeFile(inputStream);
		} else {
			// 文件路径
			String localfilepath = ConstConfig.TOMCAT_CONTEXT_PATH;
			if (StringUtils.isNotBlank(dir)) {
				localfilepath += File.separator + dir;
			}
			localfilepath += File.separator + filename;

			File fileObj = new File(localfilepath);// 文件对象
			createLocalFileDir(localfilepath);
			writetoLocalFile(inputStream, fileObj);
		}

		// SmbFileUtils smbUtils = new SmbFileUtils(dir, filename);
	}

	/**
	 * 根据路径返回文件对象(如果传入的是一个完整的路径 ，那么dir可以不用传值),调用该方法，系统会在当前服务器的环境相关目录下生成一个文件复本
	 * 
	 * @param dir
	 *            文件夹
	 * @param name
	 *            文件名
	 * @return
	 * @throws Exception
	 */
	public static File getFileObj(String dir, String name) throws Exception {
		// 文件路径
		String localfilepath = ConstConfig.TOMCAT_CONTEXT_PATH + File.separator
				+ TMPDIR;
		if (StringUtils.isNotBlank(dir)) {
			localfilepath += File.separator + dir;
		}
		localfilepath += File.separator + name;
		if (protocol == 1) {
			SmbFileUtils smbUtils = new SmbFileUtils(dir, name);
			SmbFile smbFile = smbUtils.getSmbFile();

			File fileObj = new File(localfilepath);// 文件对象
			if (smbFile.exists() && fileObj.exists()) {
				if (fileequal(smbFile, fileObj)) {
					return fileObj;
				}
			}
			/**
			 * 判断文件是否存在
			 */
			if (smbFile.exists()) {
				fileObj.deleteOnExit();
				createLocalFileDir(localfilepath);
				fileObj.createNewFile();
				writetoLocalFile(smbUtils.readFile(), fileObj);
				// fileObj
			} else if (!smbFile.exists()) {
				return null;
			}
			return fileObj;
		} else {
			return new File(localfilepath);
		}
	}

	/**
	 * 将共享文件同步到服务器上
	 * 
	 * @param inputStream
	 * @param localFile
	 * @throws IOException
	 */
	private static void writetoLocalFile(InputStream inputStream, File localFile)
			throws IOException {
		// SmbFileInputStream fileInputStream = null;
		BufferedOutputStream output = null;
		int i = 0;
		byte[] b = new byte[1024];
		try {

			output = new BufferedOutputStream(new FileOutputStream(localFile));
			while ((i = inputStream.read(b)) != -1) {
				output.write(b, 0, i);
			}
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			// 关闭流
			if (inputStream != null)
				try {
					inputStream.close();
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
	 * 创建本地目录
	 * 
	 * @param path
	 */
	private static void createLocalFileDir(String path) {
		// if(path.lastIndexOf(i))
		int index1 = path.lastIndexOf("\\");
		int index2 = path.lastIndexOf("/");
		int index = 0;
		if (index1 > index2) {
			index = index1;
		} else {
			index = index2;
		}
		String dirpath = path.substring(0, index);
		File dirfile = new File(dirpath);
		if (!dirfile.exists())
			dirfile.mkdirs();
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param dir
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static boolean exists(String dir, String name) throws Exception {
		if (protocol == 1) {
			SmbFileUtils smbUtils = new SmbFileUtils(dir, name);
			return smbUtils.isexistFile();
		} else {
			File obj = new File(bulidpath(dir, name));
			return obj.exists();
		}
	}

	/**
	 * 当指定的路径是文件并且存在，那么删除
	 * 
	 * @param dir
	 * @param name
	 * @throws Exception
	 */
	public static boolean deleteFile(String dir, String name) throws Exception {
		if (protocol == 1) {
			SmbFileUtils smbUtils = new SmbFileUtils(dir, name);
			if (smbUtils.isexistFile() && smbUtils.isFile()) {
				smbUtils.delete();
				return true;
			}

		} else {
			File obj = new File(bulidpath(dir, name));
			if (obj.exists() && obj.isFile()) {
				obj.delete();
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据路径删除文件或文件夹
	 * @param filePath
	 */
	public static void deleteFile(String filePath) {
        if (StringUtil.isNullEmpty(filePath)) {
            return;
        }
        //filePath = ConstConfig.TOMCAT_CONTEXT_PATH + filePath;
        deleteSingleFile(filePath);
        for (String size : photoSize) {
            deleteSingleFile(ImageTools.newImageName(filePath, size));
        }
    }

	/**
	 * 根据路径删除文件，同时可以指定删除其它缩略文件
	 * @param filePath
	 * @param suffix
	 */
    public static void deleteFile(String filePath, List<String> suffix) {
        deleteFile(filePath);
        for (String size : suffix) {
            deleteSingleFile(ImageTools.newImageName(filePath, size));
        }
    }
    /**
     * 删除不同规则的图片文件
     * @param filePath
     * @param suffix
     */
    public static void deleteFileSuffix(String filePath, List<String> suffix) {
	    for (String size : suffix) {
	        deleteSingleFile(ImageTools.newImageName(filePath, size));
	    }
    }
    
    /**
     * 根据路径删除文件
     * @param realFilePath
     */
    public static void deleteSingleFile(String realFilePath) {
        try {
        	deleteFile(null,realFilePath);
		} catch (Exception e) {
			log.error("删除文件：" + realFilePath + " 失败！");
		}
    }
    
	private static String bulidpath(String dir, String name) {
		String path = "";
		path = Config.getInstance().getProperty("share_file_path");
		if (StringUtils.isNotBlank(dir))
			path += File.separator + dir;
		if (StringUtils.isNotBlank(name))
			path += File.separator + name;
		return path;
	}

	public static void main(String arg[]) throws FileNotFoundException,
			Exception {
		// CommonFileUtils.createLocalFileDir("D:\\liqin\\1.txt\\a\\liqinqin.txt");
		// CommonFileUtils.createLocalFileDir("D:\\liqin\\1.txt\\b/liqinqin.txt");
		// CommonFileUtils.createLocalFileDir("D:\\liqin\\1.txt\\a/liqinqin.txt");

		// 将文件以流的形式写到服务器指定目录
		/*File fobj = new File("E:\\liqinqin1.jpg");
		FileInputStream fis = new FileInputStream(fobj);
		CommonFileUtils.writeFile(new BufferedInputStream(fis), "liqinqin\\aa",
				"1.jpg");

		// 从服务器取得一个文件对象
		File f = CommonFileUtils.getFileObj("liqinqin\\aa", "1.jpg");

		// 取得服务器的一个文件输出流，然后向流中写文件内容
		BufferedOutputStream bop = new BufferedOutputStream(
				CommonFileUtils.getOutputStream("liqinqin\\aa", "1.txt"));
		bop.write("hello world!".getBytes());
		bop.flush();
		bop.close();
		*/
		List a=new ArrayList();
		a.add("312x312");
		deleteFile("uploadfile/coupon/photo/M000046/201106/M000046V000003.gif", a);
		//CommonFileUtils.writeStringToFile("liqinqin\\aa", "2.txt", "liqinqin\\aa", "utf-8");

	}
}
