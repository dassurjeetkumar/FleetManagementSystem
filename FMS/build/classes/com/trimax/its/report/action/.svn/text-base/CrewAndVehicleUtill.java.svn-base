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

import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class CrewAndVehicleUtill {

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
/*		String sql="select org_name,(case when schedule_type=1 THEN strtime else 0 end)gstrhour,"+
				"CONCAT(SEC_TO_TIME( SUM( TIME_TO_SEC(case when schedule_type=3 THEN strtime else 0 end))))dstrhour,"+ 
				"CONCAT(SEC_TO_TIME( SUM( TIME_TO_SEC(case when schedule_type=2 THEN strtime else 0 end))))nstrhour,"+
				"CONCAT(SEC_TO_TIME( SUM( TIME_TO_SEC(case when schedule_type=4 THEN strtime else 0 end))))nsstrhour,"+
				"CONCAT(SEC_TO_TIME( SUM( TIME_TO_SEC(case when schedule_type=11 THEN strtime else 0 end))))sstrhour,"+
				"(case when schedule_type=1 THEN crewutill else 0 end)gcrewutill,"+
				"CONCAT(SEC_TO_TIME( SUM( TIME_TO_SEC(case when schedule_type=3 THEN crewutill else 0 end))))dcrewutill,"+ 
				"CONCAT(SEC_TO_TIME( SUM( TIME_TO_SEC(case when schedule_type=2 THEN crewutill else 0 end))))ncrewutill,"+
				"CONCAT(SEC_TO_TIME( SUM( TIME_TO_SEC(case when schedule_type=4 THEN crewutill else 0 end))))nscrewutill,"+
				"CONCAT(SEC_TO_TIME( SUM( TIME_TO_SEC(case when schedule_type=11 THEN crewutill else 0 end))))sscrewutill,"+
				"novehicle,vehspare,(novehicle+vehspare)totveh,conductor,condreq,(conductor+condreq)totcond,driver,driverreq,(driver+driverreq)totdriv,"+
				"nootsch,CONCAT(SEC_TO_TIME( SUM( TIME_TO_SEC( othrs))))othrs "+
				"from( "+
				"select *,TIME_FORMAT(crewu,'%H:%i:%s')crewutill,ROUND((novehicle/100)*4)vehspare,ROUND((driver/100)*38.33)driverreq,ROUND((conductor/100)*38.33)condreq from ( "+
				"select org_name,count(schedule_number) nosch,sum(driver) driver,SEC_TO_TIME((SUM( TIME_TO_SEC(total_steering_time))/count(schedule_number)))crewu, "+
				"(SELECT count(*) "+
				"FROM `vehicle` "+
				"WHERE `org_chart_id` = oc.org_chart_id AND `deleted_status` = '0' AND `status` = 'active' AND `cause_status` = 'N')novehicle,"+
				"sum(conductor)conductor,"+
				"schedule_type,SEC_TO_TIME( SUM( TIME_TO_SEC(total_steering_time))) strtime,SEC_TO_TIME( SUM( TIME_TO_SEC(ot_hours)))othrs,"+
				"sum(case when ot_hours !='0:0' then 1 else 0 end)nootsch from schedule s "+
				"inner join form_four ff on s.schedule_id=ff.schedule_number_id "+
				"inner join org_chart oc on s.depot_id=oc.org_chart_id "+
				"where  s.deleted_status=0 and s.current_status='CASE WORKER' and s.status='new' "+
				"and ff.deleted_status=0 and ff.current_status='ACTIVE' and ff.effective_end_date is null " +
				" "+subquy+" "+
				"group by schedule_type,org_name)a)b group by org_name";*/
		String sql="select org_name,sum(case when schedule_type=1 THEN strtime else 0 end)gstrhour,"+
				"sum(case when schedule_type=3 THEN strtime else 0 end)dstrhour,"+
				"sum(case when schedule_type=2 THEN strtime else 0 end)nstrhour,"+
				"sum(case when schedule_type=4 THEN strtime else 0 end)nsstrhour,"+
				"sum(case when schedule_type=11 THEN strtime else 0 end)sstrhour,"+
				"sum(case when schedule_type=1 THEN crewu else 0 end)gcrewutill,"+
				"sum(case when schedule_type=3 THEN crewu else 0 end)dcrewutill,"+
				"sum(case when schedule_type=2 THEN crewu else 0 end)ncrewutill,"+
				"sum(case when schedule_type=4 THEN crewu else 0 end)nscrewutill,"+
				"sum(case when schedule_type=11 THEN crewu else 0 end)sscrewutill,"+
				"novehicle,vehspare,(novehicle+vehspare)totveh,conductor,condreq,(conductor+condreq)totcond,driver,driverreq,(driver+driverreq)totdriv,"+
				"nootsch,CONCAT(SEC_TO_TIME( SUM( TIME_TO_SEC( othrs))))othrs  "+
				"from(  "+
				"select *,ROUND((novehicle/100)*4)vehspare,ROUND((driver/100)*38.33)driverreq,ROUND((conductor/100)*38.33)condreq from ( "+
				"select org_name,count(schedule_number) nosch,sum(driver) driver,"+
				"ROUND((((SUM(total_steering_time)*60))+(SUM(TIME_TO_SEC(MINUTE(`total_steering_time`)))/60))/count(schedule_number)) crewu,"+ 
				"(SELECT count(*) "+
				"FROM `vehicle` "+
				"WHERE `org_chart_id` = oc.org_chart_id AND `deleted_status` = '0' AND `status` = 'active' AND `cause_status` = 'N')novehicle,"+
				"sum(conductor)conductor,"+
				"schedule_type,"+
				"((SUM(total_steering_time)*60)+SUM(TIME_TO_SEC(MINUTE(`total_steering_time`)))) strtime,"+
				"SEC_TO_TIME( SUM( TIME_TO_SEC(ot_hours)))othrs,"+
				"sum(case when ot_hours !='0:0' then 1 else 0 end)nootsch from schedule s "+
				"inner join form_four ff on s.schedule_id=ff.schedule_number_id "+
				"inner join org_chart oc on s.depot_id=oc.org_chart_id "+
				"where  s.deleted_status=0 and s.current_status='CASE WORKER' and s.status='new' " +
				" "+subquy+" "+
				"and ff.deleted_status=0 and ff.current_status='ACTIVE' and ff.effective_end_date is null "+
				"group by schedule_type,org_name)a )C group by org_name";
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
			int a=Integer.parseInt(list.get("gstrhour").toString().replace(".0",""));
		ja.add((a-(a%60))/60+":"+(a%60)+":"+"00");
		int b=Integer.parseInt(list.get("dstrhour").toString().replace(".0",""));

		//	ja.add(list.get("dstrhour").toString());
		ja.add((b-(b%60))/60+":"+(b%60)+":"+"00");
			//ja.add(list.get("nstrhour").toString());
		int c=Integer.parseInt(list.get("nstrhour").toString().replace(".0",""));
		ja.add((c-(c%60))/60+":"+(c%60)+":"+"00");
		int d=Integer.parseInt(list.get("nsstrhour").toString().replace(".0",""));
		ja.add((d-(d%60))/60+":"+(d%60)+":"+"00");
			//ja.add(list.get("nsstrhour").toString());
			//ja.add(Integer.parseInt(list.get("sch_0").toString())+Integer.parseInt(list.get("sch_v").toString())+Integer.parseInt(list.get("sch_vv").toString())+Integer.parseInt(list.get("sch_a").toString()));
			//ja.add(list.get("sstrhour").toString());
		int e=Integer.parseInt(list.get("sstrhour").toString().replace(".0",""));
		ja.add((e-(e%60))/60+":"+(e%60)+":"+"00");
int strtot=a+b+c+d+e;
ja.add((strtot-(strtot%60))/60+":"+(strtot%60)+":"+"00");

			//ja.add(list.get("gcrewutill").toString());
		int f=Integer.parseInt(list.get("gcrewutill").toString().replace(".0",""));
		ja.add((f-(f%60))/60+":"+(f%60)+":"+"00");

			//ja.add(list.get("dcrewutill").toString());
		int q=Integer.parseInt(list.get("dcrewutill").toString().replace(".0",""));
		ja.add((q-(q%60))/60+":"+(q%60)+":"+"00");

			//ja.add(list.get("ncrewutill").toString());
			int g=Integer.parseInt(list.get("ncrewutill").toString().replace(".0",""));
			ja.add((g-(g%60))/60+":"+(g%60)+":"+"00");

			//ja.add(list.get("nscrewutill").toString());
			int h=Integer.parseInt(list.get("nscrewutill").toString().replace(".0",""));
			ja.add((h-(h%60))/60+":"+(h%60)+":"+"00");

			//ja.add(list.get("sscrewutill").toString());
			int p=Integer.parseInt(list.get("sscrewutill").toString().replace(".0",""));
			ja.add((p-(p%60))/60+":"+(p%60)+":"+"00");
            int totutil=f+q+g+h+p;
               ja.add((totutil-(totutil%60))/60+":"+(totutil%60)+":"+"00");

			ja.add(list.get("novehicle").toString());
			ja.add(list.get("vehspare").toString());
			ja.add(list.get("totveh").toString());
			ja.add(list.get("conductor").toString());
			ja.add(list.get("condreq").toString());
			ja.add(list.get("totcond").toString());
			ja.add(list.get("driver").toString());
			ja.add(list.get("driverreq").toString());
			ja.add(list.get("totdriv").toString());
			ja.add(list.get("nootsch").toString());
			ja.add(list.get("othrs").toString());
			/*ja.add(list.get("a_gs").toString());
			ja.add(list.get("a_do").toString());
			ja.add(list.get("a_no").toString());
			ja.add(list.get("a_ns").toString());
			ja.add(list.get("a_ss").toString());
			ja.add(list.get("a_de").toString());
			ja.add(list.get("a_tot").toString());
			ja.add(list.get("TOTALSER").toString());
			ja.add(list.get("totdead").toString());*/
			//double dd=Double.parseDouble(list.get("totdead").toString())+Double.parseDouble(list.get("TOTALSER").toString());
			// BigDecimal d=new BigDecimal(dd);
				//ja.add(list.get("total").toString());
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
