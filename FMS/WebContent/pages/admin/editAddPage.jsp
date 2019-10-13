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
     	   // alert("result---"+result);
             document.getElementById('parentid').innerHTML=result;
        }
    });
	
}

function getDetails(){
	//alert("test");
	//pagetype
	var pagetype="<s:property value="pageMaster.page_type"/>";
	//alert("pagetype---"+pagetype);
	
	$.ajax({
		
	    type: "post",
	    async:false,
        url: '<%=request.getContextPath()%>/parentpagelistActionDetails.action?pagetype='+pagetype,
        success: function(result) {
        	//alert(result);
             document.getElementById('parentid').innerHTML=result;
        }
    });
	
	  var prevType="<s:property value='pageMaster.page_id'/>";
	 //alert("prevType------"+prevType);
	
	 if(prevType!=""){
		 $.ajax({
			    type: "post",
			    async:false,
		        url: '<%=request.getContextPath()%>/parentiddetails.action?prevType='+prevType,
		        success: function(result1) {
		        	//alert("result--"+result1);
		            // document.getElementById('parentid').innerHTML=result;
		             document.getElementById("parentid").value=result1;
		        	// document.getElementById("parentid").value=result;
		        	// alert("test---"+document.getElementById("parentid").value);
		             var pageiddetails = document.getElementById("parentid").value;
					 var pageid = parentid.options[parentid.selectedIndex].text;
					 //alert("pageid---"+pageid);
					 document.getElementById('select2-chosen-1').innerHTML=pageid;
		        }
		    });
		 
		 
		 
		
	 }
	
}


function SelectElement(valueToSelect)
{ 
	
var element = document.getElementById('status');
element.value = valueToSelect;
}

function getParentId(){
	//alert('Here');
	 /* var selectedValue = $('#form-control').val(); */
	
	 var len= document.getElementById('parentid').options.length;
	 var val=document.getElementById('parentid').value;
	  if(val==0)
	 {
		 document.getElementById('sequence').value=0;
		 document.getElementById('Level').value=0;
	 } 

	 if(len<=1 ) {
        $.ajax({
            type: "post",
            url: '<%=request.getContextPath()%>/findParentIdAction',
            success: function(result) {
                document.getElementById('parentid').innerHTML=result;
            }
        })
	 }
}
<%-- function clearForm()
{
	 var val=document.getElementById('parentid').value;
	 //alert(val);
	$.ajax({
   	 
        type: "get",
        url: '<%=request.getContextPath()%>/findSequenceAction?parentid='+val,
        success: function(getmaxseqid) {
     	    //document.getElementById('Level').value=level;
             document.getElementById('sequence').value=getmaxseqid;
        }
    });
	//document.getElementById('sequence').value=0;
	document.getElementById('Level').value=0;

} --%>
function setSequence()
{
	<%-- var e = document.getElementById("parentid");
	var strUser = e.options[e.selectedIndex].value;
	if(strUser.trim()=="")
	{
		document.getElementById('sequence').value="";
		 document.getElementById('Level').value="";
	}
	 var len= document.getElementById('parentid').options.length;
	//alert(strUser);
    var level=document.getElementById('Level').value;
    level="";
	 if (level==null || level.trim()=="") { 
	   // alert('Please enter something');
	     $.ajax({
	          type: "get",
	        url: '<%=request.getContextPath()%>/findLevelAction!getLevelEdit?parentid='+strUser,
	           success: function(level) {
	        	    document.getElementById('Level').value=level;
	               //document.getElementById('Level').value=level;
	          }
	      });
	     if(len>=1) {
	    	//alert(len)
	         $.ajax({
	        	 
	             type: "get",
	             url: '<%=request.getContextPath()%>/findSequenceAction!getSequenceEdit?parentid='+strUser,
	             success: function(getmaxseqid) {
	          	    //document.getElementById('Level').value=level;
	                  document.getElementById('sequence').value=getmaxseqid;
	             }
	         });
	  	 }
	    } --%>
	    
	    
	    
	    var e = document.getElementById("parentid");
		var strUser = e.options[e.selectedIndex].value;
		//alert(strUser);
		   $.ajax({
		          type: "post",
		          async:false,
		          url: '<%=request.getContextPath()%>/findLevelAction?parentid='+strUser,
		          success: function(level) {
		        	    document.getElementById('Level').value=level;
		          }
		      });
	
		   $.ajax({
	        	 
	             type: "post",
	             async:false,
	             url: '<%=request.getContextPath()%>/findSequenceAction?parentid='+strUser,
	             success: function(getmaxseqid) {
	          	    //document.getElementById('Level').value=level;
	                  document.getElementById('sequence').value=getmaxseqid;
	             }
	         });
	 
	
	}
function goView() {
	
		/* var val = $("input[type='checkbox']").val(); */
		//alert(val);
		document.forms[0].action = 'pageView.action';
		document.forms[0].submit();

}
function getStatus() {
	result = "<option value='ACTIVE'>ACTIVE</option><option value='INACTIVE'>INACTIVE</option>";
	document.getElementById('status').innerHTML = result;
}

function getPagedetails(){
var msg=document.getElementById("msg").value;
var flag=false;
var status=document.getElementById('status').value;
	
	
	if (status == 'INACTIVE') {
		if (msg == 0) {
		return true;
		} else {
			
 		 	var r = confirm(msg+". Are you Sure, you want to Inactive this record?");
			if (r == true) {
			   return true;
			} else {
				   return false;
			}  
 			
 			/*  bootbox.confirm(msg+"Are you Sure, you want to delete this record?",
					function(result) {
//				 alert('hiii'+result);
			if (result == true) {
				//alert(result);
				return true;
			}else{
				return false;
			}
			});  */
 	}
}

}

/* function getPagedetails(){
	var status=document.getElementById('status').value;
	alert("status---"+status);
	var pageid=document.getElementById('page_id').value;
	alert("pageid-------"+pageid);
	
	var pagename=document.getElementById('pageName').value;
	var path=document.getElementById('Path').value;
	var parentid=document.getElementById('parentid').value;
	var level=document.getElementById('Level').value;
	var sequence=document.getElementById('sequence').value;
	if(status=='INACTIVE'){
		alert("est");
		 $.ajax({
		  type: "POST",
          url: "getInactiveForPage.action?pageid="+pageid,
             success: function(response){
            	 //alert(response);
            	 if(response ==  0 )
            		 {
            		 document.getElementById("pageiddetails").value=pageid;
						document.getElementById("pagenamedetails").value=pagename;
						document.getElementById("pathdetails").value=path;
						document.getElementById("statusdetails").value=status;
						document.getElementById("parentiddetails").value=parentid;
						document.getElementById("leveldetails").value=level;
						document.getElementById("sequencedetails").value=sequence;
						document.getElementById("form1").submit();
            	
            		 }else{
            				bootbox.confirm(response+ "Are you Sure, you want to Inactive this record?",
									function(result) {
							
							if (result == true) {
								document.getElementById("pageiddetails").value=pageid;
								document.getElementById("pagenamedetails").value=pagename;
								document.getElementById("pathdetails").value=path;
								document.getElementById("statusdetails").value=status;
								document.getElementById("parentiddetails").value=parentid;
								document.getElementById("leveldetails").value=level;
								document.getElementById("sequencedetails").value=sequence;
								document.getElementById("form1").submit();
							}
							});

 							
 							
            		 }
             	
             }
		  });
	}else{
		document.getElementById("pageiddetails").value=pageid;
		document.getElementById("pagenamedetails").value=pagename;
		document.getElementById("pathdetails").value=path;
		document.getElementById("statusdetails").value=status;
		document.getElementById("parentiddetails").value=parentid;
		document.getElementById("leveldetails").value=level;
		document.getElementById("sequencedetails").value=sequence;
		document.getElementById("form1").submit();
		
	}
} */

</script>
<style>
h1.intro {
	color: red;
	font-size: 14px;
}
</style>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
</head>
<body onload="getDetails()">
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
	<div class="page-content-wrapper">
		<div class="page-content">
		<%if (access.equalsIgnoreCase("Y")){%>
			<%if (edit.equalsIgnoreCase("Y")){%>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<div class="row">
						<div class="col-md-12">
							<!-- BEGIN PAGE TITLE & BREADCRUMB-->
							<h3 class="page-title">
								PAGE <small></small>
							</h3>
							<font color="green"><s:actionmessage /></font>
							<!-- END PAGE TITLE & BREADCRUMB-->
						</div>
					
					</div>
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Page Management
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
							<form class="form-horizontal" method="post"
								action="editPageData.action">
								<div class="form-body">

									<div class="form-group">
										<label class="col-md-3 control-label">Page Name<font
											color="red">*</font></label>
										<div class="col-md-4">
											<div class="input-group">
												<input type="text" name="pageMaster.page_name" id="pageName"
													maxLength="50" class="form-control"
													value="<s:property value="pageMaster.page_name" />">
												<input type="hidden" name="pageMaster.page_id" id="page_id"
													maxLength="50"
													value="<s:property value="pageMaster.page_id" />">
												<input type="hidden" class="form-control" id="msg"
													name="pageMaster.msg"
													value='<s:property value="pageMaster.msg"/>'>
												<s:if test="fieldErrors.get('pageName').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('pageName').get(0)" /></span>
												</s:if>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-3 control-label">Path</label>
										<div class="col-md-4">
											<div class="input-group">
												<input type="text" name="pageMaster.path" id="Path"
													class="form-control" maxLength="50"
													value="<s:property value="pageMaster.path" />">

												<s:if test="fieldErrors.get('path').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('path').get(0)" /></span>
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
												<label class="col-md-3 control-label">Menu Type:</label>
												<div class="col-md-4">
													 <select class="form-control"  name="pageMaster.page_type"  id="pagetype" onchange="getParentPagelist()" >
														<option value="C" id="C" >ITS</option>
														<option value="D" id="D" >DOA</option>
													</select>   
														<script>
														var pagetype = "<s:property value="pageMaster.page_type"/>";
															if (pagetype != undefined) {
																if (pagetype == "C" || pagetype == "C" || pagetype == "C") {
																	document.getElementById("C").selected = true;
																} else {
																	document.getElementById("D").selected = true;
																}
															}
															</script>
											</div>
											</div>

										<div class="form-group">
													<label class="col-md-3 control-label">Parent id</label>
													<div class="col-md-4" >
														 <select Class="select2_category form-control" name="pageMaster.parent_id"   id="parentid" onclick="getParentId()" onchange="setSequence()">
												         <option value="0" >Main</option>
										                 </select>
															
													        </div>
												</div>

									<%-- <div class="form-group">
													<label class="col-md-3 control-label">Parent id</label>
													<div class="col-md-4">
														<div class="input-group">
														<select class="form-control" name="pageMaster.parent_id" id="parentid"  onclick="getParentId()">
												<option value="<s:property value="pageMaster.parent_id" />"><s:property value="pageMaster.parentname" /></option>
												
											</select>
														 
													
																<s:if test="fieldErrors.get('parentid').size() > 0">
     								<span style="color:red;"><s:property value="fieldErrors.get('parentid').get(0)" /></span>
									</s:if>	 
													<span class="input-group-addon">
															    <i class="fa fa-user"></i>
															</span>
												 		
														</div>
													</div>
												</div>
											  --%>

							<%-- 		<div class="form-group">
										<label class="col-md-3 control-label">Parent id<font
											color="red">*</font></label>
										<div class="col-md-4">
											<s:select list="parentpagelist" id="parentid"
												name="pageMaster.parent_id"
												cssClass="select2_category form-control" headerKey="0"
												headerValue="Main menu" onchange="setSequence()"></s:select>

										</div>
									</div> --%>
									
									

									<div class="form-group">
										<label class="col-md-3 control-label">level</label>
										<div class="col-md-4">
											<div class="input-group">
												<input type="text" name="pageMaster.level" maxLength="8"
													id="Level" class="form-control"
													value="<s:property value="pageMaster.level" />">
												<s:if test="fieldErrors.get('level').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('level').get(0)" /></span>
												</s:if>

											</div>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">sequence</label>
										<div class="col-md-4">
											<div class="input-group">
												<input type="text" name="pageMaster.sequence" id="sequence"
													maxLength="8" class="form-control"
													value="<s:property value="pageMaster.sequence" />">

												<s:if test="fieldErrors.get('sequence').size() > 0">
													<span style="color: red;"><s:property
															value="fieldErrors.get('sequence').get(0)" /></span>
												</s:if>

											</div>
										</div>
									</div>


									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<!-- <button type="submit" class="btn blue">Save</button> -->
											<button type="submit" class="btn blue"
												onclick="return getPagedetails();">Save</button>
											<button type="button" class="btn default" id="cancel"
												onclick="goView()">Cancel</button>

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


	<!-- <form name="form1" id="form1" action="editPageData.action" method="POST">
<input type="hidden" name="pageiddetails" id="pageiddetails"  />
<input type="hidden" name="pagenamedetails" id="pagenamedetails"  />
<input type="hidden" name="pathdetails" id="pathdetails"  />
<input type="hidden" name="statusdetails" id="statusdetails"  />
<input type="hidden" name="parentiddetails" id="parentiddetails"  />
<input type="hidden" name="leveldetails" id="leveldetails"  />
<input type="hidden" name="sequencedetails" id="sequencedetails"  />
</form>  -->



</body>
</html>

