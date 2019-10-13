package com.trimax.its.transport.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import com.trimax.its.cashremittancevoucher.dao.CashRemittanceVoucherDao;
import com.trimax.its.ticketing.dao.TicketInventoryDao;
import com.trimax.its.transport.dao.WeeklyChartDao;
import com.trimax.its.transport.model.FormFour;
import com.trimax.its.transport.model.Schedule;

import com.trimax.its.transport.model.WeeklyChart;

public class WeeklyChartAction extends ActionSupport {// implements Preparable {

	private String SEARCH_TERM;
	private String COL_NAME;
	private String DIR;
	private int START;
	private int AMOUNT;

	String insertstaus;
	String updatestaus;
	String deletestaus;

	private List<WeeklyChart> weeklychartList = new ArrayList<WeeklyChart>();
	private WeeklyChart weeklychart;
	private Schedule schedule;
	private FormFour formfour;
	private int scheduleid;
	private int scheduleid1;
	private int scheduleidlist;
	private int createid;

	private Map<Integer, String> scheduleList;
	private List<FormFour> formFourList;
	private List<WeeklyChart> weeeklylist;

	public Map<Integer, String> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(Map<Integer, String> scheduleList) {
		this.scheduleList = scheduleList;
	}

	public List<FormFour> getFormFourList() {
		return formFourList;
	}

	public void setFormFourList(List<FormFour> formFourList) {
		this.formFourList = formFourList;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public int getScheduleid() {
		return scheduleid;
	}

	public void setScheduleid(int scheduleid) {
		this.scheduleid = scheduleid;
	}

	public int getScheduleid1() {
		return scheduleid1;
	}

	public void setScheduleid1(int scheduleid1) {
		this.scheduleid1 = scheduleid1;
	}
	

	public int getScheduleidlist() {
		return scheduleidlist;
	}

	public void setScheduleidlist(int scheduleidlist) {
		this.scheduleidlist = scheduleidlist;
	}

	
	public int getCreateid() {
		return createid;
	}

	public void setCreateid(int createid) {
		this.createid = createid;
	}

	public FormFour getFormfour() {
		return formfour;
	}

	public void setFormfour(FormFour formfour) {
		this.formfour = formfour;
	}

	public List<WeeklyChart> getWeeklychartList() {
		return weeklychartList;
	}

	public void setWeeklychartList(List<WeeklyChart> weeklychartList) {
		this.weeklychartList = weeklychartList;
	}

	public List<WeeklyChart> getWeeeklylist() {
		return weeeklylist;
	}

	public void setWeeeklylist(List<WeeklyChart> weeeklylist) {
		this.weeeklylist = weeeklylist;
	}

	public WeeklyChart getWeeklychart() {
		return weeklychart;
	}

	public void setWeeklychart(WeeklyChart weeklychart) {
		this.weeklychart = weeklychart;
	}

	public String getInsertstaus() {
		return insertstaus;
	}

	public void setInsertstaus(String insertstaus) {
		this.insertstaus = insertstaus;
	}

	public String getUpdatestaus() {
		return updatestaus;
	}

	public void setUpdatestaus(String updatestaus) {
		this.updatestaus = updatestaus;
	}

	public String getDeletestaus() {
		return deletestaus;
	}

	public void setDeletestaus(String deletestaus) {
		this.deletestaus = deletestaus;
	}

	@SkipValidation
	public String execute() {

		return null;
	}

	@SkipValidation
	public String showWeeklyChart() {
		WeeklyChartDao dao = new WeeklyChartDao();
		scheduleList = dao.getScheduleNumber();
		return "success";
	}

	@SkipValidation
	public String viewWeeklyChartList() throws IOException {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			WeeklyChartDao dao = new WeeklyChartDao();
			int schdid = Integer.parseInt(request.getParameter("scheduleid2"));
			request.getSession().setAttribute("scheduleid2", schdid);
			
			String[] cols = { "form_four_id ", "schedule_number_name","effective_start_date","effective_end_date"};
			String[] dbcol = { "form_four_id", "schedule_number_name","effective_start_date","effective_end_date" };
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
			int total = -1;
            total=dao.getTotalRecords(total,request,dbcol[Integer.parseInt(sCol)], sdir, schdid);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;
//			total=10;
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = dao.getList(total,request,dbcol[Integer.parseInt(sCol)], sdir, schdid);
			out.print(result);
		} catch (Exception ex) {
		  ex.printStackTrace();
		} 
		
		return null;

	}

	/*public String showScheduleList() {
		WeeklyChartDao dao = new WeeklyChartDao();
		scheduleList = dao.getScheduleNumber();
		return "success";
	}*/

	public String showFormFourList() {
		WeeklyChartDao dao = new WeeklyChartDao();
		formFourList = dao.getFormFourList(scheduleid);
		return "success";
	}

	public String createWeeklyChart() {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		WeeklyChartDao dao = new WeeklyChartDao();
		int id1;
//		for (int i = 0; i < weeklychartList.size(); i++) {
		String[] formfourid = request.getParameterValues("formFourid");
//		
//		// id1 = Integer.parseInt(formfourid[i]);
//		System.out.println("hhhhhhh"+formfourid);
		if(formfourid==null){
			
			setInsertstaus("EmptyData");
			return "input";
		}else if (!dao.checkWeeklyChartFormFour(weeklychartList)) {
			int id = 0;
			try {
				//id = dao.createWeeklyChart(weeklychartList);
			} catch (Exception ex) {
				ex.printStackTrace();
				return "input";
			} finally {
				if (id > 0) {
					setInsertstaus("success");
					addActionMessage("FormFour WeeklyChart is Created Successfully");
				}
			}

		} else {
			setInsertstaus("duplicate");
			return "input";
		}
		//}
		return "success";

	}

	public String addEditWeeklyChart() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		int id = 0;
		WeeklyChartDao dao = new WeeklyChartDao();
		String schedulenumber=request.getParameter("schedulenumber");
			String[] formfourid1 = request.getParameterValues("formfourid");		
			String[] weklyid = request.getParameterValues("abcName");
			if(formfourid1==null){
				setUpdatestaus("EmptyDataforupdate");
				return "input";
			}else{
			try {
			dao.updateWeeklyChart(weeklychartList, weklyid,formfourid1);
			//WeeklyChartDao dao = new WeeklyChartDao();
			scheduleList = dao.getScheduleNumber();
			setUpdatestaus("success");
			addActionMessage("Schedule Weekly Chart of Schedule Number "+ schedulenumber  +" Updated Successfully");
			 }
		   catch (Exception ex) {
			setUpdatestaus("fail");
			return "input";
		} 
			}
		return "success";
	}

	@SkipValidation
	public String editWeeklyChart() throws IOException {
		WeeklyChartDao dao = new WeeklyChartDao();
		scheduleList = dao.getScheduleNumber();
		return "success";

	}

	/*
	 * @SkipValidation public void editWeeklyChartList() throws IOException {
	 * try { HttpServletRequest request = ServletActionContext.getRequest();
	 * HttpServletResponse response = ServletActionContext.getResponse();
	 * WeeklyChartDao dao = new WeeklyChartDao(); int schdid =
	 * Integer.parseInt(request.getParameter("scheduleid2"));
	 * request.getSession().setAttribute("scheduleid2", schdid); //
	 * System.out.println("Count------>" +schdid);
	 * 
	 * String[] cols = { " ", "weekly_chart_id", "form4.scheduleNumberName" };
	 * String[] dbcol = { " ", "weekly_chart_id", "form4.scheduleNumberName" };
	 * JSONObject result = new JSONObject(); int amount = 10; int start = 0; int
	 * col = 0; String dir = "asc";
	 * 
	 * String sStart = request.getParameter("iDisplayStart"); String sAmount =
	 * request.getParameter("iDisplayLength"); String sCol =
	 * request.getParameter("iSortCol_0"); String sdir =
	 * request.getParameter("sSortDir_0");
	 * 
	 * if (sStart != null) { start = Integer.parseInt(sStart); if (start < 0) {
	 * start = 0; } } if (sAmount != null) { amount = Integer.parseInt(sAmount);
	 * if (amount < 10 || amount > 50) { amount = 10; } } if (sCol != null) {
	 * col = Integer.parseInt(sCol); if (col < 0 || col > 5) col = 0; } if (sdir
	 * != null) { if (!sdir.equals("asc")) dir = "desc"; }
	 * 
	 * String colName = cols[col]; int total = -1;
	 * 
	 * AMOUNT = amount; SEARCH_TERM = request.getParameter("sSearch"); COL_NAME
	 * = colName; DIR = dir; START = start;
	 * 
	 * response.setContentType("application/json");
	 * response.setHeader("Cache-Control", "no-store"); PrintWriter out =
	 * response.getWriter();
	 * 
	 * result = dao.getEditList(total, request, schdid); //
	 * System.out.println("REsult of datatable------>" + result);
	 * out.print(result); } catch (Exception ex) { //
	 * System.out.println("=====?" + ex); // ex.printStackTrace(); } finally {
	 * 
	 * }
	 * 
	 * }
	 */

	public String getScheduleName() {
		WeeklyChartDao dao = new WeeklyChartDao();
		List<String> l1 = dao.getScheduleid();
		List<String> l2 = dao.getschedulenumberName();
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=0>Select</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option id='scheduleNumber" + l1.get(i)+ "' value=" + l1.get(i).toString() + ">"+ l2.get(i).toString() + "</option>";
		}
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

	/*
	 * public String getFormFourName() { HttpServletRequest request =
	 * ServletActionContext.getRequest(); HttpSession mySession =
	 * request.getSession(); int scheduleid =
	 * Integer.parseInt(request.getParameter("schedule_id1")); WeeklyChartDao
	 * dao = new WeeklyChartDao(); // serviceTypeIds=rmDao.getServiceId();
	 * List<String> l1 = dao.getFormfourId(scheduleid); List<String> formfour =
	 * dao.getFormfourName(scheduleid); String regionTypeAjaxString = "";
	 * regionTypeAjaxString += "<option value=0></option>"; for (int i = 0; i <
	 * l1.size(); i++) { regionTypeAjaxString += "<option id='scheduleNoName" +
	 * l1.get(i) + "' value=" + l1.get(i).toString() + ">" +
	 * formfour.get(i).toString() + "</option>"; //
	 * regionTypeAjaxString="<input type='checkbox'/>"; } HttpServletResponse
	 * response = ServletActionContext.getResponse(); PrintWriter out; try { out
	 * = response.getWriter(); out.print(regionTypeAjaxString); } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * } return null;
	 * 
	 * }
	 */

	public String showWeeklyList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession mySession = request.getSession();
		WeeklyChartDao dao = new WeeklyChartDao();
		
		weeeklylist = dao.getEditedWeeklyChart(scheduleid1);
//		

		return "success";
		
	}
	
	
	/*public String checkCreatedWeeklyChart(){
		try{
			
		}catch(Exception  e){
			e.printStackTrace();
			
		}
	}*/
}
