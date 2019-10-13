<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script src="assets/global/plugins/jquery-1.11.0.min.js" type="text/javascript"></script>
<Script>
var counter=0;
	
 function callCancell(){
		
		window.location.href='getEtmDeviceView.action';
	}
	
 function check(){
		//alert(document.getElementById("etmissue1").value+"---"+document.getElementById("etmissue2").value);

	// alert(document.getElementById("etmnumber1").value+"--"+document.getElementById("etmnumber2").value);
		if(document.getElementById("etmnumber1").value=="0" && (document.getElementById("etmnumber2").value=="0" || document.getElementById("etmnumber2").value==""))
		{
			
		bootbox.alert("Please Select ETM no");
		return false;
		}
		//alert(document.getElementById("etmissue1").value+"---"+document.getElementById("etmissue2").value);
		if(document.getElementById("etmissue1").value=="0" && (document.getElementById("etmissue2").value=="0" || document.getElementById("etmissue2").value==""))
			{
			bootbox.alert("Please Select ETM issue");
			return false;
			}
		if(document.getElementById("etmpickupdate").value=="0")
		{
			bootbox.alert("Please Select Date");
		return false;
		}
		document.getElementById("form1").action="saveEtmDeviceData.action";
		document.getElementById("form1").submit();
 }

 </Script>
<script type="text/javascript">
$(document).ready(function() {
	//$("#Descreption").hide();
 	 $("#singleetm").hide();
 	$("#multiissue").hide();
 	
 	
});
</script>

<SCRIPT type="text/javascript">
	$("#selectAll").change(function(){  //"select all" change
		alert("hi");
	    var status = this.checked; // "select all" checked status
	    $(':checkbox').each(function(){ //iterate all listed checkbox items
	    	 $("#singleetm").show();
	    	 	$("#multiissue").show();
	    	 	 $("#multietm").hide();
		    	 	$("#issuesingle").hide();
	    });
	});

	$('.checkbox').change(function(){ //".checkbox" change
		//alert(2);
	    //uncheck "select all", if one of the listed checkbox item is unchecked
	    if(this.checked == false){ //if this item is unchecked
	   	 $("#singleetm").hide();
 	 	$("#multiissue").hide();
 	 	 $("#multietm").show();
	    	 	$("#issuesingle").show();
	    }
	   
	    //check "select all" if all checkbox items are checked
	    if ($(':checkbox:checked').length == $('.checkbox').length ){
	    	//alert(3);
	        $("#selectAll")[0].checked = true; //change "select all" checked status to true
	    }
	}); 

</SCRIPT>

<script>
function selectall(){
	var cnt = $(":checkbox:checked").length;
	//alert(cnt);
	if(cnt==1){
	    	 $("#singleetm").show();
	    	 	$("#multiissue").show();
	    	 	 $("#multietm").hide();
		    	 	$("#issuesingle").hide();
	    }
	     if(cnt==0){ //if this item is unchecked
		   	 $("#singleetm").hide();
	 	 	$("#multiissue").hide();
	 	 	 $("#multietm").show();
		    	 	$("#issuesingle").show();
		    } 
	    
	 /*    $('.checkbox').change(function(){ //".checkbox" change
			//alert(2);
		    //uncheck "select all", if one of the listed checkbox item is unchecked
		    if(this.checked == false){ //if this item is unchecked
		    	 $("#singleetm").hide();
			 	 	$("#multiissue").hide();
			 	 	 $("#multietm").show();
				    	 	$("#issuesingle").show();		    }
		 
		}); */
}

</script> 

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
							ETM DEVICE HISTORY <small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Etm Device History
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> -->
							</div> 
						</div>

						<div class="portlet-body form">

<s:if test="hasActionErrors()">
   <div class="errors">
      <s:actionerror/>
   </div>
</s:if>
   
   <b>
							<font color="green"> <s:actionmessage/></font></b>
							<b><font color="red"> <s:actionerror/></font></b>
							<!-- BEGIN FORM-->
							
				 <form action="saveEtmDeviceData" method="post" class="form-horizontal" name="form1" id="form1" >

                              <div class="form-body"> 
                              <input type='checkbox' id='selectAll' onchange="selectall()"/>Multible ETM Issues
                              
								<div class="form-group" id="multietm">
									<label class="col-md-3 control-label">Etm Nos<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <s:select list="etmnumber" id="etmnumber2" name="etmidnumber" cssClass="form-control input-medium select2me" multiple="true" ></s:select>
									<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('etmidnumber').get(0)}" />
										</span>
								
					</div></div>
					
						<div class="form-group" id="singleetm">
									<label class="col-md-3 control-label">Etm No<font
										color="red">*</font></label>
									<div class="col-md-4">
						                <s:select list="etmnumber" id="etmnumber1" name="etmidnumber" cssClass="form-control input-medium select2me" ></s:select>
									<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('etmidnumber').get(0)}" />
										</span>
								
					</div></div>
					
					          			            
				            				     <div class="form-group" id="multiissue">
									<label class="col-md-3 control-label">ETM Issues:<font
										color="red">*</font></label>

							 <s:select list="etmissue" id="etmissue2" name="etmissueid" cssClass="form-control input-medium select2me" multiple="true" ></s:select>
											<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('etmissueid').get(0)}" />
										</span>		
										</div>
										
				           <div class="form-group" id="issuesingle">
									<label class="col-md-3 control-label">ETM Issue:<font
										color="red">*</font></label>

							 <s:select list="etmissue" id="etmissue1" name="etmissueid" cssClass="form-control input-medium select2me"  ></s:select>
											<span class="help-block" style="color: red"> 
											<s:property	value="%{fieldErrors.get('etmissueid').get(0)}" />
										</span>		
										</div>
										
						
										
										
								</div>
							
								
								<div class="form-group">
									<label class="control-label col-md-3">Issue Generated Date:<font
										color="red">*</font></label>
									<div class="col-md-3">
										<div class="input-group input-medium date date-picker"
											data-date-format="dd-mm-yyyy" data-date-end-date="-0d">
											<input type="text" class="form-control" id="etmpickupdate" 
												 name="etmpickupdate" /> <span
												class="input-group-btn">
												<button class="btn default" type="button" >
													<i class="fa fa-calendar"></i>
												</button> 
											</span>
											<script>
													var curr_date = new Date().toJSON().slice(0,10);
													curr_date = curr_date.split("-");
													curr_date = curr_date[2]+"-"+curr_date[1]+ "-"+ curr_date[0];
													var dtval = document.getElementById('etmpickupdate').value;
													if (dtval == '') {
														$('#etmpickupdate').val(curr_date);
													}
													
												</script>
												
										</div>
									</div>
										
								</div>

				            </div>

                              <div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="button" class="btn blue" onclick="check()">Save</button>
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