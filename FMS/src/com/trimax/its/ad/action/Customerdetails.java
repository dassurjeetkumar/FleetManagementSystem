package com.trimax.its.ad.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transaction;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.transport.model.Customer;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.utility.dao.AccessRightsDao;
import com.trimax.its.utility.model.AccessRights;
import com.trimax.its.vehicle.dao.VehicleDAO;

public class Customerdetails extends ActionSupport{
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
public String getC_cid() {
		return C_cid;
	}


	public void setC_cid(String c_cid) {
		C_cid = c_cid;
	}


	public String getCustomername() {
		return Customername;
	}


	public String getCode() {
		return Code;
	}


	public void setCode(String code) {
		Code = code;
	}
	
	public void setCustomername(String customername) {
		Customername = customername;
	}


	public String getAddress() {
		return Address;
	}


	public void setAddress(String address) {
		Address = address;
	}


	public String getCity() {
		return City;
	}


	public void setCity(String city) {
		City = city;
	}


	public String getState() {
		return State;
	}


	public void setState(String state) {
		State = state;
	}


	public String getCountry() {
		return Country;
	}


	public void setCountry(String country) {
		Country = country;
	}


	public String getEmail() {
		return Email;
	}


	public void setEmail(String email) {
		Email = email;
	}


	public String getWebsite() {
		return Website;
	}


	public void setWebsite(String website) {
		Website = website;
	}


	public String getPhone() {
		return Phone;
	}


	public void setPhone(String phone) {
		Phone = phone;
	}


	public String getCell() {
		return cell;
	}


	public void setCell(String cell) {
		this.cell = cell;
	}


	public String getContactpersonname() {
		return Contactpersonname;
	}


	public void setContactpersonname(String contactpersonname) {
		Contactpersonname = contactpersonname;
	}


	public String getContactpersonEmail() {
		return ContactpersonEmail;
	}


	public void setContactpersonEmail(String contactpersonEmail) {
		ContactpersonEmail = contactpersonEmail;
	}


	public String getContactpersonPhone() {
		return ContactpersonPhone;
	}


	public void setContactpersonPhone(String contactpersonPhone) {
		ContactpersonPhone = contactpersonPhone;
	}


	public String getCustomerEditDate() {
		return CustomerEditDate;
	}


	public void setCustomerEditDate(String customerEditDate) {
		CustomerEditDate = customerEditDate;
	}


private String C_cid;
private String Customername;
private String Code;
private String Address;
private String City;
private String State;
private String Country;
private String Email;
private String Website;
private String Phone;
private String cell;
private String Contactpersonname;
private String ContactpersonEmail;
private String ContactpersonPhone;
private String CustomerEditDate;
	
public String getStartdate() {
		return startdate;
	}


	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}


	private String startdate;
	
	static VehicleDAO daoObject = new VehicleDAO();
	private List CustomerModel;

	public List getCustomerModel() {
		return CustomerModel;
	}


	public void setCustomerModel(List customerModel) {
		CustomerModel = customerModel;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	private Customer customer;

	private String returnResult;
//	private List VehicleModel;
	
	public String editCustomerDetails(){
		System.out.println("in get edit method");
		return "success";
	}
	
	
public String execute(){
	//setCustomerModel(Customerdetails());
	
		//User user = (User) session.getAttribute("user");
	System.out.println("===============excute method");
		return "success";
	}



public String Customerdetails(){

	try{
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
	
	String orgtypeid = (String) request.getSession().getAttribute(
			"orgtypeid");
	String orgchartid = (String) request.getSession().getAttribute(
			"orgchartid");
//	System.out.println("orgtypeid..........." + orgtypeid
//			+ "orgchartid................." + orgchartid);
//	String id = "!=0";
	String id="";
//	if (orgtypeid.equals("1")) {
//		id = "depot_id!=0";
//
//	}
//	if (orgtypeid.equals("3")) {
//
//		id = "depot_id in('" + orgchartid + "')";
//	}
//	if (orgtypeid.equals("2")) {
//
//		id = "depot_id in(select org_chart_id as depotids from org_chart where parent_id='"
//				+ orgchartid + "')";
//	}
	
	if(!orgtypeid.equals("0") && orgtypeid.equals("1")){
		
	}else if(!orgtypeid.equals("0") &&  orgtypeid.equals("2")){
		//id=" and ot.org_type_id="+orgtypeid;
		id = "and og.org_chart_id in(select org_chart_id as depotids from org_chart where parent_id='"
				+ orgchartid + "')";
		
	}
	else if(!orgtypeid.equals("0") &&  orgtypeid.equals("3")){
		id="and og.org_chart_id="+orgchartid;
		
	}
	
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
		
		AMOUNT = amount;
		SEARCH_TERM = request.getParameter("sSearch");
		DIR = dir;
		START = start;
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();
		//result = daoObject.getData(total, request,sCol,sdir,viewdeletedrecord);
		result = this.getdata(request,sCol,sdir,id);
		out.print(result);
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	return null;
}
public JSONObject getdata(HttpServletRequest request,String index, String dir, String orgchartid){

	//	int totalAfterFilter = total;
		JSONObject result = new JSONObject();
		Session session1 = HibernateUtil.getSession("");
		try{
			String sql;
		/*	String[] dbcol = {"","c_cid","c_cname","c_code","c_address","c_city","c_state","c_country","c_email","c_website",
			"c_phone","c_cell","c_contact_person_name","c_contact_person_email","c_contact_person_phone",
			"c_status"};
			
	      String col = dbcol[Integer.parseInt(index)];*/
			
			sql="select ifnull(c_cid,'') c_cid,ifnull(c_cname,'')c_cname,ifnull(c_code,'') c_code,ifnull(c_address,'')c_address," +
					"ifnull(c_city,'')c_city,ifnull(c_state,'')c_state,ifnull(c_country,'')c_country," +
					"ifnull(c_email,'')c_email,ifnull(c_website,'')c_website,ifnull(c_phone,'')c_phone," +
					"ifnull(c_cell,'')c_cell,ifnull(c_contact_person_name,'')c_contact_person_name," +
					"ifnull(c_contact_person_email,'')c_contact_person_email,"+
                 "ifnull(c_contact_person_phone,'')c_contact_person_phone,ifnull(c_status,'')c_status from customer where deleted_status=0 and c_status='Active'";
			
//			String search_term = request.getParameter("sSearch");
//			if (search_term != null && !search_term.equals("")) {
////				String search_term = request.getParameter("sSearch").trim();
//				search_term=search_term.trim();
//
//				sql += " OR c_cname like '%"+search_term+"%')";
//				sql += " OR c_phone like '%"+search_term+"%') ";
//			}
			//sql+=" group by ";
			
	/*		if(!col.equals("")){
				if(dir.equals("asc")){
				  sql += " order by "+col+" asc";
				}else{
					sql += " order by "+col+" desc";
				}
			}*/
		//	sql += " limit " + request.getParameter("iDisplayStart") + ", " + request.getParameter("iDisplayLength");
			
		  // System.out.println("query........."+sql);
		  	 Query query = session1.createSQLQuery(sql)
		  			.addScalar("c_cid", Hibernate.INTEGER)
						 .addScalar("c_cname", Hibernate.STRING)
						 .addScalar("c_code", Hibernate.STRING)
						 .addScalar("c_address", Hibernate.STRING)
						 .addScalar("c_city", Hibernate.STRING)
						 .addScalar("c_state", Hibernate.STRING)
		  	 			 .addScalar("c_country",Hibernate.STRING)
		  	 			 .addScalar("c_email",Hibernate.STRING)
		  	 			  .addScalar("c_website", Hibernate.STRING)
						 .addScalar("c_phone", Hibernate.STRING)
		  	 			 .addScalar("c_cell",Hibernate.STRING)
		  	 			 .addScalar("c_contact_person_name",Hibernate.STRING)
		  	 			  .addScalar("c_contact_person_email", Hibernate.STRING)
						 .addScalar("c_contact_person_phone", Hibernate.STRING)
						 .addScalar("c_status", Hibernate.STRING);
		  	 			
			    query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();	
				
		    	JSONArray array = new JSONArray();
				
				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					//int j=i+1;
					JSONArray ja = new JSONArray();
					ja.add("<input type='checkbox' class='group-checkable' id='"+ rs.get("c_cid")+ "' value='"+ rs.get("c_cid") + "'/>");
			
					ja.add(rs.get("c_cid"));
					ja.add(rs.get("c_cname").toString());
					ja.add(rs.get("c_code").toString());
					ja.add(rs.get("c_address").toString());
					ja.add(rs.get("c_city").toString());
					ja.add(rs.get("c_state").toString());
					ja.add(rs.get("c_country").toString());
					ja.add(rs.get("c_email").toString());
					ja.add(rs.get("c_website").toString());
					ja.add(rs.get("c_phone").toString());
					ja.add(rs.get("c_cell").toString());
					ja.add(rs.get("c_contact_person_name").toString());
					ja.add(rs.get("c_contact_person_email").toString());
					ja.add(rs.get("c_contact_person_phone").toString());
					ja.add(rs.get("c_status").toString());
				
//					System.out.println("data is"+ja);
					array.add(ja);
				}
				
				int cnt=0;
				//totalAfterFilter = aliasToValueMapList.size();
				///result.put("aaData", array);
				//String search_term2= request.getParameter("sSearch");
				
				result.put("aaData", array);

//				String search_term3= request.getParameter("sSearch").trim();
//		 cnt=getTotalcustomerRecords(request,col,dir,orgchartid);//getTotalRecordsForCount(search_term3);
		
			//result.put("iTotalRecords",cnt);
			
		//	result.put("iTotalDisplayRecords", cnt);
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (session1 != null) {
				session1.close();
			}
		}
		return result;
	
}


private int getTotalcustomerRecords(HttpServletRequest request, String col,
		String dir2, String orgchartid) {

	int cnt=0;
	//Session session = HibernateUtilVtu.getSession("");
	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
 
	try{
		String sql="";
		sql = "select c_cname,c_phone from customer where deleted_status=0 and c_status='Active'";
		
		
//		String search_term1 = request.getParameter("sSearch");
//		////System.out.println("search_term-------"+search_term1);
//		if (search_term1 != null && !search_term1.equals("")) {
//			String search_term = request.getParameter("sSearch").trim();
////			sql += " and (vehicle_id like '%"+search_term+"%'";
//			sql += " and (c_cname like '%"+search_term+"%'";
//			sql += " OR c_phone like '%"+search_term+"%')";
//			
//		}
		
//		if(!col.equals("")){
//			if(dir.equals("asc")){
//			  sql += " order by "+col+" asc";
//			}else{
//				sql += " order by "+col+" desc";
//			}
//		}
		
//		if(!col.equals("")){
//			 if(dir.equals("asc")){
//				if(col.equals("TOKEN")){
//					sql += " order by CAST("+col+" AS UNSIGNED) asc";
//				}else
//			  sql += " order by "+col+" asc";
//			}else{
//				if(col.equals("TOKEN")){
//					sql += " order by CAST("+col+" AS UNSIGNED) desc";
//				}
//				else
//				sql += " order by "+col+" desc";
//			}
//		}
		Query query = session.createSQLQuery(sql);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> aliasToValueMapList = query.list();
		cnt=aliasToValueMapList.size();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		
	}
	
	return cnt;


}
public String createRoadType()
{
	return "success";
}


public String Deletecustomer() {
	Session session = null;

	try{

	String status="";
	HttpServletRequest request = ServletActionContext.getRequest();
/*	AccessRightsDao  accessrightdao=new AccessRightsDao();
	AccessRights accessrights=new AccessRights();
	//int usrsessionid=(Integer)request.getSession().getAttribute("Id");
	accessrights=accessrightdao.accessRightsdetails(usrsessionid, "Customerdetails.action");
	String access=accessrights.getACCESS();
	String create=accessrights.getCREATE();
	String edit=accessrights.getEDIT();
	String delete=accessrights.getDELETE();*/
	String DeleteId = ServletActionContext.getRequest().getParameter("DeleteId");
	int did=Integer.parseInt(DeleteId);	//if(delete.equalsIgnoreCase("Y")){
	String Deleteid = null;
	 session = HibernateUtil.getSession("hibernate.cfg.xml");
	Customer cust=(Customer) session.get(Customer.class,did);
	cust.setDeletedStatus("1");
	int deid =(Integer) session.save(cust);
	session.beginTransaction().commit();
	System.out.println("deleted------"+deid);
	if(deid!=0){
		addActionMessage("DeleteId  "+DeleteId+" deleted successfully");
		returnResult = "success";
	}else{
		addActionMessage("DeleteId  "+DeleteId+" deleted fail");
		returnResult = "fail";
	}
/*	if(status.split(":")[0].equals("success")){
	
		addActionMessage("DeleteId"+Deleteid+" deleted successfully");
		returnResult = "success";
	}
	
	if(status.equals("")){
		
		addActionMessage("DeleteId"+Deleteid+" deleted successfully");
		returnResult = "success";
	} else {
		addActionError(status);
		returnResult = "fail";
	}
	return returnResult;
	}else{
		addActionMessage("Access Denied - User Does Not Have Access Privileges");
		
	}*/
	}catch (Exception e) {
		e.printStackTrace();
	}finally{session.close();}
	return "success";
}



public String savecustomerdetails(){
	Session session=null;
	Transaction transation;
	try{
		HttpServletRequest req = ServletActionContext.getRequest();
       String name=req.getParameter("customername");
       String code=req.getParameter("C_code");
       String address=req.getParameter("C_Address");
       String city=req.getParameter("C_city");
       String state=req.getParameter("C_state");
       String country=req.getParameter("C_Country");
       String email=req.getParameter("C_email");
       String website=req.getParameter("Website");
       String phone=req.getParameter("C_Phone");
       String cell=req.getParameter("C_cell");
       String contactpersonname=req.getParameter("Contact_person_name");
       String contactpersonemail=req.getParameter("Contact_person_Email");
       String ContactpersonPhone=req.getParameter("Contact_person_Phone");
      // String date=req.getParameter("startdate");
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String strDate = simpleDateFormat.format(now);
		//String actiondate=req.getParameter("dateaction");
		Common common = new Common();
		String date1=null;
		if(!startdate.equals("") ){
		 date1=common.getDateFromPicker(startdate);
		}
       String userid=req.getSession().getAttribute("userid").toString();
       Date d=new Date();
       
    session = HibernateUtil.getSession("hibernate.cfg.xml");
   String value = null;
   Customer cust=new Customer();
   cust.setName(name);
   cust.setCode(code);
   cust.setAddress(address);
   cust.setCity(city);
   cust.setState(state);
   cust.setCountry(country);
   cust.setEmail(email);
   cust.setWebsite(website);
   cust.setPhone(phone);
   cust.setCell(cell);
   cust.setContactPPersonName(contactpersonname);
   cust.setContactPersonEmail(contactpersonemail);
   cust.setContactPersonPhone(ContactpersonPhone);
   cust.setCreateDate(date1);
   cust.setCreatedBy(userid);
   cust.setStatus("Active");
   cust.setDeletedStatus("0");
   int sid=(Integer) session.save(cust);
   System.out.println("sid-----"+sid);
   session.beginTransaction().commit();
  // Customer id=(Customer) session.get(Customer.class,sid);
	if(sid !=0){
		addActionMessage("Insert successfully");
		returnResult = "success";
	}else{
		addActionMessage("Insert fail");
		returnResult = "fail";
	}
// org.hibernate.Transaction trasation= session.beginTransaction();
/*String query="insert into customer(c_cname,c_address,c_city,c_state,c_country,c_email,c_website,c_phone,c_cell,c_contact_person_name,c_contact_person_email,c_contact_person_phone,c_status,c_created_date,created_date,created_by,deleted_status)" +
		" values("+"'"+name+"','"+address+"','"+city+"','"+state+"','"+country+"','"+email+"','"+website+"','"+phone+"','"+
    ""+cell+"','"+contactpersonname+"','"+contactpersonemail+"','"+ContactpersonPhone+"','"+"Active"+"','"+date1+"','"+strDate+"','"+userid+"','"+"0'"+")";*/
	/*System.out.println(query);
	Query qry=session.createSQLQuery(query);
	qry.executeUpdate();*/
	}catch (Exception e) {
		e.printStackTrace();
		return "fail";
	}finally{
		if(session !=null){
		session.close();}
	}

	return "success";
}



public String editcustomerdetails(){

	Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	try{
	HttpServletRequest req = ServletActionContext.getRequest();
	HttpServletResponse res = ServletActionContext.getResponse();

	String C_cid = req.getParameter("value");
	System.out.println("Customer is================"+C_cid);
	
	String sql ="SELECT `c_cid`, `c_cname`, `c_code`, `c_address`, `c_city`, `c_state`, `c_country`, `c_website`, `c_email`, `c_phone`, `c_cell`, `c_contact_person_name`, `c_contact_person_email`, `c_contact_person_phone` "+
			"FROM `customer` "+
			"WHERE `c_cid` = '"+C_cid+"'";
		
	Query query = session.createSQLQuery(sql);
	query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	List<Map<String, Object>> aliasToValueMapList = query.list();
	Map<String, Object> rs = aliasToValueMapList.get(0);
	this.setC_cid(String.valueOf(rs.get("c_cid")));
	System.out.println("======================================"+C_cid);
	this.setCustomername((String)rs.get("c_cname"));
	this.setCustomername((String)rs.get("c_code"));
	this.setAddress((String)rs.get("c_address"));
	this.setCity((String)rs.get("c_city"));
	this.setState((String)rs.get("c_state"));
	this.setCountry((String)rs.get("c_country"));
	this.setEmail((String)rs.get("c_email"));
	this.setWebsite((String)rs.get("c_website"));
	this.setPhone((String)rs.get("c_phone"));
	this.setCell((String)rs.get("c_cell"));
	this.setContactpersonname((String)rs.get("c_contact_person_name"));
	this.setContactpersonEmail((String)rs.get("c_contact_person_email"));
	this.setContactpersonPhone((String)rs.get("c_contact_person_phone"));	
	//req.setAttribute("cname", C_cid);

	}catch (Exception e) {
		e.printStackTrace();
	}finally{session.close();}
	return "success";
}


public String Updatecustomer(){
	Session session=null;
	org.hibernate.Transaction transation;
	try{
		HttpServletRequest req = ServletActionContext.getRequest();
		String customeridd=req.getParameter("customeridd");
		int id=Integer.parseInt(customeridd);
		System.out.println("================"+customeridd);
       String name=req.getParameter("customername");
       String code=req.getParameter("code");
       String address=req.getParameter("C_Address");
       String city=req.getParameter("C_city");
       String state=req.getParameter("C_state");
       String country=req.getParameter("C_Country");
       String email=req.getParameter("C_email");
       String website=req.getParameter("Website");
       String phone=req.getParameter("C_Phone");
       String cell=req.getParameter("C_cell");
       String contactpersonname=req.getParameter("Contact_person_name");
       String contactpersonemail=req.getParameter("Contact_person_Email");
       String ContactpersonPhone=req.getParameter("Contact_person_Phone");
       String customerId=(String) req.getAttribute("cname");
      // String date=req.getParameter("startdate");
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String strDate = simpleDateFormat.format(now);
		//String actiondate=req.getParameter("dateaction");
		Common common = new Common();
		String date1=null;
		if(!startdate.equals("") ){
		 date1=common.getDateFromPicker(startdate);
		}
		//String date1=common.getDateFromPicker(startdate);
       //int userid=Integer.parseInt(req.getSession().getAttribute("userid").toString());
       Date d=new Date();
       
    session = HibernateUtil.getSession("hibernate.cfg.xml");
   // org.hibernate.Transaction trasation= session.beginTransaction();
/*String query="Update customer(c_address,c_city,c_state,c_country,c_email,c_website,c_phone,c_cell,c_contact_person_name,c_contact_person_email,c_contact_person_phone,c_created_date,updated_date,updated_by)" +
		" SET('"+address+"','"+city+"','"+state+"','"+country+"','"+email+"','"+website+"','"+phone+"','"+
    ""+cell+"','"+contactpersonname+"','"+contactpersonemail+"','"+ContactpersonPhone+"','"+date1+"','"+strDate+"','"+userid+"'"+") where C_cid='"+customeridd+"'";
	String query="update customer set c_address="+address+","+"c_city="+address+","+"c_city="+address+","+"c_city="+address+","+"c_city="+address+","+
    "c_city="+address+","+"c_city="+address+","+"c_city="+address+","+"c_city="+address+","+"c_city="+address+","+"c_city="+address+","+"c_city=";*/
   transation=session.beginTransaction();
    Customer customer= (Customer) session.get(Customer.class,id);
    
   customer.setCode(code);
   customer.setAddress(address);
   customer.setCity(city);
   customer.setState(state);
   customer.setCountry(country);
   customer.setEmail(email); 
   customer.setWebsite(website);
   customer.setPhone(ContactpersonPhone);
   customer.setCell(cell);
   customer.setContactPPersonName(contactpersonname);
   customer.setContactPersonEmail(contactpersonemail);
   customer.setContactPersonPhone(ContactpersonPhone);
   customer.setCreateDate(date1);
   session.persist(customer);
   transation.commit();
    //System.out.println(query);
	//Query qry=session.createSQLQuery(query);
	//qry.executeUpdate();
	}catch (Exception e) {
		e.printStackTrace();
		return "fail";
	}finally{
		if(session !=null){
		session.close();}
	}

	return "success";
}

}