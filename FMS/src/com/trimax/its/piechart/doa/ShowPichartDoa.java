package com.trimax.its.piechart.doa;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.piechart.model.showPiechartModel;
import com.trimax.its.util.HibernateUtil;

public class ShowPichartDoa {
	
	@SuppressWarnings("unchecked")
	public JSONObject getDataBreakdown(int x,HttpServletRequest request, String status) {
		Session session1 = null;
		Transaction transaction  = null;
		JSONObject result = new JSONObject();
		try {
			//HibernateUtilVtu h = new HibernateUtilVtu();
			//session = h.getSession();
			String sql1 = "SELECT vehical_id, device_id, call_time, driver_name, conductor_name, route_no, schedual_no, depot_name, notes "	
					+" FROM breakdown_ccc "
					+" WHERE breakdown_type = '"+status+"'";
			System.out.println("sql....."+sql1);
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			Query query = session1.createSQLQuery(sql1);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			JSONArray array = new JSONArray();
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				JSONArray ja = new JSONArray();
				Map<String, Object> rs = aliasToValueMapList.get(i);
				ja.add(rs.get("vehical_id") != null ? rs.get("vehical_id").toString() : "");
				System.out.println("vehicleid="+rs.get("vehical_id"));
				ja.add(rs.get("device_id") != null ? rs.get("device_id").toString() : "");
				ja.add(rs.get("call_time")!= null ? rs.get("call_time").toString() : "");
				ja.add(rs.get("driver_name")!= null ? rs.get("driver_name").toString() : "");
				ja.add(rs.get("conductor_name") != null ? rs.get("conductor_name").toString() : "");
				ja.add(rs.get("route_no") != null ? rs.get("route_no").toString() : "");
				ja.add(rs.get("schedual_no") != null ? rs.get("schedual_no").toString() : "");
				ja.add(rs.get("depot_name") != null ? rs.get("depot_name").toString() : "");
				
				array.add(ja);
			}

			result.put("aaData", array);
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			session1.close();
		}
		return result;
	}
	public showPiechartModel AccessRightPiechart()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		showPiechartModel piechart=new showPiechartModel();
		Session session1 = null;
		Transaction transaction  = null;
		JSONObject result = new JSONObject();
		
		try {
			String key="";
			int  userid=0;
			String name="";
			boolean access=false;
			int usrsessionid=(Integer)request.getSession().getAttribute("userid");
			
			//HibernateUtilVtu h = new HibernateUtilVtu();
			//session = h.getSession();
			//String sql1 = "select status,sys_key,sys_value from  dashboard_access_condition where dashboard='ccc' and status='y'";
			//String sql1="select chart_id, user_id from dashboard_mapping where status='active' and deleted_status=0 and user_id='"+usrsessionid+"';" ;
			
		String sql1 = " select  DISTINCT  cm.chart_name as chartname from chart_master cm "+
				" inner join chart_mapping chartm on chartm.chart_id=cm.chart_id "+
				" where chartm.deleted_status='0' and chartm.status='active' and chartm.user_id in(select dashboard_id from  DashboardUsermapping "+
				" where user_id=(select role_id from menu_user_master where user_id='"+usrsessionid+"' ) and deleted_status='0' and status='active') ";
		
		//System.out.println("showPiechartModel.........."+sql1);
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			Query query = session1.createSQLQuery(sql1).addScalar("chartname");
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			piechart.setAccident("N");
			piechart.setBreakdown("N");
			piechart.setEmployee("N");
			piechart.setVtudash("N");
			piechart.setEtmdash("N");
			piechart.setTripCountdash("N");
			piechart.setCreawdata("N");
			piechart.setDepothealth("N");
			piechart.setSchedule("N");
			
			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				//key=rs.get("sys_key").toString();
				
				name=rs.get("chartname").toString().trim();
				System.out.println("chartname........."+name);
				if(name.equals("ACCIDENT"))
				{
					piechart.setAccident("Y");
					
				}
				if(name.equals("BREAKDOWN"))
				{
					piechart.setBreakdown("y");
					
				}
				if(name.equals("EMPLOYEE"))
				{
					piechart.setEmployee("y");
					
				}
				if(name.equals("VTU"))
				{
					piechart.setVtudash("y");
					
				}
				if(name.equals("ETM"))
				{
					piechart.setEtmdash("y");
					
				}
				if(name.equals("TRIPCOUNT"))
				{
					piechart.setTripCountdash("Y");
					
				}
				/*for(int j=0;j<aliasToValueMapList.size();j++)
				{
					int a=Integer.parseInt(s[j]);
					if(a==usrsessionid)
					{
						System.out.println("j"+j+"a"+a+"userid"+usrsessionid);
						access=true;
					}
					
				}
				if(access)
				{
				System.out.println("key............."+key);
				if(key.equals("accident_dashboard"))
				{
					piechart.setAccident("Y");
					
					
				}
				
				else if(key.equals("breakdown_dashboard"))
				{
					piechart.setBreakdown("y");
					
					
				}
				else if(key.equals("Employee_dashboard"))
				{
					piechart.setEmployee("y");
					
					
				}
				access=false;
				
			}*/
				
				
				if(name.equals("SCHEDULE"))
				{
					piechart.setSchedule("y");
					
				}
				if(name.equals("CREWDATA"))
				{
					piechart.setCreawdata("y");
					
				}
				if(name.equals("DEPOTHEALTH"))
				{
					piechart.setDepothealth("Y");
					
				}
				
				
			}
		
			
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			session1.close();
		}
		
		
		return piechart;
	}

}
