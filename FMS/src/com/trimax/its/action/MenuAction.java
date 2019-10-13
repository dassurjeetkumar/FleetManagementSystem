/*  Document   : MenuAction.java
    Created on : May 19, 2014, 11:00:01 AM
    Author     : manojv
*/
package com.trimax.its.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.dao.MenuDao;
import com.trimax.its.model.Menu;
import com.trimax.its.model.User;

public class MenuAction extends ActionSupport {
	List<Menu> menuList;

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	
	DashBoardParameter params=new DashBoardParameter();
	DashBoardDao dao=new DashBoardDao();

	public String execute() {
		MenuDao menuDao = new MenuDao();
		User user = (User) ServletActionContext.getRequest().getAttribute("user");
		//System.out.println("userId=" + user.getUserId());
		//Setting Menu..
		setMenuList(menuDao.getMenuList(user));
		//Setting DashBoard Parameters...
		try {
			params=dao.getDashBoardParams();
		}catch(Exception Ex) {
			Ex.fillInStackTrace();
		}
		
		return SUCCESS;
	}
}
