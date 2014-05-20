package cn.com.do1.component.mobileclient.deviceloginrecord.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.exception.CannotSafeDelException;
import cn.com.do1.common.exception.DataConfictException;
import cn.com.do1.common.exception.ObjectNotFoundException;
import cn.com.do1.common.framebase.dqdp.IBaseService;
import cn.com.do1.common.util.security.EncryptionUtils;
import cn.com.do1.component.mobileclient.deviceloginrecord.model.TbDeviceLoginRecordPO;
import cn.com.do1.component.mobileclient.deviceloginrecord.vo.TbDeviceLoginRecordVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: lilei
*/
public interface IDeviceloginrecordService extends IBaseService{
    
	Pager searchTbDeviceLoginRecord(Map<String, Object> searchValue, Pager pager) throws Exception, BaseException;

    List<TbDeviceLoginRecordVO> searchTbDeviceLoginRecordList(Map<String, Object> searchValue) throws SQLException;
    
    void casAdd(TbDeviceLoginRecordPO po) throws Exception, DataConfictException;
    List<TbDeviceLoginRecordPO> casQuery(String[] fields, Object[] values, String appendSql) throws Exception, EncryptionUtils.EncryptionException;
    void casDel(String id) throws Exception, ObjectNotFoundException, CannotSafeDelException;
}
