package cn.com.do1.component.score.scorerule.ui;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.dictionary.DictOperater;
import cn.com.do1.common.dictionary.vo.ExItemObj;
import cn.com.do1.common.annotation.struts.*;
import cn.com.do1.common.exception.IllegalParameterException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.component.dqdpdictionary.dictmgr.model.DictItem;
import cn.com.do1.component.score.scorerule.model.*;
import cn.com.do1.component.score.scorerule.service.IScoreruleService;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import cn.com.do1.common.exception.BaseException;
import javax.annotation.Resource;
import java.sql.SQLException;
import cn.com.do1.common.util.AssertUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class ScoreruleAction extends BaseAction {
    private final static transient Logger logger = LoggerFactory.getLogger(ScoreruleAction.class);
    private IScoreruleService scoreruleService;
    private TbScoreRulePO tbScoreRulePO;
    private String ids[];
    private String id;

    public IScoreruleService getScoreruleService() {
        return scoreruleService;
    }

    @Resource
    public void setScoreruleService(IScoreruleService scoreruleService) {
        this.scoreruleService = scoreruleService;
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
        @SearchValueType(name = "testDate", type = "date", format = "yyyy-MM-dd HH:mm:ss"),
        @SearchValueType(name = "testNumber", type = "number"),
        @SearchValueType(name = "testString", type="string", format = "%%%s%%")
    })
@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxSearch() throws Exception, BaseException{
    	    
    	
    	    
    	    Pager pager = new Pager(ServletActionContext.getRequest(),
    				getPageSize());
    		pager = scoreruleService.searchScorerule(getSearchValue(), pager);
    		if(!AssertUtil.isEmpty(pager.getPageData())){
    			List<TbScoreRulePO> list=(List<TbScoreRulePO>) pager.getPageData();
    			for(TbScoreRulePO po:list){
    				String dict=scoreruleService.searchDictDesc(po.getScoreType());
    				
    				
    				po.setScoreType(dict);
    			}
    			pager.setPageData(list);
    		}
    		addJsonFormatePager("pageData", pager);
    }

@JSONOut(catchException = @CatchException(errCode = "1002", successMsg = "新增成功", faileMsg = "新增失败"))
    public void ajaxAdd() throws Exception, BaseException{
	      
    }

@JSONOut(catchException = @CatchException(errCode = "1003", successMsg = "更新成功", faileMsg = "更新失败"))
    public void ajaxUpdate() throws Exception, BaseException{
	 scoreruleService.updatePO(tbScoreRulePO, false);
    }

@JSONOut(catchException = @CatchException(errCode = "1004", successMsg = "删除成功", faileMsg = "删除失败"))
    public void ajaxBatchDelete() throws Exception, BaseException{
            //完成批量更新的代码
    }

   public void setTbScoreRulePO(TbScoreRulePO tbScoreRulePO){
       this.tbScoreRulePO=tbScoreRulePO;
    }
   public TbScoreRulePO setTbScoreRulePO(){
       return this.tbScoreRulePO;
    }
   public void addTbScoreRulePO(){
       super.ajaxAdd(tbScoreRulePO);
   }
   public void updateTbScoreRulePO(){
       super.ajaxUpdate(tbScoreRulePO);
   }
   public void deleteTbScoreRulePO(){
       if(AssertUtil.isEmpty(id))
           id=ids[0];
       tbScoreRulePO._setPKValue(id);
       super.ajaxDelete(tbScoreRulePO);
   }
   public void batchDeleteTbScoreRulePO(){
       super.ajaxBatchDelete(TbScoreRulePO.class,ids);
   }
@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxView() throws Exception, BaseException{
            TbScoreRulePO xxPO = scoreruleService.searchByPk(TbScoreRulePO.class, id);
            String dict=scoreruleService.searchDictDesc(xxPO.getScoreType());
			
			addJsonObj("scoreType", dict);
            addJsonFormateObj("tbScoreRulePO", xxPO);//注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
    }
    public TbScoreRulePO getTbScoreRulePO() {
        return this.tbScoreRulePO;
    }
}
