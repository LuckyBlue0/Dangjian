package cn.com.do1.component.indexmanager.indexshow.service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.common.exception.BaseException;
import java.util.Map;
import cn.com.do1.common.util.security.EncryptionUtils;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.exception.CannotSafeDelException;
import cn.com.do1.common.exception.DataConfictException;
import cn.com.do1.common.exception.ObjectNotFoundException;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.indexmanager.indexshow.model.TbProjectInfoPO;
import cn.com.do1.component.indexmanager.indexshow.vo.TbProjectInfoVO;
import java.sql.SQLException;
import java.util.List;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: zhanqiuxiang
*/
public interface IIndexshowService extends IBaseService{
    
	Pager searchTbProjectInfo(Map<String, Object> searchValue, Pager pager) throws Exception, BaseException;

    List<TbProjectInfoVO> searchTbProjectInfoList(Map<String, Object> searchValue) throws SQLException;
    
    void casAdd(TbProjectInfoPO po) throws Exception, DataConfictException;
    List<TbProjectInfoPO> casQuery(String[] fields, Object[] values, String appendSql) throws Exception, EncryptionUtils.EncryptionException;
    void casDel(String id) throws Exception, ObjectNotFoundException, CannotSafeDelException;
    
    String updateUserInfo(TbProjectInfoPO po) throws Exception,
	BaseException;

	TbProjectInfoVO getInfoById(String id) throws Exception;
}
