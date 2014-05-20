<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/jsp/web/common/dqdp_common.jsp" %>
<jsp:include page="/jsp/web/common/dqdp_vars.jsp">
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="../css/base.css" />
<link type="text/css" rel="stylesheet" href="../css/style.css" />
 <link type="text/css" rel="stylesheet" href="../css/l_style.css" />
<script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${baseURL}/js/do1/common/common.js"></script>
<script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--正文-->
<div id="main">

             <form action="${baseURL}/membercenter/membercenterAction!updateUserReport.action" method="post" id="id_form_add">
            <div class="activity_right fl mt10 pb20" datasrc="${baseURL}/membercenter/membercenterAction!viewUserReport.action?id=${param.id}">
             <input type="hidden" name="tbIdeologyReportPO.id" id="id"/>
            <input type="hidden" name="tbIdeologyReportPO.status" id="status"/>
                <div class="act_top clearfix"><span class="fl">写思想汇报</span><input type="button" value="返回列表" onclick="window.location.href='${baseURL}/jsp/web/membercenter/userReportList.jsp'" class="greenBtn3 fr mr30" /></div>
                <div class="act_bd">
                    <div class="act_tabWrap mt10">
                        <div class="pt20 pl20 pb100">
                            <dl class="clearfix">
                                <dt class="fl w4em tr lh30">标题</dt>
                                <dd class="ml5em"><input type="text" class="reginput2" placeholder="输入标题" name="tbIdeologyReportPO.title" valid="{must:true, length:200,fieldType:'pattern', tip:'标题'}"></dd>
                            </dl>
                            <dl class="clearfix mt10">
                                <dt class="fl w4em tr">正文</dt>
                                <dd class="ml5em">
                                    <textarea name="tbIdeologyReportPO.content" id="content"  class="textarea2" valid="{must:true,fieldType:'pattern', tip:'正文'}"></textarea>
                                </dd>
                            </dl>
                            <dl class="clearfix mt20">
                                <dt class="fl w4em tr">&nbsp;</dt>
                                <dd class="ml5em">
                                    <input class="btnstore" type="button" onclick="doSave(9);" value="保存草稿"/>
                                    <input class="orgBtn ml15" type="button" onclick="doSave(0);" value="保存并提交"/>
                                </dd>
                            </dl>
                        </div>
                    </div>
                </div>
            </div>
            </form>
    
</div>
<script type="text/javascript">
function doSave(type){
	$("#status").val(type);
	var dqdp = new Dqdp();
	     if (dqdp.submitCheck("id_form_add")) {
	 $('#id_form_add').ajaxSubmit({
      
            dataType:'json',
            success:function (result) {
                if ("0" == result.code) {
                	alert("修改成功！"); 
			        window.location.href="${baseURL}/jsp/web/membercenter/userReportList.jsp"; 
                } else {
                    alert(result.desc);
                }
            },
            error:function () {
            	alert("系统出错，请稍后再试!");   
            }
        });
	 }
}
</script>
<!--正文 end-->
  </head>
  </html>