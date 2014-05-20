package cn.com.do1.component.mobileclient.device.service;

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
import cn.com.do1.component.mobileclient.device.model.TbDevicePO;
import cn.com.do1.component.mobileclient.device.vo.TbDeviceVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: lilei
*/
public interface IDeviceService extends IBaseService{
    
	Pager searchTbDevice(Map<String, Object> searchValue, Pager pager) throws Exception, BaseException;

    List<TbDeviceVO> searchTbDeviceList(Map<String, Object> searchValue) throws SQLException;
    
    void casAdd(TbDevicePO po) throws Exception, DataConfictException;
    List<TbDevicePO> casQuery(String[] fields, Object[] values, String appendSql) throws Exception, EncryptionUtils.EncryptionException;
    void casDel(String id) throws Exception, ObjectNotFoundException, CannotSafeDelException;

	/**
	 * @return
	 */
	List<String> getDeviceForiOS() throws BaseException,Exception;

	/**
	 * @param deviceId
	 * @return
	 */
	TbDevicePO findByDeviceId(String deviceId) throws BaseException,Exception;
}
