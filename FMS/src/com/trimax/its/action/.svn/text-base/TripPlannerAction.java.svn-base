package com.trimax.its.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.opensymphony.xwork2.ActionSupport;

import com.trimax.its.org.json.JSONArray;
import com.trimax.its.route.dao.RouteDAO;
import com.trimax.its.route.model.Route;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.util.HibernateUtil;

public class TripPlannerAction extends ActionSupport{
	List<BusStops> busstops;
	private Map<Integer, String> busstoplist;
	public Map<Integer, String> getBusstoplist() {
		return busstoplist;
	}

	public void setBusstoplist(Map<Integer, String> busstoplist) {
		this.busstoplist = busstoplist;
	}

	public List<BusStops> getBusstops() {
		return busstops;
	}

	public void setBusstops(List<BusStops> busstops) {
		this.busstops = busstops;
	}

	public String execute(){
		//System.out.println("in execute");
		HttpServletRequest request=ServletActionContext.getRequest();
//		Date date = new Date();
//		 String datefil= new SimpleDateFormat("dd/MM/yyyy").format(date);
//		 if(request.getParameter("datecri")==null){
//			 request.getSession().setAttribute("route_filter_date",datefil ); 
//		 }else{
//			 request.getSession().setAttribute("route_filter_date", request.getParameter("datecri"));	 
//		 }
		
		RouteDAO routedao = new RouteDAO();
		busstops = routedao.tripPlanner();
		System.out.println("busstops.size()"+busstops.size());
		
		//System.out.println("in execute======="+route.size());
		return "success";
	}
	
	public String tripPlanner(){
		return "success";
	}
	@SkipValidation
	public String tripPlannerDownList1() {
		// get Depot List..
		try {
			HttpServletRequest req = ServletActionContext.getRequest();
			RouteDAO routedao = new RouteDAO();
			String busstopname=(req.getParameter("routename"));
			List<BusStops> listroute = routedao.tripPlannerDownList1(busstopname);
			
			
			System.out.println("regionTypeAjaxString" + listroute.size());
			HttpServletResponse response = ServletActionContext.getResponse();
			PrintWriter out;
			out = response.getWriter();
			out.print(new JSONArray(listroute));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
	
	private List sourcelist;
	private List  destlist;
	
	public void setSourcelist(List sourcelist) {
		this.sourcelist = sourcelist;
	}

	public void setDestlist(List destlist) {
		this.destlist = destlist;
	}

//	public Map<String, String> getSourcelist() {
//		return sourcelist;
//	}
//
//	public void setSourcelist(Map<String, String> sourcelist) {
//		this.sourcelist = sourcelist;
//	}
//
//	public Map<String, String> getDestlist() {
//		return destlist;
//	}
//
//	public void setDestlist(Map<String, String> destlist) {
//		this.destlist = destlist;
//	}

	@SkipValidation
	public String tripPlannerMapDisplayAction() {
		// get Depot List..
		
			HttpServletRequest req = ServletActionContext.getRequest();
			RouteDAO routedao = new RouteDAO();
			String sourcegroupid=(req.getParameter("source"));
			String destgroupid=(req.getParameter("dest"));
			sourcelist=tripPlannerSourceMapDisplay(sourcegroupid);
			destlist=tripPlannerDestMapDisplay(destgroupid);
			
			
//			System.out.println("regionTypeAjaxString" +sourcelist);
//			System.out.println("regionTypeAjaxString" +destlist);
			int result = destlist.toString().length();
			System.out.println("regionTypeAjaxString==="+result);
			String sourceID="",destId="";
			for(int x=0;x<sourcelist.size();x++){
				sourceID+=sourcelist.get(x).toString()+",";
			}
			for(int x=0;x<destlist.size();x++){
				destId+=destlist.get(x).toString()+",";
			}
			
			
			PrintWriter out = null;
		 	 try {
		 		System.out.println("regionTypeAjaxString==="+sourceID+"$"+destId);
		 		 ResourceBundle resopurceBundle = ResourceBundle.getBundle("com.trimax.its.route.action.linearTracking");
		 		 
		 		 //String routeName = req.getParameter("routeName").toString();
		 		 
//		 		 Map map = new HashMap();
//		 		 map.put("routeNo", routeName.split(":")[0]);
//		 		 map.put("direction", routeName.split(":")[1]);
		 		 
		 			URL url = new URL("http://"+resopurceBundle.getString("tracking_loaction")+"/listnerservice/receive/listnerServiceCurrentTripsITS/"+sourceID.substring(0,sourceID.length()-1)+"$"+destId.substring(0,destId.length()-1));   
//		 			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//		 			conn.setRequestMethod("POST");
//		 			conn.setDoOutput(true);
//		 			conn.setRequestProperty("Accept", "application/json");
//		 			OutputStreamWriter outp = new OutputStreamWriter(conn.getOutputStream());
//		 			//com.trimax.its.org.json.JSONObject jsonObject = new com.trimax.its.org.json.JSONObject(routeName);
//		             outp.write(routeName.toString());
//		             outp.close();
//		 			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//		 			connection.setDoOutput(true);
//		 			//connection.setRequestMethod("POST");
//		             connection.setRequestProperty("Content-Type", "application/json");
//		             connection.setConnectTimeout(10000);
//		             connection.setReadTimeout(300000);
//		             OutputStreamWriter outp = new OutputStreamWriter(connection.getOutputStream());
//		             com.trimax.its.org.json.JSONObject jsonObject = new com.trimax.its.org.json.JSONObject(map);
////		             outp.write(routeName.toString());
//		             outp.flush();
//		             outp.close();
		 			
		 			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		 			connection.setRequestMethod("GET");
		 			connection.setRequestProperty("Accept", "application/json");

//		             BufferedReader in = new BufferedReader(new InputStreamReader(
//		                     connection.getInputStream()));
//		 			if (connection.getResponseCode() != 200) {
//		 				throw new RuntimeException("Failed : HTTP error code : "
//		 						+ connection.getResponseCode());
//		 			}
		 	 
		 			BufferedReader br = new BufferedReader(new InputStreamReader(
		 				(connection.getInputStream())));
		 	 
		 			String output="";
		 			while ((output = br.readLine()) != null) {
		 			//	System.out.println(output);
		 				output=output;
		 				HttpServletResponse response=ServletActionContext.getResponse();
		     			out = response.getWriter();
		     			 out.print(output.trim());
		 			}
		 			
		 			connection.disconnect();
		 	 
		 		  } catch (MalformedURLException e) {
		 	 
		 			e.printStackTrace();
		 	 
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		return null;
		

	}
	
	public List tripPlannerSourceMapDisplay(String sourcegroupid) {
		List resultMap = new ArrayList();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		try {
			String sql="SELECT bus_stop_id FROM bus_stop_group_stop WHERE group_id = '"+sourcegroupid+"' AND `status` = 'Y' ";
			Query query = session.createSQLQuery(sql)
					.addScalar("bus_stop_id", Hibernate.INTEGER);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			
		
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
			//	System.out.println("rs.get(bus_stop_id).toString()=="+rs.get("bus_stop_id"));
				resultMap.add(rs.get("bus_stop_id").toString());
			}				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			  if(session!=null){ session.close(); }
			 
		}
		return resultMap;
	}
	public List tripPlannerDestMapDisplay(String destgroupid) {
		List resultMap = new ArrayList();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		try {
			String sql="SELECT bus_stop_id FROM bus_stop_group_stop WHERE group_id = '"+destgroupid+"' AND `status` = 'Y' ";
			Query query = session.createSQLQuery(sql)
					.addScalar("bus_stop_id", Hibernate.INTEGER);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			
		
			for(int i=0;i<aliasToValueMapList.size();i++){
				Map<String, Object> rs = aliasToValueMapList.get(i);
			//	System.out.println("rs.get(bus_stop_id).toString()=="+rs.get("bus_stop_id"));
				resultMap.add(rs.get("bus_stop_id").toString());
			}				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			  if(session!=null){ session.close(); }
			 
		}
		return resultMap;
	}
	
	
	
	 
}
