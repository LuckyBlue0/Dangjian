<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="cn.com.do1.component.dqdploger.log.helper.LogHelper" %>
<%
    String path = WebUtil.getParm(request,"fileName","");
    String realPath= LogHelper.getFileRealPath(path);
    InputStream inStream = new FileInputStream(realPath);
    response.reset();
    response.setContentType("text/html");
    response.addHeader("Content-Disposition", "attachment; filename=\"" + path + "\"");
//            if ("multipart/form-data".equals("bin")) {
//                response.addHeader("Content-Disposition", "attachment; filename=\"" + "download" + postfix + "\"");
//            }
    byte[] b = new byte[1024];
    int len;
    ServletOutputStream sos = response.getOutputStream();
    while ((len = inStream.read(b)) > 0) {
        sos.write(b, 0, len);
    }
    inStream.close();
%>