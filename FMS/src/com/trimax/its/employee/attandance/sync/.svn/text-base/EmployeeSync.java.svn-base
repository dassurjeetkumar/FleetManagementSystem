package com.trimax.its.employee.attandance.sync;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;
import com.trimax.its.util.HibernateUtil;

/**
 * Servlet implementation class EmployeeSync
 */
@WebServlet("/EmployeeSync.api")
public class EmployeeSync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */


	Gson gson = null;
    public EmployeeSync() {
        super();
        // TODO Auto-generated constructor stub
        gson = new Gson();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
		// TODO Auto-generated method stub
		String jsonData = "";
		
		String formAction = request.getParameter("formAction");
		if(formAction!=null&&formAction.equalsIgnoreCase("getDepots"))
		{
			ArrayList<Depot> list = getDepots();
			jsonData = gson.toJson(list);
		}
		else if(formAction!=null&&formAction.equalsIgnoreCase("syncData"))
		{
			String depotId = request.getParameter("depotId");
			String employeeToken = request.getParameter("employeeToken");
			ArrayList<EmployeeAttendanceData> list = getEmployeeAttandanceData(employeeToken,Integer.parseInt(depotId));
			jsonData = gson.toJson(list);
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		out.print(jsonData);
	}

	public ArrayList<Depot> getDepots()
	{
		ArrayList<Depot> res = new ArrayList<Depot>();
		Session session = null;
		Transaction transaction  = null;
		
		try
		{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			transaction = session.beginTransaction();
			String sql = "select org_chart_id,org_name from org_chart where deleted_status=0 and org_name like 'Depot%'";
	        SQLQuery query = session.createSQLQuery(sql);
	        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
	        java.util.List data = query.list();
	       // java.util.List depots = query.list();
	        
	         for(Object object : data) {
	            Map row = (Map)object;
	            Depot depot = new Depot();
	            depot.setDepotId((Integer)row.get("org_chart_id")); 
	            depot.setDepotName((String)row.get("org_name"));
	            res.add(depot);
	         }
	        
	        transaction.commit();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			transaction.rollback();
		}
		finally
		{
			if(session!=null)
				session.close(); 
		}
		
		return res;
	}
	public ArrayList<EmployeeAttendanceData> getEmployeeAttandanceData(String empId,Integer sourceDepot)
	{
		Connection conn = null;
		Statement smt = null;
		ResultSet rs = null;
		ArrayList<EmployeeAttendanceData> list = new ArrayList<EmployeeAttendanceData>();
		try
		{
			conn = getDepotCentralServerConnection(sourceDepot);
			
			String sql = "select employee_att_data_id,employee_token_no,schedule_id,schedile_no,schedule_type_id,shift_type,duty_date,attendance,duty_hours,double_duty,waybill_id,logsheet_id,opted_for_GH,inserted_date,inserted_by,updated_date,updated_by,deleted_status from employee_attendance_data where employee_token_no = "+empId+" and date(duty_date) >= (last_day(curdate() - interval 2 month) + interval 1 day)";
			System.out.println(sql);
			smt = conn.createStatement();
			rs = smt.executeQuery(sql);
			while(rs.next())
			{
				EmployeeAttendanceData employeeAttendanceData = new EmployeeAttendanceData();
				employeeAttendanceData.setEmployeeAttDataId(rs.getInt("employee_att_data_id"));
				employeeAttendanceData.setEmployeeTokenNo(rs.getString("employee_token_no"));
				employeeAttendanceData.setScheduleId(rs.getString("schedule_id"));
				employeeAttendanceData.setSchedileNo(rs.getString("schedile_no"));
				employeeAttendanceData.setSchedule_type_id(rs.getString("schedule_type_id"));
				employeeAttendanceData.setShiftType(rs.getInt("shift_type"));
				employeeAttendanceData.setDutyDate(rs.getString("duty_date"));
				employeeAttendanceData.setAttendance(rs.getString("attendance"));
				employeeAttendanceData.setDutyHours(rs.getString("duty_hours"));
				employeeAttendanceData.setDoubleDuty(rs.getString("double_duty"));
				employeeAttendanceData.setWaybillId(rs.getInt("waybill_id"));
				employeeAttendanceData.setLogsheetId(rs.getInt("logsheet_id"));
				employeeAttendanceData.setOptedForGH(rs.getString("opted_for_GH"));
				employeeAttendanceData.setInsertedDate(rs.getString("inserted_date"));
				employeeAttendanceData.setInsertedBy(rs.getInt("inserted_by"));
				employeeAttendanceData.setUpdatedDate(rs.getString("updated_date"));
				employeeAttendanceData.setUpdatedBy(rs.getInt("updated_by"));
				employeeAttendanceData.setDeletedStatus(rs.getInt("deleted_status"));
				list.add(employeeAttendanceData);
			}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		finally
		{
			try {
				if(conn!=null)
					conn.close();
						
			}
			catch(Exception exp)
			{exp.printStackTrace();}

			
		}
		return list;
	}
	public Connection getDepotCentralServerConnection(Integer depotId)
	{
		Session session = null;
		Transaction transaction  = null;
		Connection connection = null;
		try
		{
			session = HibernateUtil.getSession("hibernate.cfg.xml");
			transaction = session.beginTransaction();
			String sql = "SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot02.depot_ip_info WHERE depot_id="+depotId;
			SQLQuery query = session.createSQLQuery(sql);
	        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
	        java.util.List data = query.list();
	        
	         for(Object object : data) {
		            Map row = (Map)object;
		            String depotname = (String)row.get("depotname"); 
		            String centralIp = (String)row.get("central_ip");
		            String centralUname = (String)row.get("central_uname"); 
		            String celtralPwd =(String)row.get("central_pwd");
		            String dbname = (String)row.get("dbname");
		            Class.forName("com.mysql.jdbc.Driver");
					try
					{
						String dburl = "jdbc:mysql://"+centralIp+":23306/" + depotname + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true";
						System.out.println("dburl["+dburl+"] username["+centralUname+"] password["+celtralPwd+"]");
						connection = DriverManager.getConnection(dburl,centralUname,celtralPwd);
						break;
					}catch(Exception ex)
					{
						ex.printStackTrace();
					}
	         }
	         transaction.commit();
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close(); 
		}
		return connection;
	}
}
class Depot
{
	public Integer depotId;
	public String depotName;
	public Integer getDepotId() {
		return depotId;
	}
	public void setDepotId(Integer depotId) {
		this.depotId = depotId;
	}
	public String getDepotName() {
		return depotName;
	}
	public void setDepotName(String depotName) {
		this.depotName = depotName;
	}
}
class EmployeeAttendanceData
{
	Integer employeeAttDataId;
	String employeeTokenNo;
	String scheduleId;
	String schedileNo;
	String schedule_type_id;
	Integer shiftType;
	String dutyDate;
	String attendance;
	String dutyHours;
	String doubleDuty;
	Integer waybillId;
	Integer logsheetId;
	String optedForGH;
	String insertedDate;
	Integer insertedBy;
	String updatedDate;
	Integer updatedBy;
	Integer deletedStatus;
	public Integer getEmployeeAttDataId() {
		return employeeAttDataId;
	}
	public void setEmployeeAttDataId(Integer employeeAttDataId) {
		this.employeeAttDataId = employeeAttDataId;
	}
	public String getEmployeeTokenNo() {
		return employeeTokenNo;
	}
	public void setEmployeeTokenNo(String employeeTokenNo) {
		this.employeeTokenNo = employeeTokenNo;
	}
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getSchedileNo() {
		return schedileNo;
	}
	public void setSchedileNo(String schedileNo) {
		this.schedileNo = schedileNo;
	}
	public String getSchedule_type_id() {
		return schedule_type_id;
	}
	public void setSchedule_type_id(String schedule_type_id) {
		this.schedule_type_id = schedule_type_id;
	}
	public Integer getShiftType() {
		return shiftType;
	}
	public void setShiftType(Integer shiftType) {
		this.shiftType = shiftType;
	}
	public String getDutyDate() {
		return dutyDate;
	}
	public void setDutyDate(String dutyDate) {
		this.dutyDate = dutyDate;
	}
	public String getAttendance() {
		return attendance;
	}
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	public String getDutyHours() {
		return dutyHours;
	}
	public void setDutyHours(String dutyHours) {
		this.dutyHours = dutyHours;
	}
	public String getDoubleDuty() {
		return doubleDuty;
	}
	public void setDoubleDuty(String doubleDuty) {
		this.doubleDuty = doubleDuty;
	}
	public Integer getWaybillId() {
		return waybillId;
	}
	public void setWaybillId(Integer waybillId) {
		this.waybillId = waybillId;
	}
	public Integer getLogsheetId() {
		return logsheetId;
	}
	public void setLogsheetId(Integer logsheetId) {
		this.logsheetId = logsheetId;
	}
	public String getOptedForGH() {
		return optedForGH;
	}
	public void setOptedForGH(String optedForGH) {
		this.optedForGH = optedForGH;
	}
	public String getInsertedDate() {
		return insertedDate;
	}
	public void setInsertedDate(String insertedDate) {
		this.insertedDate = insertedDate;
	}
	public Integer getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Integer insertedBy) {
		this.insertedBy = insertedBy;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Integer getDeletedStatus() {
		return deletedStatus;
	}
	public void setDeletedStatus(Integer deletedStatus) {
		this.deletedStatus = deletedStatus;
	}
	
	public String getInsertQuery()
	{
		return "query";
	}
}
