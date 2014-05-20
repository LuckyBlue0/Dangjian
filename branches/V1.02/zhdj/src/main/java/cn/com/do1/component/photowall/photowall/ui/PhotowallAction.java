package cn.com.do1.component.photowall.photowall.ui;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.struts.ActionRoles;
import cn.com.do1.common.annotation.struts.CatchException;
import cn.com.do1.common.annotation.struts.JSONOut;
import cn.com.do1.common.annotation.struts.SearchValueType;
import cn.com.do1.common.annotation.struts.SearchValueTypes;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.photowall.photowall.model.TbPhotoWallPO;
import cn.com.do1.component.photowall.photowall.service.IPhotowallService;
import cn.com.do1.component.photowall.photowall.vo.TbPhotoWallReviewVO;
import cn.com.do1.component.photowall.photowall.vo.TbPhotoWallVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: tanshaoqi
*/
public class PhotowallAction extends BaseAction {
    private final static transient Logger logger = LoggerFactory.getLogger(PhotowallAction .class);
    private IPhotowallService photowallService;
    private TbPhotoWallPO tbPhotoWallPO;
    private String ids[];
    private String id;
    private ArrayList<TbPhotoWallReviewVO> reviewList;
    private TbPhotoWallVO photowall;
    public IPhotowallService getPhotowallService() {
        return photowallService;
    }

    @Resource
    public void setPhotowallService(IPhotowallService photowallService) {
        this.photowallService = photowallService;
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
    @SearchValueTypes(nameFormat = "false", value = { @SearchValueType(name = "title", type = "string", format = "%%%s%%") })
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    @ActionRoles({"photowallList"})
    public void ajaxSearch() throws Exception, BaseException{
            Pager pager = new Pager( ServletActionContext.getRequest(), getPageSize());
            pager=photowallService .searchTbPhotoWall(getSearchValue(),pager);
            addJsonPager("pageData",pager);
    }
    
    /**
     * 启用/禁用
     * @throws BaseException
     */
    @ActionRoles({"photowallEnable"})
    public void updateEnableOrDisable() throws BaseException{
    	if(!AssertUtil.isEmpty(tbPhotoWallPO) && !AssertUtil.isEmpty(tbPhotoWallPO.getId()) && !AssertUtil.isEmpty(tbPhotoWallPO.getStatus())){
    		String desc = tbPhotoWallPO.getStatus().equals("0") ? "启用成功":"禁用成功";
    		try {
				this.photowallService.updatePO(tbPhotoWallPO, false);
				setActionResult("0", desc);
			} catch (SQLException e) {
				desc = desc+e;
				setActionResult("-1", desc);
				logger.error("留影墙"+desc+"失败,error=="+e);
			}finally{
				doJsonOut();
			}
    	}else{
    		throw new BaseException("1001", "操作失败,请求数据为空!");
    	}
    }
    
    /**
     * 留影墙详情
     * @return
     */
    public String photowallView(){
    	try {
    		photowall = this.photowallService.findPhotoWallVOById(id);
    		reviewList = this.photowallService.findPhotowallListById(id);
		} catch (Exception e) {
			logger.error("留影墙详情 >>"+e);
		} catch (BaseException e) {
			logger.error("留影墙详情 >>"+e);
		}
    	return "view";
    }
    
    public void setTbPhotoWallPO(TbPhotoWallPO tbPhotoWallPO){
        this.tbPhotoWallPO=tbPhotoWallPO;
    }
    
    public TbPhotoWallPO getTbPhotoWallPO(){
        return this.tbPhotoWallPO;
    }

    @ActionRoles({"photowallAdd"})
    public void addTbPhotoWallPO() throws BaseException {
			super.ajaxAdd(tbPhotoWallPO);
    }

    @ActionRoles({"photowallUpdate"})
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "修改成功", faileMsg = "修改失败"))
    public void modifyTbPhotoWallPO() throws BaseException, Exception {
        PropertyUtils.setProperty(tbPhotoWallPO, tbPhotoWallPO._getPKColumnName(), id);
        this.photowallService .updatePO( tbPhotoWallPO , false);
    }
    
    public void updateTbPhotoWallPO(){
        super.ajaxUpdate(tbPhotoWallPO);
    }

    @ActionRoles({"photowallDel"})
    public void deleteTbPhotoWallPO(){
        if(AssertUtil.isEmpty(id))
            id=ids[0];
        tbPhotoWallPO._setPKValue(id);
        super.ajaxDelete(tbPhotoWallPO);
    }

    @ActionRoles({"photowallDel"})
    public void batchDeleteTbPhotoWallPO(){
        super.ajaxBatchDelete(TbPhotoWallPO.class,ids);
    }

    @ActionRoles({"photowallView"})
    @JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxView() throws Exception, BaseException{
        TbPhotoWallPO tbPhotoWallPO = photowallService .searchByPk(TbPhotoWallPO.class, id);
        addJsonFormateObj("tbPhotoWallPO", tbPhotoWallPO);//注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
    }

	public ArrayList<TbPhotoWallReviewVO> getReviewList() {
		return reviewList;
	}

	public void setReviewList(ArrayList<TbPhotoWallReviewVO> reviewList) {
		this.reviewList = reviewList;
	}

	public TbPhotoWallVO getPhotowall() {
		return photowall;
	}

	public void setPhotowall(TbPhotoWallVO photowall) {
		this.photowall = photowall;
	}
}
