<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="javax.servlet.http.HttpServletResponse"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body> --%>
<%@page import="org.apache.struts2.ServletActionContext"%>


<%
  ///
			if(request.getParameter("ServiceTicketToCompany")!=null)
			{
				
	//System.out.print("Val@@@.."+request.getParameter("printing"));
		try{	
        String contexpath = request.getContextPath();
        //System.out.println("\t contexpath : "+contexpath);
        
        String filePath = "/device/";
        
       String fileName = "ServiceTicketToCompany.txt";
        String path = contexpath + filePath +fileName;
        //String path = (String) ServletActionContext.getRequest().getSession().getAttribute("filePath");
       // System.out.println("\t Final path is : "+path);
%>


<applet archive="applet/IqPrint.jar" name="print" code="IqPrint" width=100 height=100>
     <param name=httpUrl value="<%=path%>">
    <param name=printText value="">
    <param name=printDevice value="epson">
    <param name=printSubmitUrl value="">
    <param name=paginationRequired value="Y">
    <param name=directPrint value="Y">
    <param name=displayRequired value="N"> 
</applet>
<%
}catch(Exception e){
	e.printStackTrace();
}
			}
%>




<!-- </body>0
</html> -->