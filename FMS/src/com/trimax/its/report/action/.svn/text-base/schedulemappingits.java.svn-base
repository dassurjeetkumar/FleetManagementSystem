package com.trimax.its.report.action;

import java.io.FileOutputStream;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;

public class schedulemappingits extends ActionSupport {

	String path="";
	char ft = 15;
	String str="";
	String c=" ";
	
	 public String depotNo;
	 public String divisonNo;
	 
	

	public String getDivisonNo() {
		return divisonNo;
	}

	public void setDivisonNo(String divisonNo) {
		this.divisonNo = divisonNo;
	}

	public String getDepotNo() {
		return depotNo;
	}

	public void setDepotNo(String depotNo) {
		this.depotNo = depotNo;
	}


	char fl = 18;
	char f2 = 12;
	int pagi = 1;

	int FULL_LEN = 120;
	int HALF_LEN = 60;

	int subtotalTickets=0;
	int subtotalValues=0;

	String regionTypeAjaxString = "";

	private Map<Integer, String> divisionlist;

	public Map<Integer, String> getDivisionlist() {
	return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
	this.divisionlist = divisionlist;
	}

	@Override
	public String execute() throws Exception {
	this.setDivisionlist(getDivisionName());
	return "success";

	}
	
	
	

	private Map<Integer, String> getDivisionName() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from OrganisationChart orgchart  " +
						"where parent_id ='1' and deleted_status=0 order by orgchart.org_name");
		try {
			List<OrganisationChart> list = query.list();
			for (OrganisationChart org : list) {
				resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
			}
		} catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
		}
		public String getVehicle() {
		HttpServletRequest req = ServletActionContext.getRequest();

		int parentId = Integer.parseInt(req.getParameter("val"));
		String regionTypeAjaxString = "";

		List<String> l1 = getVehicleId(parentId);
		List<String> l2 = getVehicleName(parentId);

		regionTypeAjaxString += "<option value='0'>------select------</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
					+ ">" + l2.get(i).toString() + "</option>";
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
		
		public List getVehicleId(int orgchart_id) {
		List list = new ArrayList();
		String qry = "select vehicle_id from vehicle where deleted_status=0 and status='ACTIVE' and " +
				"org_chart_id="+orgchart_id+" order by vehicle_id";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("vehicle_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
		}
		public List getVehicleName(int orgchart_id) {
		List list = new ArrayList();
		String qry = "select license_number from vehicle where deleted_status=0 and status='ACTIVE' and " +
				"org_chart_id="+orgchart_id+" order by vehicle_id";
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("license_number").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
		}
		@SuppressWarnings("finally")
		
		
		
		public String mappingshedule(HttpServletRequest request) 		
		{
			
			
			JSONObject result = new JSONObject();
			Session session = null;
			 session = HibernateUtil.getSession("hibernate.cfg.xml");
			String path="";
			char ft = 15;
			String str="";

			String c=" ";

			char fl = 18;
			char f2 = 12;
			int pagi = 1;

			int FULL_LEN = 120;
			int HALF_LEN = 60;

			int subtotalTickets=0;
			int subtotalValues=0;

			String regionTypeAjaxString = "";
			
			
			try{
		//		HttpServletRequest request = ServletActionContext.getRequest();
		//		HttpServletResponse response = ServletActionContext.getResponse();
				
//				int amount = 10;
//				int start = 0;
//				int col = 0;
//				String dir = "asc";
//				String sStart = request.getParameter("iDisplayStart");
//				String sAmount = request.getParameter("iDisplayLength");
//				String sCol = request.getParameter("iSortCol_0");
//				String sdir = request.getParameter("sSortDir_0");
//				String search_term = request.getParameter("sSearch");
//				
//				if (sStart != null) {
//					start = Integer.parseInt(sStart);
//					if (start < 0) {
//					start = 0;
//					}
//				}
//				if (sAmount != null) {
//					amount = Integer.parseInt(sAmount);
//					if (amount < 10 || amount > 50) {
//						amount = 10;
//					}
//				}
//				if (sCol != null) {
//					col = Integer.parseInt(sCol);
//					if (col < 0 || col > 5)
//						col = 0;
//					}
//				if (sdir != null) {
//					if (!sdir.equals("asc"))
//						dir = "desc";
//				}
//				
				
						String querytype ="";
						if(!depotNo.equalsIgnoreCase("0")){
							querytype=" and sm.depot_id='"+depotNo+"'";
						}
						
					    else if(depotNo.equalsIgnoreCase("0")){
					    	querytype="";
					    }
						
		   String sql ="SELECT schedule_id,IFNULL(s.schedule_number,'') as schedule_no,st.schedule_type_name as schedule_type_name," +
		   		"IFNULL(v.license_number,'') as vehicle_no from schedule_mapping_vehicle sm left join schedule s on s.schedule_id=sm.schedule_no " +
		   		"left join schedule_type st on st.schedule_type_id=sm.schedule_type_id " +
		   		"left join vehicle v on v.vehicle_id=sm.vehicle_id where sm.status='ACTIVE' "+querytype+" group by sm.schedule_no";		
					
		   
          	 Query query = session.createSQLQuery(sql)
						 .addScalar("schedule_no", Hibernate.STRING)
						 .addScalar("schedule_type_name", Hibernate.STRING)
						 .addScalar("vehicle_no", Hibernate.STRING);
			    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
	
				

				Date  ss1=new Date();
				SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			    String runDateTime = sdf.format(ss1);
				String realpath = ServletActionContext.getRequest()
						.getRealPath("/");
				
				
		    	JSONArray array = new JSONArray();
				
		
			    
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					int j=i+1;
					JSONArray ja = new JSONArray();
					ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
							+ rs.get("schedule_no").toString()
							+ "' value='"
							+ rs.get("schedule_no").toString() + "'/>");
					ja.add(rs.get("schedule_no").toString());
					ja.add(rs.get("schedule_type_name").toString());
					ja.add(rs.get("vehicle_no").toString());
					
					array.add(ja);
				}
				
					
				 regionTypeAjaxString += "</table></div> </div>  <div align='center'><span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span></div>";
	
					FileOutputStream FOS = new FileOutputStream(path);
					PrintWriter PW = new PrintWriter(FOS);
					
				String p=str;

				PW.println(p);
				PW.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if (session != null) {
					session.close();
				}
			}
			return null;
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
}
	
	