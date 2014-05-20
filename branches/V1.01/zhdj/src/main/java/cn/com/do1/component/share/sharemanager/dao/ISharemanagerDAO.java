package cn.com.do1.component.share.sharemanager.dao;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.component.mobileclient.userinfo.vo.TbReplyVO;
import cn.com.do1.component.share.sharemanager.vo.TbShareInfoVO;

/**
* Copyright &copy; 2010 广州市道一信息技术有限公司
* All rights reserved.
* User: zhanqiuxiang
*/
public interface ISharemanagerDAO extends IBaseDAO {
	Pager searchTbShareInfo (Map searchValue, Pager pager) throws Exception, BaseException;

    List<TbShareInfoVO> searchTbShareInfoList(Map<String, Object> searchValue) throws SQLException;

	TbReplyVO getTbReplyMaxNum(String id) throws SQLException;

}
