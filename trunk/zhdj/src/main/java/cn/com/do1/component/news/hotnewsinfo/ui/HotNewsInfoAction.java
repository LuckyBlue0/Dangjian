package cn.com.do1.component.news.hotnewsinfo.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import jcifs.smb.SmbFile;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.struts.CatchException;
import cn.com.do1.common.annotation.struts.JSONOut;
import cn.com.do1.common.annotation.struts.SearchValueType;
import cn.com.do1.common.annotation.struts.SearchValueTypes;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.common.util.DateUtil;
import cn.com.do1.common.util.FileUtil;
import cn.com.do1.component.news.hotnewsinfo.model.TbHotNewsPO;
import cn.com.do1.component.news.hotnewsinfo.service.IHotNewsInfoService;
import cn.com.do1.component.news.hotnewsinfo.vo.HotNewsImgVO;
import cn.com.do1.component.news.hotnewsinfo.vo.ImgVO;
import cn.com.do1.component.news.newsinfo.service.INewsInfoService;
import cn.com.do1.component.news.newsinfo.vo.NewsPreviewInfoVO;
import cn.com.do1.component.pushnotification.pushnotification.util.PushNotificationUtil;
import cn.com.do1.component.pushnotification.pushnotification.vo.InfoVo;
import cn.com.do1.component.util.FileUploadUtil;
import cn.com.do1.component.util.ImageUtil;
import cn.com.do1.component.util.SmbFileUtils;
import cn.com.do1.dqdp.core.ConfigMgr;
import cn.com.do1.dqdp.core.DqdpAppContext;
import cn.com.do1.dqdp.core.permission.IUser;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class HotNewsInfoAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory.getLogger(HotNewsInfoAction.class);
	@Resource
	private IHotNewsInfoService hotNewsInfoService;
	@Resource
	private INewsInfoService newsInfoService;
	private TbHotNewsPO tbHotNewsPO;
	private String ids[];
	private String id;
	private File file;
	private String fileFileName;
	/**
	 * 列表查询时，页面要传递的参数
	 */
	@SearchValueTypes(nameFormat = "false", value = { @SearchValueType(name = "title", type = "string", format = "%%%s%%") })
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxSearch() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(), getPageSize());
		pager = hotNewsInfoService.searchHotNewsInfo(getSearchValue(), pager);
		addJsonPager("pageData", pager);
	}

	/**
	 * 新增或修改
	 * 
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1002", successMsg = "保存成功", faileMsg = "保存失败"))
	public void ajaxSave() throws Exception, BaseException {
		// todo:完成新增的代码;
		hotNewsInfoService.saveHotNewsInfo(tbHotNewsPO);
	}


	/**
	 * 删除
	 */
	@JSONOut(catchException = @CatchException(errCode = "1004", successMsg = "删除成功", faileMsg = "删除失败"))
	public void ajaxBatchDelete() throws Exception, BaseException {
		// 完成批量更新的代码
		deleteTbHotNewsInfoPO();
	}

	private void deleteTbHotNewsInfoPO() {
		if (AssertUtil.isEmpty(id))
			id = ids[0];
		if (tbHotNewsPO == null)
			tbHotNewsPO = new TbHotNewsPO();
		tbHotNewsPO._setPKValue(id);
		if (ids.length > 1) {
			super.ajaxBatchDelete(TbHotNewsPO.class, ids);
		} else {
			super.ajaxDelete(tbHotNewsPO);
		}
	}



	/**
	 * 查看详情
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxView() throws Exception, BaseException {
		TbHotNewsPO xxPO = hotNewsInfoService.searchByPk(TbHotNewsPO.class, id);
		addJsonFormateObj("tbHotNewsPO", xxPO);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
	}

	/**
	 * 预览详情
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxPreview()throws Exception, BaseException {
		NewsPreviewInfoVO perviewInfoVO = newsInfoService.getNewsPreviewInfo(id,0);
		addJsonObj("perviewInfoVO", perviewInfoVO);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
	}
	/**
	 * 取得文件服务器上的封面图片
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxGetRemotePic()throws Exception, BaseException {
		String dir = ConfigMgr.get("news", "dir", "upload/system/image/");// 从配置管理拿到文件服务器存放封面图片的路径
		SmbFileUtils smbUtils = new SmbFileUtils(dir);
		SmbFile smbFile = smbUtils.getSmbFile();
		SmbFile[] fs = smbFile.listFiles();
		List<String> list = new ArrayList<String>();
		if (fs.length != 0) {
			for (SmbFile file : fs) {
				String fileName = "upload/system/image/" + file.getName();
				list.add(fileName);
			}
		}
		List<List<ImgVO>> result = new ArrayList<List<ImgVO>>();
		int totalSize = list.size();
		int pageSize = 10;
		List<ImgVO> ls = null;
		int m = (totalSize / pageSize)+(totalSize % 10 > 0 ? 1:0);
		
		int k = 0;
		for(int i=0;i<m;i++){
			ls = new ArrayList<ImgVO>();
			for(int j=0;j<pageSize;j++){
				if(k==totalSize){
					break;
				}
				ImgVO vo=new ImgVO();
				vo.setImgPath(list.get(k));
				ls.add(vo);
				k++;
			}
			result.add(ls);
		}
		List<HotNewsImgVO> listvo=new ArrayList<HotNewsImgVO>();
        for(List<ImgVO> list1:result){
        	HotNewsImgVO vo=new HotNewsImgVO();
        	vo.setImgPath(list1);
        	listvo.add(vo);
        }
		addJsonArray("list", listvo);
	}
	
	/**
	 * 发布或下架
	 * @throws BaseException 
	 * @throws Exception 
	 */
	public void ajaxPushOrOut() throws BaseException, Exception{
		if(AssertUtil.isEmpty(tbHotNewsPO)){
			throw new BaseException("请求参数不能为空!");
		}
		if(AssertUtil.isEmpty(tbHotNewsPO.getId())){
			throw new BaseException("非法操作!");
		}
		if(AssertUtil.isEmpty(tbHotNewsPO.getStatus())){
			throw new BaseException("非法操作!");
		}
		
		TbHotNewsPO xxPO = hotNewsInfoService.searchByPk(TbHotNewsPO.class, tbHotNewsPO.getId());
		if(!AssertUtil.isEmpty(xxPO)){
			TbHotNewsPO po = new TbHotNewsPO();
			po.setId(xxPO.getId());
			if(tbHotNewsPO.getStatus() == 1){
				po.setStatus(1L);
				IUser user = (IUser)DqdpAppContext.getCurrentUser();
				if(user != null){
					if(!AssertUtil.isEmpty(po.getStatus()) && po.getStatus() == 1){
						po.setPushTime(new Date());
						po.setPushUserId(user.getPersonId());
					}
				}
				setActionResult("0","发布成功");
		    	final InfoVo news=new InfoVo();
		    	news.setId(xxPO.getId());
		    	news.setTitle(xxPO.getTitle());
		    	news.setType("0");
		    	news.setTypeDesc("热点新闻");
				PushNotificationUtil.pushNews(news);
			}else if(tbHotNewsPO.getStatus() == 2){
				po.setStatus(2L);
				setActionResult("0","下架成功");
			}
			hotNewsInfoService.updatePO(po, false);
		}
		doJsonOut();
	}
	
	/**
	 * 置顶
	 * @throws BaseException
	 * @throws Exception
	 */
	public void ajaxTop() throws BaseException, Exception{
		if(AssertUtil.isEmpty(tbHotNewsPO)){
			throw new BaseException("请求参数不能为空!");
		}
		if(AssertUtil.isEmpty(tbHotNewsPO.getId())){
			throw new BaseException("非法操作!");
		}
		if(AssertUtil.isEmpty(tbHotNewsPO.getBuyTop())){
			throw new BaseException("非法操作!");
		}
		TbHotNewsPO xxPO = hotNewsInfoService.searchByPk(TbHotNewsPO.class, tbHotNewsPO.getId());
		if(!AssertUtil.isEmpty(xxPO)){
			TbHotNewsPO po = new TbHotNewsPO();
			po.setId(xxPO.getId());
			if(tbHotNewsPO.getBuyTop() == 1){
				po.setBuyTop(1l);
				setActionResult("0","置顶成功");
			}else if(tbHotNewsPO.getBuyTop() == 2){
				po.setBuyTop(0l);
				setActionResult("0","取消置顶成功");
			}
			hotNewsInfoService.updatePO(po, false);
		}
		doJsonOut();
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
			backURL = FileUploadUtil.uploadFileBySMB(file, "upload/hotnews/image/"+DateUtil.formatCurrent("yyyyMM"), newFileName);
			//复制一个命名规范的图片文件在本地
			File newFile = FileUtil.copy(file, file.getParent()+File.separator+newFileName, false);
			//压缩小图(按系统设置的高宽压缩)
			ImageUtil.resetSize(newFile, ConfigMgr.get("news", "small", "_small"),"upload/hotnews/image/"+DateUtil.formatCurrent("yyyyMM"), false, false);
			//按比例压缩,压缩后及时删除本地文件
			ImageUtil.resetSize(newFile, ConfigMgr.get("news", "middle", "_middle"),"upload/hotnews/image/"+DateUtil.formatCurrent("yyyyMM"), true);
			setActionResult("0", backURL);
			logger.info("上传图片至文件服务器成功。");
		} catch (Exception e1) {
			logger.info("上传图片至文件服务器失败。");
			e1.printStackTrace();
		}
		doJsonOut();
	}
	public TbHotNewsPO getTbHotNewsPO() {
		return this.tbHotNewsPO;
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

	public IHotNewsInfoService getHotNewsInfoService() {
		return hotNewsInfoService;
	}

	
	public void setHotNewsInfoService(IHotNewsInfoService hotNewsInfoService) {
		this.hotNewsInfoService = hotNewsInfoService;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTbHotNewsPO(TbHotNewsPO tbHotNewsPO) {
		this.tbHotNewsPO = tbHotNewsPO;
	}

	public TbHotNewsPO setTbHotNewsPO() {
		return this.tbHotNewsPO;
	}
}
