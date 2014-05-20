package cn.com.do1.component.shyk.partylecture.service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.component.shyk.partylecture.model.TbPartyLecturePO;
import cn.com.do1.component.shyk.partylecture.vo.PartyLectureVO;

import java.util.Map;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface IPartyLectureService extends IBaseService{
    Pager searchPartyLecture(Map searchMap, Pager pager) throws Exception, BaseException;

	/**
	 * 保存党课信息
	 * @param tbPartyLecturePO
	 * @param userIds
	 * @return
	 */
	Map<String, Object> savePartyLectureInfo(TbPartyLecturePO tbPartyLecturePO, String userIds)throws Exception, BaseException;

	/**
	 * 保存党课记录
	 * @param tbPartyLecturePO
	 * @param userIds
	 * @return
	 */
	Map<String, Object> savePartyLectureRecordInfo(TbPartyLecturePO tbPartyLecturePO, String userIds)throws Exception, BaseException;

	/**
	 * 根据ID查询党课信息
	 * @param id
	 * @return
	 */
	PartyLectureVO searchById(String id)throws Exception, BaseException;

}
