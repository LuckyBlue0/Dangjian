package cn.com.do1.component.basis.label.service;

import java.util.List;

import net.sf.json.JSONArray;

import cn.com.do1.common.exception.DataConfictException;
import cn.com.do1.component.basis.label.model.TbLabelModelPO;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public interface ILabelService {

	/**
	 * 获取标签模板数据
	 * @return
	 * @throws Exception
	 */
	List<TbLabelModelPO> getLabelModel()throws Exception;
	
	/**
	 * 保存标签模板
	 * @throws Exception
	 */
	public void saveLabelModel(JSONArray modelList) throws Exception;

}
