package cn.com.do1.component.partywork.branch.dao;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.partywork.branch.model.TbBranchActivityPO;
import cn.com.do1.component.partywork.branch.vo.BranchActivityVO;

/**
* All rights reserved.
* User: ${user}
*/
public interface IBranchDAO extends IBaseDAO {

	Pager pageSearchByField(Map searchMap, Pager pager)throws SQLException;

	BranchActivityVO getBranchActivityById(String id)throws SQLException;

	List<TbBranchActivityPO>getNoEndBranchActivity()throws SQLException;
}
