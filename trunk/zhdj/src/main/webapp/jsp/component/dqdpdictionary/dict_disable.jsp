<%@ page import="cn.com.do1.common.dictionary.DictOperater" %>
<%@ page import="cn.com.do1.common.dictionary.vo.ExItemObj" %>
<%@ page import="cn.com.do1.common.exception.BaseException" %>
<%@ page import="cn.com.do1.common.util.AssertUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: SARON
  Date: 2005-12-15
  Time: 15:08:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Simple jsp page</title>
    <script language="javascript" src="js/comm.js"></script>
    <script language="JavaScript" src="js/disablePop.js"></script>
</head>

<body>
<%
    String type = request.getParameter("type");
    String item = request.getParameter("item");
    String action = request.getParameter("action");
    String parentID = "";
    SysLogObj logObj = null;
    try {
        ExItemObj obj = DictOperater.getItem(type, item);
        if(AssertUtil.isEmpty(obj.getFsParentType()))parentID=obj.getFsType();
        else parentID=obj.getFsType()+"~"+obj.getParent().getFsItemCode();
        if ("disable".equals(action)) {
            logObj = SysLog.logFail(request, "613", 2);
            obj.disable();
            action = "禁用";
        } else if ("enable".equals(action)) {
            logObj = SysLog.logFail(request, "614", 2);
            obj.enable();
            action = "启用";
        } else
            throw new IllegalParmException("有错误发生，请重新选择字典项再进行操作");
        SysLog.logSuccess(logObj);
        out.println("<script>" +
//                "alert('" + action + "成功');" +
                "opener.parent.showFrame.refreshNode('" + parentID + "');" +
                "opener.parent.showFrame.selectNode('" + obj.getFsType() + "~" + obj.getFsItemCode() + "');" +
                "window.close();" +
                "</script>");
    } catch (Exception e) {
        e.printStackTrace();
        out.println("<script>alert('" + action + "失败，按确定返回');window.history.back(-1);</script>");
    }
    catch (BaseException e) {
        e.printStackTrace();
        out.println("<script>alert('" + e.getMessage() + "');window.history.back(-1);</script>");
    }
%>
</body>
</html>