package com.trimax.its.usermanagement.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

import com.trimax.its.breaktype.dao.BreakTypeDao;
import com.trimax.its.common.Common;
import com.trimax.its.transport.dao.FormFourDAO;
import com.trimax.its.transport.model.BreakType;
import com.trimax.its.transport.model.OrgType;
import com.trimax.its.usermanagement.dao.EmployeeDAO;
import com.trimax.its.usermanagement.model.DesignationType;
import com.trimax.its.usermanagement.model.Employee;
import com.trimax.its.usermanagement.model.OrgChart;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.model.OrganisationChart;

public class EmployeeAction extends ActionSupport {
	
	private Pattern pattern;
	
	private Matcher matcher;
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public EmployeeAction() {
		pattern = Pattern.compile(EMAIL_PATTERN);
		
	}
	
	
	
	public boolean validateEmail(final String hex) {
		 
		matcher = pattern.matcher(hex);
		return matcher.matches();
 
	}
	
	
	
	
	
	
	
	
	private String DIR;

	private int START;

	private Employee employee;
	
	private DesignationType designationType;
	
	private OrgType orgType;
	
	private OrgChart orgChart;
	
	private String DL_EXPIRY_DT;
	
	private OrganisationChart organisationChart;
	
    public OrganisationChart getOrganisationChart() {
		return organisationChart;
	}


	public void setOrganisationChart(OrganisationChart organisationChart) {
		this.organisationChart = organisationChart;
	}

	private Map<Integer,String> designationMap;
	
	private Map<Integer,String> orgtypeMap;
	
	private Map<Integer,String> orgchartMap;
	
	private Map<Integer,String> emptype;
	
	private int id;
	
	
	
	private int org_type_id;
	private int org_chart_id;
	
	
	
	private String message;
	
	
	
	

	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Map<Integer, String> getEmptype() {
		return emptype;
	}



	public void setEmptype(Map<Integer, String> emptype) {
		this.emptype = emptype;
	}



	public int getOrg_chart_id() {
		return org_chart_id;
	}


	public void setOrg_chart_id(int org_chart_id) {
		this.org_chart_id = org_chart_id;
	}


	public int getOrg_type_id() {
		return org_type_id;
	}


	public void setOrg_type_id(int org_type_id) {
		this.org_type_id = org_type_id;
	}

	private int createEmployee;

	public int getCreateEmployee() {
		return createEmployee;
	}


	public void setCreateEmployee(int createEmployee) {
		this.createEmployee = createEmployee;
	}


	public DesignationType getDesignationType() {
		return designationType;
	}


	public void setDesignationType(DesignationType designationType) {
		this.designationType = designationType;
	}


	public OrgType getOrgType() {
		return orgType;
	}


	public void setOrgType(OrgType orgType) {
		this.orgType = orgType;
	}


	public OrgChart getOrgChart() {
		return orgChart;
	}


	public void setOrgChart(OrgChart orgChart) {
		this.orgChart = orgChart;
	}


	public Map<Integer, String> getDesignationMap() {
		return designationMap;
	}


	public void setDesignationMap(Map<Integer, String> designationMap) {
		this.designationMap = designationMap;
	}


	public Map<Integer, String> getOrgtypeMap() {
		return orgtypeMap;
	}


	public void setOrgtypeMap(Map<Integer, String> orgtypeMap) {
		this.orgtypeMap = orgtypeMap;
	}


	public Map<Integer, String> getOrgchartMap() {
		return orgchartMap;
	}


	public void setOrgchartMap(Map<Integer, String> orgchartMap) {
		this.orgchartMap = orgchartMap;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public String execute() throws Exception{
		return "Success";
	}
	
	
public void getEmployeelistDetails() throws Exception {
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			EmployeeDAO empdao=new EmployeeDAO();
			String[] dbcol={"","EMPLOYEE_ID","EMPLOYEE_NAME","EMPLOYEE_DESIGNATION","EMPLOYEE_NAME_KANNADA","PF","TOKEN","GENDER","WORKING_DEPOT","WORKING_DEPT","DL_EXPIRY_DT","DRIVING_LC_NO","DOB","CELL_PHONE","EMAIL","RETIREMENT_DT","FATHER_NAME","ADDRESS","STATUS","DOA","CONDUCTOR_LC_EXPDATE","TOKEN","EMPLOYEE_TYPE_NAME","org_chart2.org_name"};
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
			total =empdao.getTotalRecordsForEmployee(dbcol[Integer.parseInt(sCol)], sdir);
			DIR = dir;
			START = start;

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
			result=empdao.getEmployeeList(total, request, dbcol[Integer.parseInt(sCol)], sdir);
			//result = userdao.getUserList(total,request,sdir);
			//System.out.println("result----------"+result);
			out.print(result);
			
		}catch(Exception e){
			e.printStackTrace();
		}
}
public String viewcreatePage()
{
	EmployeeDAO emploDao = new EmployeeDAO();
	designationMap = emploDao.getDesignation();
	//System.out.println("des..................."+designationMap);
	orgtypeMap = emploDao.getOrgtype();
	orgchartMap = emploDao.getOrgchart();
	emptype=emploDao.getEmpType();
	
	return "success";
	
}
public String vieweditPage()
{
	HttpServletRequest request = ServletActionContext.getRequest();
	 SimpleDateFormat mdyFormat = new SimpleDateFormat("dd-MM-yyyy");
	EmployeeDAO employDao = new EmployeeDAO();
	employee=employDao.getEditedempId(Integer.parseInt(request
			.getParameter("empid")));
	if (employee.getDL_EXPIRY_DT() != null){
		employee.setDL_Exp_Date(mdyFormat.format(employee.getDL_EXPIRY_DT()));
	}
	
	if (employee.getDriver_RENEWAL_DT() != null){
		employee.setDriver_Renewal_Date(mdyFormat.format(employee.getDriver_RENEWAL_DT()));
	}
	if (employee.getConductor_RENEWAL_DT() != null){
		employee.setConductor_Renewal_Date(mdyFormat.format(employee.getConductor_RENEWAL_DT()));
	}
	
	if (employee.getDOB() != null){
		 String mdy = mdyFormat.format(employee.getDOB());
		System.out.println(".........."+mdy);
		employee.setD_O_B(mdy);
	}
	if (employee.getDOA() != null){
		employee.setD_O_A(mdyFormat.format(employee.getDOA()));
	}
	if (employee.getRETIREMENT_DT() != null){
		employee.setRetirementDate(mdyFormat.format(employee.getRETIREMENT_DT()));
	}
	if (employee.getCONDUCTOR_LC_EXPDATE() != null){
		employee.setCon_Lic_Exp_Date(mdyFormat.format(employee.getCONDUCTOR_LC_EXPDATE()));
	}
	if(employee.getPF().equalsIgnoreCase("NULL")){
		employee.setPF("");
	}
	if(employee.getTOKEN().equalsIgnoreCase("NULL")){
		employee.setTOKEN("");
	}
	if(employee.getWORKING_DEPT().equalsIgnoreCase("NULL")){
		employee.setWORKING_DEPT("");
	}
	if(employee.getDRIVING_LC_NO().equalsIgnoreCase("NULL")){
		employee.setDRIVING_LC_NO("");
	}
	if(employee.getCELL_PHONE().equalsIgnoreCase("NULL")){
		employee.setCELL_PHONE("");
	}
	if(employee.getEMAIL().equalsIgnoreCase("NULL")){
		employee.setEMAIL("");
	}
	if(employee.getFATHER_NAME().equalsIgnoreCase("NULL")){
		employee.setFATHER_NAME("");
	}
	if(employee.getADDRESS().equalsIgnoreCase("NULL")){
		employee.setADDRESS("");
	}
	
	
	
	System.out.println("employee.getOrgChartid===="+employee.getOrgchart().getOrg_name());
	
	EmployeeDAO emploDao = new EmployeeDAO();
	designationMap = emploDao.getDesignation();
	//System.out.println("des..................."+designationMap);
	orgtypeMap = emploDao.getOrgtype();
	orgchartMap = emploDao.getOrgchart();
	emptype=emploDao.getEmpType();
	return "success";
	
	
	
}
public String createEmployee() {
	HttpServletRequest request = ServletActionContext.getRequest();
	//java.util.Date currentDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new java.util.Date());
	String returnString="success";
	EmployeeDAO employDao = new EmployeeDAO();
	orgType=new OrgType();
	orgType.setOrg_type_id(org_type_id);
	employee.setOrgtype(orgType.getOrg_type_id()!=0?orgType:null);
//	organisationChart=new OrganisationChart();
//	organisationChart.setOrg_chart_id(Integer.parseInt(employee.getWORKING_DEPOT()));
//	employee.setOrgchart(organisationChart.getOrg_chart_id()!=0?organisationChart:null);
	
	//System.out.println(employee.getWORKING_DESIGNATION());
	//System.out.println(employee.getOrgchart());
	String desname=employDao.getDesignationName((employee.getWORKING_DESIGNATION()));
	String deptoname=employDao.getDeptoName(employee.getOrgchart());
	int divison=employDao.getDivisionid(employee.getOrgchart());
	System.out.println("employee.getDriver_Renewal_Date()=="+employee.getDriver_Renewal_Date());
	System.out.println("employee.getConductor_Renewal_Date()=="+employee.getConductor_Renewal_Date());
	Common common=new Common();
	String userid=request.getSession().getAttribute("userid").toString();
	if(employee.getWORKING_DESIGNATION().getDesignation_type_id()==23){
		employee.setEMPLOYEE_DESIGNATION(desname);
		employee.setWORKING_DEPOT(deptoname);
		employee.setDIVISION_ID(divison);
		try{
		employee.setDOB(common.getDate(employee.getD_O_B()));
		employee.setDOA(common.getDate(employee.getD_O_A()));
		employee.setRETIREMENT_DT(null);
		employee.setCONDUCTOR_LC_EXPDATE(common.getDate(employee.getCon_Lic_Exp_Date()));
		if(employee.getDriver_Renewal_Date() == null || employee.getDriver_Renewal_Date().trim().equalsIgnoreCase("")){
			employee.setDriver_RENEWAL_DT(null);
			System.out.println("Blank or null driver renewal date");
		}else{
			employee.setDriver_RENEWAL_DT(common.getDate(employee.getDriver_Renewal_Date()));
			System.out.println("driver renewal date found");
		}
		if(employee.getConductor_Renewal_Date() == null || employee.getConductor_Renewal_Date().trim().equalsIgnoreCase("")){
			employee.setConductor_RENEWAL_DT(null);
		}else{
		employee.setConductor_RENEWAL_DT(common.getDate(employee.getConductor_Renewal_Date()));
		}
		employee.setADHAR_CARD_NO(employee.getADHAR_CARD_NO());
		employee.setCreated_by(userid);
		employee.setCreated_date(new java.util.Date());
//		if(employDao.isTokenAvailable(employee)){
			id=employDao.saveEmployee(employee);
			addActionMessage("Employee Id "+id+" Created Successfully");
//				}else{
//					addActionError("Token Number already exist");
//					returnString = "input";
//				}
			
		}catch(Exception ex){
			
			returnString= "input"; 
		}
	}
	else{
	System.out.println("employee.getWORKING_DESIGNATION()=="+employee.getWORKING_DESIGNATION());
	
	employee.setEMPLOYEE_DESIGNATION(desname);
	//System.out.println(employee.getEMPLOYEE_DESIGNATION());
	
	employee.setWORKING_DEPOT(deptoname);
	employee.setDIVISION_ID(divison);
	
	
	
	employee.setTOKEN(employee.getTOKEN().trim());
	
	
	//System.out.println("userid---"+userid);
	try{
		//schedule.setEffectiveEndDate(common.getDate(schedule.getEndDate()));
		
		employee.setDL_EXPIRY_DT(common.getDate(employee.getDL_Exp_Date()));
		employee.setDOB(common.getDate(employee.getD_O_B()));
		employee.setDOA(common.getDate(employee.getD_O_A()));
		employee.setRETIREMENT_DT(common.getDate(employee.getRetirementDate()));
		employee.setCONDUCTOR_LC_EXPDATE(common.getDate(employee.getCon_Lic_Exp_Date()));
		if(employee.getDriver_Renewal_Date() == null || employee.getDriver_Renewal_Date().trim().equalsIgnoreCase("")){
			employee.setDriver_RENEWAL_DT(null);
			System.out.println("Null date found");
		}else{
			employee.setDriver_RENEWAL_DT(common.getDate(employee.getDriver_Renewal_Date()));
			System.out.println("date value found");
		}
		if(employee.getConductor_Renewal_Date() == null || employee.getConductor_Renewal_Date().trim().equalsIgnoreCase("")){
			employee.setConductor_RENEWAL_DT(null);
			System.out.println("Null conductor date found");
		}else{
			employee.setConductor_RENEWAL_DT(common.getDate(employee.getConductor_Renewal_Date()));
			System.out.println("date conductor value found");
		}
		System.out.println("emloyee date : " + employee.getDriver_Renewal_Date());
		System.out.println("emloyee date : " + employee.getConductor_Renewal_Date());
		employee.setADHAR_CARD_NO(employee.getADHAR_CARD_NO());
		employee.setCreated_by(userid);
		employee.setCreated_date(new java.util.Date());
		if(employDao.isTokenAvailable(employee)){
			
			System.out.println("---------true-----");
	id=employDao.saveEmployee(employee);
	addActionMessage("Employee Id "+id+" Created Successfully");
		}else{
			System.out.println("---------false-----");
			addActionError("Token Number already exist");
			returnString = "input";
		}
	}catch(Exception ex){
		
		returnString= "input"; 
	}
	}
	viewcreatePage();
	return returnString;
	
		
	
}
public String editEmployee(){
	String returnString="success";
	Common common=new Common();
	EmployeeDAO employDao = new EmployeeDAO();
	orgType=employee.getOrgtype();
	employee.setOrgtype(orgType.getOrg_type_id()!=0?orgType:null);
	
	HttpServletRequest request = ServletActionContext.getRequest();
	
	int chartid=Integer.parseInt(request.getParameter("chartid"));
	System.out.println("chartid............."+chartid);
	
	organisationChart=new OrganisationChart();
//	organisationChart.setOrg_chart_id(Integer.parseInt(employee.getWORKING_DEPOT()));
//	employee.setOrgchart(organisationChart.getOrg_chart_id()!=0?organisationChart:null);
	organisationChart=employee.getOrgchart();
	if(organisationChart.getOrg_chart_id()==0){
		organisationChart.setOrg_chart_id(chartid);
	}
	
	String desname=employDao.getDesignationName((employee.getWORKING_DESIGNATION()));
	employee.setEMPLOYEE_DESIGNATION(desname);
	int divison=employDao.getDivisionid(employee.getOrgchart());
	employee.setDIVISION_ID(divison);
	String deptoname=employDao.getDeptoName(employee.getOrgchart());
	employee.setWORKING_DEPOT(deptoname);
	System.out.println(employee.getDL_Exp_Date());
	employee.setDL_EXPIRY_DT(common.getDate(employee.getDL_Exp_Date()));
	System.out.println(employee.getDL_Exp_Date());
	employee.setDOB(common.getDate(employee.getD_O_B()));
	employee.setDOA(common.getDate(employee.getD_O_A()));
	employee.setRETIREMENT_DT(common.getDate(employee.getRetirementDate()));
	employee.setCONDUCTOR_LC_EXPDATE(common.getDate(employee.getCon_Lic_Exp_Date()));
	
	
	try{
		if(employDao.isTokenAvailable1(employee)){
		if(employDao.updateEmployee(employee, employee.getEmployee_id())){
			addActionMessage("Employee Id "+employee.getEmployee_id()+" Update Successfully");
		}else{
			addActionError("Problem in Employee Updation");
		}}else{
			addActionError("Token Number already exist");
			returnString= "input";
		}
	}catch (Exception e) {
		e.printStackTrace();
		returnString= "input";
	}
	viewcreatePage();
	return returnString;
}


public String editEmployeeAction(){
	String returnString="success";
	Common common=new Common();
	EmployeeDAO employDao = new EmployeeDAO();
	orgType=employee.getOrgtype();
	employee.setOrgtype(orgType.getOrg_type_id()!=0?orgType:null);
	
	HttpServletRequest request = ServletActionContext.getRequest();
	
	int chartid=Integer.parseInt(request.getParameter("chartid"));
//	System.out.println("chartid............."+chartid);
	
	organisationChart=new OrganisationChart();
//	organisationChart.setOrg_chart_id(Integer.parseInt(employee.getWORKING_DEPOT()));
//	employee.setOrgchart(organisationChart.getOrg_chart_id()!=0?organisationChart:null);
	organisationChart=employee.getOrgchart();
	if(organisationChart.getOrg_chart_id()==0){
		organisationChart.setOrg_chart_id(chartid);
	}
	
	String desname=employDao.getDesignationName((employee.getWORKING_DESIGNATION()));
	employee.setEMPLOYEE_DESIGNATION(desname);
	int divison=employDao.getDivisionid(employee.getOrgchart());
	employee.setDIVISION_ID(divison);
	String deptoname=employDao.getDeptoName(employee.getOrgchart());
	employee.setWORKING_DEPOT(deptoname);
	System.out.println(employee.getDL_Exp_Date());
	employee.setDL_EXPIRY_DT(common.getDate(employee.getDL_Exp_Date()));
	System.out.println(employee.getDL_Exp_Date());
	employee.setDOB(common.getDate(employee.getD_O_B()));
	employee.setDOA(common.getDate(employee.getD_O_A()));
	employee.setRETIREMENT_DT(common.getDate(employee.getRetirementDate()));
	employee.setCONDUCTOR_LC_EXPDATE(common.getDate(employee.getCon_Lic_Exp_Date()));
	if(employee.getDriver_Renewal_Date() == null || employee.getDriver_Renewal_Date().trim().equalsIgnoreCase("")){
		employee.setDriver_RENEWAL_DT(null);
		System.out.println("Null date found");
	}else{
		employee.setDriver_RENEWAL_DT(common.getDate(employee.getDriver_Renewal_Date()));
		System.out.println("date value found");
	}
	if(employee.getConductor_Renewal_Date() == null || employee.getConductor_Renewal_Date().trim().equalsIgnoreCase("")){
		employee.setConductor_RENEWAL_DT(null);
		System.out.println("Null conductor date found");
	}else{
		employee.setConductor_RENEWAL_DT(common.getDate(employee.getConductor_Renewal_Date()));
		System.out.println("date conductor value found");
	}
	
	//employee.setDriver_RENEWAL_DT(common.getDate(employee.getDriver_Renewal_Date()));
	//employee.setConductor_RENEWAL_DT(common.getDate(employee.getConductor_Renewal_Date()));
	employee.setADHAR_CARD_NO(employee.getADHAR_CARD_NO());
	if(employee.getWORKING_DESIGNATION().getDesignation_type_id()==23){
		employee.setDL_EXPIRY_DT(null);
		employee.setRETIREMENT_DT(null);
	}
	
	try{
//		if(employDao.isTokenAvailable1(employee)){
		int emp_id=employee.getEmployee_id();
		OrganisationChart org_chart_id=employee.getOrgchart();
		int org_id=org_chart_id.getOrg_chart_id();
//		System.out.println("org_id==="+org_id);
//		System.out.println("emp_id==="+emp_id);
		if(employDao.updateEmployeeAction(employee, employee.getEmployee_id())){
			int emp_id_new=employee.getEmployee_id();
//			System.out.println("new emp_id======="+emp_id_new);
			OrganisationChart org_chart_id_new=employee.getOrgchart();
			int org_id_new=org_chart_id_new.getOrg_chart_id();
//			System.out.println("new org_id_new======="+org_id_new);
			String menu_user_id=employDao.getMenuUserMasterID(emp_id);
		//	System.out.println("menu_user_id===="+menu_user_id);
			int count=employDao.UpdateMenuUserMasterID(menu_user_id,emp_id_new,org_id_new);
			addActionMessage("Employee Id "+employee.getEmployee_id()+" Update Successfully");
		}else{
			addActionError("Problem in Employee Updation");
		}
//		}else{
//			addActionError("Token Number already exist");
//			returnString= "input";
//		}
	}catch (Exception e) {
		e.printStackTrace();
		returnString= "input";
	}
	viewcreatePage();
	return returnString;
}




@Override
public void validate() {
	// TODO Auto-generated method stub
	
	if(getCreateEmployee()!=0){
		Common common=new Common();
		employee.setDOB(common.getDate(employee.getD_O_B()));
		employee.setDOA(common.getDate(employee.getD_O_A()));
		employee.setRETIREMENT_DT(common.getDate(employee.getRetirementDate()));
		
	if (employee.getEmployee_name() == null || employee.getEmployee_name().trim().equals("")) {
		addFieldError("employee_name","Please enter Employee Name");
	}
	if(employee.getEmployee_name().trim()!=null){
		String name=employee.getEmployee_name();
		System.out.println("name123...........>>"+name);
		if(Pattern.matches("[a-zA-Z ]+", name)) 
		{ 
		System.out.println("valid...........................>>!"); 
		} 
		else{
			addFieldError("employee_name","Please enter valid Name ");
		}
	}
	
//	if (employee.getEMPLOYEE_NAME_KANNADA() == null || employee.getEMPLOYEE_NAME_KANNADA().trim().equals("")) {
//		addFieldError("EMPLOYEE_NAME_KANNADA","Please enter Employee Name(KANNADA");
//	}
	if (employee.getWORKING_DESIGNATION() == null ) {
		addFieldError("EMPLOYEE_DESIGNATION","Please enter Employee Designation");
	}
//	if (employee.getPF() == null || employee.getPF().trim().equals("")) {
//		addFieldError("PF","Please enter PF Number");
//	}
	if(employee.getWORKING_DESIGNATION().getDesignation_type_id()==23){
		
	}else{
	if (employee.getTOKEN() == null || employee.getTOKEN().trim().equals("")) {
		addFieldError("Token","Please enter Token Number");
	}else if (employee.getTOKEN()!= null ) {
		String name=employee.getTOKEN().trim();
		if(Pattern.matches("[0-9]+", name)) 
		{ 
		System.out.println("valid...........................>>!"); 
		} 
		else{
			addFieldError("Token","Please enter valid numerical Token No ");
		}
	}}
	if (employee.getGENDER() == null || employee.getGENDER().trim().equals("")) {
		addFieldError("Gender","Please enter Employee Gender");
	}
	if (employee.getWORKING_DEPT() == null || employee.getWORKING_DEPT().trim().equals("")) {
		addFieldError("WORKING_DEPT","Please enter Employee Working Department");
	}
//	if(employee.getWORKING_DESIGNATION().getDesignation_type_id()==1 || employee.getWORKING_DESIGNATION().getDesignation_type_id()==16){
//		if (employee.getDRIVING_LC_NO() == null || employee.getDRIVING_LC_NO().trim().equals("")) {
//			addFieldError("DRIVING_LC_NO","Please enter Employee Driving Licence No");
//		}
//	}else{
//		
//	}
	
//	if (employee.getDOB() == null || employee.getDOB().equals("")) {
//		addFieldError("DOB","Please enter Employee Date Of Birth");
//	}
//	if (employee.getRETIREMENT_DT() == null || employee.getRETIREMENT_DT().equals("")) {
//		addFieldError("RETIREMENT_DT","Please enter Employee Retairment Date");
//	}
	if (employee.getSTATUS() == null || employee.getSTATUS().trim().equals("")) {
		addFieldError("getSTATUS","Please enter Employee Status");
	}
	if (employee.getEMAIL() == null || employee.getEMAIL().trim().equals("")) {
		
	}
	else if (employee.getEMAIL()!= null ) {
		boolean value=validateEmail(employee.getEMAIL().trim());
		System.out.println("bollean value...................>>"+value);
		if(value==false ){
			addFieldError("getEMAIL","Please enter valid Email Id");
		}
		
	}
	if (employee.getCELL_PHONE() == null || employee.getCELL_PHONE().trim().equals("")) {
		
	}
	else if (employee.getCELL_PHONE()!= null ) {
		String name=employee.getCELL_PHONE().trim();
		if(Pattern.matches("[0-9]+", name) && name.length()==10) 
		{ 
		System.out.println("valid...........................>>!"); 
		} 
		else{
			addFieldError("getCELL_PHONE","Please enter valid 10 digits Mobile Number ");
		}
	}
//	if (employee.getFATHER_NAME() == null || employee.getFATHER_NAME().trim().equals("")) {
//		addFieldError("FATHER_NAME","Please enter Father Name");
//	}
//	if(employee.getFATHER_NAME()!=null){
//		String name=employee.getFATHER_NAME().trim();
//		if(Pattern.matches("[a-zA-Z]+", name)) 
//		{ 
//		System.out.println("valid...........................>>!"); 
//		} 
//		else{
//			addFieldError("FATHER_NAME","Please enter valid Name ");
//		}
//	}
//	if(orgChart.getOrg_chart_id()==0){
//		addFieldError("orgchart", "Organization Name is mandatory");
//	}
//	if(orgType.getOrg_type_id()==0){
//		addFieldError("orgtype", "Organization Type is mandatory");
//	}
	super.validate();
	
	if(hasFieldErrors()){
		
EmployeeDAO emploDao=new EmployeeDAO();

	designationMap = emploDao.getDesignation();
	//System.out.println("des..................."+designationMap);
	orgtypeMap = emploDao.getOrgtype();
	orgchartMap = emploDao.getOrgchart();
	emptype=emploDao.getEmpType();
	}}
}

public String getUnitName() {
	EmployeeDAO empl=new EmployeeDAO();
	HttpServletRequest request = ServletActionContext.getRequest();
		int orgtypeid=Integer.parseInt(request.getParameter("orgid")) ;
	 	List<String> l1=empl.getUnitId(orgtypeid);
		List<String> l2=empl.getUnitName(orgtypeid);
		String regionTypeAjaxString = "";
	    //regionTypeAjaxString += "<option value=0>Select</option>";
	    for (int i = 0; i < l1.size(); i++) {
	        regionTypeAjaxString +="<option value=" + l1.get(i).toString() + " id='orgName"+l1.get(i).toString()+"'>" + l2.get(i).toString() + "</option>";
	    }
	    System.out.println("regionTypeAjaxString="+regionTypeAjaxString);
	    HttpServletResponse response = ServletActionContext.getResponse();
	    PrintWriter out;
		try {
			out = response.getWriter();
			out.print(regionTypeAjaxString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    

	System.out.println(orgtypeid);
	return null;
	  
 }
}