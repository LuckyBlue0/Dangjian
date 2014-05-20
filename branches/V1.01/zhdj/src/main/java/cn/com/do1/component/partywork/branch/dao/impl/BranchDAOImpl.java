package cn.com.do1.component.partywork.branch.dao.impl;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.component.partywork.branch.dao.IBranchDAO;
import cn.com.do1.component.partywork.branch.model.TbActivityUserPO;
import cn.com.do1.component.partywork.branch.model.TbBranchActivityPO;
import cn.com.do1.component.partywork.branch.vo.BranchActivityVO;
import cn.com.do1.component.photowall.photowall.vo.PhotoWallReviewListResponse;
import cn.com.do1.component.photowall.photowall.vo.TbPhotoWallReviewVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: ${user}
*/
public class BranchDAOImpl extends BaseDAOImpl implements IBranchDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(BranchDAOImpl.class);
	final static String condSQL = " from TB_BRANCH_ACTIVITY t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id where t.TYPE=:type and t.TITLE like :title "
			+ " and t.STATUS=:status and t.WHETHER_END=:whetherEnd and t.WHETHER_RECOMMEND=:whetherRecommend and t.ORGANIZATION_ID=:organizationId order by t.CREATE_TIME desc";
	final static String countSQL = "select count(1)  " + condSQL;
	final static String searchSQL = "select t.ID,t.TITLE,t.TYPE,t.ORGANIZATION_ID,t.WHETHER_END,t.WHETHER_RECOMMEND,t.START_TIME,t.END_TIME," + "t.ADDRESS,t.CONTENT,t.QR_CODE,t.SMS_NOTICE,t.CREATE_USER_ID,t.CREATE_TIME,"
			+ " t.STATUS,t.AUDIT_USER_ID,t.AUDIT_TIME,o.ORGANIZATION_NAME  " + condSQL;

	@Override
	public Pager pageSearchByField(Map searchMap, Pager pager)throws SQLException {
		// TODO Auto-generated method stub
		logger.info(">>>>"+searchSQL);
		return super.pageSearchByField(BranchActivityVO.class, countSQL, searchSQL, searchMap, pager);
	}
	
	
    final static String revicewSearchSQL = "select t.* from TB_BRANCH_ACTIVITY t where t.create_user_id=:userId order by t.create_time desc ";
   final static String revicewCountSQL = "select count(1) from (" + revicewSearchSQL.replaceAll("(?i)\\basc\\b|\\bdesc\\b", "").replaceAll("(?i)order\\s+by\\s+\\S+(\\s*[,\\s*\\S+])*", "")+"  ) a ";
	@Override
	public Pager getBranchActivityByUserid(Map searchValue, Pager pager)throws SQLException {
 	
 		return super.pageSearchByField(BranchActivityVO.class, revicewCountSQL, revicewSearchSQL, searchValue, pager);
	}
	@Override
	public BranchActivityVO getBranchActivityById(String id)throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select t.*,o.ORGANIZATION_NAME from TB_BRANCH_ACTIVITY t left join TB_ORGANIZATION o on t.ORGANIZATION_ID=o.id" +
				" where t.id=:id";
		this.preparedSql(sql);
		this.setPreValue("id", id);
		return this.executeQuery(BranchActivityVO.class);
	}
	
	@Override
	public List<TbBranchActivityPO>getNoEndBranchActivity()throws SQLException{
		String sql = "select t.* from TB_BRANCH_ACTIVITY t where t.end_time < sysdate";
		this.preparedSql(sql);
		return this.getList(TbBranchActivityPO.class);
	}
	@Override
	public List<BranchActivityVO>getDataByactivityId(String activityId)throws SQLException{
		String sql = "select t.* from TB_BRANCH_ACTIVITY t where t.id=:activityId";
		this.preparedSql(sql);
		this.setPreValue("activityId", activityId);
		return this.getList(BranchActivityVO.class);
	}
	 public boolean searchDataByUserId(String activityId,String userId)throws SQLException{
	    	String sql="select count(*) from TB_ACTIVITY_USER where activity_id =:activityId and user_id = :userId";
	    	super.preparedSql(sql);
	    	super.setPreValue("userId", userId);
	    	super.setPreValue("activityId", activityId);
	    	if(super.executeCount()>=1){
	    		return true;
	    	}else{
	    		return false;
	    	}
	    }
	 @Override
		public List<BranchActivityVO>searchList()throws SQLException{
			String sql = "select t.*,f.user_name from TB_BRANCH_ACTIVITY t left join tb_party_menber_info f on f.id=t.create_user_id order by t.end_time";
			this.preparedSql(sql);
			return this.getList(BranchActivityVO.class);
		}
}
