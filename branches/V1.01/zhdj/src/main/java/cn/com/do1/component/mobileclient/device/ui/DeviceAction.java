package cn.com.do1.component.mobileclient.device.ui;
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
import cn.com.do1.component.mobileclient.device.model.TbDevicePO;
import cn.com.do1.component.mobileclient.device.service.IDeviceService;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: lilei
*/
public class DeviceAction extends BaseAction {
    private final static transient Logger logger = LoggerFactory.getLogger(DeviceAction .class);
    private IDeviceService deviceService;
    private TbDevicePO tbDevicePO;
    private String ids[];
    private String id;

    public IDeviceService getDeviceService() {
        return deviceService;
    }

    @Resource
    public void setDeviceService(IDeviceService deviceService) {
        this.deviceService = deviceService;
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
        @SearchValueType(name = "createTime", type = "date", format = "yyyy-MM-dd HH:mm:ss"),
        @SearchValueType(name = "updateTime", type = "date", format = "yyyy-MM-dd HH:mm:ss")
    })
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxSearch() throws Exception, BaseException{
            Pager pager = new Pager( ServletActionContext.getRequest(), getPageSize());
            pager=deviceService .searchTbDevice(getSearchValue(),pager);
            addJsonPager("pageData",pager);
    }

    
    public void setTbDevicePO(TbDevicePO tbDevicePO){
        this.tbDevicePO=tbDevicePO;
    }
    
    public TbDevicePO getTbDevicePO(){
        return this.tbDevicePO;
    }
    
    public void addTbDevicePO() throws BaseException {
			super.ajaxAdd(tbDevicePO);
    }
    
    public void modifyTbDevicePO() throws BaseException {
			try {
				PropertyUtils.setProperty(tbDevicePO, tbDevicePO._getPKColumnName(), id);
			} catch (Exception e) {
				String msg = "修改时设置ID失败 error:"+e.getMessage();
				logger.error(msg, e);
				throw new BaseException(msg, e);
			}
			super.ajaxUpdate(tbDevicePO);
    }
    
    public void updateTbDevicePO(){
        super.ajaxUpdate(tbDevicePO);
    }
    
    public void deleteTbDevicePO(){
        if(AssertUtil.isEmpty(id))
            id=ids[0];
        tbDevicePO._setPKValue(id);
        super.ajaxDelete(tbDevicePO);
    }
    
    public void batchDeleteTbDevicePO(){
        super.ajaxBatchDelete(TbDevicePO.class,ids);
    }
    
    @JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxView() throws Exception, BaseException{
        TbDevicePO tbDevicePO = deviceService .searchByPk(TbDevicePO.class, id);
        addJsonFormateObj("tbDevicePO", tbDevicePO);//注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
    }
    
}
