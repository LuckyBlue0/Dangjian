package cn.com.do1.component.team.teaminfo.service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.common.exception.BaseException;
import java.util.Map;
import cn.com.do1.common.util.security.EncryptionUtils;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.exception.CannotSafeDelException;
import cn.com.do1.common.exception.DataConfictException;
import cn.com.do1.common.exception.ObjectNotFoundException;
import cn.com.do1.component.team.teaminfo.model.TbTeamInfoPO;
import cn.com.do1.component.team.teaminfo.vo.TbTeamInfoVO;
import java.sql.SQLException;
import java.util.List;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: zhanqiuxiang
*/
public interface ITeaminfoService extends IBaseService{
    
	Pager searchTbTeamInfo(Map<String, Object> searchValue, Pager pager) throws Exception, BaseException;

    List<TbTeamInfoVO> searchTbTeamInfoList(Map<String, Object> searchValue) throws SQLException;
    
    void casAdd(TbTeamInfoPO po) throws Exception, DataConfictException;
    List<TbTeamInfoPO> casQuery(String[] fields, Object[] values, String appendSql) throws Exception, EncryptionUtils.EncryptionException;
    void casDel(String id) throws Exception, ObjectNotFoundException, CannotSafeDelException;

	TbTeamInfoVO getDetailById(String id) throws SQLException;
}
