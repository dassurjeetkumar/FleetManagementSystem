package com.trimax.its.vts.report.action;

import java.io.File;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;

//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.mime.MultipartEntity;
//import org.apache.http.entity.mime.content.InputStreamBody;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.params.BasicHttpParams;
//import org.apache.http.util.EntityUtils;

public class StudentInformationDetails extends ActionSupport{
	
	String path="";
	char ft = 15;
	String str="";
	public String startdate;
	public String getStartdate() {
		return startdate;
	}



	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}



	public String depotid;
	public String type;
	
	String c=" ";

	char fl = 18;
	char f2 = 12;
	int pagi = 1;

	int FULL_LEN = 120;
	int HALF_LEN = 60;
	
	String regionTypeAjaxString = "";
	private Map<String, String> sourcelist;
	public String source_no;
	private Map<String, String> destinationlist;
	private Map<String, String> vialist;
	private Map<String, String> changeoverlist;
	public String destination_no;
	public String via_no;
	public String changestop_no;
	public String sourcelists;
	public String destinationlists;
	public String vialists;
	public String changeoverlists;
	public String studentno;
	public String photo;
	public String student_adds;
	
	public String school_name;
	public String student_caste;
	public String gender;
	public String student_name;
	public String mobile_no;
	
	
	
	
	



	public String getMobile_no() {
		return mobile_no;
	}



	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}



	public String getSchool_name() {
		return school_name;
	}



	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}



	public String getStudent_caste() {
		return student_caste;
	}



	public void setStudent_caste(String student_caste) {
		this.student_caste = student_caste;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getStudent_name() {
		return student_name;
	}



	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}



	public String getStudent_adds() {
		return student_adds;
	}



	public void setStudent_adds(String student_adds) {
		this.student_adds = student_adds;
	}



	public String getPhoto() {
		return photo;
	}



	public void setPhoto(String photo) {
		this.photo = photo;
	}



	public String getStudentno() {
		return studentno;
	}



	public void setStudentno(String studentno) {
		this.studentno = studentno;
	}



	public String getSourcelists() {
		return sourcelists;
	}



	public void setSourcelists(String sourcelists) {
		this.sourcelists = sourcelists;
	}



	public String getDestinationlists() {
		return destinationlists;
	}



	public void setDestinationlists(String destinationlists) {
		this.destinationlists = destinationlists;
	}



	public String getVialists() {
		return vialists;
	}



	public void setVialists(String vialists) {
		this.vialists = vialists;
	}



	public String getChangeoverlists() {
		return changeoverlists;
	}



	public void setChangeoverlists(String changeoverlists) {
		this.changeoverlists = changeoverlists;
	}



	public Map<String, String> getDestinationlist() {
		return destinationlist;
	}



	public void setDestinationlist(Map<String, String> destinationlist) {
		this.destinationlist = destinationlist;
	}



	public Map<String, String> getVialist() {
		return vialist;
	}



	public void setVialist(Map<String, String> vialist) {
		this.vialist = vialist;
	}



	public Map<String, String> getChangeoverlist() {
		return changeoverlist;
	}



	public void setChangeoverlist(Map<String, String> changeoverlist) {
		this.changeoverlist = changeoverlist;
	}



	public String getDestination_no() {
		return destination_no;
	}



	public void setDestination_no(String destination_no) {
		this.destination_no = destination_no;
	}



	public String getVia_no() {
		return via_no;
	}



	public void setVia_no(String via_no) {
		this.via_no = via_no;
	}



	public String getChangestop_no() {
		return changestop_no;
	}



	public void setChangestop_no(String changestop_no) {
		this.changestop_no = changestop_no;
	}







	public Map<String, String> getSourcelist() {
		return sourcelist;
	}



	public void setSourcelist(Map<String, String> sourcelist) {
		this.sourcelist = sourcelist;
	}



	public String getSource_no() {
		return source_no;
	}



	public void setSource_no(String source_no) {
		this.source_no = source_no;
	}



public String execute(){
	  
//	  System.out.println("Enter into execute()");
	
	  
	  return "success";
  }

	
	 
	 public String getStudentDetailsReport()
	 {
				  
			try {
				HttpServletResponse response = ServletActionContext.getResponse();
				
				
				
				
				JSONObject result = new JSONObject();
			

				  response.setContentType("application/json");
	  			response.setHeader("Cache-Control", "no-store");
	  			PrintWriter out = response.getWriter();
	  			result = getStudentData();
	  			out.print(result);       // to print
	      		} catch (Exception e) {
	      			e.printStackTrace();
	      		}
			return null;
		}
	 
	 
	 
	 @SuppressWarnings("unchecked")
	public JSONObject getStudentData(){
 		JSONObject result = new JSONObject();
 		Session session1 = HibernateUtil.getSession("hibernate.cfg.xml");
 		try{
 			
 			System.out.println("in get student");
 			HttpServletRequest req = ServletActionContext.getRequest();
 			Common common = new Common();
			String date1=common.getDateFromPicker(startdate);
			 String sql="SELECT `student_roll_no`, `father_name`, `mother_name`, `sch_address`, ifnull(sch_pincode,'')sch_pincode,"
			 		+ "school_name, `standard`, `stu_caste_name`, `student_dob`, `student_gender`, "
			 		+ "`student_name`,student_address, `student_mobile`, ifnull(student_email,'')student_email, `source_stop`,"
			 		+ " `destination_stop`, IFNULL(via_stop,'NA') via_stop,IFNULL(via_stop2,'NA') via_stop2,"
			 		+ "`image_path` FROM `student_details` where inserted_date between '"+date1+" 00:00:00'  and '"+date1+" 23:59:59' ";
			 
			 Query query = session1.createSQLQuery(sql).addScalar("student_roll_no").addScalar("father_name").addScalar("mother_name").addScalar("sch_address").addScalar("sch_pincode").addScalar("school_name")
					 .addScalar("standard").addScalar("stu_caste_name").addScalar("student_dob").addScalar("student_gender").addScalar("student_name").addScalar("student_address").addScalar("student_mobile").addScalar("student_email").addScalar("source_stop")
					 .addScalar("destination_stop").addScalar("via_stop").addScalar("via_stop2").addScalar("image_path");
			  
			 query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String,Object>> aliasToValueMapList = query.list();
 			
				JSONArray array = new JSONArray();
				  for (int i = 0; i < aliasToValueMapList.size(); i++) {
					  JSONArray ja = new JSONArray();
					Map<String,Object> list = aliasToValueMapList.get(i);
					ja.add("<input type='checkbox' class='group-checkable' data-set='#sample_2 .checkboxes' id='"
							+ list.get("student_roll_no").toString()
							+ "' value='"
							+list.get("student_roll_no").toString()+","+list.get("source_stop").toString()+","+list.get("destination_stop").toString()+","+list.get("via_stop").toString()+","+list.get("via_stop2").toString()+"'/>");
					ja.add(i+1);
					ja.add(list.get("student_roll_no").toString());
					ja.add(list.get("student_name").toString());
					ja.add(list.get("father_name").toString());
					ja.add(list.get("mother_name").toString());
					ja.add(list.get("sch_address").toString());
					ja.add(list.get("sch_pincode").toString());
					ja.add(list.get("school_name").toString());
					ja.add(list.get("standard").toString());
					ja.add(list.get("student_gender").toString());
					ja.add("<img src='http://223.30.102.19/pass/"+list.get("image_path").toString()+"' width='100' height='100'>"+"");

					ja.add(list.get("stu_caste_name").toString());
					ja.add(list.get("student_dob").toString());
//					ja.add(list.get("student_gender").toString());
//					ja.add("<img src='http://223.30.102.19/pass/"+list.get("image_path").toString()+"' width='100' height='100'>"+"");

					ja.add(list.get("student_address").toString());
					ja.add(list.get("student_mobile").toString());
					ja.add(list.get("student_email").toString());
					ja.add(list.get("source_stop").toString());
					ja.add(list.get("destination_stop").toString());
					ja.add(list.get("via_stop").toString());
					ja.add(list.get("via_stop2").toString());
					//ja.add("<img src='http://223.30.102.19/pass/"+list.get("image_path").toString()+"' width='100' height='100'>"+"");
// 				System.out.println("now end");
 			    array.add(ja);
 			}
 			result.put("aaData", array);
 		} catch (Exception ex) {
 			ex.printStackTrace();
 		} finally {
 			
 			if(session1 !=null){
 				session1.close();
 			}
 		}
 		return result;
 	}
	 
	 
	 public String editStudentDetails(){
		 HttpServletRequest request = ServletActionContext.getRequest();
		 String valu_via="";
		 String valu_via2="";
		 
		 try{
			 String value=request.getParameter("value");
			 System.out.println("Value=="+value);
			 
			 String values1=getStudentAllData(value);
			 System.out.println("value=="+values1);
			 String values[]=values1.split("@");
			 setStudentno(values[0]);
			 setSource_no(values[1]);
			 setDestination_no(values[2]);
			 System.out.println("via=="+values.length);
//			 System.out.println("via2=="+values[4]);
			 try{
			 if(values[3].equalsIgnoreCase("") || values[3].equalsIgnoreCase(null)){
				 valu_via="NA";
			 }else{
				 valu_via=values[3];
			 }
			 }catch(Exception e){
				 valu_via="NA";
			 }
			 try{
			 if(values[4].equalsIgnoreCase("") || values[4].equalsIgnoreCase(null)){
				 valu_via2="NA";
			 }else{
				 valu_via2=values[4];
			 }
			 }catch (Exception e) {
				// TODO: handle exception
				 valu_via2="NA"; 
			}
			 setVia_no(valu_via);
			 setChangestop_no(valu_via2);
			 setStudent_adds(values[5]);
			 setSchool_name(values[6]);
			 setStudent_caste(values[7]);
			 setGender(values[8]);
			 setStudent_name(values[9]);
			 setMobile_no(values[10]);
			 sourcelist = getSourcelistData();
			 destinationlist = getSourcelistData();
			 vialist = getSourcelistData();
			 changeoverlist = getSourcelistData();
			 
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 
		 return "success";
	 }
	
	 @SuppressWarnings("deprecation")
	public String saveStudentDetails(){
		 HttpServletRequest request = ServletActionContext.getRequest();
		 Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		 String sql="";
		 try{
			 String student_no=getStudentno();
			 String sources=getSourcelists();
//			 Part path=request.getPart(getPhoto());
//			 String path=getPhoto();
			 String destinations=getDestinationlists();
			 String vias=getVialists();
			 String via2s=getChangeoverlists();
			 String source[]=sources.split("@");
			 String destination[]=destinations.split("@");
			 String via[]=vias.split("@");
			 String via2[]=via2s.split("@");
			 String address=getStudent_adds();
			 String School_name=getSchool_name();
			 String student_caste=getStudent_caste();
			 String gender=getGender();
			 String Student_name=getStudent_name();
			 String mobile_no=getMobile_no();
			 
			 System.out.println("School_name==="+School_name);
			
//			 System.out.println("real==="+path.getName());
//			 System.out.println("real1==="+path.getContentType());
//			 System.out.println("Source==="+sources);
//			 System.out.println("student_no==="+student_no);
			 if(address.equalsIgnoreCase("NA")){
			 //sql="update student_details  set source_group_id="+source[0]+",source_stop='"+source[1]+"',destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"',via_stop_group_id="+via[0]+",via_stop='"+via[1]+"',via_stop2_group_id="+via2[0]+",via_stop2='"+via2[1]+"' where student_roll_no='"+student_no+"' ";
			 
			 if(source[0].equalsIgnoreCase("NA") && destination[0].equalsIgnoreCase("NA")){
			 
				 if(via[0].equalsIgnoreCase("NA") && via2[0].equalsIgnoreCase("NA")){
		    		 //sql="update student_details  set destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"' where student_roll_no='"+student_no+"' ";
		    	 }else{
		    		 
		    		 if(via[0].equalsIgnoreCase("NA") || via2[0].equalsIgnoreCase("NA")){
		    		 
		    		 if(via[0].equalsIgnoreCase("NA")){
			    		 sql="update student_details  set via_stop2_group_id="+via2[0]+",via_stop2='"+via2[1]+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";

		    		 }else{
			    		 sql="update student_details  set via_stop_group_id="+via[0]+",via_stop='"+via[1]+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";

		    			 
		    		 }
		    		 }else {
		    			 
		    			 sql="update student_details  set via_stop_group_id="+via[0]+",via_stop='"+via[1]+"',via_stop2_group_id="+via2[0]+",via_stop2='"+via2[1]+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";

		    			 
		    		 }
		    		 
		    	 }
			 
			 
			 
			 } else if(source[0].equalsIgnoreCase("NA") || destination[0].equalsIgnoreCase("NA")){
				 
			     if(source[0].equalsIgnoreCase("NA")){
			    	 if(via[0].equalsIgnoreCase("NA") && via2[0].equalsIgnoreCase("NA")){
			    		 sql="update student_details  set destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";
			    	 }else{
			    		 
			    		 if(via[0].equalsIgnoreCase("NA") || via2[0].equalsIgnoreCase("NA")){
			    		 
			    		 if(via[0].equalsIgnoreCase("NA")){
				    		 sql="update student_details  set destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"',via_stop2_group_id="+via2[0]+",via_stop2='"+via2[1]+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";

			    		 }else{
				    		 sql="update student_details  set destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"',via_stop_group_id="+via[0]+",via_stop='"+via[1]+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";

			    			 
			    		 }
			    		 }else {
			    			 
			    			 sql="update student_details  set destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"',via_stop_group_id="+via[0]+",via_stop='"+via[1]+"',via_stop2_group_id="+via2[0]+",via_stop2='"+via2[1]+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";

			    			 
			    		 }
			    		 
			    	 }
			    	 
			    	 
			     }else{
			    	 
			    	 if(via[0].equalsIgnoreCase("NA") && via2[0].equalsIgnoreCase("NA")){
			    		 sql="update student_details  set source_group_id="+source[0]+",source_stop='"+source[1]+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";
			    	 }else{
			    		 
			    		 if(via[0].equalsIgnoreCase("NA")){
				    		 sql="update student_details  set destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"',via_stop2_group_id="+via2[0]+",via_stop2='"+via2[1]+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";

			    		 }else{
				    		 sql="update student_details  set destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"',via_stop_group_id="+via[0]+",via_stop='"+via[1]+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";

			    			 
			    		 }
			    		 
			    	 }
			    	 
			    	 
			    	 
			    	 
			    	 
			    	 
			     }
			 
			 
			 
			 
			 }else {
				 
				 if(via[0].equalsIgnoreCase("NA") && via2[0].equalsIgnoreCase("NA")){
					 sql="update student_details  set source_group_id="+source[0]+",source_stop='"+source[1]+"',destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";
		    	 }else{
		    		 
		    		 if(via[0].equalsIgnoreCase("NA") || via2[0].equalsIgnoreCase("NA")){
		    		 
		    		 if(via[0].equalsIgnoreCase("NA")){
			    		 sql="update student_details  set source_group_id="+source[0]+",source_stop='"+source[1]+"',destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"',via_stop2_group_id="+via2[0]+",via_stop2='"+via2[1]+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";

		    		 }else{
			    		 sql="update student_details  set source_group_id="+source[0]+",source_stop='"+source[1]+"',destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"',via_stop_group_id="+via[0]+",via_stop='"+via[1]+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";

		    			 
		    		 }
		    		 }else {
		    			 
		    			 sql="update student_details  set source_group_id="+source[0]+",source_stop='"+source[1]+"',destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"',via_stop_group_id="+via[0]+",via_stop='"+via[1]+"',via_stop2_group_id="+via2[0]+",via_stop2='"+via2[1]+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";

		    			 
		    		 }
		    		 
		    	 }
				 
				 
				 
				 
				 
			 }
			 
			 }else {
				 // sql="update student_details  set source_group_id="+source[0]+",source_stop='"+source[1]+"',destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"',via_stop_group_id="+via[0]+",via_stop='"+via[1]+"',via_stop2_group_id="+via2[0]+",via_stop2='"+via2[1]+"',student_address='"+address+"' where student_roll_no='"+student_no+"' ";
				
				 if(source[0].equalsIgnoreCase("NA") && destination[0].equalsIgnoreCase("NA")){
					 
					 if(via[0].equalsIgnoreCase("NA") && via2[0].equalsIgnoreCase("NA")){
			    		 //sql="update student_details  set destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"' where student_roll_no='"+student_no+"' ";
			    	 }else{
			    		 
			    		 if(via[0].equalsIgnoreCase("NA") || via2[0].equalsIgnoreCase("NA")){
			    		 
			    		 if(via[0].equalsIgnoreCase("NA")){
				    		 sql="update student_details  set via_stop2_group_id="+via2[0]+",via_stop2='"+via2[1]+"',student_address='"+address+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";

			    		 }else{
				    		 sql="update student_details  set via_stop_group_id="+via[0]+",via_stop='"+via[1]+"',student_address='"+address+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";

			    			 
			    		 }
			    		 }else {
			    			 
			    			 sql="update student_details  set via_stop_group_id="+via[0]+",via_stop='"+via[1]+"',via_stop2_group_id="+via2[0]+",via_stop2='"+via2[1]+"',student_address='"+address+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";

			    			 
			    		 }
			    		 
			    	 }
				 
				 
				 
				 } else if(source[0].equalsIgnoreCase("NA") || destination[0].equalsIgnoreCase("NA")){
					 
				     if(source[0].equalsIgnoreCase("NA")){
				    	 if(via[0].equalsIgnoreCase("NA") && via2[0].equalsIgnoreCase("NA")){
				    		 sql="update student_details  set destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"',student_address='"+address+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";
				    	 }else{
				    		 
				    		 if(via[0].equalsIgnoreCase("NA") || via2[0].equalsIgnoreCase("NA")){
				    		 
				    		 if(via[0].equalsIgnoreCase("NA")){
					    		 sql="update student_details  set destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"',via_stop2_group_id="+via2[0]+",via_stop2='"+via2[1]+"',student_address='"+address+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";

				    		 }else{
					    		 sql="update student_details  set destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"',via_stop_group_id="+via[0]+",via_stop='"+via[1]+"',student_address='"+address+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";

				    			 
				    		 }
				    		 }else {
				    			 
				    			 sql="update student_details  set destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"',via_stop_group_id="+via[0]+",via_stop='"+via[1]+"',via_stop2_group_id="+via2[0]+",via_stop2='"+via2[1]+"',student_address='"+address+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";

				    			 
				    		 }
				    		 
				    	 }
				    	 
				    	 
				     }else{
				    	 
				    	 if(via[0].equalsIgnoreCase("NA") && via2[0].equalsIgnoreCase("NA")){
				    		 sql="update student_details  set source_group_id="+source[0]+",source_stop='"+source[1]+"',student_address='"+address+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";
				    	 }else{
				    		 
				    		 if(via[0].equalsIgnoreCase("NA")){
					    		 sql="update student_details  set destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"',via_stop2_group_id="+via2[0]+",via_stop2='"+via2[1]+"',student_address='"+address+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";

				    		 }else{
					    		 sql="update student_details  set destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"',via_stop_group_id="+via[0]+",via_stop='"+via[1]+"',student_address='"+address+"',school_name='"+School_name+"',stu_caste_name='"+student_caste+"',student_gender='"+gender+"',student_name='"+Student_name+"',student_mobile='"+mobile_no+"',mgov_status='1' where student_roll_no='"+student_no+"' ";

				    			 
				    		 }
				    		 
				    	 }
				    	 
				    	 
				    	 
				    	 
				    	 
				    	 
				     }
				 
				 
				 
				 
				 } else {
					 
					 if(via[0].equalsIgnoreCase("NA") && via2[0].equalsIgnoreCase("NA")){
						 sql="update student_details  set source_group_id="+source[0]+",source_stop='"+source[1]+"',destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"',student_address='"+address+"' where student_roll_no='"+student_no+"' ";
			    	 }else{
			    		 
			    		 if(via[0].equalsIgnoreCase("NA") || via2[0].equalsIgnoreCase("NA")){
			    		 
			    		 if(via[0].equalsIgnoreCase("NA")){
				    		 sql="update student_details  set source_group_id="+source[0]+",source_stop='"+source[1]+"',destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"',via_stop2_group_id="+via2[0]+",via_stop2='"+via2[1]+"' ,student_address='"+address+"' where student_roll_no='"+student_no+"' ";

			    		 }else{
				    		 sql="update student_details  set source_group_id="+source[0]+",source_stop='"+source[1]+"',destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"',via_stop_group_id="+via[0]+",via_stop='"+via[1]+"',student_address='"+address+"' where student_roll_no='"+student_no+"' ";

			    			 
			    		 }
			    		 }else {
			    			 
			    			 sql="update student_details  set source_group_id="+source[0]+",source_stop='"+source[1]+"',destination_group_id="+destination[0]+",destination_stop='"+destination[1]+"',via_stop_group_id="+via[0]+",via_stop='"+via[1]+"',via_stop2_group_id="+via2[0]+",via_stop2='"+via2[1]+"',student_address='"+address+"' where student_roll_no='"+student_no+"' ";

			    			 
			    		 }
			    		 
			    	 }
					 
					 
					 
					 
					 
				 }
				 
				 
				  
			 }
			Query query = HibernateUtil.getSession("").createSQLQuery(sql);
			int count=query.executeUpdate();
			if(count>0){
				addActionMessage("Student Details updated successfully");
			}else{
				addActionError("Student Details Not updated successfully");
			}
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 
		 return "success";
	 }
	 
	 
	 public Map<String, String> getSourcelistData() {
			Map<String, String> resultMap = new LinkedHashMap<String, String>();
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			try {
				String sql="SELECT group_id bus_stop_id,group_name bus_stop_name FROM `bus_stop_group` where `group_type_id` = '1' AND `deleted_status` = '0' AND `status` = 'ACTIVE'";
				
//			Query query = session
//					.createQuery("select org_chart_id,org_name from org_chart  where org_type_id="+orgtypeid+" and "+id+" and deleted_status=0 order by org_name");
			Query query = HibernateUtil.getSession("").createSQLQuery(sql).addScalar("bus_stop_id",Hibernate.STRING)
					.addScalar("bus_stop_name", Hibernate.STRING);;
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<Integer, String>> list = query.list();
				for (int i = 0; i < list.size(); i++) {
//					resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
					resultMap.put("NA"+"@"+"NA", "NA");
					resultMap.put(list.get(i).get("bus_stop_id")+"@"+list.get(i).get("bus_stop_name"), list.get(i).get("bus_stop_name"));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				HibernateUtil.closeSession();
			}
			return resultMap;
		}
	 
	 
	 public String getStudentAllData(String student_id) {
			String resultMap = "";
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			try {
				String sql="SELECT student_roll_no, `source_stop`, `destination_stop`,`via_stop`, `via_stop2`, `student_address`,school_name,stu_caste_name,student_gender,student_name,student_mobile FROM `student_details` WHERE `student_roll_no` = '"+student_id+"'";
				
//			Query query = session
//					.createQuery("select org_chart_id,org_name from org_chart  where org_type_id="+orgtypeid+" and "+id+" and deleted_status=0 order by org_name");
			Query query = HibernateUtil.getSession("").createSQLQuery(sql).addScalar("student_roll_no",Hibernate.STRING).addScalar("source_stop",Hibernate.STRING)
					.addScalar("destination_stop", Hibernate.STRING).addScalar("via_stop", Hibernate.STRING).addScalar("via_stop2", Hibernate.STRING)
					.addScalar("student_address", Hibernate.STRING).addScalar("school_name", Hibernate.STRING)
					.addScalar("stu_caste_name", Hibernate.STRING).addScalar("student_gender", Hibernate.STRING).addScalar("student_name", Hibernate.STRING).addScalar("student_mobile", Hibernate.STRING);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<Integer, String>> list = query.list();
				for (int i = 0; i < list.size(); i++) {
//					resultMap.put(org.getOrg_chart_id(), org.getOrg_name());
					
					resultMap=list.get(i).get("student_roll_no")+"@"+list.get(i).get("source_stop")+" @"+list.get(i).get("destination_stop")+" @"+ list.get(i).get("via_stop")+" @"+ list.get(i).get("via_stop2")+" @"+ list.get(i).get("student_address")
							+" @"+ list.get(i).get("school_name")+"@"+ list.get(i).get("stu_caste_name")+"@"+ list.get(i).get("student_gender")+"@"+ list.get(i).get("student_name")+"@"+ list.get(i).get("student_mobile");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				HibernateUtil.closeSession();
			}
			return resultMap;
		}
	 
	 
	 
	 

	 public String StudentDetails(){
		 
		 System.out.println("Enter view");
		 
		 
		 try{
//			 String value=request.getParameter("value");
//			 System.out.println("Value=="+value);
//			 String values[]=value.split(",");
//			 setStudentno(values[0]);
//			 setSource_no(values[1]);
//			 setDestination_no(values[2]);
//			 System.out.println("via=="+values[3]);
//			 System.out.println("via2=="+values[4]);
			
			 
			 
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 
		 return "success";
	 }
}
