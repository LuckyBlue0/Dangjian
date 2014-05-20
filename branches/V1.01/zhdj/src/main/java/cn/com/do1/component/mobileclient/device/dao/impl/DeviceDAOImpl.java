package cn.com.do1.component.mobileclient.device.dao.impl;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.mobileclient.device.dao.IDeviceDAO;
import cn.com.do1.component.mobileclient.device.enums.DeviceOSType;
import cn.com.do1.component.mobileclient.device.model.TbDevicePO;
import cn.com.do1.component.mobileclient.device.vo.TbDeviceVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: lilei
*/
public class DeviceDAOImpl extends BaseDAOImpl implements IDeviceDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(DeviceDAOImpl .class);
    
    final static String searchSQL = "select * from tb_device where os_type=:osType and device_id=:deviceId";
    final static String countSQL = "select count(1) from (" + searchSQL.replaceAll("(?i)order\\s+by\\s+.[^\\s,]+(,\\s+.[^\\s,]+)*", "")+"  ) a ";
    
	@Override
    public Pager searchTbDevice(Map searchValue, Pager pager) throws Exception, BaseException {
        /**
         * 自己写好查询总页数以及查询语句后调用框架方法
         * TbDqdpUserPO 查询结果封装类
         * countSQL统计总条数的语句
         * searchSQL 查询数据的语句
         * searchValue 查询条件
         * pager分页信息
         */
        return super.pageSearchByField(TbDeviceVO.class, countSQL, searchSQL, searchValue, pager);
    }

    @Override
    public List<TbDeviceVO> searchTbDeviceList(Map<String, Object> searchValue) throws SQLException {
        preparedSql(searchSQL);
        setPreValues(searchValue);//将参数设置进预置语句
        return getList(TbDeviceVO.class);
    }

	/* (non-Javadoc)
	 * @see cn.com.do1.component.devicemgr.device.dao.IDeviceDAO#getDeviceForiOS()
	 */
	@Override
	public List<String> getDeviceForiOS() throws SQLException {
		preparedSql("select device_token from tb_device where os_type=:type");
		setPreValue("type", DeviceOSType.iOS.getCode());
		return super.getList(String.class);
	}

	/* (non-Javadoc)
	 * @see cn.com.do1.component.devicemgr.device.dao.IDeviceDAO#findByDeviceId(java.lang.String)
	 */
	@Override
	public TbDevicePO findByDeviceId(String deviceId) throws SQLException {
		preparedSql("select * from tb_device where device_id=:deviceId");
		setPreValue("deviceId", deviceId);
		return super.executeQuery(TbDevicePO.class);
	}
}
