package cn.com.do1.component.indexmanager.indexshow.dao;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.component.basis.partymenber.vo.PartyMenberInfoVO;
import cn.com.do1.component.indexmanager.indexshow.vo.TbProjectInfoVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: zhanqiuxiang
*/
public interface IIndexshowDAO extends IBaseDAO {
	Pager searchTbProjectInfo (Map searchValue, Pager pager) throws Exception, BaseException;

    List<TbProjectInfoVO> searchTbProjectInfoList(Map<String, Object> searchValue) throws SQLException;
    
    public TbProjectInfoVO getInfoById(String id) throws Exception;
}
