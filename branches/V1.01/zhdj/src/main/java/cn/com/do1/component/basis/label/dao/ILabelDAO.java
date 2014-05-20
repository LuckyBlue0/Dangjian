package cn.com.do1.component.basis.label.dao;

import java.sql.SQLException;
import java.util.List;

import cn.com.do1.component.basis.label.model.TbLabelModelPO;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public interface ILabelDAO {
	/**
	 * 删除标签数据
	 */
	void saveLableModel(TbLabelModelPO po)throws SQLException;;

	/**
	 * 删除标签数据
	 */
	void delLableModel()throws SQLException;;

	/**
	 * 获取标签数据
	 * @return
	 */
	List<TbLabelModelPO> getLabelModel()throws SQLException;;


}
