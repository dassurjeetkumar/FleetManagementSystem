package com.trimax.its.usermanagement.action;

import java.io.PrintWriter;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.trimax.its.common.Common;
import com.trimax.its.common.CommonValidation;
import com.trimax.its.usermanagement.dao.BataConfigDetailDAO;
import com.trimax.its.usermanagement.model.BataConfigDetails;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BataConfigDetailAction   extends ActionSupport {
	public BataConfigDetails getBataconfig() {
		return bataconfig;
	}

	public void setBataconfig(BataConfigDetails bataconfig) {
		this.bataconfig = bataconfig;
	}

	public Map<Integer, String> getDesignationlist() {
		return designationlist;
	}

	public void setDesignationlist(Map<Integer, String> designationlist) {
		this.designationlist = designationlist;
	}

	public Map<Integer, String> getSchedultypelist() {
		return schedultypelist;
	}

	public void setSchedultypelist(Map<Integer, String> schedultypelist) {
		this.schedultypelist = schedultypelist;
	}

	public Map<Integer, String> getServiceTypeMap() {
		return serviceTypeMap;
	}

	public void setServiceTypeMap(Map<Integer, String> serviceTypeMap) {
		this.serviceTypeMap = serviceTypeMap;
	}
	public Map<Integer, String> getShiftTypeMap() {
		return shiftTypeMap;
	}
	public void setShiftTypeMap(Map<Integer, String> shiftTypeMap) {
		this.shiftTypeMap = shiftTypeMap;
	}
	BataConfigDetails bataconfig;
	private Map<Integer, String> designationlist;
	private Map<Integer, String> schedultypelist;
	private Map<Integer,String> serviceTypeMap;
	private Map<Integer,String> shiftTypeMap;
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	
	@SkipValidation
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return "success";
	}
	
	@SkipValidation
	public String showbataconfigdetails()throws Exception {
		// TODO Auto-generated method stub
		try{
		BataConfigDetailDAO bataconfigdao=new BataConfigDetailDAO();
		designationlist=bataconfigdao.getDesignationList();
		shiftTypeMap=bataconfigdao.getDutyType();
		schedultypelist=bataconfigdao.getScheduletypeList();
		serviceTypeMap = bataconfigdao.getServiceType();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "success";
	}

	@SkipValidation
	public String  getprepare() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		BataConfigDetailDAO bataconfigdao=new BataConfigDetailDAO();
		System.out.println("getBataConfigListDetails");
		try{
			String[] dbcol={"","bata_config_id","designation_type.designation_type_name","schedule_type.schedule_type_name","min_km","max_km","day_bata","day_allowance","spcl_allowance","effect_start_date","effect_end_date"};
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
			total=bataconfigdao.getTotalRecords(request, dbcol[Integer.parseInt(sCol)],sdir);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			//COL_NAME = colName;
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result=bataconfigdao.getBataList(total,request,dbcol[Integer.parseInt(sCol)],sdir);
			System.out.println("result---------"+result);
			out.print(result);
		}catch(Exception e){
			//e.printStackTrace();
		}
	return null;
	}
	
   /*Code added for periovus code */
	/*public String getAddBataConfig(){
		String flag="";
		boolean flagdetails=true;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		BataConfigDetailDAO bataconfigdao=new BataConfigDetailDAO();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		Common common = new Common();
		try{
			
			designationlist=bataconfigdao.getDesignationList();
			schedultypelist=bataconfigdao.getScheduletypeList();
			System.out.println("startdate-------"+bataconfig.getEffect_start_date());
			bataconfig.setEffect_start_date(common.getDateFromPicker(bataconfig.getEffect_start_date()));
			bataconfig.setEffect_end_date(common.getDateFromPicker(bataconfig.getEffect_end_date()));
			int i=bataconfigdao.addBataConfigDetails(bataconfig, usrsessionid);
			if(i>0){
				addActionMessage("Bata Config Id "+i+" Created Successfully");
				flag="success";
			}else{
				if(i==-1){
				addActionError("Bata Config Name Already Exist.");
				flag="error";
			}else{
				addActionError("Database Error Retry Again.");
				flag="error";
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	*/
	
	public String getAddBataConfig(){
		//String flag="";
		boolean flagdetails=true;
		 
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		BataConfigDetailDAO bataconfigdao=new BataConfigDetailDAO();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		Common common = new Common();
		//CommonValidation common1=new CommonValidation();
		CommonValidation common1=new CommonValidation();
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		//int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "createBataConfig.action");
		String access=accessrights.getACCESS();
	
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		String read=accessrights.getREAD();
		if(create.equalsIgnoreCase("Y")){
		  boolean flag=true;
		  int shiftid=bataconfig.getDesignation_type_id().getDesignation_type_id();
		  int serviceid=bataconfig.getServicetype().getService_type_id();
		  System.out.println("desginationid----"+shiftid);
		  int shifttypeid=bataconfig.getSchedule_type_id().getSchedule_type_id();
		  System.out.println("shifttypeid===="+shifttypeid);
		  String str="";
			
		try{
			
			designationlist=bataconfigdao.getDesignationList();
			schedultypelist=bataconfigdao.getScheduletypeList();
			serviceTypeMap = bataconfigdao.getServiceType();
//			shiftTypeMap=bataconfigdao.getServiceType();
			System.out.println("startdate-------"+bataconfig.getEffect_start_date());
			
			//bataconfig.setEffect_start_date(common.getDateFromPicker(bataconfig.getEffect_start_date()));
			
		
			  
			  String minkm=bataconfig.getMin_km();
			  System.out.println("minkm--------"+minkm);
			  
			  String maxkm=bataconfig.getMax_km();
			  System.out.println("maxkm--------"+maxkm);	
			  
			  String daydata=bataconfig.getDay_bata();
			  System.out.println("daydata--------"+daydata);	
			
			  String dayallowance=bataconfig.getDay_allowance();
			  System.out.println("dayallowance--------"+dayallowance);	
			  
			  String spclallowance=bataconfig.getSpcl_allowance();
			  System.out.println("spclallowance--------"+spclallowance);	
			  
			  String startdate=bataconfig.getEffect_start_date();
			  System.out.println("startdate---"+startdate);
			  
			  String enddate=bataconfig.getEffect_end_date();
			  System.out.println("enddate---"+enddate);
			  String nightsevice_allowance=bataconfig.getNightsevice_allowance();
			  int id=0;
			  
			  id=bataconfig.getBata_config_id();
			  System.out.println("id---"+id);
			  
			  if(shiftid==0){
				
				  addFieldError("shiftid","Please select Duty type");
				  flag=false;
				  str="error";
			  }
			  
			  if(shifttypeid==0){
				 
				  addFieldError("schedule_type_id","Please Select Schedule Type");
				  flag=false;
				  str="error";
			  }
			if(serviceid==0)
			{
				
				addFieldError("service_type_id","Please Select Service Type");
				  flag=false;
				  str="error";
			}
			  
			//  if(minkm.equalsIgnoreCase(" "))
				if((minkm.trim().equals(""))||(minkm==null)||(minkm.trim().length()==0))
			  {
					
				  addFieldError("min_km","Min Km Should not blank");
				  flag=false;
				  str="error";
				 // return;
			  }
			  
			  //if(maxkm.equalsIgnoreCase(" "))
			  if((maxkm.trim().equals(""))||(maxkm==null)||(maxkm.trim().length()==0))
			  {
				 
				  addFieldError("max_km","Max Km Should not blank");  
				  flag=false;
				  str="error";
				  //return;
			  }
			  
			
			  
			  if((daydata.trim().equals(""))||(daydata==null)||(daydata.trim().length()==0))
			  {
				 
				  addFieldError("day_bata","Day Bata Should not blank");   
				  flag=false;
				  str="error";
				 
			  }
			  
			  if((dayallowance.trim().equals(""))||(dayallowance==null)||(dayallowance.trim().length()==0))
			  {
				  
				  addFieldError("day_allowance","Day Allowance Should not blank");   
				  flag=false;
				  str="error";
				  
			  }
			  
			  if((spclallowance.trim().equals(""))||(spclallowance==null)||(spclallowance.trim().length()==0))
			  {
				 
				  addFieldError("spcl_allowance","Special Allowance  Should not blank");   
				  flag=false;
				  str="error";
				 
			  }
			  
			  if((startdate.trim().equals(""))||(startdate==null)||(startdate.trim().length()==0))
			  {
				 
				  addFieldError("effect_start_date","Start Date  Should not blank"); 
				  flag=false;
				  str="error";
				 
			  }
			  
			  if((nightsevice_allowance.trim().equals(""))||(nightsevice_allowance==null)||(nightsevice_allowance.trim().length()==0))
			  {
				  addFieldError("nightsevice_allowance","Night Sevice Allowance   Should not blank"); 
				  flag=false;
				  str="error";
				 
			  }
			 
			  if(!isNumeric(nightsevice_allowance)){
					
					 addFieldError("nightsevice_allowance","Night Sevice Allowance Should Be Numberic"); 
					 flag=false;
					  str="error";
				}
			  
			if(!isNumeric(minkm)){
				
				 addFieldError("min_km","Min Km Should Be Numberic"); 
				 flag=false;
				  str="error";
			}
			 if(!isNumeric(maxkm))
			 {
				
				 addFieldError("max_km","Max  Km Should Be Numberic"); 
				 flag=false;
				  str="error";
			 }
			  
			 if(!isNumeric(daydata))
			 {
				 
				 addFieldError("day_bata","Day Bata Should Be Numberic"); 
				 flag=false;
				  str="error";
			 }
			 
			 if(!isNumeric(dayallowance))
			 {
				
				 addFieldError("day_allowance","Day Allowance Should Be Numberic"); 
				 flag=false;
				  str="error";
			 }
			 
			 if(!isNumeric(spclallowance))
			 {
				
				 addFieldError("spcl_allowance","Special Allowance Should Be Numberic"); 
				 flag=false;
				  str="error";
			 }
//			 CommonValidation commonn=new CommonValidation();
//			 String retval = Double.toString(bataconfig.getIncetive_percentage());
//			 if(commonn.isSpecialChar(retval))
//				{
//					addFieldError("percentage", "Special Character For Bata Incentive  is Not Allowed");
//					 flag=false;
//					  str="error";
//					//addActionError("Special Character For Device Type Name is Not Allowed");
//				}
			/* if(bataconfig.getIncetive_percentage()!=0 )
				{
					Float f=bataconfig.getIncetive_percentage();
					String percent=f.toString().trim();
					System.out.println("position="+percent.indexOf("."));
					if((percent.indexOf("."))== 4 ||(percent.indexOf(".")) == 0)
					{
					System.out.println(percent+"inside percentage elsewwwwwwwwwwwwwwwwwwwwwwwww");
					addFieldError("percentage", "please Enter Correct Incetive Percentage Number ");
					 flag=false;
					  str="error";
					bataconfig.setIncetive_percentage(bataconfig.getIncetive_percentage());
									
					}
					else if((percent.length())>=3  && ((percent.indexOf("."))==5||(percent.indexOf("."))==4)){
						System.out.println(percent+"2");
						addFieldError("percentage", "please Enter Correct Incetive Percentage Number ");
						 flag=false;
						  str="error";
						
					}
					else if((percent.length())>5  ){
						
						addFieldError("percentage", "please Enter Correct Incetive Percentage Number ");
						 flag=false;
						  str="error";
						
					}
					else if(f>100 ){
						
						addFieldError("percentage", "please Enter Correct Incetive Percentage Number ");
						 flag=false;
						  str="error";
						
					}
					
					else if((percent.equals("100.0")&&( percent.indexOf("."))!= 3)){
						System.out.println(percent+"3");
						addFieldError("percentage", "please Enter Correct Incetive Percentage Number ");
						 flag=false;
						  str="error";
						
					}
					
				else{
					System.out.println("inside percentage else");
					bataconfig.setIncetive_percentage(bataconfig.getIncetive_percentage());
					}
				}//
*/				
			 if(flag)
			 {
			  if(Long.parseLong(minkm)==Long.parseLong(maxkm))
			  {
				
				  addFieldError("max_km","Max Km and Min Km Should Not Be Same");   
				  flagdetails=false;
				  str="error";
			  }
			 
			 }
			 boolean test=true;
			 
			  if(flag && flagdetails)
				 {
				    if(bataconfigdao.getTableSize())
				  {
				    	
				    				    	
				    	
					  
					  if(bataconfigdao.getCheckRecord(shiftid, shifttypeid,common.getDateFromPicker(bataconfig.getEffect_start_date())))
					  {
						  //already exist record to that date increase its min value
						  int minkmew=bataconfigdao.getMinKmForDate(shiftid, shifttypeid,common.getDateFromPicker(bataconfig.getEffect_start_date()));
						  if(Long.parseLong(minkm)!=minkmew)
						  {
							  
								addFieldError("min_km","Min Km Should be "+ minkmew );
								  str="error";
								  test=false;
						  }
						  
						  if(Long.parseLong(minkm) > Long.parseLong(maxkm))
						  { 
							 
							  addFieldError("max_km","Max Km Should Be Greater Than Min Km" );
							  str="error";
							  test=false;
						  }
						  //then add into database 
						  //getting effective date of that perious record and add to that database
						  
						  if(test)
						  {
							  
							 // String effectiveenddate=bataconfigdao.getEffectiveDate(bataconfig.getDesignation_type_id().getDesignation_type_id(),bataconfig.getSchedule_type_id().getSchedule_type_id(),usrsessionid,common.getDateFromPicker(bataconfig.getEffect_start_date()));
							  String effectiveenddatevalue=bataconfigdao.getEffectiveDateValue(bataconfig.getDesignation_type_id().getDesignation_type_id(),bataconfig.getSchedule_type_id().getSchedule_type_id(),usrsessionid,common.getDateFromPicker(bataconfig.getEffect_start_date()));
							  System.out.println("effectiveenddatevalue---"+effectiveenddatevalue);
						  bataconfig.setEffect_end_date(effectiveenddatevalue);
						  int i=0;
						  if(effectiveenddatevalue!=null){
						   i=bataconfigdao.addBataConfigDetails(bataconfig, usrsessionid);
						  }else{
							   i=bataconfigdao.addBataConfig(bataconfig, usrsessionid);
						  }
							if(i>0){
								
								addActionMessage("Bata Config Id "+i+" Created Successfully");
								str="success";
							}else{
								
								addActionError("Database Error Retry Again.");
								str="error";
							
						  
						  
					  }
					  } 
						  
						  
						  
						  
					  }else{
						  //not exist to that database
						  
						  //String data
						  //calculate max date of that effective start date of that designation and role
						  //if gretater than effstart date
						  //ok
					  //else//not ok
						  
						  String maxeffstartdate=bataconfigdao.getEffectivemaxStartdate(bataconfig.getDesignation_type_id().getDesignation_type_id(),bataconfig.getSchedule_type_id().getSchedule_type_id());
						  System.out.println("maxeffstartdate---"+maxeffstartdate);
						  if(maxeffstartdate.equalsIgnoreCase("NA"))
						  {
							  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								 Date date1=new Date();
								 Date date2=new Date();
								 
								 
								 
								 
						         try {
									 date1 = (Date) sdf.parse(common.getDateFromPicker(bataconfig.getEffect_start_date()));
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
						         
						       
						         
						         String currentdate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
						 		System.out.println("currentdate---"+currentdate);
						         
						 		  try {
						 			 date2 = (Date) sdf.parse(common.getDateFromPicker(currentdate));
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
						 		
						         
						 		  
						 		  
						         if(date1.before(date2)){
						        	 addFieldError("effect_start_date","Effective Start Date Should Be Greater Than Current Date Or More Than Current Date");
						        	 str="error";
									  test=false;
						         }else{
						        	 
						        	 if(Long.parseLong(minkm) > Long.parseLong(maxkm))
									  {
										  addFieldError("max_km","Max Km Should Be Greater Than Min Km" );
										  str="error";
										  test=false;
									  }
						        	 //while adding the record do not add the effective end date 
						        	 //add the record and update the perivous record effective end date which value is null
						        	
						        	  int i=bataconfigdao.addBataConfig(bataconfig, usrsessionid);
						        	  if(i>0){
						        		  // date1 = (Date) sdf.parse(common.getDateFromPicker(bataconfig.getEffect_start_date()));
						        		  
						        		 // boolean flag=getUpdatedDate(i,)
						        		  String effectiveenddate=bataconfigdao.getEffectiveDate(bataconfig.getDesignation_type_id().getDesignation_type_id(),bataconfig.getSchedule_type_id().getSchedule_type_id(),usrsessionid,common.getDateFromPicker(bataconfig.getEffect_start_date()));
										  System.out.println("effectiveenddate---"+effectiveenddate);
										  boolean flag1=false;
										  flag=bataconfigdao.getUpdatedDate(i, effectiveenddate,bataconfig.getDesignation_type_id().getDesignation_type_id(),bataconfig.getSchedule_type_id().getSchedule_type_id());
										  if(flag){
											  addActionMessage("Bata Config Id "+i+" Created Successfully");
											  str="success"; 
										  }else{
											  str="error";
										  }
										  
						        		  
						        	  }
						         }
							 
						  }else{
							  System.out.println("in else part");
							  SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
								 Date date3=new Date();
								 Date date4=new Date();
								 
								  try {
										 date3 = (Date) sdf1.parse(common.getDateFromPicker(bataconfig.getEffect_start_date()));
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								  
								  try {
							 			 date4 = (Date) sdf1.parse(common.getDateFromPicker(maxeffstartdate));
										} catch (ParseException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
								  System.out.println("date3---"+date3);
								  System.out.println("date4---"+date4);
								  if(date3.before(date4)){
									  System.out.println("in if part");
							        	 addFieldError("effect_start_date","Effective Start Date Should Be Greater Than "+ maxeffstartdate);
							        	 str="error";
							        	 test=false;
										  
							         }else{
								  
								 
								  
								  
								  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									 Date date1=new Date();
									 Date date2=new Date();
									 
									 
									 
									 
							         try {
										 date1 = (Date) sdf.parse(common.getDateFromPicker(bataconfig.getEffect_start_date()));
									} catch (ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
							         
							       
							         
							         String currentdate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
							 		System.out.println("currentdate---"+currentdate);
							         
							 		  try {
							 			 date2 = (Date) sdf.parse(common.getDateFromPicker(currentdate));
										} catch (ParseException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
							 		
							         
							 		  
							 		  
							         if(date1.before(date2)){
							        	 addFieldError("effect_start_date","Effective Start Date Should Be Greater Than Current Date Or More Than Current Date");
							        	 str="error";
										  test=false;
							         }else{
							        	 
							        	 if(Long.parseLong(minkm) > Long.parseLong(maxkm))
										  {
											  addFieldError("max_km","Max Km Should Be Greater Than Min Km" );
											  str="error";
											  test=false;
										  }
							        	 //while adding the record do not add the effective end date 
							        	 //add the record and update the perivous record effective end date which value is null
							        	
							        	  int i=bataconfigdao.addBataConfig(bataconfig, usrsessionid);
							        	  if(i>0){
							        		  // date1 = (Date) sdf.parse(common.getDateFromPicker(bataconfig.getEffect_start_date()));
							        		  
							        		 // boolean flag=getUpdatedDate(i,)
							        		  String effectiveenddate=bataconfigdao.getEffectiveDate(bataconfig.getDesignation_type_id().getDesignation_type_id(),bataconfig.getSchedule_type_id().getSchedule_type_id(),usrsessionid,common.getDateFromPicker(bataconfig.getEffect_start_date()));
											  System.out.println("effectiveenddate---"+effectiveenddate);
											  boolean flag1=false;
											  flag=bataconfigdao.getUpdatedDate(i, effectiveenddate,bataconfig.getDesignation_type_id().getDesignation_type_id(),bataconfig.getSchedule_type_id().getSchedule_type_id());
											  if(flag){
												  addActionMessage("Bata Config Id "+i+" Created Successfully");
												  str="success"; 
											  }else{
												  str="error";
											  }
											  
							        		  
							        	  }
							         }
								  
								  
						  }  
						  }
						  
						  
						  
						  
						
						  
					  }//else
					  
					  
				  }else{
					  
					  //add to database
					  //add the record do not add effective end date it should be null;
					  
					  int i=bataconfigdao.addBataConfig(bataconfig, usrsessionid);
						if(i>0){
							addActionMessage("Bata Config Id "+i+" Created Successfully");
							str="success";
						}else{
							
							addActionError("Database Error Retry Again.");
							str="error";
						
					  
					  
				  }
				  
				 }
			 
			 
			
				 }
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return str;}
		else{
			return "error";
		}
	}
	
	public String getUpdateBata(){
		HttpServletRequest request = ServletActionContext.getRequest();
		BataConfigDetailDAO bataconfigdao=new BataConfigDetailDAO();
		//int id=Integer.parseInt(request.getParameter("bataid").toString());
		int id=bataconfig.getBata_config_id();
		int userid=(Integer)request.getSession().getAttribute("userid");
		String flag="";
		try{
			designationlist=bataconfigdao.getDesignationList();
			schedultypelist=bataconfigdao.getScheduletypeList();
			
			
			int i=bataconfigdao.updateBata(id, bataconfig, userid);
			if(i>0){
				addActionMessage("Bata Config "+id+" Updated Successfully");
				flag="success";
			}else{
				if(i==-1){
				addActionError("Bata Config Name Already Exist.");
				flag="error";
			}else{
				addActionError("Database Error Retry Again.");
				flag="error";
			}
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	@SkipValidation
	public String getEditDetails(){
		HttpServletRequest request = ServletActionContext.getRequest();
		BataConfigDetailDAO bataconfigdao=new BataConfigDetailDAO();
		int id=Integer.parseInt(request.getParameter("bataconfigid").toString());
		
		try{
			designationlist=bataconfigdao.getDesignationList();
			schedultypelist=bataconfigdao.getScheduletypeList();
			bataconfig=bataconfigdao.getEditBataDetails(id);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		 return "success";
	}
	
	@SkipValidation
	public String deletedRecordBata(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		BataConfigDetailDAO bataconfigdao=new BataConfigDetailDAO();
		int id=Integer.parseInt(request.getParameter("bataconfigiddetails").toString());
		int userid=(Integer)request.getSession().getAttribute("userid");
		AccessRightsDao  accessrightdao=new AccessRightsDao();
		AccessRights accessrights=new AccessRights();
		int usrsessionid=(Integer)request.getSession().getAttribute("userid");
		accessrights=accessrightdao.accessRightsdetails(usrsessionid, "createBataConfig.action");
		String access=accessrights.getACCESS();
		
		String create=accessrights.getCREATE();
		String edit=accessrights.getEDIT();
		String delete=accessrights.getDELETE();
		String read=accessrights.getREAD();
		if(delete.equalsIgnoreCase("Y")){
		boolean flag=false;
		
		try{
			 flag=bataconfigdao.deletedBata(id, userid);
		if(flag){
			addActionMessage(" Bata Config Id "+id+" Deleted Sucessfully");
		}else{
			addActionMessage(" Bata Config Id "+id +" Not Deleted");
		}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
		
		}else{
			return "success";
		}
	
	}
	
	/*public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	} */
	
	public boolean  isNumeric(String data)
	{
	    try{
	       
	     
	               String s=data;
	               for (int i = 0; i < s.length(); i++) {
	               //If we find a non-digit character we return false.
	               if (!Character.isDigit(s.charAt(i)))
	               return false;
	               }
	               return true;
	               
	    }catch(Exception e){
	    	e.printStackTrace();
	    	 return false;
	    }
	      
	}
	
	/*public static boolean isInteger(String s){
		try{
			Integer.parseInt(s);
			System.out.println("Hiiiiiiiiiiiiiiiiiiiiiiiii");
			return true;
		}catch(NumberFormatException e){
			System.out.println("Helllllllllllllllllll");
			return false;
		}
	}*/
	
	
	
	/*public void validate(){
		  HttpServletRequest request = ServletActionContext.getRequest();
		  BataConfigDetailDAO bataconfigdao=new BataConfigDetailDAO();
		  designationlist=bataconfigdao.getDesignationList();
			schedultypelist=bataconfigdao.getScheduletypeList();
		  int desginationid=bataconfig.getDesignation_type_id().getDesignation_type_id();
		  System.out.println("desginationid----"+desginationid);
		  int shifttypeid=bataconfig.getSchedule_type_id().getSchedule_type_id();
		  System.out.println("shifttypeid===="+shifttypeid);
			CommonValidation common1=new CommonValidation();
		  boolean flag=true;
		  
			Common common = new Common();
		  String minkm=bataconfig.getMin_km();
		  System.out.println("minkm--------"+minkm);
		  
		  String maxkm=bataconfig.getMax_km();
		  System.out.println("maxkm--------"+maxkm);	
		  
		  String daydata=bataconfig.getDay_bata();
		  System.out.println("daydata--------"+daydata);	
		
		  String dayallowance=bataconfig.getDay_allowance();
		  System.out.println("dayallowance--------"+dayallowance);	
		  
		  String spclallowance=bataconfig.getSpcl_allowance();
		  System.out.println("spclallowance--------"+spclallowance);	
		  
		  String startdate=bataconfig.getEffect_start_date();
		  System.out.println("startdate---"+startdate);
		  
		  String enddate=bataconfig.getEffect_end_date();
		  System.out.println("enddate---"+enddate);
		  
		  int id=0;
		  
		  id=bataconfig.getBata_config_id();
		  System.out.println("id---"+id);
		  
		  if(desginationid==0){
			  addFieldError("designation_type_id","Please select deignation type");
		  }
		  
		  if(shifttypeid==0){
			  addFieldError("schedule_type_id","Please Select Schedule Type");
		  }
			 
		  
		//  if(minkm.equalsIgnoreCase(" "))
			if((minkm.trim().equals(""))||(minkm==null)||(minkm.trim().length()==0))
		  {
				flag=false;
			  addFieldError("min_km","Min Km Should not blank");
			 // return;
		  }
		  
		  //if(maxkm.equalsIgnoreCase(" "))
		  if((maxkm.trim().equals(""))||(maxkm==null)||(maxkm.trim().length()==0))
		  {
			  flag=false;
			  addFieldError("max_km","Max Km Should not blank");   
			  //return;
		  }
		  
		
		  
		  if((daydata.trim().equals(""))||(daydata==null)||(daydata.trim().length()==0))
		  {
			  flag=false;
			  addFieldError("day_bata","Day Bata Should not blank");   
			 
		  }
		  
		  if((dayallowance.trim().equals(""))||(dayallowance==null)||(dayallowance.trim().length()==0))
		  {
			  flag=false;
			  addFieldError("day_allowance","Day Allowance Should not blank");   
			  
		  }
		  
		  if((spclallowance.trim().equals(""))||(spclallowance==null)||(spclallowance.trim().length()==0))
		  {
			  flag=false;
			  addFieldError("spcl_allowance","Special Allowance  Should not blank");   
			 
		  }
		  
		  if((startdate.trim().equals(""))||(startdate==null)||(startdate.trim().length()==0))
		  {
			  flag=false;
			  addFieldError("effect_start_date","Start Date  Should not blank");   
			 
		  }
		  
		  if((enddate.trim().equals(""))||(enddate==null)||(enddate.trim().length()==0))
		  {
			  flag=false;
			  addFieldError("effect_end_date","End  Date  Should not blank");   
			 
		  }
		  
		 
		  
		if(!isNumeric(minkm)){
			flag=false;
			 addFieldError("min_km","Min Km Should Be Numberic"); 
		}
		 if(!isNumeric(maxkm))
		 {
			 flag=false;
			 addFieldError("max_km","Max  Km Should Be Numberic"); 
		 }
		  
		 if(!isNumeric(daydata))
		 {
			 flag=false;
			 addFieldError("day_bata","Day Bata Should Be Numberic"); 
		 }
		 
		 if(!isNumeric(dayallowance))
		 {
			 flag=false;
			 addFieldError("day_allowance","Day Allowance Should Be Numberic"); 
		 }
		 
		 if(!isNumeric(spclallowance))
		 {
			 flag=false;
			 addFieldError("spcl_allowance","Special Allowance Should Be Numberic"); 
		 }
		 
 boolean flagdetails=true;
		 
		 if(flag)
		 {
		  if(Long.parseLong(minkm)==Long.parseLong(maxkm))
		  {
			  flagdetails=false;
			  addFieldError("max_km","Max Km and Min Km Should Not Be Same");   
		  }
		 
		 
		  if(flag && flagdetails)
			 {
			  
			  
			  
			  if(bataconfigdao.getTableSize())
			  {
				  
				  if(bataconfigdao.getCheckRecord(desginationid, shifttypeid,common.getDateFromPicker(bataconfig.getEffect_start_date())))
				  {
					  //already exist value of that date increase its min value
					  int minkmew=bataconfigdao.getMinKmForDate(desginationid, shifttypeid,common.getDateFromPicker(bataconfig.getEffect_start_date()));
					  if(Long.parseLong(minkm)!=minkmew)
					  {
							addFieldError("min_km","Min Km Should be "+ minkmew );
					  }
					  
					  if(Long.parseLong(minkm) > Long.parseLong(maxkm))
					  {
						  addFieldError("max_km","Max Km Should Be Greater Than Min Km" );
					  }
					  //then add into database and update
					  
				  }else{
					  
					  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						 Date date1=new Date();
						 Date date2=new Date();
						 
				         try {
							 date1 = (Date) sdf.parse(common.getDateFromPicker(bataconfig.getEffect_start_date()));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				         
				       
				         
				         String currentdate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				 		System.out.println("currentdate---"+currentdate);
				         
				 		  try {
				 			 date2 = (Date) sdf.parse(common.getDateFromPicker(currentdate));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				 		
				         
				         if(date1.before(date2)){
				        	 addFieldError("effect_start_date","Effective Start Date Should Be Greater Than Current Date Or More Than Current Date");
				         }else{
				        	 
				        	 if(Long.parseLong(minkm) > Long.parseLong(maxkm))
							  {
								  addFieldError("max_km","Max Km Should Be Greater Than Min Km" );
							  }
				        	 //update the old date effective date
				         }
					  
				  }
				  
				  
			  }else{
				  
				  //add to database
				  
			  }
			  
			 }
		 
		 
		 
		 
	}
	*/
	
	 /* public void validate(){
		  HttpServletRequest request = ServletActionContext.getRequest();
		  BataConfigDetailDAO bataconfigdao=new BataConfigDetailDAO();
		  designationlist=bataconfigdao.getDesignationList();
			schedultypelist=bataconfigdao.getScheduletypeList();
		  int desginationid=bataconfig.getDesignation_type_id().getDesignation_type_id();
		  System.out.println("desginationid----"+desginationid);
		  int shifttypeid=bataconfig.getSchedule_type_id().getSchedule_type_id();
		  System.out.println("shifttypeid===="+shifttypeid);
			CommonValidation common1=new CommonValidation();
		  boolean flag=true;
		  
			Common common = new Common();
		  String minkm=bataconfig.getMin_km();
		  System.out.println("minkm--------"+minkm);
		  
		  String maxkm=bataconfig.getMax_km();
		  System.out.println("maxkm--------"+maxkm);	
		  
		  String daydata=bataconfig.getDay_bata();
		  System.out.println("daydata--------"+daydata);	
		
		  String dayallowance=bataconfig.getDay_allowance();
		  System.out.println("dayallowance--------"+dayallowance);	
		  
		  String spclallowance=bataconfig.getSpcl_allowance();
		  System.out.println("spclallowance--------"+spclallowance);	
		  
		  String startdate=bataconfig.getEffect_start_date();
		  System.out.println("startdate---"+startdate);
		  
		  String enddate=bataconfig.getEffect_end_date();
		  System.out.println("enddate---"+enddate);
		  
		  int id=0;
		  
		  id=bataconfig.getBata_config_id();
		  System.out.println("id---"+id);
		  
		  if(desginationid==0){
			  addFieldError("designation_type_id","Please select deignation type");
		  }
		  
		  if(shifttypeid==0){
			  addFieldError("schedule_type_id","Please Select Schedule Type");
		  }
			 
		  
		//  if(minkm.equalsIgnoreCase(" "))
			if((minkm.trim().equals(""))||(minkm==null)||(minkm.trim().length()==0))
		  {
				flag=false;
			  addFieldError("min_km","Min Km Should not blank");
			 // return;
		  }
		  
		  //if(maxkm.equalsIgnoreCase(" "))
		  if((maxkm.trim().equals(""))||(maxkm==null)||(maxkm.trim().length()==0))
		  {
			  flag=false;
			  addFieldError("max_km","Max Km Should not blank");   
			  //return;
		  }
		  
		
		  
		  if((daydata.trim().equals(""))||(daydata==null)||(daydata.trim().length()==0))
		  {
			  flag=false;
			  addFieldError("day_bata","Day Bata Should not blank");   
			 
		  }
		  
		  if((dayallowance.trim().equals(""))||(dayallowance==null)||(dayallowance.trim().length()==0))
		  {
			  flag=false;
			  addFieldError("day_allowance","Day Allowance Should not blank");   
			  
		  }
		  
		  if((spclallowance.trim().equals(""))||(spclallowance==null)||(spclallowance.trim().length()==0))
		  {
			  flag=false;
			  addFieldError("spcl_allowance","Special Allowance  Should not blank");   
			 
		  }
		  
		  if((startdate.trim().equals(""))||(startdate==null)||(startdate.trim().length()==0))
		  {
			  flag=false;
			  addFieldError("effect_start_date","Start Date  Should not blank");   
			 
		  }
		  
		  if((enddate.trim().equals(""))||(enddate==null)||(enddate.trim().length()==0))
		  {
			  flag=false;
			  addFieldError("effect_end_date","End  Date  Should not blank");   
			 
		  }
		  
		 
		  
		if(!isNumeric(minkm)){
			flag=false;
			 addFieldError("min_km","Min Km Should Be Numberic"); 
		}
		 if(!isNumeric(maxkm))
		 {
			 flag=false;
			 addFieldError("max_km","Max  Km Should Be Numberic"); 
		 }
		  
		 if(!isNumeric(daydata))
		 {
			 flag=false;
			 addFieldError("day_bata","Day Bata Should Be Numberic"); 
		 }
		 
		 if(!isNumeric(dayallowance))
		 {
			 flag=false;
			 addFieldError("day_allowance","Day Allowance Should Be Numberic"); 
		 }
		 
		 if(!isNumeric(spclallowance))
		 {
			 flag=false;
			 addFieldError("spcl_allowance","Special Allowance Should Be Numberic"); 
		 }
		 
		 boolean flagdetails=true;
		 
		 if(flag)
		 {
		  if(Long.parseLong(minkm)==Long.parseLong(maxkm))
		  {
			  flagdetails=false;
			  addFieldError("max_km","Max Km and Min Km Should Not Be Same");   
		  }
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 Date date1=new Date();
		 Date date2=new Date();
         try {
			 date1 = (Date) sdf.parse(common.getDateFromPicker(bataconfig.getEffect_start_date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         try {
			 date2 =(Date) sdf.parse(common.getDateFromPicker(bataconfig.getEffect_end_date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         if(date1.after(date2)){
        	  flagdetails=false;
			  addFieldError("effect_end_date","Effective End Date Should Be Greater Than Effective Start Date");  
         }
         if(date1.equals(date2)){
        	  flagdetails=false;
			  addFieldError("effect_end_date","Effective End Date and Effective Start Date Should Not Be Same");  
         }
		 
         
		 }  
        
		 
		 if(flag && flagdetails)
		 {
			 
			 
			 int count=bataconfigdao.getCountDetails(desginationid, shifttypeid);
			 if(count==0)
			 {
				 if(Long.parseLong(minkm) > Long.parseLong(maxkm))
				  {
					  addFieldError("max_km","Max Km Should Be Greater Than Min Km" );
				  }
				 
				 
				 
			 }else{
				 int cnt=bataconfigdao.getCountDetailsfordate(desginationid, shifttypeid,common.getDateFromPicker(bataconfig.getEffect_start_date()),common.getDateFromPicker(bataconfig.getEffect_end_date()));
				 
				 if(cnt>0)
				 {
					 System.out.println("In if condition");
					 int minkmew=bataconfigdao.getMinKmForDate(desginationid, shifttypeid,common.getDateFromPicker(bataconfig.getEffect_start_date()),common.getDateFromPicker(bataconfig.getEffect_end_date()));
					 if(Long.parseLong(minkm)!=minkmew)
					  {
							addFieldError("min_km","Min Km Should be "+ minkmew );
					  }
					 if(Long.parseLong(minkm) > Long.parseLong(maxkm))
					  {
						  addFieldError("max_km","Max Km Should Be Greater Than Min Km" );
					  }
					 
				 }else{
					 boolean str=false;
					
					 System.out.println("In else condition");
					 if(Long.parseLong(minkm) > Long.parseLong(maxkm))
					  {
						  addFieldError("max_km","Max Km Should Be Greater Than Min Km" );
					  }
					 
					 if(bataconfigdao.getStartdateCheck(desginationid, shifttypeid, common.getDateFromPicker(bataconfig.getEffect_start_date())))
					 {
						//str=true;
						if(bataconfigdao.getEnddateCheck(desginationid, shifttypeid, common.getDateFromPicker(bataconfig.getEffect_end_date())))
						   {
							 str=true;
							
						   }else{
							   str=false;
							   addFieldError("effect_end_date","End  Date  Should Not Be Proper");   
							 }
						
						
						 
					 }else{
						 str=false;
						  addFieldError("effect_start_date","Start date Should Not Proper");   
					 }
					 
					 
					 
					 System.out.println("str---------"+str);
					 
					 if(str)
					 {
				if(bataconfigdao.getstartdateCheckingWithdatabase(desginationid, shifttypeid, common.getDateFromPicker(bataconfig.getEffect_start_date()), common.getDateFromPicker(bataconfig.getEffect_end_date())))
						{
					
						}else{
							 addFieldError("effect_start_date","Start Date Should Not Proper");   
						}
				
				if(bataconfigdao.getenddateCheckingWithdatabase(desginationid, shifttypeid, common.getDateFromPicker(bataconfig.getEffect_start_date()), common.getDateFromPicker(bataconfig.getEffect_end_date())))
				{
					
				}else{
					 addFieldError("effect_end_date","End Date Should Not Proper");   
				}
								 
					 }
					 
					 
					 
				 }
			 }
			 
			 
			 
		 }else{
			 
		 }
		  
	}*/
		 
		 
	}

