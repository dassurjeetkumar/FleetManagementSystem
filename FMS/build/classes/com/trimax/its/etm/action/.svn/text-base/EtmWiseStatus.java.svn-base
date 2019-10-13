package com.trimax.its.etm.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class EtmWiseStatus {
	private String startdate;
	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	private String enddate;
	private Map<Integer, String> divisionlist;
	private Map<Integer, String> statuslist;
	private int index;
	public Map<Integer, String> getStatuslist() {
		return statuslist;
	}

	public void setStatuslist(Map<Integer, String> statuslist) {
		this.statuslist = statuslist;
	}

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
	public String execute() {
		VtsDataDAO vvt = VtsDataDAO.getInstance();
		divisionlist = vvt.getDivisionName();
		statuslist=getStatusName();
		return "success";
	}
	public Map<Integer, String> getStatusName() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		try {
		/*Query query = session
				.createQuery("SELECT ei.id,ei.issue_name from etm_issue ei");*/
		Query query = session.createSQLQuery("select id,issue_name from etm_issue");
	    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	    List<Map<String, Object>> aliasToValueMapList = query.list();
			//List <Map<Integer,String>>list = query.list();
			for (Map<String, Object> l : aliasToValueMapList) {
				//System.out.println("---"+l.get("id"));
				resultMap.put((Integer)l.get("id"),(String)l.get("issue_name"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
			HibernateUtil.closeSession();
		}
		return resultMap;
	}
	public String getdata(){
		HttpServletRequest req = ServletActionContext.getRequest();
		try {
			String regionTypeAjaxString = "";
			StringBuffer regionTypeAjaxString1= new StringBuffer(regionTypeAjaxString);
			Common common = new Common();
			String date1=common.getDateFromPicker(startdate);
			String divid=req.getParameter("divid");
			String depot=req.getParameter("depot");
			String statusid=req.getParameter("statusid");
		   
		
		Date  ss1=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	    String runDateTime = sdf.format(ss1);
		
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		
		//String depot=req.getParameter("depot");
		Connection connection=null;
		Statement stmt=null;
		Session session1 = null;
		ResultSet rs=null;
		Transaction transaction  = null;
		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
          String subquery="";
          String depots="";
           if(divid.equals("0")){
           }else if(!divid.equals("0") && depot.equals("0")){
        	   depots="and oc1.parent_id="+divid+" "; 
           }else if(!divid.equals("0") && !depot.equals("0")){
        	   depots=" and oc1.org_chart_id="+depot+" "; 
           }
           //ending division and depot logic
           if(statusid.equals("0")){}
         /*  else if(statusid.equals("In Depot")){
        	   subquery=subquery+"";   
           }*/else if(statusid.equals("In Repair At Depot")){
        	   subquery=subquery+" where status='Repair Depot' "; 
           }else if(statusid.equals("In Repair At Comp")){
        	   subquery=subquery+" where status='Service Center' ";
           }else if(statusid.equals("Lost ETM")){
        	   subquery=subquery+" where status='Lost Etm' ";  
           }
		
		 String sql="select * from(select *,(case when issue_name='Lost ETM' then 'Lost Etm' when etm_pickup_by_fme is null then 'Repair Depot' else 'Service Center' end)status from (" +
		 		"select org_name,device_serial_number,issue_name,etm_pickup_by_fme from etm_device_history edh inner join device d on d.device_id=edh.etm_number "+
	             "inner join etm_device_issue edi on edh.id=edi.etm_device_history_id  "+
		 		"inner join etm_issue ei on ei.id=edi.etm_issue_id "+
                      "inner join org_chart oc1 on oc1.org_chart_id=edh.depot_id  "+
                        "where d.status='active' and etm_received_date is null " +
                        "and etm_created_date='"+date1+"' "+depots+" )a)b "+subquery +"order by org_name" ;
		 
		Query query = session1.createSQLQuery(sql);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        List<Map<String, Object>> aliasToValueMapList = query.list();
	
		regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'> </br>Etm Wise Status Report </br>From Date:- "+startdate+"</br></h4></div>");

		regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
	        

		regionTypeAjaxString1.append("<div class='component'><table class='overflow-y' border='1'>");
		regionTypeAjaxString1.append("<thead><tr><th>Sr No</th><th>Depot</th><th>Etm No</th><th>Status</th>" +
				//"<th>Total Amount</th><th>Total Card Amount</th><th>Terminal Id</th><th>Status</th>" +
					""+"</tr></thead><tbody>");
		

		        HttpServletResponse response = ServletActionContext.getResponse();
		      
		        for (int i = 0; i < aliasToValueMapList.size(); i++) {
		        	int j=i+1;
		        	 Map<String, Object> list = aliasToValueMapList.get(i);
		        	 
			   regionTypeAjaxString1.append("<tr>");
				regionTypeAjaxString1.append("<td align='right'>"+ j+"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("org_name").toString() +"</td>");
				regionTypeAjaxString1.append("<td align='right'>"+list.get("device_serial_number").toString() +"</td>");
			regionTypeAjaxString1.append("<td align='right'>"+list.get("status").toString() +"</td>");
			 regionTypeAjaxString1.append("</tr>");
					
					 }
		        regionTypeAjaxString1.append("</tbody></table></div>");
		        
/*		        String sql1="select *,(tot_etm-(depotrep+servrep))tot_avil from (select org_name,tot_etm,"+
         "sum(case when (etm_created_date is not null and etm_pickup_by_fme is null) then 1 else 0 end ) depotrep,"+
         "sum(case when (etm_pickup_by_fme is not null and etm_received_date is null) then 1 else 0 end )servrep, "+
         "sum(case when etm_issue=12 then 1 else 0 end) lostetm "+
          "from ( "+
         "select org_name,"+
         "(select count(device_serial_number) from device d inner join device_org do on do.device_id=d.device_id "+
          "inner join org_chart oc on do.org_id=oc.org_chart_id "+
         "where d.status='Active' and do.status='Active' and oc.org_chart_id=oc1.org_chart_id and d.device_type_id=2) tot_etm,etm_issue,"+
        "etm_created_date,etm_pickup_by_fme,etm_resolved_by_fme,etm_pickup_by_verifone,etm_resolved_by_verifone,"+
          "etm_received_by_fme,etm_received_date "+
         "from etm_device_history edh "+
         "inner join device d on edh.etm_number=d.device_id  "+
          "inner join org_chart oc1 on edh.depot_id=oc1.org_chart_id "+
            "where d.status='Active' and edh.etm_received_date is null and oc1.org_chart_id !=1 "+depots+" ) a  )b "*/;
	 String sql1="select sum(tot_etm)tot_etm,sum(depotrep)depotrep,sum(servrep)servrep,sum(lostetm)lostetm,sum((tot_etm-(depotrep+servrep)))tot_avil from ( "+
                          "select org_name,tot_etm,sum(case when (etm_created_date is not null and etm_pickup_by_fme is null) then 1 else 0 end ) depotrep,"+
                  "sum(case when (etm_pickup_by_fme is not null and etm_received_date is null) then 1 else 0 end )servrep, "+
             "sum(case when etm_issue=12 then 1 else 0 end) lostetm from ( select org_name,org_chart_id,(select count(device_serial_number)  "+
             "from device d inner join device_org do on do.device_id=d.device_id inner join org_chart oc on do.org_id=oc.org_chart_id "+
            "where d.status='Active' and do.status='Active' and oc.org_chart_id=oc1.org_chart_id and d.device_type_id=2) tot_etm,etm_issue, "+
            "etm_created_date,etm_pickup_by_fme,etm_resolved_by_fme,etm_pickup_by_verifone,etm_resolved_by_verifone,etm_received_by_fme,"+
           "etm_received_date from etm_device_history edh inner join device d on edh.etm_number=d.device_id  "+
           "inner join org_chart oc1 on edh.depot_id=oc1.org_chart_id where d.status='Active' and edh.etm_received_date is null "+
          "and oc1.org_chart_id !=1   "+depots+" ) a group by org_name )b ";
				Query query1 = session1.createSQLQuery(sql1);
		        query1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		        List<Map<String, Object>> aliasToValueMapList1 = query1.list();
			
			/*	regionTypeAjaxString1.append("<div id='header' style='display: none;'><div align='center'> </br>Etm Wise Status Report </br>From Date:- "+startdate+"</br></h4></div>");

				regionTypeAjaxString1.append("<div align='right'><b>Printed Date:-</b>"+runDateTime+"</div></div>");
			      */  

				regionTypeAjaxString1.append("<div class='component'><table class='overflow-y'>");
				regionTypeAjaxString1.append("<thead><tr><th>Particulars</th><th>Count</th>" +
						//"<th>Total Amount</th><th>Total Card Amount</th><th>Terminal Id</th><th>Status</th>" +
							""+"</tr></thead><tbody>");
				

				       // HttpServletResponse response = ServletActionContext.getResponse();
				      
				       // for (int i = 0; i < aliasToValueMapList1.size(); i++) {
				        	
				        	 Map<String, Object> list = aliasToValueMapList1.get(0);
				        	 
					   regionTypeAjaxString1.append("<tr>");
						regionTypeAjaxString1.append("<td align='right'>Total ETM</td>");
						regionTypeAjaxString1.append("<td align='right'>"+list.get("tot_etm").toString() +"</td>");
					 regionTypeAjaxString1.append("</tr>");
					 regionTypeAjaxString1.append("<tr>");
						regionTypeAjaxString1.append("<td align='right'>Etm In Depot</td>");
						regionTypeAjaxString1.append("<td align='right'>"+list.get("tot_avil").toString() +"</td>");
					 regionTypeAjaxString1.append("</tr>");
					 regionTypeAjaxString1.append("<tr>");
						regionTypeAjaxString1.append("<td align='right'>In Repair At Company</td>");
						regionTypeAjaxString1.append("<td align='right'>"+list.get("servrep").toString() +"</td>");
					 regionTypeAjaxString1.append("</tr>");
					 regionTypeAjaxString1.append("<tr>");
						regionTypeAjaxString1.append("<td align='right'>In repair At Depot</td>");
						regionTypeAjaxString1.append("<td align='right'>"+list.get("depotrep").toString() +"</td>");
					 regionTypeAjaxString1.append("</tr>");
					 regionTypeAjaxString1.append("<tr>");
						regionTypeAjaxString1.append("<td align='right'>Lost Etm</td>");
						regionTypeAjaxString1.append("<td align='right'>"+list.get("lostetm").toString() +"</td>");
					 regionTypeAjaxString1.append("</tr>");
					 regionTypeAjaxString1.append("<tr>");
						regionTypeAjaxString1.append("<td align='right'>Swap</td>");
						regionTypeAjaxString1.append("<td align='right'></td>");
					 regionTypeAjaxString1.append("</tr>");
							
							// }
				        regionTypeAjaxString1.append("</tbody></table></div>");
				        
		        
					 PrintWriter out;
                

		out = response.getWriter();
		out.print(regionTypeAjaxString1);
		
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return null;

	
	}

}
