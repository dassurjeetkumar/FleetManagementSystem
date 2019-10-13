package com.trimax.its.orgchart.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.trimax.its.orgchart.dao.OrgChartDao;
import com.trimax.its.transport.dao.BusStopsDAO;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.transport.model.PointType;
import com.trimax.its.vehicle.model.OrganisationChart;

public class OrgAjaxAction {

	public String orgParentName() {
		HttpServletRequest request = ServletActionContext.getRequest();

		OrgChartDao orgdao = new OrgChartDao();
		List<String> l1 = orgdao.getorgId();
		List<String> l2 = orgdao.getOrgName();
		String regionTypeAjaxString = "";
		System.out.println("l1.size()"+l1.size());
		
		regionTypeAjaxString += "<option value=''>------select------</option>";
	
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
					+ ">" + l2.get(i).toString() + "</option>";
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

	public String getOrgTypeName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("ORGID======="+request.getParameter("id"));
		OrgChartDao orgdao = new OrgChartDao();
		int orgtypeId=orgdao.getorgTypeIdfromOrgChart(Integer.parseInt(request.getParameter("id")));
		List<String> l1 = orgdao.getOrgTypeId(orgtypeId);
		List<String> l2 = orgdao.getOrgTypeName(orgtypeId);
		String regionTypeAjaxString = "";
		if(l1.size()>1){
		regionTypeAjaxString += "<option value=''>------select------</option>";
		}
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
					+ ">" + l2.get(i).toString() + "</option>";
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
	
	List<PointType> listPoint;
	List<OrganisationChart> listOrgcharts;
	
	public List<PointType> getListPoint() {
		return listPoint;
	}

	public void setListPoint(List<PointType> listPoint) {
		this.listPoint = listPoint;
	}

	public List<OrganisationChart> getListOrgcharts() {
		return listOrgcharts;
	}

	public void setListOrgcharts(List<OrganisationChart> listOrgcharts) {
		this.listOrgcharts = listOrgcharts;
	}

	@SkipValidation
	public String showMap(){
		BusStopsDAO busstopsdao = new BusStopsDAO();
		listPoint= busstopsdao.getPointList();
		listOrgcharts=busstopsdao.getOrgChart();
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(listPoint);
			out.print(listOrgcharts);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
