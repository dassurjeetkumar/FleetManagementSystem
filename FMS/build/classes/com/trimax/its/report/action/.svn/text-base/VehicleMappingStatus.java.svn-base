package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.jaxb.xml.ws.vts.trimax.com.VtsResponseNew;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.dao.EtmDeviceHistoryDao;
import com.trimax.its.vts.dao.VtsDataDAO;
import com.trimax.vts.NewWsVts_Service;
import com.trimax.vts.VtsDataModelNew;

public class VehicleMappingStatus {
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
		try {
		HttpServletRequest req = ServletActionContext.getRequest();
		
		String divid=req.getParameter("divid");
		String subquy="and oc.parent_id="+divid;
		if(divid.equals("0")){
			subquy="";
		}
		
		
		
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql="select org_name,org_chart_id,sum(case when license_number is not null then 1 else 0 end)noveh,"+
				"sum(case when (device_id is null || device_serial_number like 'Device%' || device_serial_number like 'scrap%' || device_serial_number like 'Nrd%' || device_serial_number like 'test%') then 1 else 0 end) novehnodev,"+
				"sum(case when sim_id is null then 1 else 0 end)novehnosim,"+
				"sum(case when (sim_id is  null || device_id is  null || device_serial_number like 'Device%' || "+
                " device_serial_number like 'scrap%' || device_serial_number like 'Nrd%' || device_serial_number like 'test%') then 1 else 0 end)novehnotmap "+
				"from ("+
				"select org_name,oc.org_chart_id,license_number,d.device_id,sim_id,device_serial_number "+
				"from vehicle v "+
				"inner join org_chart oc on v.org_chart_id=oc.org_chart_id "+
				"left join vehicle_device vd on v.vehicle_id=vd.vehicle_id and vd.status='ACTIVE' "+
				"left join device d on d.device_id=vd.device_id and d.deleted_status=0 and d.status='ACTIVE' "+
				"left join sim_vtu sv on sv.device_id =d.device_id and sv.status='ACTIVE' "+
				"where v.deleted_status=0 and cause_status !='s' and v.status='ACTIVE' "+subquy+" "+  
				")a group by org_chart_id order by org_name";
		Query query = session.createSQLQuery(sql);
	    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	    List<Map<String, Object>> aliasToValueMapList = query.list();	
	    
	      JSONArray array = new JSONArray();
	      int totveh=0;
	      int totdev=0;
	      int totsim=0;
	      int totsd=0;
	        for (int i = 0; i < aliasToValueMapList.size(); i++) {
	        	JSONArray ja = new JSONArray();
	        	int j=i+1;
	        	 Map<String, Object> list = aliasToValueMapList.get(i);
	        

		 
			ja.add(j);
			ja.add(list.get("org_name").toString());
		ja.add(list.get("noveh").toString());
		totveh=totveh+Integer.parseInt(list.get("noveh").toString());
			// ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal11'  onclick=javascript:getdepotwisevehicle('"+list.get("org_chart_id").toString()+"') title='Vehicle Details'>"+list.get("noveh").toString() + "</a>");
				
			ja.add(list.get("novehnodev").toString());
			totdev=totdev+Integer.parseInt(list.get("novehnodev").toString());
			ja.add(list.get("novehnosim").toString());
			totsim=totsim+Integer.parseInt(list.get("novehnosim").toString());
			if(list.get("novehnotmap").toString().trim().equals("0")){
				ja.add(list.get("novehnotmap").toString());
			}else{
			 ja.add("<a data-toggle='modal' class='btn blue' role='button' href='#mymodal11'  onclick=javascript:getdepotwisevehicle('"+list.get("org_chart_id").toString()+"') title='Vehicle Details'>"+list.get("novehnotmap").toString() + "</a>");
			}
			//ja.add(list.get("novehnotmap").toString());
			totsd=totsd+Integer.parseInt(list.get("novehnotmap").toString());
			array.add(ja);
			
				
				 }
	        JSONArray ja = new JSONArray();
	        ja.add("");
	        ja.add("Total");
	        //ja.add(totveh);
	        ja.add("");
	        ja.add(totdev);
	        ja.add(totsim);
	        ja.add(totsd);
	        array.add(ja);
	        HttpServletResponse response = ServletActionContext.getResponse();

	   	 PrintWriter out;

	   		    	result.put("aaData",array);
	   				out = response.getWriter();
	   				out.print(result);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	@SuppressWarnings("unchecked")
	public String getdepotdata(){

		JSONObject result = new JSONObject();
		Session session = null;
		try {
		HttpServletRequest req = ServletActionContext.getRequest();
		
		String orgname=req.getParameter("orgname");
		
		
		
		
		session = HibernateUtil.getSession("hibernate.cfg.xml");
		String sql="select * from (select license_number,ifnull(d.device_serial_number,'')device_serial_number,ifnull(sc.phone_number,'')phone_number,v.cause_status,nmv.reason "+
				"from vehicle v "+
				"inner join org_chart oc on v.org_chart_id=oc.org_chart_id "+
				"left join vehicle_device vd on v.vehicle_id=vd.vehicle_id and vd.status='ACTIVE' "+
				"left join device d on d.device_id=vd.device_id and d.deleted_status=0 and d.status='ACTIVE' "+
				"left join sim_vtu sv on sv.device_id =d.device_id and sv.status='ACTIVE' " +
				"left join simcard sc on sc.simcard_id=sv.sim_id and sc.status='ACTIVE' "+
				"left join not_mapped_vehicle_reason nmv on nmv.vehicle_no=v.license_number "+
				"where v.deleted_status=0 and cause_status !='s' and v.status='ACTIVE' and oc.org_chart_id="+orgname +"" +
						")a where (device_serial_number='' || phone_number='' || device_serial_number like 'Device%' || "+ 
                   " device_serial_number like 'scrap%' || device_serial_number like 'Nrd%' || device_serial_number like 'test%')";  
				
		Query query = session.createSQLQuery(sql);
	    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	    List<Map<String, Object>> aliasToValueMapList = query.list();	
	    
	      JSONArray array = new JSONArray();
	        for (int i = 0; i < aliasToValueMapList.size(); i++) {
	        	JSONArray ja = new JSONArray();
	        	int j=i+1;
	        	 Map<String, Object> list = aliasToValueMapList.get(i);
	        

		 
			ja.add(j);
			ja.add(list.get("license_number").toString());
		ja.add(list.get("device_serial_number").toString());
		
			ja.add(list.get("phone_number").toString());
			ja.add(list.get("cause_status").toString());
			if(list.get("reason")==null){
				
			ja.add("<td align='right'>"
					+"<a data-toggle='modal' class='btn green' role='button' href='#mymodal1'  onclick=javascript:getdepotwiseexceptionreport('"
					+ list.get("license_number").toString()
					 + "','"
                    + orgname.toString()
		            + "') title='Enter a Reason' id='alert_details"
					+ "'>"
					+ "Write Reason details"+""
					+ "</a>"+
			"</td>"); // System.out.println("inside---");
			}else {
				ja.add(list.get("reason").toString());
				
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
		}
		return null;
		
		
	}
	
	
	public void InsertReasonForNotMapped(){
		Session session=null;
		//============
		int i=0;
		String status=null;
		String regionTypeAjaxString1="";
			try {
			//System.out.println("HIIIIII");
			HttpServletRequest req=ServletActionContext.getRequest();
			//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//Date now = new Date();
			//String strDate = simpleDateFormat.format(now);
			//String actiondate=req.getParameter("dateaction");
			Common common = new Common();
//			String date1=common.getDateFromPicker(sloveingdate);
			//date1=date1+" 00:00:00";
			String vehicleno=req.getParameter("vehicleno").trim();
	     	//String deviceid=req.getParameter("deviceid");
	     	String reason=req.getParameter("reason");
	     	
	     	System.out.println("vehicleno=="+vehicleno);
	     	
	     	String userid=req.getSession().getAttribute("userid").toString();
	     	
	     	session = HibernateUtil.getSession("hibernate.cfg.xml");
			String sql=" insert into not_mapped_vehicle_reason (vehicle_no,device_id,reason) values ('"+vehicleno+"','NA','"+reason+"')";  
					
			session.beginTransaction();
			 Query query1 =session.createSQLQuery(sql);
			int count1 =query1.executeUpdate();
			// session.getTransaction().commit();
			 
			if(count1 > 0){
				session.getTransaction().commit();
				regionTypeAjaxString1="Record successfully Inserted";
			}else {
				session.getTransaction().rollback();
				regionTypeAjaxString1="Record Not successfully Inserted";
				
			}
		        	 System.out.println("status is------"+status);
				HttpServletResponse response = ServletActionContext.getResponse();
				// String regionTypeAjaxString1="Record successfully Inserted";
				PrintWriter out;
				  out = response.getWriter();
				 out.print(regionTypeAjaxString1);
				
			  
			    
			} catch (Exception e) {
			
			
				e.printStackTrace();
				
			} 
		
	}
	
	
	
}
