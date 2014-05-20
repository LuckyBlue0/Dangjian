<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="newsProjectType"></jsp:param>
    <jsp:param name="permission" value="newsManager,newsEdit"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/imgslide.css" rel="stylesheet" type="text/css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js"></script>
    <script src="${baseURL}/js/DatePicker/WdatePicker.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/3rd-plug/kindeditor/kindeditor.js"></script>
    <script src="${baseURL}/js/3rd-plug/kindeditor/lang/zh_CN.js"></script>
    <script type="text/javascript" src="${baseURL}/js/popWin.js"></script>
    <script type="text/javascript" src="${baseURL}/js/imgslide.js"></script>
    <script type="text/javascript" src="${baseURL}/js/tab.js"></script>
    <style type="text/css">
    
    </style>
</head>

<body style="background: #f6ebe5;" >
<form action="${baseURL}/news/newsinfo/newsInfoAction!ajaxSave.action" method="post" id="id_form_add">
      <div class="searchWrap">
            <div class="title">
                <h2 class="icon1">${name}信息编辑</h2>
	           	<input type="hidden" id="newsInfoId" name="tbNewsInfoPO.newsInfoId" value="${tbNewsInfoPO.newsInfoId}"/>
	           	<input type="hidden" id="newsInfoType" name="tbNewsInfoPO.newsInfoType" value="${newsType}"/>
	           	<input type="hidden" id="status" name="tbNewsInfoPO.status" value="${tbNewsInfoPO.status}"/>
            </div>
        </div>
    <div class="text_bg noneborder" id="bt" >
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" >
        <tbody>
	        <c:forEach items="${map}" var="o">
	        	<c:if test="${o.key == 0}">
			        <tr>
			            <td class="tdBlue"><span style="color: red">*</span>&nbsp;${o.value}</td>
			            <td >
			                <input type="text" id="title" name="tbNewsInfoPO.title" value="${tbNewsInfoPO.title}" valid="{must:true,tip:'${o.value}',fieldType:'pattern'}"/>&nbsp;&nbsp;&nbsp;
			            </td>
			        </tr>
		        </c:if>
		        <c:if test="${o.key == 1}">
		        <tr>
		            <td width="120" class="tdBlue"><span style="color: red">*</span>&nbsp;${o.value}</td>
		            <td>
		            	<input type="hidden" id="projectType_hidden" value="${tbNewsInfoPO.projectType}"/>
		            	<select dictType="newsProjectType" name="tbNewsInfoPO.projectType" id="projectType" value="${tbNewsInfoPO.projectType}" valid="{must:true,tip:'${o.value}',fieldType:'pattern'}"/>
		            </td>
		        </tr>
		        </c:if>
		        <c:if test="${o.key == 2}">
		        <tr>
		            <td width="120" class="tdBlue"><span style="color: red">*</span>&nbsp;${o.value}</td>
		            <td>
		            	<!-- 
		                <input class="form120px" name="file" id="file" type="file" style="width: 400px" />
		                <input name="tbNewsInfoPO.imgPath" id="imgPath" class="form120px" value="" type="hidden"  valid="{must:true,tip:'封面图片',fieldType:'pattern'}" />
		                <c:if test="${not empty tbNewsInfoPO.imgPath}"> 
							<img id="img" src="${baseURL}/${tbNewsInfoPO.imgPath}" height="100px" width="100px" />
						</c:if>
		                <c:if test="${empty tbNewsInfoPO.imgPath}"> 
							<img id="img" src="" height="100px" width="100px" style="display:none;" />
						</c:if>
						<input type="button" value="上 传" onClick="uploadFile();" class="greenbtn mr10">&nbsp;
						-->
		            	<img src="" id="pic" height="150" width="200" alt="" />
						<input type="hidden" id="path" readonly="readonly" value="${tbNewsInfoPO.imgPath}" placeholder="图片建议尺寸：360像素 * 200像素" name="tbNewsInfoPO.imgPath" valid="{must:true,tip:'${o.value}',fieldType:'pattern'}"/>
						<span class="popbtn"><a href="#"><img src="${baseURL}/themes/default/css/images/up.png" /></a></span>
		            	(图片建议尺寸：360像素 * 200像素)
		            </td>
		        </tr>
		        </c:if>
		        <c:if test="${o.key == 3}">
		        <tr>
		            <td class="tdBlue"><span style="color: red">*</span>&nbsp;${o.value}</td>
		            <td colspan="3">
		               <input type="text" style="width:470px" name="tbNewsInfoPO.tabBar" id="tabBar" value="${tbNewsInfoPO.tabBar}" valid="{must:true,tip:'${o.value}',fieldType:'pattern'}"/>
		            </td>
		        </tr>
		        </c:if>

		        <c:if test="${o.key == 4}">
		        <tr>
		            <td class="tdBlue"><span style="color: red">*</span>&nbsp;${o.value}</td>
		            <td colspan="3">
		                <input type="text" style="width:470px" name="tbNewsInfoPO.source" id="source" value="${tbNewsInfoPO.source}" valid="{must:true,tip:'${o.value}',fieldType:'pattern'}"/>
		            </td>
		        </tr>
		        </c:if>
		        <c:if test="${o.key == 5}">
		        <tr>
		            <td class="tdBlue"><span style="color: red">*</span>&nbsp;${o.value}</td>
		            <td colspan="3">
		              <textarea rows="3" cols="80" name="tbNewsInfoPO.bodyDigest" id="bodyDigest" valid="{must:true,tip:'${o.value}',fieldType:'pattern'}">${tbNewsInfoPO.bodyDigest}</textarea>
		            </td>
		        </tr>
		        </c:if>
		        <c:if test="${o.key == 6}">
		        <tr>
		            <td class="tdBlue"><span style="color: red">*</span>&nbsp;${o.value}</td>
		            <td colspan="3">
		               <textarea rows="6" cols="80" name="tbNewsInfoPO.content" id="content" valid="{must:true,tip:'${o.value}',fieldType:'pattern'}">${tbNewsInfoPO.content}</textarea>
		            </td>
		        </tr>
		        </c:if>
	        </c:forEach>
        </tbody>
    </table>
	<div class="toolBar">
	    <div align="center">
	    	<input class="greenbtn mr10" type="button" id="save" permission="newsEdit" value="保存"/>&nbsp;
	    	<input class="greenbtn mr10" type="button" onclick="javascript:history.back();" value="返 回"/>
	    </div>
	</div>
	</div>
<div id="role_list"></div>

<div class="modal-box" >
    <div class="modal-header">
        <h3>封面图片</h3>
        <span class="modal-del"></span>
    </div>
    <div class="modal-body">
        <div class="slideTxtBox">
            <div class="hd">

                <!-- 下面是前/后按钮代码，如果不需要删除即可 -->
                <span class="arrow"><a class="next"></a><a class="prev"></a></span>

                <ul class="tab_hd"><li class="on">系统图片</li><li>本地图片</li>
            </div>
            <div class="bd">
				<div class="slideBox" datasrc="${baseURL}/news/hotNewsInfo/hotNewsInfoAction!ajaxGetRemotePic.action">
					<ul class="inline-float">
						<li name="list">
							<a href="javascript:void(0);" style="text-align: center;height: 130px;" onclick="getThisPic(this);" name="imgPath"><input type="hidden" value="@{imgPath}"/><img src="${baseURL}/@{imgPath}" alt="" style="width: 150px;height: 100px;"/><br/><span  style="display: none;"><img src="${baseURL}/themes/default/css/images/icon-right.png" alt=""/></span></a>
						</li>
						  
					</ul>  
				</div>
            </div>
			<div class="bd" style="display: none;">
				<ul >
                    <li style="border: 0;position: relative;">
                        
                        <a href="#" style="border: 0;position: relative;">
                        <img src="${baseURL}/themes/default/css/images/addpic.png" alt="" style="position:absolute"/>
                        <input type="file" name="file" id="file" onchange="uploadFile();" style="margin-left:55px;width:100px;height:100px;position:relative;filter:alpha(opacity=0);-moz-opacity:0;opacity:0;"/>
                        <input type="hidden" id="imgPath"/>
                        </a>
                         <a href="#">
                        <img id="img" src="" alt="" style="margin-left: 100px;width:150px;height:100px;  "/>
                        </a>
                    </li>

                </ul>
			</div>
        </div>
    </div>
    <div class="modal-footer">
        <span class="btn-primary btn" onclick="getImgPath();">确认</span>
        <span class="btn" onclick="closeDiv();">取消</span>
    </div>
</div>
<!--弹出层 end-->
<!--工具栏 end-->

<script type="text/javascript">

    $(document).ready(function(){
      $("#pic").attr("src","${baseURL}/"+$("#path").val());
      document.getElementById("save").disabled = false;
      initChecked();
      
      initProject();
	  var editor1; 
	  KindEditor.ready(function(K) {
			editor1 = K.create('textarea[name="tbNewsInfoPO.content"]', {
			cssPath : '${baseURL}/js/3rd-plug/kindeditor/plugins/code/prettify.css',
			uploadJson : '${baseURL}/KindEditUpload?method=upload',
			fileManagerJson : '${baseURL}/KindEditUpload?method=',
			allowFileManager : true,
			filterMode : false,
			items : [
				'bold', 'italic', 'underline','|','insertunorderedlist', 'insertorderedlist', '|','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 
				'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright','|',
				'image', 'link','insertfile'] ,
			afterCreate : function() {
				this.sync(); 
			},afterBlur:function(){ 
	            this.sync(); 
	            $('#id_form_add').find('textarea[name="tbNewsInfoPO.content"]').val(K('textarea[name="tbNewsInfoPO.content"]').val());
	        }  
		})});
    });
    
    function initProject(){
    	$("select[@name=tbNewsInfoPO.projectType] option").each(function(){
    		if($(this).val()==$("#projectType_hidden").val()){
    			$(this).attr("selected",true);
    		}
    	});
    }
    
    function initChecked(){
		 $("input[type='checkbox']").each(function(){
			if($(this).attr("value")=="1"){
				$(this).attr("checked",true);
				$(this).val(1);
			}else{
				$(this).val(0);
			}
		});
    }
    
 $('#save').click(function(){ 
	for (var i = 0; i < _editors.values().length; i++)  _editors.values()[i].sync();
    var dqdp = new Dqdp();
    if (dqdp.submitCheck("id_form_add")) {
         document.getElementById("save").disabled = true; 
         
        //提交数据
        $('#id_form_add').ajaxSubmit({
            dataType:'json',
            success:function(result) {
                if ("0"==result.code) {
                    alert('保存成功');
                    window.location.href="${baseURL}/jsp/component/news/newsinfo/list.jsp?type="+$('#newsInfoType').val();
                } else {
                    alert(result.desc);
                }
            },
            error:function(){
                alert('保存失败，请稍候再试');    
            } 
        });
    }});
 
 
  $('#push').click(function(){ 
	for (var i = 0; i < _editors.values().length; i++)  _editors.values()[i].sync();
    var dqdp = new Dqdp();
    if (dqdp.submitCheck("id_form_add")) {
         document.getElementById("save").disabled = true;
         document.getElementById("push").disabled = true; 
         $('#status').attr("checked",true);
         
        // 提交数据
        $('#id_form_add').ajaxSubmit({
            dataType:'json',
            success:function(result) {
                if ("0"==result.code) {
                    alert('发布成功');
                    window.location.href="${baseURL}/jsp/component/news/newsinfo/list.jsp?type="+$('#newsInfoType').val();
                   
                } else {
                    alert(result.desc);
                }
            },
            error:function(){
                alert('保存失败，请稍候再试');    
            } 
        });
    }});
   
  
   function uploadFile(){
	   $('#id_form_add').attr("action","${baseURL}/news/newsinfo/newsInfoAction!fileUpload.action");
	    $('#id_form_add').ajaxSubmit({
                    dataType:'json',
                    success:function (result) {
                        if ("0" == result.code) {
                            alert('文件上传成功！');  
                            $("#img").attr("src","${baseURL}/"+result.desc);
                            $("#img").css("display","");
                            $("#imgPath").val(result.desc);
                        } else {
                            alert(result.desc);
                        }
                    },
                    error:function () {
                        alert('文件上传失败，请稍候再试');  
                    }
                });
	     $('#id_form_add').attr("action","${baseURL}/news/newsinfo/newsInfoAction!ajaxSave.action");
	     
   }
   

     $(function()
    {
        $(".slideTxtBox").imgslide();
		$(".slideTxtBox").tab();
		$('.tab_hd li:contains("本地图片")').click(function()
		{
			$("li[name='list']").find("a").find("span").each(function (){
				$(this).css("display","none");
			});
			$("#imgPath").val("");
			$('.arrow').hide();
		});
		
		$('.tab_hd li:contains("系统图片")').click(function()
		{
			$("#imgPath").val("");
			$('.arrow').show();
		});
    })
    
   function closeDiv(){
	   $('.modal-box').fadeOut(function(){ $('.overlay').hide(); });
		return false;
   }
   function getImgPath(){
	   $("#pic").attr("src", "${baseURL}/"+$("#imgPath").val());
	   $("#path").val($("#imgPath").val());
	   closeDiv();
   }
   function getThisPic($this){
	  
        $($this).parent().siblings("li").each(function (){
		   $(this).find("a").find("span").each(function(){
			   $(this).css("display","none");
		   });
		   
	        });
		    $($this).parent().find("a").find("span").each(function(){
		    	$(this).css("display","none");
		    });
	   if($($this).find("span").css("display")=="none"){
		   
	   $($this).find("span").css("display","");
	   $("#imgPath").val($($this).find("input").val());
	   }else{
		   $($this).find("span").css("display","none");
		   $("#imgPath").val("");
	   }
	   
   }
</script>
</form>
</body>
</html>
