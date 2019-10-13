<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>

<%-- <script src="assets/vts/js/scheduleroute.js"></script> --%>
<%-- <script src="assets/vts/js/vehiclealert.js"></script> --%>
 <script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
<%-- <script src="assets/vts/js/scheduledeviation.js" --%>
<%-- 	type="text/javascript"></script> --%>
	
<%-- <script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places,drawing,geometry"></script>
 --%>
 
 <script>

//  function datecompare(date1, sign, date2) {
// 	    var day1 = date1.getDate();
// 	    var mon1 = date1.getMonth();
// 	    var year1 = date1.getFullYear();
// 	    var day2 = date2.getDate();
// 	    var mon2 = date2.getMonth();
// 	    var year2 = date2.getFullYear();
// 	    if (sign === '===') {
// 	        if (day1 === day2 && mon1 === mon2 && year1 === year2) return true;
// 	        else return false;
// 	    }
// 	    else if (sign === '>') {
// 	        if (year1 > year2) return true;
// 	        else if (year1 === year2 && mon1 > mon2) return true;
// 	        else if (year1 === year2 && mon1 === mon2 && day1 > day2) return true;
// 	        else return false;
// 	    }    
// 	}

 var i=0;
 var response="";
 function getDate(pick,submit){
// 	 $('#select2-chosen-2').html("Select");
// 	 $('#select2-chosen-3').html("Select");
		//alert('Here');
		 /* var selectedValue = $('#form-control').val(); */
// 		 var val=document.getElementById('divisionlist1').value;
			 if(submit!="") {
	      
	        
	         response = $.ajax({ type: "POST",   
                url: '<%=request.getContextPath()%>/getDateCompaire?picks='+pick+'&submit='+submit,   
                async: false
              }).responseText;
	        
// 	        alert("response=="+response);
		}
 return response;
	}
 
 
 
</script>
<script>
function validate()
{
	 
//    alert("hii");
// 	 var divisionlist=document.getElementById('divisionlist1').value;
// 	 var depotlist=document.getElementById('depotlist1').value;
// 	 var etmlist=document.getElementById('etmlist').value;
// 	 var etmissue=document.getElementById('etm_issue').value;
	 var etmdate=document.getElementById('etmsubmitdate').value;
	 var note=document.getElementById('notes').value;
	 var pick= $('#etmpickupdate').val();
	 var submit= $('#etmsubmitdate').val();
// 	 var pickdate=(Date)pick;
// 	 var submitdate=(Date) submit;
	 
// 	 alert(pickdate+"=="+submitdate);
// 	 VAR Y=DATECOMPARE(PICKDATE, '>', SUBMITDATE);
     var y="";
    	 y=getDate(pick,submit);
//      alert(y);
//      console.log(y);
//      console.log(data);
//      alert(data);


   
//    if( divisionlist == "0" )
//    {
// //       alert( "Please select vehicle" );
//        bootbox.alert("Please select division");
//       return false;
//    }
//    if( depotlist == "0" )
//    {
//       //alert( "Please select vehicle" );
//        bootbox.alert("Please select depot");
//       return false;
//    }
//    if( etmlist == "0" )
//    {
//       //alert( "Please select vehicle" );
//        bootbox.alert("Please select Etm No");
//       return false;
//    }
//    if( etmissue == "" )
//    {
//       //alert( "Please select vehicle" );
//        bootbox.alert("Please Provide Etm Issue");
//       return false;
//    }
   if( etmdate == "" )
   {
      //alert( "Please select vehicle" );
       bootbox.alert("Please Provide Etm Submitted Date");
      return false;
   }
   if( note == "" )
   {
      //alert( "Please select vehicle" );
       bootbox.alert("Please Provide Remarks Date");
      return false;
   }
   if(response=="false")
   {
      //alert( "Please select vehicle" );
       bootbox.alert("ETM Submited Date should be Grater than ETM Pickup Date");
//        data="";
      return false;
   }
//    if( causestatus == "-1" )
//    {
//      // alert( "Please select cause" );
//       bootbox.alert("Please select cause");
//       return false;
//    }
//    return( true );
   
//    if( reason == "-1" )
//    {
////           alert( "Please select reason" );
//       bootbox.alert("Please select reason");
//       return false;
//    }
   return( true );
   
}


 
 </script>
 
<Script>






	 function save(){
			var value=validate();
			
			if(value==true){
			var id = $('#divisions').val();
//		 	alert(id);
			var d_id = $('#depot').val();
			var etmno = $('#etmno').val();
			var etmsumdt = $('#etmsubmitdate').val();
			var remk = $('#notes').val();

//		 	alert(empid);
//		 	alert(remk);

			
//		 	alert(id);
//		 	alert(v_type+"=="+value);
        
			document.forms[0].action ='AjaxeditEtmDeviceHistory.action?id='+id+'&d_id='+d_id+'&etmno='+etmno+'&etmsumdt='+etmsumdt+'&remk='+remk;
			document.forms[0].submit();
			}
			else{
				
			}
		}

	 function callCancell(){
			
			window.location.href='getEtmDeviceView.action';
		}
 
 
	
	

 </Script>

<title>Insert title here</title>
</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">
	
			<div class="tab-content">

				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							EDIT ETM DEVICE HISTORY <small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" >
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Etm Device History
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> -->
							</div> 
						</div>

						<div class="portlet-body form">

<!-- 							<div class="col-md-12" align="left" style="font-size: 1.1em"> -->

<%-- <%-- 								<span class="help-block" style="color: red; list-style: none"><s:actionerror /></span> --%> 
<%-- 								<h4><span class="help-block" style="color: green; list-style: none"><s:actionmessage /></span></h4> --%>
<!-- 							</div> -->
<%-- 							<s:if test="hasActionErrors()"> --%>
<!--    <div class="alert alert-danger"> -->
<!-- 			<button class="close" data-close="alert"></button> -->
<%-- 			<span> --%>
<%-- 			<s:actionerror/> </span> --%>
<!-- 		</div> -->
      
   
<%-- </s:if> --%>
							
							
<%-- 					<s:if test="hasActionMessages()"> --%>
<!--                    <div class="alert alert-success alert-dismissable"> -->
<%-- 								<strong> <s:actionmessage/> </strong> --%>
<!-- 							</div> -->
      
<%--    </s:if> --%>
   
   <b>
							<font color="green"> <s:actionmessage/></font></b>
							<b><font color="red"> <s:actionerror/></font></b>
							<!-- BEGIN FORM-->
							
				 <form method="post" class="form-horizontal" >

							
								
								 <div class="form-group">
									<label class="col-md-3 control-label">Division :</label>
									<div class="col-md-4">
											<input type="text" class="form-control" id="divisions" readonly
												maxlength="20" name="divisions" value="<%=request.getSession().getAttribute("division_id") %>">
											
										</div>
								</div>
								
								 <div class="form-group">
									<label class="col-md-3 control-label">Depot :</label>
									<div class="col-md-4">
											<input type="text" class="form-control" id="depot" readonly
												maxlength="20" name="depot" value="<%=request.getSession().getAttribute("depot_id") %>">
											
										</div>
								</div>
								
								 <div class="form-group">
									<label class="col-md-3 control-label">Etm No :</label>
									<div class="col-md-4">
											<input type="text" class="form-control" id="etmno" readonly
												maxlength="20" name="etmno" value="<%=request.getSession().getAttribute("etmno") %>">
											
										</div>
								</div>

							
                             
				            
				           <div class="form-group">
									<label class="col-md-3 control-label">ETM Issue:</label>
									<div class="col-md-4">
											<textarea class="form-control" id="etm_issue" readonly
												maxlength="20" name="etm_issue"  ><%=request.getSession().getAttribute("etm_isseu") %></textarea>
											
										</div>
								</div>
								
								
								<div class="form-group">
									<label class="control-label col-md-3">ETM Pickup Date: </label>
									<div class="col-md-3">
										
											<input type="text" class="form-control" id="etmpickupdate" 
												readonly name="etmpickupdate" value="<%=request.getSession().getAttribute("etm_pickup") %>"/> 
										
									</div>
										
								</div>
								

								<div class="form-group">
									<label class="control-label col-md-3">ETM Submitted Date:<font
										color="red">*</font></label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"
											style="width: auto" data-date-format="dd-mm-yyyy"
											data-date-viewmode="years">
											<input type="text" class="form-control" id="etmsubmitdate" 
												 name="etmsubmitdate"  /> <span
												class="input-group-btn">
												<button class="btn default" type="button">
													<i class="fa fa-calendar"></i>
												</button> 
											</span>
												
										</div>
									</div>
										
								</div>
									
								<div class="form-group">
									<label class="col-md-3 control-label">Remarkes:<font
										color="red">*</font></label>
									<div class="col-md-4">
											<textarea rows="3" class="form-control" id="notes" maxlength="100"	name="notes"></textarea>
											
										</div>
  	         

				
							<!-- END FORM-->
						</div>
											
								
											
									
											
											
									
											
											
									
									
											
											
										<%-- 	 <div class="form-group" id="scrapReason">
									<label class="col-md-3 control-label">Reason<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <select id= "Causereason" class="select2_category form-control" name="Causereason">
											<option value="-1">---select---</option>
											</select>
											</div></div>
 --%>					                       
											
										
								
									<div class="form-group">
<!-- 					          <div class="col-md-1" id=""> -->
<!-- <!-- 									<button type="button" class="btn default"style="position: static;">Submit</button> --> 
<!-- 									<button type="submit" class="btn blue">Save</button> -->
<!-- 									</div> -->
				            </div>

                              <div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="button" class="btn blue" onclick="save()">Save</button>
										<button type="button" class="btn default" onclick="callCancell()">Cancel</button>
									</div>
								</div>
                              

                          </form>
							</div>	
									
																		
							
							<!-- END FORM-->
					
				</div>
			</div>
		</div>
</div>


</body>
</html>