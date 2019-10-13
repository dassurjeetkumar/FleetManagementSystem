package com.trimax.its.report.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;

import com.trimax.its.report.dao.BusStopReportDao;

import com.trimax.its.transport.dao.BusStopsDAO;
import com.trimax.its.transport.model.BusStops;
//import com.trimax.its.utility.dao.RoleDao;
import javax.servlet.ServletOutputStream;
//import org.xhtmlrenderer.pdf.ITextRenderer;
//import org.xhtmlrenderer.pdf.PDFCreationListener;

public class BusStopAction {
	
	private BusStops bustops;
	private List<BusStops> list;
	
	private String SEARCH_TERM;

	private String COL_NAME;

	private String DIR;

	private int START;

	private int AMOUNT;
	
	public List<BusStops> getList() {
		return list;
	}

	public void setList(List<BusStops> list) {
		this.list = list;
	}

	public BusStops getBustops() {
		return bustops;
	}

	public void setBustops(BusStops bustops) {
		this.bustops = bustops;
	}

	private String status;
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	public String getExcel() throws IOException{
		try{
			BusStopReportDao busstopdao = new BusStopReportDao();
			HttpServletRequest request = ServletActionContext.getRequest();
			String status=request.getParameter("status");
//			System.out.println("status---------"+status);
		  String pdfBuffer = "";
		  String fileName = "BusstopList";
		  String fileType = "xls";
			HttpServletResponse response = ServletActionContext.getResponse();
		pdfBuffer = "<html><head>Bus Stop list</head><body>";
		pdfBuffer +="<tr ><td><b> Bus Stop Name</b> </td><td><b> Bus Stop Name In kannda </b> </td><td><b> Bus Stop code </b></td><td><b> Bus Stop code in kannda</b></td><td><b> locality </b></td><td><b> landmark</b> </td><td><b> sheltered</b> </td>" +
				" <td><b> Alias1 </b></td><td><b> Alias2</b> </td><td><b> Alias3 </b></td><td><b> Alias4 </b></td><td><b> Alias5 </b></td><td><b> Alias6 </b></td><td><b> Kannada in alias1 </b></td><td><b> Kannada in alias2 </b></td><td><b> Area Population </b></td><td><b> Stop size </b></td><td><b> Fare stage </b></td><td><b> Description </b></td><td><b> Status </b></td>" +
				" </tr>";
		
		/* pdfBuffer += "<table border='1' ><tr><th> Bus Stop Name </th><th> Bus Stop Name In kannda </th><th> Bus Stop code </th><th> locality </th><th> landmark </th><th> sheltered </th>" +
					" <th> Alias1 </th><th> Alias2 </th><th> Alias3 </th><th> Alias4 </th><th> Alias5 </th><th> Alias6 </th><th> Kannada in alias1 </th><th> Kannada in alias2 </th><th> Area Population </th><th> Fare stage </th><th> Description </th><th> Status </th></tr><tbody>";
           */
	     pdfBuffer += "<table align='center'>";
	    ArrayList result=busstopdao.getbusstoplist(status);
//	     System.out.println("result----------"+result);
	   
	     for(int i=0;i<result.size();i++){
	    	 String busstopname=(String)((ArrayList)result.get(i)).get(0).toString();
	    	 String busstopnameinkannda=(String)((ArrayList)result.get(i)).get(1).toString();
	    	 String busstopcode=(String)((ArrayList)result.get(i)).get(2).toString();
	    	 String busstopcodeinkannda=(String)((ArrayList)result.get(i)).get(3).toString();
	    	 String locality=(String)((ArrayList)result.get(i)).get(4).toString();
	    	 String landmark=(String)((ArrayList)result.get(i)).get(5).toString();
	    	 String is_sheltered=(String)((ArrayList)result.get(i)).get(6).toString();
	    	 String alias1=(String)((ArrayList)result.get(i)).get(7).toString();
	    	 String alias2=(String)((ArrayList)result.get(i)).get(8).toString();
	    	 String alias3=(String)((ArrayList)result.get(i)).get(9).toString();
	    	 String alias4=(String)((ArrayList)result.get(i)).get(10).toString();
	    	 String alias5=(String)((ArrayList)result.get(i)).get(11).toString();
	    	 String alias6=(String)((ArrayList)result.get(i)).get(12).toString();
	    	 String k_alias_1=(String)((ArrayList)result.get(i)).get(13).toString();
	    	 String k_alias_2=(String)((ArrayList)result.get(i)).get(14).toString();
	    	 String area_population=(String)((ArrayList)result.get(i)).get(15).toString();
	    	 String stop_size=(String)((ArrayList)result.get(i)).get(16).toString();
	    	 String fare_stage=(String)((ArrayList)result.get(i)).get(17).toString();
	    	 String description=(String)((ArrayList)result.get(i)).get(18).toString();
	    	 String status1=(String)((ArrayList)result.get(i)).get(19).toString();
	    	
	    	
	    	 pdfBuffer += "<tr><td>" + busstopname + "</td><td>"+ busstopnameinkannda +"</td><td>"+ busstopcode +"</td><td>"+busstopcodeinkannda+"</td><td>"+locality+"</td><td>"+landmark+"</td>" +
	    	 		"<td>" + is_sheltered + "</td><td>" + alias1 + "</td><td>" + alias2 + "</td><td>" + alias3 + "</td><td>" + alias4 + "</td><td>" + alias5 + "</td><td>" + alias6 + "</td><td>" + k_alias_1 + "</td><td>" + k_alias_2 + "</td><td>" + area_population + "</td> " +
	    	 		"<td>" + stop_size + "</td><td>" + fare_stage + "</td><td>" + description + "</td><td>" + status1 + "</td></tr>";
	     }
	     pdfBuffer += "</table></body></html>";
	     response.setContentType("application/vnd.ms-excel");
	     response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "." + fileType + "\"");
	     PrintWriter out2 = response.getWriter();
	    // System.out.println("PDFBUFFER========>"+pdfBuffer);
	     out2.print(pdfBuffer);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String getPDF()throws IOException{
		try{
			 String pdfBuffer = "";
			
			  String fileName = "BusstopList";
			  String fileType = "pdf";
			  BusStopReportDao busstopdao = new BusStopReportDao();
			  HttpServletResponse response = ServletActionContext.getResponse();
			  HttpServletRequest request = ServletActionContext.getRequest();
			  ServletOutputStream outputStream = response.getOutputStream();
			  String status=request.getParameter("status");
//			  System.out.println("status---------"+status);
			//  ITextRenderer renderer = new ITextRenderer();
			  pdfBuffer = "<html><head>Bus Stop list</head><body>";
			 /* pdfBuffer += "<table border='1' ><tr><th> Bus Stop Name </th><th> Bus Stop Name In kannda </th><th> Bus Stop code </th><th> locality </th><th> landmark </th><th> sheltered </th>" +
				" <th> Alias1 </th><th> Alias2 </th><th> Alias3 </th><th> Alias4 </th><th> Alias5 </th><th> Alias6 </th><th> Kannada in alias1 </th><th> Kannada in alias2 </th><th> Area Population </th><th> Fare stage </th><th> Description </th><th> Status </th></tr><tbody>";
             */
			  pdfBuffer +="<tr ><td><b> Bus Stop Name</b> </td><td><b> Bus Stop Name In kannda </b> </td><td><b> Bus Stop code </b></td><td><b> Bus Stop code in kannda</b></td><td><b> locality </b></td><td><b> landmark</b> </td><td><b> sheltered</b> </td>" +
						" <td><b> Alias1 </b></td><td><b> Alias2</b> </td><td><b> Alias3 </b></td><td><b> Alias4 </b></td><td><b> Alias5 </b></td><td><b> Alias6 </b></td><td><b> Kannada in alias1 </b></td><td><b> Kannada in alias2 </b></td><td><b> Area Population </b></td><td><b> Stop size </b></td><td><b> Fare stage </b></td><td><b> Description </b></td><td><b> Status </b></td>" +
						" </tr>";
             ArrayList result=busstopdao.getbusstoplist(status);
           
			
			  
            // System.out.println("result----------"+result);
         /*   for(int i=0;i<result.size();i++){
            	String busstopname=(String)((ArrayList)result.get(i)).get(0).toString();
   	    	 String busstopnameinkannda=(String)((ArrayList)result.get(i)).get(1).toString();
   	    	 String busstopcode=(String)((ArrayList)result.get(i)).get(2).toString();
   	    	 String locality=(String)((ArrayList)result.get(i)).get(3).toString();
   	    	 String landmark=(String)((ArrayList)result.get(i)).get(4).toString();
   	    	 String is_sheltered=(String)((ArrayList)result.get(i)).get(5).toString();
   	    	 String alias1=(String)((ArrayList)result.get(i)).get(6).toString();
   	    	 String alias2=(String)((ArrayList)result.get(i)).get(7).toString();
   	    	 String alias3=(String)((ArrayList)result.get(i)).get(8).toString();
   	    	 String alias4=(String)((ArrayList)result.get(i)).get(9).toString();
   	    	 String alias5=(String)((ArrayList)result.get(i)).get(10).toString();
   	    	 String alias6=(String)((ArrayList)result.get(i)).get(11).toString();
   	    	 String k_alias_1=(String)((ArrayList)result.get(i)).get(12).toString();
   	    	 String k_alias_2=(String)((ArrayList)result.get(i)).get(13).toString();
   	    	 String area_population=(String)((ArrayList)result.get(i)).get(14).toString();
   	    	 String stop_size=(String)((ArrayList)result.get(i)).get(15).toString();
   	    	 String fare_stage=(String)((ArrayList)result.get(i)).get(16).toString();
   	    	 String description=(String)((ArrayList)result.get(i)).get(17).toString();
   	    	 String status1=(String)((ArrayList)result.get(i)).get(18).toString();
   	    	 //pdfBuffer += "<tr ><td>" + id + "</td><td>"+name+"</td></tr>";
   	    	 pdfBuffer += "<tr><td>" + busstopname + "</td><td>"+ busstopnameinkannda +"</td><td>"+ busstopcode +"</td><td>"+locality+"</td><td>"+landmark+"</td>" +
 	    	 		"<td>" + is_sheltered + "</td><td>" + alias1 + "</td><td>" + alias2 + "</td><td>" + alias3 + "</td><td>" + alias4 + "</td><td>" + alias5 + "</td><td>" + alias6 + "</td><td>" + k_alias_1 + "</td><td>" + k_alias_2 + "</td><td>" + area_population + "</td> " +
 	    	 		"<td>" + stop_size + "</td><td>" + fare_stage + "</td><td>" + description + "</td><td>" + status1 + "</td></tr>";
   	     	  }*/
         
    	     //System.out.println("result----------"+result);
    	   
    	     for(int i=0;i<result.size();i++){
    	    	 String busstopname=(String)((ArrayList)result.get(i)).get(0).toString();
    	    	 String busstopnameinkannda=(String)((ArrayList)result.get(i)).get(1).toString();
    	    	 String busstopcode=(String)((ArrayList)result.get(i)).get(2).toString();
    	    	 String busstopcodeinkannda=(String)((ArrayList)result.get(i)).get(3).toString();
    	    	 String locality=(String)((ArrayList)result.get(i)).get(4).toString();
    	    	 String landmark=(String)((ArrayList)result.get(i)).get(5).toString();
    	    	 String is_sheltered=(String)((ArrayList)result.get(i)).get(6).toString();
    	    	 String alias1=(String)((ArrayList)result.get(i)).get(7).toString();
    	    	 String alias2=(String)((ArrayList)result.get(i)).get(8).toString();
    	    	 String alias3=(String)((ArrayList)result.get(i)).get(9).toString();
    	    	 String alias4=(String)((ArrayList)result.get(i)).get(10).toString();
    	    	 String alias5=(String)((ArrayList)result.get(i)).get(11).toString();
    	    	 String alias6=(String)((ArrayList)result.get(i)).get(12).toString();
    	    	 String k_alias_1=(String)((ArrayList)result.get(i)).get(13).toString();
    	    	 String k_alias_2=(String)((ArrayList)result.get(i)).get(14).toString();
    	    	 String area_population=(String)((ArrayList)result.get(i)).get(15).toString();
    	    	 String stop_size=(String)((ArrayList)result.get(i)).get(16).toString();
    	    	 String fare_stage=(String)((ArrayList)result.get(i)).get(17).toString();
    	    	 String description=(String)((ArrayList)result.get(i)).get(18).toString();
    	    	 String status1=(String)((ArrayList)result.get(i)).get(19).toString();
    	    	
    	    	
    	    	 pdfBuffer += "<tr><td>" + busstopname + "</td><td>"+ busstopnameinkannda +"</td><td>"+ busstopcode +"</td><td>"+busstopcodeinkannda+"</td><td>"+locality+"</td><td>"+landmark+"</td>" +
    	    	 		"<td>" + is_sheltered + "</td><td>" + alias1 + "</td><td>" + alias2 + "</td><td>" + alias3 + "</td><td>" + alias4 + "</td><td>" + alias5 + "</td><td>" + alias6 + "</td><td>" + k_alias_1 + "</td><td>" + k_alias_2 + "</td><td>" + area_population + "</td> " +
    	    	 		"<td>" + stop_size + "</td><td>" + fare_stage + "</td><td>" + description + "</td><td>" + status1 + "</td></tr>";
    	     }
             
            pdfBuffer += "</tbody></table></body></html>";
//            System.out.println("pdfBuffer-------"+pdfBuffer);
            //renderer.setDocumentFromString(pdfBuffer);
           // renderer.layout();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + "." + fileType + "");
           // renderer.createPDF(outputStream);
            outputStream.flush();
            outputStream.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}
	
		
	public String getBusstoplist() throws IOException{

		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		BusStopReportDao busstopdao = new BusStopReportDao();
		//int cnt = busstopdao.getTotalRecords();
		//System.out.println("Count------>"+cnt);
		String[] cols = { "Bus Stop Id ", "Bus Stop Name", "Bus Stop Name (Kanadda)", "Bus Stop Code (English)", "Bus Stop Code (Kanadda)",
		"Locality ","Landmark", "Is Sheltered", "Alias 1", "Alias 2", "Alias 3","Alias 4","Alias 5","Alias 6",
		"Kannada Alias 1","Kannada Alias 2", "Toll Zone", "City Limit", "Ward No", " Area Population","Towards",
		"Stop Size","Fare Stage", "Decscription","Point Type", "Status"};
		
		String[] dbcol={"id","busStopName","busStopNameKannada","bus_stop_code","bus_stop_code_kannada",
				"locality","landmark","sheltered","alias1","alias2","alias3","alias4","alias5","alias6",
				"kalias1","k_alias_2","tollZone","cityLimit","wardNumber","areaPopulation","stop_direction",
				"stopSize","fareStage","description","point_type_id","status"};
		JSONObject result = new JSONObject();
		int amount = 10;
		int start = 0;
		int col = 0;
		String dir = "asc";
		String sStart = request.getParameter("iDisplayStart");
		String sAmount = request.getParameter("iDisplayLength");
		String sCol = request.getParameter("iSortCol_0");
		String sdir = request.getParameter("sSortDir_0");
		String status=request.getParameter("status");
		
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
			total = busstopdao.getTotalRecords(status);
			AMOUNT = amount;
			SEARCH_TERM = request.getParameter("sSearch");
			COL_NAME = colName;
			DIR = dir;
			START = start;
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();
//			System.out.println("REsult of datatable------>"+dbcol[Integer.parseInt(sCol)]+"========="+sdir);
			//result = busstopdao.getData(total, request,dbcol[Integer.parseInt(sCol)],sdir);
			BusStopReportDao bustopsreport=new BusStopReportDao();
			result=bustopsreport.getbusstoplist(total,status, request,dbcol[Integer.parseInt(sCol)],sdir);
//			System.out.println("result----------"+result);
			out.print(result);
			return null;
	}
	
	
	 
     
     
	public String getShowBusstop(){
		System.out.println("test");
		 return "success";
	}

}
