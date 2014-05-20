package cn.com.do1.component.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.util.ImageTools;
import cn.com.do1.common.util.string.StringUtil;

/**
 * FileUpload
 * 用来保存优惠卷图片在本地的目录,并返回相对路径名
 *
 * @author yeshenghai
 * @version 1.0 2007/07/10
 */
public class FileUploadUtil {
    private transient final static Logger log = LoggerFactory.getLogger(FileUploadUtil.class);
    private static int BUFFER_SIZE = 16 * 1024;
    private static List<String> photoSize = Collections.synchronizedList(new ArrayList<String>());

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

    /**
     * 文件上传(不修改文件名)
     *
     * @param file
     * @param filePath 文件存放目录（相对）
     * @param fileName 文件名称
     *
     * @return distName 返回保存路径
     * @throws Exception
     */
    public static String uploadFile(File file, String filePath, String fileName) throws Exception {
        if (file == null || StringUtils.isBlank(filePath) || StringUtils.isBlank(fileName)) {
            throw new Exception("文件、文件路径和文件名不能为空！");
        }
        SmbFileUtils smbUtils= new SmbFileUtils(filePath, fileName);
        smbUtils.writeFile(file);
        String distName =  filePath + "/" + fileName;
        return distName;
    }

    /**
     * 文件上传(文件名由UUID重新命名)
     *
     * @param file
     * @param filePath 文件存放目录（相对）
     * @param fileName 文件名称
     *
     * @return distName 返回保存路径
     * @throws Exception
     */
    public static String uploadFileUUID(File file, String filePath, String fileName) throws Exception {
        if (file == null || StringUtils.isBlank(filePath) || StringUtils.isBlank(fileName)) {
            throw new Exception("文件、文件路径和文件名不能为空！");
        }
        String distName = UUID.randomUUID().toString();

        String newfilename= distName + fileName.substring(fileName.indexOf("."), fileName.length());
        SmbFileUtils smbUtils= new SmbFileUtils(filePath, newfilename);
        smbUtils.writeFile(file);
        distName =  filePath + "/" + newfilename;
        return distName;
        
    }

    /**
     * 通过jcifs组件文件上传(文件名由UUID重新命名)
     *
     * @param file
     * @param filePath 文件存放目录（相对）
     * @param fileName 文件名称
     *
     * @return distName 返回保存路径
     * @throws Exception
     */
    public static String uploadFileUUIDBySMB(File file, String filePath, String fileName) throws Exception {
        if (file == null || StringUtils.isBlank(filePath) || StringUtils.isBlank(fileName)) {
            throw new Exception("文件、文件路径和文件名不能为空！");
        }
        
        String distName = UUID.randomUUID().toString();

        String newfilename= distName + fileName.substring(fileName.indexOf("."), fileName.length());
        SmbFileUtils smbUtils= new SmbFileUtils(filePath, newfilename);
        smbUtils.writeFile(file);
        distName =  filePath + "/" + newfilename;
        return distName;
    }
    
    /**
     * 通过jcifs组件文件上传(不修改文件名)
     *
     * @param file
     * @param filePath 文件存放目录（相对）
     * @param fileName 文件名称
     *
     * @return distName 返回保存路径
     * @throws Exception
     */
    public static String uploadFileBySMB(File file, String filePath, String fileName) throws Exception {
        if (file == null || StringUtils.isBlank(filePath) || StringUtils.isBlank(fileName)) {
            throw new Exception("文件、文件路径和文件名不能为空！");
        }
       // String newfilename= distName + fileName.substring(fileName.indexOf("."), fileName.length());
        SmbFileUtils smbUtils= new SmbFileUtils(filePath, fileName);
        smbUtils.writeFile(file);
        String distName =  filePath + "/" + fileName;
        return distName;
    }
    
    public static String uploadFileBySMB(byte[] content, String filePath, String fileName) throws Exception {
        if (content == null || StringUtils.isBlank(filePath) || StringUtils.isBlank(fileName)) {
            throw new Exception("文件、文件路径和文件名不能为空！");
        }
       // String newfilename= distName + fileName.substring(fileName.indexOf("."), fileName.length());
        SmbFileUtils smbUtils= new SmbFileUtils(filePath, fileName);
        smbUtils.writeFile(content);
        String distName =  filePath + "/" + fileName;
        return distName;
    }
    
    public static void deleteFile(String filePath) {
        if (StringUtil.isNullEmpty(filePath)) {
            return;
        }
        filePath = ConstConfig.TOMCAT_CONTEXT_PATH + filePath;
        deleteSingleFile(filePath);
        for (String size : photoSize) {
            deleteSingleFile(ImageTools.newImageName(filePath, size));
        }
    }

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
    public static String getImagePath(String filePath, String imageType) {
        String imagePath = null;
        if (filePath != null && filePath.length() > 0) {
            int length = filePath.indexOf(".");
            if (length != -1) {
                imagePath = filePath.substring(0, length) + imageType + filePath.substring(length);
            }
        }
        return imagePath;
    }

    public static void deleteSingleFile(String realFilePath) {
        File file = new File(realFilePath);
        if (file.exists() && !file.isDirectory()) {
            boolean result = file.delete();
            if (!result) {
                log.error("删除文件：" + realFilePath + " 失败！");
            }
        }
    }
    
    /**
     * 根据参数fileType(文件类型)判断是否是图片格式的文件
     * @return
     */
    public static boolean isImagesFile(String fileType){
    	if(StringUtil.isNullEmpty(fileType))
    		return false;
    	String[] fileTypes = {"jpg", "png", "gif", "bmp", "tif", "jpeg"};
    	for(int i=0;i<fileTypes.length;i++){
    		if(fileType.equalsIgnoreCase(fileTypes[i]))
    			return true;
    	}
    	return false;
    }
    
    /**
     * 根据参数fileType(文件类型)判断是否是自定义的文件格式
     * @param fileType：上传的文件类型
     * @param fileTypes：目标类型字符串，用","隔开，如"jpg,png,gif"。
     * @return
     */
    public static boolean isFileType(String fileType, String fileTypes){
    	if(StringUtil.isNullEmpty(fileType))
    		return false;
    	if(StringUtil.isNullEmpty(fileTypes))
    		return false;
    	String[] fileTypeStr = fileTypes.split(",");
    	for(int i=0;i<fileTypeStr.length;i++){
    		if(fileType.equals(fileTypeStr[i]))
    			return true;
    	}
    	return false;
    }
}
