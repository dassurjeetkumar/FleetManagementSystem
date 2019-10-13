package com.trimax.its.holiday.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.holiday.dao.HolidayMasterDao;

import com.trimax.its.holiday.model.HolidayMasterModel;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;

public class HolidayMasterAction extends ActionSupport{
	HolidayMasterModel holidayMasterModel;
	private Map<Integer,String> holidaytype;
	
	public Map<Integer, String> getHolidaytype() {
		return holidaytype;
	}

	public void setHolidaytype(Map<Integer, String> holidaytype) {
		this.holidaytype = holidaytype;
	}

	public String execute() throws Exception {
		return "success";
	}
	
	public HolidayMasterModel getHolidayMasterModel() {
		return holidayMasterModel;
	}
	public void setHolidayMasterModel(HolidayMasterModel holidayMasterModel) {
		this.holidayMasterModel = holidayMasterModel;
	}

	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
   public String viewHolidayMaster(){
	   try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			HolidayMasterDao holidayMasterDao=new HolidayMasterDao();
			String[] cols = { "", "holiday_master_id", "holiday_date","holiday_day","holiday_name","holiday_type" };
			JSONObject result = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			String sdir = request.getParameter("sSortDir_0");

			if (sStart != null) {
				start = Integer.parseInt(sStart);
				if (start < 0) {
					start = 0;
				}
			}
			if (sAmount != null) {
				amount = Integer.parseInt(sAmount);
				if (amount < 10 || amount > 50) {
					amount = 10;
				}
			}
			if (sCol != null) {
				col = Integer.parseInt(sCol);
				if (col < 0 || col > 5)
					col = 0;
			}
			if (sdir != null) {
				if (!sdir.equals("asc"))
					dir = "desc";
			}

			String colName = cols[col];
			
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;
			int total = 0;
			total = holidayMasterDao.getTotalRecords(total, request,
					cols[Integer.parseInt(sCol)], sdir);
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = holidayMasterDao.getData(total, request,
					cols[Integer.parseInt(sCol)], sdir);
			//System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			// ex.printStackTrace();
			//System.out.println(ex);
		} finally {

		}

	   return null;
   }
   public String deleteHolidayMaster(){
	   String masterid = ServletActionContext.getRequest().getParameter("value");
	   System.out.println("masterid..............."+masterid);
	   HolidayMasterDao holidayMasterDao=new HolidayMasterDao();
	   try{
		 if(holidayMasterDao.deleteholidayMaster(Integer.parseInt(masterid))){
			 addActionMessage("Holiday Master Id "+masterid+ " Deleted SuccessFully");
		 }else{
			 addActionError("Problem in Holiday Master Id Delete");
		 }
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return "success";
   }
   HolidayMasterDao holidayMasterDao=new HolidayMasterDao();
   public String createholidaymaster(){
	   
	   holidaytype=holidayMasterDao.getholidaytypedate();
	   return "success";
   }
   public String createHolidaymasterAction(){
	   String returnString="success";
	   holidaytype=holidayMasterDao.getholidaytypedate();
	  try{
		int	id=holidayMasterDao.saveHolidayMaster(holidayMasterModel);
			addActionMessage("Holiday Master Id "+id+" Created Successfully");
				
			}catch(Exception ex){
				
				returnString= "input"; 
			}
	   return returnString;
   }
public String editholidaymaster(){
	 String masterid = ServletActionContext.getRequest().getParameter("value");
	   holidaytype=holidayMasterDao.getholidaytypedate();
	   holidayMasterModel = holidayMasterDao.getEditedHolidayType(Integer.parseInt(masterid));
	   return "success";
   }
   public String editHolidaymasterAction(){
	   String returnString="success";
	   holidaytype=holidayMasterDao.getholidaytypedate();
	  try{
		  holidayMasterDao.updateHolidayMaster(holidayMasterModel);
			addActionMessage("Holiday Master Id "+holidayMasterModel.getHoliday_master_id() +" updated Successfully");
				
			}catch(Exception ex){
				
				returnString= "input"; 
			}
	   return returnString;
   }
}
