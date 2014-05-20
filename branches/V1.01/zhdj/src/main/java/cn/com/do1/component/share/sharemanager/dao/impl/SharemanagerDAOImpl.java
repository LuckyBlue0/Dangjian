package cn.com.do1.component.share.sharemanager.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.common.annotation.po.Security;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.mobileclient.userinfo.vo.TbReplyVO;
import cn.com.do1.component.share.sharemanager.dao.ISharemanagerDAO;
import cn.com.do1.component.share.sharemanager.vo.TbShareInfoVO;

/**
 * Copyright &copy; 2010 广州市道一信息技术有限公司 All rights reserved. User: zhanqiuxiang
 */
public class SharemanagerDAOImpl extends BaseDAOImpl implements
		ISharemanagerDAO {
	private final static transient Logger logger = LoggerFactory
			.getLogger(SharemanagerDAOImpl.class);

	final static String searchSQL = "select t.* from TB_SHARE_INFO t where t.title = :title and  t.type = :type order by create_time desc";
	final static String countSQL = "select count(1) from ("
			+ searchSQL.replaceAll("(?i)\\basc\\b|\\bdesc\\b", "").replaceAll(
					"(?i)order\\s+by\\s+\\S+(\\s*[,\\s*\\S+])*", "") + "  ) a ";

	@Override
	public Pager searchTbShareInfo(Map searchValue,
			@Security(encode = "") Pager pager) throws Exception, BaseException {
		/**
		 * 自己写好查询总页数以及查询语句后调用框架方法 TbDqdpUserPO 查询结果封装类 countSQL统计总条数的语句
		 * searchSQL 查询数据的语句 searchValue 查询条件 pager分页信息
		 */
		return super.pageSearchByField(TbShareInfoVO.class, countSQL,
				searchSQL, searchValue, pager);
	}

	@Override
	public List<TbShareInfoVO> searchTbShareInfoList(
			Map<String, Object> searchValue) throws SQLException {
		preparedSql(searchSQL);
		setPreValues(searchValue);// 将参数设置进预置语句
		return getList(TbShareInfoVO.class);
	}

	@Override
	public TbReplyVO getTbReplyMaxNum(String id ) throws SQLException {
		String sql = "select * from (select  * from tb_reply_info t where t.post_id='"+id+"'  order by t.on_num desc ) tt where rownum=1";
		return executeQuery(sql, TbReplyVO.class);
	}
}
