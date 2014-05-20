package cn.com.do1.component.mobileclient.device.dao;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.mobileclient.device.model.TbDevicePO;
import cn.com.do1.component.mobileclient.device.vo.TbDeviceVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: lilei
*/
public interface IDeviceDAO extends IBaseDAO {
	Pager searchTbDevice (Map searchValue, Pager pager) throws Exception, BaseException;

    List<TbDeviceVO> searchTbDeviceList(Map<String, Object> searchValue) throws SQLException;

	/**
	 * @return
	 * @throws SQLException 
	 */
	List<String> getDeviceForiOS() throws SQLException;

	/**
	 * @param deviceId
	 * @return
	 */
	TbDevicePO findByDeviceId(String deviceId) throws SQLException;
}
