<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../common/dqdp_common.jsp" %>
<jsp:include page="../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="test"></jsp:param>
    <jsp:param name="permission" value="ROLE_user,abc"></jsp:param>
    <jsp:param name="mustPermission" value="ROLE_user"></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>演示-列表</title>
    <%--<link type="text/css" href="${baseURL}/themes/do1/jquery-ui/jquery.ui.all.css" rel="stylesheet"/>--%>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/do1/common/error1.0.js"></script>
    <script src="${baseURL}/js/do1/common/pop_up1.0.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/3rd-plug/jsmart/smart-2.9.min.js"></script>

</head>

<body>
<!--头部 end-->


<!--公告 end-->
<div class="searchWrap" permission="abc">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="searchLeft"></td>
            <div class="title">
                <h2 class="icon1">权限管理</h2>
            </div>
            <!--标题 end-->


        </tr>

    </table>
</div>

<!--标签选项卡 end-->

<form action="${baseURL}/demomodel/demomodel!testList.action" method="post" id="id_form_search" onsubmit="false">
    <div class="searchWrap">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td class="searchLeft"></td>
                <td class="searchBg">
                    <table class="search" width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td>
                                <table class="search" width="100%" border="0" cellspacing="0" cellpadding="0">

                                    <tr>
                                        <td width="60" height="30">测试日期：</td>
                                        <td width="140"><input class="form120px" name="searchValue.testDate" type="text" value="2012-10-10 03:00:01"/></td>

                                        <td><input class="btnQuery" name="input" type="button" value="搜索"/></td>
                                    </tr>
                                    <tr>
                                        <td width="60" height="30">测试数字：</td>
                                        <td width="140"><input class="form120px" name="searchValue.testNumber" type="text" value="sss"/></td>

                                        <td><input class="btnQuery" name="input" type="button" value="搜索" onclick="doRedraw();alert('a')"/></td>
                                    </tr>
                                    <tr>
                                        <td width="60" height="30">测试字符：</td>
                                        <td width="140"><input id="id_str" class="form120px" name="searchValue.testString" type="text" value="sss"/></td>

                                        <td><input class="btnQuery" name="input" type="button" value="搜索"/></td>
                                    </tr>
                                    <input type="checkbox" name="searchValue.abc" dictType="test" defaultValue="1,3">
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
                <td class="searchRight"></td>
            </tr>
            <tr>
                <td class="searchButtomLeft"></td>
                <td class="searchButtom"></td>
                <td class="searchButtoRight"></td>
            </tr>
        </table>
    </div>
</form>
<!--标题 end-->
<div class="pageDown" id="pageTop"></div>
<!--翻页 end-->

<div class="operation">
    <input class="btnS4" type="button" value="新增权限" onclick="javascript:window.location.href='新增权限.html'"/>
</div>

<!--工具栏 end-->
<script type="text/javascript">

    $(document).ready(function () {
        doSearch(1);
//        $("#id_dqdp_tip").dialog({modal:true, title:"提示", buttons:{确定:function () {
//            $(this).dialog('close');
//        }, 取消:function () { $(this).dialog('close'); }
//        }}).append("<div style='height:100%;width:100%;align:center;'>aaaaa</div>");
//        _showTip("hahaha1");
//        $("#id_dqdp_tip").error({title:"信息提示层",content:"xxxx",button : [{text : "确定",events : "test"},{text : "取消",events : "test"}]});
//        $("#id_dqdp_tip").error({title:"信息提示层",content:"您所在的用户组没有权限访问该目录，如需要访问请与管理员联系！",button : [{text : "确定",events : "test"},{text : "取消",events : "test"}]});
    });
    function test() {
        alert("我是测试文本");
    }
    function doSearch($pageIndex) {
        $('#id_form_search').ajaxSubmit({
            dataType:'json',
            data:{page:$pageIndex},
            success:function (result) {
                if ("0" == result.code) {
                    var list1 = new ListTable(
                            {

                                checkableColumn:"haha1",
                                title:[
                                    {width:"10%", name:"haha1", showName:"看看", isCheckColunm:true, checkAble:function (index, content) {
                                        return false;
                                    }},
                                    {width:"20%", name:"haha3", showName:"看看"},
                                    {width:"25%", name:"haha1", showName:"看看1", length:50, noSafe:true, linkPermission:"xx", href:function (index, content) {
                                        alert(content.haha1)
                                    }},
                                    {width:"25%", name:"haha1", showName:"看看2", showType:"image", href:function (index, content) {
                                        alert(content.haha2 + "3")
                                    }},
                                    {width:"25%", name:"haha4", showName:"看看4"},
                                    {width:"20%", name:"haha2", showName:"看看3", isOperationColumn:true}
                                ],
                                data:result.data.pageData,
                                operations:[
                                    {name:"查询", permission:"", condition:function (index, content) {
                                        return content.haha != ""
                                    }, event:function (index, content) {
                                        alert(content.haha1);
                                    }},
                                    {name:"删除", permission:"", condition:function (index, content) {
                                        return content.haha != "0"
                                    }, event:function (index, content) {
                                        _doSignlDel("test", content.haha1);
                                    }}
                                ],
                                trStyle:["trWhite"]
//                                ,
//                                trevent:{click:function (index, content) {
//                                    alert(content.haha);
//                                }}
                            });
                    list1.createList("test");
                    var pager = new Pager({totalPages:result.data.maxPage, currentPage:result.data.currPage, funcName:"doSearch"});
                    pager.createPageBar("idPage");
                    pager.createPageBar("pageTop");
                } else {
                    $("#tip").error({title:"信息提示层", content:result.desc, button:[
                        {text:"确定", events:"test"},
                        {text:"取消", events:"test"}
                    ]});
                }
            },
            error:function () {
            }
        });
    }
    function doLoginTest() {
        var formSubmit = document.getElementById("id_form_search");
        formSubmit.action = "";
        formSubmit.submit();
    }
</script>
<div id="test" delUrl="${baseURL}/demomodel/demomodel!batchDelete.action"
     editUrl="${baseURL}/demomodel/demomodel!testList.action">
</div>

<!--表格 end-->

<%--<div class="toolBar" permission="">--%>
<!--筛选 end-->
<div class="pageDown" id="idPage">

    <div class="pageDown" id="idPage_1" template="_template_page">

    </div>
    <!--翻页 end-->
    <div class="">
        <input class="btnS4" type="button" value="新增" onclick="javascript:document.location.href='${baseURL}/demomodel/demomodel!ajaxUpdate.action'"/>
        <input class="btnS4" type="button" value="编辑" onclick="javascript:_doEdit()"/>
        <input class="btnS4" type="button" value="删除" onclick="javascript:_doDel('test')"/>
        <input class="btnS4" type="button" value="测试登陆接口" onclick="javascript:doLoginTest()"/>
    </div>
    <!--对表格数据的操作 end-->
</div>
<div>&nbsp;
</div>
<div id="test2" permission="role_a" showOn='_isNull($("#id_str").val())'>
    <table class="tableCommon" border="0" cellSpacing="0" cellPadding="0" width="100%">
        <thead>
            <tr>
                <th width="10%"><input onclick="_doCheck(this,'ids')" type="checkbox"></th>
                <th width="20%" tdAttr="{name:'haha1'}">看看</th>
                <th width="25%">看看1</th>
                <th width="25%">看看1</th>
                <th width="20%">操作</th>
            </tr>
        </thead>
        <tbody>
            <tr class="trWhite">
                <td class="tc" width="10%"><input name="ids" value="hh1" type="checkbox"></td>
                <td class="tc">&nbsp;</td>
                <td class="tc">hh1</td>
                <td class="tc">hh1</td>
                <td class="tdOpera  tc" width="20%">
                    <a href="javascript:" onclick="">查询</a>&nbsp;&nbsp;
                </td>
            </tr>
        </tbody>
    </table>
</div>
<!-- 这里是演示如何用模板来展现列表及分页 -->
<div datasrc="local:a">
    <table width="100%" cellspacing="0" cellpadding="0" border="0" class="tableCommon">
        <thead>
            <tr>
                <th width="10%"><input type="checkbox" onclick="_doCheck(this,'ids')"></th>
                <th width="20%">看看a</th>
                <th width="25%">看看1</th>
                <th width="25%">看看1</th>
                <th width="25%">看看2</th>
                <th width="25%">看看2</th>
                <th width="20%"> 操作</th>
            </tr>
        </thead>
        <tbody>
            <tr class="trWhite|trBlack" name="pageData" style="display: none">
                <td width="10%" class="@{#:haha2<=1?'true':'tc'#}"><input type="checkbox" value="@{haha1}" _readonly="@{#:haha2<=2?'true':'false'#}" _disabled=@{#:haha2<=1?true:false#} name="ids" eval=""></td>
                <td class="tc"><span name="_index"></span></td>
                <td class="tc" name="@{.tName}" charLength="5"></td>
                <td class="tc" name=".totalRows" ></td>
                <td class="tc"><a href="javascript:" titleindex="3" type="link" name="haha2"></a></td>
                <td class="tc"><div name="ta"><span >@{..totalRows}</span></div></td>
                <td width="20%" class="tdOpera  tc">
                    <a href="javascript:alert('@{haha2}')">查询</a>&nbsp;&nbsp;
                    <a href="javascript:recourse_article.jsp?guid=@{applyvo.guid}&beforetitle=@{applyvo.beforetitle}&nexttitle=@{applyvo.nexttitle}">删除</a>&nbsp;&nbsp;</td>

            </tr>

        </tbody>
        <select name="pageData" keyForSelect="haha1" descForSelect="haha3"></select>
@{#:1+@{totalRows}#}
    </table>
    <div class="pageDown" showOn="@{totalRows}>0" template="" datasrc="">
        <a href="javascript:doUP()">上一页</a>|
        <a href="javascript:doSearch(@{currPage}+1)">下一页</a>|&nbsp;
        共@{maxPage}页<span class="font048">
            第 <input type="text" value="@{currentPage}" id="id_idPage_page3" class="form24px border999"> 页</span>
        <input type="button" onclick="_pageGo(@{maxPage},$('#id_idPage_page3').val(),'doSearch')" value="转到" class="btnQuery">
    </div>
    <div class="pageDown" showOn="@{totalRows}>0">
        <a href="#">上一页</a>|<a href="#">下一页</a>|&nbsp;共0页<span class="font048"></span>
    </div>
    <select id="XXXXE" dictType="test" defaultTip="" name="currPage">
    </select>@{currPage}
</div>
<!-- 如何用模板来展现列表及分页演示结束 -->
<div>&nbsp;<br/><br/></div>
<div id="tabs">
    <ul>
        <li><a href="#tabs-1">Nunc tincidunt</a></li>
        <li><a href="#tabs-2">Proin dolor</a></li>
        <li><a href="#tabs-3">Aenean lacinia</a></li>
    </ul>
    <div id="tabs-1">
        <p>Proin elit arcu, rutrum commodo, vehicula tempus, commodo a, risus. Curabitur nec arcu. Donec sollicitudin mi sit amet mauris. Nam elementum quam ullamcorper ante. Etiam aliquet massa et lorem. Mauris dapibus lacus auctor risus. Aenean tempor ullamcorper leo. Vivamus sed magna quis ligula eleifend adipiscing. Duis orci. Aliquam sodales tortor vitae ipsum. Aliquam nulla. Duis aliquam molestie erat. Ut et mauris vel pede varius sollicitudin. Sed ut dolor nec orci tincidunt interdum. Phasellus ipsum. Nunc tristique tempus lectus.</p>
    </div>
    <div id="tabs-2">
        <p>Morbi tincidunt, dui sit amet facilisis feugiat, odio metus gravida ante, ut pharetra massa metus id nunc. Duis scelerisque molestie turpis. Sed fringilla, massa eget luctus malesuada, metus eros molestie lectus, ut tempus eros massa ut dolor. Aenean aliquet fringilla sem. Suspendisse sed ligula in ligula suscipit aliquam. Praesent in eros vestibulum mi adipiscing adipiscing. Morbi facilisis. Curabitur ornare consequat nunc. Aenean vel metus. Ut posuere viverra nulla. Aliquam erat volutpat. Pellentesque convallis. Maecenas feugiat, tellus pellentesque pretium posuere, felis lorem euismod felis, eu ornare leo nisi vel felis. Mauris consectetur tortor et purus.</p>
    </div>
    <div id="tabs-3">
        <p>Mauris eleifend est et turpis. Duis id erat. Suspendisse potenti. Aliquam vulputate, pede vel vehicula accumsan, mi neque rutrum erat, eu congue orci lorem eget lorem. Vestibulum non ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sodales. Quisque eu urna vel enim commodo pellentesque. Praesent eu risus hendrerit ligula tempus pretium. Curabitur lorem enim, pretium nec, feugiat nec, luctus a, lacus.</p>

        <p>Duis cursus. Maecenas ligula eros, blandit nec, pharetra at, semper at, magna. Nullam ac lacus. Nulla facilisi. Praesent viverra justo vitae neque. Praesent blandit adipiscing velit. Suspendisse potenti. Donec mattis, pede vel pharetra blandit, magna ligula faucibus eros, id euismod lacus dolor eget odio. Nam scelerisque. Donec non libero sed nulla mattis commodo. Ut sagittis. Donec nisi lectus, feugiat porttitor, tempor ac, tempor vitae, pede. Aenean vehicula velit eu tellus interdum rutrum. Maecenas commodo. Pellentesque nec elit. Fusce in lacus. Vivamus a libero vitae lectus hendrerit hendrerit.</p>
    </div>
</div>
<div style="height: 5px">&nbsp;</div>
<select id="sss" dictType="mdict_person" dictItem="" defaultTip="" param="searchValue['a']=xxx" defaultValue="3">
</select>
<select id="sss2" parentDict="sss" param="searchValue['type']=1">
</select>
<select id="sss1" parentDict="sss2" param="searchValue['type']=2">
</select>
<select id="ade" dictType="test" dictItem="21">
</select>
<select id="edf" parentDict="ade" defaultValue="11">
</select>
<%--<select id="eee" parentDict="edf" defaultValue="">--%>
<%--</select>--%>
<div datasrc="local:a" style="background-color: red">
    <input type="checkbox" name="cv" dictType="test" defaultValue="@{cv}">
</div>
<input type="checkbox" name="abc" dictType="test" defaultValue="1,3" onclick="alert('a')">
<input type="radio" name="def" dictType="test" defaultValue="2">
<input type="radio" name="def1" dictType="mdict_person" defaultValue="" param="searchValue['a']=xxx">
<!--主体部分 end-->
<script type="text/javascript">
    //        $(document.body).append("<div id=\"id_dialog\">abc<div>");
    //        var dialog = $("#id_dialog").html("sssss").dialog();
    function func1() {

    }
    //       _alert("abc","ddd");
    var v1 = _doGetDataSrc("${baseURL}/demomodel/demomodel!testList.action", "");
    //        alert(v1.maxPage);
    $(function () {
        $("#tabs").tabs();
//        var $tabs = $('#tabs').tabs({
//            tabTemplate: '<li><a href="\#{href}">\#{label}</a> <span class="ui-icon ui-icon-close">Remove Tab</span></li>',
//            add: function(event, ui) {
//                var tab_content = $tab_content_input.val() || 'Tab '+tab_counter+' content.';
//                $(ui.panel).append('<p>'+tab_content+'</p>');
//            }
//        });
    });

    var a = {cv:"1,3",
        "maxPage":13, "currPage":'2', "totalRows":12,"tName":"haha2",
        "pageData":[
            {"haha1":"hhh1", "haha2":"1", "haha3":"hhh1","ta":[{"xx":"x1"}]},
            {"haha1":"hhh2", "haha2":"<script>alert('a')<\/script>", "haha3":"hhh2","ta":[{"xx":"x2"}]},
            {"haha1":"hhh3", "haha2":"hhh3abcdefgaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "haha3":"hhh3","ta":[{"xx":"x3"}]},
            {"haha1":"hhh4", "haha2":"hhh3abcdefgaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "haha3":"hhh3","ta":[{"xx":"x4"}]}
        ]};

    function doUP() {
        a = {cv:"1,3",
            "maxPage":12, "currPage":2, "totalRows":12,
            "pageData":[
                {"haha1":"aaa", "haha2":"aaa", "haha3":"aaa"},
                {"haha1":"aaa", "haha2":"<aaa><\/script>", "haha3":"aaa"},
                {"haha1":"aaa", "haha2":"aaaa", "haha3":"aaa"}
            ]};
//    _redraw();
    }

    function doRedraw() {
        a = {cv:"1,3",
            "maxPage":11, "currPage":2, "totalRows":12,
            "pageData":[
                {"haha1":"aaa", "haha2":"aaa", "haha3":"aaa"},
                {"haha1":"aaa", "haha2":"<aaa><\/script>", "haha3":"aaa"},
                {"haha1":"aaa", "haha2":"aaaa", "haha3":"aaa"}
            ]};
        _redraw();
    }
</script>
</body>

</html>
