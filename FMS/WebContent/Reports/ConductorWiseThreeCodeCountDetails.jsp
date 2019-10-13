  <?xml version="1.0" encoding="UTF-8" ?>
  <%@ taglib prefix="s" uri="/struts-tags" %>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  <html>
<head>
<link rel="stylesheet" href="css/page-style.css" type="text/css"
	media="screen" />
<link rel="shortcut icon" href="favicon.ico" type="../images/x-icon" />
<link rel="stylesheet" href='./css/metallic.css' type="text/css" />
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />


<script type="text/javascript">
window.onload=jsFunction;

function jsFunction(){
	var tokenId = $('#tokenId').val();	
	var startdate = $('#startdate').val();	
	var enddate = $('#enddate').val();	
	var depotlist1 = $('#depotlist1').val();	
	//alert(enddate);
	
		$.ajax({
        type: "post",
        url: '<%=request.getContextPath()%>/getajaxconductordetails.action?startdate='+startdate+'&enddate='+enddate+'&tokenId='+tokenId+'&depotlist1='+depotlist1,
          success: function(result) {
       	 document.getElementById('details').innerHTML=result;
       	 fixHeader();
          }
     });
}

function callCancell(){
	window.location.href='getConductorWiseThreeCodeCount.action';
}

function printDiv() {     
    
    //  document.getElementById("print").style.visibility='hidden';     
    var divElements = document.getElementById("header").innerHTML;
    divElements += document.getElementById("details").innerHTML;
   // alert("divElements"+divElements);
   
   var noOfTableExist = 0;
    try{
		$("#bagwisedailypassconsuptionid table").each(function(){
			noOfTableExist++;
		});
		
		console.log("Total no of tables : " + noOfTableExist);
		/* If two table exist  then remove the last table on print click*/
		if(noOfTableExist >= 2){
			divElements = divElements.substring(0, divElements.indexOf("</table>") + 8) + "</div></div>";
		}
	}catch(err){
	    console.log("ExceptionCaught : " + err);
	}

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
          type: "get",
          url:"Ticketing/RawPrint.jsp",
          data:"offlineVoucherreport=new",
            success: function(result){
            	$("#detailsraw").html(result);
            	//alert(result);
              
            }
        });
}

</script>
</head>
<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<div class="modal fade" id="portlet-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
							<h4 class="modal-title">Modal title</h4>
						</div>
						<div class="modal-body">
							 Widget settings form goes here
						</div>
						<div class="modal-footer">
							<button type="button" class="btn blue">Save changes</button>
							<button type="button" class="btn default" data-dismiss="modal">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			
			<!-- END STYLE CUSTOMIZER -->
			
			
			<div class="row">
			<div class="col-md-12">
				<!-- BEGIN EXAMPLE TABLE PORTLET-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-globe"></i>Conductor Wise Three Code Details
						</div>
						
					</div>

					<div class="portlet-body">
						<div class="table-scrollable">
<input type='hidden' id="tokenId"  value='<s:property value="tokenId"/>'/>
<input type='hidden' id="startdate"  value='<s:property value="startdate"/>'/>
<input type='hidden' id="enddate"  value='<s:property value="enddate"/>'/>
<input type='hidden' id="depotlist1"  value='<s:property value="depotlist1"/>'/>

<div id="details" ></div>


							 <div align='center'>
<%-- 							 <span><input type='button' class='btn green' id='button1' onclick='printDiv()' value='Print' /></span> --%>
<%-- 							 &nbsp;<span><input type='button' class='btn blue' id='button1' name='rawprint' onclick='rawPrint()' value='RawPrint' /></span> --%>
							 &nbsp;<span><input type='button' class='btn green' id='button1' name='cancel' onclick='callCancell()' value='Cancel' /></span></div>
						<div id="detailsraw">
			</div>	
						</div>
						</div>
					</div>
					
					</div>  
					</div>
					
				</div>
			</div>
		</div>
	
	
	
	
	
