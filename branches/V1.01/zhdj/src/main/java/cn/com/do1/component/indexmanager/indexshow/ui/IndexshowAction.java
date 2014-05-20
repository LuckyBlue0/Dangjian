package cn.com.do1.component.indexmanager.indexshow.ui;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.annotation.struts.*;
import cn.com.do1.common.exception.IllegalParameterException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.basis.partymenber.vo.PartyMenberInfoVO;
import cn.com.do1.component.indexmanager.indexshow.model.TbProjectInfoPO;
import cn.com.do1.component.indexmanager.indexshow.vo.TbProjectInfoVO;
import cn.com.do1.component.indexmanager.indexshow.service.IIndexshowService;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import cn.com.do1.common.exception.BaseException;
import javax.annotation.Resource;
import java.sql.SQLException;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.common.util.reflation.BeanHelper;
import cn.com.do1.common.util.string.StringUtil;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: zhanqiuxiang
*/
public class IndexshowAction extends BaseAction {
    private final static transient Logger logger = LoggerFactory.getLogger(IndexshowAction .class);
    private IIndexshowService indexshowService;
    private TbProjectInfoPO tbProjectInfoPO;
	private TbProjectInfoVO tbProjectInfoVO;
    private String ids[];
    private String id;
    public TbProjectInfoVO getTbProjectInfoVO() {
		return tbProjectInfoVO;
	}

	public void setTbProjectInfoVO(TbProjectInfoVO tbProjectInfoVO) {
		this.tbProjectInfoVO = tbProjectInfoVO;
	}

    public IIndexshowService getIndexshowService() {
        return indexshowService;
    }

    @Resource
    public void setIndexshowService(IIndexshowService indexshowService) {
        this.indexshowService = indexshowService;
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
        @SearchValueType(name = "proDate", type = "date", format = "yyyy-MM-dd HH:mm:ss"),
        @SearchValueType(name = "createTime", type = "date", format = "yyyy-MM-dd HH:mm:ss")
    })
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))

    public void ajaxSearch() throws Exception, BaseException{
            Pager pager = new Pager( ServletActionContext.getRequest(), getPageSize());
            pager=indexshowService .searchTbProjectInfo(getSearchValue(),pager);
            addJsonPager("pageData",pager);
    }

    @JSONOut(catchException = @CatchException(errCode = "1003", successMsg = "操作成功", faileMsg = "更新失败"))
	public void ajaxChangeState() throws Exception, BaseException {
		try {
			if (null != tbProjectInfoPO
					&& !StringUtil.isNullEmpty(tbProjectInfoPO.getId())) {
				String msg = this.indexshowService
						.updateUserInfo(tbProjectInfoPO);
				if (StringUtil.isNullEmpty(msg)) {
					setActionResult("0", "操作成功");
				} else {
					setActionResult("1000", "操作失败," + msg);
				}
			} else {
				setActionResult("1000", "操作失败,丢失参数!");
			}
		} catch (Exception e) {
			setActionResult("1001", "操作失败," + e.getMessage());
			logger.error(e.getMessage(), e);
		} finally {
			doJsonOut();
		}
	}
    
    @JSONOut(catchException = @CatchException(errCode = "1004", successMsg = "删除成功", faileMsg = "删除失败"))
	public void ajaxBatchDelete() throws Exception, BaseException {
		if (AssertUtil.isEmpty(id))
			id = ids[0];
		if (tbProjectInfoPO == null)
			tbProjectInfoPO = new TbProjectInfoPO();
		tbProjectInfoPO._setPKValue(id);
		if (ids.length > 1) {
			super.ajaxBatchDelete(TbProjectInfoPO.class, ids);
		} else {
			super.ajaxDelete(tbProjectInfoPO);
		}
	}
    public void setTbProjectInfoPO(TbProjectInfoPO tbProjectInfoPO){
        this.tbProjectInfoPO=tbProjectInfoPO;
    }
    
    public TbProjectInfoPO getTbProjectInfoPO(){
        return this.tbProjectInfoPO;
    }


    public void addTbProjectInfoPO() throws BaseException {
    	tbProjectInfoPO.setId(UUID.randomUUID().toString());
    	tbProjectInfoPO.setCreateTime(new Date());
    	tbProjectInfoPO.setStatus("1");
			super.ajaxAdd(tbProjectInfoPO);
    }


    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "修改成功", faileMsg = "修改失败"))
    public void modifyTbProjectInfoPO() throws BaseException, Exception {
    	try {
			tbProjectInfoPO =new TbProjectInfoPO();
		/*	PropertyUtils.setProperty(tbProjectInfoPO, tbProjectInfoPO._getPKColumnName(), id);*/
			BeanHelper.copyFormatProperties(tbProjectInfoPO,tbProjectInfoVO,true);
			tbProjectInfoPO.setCreateTime(new Date());
			indexshowService.updatePO(tbProjectInfoPO,false);
		} catch (Exception e) {
			String msg = "修改时设置ID失败 error:"+e.getMessage();
			logger.error(msg, e);
			throw new BaseException(msg, e);
		}			
    }
    
    public void updateTbProjectInfoPO(){
        super.ajaxUpdate(tbProjectInfoPO);
    }

 
    public void deleteTbProjectInfoPO(){
        if(AssertUtil.isEmpty(id))
            id=ids[0];
        tbProjectInfoPO._setPKValue(id);
        super.ajaxDelete(tbProjectInfoPO);
    }


    public void batchDeleteTbProjectInfoPO(){
        super.ajaxBatchDelete(TbProjectInfoPO.class,ids);
    }


    
    @JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
	public void ajaxView() throws Exception, BaseException {
    	TbProjectInfoVO tbProjectInfoVO = indexshowService
				.getInfoById(id);
		addJsonObj("tbProjectInfoVO", tbProjectInfoVO);// 注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
	}
    
}
