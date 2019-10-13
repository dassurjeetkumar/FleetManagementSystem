	package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;	
import com.trimax.its.common.Common;
import com.trimax.its.report.dao.VehicleLocationDataDao;
import com.trimax.its.transport.model.Schedule;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;
import org.hibernate.Session;


	public class VehicleLocationReportAction {
	
	private Map<Integer, String> vehiclelistname;

	public Map<Integer, String> getVehiclelistname() {
		return vehiclelistname;
	}

	public void setVehiclelistname(Map<Integer, String> vehiclelistname) {
		this.vehiclelistname = vehiclelistname;
	}

	public String startdate;
	public String enddate;
	public String ctoken;

	public String getCtoken() {
		return ctoken;
	}

	public void setCtoken(String ctoken) {
		this.ctoken = ctoken;
	}

	private List<Schedule> schedulelist;
	private String schedule;

	public List<Schedule> getSchedulelist() {
		return schedulelist;
	}

	public void setSchedulelist(List<Schedule> schedulelist) {
		this.schedulelist = schedulelist;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
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

	private String depotlist1;
	

	private Map<Integer, String> divisionlist;

	

	public Map<Integer, String> getDivisionlist() {
		return divisionlist;
	}

	public void setDivisionlist(Map<Integer, String> divisionlist) {
		this.divisionlist = divisionlist;
	}

	public String execute() {
		HttpServletResponse resp = ServletActionContext.getResponse();
		HttpServletRequest req = ServletActionContext.getRequest();
		 String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
         String orgchartid=(String)req.getSession().getAttribute("orgchartid");
         System.out.println("orgtypeid=="+orgtypeid);
         String id = "!=0";
 		if (orgtypeid.equals("1")) {
 			id = "org_type_id=2 and org_chart_id!=0";

 		}
 		if (orgtypeid.equals("3")) {

 			id = "org_chart_id in (select parent_id from org_chart where org_chart_id='"
 					+ orgchartid + "')";
 		}
 		if (orgtypeid.equals("2")) {

 			id = "org_chart_id in ('"+ orgchartid + "')";
 		}
    
         if(orgtypeid.equals("2")){
        	VtsDataDAO vvt = VtsDataDAO.getInstance();
        	int parentId1=0;
        	Date date = new Date();
     		Date date1 = new Date();
     		Date addedDate2 = addDays(date1, 1);
     		int parentId=0;

     		try {
                //  To display date in IST formate     			
     			SimpleDateFormat sm2 = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
     			SimpleDateFormat sm3 = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
     			setStartdate(sm3.format(addedDate2));
     			setEnddate(sm2.format(date));
     		} catch (Exception ex) {

     		}
			try {
				
				parentId1 = vvt.getDepot1DC(orgtypeid,orgchartid);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			divisionlist = vvt.getDivisionNameDC(orgtypeid,orgchartid,parentId1);
        }
         
        else if(orgtypeid.equals("1")  && orgchartid.equals("1")){
        	VtsDataDAO vvt = VtsDataDAO.getInstance();
    		Date date = new Date();
    		Date date1 = new Date();
    		Date addedDate2 = addDays(date1, 1);
    		try {
    			SimpleDateFormat sm2 = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
    			SimpleDateFormat sm3 = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
    			setStartdate(sm3.format(addedDate2));
    			setEnddate(sm2.format(date));
    		} catch (Exception ex) {

    		}
    		try {
    			divisionlist= vvt.getDepot();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		divisionlist = vvt.getDivisionName();
    		//vehiclelistname = vvt.getVehicledetails(0);
        }
         
        else{
         	//Our Logic
         	VtsDataDAO vvt = VtsDataDAO.getInstance();
 		Date date = new Date();
 		Date date1 = new Date();
 		Date addedDate2 = addDays(date1, 2);
 		int parentId=0;
 		try {
 			SimpleDateFormat sm2 = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
 			SimpleDateFormat sm3 = new SimpleDateFormat("dd MMMM yyyy - HH:mm");
 			setStartdate(sm3.format(addedDate2));
 			setEnddate(sm2.format(date));
 		} catch (Exception ex) {

 		}
 		try {
 			parentId = vvt.getDepot1(orgtypeid,orgchartid);
 			
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		divisionlist = vvt.getDivisionName(orgtypeid,orgchartid,parentId);
 		//Ends..
         }
		return "success";
	}

	public static Date addDays(Date d, int days) {
		d.setTime(d.getTime()  - 86400 * 1000);
		return d;
	}
		
		public String getDepotlist1() {
			return depotlist1;
		}

		public void setDepotlist1(String depotlist1) {
			this.depotlist1 = depotlist1;
		}

		StringBuffer regionTypeAjaxString=new StringBuffer();
		double totdistancee=0.0;
		double percVal=0.0;
		public static String rbKey = String.valueOf(getSysParameterForVts());
		
		public String getVehicleLocationData()
		{
					HttpServletRequest req=ServletActionContext.getRequest();
					VehicleLocationDataDao vdao=new VehicleLocationDataDao();
					try {
						Common common=new Common();
					
					Date  ss1=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
				    String runDateTime = sdf.format(ss1);
				    SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd hh:mm ");
				    String startdate1 = common.getDateTimeFromPickerToDB(startdate);
				    String enddate1 = common.getDateTimeFromPickerToDB(enddate);
//				    System.out.println("strt--"+startdate1);
//				    System.out.println("end--"+enddate1);
				 
					model.jaxb.xml.trimax.com.VtsResponse5 locationresult = null;
					com.trimax.ws.vts.vtsutility.WsUtil_Service service = new com.trimax.ws.vts.vtsutility.WsUtil_Service();
					com.trimax.ws.vts.vtsutility.WsUtil port = service.getWsUtilPort();
					locationresult=port.webLocationData(startdate1, enddate1, ctoken, rbKey);
//					queryyy="select device_id, DATE_FORMAT(IST_DATE,'%d-%m-%Y %H:%i') IST_DATE," +
//							"round(avg(SPEED_KMPH),2) Speed, LAT, LONGITUDE from vts_parse_data vpt FORCE INDEX (IDX_DEVICE_ID_IST_DATE) " +
//							"where device_id='"+vehno+"' and " +
//							" ist_date between '"+startdate1+"' and '"+enddate1+"' group by " +
//							"DATE_FORMAT(IST_DATE,'%m-%d-%Y %:%i') order by IST_DATE";
//					
//					Query query = session.createSQLQuery(queryyy)
//				  			.addScalar("device_id", Hibernate.INTEGER)
//								 .addScalar("IST_DATE", Hibernate.STRING)
//								 .addScalar("Speed", Hibernate.DOUBLE)
//								 .addScalar("LAT", Hibernate.DOUBLE)
//				  	 			 .addScalar("LONGITUDE",Hibernate.DOUBLE);
//					
//					 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//						List<Map<String, Object>> aliasToValueMapList = query.list();	
				  
			   
			 regionTypeAjaxString.append(" <div id='header' style='display: none;'><div align='center'><h4>Vehicle Location Report</br>From Date:- "+startdate1+" To Date:-"+ enddate1+"</h4></br></div>");
//		        regionTypeAjaxString.append("<div align='right'><b> Date:-</b>"+runDateTime+"</div></div>");
			 regionTypeAjaxString.append("<div align='right'>&nbsp;&nbsp;&nbsp;<b> Vehicle No.:-</b>"+vehicleNo(ctoken)+"</div>&nbsp;&nbsp;&nbsp;</div>");
		        
				regionTypeAjaxString.append("<div class='component'><table class='overflow-y' border='1'>");
				regionTypeAjaxString.append("<thead><tr><th>Sr. No</th><th>Time</th><th>Location</th><th>Speed Km/h</th>"+"</tr></thead><tbody>");
	
			    	for (int i = 0; i < locationresult.getVtsDatamodel().size(); i++) {
						int j=i+1;
						regionTypeAjaxString.append("<tr>");
						regionTypeAjaxString.append("<td>"+(i+1)+"</td>");
						regionTypeAjaxString.append("<td>"+locationresult.getVtsDatamodel().get(i).getISTDATE()+"</td>");
						
						String lat=locationresult.getVtsDatamodel().get(i).getLAT();     
						double lat1=Double.parseDouble(lat);
						String long1=locationresult.getVtsDatamodel().get(i).getLONGITUDE();
						double longitude=Double.parseDouble(long1);
						String location=getVehicleLocation(lat1,longitude);
						regionTypeAjaxString.append("<td>"+location+"</td>");
						regionTypeAjaxString.append("<td>"+locationresult.getVtsDatamodel().get(i).getSPEEDKMPH()+"</td>");
						regionTypeAjaxString.append("</tr>");
                    }
			    	
			    	for (int i = 0; i < locationresult.getVtsDatamodel().size()-1 ; i++) {
			    	                            if (i != locationresult.getVtsDatamodel().size()) {
			    	                            	Double lat2=Double.parseDouble(locationresult.getVtsDatamodel().get(i + 1).getLAT());
			    	                            	Double lonitude2=Double.parseDouble(locationresult.getVtsDatamodel().get(i + 1).getLONGITUDE());
			    	                                totdistancee += distanceBetweenTwoLocationsInM((locationresult.getVtsDatamodel().get(i).getLAT()).toString(), locationresult.getVtsDatamodel().get(i).getLONGITUDE().toString(), Double.parseDouble(locationresult.getVtsDatamodel().get(i + 1).getLAT()), Double.parseDouble(locationresult.getVtsDatamodel().get(i + 1).getLONGITUDE()));
			    	                                DecimalFormat df = new DecimalFormat("#.##");      
			    	                                percVal = Double.valueOf(df.format(totdistancee));
			    	                            }
			    	                        }
			    	regionTypeAjaxString.append("<tr><td colspan='4'><center><b>Total KM Operated by "+vehicleNo(ctoken)+" is = </b><b>"+ percVal +" KM</td></tr>"+"\n");
					 regionTypeAjaxString.append( "</tbody></table></div> </div>"); 
					 if(locationresult.getVtsDatamodel().size()==0){
				    		regionTypeAjaxString.append("<div><table border='1' class='table table-striped table-bordered table-hover' id='sample_6' style='width:100%;'>");
				    		regionTypeAjaxString.append("<tr>");
							regionTypeAjaxString.append("<td colspan='3' align='center'><b>No Result Found</b></td>");
							regionTypeAjaxString.append("</tr>");	 
						 }
						HttpServletResponse response = ServletActionContext.getResponse();
					PrintWriter out;
					out = response.getWriter();
					out.print(regionTypeAjaxString);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
		public static Double distanceBetweenTwoLocationsInM(String latitudeOne, String longitudeOne, Double latitudeTwo, Double longitudeTwo) {
	        if (latitudeOne == null || latitudeTwo == null || longitudeOne == null || longitudeTwo == null) {
	            return null;
	        }
	        Double earthRadius = 6371.0;
	        Double diffBetweenLatitudeRadians = Math.toRadians(latitudeTwo - Double.parseDouble(latitudeOne));
	        Double diffBetweenLongitudeRadians = Math.toRadians(longitudeTwo - Double.parseDouble(longitudeOne));
	        Double latitudeOneInRadians = Math.toRadians(Double.parseDouble(latitudeOne));
	        Double latitudeTwoInRadians = Math.toRadians(latitudeTwo);
	        Double a = Math.sin(diffBetweenLatitudeRadians / 2)
	                * Math.sin(diffBetweenLatitudeRadians / 2)
	                + Math.cos(latitudeOneInRadians)
	                * Math.cos(latitudeTwoInRadians)
	                * Math.sin(diffBetweenLongitudeRadians / 2)
	                * Math.sin(diffBetweenLongitudeRadians / 2);
	        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	        if (earthRadius * c * 1000 > 5000) {
	            return 0.0;
	        }
	        return (earthRadius * c * 1000)/1000;
	    }

		
		
		public String vehicleNo(String device_serial_no)
		{ 
			String vehno="";
			 try {
		
			 ses=HibernateUtil.getSession("hibernate.cfg.xml");
			
			Query qry = ses.createSQLQuery("select v.license_number as VehicleNo from vehicle v inner join vehicle_device vd on vd.vehicle_id = v.vehicle_id " +
					" inner join device d on d.device_id = vd.device_id  " +
					" where v.deleted_status=0 " +
//					"and org_chart_id= '10'" +
					" and vd.status = 'active' and d.device_serial_number='"+device_serial_no+"' and d.deleted_status = 0 group by v.vehicle_id  " +
							" order by v.license_number").addScalar("VehicleNo");
			vehno= (String)qry.uniqueResult();	
			
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				
			}
			return vehno;
		}
		
		public String getPerticularVehicle() {

			HttpServletRequest req = ServletActionContext.getRequest();
			VtsDataDAO dao = VtsDataDAO.getInstance();
			int parentId = Integer.parseInt(req.getParameter("val"));
			String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
		    String orgchartid=(String)req.getSession().getAttribute("orgchartid");
		    
			List<String> l1 = dao.getVehicleID1(parentId);
			List<String> l2 = dao.getVehicleName1(parentId);
			String regionTypeAjaxString = "";
			regionTypeAjaxString += "<option value='0'>------select------</option>";
			//regionTypeAjaxString += "<option  value='ALL'>------------</option>";
			for (int i = 0; i < l1.size(); i++) {
				//String vehiclearr[] = l1.get(i).toString().split("#");
				regionTypeAjaxString += "<option value=" + l1.get(i).toString() + ">"
						+ l2.get(i).toString()  + "</option>";
			}
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
		
		
		public String getPerticularDepot() {
			// get Depot List..
			HttpServletRequest req = ServletActionContext.getRequest();
			VtsDataDAO dao = VtsDataDAO.getInstance();
			int parentId = Integer.parseInt(req.getParameter("val"));
			String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
	        String orgchartid=(String)req.getSession().getAttribute("orgchartid");
	        
	        if(orgtypeid.equals("2")){
//	        	if(!orgtypeid.equals("1") && !orgchartid.equals("1")){
	        		List<String> l1 = dao.getDepotIdDC(parentId,orgchartid);
	        		List<String> l2 = dao.getDepotNameDC(parentId,orgchartid);
	        		String regionTypeAjaxString = "";
	        		//regionTypeAjaxString += "<option value='0'>------select------</option>";
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
//	        		return null;
//	        	}
			
	        }
	        
	      
	        else if(orgtypeid.equals("1") && orgchartid.equals("1")){
	        	List<String> l1 = dao.getDepotId(parentId);
	    		List<String> l2 = dao.getDepotName(parentId);
	    		String regionTypeAjaxString = "";
	    		regionTypeAjaxString += "<option value='0'>select</option>";
	    		regionTypeAjaxString += "<option value='-1'>All</option>";
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
	    		regionTypeAjaxString += "<option value='0'>------select------</option>";
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
	        
			return null;

		}
		
		
		
		
		Session ses;
		// here we are getting Location
				public String getVehicleLocation(double lat1, double longitude){
					String loc = "";
					try{
						 ses=HibernateUtil.getSession("hibernate.cfg.xml");
					Query query2 = ses.createSQLQuery("SELECT bus_stop_name,(((acos(sin(('"+lat1+"'*pi()/180)) *  sin((`latitude_current`*pi()/180))+ " +
							" cos(('"+lat1+"'*pi()/180)) *cos((`latitude_current`*pi()/180)) * cos((('"+longitude+"'- `longitude_current`)* " +
							" pi()/180))))*180/pi())*60*1.1515)*1000 as dist FROM bus_stop bs  where bs.status='Approved' and   bs.point_type_id not in (2,13,18)  order by dist limit 1")
							.addScalar("bus_stop_name");
					loc= (String)query2.uniqueResult();			
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}finally{
					if(ses !=null){
						ses.close();
					}
				}
					return loc;
		}
				
				public static int getSysParameterForVts() {
					int param = 2;
					Session session = null;
					try {
						session = HibernateUtil.getSession("");
						String sql = "select sys_value from default_system_veriable where sys_key ='VTS_DASHBOARD_RECTRICTION_PARAM'";
						Query query = session.createSQLQuery(sql);
						if (query.list().size() > 0) {
							param = Integer.parseInt(query.uniqueResult().toString());
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
						session.close();
					}
					return param;
				}
		
		
			 }





	
	
	

