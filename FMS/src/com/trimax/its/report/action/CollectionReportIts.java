package com.trimax.its.report.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.trimax.its.common.Common;
import com.trimax.its.transport.model.ShiftType;
import com.trimax.its.util.HibernateUtil;
import com.trimax.its.vts.dao.VtsDataDAO;

public class CollectionReportIts {

	
	 String path = "";
	    char ft = 15;
	    String str = "";
	    String str1 = "";
	   
	    public String startdate;
	    public String enddate;
	    String c = " ";
	    char fl = 18;
	    char f2 = 12;
	    int pagi = 1;
	    DecimalFormat df1 = new DecimalFormat("0.00");
	    String new_line = System.getProperty("line.separator");
	    int FULL_LEN = 120;
	    int HALF_LEN = 60;

	    private Map<Integer, String> divisionlist;
 		private Map<Integer, String> depotlist;
	    private String depotlist1;
 		private String divisionlist1;
 		 List<Map<String, String>> getpasslist;
 	    
 	    public List<Map<String, String>> getGetpasslist() {
 			return getpasslist;
 		}

 		public void setGetpasslist(List<Map<String, String>> getpasslist) {
 			this.getpasslist = getpasslist;
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


		public String getDepotlist1() {
			return depotlist1;
		}


		public void setDepotlist1(String depotlist1) {
			this.depotlist1 = depotlist1;
		}


		public String getDivisionlist1() {
			return divisionlist1;
		}


		public void setDivisionlist1(String divisionlist1) {
			this.divisionlist1 = divisionlist1;
		}
	    public String getEnddate() {
	        return enddate;//Do
	    }

	    public String getStartdate() {
	        return startdate;
	    }

	    public void setStartdate(String startdate) {
	        this.startdate = startdate;
	    }

	    public void setEnddate(String enddate) {
	        this.enddate = enddate;
	    }

	    public String execute() {
	    	VtsDataDAO vvt = VtsDataDAO.getInstance();
			divisionlist = vvt.getDivisionName();
	        return "success";
	    }

	   
	    public String CollectionDetailsActionIts() throws SQLException, ClassNotFoundException {
	    	Common common = new Common();
	       // CollectionReportDAO dao = new CollectionReportDAO();
	    	
	    	
	    	Connection connection=null;
			Statement stmt=null;
			Session session1 = null;
			ResultSet rs=null;
			Transaction transaction  = null;
			HttpServletRequest req=ServletActionContext.getRequest();
			String date1=common.getDateFromPicker(startdate);
			 String enddate = req.getParameter("enddate");
			String date2=common.getDateFromPicker(enddate);
		    String division1= divisionlist1;
		    String depot1= depotlist1;
		   
	        String queryyy;
	        String queryyy1;
	        Date ss1 = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
	        String runDateTime = sdf.format(ss1);
	        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	   
	        String regionTypeAjaxString = "";
	       
	        session1 = HibernateUtil.getSession("hibernate.cfg.xml");
			 transaction = session1.beginTransaction();
			 String realpath = ServletActionContext.getRequest()
						.getRealPath("/");
		//	String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+depot+"'";
			 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+depot1+"'";
//				String depotdb=common.getDBResultStr(session1, sql1, "depotname");
				Query qry1=session1.createSQLQuery(sql1).
						addScalar("depotname")
						.addScalar("central_ip",Hibernate.STRING)
						.addScalar("central_uname")
						.addScalar("central_pwd");
				
				qry1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
					List<Map<String, Object>> aliasToValueMapList = qry1.list();	
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
			 stmt = connection.createStatement();
	        
	       //for sundry reciept purpose
	        String sql3 = "select IFNULL(Reason_For_Reciept,'') Reason_For_Reciept,Receipt_voucher_no, IFNULL(Employee_name,'') empname,IFNULL(Party_name,'') partyname,IFNULL(Ammount_received_number,'0.0') amounts from sundry_receipt where inserted_date BETWEEN '" + date1 + " 00:00:00' and '" + date2 + " 23:59:59'";
	      //  Query query3 = session1.createSQLQuery(sql3);
	       // ResultSet rs1 = stmt.executeQuery(sql3);
	        double totalsundryrcptamount=0.0;
	        String sundryrcptnameparty="";
	        String sundryrcptename ="";
	        String sundryrcptpname ="";
	        double sundryrcptamount =0.0;
	        int sundryrcptvoucher=0;
	        String reason = "";
	       // query3.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	       // List<Map<String, Object>> aliasToValueMapList4 = query3.list();
	        //end of sundry reciept
	        
	        //for sundry payment purpose
	        String sql4 = "select IFNULL(Reason_For_Payment,'') Reason_For_Payment,Receipt_voucher_no, IFNULL(Employee_name,'') empname,IFNULL(Party_name,'') partyname,IFNULL(Ammount_received_number,'0.0') amounts from sundry_payments where inserted_date BETWEEN '" + date1 + " 00:00:00' and '" + date2 + " 23:59:59'";
	       // Query query4 = session1.createSQLQuery(sql4);
	        //ResultSet rs2 = stmt.executeQuery(sql4);
	        double totalsundrypmtamount=0.0;
	        String sundrypmtnameparty="";
	        String sundrypmtename ="";
	        String sundrypmtpname ="";
	        double sundrypmtamount =0.0;
	        int sundrypmtvoucher=0;
	        String pmtreason = "";
	       // query4.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	        //List<Map<String, Object>> aliasToValueMapList5 = query4.list();
	        
	        //end of sundry payemnt
	        
	        
				String name1="";
				
				String operated="0";String cashcollection="0";String notoperated="0";String dueforaudit="0";String oaudit="0";String totalsced="0";
				String possale="0";
				String datePreviousDate11="";String datePreviousDate12="";
				try{
					int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;
				     
				      java.text.SimpleDateFormat dateFormat =
				        	new java.text.SimpleDateFormat("yyyy-MM-dd");
				      java.util.Date dateSelectedFrom = null;
				      java.util.Date datePreviousDate = null;
				      java.util.Date dateSelectedFrom1 = null;
				      java.util.Date datePreviousDate1 = null;

				      try
				      {
					  dateSelectedFrom = dateFormat.parse(date1);
					  dateSelectedFrom1 = dateFormat.parse(date2);
					  
				      }
				      catch(Exception e)
				      {
					  e.printStackTrace();
				      }
				      String previousDate =
				      dateFormat.format(dateSelectedFrom.getTime() - MILLIS_IN_DAY);
				      String previousDate1 =
						      dateFormat.format(dateSelectedFrom1.getTime() - MILLIS_IN_DAY);

				      
				      //get the previous date in java.util.Date.
				      try
				      {
					  datePreviousDate = dateFormat.parse(previousDate);
					   datePreviousDate11=dateFormat.format(datePreviousDate);
				      datePreviousDate1 = dateFormat.parse(previousDate1);
					   datePreviousDate12=dateFormat.format(datePreviousDate1);
				      }
				      catch(Exception e)
				      {
					  e.printStackTrace();
				      }
				      String dosch="SELECT count(*) as count FROM `schedule` WHERE `status` IN ('new','active') AND `current_status` IN ('APPROVED','CASE WORKER') AND `deleted_status` = '0' AND `schedule_type` = '3'";
				    // String totals = common.getDBResultStr(session1, dosch, "count");
				      ResultSet rs3 = stmt.executeQuery(dosch);
				      String totals="";
				      while (rs3.next())
				    	   totals = rs3.getString("count");
				      String totalsc="SELECT count(*) as count FROM `schedule` WHERE `status` IN ('new','active') AND `current_status` IN ('APPROVED','CASE WORKER') AND `deleted_status` = '0' ";
				      //totalsced = common.getDBResultStr(session1, totalsc, "count");
				      ResultSet rs4 = stmt.executeQuery(totalsc);  
				      while (rs4.next())
				    	  totalsced = rs4.getString("count");
				      totalsced=String.valueOf(Integer.parseInt(totalsced)+Integer.parseInt(totals));
			String operatedquery="select (sum(count1)+sum(count2)) as audited from(select count(*) as count1,0 as count2 from Waybill_Details " +
					"where   (( status IN ('AUDITED','closed')  AND ETIM_Coll_Amt+Bag_Coll_Amt!=0) OR (status = 'ONLINE')) and (generated_Date between '"+ date1 +"' and '"+ date2 +"')" +
							" and Schedult_Type!=2 union all " +
					"select 0 as count1,count(*) as count2 from Waybill_Details where  (( status IN ('AUDITED','closed')  AND ETIM_Coll_Amt+Bag_Coll_Amt!=0) OR (status = 'ONLINE')) and " +
					"(generated_Date between '"+ datePreviousDate11 +"' and '"+ datePreviousDate12 +"') and Schedult_Type=2)a";
//			 operated = common.getDBResultStr(session1, operatedquery, "audited");
			 ResultSet rs5 = stmt.executeQuery(operatedquery);  
			 while (rs5.next())
				 operated = rs5.getString("audited");
			 String cashcollectionquery="select count(*) as closed from Waybill_Details " +
						"where   Status ='closed' and (Ticket_Audited_Date BETWEEN ('"+date1+" 00:00:00') AND ('"+date2+" 23:59:59')) " +
								"AND ETIM_Coll_Amt+Bag_Coll_Amt!=0";
			// cashcollection = common.getDBResultStr(session1, cashcollectionquery, "closed");
			 ResultSet rs6 = stmt.executeQuery(cashcollectionquery);  
			 while (rs6.next())
				 cashcollection = rs6.getString("closed");
			 notoperated=String.valueOf(Integer.parseInt(totalsced)-Integer.parseInt(operated));
			String dueforauditquery="select (sum(count1)+sum(count2)) as dueforaudi from(select count(*) as count1,0 as count2 from Waybill_Details " +
								"where  Status in('online') and (generated_Date between '"+date1+"' and '"+date2+"') " +
										"and Schedult_Type!=2 union all " +
								"select 0 as count1,count(*) as count2 from Waybill_Details where  Status in('online') and " +
								"(generated_Date between '"+ datePreviousDate11 +"' and '"+ datePreviousDate12 +"') and Schedult_Type=2)a";
		//	dueforaudit = common.getDBResultStr(session1, dueforauditquery, "dueforaudi");
			 ResultSet rs7 = stmt.executeQuery(dueforauditquery);
			while (rs7.next())
				dueforaudit = rs7.getString("dueforaudi");
			
			String oauditquery="select count(*) as oaudited from Waybill_Details " +
						"where  Status IN ('closed','AUDITED') and (Ticket_Audited_Date BETWEEN ('"+date1+" 00:00:00') AND ('"+date2+" 23:59:59')) " +
								"AND ETIM_Coll_Amt+Bag_Coll_Amt=0";
			//oaudit = common.getDBResultStr(session1, oauditquery, "oaudited");
			ResultSet rs8 = stmt.executeQuery(oauditquery);
			while (rs8.next())
				oaudit = rs8.getString("oaudited");
			
			String posquery="SELECT sum(amount) amt FROM Waybill_Details wd  " +
					"INNER JOIN waybill_account_details wad ON wad.waybill_id=wd.waybil_Id " +
					"INNER JOIN account_head ah ON wad.account_head_id=ah.account_head_id  and account_head_number='411814' " +
					"where  wd.Ticket_Audited_Date BETWEEN '"+date1+" 00:00:00' and '"+date2+" 23:59:59' and wd.Status IN (UPPER('Closed'),'AUDITED')";

			//possale = common.getDBResultStr(session1, posquery, "amt");
			ResultSet rs9 = stmt.executeQuery(posquery);
			while (rs9.next())
				possale = rs9.getString("amt");
			if(possale==null || possale.equalsIgnoreCase("")){
				possale="0.0";
			}
			System.out.println("possale..........."+possale);
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				
				setGetpasslist(getpassdata(depot1,date1,date2,session1));
				 try {
	        String sql = "SELECT ifnull(wpc.status,'') wpcstatus,wd.Status,wd.waybil_Id,ifnull(block.manualpassenger,0) manualpassenger,ifnull(block.manualdailypass,0) manualdailypass," +
	        		"ifnull(block.manualdailypasssisty,0) manualdailypasssisty,ifnull(block.manualdailypassseventy,0) manualdailypassseventy,"
	                + " ifnull(block.manualmonthlypass,0) manualmonthlypass,ifnull(block.manualluggage,0) manualluggage,IFNULL(c.amount,'0')amount,"
	                + "  round(etmpassenger,2) etmpassenger,0 as etmdailypass,0 as etmmonthlypass, round(etmluggage,2) etmluggage,sum(if(t.ticket_type_short_code=2 && t.payment_mode=1,t.luggage_total_amount-t.service_tax_amt,0)) etmcardluggage,"
	                + " s.schedule_number,ifnull(STYPE.shift_type_name,sch_type.schedule_type_name) AS shift_type_name,ifnull(ttbag.bag_code,0) AS Bag_No,e.EMPLOYEE_NAME,IF(e.WORKING_DESIGNATION = '16',CONCAT(IFNULL(e.TOKEN,''),'DC'),e.TOKEN) TOKEN,"
	                + " wd.waybill_No,ifnull(wd.ETIM_Coll_Amt,0) passenger, tollamnt,round(wd.Excess_Amt,1) Excess_Amt,round(wd.Shortage_Amt,1) Shortage_Amt,"
	                + " wd.Excess_Amt,(wd.cond_bata_amount+wd.driver_bata_amount) incbata,round((wd.cond_incentive_amount+wd.driver_incentive_amount),2) incentive,"
	                + "  round((wd.cond_dailypass_amount+wd.driver_dailypass_amount),2) dailypass,(wd.cond_monthpass_amount+wd.driver_monthpass_amount) monthpass,"
	                + " (wd.cond_alltrip_amount+wd.driver_alltrip_amount) alltrip,(wd.cond_other_deduction+wd.driver_other_deduction) othded,"
	                + " round((ifnull(wd.conductor_Total_Bata,0.0)+ifnull(wd.driver_Total_Bata,0.0)+ifnull(wd.cond_other_deduction,0.0)),2) totalinc,wd.ETIM_Coll_Amt,round(wd.Cash_Deposited,2) Cash_Deposited," +
	                "round((wd.etm_service_tax_amt+wd.bag_service_tax_amt+wd.pass_service_tax_amt),2) service_tax_amt,round(IFNULL(wd.etm_card_amt,0),2) etm_card_amt," +
	                "ifnull(wd.etm_service_tax_amt,0.0) esertax,sum(ifnull(t.service_tax_amt,0)) etmcardservice,ifnull(wd.bag_service_tax_amt,0.0) bsertax,ifnull(wd.pass_service_tax_amt,0.0) psertax, " +
	                " wd.Ticket_Audited_Date,mum.userloginname FROM Waybill_Details wd "
	                + " LEFT JOIN  "
	                + " (SELECT a1.waybill_cwa_block_master_id, sum(if(ticket_type_manual_id IN (1) and is_service_tax='N', value, 0)) AS manualpassenger,"
	                + " sum(if(ticket_type_manual_id=2 and is_service_tax='N', value, 0)) AS manualdailypass," +
	                "sum(if(ticket_type_manual_id=2 and denomination_type_manual_id=19 and is_service_tax='N', value, 0)) AS manualdailypasssisty," +
	                "sum(if(ticket_type_manual_id=2 and denomination_type_manual_id=8 and is_service_tax='N', value, 0)) AS manualdailypassseventy," +
	                "sum(if(ticket_type_manual_id =3, value, 0)) AS manualmonthlypass,"
	                + " sum(if(ticket_type_manual_id IN (4) and is_service_tax='N', value, 0)) AS manualluggage   FROM waybill_cwa_block_master a1"
	                + " INNER JOIN Waybill_cwa_receipt_details a2 ON a1.waybill_cwa_block_master_id = a2.waybill_cwa_block_master_id"
	                + " GROUP BY a1.waybill_cwa_block_master_id) block ON block.waybill_cwa_block_master_id = wd.param2 "
	                + " INNER JOIN form_four ff ON ff.form_four_id=wd.schedule_No "
	                +" LEFT JOIN (SELECT wd1.waybil_Id,sum(amount) amount FROM Waybill_Details wd1  " +
	                "INNER JOIN waybill_account_details wad ON wad.waybill_id=wd1.waybil_Id " +
	                "INNER JOIN account_head ah ON wad.account_head_id=ah.account_head_id  and account_head_number='411814' " +
	                "where  wd1.Ticket_Audited_Date BETWEEN '" + date1 + " 00:00:00' and '" + date2 + " 23:59:59'  " +
	                "and wd1.Status IN (UPPER('Closed'),'AUDITED') group by wd1.waybil_Id) c ON c.waybil_Id=wd.waybil_Id "
	                + " INNER JOIN schedule s ON s.schedule_id=ff.schedule_number_id "
	                + " LEFT JOIN ticket t ON t.waybil_Id=wd.waybil_Id AND t.waybill_NO = wd.waybill_No AND t.ticket_printed_flag='Y' "
	                + " AND t.fare_type Not IN ('NINC') AND t.payment_mode='1' "
	                + " LEFT JOIN ticket_type tt ON tt.ticket_type_id=t.ticket_type_short_code  AND tt.ticket_type_name NOT IN ('Line Checking', 'Penalty Ticket', 'Trip Status') "
	                +"LEFT JOIN (SELECT wd1.waybil_Id,sum(ifnull(t.px_total_amount,0)) etmpassenger,sum(if(t.ticket_type_short_code=2,t.luggage_total_amount-t.service_tax_amt,0)) etmluggage,sum(ifnull(t.toll_amount,0)) AS tollamnt " +
	                "FROM Waybill_Details wd1 LEFT JOIN ticket t ON t.waybil_Id=wd1.waybil_Id AND t.waybill_NO = wd1.waybill_No " +
	                "AND t.ticket_printed_flag='Y'  AND t.fare_type Not IN ('NINC') AND t.payment_mode='0' " +
	                "LEFT JOIN ticket_type tt ON tt.ticket_type_id=t.ticket_type_short_code  AND tt.ticket_type_name NOT IN " +
	                "('Line Checking', 'Penalty Ticket', 'Trip Status') where  wd1.Ticket_Audited_Date BETWEEN '" + date1 + " 00:00:00' " +
	                "and '" + date2 + " 23:59:59'  and wd1.Status IN (UPPER('Closed'),'AUDITED') group by wd1.waybil_Id) d ON d.waybil_Id=wd.waybil_Id"
	                + " LEFT JOIN employee e ON e.EMPLOYEE_ID=wd.conductor_Id " +
	                " LEFT JOIN menu_user_master mum ON mum.user_id=wd.Audited_By "
	                + " Left join waybill_pending_collection wpc on wpc.waybill_no=wd.waybill_No and collection_amount>0 and wpc.status='PENDING'  "
	                + " INNER JOIN schedule_type sch_type ON sch_type.schedule_type_id=s.schedule_type "
	                + " LEFT JOIN shift_type STYPE ON STYPE.shift_type_id=wd.Shift_Type "
	                + " LEFT JOIN ticketbag_master ttbag ON wd.Bag_No=ttbag.ticketbag_id where "
	                + " wd.Ticket_Audited_Date BETWEEN '" + date1 + " 00:00:00' and '" + date2 + " 23:59:59' and wd.Status IN (UPPER('Closed'),'AUDITED') GROUP BY s.schedule_number,"
	                 + " e.EMPLOYEE_ID,wd.waybill_No order by CAST(ttbag.bag_code as UNSIGNED)";
	        
	        
	        
	        //System.out.println("1111query"+sql);	
	       
	       //Query query = session1.createSQLQuery(sql);
	       // query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	      // List<Map<String, Object>> aliasToValueMapList = query.list();
	        ResultSet rs10 = stmt.executeQuery(sql);
			
	            if (aliasToValueMapList.size() > 0) {
	                int tpassenger = 0;
	                int tollamnt = 0;
	                int tdailyPass = 0;//tpass45
	                int tpassmonth = 0;
	                int tpassmanual = 0;//tpass50
	                int tmanualluggage = 0;
	                double etmpassenger = 0.0;
	                int etmdailypass = 0;
	                int etmmonthlypass = 0;
	                double tetmcoll = 0;
	                double tluggagecoll = 0.00;
	                double texcess = 0;
	                int ttotals = 0;
	                int ttotal = 0;
	                double tincentive =  0.00;
	                int texp = 0;
	                int tamtremitt = 0;
	                double tamtremitt2 = 0.00;
	                double tcashdepsite = 0;
	                double tshortage = 0;
	                double totalincetivecd = 0.00;
	                double totalbatacd = 0.00;
	                double bak_remittance_amt = 0;
	                int tbata = 0;
	                int tdailypass = 0;
	                int tmonthpass = 0;
	                int talltrip = 0;
	                double ttotalinc = 0.00;
	                double etmTotalRevenues = 0.0;
	                double manualTotalRevenues = 0.00;
	                double totalGrossAmts = 0.0;
	                double dailypassAmts = 0.0;
	                double totalincAmts = 0;
	                double totalsertyaxAmts = 0.0;
	                double sertyaxAmts = 0.0;
	                double esertyaxAmts = 0.0;
	                double esertyaxcardAmts = 0.0;
	                double esertyaxticketAmts = 0.0;
	                double bsertyaxAmts = 0.0;
	                double psertyaxAmts = 0.0;
	                double etmcard = 0.0;
	                double possum = 0.0;
	                double cardcoll = 0.0;
	                double amountToBeRemittedAmts = 0;
	                int sistyfivepass = 0;
	                int seventypass = 0;
	                int totalsistyfivepass = 0;
	                int totalseventypass = 0;
	               
	                regionTypeAjaxString += " <div id='header' style='display: none;'><div align='center'><h4> </br> </br>Daily Revenue Collection Statement</br>Date:- " + startdate + " to " + enddate + "</h4></div>";

	                regionTypeAjaxString += "<div align='right'><b>Date:-</b><b>" + runDateTime + "</b></div></div>";
	               
	                regionTypeAjaxString += "<div class='component'><table border='1' class='overflow-y'>";
	                regionTypeAjaxString += "<thead><tr><th style='font-size:15px;'>SI.No.</th><th style='font-size:15px;'>Sch No.</th><th style='font-size:15px;'>Duty Type</th>" +
	                		"<th style='font-size:15px;'>Bag No.</th><th style='font-size:15px;'>Employee Name</th>"
	                        + " <th style='font-size:15px;'>Token No.</th><th style='font-size:15px;'>Way Bill No.</th><th style='font-size:15px;'> Passenger<br>Fare ETM</th>" +
	                        "<th style='font-size:15px;'> Passenger<br>Fare Manual</th>" +
//	                        "<th style='font-size:15px;'> Passenger<br>Fare ePurse</th>" +
//	                        "<th style='font-size:15px;'> Daily Pass<br>Revenue ETM</th>"
	                        " <th style='font-size:15px;'> Daily Pass<br>Revenue<br>Manual</th>" + " <th style='font-size:15px;'> Luggage<br>Revenue<br>ETM</th>" +
	                        		" <th style='font-size:15px;'> Luggage<br>Revenue<br>Manual</th>"
	                        + " <th style='font-size:15px;'> Monthly<br>Pass ETM</th><th style='font-size:15px;'> Monthly<br>Pass Manual</th>" 
	                        		+ " <th style='font-size:15px;'> Total<br>Revenue<br>ETM</th><th style='font-size:15px;'> Total<br>Revenue<br>Manual</th>"
	                        + " <th style='font-size:15px;'>Total (Gross)</th>" + "<th style='font-size:15px;'>Batta</th>" + "<th style='font-size:15px;'>Incentive</th>" + "<th style='font-size:15px;'>Daily<br>Pass Inc.</th>"
	                        + " <th style='font-size:15px;'>All<br>Trip Inc.</th>" + "<th style='font-size:15px;'>Monthly<br>Pass Inc.</th>" + "<th style='font-size:15px;'>Misc. Exp</th>" + "<th style='font-size:15px;'>Total<br>Deduction</th><th style='font-size:15px;'>Service Tax</th><th style='font-size:15px;'>Top up</th>" + " <th style='font-size:15px;'>Amt to<br>be Remitted</th>"+" <th style='font-size:15px;'>POS</th>"+" <th style='font-size:15px;'>Card Coll(inclu ST)</th>"
	                        + " <th style='font-size:15px;'>Amt Rmitted</th>" + " <th style='font-size:15px;'>Shortage/Excess</th>"+" <th style='font-size:15px;'>Audited By</th>"+ "<th style='font-size:15px;'>Audited Date</th>" + "" + " </tr></thead><tbody>";
    
	              
	                int counter=0;
	                String str5="";
	                int i=1;
	                while (rs10.next()){
	    			
	                    int finalbt = 0;
	                    int finalinc = 0;
	                    
	                    //regionTypeAjaxString +="font-size:25px";
	                   // Map<String, Object> list = aliasToValueMapList.get(i);
	                    JSONArray ja = new JSONArray();
	                   
	                    double etmpassengerAmt1 = Double.parseDouble(rs10.getString("passenger"));
	                   // int etmpassengerAmt = (int)etmpassengerAmt1;
	                    double etmdailypassAmt1 = Double.parseDouble(rs10.getString("etmdailypass"));
	                    //int etmdailypassAmt = (int)etmdailypassAmt1;
	                    double etmmonthlypassAmt1 = Double.parseDouble(rs10.getString("etmmonthlypass"));
	                   // int etmmonthlypassAmt = (int)etmmonthlypassAmt1;
	                    double tetmcollAmt1 = Double.parseDouble(rs10.getString("etmluggage"));
	                   // int tetmcollAmt = (int)tetmcollAmt1;
	                    
	                    double etmTotalRevenue1 = etmpassengerAmt1 + etmdailypassAmt1 + etmmonthlypassAmt1 + Double.parseDouble(rs10.getString("etm_card_amt"));  //// test
	                    //etmTotalRevenue1=Math.rint(etmTotalRevenue1*100/100);
	                    //  int etmTotalRevenue = (int)etmTotalRevenue1;
	                    String etmTotalRevenue1display=df1.format(etmTotalRevenue1);
	                    etmTotalRevenues = etmTotalRevenues + etmTotalRevenue1;
	                    double tpassengerAmt1 = Double.parseDouble(rs10.getString("manualpassenger"));
	                    double tpassengerAmt = (double)tpassengerAmt1;
	                    double tdailyPassAmt1 = Double.parseDouble(rs10.getString("manualdailypass"));
	                    double tdailyPassAmt = (double)tdailyPassAmt1;
	                    double tmanualluggageAmt1 = Double.parseDouble(rs10.getString("manualluggage"));
	                    double tmanualluggageAmt = (double)tmanualluggageAmt1;
	                    double tpassmonthAmt1 = Double.parseDouble(rs10.getString("manualmonthlypass"));
	                    int tpassmonthAmt = (int)tpassmonthAmt1;
	                    double manualTotalRevenue = tpassengerAmt + tdailyPassAmt + tmanualluggageAmt + tpassmonthAmt;
	                    manualTotalRevenues = manualTotalRevenues + manualTotalRevenue;
	                    int ecard1=(int) (Math.rint(Double.parseDouble(rs10.getString("etm_card_amt"))*100)/100);
	                    double amount=(double) (Math.rint(Double.parseDouble(rs10.getString("amount"))*100)/100);
	                    double totalGross = etmTotalRevenue1 + manualTotalRevenue;
	                    double totalGross1 = etmTotalRevenue1 + manualTotalRevenue+amount;
	                    totalGrossAmts = totalGrossAmts + totalGross;
	                    if(totalGross1==0){
	                    
	                    }else{
	                    regionTypeAjaxString += "<tr>";
	                    regionTypeAjaxString += "<td >" + (++counter) + "</td>";
	                    regionTypeAjaxString += "<td>" + rs10.getString("schedule_number") + "</td>";
	                    regionTypeAjaxString += "<td>" + rs10.getString("shift_type_name") + "</td>";
	                    String str2 = rs10.getString("shift_type_name");
	                    if (str2.length() > 11)
	                        str2 = str2.substring(0, 9) + "";
	                    //String str = "Pradeep Kumar";
//	                    char s = str2.charAt(0);
//	                    for(int k = 0; k<str2.length(); i++){
//	                    	if(str2.charAt(k)==(' ')){
//	                    		s += str.charAt(i+1);
//	                    	}
//	                    }
	                    
	                    
//	            	
	            		String shortName = "" + str2.charAt(0);
	            		for(int k = 0; k<str2.length(); k++){
	            			if(("" + str2.charAt(k)).equals(" ")){
	            				shortName += "" + str2.charAt(k+1);
	            			}
	            		}

	            		//System.out.println("Sortname is : " + shortName);

	            		String waybill_No = rs10.getString("waybill_No");
	                    waybill_No= waybill_No.substring(waybill_No.length()-5);
	                    String bagNo = rs10.getString("Bag_No") != null ? rs10.getString("Bag_No") : "";
	                    regionTypeAjaxString += "<td>" + bagNo + "</td>";
	                    if(rs10.getString("EMPLOYEE_NAME")==null){
	                    	regionTypeAjaxString += "<td>" + "" + "</td>";
	                    }else{
	                    str5 = rs10.getString("EMPLOYEE_NAME").toString();
	                    if (str5.length() > 10){
	                    	str5 = str5.substring(0, 10) + "";
	                    }
	                    regionTypeAjaxString += "<td>" + str5 + "</td>";
	                    }
	                    String TOKEN = "";
	                    if(rs10.getString("TOKEN")==null){
	                    	regionTypeAjaxString += "<td>" + "" + "</td>";
	                    }else{
	                    	  TOKEN = rs10.getString("TOKEN").toString();
	                    regionTypeAjaxString += "<td>" + rs10.getString("TOKEN").toString() + "</td>";
	                    }
	                    regionTypeAjaxString += "<td>" + rs10.getString("waybill_No").toString() + "</td>";
	                    System.out.println("decimal value : " + rs10.getString("passenger").toString());
	                    System.out.println("decimal value in decimal : " + Double.parseDouble(rs10.getString("passenger").toString()));
	                    double etmluggagerev= Double.parseDouble(rs10.getString("etmluggage").toString()) + Double.parseDouble(rs10.getString("etmcardluggage").toString());
	                    tetmcoll+=etmluggagerev;
	                    double etmpassenger3= Double.parseDouble(rs10.getString("passenger").toString())+Double.parseDouble(rs10.getString("etm_card_amt").toString())-etmluggagerev;
	                    System.out.println("decimal value etmpassenger3 : " + etmpassenger3);
	                    
	                    //etmpassenger3=Math.rint((etmpassenger3*100)/100);
	                    DecimalFormat decimalFormatObj = new DecimalFormat("0.00");
	                    String formattedVal = decimalFormatObj.format(etmpassenger3);
	                    //etmpassenger3 = (Double)decimalFormatObj.parse(formattedVal);
	                    System.out.println("decimal value etmpassenger3 after conversion : " + formattedVal);
	                    
	                    regionTypeAjaxString += "<td >" + formattedVal + "</td>";
	                    etmpassenger += etmpassenger3;
	                    regionTypeAjaxString += "<td>" + rs10.getString("manualpassenger").toString() + "</td>";
	                    tpassenger += Integer.parseInt(rs10.getString("manualpassenger").toString());
	                    int ecard=(int) (Math.rint(Double.parseDouble(rs10.getString("etm_card_amt").toString())*100)/100);
//	                    regionTypeAjaxString += "<td>" +ecard+ "</td>";
//	                    regionTypeAjaxString += "<td >" + list.get("etmdailypass").toString() + "</td>";
	                    etmdailypass += Integer.parseInt(rs10.getString("etmdailypass").toString());
	                    regionTypeAjaxString += "<td >" + rs10.getString("manualdailypass").toString() + "</td>";
	                    tdailyPass += Integer.parseInt(rs10.getString("manualdailypass").toString());
	                    regionTypeAjaxString += "<td >" + etmluggagerev + "</td>";
	                    //tetmcoll += (int)Double.parseDouble(list.get("etmluggage").toString());
	                    //regionTypeAjaxString +="<td >"+list.get("manual").toString()+"</td>";
	                    regionTypeAjaxString += "<td >" + rs10.getString("manualluggage").toString() + "</td>";
	                    //tpassmanual+=Integer.parseInt(list.get("manual").toString());
	                    tmanualluggage += Integer.parseInt(rs10.getString("manualluggage").toString());
	                    regionTypeAjaxString += "<td >" + rs10.getString("etmmonthlypass").toString() + "</td>";
	                    etmmonthlypass += Integer.parseInt(rs10.getString("etmmonthlypass").toString());
	                    regionTypeAjaxString += "<td >" + rs10.getString("manualmonthlypass").toString() + "</td>";
	                    //tpass70+=Integer.parseInt(list.get("pass70").toString());
	                    tpassmonth += Integer.parseInt(rs10.getString("manualmonthlypass").toString());
	                    regionTypeAjaxString += "<td >" + etmTotalRevenue1display + "</td>";
	                    regionTypeAjaxString += "<td >" + manualTotalRevenue + "</td>";
	                    regionTypeAjaxString += "<td >" + totalGross + "</td>";
	                    double totalRemit = 0;
	                    double totals = 0;
	                    int passTot = Integer.parseInt(rs10.getString("manualpassenger").toString());
	                    int pass40Tot = Integer.parseInt(rs10.getString("manualdailypass").toString()) + Integer.parseInt(rs10.getString("manualmonthlypass").toString());
	                    double etimCollTot = Double.parseDouble(rs10.getString("ETIM_Coll_Amt").toString());
	                   // int etimCollTot =(int)etimCollTot1;
	                    double luggTot = Double.parseDouble(rs10.getString("etmluggage").toString());
	                    //int luggTot =(int)luggTot1;
	                    double manualluggageamnt1 = Double.parseDouble(rs10.getString("manualluggage").toString());
	                    int manualluggageamnt =(int)manualluggageamnt1;
	                    double excessTot1 = Double.parseDouble(rs10.getString("Excess_Amt").toString());
	                    int excessTot =(int)excessTot1;
	                    totals = etimCollTot + pass40Tot + manualluggageamnt;
	                    double totalinc1 = Double.parseDouble(rs10.getString("totalinc").toString());
	                    
	                    //System.out.println("totalinc1........"+totalinc1);
	                    //int totalinc =(int)totalinc1;
	                    totalRemit = totals - totalinc1;
	                    ttotals += totals;
	                    String schedule_number = rs10.getString("schedule_number").toString().trim();
	                    if(schedule_number.equalsIgnoreCase("Bengaluru Darshini")){
	                    	System.out.println("schedule_number==");
	                    	schedule_number="BDarshini";
	                    }
	                    String shift_typea = rs10.getString("shift_type_name").toString();
	                    String bagNo1 = rs10.getString("Bag_No") != null ? rs10.getString("Bag_No").toString() : "";
//	                    String EMPLOYEE_NAME = list.get("EMPLOYEE_NAME").toString();
//	                    //System.out.println("EMPLOYEE_NAME"+EMPLOYEE_NAME);
//	                    String str1 = EMPLOYEE_NAME;
//	                    if (str1.length() > 9)
//	                        str1 = str1.substring(0, 10) + "";
//	                   // System.out.println("EMPLOYEE_NAME1"+str1);
//	                    String TOKEN = list.get("TOKEN").toString();
	                    
	                    String passenger = rs10.getString("passenger").toString();
	                    
//	                    System.out.println("passenger ..."+passenger);
//	                    System.out.println("passenger ..." +list.get("etmpassenger").toString());
//	                    System.out.println("manualdailypass ..."+list.get("manualdailypass").toString());
//	                    System.out.println("monthlypass ..."+list.get("manualmonthlypass").toString());
	                    String ETIM_Coll_Amt = rs10.getString("ETIM_Coll_Amt").toString();
	                    String luggage = rs10.getString("etmluggage").toString();
	                    String Excess_Amt = rs10.getString("Excess_Amt").toString();
	                   
	                    Double.parseDouble(rs10.getString("incbata").toString());
	                    Double.parseDouble(rs10.getString("othded").toString());
	                    
	                   double incbata1= Double.parseDouble(rs10.getString("incbata").toString());
	                   int incbatab =(int)incbata1;
	                   double incentive1= Double.parseDouble(rs10.getString("incentive").toString());
	                  //System.out.println("incentive1---"+incentive1);
	                  // int incentive =(int)incentive1;
	                   
//	                    if (incbatab > incentive) {
//	                        finalbt = incbatab;
//	                    } else {
//	                        finalinc = incentive;
//	                    }
	                    
	                    
	                   double dailypasstest1=(Double.parseDouble(rs10.getString("dailypass").toString()));
	                  // int dailypasstest =(int)dailypasstest1;
	                   
	                   double alltriptest1=Double.parseDouble(rs10.getString("alltrip").toString());
	                   int alltriptest =(int)alltriptest1;
	                   double monthpasstest1=Double.parseDouble(rs10.getString("monthpass").toString());
	                   int monthpasstest =(int)monthpasstest1;
	                  
//	                    regionTypeAjaxString += "<td >" + finalbt + "</td>";
//	                    regionTypeAjaxString += "<td >" + finalinc + "</td>";
	                   regionTypeAjaxString += "<td >" + incbatab + "</td>";
	                   regionTypeAjaxString += "<td >" +  incentive1 + "</td>";
	                    regionTypeAjaxString += "<td >" + dailypasstest1 + "</td>";
	                    regionTypeAjaxString += "<td >" + alltriptest + "</td>";
	                    regionTypeAjaxString += "<td >" + monthpasstest + "</td>";
	                    tbata += incbatab;
	                    tincentive += incentive1;
	                    double dailypass = Double.parseDouble(rs10.getString("dailypass").toString());
	                    double monthpass = Double.parseDouble(rs10.getString("monthpass").toString());
	                    double alltrip = Double.parseDouble(rs10.getString("alltrip").toString());
	                    double othdedAmt1 = Double.parseDouble(rs10.getString("othded").toString());
	                    int othdedAmt =(int)(othdedAmt1);
	                    
	                    dailypassAmts += dailypasstest1;
	                    tmonthpass += monthpasstest;
	                    talltrip += alltriptest;
	                    ttotalinc += totalinc1;
	                    regionTypeAjaxString += "<td >" + othdedAmt + "</td>";
//	                    double totalincl = finalbt + finalinc + dailypasstest + alltriptest + monthpasstest1 + othdedAmt;
//	                    int totalincl1 =(int)totalincl;
	                    totalincAmts = (totalincAmts + totalinc1);
	                    //System.out.println("totalincAmts....."+totalincAmts);
	                    sertyaxAmts=Double.parseDouble(rs10.getString("service_tax_amt").toString());
	                    esertyaxAmts+=Double.parseDouble(rs10.getString("esertax").toString());
	                    esertyaxcardAmts+=Double.parseDouble(rs10.getString("etmcardservice").toString());
	                    esertyaxticketAmts=esertyaxAmts;
	                    bsertyaxAmts+=Double.parseDouble(rs10.getString("bsertax").toString());
	                    psertyaxAmts+=Double.parseDouble(rs10.getString("psertax").toString());
	                    double etmcardincludest=Double.parseDouble(rs10.getString("etm_card_amt").toString())+Double.parseDouble(rs10.getString("etmcardservice").toString());
	                    etmcard+=Math.rint(etmcardincludest*100)/100;
	                  //  etmcard+=Math.rint(Double.parseDouble(list.get("etm_card_amt").toString())*100)/100;
	                   possum+=Double.parseDouble(rs10.getString("amount").toString());
	                   cardcoll+=Double.parseDouble(rs10.getString("etm_card_amt").toString())+Double.parseDouble(rs10.getString("etmcardservice").toString());
	                   
	                    totalsertyaxAmts+=sertyaxAmts;
	                    double amountToBeRemitted1 = totalGross - totalinc1+sertyaxAmts;
	                    amountToBeRemitted1=amountToBeRemitted1+amount;
	                    amountToBeRemitted1=Math.rint(amountToBeRemitted1*100)/100;
	                    //int amountToBeRemitted = (int)amountToBeRemitted1;
	                   // System.out.println("amountToBeRemitted........." +amountToBeRemitted);
	                    //System.out.println("total gross:"+totalGross+" \t totalinc:"+totalinc);
	                    
	                    amountToBeRemittedAmts = totalGrossAmts - totalincAmts+totalsertyaxAmts;
	                    amountToBeRemittedAmts=amountToBeRemittedAmts+possum;
	                    regionTypeAjaxString += "<td >" + totalinc1 + "</td>";
	                    regionTypeAjaxString += "<td >" + rs10.getString("service_tax_amt").toString() + "</td>";
	                    regionTypeAjaxString += "<td >0</td>";//how we are calculating value of totalinc?
	                    double othded = Double.parseDouble(rs10.getString("othded").toString());
	                    int othded1 = (int)othded;
	                    texp += othded1;
	                    https://www.youtube.com/watch?v=2R97onZxCgw
	                    	amountToBeRemitted1=Math.rint(amountToBeRemitted1*100)/100;
	                    regionTypeAjaxString += "<td >" + amountToBeRemitted1 + "</td>";
	                    regionTypeAjaxString += "<td >" + rs10.getString("amount").toString() + "</td>";
	                    regionTypeAjaxString += "<td >" + etmcardincludest + "</td>";
	                    tamtremitt += totalRemit;
	                   double Cash_Deposited = Double.parseDouble(rs10.getString("Cash_Deposited").toString());
	                   //int Cash_Deposited1 = (int)Cash_Deposited;
	                  // System.out.println("Cash_Deposited===="+Cash_Deposited);
	                   double Shortage_Amt = Double.parseDouble(rs10.getString("Shortage_Amt").toString());
	                   int Shortage_Amt1 = (int)Shortage_Amt;
	                  
	                   double Excess_Amt1 = Double.parseDouble(rs10.getString("Excess_Amt").toString());
	                   //int Excess_Amt2 = (int)Excess_Amt1;
	                   if(rs10.getString("Status").equals("AUDITED")){
	                	   Cash_Deposited=0;  
	                   }
	                   amountToBeRemittedAmts=Math.rint(amountToBeRemittedAmts*100)/100;
	                    regionTypeAjaxString += "<td >" +Cash_Deposited + "</td>";
	                    tcashdepsite += Cash_Deposited;
	                    if(rs10.getString("Status").equals("AUDITED")){
	                        double shortageamountremitted=Shortage_Amt1+amountToBeRemitted1;
	                        shortageamountremitted=Math.rint(shortageamountremitted*100)/100;
	                        if(shortageamountremitted>0){
	                       	 	regionTypeAjaxString += "<td >-" + shortageamountremitted + "</td>";
	                        }else{
	                        	 regionTypeAjaxString += "<td >" +Excess_Amt1 + "</td>";
	                        }
	                        }
	                    else if(rs10.getString("wpcstatus").equals("PENDING")){
	                    	 regionTypeAjaxString += "<td >3C</td>";
	                    }else{
	                        	 if(Shortage_Amt1>0){
	                        		 
	                                	 regionTypeAjaxString += "<td >-" +  Shortage_Amt1 + "</td>";
	                                 
	                        	 }else{
	                        		 regionTypeAjaxString += "<td >" +Excess_Amt1 + "</td>"; 
	                        	 }
	                        }
	                    regionTypeAjaxString += "<td >" +rs10.getString("userloginname").toString() + "</td>"; 
	                    regionTypeAjaxString += "<td >" +rs10.getString("Ticket_Audited_Date").toString() + "</td>"; 
	                   
	                    //regionTypeAjaxString += "<td >" + Excess_Amt1 + "</td>";
	                  
	                    tshortage += Shortage_Amt1;
	                   // System.out.println("Shortage_Amt1.."+Shortage_Amt1+"......."+tshortage);
	                    texcess += Excess_Amt1;
	                   
//	                    regionTypeAjaxString+="<td>" + list.get("service_tax_amt").toString() + "</td>";
	                    regionTypeAjaxString += "</tr>";
	                    sistyfivepass=Integer.parseInt(rs10.getString("manualdailypasssisty").toString());
	                    seventypass=Integer.parseInt(rs10.getString("manualdailypassseventy").toString());
	                    totalsistyfivepass+=sistyfivepass;
	                    totalseventypass+=seventypass;
	                    
//	                   
	                }
	                }
	                dailypassAmts=Math.rint(dailypassAmts*100)/100;
	                tincentive=Math.rint(tincentive*100)/100;
	                totalincAmts=Math.rint(totalincAmts*100)/100;
	                
	                tcashdepsite=Math.rint(tcashdepsite*100)/100;
	                tshortage=Math.rint(tshortage*100)/100;
	                texcess=Math.rint(texcess*100)/100;
	                double tshortageexcess=0.0;
//	                if(tshortage>0){
//	                	tshortageexcess=tshortage;
//	                }else{
//	                	tshortageexcess=texcess;
//	                }
	                tshortageexcess=texcess-tshortage;
	                str += "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
	                regionTypeAjaxString += "<tr><td colspan='7'><center><b>Total</b></center></td><td align='right'><b>" + df1.format(etmpassenger) + "</b></td>"
	                        + "<td align='right'><b>" + tpassenger + "</b></td>" +
//	                        "<td align='right'><b>" + etmcard + "</b></td>" +
//	                        "<td align='right'><b>" + etmdailypass + "</b></td>" +
	                        		"<td align='right'><b>" + tdailyPass
	                        + "<td align='right'><b>" + tetmcoll + "</b></td><td align='right'><b>" + tmanualluggage
	                        + "</b></td><td align='right'><b>" + etmmonthlypass + "</b></td><td align='right'><b>" + tpassmonth
	                        + "</b></td><td align='right'><b>" + df1.format(etmTotalRevenues) + "</b></td><td align='right'><b>" + manualTotalRevenues
	                        + "</b></td><td align='right'><b>" + totalGrossAmts + "</b></td><td align='right'><b>" + tbata
	                        + "</b></td><td align='right'><b>" + df1.format(tincentive) + "</b></td><td align='right'><b>" + df1.format(dailypassAmts)
	                        + "</b></td><td align='right'><b>" + talltrip + "</b></td><td align='right'><b>" + tmonthpass
	                        + "</b></td><td align='right'><b>" + texp+ "</b></td><td align='right'><b>" +  df1.format(totalincAmts)
	                        + "</b></td><td align='right'><b>" + df1.format(totalsertyaxAmts)+ "</b></td><td align='right'><b>0</b></td><td align='right'><b>" + df1.format(amountToBeRemittedAmts) + "</b></td><td align='right'><b>" + df1.format(possum) + "</b></td><td align='right'><b>" + df1.format(cardcoll) + "</b></td><td align='right'><b>" + df1.format(tcashdepsite)
	                      //  + "</b></td><td align='right'><b>" + tshortage + "</b></td><td align='right'><b>" + texcess + "</b></td></tr></tbody></table></div>\n\n";;
	                        + "</b></td><td align='right'><b>" + df1.format(tshortageexcess) + "</b></td></tr></tbody></table></div>\n\n";;
	                //regionTypeAjaxString +="<tr><td colspan='7'><center><b>Total</b></center></td><td align='right'><b>"+ tpassenger+"</b></td><td align='right'><b>"+tdailyPass+"</b></td><td align='right'><b>"+tmonthpass+"</b></td><td align='right'><b>"+tpassmonth+"</b></td><td align='right'><b>"+tpassmanual+"</b></td><td align='right'><b>"+tmanualluggage+"</b></td><td align='right'><b>"+tetmcoll+"</b></td><td align='right'><b>"+df1.format(ttotal)+"</b></td><td align='right'><b>"+df1.format(ttotals)+"</b></td><td align='right'><b>"+df1.format(tbata)+"</b></td><td align='right'><b>"+df1.format(tincentive)+"</b></td><td align='right'><b>"+df1.format(tdailypass)+"</b></td><td align='right'><b>"+df1.format(tmonthpass)+"</b></td><td align='right'><b>"+df1.format(talltrip)+"</b></td><td align='right'><b>"+df1.format(texp)+"</b></td><td align='right'><b>"+df1.format(ttotalinc)+"</b></td><td align='right'><b>"+df1.format(tamtremitt)+"</b></td><td align='right'><b>"+df1.format(tcashdepsite)+"</b></td><td align='right'><b>"+df1.format(tshortage)+"</b></td><td align='right'><b>"+df1.format(texcess)+"</b></td></tr></tbody></table>";
	                double mothlypasstotal=etmmonthlypass+tpassmonth;
	                double luggagetotal=tetmcoll+tmanualluggage;
	                     //   System.out.println("etmmonthlypass"+etmmonthlypass);
	                       // System.out.println("tpassmonth"+tpassmonth);
	                bak_remittance_amt = tcashdepsite;
	               
	              
	                String strToBePrinted = "";
	                
	               
	               
	                
	              //  str += "" + add("Sundry Receipt :", 10) + "" + add("party", 10) + "" + add("", 10) + "" + add("Reason", 10) + "" + add("", 10) + "" + add("Sundry", 10) + "" + add(" party", 10) + "" + add(" ", 10) + "" + add(" Reason", 10) + "" + add(" ", 10) + "" + add("Parcel Charges(08) ", 30) + "" + add("", 10) + "" + add("0.00", 10) + "" + "\n";
	                regionTypeAjaxString += "\n";
	                regionTypeAjaxString += "\n";
	                regionTypeAjaxString += "\n";
	                regionTypeAjaxString += "\n";
	                regionTypeAjaxString += "\n";
	                regionTypeAjaxString += "<table border='0' id='sample_6' style='width:100%;'>";
	                ResultSet rs1 = stmt.executeQuery(sql3);
	                while(rs1.next()) {
	                    //Map<String, Object> rs = aliasToValueMapList4.get(f);
	                    JSONArray ja2 = new JSONArray();

	                    sundryrcptename = rs1.getString("empname").toString();
	                    sundryrcptpname = rs1.getString("partyname").toString();
	                    if(rs1.getString("amounts").toString()==null || rs1.getString("amounts").toString().equals("")){
	                    	sundryrcptamount=0.0;
	                    }else{
	                    sundryrcptamount = Double.parseDouble(rs1.getString("amounts").toString());
	                    }
	                    sundryrcptvoucher = Integer.parseInt(rs1.getString("Receipt_voucher_no").toString());
	                    reason = rs1.getString("Reason_For_Reciept").toString();
	                    totalsundryrcptamount +=sundryrcptamount;
	                    
	                   // System.out.println("hiiiiiiiii"+sundryrcptename + sundryrcptpname + sundryrcptamount);
//	                    if(sundryrcptename==null || sundryrcptename==""){
//	                    	sundryrcptnameparty =sundryrcptpname;
//	                    }
//	                    else{
//	                    	sundryrcptnameparty=sundryrcptename;
//	                    }
	                    if(sundryrcptpname=="" || sundryrcptamount==0.0 || reason==""){
	                    	// str +=""+ add("Sundry Receipt: ---------------------",20)+ add("  Party Name : ---------------------",20)+"\n\n";
	                    	 regionTypeAjaxString += "<tr><td><b><br>Sundry Receipt :</b></td><td> ______________ </td><td><b><br>Party Name :</b></td><td> ______________ </td></tr>"+"\n\n";
	                    	
	                    }else{
	                    	// str +=""+ add("Sundry Receipt:",10)+add(String.valueOf(sundryrcptamount),10)+ add("  Party Name :",10)+add(String.valueOf(sundryrcptpname),10)+"\n\n";
	                         regionTypeAjaxString += "<tr><td><b>Sundry Receipt :</b></td><td> "+sundryrcptamount+" </td><td><b>Party Name :</b></td><td> "+sundryrcptpname+ "</td></tr>"+"\n\n";
	                    }
	                    }
	                ResultSet rs2 = stmt.executeQuery(sql4);
	                while(rs2.next()) {
	                 //   Map<String, Object> rs1 = aliasToValueMapList5.get(f1);
	                    JSONArray ja3 = new JSONArray();
	                
	                    sundrypmtename = rs2.getString("empname").toString();
	                    sundrypmtpname = rs2.getString("partyname").toString();
	                    if(rs2.getString("amounts").toString()==null || rs2.getString("amounts").toString().equals("")){
	                    	sundrypmtamount=0.0;
	                    }else{
	                    sundrypmtamount = Double.parseDouble(rs2.getString("amounts").toString());
	                    }
	                    sundrypmtvoucher = Integer.parseInt(rs2.getString("Receipt_voucher_no").toString());
	                    pmtreason = rs2.getString("Reason_For_Payment").toString();
	                   // System.out.println("pmtreason"+pmtreason);
	                    totalsundrypmtamount +=sundrypmtamount;
	                    
	                  //  System.out.println("hiiiiiiiii"+sundryrcptename + sundryrcptpname + sundryrcptamount);
	                    
//	                    if(sundrypmtename==""){
//	                    	sundrypmtnameparty =sundrypmtename;
//	                    }
//	                    else{
//	                    	sundrypmtnameparty=sundrypmtename;
//	                    }
	                    if(sundrypmtpname=="" || sundrypmtamount==0.0 || pmtreason==""){
	                    	//str +=""+ add("Sundry Refund : ---------------------",20)+ add("  Party Name : ---------------------",20)+"\n\n";
	                    	regionTypeAjaxString += "<tr><td><b><br>Sundry Refund :</b></td><td>_______________ </td><td><b> <br>Party Name :</b></td><td>_____________</td></tr>"+"\n\n";
	                        
	                   	
	                    	          }
	                    else{
	                    	 
	                    	// str +=""+ add("Sundry Refund :",10)+add(String.valueOf(sundrypmtamount),10)+ add("  Party Name :",10)+add(sundrypmtpname,10)+ "\n\n";
	                     	regionTypeAjaxString += "<tr><td><b>Sundry Refund :</b></td><td>"+sundrypmtamount+" </td><td><b>Party Name :</b></td><td>"+sundrypmtpname+"</td></tr>"+"\n\n";
	           
	                    }
	                    
	                    
	               
	                }
	                int count=totalsistyfivepass/65;
	                int count1=totalseventypass/70;
//	                str1 +="\n\n\n\n                                                Summary Of Daily Revenue Collection Statement (Acc - 66)\n\n";
//	                str1 +=""+ add("Total Sundry Receipt:",10)+add(String.valueOf(totalsundryrcptamount),10)+ add("  Total Sundry Refund :",10)+add(String.valueOf(totalsundrypmtamount),20);
//	                //str += "" + add("Receipt", 20) + "" + add("Name", 10) + "" + add("", 10) + "" + add("___________", 22) + "" + add("Refund", 10) + "" + add("Name", 10) + "" + add("__________ ", 20) + "" + add(" ", 10) + "" + add("Casual Contract Collection(13) ", 30) + "" + add("", 10) + "" + add("0.00", 10) + "" + "\n";
//	               // str += "" + add("Total Sundry Receipt", 20) + "" + add("", 10) + "" + add("", 10) + "" + add("0.0", 10) + "" + add("", 10) + "" + add("Total Sundry Refund", 32) + "" + add(" 0.0", 10) + "" + add(" ", 10) + "" + add("Student Pass Collection(12) ", 30) + "" + add("", 10) + "" + add("0.00", 10) + "" + "\n";
//	                str1 += "" + add("", 43) + "" + add("Passenger Fare ETM", 28) + "" + add("", 2) + "" + addMoney(df1.format(etmpassenger), 20) + "" + "\n";
//	                str1 += ""+ add("Total Schedules", 28) + "" + add("", 2) + "" + addMoney(String.valueOf(totalsced), 20) + add("", 67) + "" + add("Passenger Fare Manual", 28) + "" + add("", 2) + "" + addMoney(String.valueOf(tpassenger), 20) + "" + "\n";
//	                str1 += ""+ add("Opearated Schedules", 28) + "" + add("", 2) + "" + addMoney(String.valueOf(operated), 20) + add("", 67) + "" + add("Monthly Pass ETM/Manual", 28) + "" + add("", 2) + "" + addMoney(df1.format(mothlypasstotal), 20) + "" + "\n";
//	                str1 += ""+ add("Not Opearated Schedules", 28) + "" + add("", 2) + "" + addMoney(String.valueOf(notoperated), 20) + add("", 67) + "" + add("Luggage Amount ETM/Manual", 28) + "" + add("", 2) + "" + addMoney(df1.format(luggagetotal), 20) + "" + "\n";
//	                str1 += ""+ add("Due For Audit Schedules", 28) + "" + add("", 2) + "" + addMoney(String.valueOf(dueforaudit), 20) + add("", 67) + "" + add("Casual Contract Collection(13)", 28) + "" + add("", 2) + "" + addMoney("0", 20) + "" + "\n";
//	                str1 += ""+ add("Cash Collection Schedules", 28) + "" + add("", 2) + "" + addMoney(String.valueOf(cashcollection), 20) + add("", 67) + "" + add("Student Pass Collection(12)", 28) + "" + add("", 2) + "" + addMoney("0", 20) + "" + "\n";
//	                str1 += "" + add("Zero Audit Schedules", 28) + "" + add("", 2) + "" + addMoney(String.valueOf(oaudit), 20) + add("", 67) + "" + add("Parcel Charges(08)", 30) + "" + add("", 10) + "" + addMoney("0", 10) + "" + "\n";
//	                if(getpasslist==null){
//	                	
//	                }else{
//	                if(getpasslist.size()>0){
//	                for (int j = 0; j < getpasslist.size(); j++) {
//	                    Map<String, String> list = getpasslist.get(j);
//	               // str1 += "" + add("", 119) + "" + add(" Daily Pass"+list.get("denom")+"Rs", 30) + "" + add("", 10) + "" + addMoney(""+list.get("value")+"("+list.get("tpass")+")", 10) + "" + "\n";
//	                }
//	                }
//	                }
//	              
	               
	                
	                regionTypeAjaxString += "<tr><td><b>Total Sundry Receipt</b></td><td>" +totalsundryrcptamount+" </td><td><b>Total Sundry Refund </b></td><td>"+totalsundrypmtamount+"</td><td></td><td></td>";
	                regionTypeAjaxString += "<tr><td colspan='2'><b><br> Total Schedules</b></td><td style='text-align:right'><br>"+totalsced+"</td><td colspan='3'></td><td><b><br> Passenger Fare ETM </b></td><td style='text-align:right'><br>"+df1.format(etmpassenger)+"</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='2'><b><br> Operated Schedules</b></td><td style='text-align:right'><br>"+operated+"</td><td colspan='3'></td><td><b><br> Passenger Fare Manual </b></td><td style='text-align:right'><br>"+tpassenger+"</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='2'><b><br> Not Operated Schedules</b></td><td style='text-align:right'><br>"+notoperated+"</td><td colspan='3'></td><td><b><br> Monthly Pass ETM/Manual </b></td><td style='text-align:right'><br>"+df1.format(mothlypasstotal)+"</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='2'><b><br> Due For Audit Schedules</b></td><td style='text-align:right'><br><a href='#' onclick='jsFunction();'>"+dueforaudit+"</a></td><td colspan='3'></td><td><b><br> Luggage Amount ETM/Manual</b></td><td style='text-align:right'><br>"+df1.format(luggagetotal)+"</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='2'><b><br> Cash Collection Schedules</b></td><td style='text-align:right'><br>"+cashcollection+"</td><td colspan='3'></td><td><b><br>Casual Contract Collection(13)</b></td><td style='text-align:right'><br>0</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='2'><b><br> Zero Audit Schedules</b></td><td style='text-align:right'><br>"+oaudit+"</td><td colspan='3'></td><td><b><br>Student Pass Collection(12)</b></td><td style='text-align:right'><br>0</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>Parcel Charges(08)</b></td><td style='text-align:right'><br>0</td></tr>";
	                if(getpasslist==null){
	                	
	                }else{
	                if(getpasslist.size()>0){
	                for (int j = 0; j < getpasslist.size(); j++) {
	                    Map<String, String> list = getpasslist.get(j);
	                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br> Daily Pass "+list.get("denom")+"Rs </b></td><td style='text-align:right'><br>"+list.get("value")+"("+list.get("tpass")+")</td></tr>";
	                }
	                }
	                }
	                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>Luggage Collection At Bus Station (09)</b></td><td style='text-align:right'><br>0</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>Casual Contract Refund(35)</b></td><td style='text-align:right'><br>0</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>Conductor Shortage Payments(14)</b></td><td style='text-align:right'><br>" +  df1.format(tshortage) + "</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>Conductor Excess Payments</b></td><td style='text-align:right'><br>" + df1.format(texcess) + "</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>Casual Contract Incentive</b></td><td style='text-align:right'><br>0</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>Total Incentive</b></td><td style='text-align:right'><br>" + df1.format(tincentive) + "</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>Total Batta</b></td><td style='text-align:right'><br>" + tbata + "</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>Total Daily Pass Inc</b></td><td style='text-align:right'><br>" + df1.format(dailypassAmts) + "</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>Total Monthly Pass inc</b></td><td style='text-align:right'><br>" + tmonthpass + "</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>Total All Trip inc</b></td><td style='text-align:right'><br>" + talltrip + "</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>Total Mis Exp</b></td><td style='text-align:right'><br>" + texp + "</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>Total Ded</b></td><td style='text-align:right'><br>" + df1.format(totalincAmts) + "</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>Total Exces</b></td><td style='text-align:right'><br>" +  df1.format(texcess) + "</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>Total Shortage</b></td><td style='text-align:right'><br>" + df1.format(tshortage) + "</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>ETM Ticket Service Tax</b></td><td style='text-align:right'><br>" + df1.format(esertyaxticketAmts) + "</td></tr>";
	                                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>Bag Service Tax</b></td><td style='text-align:right'><br>" + df1.format(bsertyaxAmts) + "</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>Pass Service Tax</b></td><td style='text-align:right'><br>" + df1.format(psertyaxAmts) + "</td></tr>";
	                                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>Sale Of Ticket Through POS at Airport Counter </b></td><td style='text-align:right'><br>" + possale + "</td></tr>";
	                
	                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>Total Service Tax</b></td><td style='text-align:right'><br>" + df1.format(totalsertyaxAmts) + "</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>Smart Card Incentive</b></td><td style='text-align:right'><br>0</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>Smart Card Service Tax</b></td><td style='text-align:right'><br>" + df1.format(esertyaxcardAmts) + "</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>Smart Card Revenue </b></td><td style='text-align:right'><br>" + df1.format(etmcard) + "</td></tr>";

	                regionTypeAjaxString += "<tr><td colspan='6'></td><td><b><br>BANK REMITTANCE AMOUNT</b></td><td style='text-align:right'><br>" + df1.format(bak_remittance_amt) + "</td></tr>";
	                regionTypeAjaxString += "<tr><td colspan='8' height='100'></td></tr>";
	                regionTypeAjaxString += "<tr><td align='left' colspan='4'><b>Note:-'3C' indicates 3Code Audit</b></td><td align='right' colspan='4'><b>Signature of Cashier/Acc Supr./Depot Mgr.</b></td></tr>";
	               regionTypeAjaxString += "</table>";
	                regionTypeAjaxString += "</div>  <div align='center'><span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span>&nbsp;" +
//	                		"<span><input type='button' class='btn green' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span>" +
	                		"</div>";
	            
	            } else {
	                regionTypeAjaxString += "<center><h4><span style='color:red'><b>No Records Found<b></span></h4></center>";
	            }
	            HttpServletResponse response = ServletActionContext.getResponse();
	            PrintWriter out;
	            out = response.getWriter();
	            out.print(regionTypeAjaxString);

	        } catch (Exception e) {
	            //TODO Auto-generated catch block
	            e.printStackTrace();
	        } finally {
	            if (session1 != null && session1.isOpen()) {
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

	    //Daily Report

	  
	    @SuppressWarnings("null")
		private List<Map<String, String>> getpassdata(String parentID, String date1,
				String date2,Session session) {
	    	   Connection connection=null;
	   		Statement stmt=null;
	   		Statement stmt1=null;
	   		Session session1 = null;
	   		ResultSet rs=null;
	   		
	   		Transaction transaction  = null;
	   		HttpServletRequest req=ServletActionContext.getRequest();
	   		List<Map<String, String>> getpasslist = new ArrayList<Map<String, String>>();
	   		try {
	   		
	   		session1 = HibernateUtil.getSession("hibernate.cfg.xml");
	   		 transaction = session1.beginTransaction();
	   		 String realpath = ServletActionContext.getRequest()
	   					.getRealPath("/");
	   	//	String sql1="SELECT depotname,ip,uname,password,dbname FROM depot_ip_info_report WHERE depot_id = '"+parentID+"'";
	   		 Common common = new Common();
	   	//	String sql1="SELECT depotname FROM depot_ip_info WHERE depot_id = '"+parentID+"'";
	   		 String sql1="SELECT depotname,central_ip,central_uname,central_pwd,dbname FROM depot_ip_info WHERE depot_id = '"+parentID+"'";
//	   			String depotdb=common.getDBResultStr(session1, sql1, "depotname");
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
	   		 stmt = connection.createStatement();
	   		stmt1 = connection.createStatement();
	  
	   		String qry1 = "SELECT `sys_value` FROM `local_system_variable` WHERE `sys_key` = 'PASS_DENOM' AND `status` = 'Y' ";
	   		System.out.println("qry1"+qry1);
	   		rs=stmt.executeQuery(qry1);
	   	    List list = new ArrayList();
		   	// List<Map<String, String>> getpasslist=null;
		   	
		   		
	   	 	 while(rs.next()){
	   	 		//Map<String,String> mapa = (Map<String,String>)rs.getString("sys_value");
	             String Sql="SELECT sum(manualdailypasssisty) as manualdailypasssisty,denomination_type_manual,sum(manualdailypasssisty)/denomination_type_manual as noofpass " +
	             		"FROM Waybill_Details wd LEFT JOIN  (SELECT a1.waybill_cwa_block_master_id, sum(value) AS manualdailypasssisty," +
	             		"denomination_type_manual FROM waybill_cwa_block_master a1 " +
	             		"INNER JOIN Waybill_cwa_receipt_details a2 ON a1.waybill_cwa_block_master_id = a2.waybill_cwa_block_master_id " +
	             		"INNER JOIN denomination_type_manual dtm on dtm.denomination_type_manual_id=a2.denomination_type_manual_id " +
	             		"where a2.ticket_type_manual_id=2 and denomination_type_manual='"+Integer.parseInt(rs.getString("sys_value"))+"' " +
	             		"GROUP BY a1.waybill_cwa_block_master_id) block ON " +
	             		"block.waybill_cwa_block_master_id = wd.param2 where  wd.Ticket_Audited_Date " +
	             		"BETWEEN '"+date1+" 00:00:00' and '"+date2+" 23:59:59' " +
	             		"and wd.Status IN (UPPER('Closed'),'AUDITED') GROUP BY denomination_type_manual";
	             System.out.println("Sql==="+Sql);
	             ResultSet rs1=stmt1.executeQuery(Sql);
	             
	             while(rs1.next()){
	            	// System.out.println("rs1 resultset==="+rs1.getString("manualdailypasssisty"));
	            	 if(rs1.getString("manualdailypasssisty")==null){
		                	
		                }else{
		                Map<String, String> list1 = new LinkedHashMap<String, String>();
		                String value=rs1.getString("manualdailypasssisty").toString();
		                String denom=rs1.getString("denomination_type_manual").toString();
		                String tpass=rs1.getString("noofpass").toString();
		                int count=Integer.parseInt(value)/Integer.parseInt(denom);
		                tpass=String.valueOf(count);
		                list1.put("value", value);
			            list1.put("denom", denom);
			            list1.put("tpass", tpass);
			           // System.out.println("rs1 adding map ==="+rs1.getString("noofpass"));
			            getpasslist.add(list1);
	             }
	   	 	 		}
	   	 	 
	   	 	
	   	 	}
	   	 	
	         }catch (Exception e) {
				e.printStackTrace();
			}finally{
				
			}
	         System.out.println("getdata pass list........."+getpasslist.size());
			return getpasslist;
	    }

	  

	   
}
