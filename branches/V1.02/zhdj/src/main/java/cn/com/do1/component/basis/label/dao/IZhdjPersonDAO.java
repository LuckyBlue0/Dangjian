package cn.com.do1.component.basis.label.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.com.do1.common.dac.Pager;
import cn.com.do1.common.exception.BaseException;
import cn.com.do1.common.framebase.dqdp.IBaseDAO;
import cn.com.do1.common.framebase.dqdp.IBaseDBVO;
import cn.com.do1.component.systemmgr.user.model.BaseUserVO;
/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public interface IZhdjPersonDAO extends IBaseDAO{

        public abstract Pager listPerson(Map map, Pager pager)
            throws BaseException, Exception;

        public abstract void addPersonUserRef(String s, String as[])
            throws SQLException;

        public abstract void delPersonUserRef(String s, String s1)
            throws SQLException, BaseException;

        public abstract void delAllPersonUserRefByPersonId(String s)
            throws SQLException, BaseException;

        public abstract List listUserByPersonId(String s)
            throws SQLException, BaseException;

        public abstract Pager listUserByPersonIdView(String s, Pager pager)
            throws BaseException, Exception;

        public abstract Object searchPersonUserInfoByUserName(Class class1, IBaseDBVO ibasedbvo, String s)
            throws Exception, BaseException;

        public abstract Object searchPersonUserInfoById(Class class1, IBaseDBVO ibasedbvo, String s)
            throws Exception, BaseException;

        public abstract Pager listPersonByOrg(Class class1, IBaseDBVO ibasedbvo, String s, Pager pager, Map map)
            throws Exception, BaseException;

        public abstract Pager listPersonByOrg(Class class1, IBaseDBVO ibasedbvo, String s, Pager pager, Map map, String s1, String s2)
            throws Exception, BaseException;

        public abstract List listMyPerson(Class class1, IBaseDBVO ibasedbvo, String s, String s1, Map map)
            throws Exception, BaseException;

        public abstract BaseUserVO getBaseUserVOByPersonId(String s)
            throws BaseException, Exception;

        public abstract Pager listBaseUserByRoleId(String s, Pager pager)
            throws Exception, BaseException;

        public abstract boolean isPersonExist(String s)
            throws SQLException, BaseException;

        public abstract Pager listMyPersonPage(Class class1, IBaseDBVO ibasedbvo, String s, String s1, Map map, Pager pager)
            throws Exception, BaseException;
    }
