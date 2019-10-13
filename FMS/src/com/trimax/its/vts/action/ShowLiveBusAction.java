package com.trimax.its.vts.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.trimax.its.vts.dao.VtsDataDAO;

public class ShowLiveBusAction {
	
	
	private Map<Integer, String> divisionlist;
	private String vehiclelist;
	private String startdate;
	private Map<Integer, String> vehiclelistname;


	public Map<Integer, String> getVehiclelistname() {
		return vehiclelistname;
	}

	public void setVehiclelistname(Map<Integer, String> vehiclelistname) {
		this.vehiclelistname = vehiclelistname;
	}
	
	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	private String enddate;
	public String getVehiclelist() {
		return vehiclelist;
	}

	public void setVehiclelist(String vehiclelist) {
		this.vehiclelist = vehiclelist;
	}

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
	 
		public String showLiveBusDetailsTrackingData() {
			
			try {
				
				VtsDataDAO vvt = VtsDataDAO.getInstance();
		        HttpServletRequest req = ServletActionContext.getRequest();
		        String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
		        String orgchartid=(String)req.getSession().getAttribute("orgchartid");
		        Date date = new Date();
				Date date1 = new Date();
				//Date addedDate2 = addDays(date1, 1);
				int parentId=0;
				divisionlist = vvt.getDivisionName(orgtypeid,orgchartid,parentId);
				divisionlist = vvt.getDivisionName();
				System.out.println("divisionlist"+divisionlist);
				vehiclelist = req.getParameter("vehiclelist");
				System.out.println("vehiclelist"+vehiclelist);
				
				try {
					SimpleDateFormat sm2 = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
					SimpleDateFormat sm3 = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
					setStartdate(sm3.format(date1));
					setEnddate(sm2.format(date1));
					System.out.println("startdate:"+getStartdate());
					System.out.println("enddate:"+getEnddate());
					//VtsDataDAO dao= VtsDataDAO.getInstance();
			        
			       
				} catch (Exception ex) {

				}
				try {
				} catch (Exception ex) {

				}
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		        
		        	try {
		    		} catch (Exception e) {
		    			// TODO Auto-generated catch block
		    			e.printStackTrace();
		    		}
		        	vehiclelistname = vvt.getVehicledetails(0);
				System.out.println("vehiclelist"+vehiclelistname);
				//schedulelist = vvt.getScheduleName();
		        
			} catch (Exception e) {
				e.printStackTrace();
			}
				
				return "success";
		}
}
