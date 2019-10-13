package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vehicle.model.ServiceType;
import com.trimax.its.vts.dao.VtsDataDAO;

public class KMPLDriverWise extends ActionSupport {
	String path = "";
	char ft = 15;
	String str = "";
	String c = " ";
	public String startdate;
	public String enddate;

	private Map<Integer, String> depotlist;
	private String drivertoken;
	private Map<Integer, String> drivertokenlist;

	public String getDrivertoken() {
		return drivertoken;
	}

	public void setDrivertoken(String drivertoken) {
		this.drivertoken = drivertoken;
	}

	public Map<Integer, String> getDrivertokenlist() {
		return drivertokenlist;
	}

	public void setDrivertokenlist(Map<Integer, String> drivertokenlist) {
		this.drivertokenlist = drivertokenlist;
	}

	public Map<Integer, String> getDepotlist() {
		return depotlist;
	}

	public void setDepotlist(Map<Integer, String> depotlist) {
		this.depotlist = depotlist;
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

	char fl = 18;
	char f2 = 12;
	int pagi = 1;

	int FULL_LEN = 120;
	int HALF_LEN = 60;

	int subtotalTickets = 0;
	int subtotalValues = 0;

	String regionTypeAjaxString = "";

	@Override
	public String execute() throws Exception {
		this.setDepotlist(getDepotName());
		return "success";
	}

	@SuppressWarnings("finally")
	public String KMPLDriverWise() {
		try {

			Date ss1 = new Date();
			Common common = new Common();
			SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			String runDateTime = sdf.format(ss1);

			Session session1 = null;
			Transaction transaction = null;

			String sql = "";
			// session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			// transaction = session1.beginTransaction();
			// Query query =
			// session1.createSQLQuery(sql).addScalar("dte").addScalar("snumber")
			// .addScalar("dist").addScalar("total");
			// query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			// List<Map<String, Object>> aliasToValueMapList = query.list();
			// String realpath = ServletActionContext.getRequest()
			// .getRealPath("/");
			//
			//
			// String filePath = "Reports/";
			//
			// String fileName = "EarlyArrivalSummary.txt";

			regionTypeAjaxString += " <div id='header' style='display: none;'><div align='center'><h4>"
					+ ""
					+ " </br>"
					+ ""
					+ " </br>KMPL Driver Wise Report</br>From Date:- "
					+ startdate + " To Date:-" + enddate + "</h4></div>";

			regionTypeAjaxString += "<div align='right'><b>Run Date:-</b>"
					+ runDateTime + "</div></div>";

			String nwkr = "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _\n\n"
					+ "                                     "
					+ ""
					+ "                                                                          \n"
					+ "                                            "
					+ ""
					+ "                                                                                  \n"
					+ "                                   KMPL Driver Wise Report      "
					+ "  \n                                  From Date:- "
					+ startdate
					+ " To Date:-"
					+ enddate
					+ "    \n"
					+ "                                                               Run Date:-"
					+ runDateTime + "               \n";
			// regionTypeAjaxString
			// +="<div align='center'><b>Range Of Conductors:</b></div>"+"<br>";

			regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>";
			regionTypeAjaxString += "<td align='center' width='10%'><b>Sl No.</b></td><td align='center' width='20%'><b>Driver Token No.</b></td><td></td><td align='center' width='10%'><b>Date</b></td>"
					+ "<td align='center' width='10%'><b>1</b></td>"
					+ "<td align='center' width='10%'><b>2</b></td>"
					+ "<td align='center' width='10%'><b>3</b></td>"
					+ "<td align='center' width='10%'><b>4</b></td>"
					+ "<td align='center' width='10%'><b>5</b></td>"
					+ "<td align='center' width='10%'><b>6</b></td>"
					+ "<td align='center' width='10%'><b>7</b></td>"
					+ "<td align='center' width='10%'><b>8</b></td>"
					+ "<td align='center' width='10%'><b>9</b></td>"
					+ "<td align='center' width='10%'><b>10</b></td>"
					+ "<td align='center' width='10%'><b>11</b></td>"
					+ "<td align='center' width='10%'><b>12</b></td>"
					+ "<td align='center' width='10%'><b>13</b></td>"
					+ "<td align='center' width='10%'><b>14</b></td>"
					+ "<td align='center' width='10%'><b>15</b></td>"
					+ "<td align='center' width='10%'><b>16</b></td>"
					+ "<td align='center' width='10%'><b>17</b></td>"
					+ "<td align='center' width='10%'><b>18</b></td>"
					+ "<td align='center' width='10%'><b>19</b></td>"
					+ "<td align='center' width='10%'><b>20</b></td>"
					+ "<td align='center' width='10%'><b>21</b></td>"
					+ "<td align='center' width='10%'><b>22</b></td>"
					+ "<td align='center' width='10%'><b>23</b></td>"
					+ "<td align='center' width='10%'><b>24</b></td>"
					+ "<td align='center' width='10%'><b>25</b></td>"
					+ "<td align='center' width='10%'><b>26</b></td>"
					+ "<td align='center' width='10%'><b>27</b></td>"
					+ "<td align='center' width='10%'><b>28</b></td>"
					+ "<td align='center' width='10%'><b>29</b></td>"
					+ "<td align='center' width='10%'><b>30</b></td>"
					+ "<td align='center' width='10%'><b>31</b></td></tr>";
			regionTypeAjaxString += "<tr><td align='center' width='20%'></td><td align='center' width='20%'></td>"
					+ "<td align='center' width='20%'><b>Vehicle No.</b></td><td align='center' width='10%'><b></b></td></tr>";
			regionTypeAjaxString += "<tr><td align='center' width='20%'></td><td align='center' width='20%'></td>"
					+ "<td align='center' width='20%'><b>Shedule No.</b></td><td align='center' width='10%'><b></b></td></tr>";
			regionTypeAjaxString += "<tr><td align='center' width='20%'></td><td align='center' width='20%'></td>"
					+ "<td align='center' width='20%'><b></b></td><td align='center' width='10%'><b>KMS</b></td></tr>";
			regionTypeAjaxString += "<tr><td align='center' width='20%'></td><td align='center' width='20%'></td>"
					+ "<td align='center' width='20%'><b></b></td><td align='center' width='10%'><b>HSD</b></td></tr>";
			regionTypeAjaxString += "<tr><td align='center' width='20%'></td><td align='center' width='20%'></td>"
					+ "<td align='center' width='20%'><b></b></td><td align='center' width='10%'><b>KMPL</b></td></tr>";

			// String headingprint
			// =" "+"_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ \n"
			// +add("",5)+"|"+add("Date",20)+ "|"+add("Schedule No",20)+
			// "|"+add("Scheduled Km",20)+"|"+add("Target Earnings",20)+"|"+add("Actual Earnings",20)+"|"+"\n"
			// + " "+
			// "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ \n";
			//
			//

			// String path = realpath + filePath + fileName;
			// str+=ft+nwkr+add(headingprint,5);

			// for (int i = 0; i < aliasToValueMapList.size(); i++) {
			//
			// regionTypeAjaxString +="<tr>";
			// Map<String, Object> list = aliasToValueMapList.get(i);
			// regionTypeAjaxString +="<td>"+list.get("dte").toString()+"</td>";
			// regionTypeAjaxString
			// +="<td>"+list.get("snumber").toString()+"</td>";
			// String
			// sql11="SELECT sum(`distance`) as dista FROM `schedule_details` WHERE `schedule_number` = '"+list.get("dist").toString()+"'";
			// String count = common.getDBResultStr(session1, sql11, "dista");
			// regionTypeAjaxString +="<td>"+count+"</td>";
			// String
			// sql12="SELECT ifnull(targetamount,0)tamount FROM `scheduletargetamount` WHERE `scheduleid` = '"+list.get("dist").toString()+"'";
			// String count1 = common.getDBResultStr(session1, sql12,
			// "tamount");
			// regionTypeAjaxString +="<td>"+count1+"</td>";
			// regionTypeAjaxString
			// +="<td>"+list.get("total").toString()+"</td>";
			// totalamount+=Double.parseDouble(list.get("total").toString());
			//
			// String date = list.get("dte").toString();
			// String snumber = list.get("snumber").toString();
			// String total = list.get("total").toString();
			//
			// str+=""+add("",5)+"|"+add(date,20)+"|" + add(snumber, 20) +"|" +
			// add(count,20)+"|" + add(count1,20)+"|" + add(total,20)+ "|"+"\n";
			// regionTypeAjaxString +="</tr>";
			// }
			//

			// regionTypeAjaxString
			// +="<tr><td colspan='4'><center><b>Grand Total</b></center></td><td align='right'><b>"+
			// ""+"</td></tr>" +"\n";

			regionTypeAjaxString += "</table></div> </div>  <div align='center'><span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span></div>";
			// str+= ""+add("",5)+"|" + add("Total", 90) + "|" +
			// add(String.valueOf(totalamount),20)+ "|"+"\n";
			//
			// ServletActionContext.getRequest().getSession().setAttribute("filePath",realpath
			// + filePath + fileName);
			// System.out.println("path is...."+ServletActionContext.getRequest().getSession().getAttribute("filePath"));

			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;

			// FileOutputStream FOS = new FileOutputStream(path);
			// PrintWriter PW = new PrintWriter(FOS);
			//
			// System.out.println(str);
			// String p=str;
			// System.out.println("realpath="+path);
			// System.out.println("string..@@"+p);
			//
			// PW.println(p);
			// PW.close();
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private Map<Integer, String> getDepotName() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
			String sql = "SELECT org_chart_id , org_name FROM  org_chart WHERE org_type_id = '3' AND deleted_status = '0'";
			Query query = session.createSQLQuery(sql)
					.addScalar("org_chart_id", Hibernate.INTEGER)
					.addScalar("org_name", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			resultMap.put(0, "select");
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				resultMap.put(Integer.parseInt(rs.get("org_chart_id")
						.toString()), rs.get("org_name").toString());
			}
		} catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}

	@SuppressWarnings("unchecked")
	public String getDriverTokenDetail() {

		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("in get");
		int orgchart_id = Integer.parseInt(request.getParameter("val"));
		String regionTypeAjaxString = "";

		List<String> l1 = getDriverTokenNo(orgchart_id);
		regionTypeAjaxString += "<option value='0'>----select----</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
					+ ">" + l1.get(i).toString() + "</option>";
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

	@SuppressWarnings("unchecked")
	public List getDriverTokenNo(int orgchart_id) {
		HttpServletRequest request = ServletActionContext.getRequest();
		// int orgchart_id= Integer.parseInt(request.getParameter("val"));
		// System.out.println("depot id=="+orgchart_id);
		List list = new ArrayList();
		String qry = "SELECT EMPLOYEE_ID,EMPLOYEE_NAME,TOKEN FROM employee WHERE  EMPLOYEE_DESIGNATION = 'Driver' AND STATUS = 'ACTIVE' " +
				"AND org_chart_id ='"+orgchart_id+"' ";

		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("TOKEN").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}

	static String add(String str1, int a1) {
		StringBuilder sb = new StringBuilder(str1);
		int m1 = str1.length();
		if (m1 >= a1) {
			str1.substring(0, a1 - 1);
		}
		a1 = a1 - m1;
		for (int i = 0; i <= a1; i++) {
			sb.append(" ");
		}
		String sb1 = sb.toString();
		return sb1;
	}

	static String addMoney(String str1, int a1) {
		StringBuilder sb = new StringBuilder(str1);
		StringBuilder sb2 = new StringBuilder();

		// String sb1 =
		int m1 = str1.length();
		if (m1 >= a1) {
			str1.substring(0, a1 - 1);
			return str1;
		}
		a1 = a1 - m1;
		for (int i = 0; i < a1; i++) {
			sb2.append(" ");
		}
		sb2.append(sb);
		String sb1 = sb2.toString();
		return sb1;
	}
}
