package cn.com.do1.component.report.access.ui;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.dictionary.DictOperater;
import cn.com.do1.common.dictionary.DictUtil;
import cn.com.do1.common.annotation.struts.*;
import cn.com.do1.common.exception.IllegalParameterException;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.component.dqdpdictionary.dictmgr.model.DictItem;
import cn.com.do1.component.dqdpdictionary.dictmgr.ui.DictmgrAction;
import cn.com.do1.component.report.access.model.*;
import cn.com.do1.component.report.access.service.IAccessService;
import cn.com.do1.component.score.personalscore.vo.PersonalScoreVO;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import cn.com.do1.common.exception.BaseException;
import javax.annotation.Resource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.common.util.DateUtil;
import cn.com.do1.common.util.reflation.BeanProcesser.DictDescProcesser;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
public class AccessAction extends BaseAction {
    private final static transient Logger logger = LoggerFactory.getLogger(AccessAction.class);
    private IAccessService accessService;
    private TbAccessInfoPO tbAccessInfoPO;
    private String ids[];
    private String id;

    public IAccessService getAccessService() {
        return accessService;
    }

    @Resource
    public void setAccessService(IAccessService accessService) {
        this.accessService = accessService;
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
            Pager pager1 = new Pager( ServletActionContext.getRequest(), getPageSize());
            pager=accessService.searchAccess(getSearchValue(),pager);
            if(!AssertUtil.isEmpty(pager.getPageData())){
            List<TbAccessInfoVO> list=(List<TbAccessInfoVO>) pager.getPageData();
            for(TbAccessInfoVO vo:list){
            	String lastTime="";
            	if(!"anonymous".equals(vo.getUserId())){
            		 TbAccessInfoVO vo1=accessService.searchAccessByUsername(vo.getUserId(),DateUtil.parse(vo.getAccessTime(), "yyyy-MM-dd hh:mm:ss"));
            		 if(!AssertUtil.isEmpty(vo1)){
            	     lastTime=vo1.getAccessTime();
            		 }
            		 
            	}
            	vo.setLastTime(lastTime);
            }
            pager.setPageData(list);
            }
            addJsonPager("pageData",pager);
    }
    @JSONOut(catchException = @CatchException(errCode = "1002", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxSearchAccessData() throws Exception, BaseException{
    	AccessDataVO vo =accessService.searchAccessData();
    	addJsonObj("accessData", vo);
    	
    }
    public void ajaxSearchPvData() throws Exception, BaseException{
    	DateFormat df = new SimpleDateFormat("yyyy/MM/dd");	
    	DateFormat df2 = new SimpleDateFormat("MM月dd");	
    	String time = df.format(new Date().getTime()-14*24*60*60*1000);		
        Date date=df.parse(time);
    	List<PvReportVO> pvvos =accessService.searchPvDataByDate(date);
		String string="";
		for(PvReportVO vo :pvvos){
			vo.setShowDate(vo.getShowDate().replace("-", "月"));
			string+=vo.getShowDate()+",";
		}
		for (int i=0;i<15;i++){
			String newTime = df2.format(new Date().getTime()-i*24*60*60*1000);	
			if(string.contains(newTime)){
				continue;
			}else{
				PvReportVO vo2=new PvReportVO();
				vo2.setShowDate(newTime);
				vo2.setUserCnt(0);
				pvvos.add(vo2);
				string+=newTime+",";
			}
		}
		Object[] beanObjects = pvvos.toArray();   
		Arrays.sort(beanObjects);
        pvvos.removeAll(pvvos);
		 for (int i = 0; i < beanObjects.length; i++) {   
			            PvReportVO vo=new PvReportVO();
			            vo=(PvReportVO)beanObjects[i];
			            pvvos.add(vo);
	    }   
		 
    	
    	List<PvReportVO> plvos =accessService.searchPlDataByDate(date);
    	String string1="";
		for(PvReportVO vo :plvos){
			vo.setShowDate(vo.getShowDate().replace("-", "月"));
			string1+=vo.getShowDate()+",";
		}
		for (int i=0;i<15;i++){
			String newTime = df2.format(new Date().getTime()-i*24*60*60*1000);	
			if(string1.contains(newTime)){
				continue;
			}else{
				PvReportVO vo2=new PvReportVO();
				vo2.setShowDate(newTime);
				vo2.setUserCnt(0);
				plvos.add(vo2);
				string1+=newTime+",";
			}
		}
		Object[] beanObjects1 = plvos.toArray();   
		Arrays.sort(beanObjects1);
		plvos.removeAll(plvos);
		 for (int i = 0; i < beanObjects1.length; i++) {   
			            PvReportVO vo=new PvReportVO();
			            vo=(PvReportVO)beanObjects1[i];
			            plvos.add(vo);
	    }   
    	String pvdata=buildChartXml("访问用户数趋势图",pvvos);
    	String pldata=buildChartXml("登录用户数趋势图",plvos);
    	setActionResult("0" ,"查询成功!");
    	addJsonObj("pvdata", pvdata);
    	addJsonObj("pldata", pldata);
    	doJsonOut();
    	
    }
    
    private String buildChartXml(String name,List<PvReportVO> list){
		if(AssertUtil.isEmpty(list)){
			return null;
		}
		StringBuilder sb = new StringBuilder();
		StringBuilder cats = new StringBuilder("<categories>");
		StringBuilder dsPv1 = new StringBuilder("<dataset seriesName='用户数' parentYAxis='P' anchorSides='10'  anchorRadius='3' anchorBorderColor='009900'>");
		for(PvReportVO key:list){
			cats.append("<category label='"+key.getShowDate()+"'/>");
			dsPv1.append("<set value='"+key.getUserCnt()+"'/>");
		}
		cats.append("</categories>");
		dsPv1.append("</dataset>");
		sb.append("<chart caption='"+name+"' XAxisName='时间（日）' SYAxisName='用户数' PYAxisName='用户数'  palette='1' animation='1' subcaption=' ' formatNumberScale='0' showValues='0' seriesNameInToolTip='1'>");
		sb.append(cats).append(dsPv1);
		sb.append("<styles><definition><style type='font' color='666666' name='CaptionFont' size='15' /><style type='font' name='SubCaptionFont' bold='0' /></definition><application><apply toObject='caption' styles='CaptionFont' /><apply toObject='SubCaption' styles='SubCaptionFont' /></application></styles>");
		sb.append("</chart>");
		return sb.toString();
	}
    @JSONOut(catchException = @CatchException(errCode = "1006", successMsg = "导出成功", faileMsg = "导出失败"))
	public void ajaxBatchExport() throws Exception, BaseException {
		String fileName = "用户访问记录"
				+ new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())
				+ ".csv";
		String filePath = getReqeustObj().getSession().getServletContext()
				.getRealPath("/")
				+ "downloadTemp/csv/" + fileName;
		File file = new File(filePath);
		FileOutputStream out = new FileOutputStream(file);

		OutputStreamWriter osw = new OutputStreamWriter(out, "GB2312");
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write("访问用户,访问栏目,访问时间,上次访问时间,来源渠道\r\n");// CSV默认是逗号","分隔单元格。这里是表头

	    // 取出的数据
		Pager pager = new Pager( ServletActionContext.getRequest(), 99999999);
        Pager pager1 = new Pager( ServletActionContext.getRequest(), 99999999);
        pager=accessService.searchAccess(getSearchValue(),pager);
        if(!AssertUtil.isEmpty(pager.getPageData())){
        List<TbAccessInfoVO> list=(List<TbAccessInfoVO>) pager.getPageData();
        for(TbAccessInfoVO vo:list){
        	String lastTime="";
        	if(!"anonymous".equals(vo.getUserId())){
        		 getSearchValue().put("userid", vo.getUserId());
        		 pager1=accessService.searchAccess(getSearchValue(),pager);
        		 if(!AssertUtil.isEmpty(pager1.getPageData())){
        			 List<TbAccessInfoVO> list1=(List<TbAccessInfoVO>) pager.getPageData();
        			 lastTime=list1.get(0).getAccessTime();
        		 }
        	}
        	vo.setLastTime(lastTime);
        }
        TbAccessInfoVO vo = null;
		for (int i = 0; i < list.size(); i++) {
			vo = list.get(i);
			bw.write(vo.getUserId() + "," + vo.getAccessItem() + ","
					+ vo.getAccessTime() + "," + vo.getLastTime() + ","
					+ vo.getAccessTypeDesc() + "\r\n");
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

   public void setTbAccessInfoPO(TbAccessInfoPO tbAccessInfoPO){
       this.tbAccessInfoPO=tbAccessInfoPO;
    }
   public TbAccessInfoPO setTbAccessInfoPO(){
       return this.tbAccessInfoPO;
    }
   public void addTbAccessInfoPO(){
       super.ajaxAdd(tbAccessInfoPO);
   }
   public void updateTbAccessInfoPO(){
       super.ajaxUpdate(tbAccessInfoPO);
   }
   public void deleteTbAccessInfoPO(){
       if(AssertUtil.isEmpty(id))
           id=ids[0];
       tbAccessInfoPO._setPKValue(id);
       super.ajaxDelete(tbAccessInfoPO);
   }
   public void batchDeleteTbAccessInfoPO(){
       super.ajaxBatchDelete(TbAccessInfoPO.class,ids);
   }
@JSONOut(catchException = @CatchException(errCode = "1005", successMsg = "查询成功", faileMsg = "查询失败"))
    public void ajaxView() throws Exception, BaseException{
            TbAccessInfoPO xxPO = accessService.searchByPk(TbAccessInfoPO.class, id);
            addJsonFormateObj("tbAccessInfoPO", xxPO);//注意，PO才用addJsonFormateObj，如果是VO，要采用addJsonObj
    }
    public TbAccessInfoPO getTbAccessInfoPO() {
        return this.tbAccessInfoPO;
    }
}
