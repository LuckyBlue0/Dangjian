package cn.com.do1.component.leader.leaderinfo.ui;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.dictionary.DictOperater;
import cn.com.do1.common.dictionary.vo.ExItemObj;
import cn.com.do1.common.annotation.bean.DictDesc;
import cn.com.do1.common.annotation.struts.*;
import cn.com.do1.common.exception.IllegalParameterException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.dqdpdictionary.dictmgr.model.DictItem;
import cn.com.do1.component.leader.leaderinfo.model.*;
import cn.com.do1.component.leader.leaderinfo.service.ILeaderinfoService;
import cn.com.do1.component.leader.org.model.TbOrganizationPO;
import cn.com.do1.component.systemmgr.org.model.TbDqdpOrgPO;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import cn.com.do1.common.exception.BaseException;
import javax.annotation.Resource;
import java.sql.SQLException;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.common.util.reflation.BeanProcesser.DictDescProcesser;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class LeaderinfoAction extends BaseAction {
    private final static transient Logger logger = LoggerFactory.getLogger(LeaderinfoAction.class);
    private ILeaderinfoService leaderinfoService;
    private TbLearderPO tbLearderPO;
    private String ids[];
    private String id;
    private String parentName;
    private TbLearderVO tbLearderVO;

    public ILeaderinfoService getLeaderinfoService() {
        return leaderinfoService;
    }

    @Resource
    public void setLeaderinfoService(ILeaderinfoService leaderinfoService) {
        this.leaderinfoService = leaderinfoService;
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
    @SearchValueTypes(
        nameFormat="false",value={
        @SearchValueType(name = "userName", type="string", format = "%%%s%%")
    })
@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxSearch() throws Exception, BaseException{
            Pager pager = new Pager( ServletActionContext.getRequest(), getPageSize());
            pager=leaderinfoService.searchLeaderinfo(getSearchValue(),pager);
            addJsonPager("pageData",pager);
    }

@JSONOut(catchException = @CatchException(errCode = "1002", successMsg = "新增成功", faileMsg = "新增失败"))
    public void ajaxAdd() throws Exception, BaseException{
	      leaderinfoService.addLeader(tbLearderVO);
	    
    }

@JSONOut(catchException = @CatchException(errCode = "1003", successMsg = "更新成功", faileMsg = "更新失败"))
    public void ajaxUpdate() throws Exception, BaseException{
	    leaderinfoService.editLeader(tbLearderVO);
    }

@JSONOut(catchException = @CatchException(errCode = "1004", successMsg = "删除成功", faileMsg = "删除失败"))
    public void ajaxBatchDelete() throws Exception, BaseException{
	    leaderinfoService.deleteLeader(ids);
	
    }
@JSONOut(catchException = @CatchException(errCode = "1003", successMsg = "更新成功", faileMsg = "更新失败"))
public void ajaxUpdateStatus() throws Exception, BaseException{
    leaderinfoService.editLeaderStatus(tbLearderVO);
}

   public void setTbLearderPO(TbLearderPO tbLearderPO){
       this.tbLearderPO=tbLearderPO;
    }
   public TbLearderPO setTbLearderPO(){
       return this.tbLearderPO;
    }
   public void addTbLearderPO(){
       super.ajaxAdd(tbLearderPO);
   }
   public void updateTbLearderPO(){
       super.ajaxUpdate(tbLearderPO);
   }
   public void deleteTbLearderPO(){
       if(AssertUtil.isEmpty(id))
           id=ids[0];
       tbLearderPO._setPKValue(id);
       super.ajaxDelete(tbLearderPO);
   }
   public void batchDeleteTbLearderPO(){
       super.ajaxBatchDelete(TbLearderPO.class,ids);
   }
@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxView() throws Exception, BaseException{
	
            TbLearderVO tbLearderVO = leaderinfoService.searchLeaderById(id);
          
            addJsonFormateObj("tbLearderVO", tbLearderVO);//注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
    }
    public TbLearderPO getTbLearderPO() {
        return this.tbLearderPO;
    }

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setTbLearderVO(TbLearderVO tbLearderVO) {
		this.tbLearderVO = tbLearderVO;
	}

	public TbLearderVO getTbLearderVO() {
		return tbLearderVO;
	}
}
