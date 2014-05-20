package cn.com.do1.component.team.teaminfo.ui;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.annotation.struts.*;
import cn.com.do1.common.exception.IllegalParameterException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.component.team.teaminfo.model.TbTeamInfoPO;
import cn.com.do1.component.team.teaminfo.vo.TbTeamInfoVO;
import cn.com.do1.component.team.teaminfo.service.ITeaminfoService;
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
public class TeaminfoAction extends BaseAction {
    private final static transient Logger logger = LoggerFactory.getLogger(TeaminfoAction .class);
    private ITeaminfoService teaminfoService;
    private TbTeamInfoPO tbTeamInfoPO;
    private String ids[];
    private String id;

    public ITeaminfoService getTeaminfoService() {
        return teaminfoService;
    }

    @Resource
    public void setTeaminfoService(ITeaminfoService teaminfoService) {
        this.teaminfoService = teaminfoService;
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
        @SearchValueType(name = "releaseDate", type = "date", format = "yyyy-MM-dd HH:mm:ss"),
        @SearchValueType(name = "createTime", type = "date", format = "yyyy-MM-dd HH:mm:ss")
    })
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    @ActionRoles({"teaminfoList"})
    public void ajaxSearch() throws Exception, BaseException{
            Pager pager = new Pager( ServletActionContext.getRequest(), getPageSize());
            pager=teaminfoService .searchTbTeamInfo(getSearchValue(),pager);
            addJsonPager("pageData",pager);
    }

    
    public void setTbTeamInfoPO(TbTeamInfoPO tbTeamInfoPO){
        this.tbTeamInfoPO=tbTeamInfoPO;
    }
    
    public TbTeamInfoPO getTbTeamInfoPO(){
        return this.tbTeamInfoPO;
    }

    @ActionRoles({"teaminfoAdd"})
    public void addTbTeamInfoPO() throws BaseException {
			super.ajaxAdd(tbTeamInfoPO);
    }

    @ActionRoles({"teaminfoUpdate"})
    @JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "修改成功", faileMsg = "修改失败"))
    public void modifyTbTeamInfoPO() throws BaseException, Exception {
        PropertyUtils.setProperty(tbTeamInfoPO, tbTeamInfoPO._getPKColumnName(), id);
        this.teaminfoService .updatePO( tbTeamInfoPO , false);
    }
    
    public void updateTbTeamInfoPO(){
        super.ajaxUpdate(tbTeamInfoPO);
    }

    @ActionRoles({"teaminfoDel"})
    public void deleteTbTeamInfoPO(){
        if(AssertUtil.isEmpty(id))
            id=ids[0];
        tbTeamInfoPO._setPKValue(id);
        super.ajaxDelete(tbTeamInfoPO);
    }

    @ActionRoles({"teaminfoDel"})
    public void batchDeleteTbTeamInfoPO(){
        super.ajaxBatchDelete(TbTeamInfoPO.class,ids);
    }

    @ActionRoles({"teaminfoView"})
    @JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxView() throws Exception, BaseException{
        TbTeamInfoPO tbTeamInfoPO = teaminfoService .searchByPk(TbTeamInfoPO.class, id);
        addJsonFormateObj("tbTeamInfoPO", tbTeamInfoPO);//注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
    }
    
}
