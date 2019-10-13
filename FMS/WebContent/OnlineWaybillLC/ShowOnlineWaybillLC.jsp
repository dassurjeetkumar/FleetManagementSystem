<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="//www.google.com/jsapi"></script>

<script>
function validateForm() 
{
	var datecheck=$("#ticketDate").val();
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
	if(datecheck==null || datecheck=="")
	{
	bootbox.alert("please Select Date");
	return false;
	}
	
}
	function goView() {
		document.forms[0].action = 'showOnlineWaybillLc.action';
		document.forms[0].submit();
	}
	$(document).ready(function(){
		var cdate=$('#ticketDate1').val();
		//alert("cdate"+cdate);
		$("#ticketDate").val(cdate);
		var id=$("#depotId").val();
		
	
		var  depot=$('#did').val();
		
		getDepot();
		
		setTimeout(function(){ 
		
		
			$("#depotId").val(depot);
			
		 }, 500);
		 
		
	 });
	function getDepot(){
		
		
		 var val1=document.getElementById('ticketDate').value;
		 
		// alert("date"+$("#ticketDate2").val());
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

<%-- <script> --%>
<!-- // var tableToExcel = (function() { -->
<!-- //   var uri = 'data:application/vnd.ms-excel;base64,' -->
<!-- //     , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head>[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]</head><body><table>{table}</table></body></html>' -->
<!-- //     , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) } -->
<!-- //     , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) } -->
<!-- //   return function(table, name) { -->
<!-- //     if (!table.nodeType) table = document.getElementById(table) -->
<!-- //     var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML} -->
<!-- //     window.location.href = uri + base64(format(template, ctx)) -->
<!-- //   } -->
<!-- // })() -->
<%-- </script> --%>



<title>Insert title here</title>
</head>
<body>
	<div class="page-content-wrapper">
		<div class="page-content">

			<div class="tab-content">
				<div class="row"></div>
				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							Line Checking Ticket Status Report<small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>View Line Checking Ticket Status Report
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
							<form action="showOnlineDepotGprs.action" method="post"
								class="form-horizontal" onsubmit="return  validateForm()">


								<div class="form-group">
									<label class="col-md-3 control-label">Division<sup><font
											color="red">*</font></sup></label>
									<div class="col-md-3">
										<s:select list="divisionMap" id="divisionId" name="divisionId"
											cssClass="select2_category form-control" onchange="getDepot()"></s:select>
										<s:if test="fieldErrors.get('depotId').size() > 0">
											<span style="color: red;"><s:property
													value="fieldErrors.get('depotId').get(0)" /></span>
										</s:if>
									</div>

								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Depot Code<sup><font
											color="red">*</font></sup></label>
											<input type="hidden" value="<s:property value="depotId" />" id="did"/>
									<div class="col-md-3">
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
									<div class="col-md-3">
										<div class="input-group input-small date date-picker"
											data-date-format="dd-mm-yyyy">
											<s:textfield name="ticketdate" id="ticketDate"  cssClass="form-control input-small"  readonly="readonly"/>
											<input type="hidden" name="tdate" value="<s:property value="ticketDate" />" id="ticketDate1" readonly/>
										
											 <span
												class="input-group-btn">
												<button class="btn default" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
											<s:if test="%{ticketDate ==null}">
											<script>
												var curr_date = new Date()
														.toJSON().slice(0, 10);
												curr_date = curr_date
														.split("-");
												curr_date = curr_date[2] + "-"
														+ curr_date[1] + "-"
														+ curr_date[0];
												$('#ticketDate').val(curr_date);
											</script>
											</s:if>
										</div>

									</div>
								</div>


								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn default">Submit</button>
										<button type="button" class="btn default" onclick="goView()">Cancel</button>
										<span><button type="button" class="btn default" id="btnExport" onclick="tabletoExcel();"> EXPORT  </button></span>&nbsp;<span>
 
<!-- 										 <button type="button"  class="btn default" onclick="tableToExcel('ETMAvailListable', 'W3C Example Table')" >Export to Excel</button> -->
										
										<%-- <input type="hidden" name="" value="<s:property value="ticketDate"/>"/>
										<input type="hidden" name="" value="<s:property value="tickettate"/>"/> --%>
									</div>
								</div>
							<div class="table-scrollable">
						<input type="hidden" name="formfourID"
							value="<s:property value="formfour.id" />" />
						<table class="table table-striped table-bordered table-hover"
							id="triptable1" style="height: 50px">

							<thead>
								<tr bgcolor="#819FF7">
								<th scope="col" align="center">Sr. No.</th>
									<th scope="col" align="center">Depot Name</th>
									<th scope="col" align="center">LC Etim Count</th>
									<th scope="col" align="center">LC Ticket Count</th>
									

								</tr>
							</thead>
							<tbody>
									<s:if test="%{showWaybill.size>0}">
								<s:iterator value="showWaybill" var="trips" status="ctr">
										
									<tr>
										<td align="center"><s:property value="%{#ctr.index + 1}" /></td>
										<td align="center"><a href='Showlcdepotwaybill.action?divisionId=<s:property value="divisionId" />&ticketdate=<s:property value="ticketDate" />&depotId=<s:property value="depotId" />&ticketDate=<s:property value="ticketDate" />'><s:property value="waybillNo" /></a></td>
										<td align="center"><s:property value="scheduleNo" /></td>
										<td align="center"><s:property value="scheduleType" /></td>
										<%-- <td align="center"><s:property value="shiftType" /></td>
										<td align="center"><s:property value="etimNo" /></td>
										<td align="center"><s:property value="routeNo" /></td>
										<td align="center"><s:property value="vehicleNo" /></td>
										<td align="center"><s:property value="total_ticket_amount" /></td>
										<td align="center"><a href='showOnlineWaybillsGprswaybillwise.action?waybillno=<s:property value="waybillNo" />&ticketdate=<s:property value="ticketDate" />&depotid=<s:property value="depotId" />'><s:property value="noOfTickets" /></a></td> --%>
									</tr>
									
								</s:iterator>
								
								 </s:if>
									 <s:else>
  								 <td colspan="4"><center><b color="red">  <font color="red">No Data Found!</font> </b></center></td>
								</s:else>
								<%-- <tr>
								<td colspan="5" align="center">Total</td>
								<td align="center"><s:property value="totalAmount" /></td>
								<td align="center"><s:property value="totalTickets" /></td>
								
								</tr> --%>
							</tbody>
						</table>

<script>                 	
   function tabletoExcel(btnExport){     
  	var htmltable= document.getElementById('btnExport');   
    $( "#header-fixed" ).remove();
    var inputHTML = "<table border='1'>";
    inputHTML += "<tr>";
    inputHTML += "<th  align='left'colspan='5'>Bangalore Metropolitan Transport Corporation</th>";
    inputHTML += "</tr>";
    inputHTML += "<th  align='left'colspan='5'>Line Checking Ticket Status Report</th>";
    inputHTML += "</tr>";
    inputHTML += "<tr>";
    inputHTML += "<th colspan='3' align='left'>Date Range:</th>";
    inputHTML += "<th colspan='2' align='left'>" + $("#startdate").val() + " to " + $("#endate").val() + "</th>";
    inputHTML += "</tr>";
    inputHTML += "</table>";
    var htmltable = document.getElementById("triptable1");
    var html = inputHTML + htmltable.outerHTML;
    var downloadLink = document.createElement("a");
    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(html);
    downloadLink.download = "Line Checking Ticket Status Report.xls";
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
}</script>

					</div>

							</form>
							<!-- END FORM-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>