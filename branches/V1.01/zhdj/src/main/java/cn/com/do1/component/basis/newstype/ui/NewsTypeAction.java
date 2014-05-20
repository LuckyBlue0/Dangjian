package cn.com.do1.component.basis.newstype.ui;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
import cn.com.do1.component.basis.newstype.model.TbNewsColumnTypePO;
import cn.com.do1.component.basis.newstype.service.INewsTypeService;
import cn.com.do1.component.news.newsinfo.model.TbNewsInfoPO;
import cn.com.do1.component.news.newsinfo.service.INewsInfoService;

/**
* Copyright &copy; 2013 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class NewsTypeAction extends BaseAction {
    private final static transient Logger logger = LoggerFactory.getLogger(NewsTypeAction.class);
    private INewsTypeService newsTypeService;
    private TbNewsColumnTypePO tbNewsColumnTypePO;
    private String ids[];
    private String newsTypeId;

    public INewsTypeService getNewsTypeService() {
        return newsTypeService;
    }
    @Resource
    private INewsInfoService newsInfoService;
    
    @Resource
    public void setNewsTypeService(INewsTypeService newsTypeService) {
        this.newsTypeService = newsTypeService;
    }
	private String newsType;
	
	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getNewsTypeId() {
		return newsTypeId;
	}

	public void setNewsTypeId(String newsTypeId) {
		this.newsTypeId = newsTypeId;
	}

	/**
	 * 列表查询时，页面要传递的参数
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	@SearchValueTypes(nameFormat = "false", value = { @SearchValueType(name = "name", type = "string", format = "%%%s%%") })
	public void ajaxSearch() throws Exception, BaseException {
		Pager pager = new Pager(ServletActionContext.getRequest(), getPageSize());
		pager = newsTypeService.searchNewsType(getSearchValue(), pager);
		addJsonPager("pageData", pager);
	}

	public void ajaxSave()  {
		// todo:完成新增的代码;
		try {
			newsTypeService.saveNewsTypePO(tbNewsColumnTypePO);
			 setActionResult("0","保存成功");
		} catch (Exception e) {
			 setActionResult("1001","保存失败,"+e.getMessage());
			 logger.error(e.getMessage(),e);
		}finally{
			doJsonOut();
		}
	}
	
	
	/**
	 * 获取所有新闻父菜单
	 * @throws Exception
	 */
	@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxNewsTypeList() throws Exception{
		List<TbNewsColumnTypePO> newsTypes = newsTypeService.getNewsType();
		List<TbNewsColumnTypePO> result = new ArrayList<TbNewsColumnTypePO>();
		for(TbNewsColumnTypePO p : newsTypes){
			if(p.getParentId() == null){
				result.add(p);
			}
		}
		addJsonArray("newsTypeList", result);
	}

	@JSONOut(catchException = @CatchException(errCode = "1003", successMsg = "更新成功", faileMsg = "更新失败"))
	public void ajaxUpdate() throws Exception, BaseException {
		// todo:完成更新的代码;
	}

	@JSONOut(catchException = @CatchException(errCode = "1004", successMsg = "删除成功", faileMsg = "删除失败"))
	public void ajaxBatchDelete() throws Exception, BaseException {
		// 完成批量删除的代码
		deletetbNewsColumnTypePO();
	}
	
   public void batchDeletetbNewsColumnTypePO(){
       
   }


   public TbNewsColumnTypePO getTbNewsColumnTypePO() {
		return tbNewsColumnTypePO;
	}

	public void setTbNewsColumnTypePO(TbNewsColumnTypePO tbNewsColumnTypePO) {
		this.tbNewsColumnTypePO = tbNewsColumnTypePO;
	}

	public void addtbNewsColumnTypePO() {
		super.ajaxAdd(tbNewsColumnTypePO);
	}
   public void updatetbNewsColumnTypePO(){
       super.ajaxUpdate(tbNewsColumnTypePO);
   }
   public void deletetbNewsColumnTypePO() throws Exception, BaseException{
       if(AssertUtil.isEmpty(newsTypeId))
    	   newsTypeId=ids[0];
       if(tbNewsColumnTypePO == null)
    	   tbNewsColumnTypePO = new TbNewsColumnTypePO();
       tbNewsColumnTypePO._setPKValue(newsTypeId);
       
       
       if(true){
    	   boolean flag = false;
    	   for(String id : ids){
    		   TbNewsColumnTypePO po = this.newsTypeService.searchByPk(TbNewsColumnTypePO.class,id);
    		   TbNewsInfoPO newsInfo = this.newsInfoService.getNewsInfoByType(po.getType().toString());
    		   if(!AssertUtil.isEmpty(newsInfo)){
    			   flag = true;
    			   break;
    		   }
    	   }
		   if(flag){
			   setActionResult("1001","删除失败,请先删除新闻再删除新闻类型!");
			   doJsonOut();
		   }else{
		       if(ids.length > 1){
		    	   super.ajaxBatchDelete(TbNewsColumnTypePO.class,ids);
		       }else{
		    	   super.ajaxDelete(tbNewsColumnTypePO);
		       }
		   }
       }
   }


	@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxView() throws Exception, BaseException {
		TbNewsColumnTypePO xxPO = newsTypeService.searchByPk(TbNewsColumnTypePO.class, newsTypeId);
		addJsonFormateObj("tbNewsColumnTypePO", xxPO);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
	}

	public TbNewsColumnTypePO gettbNewsColumnTypePO() {
		return this.tbNewsColumnTypePO;
	}
}
