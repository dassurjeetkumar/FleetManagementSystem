<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.trimax.its.model.User"%>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<!DOCTYPE html>
<html>
<head>
<script>

function getParentPagelist(){
	var pagetype=document.getElementById('pagetype').value;
	//alert("pagetype---"+pagetype);
	  document.getElementById('select2-chosen-1').innerHTML='Main';
	$.ajax({
	   	 
        type: "post",
        url: '<%=request.getContextPath()%>/parentpagelistActionDetails.action?pagetype='+pagetype,
        success: function(result) {
     	  //alert("hello");
     	    //alert("result---"+result);
             document.getElementById('parentid').innerHTML=result;
        }
    });
	
}



function getParentId(){
	//alert('Here');
	 /* var selectedValue = $('#form-control').val(); */
	 var val=document.getElementById('parentid').value;
	  if(val==0)
	 {
		 document.getElementById('sequence').value=0;
		 document.getElementById('Level').value=0;
	 } 
	 var len= document.getElementById('parentid').options.length;

	 if(len<=1 ) {
        $.ajax({
            type: "post",
            url: '<%=request.getContextPath()%>/findParentIdAction',
            success: function(result) {
                document.getElementById('parentid').innerHTML=result;
            }
        });
	 }
	// alert("end");
}
function clearForm()
{
	 var val=document.getElementById('parentid').value;
	 //alert(val);
	$.ajax({
   	 
        type: "post",
        url: '<%=request.getContextPath()%>/findSequenceAction?parentid='+val,
        success: function(getmaxseqid) {
     	    //document.getElementById('Level').value=level;
             document.getElementById('sequence').value=getmaxseqid;
        }
    });
	//document.getElementById('sequence').value=0;
	document.getElementById('Level').value=0;
	 //document.getElementById('pageName').focus();
}
/* function validatePage()
{
	
	 var pagename=document.getElementById('pageName').value;
	 if (pagename==null || pagename.trim()=="") { 
		 alert("please enter page name");
	 }
	 
} */

function getdetails(){
	 var pagetype=document.getElementById("pagetype").value;
	// alert("pagetype---"+pagetype);
		 if(pagetype==""){
			 pagetype='C';
		 }else{
			 
		 }
		 
	

		  $.ajax({
			   	 
		        type: "post",
		        async:false,
		        url: '<%=request.getContextPath()%>/parentpagelistActionDetails.action?pagetype='+pagetype,
		        success: function(result) {
		     	     document.getElementById('parentid').innerHTML=result;
		        }
		    }); 
		  
		  
		   var prevType="<s:property value='pageMaster.parent_id'/>";
		   //alert("prevType--------"+prevType);
		  
		 	 if(prevType!=""){
			
				 document.getElementById("parentid").value=prevType;
				 var pageiddetails = document.getElementById("parentid").value;
				 var pageid = parentid.options[parentid.selectedIndex].text;
				 document.getElementById('select2-chosen-1').innerHTML=pageid;
					
			 }
		 	 
		 	
		 	setSequence();
		 
		 	 
	
}


function setSequence()
{ 
	
 	
	  
	  
	var e = document.getElementById("parentid");
	var strUser = e.options[e.selectedIndex].value;
	//alert(strUser);
	/* if(strUser.trim()=="")
	{
		document.getElementById('sequence').value="";
		 document.getElementById('Level').value="";
		
	} */
	
   // var len= document.getElementById('parentid').options.length;

    /* var level=document.getElementById('Level').value;
    level=""; */
	
   // if (level==null || level.trim()=="") { 
	 // alert('Please enter something');
	 
	// alert("Level---"+document.getElementById('Level').value);
	 
	     $.ajax({
	          type: "post",
	          async:false,
	          url: '<%=request.getContextPath()%>/findLevelAction?parentid='+strUser,
	          success: function(level) {
	        	    document.getElementById('Level').value=level;
	          }
	      });
	     
	     //if(len>=1) {
	   	//alert(len)
	         $.ajax({
	        	 
	             type: "post",
	             async:false,
	             url: '<%=request.getContextPath()%>/findSequenceAction?parentid='+strUser,
	             success: function(getmaxseqid) {
	          	    //document.getElementById('Level').value=level;
	                  document.getElementById('sequence').value=getmaxseqid;
	             }
	         });
	  	// }
	  
	      //this.textField1.focus();
	     // return false; // cancel submission
	   // }
	 
	<%-- //alert("hello");
	 if(len>=1 ) {
       $.ajax({
           type: "get",
           url: '<%=request.getContextPath()%>/findSequenceAction?parentid='+strUser,
           success: function(getmaxseqid) {
        	    //document.getElementById('Level').value=level;
                document.getElementById('sequence').value=getmaxseqid;
           }
       });
	 } --%>
	 
	 
	//alert("hello");
		//alert( document.getElementById('Level').value); 
	}
function goView() {
	
		/* var val = $("input[type='checkbox']").val(); */
		//alert(val);
		document.forms[0].action = 'pageView.action';
		document.forms[0].submit();

	
}

function isInt(n) {
	   return typeof n === 'number' && n % 1 == 0;
	}

function checkInt(){

	var val=document.getElementById('Level').value;
	var val2=document.getElementById('sequence').value;

	if(isNaN(val) || isInteger(val) || val<0){
		
		document.getElementById('integerVal').innerHTML='Level should be in Integer';
	   return false;
	}
	else if(isNaN(val2) || isInteger(val2) || val2<0){
		document.getElementById('integerVal2').innerHTML='Sequence should be in Integer';
		   return false;
	}
	else{
		return true;
	}
}

function isInteger(n) {
	if(parseFloat(n) != parseInt(n, 10)){
	   return true;
	}else{
		return false;
	}
	} 

</script>
<style>
h1.intro {color:red;font-size:14px;}

</style>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
</head>
<body onload="setSequence(),getdetails()">
<div class="page-content-wrapper">
<%
AccessRightsDao  accessrightdao=new AccessRightsDao();
AccessRights accessrights=new AccessRights();
int usrsessionid=(Integer)request.getSession().getAttribute("userid");
accessrights=accessrightdao.accessRightsdetails(usrsessionid, "pageView.action");
String access=accessrights.getACCESS();
String create=accessrights.getCREATE();
String edit=accessrights.getEDIT();
String delete=accessrights.getDELETE();
String read=accessrights.getREAD();
String error=accessrights.getERROR();
String viewdelete=accessrights.getVIEWDELETE();
String viewdeletedrecord=(String)request.getSession().getAttribute("viewdeletedrecord");
%>
	<div class="page-content">
	<%if (access.equalsIgnoreCase("Y")){%>
<div class="tab-content">
						<%if (create.equalsIgnoreCase("Y")){%>	<div class="tab-pane active" id="tab_0">
										<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
						PAGE <small></small>
					</h3>
					<font color="green"><s:actionmessage/></font>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
				</div>
									<div class="portlet box grey-cascade">
									<div class="portlet-title">
										<div class="caption">
											<i class="fa fa-gift"></i>Create Page Management
										</div>
									
									</div>
									<div class="portlet-body form">
									<s:if test="hasActionErrors()">
					<font color="Red"> <s:actionerror />
					</font>
					</s:if>
					
					<s:if test="hasActionMessages()">
					<font color="green"> <s:actionmessage />
					
				</font>
					</s:if>
										<!-- BEGIN FORM-->
										<form action="addNew.action" onsubmit="return checkInt()" class="form-horizontal" method="post">
											<div class="form-body">
											
												<div class="form-group">
													<label class="col-md-3 control-label">Page Name<font color="red">*</font>:</label>
													   <div class="col-md-4">
														 <div class="input-group">
															 <input type="text" name="pageMaster.page_name" id="pageName" maxLength="50" class="form-control" value="<s:property value="pageMaster.page_name" />">
														    	<s:if test="fieldErrors.get('pageName').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('pageName').get(0)" /></span>
									</s:if>
														</div>
													 </div>
												 </div>
												 <div class="form-group">
													<label class="col-md-3 control-label">Path:</label>
													<div class="col-md-4">
														<div class="input-group">
														 <input type="text" name="pageMaster.path" id="path" class="form-control" maxLength="50" value="<s:property value="pageMaster.path" />">
									<s:if test="fieldErrors.get('path').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('path').get(0)" /></span>
									</s:if>															
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">Status</label>
													<div class="col-md-4">
														<div class="input-group">
														<select class="form-control" name="pageMaster.status" >
												<option id="active" value="ACTIVE">ACTIVE</option>
												<option id="inactive" value="INACTIVE">INACTIVE</option>
												
											</select>
															<script>
															var media = "<s:property value="pageMaster.status"/>";
															if (media != undefined) {
																if (media == "INACTIVE" || media == "INACTIVE") {
																	document.getElementById("inactive").selected = true;
																} else {
																	document.getElementById("active").selected = true;
																	
																}
															}
														</script>												
														</div>
													</div>
												</div>
											
											
											
												<div class="form-group">
													<label class="col-md-3 control-label">Menu Type</label>
													<div class="col-md-4">
														<div class="input-group">
														<select class="form-control" name="pageMaster.page_type"  id="pagetype" onchange="getParentPagelist()">
															
														    <option id="its" value="C">ITS</option>
															<option id="dao" value="D">DOA</option>
															
														</select>
														  <script>
															var media = "<s:property value="pageMaster.page_type"/>";
														
															if (media != undefined) {
																if (media == "D" || media == "D") {
																	document.getElementById("dao").selected = true;
																} else {
																	document.getElementById("its").selected = true;
																	
																}
															}
														</script>  
														</div>
													</div>
												</div>
											
											
											<div class="form-group">
													<label class="col-md-3 control-label">Parent id</label>
													<div class="col-md-4" >
														 <select Class="select2_category form-control" name="pageMaster.parent_id" id="parentid" onclick="getParentId()" onchange="setSequence()">
												         <option value="0" >Main</option>
										                 </select>
															
													        </div>
												</div>
											
											<%-- 	<div class="form-group">
													<label class="col-md-3 control-label">Parent id</label>
													<div class="col-md-4">
														<s:select list="parentpagelist" id="parentid" name="pageMaster.page_id" 
		cssClass="select2_category form-control" headerKey="0" headerValue="Main menu"  onchange="setSequence()" ></s:select>														
													</div>
												</div> --%>
											
											<%-- <div class="form-group">
													<label class="col-md-3 control-label">Parent id</label>
													<div class="col-md-4">
														<div class="input-group">
													 	<select class="form-control" name="pageMaster.parent_id" id="parentid" onclick="getParentId()" onchange="setSequence()">
														<option value='0'>--select--</option>
												
										
											<s:select list="parentpagelist" id="parentid" name="" 
		cssClass="select2_category form-control" headerKey="0" headerValue="Select"></s:select>
		
		
														
															</select> 
															<s:if test="fieldErrors.get('parentid').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('parentid').get(0)" /></span>
									</s:if>	 
														 
													
															
														
														</div>
													</div>
												</div> --%>
											
											<div class="form-group">
													<label class="col-md-3 control-label">Level<font color="red">*</font></label>
													<div class="col-md-4">
														<div class="input-group">
														 <input type="text" name="pageMaster.level" id="Level" maxLength="1" class="form-control" value="<s:property value="pageMaster.level" />">
														<s:if test="fieldErrors.get('level').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('level').get(0)" /></span>
									</s:if>	
								<%-- 	<font color="red"><span id="integerVal"></span></font> --%>
														</div>
													</div>
												</div>
											
											<div class="form-group">
													<label class="col-md-3 control-label">Sequence<font color="red">*</font></label>
													<div class="col-md-4">
														<div class="input-group">
														 <input type="text" name="pageMaster.sequence" id="sequence" maxLength="8" class="form-control" value="<s:property value="pageMaster.sequence" />">
														 
									<s:if test="fieldErrors.get('sequence').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('sequence').get(0)" /></span>
									</s:if>	
									<font color="red"><span id="integerVal2"></span></font>
															
														</div>
													</div>
												</div>
												
												
											<div class="form-actions fluid">
												<div class="col-md-offset-3 col-md-9">
													<button type="submit" class="btn blue">Save</button>
													<button type="button" id="cancel" class="btn default" onclick="goView()">Cancel</button>
													<h1 class="intro"><s:property value="msg" /></h1>
												</div>
											</div>
											</div>
										</form>
										<!-- END FORM-->
										<%}else{ %>
<%@ include file="/pages/admin/error.jsp" %>
<%} %>									
										
<%}else{%>
 


<%@ include file="/pages/admin/error.jsp" %>


<%}%>
										
									</div>
								</div>
								</div>
								</div>
								</div>
								</div>
								
						
						
								
								</body>
								</html>
								
