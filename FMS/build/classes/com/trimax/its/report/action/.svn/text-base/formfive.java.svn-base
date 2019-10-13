package com.trimax.its.report.action;

import java.io.PrintWriter;
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

import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class formfive {
	private Map<Integer, String> divisionlist;

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}
	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}
	private String date;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
		Common com = new Common();
		String divid=req.getParameter("divid");
		String fromDate = req.getParameter("date");
		String date1 = com.getDateFromPicker(fromDate);
		System.out.println("date===="+date1);
		String subquy="and oc.parent_id="+divid;
		if(divid.equals("0")){
			subquy="";
		}
		
		
		
		session = HibernateUtil.getSession("hibernate.cfg.xml");
//		String sql="select org_name,sum(case when service_type_id=2 then schedule_count else 0 end) sch_vv,"+
//				"sum(case when service_type_id=3 then schedule_count else 0 end) sch_v,"+
//				"sum(case when service_type_id=1 then schedule_count else 0 end) sch_0,"+
//				"sum(case when service_type_id=5 then schedule_count else 0 end) sch_a,"+
//				"sum(case when service_type_id=1 and schedule_type_id=1 then schedule_count else 0 end) sch_o_gs,"+
//				"sum(case when service_type_id=1 and schedule_type_id=2 then schedule_count else 0 end) sch_o_no,"+
//				"sum(case when service_type_id=1 and schedule_type_id=3 then schedule_count else 0 end) sch_o_do,"+
//				"sum(case when service_type_id=1 and schedule_type_id=4 then schedule_count else 0 end) sch_o_ns,"+
//				"sum(case when service_type_id=1 and schedule_type_id=11 then schedule_count else 0 end) sch_o_ss,"+
//				"sum(case when service_type_id=3 and schedule_type_id=1 then schedule_count else 0 end) sch_v_gs,"+
//				"sum(case when service_type_id=3 and schedule_type_id=2 then schedule_count else 0 end) sch_v_no,"+
//				"sum(case when service_type_id=3 and schedule_type_id=3 then schedule_count else 0 end) sch_v_do,"+
//				"sum(case when service_type_id=3 and schedule_type_id=4 then schedule_count else 0 end) sch_v_ns,"+
//				"sum(case when service_type_id=3 and schedule_type_id=11 then schedule_count else 0 end) sch_v_ss,"+
//				"sum(case when service_type_id=2 and schedule_type_id=1 then schedule_count else 0 end) sch_vv_gs,"+
//				"sum(case when service_type_id=2 and schedule_type_id=2 then schedule_count else 0 end) sch_vv_no,"+
//				"sum(case when service_type_id=2 and schedule_type_id=3 then schedule_count else 0 end) sch_vv_do,"+
//				"sum(case when service_type_id=2 and schedule_type_id=4 then schedule_count else 0 end) sch_vv_ns,"+
//				"sum(case when service_type_id=2 and schedule_type_id=11 then schedule_count else 0 end) sch_vv_ss,"+
//				"sum(case when service_type_id=5 and schedule_type_id=1 then schedule_count else 0 end) sch_a_gs,"+
//				"sum(case when service_type_id=5 and schedule_type_id=2 then schedule_count else 0 end) sch_a_no,"+
//				"sum(case when service_type_id=5 and schedule_type_id=3 then schedule_count else 0 end) sch_a_do,"+
//				"sum(case when service_type_id=5 and schedule_type_id=4 then schedule_count else 0 end) sch_a_ns,"+
//				"sum(case when service_type_id=5 and schedule_type_id=11 then schedule_count else 0 end) sch_a_ss "+
//				"from ("+
//				"select org_name,count(s.schedule_id) schedule_count,service_type_id,schedule_type_id "+
//				"from schedule s "+
//				"inner join service_type st on service_type_id=schedule_service_type "+
//				"inner join schedule_type scht on s.schedule_type=scht.schedule_type_id "+
//				"inner join org_chart oc on oc.org_chart_id=s.depot_id "+
//				"WHERE "+
//				"s.deleted_status = '0' AND s.status = 'NEW' AND `current_status` = 'CASE WORKER' "+
//				"and st.deleted_status=0 and st.status='active' and oc.org_name !='Central Office' and oc.org_name !='Depot-Test' "+ 
//				"and oc.deleted_status=0 "+subquy+" "+
//				"group by depot_id,service_type_id,schedule_type_id ) a group by a.org_name";
		String sql = "select org_name,sum(case when service_type_id=2 then schedule_count else 0 end) sch_vv,sum(case when service_type_id=3 then schedule_count else 0 end) sch_v," +
				" sum(case when service_type_id=1 then schedule_count else 0 end) sch_0,sum(case when service_type_id=5 then schedule_count else 0 end) sch_a, " +
				" sum(case when service_type_id=1 and schedule_type_id=1 then schedule_count else 0 end) sch_o_gs,sum(case when service_type_id=1 and schedule_type_id=2 then schedule_count else 0 end) sch_o_no," +
				"sum(case when service_type_id=1 and schedule_type_id=3 then schedule_count else 0 end) sch_o_do,sum(case when service_type_id=1 and schedule_type_id=4 then schedule_count else 0 end) sch_o_ns, sum(case when service_type_id=1 and schedule_type_id=11 then schedule_count else 0 end) sch_o_ss,sum(case when service_type_id=3 and schedule_type_id=1 then schedule_count else 0 end) sch_v_gs," +
				" sum(case when service_type_id=3 and schedule_type_id=2 then schedule_count else 0 end) sch_v_no,sum(case when service_type_id=3 and schedule_type_id=3 then schedule_count else 0 end) sch_v_do, " +
				" sum(case when service_type_id=3 and schedule_type_id=4 then schedule_count else 0 end) sch_v_ns,sum(case when service_type_id=3 and schedule_type_id=11 then schedule_count else 0 end) sch_v_ss," +
				" sum(case when service_type_id=2 and schedule_type_id=1 then schedule_count else 0 end) sch_vv_gs,sum(case when service_type_id=2 and schedule_type_id=2 then schedule_count else 0 end) sch_vv_no," +
				" sum(case when service_type_id=2 and schedule_type_id=3 then schedule_count else 0 end) sch_vv_do,sum(case when service_type_id=2 and schedule_type_id=4 then schedule_count else 0 end) sch_vv_ns," +
				" sum(case when service_type_id=2 and schedule_type_id=11 then schedule_count else 0 end) sch_vv_ss,sum(case when service_type_id=5 and schedule_type_id=1 then schedule_count else 0 end) sch_a_gs," +
				" sum(case when service_type_id=5 and schedule_type_id=2 then schedule_count else 0 end) sch_a_no,sum(case when service_type_id=5 and schedule_type_id=3 then schedule_count else 0 end) sch_a_do," +
				" sum(case when service_type_id=5 and schedule_type_id=4 then schedule_count else 0 end) sch_a_ns,sum(case when service_type_id=5 and schedule_type_id=11 then schedule_count else 0 end) sch_a_ss " +
				" from (select org_name,count(s.schedule_id) schedule_count,service_type_id,schedule_type_id from schedule s " +
				" inner join service_type st on service_type_id=schedule_service_type inner join schedule_type scht on s.schedule_type=scht.schedule_type_id " +
				" inner join org_chart oc on oc.org_chart_id=s.depot_id WHERE (('"+date1+"' between s.effective_start_date and s.effective_end_date) || s.updated_date is null) " +
				" and st.deleted_status=0 and st.status='active' and oc.org_name !='Central Office' " +
				" and oc.org_name !='Depot-Test' and oc.deleted_status=0 " +
				" "+subquy+" group by depot_id,service_type_id,schedule_type_id ) a group by a.org_name";
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
		ja.add(list.get("sch_0").toString());
			ja.add(list.get("sch_v").toString());
			ja.add(list.get("sch_vv").toString());
			ja.add(list.get("sch_a").toString());
			ja.add(Integer.parseInt(list.get("sch_0").toString())+Integer.parseInt(list.get("sch_v").toString())+Integer.parseInt(list.get("sch_vv").toString())+Integer.parseInt(list.get("sch_a").toString()));
			ja.add(list.get("sch_o_do").toString());
			ja.add(list.get("sch_o_no").toString());
			ja.add(list.get("sch_o_gs").toString());
			ja.add(list.get("sch_o_ns").toString());
			ja.add(list.get("sch_o_ss").toString());
			ja.add(list.get("sch_v_do").toString());
			ja.add(list.get("sch_v_no").toString());
			ja.add(list.get("sch_v_gs").toString());
			ja.add(list.get("sch_v_ns").toString());
			ja.add(list.get("sch_v_ss").toString());
			ja.add(list.get("sch_vv_do").toString());
			ja.add(list.get("sch_vv_no").toString());
			ja.add(list.get("sch_vv_gs").toString());
			ja.add(list.get("sch_vv_ns").toString());
			ja.add(list.get("sch_vv_ss").toString());
			ja.add(list.get("sch_a_do").toString());
			ja.add(list.get("sch_a_no").toString());
			ja.add(list.get("sch_a_gs").toString());
			ja.add(list.get("sch_a_ns").toString());
			ja.add(list.get("sch_a_ss").toString());
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
