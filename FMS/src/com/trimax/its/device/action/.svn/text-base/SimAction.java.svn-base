package com.trimax.its.device.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.trimax.its.device.dao.DeviceDao;
import com.trimax.its.device.dao.SimCardDao;
import com.trimax.its.device.model.Device;
import com.trimax.its.device.model.Simcard;

public class SimAction {
	Device device;
	Simcard simcard;
	String msg;
	String tabstatus;
	public Device getDevice() {
		return device;
	}
	public Simcard getSimcard() {
		return simcard;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	public void setSimcard(Simcard simcard) {
		this.simcard = simcard;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getTabstatus() {
		return tabstatus;
	}
	public void setTabstatus(String tabstatus) {
		this.tabstatus = tabstatus;
	}
	@SkipValidation
	public String saveSimCard()
	{
		SimCardDao dao = new SimCardDao();
		HttpServletRequest request = ServletActionContext.getRequest();
		int simid=dao.getSimId(simcard.getPhone_number());
		System.out.println("Allocation Sim Card===>"+device.getDevice_id()+"\t"+simcard.getPhone_number());
		boolean flag=dao.checkSimDevice(device.getDevice_id(), simid);
		setTabstatus(request.getSession().getAttribute("tabstat").toString());
		if(flag==false){
		dao.mapSimVtu(device.getDevice_id(), simid,request);
		}else{
			msg="Simcard already Assigned to Some Device!!";
			return "input";
		}
		return "success";
	}
	@SkipValidation
	public String addOrg()
	{
		return "success";
	}
//	public void validate() {
//		
//		
//	}
//	
	
}
