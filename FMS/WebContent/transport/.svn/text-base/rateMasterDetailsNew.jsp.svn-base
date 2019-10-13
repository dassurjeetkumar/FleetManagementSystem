<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<script type="text/javascript">
function validate() {
//    var a='';
// 	return Empty();
    
	document.forms[0].action = 'rateDetailsUpdate.action';
	document.forms[0].submit();
} 
	function goView()
	{		
		document.forms[0].action = 'canclePeakHours.action';
		document.forms[0].submit();
	}
	/* $(document).ready(function() {
		window.history.pushState("", "", "rateDetailsUpdate.action");
		
	});  */
</script>
<script>
function myTrim(x) {
    return x.replace(/^\s+|\s+$/gm,'');
}	
function calcFare(stageNo){
	  var adult=myTrim(document.getElementById("adult"+stageNo).value);
	  document.getElementById("adult"+stageNo).value=parseInt(adult);	  
  	  
//validate input value
	if(checkInt(adult)){
  
	 var child=Math.round(adult*0.50);
 	 var senior=Math.round(adult*0.75);
  
  	 document.getElementById("children"+stageNo).value=child;
  	 document.getElementById("seniorCitizen"+stageNo).value=senior;

	}else{
		alert('Fare amount value entered is Invalid.');
		document.getElementById("adult"+stageNo).value="";
	}
}

function calcFare2(pType,stageNo){
	  var adult=myTrim(document.getElementById(pType+stageNo).value);
	  document.getElementById(pType+stageNo).value=parseInt(adult);	  
	//validate input value
		if(checkInt(adult)){
			  

		}else{
			alert('Fare amount value entered is Invalid.');
			 
			adult=myTrim(document.getElementById("adult"+stageNo).value);
			  document.getElementById("adult"+stageNo).value=adult;	  
			  
			  var child=Math.round(adult*0.50);
			  var senior=Math.round(adult*0.75);
			  
			  document.getElementById("children"+stageNo).value=child;
			  document.getElementById("seniorCitizen"+stageNo).value=senior;
		}
	}
function isInt(n) {
	   return typeof n === 'number' && n % 1 == 0;
	}
	
function isInteger(n) {
	if(parseFloat(n) != parseInt(n, 10)){
	   return true;
	}else{
		return false;
	}
	} 
	
function checkInt(val){
	
	//var val=document.getElementById('version').value;
	
	if(isNaN(val) || isInt(val) || val<0 || isInteger(val)){
		
	
	   //document.getElementById('integerVal').innerHTML='Invalid value';
	   return false;
	}else{
		return true;
	}
		
}

function checkInt2(){
	
	var val=document.getElementById('version').value;
	//alert("version"+val);
	if(isNaN(val) ||isInteger(val) || isInt(val)){
		//alert("hiii"+val);
		if(val==""){
			//alert("heloooo"+val);
			 document.getElementById('integerVal').innerHTML='Please enter Version Number';   
		   }
		   else{
			 //  alert("fffffff"+val);
		   document.getElementById('integerVal').innerHTML='Version Number should be Integer value';
		   }
	   return false;
	}
}

function validateStages(){
	var i=0;
	alert('valid');
	var aFare=0,cFare=0,sFare=0;
	for (i = 1; i <= 70; i++) {
		alert(i);
	    aFare=document.getElementById("adult"+i).value;
	    cFare=document.getElementById("children"+i).value;
	    sFare=document.getElementById("seniorCitizen"+i).value;
	   
	   if(aFare<=document.getElementById("adult"+i).value && cFare<=document.getElementById("children"+i).value && sFare<=document.getElementById("seniorCitizen"+i).value){
		   return true;
	   }else{
		   return false;
	   }
	}
}function validateStages(){
	var i=0;
	alert('valid');
	var aFare=0,cFare=0,sFare=0;
	for (i = 1; i <= 70; i++) {
		alert(i);
	    aFare=document.getElementById("adult"+i).value;
	    cFare=document.getElementById("children"+i).value;
	    sFare=document.getElementById("seniorCitizen"+i).value;
	   
	   if(aFare<=document.getElementById("adult"+i).value && cFare<=document.getElementById("children"+i).value && sFare<=document.getElementById("seniorCitizen"+i).value){
		   return true;
	   }else{
		   return false;
	   }
	}
}function validateStages(){
	var i=0;
	alert('valid');
	var aFare=0,cFare=0,sFare=0;
	for (i = 1; i <= 70; i++) {
		alert(i);
	    aFare=document.getElementById("adult"+i).value;
	    cFare=document.getElementById("children"+i).value;
	    sFare=document.getElementById("seniorCitizen"+i).value;
	   
	   if(aFare<=document.getElementById("adult"+i).value && cFare<=document.getElementById("children"+i).value && sFare<=document.getElementById("seniorCitizen"+i).value){
		   return true;
	   }else{
		   return false;
	   }
	}
}function validateStages(){
	var i=0;
	alert('valid');
	var aFare=0,cFare=0,sFare=0;
	for (i = 1; i <= 70; i++) {
		alert(i);
	    aFare=document.getElementById("adult"+i).value;
	    cFare=document.getElementById("children"+i).value;
	    sFare=document.getElementById("seniorCitizen"+i).value;
	   
	   if(aFare<=document.getElementById("adult"+i).value && cFare<=document.getElementById("children"+i).value && sFare<=document.getElementById("seniorCitizen"+i).value){
		   return true;
	   }else{
		   return false;
	   }
	}
}

function validateStages(){
	//checkInt2();
	var i=0;


	
	for (i = 1; i <= 70; i++) {

		if(i>1){
		var aFare=parseInt(document.getElementById("adult"+(i-1)).value);
	    var cFare=parseInt(document.getElementById("children"+(i-1)).value);
	    var sFare=parseInt(document.getElementById("seniorCitizen"+(i-1)).value);
		
		var a=parseInt(document.getElementById("adult"+i).value);
		var c=parseInt(document.getElementById("children"+i).value);
		var s=parseInt(document.getElementById("seniorCitizen"+i).value);
		
		if(aFare<=a && cFare<=c && sFare<=s){
		}else{
			   document.getElementById('error').innerHTML='Fare for stage '+i+' should  be greater than or equals to stage '+(i-1);
			   return false;
		}
		
		}
		
	}
}

function Empty(){
var a='';
	for (var i=1; i <= 70; i++) {
		
			var aFare=document.getElementById("adult"+(i)).value;
				
			if(aFare == ' '){	
				//alert("aFare "+aFare+i);
			alert("Space is not allowed Here");
			//aFare.focus();
			return false;
		
}
	
}
}

</script>


<div class="page-content-wrapper">
	<div class="page-content">
		<div class="tab-content">
		<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					 RATE MASTER <small></small> 
					
					</h3>
					<%-- <FONT color="green"><s:actionmessage /></FONT> --%>
					<!-- END PAGE TITLE & BREADCRUMB-->
		</div>
			<div class="tab-pane active" id="tab_0">
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-gift"></i>Edit Rate Master Detail
						</div>

						<div class="tools">
							
						</div>
					</div>
					<div class="portlet-body form">
							<font color="red"> <s:actionerror /></font>								
							<font color="green"> <s:actionmessage/> </font>

						<!-- BEGIN FORM-->
						<form action="rateDetailsUpdate" onsubmit="return validateStages();" class="form-horizontal" method="post">
						
						<br>
						<div align="center">
						<div class="form-body">
									<div class="form-group">									
										<label class="col-md-3 control-label">Version Number:<font color="red">*</font></label>
									<div class="col-md-2">
									<s:textfield name="version" cssClass="form-control"></s:textfield>
									<s:if test="fieldErrors.get('versionNumber').size() > 0">
		     								<span style="color:red;"><s:property value="fieldErrors.get('versionNumber').get(0)" /></span>
											</s:if>
											<font color="red"><span id="integerVal"></span></font>
										</div>										
										<div class="col-md-2">
										<label class="control-label">Fare Type:<font color="red">*</font></label>
										</div>	
									<div class="col-md-3">	
									   	 <input type="text" class="form-control" name="fareType" value=" <s:property value="fareType" />" readonly="readonly"/>
																	
										</div>
									</div>
								</div>
						
						</div>
							<br>
							<input type="hidden" name="rateMasterid" value='<s:property value="peakhour.rateMaster.rateMasterId"/>' />
						
							<table class="table table-striped table-bordered table-hover"
								id="sample_6" style="height:300px; overflow-y:scroll; display:block;">
								<!-- <table class="table table-striped table-bordered table-hover" id="sample_1"> -->
								<thead >
									<tr>

										<th style="width1: 8px;">Stage No</th>
										<th>Adult</th>
										<th>Children</th>
										<th>Senior Citizen</th>


									</tr>
								</thead>
								<tbody>

									<s:iterator value="rateMasterDetailList"
										id="rateMasterDetailList">
										<tr>
											<td align="center"><input type="text" size="3"
												class="form-control" readonly="readonly"
												name="stageNo<s:property value='stageNo'/>"
												value="<s:property value="stageNo"/>"></td>

											<td align="center"><input size="15"
												id="adult<s:property value='stageNo'/>" type="text"
												class="form-control"
												name="adult<s:property value='stageNo'/>"
												value="<s:property value="adult"/>"									
												onchange="calcFare(<s:property value='stageNo'/>)"
												tabindex="<s:property value='stageNo'/>" maxLength="8"></td>

											<td align="center"><input size="15"
												id="children<s:property value='stageNo'/>" type="text"
												class="form-control"
												name="children<s:property value='stageNo'/>"
												value="<s:property value="children"/>"
												onchange="calcFare2('children',<s:property value='stageNo'/>)"
												maxLength="8" >
											</td>

											<td align="center"><input size="15"
												id="seniorCitizen<s:property value='stageNo'/>" type="text"
												class="form-control"
												name="seniorCitizen<s:property value='stageNo'/>"
												value="<s:property value="seniorCitizen"/>"
												onchange="calcFare2('seniorCitizen',<s:property value='stageNo'/>)"
												maxLength="8">
											</td>


										</tr>
									</s:iterator>
								</tbody>
							</table>
							<div class="form-actions fluid" align="center">

								<div class="col-md-offset-3 col-md-5">
									<button  type="submit" class="btn blue" onclick="Empty()" onSubmit="validate()">Save</button>
									<button type="button" class="btn red" onclick="goView()">Cancel</button>
								</div>


							</div>
							
						</form>
						<!-- END FORM-->
					</div>
				</div>
			</div>
		</div>
	</div>
</div>