package cn.com.do1.component.basis.label.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.common.util.reflation.BeanHelper;
import cn.com.do1.common.util.reflation.ClassTypeUtil;
import cn.com.do1.component.basis.label.dao.IZhdjPersonDAO;
import cn.com.do1.component.leader.org.model.TbOrganizationPO;
import cn.com.do1.component.systemmgr.person.model.TbDqdpPersonPO;
import cn.com.do1.component.systemmgr.user.model.BaseUserVO;
import cn.com.do1.component.systemmgr.user.model.TbDqdpUserPO;

/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class ZhdjPersonDAOImpl extends BaseDAOImpl implements IZhdjPersonDAO {

//	@Override
//	public void addPersonUserRef(String s, String[] as) throws SQLException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void delAllPersonUserRefByPersonId(String s) throws SQLException, BaseException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void delPersonUserRef(String s, String s1) throws SQLException, BaseException {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public BaseUserVO getBaseUserVOByPersonId(String s) throws BaseException, Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean isPersonExist(String s) throws SQLException, BaseException {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public Pager listBaseUserByRoleId(String s, Pager pager) throws Exception, BaseException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List listMyPerson(Class class1, IBaseDBVO ibasedbvo, String s, String s1, Map map) throws Exception, BaseException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Pager listMyPersonPage(Class class1, IBaseDBVO ibasedbvo, String s, String s1, Map map, Pager pager) throws Exception, BaseException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Pager listPerson(Map map, Pager pager) throws BaseException, Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Pager listPersonByOrg(Class class1, IBaseDBVO ibasedbvo, String s, Pager pager, Map map) throws Exception, BaseException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Pager listPersonByOrg(Class class1, IBaseDBVO ibasedbvo, String s, Pager pager, Map map, String s1, String s2) throws Exception, BaseException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List listUserByPersonId(String s) throws SQLException, BaseException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Pager listUserByPersonIdView(String s, Pager pager) throws BaseException, Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Object searchPersonUserInfoById(Class class1, IBaseDBVO ibasedbvo, String s) throws Exception, BaseException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Object searchPersonUserInfoByUserName(Class class1, IBaseDBVO ibasedbvo, String s) throws Exception, BaseException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <T extends IBaseDBVO> Pager pageSearchByField(Class<T> arg0, Map<String, Object> arg1, String arg2, Pager arg3) throws SQLException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <T> Pager pageSearchByField(Class<T> arg0, String arg1, String arg2, Map<String, Object> arg3, Pager arg4) throws SQLException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <T> Pager pageSearchByField(Class<T> arg0, String arg1, String arg2, Map<String, Object> arg3, Pager arg4, boolean arg5) throws SQLException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <T> List<T> searchByField(Class<T> arg0, String arg1, Map<String, Object> arg2) throws SQLException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <T> List<T> searchByField(Class<T> arg0, String arg1, Map<String, Object> arg2, boolean arg3) throws SQLException {
//		// TODO Auto-generated method stub
//		return null;
//	}

    public Pager listPerson(Map searchMap, Pager pager)
        throws BaseException, Exception
    {
        return pageSearchByField(TbDqdpPersonPO.class, getSqlByID("SQL_listPersonCount"), getSqlByID("SQL_listPerson"), searchMap, pager);
    }

    public void addPersonUserRef(String personId, String userIds[])
        throws SQLException
    {
        String arr$[] = userIds;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            String userId = arr$[i$];
            StringBuilder insertSql = new StringBuilder();
            insertSql.append("insert into tb_person_user_ref (ref_id, person_id, user_id) values ('").append(UUID.randomUUID().toString()).append("','").append(personId).append("','").append(userId).append("')");
            super.addBatch(insertSql.toString());
        }

        super.execBatch();
    }

    public void delPersonUserRef(String personId, String userId)
        throws SQLException, BaseException
    {
        super.preparedSql(getSqlByID("delPersonUserRef"));
        super.setPreValue("personId", personId);
        super.setPreValue("userId", userId);
        super.executeUpdate();
    }

    public void delAllPersonUserRefByPersonId(String personId)
        throws SQLException, BaseException
    {
        super.preparedSql(getSqlByID("SQL_delAllPersonUserRef"));
        super.setPreValue("personId", personId);
        super.executeUpdate();
    }

    public List listUserByPersonId(String personId)
        throws SQLException, BaseException
    {
        super.preparedSql(getSqlByID("SQL_listUserByPersonId"));
        super.setPreValue("personId", personId);
        return super.getList(TbDqdpUserPO.class);
    }

    public Pager listUserByPersonIdView(String personId, Pager pager)
        throws BaseException, Exception
    {
        HashMap params = new HashMap();
        params.put("personId", personId);
        return pageSearchByField(TbDqdpUserPO.class, getSqlByID("SQL_listUserByPersonIdCount"), getSqlByID("SQL_listUserByPersonId"), params, pager, true);
    }

    public Object searchPersonUserInfoByUserName(Class clz, IBaseDBVO myPersonPO, String userName)
        throws Exception, BaseException
    {
        Map searchValue = new HashMap(2);
        searchValue.put("userName", userName);
        List userList = listMyPerson(clz, myPersonPO, " and u.user_name=:userName", null, searchValue);
        if(userList != null && userList.size() == 1)
            return userList.get(0);
        else
            throw new BaseException("\u8D26\u53F7\u4E0D\u5B58\u5728\u6216\u4E0D\u552F\u4E00");
    }

    public Object searchPersonUserInfoById(Class clz, IBaseDBVO myPersonPO, String id)
        throws Exception, BaseException
    {
        Map searchValue = new HashMap(2);
        searchValue.put("personId", id);
        List userList = listMyPerson(clz, myPersonPO, " and p.person_id=:personId", null, searchValue);
        if(userList != null && userList.size() == 1)
            return userList.get(0);
        else
            throw new BaseException("\u7528\u6237ID\u4E0D\u5B58\u5728\u6216\u4E0D\u552F\u4E00");
    }

    public Pager listPersonByOrg(Class clz, IBaseDBVO myPersonPO, String orgId, Pager pager, Map searchValue)
        throws Exception, BaseException
    {
        Map sqlMap = new HashMap(2);
        sqlMap.put("po", myPersonPO);
        HashMap params = new HashMap();
        TbOrganizationPO orgPO;
        if(!AssertUtil.isEmpty(searchValue.get("orgId")))
            orgPO = (TbOrganizationPO)searchByPk(TbOrganizationPO.class, searchValue.get("orgId").toString());
        else
            orgPO = (TbOrganizationPO)super.executeQuery("select * from TB_ORGANIZATION where 1=1", TbOrganizationPO.class);
        searchValue.remove("orgId");
        params.putAll(searchValue);
//        params.put("leftvalue", orgPO.getLeftvalue());
//        params.put("rightvalue", orgPO.getRightvalue());
        return super.pageSearchByField(clz, getSqlByID("SQL_LIST_PERSON_COUNT", sqlMap), getSqlByID("SQL_LIST_PERSON_DATA", sqlMap), params, pager);
    }

    public Pager listPersonByOrg(Class clz, IBaseDBVO myPersonPO, String orgId, Pager pager, Map searchValue, String condSql, String orderSql)
        throws Exception, BaseException
    {
        Map sqlMap = new HashMap(2);
        sqlMap.put("po", myPersonPO);
        sqlMap.put("condSql", condSql);
        sqlMap.put("orderSql", orderSql);
        HashMap params = new HashMap();
        TbOrganizationPO orgPO;
        if(!AssertUtil.isEmpty(searchValue.get("orgId")))
            orgPO = (TbOrganizationPO)searchByPk(TbOrganizationPO.class, searchValue.get("orgId").toString());
        else
            orgPO = (TbOrganizationPO)super.executeQuery("select * from TB_ORGANIZATION where 1=1", TbOrganizationPO.class);
        searchValue.remove("orgId");
        params.putAll(searchValue);
//        params.put("leftvalue", orgPO.getLeftvalue());
//        params.put("rightvalue", orgPO.getRightvalue());
        return super.pageSearchByField(clz, getSqlByID("SQL_LIST_PERSON_COUNT", sqlMap), getSqlByID("SQL_LIST_PERSON_DATA", sqlMap), params, pager);
    }

    public List listMyPerson(Class clz, IBaseDBVO myPersonPO, String condSQL, String orderSQL, Map searchValue)
        throws Exception, BaseException
    {
        Map sqlMap = new HashMap(2);
        sqlMap.put("po", myPersonPO);
        sqlMap.put("condSql", condSQL);
        sqlMap.put("orderSql", orderSQL);
        return super.searchByField(clz, getSqlByID("SQL_SEARCH_PERSON_LIST", sqlMap), searchValue);
    }

    public BaseUserVO getBaseUserVOByPersonId(String personId)
        throws BaseException, Exception
    {
        BaseUserVO baseUserVO = (BaseUserVO)ClassTypeUtil.getAutoReplaceBeanInstance(BaseUserVO.class);
        TbDqdpPersonPO personPO = (TbDqdpPersonPO)searchByPk(TbDqdpPersonPO.class, personId);
        BeanHelper.copyFormatProperties(baseUserVO, personPO, true);
        super.preparedSql(getSqlByID("SQL_GET_USERVO_BY_PERSONID"));
        super.setPreValue("personId", personId);
        TbDqdpUserPO userPO = (TbDqdpUserPO)super.executeQuery(TbDqdpUserPO.class);
        BeanHelper.copyFormatProperties(baseUserVO, userPO, true);
        return baseUserVO;
    }

    public Pager listBaseUserByRoleId(String roleId, Pager pager)
        throws Exception, BaseException
    {
        HashMap params = new HashMap();
        params.put("roleId", roleId);
        return pageSearchByField(BaseUserVO.class, getSqlByID("SQL_LIST_BASEUSER_BY_ROLEID_COUNT"), getSqlByID("SQL_LIST_BASEUSER_BY_ROLEID"), params, pager, true);
    }

    public boolean isPersonExist(String personId)
        throws SQLException, BaseException
    {
        super.preparedSql(getSqlByID("SQL_IS_PERSON_EXIST"));
        super.setPreValue("personId", personId);
        return super.executeCount() > 0;
    }

    public Pager listMyPersonPage(Class clz, IBaseDBVO myPersonPO, String condSQL, String orderSQL, Map searchValue, Pager pager)
        throws Exception, BaseException
    {
        return listPersonByOrg(clz, myPersonPO, null, pager, searchValue);
    }
}

