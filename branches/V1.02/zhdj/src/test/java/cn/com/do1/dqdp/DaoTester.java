package cn.com.do1.dqdp;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.dac.QuickDAC;
import cn.com.do1.common.util.DateUtil;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © 2012 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 12-8-22
 * Time: 下午5:38
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class DaoTester {
    public static void main(String[] args) {
        QuickDAC qdac = null;
               try {

                   qdac = new QuickDAC();
                   System.out.println(System.currentTimeMillis());
                   StringBuffer condSQL=new StringBuffer();
           		StringBuffer countSQL=new StringBuffer();
           		StringBuffer searchSQL=new StringBuffer();
           		condSQL.append("select d.operation_id,o.organization_description,count(1) amount from tb_dqdp_log d,tb_dqdp_user u,tb_person_organization_ref r,tb_dqdp_organization  o where d.operation_id=u.user_name");
           		condSQL.append(" and u.user_id=r.person_id and r.org_id=o.organization_id   ");
           		condSQL.append("  and d.create_time> :startTime and d.create_time< :endTime and d.operation_desc='登录成功'   group by d.operation_id,o.organization_description order by d.operation_id");
           		countSQL.append("select count(1) from (");
           		countSQL.append(condSQL);
           		countSQL.append(")");
           	    searchSQL =  condSQL;
       //            String countSQL = "select count(1) from tb_dqdp_log d where d.create_time > :t1 and d.create_time < :t2";
       //            String SQL = "select * from tb_dqdp_log d where d.create_time > :t1 and d.create_time < :t2";
                   Map<String,Object> map = new HashMap<String, Object>();
                   map.put("startTime",DateUtil.parse("2010-10-10 00:00:00", "yyyy-MM-dd HH:mm:ss"));
                   map.put("endTime", DateUtil.parse("2013-10-10 00:00:00", "yyyy-MM-dd HH:mm:ss"));
                   Pager list = qdac.pageSearchByField(StatisticsVO.class,countSQL.toString(),condSQL.toString(),map,new Pager());
                   System.out.println(System.currentTimeMillis());
                   System.out.println("aa");
               } catch (SQLException e) {
                   e.printStackTrace();
               } catch (Exception e) {
                   e.printStackTrace();
               } finally {
                   if (qdac != null) qdac.destory();
               }
    }
    public class StatisticsVO {
    	private String operationId;//操作账号
    	private String organizationDescription;//所属机构
    	private String amount;//次数
    	public String getOperationId() {
    		return operationId;
    	}
    	public void setOperationId(String operationId) {
    		this.operationId = operationId;
    	}
    	public String getOrganizationDescription() {
    		return organizationDescription;
    	}
    	public void setOrganizationDescription(String organizationDescription) {
    		this.organizationDescription = organizationDescription;
    	}
    	public String getAmount() {
    		return amount;
    	}
    	public void setAmount(String amount) {
    		this.amount = amount;
    	}

    }

}
