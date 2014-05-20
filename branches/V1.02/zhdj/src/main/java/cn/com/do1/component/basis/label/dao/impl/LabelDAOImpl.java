package cn.com.do1.component.basis.label.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.DataConfictException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.component.basis.label.dao.ILabelDAO;
import cn.com.do1.component.basis.label.model.TbLabelModelPO;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class LabelDAOImpl extends BaseDAOImpl implements ILabelDAO{

	@Override
	public void delLableModel() throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from TB_LABEL_MODEL t";
		super.preparedSql(sql);
		super.executeUpdate();
	}

	@Override
	public List<TbLabelModelPO> getLabelModel() throws SQLException {
		String sql = "select * from TB_LABEL_MODEL t order by t.SORT_VALUE asc";
		super.preparedSql(sql);
		return super.getList(TbLabelModelPO.class);
	}

	@Override
	public void saveLableModel(TbLabelModelPO po) throws SQLException {
		// TODO Auto-generated method stub
		try {
			super.insert(po);
		} catch (DataConfictException e) {
			throw new SQLException(e);
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}



}
