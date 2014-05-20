package cn.com.do1.component.basis.label.ui;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.struts.ActionRoles;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.component.basis.label.model.TbLabelModelPO;
import cn.com.do1.component.basis.label.service.ILabelService;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class LabelAction extends BaseAction{

	Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	ILabelService labelService;
	
	/**
	 * 获取标签数据
	 */
	@ActionRoles({"newsColumnAttManager"})
	public void getModel(){
		List<TbLabelModelPO> labelModel;
		try {
			labelModel = labelService.getLabelModel();
			this.addJsonArray("modelList", labelModel);
			this.setActionResult(0, "操作成功");
			this.doJsonOut();
		} catch (Exception e) {
			this.setActionResult(1, "操作失败");
			this.logger.error("取标签数据发生异常",e);
		}
	}
	
	
	@ActionRoles({"newsColumnAttManager"})
	public void saveModel() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String modelListStr=request.getParameter("modelListStr");
		try {
			if (modelListStr != null) {
				JSONArray modelList = JSONArray.fromObject(modelListStr);
				if (modelList != null) {
					labelService.saveLabelModel(modelList);
					this.setActionResult(0, "操作成功");
				}
			}
		} catch (Exception e) {
			this.logger.error("保存标签模板发生异常",e);
			this.setActionResult(2, "操作失败,发生异常:"+e.getMessage());
		}finally{
			this.doJsonOut();
		}
	}
	
}
