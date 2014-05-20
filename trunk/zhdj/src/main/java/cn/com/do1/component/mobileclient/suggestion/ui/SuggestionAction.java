package cn.com.do1.component.mobileclient.suggestion.ui;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.dictionary.DictOperater;
import cn.com.do1.common.annotation.struts.*;
import cn.com.do1.common.exception.IllegalParameterException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.component.mobileclient.suggestion.model.*;
import cn.com.do1.component.mobileclient.suggestion.service.ISuggestionService;
import cn.com.do1.component.mobileclient.version.model.TbVersionPO;
import cn.com.do1.component.report.access.model.TbAccessInfoVO;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import cn.com.do1.common.exception.BaseException;
import javax.annotation.Resource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.common.util.DateUtil;

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
public class SuggestionAction extends BaseAction {
    private final static transient Logger logger = LoggerFactory.getLogger(SuggestionAction.class);
    private ISuggestionService suggestionService;
    private TbSuggestionPO tbSuggestionPO;
    private String ids[];
    private String id;

    public ISuggestionService getSuggestionService() {
        return suggestionService;
    }

    @Resource
    public void setSuggestionService(ISuggestionService suggestionService) {
        this.suggestionService = suggestionService;
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
            Pager pager = new Pager( ServletActionContext.getRequest(), getPageSize());
            pager=suggestionService.searchSuggestion(getSearchValue(),pager);
            addJsonFormatePager("pageData",pager);
    }
    @JSONOut(catchException = @CatchException(errCode = "1006", successMsg = "导出成功", faileMsg = "导出失败"))
	public void ajaxBatchExport() throws Exception, BaseException {
		String fileName = "意见发聩记录"
				+ new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())
				+ ".csv";
		String filePath = getReqeustObj().getSession().getServletContext()
				.getRealPath("/")
				+ "downloadTemp/csv/" + fileName;
		File file = new File(filePath);
		FileOutputStream out = new FileOutputStream(file);

		OutputStreamWriter osw = new OutputStreamWriter(out, "GB2312");
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write("用户名,意见反馈,平台类型,提交时间\r\n");// CSV默认是逗号","分隔单元格。这里是表头

	    // 取出的数据
		Pager pager = new Pager( ServletActionContext.getRequest(), 99999999);
        pager=suggestionService.searchSuggestion(getSearchValue(),pager);
        if(!AssertUtil.isEmpty(pager.getPageData())){
        	List<TbSuggestionPO> list=(List<TbSuggestionPO>) pager.getPageData();
        
        	TbSuggestionPO vo = null;
		for (int i = 0; i < list.size(); i++) {
			vo = list.get(i);
			bw.write(vo.getUserName() + "," + vo.getSuggestion() + ","
					+ DictOperater.getItemDesc("clientType",String.valueOf(vo.getType())) + "," + DateUtil.format(vo.getCreateTime(),"yyyy-MM-dd HH:mm:ss") + "\r\n");
		}
        }
        
		
		bw.flush();
		bw.close();
		osw.close();
		out.close();
		addJsonObj("filePath", filePath);
	}

@JSONOut(catchException = @CatchException(errCode = "1002", successMsg = "新增成功", faileMsg = "新增失败"))
    public void ajaxAdd() throws Exception, BaseException{
            //todo:完成新增的代码;
    }

@JSONOut(catchException = @CatchException(errCode = "1003", successMsg = "更新成功", faileMsg = "更新失败"))
    public void ajaxUpdate() throws Exception, BaseException{
            //todo:完成更新的代码;
    }

@JSONOut(catchException = @CatchException(errCode = "1004", successMsg = "删除成功", faileMsg = "删除失败"))
    public void ajaxBatchDelete() throws Exception, BaseException{
	if(!AssertUtil.isEmpty(ids)){
	   	   for(String id:ids){
	   		TbSuggestionPO xxPO = this.suggestionService.searchByPk(TbSuggestionPO.class, id);
	   		   this.suggestionService.delPO(xxPO);
	   	   }
	      }
    }

   public void setTbSuggestionPO(TbSuggestionPO tbSuggestionPO){
       this.tbSuggestionPO=tbSuggestionPO;
    }
   public TbSuggestionPO setTbSuggestionPO(){
       return this.tbSuggestionPO;
    }
   public void addTbSuggestionPO(){
       super.ajaxAdd(tbSuggestionPO);
   }
   public void updateTbSuggestionPO(){
       super.ajaxUpdate(tbSuggestionPO);
   }
   public void deleteTbSuggestionPO(){
       if(AssertUtil.isEmpty(id))
           id=ids[0];
       tbSuggestionPO._setPKValue(id);
       super.ajaxDelete(tbSuggestionPO);
   }
   public void batchDeleteTbSuggestionPO(){
       super.ajaxBatchDelete(TbSuggestionPO.class,ids);
   }
@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxView() throws Exception, BaseException{
            TbSuggestionPO xxPO = suggestionService.searchByPk(TbSuggestionPO.class, id);
            addJsonFormateObj("tbSuggestionPO", xxPO);//注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
    }
    public TbSuggestionPO getTbSuggestionPO() {
        return this.tbSuggestionPO;
    }
}
