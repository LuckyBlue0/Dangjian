package cn.com.do1.component.post.postinfo.ui;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.annotation.struts.*;
import cn.com.do1.common.exception.IllegalParameterException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.component.mobileclient.userinfo.vo.TbReplyVO;
import cn.com.do1.component.post.postinfo.model.TbPostInfoPO;
import cn.com.do1.component.post.postinfo.vo.TbPostInfoVO;
import cn.com.do1.component.post.postinfo.service.IPostinfoService;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.com.do1.common.exception.BaseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;

import cn.com.do1.common.util.AssertUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: zhanqiuxiang
*/
public class PostinfoAction extends BaseAction {
    private final static transient Logger logger = LoggerFactory.getLogger(PostinfoAction .class);
    private IPostinfoService postinfoService;
    private TbPostInfoPO tbPostInfoPO;
    private String ids[];
    private String id;
    private TbPostInfoVO postInfo;
    private List<TbReplyVO> tbReplyVOs;

    public IPostinfoService getPostinfoService() {
        return postinfoService;
    }

    @Resource
    public void setPostinfoService(IPostinfoService postinfoService) {
        this.postinfoService = postinfoService;
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

    /**
    * 列表查询时，页面要传递的参数
    */
    @SearchValueTypes(nameFormat = "false", value = { 
        @SearchValueType(name = "viewNum", type = "number"),
        @SearchValueType(name = "createTime", type = "date", format = "yyyy-MM-dd HH:mm:ss")
    })
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    @ActionRoles({"postinfoList"})
    public void ajaxSearch() throws Exception, BaseException{
            Pager pager = new Pager( ServletActionContext.getRequest(), getPageSize());
            pager=postinfoService .searchTbPostInfo(getSearchValue(),pager);
            addJsonPager("pageData",pager);
    }

    
    public void setTbPostInfoPO(TbPostInfoPO tbPostInfoPO){
        this.tbPostInfoPO=tbPostInfoPO;
    }
    
    public TbPostInfoPO getTbPostInfoPO(){
        return this.tbPostInfoPO;
    }

    @ActionRoles({"postinfoAdd"})
    public void addTbPostInfoPO() throws BaseException {
			super.ajaxAdd(tbPostInfoPO);
    }

    @ActionRoles({"postinfoUpdate"})
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "修改成功", faileMsg = "修改失败"))
    public void modifyTbPostInfoPO() throws BaseException, Exception {
        PropertyUtils.setProperty(tbPostInfoPO, tbPostInfoPO._getPKColumnName(), id);
        this.postinfoService .updatePO( tbPostInfoPO , false);
    }
    
    public void updateTbPostInfoPO(){
        super.ajaxUpdate(tbPostInfoPO);
    }

    @ActionRoles({"postinfoDel"})
    public void deleteTbPostInfoPO(){
        if(AssertUtil.isEmpty(id))
            id=ids[0];
        tbPostInfoPO._setPKValue(id);
        super.ajaxDelete(tbPostInfoPO);
    }

    @ActionRoles({"postinfoDel"})
    public void batchDeleteTbPostInfoPO(){
        super.ajaxBatchDelete(TbPostInfoPO.class,ids);
    }

    @ActionRoles({"postinfoView"})
    @JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxView() throws Exception, BaseException{
        TbPostInfoPO tbPostInfoPO = postinfoService .searchByPk(TbPostInfoPO.class, id);
        addJsonFormateObj("tbPostInfoPO", tbPostInfoPO);//注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
    }
    
    
    public String allReplyInfo() throws Exception, BaseException{
    	
    	HttpServletRequest request = ServletActionContext.getRequest();
    	
    	int pageIndex = request.getParameter("page_no") == null ? 1 : Integer.parseInt(request.getParameter("page_no"));
		int pageSize = request.getParameter("page_size") == null ? 10 : Integer.parseInt(request.getParameter("page_size"));
        if (pageSize==0) {
			pageSize = 10;
		}
        Pager pager = new Pager(ServletActionContext.getRequest(), pageSize);
        pager.setCurrentPage(pageIndex);
    	getSearchValue().put("id",id);
    	pager = postinfoService.searchTbPostInfoListQnh(getSearchValue(), pager);
    	
    	
    	tbPostInfoPO = postinfoService.searchByPk(TbPostInfoPO.class,id);
    	if(tbPostInfoPO != null){
    		tbPostInfoPO.setViewNum(tbPostInfoPO.getViewNum() + 1 );
        	this.postinfoService.updatePO(tbPostInfoPO,false);
    	}
    	
    	if(pager.getPageData() != null){
    		tbReplyVOs = (ArrayList)pager.getPageData();
    	}
//    	addJsonFormateObj("tbPostInfoPO",tbPostInfoPO);
//    	addJsonArray("list", (List) (pager.getPageData() == null ? new ArrayList() : pager.getPageData()));
//	    addJsonObj("page_no", pager.getCurrentPage());
//	    addJsonObj("page_count", pager.getTotalPages());
//	    addJsonObj("totalRows", pager.getTotalRows());
//    	
//        addJsonFormateObj("tbPostInfoPO", tbPostInfoPO);//注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
    	
    	return "success";
    }

	public List<TbReplyVO> getTbReplyVOs() {
		return tbReplyVOs;
	}

	public void setTbReplyVOs(List<TbReplyVO> tbReplyVOs) {
		this.tbReplyVOs = tbReplyVOs;
	}

	public TbPostInfoVO getPostInfo() {
		return postInfo;
	}

	public void setPostInfo(TbPostInfoVO postInfo) {
		this.postInfo = postInfo;
	}

	 

	
    
}
