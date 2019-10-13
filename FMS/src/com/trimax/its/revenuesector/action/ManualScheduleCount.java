package com.trimax.its.revenuesector.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class ManualScheduleCount {
	 public String startdate;
	    public String enddate;
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
	private Map<Integer, String> divisionlist;

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}
	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
	public String execute() {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		return "success";
	}
	public String getdata(){
		JSONObject result = new JSONObject();
		Session session = null;
		Connection connection=null;
		Statement stmt=null;
		Session session1 = null;
		ResultSet rs=null;
		Transaction transaction  = null;
		try {
		HttpServletRequest req = ServletActionContext.getRequest();
		Common common = new Common();
	
		String date1=common.getDateFromPicker(startdate);
	//	String date1=req.getParameter("startdate");
		String date2=common.getDateFromPicker(enddate);
		
		
		
		//session = HibernateUtil.getSession("hibernate.cfg.xml");
		 Class.forName("com.mysql.jdbc.Driver");
		 //connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);

		 //connection = DriverManager.getConnection("jdbc:mysql://10.30.1.162:23306/D02?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswaraR");

		 connection = DriverManager.getConnection("jdbc:mysql://10.30.1.162:23306/D02?zeroDateTimeBehavior=convertToNull&autoReconnect=true","revenuereport","ReporTCentraL");

		 System.out.println("connection........."+connection);
	 stmt = connection.createStatement();
	 
	 String sql="SELECT 'Depot02',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D02.Waybill_Details wd inner join D02.form_four ff on wd.schedule_No = ff.form_four_id inner join D02.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D02.schedule_type st on st.schedule_type_id =  wd.Schedult_Type inner join D02.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				"union " + 
				 "SELECT 'Depot03',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D03.Waybill_Details wd inner join D03.form_four ff on wd.schedule_No = ff.form_four_id inner join D03.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D03.schedule_type st on st.schedule_type_id =  wd.Schedult_Type inner join D03.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				"union " + 
				"SELECT 'Depot04',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D04.Waybill_Details wd inner join D04.form_four ff on wd.schedule_No = ff.form_four_id inner join D04.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D04.schedule_type st on st.schedule_type_id =  wd.Schedult_Type inner join D04.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				"union " + 
				"SELECT 'Depot06',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D06.Waybill_Details wd inner join D06.form_four ff on wd.schedule_No = ff.form_four_id inner join D06.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D06.schedule_type st on st.schedule_type_id =  wd.Schedult_Type inner join D06.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				"union " + 
				"SELECT 'Depot07',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D07.Waybill_Details wd inner join D07.form_four ff on wd.schedule_No = ff.form_four_id inner join D07.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D07.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D07.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				"union " + 
				"SELECT 'Depot08',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D08.Waybill_Details wd inner join D08.form_four ff on wd.schedule_No = ff.form_four_id inner join D08.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D08.schedule_type st on st.schedule_type_id =  wd.Schedult_Type inner join D08.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				"union " + 
				"SELECT 'Depot09',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D09.Waybill_Details wd inner join D09.form_four ff on wd.schedule_No = ff.form_four_id inner join D09.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D09.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D09.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				"union " + 
				"SELECT 'Depot10',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D10.Waybill_Details wd inner join D10.form_four ff on wd.schedule_No = ff.form_four_id inner join D10.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D10.schedule_type st on st.schedule_type_id =  wd.Schedult_Type inner join D10.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				"union " + 
				"SELECT 'Depot11',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D11.Waybill_Details wd inner join D11.form_four ff on wd.schedule_No = ff.form_four_id inner join D11.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D11.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D11.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				"union " + 
				"SELECT 'Depot12',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D12.Waybill_Details wd inner join D12.form_four ff on wd.schedule_No = ff.form_four_id inner join D12.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D12.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D12.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				"union " + 
				"SELECT 'Depot13',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D13.Waybill_Details wd inner join D13.form_four ff on wd.schedule_No = ff.form_four_id inner join D13.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D13.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D13.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				"union " + 
				"SELECT 'Depot14',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D14.Waybill_Details wd inner join D14.form_four ff on wd.schedule_No = ff.form_four_id inner join D14.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D14.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D14.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				"union " + 
				"SELECT 'Depot15',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D15.Waybill_Details wd inner join D15.form_four ff on wd.schedule_No = ff.form_four_id inner join D15.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D15.schedule_type st on st.schedule_type_id =  wd.Schedult_Type inner join D15.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				"union " + 
				"SELECT 'Depot16',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D16.Waybill_Details wd inner join D16.form_four ff on wd.schedule_No = ff.form_four_id inner join D16.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D16.schedule_type st on st.schedule_type_id =  wd.Schedult_Type inner join D16.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				"union " + 
				"SELECT 'Depot17',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D17.Waybill_Details wd inner join D17.form_four ff on wd.schedule_No = ff.form_four_id inner join D17.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D17.schedule_type st on st.schedule_type_id =  wd.Schedult_Type inner join D17.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot18',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D18.Waybill_Details wd inner join D18.form_four ff on wd.schedule_No = ff.form_four_id inner join D18.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D18.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D18.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot19',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D19.Waybill_Details wd inner join D19.form_four ff on wd.schedule_No = ff.form_four_id inner join D19.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D19.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D19.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot20',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D20.Waybill_Details wd inner join D20.form_four ff on wd.schedule_No = ff.form_four_id inner join D20.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D20.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D20.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot21',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D21.Waybill_Details wd inner join D21.form_four ff on wd.schedule_No = ff.form_four_id inner join D21.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D21.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D21.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot22',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D22.Waybill_Details wd inner join D22.form_four ff on wd.schedule_No = ff.form_four_id inner join D22.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D22.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D22.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot23',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D23.Waybill_Details wd inner join D23.form_four ff on wd.schedule_No = ff.form_four_id inner join D23.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D23.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D23.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot24',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D24.Waybill_Details wd inner join D24.form_four ff on wd.schedule_No = ff.form_four_id inner join D24.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D24.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D24.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot25',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D25.Waybill_Details wd inner join D25.form_four ff on wd.schedule_No = ff.form_four_id inner join D25.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D25.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D25.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot26',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D26.Waybill_Details wd inner join D26.form_four ff on wd.schedule_No = ff.form_four_id inner join D26.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D26.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D26.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot27',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D27.Waybill_Details wd inner join D27.form_four ff on wd.schedule_No = ff.form_four_id inner join D27.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D27.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D27.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot28',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D28.Waybill_Details wd inner join D28.form_four ff on wd.schedule_No = ff.form_four_id inner join D28.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D28.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D28.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot29',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D29.Waybill_Details wd inner join D29.form_four ff on wd.schedule_No = ff.form_four_id inner join D29.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D29.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D29.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot30',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D30.Waybill_Details wd inner join D30.form_four ff on wd.schedule_No = ff.form_four_id inner join D30.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D30.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D30.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot31',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D31.Waybill_Details wd inner join D31.form_four ff on wd.schedule_No = ff.form_four_id inner join D31.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D31.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D31.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot32',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D32.Waybill_Details wd inner join D32.form_four ff on wd.schedule_No = ff.form_four_id inner join D32.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D32.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D32.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot33',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D33.Waybill_Details wd inner join D33.form_four ff on wd.schedule_No = ff.form_four_id inner join D33.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D33.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D33.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot34',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D34.Waybill_Details wd inner join D34.form_four ff on wd.schedule_No = ff.form_four_id inner join D34.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D34.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D34.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot35',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D35.Waybill_Details wd inner join D35.form_four ff on wd.schedule_No = ff.form_four_id inner join D35.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D35.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D35.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot36',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D36.Waybill_Details wd inner join D36.form_four ff on wd.schedule_No = ff.form_four_id inner join D36.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D36.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D36.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot37',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D37.Waybill_Details wd inner join D37.form_four ff on wd.schedule_No = ff.form_four_id inner join D37.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D37.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D37.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot38',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D38.Waybill_Details wd inner join D38.form_four ff on wd.schedule_No = ff.form_four_id inner join D38.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D38.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D38.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot39',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D39.Waybill_Details wd inner join D39.form_four ff on wd.schedule_No = ff.form_four_id inner join D39.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D39.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D39.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot40',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D40.Waybill_Details wd inner join D40.form_four ff on wd.schedule_No = ff.form_four_id inner join D40.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D40.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D40.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot41',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D41.Waybill_Details wd inner join D41.form_four ff on wd.schedule_No = ff.form_four_id inner join D41.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D41.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D41.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot43',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D43.Waybill_Details wd inner join D43.form_four ff on wd.schedule_No = ff.form_four_id inner join D43.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D43.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D43.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot44',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D44.Waybill_Details wd inner join D44.form_four ff on wd.schedule_No = ff.form_four_id inner join D44.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D44.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D44.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot45',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D45.Waybill_Details wd inner join D45.form_four ff on wd.schedule_No = ff.form_four_id inner join D45.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D45.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D45.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot46',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D46.Waybill_Details wd inner join D46.form_four ff on wd.schedule_No = ff.form_four_id inner join D46.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D46.schedule_type st on st.schedule_type_id = wd.Schedult_Type inner join D46.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				" " + 
				"union " + 
				"SELECT 'Depot47',count(*)tot,ifnull(sum(case when  wd.ETIM_Coll_Amt=0 and wd.Bag_Coll_Amt!=0 then 1 else 0 end),0)tot1 FROM D47.Waybill_Details wd inner join D47.form_four ff on wd.schedule_No = ff.form_four_id inner join D47.ticketbag_master tm on tm.ticketbag_id=wd.Bag_No inner join D47.schedule_type st on st.schedule_type_id =  wd.Schedult_Type inner join D47.shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') AND generated_Date between '"+date1+"' and '"+date2+"'    " + 
				"";
		 System.out.println(sql);
		 rs = stmt.executeQuery(sql);
	    
	      JSONArray array = new JSONArray();
	    int i=0;
	      while (rs.next()) {
	        	JSONArray ja = new JSONArray();
	         i++;
	        	// Map<String, Object> list = aliasToValueMapList.get(i);
	        

		 
			ja.add(i);
			//ja.add(rs.getString("depot02").toString());
	
			
		
			if(rs.getString("tot1").toString().trim().equals("0")){
				ja.add("<center>"+rs.getString("Depot02").toString()+"<center>");
			}else{
			 ja.add("<center>"+"<a data-toggle='modal' class='btn blue' role='button' href='#mymodal11'  onclick=javascript:getdepotwise('D"+rs.getString("depot02").toString().substring(5, 7)+"','"+date1+"','"+date2+"') title='Details'>"+rs.getString("Depot02").toString() + "</a>"+"<center>");
			}
			if(rs.getString("tot").toString().trim().equals("0") && rs.getString("tot1").toString().trim().equals("0")) {	
				ja.add("<center>"+"Sync Issue"+"<center>");
				}else {ja.add("<center>"+rs.getString("tot").toString()+"<center>");}
			if(rs.getString("tot").toString().trim().equals("0") && rs.getString("tot1").toString().trim().equals("0")) {
				ja.add("<center>"+"Sync Issue"+"<center>");
			}else {
			ja.add("<center>"+rs.getString("tot1").toString()+"<center>");		
			}
			//System.out.println(Integer.parseInt(rs.getString("tot").toString())+"----"+rs.getString("tot1").toString());
			if(rs.getString("tot").toString().trim().equals("0") || rs.getString("tot1").toString().trim().equals("0")) {
				ja.add("<center>"+"0%"+"<center>");
			}else {
				System.out.println("in------");
				int t=Integer.parseInt(rs.getString("tot1").toString());
				int tt=Integer.parseInt(rs.getString("tot").toString());
			double tot=((double)((double)t*100/(double)tt));
			BigDecimal BD	=new BigDecimal(tot);
			//BD.setScale(2, RoundingMode.HALF_EVEN);
			ja.add("<center>"+BD.setScale(1, RoundingMode.HALF_EVEN)+"%</center>");
				//ja.add(tot);

			}
			array.add(ja);
			
				
				 }
	
	        HttpServletResponse response = ServletActionContext.getResponse();

	   	 PrintWriter out;

	   		    	result.put("aaData",array);
	   				out = response.getWriter();
	   				out.print(result);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
			stmt.close();
			connection.close();
			}catch (Exception e) {
			e.printStackTrace();
			}
		}
		return null;
		
	}
	public String getdepotdata(){

		JSONObject result = new JSONObject();
		Session session = null;
		Connection connection=null;
		Statement stmt=null;
		Session session1 = null;
		ResultSet rs=null;
		Transaction transaction  = null;
		try {
		HttpServletRequest req = ServletActionContext.getRequest();
		
		String DB=req.getParameter("orgname");
		String date1=req.getParameter("date1");
		String date2=req.getParameter("date2");
		
		 Class.forName("com.mysql.jdbc.Driver");

		 connection = DriverManager.getConnection("jdbc:mysql://10.30.1.162:23306/"+DB+"?zeroDateTimeBehavior=convertToNull&autoReconnect=true","revenuereport","ReporTCentraL");
		// connection = DriverManager.getConnection("jdbc:mysql://10.30.1.162:23306/"+DB+"?zeroDateTimeBehavior=convertToNull&autoReconnect=true","venkateswara","VenkateswaraR");

		// connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
		// connection = DriverManager.getConnection("jdbc:mysql://10.30.1.162:23306/"+DB+"?zeroDateTimeBehavior=convertToNull&autoReconnect=true","bmtcdepot_usr","depot@123");

		 System.out.println("connection........."+connection);
	 stmt = connection.createStatement();
		
		//session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql="SELECT generated_Date,schedule_number_name, schedule_type_name, shift_type_name,"
				+ "bag_code,(ETIM_Coll_Amt+Bag_Coll_Amt) as collection,wd.waybill_no FROM `Waybill_Details` wd "
				+ "inner join form_four ff on wd.schedule_No = ff.form_four_id "
				+ "inner join ticketbag_master tm on tm.ticketbag_id=wd.Bag_No "
				+ "inner join schedule_type st on st.schedule_type_id =  wd.Schedult_Type "
				+ "inner join shift_type sy on sy.shift_type_id = wd.Shift_Type WHERE wd.Status IN ('AUDITED','Closed') "
				+ "AND generated_Date between '"+date1+"' and  '"+date2+"' and ETIM_Coll_Amt=0 having collection >0  "
				+ "order by schedule_type_name,shift_type_name";  
				
		 System.out.println(sql);
		 rs = stmt.executeQuery(sql);
	    
	      JSONArray array = new JSONArray();
		    int i=0;
		      while (rs.next()) {
		        	JSONArray ja = new JSONArray();
		         i++;
		  
				ja.add(i);
				ja.add(rs.getString("waybill_no").toString());
				ja.add(rs.getString("generated_Date").toString());
		ja.add(rs.getString("schedule_number_name").toString());		
		ja.add(rs.getString("schedule_type_name").toString());
	ja.add(rs.getString("shift_type_name").toString());	
	ja.add(rs.getString("bag_code").toString());
ja.add(rs.getString("collection").toString());	

//ja.add(rs.getString("tot").toString());	
				array.add(ja);
				
					
					 }
	        HttpServletResponse response = ServletActionContext.getResponse();

	   	 PrintWriter out;

	   		    	result.put("aaData",array);
	   				out = response.getWriter();
	   				out.print(result);
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
			stmt.close();
			connection.close();
			}catch (Exception e) {
			e.printStackTrace();
			}
		}
		return null;
		
		
	}
	
}
