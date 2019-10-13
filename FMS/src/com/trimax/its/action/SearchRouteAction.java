package com.trimax.its.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.VehicleStatusModel;
import com.trimax.its.vts.dao.VtsDataDAO;

public class SearchRouteAction extends ActionSupport {

	private Map<Integer, String> busstoplist;
	private String busstopname;
	private String busstopid;

	public Map<Integer, String> getBusstoplist() {
		return busstoplist;
	}

	public void setBusstoplist(Map<Integer, String> busstoplist) {
		this.busstoplist = busstoplist;
	}

	public String getBusstopname() {
		return busstopname;
	}

	public void setBusstopname(String busstopname) {
		this.busstopname = busstopname;
	}

	public String getBusstopid() {
		return busstopid;
	}

	public void setBusstopid(String busstopid) {
		this.busstopid = busstopid;
	}

	public String execute() {

		return "success";
	}

	public String SearchRoute() {

		try {

			HttpServletRequest req = ServletActionContext.getRequest();

			String orgtypeid = (String) req.getSession().getAttribute("orgtypeid");
			String orgchartid = (String) req.getSession().getAttribute("orgchartid");
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			if (!orgtypeid.equals("1") && !orgchartid.equals("1")) {
				// Our Logic
				int parentId = 0;
				try {
				} catch (Exception ex) {

				}
				try {
					parentId = vvt.getDepot1(orgtypeid, orgchartid);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// divisionlist =
				// vvt.getDivisionName(orgtypeid,orgchartid,parentId);
				// Ends..
			} else {
				try {
					// divisionlist1 = vvt.getDepot();
					// System.out.println(divisionlist1.size()
					// + "divisionlist1%%%%%%%%%%%%%%%%%%%%%%");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// divisionlist = vvt.getDivisionName();
				busstoplist = vvt.getBusStopName();
				// System.out.println("vehiclelistname"+vehiclelistname);
				// schedulelist = vvt.getScheduleName();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public String getroutdetails() {
		System.out.println("we are in getroutdetails");
		try {
			
			JSONObject result = new JSONObject();
			HttpServletRequest request = ServletActionContext.getRequest();
			int firstbusstopid = Integer.parseInt(request.getParameter("firstbusstop"));
			int secondbusstopid = Integer.parseInt(request.getParameter("secondbusstop"));
			HttpServletResponse response = ServletActionContext.getResponse();
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			result=vvt.getroutedetails(firstbusstopid, secondbusstopid);
			System.out.println("result"+result);
			
			
			
			
		/*	HttpServletRequest request = ServletActionContext.getRequest();
			int firstbusstopid = Integer.parseInt(request.getParameter("firstbusstop"));
			int secondbusstopid = Integer.parseInt(request.getParameter("secondbusstop"));
			System.out.println(firstbusstopid + "............." + secondbusstopid);
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			List<String> l1 = vvt.getroutedetails(firstbusstopid, secondbusstopid);
			String regionTypeAjaxString = "";
			if (l1 == null || l1.size() == 0) {
				regionTypeAjaxString += "No Routes Found";
			} else {
				regionTypeAjaxString += "<table>";
				regionTypeAjaxString += "<tr><td>Route No</td></tr>";
				for (int i = 0; i < l1.size(); i++) {
					regionTypeAjaxString += "<tr>";
					regionTypeAjaxString += "<td>" + l1.get(i) + "</td>";
					regionTypeAjaxString += "</tr>";
				}
				regionTypeAjaxString += "</table>";
			}
			System.out.println("regionTypeAjaxString=" + regionTypeAjaxString);
			HttpServletResponse response = ServletActionContext.getResponse();*/
			PrintWriter out;

			out = response.getWriter();
			out.print(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private String routeNo;
	
public String getRouteNo() {
		return routeNo;
	}

	public void setRouteNo(String routeNo) {
		this.routeNo = routeNo;
	}

public String getRouteList(){
		
		HttpServletRequest req = ServletActionContext.getRequest();
		 routeNo = req.getParameter("routeNo");
		
		
		return "success";
	}
}
