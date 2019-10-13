package com.trimax.its.device.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.trimax.its.device.dao.DeviceDao;
import com.trimax.its.model.Vendor;

public class DeviceAjaxAction {
//	public String getdeviceTypes() {
//		DeviceDao devicedao = new DeviceDao();
//
//		// serviceTypeIds=rmDao.getServiceId();
//		System.out.println("hi i am in finduser");
//		List<String> l1 = devicedao.getDeviceId();
//		List<String> l2 = devicedao.getDeviceName();
//		String regionTypeAjaxString = "";
//		regionTypeAjaxString += "<option value=''>------select------</option>";
//		for (int i = 0; i < l1.size(); i++) {
//			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
//					+ ">" + l2.get(i).toString() + "</option>";
//		}
//		// regionTypeAjaxString += "</select>";
//		System.out.println("regionTypeAjaxString=" + regionTypeAjaxString);
//		HttpServletResponse response = ServletActionContext.getResponse();
//		PrintWriter out;
//		try {
//			out = response.getWriter();
//			out.print(regionTypeAjaxString);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return null;
//
//	}

	public String getVendorName() {
		DeviceDao devicedao = new DeviceDao();

		// serviceTypeIds=rmDao.getServiceId();
		HttpServletRequest req = ServletActionContext.getRequest();
		String devicetype=req.getParameter("type");

	//	List<String> l1 = devicedao.getVendorId();
		List<Vendor> l2 = devicedao.getVendorName(devicetype);
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=''>------select------</option>";
		for (int i = 0; i < l2.size(); i++) {
			Vendor v=l2.get(i);
			regionTypeAjaxString += "<option value=" + v.getId()
					+ ">" + v.getVendor_name() + "</option>";
		}
		// regionTypeAjaxString += "</select>";
		System.out.println("regionTypeAjaxString=" + regionTypeAjaxString);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

//	public String getSimCardAllotName() {
//		DeviceDao devicedao = new DeviceDao();
//
//		// serviceTypeIds=rmDao.getServiceId();
//
//		List<String> l1 = devicedao.getVendorId();
//		List<String> l2 = devicedao.getVendorName();
//		String regionTypeAjaxString = "";
//		regionTypeAjaxString += "<option value=''>------select------</option>";
//		for (int i = 0; i < l1.size(); i++) {
//			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
//					+ ">" + l2.get(i).toString() + "</option>";
//		}
//		// regionTypeAjaxString += "</select>";
//		System.out.println("regionTypeAjaxString=" + regionTypeAjaxString);
//		HttpServletResponse response = ServletActionContext.getResponse();
//		PrintWriter out;
//		try {
//			out = response.getWriter();
//			out.print(regionTypeAjaxString);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return null;
//	}
	public String getOrgAllotName()
	{
		
		return null;
	}
	
	public String getBatAllotName()
	{
		return null;
	}
	
}
