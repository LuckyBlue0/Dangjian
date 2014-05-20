package cn.com.do1.component.basis.label.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.component.basis.label.dao.ILabelDAO;
import cn.com.do1.component.basis.label.model.TbLabelModelPO;
import cn.com.do1.component.basis.label.service.ILabelService;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Service("labelService")
public class LabelServiceImpl extends BaseService implements ILabelService{

	@Resource
	private ILabelDAO labelDAO;
	@Override
	public List<TbLabelModelPO> getLabelModel() throws Exception {
		return labelDAO.getLabelModel();
	}

	@Override
	public void saveLabelModel(JSONArray modelList) throws Exception {
		labelDAO.delLableModel();
		JSONObject o = null;
		TbLabelModelPO model = null;
		for (int i = 0; i < modelList.size(); i++) {
			o = modelList.getJSONObject(i);
			model = new TbLabelModelPO();
			model.setLabelModelId((o.getString("labelModelId")));
			model.setItemType(o.getString("itemType"));
			model.setLabelName(o.getString("labelName"));
			model.setSelectList(o.getString("selectList"));
			model.setSortValue(Integer.valueOf(i));
			labelDAO.saveLableModel(model);
		}	
	}

}
