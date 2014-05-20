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
    <title>CRON BUILDER</title>
    <link href="${baseURL}/themes/${style}/css/common.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" href="${baseURL}/themes/do1/jquery-ui/jquery.ui.all.css" rel="stylesheet"/>
    <script src="${baseURL}/js/do1/common/jquery-1.6.3.min.js"></script>
    <script src="${baseURL}/js/do1/common/common.js?ver=<%=jsVer%>"></script>
    <script src="${baseURL}/js/3rd-plug/jquery/jquery.form.js"></script>
    <script src="${baseURL}/js/3rd-plug/jquery-ui-1.8/js/jquery-ui-1.8.custom.min.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery-ui-1.8/ui/jquery.ui.core.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery-ui-1.8/ui/jquery.ui.widget.js"></script>
    <script type="text/javascript" src="${baseURL}/js/3rd-plug/jquery-ui-1.8/ui/jquery.ui.tabs.js"></script>
    <script src="${baseURL}/js/DatePicker/WdatePicker.js"></script>
    <style type="text/css">
    </style>
</head>
<body>

<div class="searchWrap">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="searchLeft"></td>
            <div class="title">
                <h2 class="icon1">CRON表达式生成器</h2>
            </div>
        </tr>
    </table>
</div>

<div id="cronExpressionDiv" style="margin: 10px;">
    <br/>
    CRON表达式：<input type="text" id="cronExpression"/>
    <br/>
    <table style="margin: 10px;">
        <tr align="center">
            <td>秒</td>
            <td>分钟</td>
            <td>小时</td>
            <td>天(月)</td>
            <td>月</td>
            <td>天(星期)</td>
            <td>年</td>
        </tr>
        <tr>
            <td><input type="text" id="secondField" readonly="true"/>-</td>
            <td><input type="text" id="miniuteField" readonly="true"/>-</td>
            <td><input type="text" id="hourField" readonly="true"/>-</td>
            <td><input type="text" id="dayOfMonthField" readonly="true"/>-</td>
            <td><input type="text" id="monthField" readonly="true"/>-</td>
            <td><input type="text" id="weekField" readonly="true"/>-</td>
            <td><input type="text" id="yearField" readonly="true"/></td>
        </tr>
    </table>
</div>

<div id="tabs" style="margin: 10px;">
    <ul>
        <li><a href="#secondTab">秒</a></li>
        <li><a href="#miniuteTab">分</a></li>
        <li><a href="#hourTab">小时</a></li>
        <li><a href="#dayOfMonthTab">天(月)</a></li>
        <li><a href="#monthTab">月</a></li>
        <li><a href="#weekTab">天(星期)</a></li>
        <li><a href="#yearTab">年</a></li>
    </ul>

    <div id="secondTab">
        <input type="radio" name="secondTypeRadio" value="0"/>循环<br/>

        <div style="display: none;" id="secondCycleTab">
            从<input type="text" id="secondStart"/>秒开始，到<input type="text" id="secondEnd"/>（可不填）秒，每<input type="text" id="secondCycle"/>秒执行一次
        </div>

        <input type="radio" name="secondTypeRadio" value="1"/>指定<br/>

        <div style="display: none;" id="secondAsignTab">
            <input type="checkbox" name="secondAssignCB" value="0"/>0
            <input type="checkbox" name="secondAssignCB" value="1"/>1
            <input type="checkbox" name="secondAssignCB" value="5"/>5
            <input type="checkbox" name="secondAssignCB" value="10"/>10
            <input type="checkbox" name="secondAssignCB" value="15"/>15
            <input type="checkbox" name="secondAssignCB" value="20"/>20
            <input type="checkbox" name="secondAssignCB" value="25"/>25
            <input type="checkbox" name="secondAssignCB" value="30"/>30
            <input type="checkbox" name="secondAssignCB" value="35"/>35
            <input type="checkbox" name="secondAssignCB" value="40"/>40
            <input type="checkbox" name="secondAssignCB" value="45"/>45
            <input type="checkbox" name="secondAssignCB" value="50"/>50
            <input type="checkbox" name="secondAssignCB" value="55"/>55
        </div>

        <input type="radio" name="secondTypeRadio" value="2"/>1分钟内每秒执行一次<br/>
    </div>

    <div id="miniuteTab">
        <input type="radio" name="miniuteTypeRadio" value="0"/>循环<br/>

        <div id="miniuteCycleTab" style="display: none;">
            从<input type="text" id="miniuteStart"/>分开始，到<input type="text" id="miniuteEnd"/>（可不填）分，每<input type="text" id="miniuteCycle"/>分钟执行一次
        </div>

        <input type="radio" name="miniuteTypeRadio" value="1"/>指定<br/>

        <div id="miniuteAsignTab" style="display: none;">
            <input type="checkbox" name="miniuteAssignCB" value="0"/>0
            <input type="checkbox" name="miniuteAssignCB" value="1"/>1
            <input type="checkbox" name="miniuteAssignCB" value="5"/>5
            <input type="checkbox" name="miniuteAssignCB" value="10"/>10
            <input type="checkbox" name="miniuteAssignCB" value="15"/>15
            <input type="checkbox" name="miniuteAssignCB" value="20"/>20
            <input type="checkbox" name="miniuteAssignCB" value="25"/>25
            <input type="checkbox" name="miniuteAssignCB" value="30"/>30
            <input type="checkbox" name="miniuteAssignCB" value="35"/>35
            <input type="checkbox" name="miniuteAssignCB" value="40"/>40
            <input type="checkbox" name="miniuteAssignCB" value="45"/>45
            <input type="checkbox" name="miniuteAssignCB" value="50"/>50
            <input type="checkbox" name="miniuteAssignCB" value="55"/>55
        </div>

        <input type="radio" name="miniuteTypeRadio" value="2"/>1小时内每分钟执行一次<br/>
    </div>

    <div id="hourTab">
        <input type="radio" name="hourTypeRadio" value="0"/>循环<br/>

        <div id="hourCycleTab" style="display: none;">
            从<input type="text" id="hourStart"/>时开始，到<input type="text" id="hourEnd"/>（可不填）时，每<input type="text" id="hourCycle"/>小时执行一次
        </div>

        <input type="radio" name="hourTypeRadio" value="1"/>指定<br/>

        <div id="hourAsignTab" style="display: none;">
            <input type="checkbox" name="hourAssignCB" value="0"/>0
            <input type="checkbox" name="hourAssignCB" value="1"/>1
            <input type="checkbox" name="hourAssignCB" value="2"/>2
            <input type="checkbox" name="hourAssignCB" value="3"/>3
            <input type="checkbox" name="hourAssignCB" value="4"/>4
            <input type="checkbox" name="hourAssignCB" value="5"/>5
            <input type="checkbox" name="hourAssignCB" value="6"/>6
            <input type="checkbox" name="hourAssignCB" value="7"/>7
            <input type="checkbox" name="hourAssignCB" value="8"/>8
            <input type="checkbox" name="hourAssignCB" value="9"/>9
            <input type="checkbox" name="hourAssignCB" value="10"/>10
            <input type="checkbox" name="hourAssignCB" value="11"/>11
            <input type="checkbox" name="hourAssignCB" value="12"/>12
            <input type="checkbox" name="hourAssignCB" value="13"/>13
            <input type="checkbox" name="hourAssignCB" value="14"/>14
            <input type="checkbox" name="hourAssignCB" value="15"/>15
            <input type="checkbox" name="hourAssignCB" value="16"/>16
            <input type="checkbox" name="hourAssignCB" value="17"/>17
            <input type="checkbox" name="hourAssignCB" value="18"/>18
            <input type="checkbox" name="hourAssignCB" value="19"/>19
            <input type="checkbox" name="hourAssignCB" value="20"/>20
            <input type="checkbox" name="hourAssignCB" value="21"/>21
            <input type="checkbox" name="hourAssignCB" value="22"/>22
            <input type="checkbox" name="hourAssignCB" value="23"/>23

        </div>

        <input type="radio" name="hourTypeRadio" value="2"/>1天内每小时执行<br/>

    </div>

    <div id="dayOfMonthTab">
        <input type="radio" name="dayOfMonthTypeRadio" value="0"/>每天执行<br/>
        <input type="radio" name="dayOfMonthTypeRadio" value="1"/>指定具体天数<br/>

        <div id="dayOfMonthAsignTab1" style="display: none;">
            <input type="checkbox" name="dayOfMonthAssignCB" value="1"/>1
            <input type="checkbox" name="dayOfMonthAssignCB" value="2"/>2
            <input type="checkbox" name="dayOfMonthAssignCB" value="3"/>3
            <input type="checkbox" name="dayOfMonthAssignCB" value="4"/>4
            <input type="checkbox" name="dayOfMonthAssignCB" value="5"/>5
            <input type="checkbox" name="dayOfMonthAssignCB" value="6"/>6
            <input type="checkbox" name="dayOfMonthAssignCB" value="7"/>7
            <input type="checkbox" name="dayOfMonthAssignCB" value="8"/>8
            <input type="checkbox" name="dayOfMonthAssignCB" value="9"/>9
            <input type="checkbox" name="dayOfMonthAssignCB" value="10"/>10
            <input type="checkbox" name="dayOfMonthAssignCB" value="11"/>11
            <input type="checkbox" name="dayOfMonthAssignCB" value="12"/>12
            <input type="checkbox" name="dayOfMonthAssignCB" value="13"/>13
            <input type="checkbox" name="dayOfMonthAssignCB" value="14"/>14
            <input type="checkbox" name="dayOfMonthAssignCB" value="15"/>15
            <input type="checkbox" name="dayOfMonthAssignCB" value="16"/>16
            <input type="checkbox" name="dayOfMonthAssignCB" value="17"/>17
            <input type="checkbox" name="dayOfMonthAssignCB" value="18"/>18
            <input type="checkbox" name="dayOfMonthAssignCB" value="19"/>19
            <input type="checkbox" name="dayOfMonthAssignCB" value="20"/>20
            <input type="checkbox" name="dayOfMonthAssignCB" value="21"/>21
            <input type="checkbox" name="dayOfMonthAssignCB" value="22"/>22
            <input type="checkbox" name="dayOfMonthAssignCB" value="23"/>23
            <input type="checkbox" name="dayOfMonthAssignCB" value="24"/>24
            <input type="checkbox" name="dayOfMonthAssignCB" value="25"/>25
            <input type="checkbox" name="dayOfMonthAssignCB" value="26"/>26
            <input type="checkbox" name="dayOfMonthAssignCB" value="27"/>27
            <input type="checkbox" name="dayOfMonthAssignCB" value="28"/>28
            <input type="checkbox" name="dayOfMonthAssignCB" value="29"/>29
            <input type="checkbox" name="dayOfMonthAssignCB" value="30"/>30
            <input type="checkbox" name="dayOfMonthAssignCB" value="31"/>31
        </div>

        <input type="radio" name="dayOfMonthTypeRadio" value="2"/>每月的最后一天<br/>
        <input type="radio" name="dayOfMonthTypeRadio" value="3"/>每月的最后一个工作日<br/>
        <input type="radio" name="dayOfMonthTypeRadio" value="4"/>月内最近某天的工作日（星期一到星期五）<br/>

        <div id="workDayAsignTab" style="display: none;">
            在距离第<input type="text" id="workDayAsignText"/>天的最近的工作日触发
        </div>
    </div>

    <div id="monthTab">
        <input type="radio" name="monthTypeRadio" value="0"/>1年内每月执行<br/>
        <input type="radio" name="monthTypeRadio" value="1"/>指定<br/>

        <div id="monthAsignTab" style="display: none;">
            <input type="checkbox" name="monthAssignCB" value="1"/>1
            <input type="checkbox" name="monthAssignCB" value="2"/>2
            <input type="checkbox" name="monthAssignCB" value="3"/>3
            <input type="checkbox" name="monthAssignCB" value="4"/>4
            <input type="checkbox" name="monthAssignCB" value="5"/>5
            <input type="checkbox" name="monthAssignCB" value="6"/>6
            <input type="checkbox" name="monthAssignCB" value="7"/>7
            <input type="checkbox" name="monthAssignCB" value="8"/>8
            <input type="checkbox" name="monthAssignCB" value="9"/>9
            <input type="checkbox" name="monthAssignCB" value="10"/>10
            <input type="checkbox" name="monthAssignCB" value="11"/>11
            <input type="checkbox" name="monthAssignCB" value="12"/>12
        </div>
    </div>

    <div id="weekTab">
        <input type="checkbox" id="useWeekField"/>使用星期字段<br/>

        <div id="weekSubTab" style="display: none;">
            <input type="radio" name="weekTypeRadio" value="0">每天执行<br/>

            <input type="radio" name="weekTypeRadio" value="1">指定特定的天数<br/>

            <div id="weekAsignTab" style="display: none;">
                <input type="checkbox" name="weekAssignCB" value="SUN"/>星期日
                <input type="checkbox" name="weekAssignCB" value="MON"/>星期一
                <input type="checkbox" name="weekAssignCB" value="TUE"/>星期二
                <input type="checkbox" name="weekAssignCB" value="WED"/>星期三
                <input type="checkbox" name="weekAssignCB" value="THU"/>星期四
                <input type="checkbox" name="weekAssignCB" value="FRI"/>星期五
                <input type="checkbox" name="weekAssignCB" value="SAT"/>星期六
            </div>

            <input type="radio" name="weekTypeRadio" value="2">指定相对的天数<br/>

            <div id="weekAsignTab2" style="display: none;">
                <input type="radio" name="weekAssignCB2" value="1L"/>最后一个星期日
                <input type="radio" name="weekAssignCB2" value="2L"/>最后一个星期一
                <input type="radio" name="weekAssignCB2" value="3L"/>最后一个星期二
                <input type="radio" name="weekAssignCB2" value="4L"/>最后一个星期三
                <input type="radio" name="weekAssignCB2" value="5L"/>最后一个星期四
                <input type="radio" name="weekAssignCB2" value="6L"/>最后一个星期五
                <input type="radio" name="weekAssignCB2" value="7L"/>最后一个星期六
            </div>

            <input type="radio" name="weekTypeRadio" value="3">指定第N天触发<br/>

            <div id="weekAsignTab3" style="display: none;">
                在第<input type="text" id="weekNumText"/>个星期的
                <select id="weekDay">
                    <option value="1">星期日</option>
                    <option value="2">星期一</option>
                    <option value="3">星期二</option>
                    <option value="4">星期三</option>
                    <option value="5">星期四</option>
                    <option value="6">星期五</option>
                    <option value="7">星期六</option>
                </select>
                触发
            </div>

        </div>
    </div>

    <div id="yearTab">
        <input type="checkbox" id="useYearField"/>使用年份字段<br/>

        <div id="yearSubTab" style="display: none;">
            <input type="radio" name="yearTypeRadio" value="0"/>指定<br/>

            <div id="yearAsignTab" style="display: none;">
                在<input type="text" id="yearAssignField"/>执行<br/>
            </div>

            <input type="radio" name="yearTypeRadio" value="1"/>区间<br/>

            <div id="yearRangeTab" style="display: none;">
                从<input type="text" id="startYearAssignField"/>年到<input type="text" id="endYearAssignField"/>年
            </div>
        </div>
    </div>
</div>

<div style="margin: 10px;">
    预计运行时间：<br/>
    <select size="11" id="RunTimeList" style="width: 160px;"></select>
</div>

<div class="toolBar">
    <div align="center">
        <input class="btn4" id="build" type="button" value="生成表达式"/>
        <input class="btn4" id="evalute" type="button" value="预测运行时间"/>
        <input class="btn4" id="confirmBtn" type="button" value="确 定"/>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $("#tabs").tabs({

        });

        $("[name=secondTypeRadio]").click(function () {
            if ($(this).val() == 0) {
                $("#secondAsignTab").hide();
                $("#secondCycleTab").show();
            } else if ($(this).val() == 1) {
                $("#secondAsignTab").show();
                $("#secondCycleTab").hide();
            } else if ($(this).val() == 2) {
                $("#secondAsignTab").hide();
                $("#secondCycleTab").hide();
            }
        });

        $("[name=miniuteTypeRadio]").click(function () {
            if ($(this).val() == 0) {
                $("#miniuteCycleTab").show();
                $("#miniuteAsignTab").hide();
            } else if ($(this).val() == 1) {
                $("#miniuteAsignTab").show();
                $("#miniuteCycleTab").hide();
            } else if ($(this).val() == 2) {
                $("#miniuteAsignTab").hide();
                $("#miniuteCycleTab").hide();
            }
        });

        $("[name=hourTypeRadio]").click(function () {
            if ($(this).val() == 0) {
                $("#hourCycleTab").show();
                $("#hourAsignTab").hide();
            } else if ($(this).val() == 1) {
                $("#hourAsignTab").show();
                $("#hourCycleTab").hide();
            } else if ($(this).val() == 2) {
                $("#hourAsignTab").hide();
                $("#hourCycleTab").hide();
            }
        });

        $("[name=dayOfMonthTypeRadio]").click(function () {
            if ($(this).val() == 0) {
                $("#dayOfMonthAsignTab1").hide();
                $("#workDayAsignTab").hide();
            } else if ($(this).val() == 1) {
                $("#dayOfMonthAsignTab1").show();
                $("#workDayAsignTab").hide();
            } else if ($(this).val() == 4) {
                $("#dayOfMonthAsignTab1").hide();
                $("#workDayAsignTab").show();
            } else {
                $("#dayOfMonthAsignTab1").hide();
                $("#workDayAsignTab").hide();
            }
        });

        $("[name=monthTypeRadio]").click(function () {
            if ($(this).val() == 0) {
                $("#monthAsignTab").hide();
            } else if ($(this).val() == 1) {
                $("#monthAsignTab").show();
            }
        });

        $("#useWeekField").click(function () {
            if ($(this).attr("checked"))
                $("#weekSubTab").show();
            else
                $("#weekSubTab").hide();
        });

        $("[name=weekTypeRadio]").click(function () {
            if ($(this).val() == 0) {
                $("#weekAsignTab").hide();
                $("#weekAsignTab2").hide();
                $("#weekAsignTab3").hide();
            } else if ($(this).val() == 1) {
                $("#weekAsignTab").show();
                $("#weekAsignTab2").hide();
                $("#weekAsignTab3").hide();
            } else if ($(this).val() == 2) {
                $("#weekAsignTab").hide();
                $("#weekAsignTab2").show();
                $("#weekAsignTab3").hide();
            } else if ($(this).val() == 3) {
                $("#weekAsignTab").hide();
                $("#weekAsignTab2").hide();
                $("#weekAsignTab3").show();
            }
        });

        $("#useYearField").click(function () {
            if ($(this).attr("checked"))
                $("#yearSubTab").show();
            else
                $("#yearSubTab").hide();
        });

        $("[name=yearTypeRadio]").click(function () {
            if ($(this).val() == 0) {
                $("#yearAsignTab").show();
                $("#yearRangeTab").hide();
            } else if ($(this).val() == 1) {
                $("#yearAsignTab").hide();
                $("#yearRangeTab").show();
            }
        });
    });

    function buildCronExpression() {
        //秒字段
        var secondType = $("[name=secondTypeRadio]:checked").val();
        switch (secondType) {
            case "0":
                var secondEndStr = $("#secondEnd").val().trim();
                if (secondEndStr == "")
                    $("#secondField").val($("#secondStart").val() + "/" + $("#secondCycle").val());
                else
                    $("#secondField").val($("#secondStart").val() + "-" + secondEndStr + "/" + $("#secondCycle").val());
                break;
            case "1":
                var secondFieldStr = "";
                $("[name=secondAssignCB]").each(function (index, content) {
                    if ($(this).attr("checked")) {
                        secondFieldStr += $(this).val() + ",";
                    }
                });
                secondFieldStr = secondFieldStr.substr(0, secondFieldStr.length - 1);
                $("#secondField").val(secondFieldStr);
                break;
            case "2":
                $("#secondField").val("*");
                break;
            default:
                $("#secondField").val("0");
        }

        //分钟字段
        var miniuteType = $("[name=miniuteTypeRadio]:checked").val();
        switch (miniuteType) {
            case "0":
                var miniuteEndStr = $("#miniuteEnd").val().trim();
                if (miniuteEndStr == "")
                    $("#miniuteField").val($("#miniuteStart").val() + "/" + $("#miniuteCycle").val());
                else
                    $("#miniuteField").val($("#miniuteStart").val() + "-" + miniuteEndStr + "/" + $("#miniuteCycle").val());
                break;
            case "1":
                var miniuteFieldStr = "";
                $("[name=miniuteAssignCB]").each(function (index, content) {
                    if ($(this).attr("checked")) {
                        miniuteFieldStr += $(this).val() + ",";
                    }
                });
                miniuteFieldStr = miniuteFieldStr.substr(0, miniuteFieldStr.length - 1);
                $("#miniuteField").val(miniuteFieldStr);
                break;
            case "2":
                $("#miniuteField").val("*");
                break;
            default:
                $("#miniuteField").val("0");
        }

        //小时字段
        var hourType = $("[name=hourTypeRadio]:checked").val();
        switch (hourType) {
            case "0":
                var hourEndStr = $("#hourEnd").val().trim();
                if (hourEndStr == "")
                    $("#hourField").val($("#hourStart").val() + "/" + $("#hourCycle").val());
                else
                    $("#hourField").val($("#hourStart").val() + "-" + hourEndStr + "/" + $("#hourCycle").val());
                break;
            case "1":
                var hourFieldStr = "";
                $("[name=hourAssignCB]").each(function (index, content) {
                    if ($(this).attr("checked")) {
                        hourFieldStr += $(this).val() + ",";
                    }
                });
                hourFieldStr = hourFieldStr.substr(0, hourFieldStr.length - 1);
                $("#hourField").val(hourFieldStr);
                break;
            case "2":
                $("#hourField").val("*");
                break;
            default:
                $("#hourField").val("0");
        }

        //天(月)字段
        var dayOfMonthType = $("[name=dayOfMonthTypeRadio]:checked").val();
        switch (dayOfMonthType) {
            case "0":
                $("#dayOfMonthField").val("*");
                break;
            case "1":
                var dayOfMonthFieldStr = "";
                $("[name=dayOfMonthAssignCB]").each(function (index, content) {
                    if ($(this).attr("checked")) {
                        dayOfMonthFieldStr += $(this).val() + ",";
                    }
                });
                dayOfMonthFieldStr = dayOfMonthFieldStr.substr(0, dayOfMonthFieldStr.length - 1);
                $("#dayOfMonthField").val(dayOfMonthFieldStr);
                break;
            case "2":
                $("#dayOfMonthField").val("L");
                break;
            case "3":
                $("#dayOfMonthField").val("LW");
                break;
            case "4":
                $("#dayOfMonthField").val($("#workDayAsignText").val() + "W");
                break;
            default:
                $("#dayOfMonthField").val("*");
        }

        //月字段
        var monthType = $("[name=monthTypeRadio]:checked").val();
        switch (monthType) {
            case "0":
                $("#monthField").val("*");
                break;
            case "1":
                var monthFieldStr = "";
                $("[name=monthAssignCB]").each(function (index, content) {
                    if ($(this).attr("checked")) {
                        monthFieldStr += $(this).val() + ",";
                    }
                });
                monthFieldStr = monthFieldStr.substr(0, monthFieldStr.length - 1);
                $("#monthField").val(monthFieldStr);
                break;
            default:
                $("#monthField").val("*");
        }

        //星期字段
        if ($("#useWeekField").attr("checked")) {
            $("#dayOfMonthField").val("?");
            var weekType = $("[name=weekTypeRadio]:checked").val();
            switch (weekType) {
                case "0":
                    $("#weekField").val("*");
                    break;
                case "1":
                    var weekFieldStr = "";
                    $("[name=weekAssignCB]").each(function (index, content) {
                        if ($(this).attr("checked")) {
                            weekFieldStr += $(this).val() + ",";
                        }
                    });
                    weekFieldStr = weekFieldStr.substr(0, weekFieldStr.length - 1);
                    $("#weekField").val(weekFieldStr);
                    break;
                case "2":
                    $("#weekField").val($("[name=weekAssignCB2]:checked").val());
                    break;
                case "3":
                    $("#weekField").val($("#weekDay").val() + "#" + $("#weekNumText").val());
                    break;
                default:
                    $("#weekField").val("");
            }
        } else {
            $("#weekField").val("?");
        }

        //年份字段
        if ($("#useYearField").attr("checked")) {
            var yearType = $("[name=yearTypeRadio]:checked").val();
            switch (yearType) {
                case "0":
                    $("#yearField").val($("#yearAssignField").val());
                    break;
                case "1":
                    $("#yearField").val($("#startYearAssignField").val() + "-" + $("#endYearAssignField").val());
                    break;
                default:
                    $("#yearField").val("");
            }
        } else {
            $("#yearField").val("");
        }

        $("#cronExpression").val($("#secondField").val() + " " + $("#miniuteField").val() + " " + $("#hourField").val() + " "
                + $("#dayOfMonthField").val() + " " + $("#monthField").val() + " " + $("#weekField").val() + " " + $("#yearField").val());
    }

    $("#build").click(function () {
        buildCronExpression();
    });

    $("#evalute").click(function () {
        if ($("#cronExpression").val().trim() == "")
            return;
        $.ajax({
            url:"${baseURL}/schedulemgr/schedulemgr!evaluteRunTime.action",
            type:"post",
            data:{
                "cronExpression":$("#cronExpression").val()
            },
            dataType:"json",
            success:function (result) {
                if ("0" == result.code) {
                    var timeList = result.data.runTimeList;
                    $("#RunTimeList option").remove();
                    for (var i = 0; i < timeList.length; i++) {
                        $("#RunTimeList").append("<option>" + timeList[i] + "</option>");
                    }
                } else {
                    alert(result.desc);
                }
            },
            error:function () {
                alert("通讯错误");
            }
        });
    });

    $("#confirmBtn").click(function() {
        window.opener.setCronExpression($("#cronExpression").val());
        window.close();
    });
</script>

</body>
</html>
