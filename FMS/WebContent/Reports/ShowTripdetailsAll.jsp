<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
<tr>
     <td>Trip No</td>
  </tr>
<s:iterator value="l" >
  <tr>
     <td><s:property/></td>
  </tr>
</s:iterator>
</table>
</body>
</html>