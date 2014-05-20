package cn.com.do1.component.news.tissuemieninfo.dao;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.news.newsinfo.model.ShortNewsInfoVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface ITissueMienInfoDAO extends IBaseDAO {

	Pager pageSearchByField(Map searchMap, Pager pager) throws SQLException;

	List<ShortNewsInfoVO> searchTop5TissueMienInfo()throws SQLException;

}
