package cn.com.do1.component.share.sharemanager.ui;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.annotation.struts.*;
import cn.com.do1.common.exception.IllegalParameterException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.component.share.sharemanager.model.TbShareInfoPO;
import cn.com.do1.component.share.sharemanager.vo.TbShareInfoVO;
import cn.com.do1.component.share.sharemanager.service.ISharemanagerService;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import cn.com.do1.common.exception.BaseException;
import javax.annotation.Resource;
import java.sql.SQLException;
import cn.com.do1.common.util.AssertUtil;
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
public class SharemanagerAction extends BaseAction {
    private final static transient Logger logger = LoggerFactory.getLogger(SharemanagerAction .class);
    private ISharemanagerService sharemanagerService;
    private TbShareInfoPO tbShareInfoPO;
    private String ids[];
    private String id;

    public ISharemanagerService getSharemanagerService() {
        return sharemanagerService;
    }

    @Resource
    public void setSharemanagerService(ISharemanagerService sharemanagerService) {
        this.sharemanagerService = sharemanagerService;
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
        @SearchValueType(name = "createTime", type = "date", format = "yyyy-MM-dd HH:mm:ss")
    })
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    @ActionRoles({"sharemanagerList"})
    public void ajaxSearch() throws Exception, BaseException{
            Pager pager = new Pager( ServletActionContext.getRequest(), getPageSize());
            pager=sharemanagerService .searchTbShareInfo(getSearchValue(),pager);
            addJsonPager("pageData",pager);
    }

    
    public void setTbShareInfoPO(TbShareInfoPO tbShareInfoPO){
        this.tbShareInfoPO=tbShareInfoPO;
    }
    
    public TbShareInfoPO getTbShareInfoPO(){
        return this.tbShareInfoPO;
    }

    @ActionRoles({"sharemanagerAdd"})
    public void addTbShareInfoPO() throws BaseException {
			super.ajaxAdd(tbShareInfoPO);
    }

    @ActionRoles({"sharemanagerUpdate"})
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "修改成功", faileMsg = "修改失败"))
    public void modifyTbShareInfoPO() throws BaseException, Exception {
        PropertyUtils.setProperty(tbShareInfoPO, tbShareInfoPO._getPKColumnName(), id);
        this.sharemanagerService .updatePO( tbShareInfoPO , false);
    }
    
    public void updateTbShareInfoPO(){
        super.ajaxUpdate(tbShareInfoPO);
    }

    @ActionRoles({"sharemanagerDel"})
    public void deleteTbShareInfoPO(){
        if(AssertUtil.isEmpty(id))
            id=ids[0];
        tbShareInfoPO._setPKValue(id);
        super.ajaxDelete(tbShareInfoPO);
    }

    @ActionRoles({"sharemanagerDel"})
    public void batchDeleteTbShareInfoPO(){
        super.ajaxBatchDelete(TbShareInfoPO.class,ids);
    }

    @ActionRoles({"sharemanagerView"})
    @JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxView() throws Exception, BaseException{
        TbShareInfoPO tbShareInfoPO = sharemanagerService .searchByPk(TbShareInfoPO.class, id);
        addJsonFormateObj("tbShareInfoPO", tbShareInfoPO);//注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
    }
    
}
