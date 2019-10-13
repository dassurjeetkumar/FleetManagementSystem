	package com.trimax.its.report.action;

	import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
	import java.text.SimpleDateFormat;
    import java.util.ArrayList;
import java.util.Calendar;
	import java.util.Date;
	import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import org.apache.struts2.ServletActionContext;
	import org.hibernate.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
    import org.hibernate.transform.Transformers;
	
import com.trimax.its.common.Common;
    import com.trimax.its.transport.model.Schedule;
    import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

	import org.hibernate.Hibernate;
	import org.hibernate.Session;
import org.hibernate.Transaction;


	public class TripWiseRevenueReport {
		
		String str="";
		String path="";
		char ft = 15;
	
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
		public String divisionlist1;
	    public String getDivisionlist1() {
			return divisionlist1;
		}


		public void setDivisionlist1(String divisionlist1) {
			this.divisionlist1 = divisionlist1;
		}


		private Map<Integer, String> divisionlist;
	  


	public Map<Integer, String> getDivisionlist() {
			return divisionlist;
		}


		public void setDivisionlist(Map<Integer, String> divisionlist) {
			this.divisionlist = divisionlist;
		}

		
	double totalAmmount=0;
	int subtotalValues=0;

	StringBuffer regionTypeAjaxString = new StringBuffer();
	
		public String execute() {
			VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
			return "success";
		}

		public String getDepotlist1() {
			return depotlist1;
		}

		public void setDepotlist1(String depotlist1) {
			this.depotlist1 = depotlist1;
		}

		
		public String getTripRevenueData() throws SQLException
		{
		            Connection connection = null;
		            Statement stmt1 = null;
		            ResultSet rs1 = null;
		            Transaction transaction=null;
		            Session session1=null;
					try {
					Date  ss1=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
				    String runDateTime = sdf.format(ss1);
					session1 = null;
					String queryyy;
					Common common = new Common();
					String realpath = ServletActionContext.getRequest()
							.getRealPath("/");
//					System.out.print("depotNo is---"+depotlist1);
					session1 = HibernateUtil.getSession("hibernate.cfg.xml");
//					String sql="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"' AND division_id = '"+divisionlist1+"'";
					//String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depotlist1+"' AND division_id = '"+divisionlist1+"'";
					//String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"' AND division_id = '"+divisionlist1+"'";
					 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depotlist1+"'";
//						String depotdb=common.getDBResultStr(session1, sql1, "depotname");
						Query qry=session1.createSQLQuery(sql1).
								addScalar("depotname")
								.addScalar("central_ip",Hibernate.STRING)
								.addScalar("central_uname")
								.addScalar("central_pwd");
						
						qry.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
							List<Map<String, Object>> aliasToValueMapList = qry.list();	
							String depotdb="";
							String depotIp="";
							String user="";
							String password="";
							for (int j = 0; j < aliasToValueMapList.size(); j++) {
								Map<String, Object> aliasValue = aliasToValueMapList.get(j);
								 depotdb=aliasValue.get("depotname").toString();
								 depotIp=aliasValue.get("central_ip").toString();
								 user=aliasValue.get("central_uname").toString();
								 password=aliasValue.get("central_pwd").toString();
							}
						 Class.forName("com.mysql.jdbc.Driver");
						 connection = DriverManager.getConnection("jdbc:mysql://"+depotIp+":23306/" + depotdb + "?zeroDateTimeBehavior=convertToNull&autoReconnect=true",user,password);
						 System.out.println("connection........."+connection);
					 stmt1 = connection.createStatement();
					 transaction = session1.beginTransaction();

			 SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			 Date startDate = format.parse(startdate);
			 Date endDateO = format.parse(enddate);
			 SimpleDateFormat fomat2 = new SimpleDateFormat("yyyy-MM-dd");
			 String startDate1 = fomat2.format(startDate).toString();
			 String endDate1 = fomat2.format(endDateO).toString();
			 int scheduleId=Integer.parseInt(ctoken);
			 
			if(scheduleId!=0) {
							
				/*queryyy = "SELECT generated_Date,wd.waybill_No,sum(etm_passenger_amount) totalAmt,wtd.trip_number trip_number,wd.`schedule_No`, " +
				    	" shift_type_name FROM `Waybill_Details` wd inner join Waybill_Trip_Details wtd on wd.waybill_No=wtd.waybill_No " +
						" inner join shift_type st on st.shift_type_id=wtd.shift_type_id " +
						"  WHERE `generated_Date` between  '"+startDate1+"' and '"+endDate1+"' AND  wd.`schedule_No` = '"+scheduleId+"'  " +
						" group by generated_Date,wtd.shift_type_id,wtd.trip_number ";*/
				
				queryyy="SELECT DATE_FORMAT(generated_Date,'%d-%m-%y') generated_Date,wd.waybill_No,sum(etm_passenger_amount) totalAmt,wd.`schedule_No`, st.shift_type_id, " +
						" shift_type_name ,wtd.trip_number FROM `Waybill_Details` wd inner join Waybill_Trip_Details wtd  on wd.waybill_No=wtd.waybill_No" +
						" inner join shift_type st  on st.shift_type_id=wtd.shift_type_id   WHERE `generated_Date` between  " +
						" '"+startDate1+"' and '"+endDate1+"' AND   wd.`schedule_No` = '"+scheduleId+"'  " +
						"  group by generated_Date,wd.waybill_No,wtd.shift_type_id,wtd.trip_number";
			}
			else {
	            queryyy = "";
	        }
			 rs1 = stmt1.executeQuery(queryyy);
//			 System.out.println("queryy is --"+queryyy);	
	
							String filePath = "Report/";

							String fileName = "TripWiseRevenueReport.txt";
					   
					 regionTypeAjaxString.append("<div id='header' style='display: none;'><div align='center'><h4>TripWise Revenue Report</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></br></div>");

				        regionTypeAjaxString.append("<div align='right'><b> Date:-</b>"+runDateTime+"</div></div>");
				        
				        String nwkr=""    +   "                                   Trip Wise Revenue Report    " +
			      		
				        		"\n                                    From Date:"+ startdate+"  End Date:"+ enddate+"                                              ";
						regionTypeAjaxString.append("<div class='component'><table class='overflow-y' border='1'>");
						regionTypeAjaxString.append("<thead><tr><th>Generated Date</th><th>Trip No.</th><th>Shift Name</th><th>Revenue</th>"+"</tr></thead><tbody>");
						

						    
						    String headingprint =" "+"_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n" 
						    		+add("Generated Date",3)+"|"+add("Trip No",3)+"|"+add("Shift Name",7)+"|"+add("Revenue",7)+
								 " "+ "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _  _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n";
							
							String path = realpath + filePath + fileName;
					        str+=ft+nwkr+add(headingprint,5);
						  
                            HttpServletResponse response = ServletActionContext.getResponse();
                            int i=1;
                            double total=0.0;
                            double rev=0.0;
                            while (rs1.next()) {
                            	
                            	int tripNo=rs1.getInt("trip_number");
                            	
                            	 rev=rs1.getDouble("totalAmt");
                            	
                            	 regionTypeAjaxString.append("<tr>");
                            	 regionTypeAjaxString.append("<td>"+rs1.getString("generated_Date")+"</td>");
           						 regionTypeAjaxString.append("<td>"+rs1.getInt("trip_number")+"</td>");
           						 regionTypeAjaxString.append("<td>"+rs1.getString("shift_type_name")+"</td>");
           						 regionTypeAjaxString.append("<td>"+rev+"</td>");
           						regionTypeAjaxString.append("</tr>");
           						
                            }
                            total +=rev;
       					regionTypeAjaxString.append("<tr><td colspan='3'><center><b>Total</b></center></td><td align='center'><b>"+ total+"</td></tr>" +"\n");

       				
       					
					PrintWriter out;
				
						out = response.getWriter();
						out.print(regionTypeAjaxString);
					} catch (Exception e) {
						
						e.printStackTrace();
					}finally{
						// connection.close();

			            if (rs1 != null) {
			            	rs1.close();
			            }
			            
			            if (stmt1 != null) {
			            	stmt1.close();
			            }

			            if (connection != null) {
			                connection.close();
			            }
			            if(session1 !=null){
			            	session1.close();
			            }
					}

					return null;
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
		
		static String addMoney(String str1, int a1) {
	        StringBuilder sb = new StringBuilder(str1);
	        StringBuilder sb2 = new StringBuilder();

	        //String sb1 =
	        int m1 = str1.length();
	        if (m1 >= a1) {
	            str1.substring(0, a1 - 1);
	            return str1;
	        }
	        a1 = a1 - m1;
	        for (int i = 0; i < a1; i++) {
	            sb2.append(" ");
	        }
	        sb2.append(sb);
	        String sb1 = sb2.toString();
	        return sb1;
	    }
		
		
		public String getDepotWiseSchdNo() {
			// get Depot List..
			HttpServletRequest req = ServletActionContext.getRequest();
			VtsDataDAO dao = VtsDataDAO.getInstance();
			int parentId = Integer.parseInt(req.getParameter("val"));
			//String date = req.getParameter("selectedDate");
//			Common cm = new Common();
//			String formattedgivendate = cm.getDateFromPicker(date);
//			System.out.println("formattedgivendate" + formattedgivendate);
			List<String> l2 = getDepotWiseScheduleName(parentId);
			List<String> l1 =getDepotWiseScheduleNameFormFourId(parentId);
			String regionTypeAjaxString = "<option value='0'>----Select----</option>";

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
		
		public List getDepotWiseScheduleName(int parentID)
		{
			
			List list = new ArrayList();
			String qry = "SELECT schedule_number, form_four_id  FROM  schedule s left join form_four f " +
					"on s.schedule_id=f.schedule_number_id where f.current_status ='ACTIVE'  and f.deleted_Status=0 and depot_id='"+parentID+"' ";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("schedule_number").toString());
					
				}
			}
			HibernateUtil.closeSession();
			return list;
		}
		
		
		public List getDepotWiseScheduleNameFormFourId(int parentID)
		{
			
			List list = new ArrayList();
			String qry = "SELECT schedule_number, form_four_id  FROM  schedule s left join form_four f " +
					"on s.schedule_id=f.schedule_number_id where f.current_status ='ACTIVE'  and f.deleted_Status=0 and depot_id='"+parentID+"' ";
			Query query = HibernateUtil.getSession("").createSQLQuery(qry);
			query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
			List<Map<String, Object>> aliasToValueMapList = query.list();
			if (aliasToValueMapList.size() > 0) {

				for (int i = 0; i < aliasToValueMapList.size(); i++) {
					Map<String, Object> rs = aliasToValueMapList.get(i);
					list.add(rs.get("form_four_id").toString());
				}
			}
			HibernateUtil.closeSession();
			return list;
		}
		
		
		
		
			 }





	
	
	

