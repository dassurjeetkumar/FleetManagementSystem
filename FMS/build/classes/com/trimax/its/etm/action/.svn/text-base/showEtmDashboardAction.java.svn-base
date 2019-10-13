package com.trimax.its.etm.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.ccc.action.showDashBoardAction;
import com.trimax.its.common.Common;
import com.trimax.its.etm.doa.showEtmDashboardDoa;
import com.trimax.its.vts.dao.VtsDataDAO;

public class showEtmDashboardAction extends ActionSupport  {
	private Map<Integer, String> divisionlist1;
   private String reportdate;
	public String getReportdate() {
	return reportdate;
}

public void setReportdate(String reportdate) {
	this.reportdate = reportdate;
}

	public String showEtmDashoboard()
     {
		String date1=null;
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		//SimpleDateFormat sdff=new SimpleDateFormat("yyyy-MM-dd");
		//VtsDataDAO dao = VtsDataDAO.getInstance();
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			HttpServletResponse resp = ServletActionContext.getResponse();

      String date=req.getParameter("dateaction");
		

		Date d=new Date();
		String s=sdf.format(d);
		date1=s;
      if(date !=null) {
    		Common common = new Common();
			 date1=common.getDateFromPicker(date);
			System.out.println("----------------------"+date1);
			req.getSession().setAttribute("regdate", date1);
			this.reportdate=req.getParameter("dateaction");
      }else {
    	  this.reportdate=date1;
      }
			//setDivisionlist1(dao.getDepot1());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("this===================="+date1);
		return "success";
   }

	public String getAlertDetailsForEtm() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		//VtsDataDAO dao = VtsDataDAO.getInstance();
		showEtmDashboardDoa dao=new showEtmDashboardDoa();
		JSONObject result = new JSONObject();
		JSONObject wholeresult = new JSONObject();
		Date FromDate = new Date();
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
			resp.setContentType("application/json");
			result = dao.getDataForChart(1, req, "", "", sm.format(FromDate)
					.toString() + " 00:00:00", sm.format(FromDate).toString()
					+ " 23:59:59");

		} catch (Exception ex) {

		}
		out.print(result);

		return null;
	}
	
	public String gettotalAlertDetails() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
		showEtmDashboardDoa dao=new showEtmDashboardDoa();
		//VtsDataDAO dao = VtsDataDAO.getInstance();
		JSONObject result = new JSONObject();
		JSONObject wholeresult = new JSONObject();
		try {
			out = resp.getWriter();
			resp.setContentType("application/json");
			wholeresult = dao.getAlerts();

			//
		} catch (Exception ex) {

		}
		// out.print(result);
		out.print(wholeresult);
		return null;
	}
	
	
	public String batteryVehicles()
	{
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;

	    showEtmDashboardDoa dao=new showEtmDashboardDoa();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String packet_code = req.getParameter("alertID");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
		
		     result = dao.getDataForChartBatteryStatus(packet_code, req, "",
						"", sm.format(FromDate).toString() + " 00:00:00", sm
						.format(FromDate).toString() + " 23:59:59");
						out.print(result);
		} catch (Exception ex) {

		} finally {

		}
		return null;
		
		
	}
	public String tamperStatus()
	{
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;

	    showEtmDashboardDoa dao=new showEtmDashboardDoa();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String packet_code = req.getParameter("alertID");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
		
		     result = dao.getDataForChartTampered(packet_code, req, "",
						"", sm.format(FromDate).toString() + " 00:00:00", sm
						.format(FromDate).toString() + " 23:59:59");
						out.print(result);
		} catch (Exception ex) {

		} finally {

		}
		return null;
		
		
	}
	public String gprsSignalData()
	{
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;

	    showEtmDashboardDoa dao=new showEtmDashboardDoa();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String packet_code = req.getParameter("alertID");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			out = resp.getWriter();
		
		     result = dao.getDataForChartGprsSignal(packet_code, req, "",
						"", sm.format(FromDate).toString() + " 00:00:00", sm
						.format(FromDate).toString() + " 23:59:59");
						out.print(result);
		} catch (Exception ex) {

		} finally {

		}
		return null;
		
		
	}
	//Etm Pie Chart
	public String getEtmPieChart() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
	   showEtmDashboardDoa doa=new showEtmDashboardDoa();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = doa.getDataForEtmChart(1, req, "", "",
					sm.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59");
		} catch (Exception ex) {

		}
		out.print(result);

		return null;
	}
	
	public String getEtmPieChartDetails() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
	   showEtmDashboardDoa doa=new showEtmDashboardDoa();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = doa.getDataForEtmChartDetails(1, req, "", "",
					sm.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59",sm1.format(FromDate).toString());
		} catch (Exception ex) {

		}
		out.print(result);

		return null;
	}
	
	public String getingenico() {

		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
	   showEtmDashboardDoa doa=new showEtmDashboardDoa();
		JSONObject result = new JSONObject();
		//Date FromDate = new Date();
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
			resp.setContentType("application/json");
			out = resp.getWriter();
/*			result = doa.getDataForEtmChartDetails(1, req, "", "",
					sm.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59",sm1.format(FromDate).toString());*/
			result=doa.getingenico(req);
		} catch (Exception ex) {
  ex.printStackTrace();
		}
		out.print(result);

		return null;
	
	}
	
	// 
	
	public String getGprsDetails() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
	   showEtmDashboardDoa doa=new showEtmDashboardDoa();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String alertId=req.getParameter("alertID");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = doa.getDataForEtmChartGprsDetails(1, req, "", "",
					sm.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59",sm1.format(FromDate).toString(),alertId);
		} catch (Exception ex) {

		}
		out.print(result);

		return null;
	}
	
	public String getInactiveDetails() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
	   showEtmDashboardDoa doa=new showEtmDashboardDoa();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String alertId=req.getParameter("alertID");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = doa.getDataForEtmChartInactiveDetails(1, req, "", "",
					sm.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59",sm1.format(FromDate).toString(),alertId);
		} catch (Exception ex) {

		}
		out.print(result);

		return null;
	}
	public Map<Integer, String> getDivisionlist1() {
		return divisionlist1;
	}

	public void setDivisionlist1(Map<Integer, String> divisionlist1) {
		this.divisionlist1 = divisionlist1;
	}
	
	//Etm data for Volvo add by Rajesh
	public String getEtmVolvoPieChartDetails() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
	   showEtmDashboardDoa doa=new showEtmDashboardDoa();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = doa.getDataForVolvoEtmChartDetails(1, req, "", "",
					sm.format(FromDate).toString() + " 00:00:00",
					sm.format(FromDate).toString() + " 23:59:59",sm1.format(FromDate).toString());
		} catch (Exception ex) {

		}
		out.print(result);

		return null;
	}
	
	
	//Etm data for Ordinary add by Rajesh
		public String getEtmOrdPieChartDetails() {
			HttpServletResponse resp = ServletActionContext.getResponse();
			HttpServletRequest req = ServletActionContext.getRequest();
			PrintWriter out = null;
		   showEtmDashboardDoa doa=new showEtmDashboardDoa();
			JSONObject result = new JSONObject();
			Date FromDate = new Date();
			try {
				SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
				resp.setContentType("application/json");
				out = resp.getWriter();
				result = doa.getDataForOrdEtmChartDetails(1, req, "", "",
						sm.format(FromDate).toString() + " 00:00:00",
						sm.format(FromDate).toString() + " 23:59:59",sm1.format(FromDate).toString());
			} catch (Exception ex) {

			}
			out.print(result);

			return null;
		}
	//For Volvo Details Active Record Add by Rajesh
		public String getVolvoGprsDetails() {
			HttpServletResponse resp = ServletActionContext.getResponse();
			HttpServletRequest req = ServletActionContext.getRequest();
			PrintWriter out = null;
		   showEtmDashboardDoa doa=new showEtmDashboardDoa();
			JSONObject result = new JSONObject();
			Date FromDate = new Date();
			String alertId=req.getParameter("alertID");
//			System.out.println("alert_id===="+alertId);
			try {
				SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
				resp.setContentType("application/json");
				out = resp.getWriter();
				result = doa.getDataForVolvoEtmChartGprsDetails(1, req, "", "",
						sm.format(FromDate).toString() + " 00:00:00",
						sm.format(FromDate).toString() + " 23:59:59",sm1.format(FromDate).toString(),alertId);
			} catch (Exception ex) {

			}
			out.print(result);

			return null;
		}
		//For Volvo Details InActive Record Add by Rajesh	
		public String getInactiveVolvoDetails() {
			HttpServletResponse resp = ServletActionContext.getResponse();
			HttpServletRequest req = ServletActionContext.getRequest();
			PrintWriter out = null;
		   showEtmDashboardDoa doa=new showEtmDashboardDoa();
			JSONObject result = new JSONObject();
			Date FromDate = new Date();
			String alertId=req.getParameter("alertID");
			try {
				SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
				resp.setContentType("application/json");
				out = resp.getWriter();
				result = doa.getDataForVolvoEtmChartInactiveDetails(1, req, "", "",
						sm.format(FromDate).toString() + " 00:00:00",
						sm.format(FromDate).toString() + " 23:59:59",sm1.format(FromDate).toString(),alertId);
			} catch (Exception ex) {

			}
			out.print(result);

			return null;
		}
		
		//For Ordinary Details Active Record Add by Rajesh
				public String getOrdGprsDetails() {
					//System.out.println("inside gprs===========================");
					HttpServletResponse resp = ServletActionContext.getResponse();
					HttpServletRequest req = ServletActionContext.getRequest();
					PrintWriter out = null;
				   showEtmDashboardDoa doa=new showEtmDashboardDoa();
					JSONObject result = new JSONObject();
					Date FromDate = new Date();
					String alertId=req.getParameter("alertID");
					try {
						SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
						SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
						resp.setContentType("application/json");
						out = resp.getWriter();
						result = doa.getDataForOrdEtmChartGprsDetails(1, req, "", "",
								sm.format(FromDate).toString() + " 00:00:00",
								sm.format(FromDate).toString() + " 23:59:59",sm1.format(FromDate).toString(),alertId);
					} catch (Exception ex) {

					}
					out.print(result);

					return null;
				}
			
				public String getInactiveOrdDetails() {
					HttpServletResponse resp = ServletActionContext.getResponse();
					HttpServletRequest req = ServletActionContext.getRequest();
					PrintWriter out = null;
				   showEtmDashboardDoa doa=new showEtmDashboardDoa();
					JSONObject result = new JSONObject();
					Date FromDate = new Date();
					String alertId=req.getParameter("alertID");
					try {
						SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
						SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
						resp.setContentType("application/json");
						out = resp.getWriter();
						result = doa.getDataForOrdEtmChartInactiveDetails(1, req, "", "",
								sm.format(FromDate).toString() + " 00:00:00",
								sm.format(FromDate).toString() + " 23:59:59",sm1.format(FromDate).toString(),alertId);
					} catch (Exception ex) {

					}
					out.print(result);

					return null;
				}
				
				//For Active Vehicle Details Record Add by Rajesh	
				public String getActiveVehicleDetails() {
					HttpServletResponse resp = ServletActionContext.getResponse();
					HttpServletRequest req = ServletActionContext.getRequest();
					PrintWriter out = null;
				   showEtmDashboardDoa doa=new showEtmDashboardDoa();
					JSONObject result = new JSONObject();
					Date FromDate = new Date();
					String depot_name=req.getParameter("depotname");
					String alertId=req.getParameter("alertId");
//					System.out.println("alert==="+alertId);
					try {
						SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
						SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
						resp.setContentType("application/json");
						out = resp.getWriter();
						result = doa.getDataForActiveVehicleDetails(1, req, "", "",
								sm.format(FromDate).toString() + " 00:00:00",
								sm.format(FromDate).toString() + " 23:59:59",sm1.format(FromDate).toString(),depot_name,alertId);
					} catch (Exception ex) {

					}
					out.print(result);

					return null;
				}
		public String getingenicodetails() {

		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
	   showEtmDashboardDoa doa=new showEtmDashboardDoa();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String alertId=req.getParameter("alertID");
		System.out.println(alertId+"---------------------------");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = doa.getingenicodetailsdao( req,alertId);
		} catch (Exception ex) {

		}
		out.print(result);

		return null;
	
}
		public String  ingenicodepot() {


		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
	   showEtmDashboardDoa doa=new showEtmDashboardDoa();
		JSONObject result = new JSONObject();
	
		String alertId=req.getParameter("alertId");
		String depot=req.getParameter("depotname");
		System.out.println(alertId+"---------------------------");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = doa.getingenicodepot( req,depot,alertId);
		} catch (Exception ex) {

		}
		out.print(result);

		return null;
	
		}
		
		
		public String getverifone() {

			HttpServletResponse resp = ServletActionContext.getResponse();
			HttpServletRequest req = ServletActionContext.getRequest();
			PrintWriter out = null;
		   showEtmDashboardDoa doa=new showEtmDashboardDoa();
			JSONObject result = new JSONObject();
			//Date FromDate = new Date();
			try {
				SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
				resp.setContentType("application/json");
				out = resp.getWriter();
	/*			result = doa.getDataForEtmChartDetails(1, req, "", "",
						sm.format(FromDate).toString() + " 00:00:00",
						sm.format(FromDate).toString() + " 23:59:59",sm1.format(FromDate).toString());*/
				result=doa.getverifone(req);
			} catch (Exception ex) {
	  ex.printStackTrace();
			}
			out.print(result);

			return null;
		
		}
		public String getverifonedetails() {

		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
	   showEtmDashboardDoa doa=new showEtmDashboardDoa();
		JSONObject result = new JSONObject();
		Date FromDate = new Date();
		String alertId=req.getParameter("alertID");
		System.out.println(alertId+"---------------------------");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = doa.getverifonedetailsdao( req,alertId);
		} catch (Exception ex) {

		}
		out.print(result);

		return null;
	
}
		public String  verifonedepot() {


		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		PrintWriter out = null;
	   showEtmDashboardDoa doa=new showEtmDashboardDoa();
		JSONObject result = new JSONObject();
	
		String alertId=req.getParameter("alertId");
		String depot=req.getParameter("depotname");
		System.out.println(alertId+"---------------------------");
		try {
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
			resp.setContentType("application/json");
			out = resp.getWriter();
			result = doa.getverifonedepot( req,depot,alertId);
		} catch (Exception ex) {

		}
		out.print(result);

		return null;
	
		}
		
		public String getetmpingdata() {

			System.out.println("+++++++++++++++++++++++++++++++++");

			HttpServletResponse resp = ServletActionContext.getResponse();
			HttpServletRequest req = ServletActionContext.getRequest();
			PrintWriter out = null;
		   showEtmDashboardDoa doa=new showEtmDashboardDoa();
			JSONObject result = new JSONObject();
			Date FromDate = new Date();
			String alertId=req.getParameter("alertID");
			try {
				SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
				resp.setContentType("application/json");
				out = resp.getWriter();
				result = doa.getetmpingdata( req,alertId);
			} catch (Exception ex) {

			}
			out.print(result);

			return null;
		

		}
		public String getetmpingdepot() {

			HttpServletResponse resp = ServletActionContext.getResponse();
			HttpServletRequest req = ServletActionContext.getRequest();
			PrintWriter out = null;
		   showEtmDashboardDoa doa=new showEtmDashboardDoa();
			JSONObject result = new JSONObject();
//			Date FromDate = new Date();
			String alertId=req.getParameter("alertID");
			try {
		/*		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");*/
				resp.setContentType("application/json");
				out = resp.getWriter();
				result = doa.getetmpingdepot( req, alertId);
			} catch (Exception ex) {

			}
			out.print(result);

			return null;
			
		}
		
		public String depotwisepingdetails() {



			HttpServletResponse resp = ServletActionContext.getResponse();
			HttpServletRequest req = ServletActionContext.getRequest();
			PrintWriter out = null;
		   showEtmDashboardDoa doa=new showEtmDashboardDoa();
			JSONObject result = new JSONObject();
		
			String alertId=req.getParameter("alertId");
			String depot=req.getParameter("depotname");
			System.out.println(alertId+"---------------------------");
			try {
				SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sm1 = new SimpleDateFormat("dd-MM-yyyy");
				resp.setContentType("application/json");
				out = resp.getWriter();
				result = doa.depotwisepingdetails( req,depot,alertId);
			} catch (Exception ex) {

			}
			out.print(result);

			return null;
		
			
		}
		
}