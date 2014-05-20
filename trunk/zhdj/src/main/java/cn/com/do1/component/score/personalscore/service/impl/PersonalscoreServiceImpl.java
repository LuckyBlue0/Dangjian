package cn.com.do1.component.score.personalscore.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseService;
import cn.com.do1.component.score.personalscore.dao.IPersonalscoreDAO;
import cn.com.do1.component.score.personalscore.model.TbPersonalScoreInfoPO;
import cn.com.do1.component.score.personalscore.service.IPersonalscoreService;
import cn.com.do1.component.score.personalscore.vo.PersonalScoreInfoVO;
import cn.com.do1.component.score.personalscore.vo.PersonalScoreVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: ${user}
 */
@Service("personalscoreService")
public class PersonalscoreServiceImpl extends BaseService implements
		IPersonalscoreService {
	private final static transient Logger logger = LoggerFactory
			.getLogger(PersonalscoreServiceImpl.class);

	private IPersonalscoreDAO personalscoreDAO;

	@Resource
	public void setPersonalscoreDAO(IPersonalscoreDAO personalscoreDAO) {
		this.personalscoreDAO = personalscoreDAO;
		setDAO(personalscoreDAO);
	}

	@Override
	public TbPersonalScoreInfoPO getPersonalScoreInfoByUserId(String userId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePersonalscorePO(TbPersonalScoreInfoPO po) throws Exception,
			BaseException {
		// TODO Auto-generated method stub

	}

	@Override
	public Pager searchPersonalscore(Map<?, ?> searchMap, Pager pager)
			throws Exception, BaseException {
		return this.personalscoreDAO.searchPersonalscore(searchMap, pager);
	}

	@Override
	public Pager searchPersonalscoreInfo(Map<?, ?> searchMap, Pager pager)
			throws Exception, BaseException {
		return this.personalscoreDAO.searchPersonalscoreInfo(searchMap, pager);
	}

	@Override
	public PersonalScoreVO getPersonalScoreByUserId(String userId)
			throws Exception {
		return this.personalscoreDAO.getPersonalScoreByUserId(userId);
	}

	@Override
	public void updatePersonalscore() throws Exception, BaseException {
		try {
			String psersonCleanSQL = "update tb_personal_score s set s.status = 0 where s.status = 1 ";
			/** 影响：删除积分详情数据 **/
			String cleanLeavePsersonSQL = "delete from tb_personal_score_info i where i.user_id not in(select m.id from tb_party_menber_info m)";
			String psersonScoreSQL = "insert into tb_personal_score(id,user_id,accumulative_score,ranking,organization_id,branch_ranking,create_time,status) select sys_guid(),psi.user_id,sum(psi.score),1,organization_id,1,sysdate,1 from tb_personal_score_info psi left join tb_party_menber_info p on psi.user_id = p.id group by psi.user_id,organization_id ";
			String psersonRankingSQL = "update tb_personal_score ps set ps.ranking = (select t.ranking from (select s.accumulative_score,(select count(t.user_id) + 1 from tb_personal_score t where t.accumulative_score > s.accumulative_score and t.status =1) ranking from tb_personal_score s group by s.accumulative_score order by s.accumulative_score desc)t where ps.accumulative_score = t.accumulative_score) where ps.status = 1 ";
			String psersonBranchRankingSQL = "update tb_personal_score ps set ps.branch_ranking = (select t.ranking from (select s.organization_id,s.accumulative_score,(select count(t.user_id) + 1 from tb_personal_score t where t.accumulative_score > s.accumulative_score and t.organization_id = s.organization_id and t.status =1) ranking from tb_personal_score s group by s.organization_id,s.accumulative_score order by s.organization_id,s.accumulative_score desc)t where ps.accumulative_score = t.accumulative_score and t.organization_id = ps.organization_id) where ps.status = 1 ";
			String psersonLeaveSQL = "update tb_personal_score s set s.leave = (select t.id from tb_score_leave t where t.type = 1 and t.start_score = (select max(t.start_score) from tb_score_leave t where t.type = 1 and t.start_score <= s.accumulative_score )) where s.status = 1 ";
			String psersonYesterdaySQL = "update tb_personal_score s set s.yesterday_get_score = (select sum(i.score) from tb_personal_score_info i where trunc(i.get_time) = trunc(sysdate-1) and i.user_id = s.user_id) where s.status = 1 ";

			String orgCleanSQL = "update tb_organization_score s set s.status = 0 where s.status = 1 ";
			/** 影响：删除积分详情数据 **/
			String cleanLeaveOrgSQL = "delete from tb_organization_score_info i where i.organization_id not in(select o.id from tb_organization o)";
			String orgScoreSQL = "insert into tb_organization_score(id,organization_id,accumulative_score,ranking,status) select sys_guid(),o.id,case when sum(osi.score) is null then 0 else sum(osi.score) end,1,1 from tb_organization o left join tb_organization_score_info osi on osi.organization_id = o.id group by o.id";
			String orgRankingSQL = "update tb_organization_score ps set ps.ranking = (select t.ranking from (select s.accumulative_score,(select count(t.id) + 1 from tb_organization_score t where t.accumulative_score > s.accumulative_score and t.status =1) ranking from tb_organization_score s group by s.accumulative_score order by s.accumulative_score desc)t where ps.accumulative_score = t.accumulative_score) where ps.status = 1 ";
			String orgLeaveSQL = "update tb_organization_score  s set s.leave = (select t.id from tb_score_leave t where t.type = 2 and t.start_score = (select max(t.start_score) from tb_score_leave t where t.type = 2 and t.start_score <= s.accumulative_score )) where s.status = 1 ";

			this.personalscoreDAO.executeSql(psersonCleanSQL); // 作废个人积分数据
			this.personalscoreDAO.executeSql(cleanLeavePsersonSQL); // 删除系统不存在用户的积分信息
			this.personalscoreDAO.executeSql(psersonScoreSQL); // 统计个人总积分
			this.personalscoreDAO.executeSql(psersonRankingSQL); // 更新个人总排名
			this.personalscoreDAO.executeSql(psersonBranchRankingSQL); // 更新个人组织内排名
			this.personalscoreDAO.executeSql(psersonLeaveSQL); // 更新个人积分星级
			this.personalscoreDAO.executeSql(psersonYesterdaySQL); // 更新昨日获得积分

			this.personalscoreDAO.executeSql(orgCleanSQL); // 作废组织积分数据
			this.personalscoreDAO.executeSql(cleanLeaveOrgSQL); // 删除系统不存在组织的积分信息
			this.personalscoreDAO.executeSql(orgScoreSQL); // 统计组织总积分
			this.personalscoreDAO.executeSql(orgRankingSQL); // 更新组织积分排名
			this.personalscoreDAO.executeSql(orgLeaveSQL); // 更新组织积分星级
		} catch (Exception e) {
			logger.error("统计积分排名星级时发生错误：" + e.getMessage(), e);
			throw new Exception("统计积分排名星级时发生错误：" + e.getMessage());
		}
	}

	@Override
	public List<PersonalScoreVO> getPersonalScore(Map<?, ?> searchMap)
			throws Exception, BaseException {
		return this.personalscoreDAO.getPersonalScore(searchMap);
	}

	@Override
	public List<PersonalScoreInfoVO> getPersonalScoreInfo(Map<?, ?> searchMap)
			throws Exception, BaseException {
		return this.personalscoreDAO.getPersonalScoreInfo(searchMap);
	}

	@Override
	public PersonalScoreInfoVO getPersonalScoreInfoByid(String id)
			throws Exception {
		return this.personalscoreDAO.getPersonalScoreInfoByid(id);
	}

}
