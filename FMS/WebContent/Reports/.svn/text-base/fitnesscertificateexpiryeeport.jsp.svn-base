version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/assets/global/plugins/jsapi.js"></script>
 
 
 <script>
 
function getdteail(){
	
	// var val=document.getElementById('vehicleidlistid').value;
	 	// if(val!=0) 
	 /* 		$('#pageLoader').show(); */
	 		var var1=$("#startdate").val();
	 		var var2=$("#divisionId").val();
	 		var var3=$("#depotId").val();
	 		
        $.ajax({
        
            type: "post",
            url: '<%=request.getContextPath()%>/FitnesscertiFicateexpiryReport.action?startdate='+var1+'&divisionId='+var2+'&depotId='+var3,
            success: function(result) {
            	//alert(result);
    /*         	$('#pageLoader').hide(); */
                document.getElementById('FitnessExpiredid').innerHTML=result;
            }
        });
	
}
</script>

<SCRIPT>
function getDepot(){
		
		
		 var val1=document.getElementById('startdate').value;
		 
		// alert("date"+$("#startdate2").val());
		 var val=document.getElementById('divisionId').value;
		//alert("val"+val);
// 		 document.getElementById("select2-chosen-4").innerHTML = "Select";
// 			$("#depotId").val('0');
			 if(val!=0) {
	        $.ajax({
	            type: "post",
	            url: '<%=request.getContextPath()%>/getDepotName?val=' + val,
				success : function(result) {
					//alert("result"+result);
					document.getElementById('depotId').innerHTML = result;
					
				}
			});
	        
	      
		}
			  
	}
</script>
<script>
function validateForm() 
{
	
	var depot=$("#depotId").val();
	
	var divi=$("#divisionId").val();
	
	if(divi==null || divi=='0')
	{
		bootbox.alert("Please Select Division");
		return false;
	
	}
	
	if(depot==null || depot=='0')
	{
	bootbox.alert("Please Select Depot");
	return false;
	}
	
	
}

</script>

<script>
function printDiv() {     
    //  document.getElementById("print").style.visibility='hidden';     
    var divElements = document.getElementById("header").innerHTML;
    divElements += document.getElementById("printid").innerHTML;

    var mywindow = window.open("<%=request.getContextPath()%>/Print.jsp");
   
    mywindow.onload = function() {
    mywindow.document.body.innerHTML=divElements;
    mywindow.document.body.innerHTML=divElements;
    mywindow.print();
    mywindow.close();
    }
            
}
function rawPrint(){
	//alert("hdfdfdf");

	/* var htmlCode = "<applet archive='/doa/applet/IqPrint.jar' name='print' code='IqPrint' width=0 height=0><param name=httpUrl value='/doa/Ticketing/CashierReport.txt'><param name=printText value=''><param name=printDevice value='epson'><param name=printSubmitUrl value=''><param name=paginationRequired value='Y'><param name=directPrint value='Y'><param name=displayRequired value='N'></applet>";
	$("#resultset").html(htmlCode); */
        $.ajax({
          type: "post",
          url:"Ticketing/RawPrint.jsp",
          data:"LicenseExpiredReport=new",
            success: function(result){
            	$("#licenseExpiredrow").html(result);
            	//alert(result);
              
            }
        });
}
</script>
 
 

<title>Insert title here</title>
</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">
	

			<div class="tab-content">

				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>View Fitness Certificate  Expired Report
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> 
							</div> -->
						</div>

		<div class="portlet-body form">

							<div class="col-md-12" align="left" style="font-size: 1.1em">

								<%-- <span class="help-block" style="color: red; list-style: none"><s:actionerror /></span> --%>
								<span class="help-block" style="color: red; list-style: none"><s:actionmessage /></span>
							</div>
							<!-- BEGIN FORM-->
							<form action="" method="post" class="form-horizontal">

								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Division<font
											color="red">*</font></label>
											<div class="col-md-4">
										<s:select list="divisionMap" id="divisionId" name="divisionId"
											cssClass="select2_category form-control" onchange="getDepot()"></s:select>
										<s:if test="fieldErrors.get('depotId').size() > 0">
											<span style="color: red;"><s:property
													value="fieldErrors.get('depotId').get(0)" /></span>
										</s:if>
									</div>
									</div>
								

								<div class="form-group">
									<label class="col-md-3 control-label">Depot<font
										color="red">*</font></label>
									<div class="col-md-4">
										<select id="depotId" name="depotId" class="select1_category form-control">
										
											</select>
										<s:if test="fieldErrors.get('depotId').size() > 0">
											<span style="color: red;"><s:property
													value="fieldErrors.get('depotId').get(0)" /></span>
										</s:if>
									</div>

								</div>




 <div class="form-group">
									<label class="col-md-3 control-label">Date<sup><font color="red">*</font></sup>
									</label>
									<div class="col-md-4">
										<div class="input-group input-small date date-picker"
											data-date-format="dd/mm/yyyy">
											<s:textfield name="startdate" id="startdate"  cssClass="form-control input-small"  readonly="readonly"/>
											<input type="hidden" name="tdate" value="<s:property value="startdate" />" id="startdate1" readonly/>
										
											 <span
												class="input-group-btn">
												<button class="btn default" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
											<s:if test="%{startdate ==null}">
											<script>
												var curr_date = new Date()
														.toJSON().slice(0, 10);
												curr_date = curr_date
														.split("-");
												curr_date = curr_date[2] + "/"
														+ curr_date[1] + "/"
														+ curr_date[0];
												$('#startdate').val(curr_date);
											</script>
											</s:if>
										</div>

									</div>
								</div>



								
								<div class="form-group">
								<label class="control-label col-md-3"></label>
									<div class="col-md-3" id="">
									<button type="button" class="btn default" onclick="getdteail()" >Submit</button>
									</div>
									</div>
								</div>
								</form>
								<div id=FitnessExpiredid></div>		
								</div>
			</div>
			</div></div></div></div>	 
							

</body>
</html>
	
	