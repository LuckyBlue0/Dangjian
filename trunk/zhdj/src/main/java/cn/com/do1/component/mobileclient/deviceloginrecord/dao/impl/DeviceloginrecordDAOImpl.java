package cn.com.do1.component.mobileclient.deviceloginrecord.dao.impl;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.po.Security;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.mobileclient.deviceloginrecord.dao.IDeviceloginrecordDAO;
import cn.com.do1.component.mobileclient.deviceloginrecord.vo.TbDeviceLoginRecordVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: lilei
*/
public class DeviceloginrecordDAOImpl extends BaseDAOImpl implements IDeviceloginrecordDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(DeviceloginrecordDAOImpl .class);
    
    final static String searchSQL = "select * from tb_device_login_record";
    final static String countSQL = "select count(1) from (" + searchSQL.replaceAll("(?i)order\\s+by\\s+.[^\\s,]+(,\\s+.[^\\s,]+)*", "")+"  ) a ";
    
	@Override
    public Pager searchTbDeviceLoginRecord(Map searchValue, @Security(encode = "")Pager pager) throws Exception, BaseException {
        /**
         * 自己写好查询总页数以及查询语句后调用框架方法
         * TbDqdpUserPO 查询结果封装类
         * countSQL统计总条数的语句
         * searchSQL 查询数据的语句
         * searchValue 查询条件
         * pager分页信息
         */
        return super.pageSearchByField(TbDeviceLoginRecordVO.class, countSQL, searchSQL, searchValue, pager);
    }

    @Override
    public List<TbDeviceLoginRecordVO> searchTbDeviceLoginRecordList(Map<String, Object> searchValue) throws SQLException {
        preparedSql(searchSQL);
        setPreValues(searchValue);//将参数设置进预置语句
        return getList(TbDeviceLoginRecordVO.class);
    }
}
