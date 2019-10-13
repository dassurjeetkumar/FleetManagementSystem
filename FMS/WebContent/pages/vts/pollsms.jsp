
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="../assets/global/scripts/metronic.js"
	type="text/javascript"></script>
<script src="../assets/admin/layout/scripts/layout.js"
	type="text/javascript"></script>
<script src="../../assets/global/scripts/datatable.js"></script>
<script src="../../assets/admin/pages/scripts/table-ajax.js"></script>
<script type="text/javascript"
	src="../../assets/global/plugins/data-tables/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="../../assets/global/plugins/data-tables/DT_bootstrap.js"></script>
<script type="text/javascript"
	src="../../assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>


<script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
<script>

function getDepot(orgId){
	 var val=document.getElementById('divisionlist').value;
		 if(val!=0) {
        $.ajax({
            type: "post",
            url: '<%=request.getContextPath()%>/getDepot?val='+val,
            success: function(result) {
                document.getElementById('depotlist').innerHTML=result;
            }
        });
	 }
	
}
function getVehicle(depotID)
{
	//$('#search').val(); 
	document.getElementById("search").value='';
	$('#searchCnt').html(''); 
	var divId = document.getElementById("vehiclelist");
	divId.style.display = '';
	var val=document.getElementById('depotlist').value;
	 if(val!=0) {
		 $('#loadingmessage').show(); 
   $.ajax({
       type: "post",
       url: '<%=request.getContextPath()%>/getVehicleDataForSms?val='
						+ val,
				success : function(result) {
					document.getElementById('vehiclelist').innerHTML = result;
					$('#loadingmessage').hide(); 
				}
			});
		}
	}
	function activeInformation(deviceId) {

	}
	
	function validate(val){
		//alert('1'+val);
		bootbox.confirm("Are you sure you want to Fire the Command ??",
				function(result) {

					if (result == true) {
						
						document.getElementById("buttonName").value=val;
						//alert('document.getElementById("buttonName").value'+document.getElementById("buttonName").value);
						document.forms[1].submit();
						//document.getElementById("form").submit();
						//alert('deleted');
						//return true;
					}else{
						//alert('3');
						//return false;
					}
				});
	}
	function toggle(source) {
		  checkboxes = document.getElementsByName('deviceid');
		  for(var i=0, n=checkboxes.length;i<n;i++) {
		    checkboxes[i].checked = source.checked;
		  }
		}
	
	$(document).ready(function()
			{
				$('#search').keyup(function()
				{
					searchTable($(this).val());
				});
				
			});

			function searchTable(inputVal)
			{
				var table = $('#tblData');
				var x=1;
				table.find('tr').each(function(index, row)
				{
					var allCells = $(row).find('td');
					if(allCells.length > 0)
					{
						var found = false;
						allCells.each(function(index, td)
						{
							var regExp = new RegExp(inputVal, 'i');
							if(regExp.test($(td).text()))
							{
								found = true;
								return false;
							}
						});
						//alert(found);
						if(found == true){
							$(row).show();
							x++;
						}
						 else {
							 $(row).hide();
						 }
						
					}
					
				});
				//var rowCount = $('#tblData tr').length;
				//alert(x);
				$("#searchCnt").html('<B>Total Search Record:</B>'+(x-1));
			}
	
</script>
<!-- <form name="form1" id="form1" action="insertPoll.action" method="POST">

</form> -->
<form action="insertPoll.action" id="submitForm" method="POST">
	<div class="page-content-wrapper">
		<div class="page-content">
			<div class="row">
				<div class="col-md-8 ">
					<!-- BEGIN SAMPLE FORM PORTLET-->
					<div class="portlet box blue">
						<div class="portlet-title">
							<div class="caption">
							
								<i class="fa fa-gift"></i> Vehicle Details
								<div id='loadingmessage' style='display:none'>
						<center> Loading Please Wait...<img src='assets/images/loader1.gif' width="500px" align="middle"/></center> 
						</div>
							</div>
						</div>
						
						<div class="portlet-body form">
							
							<div class="form-body">
								<div class="form-group">
									<label>Division Name:</label>
									<s:select list="divisionlist" id="divisionlist"
										name="orgchart.org_chart_id"
										cssClass="select2_category form-control" headerKey="0"
										headerValue="Select" onchange="getDepot(this.value)"></s:select>
								</div>
								<div class="form-group">
									<label for="depotName">Depot Name:</label> <select
										class="form-control" id="depotlist"
										onchange="getVehicle(this.value)"></select>
								</div>
								<input type="checkbox" onClick="toggle(this)" /> Select All<br/>
								<p>
		<label for="search">
			<strong >Enter Keyword</strong>
		</label>
		<input type="text" id="search"/>
		<label id='searchCnt'></label>
	</p>
								<div class="form-group"  style="display: none; height: 1000px;overflow-y: scroll; " id="vehiclelist"  >
										
								</div>
							</div>

						</div>
					</div>
				</div>
				<div class="col-md-4 ">
					<!-- BEGIN SAMPLE FORM PORTLET-->
					<div class="portlet box blue ">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i> Message Details
							</div>
						</div>
						<div class="portlet-body form">
							<font color="green"><s:actionmessage /></font>
							<div class="form-body">
								<div class="form-group" align="justify">
									<table class="table table-striped table-bordered table-hover" style='table-layout:fixed;overflow: hidden;' >
									<input type='hidden' id='buttonName' name='buttonName'>
										<tr>
											<td><input type="button" class="btn red" onclick="validate(this.value);"
												value="SET_ODOM" ></td>
												<td align="justify">#set$140613238@aquila123#SET_ODOM:5000*</td>
										</tr>
										 <tr>
											<td><input type="button" class="btn red" onclick="validate(this.value);"
												value="ADD_GF" ></td>
											<td align="center">#set$150221032@aquila123#ADD_GF:20,12.9546,77.5953,50*</td>
										</tr>
										<tr>
											<td><input type="button" class="btn red" onclick="validate(this.value);"
												value="FOTA" ></td>
											<td align="justify">#set$150219501@aquila123#INIT_FOTA:60.243.245.180,21,ITBLR,itblr123,/home/ITBLR/,app_1_42BMTC,1234*
</td>

										</tr>
										<tr>
											<td><input type="button" class="btn red" onclick="validate(this.value);"
												value="ERRASEGF" ></td>
											<td align="right">##set$150221032@aquila123#ERRASE_GF*</td>

										</tr>
										<tr>
											<td><input type="button" class="btn red" onclick="validate(this.value);"
												value="ACCLIMIT" ></td>
											<td align="right">@aquila123#ACC_LIMIT:20*</td>
										</tr>
										<tr>
											<td><input type="button" class="btn red" onclick="validate(this.value);"
												value="DEACCLIMIT" ></td>
											<td align="right">@aquila123#DEACC_LIMIT:20*</td>
										</tr>
										<tr>
											<td><input type="button" class="btn red" onclick="validate(this.value);"
												value="LIST_GF" ></td>
											<td align="right">@aquila123#LIST_GF*</td>
										</tr>
										<tr>
											<td><input type="button" class="btn red" onclick="validate(this.value);"
												value="ADD_DOMAIN" ></td>
											<td align="right">@aquila123#CFG_GPRS:airtelgprs.com,,,bmtce.com,1920*
											</td>
										</tr>
										<tr>
											<td><input type="button" class="btn red" onclick="validate(this.value);"
												value="CHECK_CFG" ></td>
											<td align="right">%CHKCFG%
											</td>
										</tr>
										
										<tr>
											<td><input type="button" class="btn red" onclick="validate(this.value);"
												value="restart" ></td>
											<td align="right">#restart:aquila123
											</td>
										</tr>
										<tr>
											<td><input type="button" class="btn red" onclick="validate(this.value);"
												value="frequency_set" ></td>
											<td align="right">@aquila123#CFG_TL:GPRS,10S,5M*
											</td>
										</tr>	
										<!--<tr>
											<td><input type="submit" class="btn red"
												value="Store Current Configuration" name="buttonName"></td>
											<td align="right"><017(DEVICE-ID)EF1234557</td>
										</tr>
										<tr>

											<td><input type="submit" class="btn red" value="FOTA"
												name="buttonName"></td>
												<td align="right"><029(DEVICE-ID)F112345343456563F5647</td>
										</tr>
										<tr>

											<td><input type="submit" class="btn red"
												value="1st BS IP ADDR" name="buttonName"></td>
												<td align="right" witdh="30%" ><041(DEVICE-ID)E112345012010.008.053.131:121114</td>
										</tr>
										<tr>

											<td><input type="submit" class="btn red"
												value="Vehicle Stationary" name="buttonName"></td>
												<td align="right"><024(DEVICE-ID)E1123450211FC59</td>

										</tr>
										<tr>

											<td><input type="submit" class="btn red"
												value="Power On" name="buttonName"></td>
												<td align="right"><021(DEVICE-ID)E11234502315B</td>

										</tr>
										<tr>

											<td><input type="submit" class="btn red"
												value="Speed Normalized" name="buttonName"></td>
												<td align="right"><022(DEVICE-ID)E1123450301369</td>

										</tr>
										<tr>

											<td><input type="submit" class="btn red"
												value="Factory Resetting" name="buttonName"></td>
												<td align="right"><017(DEVICE-ID)E4123455B</td>
										</tr>
										<tr>

											<td><input type="submit" class="btn red"
												value="Read Configuration" name="buttonName"></td>
												<td align="right"><022(DEVICE-ID)E1123450301369</td>
										</tr> -->
									</table>
								</div>

							</div>

						</div>

					</div>
				</div>

			</div>
		</div>

	</div>




</form>
<script>
	   window.history.pushState("","", "pollmsg.action");
</script>
