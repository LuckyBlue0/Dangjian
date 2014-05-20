package cn.com.do1.component.leader.org.dao;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.leader.org.model.TbOrganizationVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public interface IOrgManageDAO extends IBaseDAO {
	/**
	 * 分页查询机构列表
	 * @param searchMap
	 * @param pager
	 * @return
	 * @throws Exception
	 * @throws BaseException
	 */
    Pager searchOrgManage(Map searchMap, Pager pager) throws Exception, BaseException;
    /**
     * 机构列表
     * @return
     * @throws SQLException
     */
    public  List<TbOrganizationVO> listOrgRoot()throws SQLException;
    /**
     * 根据机构id查找子机构是否存在
     * @param orgId
     * @return
     * @throws SQLException
     */
    public boolean findChildOrg(String orgId)throws SQLException;
    /**
     * 根据机构id查找机构下是否存在党员
     * @param orgId
     * @return
     * @throws SQLException
     */
    public boolean findOrgMember(String orgId)throws SQLException;
    /**
     * 查询机构组织委员
     * @param orgId
     * @return
     * @throws SQLException
     */
    public List<TbPartyMenberInfoPO> searchLeaderByOrgId(String orgId)throws SQLException;
    /**
     * 查询机构党务工作者
     * @param orgId
     * @return
     * @throws SQLException
     */
    public List<TbPartyMenberInfoPO> searchEditorByOrgId(String orgId)throws SQLException;
}
