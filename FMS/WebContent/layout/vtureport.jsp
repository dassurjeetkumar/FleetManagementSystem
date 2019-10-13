<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8"/>
<title><tiles:insertAttribute name="title"/></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<!--to load dynamic plugin -->

<!--for demo start -->
 <%-- <script>
$(document).ready(function() {	
	//var x='';
    alert('x=');
	});
</script>  --%>
<!--for demo end -->	
</head>
<body>

<tiles:insertAttribute name="content" />
<div class="clearfix"></div>
</body>
</html>