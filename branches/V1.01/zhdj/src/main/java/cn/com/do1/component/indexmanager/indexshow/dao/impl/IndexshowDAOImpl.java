package cn.com.do1.component.indexmanager.indexshow.dao.impl;
import cn.com.do1.common.framebase.dqdp.BaseDAOImpl;
import cn.com.do1.component.basis.partymenber.vo.PartyMenberInfoVO;
import cn.com.do1.component.indexmanager.indexshow.dao.IIndexshowDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.com.do1.component.indexmanager.indexshow.vo.TbProjectInfoVO;
import cn.com.do1.common.annotation.po.Security;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: zhanqiuxiang
*/
public class IndexshowDAOImpl extends BaseDAOImpl implements IIndexshowDAO {
    private final static transient Logger logger = LoggerFactory.getLogger(IndexshowDAOImpl .class);
    
    final static String searchSQL = "select t.* from TB_PROJECT_INFO t where t.pro_name = :proName  order by create_time desc";
    final static String countSQL = "select count(1) from (" + searchSQL.replaceAll("(?i)\\basc\\b|\\bdesc\\b", "").replaceAll("(?i)order\\s+by\\s+\\S+(\\s*[,\\s*\\S+])*", "")+"  ) a ";
    
	@Override
    public Pager searchTbProjectInfo(Map searchValue, @Security(encode = "")Pager pager) throws Exception, BaseException {
        /**
         * 自己写好查询总页数以及查询语句后调用框架方法
         * TbDqdpUserPO 查询结果封装类
         * countSQL统计总条数的语句
         * searchSQL 查询数据的语句
         * searchValue 查询条件
         * pager分页信息
         */
        return super.pageSearchByField(TbProjectInfoVO.class, countSQL, searchSQL, searchValue, pager);
    }

    @Override
    public List<TbProjectInfoVO> searchTbProjectInfoList(Map<String, Object> searchValue) throws SQLException {
        preparedSql(searchSQL);
        setPreValues(searchValue);//将参数设置进预置语句
        return getList(TbProjectInfoVO.class);
    }
    
    @Override
	public TbProjectInfoVO getInfoById(String id) throws Exception {
		String sql = "select t.* from TB_PROJECT_INFO t where t.id=:id";
		super.preparedSql(sql);
		super.setPreValue("id", id);
		return super.executeQuery(TbProjectInfoVO.class);
	}

}
