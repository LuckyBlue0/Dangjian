package cn.com.do1.component.team.teaminfo.service.impl;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.exception.CannotSafeDelException;
import cn.com.do1.common.exception.DataConfictException;
import cn.com.do1.common.exception.ObjectNotFoundException;
import cn.com.do1.component.team.teaminfo.dao.ITeaminfoDAO;
import cn.com.do1.component.team.teaminfo.service.ITeaminfoService;
import cn.com.do1.component.team.teaminfo.model.TbTeamInfoPO;
import cn.com.do1.component.team.teaminfo.vo.TbTeamInfoVO;
import org.springframework.stereotype.Service;
import cn.com.do1.common.util.security.EncryptionUtils;
import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: zhanqiuxiang
*/
@Service("teaminfoService")
public class TeaminfoServiceImpl extends BaseService implements ITeaminfoService {
    private final static transient Logger logger = LoggerFactory.getLogger(TeaminfoServiceImpl .class);

    private ITeaminfoDAO teaminfoDAO;
    
    @Resource
    public void setTeaminfoDAO(ITeaminfoDAO teaminfoDAO) {
        this.teaminfoDAO = teaminfoDAO;
        setDAO(teaminfoDAO);
    }

	@Override 
    public Pager searchTbTeamInfo(Map<String, Object> searchMap, Pager pager) throws Exception, BaseException {
        return teaminfoDAO .searchTbTeamInfo(searchMap, pager);
    }
    
    @Override
    public List<TbTeamInfoVO> searchTbTeamInfoList(Map<String, Object> searchValue) throws SQLException {
        return teaminfoDAO .searchTbTeamInfoList(searchValue);
    }

    /**
     * 删除对象，只需要对象里的主键有值即可
     *
     * @param userPO
     * @throws Exception
     */
    public void delPO(TbTeamInfoPO po) throws Exception, ObjectNotFoundException, CannotSafeDelException {
        teaminfoDAO .delete(po);
    }

    @Override
    public void casAdd(TbTeamInfoPO po) throws Exception, DataConfictException {
        teaminfoDAO .insert(po);
    }

    @Override 
    public List<TbTeamInfoPO> casQuery(String[] fields, Object[] values, String appendSql) throws Exception, EncryptionUtils.EncryptionException {
        return teaminfoDAO .searchByField(TbTeamInfoPO.class, fields, values, appendSql);
    }

    @Override
    public void casDel(String id) throws Exception, ObjectNotFoundException, CannotSafeDelException {
        TbTeamInfoPO po = new TbTeamInfoPO();
        po._setPKValue(id);
        teaminfoDAO .delete(po);
    }

	@Override
	public TbTeamInfoVO getDetailById(String id) throws SQLException {
		return teaminfoDAO.getDetailById(id);
	}
}
