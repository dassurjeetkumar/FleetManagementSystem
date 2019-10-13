package com.trimax.its.route.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class TripRouteActionPlanner {

	
	public String getRouteListPlanner(){
		return "success";
	}
	
	 public String tripPlannerMapDisplayActionRoute() {

 		PrintWriter out = null;
 	 try {
 		 ResourceBundle resopurceBundle = ResourceBundle.getBundle("com.trimax.its.route.action.linearTracking");
 		 HttpServletRequest req=ServletActionContext.getRequest();
 		 String routeName = req.getParameter("routeName").toString();
 		 Map map = new HashMap();
 		 map.put("routeNo", routeName.split(":")[0]);
 		 map.put("direction", routeName.split(":")[1]);
 		 
 			URL url = new URL("http://"+resopurceBundle.getString("tracking_loaction")+"/listnerservice/receive/routeLinetracking/"+routeName);   
// 			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
// 			conn.setRequestMethod("POST");
// 			conn.setDoOutput(true);
// 			conn.setRequestProperty("Accept", "application/json");
// 			OutputStreamWriter outp = new OutputStreamWriter(conn.getOutputStream());
// 			//com.trimax.its.org.json.JSONObject jsonObject = new com.trimax.its.org.json.JSONObject(routeName);
//             outp.write(routeName.toString());
//             outp.close();
// 			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
// 			connection.setDoOutput(true);
// 			//connection.setRequestMethod("POST");
//             connection.setRequestProperty("Content-Type", "application/json");
//             connection.setConnectTimeout(10000);
//             connection.setReadTimeout(300000);
//             OutputStreamWriter outp = new OutputStreamWriter(connection.getOutputStream());
//             com.trimax.its.org.json.JSONObject jsonObject = new com.trimax.its.org.json.JSONObject(map);
////             outp.write(routeName.toString());
//             outp.flush();
//             outp.close();
 			
 			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
 			connection.setRequestMethod("GET");
 			connection.setRequestProperty("Accept", "application/json");

//             BufferedReader in = new BufferedReader(new InputStreamReader(
//                     connection.getInputStream()));
// 			if (connection.getResponseCode() != 200) {
// 				throw new RuntimeException("Failed : HTTP error code : "
// 						+ connection.getResponseCode());
// 			}
 	 
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
 	 
 		  }catch(Exception e){
 			  e.printStackTrace();
 		  }
 	 return null;
 		}
}
