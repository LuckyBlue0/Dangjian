package cn.com.do1.component.basis.partymenber.service.impl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.common.util.string.StringUtil;
import cn.com.do1.component.basis.partymenber.dao.IPartymenberDAO;
import cn.com.do1.component.basis.partymenber.model.TbPartyMenberInfoPO;
import cn.com.do1.component.basis.partymenber.service.IPartymenberService;
import cn.com.do1.component.basis.partymenber.vo.PartyMenberInfoVO;
import cn.com.do1.component.leader.org.model.TbOrganizationVO;
import cn.com.do1.component.systemmgr.permission.service.IPermissionMgrService;
import cn.com.do1.component.systemmgr.person.model.PersonVO;
import cn.com.do1.component.systemmgr.person.model.TbDqdpPersonPO;
import cn.com.do1.component.systemmgr.person.service.IPersonService;
import cn.com.do1.component.systemmgr.user.model.TbDqdpUserPO;
import cn.com.do1.component.systemmgr.user.model.UserVO;
import cn.com.do1.component.util.Md5;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
@Service("partymenberService")
public class PartymenberServiceImpl extends BaseService implements
		IPartymenberService {
	private final static transient Logger logger = LoggerFactory
			.getLogger(PartymenberServiceImpl.class);

	private IPartymenberDAO partymenberDAO;
	private IPermissionMgrService permissionMgrService;
	private IPersonService myPersonService;

	@Resource
	public void setMyPersonService(IPersonService myPersonService) {
		this.myPersonService = myPersonService;
	}

	@Resource
	public void setPermissionMgrService(
			IPermissionMgrService permissionMgrService) {
		this.permissionMgrService = permissionMgrService;
	}

	@Resource
	public void setPartymenberDAO(IPartymenberDAO partymenberDAO) {
		this.partymenberDAO = partymenberDAO;
		setDAO(partymenberDAO);
	}

	public Pager searchPartymenber(Map searchMap, Pager pager)
			throws Exception, BaseException {
		return partymenberDAO.searchPartyMenber(searchMap, pager);
	}

	public Pager searchPartyRelation(Map searchMap, Pager pager)
			throws Exception, BaseException {
		return partymenberDAO.searchPartyRelation(searchMap, pager);
	}

	@Override
	public void savePartymenberPO(TbPartyMenberInfoPO po) throws Exception,
			BaseException {
		TbPartyMenberInfoPO partyMenberInfoPO = this.partymenberDAO
				.getPartyMenberByUserName(po.getUserName());

		if (partyMenberInfoPO != null) {
			if (!AssertUtil.isEmpty(partyMenberInfoPO.getUserName())) {
				throw new Exception("用户已存在：【" + partyMenberInfoPO.getUserName()
						+ "】");
			}
		}
		String password = po.getPassword();
		po.setPassword(new Md5().getMD5ofStr(password).trim().toLowerCase());
		po.setId(UUID.randomUUID().toString());
		po.setCreateTime(new Date());
		po.setState(1L);
		this.partymenberDAO.insertData(po);
		if ("1".equals(po.getIsManagement())) {
			PersonVO personVO = new PersonVO();
			UserVO userVO = new UserVO();
			userVO.setUserId(po.getId());
			userVO.setUserName(po.getUserName());
			userVO.setPassword(password);
			userVO.setIsInternal("1");
			// 默认权限ID
			userVO.setRoleIds("user");
			personVO.setPersonId(po.getId());
			personVO.setUserIds(po.getId() + ",");
			personVO.setPersonName(po.getName());
			personVO.setOrgId(po.getOrganizationId());
			personVO.setSex(po.getSex().toString());
			try {
				personVO.setAge(Integer.parseInt(new SimpleDateFormat("yyyy")
						.format(new Date()))
						- Integer.parseInt(po.getBirthday().substring(0, 4))
						+ "");
			} catch (Exception e) {
			}
			try {
				this.myPersonService.addPerson(personVO, userVO, new HashMap());
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}
	}

	/**
	 * partyMember 党员信息对象，要求id，userName，password不能为空
	 */
	public String updateUserInfo(TbPartyMenberInfoPO partyMember)
			throws Exception, BaseException {
		String msg = "";
		TbDqdpUserPO userVO = null;
		TbDqdpPersonPO personVO = null;
		PartyMenberInfoVO vo = this.partymenberDAO
				.getPartyMenberById(partyMember.getId());
		String userName = "";

		try {
			// 获取系统用户信息
			userVO = this.partymenberDAO.searchByPk(TbDqdpUserPO.class,
					partyMember.getId());
			personVO = this.partymenberDAO.searchByPk(TbDqdpPersonPO.class,
					partyMember.getId());
			userName = userVO.getUserName();
		} catch (Exception e) {
			logger.info("系统不存在该用户信息:" + e.getMessage());
			userName = vo.getUserName();
		}
		// 如果用户名没有改变，或者改变后的用户名不存在
		if (userName.equals(partyMember.getUserName())
				|| null == this.partymenberDAO.getPartyMenberByUserName(partyMember
						.getUserName())) {
			boolean changeUser = false; // 是否需要修改系统用户登录信息
			// 判断用户名是否改变
			if (null != userVO && !userName.equals(partyMember.getUserName())) {
				userVO.setUserName(partyMember.getUserName());
				changeUser = true;
			}
			// 判断是否需要修改密码
			if (StringUtil.isNullEmpty(partyMember.getPassword())) {
				partyMember.setPassword(null);
			} else {
				partyMember.setPassword(new Md5().getMD5ofStr(
						partyMember.getPassword()).trim().toLowerCase());
				if (null != userVO) {
					userVO.setPassword(partyMember.getPassword());
					changeUser = true;
				}
			}
			if (null != userVO) {
				// 更新dqdp person表
				personVO.setPersonName(partyMember.getName());
				if (partyMember.getSex() != null) {
					personVO.setSex(partyMember.getSex().intValue());
				}
				this.updatePO(personVO, false);
				if (changeUser) { // 更新dqdp user表
					this.updatePO(userVO, false);
				}
			}
			this.updatePO(partyMember, false);

		} else { // 如果用户名改变了，并且改变后的用户名存在
			msg = "用户名：" + partyMember.getUserName() + "已经存在！";
		}
		return msg;
	}

	public List<TbOrganizationVO> getOrganizations() throws Exception {
		return this.partymenberDAO.getOrganizations();
	}

	@Override
	public PartyMenberInfoVO getPartyMenberById(String id) throws Exception {
		return this.partymenberDAO.getPartyMenberById(id);
	}

	@Override
	public TbPartyMenberInfoPO getPartyMenberByUserName(String userName)
			throws Exception {
		return this.partymenberDAO.getPartyMenberByUserName(userName);
	}

	@Override
	public void updateHisPartyMenberState() throws Exception {
		String sql = "update tb_party_menber_info t set t.state = 0 where t.user_type = 3";
		this.partymenberDAO.executeSql(sql);
		
	}
}
