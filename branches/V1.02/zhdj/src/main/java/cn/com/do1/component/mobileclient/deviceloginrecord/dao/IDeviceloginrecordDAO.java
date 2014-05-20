package cn.com.do1.component.mobileclient.deviceloginrecord.dao;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.mobileclient.deviceloginrecord.vo.TbDeviceLoginRecordVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: lilei
*/
public interface IDeviceloginrecordDAO extends IBaseDAO {
	Pager searchTbDeviceLoginRecord (Map searchValue, Pager pager) throws Exception, BaseException;

    List<TbDeviceLoginRecordVO> searchTbDeviceLoginRecordList(Map<String, Object> searchValue) throws SQLException;
    
    
}
