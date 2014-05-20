package cn.com.do1.component.mobileclient.device.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.exception.CannotSafeDelException;
import cn.com.do1.common.exception.DataConfictException;
import cn.com.do1.common.exception.ObjectNotFoundException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.util.security.EncryptionUtils;
import cn.com.do1.component.mobileclient.device.dao.IDeviceDAO;
import cn.com.do1.component.mobileclient.device.model.TbDevicePO;
import cn.com.do1.component.mobileclient.device.service.IDeviceService;
import cn.com.do1.component.mobileclient.device.vo.TbDeviceVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: lilei
*/
@Service("deviceService")
public class DeviceServiceImpl extends BaseService implements IDeviceService {
    private final static transient Logger logger = LoggerFactory.getLogger(DeviceServiceImpl .class);

    private IDeviceDAO deviceDAO;
    
    @Resource
    public void setDeviceDAO(IDeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
        setDAO(deviceDAO);
    }

	@Override 
    public Pager searchTbDevice(Map<String, Object> searchMap, Pager pager) throws Exception, BaseException {
        return deviceDAO .searchTbDevice(searchMap, pager);
    }
    
    @Override
    public List<TbDeviceVO> searchTbDeviceList(Map<String, Object> searchValue) throws SQLException {
        return deviceDAO .searchTbDeviceList(searchValue);
    }

    /**
     * 删除对象，只需要对象里的主键有值即可
     *
     * @param userPO
     * @throws Exception
     */
    public void delPO(TbDevicePO po) throws Exception, ObjectNotFoundException, CannotSafeDelException {
        deviceDAO .delete(po);
    }

    @Override
    public void casAdd(TbDevicePO po) throws Exception, DataConfictException {
        deviceDAO .insert(po);
    }

    @Override 
    public List<TbDevicePO> casQuery(String[] fields, Object[] values, String appendSql) throws Exception, EncryptionUtils.EncryptionException {
        return deviceDAO .searchByField(TbDevicePO.class, fields, values, appendSql);
    }

    @Override
    public void casDel(String id) throws Exception, ObjectNotFoundException, CannotSafeDelException {
        TbDevicePO po = new TbDevicePO();
        po._setPKValue(id);
        deviceDAO .delete(po);
    }

	/* (non-Javadoc)
	 * @see cn.com.do1.component.devicemgr.device.service.IDeviceService#getDeviceForType(cn.com.do1.component.devicemgr.common.enm.DeviceOSType)
	 */
	@Override
	public List<String> getDeviceForiOS()
			throws BaseException, Exception {
		return deviceDAO.getDeviceForiOS();
	}

	/* (non-Javadoc)
	 * @see cn.com.do1.component.devicemgr.device.service.IDeviceService#findByDeviceId(java.lang.String)
	 */
	@Override
	public TbDevicePO findByDeviceId(String deviceId) throws BaseException,
			Exception {
		return deviceDAO.findByDeviceId(deviceId);
	}
}
