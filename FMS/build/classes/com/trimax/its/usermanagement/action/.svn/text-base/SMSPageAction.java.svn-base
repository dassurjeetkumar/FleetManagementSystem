package com.trimax.its.usermanagement.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.transport.dao.WeeklyChartDao;
import com.trimax.its.usermanagement.dao.SMSPageDAO;

public class SMSPageAction extends ActionSupport{

	private String SEARCH_TERM;
	private String COL_NAME;
	private String DIR;
	private int START;
	private int AMOUNT;

	
	
	public String execute() throws Exception{
		return "Success";
	}
	
	
	@SkipValidation
	public String SMSForDriver() throws IOException {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			SMSPageDAO dao = new SMSPageDAO();
			
			String group_name=request.getParameter("groupName");
			System.out.println("group_name==="+group_name);
			String[] cols = { "", "","",""};
			String[] dbcol = { "", "","","" };
			JSONObject result = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";

			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			String sdir = request.getParameter("sSortDir_0");

			if (sStart != null) {
				start = Integer.parseInt(sStart);
				if (start < 0) {
					start = 0;
				}
			}
			if (sAmount != null) {
				amount = Integer.parseInt(sAmount);
				if (amount < 10 || amount > 50) {
					amount = 10;
				}
			}
			if (sCol != null) {
				col = Integer.parseInt(sCol);
				if (col < 0 || col > 5)
					col = 0;
			}
			if (sdir != null) {
				if (!sdir.equals("asc"))
					dir = "desc";
			}

			String colName = cols[col];
			int total = -1;
            total=dao.getTotalRecords(dbcol[Integer.parseInt(sCol)], sdir);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;
//			total=10;
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = dao.getList(total, request, dbcol[Integer.parseInt(sCol)], sdir);
			out.print(result);
		} catch (Exception ex) {
		  ex.printStackTrace();
		} 
		
		return null;

	}
	
	@SkipValidation
	public String SMSForConductor() throws IOException {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			SMSPageDAO dao = new SMSPageDAO();
			
			
			String[] cols = { "", "","",""};
			String[] dbcol = { "", "","","" };
			JSONObject result = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";

			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			String sdir = request.getParameter("sSortDir_0");

			if (sStart != null) {
				start = Integer.parseInt(sStart);
				if (start < 0) {
					start = 0;
				}
			}
			if (sAmount != null) {
				amount = Integer.parseInt(sAmount);
				if (amount < 10 || amount > 50) {
					amount = 10;
				}
			}
			if (sCol != null) {
				col = Integer.parseInt(sCol);
				if (col < 0 || col > 5)
					col = 0;
			}
			if (sdir != null) {
				if (!sdir.equals("asc"))
					dir = "desc";
			}

			String colName = cols[col];
			int total = -1;
            total=dao.getTotalRecordsforCoductor(dbcol[Integer.parseInt(sCol)], sdir);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;
//			total=10;
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = dao.getListCoductor(total, request, dbcol[Integer.parseInt(sCol)], sdir);
			out.print(result);
		} catch (Exception ex) {
		  ex.printStackTrace();
		} 
		
		return null;

	}
	
	@SkipValidation
	public String SMSForConductorDriver() throws IOException {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			SMSPageDAO dao = new SMSPageDAO();
			
			
			String[] cols = { "", "","",""};
			String[] dbcol = { "", "","","" };
			JSONObject result = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";

			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			String sdir = request.getParameter("sSortDir_0");

			if (sStart != null) {
				start = Integer.parseInt(sStart);
				if (start < 0) {
					start = 0;
				}
			}
			if (sAmount != null) {
				amount = Integer.parseInt(sAmount);
				if (amount < 10 || amount > 50) {
					amount = 10;
				}
			}
			if (sCol != null) {
				col = Integer.parseInt(sCol);
				if (col < 0 || col > 5)
					col = 0;
			}
			if (sdir != null) {
				if (!sdir.equals("asc"))
					dir = "desc";
			}

			String colName = cols[col];
			int total = -1;
            total=dao.getTotalRecordsDriverConductor(dbcol[Integer.parseInt(sCol)], sdir);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;
//			total=10;
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = dao.getListDriverConductor(total, request, dbcol[Integer.parseInt(sCol)], sdir);
			out.print(result);
		} catch (Exception ex) {
		  ex.printStackTrace();
		} 
		
		return null;

	}
	
	@SuppressWarnings("unused")
	public String SendSms(){
		
		String requestUrl ;

		
//		String username = "my_username";
//	    String password = "SecrEt12345";
		
		try{
			SMSPageDAO dao = new SMSPageDAO();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String sub=request.getParameter("subject");
			String msg=request.getParameter("description").toString();
			String checkboxvalue[]=request.getParameterValues("logid");
			System.out.println("Size====="+checkboxvalue.length);
			List<String> value=new ArrayList<String>();
			
			
			String[] mobile={"9494463022","9916182314"};
			String[] data = null;

//			String[] mobile={"7760991248","7760991015","7760991088","7760991087","7760991089"};
//			System.out.println("mobile size===="+mobile.length);
			HttpURLConnection httpURLConnection=null;
			String outResponse="";
			String res_res="";
			int count=0;
			String send_res="";
			String mob="";
			int j=1000;
			for(int i=0;i<checkboxvalue.length;i++){
				System.out.println("Phone======="+checkboxvalue[i].trim());
				if(i==j){
				 mob="7760991173";
				 j=j+1000;
				}else{
					mob=checkboxvalue[i].trim();
				}
				value.add(checkboxvalue[i].trim());
             
			System.out.println("Value===="+value.size());
//			System.out.println("Phone======="+value[0]+"  MSG===="+msg);
			
//             String rawdata="{outboundSMSMessageRequest:{address:[tel: 9448094423],"+
//            		 "senderAddress":"tel:CCBMTC",
//            		 "outboundSMSTextMessage":{"message":"CCBMTC ಪರೀಕ್ಷಾ ಸಂದೇಶವನ್ನು - ರಾಮಕೃಷ್ಣ"},
//            		 "clientCorrelator":"",
//            		 "messageType":"4",
//            		 "category":"CCBMTC-Employment",
//            		 "receiptRequest": {"notifyURL":"",
//            			 "callbackData":"$(callbackData)"},
//            			 "senderName":"CCBMTC"}
//						}";
			}
			System.out.println("data======="+value);
//			requestUrl="http://smspush.openhouseplatform.com/smsmessaging/1/outbound/tel%3A%2BCCBMTC/requests";
//			URL url = new URL(requestUrl);
////			HttpURLConnection 
//			httpURLConnection = (HttpURLConnection) url.openConnection(); 
//			httpURLConnection.setRequestMethod("POST"); 
//			httpURLConnection.setDoOutput(true); 
//			httpURLConnection.setRequestProperty("Content-type", "application/json;charset=UTF-8"); 
//			httpURLConnection.addRequestProperty("key", "fba20609-e830-47f8-b553-b41592c638ab");
//			
//			
//	String strReq = "{\"outboundSMSMessageRequest\":{\"address\":[\"tel:"+mob+"\"],\"senderAddress\":\"tel:CCBMTC\",\"outboundSMSTextMessage\":{\"message\":\"Subject : "+sub+"\\n "+msg+"\"},\"clientCorrelator\":\"\",\"messageType\":\"4\",\"notifyURL\":\" http(s)://smspush.openhouseplatform.com/ /notifications/DeliveryInfoNotification\",\"callbackData\":\"$(callbackData)\",\"senderName\":\"CCBMTC\"}}";
//	
//	OutputStreamWriter createUserosw = new OutputStreamWriter(httpURLConnection.getOutputStream()); 
//	createUserosw.write(strReq.toString()); 
//	createUserosw.close(); 
//			
//	int userResponseCode = httpURLConnection.getResponseCode(); 
//	System.out.println("User_Response_Code===="+userResponseCode);
//	InputStream createUserInputStream = httpURLConnection.getInputStream(); 
//	StringWriter createUserwriter = new StringWriter(); 
//	IOUtils.copy(createUserInputStream, createUserwriter, "UTF-8"); 
//			
//	outResponse = createUserwriter.toString(); 
//			
//	System.out.println("Response==="+outResponse);
//			
//	String[] resp=outResponse.split(":");
//			
//	String result[]=resp[5].split("}");
//	 res_res=result[0].substring(1,9);
//	System.out.println("resp===="+res_res);
//	if(res_res.equalsIgnoreCase("Submitte")){
//		send_res="Success";
//		count=dao.insertSmsStatus(mob,send_res);
//	}else{
//		send_res="Error";
//		count=dao.insertSmsStatus(mob,res_res);
//	}
//	System.out.println("Count======"+count);
//			}
//	return outResponse;
	
			
	
	
	
	

			      if(outResponse == null) {
			        System.out.println("No response received");
			      }
			      else if(res_res.equalsIgnoreCase("Submitte")) {
			        System.out.println("Message sent successfully");
			        addActionMessage("Message sent successfully");
			      } 
			      else {
			        System.out.println("Error - " + outResponse);
			      }
			
				  
				  

				  httpURLConnection.disconnect();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "Success";
	}
	
	
	public String sendEmail(){
		try{
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	
}
