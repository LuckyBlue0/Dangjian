package cn.com.do1.component.democreater.democ1.ui;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.IllegalParameterException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.annotation.struts.SearchValueType;
import cn.com.do1.common.annotation.struts.SearchValueTypes;
import cn.com.do1.component.democreater.democ1.model.*;
import cn.com.do1.component.democreater.democ1.service.IDemoc1Service;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import cn.com.do1.common.exception.BaseException;
import javax.annotation.Resource;
import java.sql.SQLException;
import cn.com.do1.common.util.AssertUtil;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class Democ1Action extends BaseAction {
    private final static transient Logger logger = LoggerFactory.getLogger(Democ1Action.class);
    private IDemoc1Service democ1Service;
    TbDqdpLogPO tbDqdpLogPO;
    private String ids[];
    private String id;

    public IDemoc1Service getDemoc1Service() {
        return democ1Service;
    }

    @Resource
    public void setDemoc1Service(IDemoc1Service democ1Service) {
        this.democ1Service = democ1Service;
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
    public void ajaxSearch() {
        try {
            Pager pager = new Pager( ServletActionContext.getRequest(), 20);
            pager=democ1Service.searchDemoc1(getSearchValue(),pager);
            addJsonPager("pageData",pager);
            setActionResult("0","查询成功");
        } catch (Exception e) {
            setActionResult("1001","查询失败");
            logger.error(e.getMessage(),e);
        } catch (BaseException e) {
            setActionResult(e.getErrCode(), e.getErrMsg());
            logger.error(e.getMessage(),e);
        }
        doJsonOut();
    }


    public void ajaxAdd() {
        try {
            //todo:完成新增的代码;
            setActionResult("0","新增成功");
        } catch (Exception e) {
            setActionResult("1002", "新增失败");
            logger.error(e.getMessage(),e);
        }
        doJsonOut();
    }

    public void ajaxUpdate() {
        try {
            //todo:完成更新的代码;
            setActionResult("0","更新成功");
        } catch (Exception e) {
            setActionResult("1002","更新失败");
            logger.error(e.getMessage(),e);
        }
        doJsonOut();
    }

    public void ajaxBatchDelete() {
        try {
            //完成批量更新的代码
            setActionResult("0","删除成功");
        } catch (Exception e) {
            setActionResult("1002","删除失败");
            logger.error(e.getMessage(),e);
        }
        doJsonOut();
    }

   public void setTbDqdpLogPO(TbDqdpLogPO tbDqdpLogPO){
       this.tbDqdpLogPO=tbDqdpLogPO;
    }
   public TbDqdpLogPO setTbDqdpLogPO(){
       return this.tbDqdpLogPO;
    }
   public void addTbDqdpLogPO(){
       super.ajaxAdd(tbDqdpLogPO);
   }
   public void updateTbDqdpLogPO(){
       super.ajaxUpdate(tbDqdpLogPO);
   }
   public void deleteTbDqdpLogPO(){
       if(AssertUtil.isEmpty(id))
           id=ids[0];
       tbDqdpLogPO._setPKValue(id);
       super.ajaxDelete(tbDqdpLogPO);
   }
   public void batchDeleteTbDqdpLogPO(){
       super.ajaxBatchDelete(TbDqdpLogPO.class,ids);
   }
    public void ajaxView() {
        try {
            TbDqdpLogPO xxPO = democ1Service.searchByPk(TbDqdpLogPO.class, id);
            addJsonFormateObj("tbDqdpLogPO", xxPO);//注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
            setActionResult("0", "查询成功");
        } catch (Exception e) {
            setActionResult("1001", "查询失败");
            logger.error(e.getMessage(), e);
        } catch (BaseException e) {
            setActionResult(e);
            logger.error(e.getMessage(), e);
        }
        doJsonOut();
    }
    public TbDqdpLogPO getTbDqdpLogPO() {
        return this.tbDqdpLogPO;
    }
}
