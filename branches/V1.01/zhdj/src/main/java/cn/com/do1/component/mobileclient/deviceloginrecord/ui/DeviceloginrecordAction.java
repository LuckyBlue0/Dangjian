package cn.com.do1.component.mobileclient.deviceloginrecord.ui;
import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
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
import cn.com.do1.component.mobileclient.deviceloginrecord.model.TbDeviceLoginRecordPO;
import cn.com.do1.component.mobileclient.deviceloginrecord.service.IDeviceloginrecordService;
;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: lilei
*/
public class DeviceloginrecordAction extends BaseAction {
    private final static transient Logger logger = LoggerFactory.getLogger(DeviceloginrecordAction .class);
    private IDeviceloginrecordService deviceloginrecordService;
    private TbDeviceLoginRecordPO tbDeviceLoginRecordPO;
    private String ids[];
    private String id;

    public IDeviceloginrecordService getDeviceloginrecordService() {
        return deviceloginrecordService;
    }

    @Resource
    public void setDeviceloginrecordService(IDeviceloginrecordService deviceloginrecordService) {
        this.deviceloginrecordService = deviceloginrecordService;
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
        @SearchValueType(name = "osType", type = "number"),
        @SearchValueType(name = "loginTime", type = "date", format = "yyyy-MM-dd HH:mm:ss")
    })
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxSearch() throws Exception, BaseException{
            Pager pager = new Pager( ServletActionContext.getRequest(), getPageSize());
            pager=deviceloginrecordService .searchTbDeviceLoginRecord(getSearchValue(),pager);
            addJsonPager("pageData",pager);
    }

    
    public void setTbDeviceLoginRecordPO(TbDeviceLoginRecordPO tbDeviceLoginRecordPO){
        this.tbDeviceLoginRecordPO=tbDeviceLoginRecordPO;
    }
    
    public TbDeviceLoginRecordPO getTbDeviceLoginRecordPO(){
        return this.tbDeviceLoginRecordPO;
    }
    
    public void addTbDeviceLoginRecordPO() throws BaseException {
			super.ajaxAdd(tbDeviceLoginRecordPO);
    }
    
    public void modifyTbDeviceLoginRecordPO() throws BaseException {
			try {
				PropertyUtils.setProperty(tbDeviceLoginRecordPO, tbDeviceLoginRecordPO._getPKColumnName(), id);
			} catch (Exception e) {
				String msg = "修改时设置ID失败 error:"+e.getMessage();
				logger.error(msg, e);
				throw new BaseException(msg, e);
			}
			super.ajaxUpdate(tbDeviceLoginRecordPO);
    }
    
    public void updateTbDeviceLoginRecordPO(){
        super.ajaxUpdate(tbDeviceLoginRecordPO);
    }
    
    public void deleteTbDeviceLoginRecordPO(){
        if(AssertUtil.isEmpty(id))
            id=ids[0];
        tbDeviceLoginRecordPO._setPKValue(id);
        super.ajaxDelete(tbDeviceLoginRecordPO);
    }
    
    public void batchDeleteTbDeviceLoginRecordPO(){
        super.ajaxBatchDelete(TbDeviceLoginRecordPO.class,ids);
    }
    
    @JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxView() throws Exception, BaseException{
        TbDeviceLoginRecordPO tbDeviceLoginRecordPO = deviceloginrecordService .searchByPk(TbDeviceLoginRecordPO.class, id);
        addJsonFormateObj("tbDeviceLoginRecordPO", tbDeviceLoginRecordPO);//注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
    }
    
}
