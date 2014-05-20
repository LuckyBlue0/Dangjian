package cn.com.do1.component.news.newsinfo.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.struts.CatchException;
import cn.com.do1.common.annotation.struts.JSONOut;
import cn.com.do1.common.annotation.struts.SearchValueType;
import cn.com.do1.common.annotation.struts.SearchValueTypes;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.dictionary.DictOperater;
import cn.com.do1.common.dictionary.vo.ExItemObj;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.common.util.DateUtil;
import cn.com.do1.common.util.FileUtil;
import cn.com.do1.component.basis.newstype.model.TbNewsColumnTypePO;
import cn.com.do1.component.basis.newstype.service.INewsTypeService;
import cn.com.do1.component.mobileclient.device.service.IDeviceService;
import cn.com.do1.component.news.newsinfo.model.TbNewsInfoPO;
import cn.com.do1.component.news.newsinfo.service.INewsInfoService;
import cn.com.do1.component.news.newsinfo.vo.NewsPreviewInfoVO;
import cn.com.do1.component.pushnotification.pushnotification.util.PushNotificationUtil;
import cn.com.do1.component.pushnotification.pushnotification.vo.InfoVo;
import cn.com.do1.component.util.FileUploadUtil;
import cn.com.do1.component.util.ImageUtil;
import cn.com.do1.dqdp.core.ConfigMgr;
import cn.com.do1.dqdp.core.DqdpAppContext;
import cn.com.do1.dqdp.core.permission.IUser;

/**
 * Copyright &copy; 2013 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class NewsInfoAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory.getLogger(NewsInfoAction.class);
	@Resource
	private INewsInfoService newsInfoService;
	private TbNewsInfoPO tbNewsInfoPO; 
	private String ids[];
	private String newsInfoId;
	private File file;
	private String fileFileName;
	private String fileContentType;
	private String newsType;
	
	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}


	@Resource
	private INewsTypeService newsTypeService;


	public String[] getIds() {
		return ids;
	} 

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getNewsInfoId() {
		return newsInfoId;
	}

	public void setNewsInfoId(String newsInfoId) {
		this.newsInfoId = newsInfoId;
	}

	/**
	 * 列表查询时，页面要传递的参数
	 */
	@SearchValueTypes(nameFormat = "false", value = { @SearchValueType(name = "title", type = "string", format = "%%%s%%") })
	public void ajaxSearch() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(), getPageSize());
		try {
			pager = newsInfoService.searchNewsinfo(getSearchValue(), pager);
			addJsonPager("pageData", pager);
			if (getSearchValue().get("newsInfoType") != null) {
				TbNewsColumnTypePO newsTypePO = this.newsTypeService.getNewsTypeByType(getSearchValue().get("newsInfoType").toString());
				addJsonObj("newsTypePO", newsTypePO);
			}
			 setActionResult("0","查询成功");
		} catch (Exception e) {
			 setActionResult("1001","查询失败,"+e.getMessage());
			 logger.error(e.getMessage(),e);
		}finally{
			doJsonOut();
		}

	}

	@JSONOut(catchException = @CatchException(errCode = "1002", successMsg = "保存成功", faileMsg = "保存失败"))
	public void ajaxSave() throws Exception, BaseException {
		// todo:完成新增的代码;
		newsInfoService.saveNewsInfo(tbNewsInfoPO);

	}

	@JSONOut(catchException = @CatchException(errCode = "1004", successMsg = "删除成功", faileMsg = "删除失败"))
	public void ajaxBatchDelete() throws Exception, BaseException {
		// 完成批量更新的代码
		deleteTbNewsInfoPO();
	}

	public void setTbNewsInfoPO(TbNewsInfoPO tbNewsInfoPO) {
		this.tbNewsInfoPO = tbNewsInfoPO;
	}

	public TbNewsInfoPO setTbNewsInfoPO() {
		return this.tbNewsInfoPO;
	}

	private void deleteTbNewsInfoPO() {
		if (AssertUtil.isEmpty(newsInfoId))
			newsInfoId = ids[0];
		if (tbNewsInfoPO == null)
			tbNewsInfoPO = new TbNewsInfoPO();
		tbNewsInfoPO._setPKValue(newsInfoId);
		if (ids.length > 1) {
			super.ajaxBatchDelete(TbNewsInfoPO.class, ids);
		} else {
			super.ajaxDelete(tbNewsInfoPO);
		}
	}

	public void batchDeleteTbNewsInfoPO() {
		super.ajaxBatchDelete(TbNewsInfoPO.class, ids);
	}

	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxView() throws Exception, BaseException {
		TbNewsInfoPO xxPO = newsInfoService.searchByPk(TbNewsInfoPO.class, newsInfoId);
		addJsonFormateObj("tbNewsInfoPO", xxPO);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
	}
	
	/**
	 * 预览详情
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxPreview()throws Exception, BaseException {
		TbNewsInfoPO po = newsInfoService.searchByPk(TbNewsInfoPO.class, newsInfoId);
		NewsPreviewInfoVO perviewInfoVO = newsInfoService.getNewsPreviewInfo(newsInfoId,2);
		addJsonObj("perviewInfoVO", perviewInfoVO);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
		addJsonFormateObj("tbNewsInfoPO", po);
	}
	
	/**
	 * 发布或下架
	 * @throws BaseException 
	 * @throws Exception 
	 */
	public void ajaxPushOrOut() throws BaseException, Exception{
		if(AssertUtil.isEmpty(tbNewsInfoPO)){
			throw new BaseException("请求参数不能为空!");
		}
		if(AssertUtil.isEmpty(tbNewsInfoPO.getNewsInfoId())){
			throw new BaseException("非法操作!");
		}
		if(AssertUtil.isEmpty(tbNewsInfoPO.getStatus())){
			throw new BaseException("非法操作!");
		}
		
		TbNewsInfoPO xxPO = newsInfoService.searchByPk(TbNewsInfoPO.class, tbNewsInfoPO.getNewsInfoId());
		if(!AssertUtil.isEmpty(xxPO)){
			TbNewsInfoPO po = new TbNewsInfoPO();
			po.setNewsInfoId(xxPO.getNewsInfoId());
			if(tbNewsInfoPO.getStatus() == 1){
				po.setPushTime(new Date());
				po.setStatus(1);
				IUser user = (IUser)DqdpAppContext.getCurrentUser();
				if(user != null){
					if(!AssertUtil.isEmpty(po.getStatus()) && po.getStatus() == 1){
						po.setPushTime(new Date());
						po.setPushUserId(user.getPersonId());
					}
				}
				setActionResult("0","发布成功");
				if (xxPO.getNewsInfoType().equals("1") || xxPO.getNewsInfoType().equals("2")) {
					final InfoVo news = new InfoVo();
					news.setId(xxPO.getNewsInfoId());
					news.setTitle(xxPO.getTitle());
					news.setType(xxPO.getNewsInfoType());
					if (news.getType().equals("1")) {
						news.setTypeDesc("通知公告");
					} else if (news.getType().equals("2")) {
						news.setTypeDesc("工作动态");
					}
					PushNotificationUtil.pushNews(news);
				}
			}else if(tbNewsInfoPO.getStatus() == 2){
				po.setStatus(2);
				setActionResult("0","下架成功");
			}
			newsInfoService.updatePO(po, false);
		}
		doJsonOut();
	}
	@Resource
	private IDeviceService deviceService;
	/**
	 * 消息推送
	 * @param news
	 * @throws BaseException
	 * @throws Exception
	 */
	private void push(TbNewsInfoPO news) throws BaseException, Exception{
    	final InfoVo vo=new InfoVo();
    	vo.setId(news.getNewsInfoId());
    	vo.setTitle(news.getTitle());
    	vo.setType(news.getNewsInfoType());
//    	vo.setPublishTime(news.getPushTime());
//    	vo.setSource(news.getSource());
//    	vo.setUrl("/jsp/mobileclient/view.jsp");
    	//iOS推送
    	final List<String> devices=deviceService.getDeviceForiOS();
    	new Thread(new Runnable() {
			@Override
			public void run() {
		    	PushNotificationUtil.push4Iphone(vo,devices);
			}
		}).start();
    	
    	//android推送
    	new Thread(new Runnable() {
			@Override
			public void run() {
		    	PushNotificationUtil.push4Android(vo);
			}
		}).start();
	}
	
	/**
	 * 置顶
	 * @throws BaseException
	 * @throws Exception
	 */
	public void ajaxTop() throws BaseException, Exception{
		if(AssertUtil.isEmpty(tbNewsInfoPO)){
			throw new BaseException("请求参数不能为空!");
		}
		if(AssertUtil.isEmpty(tbNewsInfoPO.getNewsInfoId())){
			throw new BaseException("非法操作!");
		}
		if(AssertUtil.isEmpty(tbNewsInfoPO.getBuyTop())){
			throw new BaseException("非法操作!");
		}
		TbNewsInfoPO xxPO = newsInfoService.searchByPk(TbNewsInfoPO.class, tbNewsInfoPO.getNewsInfoId());
		if(!AssertUtil.isEmpty(xxPO)){
			TbNewsInfoPO po = new TbNewsInfoPO();
			po.setNewsInfoId(xxPO.getNewsInfoId());
			if(tbNewsInfoPO.getBuyTop() == 1){
				po.setBuyTop(1);
				setActionResult("0","置顶成功");
			}else if(tbNewsInfoPO.getBuyTop() == 2){
				po.setBuyTop(0);
				setActionResult("0","取消置顶成功");
			}
			newsInfoService.updatePO(po, false);
		}
		doJsonOut();
	}

	public TbNewsInfoPO getTbNewsInfoPO() {
		return this.tbNewsInfoPO;
	}

	public void fileUpload() {
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		// 最大文件大小
		long maxSize = 1000000;

		if (AssertUtil.isEmpty(file)) {
			setActionResult("1000", "上传文件不能为空！");
			doJsonOut();
			return;
		}
		// 检查文件大小
		if (file.length() > maxSize) {
			setActionResult("1000", "上传文件大小超过限制！");
			doJsonOut();
			return;
		}
		// 检查扩展名
		String fileExt = fileFileName.substring(fileFileName.lastIndexOf(".") + 1).toLowerCase();
		if (!Arrays.<String> asList(extMap.get("image").split(",")).contains(fileExt)) {
			setActionResult("1000", "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get("image") + "格式。");
			doJsonOut();
			return;
		}

		String uuid = java.util.UUID.randomUUID().toString();
		String newFileName = uuid + "." + fileExt;
		// 上传图片至文件服务器
		logger.info("开始上传图片至文件服务器");
		String backURL = "";
		try {
			backURL = FileUploadUtil.uploadFileBySMB(file, "upload/news/image/"+DateUtil.formatCurrent("yyyyMM"), newFileName);
			//复制一个命名规范的图片文件在本地
			File newFile = FileUtil.copy(file, file.getParent()+File.separator+newFileName, false);
			//压缩小图(按系统设置的高宽压缩)
			ImageUtil.resetSize(newFile, ConfigMgr.get("news", "small", "_small"),"upload/news/image/"+DateUtil.formatCurrent("yyyyMM"), false, false);
			//按比例压缩,压缩后及时删除本地文件
			ImageUtil.resetSize(newFile, ConfigMgr.get("news", "middle", "_middle"),"upload/news/image/"+DateUtil.formatCurrent("yyyyMM"), true);
			setActionResult("0", backURL);
			logger.info("上传图片至文件服务器成功。");
		} catch (Exception e1) {
			logger.info("上传图片至文件服务器失败。");
			e1.printStackTrace();
		}
		doJsonOut();
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	
	
	/**
	 * 跳转至新增页面
	 * @return
	 */
	public String toAdd(){
		try {
			SortedMap<String,String> map = getDictMap("newsTypeAttr");
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("map", map);
			if(!AssertUtil.isEmpty(newsType)){
				TbNewsColumnTypePO newsTypePO = this.newsTypeService.getNewsTypeByType(newsType);
				request.setAttribute("name", newsTypePO.getName());
			}
		} catch (BaseException e) {
			logger.info("跳转至新增页面失败。"+e.getErrMsg());
		} catch (Exception e) {
			logger.info("跳转至新增页面失败。"+e.getMessage());
		}
		return "add";
	}
	
	/**
	 * 跳转至修改页面
	 * @return
	 */
	public String toEdit(){
		try {
			SortedMap<String,String> map = getDictMap("newsTypeAttr");
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("map", map);
			if(!AssertUtil.isEmpty(newsType)){
				TbNewsColumnTypePO newsTypePO = this.newsTypeService.getNewsTypeByType(newsType);
				request.setAttribute("name", newsTypePO.getName());
			}
			TbNewsInfoPO newsInfoPO = newsInfoService.searchByPk(TbNewsInfoPO.class, newsInfoId);
			request.setAttribute("tbNewsInfoPO", newsInfoPO);
		} catch (BaseException e) {
			logger.info("跳转至修改页面失败。"+e.getErrMsg());
		} catch (Exception e) {
			logger.info("跳转至修改页面失败。"+e.getMessage());
		}
		return "edit";
	}
	
	
	private SortedMap<String,String> getDictMap(String fsType) throws Exception, BaseException{
		TbNewsColumnTypePO newsTypePO = newsTypeService.getNewsTypeByType(newsType);
		SortedMap<String,String> dictMap = null;
		if(!AssertUtil.isEmpty(newsTypePO) && !AssertUtil.isEmpty(newsTypePO.getAttributeId())){
			List<ExItemObj> list = DictOperater.getAllItemByType(fsType);
			if(!AssertUtil.isEmpty(list)){
				dictMap = new TreeMap<String,String>();
				
				String [] attIds =  newsTypePO.getAttributeId().split(",");
				
				ArrayList<String> ids = new ArrayList();
				Collections.addAll(ids, attIds);
				for(ExItemObj dict : list){
					if(ids.contains(dict.getFsItemCode())){
						dictMap.put(dict.getFsItemCode(), dict.getFsItemDesc());
					}
				}
			}
		}
		if(!AssertUtil.isEmpty(dictMap)){
			return mapSortByKey(dictMap);
		}
		
		return dictMap;
	}

	private static SortedMap<String, String> mapSortByKey(SortedMap<String, String> unsort_map) {
		TreeMap<String, String> result = new TreeMap<String, String>();

		Object[] unsort_key = unsort_map.keySet().toArray();
		Arrays.sort(unsort_key);

		for (int i = 0; i < unsort_key.length; i++) {
			result.put( unsort_key[i].toString(),unsort_map.get(unsort_key[i]));
		}
		return result;
		
 
	}

}
