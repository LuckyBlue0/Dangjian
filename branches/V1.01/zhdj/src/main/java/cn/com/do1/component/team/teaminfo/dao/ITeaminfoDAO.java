package cn.com.do1.component.team.teaminfo.dao;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.component.team.teaminfo.vo.TbTeamInfoVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: zhanqiuxiang
*/
public interface ITeaminfoDAO extends IBaseDAO {
	Pager searchTbTeamInfo (Map searchValue, Pager pager) throws Exception, BaseException;

    List<TbTeamInfoVO> searchTbTeamInfoList(Map<String, Object> searchValue) throws SQLException;

	TbTeamInfoVO getDetailById(String id) throws SQLException;
}
