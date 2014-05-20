<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">




<script>var baseURL="/decaiedu";</script>
<div id="id_dqdp_tip" style="position: absolute;"></div>















<script type="text/javascript">var _dqdp_dict={'advertTypes':{'1':{desc:"内部无链接广告"},'2':{desc:"内部有链接广告"},'3':{desc:"外部广告"}}}; var _dqdp_permissions={};</script>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>活动广告信息编辑</title>
    <link href="/dqdp-web/themes/default/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="/dqdp-web/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="/dqdp-web/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="/dqdp-web/js/do1/common/common.js"></script>

	<%--<script src="${baseURL}/js/do1/common/imgPreview/CJL.0.1.min.js"></script>--%>
	<%--<script src="${baseURL}/js/do1/common/imgPreview/QuickUpload.js"></script>--%>
	<%--<script src="${baseURL}/js/do1/common/imgPreview/ImagePreviewd.js"></script>--%>
</head>

<body>
<form action="${baseURL}/advertmgr/advertmgr!ajaxUpdate.action" method="post" id="advert_update_form">
    <table class="tableCommon mt5" width="100%" border="0" cellspacing="0" cellpadding="0"
    		dataSrc="local:v1">
        <thead>
            <tr>
                <th colspan="4">活动广告信息编辑</th>
            </tr>
        </thead>
        <tbody>
            <input type="hidden" name="dcAdvertisingPO.adId"/>
            <tr>
                <td width="120" class="tdBlue">广告名称：</td>
                <td valign="top">
                	<input style="width: 300px" type="text" name="dcAdvertisingPO.adTitle" valid="{must:true,length:200,tip:'广告名称'}" />
                	<span><font color="red">*</font> 最长200个字符或100个汉字</span>
                </td>
            </tr>
            <tr>
                <td width="120" class="tdBlue">广告内容：</td>
				<td colspan="4" valign="top">
                	<textarea name="dcAdvertisingPO.adContent" style="width: 400px;height:50px" valid="{must:false,length:1000,tip:'广告内容'}"></textarea>
                	<span><font color="red"></font> 最长1000个字符或500个汉字</span>
                </td>
            </tr>
            <tr>
                <td width="120" class="tdBlue">广告类型：</td>
                <td valign="top">
					<select id="dcAdvertising_adType" class="form80px" name="dcAdvertisingPO.adType" dictType="advertTypes" valid="{must:true,tip:'广告类型'}"></select>
                	<span><font color="red">*</font></span>
                </td>
            </tr>
            <tr>
                <td width="120" class="tdBlue">图片链接地址：</td>
                <td valign="top">
                	<input style="width: 300px" type="text" name="dcAdvertisingPO.adUrl"/>
                	<span> 最长200个字符或100个汉字</span>
                </td>
            </tr>
            <tr>
                <td width="120" class="tdBlue">广告图片：</td>
				<td valign="top">
					<input id="idFile" name="dcAdvertisingVO.image" type="file" valid="{must:false,length:500,tip:'广告图片'}"/>
                	<span><font color="red"></font> 图片的格式为：jpg、gif、png、bmp，图片最大为10M</span>
					</br>
                	<img src="${baseURL}/images/index/" name="dcAdvertisingPO.imageUrl" width="200"/>
                	&nbsp<img id="idImg" />
                </td>
            </tr>
        </tbody>
    </table>
</form>

<div class="toolBar">
    <div align="center">
        <input class="btn4" type="button" onclick="doSave();" value="保存"/>
        <input class="btn4" type="button" onclick="javascript:history.back();" value="返 回"/>
    </div>
</div>

<!--工具栏 end-->
<script type="text/javascript">	


var local = "/decaiedu/advertmgr/advertmgr!searchAdvertByID.action?id=08609c52-d772-4779-97ca-e7d3b03d5f56";
//调用js得到查询结果
var v1 = {"dcAdvertisingPO":{"_adStatus_desc":"草稿","createTime":"2012-07-17 10:49:00","adStatus":"0","imageUrl":"364094ed-e77a-4525-8400-2e439fcf24b4.jpg","adId":"08609c52-d772-4779-97ca-e7d3b03d5f56","_adType_desc":"外部广告","adContent":"ssss","adType":"3","adFlag":"1","adTitle":"tesst","adUrl":""}};

	$(document).ready(function() {
		//HiddenTr();
		//$('#dcAdvertising_adType').bind('change', HiddenTr);
	});

	/**
	* 隐藏或显示表格的图片链接行
	*/
	function HiddenTr(){
		var str = document.getElementById("dcAdvertising_adType");
		var num = str.options[str.selectedIndex].value;
		var tempTable=document.getElementsByTagName("table")[0];//表示页面中第几个表格，在页面中从上往下数
		var tempTd=tempTable.getElementsByTagName("tr")[4];
		if(num=="3"){
			tempTd.style.display="";
		}
		else{
			tempTd.style.display="none";
		}
	}

	function doSave() {
    	var dqdp = new Dqdp();
    	if (dqdp.submitCheck("advert_update_form")) {
			$('#advert_update_form').ajaxSubmit( {
				dataType : 'json',
				success : function(result) {
					if ("0" == result.code) {
						alert(result.desc);
						window.location.href = "advert_list.jsp";
					} else {
						alert(result.desc);
					}
				},
				error : function() {
					alert('修改失败，请检查上传图片的格式是否符合要求');
				}
			});
		}
    }
</script>
</body>
</html>