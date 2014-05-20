package cn.com.do1.component.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

import cn.com.do1.component.util.FileUploadUtil;

public class uploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Charset","UTF-8");
		PrintWriter out=response.getWriter();
		JSONObject obj = new JSONObject();
		//文件保存目录路径
		String savePath = request.getSession().getServletContext().getRealPath("/") + "upload/file/";

		//文件保存目录URL
		String saveUrl  = request.getContextPath() +"/upload/file/";
//		try {
//			saveUrl = ConfigMgr.get("imageSyn", "dpicUrl") + "/upload/news/";
//		} catch (ConfigLoadExcetion e) {
//			e.printStackTrace();
//		}

		//定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("videoFile", "swf,flv,avi,rm,rmvb,3gp");
		extMap.put("partyFile", "doc,ppt,xls,txt");
		extMap.put("dataFile", "doc,ppt,xls,txt");
		extMap.put("musicFile", "mp3");
		extMap.put("versionFile", "apk,ipa");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		//最大文件大小
		long maxSize = 1000000000;

		response.setContentType("text/html; charset=UTF-8");

		if(!ServletFileUpload.isMultipartContent(request)){
			obj.put("tip", "请选择文件。");
			out.println(obj.toJSONString());
			return;
		}
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			uploadDir.mkdirs();
			//out.println(getError("上传目录不存在。"));
			//return;
		}
	
		

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		
		//创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(2048*1024);
		myProgressListener getBarListener = new myProgressListener(request);
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setProgressListener(getBarListener);
		upload.setHeaderEncoding("UTF-8");
		try {
			List formList = upload.parseRequest(request);
			Iterator<Object> formItem = formList.iterator();
			// 将进度监听器加载进去
			while (formItem.hasNext()) {
				FileItem item = (FileItem) formItem.next();
				String fileName = item.getName();
				long fileSize = item.getSize();
			
				if (item.isFormField()) {
					System.out.println("Field Name:" + item.getFieldName());
					if("file".equals(item.getFieldName())){
						obj.put("tip", "请选择文件。");
						out.println(obj.toJSONString());
						return;
					}
				} else {
					
					//检查文件大小
					if(item.getSize() > maxSize){
						obj.put("tip", "上传文件大小超过限制。");
						out.println(obj.toJSONString());
						return;
					}
					//检查扩展名
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
						obj.put("tip", "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
						out.println(obj.toJSONString());
						return;
					}
					
					String uuid = java.util.UUID.randomUUID().toString();
					String newFileName = uuid + "." + fileExt;
					try{
						File uploadedFile = new File(savePath, newFileName);
						item.write(uploadedFile);
					}catch(Exception e){
						obj.put("tip", "上传文件失败。");
						out.println(obj.toJSONString());
						e.printStackTrace();
						return;
					}
					
					// 同步图片至文件服务器
//					File file=new File(savePath+newFileName);
//					String backURL="";
//					FileUploadUtil up=new FileUploadUtil();
//					try {
//						backURL=up.uploadFileBySMB(file, "upload/file/"+dirName+"/"+ymd, newFileName);
//						obj.put("url", backURL);
//						obj.put("result", "true");
//						out.println(obj.toJSONString());
//					} catch (Exception e1) {
//						obj.put("tip", "同步文件至文件服务器失败。");
//						out.println(obj.toJSONString());
//						e1.printStackTrace();
//					}
					obj.put("url", "upload/file/"+dirName+"/"+ymd+"/"+newFileName);
					obj.put("result", "true");
					out.println(obj.toJSONString());
					
				}
			}
		} catch (FileUploadException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}

}
