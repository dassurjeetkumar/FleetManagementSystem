package com.trimax.its.report.action;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
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

import com.opensymphony.xwork2.ActionSupport;
import com.trimax.its.common.Common;
import com.trimax.its.util.HibernateUtil;

public class ETMWiseTicketSaleDetailsReport extends ActionSupport{
	
	
	String path="";
	char ft = 15;
	String str="";

	String c=" ";
	 public String startdate;
	    public String enddate;
	public  Map<Integer, String> etmtype;
	public Map<Integer, String> getEtmtype() {
		return etmtype;
	}


	public void setEtmtype(Map<Integer, String> etmtype) {
		this.etmtype = etmtype;
	}


	public String etmno;

	


	public String getEtmno() {
		return etmno;
	}


	public void setEtmno(String etmno) {
		this.etmno = etmno;
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


	char fl = 18;
	char f2 = 12;
	int pagi = 1;

	int FULL_LEN = 120;
	int HALF_LEN = 60;

	double earnAmmount=0.0;
	double dedtotal=0.0;
	int subtotalValues=0;
	double totalammount=0.0;

	String regionTypeAjaxString = "";

	
		public String execute() {
			this.setEtmtype(getEtmType1());
			
			return "success";
		}

		
		@SuppressWarnings("finally")
		public String AjaxETMWiseTicketSaleDetailsReport()
		{
			
			HttpServletRequest req = ServletActionContext.getRequest();
					try {
					
					Date  ss1=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yy hh:mm aaa ");
				    String runDateTime = sdf.format(ss1);
				   Session session1 = null;
					Transaction transaction  = null;
					Common common = new Common();
					String statr1= req.getParameter("startdate");
					String end1= req.getParameter("startdate");
					String date1=common.getDateFromPicker(startdate);
					String date2=common.getDateFromPicker(enddate);
					String queryyy;
					
					String realpath = ServletActionContext.getRequest()
							.getRealPath("/");
					
					
					String sql = "";
//					 sql="select dat,sum(tckt_revenue) tckt_revenue ,sum(lugg_revenue) lugg_revenue,sum(sistyfive_pass) sistyfive_pass,sum(seventy_pass) seventy_pass,sum(incentive) incentive,sum(bata) bata,sum(misc) misc" +
//						  		" from (select dat,tckt_revenue,lugg_revenue,sistyfive_pass,seventy_pass," +
//						  		" incentive,bata,misc from cummulative_revenue WHERE dat between '"+date1+"' and '"+date2+"') a group by dat order by dat" ;
//						  		
//					 session1 = HibernateUtil.getSession("hibernate.cfg.xml");
//					 transaction = session1.beginTransaction();
//					 Query query = session1.createSQLQuery(sql);
//				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//					List<Map<String, Object>> aliasToValueMapList = query.list();
					String filePath = "Ticketing/";

					String fileName = "ETMWiseTicketSaleDetailsReport.txt";
					  
					     regionTypeAjaxString +=" <div id='header' style='display: none;'><div align='center'><h4>Cumulative Revenue Report</br>From Date:- "+startdate+" To Date:-"+ enddate+"</h4></br></div>";

				        regionTypeAjaxString +="<div align='right'><b>Run Date:-</b>"+runDateTime+"</div></div>";
				        
				        String nwkr=""    +   "                                    Schedulewise Earnings   " +
			      		
				        		"\n                                    From Date:"+ startdate+"  End Date:"+ enddate+"                                              ";
						
						regionTypeAjaxString += "<div class='component'><table class='overflow-y' border='1'>";
						regionTypeAjaxString +="<thead><tr><th rowspan='1'>ETM No.</th><th rowspan='1'>Date</th><th rowspan='1'>WayBill No</th><th rowspan='1'>Schd No</th><th rowspan='1'>PF No</th><th rowspan='1'>Conductor Name</th><th rowspan='1' colspan='3'>-------As per ACC 66------</th><th rowspan='1' colspan='3'>--------As per ETM Data--------</th><th>Start Ticket</th><th>Start Date</th> <th>Start Time</th><th>End Ticket</th><th>End Date</th><th>End Time</th><th>Tot Ticket</th>"+"</tr>";
						regionTypeAjaxString +="<thead><tr><th></th><th></th><th></th><th></th><th></th><th></th><th rowspan='1'>Collection</th><th rowspan='1'>Deduction</th><th rowspan='1'>Net Amt</th><th rowspan='1'>Collection</th><th rowspan='1'>Deduction</th><th rowspan='1'>Net Amt</th><th></th><th></th>" +
								"                          <th></th><th></th><th></th><th></th><th></th>"+"</tr></thead><tbody>";
						

//						String headingprint =" "+"_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _  _ ___ _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n" 
//								+add("SNo",3)+"|"+add("Date",10)+ "|"+add("Tckt Rev",25)+ "|"+add("Lug Rev",7)+"|"+add("Dp-65",14)+"|"+add("Dp-70",10)+"|"+add("Dp-95",11)+"|"+add("Dp-100",7)+"|"+add("Dp-140",10)+"|"+add("Dp-170",14)+"|"+
//								add("Mp-1050",14)+"|"+add("Mp-1500",14)+"|"+add("Mp-2250",14)+"|"+add("Mp-2300",14)+"|"+add("Mp-3650",14)+"|"+add("Id Card",14)+"|"+add("Earn Total",14)+"|"+add("Incentive",14)+"|"+add("Batta",14)+"|"+add("Misc",14)+add("Ded Total",14)+"|"+add("Net Total",14)+"|"+"\n"+
//								    " "+ "_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _  _ _ _ _ __ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n";
//						
							
						 
						    String path = realpath + filePath + fileName;
//					        str+=ft+nwkr+add(headingprint,5);
//					        if(aliasToValueMapList.size()==0){
//					        regionTypeAjaxString +="<tr><td colspan='22'>No Records Found</td></tr>";
//					        }
//					        for (int i = 0; i < aliasToValueMapList.size(); i++) {
//								int j=i+1;
//								regionTypeAjaxString +="<tr>";
//								Map<String, Object> list = aliasToValueMapList.get(i);
//								
//								BigDecimal b=new BigDecimal(list.get("tckt_revenue").toString());
//								System.out.println("tcktrev--"+b);
//								double tcktrev = Double.parseDouble(list.get("tckt_revenue").toString());
//								double luggrev = Double.parseDouble(list.get("lugg_revenue").toString());
//								double sixtyfive = Double.parseDouble(list.get("sistyfive_pass").toString());
//								double seventy = Double.parseDouble(list.get("seventy_pass").toString());
//								System.out.println("tcktrev--"+tcktrev);
//								regionTypeAjaxString +="<td>"+j+"</td>";
//								String d=common.getDateToPicker(list.get("dat").toString());
//								regionTypeAjaxString +="<td>"+d+"</td>";
//								regionTypeAjaxString +="<td>"+b+"</td>";
//								regionTypeAjaxString +="<td>"+luggrev+"</td>";
//								regionTypeAjaxString +="<td>"+sixtyfive+"</td>";
//								regionTypeAjaxString +="<td>"+seventy+"</td>";
//								regionTypeAjaxString +="<td>"+"0"+"</td>";
//								regionTypeAjaxString +="<td>"+"0"+"</td>";
//								regionTypeAjaxString +="<td>"+"0"+"</td>";
//								regionTypeAjaxString +="<td>"+"0"+"</td>";
//								regionTypeAjaxString +="<td>"+"0"+"</td>";
//								regionTypeAjaxString +="<td>"+"0"+"</td>";
//								regionTypeAjaxString +="<td>"+"0"+"</td>";
//								regionTypeAjaxString +="<td>"+"0"+"</td>";
//								regionTypeAjaxString +="<td>"+"0"+"</td>";
//								regionTypeAjaxString +="<td>"+"0"+"</td>";
//								
//								
//								
//								
//								earnAmmount+=tcktrev+luggrev+sixtyfive+seventy;
//								BigDecimal b1=new BigDecimal(earnAmmount);
//								regionTypeAjaxString +="<td>"+b1+"</td>";
//								double incentive = Double.parseDouble(list.get("incentive").toString());
//								double bata = Double.parseDouble(list.get("bata").toString());
//								double misc = Double.parseDouble(list.get("misc").toString());
//								incentive=Math.rint(incentive*100)/100;
//								bata=Math.rint(bata*100)/100;
//								misc=Math.rint(misc*100)/100;
//								regionTypeAjaxString +="<td>"+incentive+"</td>";
//								regionTypeAjaxString +="<td>"+bata+"</td>";
//								regionTypeAjaxString +="<td>"+misc+"</td>";
//								dedtotal+=incentive+bata+misc;
//								dedtotal=Math.rint(dedtotal*100)/100;
//								regionTypeAjaxString +="<td>"+dedtotal+"</td>";
//								totalammount=earnAmmount-dedtotal;
//								
//								BigDecimal b2=new BigDecimal(totalammount);
//								 MathContext mc = new MathContext(12);
//								b2=b2.round(mc);
//								regionTypeAjaxString +="<td>"+b2+"</td>";
//								// str+=""+add(String.valueOf(j),5)+"|" + add(date,20) + "|" + add(schduleNo, 20) +"|" + add(tokenNo,20)+"|" + add(name,20)+"|" + add(total,10)+ "|"+"\n";
//								 if(j%55==0){
//									   str+=f2+add(headingprint,5);
//								   }
//								regionTypeAjaxString +="</tr>";
//							}
					        
							
					        
						   
					    
					        
					     
						    regionTypeAjaxString += "</tbody></table></div>        </div>"; 
						    
                             str+="_ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _  _ _ _ _ _ _ _ _  _ ____ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  __ _ _ _ _ _ _  __ _\n";
						   ServletActionContext.getRequest().getSession().setAttribute("filePath",realpath + filePath + fileName);
					HttpServletResponse response = ServletActionContext.getResponse();
					PrintWriter out;
					
					
					
						
						FileOutputStream FOS = new FileOutputStream(path);
						PrintWriter PW = new PrintWriter(FOS);
						
					String p=str;
					

					PW.println(p);
					PW.close();
						out = response.getWriter();
						out.print(regionTypeAjaxString);
					} catch (Exception e) {
						
						e.printStackTrace();
					}

					return null;
			}
		

		public Map<Integer, String> getEtmType1() {
			Map<Integer, String> resultMap = new LinkedHashMap<Integer, String>();
			Session session = HibernateUtil.getSession("hibernate.cfg.xml");
			
			try {
				String sql="SELECT device_id, device_serial_number FROM device WHERE device_type_id = '2' AND status = 'ACTIVE' AND deleted_status = '0' ORDER BY device_serial_number ";
				Query query = session.createSQLQuery(sql)
						.addScalar("device_id", Hibernate.INTEGER)
						.addScalar("device_serial_number", Hibernate.STRING);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				List<Map<String, Object>> aliasToValueMapList = query.list();
				resultMap.put(0, "select");
				for(int i=0;i<aliasToValueMapList.size();i++){
					Map<String, Object> rs = aliasToValueMapList.get(i);
					resultMap.put(Integer.parseInt(rs.get("device_id").toString()),rs.get("device_serial_number").toString());
				}				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				  if(session!=null){ session.close(); }
				 
			}
			return resultMap;
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
	

}
