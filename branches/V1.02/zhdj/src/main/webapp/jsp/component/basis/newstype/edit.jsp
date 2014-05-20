<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../../common/dqdp_common.jsp" %>
<jsp:include page="../../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="newsTypeCreateWay,newsTypeUseStatus,newsTypeAttr"></jsp:param>
    <jsp:param name="permission" value="newsTypeEdit"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>修改栏目类型</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/js/3rd-plug/jquery-ui-1.8/css/smoothness/jquery-ui-1.8.custom.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <link href="${baseURL}/themes/${style}/css/imgslide.css" rel="stylesheet" type="text/css" />
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
</head>

<body style="background: #f6ebe5;">
<form action="${baseURL}/basis/newsType/newsTypeAction!ajaxSave.action" method="post" id="id_form_add">
<div class="searchWrap">
            <div class="title">
                <h2 class="icon1">修改栏目类型</h2>
            </div>
        </div>
    <div class="text_bg noneborder" id="bt">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0"
    	dataSrc='${baseURL}/basis/newsType/newsTypeAction!ajaxView.action?newsTypeId=${param.newsTypeId}'>

        <tbody>
            <input type="hidden" name="tbNewsColumnTypePO.newsTypeId"/>
            <tr>
            	<td colspan="2">栏目基本信息</td>
            </tr>
            <tr>
                <td class="tdBlue"><span style="color: red">*</span>栏目名称</td>
                <td >
                	<input  type="text" name="tbNewsColumnTypePO.name" valid="{must:true,length:30,fieldType:'pattern',tip:'栏目名称'}" />
                	
                </td>
            </tr>
            <tr>
                <td class="tdBlue"><span style="color: red">*</span>父级栏目</td>
                <td>
                	<input type="hidden" id="parentId" name="tbNewsColumnTypePO.parentId"/>
                	<input class="form120px" id="parentNewsType" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td class="tdBlue"><span style="color: red">*</span>栏目排序</td>
                <td >
                	<input type="text" name="tbNewsColumnTypePO.orderValue" valid="{must:true, length:3,fieldType:'pattern', regex:'^\\d{0,3}$', tip:'栏目排序'}"/>
                	
                </td>
            </tr>
            <tr>
                <td class="tdBlue"><span style="color: red">*</span>使用状态</td>
                <td >
                	<select name="tbNewsColumnTypePO.useStatus" dictType="newsTypeUseStatus" valid="{must:true,length:50,tip:'使用状态'}"/>
                	
                </td>
			</tr>
            <tr>
                <td class="tdBlue">栏目说明</td>
                <td >
                	<textarea rows="4" cols="40" name="tbNewsColumnTypePO.des"></textarea>
                </td>
            </tr>
            
            <tr>
            	<td colspan="2">选择栏目属性 </td>
            </tr>
            <tr>
                <td colspan="2" id="td_attr">
                	<input type="hidden" id="attributeId"/>
                	<input type="checkbox"  dictType="newsTypeAttr" name="tbNewsColumnTypePO.attributeId"/>
                </td>
			</tr>
        </tbody>
    </table>
    
    </div>
</form>

<div class="toolBar">
    <div align="center">
        <input class="greenbtn mr10" type="button" onclick="doSave();" value="保存"/>
        <input class="greenbtn mr10" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->
<script type="text/javascript">


	
	
    $(document).ready(function () {
       init();
    });


    /**
     * 初始化标签属性数据
     */
    function init() {
    	var pojo;
        $.ajax({
            url:'${baseURL}/basis/newsType/newsTypeAction!ajaxView.action?newsTypeId=${param.newsTypeId}',
            type:'post',
            dataType:'json',
            success:function (result) {
                if ('0' == result.code) {
                    pojo = result.data.tbNewsColumnTypePO;
                    $('#attributeId').val(pojo.attributeId);
                    setChecked();
                }
            }
        });
        initParentNewsType();
        flashIframe_GDZC();
    }
    
    
    function initParentNewsType(){
       $.ajax({
            url:'${baseURL}/basis/newsType/newsTypeAction!ajaxNewsTypeList.action',
            type:'post',
            dataType:'json',
            success:function (result) {
                if ('0' == result.code) {
                    var newsTypeList = result.data.newsTypeList;
                    	for(var i=0;i<newsTypeList.length;i++){
                    		var newsType = newsTypeList[i];
                    		if($("#parentId").val() == newsType.newsTypeId){
                    			$("#parentNewsType").val(newsType.name);
                    			break;
                    		}
                    	}
                    }
                }
            });
    }
    function setChecked(){
        var attrIds = $('#attributeId').val().split(',');
        for(var i=0;i<attrIds.length;i++){
        	$('input[type="checkbox"]').each(function(){
	   			if($(this).val()==attrIds[i]){
	   				$(this).attr("checked",true);
	   			}    
	  		});    
        }
    }
    /**
    function setAttr(modelList){
    	var checkboxHTML = "";
    	for(var i=0;i<modelList.length;i++){
    		checkboxHTML=checkboxHTML+"<input type=\"checkbox\" id=\"checkId_"+modelList[i].labelModelId+"\" name=\"attrId\" value=\""+modelList[i].labelModelId+"\" onClick=\"doCheckedClick('"+modelList[i].labelModelId+"');\" />&nbsp;"+modelList[i].labelName+"\&nbsp;\&nbsp;\&nbsp;\&nbsp;&nbsp;\&nbsp;\&nbsp;";
    		if(i/5==1){
    			checkboxHTML=checkboxHTML+"</br></br>";
    		}
    	}
    	
    	$('#td_attr').append(checkboxHTML);
    	for(var i=0;i<modelList.length;i++){
    		setChecked(modelList[i].labelModelId);
    	}
    	
    }
    
    function setChecked(labelModelId){
    	var attrIds = $('#attrIds').val().split(',');
    	for(var i=0;i<attrIds.length;i++){
    		if($('#checkId_'+labelModelId).val()==attrIds[i]){
    			$('#checkId_'+labelModelId).attr("checked",true);
    		}
    	}
    }
    
    function doCheckedClick(id){
	 	var chk_value =[];    
	  	$('input[name="attrId"]:checked').each(function(){    
	   		chk_value.push($(this).val());    
	  	});    
	  	$('#attrIds').val(chk_value);
    }
    */
    
    
    function defaultAttrValue(){
       	$("input[type='checkbox']").each(function(){
   			if($(this).val()== 0 || $(this).val()== 2 || $(this).val()== 5 || $(this).val()== 6){
   				$(this).attr("checked",true);
   			}    
  		});   
    }
    function doSave() {
    	defaultAttrValue();
        _doCommonSubmit("id_form_add",null,{ok:function(){
        	//parent.location.reload();
        	history.back();
        	},
        	fail:function(){}});
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
</script>
</body>
</html>
