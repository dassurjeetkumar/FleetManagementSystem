package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class ClsOfKlms {
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
		String sql="select org_name,o_gs,o_do,o_no,o_ns,o_ss,o_de,o_tot,v_gs,v_do,v_no,v_ns,v_ss,v_de,v_tot,vv_gs,vv_do,vv_no,vv_ns,vv_ss,vv_de,vv_tot,a_gs,a_do,a_no,a_ns,a_ss,a_de,a_tot,TOTALSER,totdead,(TOTALSER+totdead)total from(" +
				"select org_name,o_gs,o_do,o_no,o_ns,o_ss,o_de,o_tot,v_gs,v_do,v_no,v_ns,v_ss,v_de,v_tot,vv_gs,vv_do,vv_no,vv_ns,vv_ss,vv_de,vv_tot,a_gs,a_do,a_no,a_ns,a_ss,a_de,a_tot,"+
				"(o_gs+o_do+o_no+o_ns+o_ss+v_gs+v_do+v_no+v_ns+v_ss+vv_gs+vv_do+vv_no+vv_ns+vv_ss+a_gs+a_do+a_no+a_ns+a_ss)TOTALSER,"+
				"(o_de+v_de+vv_de+a_de)totdead from (select org_name,"+
				"sum(case when schedule_service_type=1 and schedule_type=1 and is_dread_trip=0  then distance else 0 end)o_gs,"+ 
				"sum(case when schedule_service_type=1 and schedule_type=3 and is_dread_trip=0  then distance else 0 end)o_do,"+
				"sum(case when schedule_service_type=1 and schedule_type=2 and is_dread_trip=0  then distance else 0 end)o_no,"+
				"sum(case when schedule_service_type=1 and schedule_type=4 and is_dread_trip=0  then distance else 0 end)o_ns,"+
				"sum(case when schedule_service_type=1 and schedule_type=11 and is_dread_trip=0  then distance else 0 end)o_ss,"+
				"sum(case when schedule_service_type=1 and is_dread_trip=1  then distance else 0 end)o_de,"+
				"sum(case when schedule_service_type=1  then distance else 0 end)o_tot,"+
				"sum(case when schedule_service_type=3 and schedule_type=1 and is_dread_trip=0  then distance else 0 end)v_gs,"+
				"sum(case when schedule_service_type=3 and schedule_type=3 and is_dread_trip=0  then distance else 0 end)v_do,"+
				"sum(case when schedule_service_type=3 and schedule_type=2 and is_dread_trip=0  then distance else 0 end)v_no,"+
				"sum(case when schedule_service_type=3 and schedule_type=4 and is_dread_trip=0  then distance else 0 end)v_ns,"+
				"sum(case when schedule_service_type=3 and schedule_type=11 and is_dread_trip=0  then distance else 0 end)v_ss,"+
				"sum(case when schedule_service_type=3 and is_dread_trip=1  then distance else 0 end)v_de,"+
				"sum(case when schedule_service_type=3  then distance else 0 end)v_tot,"+
				"sum(case when schedule_service_type=2 and schedule_type=1 and is_dread_trip=0  then distance else 0 end)vv_gs,"+
				"sum(case when schedule_service_type=2 and schedule_type=3 and is_dread_trip=0  then distance else 0 end)vv_do,"+
				"sum(case when schedule_service_type=2 and schedule_type=2 and is_dread_trip=0  then distance else 0 end)vv_no,"+
				"sum(case when schedule_service_type=2 and schedule_type=4 and is_dread_trip=0  then distance else 0 end)vv_ns,"+
				"sum(case when schedule_service_type=2 and schedule_type=11 and is_dread_trip=0  then distance else 0 end)vv_ss,"+
				"sum(case when schedule_service_type=2 and is_dread_trip=1  then distance else 0 end)vv_de,"+
				"sum(case when schedule_service_type=2 then distance else 0 end)vv_tot,"+
				"sum(case when schedule_service_type=5 and schedule_type=1 and is_dread_trip=0  then distance else 0 end)a_gs,"+
				"sum(case when schedule_service_type=5 and schedule_type=3 and is_dread_trip=0  then distance else 0 end)a_do,"+
				"sum(case when schedule_service_type=5 and schedule_type=2 and is_dread_trip=0  then distance else 0 end)a_no,"+
				"sum(case when schedule_service_type=5 and schedule_type=4 and is_dread_trip=0  then distance else 0 end)a_ns,"+
				"sum(case when schedule_service_type=5 and schedule_type=11 and is_dread_trip=0  then distance else 0 end)a_ss,"+
				"sum(case when schedule_service_type=5 and is_dread_trip=1  then distance else 0 end)a_de," +
				"sum(case when schedule_service_type=5  then distance else 0 end)a_tot from("+
				"select org_name,schedule_number_name,trip_number,schedule_type,schedule_service_type,is_dread_trip,distance from schedule_details sd "+
				"inner join form_four ff on sd.form_four_id=ff.form_four_id "+
				"and (schedule_number_name like'%Week Days' "+  
				"OR schedule_number_name like'%All Days') "+
				"inner join schedule s on ff.schedule_number_id=s.schedule_id "+
				"left join org_chart oc on oc.org_chart_id=s.depot_id "+
				"where sd.deleted_status=0 and ff.deleted_status=0 and ff.current_status='active' " +
				"and ff.effective_end_date IS NULL and s.effective_end_date IS NULL " +
				"and org_name not in('Depot-Test','MSRTC') "+subquy+" "+
				"and "+
				"s.status='new' and s.deleted_status=0 and s.current_status='CASE WORKER')a group by a.org_name"+
				")a)b";
		Query query = session.createSQLQuery(sql);
	    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	    List<Map<String, Object>> aliasToValueMapList = query.list();	
	    
	      JSONArray array = new JSONArray();
	        for (int i = 0; i < aliasToValueMapList.size(); i++) {
	        	JSONArray ja = new JSONArray();
	        	int j=i+1;
	        	 Map<String, Object> list = aliasToValueMapList.get(i);
	        

		 
			ja.add(j);
			ja.add(list.get("org_name").toString());
		ja.add(list.get("o_gs").toString());
			ja.add(list.get("o_do").toString());
			ja.add(list.get("o_no").toString());
			ja.add(list.get("o_ns").toString());
			//ja.add(Integer.parseInt(list.get("sch_0").toString())+Integer.parseInt(list.get("sch_v").toString())+Integer.parseInt(list.get("sch_vv").toString())+Integer.parseInt(list.get("sch_a").toString()));
			ja.add(list.get("o_ss").toString());
			ja.add(list.get("o_de").toString());
			ja.add(list.get("o_tot").toString());
			ja.add(list.get("v_gs").toString());
			ja.add(list.get("v_do").toString());
			ja.add(list.get("v_no").toString());
			ja.add(list.get("v_ns").toString());
			ja.add(list.get("v_ss").toString());
			ja.add(list.get("v_de").toString());
			ja.add(list.get("v_tot").toString());
			ja.add(list.get("vv_gs").toString());
			ja.add(list.get("vv_do").toString());
			ja.add(list.get("vv_no").toString());
			ja.add(list.get("vv_ns").toString());
			ja.add(list.get("vv_ss").toString());
			ja.add(list.get("vv_de").toString());
			ja.add(list.get("vv_tot").toString());
			ja.add(list.get("a_gs").toString());
			ja.add(list.get("a_do").toString());
			ja.add(list.get("a_no").toString());
			ja.add(list.get("a_ns").toString());
			ja.add(list.get("a_ss").toString());
			ja.add(list.get("a_de").toString());
			ja.add(list.get("a_tot").toString());
			ja.add(list.get("TOTALSER").toString());
			ja.add(list.get("totdead").toString());
			//double dd=Double.parseDouble(list.get("totdead").toString())+Double.parseDouble(list.get("TOTALSER").toString());
			// BigDecimal d=new BigDecimal(dd);
				ja.add(list.get("total").toString());
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
}
