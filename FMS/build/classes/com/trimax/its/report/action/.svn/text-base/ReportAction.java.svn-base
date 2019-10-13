package com.trimax.its.report.action;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.json.simple.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import com.trimax.its.report.dao.ReportDao;
import com.trimax.its.report.model.ReportDetail;
import com.trimax.its.report.model.ReportFilter;
import com.trimax.its.report.model.ReportHeader;
import com.trimax.its.report.model.ReportRelation;
import com.trimax.its.util.HibernateUtil;



public class ReportAction extends ActionSupport implements Preparable{

	private ReportHeader reportHeader;
	private ReportDetail reportDetail;
	private String str[];
	private Map<String,String> tableMap;
	private Map<String,String> reportHeaderMap;
	private String encryptuser;
	private String encryptpassword;
	private  String formats1;
	private String filterColumn;
	private String sortColumn;
	private Map<String ,String> getColumn=new HashMap<String, String>();
	private String filtervalue=null;
	private String sortvalue=null;
    private  String filtersubstring=null;
    private String sortSubstring=null;
    private  String splittedWhere;
    private    String filtercolumn=null;
    private    String sortName1=null;
    private List<String> selCol=new ArrayList<String>();
    private List<String> DefaulColumn=new ArrayList<String>();
    private List<String> ColumnName=new ArrayList<String>();
    private String aggColumn;
    private String aggFuction;
    private String aggsessioncolumn=null;
    private String aggcolumnvalue=null;
    private String aggSubString=null;
    private String requestType=null;
    
    public static final String DEFAULT_FILENAME = "BIRTReport"; //$NON-NLS-1$
	  //private HttpSession session=null;//request.getSession(true);
	//private int birtPort=61107;
	
	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getFilterColumn() {
		return filterColumn;
	}

	public void setFilterColumn(String filterColumn) {
		this.filterColumn = filterColumn;
	}

	public String getAggColumn() {
		return aggColumn;
	}

	public void setAggColumn(String aggColumn) {
		this.aggColumn = aggColumn;
	}

	@SkipValidation
	public String view() {
		return "view";
	}
	
	@SkipValidation
	public String homeMenu() {
		return "home";
	}
	public String[] getconfig(){
//		HibernateUtil.getconfig().getProperty("connection.url");
		str=new String[3];
		
			str[0]=HibernateUtil.getconfig().getProperty("connection.url");
			//System.out.println("array value"+str[0]);
			
			
			encryptuser=decodeParam(str[1]=HibernateUtil.getconfig().getProperty("connection.username"));
			encryptpassword=decodeParam(str[2]=HibernateUtil.getconfig().getProperty("connection.password"));	
		String uname=HibernateUtil.getconfig().getProperty("connection.username");
		System.out.println("usser name --"+HibernateUtil.getconfig().getProperty("connection.username"));
		System.out.println(encryptuser);
		return str;
		
		
		
	
	}
	
	public String  decodeParam(String value){
	    String  decodedParam = "";
	    char character;
	    char temp ;
	    for( int i=0;i<value.length();i++){
	        switch(character=value.charAt(i)){
	        case 'a':temp='h';
	        break;
	        case 'A':temp='H';
            break;
            case 'b':temp='q';
            break;           
            case 'B': temp='Q';
            break;
            case 'c':temp='y';
            break;
            case 'C':temp='Y';
            break;
            case 'd':temp='z';
            break;
            case 'D':temp='Z';
            break;
            case 'e':temp='g';
            break;
            case 'E':temp='G';
            break;
            case 'f':temp='p';
            break;
            case 'F':temp='P';
            break;
            case 'g':temp='x';
            break;
            case 'G':temp='X';
            break;
            case 'h':temp='o';
            break;
            case 'H':temp='O';
            break;
            case 'i':temp='v';
            break;
            case 'I':temp='V';
            break;
            case 'j':temp='f';
            break;
            case 'J':temp='F';
            break;
            case 'k':temp='n';
            break;
            case 'K':temp='N';
            break;
            case 'l':temp='e';
            break;
            case 'L':temp='E';
            break;
            case 'm':temp='u';
            break;
            case 'M':temp='U';
            break;
            case 'n':temp='w';
            break;
            case 'N':temp='W';
            break;
            case 'o':temp='m';
            break;
            case 'O':temp='M';
            break;
            case 'p':temp='d';
            break;
            case 'P':temp='D';
            break;
            case 'q':temp='t';
            break;
            case 'Q':temp='T';
            break;
            case 'r':temp='l';
            break;
            case 'R':temp='L';
            break;
            case 's':temp='k';
            break;
            case 'S':temp='K';
            break;
            case 't':temp='c';
            break;
            case 'T':temp='C';
            break;
            case 'u':temp='s';
            break;
            case 'U':temp='S';
            break;
            case 'v':temp='j';
            break;
            case 'V':temp='J';
            break;
            case 'w':temp='b';
            break;
            case 'W':temp='B';
            break;
            case 'x':temp='r';
            break;
            case 'X':temp='R';
            break;
            case 'y':temp='a';
            break;
            case 'Y':temp='A';
            break;
            case 'z':temp='i';
            break;
            case '0':temp='9';
            break;
            case '1':temp='8';
            break;
            case '2':temp='7';
            break;
            case '3':temp='6';
            break;
            case '4':temp='5';
            break;
            case '5':temp='4';
            break;
            case '6':temp='3';
            break;
            case '7':temp='2';
            break;
            case '8':temp='1';
            break;
            case '9':temp='0';
            
            break;
            case '!':temp='&';
            break;
            case '@':temp='^';
            break;
            case '#':temp='@';
            break;
            case '$':temp='$';
            break;
            case '%':temp='%';
            break;
            case '^':temp='#';
            break;
            case '+':temp=',';
            break;
            case '-':temp='.';
            break;
            case '_':temp='=';
            break;
            case '.':temp='_';
            break;
            case ',':temp='+';
            break;
            
            
            
            default:temp='*';
	        }
	    decodedParam = decodedParam + temp;
	    }
//	    reportContext.setPersistentGlobalVariable("temp",decodedParam);
	    return decodedParam;
	}

	@SkipValidation
	public String setParameters(int  id){
         
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		ReportDao dao=new ReportDao();
		 Set<String>  viewColumns =new HashSet<String>();  
		//String code=id;
		
		// int id=dao.getReportHeaderByCode(code);//Integer.parseInt(request.getParameter("id"));
		
		request.getSession().setAttribute("tableid",id);
		
		List<ReportDetail> list=dao.getReportDetail(id);
		  //Collection);
	//	Collections.sort(list);
		List<ReportFilter> rfList=dao.getReportFilterById(id);
		List<ReportRelation> rrList=dao.getReportRelationById(id);
		
		//ReportDetail rHeader=list.get(1);
		
		String colName="";
		String operator="";
		String value="";
		String groupBy="";
		String sortKey=list.get(0).getColumnName().toString();//"rate_master_id";
		//String dataSet="rate_master";
		String dataSet="revenue_sector";
		String reportTitle="";
		String whereClause="";
		String joincondition=" ";
		String innerJoin=" ";
		String InnerWhere="   ";
		String InnerOrder=" ";
		StringBuffer dynAggCols=new StringBuffer();//aggco,lumns
	//	System.out.println("join Query"+joincondition);
		
    	 viewColumns.add(sortKey);
    	 //System.out.println("Hi innerWhere--->"+InnerWhere);
	
		//datasett
		List<ReportHeader> rhList=dao.getReportHeaderById(id);
		
		if(rhList.size()>0){
		dataSet=rhList.get(0).getReportTableName();
		reportTitle=rhList.get(0).getReportTitle();
		}
		
		//dyn cols
		//String dynCols="";
		//String dynColsName="";
		  StringBuffer dynCols=new StringBuffer();
		  StringBuffer  dynColsName=new StringBuffer();
		for(int i=list.size()-1;i>=0;i--){
			
			if(list.get(i).getDefaultColumn().equals("Y")){
			
				
			
					  viewColumns.add(list.get(i).getColumnName());
					  dynCols.append("&DynColumns="+list.get(i).getColumnName());
				      dynColsName.append("&DynColumnsName="+list.get(i).getAlias());
				      DefaulColumn.add(list.get(i).getColumnName());
				      getColumn.put(list.get(i).getColumnName(),list.get(i).getAlias());
				      request.getSession().setAttribute("Map", getColumn);
				      request.getSession().setAttribute("Default", DefaulColumn);
					
				}else{
					   viewColumns.add(list.get(i).getColumnName());
//					  dynCols.append("&DynColumns="+list.get(i).getColumnName());
//				      dynColsName.append("&DynColumnsName="+list.get(i).getAlias());
				      getColumn.put(list.get(i).getColumnName(),list.get(i).getAlias());
				      request.getSession().setAttribute("Map", getColumn);
					
				}
		
		}
		
           for(int i=rfList.size()-1;i>=0;i--){//for  innner join condition
			
					innerJoin=rfList.get(i).getColumnName();
					viewColumns.add(rfList.get(i).getColumnName());
					
				
				}
		
	
		for (int i = rfList.size() - 1; i >= 0; i--) {

			if (rfList.get(i).getDefaultColumn().equals("Y")) {

				if (whereClause.isEmpty()) {

				//	System.out.println("splittedWhere" + splittedWhere);

					whereClause += " where " + rfList.get(i).getColumnName()
							+ " " + rfList.get(i).getOperator() + " "
							+ rfList.get(i).getValue();
					viewColumns.add(rfList.get(i).getColumnName());
				} else {
					whereClause += " and " + rfList.get(i).getColumnName()
							+ " " + rfList.get(i).getOperator() + " "
							+ rfList.get(i).getValue();
					viewColumns.add(rfList.get(i).getColumnName());
				}

			}
		}
		
		for (int i = rrList.size() - 1; i >= 0; i--) {// for join Condition....

			joincondition += rrList.get(i).getJoinType() + " "
					+ rrList.get(i).getJoinCondition() + "  ";
			//System.out.println("joindd888 " + joincondition);

		}
		String innerQString = "";
		Iterator iter = viewColumns.iterator();
		while (iter.hasNext()) {
			innerQString += (String) (iter.next()) + " ,";
			// System.out.println(iter.next());
		}
		String innerString = innerQString.trim().substring(0,innerQString.length() - 1);
		request.getSession().setAttribute("innerQueryAgg", innerString);
		request.getSession().setAttribute("innerQuery", viewColumns);
		//System.out.println("joinConditon" + joincondition);
		
	//	dynCols=(String)request.getSession().getAttribute("dynCols");
		//dynColsName=(String)request.getSession().getAttribute("dynColsName");
		request.getSession().setAttribute("dynCols", dynCols.toString());
		request.getSession().setAttribute("dynColsName", dynColsName.toString());
		request.getSession().setAttribute("whereClause", whereClause);
		String whereClauseCheck=whereClause;
		request.getSession().setAttribute("whereClauseCheck", whereClauseCheck);
		String sortcheck="Order By "+sortKey;
		request.getSession().setAttribute("SortKey",sortcheck );
		 
		//System.out.println(new Date()+" whereClause="+request.getSession().getAttribute("whereClause"));
		
		return whereClause;
		
	}
	
	@SkipValidation
	public String generateReport() {
		try{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		getconfig();	
		  HttpSession session=request.getSession();  
		String code=(request.getParameter("id"));
		ReportDao dao=new ReportDao();
		int id=dao.getReportHeaderByCode(code);//Integer.parseInt(request.getParameter("id"));
		String wheregen=setParameters( id);

         
		java.util.Date  ss1=new Date();
		SimpleDateFormat formatter5=new SimpleDateFormat("dd/MM/yy");
	    formats1 = formatter5.format(ss1);
	//	System.out.println("date"+formats1);
		  session.removeAttribute("whereClauseAgg");
		  session.removeAttribute("Selectdyncols");
		  session.removeAttribute("SelectdynCols");
		  session.removeAttribute("SelectdynColsName");
		  session.removeAttribute("SortCondition");
	
		 Set<String>  viewColumns =new HashSet<String>();  
	
		request.getSession().setAttribute("Code",code);
	
		
		request.getSession().setAttribute("tableid",id);
		
		List<ReportDetail> list=dao.getReportDetail(id);
		  //Collection);
	//	Collections.sort(list);
		List<ReportFilter> rfList=dao.getReportFilterById(id);
		List<ReportRelation> rrList=dao.getReportRelationById(id);
		
		//ReportDetail rHeader=list.get(1);
		
		String colName="";
		String operator="";
		String value="";
		String groupBy="";
		String sortKey=list.get(0).getColumnName();//"rate_master_id";
		//String dataSet="rate_master";
		String dataSet="revenue_sector";
		String reportTitle="";
		String whereClause="";
		String joincondition=" ";
		String innerJoin=" ";
		String InnerWhere="   ";
		String InnerOrder=" ";
		StringBuffer dynAggCols=new StringBuffer();//aggco,lumns
		//System.out.println("join Query"+joincondition);
		
    	 viewColumns.add(sortKey);
    	// System.out.println("Hi innerWhere--->"+InnerWhere);
	
		//datasett
		List<ReportHeader> rhList=dao.getReportHeaderById(id);
		
		if(rhList.size()>0){
		dataSet=rhList.get(0).getReportTableName();
		reportTitle=rhList.get(0).getReportTitle();
		}
		
		//dyn cols
		StringBuffer dynCols=new StringBuffer();
		StringBuffer dynColsName=new StringBuffer();
		
		for(int i=list.size()-1;i>=0;i--){
			
			if(list.get(i).getDefaultColumn().equals("Y")){
			
				
			
					  viewColumns.add(list.get(i).getColumnName());
					  dynCols.append("&DynColumns="+list.get(i).getColumnName());
				      dynColsName.append("&DynColumnsName="+list.get(i).getAlias());
				      DefaulColumn.add(list.get(i).getColumnName());
				      getColumn.put(list.get(i).getColumnName(),list.get(i).getAlias());
				      request.getSession().setAttribute("Map", getColumn);
				      request.getSession().setAttribute("Default", DefaulColumn);
					
				}else{
					
//					  dynCols.append("&DynColumns="+list.get(i).getColumnName());
//				      dynColsName.append("&DynColumnsName="+list.get(i).getAlias());
				      getColumn.put(list.get(i).getColumnName(),list.get(i).getAlias());
				      request.getSession().setAttribute("Map", getColumn);
					
				}
		
		}
		
           for(int i=rfList.size()-1;i>=0;i--){//for  innner join condition
			
					innerJoin=rfList.get(i).getColumnName();
					viewColumns.add(rfList.get(i).getColumnName());
					
				
				}
		
	
		for (int i = rfList.size() - 1; i >= 0; i--) {

			if (rfList.get(i).getDefaultColumn().equals("Y")) {

				if (whereClause.isEmpty()) {

					//System.out.println("splittedWhere" + splittedWhere);

					whereClause += " where " + rfList.get(i).getColumnName()
							+ " " + rfList.get(i).getOperator() + " "
							+ rfList.get(i).getValue();
					viewColumns.add(rfList.get(i).getColumnName());
				} else {
					whereClause += " and " + rfList.get(i).getColumnName()
							+ " " + rfList.get(i).getOperator() + " "
							+ rfList.get(i).getValue();
					viewColumns.add(rfList.get(i).getColumnName());
				}

			}
		}
		
		for (int i = rrList.size() - 1; i >= 0; i--) {// for join Condition....

			joincondition += rrList.get(i).getJoinType() + " "
					+ rrList.get(i).getJoinCondition() + "  ";
			//System.out.println("joindd888 " + joincondition);

		}
		String innerQString = "";
		Iterator iter = viewColumns.iterator();
		while (iter.hasNext()) {
			innerQString += (String) (iter.next()) + " ,";
			// System.out.println(iter.next());
		}
		String innerString = innerQString.trim().substring(0,innerQString.length() - 1);
		request.getSession().setAttribute("innerQueryAgg", innerString);
		request.getSession().setAttribute("innerQuery", viewColumns);
		//System.out.println("joinConditon" + joincondition);
		request.getSession().setAttribute("dynCols", dynCols.toString());
		request.getSession().setAttribute("dynColsName", dynColsName.toString());
		request.getSession().setAttribute("whereClause", whereClause);
		request.getSession().setAttribute("whereClauseCheck", whereClause);
		if(request.isSecure()){
			requestType="https";
		}else{
			requestType="http";
		}
		
		/*String url="http://localhost:8080/its/birtrep/frameset?__report=ITSRREPORT.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&Columns="+colName+"&SortKey="+sortKey+"&AscDesc=asc&String="+operator+"&Version="+value+"&GroupBy="+groupBy+"&DataSet="+dataSet
				   +dynCols.toString()+"&ReportTitle="+reportTitle+dynColsName.toString()+"&where="+whereClause+"&url="+str[0]+"&user="+encryptuser+"&pass="+encryptpassword+"&currentdate="+dynAggCols+"&JoinCondition="+joincondition+"&innerQuery="+innerString+"&InnerWhere="+InnerWhere;//+"&InnerOrder="+InnerOrder;
		*/
		String url="https"+"://"+request.getServerName()+"/birt/frameset?__report=ITSREPORT.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&Columns="+colName+"&SortKey="+sortKey+"&AscDesc=asc&String="+operator+"&Version="+value+"&GroupBy="+groupBy+"&DataSet="+dataSet
		   +dynCols.toString()+"&ReportTitle="+reportTitle+dynColsName.toString()+"&where="+whereClause+"&url="+str[0]+"&user="+encryptuser+"&pass="+encryptpassword+"&currentdate="+dynAggCols+"&JoinCondition="+joincondition+"&innerQuery="+innerString+"&InnerWhere="+InnerWhere;//"+&InnerOrder="+InnerOrder;
			
		//String url="http://localhost:8080/its/birtrep/ITSREPORT.rptdesign";
/*	String url="http://127.0.0.1:54862/viewer/frameset?__report=%2Froot%2Fworkspaceofeclipseneon%2Fits%2FWebContent%2Fbirt%2FMASTERREPORT.rptdesign&__format=html&__svg=true&__locale=en_US&__timezone=IST&__masterpage=true&__rtl=false&__cubememsize=10&__resourceFolder=%2Froot%2Fworkspaceofeclipseneon%2Fits&489394093&Columns="+colName+"&SortKey="+sortKey+"&AscDesc=asc&String="+operator+"&Version="+value+"&GroupBy="+groupBy+"&DataSet="+dataSet
			   +dynCols.toString()+"&ReportTitle="+reportTitle+dynColsName.toString()+"&where="+whereClause+"&url="+str[0]+"&user="+encryptuser+"&pass="+encryptpassword+"&currentdate="+formats1+"&currentdate="+dynAggCols+"&JoinCondition="+joincondition+"&innerQuery="+innerString+"&InnerWhere="+InnerWhere;//+"&InnerOrder="+InnerOrder;
*/System.out.println("url is==="+url);
	//String url=requestType+"://"+request.getServerName()+":"+request.getLocalPort()+"/birt/frameset?__report=ITSREPORT.rptdesign&amp;__format=html&amp";
		//System.out.println("ptahs="+request.getLocalAddr()+","+request.getLocalPort());
		//System.out.println("url=>> "+url);
		request.getSession().setAttribute("url",url);
		
//	
		 String sortcondtion= " Order By  "+sortKey;
	///	 request.getSession().setAttribute("SortKey", "Order By"+sortKey);
			request.getSession().setAttribute("SortKey",sortcondtion);   
		
	/*	try{
			
			//response.sendRedirect(url);
			//RequestDispatcher rd=request.getRequestDispatcher("/Reports/ReportViewer.jsp?url="+url);  
			//servlet2 is the url-pattern of the second servlet  
			  
			//rd.forward(request, response);
			//PrintWriter out=response.getWriter();
			//out.print(url);
		
	//	}
		catch(Exception e){
			e.printStackTrace();
		}*/
		
		//tableMap=dao.getTableName();
		//reportHeaderMap=dao.getReportHeader();
		//return "birt";   //newlly add for using try catch
			System.out.println("userid-------"+session.getAttribute("user"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "birt";
	}
	
	@SkipValidation
	public String generateLineReport() {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		getconfig();	
		  HttpSession session=request.getSession();  
		String code=(request.getParameter("id"));
		ReportDao dao=new ReportDao();
		int id=dao.getReportHeaderByCode(code);//Integer.parseInt(request.getParameter("id"));
		String wheregen=setParameters( id);

         
		java.util.Date  ss1=new Date();
		SimpleDateFormat formatter5=new SimpleDateFormat("dd/MM/yy");
	    formats1 = formatter5.format(ss1);
		  session.removeAttribute("whereClauseAgg");
		  session.removeAttribute("Selectdyncols");
		  session.removeAttribute("SelectdynCols");
		  session.removeAttribute("SelectdynColsName");
		  session.removeAttribute("SortCondition");
	
		 Set<String>  viewColumns =new HashSet<String>();  
	
		request.getSession().setAttribute("Code",code);
	
		
		request.getSession().setAttribute("tableid",id);
		
		List<ReportDetail> list=dao.getReportDetail(id);
		  //Collection);
	//	Collections.sort(list);
		List<ReportFilter> rfList=dao.getReportFilterById(id);
		List<ReportRelation> rrList=dao.getReportRelationById(id);
		
		//ReportDetail rHeader=list.get(1);
		
		String colName="";
		String operator="";
		String value="";
		String groupBy="";
		String sortKey=list.get(0).getColumnName();//"rate_master_id";
		//String dataSet="rate_master";
		String dataSet="line_sector";
		String reportTitle="";
		String whereClause="";
		String joincondition=" ";
		String innerJoin=" ";
		String InnerWhere="   ";
		String InnerOrder=" ";
		StringBuffer dynAggCols=new StringBuffer();//aggco,lumns
		//System.out.println("join Query"+joincondition);
		
    	 viewColumns.add(sortKey);
    	// System.out.println("Hi innerWhere--->"+InnerWhere);
	
		//datasett
		List<ReportHeader> rhList=dao.getReportHeaderById(id);
		
		if(rhList.size()>0){
		dataSet=rhList.get(0).getReportTableName();
		reportTitle=rhList.get(0).getReportTitle();
		}
		
		//dyn cols
		StringBuffer dynCols=new StringBuffer();
		StringBuffer dynColsName=new StringBuffer();
		for(int i=list.size()-1;i>=0;i--){
			
			if(list.get(i).getDefaultColumn().equals("Y")){
			
				
			
					  viewColumns.add(list.get(i).getColumnName());
					  dynCols.append("&DynColumns="+list.get(i).getColumnName());
				      dynColsName.append("&DynColumnsName="+list.get(i).getAlias());
				      DefaulColumn.add(list.get(i).getColumnName());
				      getColumn.put(list.get(i).getColumnName(),list.get(i).getAlias());
				      request.getSession().setAttribute("Map", getColumn);
				      request.getSession().setAttribute("Default", DefaulColumn);
					
				}else{
					
//					  dynCols.append("&DynColumns="+list.get(i).getColumnName());
//				      dynColsName.append("&DynColumnsName="+list.get(i).getAlias());
				      getColumn.put(list.get(i).getColumnName(),list.get(i).getAlias());
				      request.getSession().setAttribute("Map", getColumn);
					
				}
		
		}
		
           for(int i=rfList.size()-1;i>=0;i--){//for  innner join condition
			
					innerJoin=rfList.get(i).getColumnName();
					viewColumns.add(rfList.get(i).getColumnName());
					
				
				}
		
	
		for (int i = rfList.size() - 1; i >= 0; i--) {

			if (rfList.get(i).getDefaultColumn().equals("Y")) {

				if (whereClause.isEmpty()) {

					//System.out.println("splittedWhere" + splittedWhere);

					whereClause += " where " + rfList.get(i).getColumnName()
							+ " " + rfList.get(i).getOperator() + " "
							+ rfList.get(i).getValue();
					viewColumns.add(rfList.get(i).getColumnName());
				} else {
					whereClause += " and " + rfList.get(i).getColumnName()
							+ " " + rfList.get(i).getOperator() + " "
							+ rfList.get(i).getValue();
					viewColumns.add(rfList.get(i).getColumnName());
				}

			}
		}
		
		for (int i = rrList.size() - 1; i >= 0; i--) {// for join Condition....

			joincondition += rrList.get(i).getJoinType() + " "
					+ rrList.get(i).getJoinCondition() + "  ";
			//System.out.println("joindd888 " + joincondition);

		}
		String innerQString = "";
		Iterator iter = viewColumns.iterator();
		while (iter.hasNext()) {
			innerQString += (String) (iter.next()) + " ,";
			// System.out.println(iter.next());
		}
		String innerString = innerQString.trim().substring(0,innerQString.length() - 1);
		request.getSession().setAttribute("innerQueryAgg", innerString);
		request.getSession().setAttribute("innerQuery", viewColumns);
		//System.out.println("joinConditon" + joincondition);
		request.getSession().setAttribute("dynCols", dynCols.toString());
		request.getSession().setAttribute("dynColsName", dynColsName.toString());
		request.getSession().setAttribute("whereClause", whereClause);
		request.getSession().setAttribute("whereClauseCheck", whereClause);
		if(request.isSecure()){
			requestType="https";
		}else{
			requestType="http";
		}
		
		//System.out.println(new Date()+" whereClause="+request.getSession().getAttribute("whereClause"));

		/*String url=requestType+"://"+request.getServerName()+":"+request.getLocalPort()+"/birt/frameset?__report=ITSREPORT.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&Columns="+colName+"&SortKey="+sortKey+"&AscDesc=asc&String="+operator+"&Version="+value+"&GroupBy="+groupBy+"&DataSet="+dataSet
				   +dynCols.toString()+"&ReportTitle="+reportTitle+dynColsName.toString()+"&where="+whereClause+"&url="+str[0]+"&user="+encryptuser+"&pass="+encryptpassword+"&currentdate="+dynAggCols+"&JoinCondition="+joincondition+"&innerQuery="+innerString+"&InnerWhere="+InnerWhere;//+"&InnerOrder="+InnerOrder;
		*/
		
		String url="https"+"://"+request.getServerName()+"/birt/frameset?__report=ITSREPORT.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&Columns="+colName+"&SortKey="+sortKey+"&AscDesc=asc&String="+operator+"&Version="+value+"&GroupBy="+groupBy+"&DataSet="+dataSet
				   +dynCols.toString()+"&ReportTitle="+reportTitle+dynColsName.toString()+"&where="+whereClause+"&url="+str[0]+"&user="+encryptuser+"&pass="+encryptpassword+"&currentdate="+dynAggCols+"&JoinCondition="+joincondition+"&innerQuery="+innerString+"&InnerWhere="+InnerWhere;//+"&InnerOrder="+InnerOrder;
		
		
		//System.out.println("ptahs="+request.getLocalAddr()+","+request.getLocalPort());
		//System.out.println("url=>> "+url);
		request.getSession().setAttribute("url",url);
		
//	
		 String sortcondtion= " Order By  "+sortKey;
	///	 request.getSession().setAttribute("SortKey", "Order By"+sortKey);
			request.getSession().setAttribute("SortKey",sortcondtion);   
		
	/*	try{
			
			//response.sendRedirect(url);
			//RequestDispatcher rd=request.getRequestDispatcher("/Reports/ReportViewer.jsp?url="+url);  
			//servlet2 is the url-pattern of the second servlet  
			  
			//rd.forward(request, response);
			//PrintWriter out=response.getWriter();
			//out.print(url);
		
	//	}
		catch(Exception e){
			e.printStackTrace();
		}*/
		
		//tableMap=dao.getTableName();
		//reportHeaderMap=dao.getReportHeader();
		return "birt";
	}
	
	
	
	@SkipValidation
	public String getCustomReport() {
		getconfig();
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		ReportDao dao=new ReportDao();
		int id=0;
		String selectdyncols="";
		
//	
		    try{
		    	id=Integer.parseInt(request.getParameter("id"));
		    //	int id=dao.getReportHeaderByCode(code);//Integer.parseInt(request.getParameter("id"));
				String wheregen=setParameters( id);
		    	///System.out.println("Hii in Sortdddddddddddddddddd->>>>>>>"+sortColumn);
		       int startIndex = sortColumn.indexOf("0,");
		       int startIndex1 = sortColumn.indexOf(",");
        // System.out.println("Hii"+startIndex);
               int endIndex = sortColumn.indexOf(",");
               String sortcolumn = sortColumn.substring(startIndex + 1);
               sortName1=sortcolumn.substring(endIndex);//sort column name as table name
               Map<String, String> map2=(Map<String, String>)request.getSession().getAttribute("Map");
             //  System.out.println("to be "+sortName1);
              // System.out.println("to session map "+map2+"sort"+sortName1);
//            
               String sortvalues[]=sortName1.trim().split(",");
            
     
        	     if(sortvalues.length==1){
        		 
        		 sortvalue=map2.get(sortvalues[0]);
        //		 System.out.println("osrt====> " +sortvalue);
        	    
             	
                 }else{
            	 
            	          for(int i=0;i<sortvalues.length;i++){
            		      if(!(sortvalues[0]==null ||sortvalues[0].equals("") ||sortvalues[0].equals("null"))){
            		      String keyvalue=sortvalues[i].trim();
            		      sortvalue+=map2.get(keyvalue).trim()+",";
            		              		 
            		       }
            		 
            	        }
             	
             // }
            	          }	
               }catch (Exception e) {
        	
        	
			// TODO: handle exception
		  }
       
        
        
        
       Set<String> innerqSet=new HashSet<String>();
       innerqSet=(Set)request.getSession().getAttribute("innerQuery");
        
        
    
		
		List<ReportDetail> list=dao.getReportDetail(id);
		
		List<ReportFilter> rfList=dao.getReportFilterById(id);
		List<ReportRelation> rrList=dao.getReportRelationById(id);
		
		String colName="";
		String operator="";
		String value="";
		String groupBy="";
		String sortKey=list.get(0).getColumnName();//"rate_master_id";
		String dataSet="rate_master";
		String reportTitle="";
		String whereClause="";
		String url="";
		String joincondition="";//for join Condition
	   // Set<String>  innerJoin = new HashSet<String>();
		String innerJoin=" "; 
		String InnerWhere="";  
		String InnerOrder=" ";                    // for inner Query
		//dyn cols
		StringBuffer dynAggCols=new StringBuffer();//aggco,lumns
		StringBuffer dynCols=new StringBuffer();
		StringBuffer dynColsName=new StringBuffer();
		String innerString="";		
		
		
		
		
		
		
		try{
			
			
			
			
			List<String> sessionColumnsSort=(List<String>)request.getSession().getAttribute("SelectWhere");
	        
			  
	         //System.out.println("SessionColumns "+sessionColumnsSort);
		//datasett
		List<ReportHeader> rhList=dao.getReportHeaderById(id);
		
		if(rhList.size()>0){
		dataSet=rhList.get(0).getReportTableName();
		reportTitle=rhList.get(0).getReportTitle();
		}
       for(int i=0;i<rrList.size();i++){//for joinCondition
		
		     joincondition+=rrList.get(i).getJoinType()+" "+rrList.get(i).getJoinCondition()+ "  "  ;
			//System.out.println("joindd888 "+joincondition);
				
           }
        for(int i=rfList.size()-1;i>=0;i--){// for inner join 
			
			//innerJoin.add(rfList.get(i).getColumnName());
        	innerJoin=rfList.get(i).getColumnName();
			//System.out.println("dfdf==="+innerJoin);
			
		}
		//sort
        

		for(int i=rfList.size()-1;i>=0;i--){
			
			if(rfList.get(i).getDefaultColumn().equals("Y")){
				if(whereClause.isEmpty()){
					whereClause+=" where "+rfList.get(i).getColumnName()+" "+rfList.get(i).getOperator()+" "+rfList.get(i).getValue();
				}else{
					whereClause+=" and "+rfList.get(i).getColumnName()+" "+rfList.get(i).getOperator()+" "+rfList.get(i).getValue();
				}
			}
		}
        
				String sort=request.getParameter("sort");
				
				if(sort!=null && (!sort.trim().isEmpty()) ){
				     Map<String, String> map3=(Map<String, String>)request.getSession().getAttribute("Map");
					List<String> Default=new ArrayList<String>();
					boolean a=sortName1.contains(",");
					if(a==true){
						String def[]=sortName1.split(",");
						 for(int i=0;i<def.length;i++){
							 String defvalue=def[i].trim();
						      ;
							 Default.add(defvalue.trim());
							// innerJoin+=","+defvalue;
							 innerqSet.add(defvalue);
							
							 
						 }
						
					}else{
						Default.add(sortName1.trim());
						innerJoin=","+sortName1.trim();
						 innerqSet.add(sortName1.trim());
						// innerJoin.add(","+sortName1.trim());
//						 dynCols.append("&DynColumns="+sortName1.trim());
//						 dynColsName.append("&DynColumnsName="+map3.get(sortName1.trim()));
						
					}
					
					
					String[] col=request.getParameterValues("sortColumn");
					String[] colorder=request.getParameterValues("orderby");
					String sortName="";
					String sortName1="";
					for(int i=1;i<col.length;i++){
						try{
							sortName1=col[i]+" "+colorder[i];//for inner Query
							if(!joincondition.isEmpty()){
								sortName1=sortName1.split("\\.")[1];
							}
							
							
						//col=request.getParameter(list.get(i).getColumnName()+"*");
						
						//if(!col.trim().isEmpty()){
						String colAliasName=list.get(i).getAlias();
						
						if(sortName.trim().isEmpty()){
							sortName=sortName1;
						}else{
						sortName=sortName+","+sortName;
						}
						
						
						//}
						}
						catch(Exception e){
							e.printStackTrace();
						}
					}
					sortKey=sortName;
					//System.out.println("hoseort"+sortKey);
					dynCols.append((String)request.getSession().getAttribute("dynCols"));	
					dynColsName.append((String)request.getSession().getAttribute("dynColsName"));
					whereClause=(String)request.getSession().getAttribute("whereClause");
					
				try{
					 
					String sortcondtion= " Order By  "+sortKey;
					   
						request.getSession().setAttribute("SortCondition",sortcondtion);  
					List<String> sessionColumns1 = (List<String>) request.getSession().getAttribute("SelectWhere");

					String sortname = "Sort By:";
					if (sortvalue.contains("null") || sortvalue == null) {

						int startIndex = sortvalue.indexOf("null");
						sortSubstring = sortvalue.substring(startIndex + 4,
								sortvalue.length() - 1);
					} else {
						sortSubstring = sortvalue;

					}
					request.getSession().setAttribute("SortAlias",
							sortSubstring);
					request.getSession().setAttribute("SortText", sortname);
				} catch (Exception e) {
                e.printStackTrace();
				}
					
					 
					
				} else{
	    	String col="";
		    for(int i=list.size()-1;i>=0;i--){
			try{
			col=request.getParameter(list.get(i).getColumnName());
			
			if(col!=null && !col.trim().isEmpty()){
			String colAliasName=list.get(i).getAlias();
			
			dynCols.append("&DynColumns="+col);
			dynColsName.append("&DynColumnsName="+colAliasName);
			selCol.add(col);
			innerqSet.add(col);
			
			request.getSession().setAttribute("SelectWhere",selCol);
			
			
			}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			}
			//18.10.2014
		    selectdyncols="SelectDyncols";
			request.getSession().setAttribute("Selectdyncols",selectdyncols);
			request.getSession().setAttribute("SelectdynCols",dynCols.toString());
			request.getSession().setAttribute("SelectdynColsName",dynColsName.toString());
			//request.getSession().setAttribute("whereClause",whereClause);
		request.getSession().setAttribute("dynCols",dynCols.toString());
		request.getSession().setAttribute("dynColsName",dynColsName.toString());
		request.getSession().setAttribute("whereClause",whereClause);
			
		
		
		//&DynColumns="+dynCols
		
		}
		}
		catch(Exception e){
			e.printStackTrace();
			
			
				
			
		}
		String innerQString = "";
		Iterator iter = innerqSet.iterator();
		while (iter.hasNext()) {
			innerQString += (String) (iter.next()) + " ,";
			// System.out.println(iter.next());
		}
		innerString = innerQString.trim().substring(0,innerQString.length() - 1);
		if(request.isSecure()){
			requestType="https";
		}else{
			requestType="http";
		}
		//url="https://localhost:57767/viewer/frameset?__report=%2Froot%2FEclipse+Indigo+Wspace%2Fitsreport%2FItsDemo.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&__resourceFolder=%2Froot%2FEclipse+Indigo+Wspace%2Fitsreport&Columns="+colName+"&AscDesc=asc&String="+operator+"&Version="+value+"&GroupBy="+groupBy+"&DataSet="+dataSet
		//				   +dynCols.toString()+dynColsName.toString()+"&ReportTitle="+reportTitle+"&SortKey="+sortKey;

//		  url="https://localhost:"+birtPort+"/viewer/frameset?__report=%2Froot%2FEclipse+Indigo+Wspace%2Fits%2FItsDemo.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&__resourceFolder=%2Froot%2FEclipse+Indigo+Wspace%2Fits&Columns="+colName+"&SortKey="+sortKey+"&AscDesc=asc&String="+operator+"&Version="+value+"&GroupBy="+groupBy+"&DataSet="+dataSet
//				   +dynCols.toString()+"&ReportTitle="+reportTitle+dynColsName.toString()+"&where="+whereClause;
//				
//				
//	      url="https://"+request.getLocalAddr()+":"+request.getLocalPort()+"/birt/frameset?__report=ItsDemo.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&__resourceFolder=%2Froot%2FEclipse+Indigo+Wspace%2Fitsreport&Columns="+colName+"&SortKey="+sortKey+"&AscDesc=asc&String="+operator+"&Version="+value+"&GroupBy="+groupBy+"&DataSet="+dataSet
//						   +dynCols.toString()+"&ReportTitle="+reportTitle+dynColsName.toString()+"&where="+whereClause;;
			
		  url="https"+"://"+request.getServerName()+"/birt/frameset?__report=ITSREPORT.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&Columns="+colName+"&SortKey="+sortKey+"&AscDesc=asc&String="+operator+"&Version="+value+"&GroupBy="+groupBy+"&DataSet="+dataSet
			   +dynCols.toString()+"&ReportTitle="+reportTitle+dynColsName.toString()+"&where="+whereClause+"&url="+str[0]+"&user="+encryptuser+"&pass="+encryptpassword+"&currentdate="+dynAggCols+"&JoinCondition="+joincondition+"&innerQuery="+innerString+"&InnerWhere="+InnerWhere;//"+&InnerOrder="+InnerOrder;
					
		/* url="http://127.0.0.1:63268/viewer/frameset?__report=%2Froot%2Fworkspaceofeclipseneon%2Fits%2FWebContent%2Fbirt%2FITSREPORT.rptdesign&__format=html&__svg=true&__locale=en_US&__timezone=IST&__masterpage=true&__rtl=false&__cubememsize=10&__resourceFolder=%2Froot%2Fworkspaceofeclipseneon%2Fits&1569096592&Columns="+colName+"&SortKey="+sortKey+"&AscDesc=asc&String="+operator+"&Version="+value+"&GroupBy="+groupBy+"&DataSet="+dataSet
				   +dynCols.toString()+"&ReportTitle="+reportTitle+dynColsName.toString()+"&where="+whereClause+"&url="+str[0]+"&user="+encryptuser+"&pass="+encryptpassword+"&currentdate="+formats1+"&currentdate="+dynAggCols+"&JoinCondition="+joincondition+"&innerQuery="+innerString+"&InnerWhere="+InnerWhere;//+"&InnerOrder="+InnerOrder;
*/
		request.getSession().setAttribute("url",url);
		
		/*try{
		
			//response.sendRedirect(url);
			RequestDispatcher rd=request.getRequestDispatcher("/Reports/ReportViewer.jsp");
			request.setAttribute("url",url);
			//servlet2 is the url-pattern of the second servlet  
			  
			rd.forward(request, response);
			// response.sendRedirect(url);
			
			//PrintWriter out=response.getWriter();
			//out.print(url);
		
		}
		catch(Exception e){
			e.printStackTrace();
		}*/
		
		//tableMap=dao.getTableName();
		//reportHeaderMap=dao.getReportHeader();
		return "birt";
	}
	

	
	
	@SkipValidation
	public String getAggregationReport() {
		getconfig();
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
	//	String code=(String)request.getSession().getAttribute("Code");
		int id=Integer.parseInt(request.getParameter("id"));
		String filterWhere=setParameters(id);
		ReportDao dao=new ReportDao();
		 
		String code=(request.getParameter("id"));
		
//		int id=dao.getReportHeaderByCode(code);
		try {

			int startIndex = aggColumn.indexOf("0,");
			//int startIndex1 = aggColumn.indexOf(",");
			//System.out.println("Hii" + startIndex);
			int endIndex = aggColumn.indexOf(",");
			String toBeReplaced = aggColumn.substring(startIndex + 1);

			aggsessioncolumn = toBeReplaced.substring(endIndex);
			//System.out.println("To be Replaced:-" + aggsessioncolumn);// filter
																	// column as
																	// table
																	// name
																	// separeted
																	// by comma
			Map<String, String> map1 = (Map<String, String>) request
					.getSession().getAttribute("Map");

			// getting map values from session

			String aggColumnsplit[] = aggsessioncolumn.trim().split(",");// split

			if (aggColumnsplit.length == 1) {
				aggcolumnvalue = map1.get(aggColumnsplit[0]);
				//System.out.println("Filter Value if 1" + aggcolumnvalue);

			} else {
                    
				for (int i = 0; i < aggColumnsplit.length; i++) {
					if (!(aggColumnsplit[0] == null || aggColumnsplit[0].equals("") || aggColumnsplit[0].equals("null"))) {
						if(!aggColumnsplit[i].trim().equals("0")){
						
						String key = aggColumnsplit[i].trim();
						//System.out.println("key=" + key);
						aggcolumnvalue += map1.get(key).trim() + ",";// getting
																	// values
																	// from map
						}								// based on
																	// key

					}

				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		//	System.out.println("Exception is" + e);

			// TODO: handle exception
		}

		List<String> sessionColumns=(List<String>)request.getSession().getAttribute("SelectWhere");
        
		//int id=Integer.parseInt(request.getParameter("id"));
		List<ReportDetail> list=dao.getReportDetail(id);
		
		List<ReportFilter> rfList=dao.getReportFilterById(id);
		
		//ReportDetail rHeader=list.get(1);
		
		String colName="";
		String operator="";
		String filterValue="";
		String groupBy="";
		String sortKey=list.get(0).getColumnName();//"rate_master_id";
		String dataSet="rate_master";
		String reportTitle="";
		String whereClause="";
		String joincondition="";
		String innerJoin=" ";
		String InnerWhere="";//For Where Condition
		String InnerOrder=" ";
		String innerString=" ";
		String Query="";
		
		//datasett
		List<ReportHeader> rhList=dao.getReportHeaderById(id);
		List<ReportRelation> rrList=dao.getReportRelationById(id);
		
		if(rhList.size()>0){
		dataSet=rhList.get(0).getReportTableName();
		reportTitle=rhList.get(0).getReportTitle();
		}
		
		for(int i=rrList.size()-1;i>=0;i--){
			
			
			
			joincondition+=rrList.get(i).getJoinType()+" "+rrList.get(i).getJoinCondition()+ "  "  ;
			//System.out.println("joindd888 "+joincondition);
			
			
		
	}
	
		
     for(int i=rfList.size()-1;i>=0;i--){
			
			
			
			innerJoin=rfList.get(i).getColumnName();
			//System.out.println("Hii filter"+innerJoin);
			
		
	}
		//dyn cols
		StringBuffer dynCols=new StringBuffer();
		StringBuffer dynColsName=new StringBuffer();
		StringBuffer dynAggCols=new StringBuffer();//aggco,lumns
		
		String[] fc=request.getParameterValues("aggColumn");//taking columns for aggregation
		String[] ff=request.getParameterValues("aggFuction");//functions applied on columns
	     Map<String, String> map3=(Map<String, String>)request.getSession().getAttribute("Map");  
		
		 Set<String> innerqSet=new HashSet<String>();
	       innerqSet=(Set)request.getSession().getAttribute("innerQuery");
		    		
		String filter = request.getParameter("filter");
		String url = "";
		boolean check=false;
		boolean groupcheck=false;
		if (filter != null || (!filter.trim().isEmpty())) {

			String col = "";
			String filterName = "";
			String aggColumns="";
			String groupby="";
			String wherecheck="";                     //taking group by columns
           //Set<String> ColumnName=new HashSet<String>();
           // List<String> ColumnName=new ArrayList<String>();
            String dynamicAlias="";
            String joingroupby="";
			int j=0;
			String dummyvalue="";
			if (fc.length > 1 && ff.length > 1 ) {

				for (int i = 0; i < fc.length; i++) {
					try {
						if (filterName.trim().isEmpty()) {
							wherecheck = (String) request.getSession()
									.getAttribute("whereClauseAgg");
							filterName=wherecheck;
							
						
							if(filterName == null || filterName.contains("null")){
						
							
							filterName=(String)request.getSession().getAttribute("whereClauseCheck");
							}
						}
						
						
						
						
						else if(!fc[i].trim().equals("0")){
							
//							
									String filterColumnName = fc[i];
							 }
							// for inner Query
						          
							String filterColumnName = fc[i];
							
						//	System.out.println("Gilter " + filterColumnName);

							if (!joincondition.isEmpty()) {
								if(!filterColumnName.trim().equals("0")){
								
										 joingroupby=filterColumnName;
									
									ColumnName.add(filterColumnName);
								   dynCols.append("&DynColumns="+filterColumnName);
								   //joingroupby=filterColumnName;
							 	     check=true;
							 	  
								   filterColumnName = filterColumnName
								.split("\\.")[1];
								}
							}

				
                           if(ff[i].equals("0")||ff[i].equals(""))	{
                        	 
                        	   if(filterColumnName.equals("0")){
                        		   
                        		   
                        		   
                        		   groupby+="";
                        	   }else{
                        		   if(!joincondition.isEmpty()){
                        		   
                        		   groupby+=filterColumnName+" ,";
                        		   ColumnName=ColumnName;
                        		   dynCols=dynCols;
                        		   }
                        		   else{
                        			   
                        			   groupby+=filterColumnName+" ,";
                            		   ColumnName.add(filterColumnName);
                            		   dynCols.append("&DynColumns="+filterColumnName);
                        		   }
                        	   }
//                                if(filterColumnName.equals("0")&& groupcheck==false){
//                        		   
//                        		   
//                        		   
//                        		   groupby+="";
//                        	   }else{
//                        		   groupby+=joincondition+" ,";
//                        		   ColumnName.add(joincondition);
//                        		   dynCols.append("&DynColumns="+joincondition);
//                        	   }
							     
                        	  
                        	   
                        	   
                           }else{
							 switch(ff[i].toString().charAt(0))
						      {
						         case 'C' :
						        		col ="COUNT("+filterColumnName +")"+filterColumnName+" ,";
						        		if(check==false){
						        		ColumnName.add(filterColumnName);
						        		dynCols.append("&DynColumns="+filterColumnName);
						        		
						        		}dynAggCols.append(col);
						            break;
						         case 'M' :
						        	 
						        		col ="MAX("+filterColumnName +")"+filterColumnName+",";
						        		if(check==false){
						        		ColumnName.add(filterColumnName);
						        		dynCols.append("&DynColumns="+filterColumnName);
						        		}dynAggCols.append(col);
						        		break;
						         case 'm' :
						        	
						        		col ="MIN("+filterColumnName +")"+filterColumnName+",";
						        		if(check==false){
						        		ColumnName.add(filterColumnName);
						        		dynCols.append("&DynColumns="+filterColumnName);
						        		}dynAggCols.append(col);
						            break;
						         case 'S' :
						        	
						        		col ="SUM("+filterColumnName +")"+filterColumnName+","; 
						        		if(check==false){
						        		ColumnName.add(filterColumnName);
						        		dynCols.append("&DynColumns="+filterColumnName); 
						        		}dynAggCols.append(col);
						        		
						        		break;
						         case 'A' :
						        		col ="AVG("+filterColumnName +")"+filterColumnName+","; 
						        		if(check==false){
						        		ColumnName.add(filterColumnName);
						        		 dynCols.append("&DynColumns="+filterColumnName); 
						        		
						        		}
						        		dynAggCols.append(col);
						            break;
						         case 'D' :
						        		col ="DATE("+filterColumnName +")"+filterColumnName+","; 
						        		groupby+="DATE("+filterColumnName +")"+",";
						        		if(check==false){
						        		 ColumnName.add(filterColumnName);
						        		 dynCols.append("&DynColumns="+filterColumnName); 
						        		
						        		}
						        		dynAggCols.append(col);
						            break;
						         default :
						        		aggColumns +=col ;
						      }
								aggColumns +=col ;
						   
                           }
							
							
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
					
			
	      
	        // Select  count(account_head.account_head_id) 
	         //From (Select   account_head.account_head_id ,account_head.account_head_number,revenue_type.revenue_type_name,account_head.status,
	         //account_head.notes,account_head.deleted_status  From its.account_head   
	        // inner join its.revenue_type on  its.account_head.revenue_type_id=its.revenue_type.revenue_type_id  ) account_head   
	        // where account_head.deleted_status = 0  	    
	 
					 
			whereClause = filterName+" ";
			String sortcondition="";
			sortcondition=(String)request.getSession().getAttribute("SortCondition");
			if(sortcondition==null||sortcondition.contains("null")){
				sortcondition=(String)request.getSession().getAttribute("SortKey");
			}else{
				sortcondition=sortcondition;
			}
		    
			if(!joincondition.isEmpty() ){
		
					if(groupby != null && !groupby.isEmpty()){
				
						String innercolumns=(String)request.getSession().getAttribute("innerQueryAgg");
						 String actualgroupby=groupby.substring(0,groupby.length()-1);
							groupBy=actualgroupby;
						String actualaggcolumn=aggColumns.substring(0,aggColumns.length()-1);
						  Query="Select "+actualaggcolumn+ " ,"+actualgroupby+"  From ( Select  "+innercolumns+" From "+dataSet+"  "+joincondition+") "+dataSet+" "+whereClause+" group by "+actualgroupby+"  "+sortcondition;
						  
				  }
				   else{
				
					  
					  String innercolumns=(String)request.getSession().getAttribute("innerQueryAgg");
						
						String actualaggcolumn=aggColumns.substring(0,aggColumns.length()-1);
						  Query="Select "+actualaggcolumn+ "   From ( Select  "+innercolumns+" From "+dataSet+"  "+joincondition+") "+dataSet+" "+whereClause+"  "+sortcondition;
					  }
				
			
				
				
				
			}else{


	//	if(groupby == null||groupby.contains("null")){
			if(groupby != null && !groupby.isEmpty()){
				String actualaggcolumn=aggColumns.substring(0,aggColumns.length()-1);
	            String actualgroupby=groupby.substring(0,groupby.length()-1);
				groupBy=actualgroupby;
			
				       Query="Select "+actualaggcolumn+ ","+actualgroupby +"   From "+dataSet+" "+whereClause+" group by "+actualgroupby+"  "+sortcondition;
				       
				
			}else{
				
				       
				       String actualaggcolumn=aggColumns.substring(0,aggColumns.length()-1);
						  Query="Select "+actualaggcolumn+ "   From "+dataSet+"   "+joincondition +"  "+whereClause+" "+"  "+sortcondition; 
				
			}
			}
			   
                   
			try {
			
				
//				List<String> defaultColumns=(List<String>)request.getSession().getAttribute("Default");
		//	System.out.println("Default Clause:@@@" + Query);
//			
//				
//				
//				
//				List<String> Def=new ArrayList<String>();
//				boolean a=aggsessioncolumn.contains(",");
//				if(a==true){
//					String def[]=aggsessioncolumn.split(",");
//					 for(int i=0;i<def.length;i++){
//						 String defvalue=def[i].trim();
//					
//						 Def.add(defvalue.trim());
//						 innerJoin+=","+defvalue;
//						 innerqSet.add(defvalue);
//						 //dynCols.append("&DynColumns="+def[i].trim());
//						 
//					 }
//					
//				}else{
//					Def.add(aggsessioncolumn.trim());
//					innerJoin+=","+aggsessioncolumn.trim();
//					 innerqSet.add(aggsessioncolumn.trim());
//					// dynCols.append("&DynColumns="+filtercolumn.trim());
//					
//				}
			
				    	
				    	
//				
				if (aggcolumnvalue == null || aggcolumnvalue.contains("null")) {
					int startIndex = aggcolumnvalue.indexOf("null");
					aggSubString = aggcolumnvalue.substring(startIndex + 4,
							aggcolumnvalue.length() - 1);

					//
				} else {
					aggSubString = aggcolumnvalue;
					

				}
				//System.out.println("AGG COLIu******"+aggSubString);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			              String filtername = "Aggregation  By :";
						    request.getSession().setAttribute("AggBy",filtername);
						   request.getSession().setAttribute("AggName",aggSubString);
				
						
						   String headerName="";
						   
						   Iterator iter = ColumnName.iterator();
						while (iter.hasNext()) {
							dynamicAlias =(String) (iter.next());
							headerName=map3.get(dynamicAlias.trim());
							dynColsName.append("&DynColumnsName="+headerName);
								
							}
				
					
					
					
				}else{
//		     
		
		}
		if(request.isSecure()){
			requestType="https";
		}else{
			requestType="http";
		}
//	
		/* url=requestType+"://"+request.getServerName()+":"+request.getLocalPort()+"/birt/frameset?__report=ITSREPORT.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&__resourceFolder=%2Froot%2FEclipse+Indigo+Wspace%2Fitsreport&Columns="+colName+"&SortKey="+sortKey+"&AscDesc=asc&String="+operator+"&Version="+filterValue+"&GroupBy="+Query+"&DataSet="+dataSet
				   +dynCols.toString()+"&ReportTitle="+reportTitle+dynColsName.toString()+"&where="+whereClause+"&url="+str[0]+"&user="+encryptuser+"&pass="+encryptpassword+"&currentdate="+dynAggCols+"&JoinCondition="+joincondition+"&innerQuery="+innerString+"&InnerWhere="+InnerWhere;//+"&InnerOrder="+InnerOrder;
		*/
		 url="https"+"://"+request.getServerName()+"/birt/frameset?__report=ITSREPORT.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&__resourceFolder=%2Froot%2FEclipse+Indigo+Wspace%2Fitsreport&Columns="+colName+"&SortKey="+sortKey+"&AscDesc=asc&String="+operator+"&Version="+filterValue+"&GroupBy="+Query+"&DataSet="+dataSet
				   +dynCols.toString()+"&ReportTitle="+reportTitle+dynColsName.toString()+"&where="+whereClause+"&url="+str[0]+"&user="+encryptuser+"&pass="+encryptpassword+"&currentdate="+dynAggCols+"&JoinCondition="+joincondition+"&innerQuery="+innerString+"&InnerWhere="+InnerWhere;//+"&InnerOrder="+InnerOrder;
		
		
		request.getSession().setAttribute("url",url);
		
		/*try{
		
			//response.sendRedirect(url);
			RequestDispatcher rd=request.getRequestDispatcher("/Reports/ReportViewer.jsp?url="+url);  
			//servlet2 is the url-pattern of the second servlet  
			  
			rd.forward(request, response);
			//PrintWriter out=response.getWriter();
			//out.print(url);
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		*/
		//tableMap=dao.getTableName();
		//reportHeaderMap=dao.getReportHeader();
		return "birt";
	}
	
	
	
	
	@SkipValidation
	public String getFilterReport() {
		getconfig();
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		String code=(String)request.getSession().getAttribute("Code");
		int id=0;
		ReportDao dao=new ReportDao();
		String filterWhere="";
//		String code=(request.getParameter("id"));
		id=Integer.parseInt(request.getParameter("id"));
		filterWhere	=setParameters(id);
//		int id=dao.getReportHeaderByCode(code);
		try {
			
			

			int startIndex = filterColumn.indexOf("0,");
			int startIndex1 = filterColumn.indexOf(",");
			//System.out.println("Hii" + startIndex);
			int endIndex = filterColumn.indexOf(",");
			String toBeReplaced = filterColumn.substring(startIndex + 1);

			filtercolumn = toBeReplaced.substring(endIndex);
			//.out.println("To be Replaced:-" + filtercolumn);// filter
		//															// column as
																	// table
																	// name
																	// separeted
																	// by comma
			Map<String, String> map1 = (Map<String, String>) request
					.getSession().getAttribute("Map");

			// getting map values from session

			String filtersplit[] = filtercolumn.trim().split(",");// split

			if (filtersplit.length == 1) {
				filtervalue = map1.get(filtersplit[0]);
			//	System.out.println("Filter Value if 1" + filtervalue);
//
			} else {
                    
				for (int i = 0; i < filtersplit.length; i++) {
					if (!(filtersplit[0] == null || filtersplit[0].equals("") || filtersplit[0].equals("null"))) {
						if(!filtersplit[i].trim().equals("0")){
						
						String key = filtersplit[i].trim();
						//System.out.println("key=" + key);
						filtervalue += map1.get(key).trim() + ",";// getting
																	// values
																	// from map
						}								// based on
																	// key

					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("Exception is" + e);

			// TODO: handle exception
		}

		List<String> sessionColumns=(List<String>)request.getSession().getAttribute("SelectWhere");
		
		List<ReportDetail> list=dao.getReportDetail(id);
		
		List<ReportFilter> rfList=dao.getReportFilterById(id);
		
		//ReportDetail rHeader=list.get(1);
		
		String colName="";
		String operator="";
		String filterValue="";
		String groupBy="";
		String sortKey=list.get(0).getColumnName();//"rate_master_id";
		String dataSet="rate_master";
		String reportTitle="";
		String whereClause="";
		String joincondition="";
		String innerJoin=" ";
		String InnerWhere="";//For Where Condition
		String InnerOrder=" ";
		String innerString=" ";
		StringBuffer dynAggCols=new StringBuffer();
		//datasett
		List<ReportHeader> rhList=dao.getReportHeaderById(id);
		List<ReportRelation> rrList=dao.getReportRelationById(id);
		
		if(rhList.size()>0){
		dataSet=rhList.get(0).getReportTableName();
		reportTitle=rhList.get(0).getReportTitle();
		}
		
		for(int i=rrList.size()-1;i>=0;i--){
			
			
			
			joincondition+=rrList.get(i).getJoinType()+" "+rrList.get(i).getJoinCondition()+ "  "  ;
		//	System.out.println("joindd888 "+joincondition);
			
			
		
	}
	
		
     for(int i=rfList.size()-1;i>=0;i--){
			
			
			
			innerJoin=rfList.get(i).getColumnName();
		//	System.out.println("Hii filter"+innerJoin);
			
		
	}
		//dyn cols
		StringBuffer dynCols=new StringBuffer();
		StringBuffer dynColsName=new StringBuffer();
		
		String[] fc=request.getParameterValues("filterColumn");
		String[] fe=request.getParameterValues("filterExpression");
		String[] fv=request.getParameterValues("filterValue");
		String[] fo=request.getParameterValues("filterOperator");
		String aii[]=new String[fo.length];
		for(int i=0;i<fo.length;i++){
			if(i==0){
				
			}else{
				aii[i-1]=fo[i];
			}
			
		}
		aii[fo.length-1]="";
//		for(int y=0;y<fc.length;y++){
//			System.out.println("["+y+"] where "+fc[y]+" "+fe[y]+" "+fv[y]);
//		}
//		
//		System.out.println("filterColumn >> "+request.getParameter("filterColumn"));
//		System.out.println("filterOperator >> "+request.getParameter("filterExpression"));
//		System.out.println("filterValue >> "+request.getParameter("filterValue"));
		
		//filter
		
		 Set<String> innerqSet=new HashSet<String>();
	       innerqSet=(Set)request.getSession().getAttribute("innerQuery");
		    		
		String filter = request.getParameter("filter");
		String url = "";
		if (filter != null || (!filter.trim().isEmpty())) {

			String col = "";
			String filterName = "";
			int j=0;
			String dummyvalue="";
			if (fc.length > 1 && fe.length > 1 && fv.length > 1) {

				for (int i = 0; i < fc.length; i++) {
					try {
						if (filterName.trim().isEmpty()) {
							filterName =filterWhere; //(String) request.getSession()
									//.getAttribute("whereClause");// "where "+col;
						} else if(!fc[i].trim().equals("0")){
							
//							 if(col1==col1){
//								 String filterColumnName = "("+fc[i];
							// }else{
									String filterColumnName = fc[i];
							 }
							// for inner Query
							String filterColumnName = fc[i];
							System.out.println("Gilter " + filterColumnName);

							if (!joincondition.isEmpty()) {
								if(!filterColumnName.trim().equals("0"))
								filterColumnName = filterColumnName
										.split("\\.")[1];
							}

							if (fe[i].indexOf("%") > 0) {
								col = filterColumnName + " LIKE '%25" + fv[i]
										+ "%25' ";
							} else if (fe[i].matches("IN")) {
								
								String filcol=fv[i].trim();
								
								String filcolsplit[]=filcol.split(",");
								for(int k=0;k<filcolsplit.length;k++){
									dummyvalue+="'"+filcolsplit[k].trim() +"',";
									
									
									
								}
								String actualvalue=dummyvalue.substring(0,dummyvalue.length()-1).trim();
								
								
								col = filterColumnName + " IN (" + actualvalue + ") ";
							} else {
								col = filterColumnName + " " + fe[i] + " '"
										+ fv[i] + "' ";
								
								
							}
							  
							j++;
							if(j>0&&j<=1){
								filterName = filterName+"  " + " " + aii[i] + " (  ";
							
								
								}
							else{
								
                            
							filterName += " " + " " +col  + "  " + aii[i]+" ";
							}
						//}
				
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
					
			
	      
					    
					  
					 
			whereClause = filterName+" )";
		
		//	System.out.println("Where Clause:--" + whereClause);
			String filtername = "Filter By :";

			try {
				
				List<String> defaultColumns=(List<String>)request.getSession().getAttribute("Default");
				//System.out.println("Default Clause:@@@" + defaultColumns);
			
				
				
				
				List<String> Def=new ArrayList<String>();
				boolean a=filtercolumn.contains(",");
				if(a==true){
					String def[]=filtercolumn.split(",");
					 for(int i=0;i<def.length;i++){
						 String defvalue=def[i].trim();
					
						 Def.add(defvalue.trim());
						 innerJoin+=","+defvalue;
						 innerqSet.add(defvalue);
						 //dynCols.append("&DynColumns="+def[i].trim());
						 
					 }
					
				}else{
					Def.add(filtercolumn.trim());
					innerJoin+=","+filtercolumn.trim();
					 innerqSet.add(filtercolumn.trim());
					// dynCols.append("&DynColumns="+filtercolumn.trim());
					
				}
			
				String whereAggregation="";
				    	
//				System.out.println("Hi innerWhere--->" + InnerWhere);
//				System.out.println("Hi Filtereee--->" + filtervalue);
				if (filterValue == null || filtervalue.contains("null")) {
					int startIndex = filtervalue.indexOf("null");
					filtersubstring = filtervalue.substring(startIndex + 4,
							filtervalue.length() - 1);
					whereAggregation="where   "+innerJoin;

					//
				} else {
					whereAggregation=whereClause;
					request.getSession().setAttribute("whereClauseAgg",whereAggregation);
					filtersubstring = filtervalue;

				}
				
				
				
				
			    	HttpSession session=request.getSession();
				  request.getSession().setAttribute("FilterBy",filtername);
				   request.getSession().setAttribute("FilterName",filtersubstring);
				 //18.10.2014
				   String selectdyncolscheck="";
				   selectdyncolscheck=(String)request.getSession().getAttribute("Selectdyncols");
				
				 //  request.getSession().setAttribute("dynCols",dynCols.toString());
					//request.getSession().setAttribute("dynColsName",dynColsName.toString());
					if(selectdyncolscheck==null){
						
					
						
						
						dynCols.append((String)request.getSession().getAttribute("dynCols"));
						dynColsName.append((String)request.getSession().getAttribute("dynColsName"));
					
					
					//&DynColumns="+dynCols
					
					}else{
						String	Selectdyncols=(String)request.getSession().getAttribute("SelectdynCols");
						 String SelectAlias=(String)request.getSession().getAttribute("SelectdynColsName");
						 StringBuffer dyncolsSelect=new StringBuffer(Selectdyncols);
						 StringBuffer dyncolsNameSelect=new StringBuffer(SelectAlias);
						dynCols=dyncolsSelect;
						dynColsName=dyncolsNameSelect;
						
					}
						
					
					//session.removeAttribute(arg0)
			//dynCols.append((String)request.getSession().getAttribute("dynCols"));
			Set<String>    columns=new HashSet<String>();
			columns.add(dynCols.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
						
//					System.out.println("HashSet"+columns);
					//dynColsName.append((String)request.getSession().getAttribute("dynColsName"));
					
				}else{
		     String col="";
		     for(int i=list.size()-1;i>=0;i--){
			 try{
			col=request.getParameter(list.get(i).getColumnName());
			
			if(!col.trim().isEmpty()){
			String colAliasName=list.get(i).getAlias();
			innerqSet.add(col);
			dynCols.append("&DynColumns="+col);
			dynColsName.append("&DynColumnsName="+colAliasName);
			
			}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			}
		
		}

		String innerQString = "";
		Iterator iter = innerqSet.iterator();
		while (iter.hasNext()) {
			innerQString += (String) (iter.next()) + " ,";
			// System.out.println(iter.next());
		}
		innerString = innerQString.trim().substring(0,innerQString.length() - 1);
//		url="https://localhost:"+birtPort+"/viewer/frameset?__report=%2Froot%2FEclipse+Indigo+Wspace%2Fits%2FItsDemo.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&__resourceFolder=%2Froot%2FEclipse+Indigo+Wspace%2Fits&Columns="+colName+"&SortKey="+sortKey+"&AscDesc=asc&String="+operator+"&Version="+filterValue+"&GroupBy="+groupBy+"&DataSet="+dataSet
//						   +dynCols.toString()+dynColsName.toString()+"&ReportTitle="+reportTitle+"&where="+whereClause;

		
		//		 url="https://"+request.getLocalAddr()+":"+request.getLocalPort()+"/birt/frameset?__report=ItsDemo.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&__resourceFolder=%2Froot%2FEclipse+Indigo+Wspace%2Fitsreport&Columns="+colName+"&SortKey="+sortKey+"&AscDesc=asc&String="+operator+"&Version="+value+"&GroupBy="+groupBy+"&DataSet="+dataSet
//						   +dynCols.toString()+"&ReportTitle="+reportTitle+dynColsName.toString()+"&where="+whereClause;
//	
				
//				System.out.println("String at 0"+str[0]);
		if(request.isSecure()){
			requestType="https";
		}else{
			requestType="http";
		}
		 url="https"+"://"+request.getServerName()+"/birt/frameset?__report=ITSREPORT.rptdesign&__format=html&__overwrite=true&__locale=en_US&__svg=true&__designer=true&__resourceFolder=%2Froot%2FEclipse+Indigo+Wspace%2Fitsreport&Columns="+colName+"&SortKey="+sortKey+"&AscDesc=asc&String="+operator+"&Version="+filterValue+"&GroupBy="+groupBy+"&DataSet="+dataSet
				   +dynCols.toString()+"&ReportTitle="+reportTitle+dynColsName.toString()+"&where="+whereClause+"&url="+str[0]+"&user="+encryptuser+"&pass="+encryptpassword+"&currentdate="+dynAggCols+"&JoinCondition="+joincondition+"&innerQuery="+innerString+"&InnerWhere="+InnerWhere;//+"&InnerOrder="+InnerOrder;
		
	/*	 url="http://127.0.0.1:63268/viewer/frameset?__report=%2Froot%2Fworkspaceofeclipseneon%2Fits%2FWebContent%2Fbirt%2FITSREPORT.rptdesign&__format=html&__svg=true&__locale=en_US&__timezone=IST&__masterpage=true&__rtl=false&__cubememsize=10&__resourceFolder=%2Froot%2Fworkspaceofeclipseneon%2Fits&1569096592&Columns="+colName+"&SortKey="+sortKey+"&AscDesc=asc&String="+operator+"&GroupBy="+groupBy+"&DataSet="+dataSet
				   +dynCols.toString()+"&ReportTitle="+reportTitle+dynColsName.toString()+"&where="+whereClause+"&url="+str[0]+"&user="+encryptuser+"&pass="+encryptpassword+"&currentdate="+formats1+"&currentdate="+dynAggCols+"&JoinCondition="+joincondition+"&innerQuery="+innerString+"&InnerWhere="+InnerWhere;//+"&InnerOrder="+InnerOrder;
*/
		//url=requestType+"://"+request.getServerName()+":"+request.getLocalPort()+"/frameset?__report=ITSREPORT.rptdesign";
		// url="http://127.0.0.1:64402/viewer/frameset?__report=%2Froot%2Fworkspaceofeclipseneon%2Fits%2FWebContent%2Fbirt%2FITSREPORT.rptdesign&__format=html&__svg=true&__locale=en_US&__timezone=IST&__masterpage=true&__rtl=false&__cubememsize=10&__resourceFolder=%2Froot%2Fworkspaceofeclipseneon%2Fits&2053968123";
		request.getSession().setAttribute("url",url);
		System.out.println("ur trying to show this url------"+url);
		
		try{
		
			//response.sendRedirect(url);
			RequestDispatcher rd=request.getRequestDispatcher("/Reports/ReportViewer.jsp?url="+url);  
			//servlet2 is the url-pattern of the second servlet  
			  
			rd.forward(request, response);
			//PrintWriter out=response.getWriter();
			//out.print(url);
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		//tableMap=dao.getTableName();
		//reportHeaderMap=dao.getReportHeader();
		return null;
	}
	@SkipValidation
	public String edit() {
		
		
		return "edit";
	}
	
	public String execute(){
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			
			ReportDao dao = new ReportDao();

			String[] dbcol={"","reportTitle"};
			
			
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
				if (sdir.equals("asc"))
					dir = "desc";
			}


			int total = -1;
			total = dao.getTotalRecords(request,dbcol[col],dir);

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-store");
			PrintWriter out = response.getWriter();

			result = dao.getData(total, request,dbcol[Integer.parseInt(sCol)],dir);

			out.print(result);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			//System.out.println("=====?" + ex);
		} finally {

		}
		return null;
	}

	@Override
	public void prepare() throws Exception {
	 
		
	}

	public ReportHeader getReportHeader() {
		return reportHeader;
	}

	public void setReportHeader(ReportHeader reportHeader) {
		this.reportHeader = reportHeader;
	}

	public ReportDetail getReportDetail() {
		return reportDetail;
	}

	public void setReportDetail(ReportDetail reportDetail) {
		this.reportDetail = reportDetail;
	}

	public Map<String,String> getTableMap() {
		return tableMap;
	}

	public void setTableMap(Map<String,String> tableMap) {
		this.tableMap = tableMap;
	}

	public Map<String,String> getReportHeaderMap() {
		return reportHeaderMap;
	}

	public void setReportHeaderMap(Map<String,String> reportHeaderMap) {
		this.reportHeaderMap = reportHeaderMap;
	}




}