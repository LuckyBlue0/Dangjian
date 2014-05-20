package cn.com.do1.component.baseweb.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import cn.com.do1.common.framebase.dqdp.ErrorCode;
import cn.com.do1.common.framebase.struts.BaseAction;
import cn.com.do1.common.util.AssertUtil;
import cn.com.do1.common.util.TreeObject;
import cn.com.do1.component.basis.newstype.model.TbNewsColumnTypePO;
import cn.com.do1.component.basis.newstype.service.INewsTypeService;
import cn.com.do1.dqdp.core.DqdpAppContext;
import cn.com.do1.dqdp.core.menu.MenuItem;
import cn.com.do1.dqdp.core.menu.MenuMgr;

/**
 * Copyright © 2012 广州市道一信息技术有限公司
 * All rights reserved.
 * User: saron
 * Date: 12-3-6
 * Time: 下午5:15
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 * ★                         Saron出品
 * ★★★★★★★★★★★★★★★★★★★★★★★★★★
 */
public class MainPageAction extends BaseAction {
    static Logger logger = Logger.getLogger(MainPageAction.class);
    public void getMainPageInfo() {
        try {
            List<MenuItem> currUserMenu = MenuMgr.getCurrUserMenu();
            setCurrUserMenu(currUserMenu);
            List<TreeObject> treeList = new ArrayList<TreeObject>();
            for (MenuItem menuItem : currUserMenu) {
                TreeObject treeObject = menuItem.converToTreeObject();
                treeList.add(treeObject);
            }
            setActionResult("0", "首页信息获取成功");
            addJsonArray("tree", treeList);
        } catch (Exception e) {
            e.printStackTrace();
            setActionResult(ErrorCode.UNKOWN_EXCEPTION, "首页信息获取失败");
        }
        doJsonOut();
    }
    
    
    /**
     * 添加新闻管理菜单
     * @param currUserMenu
     */
    private void setCurrUserMenu(List<MenuItem> currUserMenu){
        //检查用户是否拥有新闻管理的权限
        boolean flag = DqdpAppContext.checkPermissionAND(DqdpAppContext.getCurrentUser(), "newsManager");
        if(flag){
        	INewsTypeService newsTypeService = (INewsTypeService)DqdpAppContext.getSpringContext().getBean("newsTypeService");
        	try {
        		List<TbNewsColumnTypePO> news = newsTypeService.getNewsType();
        		if(!AssertUtil.isEmpty(news)){
        			for(MenuItem item : currUserMenu){
        				if(!AssertUtil.isEmpty(item.getRoles()) && item.getRoles().contains("newsManager")){
        					
        					//所有父菜单
        					HashMap<String,MenuItem>parantMenu= new HashMap<String, MenuItem>();
        					MenuItem parentMenu = null;
        					for(TbNewsColumnTypePO po : news){
        						if(po.getParentId() == null){
        							parentMenu = new MenuItem();
        							parentMenu.setParent(item);
        							parentMenu.setLink(po.getLink());
        							parentMenu.setMenuName(po.getName());
        							//将ID设置为父菜单的key,子菜单取父菜单用parentId
        							parantMenu.put(po.getNewsTypeId(), parentMenu);
        						}
        					}
        					
        					//父菜单列表
        					List<MenuItem> parentMents = new ArrayList<MenuItem>();
                			MenuItem menu = null;
                			for(TbNewsColumnTypePO po : news){
                	            //如果是子菜单
                	            if(po.getParentId() != null){
                       	            menu = new MenuItem();
                    	            menu.setLink(po.getLink());
                    	            menu.setMenuName(po.getName());
                	            	//父菜单
                	            	MenuItem m = parantMenu.get(po.getParentId());
                	            	//设置菜单的父菜单
                	            	menu.setParent(m);
                	            	m.setLink(null);
                	            	//父菜单的子菜单列表
                	            	List<MenuItem> childrenMenus = m.getChildrenMenus();
                	            	//将菜单加入到子菜单列表
                	            	childrenMenus.add(menu);
                	            	//设置父菜单的子菜单
                	            	m.setChildrenMenus(childrenMenus);
                	            	if(parentMents.contains(m))
                	            		continue;
                	            	//设置模块菜单的子菜单,(层次结构：{新闻管理：{新闻公告：{重要新闻,热点新闻}}})
                	            	parentMents.add(m);
                	            }else{
                	            	parentMents.add(parantMenu.get(po.getNewsTypeId()));
                	            }
                			}
                			for(MenuItem m : parentMents){
                				item.addChild(m);
                			}
        				}
        			}

        		}
			} catch (Exception e) {
				setActionResult(ErrorCode.UNKOWN_EXCEPTION, "首页信息获取失败");
			}
        }

    }
}
