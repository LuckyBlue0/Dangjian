package cn.com.do1.component.indexmanager.indexshow.service.impl;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.exception.CannotSafeDelException;
import cn.com.do1.common.exception.DataConfictException;
import cn.com.do1.common.exception.ObjectNotFoundException;
import cn.com.do1.component.basis.partymenber.vo.PartyMenberInfoVO;
import cn.com.do1.component.indexmanager.indexshow.dao.IIndexshowDAO;
import cn.com.do1.component.indexmanager.indexshow.service.IIndexshowService;
import cn.com.do1.component.indexmanager.indexshow.model.TbProjectInfoPO;
import cn.com.do1.component.indexmanager.indexshow.vo.TbProjectInfoVO;
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
@Service("indexshowService")
public class IndexshowServiceImpl extends BaseService implements IIndexshowService {
    private final static transient Logger logger = LoggerFactory.getLogger(IndexshowServiceImpl .class);

    private IIndexshowDAO indexshowDAO;
    
    @Resource
    public void setIndexshowDAO(IIndexshowDAO indexshowDAO) {
        this.indexshowDAO = indexshowDAO;
        setDAO(indexshowDAO);
    }

	@Override 
    public Pager searchTbProjectInfo(Map<String, Object> searchMap, Pager pager) throws Exception, BaseException {
        return indexshowDAO .searchTbProjectInfo(searchMap, pager);
    }
    
    @Override
    public List<TbProjectInfoVO> searchTbProjectInfoList(Map<String, Object> searchValue) throws SQLException {
        return indexshowDAO .searchTbProjectInfoList(searchValue);
    }

    /**
     * 删除对象，只需要对象里的主键有值即可
     *
     * @param userPO
     * @throws Exception
     */
    public void delPO(TbProjectInfoPO po) throws Exception, ObjectNotFoundException, CannotSafeDelException {
        indexshowDAO .delete(po);
    }

    @Override
    public void casAdd(TbProjectInfoPO po) throws Exception, DataConfictException {
        indexshowDAO .insert(po);
    }

    @Override 
    public List<TbProjectInfoPO> casQuery(String[] fields, Object[] values, String appendSql) throws Exception, EncryptionUtils.EncryptionException {
        return indexshowDAO .searchByField(TbProjectInfoPO.class, fields, values, appendSql);
    }

    @Override
    public void casDel(String id) throws Exception, ObjectNotFoundException, CannotSafeDelException {
        TbProjectInfoPO po = new TbProjectInfoPO();
        po._setPKValue(id);
        indexshowDAO .delete(po);
    }

	@Override
	public String updateUserInfo(TbProjectInfoPO po) throws Exception, BaseException {
		String msg = "";
	try {
		this.updatePO(po, false);
	} catch (SQLException e) {
		e.printStackTrace();
		throw new Exception("操作数据库失败！");
	}
		return msg;
	}
	
	@Override
	public TbProjectInfoVO getInfoById(String id) throws Exception {
		return this.indexshowDAO.getInfoById(id);
	}
}


