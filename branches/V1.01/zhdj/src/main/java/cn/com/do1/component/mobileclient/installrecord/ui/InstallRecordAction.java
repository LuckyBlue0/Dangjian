package cn.com.do1.component.mobileclient.installrecord.ui;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.struts.CatchException;
import cn.com.do1.common.annotation.struts.JSONOut;
import cn.com.do1.common.annotation.struts.SearchValueType;
import cn.com.do1.common.annotation.struts.SearchValueTypes;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.dictionary.DictOperater;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.common.util.DateUtil;
import cn.com.do1.component.mobileclient.installrecord.model.TbInstallRecordPO;
import cn.com.do1.component.mobileclient.installrecord.service.IInstallRecordService;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class InstallRecordAction extends BaseAction {
    private final static transient Logger logger = LoggerFactory.getLogger(InstallRecordAction.class);
    private IInstallRecordService installRecordService;
    private TbInstallRecordPO tbInstallRecordPO;
    private String ids[];
    private String id;

    public IInstallRecordService getInstallRecordService() {
        return installRecordService;
    }

    @Resource
    public void setInstallRecordService(IInstallRecordService installRecordService) {
        this.installRecordService = installRecordService;
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
        @SearchValueType(name = "startTime", type = "date", format = "yyyy-MM-dd"),
        @SearchValueType(name = "endTime", type = "date", format = "yyyy-MM-dd")
    })
@JSONOut(catchException = @CatchException(errCode = "1001", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxSearch() throws Exception, BaseException{
            Pager pager = new Pager( ServletActionContext.getRequest(), getPageSize());
            pager=installRecordService.searchInstallRecord(getSearchValue(),pager);
            addJsonFormatePager("pageData",pager);
    }
    @SearchValueTypes(
            nameFormat="false",value={
            @SearchValueType(name = "startTime", type = "date", format = "yyyy-MM-dd"),
            @SearchValueType(name = "endTime", type = "date", format = "yyyy-MM-dd")
        })
    @JSONOut(catchException = @CatchException(errCode = "1006", successMsg = "导出成功", faileMsg = "导出失败"))
	public void ajaxBatchExport() throws Exception, BaseException {
		String fileName = "安装记录"
				+ new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())
				+ ".csv";
		String filePath = getReqeustObj().getSession().getServletContext()
				.getRealPath("/")
				+ "downloadTemp/csv/" + fileName;
		File file = new File(filePath);
		FileOutputStream out = new FileOutputStream(file);

		OutputStreamWriter osw = new OutputStreamWriter(out, "GB2312");
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write("版本号,平台类型,设备号,安装时间\r\n");// CSV默认是逗号","分隔单元格。这里是表头

	    // 取出的数据
		Pager pager = new Pager( ServletActionContext.getRequest(), 99999999);
		pager=installRecordService.searchInstallRecord(getSearchValue(),pager);
        if(!AssertUtil.isEmpty(pager.getPageData())){
        	List<TbInstallRecordPO> list=(List<TbInstallRecordPO>) pager.getPageData();
        
        	TbInstallRecordPO vo = null;
		for (int i = 0; i < list.size(); i++) {
			vo = list.get(i);
			bw.write(vo.getVersionNum() + "," + DictOperater.getItemDesc("clientType", String.valueOf(vo.getType())) + "," + vo.getEquipmentNum() + ","+ DateUtil.format(vo.getInstallTime(),"yyyy-MM-dd HH:mm:ss") + "\r\n");
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
            //完成批量更新的代码
    }

   public void setTbInstallRecordPO(TbInstallRecordPO tbInstallRecordPO){
       this.tbInstallRecordPO=tbInstallRecordPO;
    }
   public TbInstallRecordPO setTbInstallRecordPO(){
       return this.tbInstallRecordPO;
    }
   public void addTbInstallRecordPO(){
       super.ajaxAdd(tbInstallRecordPO);
   }
   public void updateTbInstallRecordPO(){
       super.ajaxUpdate(tbInstallRecordPO);
   }
   public void deleteTbInstallRecordPO(){
       if(AssertUtil.isEmpty(id))
           id=ids[0];
       tbInstallRecordPO._setPKValue(id);
       super.ajaxDelete(tbInstallRecordPO);
   }
   public void batchDeleteTbInstallRecordPO(){
       super.ajaxBatchDelete(TbInstallRecordPO.class,ids);
   }
@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxView() throws Exception, BaseException{
            TbInstallRecordPO xxPO = installRecordService.searchByPk(TbInstallRecordPO.class, id);
            addJsonFormateObj("tbInstallRecordPO", xxPO);//注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
    }
    public TbInstallRecordPO getTbInstallRecordPO() {
        return this.tbInstallRecordPO;
    }
}
