<%@page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../common/dqdp_common.jsp" %>
<jsp:include page="../../common/dqdp_vars.jsp">
    <jsp:param name="dict" value="taskStatus"></jsp:param>
    <jsp:param name="permission" value=""></jsp:param>
    <jsp:param name="mustPermission" value=""></jsp:param>
</jsp:include>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>任务管理</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${baseURL}/themes/${style}/css/modal.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/brand.js"></script>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/do1/common/error1.0.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <style type="text/css">
    </style>
</head>

<body style="background: #f6ebe5;">
<!--头部 end-->

<!--公告 end-->
<div class="searchWrap">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="searchLeft"></td>
            <div class="title">
                <h2 class="icon1">任务管理</h2>
            </div>
        </tr>
    </table>
</div>

<form action="${baseURL}/schedulemgr/schedulemgr!ajaxSearchJob.action" method="post" id="job_search">
<div class="text_bg">
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
                                        <td >任务名称：</td>
                                        <td ><input class="form120px" name="searchValue.jobName" type="text"/></td>
                                        <td >任务组：</td>
                                        <td ><input class="form120px" name="searchValue.jobGroup" type="text"/></td>
                                        <td >执行类：</td>
                                        <td ><input class="form120px" name="searchValue.className" type="text"/></td>
                                        <td >任务状态：</td>
                                        <td ><select dictType="taskStatus" name="searchValue.status"/></td>
                                        <td><input class="btnQuery" name="search" type="button" value="查询"
                                                   onclick="javascript:doSearch(1);"/>
                                            <input class="btnQuery" type="button" value="新增任务" onclick="javascript:window.location.href='jobAdd.jsp'+ '?dqdp_csrf_token='+dqdp_csrf_token"/>
                                            <input class="btnQuery" type="button" value="删除" onclick="javascript:_doDel('job_list');doSearch(1);"/></td>
                                    </tr>

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
    <div id="job_list" delUrl="${baseURL}/role/role!removeJob.action"></div>
<div id="tip"></div>


<!--表格 end-->

<div class="toolBar">
    <!--筛选 end-->
    <div class="page" id="downIdPage">

    </div>
    <!--翻页 end-->

    <!--对表格数据的操作 end-->
</div>
    </div>
</form>
<!--角色信息搜索表单 end-->

<!--翻页 end-->



<!--工具栏 end-->

<!--主体部分 end-->
<script type="text/javascript">

    $(document).ready(function () {
        doSearch(1);
    });
    function doSearch($pageIndex) {
        $('#job_search').ajaxSubmit({
            data:{
                'page':$pageIndex
            },
            dataType:'json',
            success:function (result) {
                if ("0" == result.code) {
                    var list1 = new ListTable(
                            {
                                checkableColumn:"jobId",
                                title:[
                                    {width:"2%", name:"", showName:"", isCheckColunm:true},
                                    {width:"10%", name:"jobName", showName:"任务名称"},
                                    {width:"10%", name:"jobGroup", showName:"任务组名称"},
                                    {width:"20%", name:"className", showName:"类名"},
                                    {width:"10%", name:"statusDesc", showName:"状态"},
                                    {width:"10%", name:"triggerStatusDesc", showName:"触发器状态"},
                                    {width:"10%", name:"ip", showName:"IP"},
                                    {width:"25%", name:"", showName:"操作", isOperationColumn:true}
                                ],
                                data:result.data.pageData,
                                operations:[
                                    {name:"查看", permission:"", event:function (index, content) {
                                        window.location.href = 'jobView.jsp?jobId=' + content.jobId+ '&dqdp_csrf_token='+dqdp_csrf_token;
                                    }},
                                    {name:"修改", permission:"", event:function (index, content) {
                                        window.location.href = 'jobEdit.jsp?jobId=' + content.jobId+ '&dqdp_csrf_token='+dqdp_csrf_token;
                                    }},
                                    {name:"删除", permission:"", event:function (index, content) {
                                        removeJob(content.jobName);
                                    }},
                                    {name:"启动", permission:"", condition:function (index, content) {
                                        return content.status == "1";
                                    }, event:function (index, content) {
                                        startJob(content.jobName);
                                    }},
                                    {name:"停止", permission:"", condition:function (index, content) {
                                        return content.status != "1";
                                    }, event:function (index, content) {
                                        stopJob(content.jobName);
                                    }},
                                    {name:"暂停", permission:"", condition:function (index, content) {
                                        return content.triggerStatusDesc == "正常";
                                    }, event:function (index, content) {
                                        pauseJob(content.jobName);
                                    }},
                                    {name:"恢复", permission:"", condition:function (index, content) {
                                        return content.triggerStatusDesc == "暂停";
                                    }, event:function (index, content) {
                                        resumeJob(content.jobName);
                                    }}
                                ],
                                trStyle:["trWhite"]
                            });
                    list1.createList("job_list");
                    var pager = new Pager({totalPages:result.data.maxPage, currentPage:result.data.currPage, funcName:"doSearch"});
                    pager.createPageBar("topIdPage");
                    pager.createPageBar("downIdPage");
                } else {
                    $("#tip").error({title:"错误", content:result.desc, button:[
                        {name:"确定", event:"_hideMsg()"}
                    ]});
                }
            },
            error:function () {
            }
        });

        function pauseJob(jobName) {
            $.ajax({
                url:"${baseURL}/schedulemgr/schedulemgr!pauseJob.action",
                type:"post",
                dataType:"json",
                data:{
                    "jobName":jobName
                },
                success:function (result) {
                    alert(result.desc);
                    doSearch(1);
                },
                error:function () {
                    alert("通讯错误");
                }
            });
        }

        function resumeJob(jobName) {
            $.ajax({
                url:"${baseURL}/schedulemgr/schedulemgr!resumeJob.action",
                type:"post",
                dataType:"json",
                data:{
                    "jobName":jobName
                },
                success:function (result) {
                    alert(result.desc);
                    doSearch(1);
                },
                error:function () {
                    alert("通讯错误");
                }
            });
        }

        function removeJob(jobName) {
            $.ajax({
                url:"${baseURL}/schedulemgr/schedulemgr!removeJob.action",
                type:"post",
                dataType:"json",
                data:{
                    "jobName":jobName
                },
                success:function (result) {
                    alert(result.desc);
                    doSearch(1);
                },
                error:function () {
                    alert("通讯错误");
                }
            });
        }

        function startJob(jobName) {
            $.ajax({
                url:"${baseURL}/schedulemgr/schedulemgr!startJob.action",
                type:"post",
                dataType:"json",
                data:{
                    "jobName":jobName
                },
                success:function (result) {
                    alert(result.desc);
                    doSearch(1);
                },
                error:function () {
                    alert("通讯错误");
                }
            });
        }

        function stopJob(jobName) {
            $.ajax({
                url:"${baseURL}/schedulemgr/schedulemgr!stopJob.action",
                type:"post",
                dataType:"json",
                data:{
                    "jobName":jobName
                },
                success:function (result) {
                    alert(result.desc);
                    doSearch(1);
                },
                error:function () {
                    alert("通讯错误");
                }
            });
        }

    }

</script>

</body>

</html>
