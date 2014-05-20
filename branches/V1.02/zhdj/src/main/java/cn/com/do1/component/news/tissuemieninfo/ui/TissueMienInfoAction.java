package cn.com.do1.component.news.tissuemieninfo.ui;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.annotation.Resource;

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
import cn.com.do1.component.news.newsinfo.service.INewsInfoService;
import cn.com.do1.component.news.newsinfo.vo.NewsPreviewInfoVO;
import cn.com.do1.component.news.tissuemieninfo.model.TbTissueMienPO;
import cn.com.do1.component.news.tissuemieninfo.service.ITissueMienInfoService;
import cn.com.do1.component.pushnotification.pushnotification.util.PushNotificationUtil;
import cn.com.do1.component.pushnotification.pushnotification.vo.InfoVo;
import cn.com.do1.component.score.orgscore.model.TbOrganizationScoreInfoPO;
import cn.com.do1.component.score.scorerule.model.TbScoreRulePO;
import cn.com.do1.component.score.scorerule.service.IScoreruleService;
import cn.com.do1.component.systemmgr.org.model.OrgVO;
import cn.com.do1.component.systemmgr.org.service.IOrgService;
import cn.com.do1.component.util.FileUploadUtil;
import cn.com.do1.component.util.ImageUtil;
import cn.com.do1.dqdp.core.ConfigMgr;
import cn.com.do1.dqdp.core.DqdpAppContext;
import cn.com.do1.dqdp.core.permission.IUser;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
public class TissueMienInfoAction extends BaseAction {
	private final static transient Logger logger = LoggerFactory.getLogger(TissueMienInfoAction.class);
	@Resource
	private ITissueMienInfoService tissueMienInfoService;
	@Resource
	private INewsInfoService newsInfoService;
	@Resource
	private IScoreruleService scoreruleService;
	private TbTissueMienPO tbTissueMienPO;
	private String ids[];
	private String id;
	private File file;
	private String fileFileName;
	
    @Resource
    private IOrgService orgService;

	/**
	 * 列表查询时，页面要传递的参数
	 */
	@SearchValueTypes(nameFormat = "false", value = { @SearchValueType(name = "title", type = "string", format = "%%%s%%") })
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxSearch() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(), getPageSize());
		pager = tissueMienInfoService.searchTissueMienInfo(getSearchValue(), pager);
		addJsonPager("pageData", pager);
	}
	
	/**
	 * 保存
	 * @throws Exception
	 * @throws BaseException
	 */
	public void ajaxSave() throws Exception, BaseException {
		IUser user = (IUser)DqdpAppContext.getCurrentUser();
		OrgVO orgVO = orgService.getOrgByPersonId(user.getPersonId());
		TbTissueMienPO po = tissueMienInfoService.searchByPk(TbTissueMienPO.class, tbTissueMienPO.getId());
		if(po == null){
//			tbTissueMienPO.setId(UUID.randomUUID().toString().toLowerCase());
			tbTissueMienPO.setCreateTime(new Date());
			tbTissueMienPO.setCreateUserId(user.getPersonId());
			tbTissueMienPO.setBuyTop(0L);
			tbTissueMienPO.setWhetherRecommend(0l);
			if(tbTissueMienPO.getStatus()==1){
				tbTissueMienPO.setPushTime(new Date());
				tbTissueMienPO.setPushUserId(user.getPersonId());
			}
			this.tissueMienInfoService.insertPO(tbTissueMienPO, false);
			setActionResult("0","新增成功");
		}else{
			if(!AssertUtil.isEmpty(orgVO) && !AssertUtil.isEmpty(orgVO.getOrganizationId())){
				tbTissueMienPO.setOrganizationId(orgVO.getOrganizationId());
			}
			if(tbTissueMienPO.getStatus()==1){
				tbTissueMienPO.setPushTime(new Date());
				tbTissueMienPO.setPushUserId(user.getPersonId());
			}
			this.tissueMienInfoService.updatePO(tbTissueMienPO, false);
			setActionResult("0","修改成功");
		}
		if("1".equals(tbTissueMienPO.getStatus())){
	    	final InfoVo news=new InfoVo();
	    	news.setId(po.getId());
	    	news.setTitle(po.getTitle());
	    	news.setType("3");
	    	news.setTypeDesc("组织风采");
			PushNotificationUtil.pushNews(news);
		}
		if(null == tbTissueMienPO.getPushTime() && "1".equals(tbTissueMienPO.getStatus())){//如果之前没有发布过，第一次发布
			String scoreType = "";
			//String scoreTitle = "";
			if(0 == tbTissueMienPO.getType() || 1 == tbTissueMienPO.getType()){
				scoreType = "1";//三会一课总结上报
				//scoreTitle = "三会一课总结上报";
			}else if(2 == tbTissueMienPO.getType()){
				scoreType = "2";//支部活动总结上报
				//scoreTitle = "支部活动总结上报";
			}else if(3 == tbTissueMienPO.getType()){
				scoreType = "4";//志愿活动总结上报
				//scoreTitle = "志愿活动总结上报";
			}else if(4 == tbTissueMienPO.getType()){
				scoreType = "3";//民主生活会总结上报
				//scoreTitle = "民主生活会总结上报";
			}
			TbOrganizationScoreInfoPO orgScore = new TbOrganizationScoreInfoPO();
	    	ExItemObj dic = DictOperater.getItem("orgScore", scoreType);
			orgScore.setId(UUID.randomUUID().toString().toLowerCase());
			orgScore.setOrganizationId(tbTissueMienPO.getOrganizationId());
			orgScore.setScoreType(scoreType);
			TbScoreRulePO scoreRule = this.scoreruleService.searchByType(dic.getFsDictItemId());
			if(null != scoreRule){
				orgScore.setScore(Long.parseLong(scoreRule.getScoreValue()));
			}
			orgScore.setGetTime(new Date());
			orgScore.setNote("《"+tbTissueMienPO.getTitle()+"》获得积分");
			this.newsInfoService.insertPO(orgScore, true);
			
		}
		doJsonOut();
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
		if (tbTissueMienPO == null)
			tbTissueMienPO = new TbTissueMienPO();
		tbTissueMienPO._setPKValue(id);
		if (ids.length > 1) {
			super.ajaxBatchDelete(TbTissueMienPO.class, ids);
		} else {
			super.ajaxDelete(tbTissueMienPO);
		}
	}

	/**
	 * 详情
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxView() throws Exception, BaseException {
		TbTissueMienPO xxPO = tissueMienInfoService.searchByPk(TbTissueMienPO.class, id);
		addJsonFormateObj("tbTissueMienPO", xxPO);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
	}
	
	/**
	 * 预览详情
	 * @throws Exception
	 * @throws BaseException
	 */
	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxPreview()throws Exception, BaseException {
		NewsPreviewInfoVO perviewInfoVO = newsInfoService.getNewsPreviewInfo(id,3);
		addJsonObj("perviewInfoVO", perviewInfoVO);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
	}
	
	/**
	 * 发布或下架
	 * @throws BaseException 
	 * @throws Exception 
	 */
	public void ajaxPushOrOut() throws BaseException, Exception{
		if(AssertUtil.isEmpty(tbTissueMienPO)){
			throw new BaseException("请求参数不能为空!");
		}
		if(AssertUtil.isEmpty(tbTissueMienPO.getId())){
			throw new BaseException("非法操作!");
		}
		if(AssertUtil.isEmpty(tbTissueMienPO.getStatus())){
			throw new BaseException("非法操作!");
		}
		
		TbTissueMienPO xxPO = tissueMienInfoService.searchByPk(TbTissueMienPO.class, tbTissueMienPO.getId());
		if(!AssertUtil.isEmpty(xxPO)){
			if(tbTissueMienPO.getStatus() == 1){
				
				xxPO.setStatus(1L);
				xxPO.setPushTime(new Date());
				IUser user = (IUser)DqdpAppContext.getCurrentUser();
				if(user != null){
					xxPO.setPushUserId(user.getPersonId());
				}
				xxPO.setPushTime(new Date());
				xxPO.setPushUserId(user.getPersonId());
				setActionResult("0","发布成功");
				
		    	final InfoVo news=new InfoVo();
		    	news.setId(xxPO.getId());
		    	news.setTitle(xxPO.getTitle());
		    	news.setType("3");
		    	news.setTypeDesc("组织风采");
				PushNotificationUtil.pushNews(news);
				
			}else if(tbTissueMienPO.getStatus() == 2){
				xxPO.setStatus(2L);
				setActionResult("0","下架成功");
			}
			tissueMienInfoService.updatePO(xxPO, false);
		}
		doJsonOut();
	}
	
	/**
	 * 置顶/取消置顶
	 * @throws BaseException
	 * @throws Exception
	 */
	public void ajaxTop() throws BaseException, Exception{
		if(AssertUtil.isEmpty(tbTissueMienPO)){
			throw new BaseException("请求参数不能为空!");
		}
		if(AssertUtil.isEmpty(tbTissueMienPO.getId())){
			throw new BaseException("非法操作!");
		}
		if(AssertUtil.isEmpty(tbTissueMienPO.getBuyTop())){
			throw new BaseException("非法操作!");
		}
		TbTissueMienPO xxPO = tissueMienInfoService.searchByPk(TbTissueMienPO.class,tbTissueMienPO.getId());
		if(!AssertUtil.isEmpty(xxPO)){
			TbTissueMienPO po = new TbTissueMienPO();
			po.setId(xxPO.getId());
			if(tbTissueMienPO.getBuyTop() == 1){
				po.setBuyTop(1l);
				setActionResult("0","置顶成功");
			}else if(tbTissueMienPO.getBuyTop() == 2){
				po.setBuyTop(0l);
				setActionResult("0","取消置顶成功");
			}
			tissueMienInfoService.updatePO(po, false);
		}
		doJsonOut();
	}
	
	/**
	 * 推荐/取消推荐
	 * @throws BaseException
	 * @throws Exception
	 */
	public void ajaxRecommend() throws BaseException, Exception{
		if(AssertUtil.isEmpty(tbTissueMienPO)){
			throw new BaseException("请求参数不能为空!");
		}
		if(AssertUtil.isEmpty(tbTissueMienPO.getId())){
			throw new BaseException("非法操作!");
		}
		if(AssertUtil.isEmpty(tbTissueMienPO.getWhetherRecommend())){
			throw new BaseException("非法操作!");
		}
		TbTissueMienPO xxPO = tissueMienInfoService.searchByPk(TbTissueMienPO.class,tbTissueMienPO.getId());
		if(!AssertUtil.isEmpty(xxPO)){
			TbTissueMienPO po = new TbTissueMienPO();
			po.setId(xxPO.getId());
			if(tbTissueMienPO.getWhetherRecommend() == 1){
				po.setWhetherRecommend(1l);
				setActionResult("0","推荐成功");
			}else if(tbTissueMienPO.getWhetherRecommend() == 2){
				po.setWhetherRecommend(0l);
				setActionResult("0","取消推荐成功");
			}
			tissueMienInfoService.updatePO(po, false);
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
			backURL = FileUploadUtil.uploadFileBySMB(file, "upload/tissuemien/image/"+DateUtil.formatCurrent("yyyyMM"), newFileName);
			//复制一个命名规范的图片文件在本地
			File newFile = FileUtil.copy(file, file.getParent()+File.separator+newFileName, false);
			//压缩小图(按系统设置的高宽压缩)
			ImageUtil.resetSize(newFile, ConfigMgr.get("news", "small", "_small"),"upload/tissuemien/image/"+DateUtil.formatCurrent("yyyyMM"), false, false);
			//按比例压缩,压缩后及时删除本地文件
			ImageUtil.resetSize(newFile, ConfigMgr.get("news", "middle", "_middle"),"upload/tissuemien/image/"+DateUtil.formatCurrent("yyyyMM"), true);
			setActionResult("0", backURL);
			setActionResult("0", backURL);
			logger.info("上传图片至文件服务器成功。");
		} catch (Exception e1) {
			logger.info("上传图片至文件服务器失败。");
			e1.printStackTrace();
		}
		doJsonOut();
	}
	
	
	public TbTissueMienPO getTbTissueMienPO() {
		return tbTissueMienPO;
	}

	public void setTbTissueMienPO(TbTissueMienPO tbTissueMienPO) {
		this.tbTissueMienPO = tbTissueMienPO;
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
}
