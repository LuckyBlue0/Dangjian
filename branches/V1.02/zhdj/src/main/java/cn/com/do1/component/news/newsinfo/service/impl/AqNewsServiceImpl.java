package cn.com.do1.component.news.newsinfo.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.common.util.DateUtil;
import cn.com.do1.component.basis.newstype.dao.INewsTypeDAO;
import cn.com.do1.component.basis.newstype.model.TbNewsColumnTypePO;
import cn.com.do1.component.news.newsinfo.dao.INewsInfoDAO;
import cn.com.do1.component.news.newsinfo.model.TbNewsInfoPO;
import cn.com.do1.component.news.newsinfo.service.IAqNewsService;
import cn.com.do1.component.news.newsinfo.vo.AqNewsListVO;
import cn.com.do1.component.news.newsinfo.vo.AqNewsVO;
import cn.com.do1.component.news.newsinfo.vo.NewsIndex;
import cn.com.do1.component.util.FileUploadUtil;
import cn.com.do1.component.util.HttpUtil;
import cn.com.do1.component.util.ImageUtil;
import cn.com.do1.component.util.NewsConfig;
import cn.com.do1.component.util.PropertyUtil;
import cn.com.do1.dqdp.core.ConfigMgr;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
@Service("aqNewsService")
public class AqNewsServiceImpl extends BaseService implements IAqNewsService{
	private final static transient Logger log = LoggerFactory.getLogger(AqNewsServiceImpl.class);
	@Resource
    private INewsInfoDAO newsInfoDAO;
    @Resource
    private INewsTypeDAO newsTypeDAO;
    /**请求成功状态码 200*/
    private static String SUCCESSFUL_STATUS_CODE = "200";
	/**数据抓取接口地址*/
	public static final String INTERFACE_URL = "INTERFACEURL";
	/**数据抓取接口地址配置文件*/
	public static final String CONFIG_BASE_PATH = "/META-INF/config/aqnews.properties";
	/**接口地址map*/
    private static HashMap<String,String>propertyMap;
    static{
    	init();
    }
    
    public static void init(){
    	if(propertyMap == null){
       		propertyMap = new HashMap<String, String>();
    	}
    	new PropertyUtil(CONFIG_BASE_PATH,propertyMap);
    }
	@Override
	public void process() throws Exception, BaseException {
		if(propertyMap.size()==0){
			init();
		}
		if(!isConnection()){
			return;
		}
		int count = 0;
		//获取一级菜单(配置文件在 /META-INF/config/news.xml)
		List<NewsIndex> newsIndexs = NewsConfig.getNewsIndexList();
		if(!AssertUtil.isEmpty(newsIndexs)){
			for(NewsIndex n : newsIndexs){
				if(n.getGetContentUrl() == null){
					continue;
				}
				//根据一级菜单获取二级菜单列表
				List<TbNewsColumnTypePO> childrenMenuList = getChildrenMenus(n.getTitle());
				if(AssertUtil.isEmpty(childrenMenuList)){
					continue;
				}
				
				List<TbNewsInfoPO> newsInfoList = new ArrayList<TbNewsInfoPO>();
				HashMap<String,String>cfMap=new HashMap<String, String>(); 
				//循环每个二级新闻菜单,并新增新闻
				for(int i = 0 ;i < childrenMenuList.size();i++){
					TbNewsColumnTypePO menu = childrenMenuList.get(i);
					String listJson = HttpUtil.get(n.getGetContentUrl()+"&type="+i);
					String contentJson = null;
					Map<String, Class> map = new HashMap<String, Class>();
					map.put("allContent", AqNewsVO.class);
					AqNewsListVO aqNewsInfoList = null;
					try {
						aqNewsInfoList = (AqNewsListVO) JSONObject.toBean(JSONObject.fromObject(listJson), AqNewsListVO.class, map);
					} catch (Exception e1) {
						e1.printStackTrace();
						log.error("新闻列表json数据转换成po时出现异常,error>>"+e1);
						continue;
					}
					if (SUCCESSFUL_STATUS_CODE.equals(aqNewsInfoList.getResultCode())) {
						for (AqNewsVO newsVO : aqNewsInfoList.getAllContent()) {
							//二级菜单的类型,i已经是在查询数据库时排好序的,跟我们定义的一致
							if(AssertUtil.isEmpty(propertyMap.get(menu.getName())) || newsVO.getUrl() == null){
								continue;
							}
							contentJson = HttpUtil.get(propertyMap.get(menu.getName())+"&url="+newsVO.getUrl());
							Map<String, Class> detailMap = new HashMap<String, Class>();
							detailMap.put("content", AqNewsVO.class);
							AqNewsVO vo = null;
							try {
								vo = (AqNewsVO) JSONObject.toBean(JSONObject.fromObject(contentJson), AqNewsVO.class, detailMap);
							} catch (Exception e) {
								e.printStackTrace();
								log.error("新闻详情json数据转换成po时出现异常,contentJson=="+contentJson+",error>>"+e);
								continue;
							}
							if (AssertUtil.isEmpty(vo))
								continue;
							TbNewsInfoPO po = newsInfoDAO.searchByPk(TbNewsInfoPO.class, newsVO.getId());
							String imageUrl = "";
							if (po == null) {
								//news 对象是从列表拿的数据,vo 对象是详情拿的
								String pushTime = null;
								if(newsVO.getPushTime() != null){
									pushTime = newsVO.getPushTime();
								}else{
									pushTime = DateUtil.formartCurrentDate();
								}
								po = new TbNewsInfoPO();
								po.setNewsInfoId(newsVO.getId());
								po.setTitle(newsVO.getTitle());
								po.setContent(vo.getContent());
								po.setPushTime(pushTime);
								po.setSource(vo.getResource());
								if (!AssertUtil.isEmpty(vo.getImageUrl())) {
									imageUrl = fileUpload(vo.getImageUrl());
									if (!AssertUtil.isEmpty(imageUrl)) {
										po.setImgPath(imageUrl);
									}
								}
								po.setStatus(1);
								po.setCreateUserId("admin");
								po.setCreateTime(new Date());
								po.setLastModifyTime(pushTime);
								po.setNewsInfoType(menu.getType().toString());
								po.setNewsInfoTypeId(menu.getNewsTypeId());
								
								if(cfMap.containsKey(newsVO.getId())){
									log.warn("重复数据："+newsVO.getId()+" == "+ newsVO.getTitle());
								}else{
									newsInfoList.add(po);
									count++;
								}
								cfMap.put(newsVO.getId(), newsVO.getTitle());
								if(newsInfoList.size() == 200){
									newsInfoDAO.execBatchInsert(newsInfoList);
									newsInfoList.clear();
								}
								
							}
						}
					}
				}
				if(newsInfoList.size()>0){
					newsInfoDAO.execBatchInsert(newsInfoList);
				}
				log.info("插入安庆党建新闻信息,数量==" + count);
			}
		}
	}
	
	private List<TbNewsColumnTypePO>getChildrenMenus(String typeName)throws Exception{
		List<TbNewsColumnTypePO> childrenMenuList = newsTypeDAO.getChildrenTypesByParentTypeName(typeName);
		return childrenMenuList;
	}
	


	
	public static String fileUpload(String fileUrl) {
		String backURL = "";
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		// 检查扩展名
		String fileExt = fileUrl.substring(fileUrl.lastIndexOf(".") + 1).toLowerCase();

		if (!Arrays.<String> asList(extMap.get("image").split(",")).contains(fileExt)) {
			log.warn("1000", "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get("image") + "格式。");
			return backURL;
		}
		
		String uuid = java.util.UUID.randomUUID().toString();
		String newFileName = uuid + "." + fileExt;
		// 上传图片至文件服务器
		log.info("开始上传图片至文件服务器");
		try {
			String savePath = "C://aqdj//upload//";
			//下载文件到本地
			File tempFile = ImageUtil.download(fileUrl,newFileName,savePath);
			File f = new File(tempFile+"\\"+newFileName);
			backURL = FileUploadUtil.uploadFileBySMB(f, "upload/news/image/"+DateUtil.formatCurrent("yyyyMM"), newFileName);
			//压缩小图(按系统设置的高宽压缩)
			ImageUtil.resetSize(f, ConfigMgr.get("news", "small", "_small"),"upload/news/image/"+DateUtil.formatCurrent("yyyyMM"), false, false);
			//按比例压缩,压缩后及时删除本地文件
			ImageUtil.resetSize(f, ConfigMgr.get("news", "middle", "_middle"),"upload/news/image/"+DateUtil.formatCurrent("yyyyMM"), true);
			log.info("上传图片至文件服务器成功。");
		} catch (Exception e1) {
			log.info("上传图片至文件服务器失败。");
			e1.printStackTrace();
		}
		return backURL;
	}
	
	private static boolean isConnection(){
		boolean flag = true;
			try {
				// 构造URL
				URL url = new URL(propertyMap.get(INTERFACE_URL));
				
				// 打开连接
				URLConnection con = url.openConnection();
				
				HttpURLConnection httpUrlConnection = (HttpURLConnection) con;  
				//设置请求超时为5s  
				httpUrlConnection.setConnectTimeout(3*1000);
				
				if(httpUrlConnection.getResponseCode() != 200){
					flag = false;
				}
			} catch (MalformedURLException e) {
				flag = false;
			} catch (IOException e) {
				flag = false;
			}
			if(!flag){
				log.warn("接口调用失败.....,请求地址"+propertyMap.get(INTERFACE_URL));
			}
		return flag;
	}
	
	public static void main(String[] args) {
			fileUpload("http://aq.ahxf.gov.cn/upload/2013/5/2716657558.jpg");
	}
	

	
}
