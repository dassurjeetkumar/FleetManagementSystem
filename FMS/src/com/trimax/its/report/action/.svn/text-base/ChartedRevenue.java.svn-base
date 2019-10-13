package com.trimax.its.report.action;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.report.dao.EmployeeDutyTypeDAO;
import com.trimax.its.transport.model.TripType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;

public class ChartedRevenue extends ActionSupport{
	private String startdate;
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
	private String enddate;
    private Map<Integer, String> depotlist;
    private Map<Integer, String> charttypelist;
	public Map<Integer, String> getCharttypelist() {
		return charttypelist;
	}
	public void setCharttypelist(Map<Integer, String> charttypelist) {
		this.charttypelist = charttypelist;
	}
	public Map<Integer, String> getDepotlist() {
		return depotlist;
	}
	public void setDepotlist(Map<Integer, String> depotlist) {
		this.depotlist = depotlist;
	}
	public String execute() throws Exception {
		try {
		HttpSession session=ServletActionContext.getRequest().getSession();
		System.out.println("==============="+session.getAttribute("message"));
		if(session.getAttribute("message") !=null) {
	String result=	(String) session.getAttribute("message");
	addActionMessage(result);
	session.setAttribute("message",null);
		}}catch (Exception e) {
			e.printStackTrace();
		}
depotlist = getDepotName();
charttypelist=getChartType();
return "success";
}
	public Map<Integer, String> getDepotName() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from OrganisationChart orgchart  where org_type_id=3 and deleted_status=0 order by orgchart.org_name");
		try {
		
			List<OrganisationChart> list = query.list();
			for (OrganisationChart org : list) {
				resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}
	
	
	public Map<Integer, String> getChartType() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		try {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from TripType triptype  where route_type_id=5 and trip_type_id !=18 and deleted_status=0  and status='active' ");
		
			
			List<TripType> list = query.list();
			System.out.println("list ----"+list.size());
			for (TripType tt : list) {
				resultMap.put(tt.getId(), tt.getTripTypeName());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}
	public void getdata() {
		HttpServletRequest req = ServletActionContext.getRequest();
		String depot=req.getParameter("depot");
		String chart=req.getParameter("chart");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		JSONObject result=new JSONObject();
		Common common = new Common();
		
		String date=common.getDateFromPicker(enddate);
		String str="";
		try{
			str="select s.schedule_number,st.schedule_code,r.route_number,trip_number,s.schedule_id,s.schedule_type,r.route_id,shift.shift_type_name,shift.shift_type_id,"
					+ "c_cname,sd.customer_id,sd.distance,sd.schedule_details_id from schedule s " + 
					"inner join form_four ff on s.schedule_id=ff.schedule_number_id " + 
					"inner join schedule_details sd  on sd.form_four_id=ff.form_four_id and sd.schedule_number=s.schedule_id  " + 
					"inner join trip_type tt on tt.trip_type_id=sd.trip_type " + 
					"inner join route_type rt on rt.route_type_id=tt.route_type_id " + 
					"inner join schedule_type st on schedule_type_id=s.schedule_type " + 
					"inner join route r on r.route_id=sd.route_number_id " + 
					"inner join shift_type shift on shift.shift_type_id=sd.shift_type_id " + 
					"inner join customer  cus on cus.c_cid=sd.customer_id " + 
					"where s.status='new' and s.current_status='CASE WORKER' and s.deleted_status=0 and ff.current_status='active'  " + 
					"and ff.deleted_status=0 and rt.route_type_id=5 and tt.trip_type_id !=18 and sd.deleted_status=0 and tt.trip_type_id="+chart+"  " + 
					"and depot_id="+depot+" and sd.schedule_details_id not in (select schedule_details_id from chartedrevenue where '"+date+"' between from_date and 	to_date) "
							+ "order by s.schedule_type,shift_type_name ";
			
			Query query=session.createSQLQuery(str)	;
				/*	.addScalar("schedule_number")
					.addScalar("schedule_code")
					.addScalar("route_number")
					.addScalar("trip_number");*/
			
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasMapValueList=query.list();
			System.out.println("alsize----------->"+aliasMapValueList.size());
			    
		int j=1;
			JSONArray array=new JSONArray();
			for(int i=0;i<aliasMapValueList.size();i++){
				Map<String, Object> rs=aliasMapValueList.get(i);
				JSONArray ja=new JSONArray();

		//	ja.add("<input type='hidden'  id='chart' name='chart' readonly='readonly' value='"+chart+ "'/>"); 
			/*	ja.add("<input type='hidden'  id='s_type' name='s_type' readonly='readonly' value='"+ rs.get("schedule_type")+ "'/>"); 
				ja.add("<input type='hidden'  id='route' name='s_no' readonly='readonly' value='"+ rs.get("route_id")+ "'/>"); 
				ja.add("<input type='hidden'  id='trip' name='s_no' readonly='readonly' value='"+ rs.get("trip_number")+ "'/>"); 
				*/
		ja.add("<input type='hidden'  id='chart' name='chart' readonly='readonly' value='"+chart+ "'/><input type='hidden'  id='sdid' name='sdid' readonly='readonly' value='"+rs.get("schedule_details_id")+ "'/>"+"<center>"+j+"<center>");
                    j++;
				ja.add("<input type='hidden'  id='s_no' name='s_no' readonly='readonly' value='"+ rs.get("schedule_id")+ "'/>"+"<center>"+rs.get("schedule_number")+"<center>");
				ja.add("<input type='hidden'  id='s_type' name='s_type' readonly='readonly' value='"+ rs.get("schedule_type")+ "'/>"+"<center>"+rs.get("schedule_code")+"<center>");
				ja.add("<input type='hidden'  id='shift_type' name='shift' readonly='readonly' value='"+ rs.get("shift_type_id")+ "'/>"+"<center>"+rs.get("shift_type_name")+"<center>");

				ja.add("<input type='hidden'  id='route' name='route' readonly='readonly' value='"+ rs.get("route_id")+ "'/>"+"<center>"+rs.get("c_cname")+"<center>");
				ja.add("<input type='hidden'  id='customer' name='customer' readonly='readonly' value='"+ rs.get("customer_id")+ "'/>"+"<center>"+rs.get("route_number")+"<center>");
				ja.add("<input type='hidden'  id='trip' name='trip' readonly='readonly' value='"+ rs.get("trip_number")+ "'/>"+"<center>"+rs.get("trip_number")+"<center>");
				ja.add("<center>"+ rs.get("distance")+"<center>");
				ja.add("<input type='text' name='kms'  id='"+rs.get("schedule_number")+"_"+rs.get("schedule_number")+"_kms' name='kms'  value='0'/>"); 			   
				ja.add("<input type='text'  name='rate'  id='"+rs.get("schedule_number")+"_"+rs.get("schedule_number")+"_rate' name='rate'  value='0'/>"); 	

			    
			    array.add(ja);
				
				
			}
		
	        HttpServletResponse response = ServletActionContext.getResponse();

	   	 PrintWriter out;

	   		    	result.put("aaData",array);
	   				out = response.getWriter();
	   				out.print(result);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
			}
		}
		
		
		
	
	}
	public String getsavedata() {
		int result=0;
		int i=0;
		HttpSession session=ServletActionContext.getRequest().getSession();
		HttpServletRequest request=ServletActionContext.getRequest();
		try{
					String[] schedules=request.getParameterValues("s_no");
					String[] sch_type=request.getParameterValues("s_type");
//					System.out.println("ARR SIZE---"+arr22.length);
//					System.out.println("length is"+arr1.length);
					String[] shift=request.getParameterValues("shift");
					//System.out.println("arr2------"+arr2);
					String[] route=request.getParameterValues("route");
					String[] customer=request.getParameterValues("customer");
					String[] trip=request.getParameterValues("trip");
					String[] kms=request.getParameterValues("kms");
					//System.out.println("batch size--------"+dutybatch.length);
					String[] sdid=request.getParameterValues("sdid");
					String[] rate=request.getParameterValues("rate");
				/*	System.out.println(arr5);
					System.out.println(arr3);
					System.out.println(arr2);
					System.out.println(arr1);*/
//					System.out.println("arr1.length"+arr1.length);
//					System.out.println("arr2.length"+arr2.length);
//					System.out.println("arr3.length"+arr3.length);
//					System.out.println("arr5.length"+arr5.length);
//					
				
					Common common = new Common();
					
					String date1=common.getDateFromPicker(startdate);
				
					String date2=common.getDateFromPicker(enddate);
					String depot=request.getParameter("depotname");
					String chart=request.getParameter("chartname");
					System.out.println("startdate==="+startdate);
					System.out.println("enddate==="+enddate);
					// String empid="";
					 int loop=0;
					 int loop1=0;
			for(String schedule:schedules){
		String sch=schedule;
		String sc_type=sch_type[loop];
		String   shiftt=shift[loop];
		String  routee=route[loop];
		String customerr=customer[loop];
		String tripp=trip[loop];
		String kmss=kms[loop].trim();
		String ratee=rate[loop].trim();
		String schdid=sdid[loop];
			 
					   /*
					    Session session1 = HibernateUtil.getSession("hibernate.cfg.xml");
						session1.beginTransaction();*/
						
							try{
					if(!kmss.equals("0") || !ratee.equals("0")) {
					result=savedata(date1,date2,depot,sch,schdid,sc_type,shiftt,chart,routee,customerr,tripp,kmss,ratee);
					}
						
							}catch (Exception e) {
								e.printStackTrace();
								
							}/*finally{
						
							}*/
							loop++;
						}
			
			if(result !=0 ){
				session.setAttribute("message", "Charted Service  Created Successfully");
				addActionMessage("Charted Service  Created Successfully");
				}else{
					session.setAttribute("message", "Charted Service  Creation Failed");
					addActionError("Charted Service  Creation Failed");
				}
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.setAttribute("message", "Error Happend");
				addActionError("Error Happend");

			}
		return "redir";
		
	
	}
	public int savedata(String date1,String date2,String depot,String sch, String sdid, String sc_type, String shiftt, String chart,String routee, String customer,String tripp, String kmss, String ratee) {
		
		int userid=Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("userid").toString());


		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
int i=0;
	
		try{
String sql="INSERT INTO `chartedrevenue` ( `from_date`, `to_date`, `depot`, `schedule_no`,schedule_details_id,  `schedule_type`,shift_type, `charted_type`, "
		+ "`route_no`, customer_id,`trip_no`, `kms_per_day`, `rate/kms`,created_by) "
		+ "values('"+date1+"','"+date2+"','"+depot+"','"+sch+"','"+sdid+"','"+sc_type+"','"+shiftt+"','"+chart+"','"+routee+"','"+customer+"','"+tripp+"','"+kmss+"','"+ratee+"','"+userid+"')";
			Query query=session.createSQLQuery(sql);

	i=query.executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	
		
	
	
		
		
		return i;
		
	}
	
}
