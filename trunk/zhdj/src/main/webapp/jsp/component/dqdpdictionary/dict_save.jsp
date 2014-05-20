<%@ page import="cn.com.do1.common.dictionary.DictOperater" %>
<%@ page import="cn.com.do1.common.dictionary.vo.ExItemObj" %>
<%@ page import="cn.com.do1.common.dictionary.vo.ItemObj" %>
<%@ page import="cn.com.do1.common.exception.BaseException" %>
<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
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
    boolean modify = "modify".equals(action);
    ItemObj itemObj = new ItemObj();
    SysLogObj logObj = null;
    try {
        itemObj.getValueFromRequest(request);
        ExItemObj oldObj = null;
        try {
            oldObj = DictOperater.getItem(type, itemObj.getFsItemCode());
        } catch (BaseException e) {
        }
        if (!modify) {
            logObj = SysLog.logFail(request, "611", 1);
            if (oldObj != null) throw new IllegalParmException("新添加的字典项代码与本分类下的[" + oldObj.getFsItemDesc() + "]字典项重复");
            String cls = DictOperater.getClassByType(type);
            String typeDesc = DictOperater.getTypeDesc(type);
            itemObj.setFsDicItemID(Uuid.create().toString());
            itemObj.setFsClass(cls);
            itemObj.setFsType(type);
            itemObj.setFsTypeDesc(typeDesc);
            itemObj.setFiLevel("0");
            itemObj.setFsStatus("0");
            if (!WebUtil.ParmIsNull(item)) {
                ExItemObj obj = DictOperater.getItem(type, item);
                itemObj.setFsParentType(obj.getFsItemCode());
                itemObj.setFiLevel(new Integer(obj.getFiLevel().intValue() + 1));
            }
        }
        DictSession dictSeesion = IFFinder.findDictSession();
        if (modify) {
            logObj = SysLog.logFail(request, "612", 2);
            itemObj.setFsDicItemID(oldObj.getFsDicItemID());
            itemObj.setFsType(oldObj.getFsType());
            oldObj.setFsItemDesc(itemObj.getFsItemDesc());
            dictSeesion.modifyItem(oldObj);
        } else dictSeesion.addItem(new ExItemObj(itemObj));
        ExItemObj lastObj = DictOperater.getItem(itemObj.getFsType(), itemObj.getFsItemCode());
        if (AssertUtil.isEmpty(lastObj.getFsParentType())) parentID = lastObj.getFsType();
        else parentID = lastObj.getFsType() + "~" + lastObj.getParent().getFsItemCode();
        SysLog.logSuccess(logObj);
        out.println("<script>" +
//                "alert('保存成功');" +
//                "opener.parent.showFrame.refreshNode('" + parentID + "');" +
//                "opener.parent.showFrame.selectNode('" + lastObj.getFsType() + "~" + lastObj.getFsItemCode() + "');" +
                "opener.parent.showFrame.document.location.href='dict_show.jsp?loadNode=" + itemObj.getFsType()+"~"+itemObj.getFsItemCode() + "';" +
                "window.close();" +
                "</script>");
    } catch (Exception e) {
        e.printStackTrace();
        out.println("<script>alert('保存失败，按确定返回');window.history.back(-1);</script>");
    }
    catch (BaseException e) {
        e.printStackTrace();
        out.println("<script>alert('" + e.getMessage() + "');window.history.back(-1);</script>");
    }
%>
</body>
</html>