<%@ page import="cn.com.do1.common.util.web.WebUtil" %>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="permission" value="hotNewsEdit"></jsp:param>
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
<form action="${baseURL}/news/hotNewsInfo/hotNewsInfoAction!ajaxSave.action" method="post" id="id_form_add">
      <div class="searchWrap">
            <div class="title">
                <h2 class="icon1">热点新闻信息编辑</h2>
            </div>
        </div>
    <div class="text_bg noneborder" id="bt" >
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0" dataSrc="${baseURL}/news/hotNewsInfo/hotNewsInfoAction!ajaxView.action?id=${param.id}">
        <tbody>
		        <tr>
		            <td class="tdBlue"><span style="color: red">*</span>&nbsp;标题</td>
		            <td>
		            	<input type="hidden" id="hotNewsId" name="tbHotNewsPO.id"/>
		                <input type="text" id="title" name="tbHotNewsPO.title" valid="{must:true,tip:'标题',fieldType:'pattern'}"/>&nbsp;&nbsp;&nbsp;
		            </td>
		        </tr>
		        <tr>
		            <td width="120" class="tdBlue"><span style="color: red">*</span>&nbsp;封面图片</td>
		            <td>
		            	<img src="" id="pic" height="150" width="200" alt="" />
                        <input type="hidden" id="path" name="tbHotNewsPO.imgPath" placeholder="图片建议尺寸：360像素 * 200像素" valid="{must:true,tip:'封面图片',fieldType:'pattern'}"/>
                        <span class="popbtn"><a href="#"><img src="${baseURL}/themes/default/css/images/up.png" /></a></span>
		            	(图片建议尺寸：360像素 * 200像素)
		            </td>
		        </tr>
				<tr>
		            <td class="tdBlue"><span style="color: red">*</span>&nbsp;摘要</td>
		            <td colspan="3">
		               <textarea name="tbHotNewsPO.bodyDigest" id="bodyDigest" ></textarea>
		            </td>
		        </tr>
				<tr>
		            <td class="tdBlue"><span style="color: red">*</span>&nbsp;正文</td>
		            <td colspan="3">
		               <textarea rows="6" cols="80" name="tbHotNewsPO.content" id="content" ></textarea>
		            </td>
		        </tr>
				<tr>
		            <td class="tdBlue"><span style="color: red">*</span>&nbsp;来源</td>
		            <td colspan="3">
		               <input type="text" id="source" name="tbHotNewsPO.source" valid="{must:true,tip:'来源',fieldType:'pattern'}"/>&nbsp;&nbsp;&nbsp;
		            </td>
		        </tr>
        </tbody>
    </table>
	</div>
<div id="role_list"></div>


<div class="toolBar">
    <div align="center">
    	<input class="greenbtn mr10" type="button" id="save" permission="hotNewsEdit" value="保存"/>&nbsp;
    	<input class="greenbtn mr10" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>
<!--弹出层-->
<div class="overlay" style="height: 1083px;"></div>
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
      document.getElementById("save").disabled = false;
      
      if($("#imgPath").val() != null){
         $("#img").css("display","");
         $("#img").attr("src","${baseURL}/"+$("#imgPath").val());
      }
      $("#pic").attr("src","${baseURL}/"+$("#path").val());
      
	  var editor1; 
	  KindEditor.ready(function(K) {
			editor1 = K.create('textarea[name="tbHotNewsPO.content"]', {
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
	            $('#id_form_add').find('textarea[name="tbHotNewsPO.content"]').val(K('textarea[name="tbHotNewsPO.content"]').val());
	        }  
		})});
      	flashIframe_GDZC();
    });
    
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
                    window.location.href="${baseURL}/jsp/component/news/hotnewsinfo/list.jsp";
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
	   $('#id_form_add').attr("action","${baseURL}/news/hotNewsInfo/hotNewsInfoAction!fileUpload.action");
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
	     $('#id_form_add').attr("action","${baseURL}/news/hotNewsInfo/hotNewsInfoAction!ajaxSave.action");
	     flashIframe_GDZC();
   }
   
    function flashIframe_GDZC(){
    	var i=parent.document.getElementById("ifm");
    	if(i!=null)
    	{	
    		if(document.body.scrollHeight<700)
    		{
    			i.style.height ="730px";
    		}else
    		{
    			i.style.height = (document.body.scrollHeight+30)+"px";
    		}
    	}
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
