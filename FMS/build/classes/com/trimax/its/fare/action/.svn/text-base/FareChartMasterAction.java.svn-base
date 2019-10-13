package com.trimax.its.fare.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.fare.dao.FareChartMasterDao;
import com.trimax.its.fare.dao.RateMasterDAO;
import com.trimax.its.fare.model.FareChart;
import com.trimax.its.fare.model.Farechart_Master;
import com.trimax.its.fare.model.RateMaster;
import com.trimax.its.route.model.Route;
import com.trimax.its.transport.dao.BusStopsDAO;
import com.trimax.its.transport.dao.ScheduleDAO;
import com.trimax.its.transport.model.BusStops;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

public class FareChartMasterAction extends ActionSupport  implements Preparable{
	Farechart_Master fareChartMaster;
	List<Farechart_Master> list;
	private String routeNumber;
	private String serviceType;
	private Map<Integer,String> routeNumberMap;
	private Map<Integer,String> serviceTypeMap;
	private Map<Integer,String> passengerTypeMap;
	private Map<String,String> nightServiceMap;
	private Map<String,String> peakHoursMap;
	private Map<String,String> flexiFareMap;
	
	private int ceilValue;
	private int oldCeilValue;
	private int applyCeilValue;
	private int masterId;
	/*private String passengerType;*/
	private String rateMasterName;
	List chart;
	List busStops;
	private String SEARCH_TERM;
	private String COL_NAME;
	private String DIR;
	private int START;

	private int AMOUNT;
	private int oldRateMasterId;
	@SkipValidation
	public String execute() throws IOException {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			FareChartMasterDao fareChartdao = new FareChartMasterDao();
			String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
			//int cnt = fareChartdao.getTotalRecords();
			//System.out.println("Count------>" + cnt);
			String[] cols = { "farechart_master_id","farechart_name", "route_id",
					"Service Type", "Passenger Type", "Effective Start Date",
					"Effective End Date", "Operating From Time",
					"Operating To Time", "Night service", "Peak Hours" ,"Flexi Fare"};
			String[] dbcol={"","farechart_master_id","farechart_name","fcm.rate_master_id","r.route_number","st.service_type_name",
					"farechart_master_id","fcm.effect_start_date","fcm.effect_end_date","fcm.nignt_service","fcm.peak_time","fcm.flexi_fare"};
			
			
			JSONObject result = new JSONObject();
			int amount = 10;
			int start = 0;
			int col = 0;
			String dir = "asc";
			String sStart = request.getParameter("iDisplayStart");
			String sAmount = request.getParameter("iDisplayLength");
			String sCol = request.getParameter("iSortCol_0");
			String sdir = request.getParameter("sSortDir_0");
//System.out.println("sCol="+sCol);
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
			total = fareChartdao.getTotalRecords(request,dbcol[col],dir,viewdeletedrecord);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;
//System.out.println("SEARCH_TERM="+SEARCH_TERM);
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = fareChartdao.getData(total, request,dbcol[Integer.parseInt(sCol)],sdir,SEARCH_TERM);
			//System.out.println("REsult of datatable------>" + result);
			out.print(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
		return null;
	}

	@SkipValidation
	public String showfareChart() {
		return "success";
	}

	@SkipValidation
	public String editFareChart() {
		FareChartMasterDao fareChartdao = new FareChartMasterDao();
		HttpServletRequest request = ServletActionContext.getRequest();
			
		routeNumberMap=fareChartdao.getRouteNumber();

		fareChartMaster = fareChartdao.getFareChart(Integer.parseInt(request
				.getParameter("chartid")));
		oldCeilValue=fareChartMaster.getCeiling_fare();
		
		fareChartMaster.setEffect_start_date(Common.getDateFromDateTimeFmt2(fareChartMaster.getEffect_start_date()));
		
		
		if(fareChartMaster.getEffect_end_date()!=null){
			
		fareChartMaster.setEffect_end_date(Common.getDateFromDateTimeFmt2(fareChartMaster.getEffect_end_date()));
		}
		//setRouteNumber(fareChartdao.getRoutenoById(fareChartMaster.getRoute().getRoute_id()).get(0).toString());
       // setServiceType(fareChartdao.getServiceNameById(fareChartMaster.getService_type_id()).get(0).toString());
		//setPassengerType(fareChartdao.getPassengerTypeById(fareChartMaster.getPassenger_type_id()).get(0).toString());
        setRateMasterName(fareChartdao.getRateMasterVersionById(fareChartMaster.getRate_master_id()));
		// System.out.println("Bus stops Size------>"+requestType);
		return "success";
	}
    
	@SkipValidation
	public String createFareChart() {
		FareChartMasterDao fareChartdao = new FareChartMasterDao();		
		routeNumberMap=fareChartdao.getRouteNumber();
		return "success";
	}

	public String copyFare_Chart() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		//original date
		String sDt=fareChartMaster.getEffect_start_date();
		String eDt=fareChartMaster.getEffect_end_date();

		
		Common common = new Common();

		//date format change from dd/MM/yyyy to MM/dd/yyyy
		if(fareChartMaster.getEffect_start_date()!=null || !fareChartMaster.getEffect_start_date().equals("") || fareChartMaster.getEffect_start_date().length()>0){
			
			fareChartMaster.setEffect_start_date(common.getDateToDate(fareChartMaster.getEffect_start_date()));
		}
		
		if(fareChartMaster.getEffect_end_date()!=null || (!fareChartMaster.getEffect_end_date().equals("")) || fareChartMaster.getEffect_end_date().length()>0){
			fareChartMaster.setEffect_end_date(common.getDateToDate(fareChartMaster.getEffect_end_date()));
		}
		
		
		FareChartMasterDao fareChartdao = new FareChartMasterDao();
		//System.out.println("=========>" + fareChartMaster.getRoute().getRoute_id());
		int oldMasterId=0;
        String oldFmId=request.getParameter("farechartlist");
		if(oldFmId!=null){
		 if(!oldFmId.trim().isEmpty()){
		 oldMasterId =Integer.parseInt(oldFmId);
		 }else{
			fareChartMaster.setEffect_start_date(sDt);
     		fareChartMaster.setEffect_end_date(eDt);
     		
     		addFieldError("farechartlist","Please select Fare chart list");
			 return "input2";  
		 }
		 if(oldMasterId<=0){
			 fareChartMaster.setEffect_start_date(sDt);
	     	 fareChartMaster.setEffect_end_date(eDt);
	     	addFieldError("farechartlist","Please select Fare chart list");
			 return "input2"; 
		 }
		}
		//System.out.println("oldFmId="+oldFmId+"  oldMasterId="+oldMasterId);

		fareChartMaster.setRate_master_id(fareChartMaster.getRate_master_id());	
		fareChartMaster.setCreated_by(request.getSession().getAttribute("userid").toString());
//		fareChartMaster.setEffect_start_date(common.getDateTimeFromPicker(fareChartMaster.getEffect_start_date()));
		fareChartMaster.setPassenger_type_id(2);
		
		setRateMasterName(fareChartdao.getRateMasterVersionById(fareChartMaster.getRate_master_id()));
		
		
		//System.out.println("fareChartMaster.getEffect_end_date()="+fareChartMaster.getEffect_end_date().length());
		
		try{
			if(fareChartMaster.getEffect_end_date().length()>0){
				
				//validate str& end date
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				Date date1=sdf.parse(fareChartMaster.getEffect_start_date());
				Date date2=sdf.parse(fareChartMaster.getEffect_end_date());
				
				if(date1.compareTo(date2)>0){
	        		System.out.println("Date1 is after Date2");
	        		addActionError("Effective End Date should be greater than Effective Start Date");
//	        		fareChartMaster.setEffect_start_date(common.getDateToDate2(fareChartMaster.getEffect_start_date()));
//	        		fareChartMaster.setEffect_end_date(common.getDateToDate2(fareChartMaster.getEffect_end_date()));
	        		fareChartMaster.setEffect_start_date(sDt);
	         		fareChartMaster.setEffect_end_date(eDt);
	         		
	        		if(oldMasterId==0){
		        		return "input";
		        		}else{
		        			return "input2";
		        		}
	        		
	        	}else if(date1.compareTo(date2)==0){
	        		System.out.println("Date1 is equal to Date2");
	        		addActionError("Effective End Date should be greater than Effective Start Date");
//	        		fareChartMaster.setEffect_start_date(common.getDateToDate2(fareChartMaster.getEffect_start_date()));
//	        		
//	        		
//	        		fareChartMaster.setEffect_end_date(common.getDateToDate2(fareChartMaster.getEffect_end_date()));
	        		fareChartMaster.setEffect_start_date(sDt);
	         		fareChartMaster.setEffect_end_date(eDt);
	         		
	        		if(oldMasterId==0){
	        		return "input";
	        		}else{
	        			return "input2";
	        		}
	        		
	        	}
				
				
				
				
			 fareChartMaster.setEffect_end_date(common.getDateTimeFromPicker(fareChartMaster.getEffect_end_date()));
			}else{
				fareChartMaster.setEffect_end_date(null);
			}
			}catch(Exception e){e.printStackTrace();}
		fareChartMaster.setEffect_start_date(common.getDateTimeFromPicker(fareChartMaster.getEffect_start_date()));
		
		
		
		
		
		fareChartMaster.setCreated_date(new java.util.Date());
		
		if(oldMasterId==0){
		fareChartMaster.setPercentage(0);
		

		String res = fareChartdao.insertFareChartMaster(fareChartMaster);
		
		if (res.equals("success")) 
		{
       
			int fareMasterId=fareChartMaster.getFarechart_master_id();
			
			List<FareChart> farecharts=fareChartdao.getFareChartDetail(fareMasterId);
			setChart(farecharts);
			//System.out.println("fc list sz="+chart.size());
			//System.out.println("farecharts.get(0).getRouteId()="+farecharts.get(0).getRouteId());
			
			List bStops=fareChartdao.getBusStopsList(farecharts.get(0).getRouteId());//fareChartdao.getBusStop(farecharts.get(0).getRouteId(),HibernateUtil.getSession(""));
			setBusStops(bStops);
			//System.out.println("b stp sz="+bStops.size());
		
			request.getSession().setAttribute("faremap",fareChartdao.getFareChartDetailMap(fareMasterId));
			request.getSession().setAttribute("stop",bStops);
			request.getSession().setAttribute("farechart",farecharts);
			request.getSession().setAttribute("masterid", fareMasterId);

			setCeilValue(fareChartMaster.getCeiling_fare());
			addActionMessage("Fare chart "+fareChartMaster.getFarechart_master_id()+" created successfully.");
			
			//Header for fare chart triangle 13/09/14
			//createFareChartHeader(fareChartMaster);

			return "faretri";
		} else {
			if(res.equals("duplicate")){
//				fareChartMaster.setEffect_start_date(common.getDateToDate4(fareChartMaster.getEffect_start_date()));
//        		
//				if(fareChartMaster.getEffect_end_date()!=null && (!fareChartMaster.getEffect_end_date().trim().isEmpty())){
//				fareChartMaster.setEffect_end_date(common.getDateToDate4(fareChartMaster.getEffect_end_date()));
//				}
				fareChartMaster.setEffect_start_date(sDt);
	     		fareChartMaster.setEffect_end_date(eDt);
	     		
				addActionError("Fare chart already exist.");
				return "input";
			}else{
				if(res.equals("after")){
//					fareChartMaster.setEffect_start_date(common.getDateToDate2(fareChartMaster.getEffect_start_date()));
//	        		
//					if(fareChartMaster.getEffect_end_date()!=null){
//					fareChartMaster.setEffect_end_date(common.getDateToDate2(fareChartMaster.getEffect_end_date()));
//					}
					fareChartMaster.setEffect_start_date(sDt);
		     		fareChartMaster.setEffect_end_date(eDt);
		     		
					addActionError("Fare chart already exist.");
					return "input";
				}
				
				else{
					
					if(res.equals("duplicate2")){
//						fareChartMaster.setEffect_start_date(common.getDateToDate4(fareChartMaster.getEffect_start_date()));
//		        		
//						if(fareChartMaster.getEffect_end_date()!=null && (!fareChartMaster.getEffect_end_date().trim().isEmpty())){
//						fareChartMaster.setEffect_end_date(common.getDateToDate4(fareChartMaster.getEffect_end_date()));
//						}
						fareChartMaster.setEffect_start_date(sDt);
			     		fareChartMaster.setEffect_end_date(eDt);
						
						addActionError("Fare chart effective date should be equal to or in between route activation date");
						
						return "input";
					}else{
					
						fareChartMaster.setEffect_start_date(sDt);
			     		fareChartMaster.setEffect_end_date(eDt);	
						
					addActionError("Database error retry again.");
					return "input";
					}
				}
			
			}
		}
		}else{
			//copy fare chart triangle
			String res =fareChartdao.insertFareChartMasterCopy(fareChartMaster,oldMasterId);
			
			int fareMasterId=fareChartMaster.getFarechart_master_id();
			
			List<FareChart> farecharts=fareChartdao.getFareChartDetail(fareMasterId);
			setChart(farecharts);
			//System.out.println("fc list sz="+chart.size());
			//System.out.println("farecharts.get(0).getRouteId()="+farecharts.get(0).getRouteId());
			
			try{
			List bStops=fareChartdao.getBusStopsList(farecharts.get(0).getRouteId());//fareChartdao.getBusStop(farecharts.get(0).getRouteId(),HibernateUtil.getSession(""));
			setBusStops(bStops);
			//System.out.println("b stp sz="+bStops.size());
		
			request.getSession().setAttribute("faremap",fareChartdao.getFareChartDetailMap(fareMasterId));
			request.getSession().setAttribute("stop",bStops);
			request.getSession().setAttribute("farechart",farecharts);
			request.getSession().setAttribute("masterid", fareMasterId);
			}catch(Exception e){e.printStackTrace();}
			//13/09/14
			//Header for fare chart triangle 13/09/14
			//createFareChartHeader(fareChartMaster);
			
			if (res.equals("success")) 
			{   
				addActionMessage("Fare chart "+fareMasterId+" created successfully.");
				return "faretri";
			}else{
				if(res.equals("duplicate")){
//					fareChartMaster.setEffect_start_date(common.getDateToDate4(fareChartMaster.getEffect_start_date()));
//	        		
//					if(fareChartMaster.getEffect_end_date()!=null){
//					fareChartMaster.setEffect_end_date(common.getDateToDate4(fareChartMaster.getEffect_end_date()));
//					}
					fareChartMaster.setEffect_start_date(sDt);
		     		fareChartMaster.setEffect_end_date(eDt);
					
					addActionError("Fare chart already exist.");
					return "input2";
				}else{
					if(res.equals("after")){
//						fareChartMaster.setEffect_start_date(common.getDateToDate4(fareChartMaster.getEffect_start_date()));
//		        		
//						if(fareChartMaster.getEffect_end_date()!=null){
//						fareChartMaster.setEffect_end_date(common.getDateToDate4(fareChartMaster.getEffect_end_date()));
//						}
						fareChartMaster.setEffect_start_date(sDt);
			     		fareChartMaster.setEffect_end_date(eDt);
						
						addActionError("Fare chart already exist.");//("Fare chart with greater date already exist.");
						return "input2";
					}else{
						if(res.equals("duplicate2")){
//							fareChartMaster.setEffect_start_date(common.getDateToDate4(fareChartMaster.getEffect_start_date()));
//			        		
//							if(fareChartMaster.getEffect_end_date()!=null){
//							fareChartMaster.setEffect_end_date(common.getDateToDate4(fareChartMaster.getEffect_end_date()));
//							}
							fareChartMaster.setEffect_start_date(sDt);
				     		fareChartMaster.setEffect_end_date(eDt);
							
							addActionError("Fare chart effective date should be equal to or in between route activation date");
							
							return "input2";
						}else{
							fareChartMaster.setEffect_start_date(sDt);
				     		fareChartMaster.setEffect_end_date(eDt);
							
						addActionError("Database error retry again.");
						return "input2";
						}
					}
				
				}
			}
		}
	}
	
	public String createFare_Chart() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowFareChartAction.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		//original date
		String sDt=fareChartMaster.getEffect_start_date();
		String eDt=fareChartMaster.getEffect_end_date();

		if(create.equalsIgnoreCase("Y")){
		Common common = new Common();

		//date format change from dd/MM/yyyy to MM/dd/yyyy
		if(fareChartMaster.getEffect_start_date()!=null || !fareChartMaster.getEffect_start_date().equals("") || fareChartMaster.getEffect_start_date().length()>0){
			
			fareChartMaster.setEffect_start_date(common.getDateToDate(fareChartMaster.getEffect_start_date()));
		}
		
		if(fareChartMaster.getEffect_end_date()!=null || (!fareChartMaster.getEffect_end_date().equals("")) || fareChartMaster.getEffect_end_date().length()>0){
			fareChartMaster.setEffect_end_date(common.getDateToDate(fareChartMaster.getEffect_end_date()));
		}
		
		
		FareChartMasterDao fareChartdao = new FareChartMasterDao();

		int oldMasterId=0;
        String oldFmId=request.getParameter("farechartlist");
		if(oldFmId!=null){
		 if(!oldFmId.trim().isEmpty()){
		 oldMasterId =Integer.parseInt(oldFmId);
		 }else{
			fareChartMaster.setEffect_start_date(sDt);
     		fareChartMaster.setEffect_end_date(eDt);
     		
     		addFieldError("farechartlist","Please select Fare chart list");
			 return "input2";  
		 }
		 if(oldMasterId<=0){
			 fareChartMaster.setEffect_start_date(sDt);
	     	 fareChartMaster.setEffect_end_date(eDt);
	     	addFieldError("farechartlist","Please select Fare chart list");
			 return "input2"; 
		 }
		}
		//System.out.println("oldFmId="+oldFmId+"  oldMasterId="+oldMasterId);

		fareChartMaster.setRate_master_id(fareChartMaster.getRate_master_id());	
		fareChartMaster.setCreated_by(request.getSession().getAttribute("userid").toString());
//		fareChartMaster.setEffect_start_date(common.getDateTimeFromPicker(fareChartMaster.getEffect_start_date()));
		fareChartMaster.setPassenger_type_id(2);
		
		setRateMasterName(fareChartdao.getRateMasterVersionById(fareChartMaster.getRate_master_id()));
		
		
		//System.out.println("fareChartMaster.getEffect_end_date()="+fareChartMaster.getEffect_end_date().length());
		
		try{
			if(fareChartMaster.getEffect_end_date().length()>0){
				
				//validate str& end date
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				Date date1=sdf.parse(fareChartMaster.getEffect_start_date());
				Date date2=sdf.parse(fareChartMaster.getEffect_end_date());
				
				if(date1.compareTo(date2)>0){
	        		System.out.println("Date1 is after Date2");
	        		addActionError("Effective End Date should be greater than Effective Start Date");
//	        		fareChartMaster.setEffect_start_date(common.getDateToDate2(fareChartMaster.getEffect_start_date()));
//	        		fareChartMaster.setEffect_end_date(common.getDateToDate2(fareChartMaster.getEffect_end_date()));
	        		fareChartMaster.setEffect_start_date(sDt);
	         		fareChartMaster.setEffect_end_date(eDt);
	         		
	        		if(oldMasterId==0){
		        		return "input";
		        		}else{
		        			return "input2";
		        		}
	        		
	        	}else if(date1.compareTo(date2)==0){
	        		System.out.println("Date1 is equal to Date2");
	        		addActionError("Effective End Date should be greater than Effective Start Date");
//	        		fareChartMaster.setEffect_start_date(common.getDateToDate2(fareChartMaster.getEffect_start_date()));
//	        		
//	        		
//	        		fareChartMaster.setEffect_end_date(common.getDateToDate2(fareChartMaster.getEffect_end_date()));
	        		fareChartMaster.setEffect_start_date(sDt);
	         		fareChartMaster.setEffect_end_date(eDt);
	         		
	        		if(oldMasterId==0){
	        		return "input";
	        		}else{
	        			return "input2";
	        		}
	        		
	        	}
				
				
				
				
			 fareChartMaster.setEffect_end_date(common.getDateTimeFromPicker(fareChartMaster.getEffect_end_date()));
			}else{
				fareChartMaster.setEffect_end_date(null);
			}
			}catch(Exception e){e.printStackTrace();}
		fareChartMaster.setEffect_start_date(common.getDateTimeFromPicker(fareChartMaster.getEffect_start_date()));
		
		
		
		
		
		fareChartMaster.setCreated_date(new java.util.Date());
		
		if(oldMasterId==0){
		fareChartMaster.setPercentage(0);
		

		String res = fareChartdao.insertFareChartMaster(fareChartMaster);
		
		if (res.equals("success")) 
		{
       
			int fareMasterId=fareChartMaster.getFarechart_master_id();
			
			List<FareChart> farecharts=fareChartdao.getFareChartDetail(fareMasterId);
			setChart(farecharts);

			try{
			
			List bStops=fareChartdao.getBusStopsList(farecharts.get(0).getRouteId());//fareChartdao.getBusStop(farecharts.get(0).getRouteId(),HibernateUtil.getSession(""));
			setBusStops(bStops);
		
			request.getSession().setAttribute("faremap",fareChartdao.getFareChartDetailMap(fareMasterId));
			request.getSession().setAttribute("stop",bStops);
			request.getSession().setAttribute("farechart",farecharts);
			request.getSession().setAttribute("masterid", fareMasterId);
			
			request.getSession().setAttribute("passengerType","2");//for passenger typ edrop down box in triangle

			setCeilValue(fareChartMaster.getCeiling_fare());
			}catch(Exception e){
				
			}
			
			addActionMessage("Fare chart "+fareChartMaster.getFarechart_master_id()+" created successfully.");

			//flexi fare functionality //14/11/14
			if(farecharts.size()<=0){
				return "successess";
			}

			return "faretri";
		} else {
			if(res.equals("duplicate")){
//				fareChartMaster.setEffect_start_date(common.getDateToDate4(fareChartMaster.getEffect_start_date()));
//        		
//				if(fareChartMaster.getEffect_end_date()!=null && (!fareChartMaster.getEffect_end_date().trim().isEmpty())){
//				fareChartMaster.setEffect_end_date(common.getDateToDate4(fareChartMaster.getEffect_end_date()));
//				}
				fareChartMaster.setEffect_start_date(sDt);
	     		fareChartMaster.setEffect_end_date(eDt);
	     		
				addActionError("Fare chart already exist.");
				return "input";
			}else{
				if(res.equals("after")){
//					fareChartMaster.setEffect_start_date(common.getDateToDate2(fareChartMaster.getEffect_start_date()));
//	        		
//					if(fareChartMaster.getEffect_end_date()!=null){
//					fareChartMaster.setEffect_end_date(common.getDateToDate2(fareChartMaster.getEffect_end_date()));
//					}
					fareChartMaster.setEffect_start_date(sDt);
		     		fareChartMaster.setEffect_end_date(eDt);
		     		
					addActionError("Fare chart already exist.");
					return "input";
				}
				
				else{
					
					if(res.equals("duplicate2")){
//						fareChartMaster.setEffect_start_date(common.getDateToDate4(fareChartMaster.getEffect_start_date()));
//		        		
//						if(fareChartMaster.getEffect_end_date()!=null && (!fareChartMaster.getEffect_end_date().trim().isEmpty())){
//						fareChartMaster.setEffect_end_date(common.getDateToDate4(fareChartMaster.getEffect_end_date()));
//						}
						fareChartMaster.setEffect_start_date(sDt);
			     		fareChartMaster.setEffect_end_date(eDt);
						
						addActionError("Fare chart effective date should be equal to or in between route activation date");
						
						return "input";
					}else{
					
						fareChartMaster.setEffect_start_date(sDt);
			     		fareChartMaster.setEffect_end_date(eDt);	
						
					addActionError("Database error retry again.");
					return "input";
					}
				}
			
			}
		}
		}else{
			//copy fare chart triangle
			String res =fareChartdao.insertFareChartMasterCopy(fareChartMaster,oldMasterId);
			
			int fareMasterId=fareChartMaster.getFarechart_master_id();
			
			List<FareChart> farecharts=fareChartdao.getFareChartDetail(fareMasterId);
			setChart(farecharts);
			//System.out.println("fc list sz="+chart.size());
			//System.out.println("farecharts.get(0).getRouteId()="+farecharts.get(0).getRouteId());
			
			List bStops=fareChartdao.getBusStopsList(farecharts.get(0).getRouteId());//fareChartdao.getBusStop(farecharts.get(0).getRouteId(),HibernateUtil.getSession(""));
			setBusStops(bStops);
			//System.out.println("b stp sz="+bStops.size());
		
			request.getSession().setAttribute("faremap",fareChartdao.getFareChartDetailMap(fareMasterId));
			request.getSession().setAttribute("stop",bStops);
			request.getSession().setAttribute("farechart",farecharts);
			request.getSession().setAttribute("masterid", fareMasterId);
			
			request.getSession().setAttribute("passengerType","2");//for passenger typ edrop down box in triangle
			
			if (res.equals("success")) 
			{   
				//13/09/14
				//Header for fare chart triangle 13/09/14
				//createFareChartHeader(fareChartMaster);
				
				addActionMessage("Fare chart "+oldMasterId+" created successfully.");
				return "faretri";
			}else{
				if(res.equals("duplicate")){
//					fareChartMaster.setEffect_start_date(common.getDateToDate4(fareChartMaster.getEffect_start_date()));
//	        		
//					if(fareChartMaster.getEffect_end_date()!=null){
//					fareChartMaster.setEffect_end_date(common.getDateToDate4(fareChartMaster.getEffect_end_date()));
//					}
					fareChartMaster.setEffect_start_date(sDt);
		     		fareChartMaster.setEffect_end_date(eDt);
					
					addActionError("Fare chart already exist.");
					return "input2";
				}else{
					if(res.equals("after")){
//						fareChartMaster.setEffect_start_date(common.getDateToDate4(fareChartMaster.getEffect_start_date()));
//		        		
//						if(fareChartMaster.getEffect_end_date()!=null){
//						fareChartMaster.setEffect_end_date(common.getDateToDate4(fareChartMaster.getEffect_end_date()));
//						}
						fareChartMaster.setEffect_start_date(sDt);
			     		fareChartMaster.setEffect_end_date(eDt);
						
						addActionError("Fare chart already exist.");//("Fare chart with greater date already exist.");
						return "input2";
					}else{
						if(res.equals("duplicate2")){
//							fareChartMaster.setEffect_start_date(common.getDateToDate4(fareChartMaster.getEffect_start_date()));
//			        		
//							if(fareChartMaster.getEffect_end_date()!=null){
//							fareChartMaster.setEffect_end_date(common.getDateToDate4(fareChartMaster.getEffect_end_date()));
//							}
							fareChartMaster.setEffect_start_date(sDt);
				     		fareChartMaster.setEffect_end_date(eDt);
							
							addActionError("Fare chart effective date should be equal to or in between route activation date");
							
							return "input2";
						}else{
							fareChartMaster.setEffect_start_date(sDt);
				     		fareChartMaster.setEffect_end_date(eDt);
							
						addActionError("Database error retry again.");
						return "input2";
						}
					}
				
				}
			}
		}}else{
			return "input2";
		}
	}

	public String addeditFareChartMaster() {
		FareChartMasterDao fareChartdao = new FareChartMasterDao();
		routeNumberMap=fareChartdao.getRouteNumber();
		HttpServletRequest request = ServletActionContext.getRequest();
		//original date
		String sDt=fareChartMaster.getEffect_start_date();
		String eDt=fareChartMaster.getEffect_end_date();		
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowFareChartAction.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		Common com=new Common();
		if(edit.equalsIgnoreCase("Y")){
		try{
						
			//date format change from dd/MM/yyyy to MM/dd/yyyy
			if(fareChartMaster.getEffect_start_date()!=null || (!fareChartMaster.getEffect_start_date().equals("")) || fareChartMaster.getEffect_start_date().length()>0){
				
				fareChartMaster.setEffect_start_date(com.getDateToDate(fareChartMaster.getEffect_start_date()));
			}
			
//			if(fareChartMaster.getEffect_end_date()!=null || !fareChartMaster.getEffect_end_date().equals("") || fareChartMaster.getEffect_end_date().length()>0){
//				fareChartMaster.setEffect_end_date(com.getDateToDate(fareChartMaster.getEffect_end_date()));
//			}
			
			if(fareChartMaster.getEffect_end_date()==null || fareChartMaster.getEffect_end_date().equals("") || fareChartMaster.getEffect_end_date().length()<=0){
				//fareChartMaster.setEffect_start_date(sDt);
	     		//fareChartMaster.setEffect_end_date(eDt);
				
				//addFieldError("effect_end_date","Please select Effective End Date");// 11/09/14 remmoved as told by Abhishek sir
				//return "input";													  // 11/09/14
			}else{
				fareChartMaster.setEffect_end_date(com.getDateToDate(fareChartMaster.getEffect_end_date()));
				
			}
				
			
			setRateMasterName(fareChartdao.getRateMasterVersionById(fareChartMaster.getRate_master_id()));
			
			if(fareChartMaster.getEffect_end_date().length()>0){
			
			//validate str& end date
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date date1=sdf.parse(fareChartMaster.getEffect_start_date());
			Date date2=sdf.parse(fareChartMaster.getEffect_end_date());
			
			if(date1.compareTo(date2)>0){
        		System.out.println("Date1 is after Date2");
        		addActionError("Effective End Date should be greater than Effective Start Date");
//        		fareChartMaster.setEffect_start_date(com.getDateToDate2(fareChartMaster.getEffect_start_date()));
//        		fareChartMaster.setEffect_end_date(com.getDateToDate2(fareChartMaster.getEffect_end_date()));
        		fareChartMaster.setEffect_start_date(sDt);
         		fareChartMaster.setEffect_end_date(eDt);
        		
        		return "input";
        	}else if(date1.compareTo(date2)==0){
        		System.out.println("Date1 is equal to Date2");
        		addActionError("Effective End Date should be greater than Effective Start Date");
//        		fareChartMaster.setEffect_start_date(com.getDateToDate2(fareChartMaster.getEffect_start_date()));       		
//        		fareChartMaster.setEffect_end_date(com.getDateToDate2(fareChartMaster.getEffect_end_date()));
        		fareChartMaster.setEffect_start_date(sDt);
         		fareChartMaster.setEffect_end_date(eDt);
        		
        		return "input";
        	}
			
			
			//Common common = new Common();
			
		// fareChartMaster.setEffect_end_date(common.getDateTimeFromPicker(fareChartMaster.getEffect_end_date()));
		}else{
			fareChartMaster.setEffect_end_date(null);
		}
		}catch(Exception e){e.printStackTrace();}
		int oldRateMasterId=Integer.parseInt(request.getParameter("oldRateMasterId"));
		System.out.println("@@@@OLD"+oldRateMasterId);
		//System.out.println("================>" + fareChartMaster.getFarechart_master_id());
		int res = fareChartdao.updateFareChart(fareChartMaster,fareChartMaster.getFarechart_master_id(),applyCeilValue,oldRateMasterId);
		setCeilValue(fareChartMaster.getCeiling_fare());
		
		System.out.println("status return statement.................."+res);
		
		
		if(res==1){
			addActionMessage("Fare Chart "+fareChartMaster.getFarechart_master_id()+" updated successfully");
		    return "success";
		}else{
			if(res==2){
				addActionError("Fare chart already exist.");
//				fareChartMaster.setEffect_start_date(com.getDateToDate2(fareChartMaster.getEffect_start_date()));
//        		
//				if(fareChartMaster.getEffect_end_date()!=null){
//				fareChartMaster.setEffect_end_date(com.getDateToDate2(fareChartMaster.getEffect_end_date()));
//				}
				fareChartMaster.setEffect_start_date(sDt);
	     		fareChartMaster.setEffect_end_date(eDt);
				
				return "input";
			}else{
				if(res==3){
					addActionError("Fare chart already exist.");
//					fareChartMaster.setEffect_start_date(com.getDateToDate2(fareChartMaster.getEffect_start_date()));
//	        		
//					if(fareChartMaster.getEffect_end_date()!=null){
//					fareChartMaster.setEffect_end_date(com.getDateToDate2(fareChartMaster.getEffect_end_date()));
//					}
					fareChartMaster.setEffect_start_date(sDt);
		     		fareChartMaster.setEffect_end_date(eDt);
					
	        		return "input";
				}
				
				else{
					if(res==4)
					{
						addActionError("Fare chart effective date should be equal to or in between route activation date");
//						fareChartMaster.setEffect_start_date(com.getDateToDate2(fareChartMaster.getEffect_start_date()));
//		        		
//						if(fareChartMaster.getEffect_end_date()!=null){
//						fareChartMaster.setEffect_end_date(com.getDateToDate2(fareChartMaster.getEffect_end_date()));
//						}
						fareChartMaster.setEffect_start_date(sDt);
			     		fareChartMaster.setEffect_end_date(eDt);
						
		        		return "input";
		        		
					}else{
					addActionError("Database error retry again.");
//					fareChartMaster.setEffect_start_date(com.getDateToDate2(fareChartMaster.getEffect_start_date()));
//	        		
//					if(fareChartMaster.getEffect_end_date()!=null){
//					fareChartMaster.setEffect_end_date(com.getDateToDate2(fareChartMaster.getEffect_end_date()));
//					}
					
					fareChartMaster.setEffect_start_date(sDt);
		     		fareChartMaster.setEffect_end_date(eDt);
		     		
					return "input";
					}
				}
			}
		}}else{
			return "input";
		}
	}

	@SkipValidation
	public String fareChartDetail(){
		HttpServletRequest request = ServletActionContext.getRequest();
		int fareMasterId=Integer.parseInt(request.getParameter("id"));
	
		Common com=new Common();
		FareChartMasterDao fareChartdao = new FareChartMasterDao();
		try{
		fareChartMaster = fareChartdao.getFareChart(fareMasterId);
		setCeilValue(fareChartMaster.getCeiling_fare());
		if(fareChartMaster.getFlexi_fare().equalsIgnoreCase("N")){
			fareChartMaster.setEffect_start_date(Common.getDateFromDateTimeFmt2(fareChartMaster.getEffect_start_date()));
			if(fareChartMaster.getEffect_end_date()!=null){
			fareChartMaster.setEffect_end_date(Common.getDateFromDateTimeFmt2(fareChartMaster.getEffect_end_date()));
			}
		}
		List<FareChart> farecharts=fareChartdao.getFareChartDetail(fareMasterId);
		System.out.println("Fare Chart Size"+farecharts.size()+"fis"+farecharts.isEmpty());
		if(farecharts.isEmpty()){
			fareChartdao.updateFareChartTriangle(fareChartMaster, fareMasterId, applyCeilValue,0,"farechart");
		}	
		
		List<FareChart> farecharts1=fareChartdao.getFareChartDetail(fareMasterId);
		//Flexi fare fn 14/11/14
		
		
		setChart(farecharts1);
//		System.out.println("fc list sz="+chart.size());
//		System.out.println("farecharts.get(0).getRouteId()="+farecharts.get(0).getRouteId());
		
		List bStops=fareChartdao.getBusStopsList(farecharts1.get(0).getRouteId());//fareChartdao.getBusStop(farecharts.get(0).getRouteId(),HibernateUtil.getSession(""));
		setBusStops(bStops);
//		System.out.println("b stp sz="+bStops.size());
		
		request.getSession().setAttribute("faremap",fareChartdao.getFareChartDetailMap(fareMasterId));
		request.getSession().setAttribute("stop",bStops);
		request.getSession().setAttribute("farechart",farecharts);
		request.getSession().setAttribute("masterid", fareMasterId);
		
		//passenger type
		request.getSession().setAttribute("passengerType","2");
		
		if(fareChartMaster.getFlexi_fare().equalsIgnoreCase("Y")){
			return "input";
		}else{
			return "nonFlexi";
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		//13/09/14
		//Header for fare chart triangle 13/09/14
		//createFareChartHeader(fareMaster);
		
		return "input";
	}
	@SkipValidation
    public String updateFareTriangle(){
    	//System.out.println("in tri updt");
    	HttpServletRequest request=ServletActionContext.getRequest();
    	//System.out.println("ids>>>"+request.getParameter("updated_ids"));
    if(request.getParameter("updated_ids").trim().length()>0)	{
    	String[] ids=request.getParameter("updated_ids").split(",");
    	
    	FareChartMasterDao fcmDao=new FareChartMasterDao();
    	int updatedRecordId=-1;//for message on triangle update
    	
    if(ids.length>0){
    	
    	Set<String> set=new HashSet<String>();

    	//Passenger type
    	int pType=Integer.parseInt(request.getParameter("fareChartMaster.passenger_type_id"));
    	
    	//ceiling fare 
    	int ceilingFare=Integer.parseInt(request.getParameter("ceilValue"));
   	
    	for(int i=0;i<ids.length;i++)
    	{
    		set.add(ids[i]);
    	}
    	
    	Iterator<String> iterator = set.iterator();
    	
      	
	    while(iterator.hasNext()) {
	    	try{
	         String setElement =iterator.next().trim();
	         //System.out.println("setElement="+setElement+"ll");
	         
	         int fare=0;
	         int toll=0;
	         
//	         String Para=request.getParameter(setElement);
//	         System.out.println("Para="+Para);
//	         if(Para!=null ){
//	        	 if(!setElement.startsWith("toll")){
//	        	 System.out.println("fare-------------------------"+Para);
//	        	 fare=Integer.parseInt(Para);
//	        	 System.out.println("fare="+fare);
//	        	 String para2=request.getParameter("toll"+setElement);
//	        	 System.out.println("para2="+para2+ "  toll"+setElement);
//	        	 if(para2!=null && para2.length()>0){
//	        		toll=Integer.parseInt(para2);
//	        		System.out.println("toll="+toll);
//	        	 }
//	        	 }else{
//	        		 System.out.println("toll-------------------------"+Para);
//	        	   String para3=request.getParameter(setElement.substring(4));
//	        	   setElement=setElement.substring(4);
//	        	   if(para3!=null && para3.length()>0){
//	  	              fare=Integer.parseInt(para3);
//	        	   }
//	  	           toll=Integer.parseInt(Para); 
//	        	 }
//	         }
	         int fvalue=0;
	         if(setElement.startsWith("t")){
	         String Para=request.getParameter(setElement);//toll
	         
	         fvalue=Integer.parseInt(Para);//Integer.parseInt(Para);
	        
	         
	         //System.out.println("fvalue="+fvalue);
	         if(Para!=null && Para.length()>0){
	        	 
	        	 String[] tollName=setElement.split(":");
	        	 
	        	 if(tollName.length>0){
	        		 for(int cc=0;cc<tollName.length;cc++){

	        			 try{
	        			        fcmDao.updateFareTriangleToll( Integer.parseInt(tollName[cc]),fare,fvalue);
	        			        updatedRecordId=Integer.parseInt(tollName[cc]);
	        			        
	        			    }catch(Exception ex){} 
	        		 }
	        	 }
	         }
	         }else{
	        	 String Para=request.getParameter(setElement);//fare
	        	 fvalue=Integer.parseInt(Para);
	        	 
	        	 if(ceilingFare!=0 && fvalue>ceilingFare){
	        		 fvalue=ceilingFare;
	        	 }
	        	 
	        	 //System.out.println("fare fvalue="+fvalue);
		         if(Para!=null && Para.length()>0){
		        	 
		        	 String[] fareName=setElement.split(":");
		        	 int fv=0;
		        	 if(fareName.length>0){
		        		 for(int cc=0;cc<fareName.length;cc++){
		        			 try{
		        				 if(pType==2){
		        				 	if(cc==0 && pType==2){
		        				 		fv=(int)Math.round(fvalue*0.50);	
		        				 	}else{
		        				 		if(cc==2 && pType==2){
		        				 			fv=(int)Math.round(fvalue*0.75);
		        				 		}else{
		        				 			fv=fvalue;
		        				 		}
		        				 	}
		        				 }else{
		        					 fv=fvalue; 
		        				 }
		        			        fcmDao.updateFareTriangle( Integer.parseInt(fareName[cc]),fv,toll);
		        			        updatedRecordId=Integer.parseInt(fareName[cc]);
		        			        
		        			    }catch(Exception ex){} 
		        		 }
		        		 
		        	 }
		         } 
	         }
	         
	         
	         //System.out.println("fare amt="+fare+"  toll="+toll);
	      }catch(Exception e){
	    	  addActionError("Please Enter Proper Fare Amount or Toll Fee");
	    	  return "input";
	      }
	    }   
	    	 
	    }
    
    int fcMasterId=fcmDao.fareChartIdByTriangleId(updatedRecordId);

    addActionMessage("Fare chart "+fcMasterId+" updated successfully");
    	
    }
		//System.out.println("ids="+ids); 
		//fareChartDetail();
    	return "successess";
    }
	
	@SkipValidation
	public String validFareValue(String fareString,String errMsg){
		String fareInt="0";
		CommonValidation cv=new CommonValidation();
		try{
			if(fareString!=null && fareString.trim().length()>0){
			if(cv.isSpecialChar(fareString)){
		     fareString=fareString.trim();

             int index=fareString.indexOf('.');
            
             //check for float
             if(index<=0){
            	 fareInt=fareString;
             }else{
            	addActionError(errMsg+" should be Integer");
 				return "input"; 
             }
             
             //check for int val
             try{
            	 
            	 int intVal=Integer.parseInt(fareString);
            	 fareInt=intVal+"";
            	 System.out.println("fareInt="+fareInt);
            	 
             }catch(Exception e){
            	addActionError(errMsg+" should be Integer");
 				return "input"; 
             }
             
             
			}else{
				addActionError("Please enter valid "+errMsg);
				return "input";
			}
			}else{
				addActionError("Please enter "+errMsg);
				return "input";
			}
		}
		catch(Exception e){}
		return fareInt;
	}
	
	@SkipValidation
    public String getTriangleByPassengerType(){
    	
    	HttpServletRequest request=ServletActionContext.getRequest();
    	
    	request.getSession().setAttribute("passengerType", request.getParameter("pid"));
    	
    	int k=Integer.parseInt(request.getParameter("pid"));
    	
    	fareChartMaster=new Farechart_Master();
    	fareChartMaster.setPassenger_type_id(k);
    	
    	return "input";
    }
	
	@SkipValidation
	public String getRouteNo() {
		FareChartMasterDao fareChartdao = new FareChartMasterDao();

		// serviceTypeIds=rmDao.getServiceId();
//		List<String> l1 = fareChartdao.getRouteId();
//		List<String> l2 = fareChartdao.getRouteno();
		List<Map<String, Object>> l=fareChartdao.getRouteId();
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=''>Select</option>";
		for (int i = 0; i < l.size(); i++) {
			Map<String, Object> rs = l.get(i);
			regionTypeAjaxString += "<option value=" + rs.get("route_id").toString()
					+ ">" + rs.get("route_number").toString()+"-"+ rs.get("route_direction").toString()+ "</option>";
		}
		// regionTypeAjaxString += "</select>";
//		System.out.println("regionTypeAjaxString=" + regionTypeAjaxString);
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	@SkipValidation
	public String getPassengerTypes() {
		FareChartMasterDao fareChartdao = new FareChartMasterDao();

		// serviceTypeIds=rmDao.getServiceId();
		List<String> l1 = fareChartdao.getPassengerId();
		List<String> l2 = fareChartdao.getPassengerName();
		String regionTypeAjaxString = "";
		regionTypeAjaxString += "<option value=''>Select</option>";
		for (int i = 0; i < l1.size(); i++) {
			regionTypeAjaxString += "<option value=" + l1.get(i).toString()
					+ ">" + l2.get(i).toString() + "</option>";
		}
		// regionTypeAjaxString += "</select>";
//		System.out.println("regionTypeAjaxString=" + regionTypeAjaxString);
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
	@SkipValidation
	public String deleteFareChartMaster() {
		HttpServletRequest request = ServletActionContext.getRequest();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "ShowFareChartAction.action");
		String access=accessrights.getACCESS();
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		if(delete.equalsIgnoreCase("Y")){
		int fareMasterId=Integer.parseInt(request.getParameter("id"));
		FareChartMasterDao fareChartdao = new FareChartMasterDao();
		String check=fareChartdao.checkDependencyFareMaster(fareMasterId);
		if(!check.equals("success")){
		String s=fareChartdao.deleteFareChart(fareMasterId);
		
		if(s.equals("success")){
    		addActionMessage("Fare chart master "+fareMasterId+" deleted successfully.");
		}else{
			addActionError("Fare chart master "+fareMasterId+" not deleted.");
		}
		}
		else{ addActionError("Fare chart master Id "+fareMasterId+" Used By Form Four "); }
	
		return "success";
		}else{
			addActionMessage("Access Denied - User Does Not Have Access Privileges");
			return "success";
		}
	}

	@SkipValidation
	public String getFareChartNameList(){
		FareChartMasterDao rmDao=new FareChartMasterDao(); 
		HttpServletRequest request = ServletActionContext.getRequest();
//System.out.println("in chart list");		
		int versionId=Integer.parseInt(request.getParameter("ver").toString());
		int routeId=Integer.parseInt(request.getParameter("rou").toString());
		int serviceId=Integer.parseInt(request.getParameter("ser").toString());
		
//System.out.println(routeId+":"+serviceId+":"+versionId);	
		List l1=rmDao.getFMaster(routeId,serviceId,versionId);

		String regionTypeAjaxString = "";
        regionTypeAjaxString += "<option value=''>Select</option>";
        
        int fId;
		String fName;
		Object[] obj;
		Common common =new Common();
		for(Iterator i=l1.iterator();i.hasNext();){
			obj=(Object[])i.next();
			fId=(Integer)obj[0];
			fName=(String)obj[1];
			
			String sDate=obj[2]!=null ? common.getDateToDate4(obj[2].toString()):"";
			String eDate=obj[3]!= null ? common.getDateToDate4(obj[3].toString()) : "";
			
			String dates=sDate+" - "+eDate;
			
			regionTypeAjaxString +=
                    "<option value=" + fId + ">"+fName+"("+dates+")</option>";
        
		}
        
//        for (int i = 0; i < l1.size(); i++) {
//            regionTypeAjaxString +=
//                    "<option value=" + l1.get(i).getFarechart_master_id() + ">" +l1.get(i).getFarechart_name() + "</option>";
//        }
        //regionTypeAjaxString += "</select>";
//        System.out.println("regionTypeAjaxString="+regionTypeAjaxString);
        HttpServletResponse response = ServletActionContext.getResponse();
        PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		return null;
	}
	
	public String getRouteNumber() {
		return routeNumber;
	}

	public void setRouteNumber(String routeNumber) {
		this.routeNumber = routeNumber;
	}

	
	@SkipValidation
	public String getRateMasterVersion(){
		FareChartMasterDao rmDao=new FareChartMasterDao(); 
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int rateId=Integer.parseInt(request.getParameter("id").toString());
		//serviceTypeIds=rmDao.getServiceId();
		List<RateMaster> l1=rmDao.getRateMasterVersionByServId(rateId);

		String regionTypeAjaxString = "";
        regionTypeAjaxString += "<option value=''>Select</option>";
        for (int i = 0; i < l1.size(); i++) {
            regionTypeAjaxString +=
                    "<option value=" + l1.get(i).getRateMasterId() + ">" +l1.get(i).getVersionNoServiceType()+"("+l1.get(i).getEffectiveStartDate()+" - "+l1.get(i).getEffectiveEndDate()+")" + "</option>";
        }
        //regionTypeAjaxString += "</select>";
//        System.out.println("regionTypeAjaxString="+regionTypeAjaxString);
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

	@SkipValidation
	public String copyFareChart(){
		HttpServletRequest request = ServletActionContext.getRequest();
		FareChartMasterDao fareChartdao = new FareChartMasterDao();
		 routeNumberMap=fareChartdao.getRouteNumber();
		try
		{
		  int fareMasterId=Integer.parseInt(request.getParameter("id"));
		  setMasterId(fareMasterId);
		  int fTri=fareChartdao.getFareChartDetailCount(fareMasterId);
		  
		  if(fTri>0){
		  
		//copy
		  fareChartMaster=fareChartdao.getFareChartById(fareMasterId); 
		  fareChartMaster.setEffect_start_date(Common.getDateFromDateTimeFmt2(fareChartMaster.getEffect_start_date()));
			
			
			if(fareChartMaster.getEffect_end_date()!=null){
				
			fareChartMaster.setEffect_end_date(Common.getDateFromDateTimeFmt2(fareChartMaster.getEffect_end_date()));
			}
			//setRouteNumber(fareChartdao.getRoutenoById(fareChartMaster.getRoute().getRoute_id()).get(0).toString());
	       // setServiceType(fareChartdao.getServiceNameById(fareChartMaster.getService_type_id()).get(0).toString());
			//setPassengerType(fareChartdao.getPassengerTypeById(fareChartMaster.getPassenger_type_id()).get(0).toString());
	        setRateMasterName(fareChartdao.getRateMasterVersionById(fareChartMaster.getRate_master_id()));
		  }else{
			//Flexi fare fn 14/11/14
			addActionError("Fare Chart Triangle is Unavailable for Fare Chart "+fareMasterId);
			return "success";
		  }
		}
		catch(Exception e){
			
		}
		
		return "copy";
	}
	@SkipValidation
    public String getNonFlexiTriangleByPassengerType(){
    	
    	HttpServletRequest request=ServletActionContext.getRequest();
    	
    	request.getSession().setAttribute("passengerType", request.getParameter("pid"));
    	
    	int k=Integer.parseInt(request.getParameter("pid"));
    	
    	fareChartMaster=new Farechart_Master();
    	fareChartMaster.setPassenger_type_id(k);
    	
    	return "nonFlexi";
    }
	public String getRateMasterNaCme() {
		return rateMasterName;
	}

	public void setRateMasterName(String rateMasterName) {
		this.rateMasterName = rateMasterName;
	}

	public void prepare() throws Exception {
		FareChartMasterDao fareChartdao = new FareChartMasterDao();
		
		//routeNumberMap=fareChartdao.getRouteNumber();
		
		serviceTypeMap=fareChartdao.getServiceType();
		
		passengerTypeMap=new HashMap<Integer,String>();
		passengerTypeMap.put(1,"Child");
		passengerTypeMap.put(2,"Adult");
		passengerTypeMap.put(3,"Senior Citizen");
		
		nightServiceMap=new HashMap<String,String>();
		nightServiceMap.put("N", "No");
		nightServiceMap.put("Y", "Yes");
		
		peakHoursMap=new HashMap<String,String>();
		peakHoursMap.put("N", "No");
		peakHoursMap.put("Y", "Yes");
		
		flexiFareMap=new HashMap<String,String>();
		flexiFareMap.put("N", "No");
		flexiFareMap.put("Y", "Yes");
	}
    
	public void validate(){
		Common common=new Common();
		FareChartMasterDao fareChartdao = new FareChartMasterDao();
		routeNumberMap=fareChartdao.getRouteNumber();
		
		setRateMasterName(fareChartdao.getRateMasterVersionById(fareChartMaster.getRate_master_id()));
		//System.out.println("fareChartMaster.getRoute().getRoute_id()="+fareChartMaster.getRoute().getRoute_id());
		if(fareChartMaster.getRoute()==null || fareChartMaster.getRoute().getRoute_id()==0){
			addFieldError("route.route_id","Please select Route No.");
		}
		
		if(fareChartMaster.getService_type_id()==0){
			addFieldError("service_type_id","Please select Service Type");
		}
		
		if(fareChartMaster.getRate_master_id()==0){
			addFieldError("rate_master_id","Please select Rate Master Version");
		}
		
		if(fareChartMaster.getEffect_start_date()==null || fareChartMaster.getEffect_start_date().equals("") || fareChartMaster.getEffect_start_date().length()<=0){
			addFieldError("effect_start_date","Please select Effective Start Date");
		}
		
//		if(fareChartMaster.getEffect_end_date()==null || fareChartMaster.getEffect_end_date().equals("") || fareChartMaster.getEffect_end_date().length()<=0){
//			addFieldError("effect_end_date","Please select Effective End Date");
//		}
		
		if(fareChartMaster.getFarechart_name()==null || fareChartMaster.getFarechart_name().trim().isEmpty()){
			addFieldError("farechart_name","Please enter Fare chart name");
		}else{

			CommonValidation cv=new CommonValidation();	
		 if (!cv.isSpecialChar(fareChartMaster.getFarechart_name())){
			addFieldError("farechart_name","Special characters not allowed"); 
		 }
		}
		
		if(fareChartMaster.getCeiling_fare()<0){
			fareChartMaster.setCeiling_fare("0");
			addFieldError("ceiling_fare","Please enter valid ceiling fare");
		}
//		else{
//			fareChartMaster.setEffect_start_date(common.getDateToDate(fareChartMaster.getEffect_start_date()));
//		}
		
//		if(!fareChartMaster.getEffect_end_date().equals("") || fareChartMaster.getEffect_end_date().length()>0){
//			fareChartMaster.setEffect_end_date(common.getDateToDate(fareChartMaster.getEffect_end_date()));
//		}
	}
	
	
	
	public Map<Integer,String> getRouteNumberMap() {
		return routeNumberMap;
	}

	public void setRouteNumberMap(Map<Integer,String> routeNumberMap) {
		this.routeNumberMap = routeNumberMap;
	}
	public Map<Integer, String> getServiceTypeMap() {
		return serviceTypeMap;
	}

	public void setServiceTypeMap(Map<Integer, String> serviceTypeMap) {
		this.serviceTypeMap = serviceTypeMap;
	}

	public Map<Integer, String> getPassengerTypeMap() {
		return passengerTypeMap;
	}

	public void setPassengerTypeMap(Map<Integer, String> passengerTypeMap) {
		this.passengerTypeMap = passengerTypeMap;
	}

	public Map<String, String> getNightServiceMap() {
		return nightServiceMap;
	}

	public void setNightServiceMap(Map<String, String> nightServiceMap) {
		this.nightServiceMap = nightServiceMap;
	}

	public Map<String, String> getPeakHoursMap() {
		return peakHoursMap;
	}

	public void setPeakHoursMap(Map<String, String> peakHoursMap) {
		this.peakHoursMap = peakHoursMap;
	}

	public List getChart() {
		return chart;
	}

	public void setChart(List chart) {
		this.chart = chart;
	}
	
	public List getBusStops() {
		return busStops;
	}

	public void setBusStops(List busStops) {
		this.busStops = busStops;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	/*public String getPassengerType() {
		return passengerType;
	}

	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}
*/
	public List<Farechart_Master> getList() {
		return list;
	}

	public void setList(List<Farechart_Master> list) {
		this.list = list;
	}

	public Farechart_Master getFareChartMaster() {
		return fareChartMaster;
	}

	public void setFareChartMaster(Farechart_Master fareChartMaster) {
		this.fareChartMaster = fareChartMaster;
	}

	public int getCeilValue() {
		return ceilValue;
	}

	public void setCeilValue(int ceilValue) {
		this.ceilValue = ceilValue;
	}

	public int getMasterId() {
		return masterId;
	}

	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}
	
	private Route routes;
	
	public Route getRoute() {
		return routes;
	}
	@SkipValidation
	public void setRoute(Route routes) {
		this.routes = routes;
	}

	@SkipValidation
	public String createFare_Chart_Route() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter out;
		Common common = new Common();
		
		System.out.println("vals========="+(String)request.getParameter("vals"));
		String vals=(String)request.getParameter("vals");
		String []serrate=vals.split(",");
		try{
		for(int m=0;m<serrate.length;m++){
			String []splitvals=serrate[m].split("-");
			fareChartMaster.setService_type_id(Integer.parseInt(splitvals[0]));
			fareChartMaster.setRate_master_id(Integer.parseInt(splitvals[1]));
		
		FareChartMasterDao fareChartdao = new FareChartMasterDao();
		//System.out.println("=========>" + fareChartMaster.getRoute().getRoute_id());
		int oldMasterId=0;
       
		fareChartMaster.setRate_master_id(fareChartMaster.getRate_master_id());	
		fareChartMaster.setCreated_by(request.getSession().getAttribute("userid").toString());
//		fareChartMaster.setEffect_start_date(common.getDateTimeFromPicker(fareChartMaster.getEffect_start_date()));
		fareChartMaster.setPassenger_type_id(2);
		
		setRateMasterName(fareChartdao.getRateMasterVersionById(fareChartMaster.getRate_master_id()));
		
		try{
			out = response.getWriter();
			if(fareChartMaster.getEffect_end_date().length()>0){
				System.out.println("=========="+fareChartMaster.getEffect_start_date()+"===="+fareChartMaster.getEffect_end_date());
				//validate str& end date
				//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				//Date date1=sdf.parse(fareChartMaster.getEffect_start_date());
				//Date date2=sdf.parse(fareChartMaster.getEffect_end_date());
				//System.out.println("date1=========="+date1+"date2===="+date2);
				/*if(date1.compareTo(date2)>0){
	        		System.out.println("Date1 is after Date2");
	        	//	addActionError("Effective End Date should be greater than Effective Start Date");
	        		out.print("Effective End Date should be greater than Effective Start Date");
	        		
	        		fareChartMaster.setEffect_start_date(common.getDateToDate2(fareChartMaster.getEffect_start_date()));
	        		fareChartMaster.setEffect_end_date(common.getDateToDate2(fareChartMaster.getEffect_end_date()));
	        		System.out.println("Effective End Date should be greater than Effective Start Date=========="+fareChartMaster.getEffect_start_date()+"===="+fareChartMaster.getEffect_end_date());
	        		if(oldMasterId==0){
		        		return "input";
		        		}else{
		        			return "input2";
		        		}
	        		return null;
	        		
	        	}else if(date1.compareTo(date2)==0){
	        		System.out.println("Date1 is equal to Date2");
	        		//addActionError("Effective End Date should be greater than Effective Start Date");
	        		out.print("Effective End Date should be greater than Effective Start Date");
	        		fareChartMaster.setEffect_start_date(common.getDateToDate2(fareChartMaster.getEffect_start_date()));
	        		
	        		
	        		fareChartMaster.setEffect_end_date(common.getDateToDate2(fareChartMaster.getEffect_end_date()));
	        		System.out.println("Effective End Date should be greater than Effective Start Date=========="+fareChartMaster.getEffect_start_date()+"===="+fareChartMaster.getEffect_end_date());
	        		if(oldMasterId==0){
	        		return "input";
	        		}else{
	        			return "input2";
	        		}
	        		return null;
	        		
	        	}*/
				
				
				
				
			 //fareChartMaster.setEffect_end_date(common.getDateTimeFromPicker1(fareChartMaster.getEffect_end_date()));
			 System.out.println("hiiii"+fareChartMaster.getEffect_start_date()+"===="+fareChartMaster.getEffect_end_date());
			}else{
				fareChartMaster.setEffect_end_date(null);
			}
			}catch(Exception e){e.printStackTrace();}
		//fareChartMaster.setEffect_start_date(common.getDateTimeFromPicker1(fareChartMaster.getEffect_start_date()));
		
		fareChartMaster.setCreated_date(new java.util.Date());
		System.out.println("hiiii222"+fareChartMaster.getEffect_start_date()+"===="+fareChartMaster.getEffect_end_date());
		if(oldMasterId==0){
		fareChartMaster.setPercentage(100);
		

		String res = fareChartdao.insertFareChartMaster(fareChartMaster);
		
		if (res.equals("success")) 
		{
       
			int fareMasterId=fareChartMaster.getFarechart_master_id();
			
			List<FareChart> farecharts=fareChartdao.getFareChartDetail(fareMasterId);
			setChart(farecharts);
			if(farecharts.size()!=0){
			List bStops=fareChartdao.getBusStopsList(farecharts.get(0).getRouteId());//fareChartdao.getBusStop(farecharts.get(0).getRouteId(),HibernateUtil.getSession(""));
			setBusStops(bStops);
			request.getSession().setAttribute("faremap",fareChartdao.getFareChartDetailMap(fareMasterId));
			request.getSession().setAttribute("stop",bStops);
			request.getSession().setAttribute("farechart",farecharts);
			request.getSession().setAttribute("masterid", fareMasterId);

			setCeilValue(fareChartMaster.getCeiling_fare());
			try {
				out = response.getWriter();
				out.print("Fare chart master "+fareChartMaster.getFarechart_master_id()+" created successfully.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else{
				try {
					out = response.getWriter();
					out.print("Fare chart master "+fareChartMaster.getFarechart_master_id()+" created successfully.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//addActionError("Fare chart master "+fareChartMaster.getFarechart_master_id()+" created successfully.");
			
			//return null;
		} else {
			if(res.equals("duplicate")){
				//fareChartMaster.setEffect_start_date(common.getDateToDate2(fareChartMaster.getEffect_start_date()));
        		
				/*if(fareChartMaster.getEffect_end_date()!=null){
				fareChartMaster.setEffect_end_date(common.getDateToDate2(fareChartMaster.getEffect_end_date()));
				}*/
				//addActionError("Fare chart "+fareChartMaster.getFarechart_master_id()+" already exist.");
				try {
					out = response.getWriter();
					out.print("ERROR: Fare chart already exist.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}else{
				if(res.equals("after")){
					/*fareChartMaster.setEffect_start_date(common.getDateToDate2(fareChartMaster.getEffect_start_date()));
	        		
					if(fareChartMaster.getEffect_end_date()!=null){
					fareChartMaster.setEffect_end_date(common.getDateToDate2(fareChartMaster.getEffect_end_date()));
					}*/
					try {
						out = response.getWriter();
						out.print("ERROR: Fare chart already exist.");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//addActionError("Fare chart "+fareChartMaster.getFarechart_master_id()+" already exist.");
					return null;
				}else{
					//addActionError("Database error retry again.");
					try {
						out = response.getWriter();
						out.print("ERROR: Database error retry again.");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}
			
			}
		}
		}else{
			//copy fare chart triangle
			String res =fareChartdao.insertFareChartMasterCopy(fareChartMaster,oldMasterId);
			
			int fareMasterId=fareChartMaster.getFarechart_master_id();
			
			List<FareChart> farecharts=fareChartdao.getFareChartDetail(fareMasterId);
			setChart(farecharts);
			//System.out.println("fc list sz="+chart.size());
			//System.out.println("farecharts.get(0).getRouteId()="+farecharts.get(0).getRouteId());
			
			List bStops=fareChartdao.getBusStopsList(farecharts.get(0).getRouteId());//fareChartdao.getBusStop(farecharts.get(0).getRouteId(),HibernateUtil.getSession(""));
			setBusStops(bStops);
			//System.out.println("b stp sz="+bStops.size());
		
			request.getSession().setAttribute("faremap",fareChartdao.getFareChartDetailMap(fareMasterId));
			request.getSession().setAttribute("stop",bStops);
			request.getSession().setAttribute("farechart",farecharts);
			request.getSession().setAttribute("masterid", fareMasterId);
			
			if (res.equals("success")) 
			{   
				//addActionError("Fare chart master "+oldMasterId+" created successfully.");
				try {
					out = response.getWriter();
					out.print("Fare chart master "+oldMasterId+" created successfully.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//return null;
			}else{
				if(res.equals("duplicate")){
					//fareChartMaster.setEffect_start_date(common.getDateToDate4(fareChartMaster.getEffect_start_date()));
	        		
					/*if(fareChartMaster.getEffect_end_date()!=null){
					fareChartMaster.setEffect_end_date(common.getDateToDate4(fareChartMaster.getEffect_end_date()));
					}*/
					//addActionError("Fare chart "+oldMasterId+" already exist.");
					try {
						out = response.getWriter();
						out.print("ERROR: Fare chart already exist.");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}else{
					if(res.equals("after")){
						//fareChartMaster.setEffect_start_date(common.getDateToDate4(fareChartMaster.getEffect_start_date()));
		        		
						/*if(fareChartMaster.getEffect_end_date()!=null){
						fareChartMaster.setEffect_end_date(common.getDateToDate4(fareChartMaster.getEffect_end_date()));
						}*/
						//addActionError("Fare chart "+oldMasterId+" already exist.");//("Fare chart with greater date already exist.");
						try {
							out = response.getWriter();
							out.print("ERROR: Fare chart already exist.");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
					}else{
						//addActionError("Database error retry again.");
						try {
							out = response.getWriter();
							out.print("ERROR: Database error retry again.");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
					}
				
				}
			}
		}
		
		}
		} catch(Exception e){
			e.printStackTrace();
		}
		finally{
			FareChartMasterDao fareChartdao1 = new FareChartMasterDao();
			fareChartdao1.updateDuplicateFareChart();
		}
		return null;
	}
	
	public String[] createFareChartHeader(int fareMasterId){
		FareChartMasterDao fareChartdao = new FareChartMasterDao();

		Farechart_Master fareMaster = fareChartdao.getFareChart(fareMasterId);
		
		String[] headerValue=new String[10];
		
		Map<Integer,String> routeNumberMap=fareChartdao.getRouteNumberAll();
		Map<Integer,String> serviceTypeMap=fareChartdao.getServiceType();
		Map<Integer,String> versionNameMap=fareChartdao.getRateMasterVersionMap();
		
		headerValue[0]=(routeNumberMap.get(fareMaster.getRoute().getRoute_id()));
		headerValue[1]=(serviceTypeMap.get(fareMaster.getService_type_id()));
		headerValue[2]=(versionNameMap.get(fareMaster.getRate_master_id()));
		
		headerValue[3]=(fareMaster.getFarechart_name());
		
		Common common=new Common();
		
		headerValue[4]=(fareMaster.getEffect_start_date()!=null ? common.getDateFromDateTime(fareMaster.getEffect_start_date()):"");
		headerValue[5]=(fareMaster.getEffect_end_date()!=null ? common.getDateFromDateTime(fareMaster.getEffect_end_date()):"");
		headerValue[6]=(fareMaster.getNignt_service().equalsIgnoreCase("N") ? "No" : "Yes");
		headerValue[7]=(fareMaster.getPeak_time().equalsIgnoreCase("N") ? "No" : "Yes");
		headerValue[8]=(fareMaster.getFlexi_fare().equalsIgnoreCase("N") ? "No" : "Yes");
		headerValue[9]=fareMaster.getCeiling_fare()+"";
		
		return headerValue;
	}

	public Map<String,String> getFlexiFareMap() {
		return flexiFareMap;
	}

	public void setFlexiFareMap(Map<String,String> flexiFareMap) {
		this.flexiFareMap = flexiFareMap;
	}
	
	@SkipValidation
	public String showHourlyChart(){
		FareChartMasterDao fareChartdao = new FareChartMasterDao();
		routeNumberMap=fareChartdao.getRouteNumber();
		
		return "success";
	}
	
	@SkipValidation
	public String getHourlyChartByRouteServiceType(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		try{
		int routeId=Integer.parseInt(request.getParameter("routeId"));
		int serviceTypeId=Integer.parseInt(request.getParameter("serviceType"));

		FareChartMasterDao fareChartdao = new FareChartMasterDao();

		String[] dbcol={"","farechart_master_id","farechart_name","hc.effect_start_date","hc.effect_end_date","0_1h","1_2h","2_3h","3_4h","4_5h","5_6h","6_7h","7_8h","8_9h","9_10h","10_11h","11_12h"
				,"12_13h","13_14h","14_15h","15_16h","16_17h","17_18h","18_19h","19_20h","20_21h","21_22h","22_23h","23_24h"};
		
		
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

		int total = -1;
		total = fareChartdao.getHourlyChartTotalRecords(request,dbcol[col],dir,routeId,serviceTypeId);
		AMOUNT = amount;
		SEARCH_TERM = request.getParameter("sSearch");
		DIR = dir;
		START = start;

		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();

		result = fareChartdao.getHourlyChartList(total, request,dbcol[Integer.parseInt(sCol)],sdir,routeId,serviceTypeId);

		out.print(result);
	} catch (Exception ex) {
		ex.printStackTrace();
	} finally {

	}
	return null;
	}

	public int getOldCeilValue() {
		return oldCeilValue;
	}

	public void setOldCeilValue(int oldCeilValue) {
		this.oldCeilValue = oldCeilValue;
	}

	public int getApplyCeilValue() {
		return applyCeilValue;
	}

	public void setApplyCeilValue(int applyCeilValue) {
		this.applyCeilValue = applyCeilValue;
	}

	public int getOldRateMasterId() {
		return oldRateMasterId;
	}

	public void setOldRateMasterId(int oldRateMasterId) {
		this.oldRateMasterId = oldRateMasterId;
	}
	
}