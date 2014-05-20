<%@ page import="cn.com.do1.common.dictionary.DictOperater" %>
<%@ page import="cn.com.do1.common.dictionary.TypeObj" %>
<%@ page import="cn.com.do1.common.dictionary.vo.ExItemObj" %>
<%@ page import="cn.com.do1.common.util.reflation.ConvertUtil" %>
<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    request.setCharacterEncoding("UTF-8");
    String type = request.getParameter("type");
    String classID = request.getParameter("classID");
    String typeID = request.getParameter("typeID");
    String itemID[] = WebUtil.outString(request.getParameter("itemID")).split("~");
    int level = ConvertUtil.getInt(request.getParameter("level")) + 1;
//  System.out.println(WebUtil.transferAllParm(request));
    StringBuilder sb = new StringBuilder();
    if ("class".equals(type)) {
        ArrayList list = DictOperater.getTypeByClass(classID);
        for (int i = 0; i < list.size(); i++) {
            TypeObj obj = (TypeObj) list.get(i);
            if (i > 0) sb.append("@^@");
            sb.append("text:").append(obj.getTypeDesc()).append(";");
            sb.append("icon:book.gif;expandIcon:bookopen.gif;");
            sb.append("mainID:").append(obj.getTypeID()).append(";");
            sb.append("hasChild:true;");
            sb.append("posion:").append((i == list.size() - 1) ? "2" : (i == 0) ? "1" : "1").append(";");
//            sb.append("expandMethod:cutExpand( this,'type','" + obj.getTypeID() + "'," + level + ");");
            sb.append("expandMethod:doExpand( this,'type=type&classID=").append(classID).append("&typeID=").append(obj.getTypeID()).append("',").append(level).append(");");
        }
    }
    if ("type".equals(type)) {
        List list = DictOperater.getChildItemByType(typeID);
        for (int i = 0; i < list.size(); i++) {
            ExItemObj obj = (ExItemObj) list.get(i);
            String stat = obj.isEnable() ? "true" : "false";
            String text = obj.isEnable() ? obj.getFsItemDesc() : "<font color=#cccccc>" + WebUtil.outString(obj.getFsItemDesc()) + "</font>";
            if (i > 0) sb.append("@^@");
            sb.append("text:").append(text).append("(<font color=red>").append(obj.getFsItemCode()).append("</font>)").append(";");
            sb.append("icon:cust2.gif;expandIcon:cust2.gif;");
            sb.append("mainID:").append(typeID).append("~").append(obj.getFsItemCode()).append(";");
            sb.append("hasChild:").append(obj.hasChild() ? "true" : "false").append(";");
            sb.append("posion:").append((i == list.size() - 1) ? "2" : (i == 0) ? "1" : "1").append(";");
            sb.append("expandMethod:doExpand( this,'type=item&classID=").append(obj.getFsClass()).append("&typeID=").append(typeID).append("&itemID=").append(typeID).append("~").append(obj.getFsItemCode()).append("',").append(level).append(");");
            sb.append("onclick:doChoose('").append(typeID).append("','").append(obj.getFsItemCode()).append("','").append(stat).append("');");
        }
    }
    if ("item".equals(type)) {
        List list = DictOperater.getItemByParent(itemID[0], itemID[1]);
        for (int i = 0; i < list.size(); i++) {
            ExItemObj obj = (ExItemObj) list.get(i);
            String stat = obj.isEnable() ? "true" : "false";
            String text = obj.isEnable() ? obj.getFsItemDesc() : "<font color=#cccccc>" + WebUtil.outString(obj.getFsItemDesc()) + "</font>";
            if (i > 0) sb.append("@^@");
            sb.append("text:").append(text).append("(<font color=red>").append(obj.getFsItemCode()).append("</font>)").append(";");
            sb.append("icon:cust2.gif;expandIcon:cust2.gif;");
            sb.append("mainID:").append(typeID).append("~").append(obj.getFsItemCode()).append(";");
            sb.append("hasChild:").append(obj.hasChild() ? "true" : "false").append(";");
            sb.append("posion:").append((i == list.size() - 1) ? "2" : (i == 0) ? "1" : "1").append(";");
            sb.append("expandMethod:doExpand( this,'type=item&classID=").append(obj.getFsClass()).append("&typeID=").append(typeID).append("&itemID=").append(typeID).append("~").append(obj.getFsItemCode()).append("',").append(level).append(");");
            sb.append("onclick:doChoose('").append(typeID).append("','").append(obj.getFsItemCode()).append("','").append(stat).append("');");
        }
    }
    out.print(sb.toString());
%>
