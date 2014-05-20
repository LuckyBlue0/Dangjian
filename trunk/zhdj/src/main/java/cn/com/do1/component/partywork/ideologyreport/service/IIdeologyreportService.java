package cn.com.do1.component.partywork.ideologyreport.service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.component.partywork.ideologyreport.vo.IdeologyReportVO;

import java.util.Map;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface IIdeologyreportService extends IBaseService{
    Pager searchIdeologyreport(Map searchMap, Pager pager) throws Exception, BaseException;

	IdeologyReportVO searchById(String id)throws Exception, BaseException;

}
