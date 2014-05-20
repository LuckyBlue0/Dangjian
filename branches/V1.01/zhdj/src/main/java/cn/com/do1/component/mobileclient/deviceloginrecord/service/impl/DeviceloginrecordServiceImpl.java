package cn.com.do1.component.mobileclient.deviceloginrecord.service.impl;

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
import cn.com.do1.component.mobileclient.deviceloginrecord.dao.IDeviceloginrecordDAO;
import cn.com.do1.component.mobileclient.deviceloginrecord.model.TbDeviceLoginRecordPO;
import cn.com.do1.component.mobileclient.deviceloginrecord.service.IDeviceloginrecordService;
import cn.com.do1.component.mobileclient.deviceloginrecord.vo.TbDeviceLoginRecordVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: lilei
*/
@Service("deviceloginrecordService")
public class DeviceloginrecordServiceImpl extends BaseService implements IDeviceloginrecordService {
    private final static transient Logger logger = LoggerFactory.getLogger(DeviceloginrecordServiceImpl .class);

    private IDeviceloginrecordDAO deviceloginrecordDAO;
    
    @Resource
    public void setDeviceloginrecordDAO(IDeviceloginrecordDAO deviceloginrecordDAO) {
        this.deviceloginrecordDAO = deviceloginrecordDAO;
        setDAO(deviceloginrecordDAO);
    }

	@Override 
    public Pager searchTbDeviceLoginRecord(Map<String, Object> searchMap, Pager pager) throws Exception, BaseException {
        return deviceloginrecordDAO .searchTbDeviceLoginRecord(searchMap, pager);
    }
    
    @Override
    public List<TbDeviceLoginRecordVO> searchTbDeviceLoginRecordList(Map<String, Object> searchValue) throws SQLException {
        return deviceloginrecordDAO .searchTbDeviceLoginRecordList(searchValue);
    }

    /**
     * 删除对象，只需要对象里的主键有值即可
     *
     * @param userPO
     * @throws Exception
     */
    public void delPO(TbDeviceLoginRecordPO po) throws Exception, ObjectNotFoundException, CannotSafeDelException {
        deviceloginrecordDAO .delete(po);
    }

    @Override
    public void casAdd(TbDeviceLoginRecordPO po) throws Exception, DataConfictException {
        deviceloginrecordDAO .insert(po);
    }

    @Override 
    public List<TbDeviceLoginRecordPO> casQuery(String[] fields, Object[] values, String appendSql) throws Exception, EncryptionUtils.EncryptionException {
        return deviceloginrecordDAO .searchByField(TbDeviceLoginRecordPO.class, fields, values, appendSql);
    }

    @Override
    public void casDel(String id) throws Exception, ObjectNotFoundException, CannotSafeDelException {
        TbDeviceLoginRecordPO po = new TbDeviceLoginRecordPO();
        po._setPKValue(id);
        deviceloginrecordDAO .delete(po);
    }
}
