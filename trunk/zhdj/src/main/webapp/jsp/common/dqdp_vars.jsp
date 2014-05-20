<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="cn.com.do1.common.dictionary.DictOperater" %>
<%@ page import="cn.com.do1.common.dictionary.vo.ExItemObj" %>
<%@ page import="cn.com.do1.common.exception.BaseException" %>
<%@ page import="cn.com.do1.common.util.AssertUtil" %>
<%@ page import="cn.com.do1.common.util.string.StringUtil" %>
<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@ page import="cn.com.do1.component.systemmgr.user.model.User" %>
<%@ page import="cn.com.do1.dqdp.core.DqdpAppContext" %>
<%@ page import="org.springframework.security.core.GrantedAuthority" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%
    String dict = WebUtil.getParm(request, "dict", "");
    String permission = WebUtil.getParm(request, "permission", "");
    String mustPermission = WebUtil.getParm(request, "mustPermission", "");
    UserDetails currentUser = DqdpAppContext.getCurrentUser();
    if (!AssertUtil.isEmpty(mustPermission)) {
        if (!((User) currentUser).isAuthoritiyFor(mustPermission)) {
            try {
                request.getRequestDispatcher("/jsp/default/login/login_in.jsp").forward(request, response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    int i = 0, j = 0;
    out.print("<script type=\"text/javascript\">var _dqdp_dict={");
    if (!AssertUtil.isEmpty(dict)) {
        try {
            for (String typeName : StringUtil.splitString(dict, ",")) {
                if (j > 0) out.print(",");
                List<ExItemObj> allItemByType = DictOperater.getChildItemByType(typeName);
                out.print("'"+typeName + "':["+DictOperater.outDictJson( allItemByType)+"]");
                j++;
            }
        } catch (BaseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    out.print("};");

    out.print(" var _dqdp_permissions={");
    if (!AssertUtil.isEmpty(permission)) {
        if (!"no".equals(permission)) {
            i = 0;
            String[] permissions = StringUtil.splitString(permission, ",");
            for (GrantedAuthority grantedAuthority : currentUser.getAuthorities()) {
                if ("all".equals(permission) || StringUtil.isInContainer(permissions, grantedAuthority.getAuthority())) {
                    if (i > 0) out.print(",");
                    out.print(grantedAuthority + ":true");
                    i++;
                }
            }
        }
    }
    out.print("};");
    out.print("</script>");
%>