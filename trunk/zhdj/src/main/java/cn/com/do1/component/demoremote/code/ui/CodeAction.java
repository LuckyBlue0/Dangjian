package cn.com.do1.component.demoremote.code.ui;
import cn.com.do1.common.annotation.struts.SearchValueType;
import cn.com.do1.common.annotation.struts.SearchValueTypes;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.demoremote.code.model.TbTestPO;
import cn.com.do1.component.demoremote.code.service.ICodeService;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;

/**
* Copyright ? 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/

public class CodeAction extends BaseAction {
    Logger logger = Logger.getLogger(getClass());
    ICodeService codeService;
    TbTestPO tbTestPO;
    String ids[];
    String id;

    public ICodeService getCodeService() {
        return codeService;
    }

    @Resource
    public void setCodeService(ICodeService codeService) {
        this.codeService = codeService;
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
        nameFormat="true",value={
        @SearchValueType(name = "testDate", type = "date", format = "yyyy-MM-dd HH:mm:ss"),
        @SearchValueType(name = "testNumber", type = "number"),
        @SearchValueType(name = "testString", type = "%%%s%%")
    })
    public void ajaxSearch() {
        try {
            Pager pager = new Pager( ServletActionContext.getRequest(), 20);
            pager=codeService.searchCode(getSearchValue(),pager);
            addJsonPager("pageData",pager);
            setActionResult("0","查询成功");
        } catch (Exception e) {
            setActionResult("1001","查询失败");
            logger.error(e.getMessage(),e);
        } catch (BaseException e) {
            setActionResult(e.getErrCode(), e.getErrMsg());
            logger.error(e);
        }
        doJsonOut();
    }


    public void ajaxAdd() {
        try {
            //todo:完成新增的代码;
            setActionResult("0","新增成功");
        } catch (Exception e) {
            setActionResult("1002", "新增失败");
        }
        doJsonOut();
    }

    public void ajaxUpdate() {
        try {
            //todo:完成更新的代码;
            setActionResult("0","更新成功");
        } catch (Exception e) {
            setActionResult("1002","更新失败");
        }
        doJsonOut();
    }

    public void ajaxBatchDelete() {
        try {
            //完成批量更新的代码
            setActionResult("0","删除成功");
        } catch (Exception e) {
            setActionResult("1002","删除失败");
        }
        doJsonOut();
    }

   public void setTbTestPO(TbTestPO tbTestPO){
       this.tbTestPO=tbTestPO;
    }
   public TbTestPO setTbTestPO(){
       return this.tbTestPO;
    }
   public void addTbTestPO(){
       super.ajaxAdd(tbTestPO);
   }
   public void updateTbTestPO(){
       super.ajaxUpdate(tbTestPO);
   }
   public void deleteTbTestPO(){
       if(AssertUtil.isEmpty(id))
           id=ids[0];
       tbTestPO._setPKValue(id);
       super.ajaxDelete(tbTestPO);
   }
   public void batchDeleteTbTestPO(){
       super.ajaxBatchDelete(TbTestPO.class,ids);
   }
    public void getTbTestPO() {
        super.searchByPk(TbTestPO.class, id);
    }
}
