package cn.com.do1.component.demo.demomodel.model;

import cn.com.do1.common.framebase.dqdp.IBaseDBVO;

import java.util.Date;

/**
 * Copyright © 2013 广州市道一信息技术有限公司
 * All rights reserved.
 * User: Saron
 * Date: 13-1-11
 * Time: 下午2:37
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class TbStationUsers implements IBaseDBVO {
    String id;
    Double rate;
    String stationId;
    Integer year = 2013;
    Integer month = 10;
    Integer date = 10;
    Integer minute=10;
    Integer hour = 10;
    Date time = new Date();
    Integer users = 10;
    Integer totalUsers = 10;

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getUsers() {
        return users;
    }

    public void setUsers(Integer users) {
        this.users = users;
    }

    public Integer getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(Integer totalUsers) {
        this.totalUsers = totalUsers;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取数据库中对应的表名
     *
     * @return
     */
    public String _getTableName() {
        return "TB_STATION_USERS";
    }

    /**
     * 获取对应表的主键字段名称
     *
     * @return
     */
    public String _getPKColumnName() {
        return "id";
    }

    /**
     * 获取主键值
     *
     * @return
     */
    public String _getPKValue() {
        return String.valueOf(id);
    }

    /**
     * 设置主键的值
     *
     * @return
     */
    public void _setPKValue(Object value) {
        this.id = (java.lang.String) value;
    }

}
