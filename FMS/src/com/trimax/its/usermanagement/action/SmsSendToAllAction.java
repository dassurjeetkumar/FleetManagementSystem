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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.usermanagement.dao.SMSPageDAO;
import com.trimax.its.usermanagement.model.DesignationType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vts.dao.VtsDataDAO;

public class SmsSendToAllAction extends ActionSupport {
	
	String path="";
	char ft = 15;
	String str="";
	public String startdate;
	public String enddate;
	
	String c=" ";

	char fl = 18;
	char f2 = 12;
	int pagi = 1;

	int FULL_LEN = 120;
	int HALF_LEN = 60;

	int subtotalTickets=0;
	int subtotalValues=0;
	
	private String SEARCH_TERM;
	private String COL_NAME;
	private String DIR;
	private int START;
	private int AMOUNT;
	
	String regionTypeAjaxString = "";
	
	
	
	 private Map<Integer, String> divisionlist;
		private Map<Integer, String> depotlist;
		private String depotlist1;
		private String divisionlist1;
		 private Map<Integer, String> disignationTypelist;
		
	


	public Map<Integer, String> getDisignationTypelist() {
			return disignationTypelist;
		}


		public void setDisignationTypelist(Map<Integer, String> disignationTypelist) {
			this.disignationTypelist = disignationTypelist;
		}


	public String getDivisionlist1() {
			return divisionlist1;
		}


		public void setDivisionlist1(String divisionlist1) {
			this.divisionlist1 = divisionlist1;
		}


	public String getDepotlist1() {
			return depotlist1;
		}


		public void setDepotlist1(String depotlist1) {
			this.depotlist1 = depotlist1;
		}


	public Map<Integer, String> getDivisionlist() {
			return divisionlist;
		}


		public void setDivisionlist(Map<Integer, String> divisionlist) {
			this.divisionlist = divisionlist;
		}


		public Map<Integer, String> getDepotlist() {
			return depotlist;
		}


		public void setDepotlist(Map<Integer, String> depotlist) {
			this.depotlist = depotlist;
		}


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
	
	
	
	
	public String execute() {
		//this.ServiceTaxCollections();
		
if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage")==null || ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage").toString().equalsIgnoreCase("null")){
			
		}else if(ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage").toString().equalsIgnoreCase("0")){
		}else{
			String msg=ServletActionContext.getRequest().getSession().getAttribute("valueforactionmessage").toString();
//			addActionMessage(msg);
			
			addActionMessage(msg);
			
			ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage","null");
		}
		
		divisionlist = getDivisionNameAll();
//		disignationTypelist=getDesignationType();
		return "Success";
	}
	
//	public Map<Integer, String> getDesignationType() {
//		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
//		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
//		Query query = session
//				.createQuery("from DesignationType   where status='ACTIVE' and deleted_status=0 order by designation_type_name");
//		try {
//			List<DesignationType> list = query.list();
//			for (DesignationType org : list) {
//				resultMap.put(0, "All");
//				resultMap.put(org.getDesignation_type_id(), org.getDesignation_type_name());
//			}
//		} catch (Exception ex) {
//		} finally {
//			HibernateUtil.closeSession();
//		}
//		return resultMap;
//	}
	
	public String getDesignationType() {
		// get Depot List..
				HttpServletRequest req = ServletActionContext.getRequest();
//				VtsDataDAO dao = VtsDataDAO.getInstance();
				SMSPageDAO dao = new SMSPageDAO();
				int parentId = Integer.parseInt(req.getParameter("val"));
				int divisionId = Integer.parseInt(req.getParameter("division"));
				String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
		        String orgchartid=(String)req.getSession().getAttribute("orgchartid");
		        
		      
//		        	if(!orgtypeid.equals("1") && !orgchartid.equals("1")){
		        		List<String> l1 = getDesignatioID(parentId,divisionId);
		        		List<String> l2 = getDesignatioName(parentId,divisionId);
		        		String regionTypeAjaxString = "";
		        		//regionTypeAjaxString += "<option value='0'>------select------</option>";
		        		regionTypeAjaxString += "<option value='-1'>---Select---</option>";
	        			regionTypeAjaxString += "<option value='0'>All</option>";
		        		for (int i = 0; i < l1.size(); i++) {
		        			
		        			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
		        					+ ">" + l2.get(i).toString() + "</option>";
		        		}
		        		HttpServletResponse response = ServletActionContext.getResponse();
		        		PrintWriter out;
		        		try {
		        			out = response.getWriter();
		        			out.print(regionTypeAjaxString);
		        		} catch (IOException e) {
		        			// TODO Auto-generated catch block
		        			e.printStackTrace();
		        		}
		        		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public List getDesignatioID(int parentID, int division_id) {
		List list = new ArrayList();
		
		String qry = "";
		if(parentID==1){
			
			if(division_id==1){
			
			qry = " select sg.group_id group_id,group_name from sms_group sg "+
				  " inner join sms_employee se on se.group_id=sg.group_id "+
				  " where sg.status='Y' group by sg.group_id";
			}else{
				qry = " select sg.group_id group_id,group_name from sms_group sg "+
						  " inner join sms_employee se on se.group_id=sg.group_id "+
						  " where se.division_id="+division_id+" and sg.status='Y' group by sg.group_id";
			}
		}else{
			qry = " select sg.group_id group_id,group_name from sms_group sg "+
					  " inner join sms_employee se on se.group_id=sg.group_id "+
					  " where se.depot_id="+parentID+" and sg.status='Y' group by sg.group_id";
		}
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("group_id").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	
	@SuppressWarnings("rawtypes")
	public List getDesignatioName(int parentID, int division_id) {
		List list = new ArrayList();
		
		String qry = "";
		if(parentID==1){
			if(division_id==1){
				
				qry = " select sg.group_id group_id,group_name from sms_group sg "+
					  " inner join sms_employee se on se.group_id=sg.group_id "+
					  " where sg.status='Y' group by sg.group_id";
				}else{
					qry = " select sg.group_id group_id,group_name from sms_group sg "+
							  " inner join sms_employee se on se.group_id=sg.group_id "+
							  " where se.division_id="+division_id+" and sg.status='Y' group by sg.group_id";
				}
		}else{
			qry = " select sg.group_id group_id,group_name from sms_group sg "+
					  " inner join sms_employee se on se.group_id=sg.group_id "+
					  " where se.depot_id="+parentID+" and sg.status='Y' group by sg.group_id";
		}
		Query query = HibernateUtil.getSession("").createSQLQuery(qry);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		if (aliasToValueMapList.size() > 0) {

			for (int i = 0; i < aliasToValueMapList.size(); i++) {
				Map<String, Object> rs = aliasToValueMapList.get(i);
				list.add(rs.get("group_name").toString());
			}
		}
		HibernateUtil.closeSession();
		return list;
	}
	
	
	public Map<Integer, String> getDivisionNameAll() {
		Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		Query query = session
				.createQuery("from OrganisationChart orgchart  where org_type_id=2 and deleted_status=0 order by orgchart.org_name");
		try {
			List<OrganisationChart> list = query.list();
			for (OrganisationChart org : list) {
				resultMap.put(1, "All");
				resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
			}
		} catch (Exception ex) {
		} finally {
			HibernateUtil.closeSession();
		}
		return resultMap;
	}
	
	public String getAllDepot() {
		// get Depot List..
		HttpServletRequest req = ServletActionContext.getRequest();
//		VtsDataDAO dao = VtsDataDAO.getInstance();
		SMSPageDAO dao = new SMSPageDAO();
		int parentId = Integer.parseInt(req.getParameter("val"));
		String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
        String orgchartid=(String)req.getSession().getAttribute("orgchartid");
        
        if(orgtypeid.equals("2")){
//        	if(!orgtypeid.equals("1") && !orgchartid.equals("1")){
        		List<String> l1 = dao.getDepotIdDC(parentId,orgchartid);
        		List<String> l2 = dao.getDepotNameDC(parentId,orgchartid);
        		String regionTypeAjaxString = "";
        		//regionTypeAjaxString += "<option value='0'>------select------</option>";
        		for (int i = 0; i < l1.size(); i++) {
        			regionTypeAjaxString += "<option value='1'>All</option>";
        			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
        					+ ">" + l2.get(i).toString() + "</option>";
        		}
        		HttpServletResponse response = ServletActionContext.getResponse();
        		PrintWriter out;
        		try {
        			out = response.getWriter();
        			out.print(regionTypeAjaxString);
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
//        		return null;
//        	}
		
        }
        
      
        else if(orgtypeid.equals("1") && orgchartid.equals("1")){
        	List<String> l1 = dao.getDepotId(parentId);
    		List<String> l2 = dao.getDepotName(parentId);
    		String regionTypeAjaxString = "";
    		regionTypeAjaxString += "<option value='0'>------select------</option>";
			regionTypeAjaxString += "<option value='1'>All</option>";

    		for (int i = 0; i < l1.size(); i++) {
    			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
    					+ ">" + l2.get(i).toString() + "</option>";
    		}
    		HttpServletResponse response = ServletActionContext.getResponse();
    		PrintWriter out;
    		try {
    			out = response.getWriter();
    			out.print(regionTypeAjaxString);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }

        else {
        	List<String> l1 = dao.getDepotId(parentId,orgchartid);
    		List<String> l2 = dao.getDepotName(parentId,orgchartid);
    		String regionTypeAjaxString = "";
    		//regionTypeAjaxString += "<option value='0'>------select------</option>";
    		for (int i = 0; i < l1.size(); i++) {
    			regionTypeAjaxString += "<option value='1'>All</option>";

    			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
    					+ ">" + l2.get(i).toString() + "</option>";
    		}
    		HttpServletResponse response = ServletActionContext.getResponse();
    		PrintWriter out;
    		try {
    			out = response.getWriter();
    			out.print(regionTypeAjaxString);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
        
		return null;

	}

	@SkipValidation
	public String SMSForAllDesignation() throws IOException {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			SMSPageDAO dao = new SMSPageDAO();
			
			String type=request.getParameter("type");
			String subject=request.getParameter("subject");
			String desc=request.getParameter("desc");
			String depot_id=request.getParameter("depot");
			String division_id=request.getParameter("division");

			System.out.println("Type==="+type);
			System.out.println("subject==="+subject);
			System.out.println("desc==="+desc);
			System.out.println("depot_id==="+depot_id);
			
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
            total=dao.getTotalRecordsAll(dbcol[Integer.parseInt(sCol)], sdir,type,depot_id,division_id);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;
//			total=10;
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = dao.getAllList(total, request, dbcol[Integer.parseInt(sCol)], sdir,type,depot_id,division_id);
			out.print(result);
		} catch (Exception ex) {
		  ex.printStackTrace();
		} 
		
		return null;

	}
	
	@SuppressWarnings("unused")
	public String SendSmsForAllType() throws IOException{
		
		String requestUrl ;
		
		try{
			SMSPageDAO dao = new SMSPageDAO();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String sub=request.getParameter("subject");
			String msg=request.getParameter("description").toString();
			String checkboxvalue[]=request.getParameterValues("logid");
			System.out.println("Size====="+checkboxvalue.length);
			List<String> value=new ArrayList<String>();
			
			
			String[] mobile={"9916182314"};
			String[] data = null;

//			String[] mobile={"7760991248","7760991015","7760991088","7760991087","7760991089","9494463022"};
//			System.out.println("mobile size===="+mobile.length);
			HttpURLConnection httpURLConnection=null;
			URLConnection connection=null;
			String outResponse="";
			String res_res="";
			int count=0;
			String send_res="";
			String mob="";
			String mesg="";
			int j=1000;
			for(int i=0;i<checkboxvalue.length;i++){
				System.out.println("Phone======="+checkboxvalue[i]);

//				mob=checkboxvalue[i].trim();
				mob="9916182314";
//System.out.println("sms===="+mob);
				value.add(checkboxvalue[i].trim());
             
//			System.out.println("Value===="+value.size());
				

			requestUrl="http://smspush.openhouseplatform.com/smsmessaging/1/outbound/tel%3A%2BCCBMTC/requests";
			URL url = new URL(requestUrl);
//			httpURLConnection = (HttpURLConnection) url.openConnection(); 
//			httpURLConnection.setRequestMethod("POST"); 
//			httpURLConnection.setDoOutput(true); 
//			httpURLConnection.setRequestProperty("Content-type", "application/json;charset=UTF-8"); 
//			httpURLConnection.addRequestProperty("key", "fba20609-e830-47f8-b553-b41592c638ab");
			
			 connection = (URLConnection)url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-type", "application/json;charset=UTF-8");
			connection.setRequestProperty("key", "fba20609-e830-47f8-b553-b41592c638ab");
			connection.setConnectTimeout(100000);
			connection.setReadTimeout(100000);
			
			System.out.println("Connected==============");
			
	String strReq = "{\"outboundSMSMessageRequest\":{\"address\":[\"tel:"+mob+"\"],\"senderAddress\":\"tel:CCBMTC\",\"outboundSMSTextMessage\":{\"message\":\"Subject : "+sub+"\\n "+msg+"\"},\"clientCorrelator\":\"\",\"messageType\":\"4\",\"notifyURL\":\" http(s)://smspush.openhouseplatform.com/ /notifications/DeliveryInfoNotification\",\"callbackData\":\"$(callbackData)\",\"senderName\":\"CCBMTC\"}}";
	
	OutputStreamWriter createUserosw = new OutputStreamWriter(connection.getOutputStream()); 
	createUserosw.write(strReq.toString()); 
	createUserosw.close(); 
	System.out.println("After Connected==============");
	
	int userResponseCode = ((HttpURLConnection) connection).getResponseCode(); 
	System.out.println("User_Response_Code===="+userResponseCode);
	InputStream createUserInputStream = connection.getInputStream(); 
	StringWriter createUserwriter = new StringWriter(); 
	IOUtils.copy(createUserInputStream, createUserwriter, "UTF-8"); 
			
	outResponse = createUserwriter.toString(); 
			
	System.out.println("Response==="+outResponse);
			
	String[] resp=outResponse.split(":");
			
	String result[]=resp[5].split("}");
	 res_res=result[0].substring(1,9);
	System.out.println("resp===="+res_res);
	if(res_res.equalsIgnoreCase("Submitte")){
		send_res="Success";
		count=dao.insertSmsStatus(mob,send_res);
	}else{
		send_res="Error";
		count=dao.insertSmsStatus(mob,res_res);
	}
	System.out.println("Count======"+count);
			}
//	return outResponse;
	
			      if(outResponse == null) {
			        System.out.println("No response received");
			      }
			      else if(res_res.equalsIgnoreCase("Submitte")) {
			        System.out.println("Message sent successfully");
			        
			        mesg="Message Sent successfully";
			         ServletActionContext.getRequest().getSession().setAttribute("valueforactionmessage", mesg);
			        

			        addActionMessage("Message sent successfully");
			       
			        
			      } 
			      else {
			        
			    	  System.out.println("Error - " + outResponse);
			      }
			
				  
				  

//				  httpURLConnection.disconnect();
//			      connection.disconnect();

			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "Success";
	}
	
	

}
