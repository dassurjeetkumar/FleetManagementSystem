package com.trimax.its.etm.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter; 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.dao.ErrorLogDAO;
import com.trimax.its.linesector.dao.LineSectorDao;
import com.trimax.its.logger.SaveRequest;
import com.trimax.its.logger.TrimaxLogger;
import com.trimax.its.model.ErrorLog;
import com.trimax.its.report.action.SouthDivisionReport;
import com.trimax.its.ticketing.dao.CollectionReportDAO;
import com.trimax.its.ticketing.dao.TicketInventoryDao;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.dao.EtmDeviceHistoryDao;
import com.trimax.its.vts.dao.ScheduleTripDurationDAO;
import com.trimax.its.vts.dao.VtsDataDAO;

public class EtmDeviceHistoryAction extends ActionSupport {
	
	String path="";
	char ft = 15;
	String str="";

	String c=" ";
	char fl = 18;
	char f2 = 12;
	int pagi = 1;

	int FULL_LEN = 120;
	int HALF_LEN = 60;

	private String SEARCH_TERM;
	private String COL_NAME;
	private String DIR;
	private int START;
	private int AMOUNT;
	
	private ArrayList<Integer> etmidnumber;
	private ArrayList<Integer> etmissueid;
	private String etmpickupdate;
	
	private int etmserviceid;
	private String data;
	private String etmissueids;
	private String issuer;
	private String receiver;
	
	
	

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getEtmissueids() {
		return etmissueids;
	}

	public void setEtmissueids(String etmissueids) {
		this.etmissueids = etmissueids;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getEtmserviceid() {
		return etmserviceid;
	}

	public void setEtmserviceid(int etmserviceid) {
		this.etmserviceid = etmserviceid;
	}

	public ArrayList<Integer> getEtmidnumber() {
		return etmidnumber;
	}

	public void setEtmidnumber(ArrayList<Integer> etmidnumber) {
		this.etmidnumber = etmidnumber;
	}

	public ArrayList<Integer> getEtmissueid() {
		return etmissueid;
	}

	public void setEtmissueid(ArrayList<Integer> etmissueid) {
		this.etmissueid = etmissueid;
	}

	public String getEtmpickupdate() {
		return etmpickupdate;
	}

	public void setEtmpickupdate(String etmpickupdate) {
		this.etmpickupdate = etmpickupdate;
	}

	private Map<Integer,String> etmnumber;
	private Map<Integer,String> etmissue;
	public Map<Integer, String> getEtmnumber() {
		return etmnumber;
	}

	public void setEtmnumber(Map<Integer, String> etmnumber) {
		this.etmnumber = etmnumber;
	}

	public Map<Integer, String> getEtmissue() {
		return etmissue;
	}

	public void setEtmissue(Map<Integer, String> etmissue) {
		this.etmissue = etmissue;
	}

@SkipValidation
	public String getdropdownlist() {
		System.out.println("in execute");
		EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
		etmnumber=etmdao.getEtmdata();
		etmissue=etmdao.getEtmissue();
		return "success";
	}
	
	@SkipValidation
	 public String getetmview(){
				 
		 HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			System.out.println("Enter into Etm Device View");
			try {
				
//				RevenueSectorDao revenuedao = new RevenueSectorDao();
//				LineSectorDao linedao=new LineSectorDao();
				EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
				String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
				System.out.println(viewdeletedrecord);
				/*
				 * int cnt = devicedao.getTotalRecords();
				 * System.out.println("Count------>" + cnt);
				 */
				String[] dbcol = {"id","division_id","depot_id","etm_number",
						"etm_issue","etm_created_date","etm_pickup_by_fme","etm_resolved_by_fme","etm_pickup_by_verifone",
						"etm_resolved_by_verifone","etm_recived_by_fme,resolved_reason,remarks" };
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

				String colName = dbcol[col];
				int total = -1;
				total = etmdao.getTotalRecords(total, request,
						dbcol[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
				AMOUNT = amount;
				SEARCH_TERM = request.getParameter("sSearch");
				COL_NAME = colName;
				DIR = dir;
				START = start;

				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-store");
				PrintWriter out = response.getWriter();

				result = etmdao.getData1(total, request,
						dbcol[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
//				System.out.println("REsult of datatable------>" + result);
				out.print(result);
			} catch (Exception ex) {
				Logger logger = TrimaxLogger.getTrimaxLogger();
	            logger.error(this.getClass() + "$Exception in LoginAction action",
	                    ex);
	            SaveRequest.save(request);
	            ErrorLog log = new ErrorLog();
	            log.setMsg(ex.getMessage());
	            ErrorLogDAO.saveException(log);
				// System.out.println("=====?" + ex);
				// ex.printStackTrace();
			} finally {

			}
		 return null;
	 }
	 public String saveEtmDeviceData(){
		 System.out.println("inside of save method------------");
		 EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
		 etmdao.savedata(getEtmidnumber(),getEtmissueid(),getEtmpickupdate());
		 return "success";
	 }
	
	public String serviceTicketToCompanyEtm(){
		HttpServletRequest request = ServletActionContext.getRequest();
		return "success";
	}
	public String serviceTicketToCompanyEtmpickup(){
		HttpServletRequest request = ServletActionContext.getRequest();
		setData(request.getParameter("value").toString());
		request.getSession().setAttribute("data1",data);
		request.getSession().setAttribute("issuer",issuer);
		request.getSession().setAttribute("receiver",receiver);
		return "success";
	}
	String regionTypeAjaxString = "";
	public String AjaxserviceTicketToCompanyEtm(){
		 Date  ss1=new Date();
	   SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
		String runDateTime = sdf.format(ss1);
		 EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			String data=(String)request.getSession().getAttribute("data1");
			data=data.replace("on,","");
			issuer=(String)request.getSession().getAttribute("issuer");
			receiver=(String)request.getSession().getAttribute("receiver");
			request.getSession().setAttribute("data1", null);
			request.getSession().setAttribute("issuer", null);
			request.getSession().setAttribute("receiver", null);
			int Docket=etmdao.insertDocketNo(issuer,receiver);
			etmdao.updatePickup(data,Docket);
			int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
			String Depot=etmdao.getname(orgchartid);
			Session session1 = null;
			Transaction transaction  = null;
			String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
			String filePath = "device/";

			String fileName = "ServiceTicketToCompany.txt";
			
			String sql="SELECT device_serial_number, ifnull(phone_number,'')phone_number," +
					"(case when ei.issue_name is null THEN (select group_concat(issue_name) from etm_issue es inner join etm_device_issue edi on es.id=etm_issue_id where etm_device_history_id=edh.id) ELSE ei.issue_name end) issue_name," +
					"etm_created_date FROM etm_device_history edh " +
					"inner join device d on d.device_id=edh.etm_number left join sim_vtu sv on sv.device_id=d.device_id and sv.status='active'  " +
					"left join simcard sd on sd.simcard_id=sv.sim_id and sd.status='active' left join etm_issue ei on ei.id=edh.etm_issue " +
					"WHERE edh.id IN ("+data+") and  depot_id='"+orgchartid+"' group by device_serial_number ";
			session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 Query query = session1.createSQLQuery(sql)
						.addScalar("device_serial_number", Hibernate.STRING)
						.addScalar("phone_number", Hibernate.STRING)
						.addScalar("issue_name", Hibernate.STRING)
						.addScalar("etm_created_date", Hibernate.STRING);
				
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
				regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h2>"+""+" </br>"+ ""+" </br>Trimax Infrastructure Service Pvt.Ltd</br>Service Ticket</h2></div>";
			    regionTypeAjaxString +="<div align='center'><b>Depot:-"+Depot+"</b></div><div align='center'><b>Date:-</b>"+runDateTime+"</div><br></div>";
			    //regionTypeAjaxString +="<b>Issuer Name:-</b>"+issuer+"             "+   "<b>Receiver Name :-</b>"+receiver+"            "+"<right><b>Docket:-</b>"+Docket+"</right></p>";
			    regionTypeAjaxString += "<div  display:block;'><div id='printid'><table  class='table table-striped table-bordered table-hover' id='sample_6' border-collapse: collapse;'></thead>";
			    regionTypeAjaxString +="<tr bgcolor="+"#FF0000"+"><td align='center' colspan="+2+"><h2><b>Issure :</b>"+issuer+"</h2></td><td align='center' colspan="+2+"><h2><b>Reciver :</b>"+receiver+" </h2></td><td align='center' colspan="+2+"><h2><b>Docket No :</b>"+Docket+"</td></h2></tr>";
				regionTypeAjaxString +="<td align='center' width='10%'><b>S.No</b></td><td align='center' width='20%'><b>ETM S/N</b></td>" +
						"               <td align='center' width='20%'><b>Buffer ETM S/N</b></td><td align='center' width='20%'><b>SIM NO</b></td>" +
						"               <td align='center' width='20%'><b>Issue Log Date</b></td><td align='center' width='20%'><b>ETM Problem</b></td>" ;
				
				 
		        String nwkr=""
		          +	  "                                     Trimax Infrastructure Service Pvt.Ltd                                                                       \n"
	      		  +   "                                            "+Depot+"                                                                                  \n" 

	      		  +   "                                    Service Ticket                                                               					 \n"
	      		 +"                                    Issuer Name:- "+issuer+" Receiver Name :-"+ receiver+"                                                 \n"
	      		  +   "                                                               Printed Date:-"+runDateTime+"               \n";
	        
		        

				String headingprint =" "+"_ _ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" 
						+add("S.No",5)+"|"+add("ETM S/N",15)+"|"+add("Buffer ETM S/N",14)+ "|"+add("SIM NO",12)+ "|"+add("Issue Log Date",14)+"|"+add("ETM Problem",20)+"|"+"\n"
						+ " "+ "_ _ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n";
				 		
				 		
				
					
				 
				    String path = realpath + filePath + fileName;
			        str+=ft+nwkr+add(headingprint,5);
		       
				 for (int i = 0; i < aliasToValueMapList.size(); i++) {
						regionTypeAjaxString +="<tr>";
						Map<String, Object> list = aliasToValueMapList.get(i);
						int j=i+1;
						regionTypeAjaxString +="<td align='center'>"+ j +"</td>";
						regionTypeAjaxString +="<td align='center'>"+ list.get("device_serial_number").toString() +"</td>";
						regionTypeAjaxString +="<td align='center'>"+ "" +"</td>";
						regionTypeAjaxString +="<td align='center'>"+ list.get("phone_number").toString() +"</td>";
						regionTypeAjaxString +="<td align='center'>"+ list.get("etm_created_date").toString() +"</td>";
						regionTypeAjaxString +="<td align='center'>"+ list.get("issue_name").toString() +"</td>";
						 regionTypeAjaxString +="</tr>";
						 
						 str+=""+add(String.valueOf(j),5)+"|" + add(list.get("device_serial_number").toString(),15) + "|" + add("",14) +"|" + add(list.get("phone_number").toString(), 12) +"|" + add(list.get("etm_created_date").toString(),14)+"|" + add(list.get("issue_name").toString(),20)+ "|"+"\n";

							
					}
				 regionTypeAjaxString+="</table><br><div id='footer' style='display: none;'><div align='left'><b>Mandatory Instructions:-</b></div></br>" +
				 		"<div align='left'><b>1.DepotManager/Supervisor Signature & stamp is mandatory</b></div></br>" +
				 		"<div align='left'><b>2.Seal in all the copies is must</b></div></br><br>" +
				 		"<div align='left'><b> Junior Assistant            </div><div align='right'><b> Fme Sign  </b></div></br></br>" +
				 		"<div align='left'><b>Manager/Supervisor Sign             </div><div align='right'><b>FSE Name & Sign:-         </b></div></br></br>" +
				 		"<div align='left'><b>Seal:-</b></div></br></div>";
				 
				 
				
				 str +="" +"  \n Mandatory Instructions:-"+"\n\n";
				 str +="" +"      1.DepotManager/Supervisor Signature & stamp is mandatory"+"\n";
				 str +="" +"      2.Seal in all the copies is must"+"\n\n\n";
				 str +="" +"  Manager/Supervisor Sign                                                          FSE Name & Sign:-      "+"\n\n\n";
				 str +="" +"  Seal:-         "+"\n";
		        
			    HttpServletResponse response = ServletActionContext.getResponse();
					PrintWriter out;
					
					FileOutputStream FOS = new FileOutputStream(path);
					PrintWriter PW = new PrintWriter(FOS);
					
		            //	System.out.println(str);
				String p=str;
				System.out.println("realpath="+path);
				PW.println(p);
				PW.close();
				out = response.getWriter();
				out.print(regionTypeAjaxString);
				
			} catch (IOException e) {
				
				e.printStackTrace();
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return null;
	}
	
	public String ResolveETM(){
		System.out.println("we are in ResolveETM()......................");
		HttpServletRequest request = ServletActionContext.getRequest();
		String ids=request.getParameter("value");
		String texts=request.getParameter("textname");
		//System.out.println("ids are---------"+ids);
		//System.out.println("ids are---------"+texts);
		//System.out.println("text lenght are---------"+texts);
		//String reason=request.getParameter("remark").trim();
		EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
		
		int count=etmdao.getstatusforResolve(ids);
		//int count=0;
		if(count>0){
		int c=etmdao.updateResolved(ids,texts);
			//int c=0;
		if(c>0){
		addActionMessage("ETM device history Id "+ids+" Resolved Successfully");
		}else{
			addActionError("ETM device history Id "+ids+" not Resolved Successfully");
		}
		}else{
			addActionError("ETM device history Id "+ids+" is not Pickup");
		}
		return "success";
	}
	public String ReceivedByDepotETM(){
		System.out.println("----------");
		try{
		HttpServletRequest request = ServletActionContext.getRequest();
		etmserviceid=Integer.parseInt(request.getParameter("value"));
		EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
		int count=etmdao.getstatus(etmserviceid);
		if(count>0){
		int c=etmdao.updateReceive(etmserviceid);
		if(c>0){
		addActionMessage("ETM device history Id "+etmserviceid+" received Successfully");
		}else{
			addActionError("ETM device history Id "+etmserviceid+" not received Successfully");
		}
		}else{
			addActionError("ETM device history Id "+etmserviceid+" is not rosolved");
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	public String AjaxserviceTablePickupAction(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			String[] dbcol = {"","id","etm_number","simno","etm_issue_date","etmproblem" };
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

			String colName = dbcol[col];
			int total = -1;
			total = etmdao.getTotalServiceRecords(total, request,
					dbcol[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = etmdao.getDataService(total, request,
					dbcol[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
//			System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			Logger logger = TrimaxLogger.getTrimaxLogger();
            logger.error(this.getClass() + "$Exception in LoginAction action",
                    ex);
            SaveRequest.save(request);
            ErrorLog log = new ErrorLog();
            log.setMsg(ex.getMessage());
            ErrorLogDAO.saveException(log);
			// System.out.println("=====?" + ex);
			// ex.printStackTrace();
		} finally {

		}
		return null;
	}
	public String serviceTicketToCompanyEtmDuplicate(){
		return "success";
	}
	public String AjaxserviceTableDuplicateAction(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			String[] dbcol = {"","docket_no","issuer_name","receiver_name","created_date" };
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

			String colName = dbcol[col];
			int total = -1;
			total = etmdao.getTotalServiceduplicate(total, request,
					dbcol[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = etmdao.getDataServiceDuplicate(total, request,
					dbcol[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
//			System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			Logger logger = TrimaxLogger.getTrimaxLogger();
            logger.error(this.getClass() + "$Exception in LoginAction action",
                    ex);
            SaveRequest.save(request);
            ErrorLog log = new ErrorLog();
            log.setMsg(ex.getMessage());
            ErrorLogDAO.saveException(log);
			// System.out.println("=====?" + ex);
			// ex.printStackTrace();
		} finally {

		}
		return null;
	}
	public String serviceTicketToCompanyduplicate(){
		HttpServletRequest request = ServletActionContext.getRequest();
		setData(request.getParameter("value").toString());
		request.getSession().setAttribute("data1",data);
		return "success";
	}
	public String AjaxserviceTicketToCompanyduplicateAction(){
		Date  ss1=new Date();
		   SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
			String runDateTime = sdf.format(ss1);
			 EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
			 String issuer="";String receiver="";
			try{
				HttpServletRequest request = ServletActionContext.getRequest();
				String data=(String)request.getSession().getAttribute("data1");
				request.getSession().setAttribute("data1", null);
				int orgchartid= Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("orgchartid").toString());
				String Depot=etmdao.getname(orgchartid);
				Session session1 = null;
				Session session = null;
				Transaction transaction  = null;
				
				String realpath = ServletActionContext.getRequest()
						.getRealPath("/");
				String filePath = "device/";

				String fileName = "ServiceTicketToCompany.txt";
				String sql1="select issuer_name,receiver_name from docket_info where docket_no='"+data+"'";
		/*		String sql="SELECT device_serial_number, phone_number," +
					"(case when ei.issue_name is null THEN (select group_concat(issue_name) from etm_issue es inner join etm_device_issue edi on es.id=etm_issue_id where etm_device_history_id=edh.id) ELSE ei.issue_name end) issue_name, "+
						//"issue_name," +
						"etm_created_date FROM etm_device_history edh " +
					"inner join device d on d.device_id=edh.etm_number left join sim_vtu sv on sv.device_id=d.device_id and sv.status='active'  " +
					"left join simcard sd on sd.simcard_id=sv.sim_id and sd.status='active' left join etm_issue ei on ei.id=edh.etm_issue " +
					"WHERE docket_no='"+data+"' group by device_serial_number " ;*/
				
				 String sql="SELECT edh.id as id,device_serial_number, ifnull(phone_number,'')phone_number," +
						 "(case when ei.issue_name is null THEN (select group_concat(issue_name) from etm_issue es inner join etm_device_issue edi on es.id=etm_issue_id where etm_device_history_id=edh.id) ELSE ei.issue_name end) issue_name," +
				 		"etm_created_date FROM etm_device_history edh " +
							"inner join device d on d.device_id=edh.etm_number left join sim_vtu sv on sv.device_id=d.device_id and sv.status='active'  " +
							"left join simcard sd on sd.simcard_id=sv.sim_id and sd.status='active' left join etm_issue ei on ei.id=edh.etm_issue "+
							"WHERE docket_no='"+data+"' group by device_serial_number " ;
				 
				session1 = HibernateUtil.getSession("hibernate.cfg.xml");
				session= HibernateUtil.getSession("hibernate.cfg.xml");
				// transaction = session1.beginTransaction();
				/* Query query = session.createSQLQuery(sql).addScalar("device_serial_number").addScalar("phone_number").addScalar("issue_name")
							.addScalar("etm_created_date");*/
				Query query = session.createSQLQuery(sql)
						.addScalar("id", Hibernate.STRING)
						.addScalar("device_serial_number", Hibernate.STRING)
						.addScalar("phone_number", Hibernate.STRING)
						.addScalar("issue_name", Hibernate.STRING)
						.addScalar("etm_created_date", Hibernate.STRING);
				 Query query1 = session1.createSQLQuery(sql1).addScalar("issuer_name").addScalar("receiver_name");
				 query1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList1 = query1.list();
					//System.out.println("out side");
					for (int i = 0; i < aliasToValueMapList1.size(); i++) {
						
						Map<String, Object> list1 = aliasToValueMapList1.get(i);
						issuer=list1.get("issuer_name").toString();
						receiver=list1.get("receiver_name").toString();
					}
					//System.out.println("beforre---------------");
					query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = query.list();
					regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h2>"+""+" </br>"+ ""+" </br>Trimax Infrastructure Service Pvt.Ltd</br>Service Ticket</h2></div>";
				    regionTypeAjaxString +="<div align='left'><b>Docket:-</b>"+data+"</div><div align='center'><b>Depot:-</b>"+Depot+"</div><div align='right'><b>Date:-</b>"+runDateTime+"</div>";
				    regionTypeAjaxString +="<div align='left'><b>Issuer Name:-</b>"+issuer+"</div><div align='right'><b>Receiver Name :-</b>"+receiver+"</div></div>";
				    regionTypeAjaxString += "<div style='height:310px; overflow-y:scroll; display:block;'><div id='printid'><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:50%;border-collapse: collapse;'></thead>";
					regionTypeAjaxString +="<td align='center'><b>S.No</b></td><td align='center'><b>ETM S/N</b></td>" +
							"               <td align='center'><b>Buffer ETM S/N</b></td><td align='center'><b>SIM NO</b></td>" +
							"               <td align='center'><b>Issue Log Date</b></td><td align='center'><b>ETM Problem</b></td>" ;
					 
					
					  String nwkr=""
					          +	  "                                     Trimax Infrastructure Service Pvt.Ltd                                                                       \n"
				      		  +   "                                            "+Depot+"                                                                                  \n" 

				      		  +   "                                    Service Ticket                                                               					 \n"
				      		 +"                                    Issuer Name:- "+issuer+" Receiver Name :-"+ receiver+"                                                 \n"
				      		  +   "                                                               Printed Date:-"+runDateTime+"               \n";
				        
					        

							String headingprint =" "+"_ _ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" 
									+add("S.No",5)+"|"+add("ETM S/N",15)+"|"+add("Buffer ETM S/N",14)+ "|"+add("SIM NO",12)+ "|"+add("Issue Log Date",14)+"|"+add("ETM Problem",20)+"|"+"\n"
									+ " "+ "_ _ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n";
							 		
							 		
							
								
							 
							    String path = realpath + filePath + fileName;
						        str+=ft+nwkr+add(headingprint,5);
						        
					for (int i = 0; i < aliasToValueMapList.size(); i++) {
							regionTypeAjaxString +="<tr>";
							Map<String, Object> list = aliasToValueMapList.get(i);
							int j=i+1;
							regionTypeAjaxString +="<td align='right'>"+ j +"</td>";
							regionTypeAjaxString +="<td align='right'>"+ list.get("device_serial_number").toString() +"</td>";
							regionTypeAjaxString +="<td align='right'>"+ "" +"</td>";
							regionTypeAjaxString +="<td align='right'>"+ list.get("phone_number").toString() +"</td>";
							regionTypeAjaxString +="<td align='right'>"+ list.get("etm_created_date").toString() +"</td>";
							regionTypeAjaxString +="<td align='right'>"+ list.get("issue_name").toString() +"</td>";
							 regionTypeAjaxString +="</tr>";
							 
							 str+=""+add(String.valueOf(j),5)+"|" + add(list.get("device_serial_number").toString(),15) + "|" + add("",14) +"|" + add(list.get("phone_number").toString(), 12) +"|" + add(list.get("etm_created_date").toString(),14)+"|" + add(list.get("issue_name").toString(),20)+ "|"+"\n";

								
						}
					 regionTypeAjaxString+="</table><div id='footer' style='display: none;'><div align='left'><b>Mandatory Instructions:-</b></div></br>" +
					 		"<div align='left'><b>1.DepotManager/Supervisor Signature & stamp is mandatory</b></div></br>" +
					 		"<div align='left'><b>2.Seal in all the copies is must</b></div></br>" +
					 		"<div align='left'><b>Manager/Supervisor Sign             </div><div align='right'><b>FSE Name & Sign:-         </b></div></br></br>" +
					 		"<div align='left'><b>Seal:-</b></div></br></div>";
					 str +="" +"  \n Mandatory Instructions:-"+"\n\n";
					 str +="" +"      1.DepotManager/Supervisor Signature & stamp is mandatory"+"\n";
					 str +="" +"      2.Seal in all the copies is must"+"\n\n\n";
					 str +="" +"  Manager/Supervisor Sign                                                          FSE Name & Sign:-      "+"\n\n\n";
					 str +="" +"  Seal:-         "+"\n";
			        
				    HttpServletResponse response = ServletActionContext.getResponse();
						PrintWriter out;
						
						FileOutputStream FOS = new FileOutputStream(path);
						PrintWriter PW = new PrintWriter(FOS);
						
			            //	System.out.println(str);
					String p=str;
					System.out.println("realpath="+path);
					PW.println(p);
					PW.close();
					out = response.getWriter();
					out.print(regionTypeAjaxString);
					
				} catch (IOException e) {
					
					e.printStackTrace();
			} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			return null;
	}
	public String updateReceiveETM(){
		HttpServletRequest request = ServletActionContext.getRequest();
		data=request.getParameter("value").toString();
		EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
		int c=etmdao.updateReceiveETMData(data);
		if(c>0){
		addActionMessage("ETM device history Id "+data+" received Successfully");
		}else{
			addActionError("ETM device history Id "+data+" not received Successfully");
		}
		return "success";
	}
	public String updateResolveETM(){
		HttpServletRequest request = ServletActionContext.getRequest();
		data=request.getParameter("value").toString();
		EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
		int c=etmdao.updateResolveETMData(data);
		if(c>0){
		addActionMessage("ETM device history Id "+data+" resolved Successfully");
		}else{
			addActionError("ETM device history Id "+data+" not resolved Successfully");
		}
		return "success";
	}
	public String receiveETMFromVerifone(){
		HttpServletRequest request = ServletActionContext.getRequest();
		data=request.getParameter("value").toString();
		String status=request.getParameter("status").toString();
		EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
		int c=0;
		System.out.println("status is----------------"+status);
		if(status.equals("Not Resolved")){
			c=etmdao.updateisuueagain(data,status);
		}
		else{
		 c=etmdao.updateReceiveETMFromVerifone(data,status);
		}
		if(c>0){
		addActionMessage("ETM device history Id "+data+" received Successfully");
		}else{
			addActionError("ETM device history Id "+data+" not received Successfully");
		}
		return "success";
	}
	
	static String add(String str1, int a1) {
		StringBuilder sb = new StringBuilder(str1);
		int m1 = str1.length();
		if (m1 >= a1) {
			str1.substring(0, a1 - 1);
		}
		a1 = a1 - m1;
		for (int i = 0; i <= a1; i++) {
			sb.append(" ");
		}
		String sb1 = sb.toString();
		return sb1;
	}
	
	public String etmpickfromdevice(){
		return "success";
	}
	public String getetmpickfromdepot(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			String[] dbcol = {"","id","device_serial_number","phone_number","etm_created_date","issue_name" };
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

			String colName = dbcol[col];
			int total = -1;
			total = etmdao.gettotetmpickdepot(total, request,
					dbcol[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = etmdao.getpickupdepot(total, request,
					dbcol[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
//			System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			Logger logger = TrimaxLogger.getTrimaxLogger();
            logger.error(this.getClass() + "$Exception in LoginAction action",
                    ex);
            SaveRequest.save(request);
            ErrorLog log = new ErrorLog();
            log.setMsg(ex.getMessage());
            ErrorLogDAO.saveException(log);
			// System.out.println("=====?" + ex);
			// ex.printStackTrace();
		} finally {

		}
		return null;
	}
	
	public String etmreslovetrimax(){
		return "success";
	}
	
	public String getetmreslovetrimax(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			String[] dbcol = {"","id","etm_number","simno","etm_issue_date","etmproblem" };
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

			String colName = dbcol[col];
			int total = -1;
			total = etmdao.gettotresolvetrimax(total, request,
					dbcol[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = etmdao.getreslovetrimax(total, request,
					dbcol[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
//			System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			Logger logger = TrimaxLogger.getTrimaxLogger();
            logger.error(this.getClass() + "$Exception in LoginAction action",
                    ex);
            SaveRequest.save(request);
            ErrorLog log = new ErrorLog();
            log.setMsg(ex.getMessage());
            ErrorLogDAO.saveException(log);
			// System.out.println("=====?" + ex);
			// ex.printStackTrace();
		} finally {

		}
		return null;
	}
	public String etmreceviedfromverifone(){
		return "success";
	}
	
	public String getetmreceviedfromverifone(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			String[] dbcol = {"","id","etm_number","simno","etm_issue_date","etmproblem" };
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

			String colName = dbcol[col];
			int total = -1;
			total = etmdao.gettotreceiveverifone(total, request,
					dbcol[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = etmdao.getreceiveverifone(total, request,
					dbcol[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
//			System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			Logger logger = TrimaxLogger.getTrimaxLogger();
            logger.error(this.getClass() + "$Exception in LoginAction action",
                    ex);
            SaveRequest.save(request);
            ErrorLog log = new ErrorLog();
            log.setMsg(ex.getMessage());
            ErrorLogDAO.saveException(log);
			// System.out.println("=====?" + ex);
			// ex.printStackTrace();
		} finally {

		}
		return null;
	}
	
	public String etmreceviedfromtrimax(){
		return "success";
	}
	
	public String getetmreceviedfromtrimax(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			String[] dbcol = {"","id","device_serial_number","phone_number","etm_created_date","issue_name" };
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

			String colName = dbcol[col];
			int total = -1;
			total = etmdao.gettotreceivetrimax(total, request,
					dbcol[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = etmdao.getreceivetrimax(total, request,
					dbcol[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
//			System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			Logger logger = TrimaxLogger.getTrimaxLogger();
            logger.error(this.getClass() + "$Exception in LoginAction action",
                    ex);
            SaveRequest.save(request);
            ErrorLog log = new ErrorLog();
            log.setMsg(ex.getMessage());
            ErrorLogDAO.saveException(log);
			// System.out.println("=====?" + ex);
			// ex.printStackTrace();
		} finally {

		}
		return null;
	}

	
	public String etmresolved(){
		return "success";
	}
	
	public String getetmresolved(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			EtmDeviceHistoryDao etmdao=new EtmDeviceHistoryDao();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			String[] dbcol = {"","id","org_name","device_serial_number","phone_number","etm_created_date","issue_name","" };
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

			String colName = dbcol[col];
			int total = -1;
			total = etmdao.gettotetmresolve(total, request,
					dbcol[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = etmdao.getetmresolve(total, request,
					dbcol[Integer.parseInt(sCol)], sdir,viewdeletedrecord);
//			System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			Logger logger = TrimaxLogger.getTrimaxLogger();
            logger.error(this.getClass() + "$Exception in LoginAction action",
                    ex);
            SaveRequest.save(request);
            ErrorLog log = new ErrorLog();
            log.setMsg(ex.getMessage());
            ErrorLogDAO.saveException(log);
			// System.out.println("=====?" + ex);
			// ex.printStackTrace();
		} finally {

		}
		return null;
	}
}
