package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.report.dao.EmployeeDutyTypeDAO;
import com.trimax.its.transport.dao.TicketSubTypeDAO;
import com.trimax.its.usermanagement.model.DesignationType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vehicle.model.OrganisationChart;
import com.trimax.its.vts.dao.VtsDataDAO;

public class Employeedefault extends ActionSupport{
	 public Map<Integer, String> getDutytypelist() {
		return dutytypelist;
	}


	public void setDutytypelist(Map<Integer, String> dutytypelist) {
		this.dutytypelist = dutytypelist;
	}


	public Map<Integer, String> getDutytypelist1() {
		return dutytypelist1;
	}


	public void setDutytypelist1(Map<Integer, String> dutytypelist1) {
		this.dutytypelist1 = dutytypelist1;
	}
	private Map<Integer, String> section;
	private Map<Integer, String> batchlist;
	public Map<Integer, String> getBatchlist() {
		return batchlist;
	}


	public void setBatchlist(Map<Integer, String> batchlist) {
		this.batchlist = batchlist;
	}


	public String getBatch() {
		return batch;
	}


	public void setBatch(String batch) {
		this.batch = batch;
	}
	private String batch;
	public Map<Integer, String> getSection() {
		return section;
	}


	public void setSection(Map<Integer, String> section) {
		this.section = section;
	}
	private Map<Integer, String> dutytypelist;
	 private Map<Integer, String> dutytypelist1;
	 private String sectionvar;
	public String getSectionvar() {
		return sectionvar;
	}


	public void setSectionvar(String sectionvar) {
		this.sectionvar = sectionvar;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDutytype() {
		return dutytype;
	}


	public void setDutytype(String dutytype) {
		this.dutytype = dutytype;
	}


	public String getWo1() {
		return wo1;
	}


	public void setWo1(String wo1) {
		this.wo1 = wo1;
	}


	public String getWo2() {
		return wo2;
	}


	public void setWo2(String wo2) {
		this.wo2 = wo2;
	}


	public String getEffective_start_date() {
		return effective_start_date;
	}


	public void setEffective_start_date(String effective_start_date) {
		this.effective_start_date = effective_start_date;
	}


	public String getEffective_end_date() {
		return effective_end_date;
	}


	public void setEffective_end_date(String effective_end_date) {
		this.effective_end_date = effective_end_date;
	}
	private String empid;
	public String getEmpid() {
		return empid;
	}


	public void setEmpid(String empid) {
		this.empid = empid;
	}


	public String getPf() {
		return pf;
	}


	public void setPf(String pf) {
		this.pf = pf;
	}
	private String pf;
	private String token;
	private String name;
	private String dutytype;
	public String getDutytype1() {
		return dutytype1;
	}


	public void setDutytype1(String dutytype1) {
		this.dutytype1 = dutytype1;
	}
	private String dutytype1;
	private String wo1;
	private String wo2;
	private String 	effective_start_date;
	private String 	effective_end_date;
	private String dutyrotaid;
	
	public String getDutyrotaid() {
		return dutyrotaid;
	}


	public void setDutyrotaid(String dutyrotaid) {
		this.dutyrotaid = dutyrotaid;
	}


	public String execute() throws Exception {
		System.out.println("hellooo");
		
		designationlist = getDesignation();
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
        String orgchartid=(String)req.getSession().getAttribute("orgchartid");
        HttpSession session = req.getSession();
        session.setAttribute("orgtypeid", orgtypeid);
        session.setAttribute("orgchartid", orgchartid);
		
		return "success";
	}
	String path="";
	char ft = 15;
	String str="";

	String c=" ";
	
	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	private String SEARCH_TERM;
	 public String startdate;
	    public String enddate;
	    private Map<Integer, String> designationlist;
	    private String designationlist1;
	    
	


	public Map<Integer, String> getDesignationlist() {
			return designationlist;
		}


		public void setDesignationlist(Map<Integer, String> designationlist) {
			this.designationlist = designationlist;
		}


		public String getDesignationlist1() {
			return designationlist1;
		}


		public void setDesignationlist1(String designationlist1) {
			this.designationlist1 = designationlist1;
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


	String regionTypeAjaxString = "";

	
	public String viewEmployeeDuty() throws Exception {
		try{
		System.out.println("hellooo");
		HttpServletRequest req = ServletActionContext.getRequest();
		VtsDataDAO dao = VtsDataDAO.getInstance();
		String orgtypeid=(String)req.getSession().getAttribute("orgtypeid");
        String orgchartid=(String)req.getSession().getAttribute("orgchartid");
        HttpSession session = req.getSession();
        session.setAttribute("orgtypeid", orgtypeid);
        session.setAttribute("orgchartid", orgchartid);
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return "success";
	}
	
		@SkipValidation
		public String getEmployeeDefaultTypeList(){
			EmployeeDutyTypeDAO dao=new EmployeeDutyTypeDAO();
			try {
				HttpServletResponse response=ServletActionContext.getResponse();
				HttpServletRequest request=ServletActionContext.getRequest();
				String orgchartid=request.getParameter("orgchartdid");  
				System.out.println("org_chart"+orgchartid);
				
				String designationId=request.getParameter("designationId");
				
				String[] cols = {"TOKEN","PF","EMPLOYEE_NAME","","","","",""};
				
				String[] dbcol={"TOKEN","PF","EMPLOYEE_NAME","","","","","",""};
				      
	           
				JSONObject result = new JSONObject();
				int amount = 10;
				int start = 0;
				int col = 0;
				String dir = "asc";
				String sStart=""; 
				String sAmount="";
				String sCol="";
				try{
					sStart= request.getParameter("iDisplayStart");
				 sAmount = request.getParameter("iDisplayLength");
				//System.out.println("sAmount---"+sAmount);
				 sCol = request.getParameter("iSortCol_0");
				}catch(Exception e){
					e.printStackTrace();
				}
				//System.out.println("sCol---"+sCol);
				//String sCol="4";
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
					total = dao.getTotalRecords(orgchartid,designationId,request,dbcol[Integer.parseInt(sCol)],sdir);
					AMOUNT = amount;
					SEARCH_TERM = request.getParameter("sSearch");
					COL_NAME = colName;
					DIR = dir;
					START = start;
					
					response.setContentType("application/json");
					response.setHeader("Cache-Control", "no-store");
					PrintWriter out = response.getWriter();
				
				result=dao.getEmployeeList(orgchartid,total,designationId,request,dbcol[Integer.parseInt(sCol)],sdir);
				out.print(result);
				designationlist = getDesignation();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				
			}
			return null;
			
		}
		

	
	

	public Map<Integer, String> getDesignation() {
		  Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
	        Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	       // int userId = Integer.parseInt(ServletActionContext.getRequest().getSession().getAttribute("org_chart_id").toString());

	        try {
	            Query query = session
	                    .createSQLQuery("SELECT designation_type_id,designation_type_name FROM designation_type " +
	                    		"WHERE status='ACTIVE' and deleted_status=0 and designation_type_id not in(1,2,16)").addScalar("designation_type_id", Hibernate.INTEGER).addScalar("designation_type_name", Hibernate.STRING);
	            query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	            List<Map<String, Object>> aliasToValueMapList = query.list();
	            resultMap.put(0, "Select");
	            System.out.println("Size of usertype type List : " + aliasToValueMapList.size());
	            for (int i = 0; i < aliasToValueMapList.size(); i++) {
	                Map<String, Object> rs = aliasToValueMapList.get(i);
	                resultMap.put(Integer.parseInt(rs.get("designation_type_id").toString()), rs.get("designation_type_name").toString());
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (session != null) {
	                session.close();
	            }

	        }
	        return resultMap;
	
	
	}
	
	
		
		@SkipValidation
		public void viewemployeeDefaultviewList(){
			try {
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpServletResponse response = ServletActionContext.getResponse();
				String orgchartid=request.getParameter("orgchartdid");  
				EmployeeDutyTypeDAO dao=new EmployeeDutyTypeDAO();
				String[] dbcol={"","dutyrota_id","TOKEN","PF","EMPLOYEE_NAME","section_name","batch_name","duty_type","wo_1","duty_type1","wo_2","effective_start_date","effective_end_date"};
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
				String SEARCH_TERM = request.getParameter("sSearch");
				total =dao.getTotalRecordsForDuty(orgchartid,dbcol[Integer.parseInt(sCol)], sdir);
				DIR = dir;
				START = start;

				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-store");
				PrintWriter out = response.getWriter();
				result=dao.getEmployeeDutyTypeList(orgchartid,total, request, dbcol[Integer.parseInt(sCol)], sdir);
				//result = userdao.getUserList(total,request,sdir);
				//System.out.println("result----------"+result);
				out.print(result);
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	public String editemployeeDefault() {

			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String val=request.getParameter("val");  
			System.out.println(val+"-----------------");
			this.dutytypelist=getdutymap();
			this.section=getsection();
			this.batchlist=getbatch();
		try {
			List<Map<String, Object>> l=new EmployeeDutyTypeDAO().getforedit(val);
			Map<String, Object> rs = l.get(0);
			System.out.println("rs sizeee--------"+rs);
			this.setDutyrotaid(rs.get("dutyrota_id").toString());
             this.setEmpid(rs.get("emd_id").toString());
             this.setPf(rs.get("PF").toString());
			this.setToken(rs.get("TOKEN").toString());
			this.setName(rs.get("EMPLOYEE_NAME").toString());
			this.setDutytype(rs.get("duty_type").toString());
			this.setDutytype1(rs.get("duty_type1").toString());
			this.setWo1(rs.get("wo_1").toString());
			this.setWo2(rs.get("wo_2").toString());
			this.setSectionvar(rs.get("section").toString());
			this.setBatch(rs.get("batch").toString());
			//Common c=new Common();
			String d1=changedate(rs.get("effective_start_date").toString());
			this.setEffective_start_date(d1);
			this.setEffective_end_date(changedate(rs.get("effective_end_date").toString()));
			
		}catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
		
		return "success";
		}
	public String SaveeditemployeeDefault() {
		Boolean result=false;
		Boolean result1=false;
		int i=0;
System.out.println("1st-----------------");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
		String empid=request.getParameter("empid"); 
		String dutyrotaid=request.getParameter("dutyrotaid");
		String dutytype=request.getParameter("dutytype"); 
		String dutytype1=request.getParameter("dutytype1"); 
		String wo1=request.getParameter("wo1"); 
		String wo2=request.getParameter("wo2"); 
		String section1=request.getParameter("section1"); 
		String batch1=request.getParameter("batch1");
		String effective_start_date=request.getParameter("effective_start_date"); 
		String effective_end_date=request.getParameter("effective_end_date"); 
		System.out.println("datae-----"+effective_start_date+" "+effective_end_date);
			
		 result=new EmployeeDutyTypeDAO().saveeditdetails(dutyrotaid, dutytype, wo1, wo2, effective_start_date, effective_end_date);
		
		 i=new EmployeeDutyTypeDAO().saveList(empid,section1,batch1,dutytype,wo1,dutytype1,wo2,effective_start_date,effective_end_date);
		System.out.println(result+"-----222-----"+i);
		if(result==true && i!=0){
			System.out.println("in--------------------");
			addActionMessage("Duty Rota  Id "+i+" Edited Successfully");
			System.out.println(getActionMessages());
			}else{
				addActionError("Duty Rota  Id "+dutyrotaid+" Not Edited Successfully");
			}
		}catch (Exception e) {
              e.printStackTrace();
          	addActionError("Duty Rota  Id "+dutyrotaid+" Not Edited Successfully");
              return "false";
                      }
		return "success";
	}
public String changedate(String pickerDate) {
	//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy");

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    String formattedDate="";
    try
    {
    	date = simpleDateFormat1.parse(pickerDate);
    	formattedDate=simpleDateFormat.format(date);
    }catch(Exception ex)
    {
    	ex.printStackTrace();
    	
    }
    finally{
    	return formattedDate;
    }

}
public Map<Integer, String> getdutymap() {
	Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	Query query = session
			.createSQLQuery("select dutyrotatype_id,duty_type from dutyrota_type   where deleted_status=0");
	try {
		System.out.println("div 2");
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> l= query.list();
		for(Map<String, Object> map:l) {
			resultMap.put((Integer)map.get("dutyrotatype_id"), (String)map.get("duty_type"));
		}
	} catch (Exception ex) {
	} finally {
		session.close();
	}
	return resultMap;
}
public Map<Integer, String> getsection() {
	Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	Query query = session
			.createSQLQuery("select id,section_name from dutyrota_section   where deleted_status=0");
	try {
		System.out.println("div 2");
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> l= query.list();
		for(Map<String, Object> map:l) {
			resultMap.put((Integer)map.get("id"), (String)map.get("section_name"));
		}
	} catch (Exception ex) {
	} finally {
		session.close();	
	}
	return resultMap;
}
public Map<Integer, String> getbatch() {
	Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	Query query = session
			.createSQLQuery("select id,batch_name from dutyrota_batch   where deleted_status=0");
	try {
		System.out.println("div 2");
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> l= query.list();
		for(Map<String, Object> map:l) {
			resultMap.put((Integer)map.get("id"), (String)map.get("batch_name"));
		}
	} catch (Exception ex) {
	} finally {
		session.close();
	}
	return resultMap;
}

	}

