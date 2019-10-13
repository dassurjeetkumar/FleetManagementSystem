<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.trimax.its.fare.model.FareChart,com.trimax.its.transport.model.BusStops,com.trimax.its.fare.action.FareChartMasterAction"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/stickyheader.css" />

<style> 
    .inputs  { 

        background: #AE1E1 ; 
        border: 1px solid #C8C8C8; 
        color: black; 
        font: 14px Helvetica, Arial, sans-serif;
        margin: 0 0 10px; 
        width:50px; 
        height:22px;
    } 

    .inputs:focus { 

        background-color: #FFF; 
        border: 1px solid #6ca9d2;/* #ED1C24; */ 
        outline: none; 
    } 

    #cell_input
    {
        position: relative;
        width: 80px;
        height: 20px;
        /* background-color:#f2f2f2; */
    }

    #cell_input label
    {
        display:inline-block;
        margin-right:4px;
        vertical-align:top; 
        font-size:12px;
    }

    #cell_head
    {
        position: relative;
        width: 50px;
        height: 20px;
        margin:-7px 0px -6px 0px;
    }

    #cell_head_text
    {
        display:inline-block;
        width: 25px;
        height: 20px;
        font-size:11px;
    }
</style> 

<script>

function printDiv(divName) {     
    
     // document.getElementById(headerinfoid).style.visibility='hidden';  
       $('#pid').hide();
       $('#cid').hide();
       $('#sid').hide();
       $('#ptype').hide();
     $('#headerinfoid').show();

     $('#captionid').show();
  
     var divElements = $(".sticky-enabled").removeAttr("style").html();
     
    // console.log(divElements);
//     alert("divElements"+divElements);
   
   	var data = $("#tempValue").val();
   	console.log(data);
   	
	$("#ex").val(data);
	 var headerHTML = document.getElementById("headerinfoid").innerHTML;
	   	divElements = "<table width=200%>"+headerHTML+"</table>" + "<table width=200%>"+data+"</table>";
// 	   	console.log(data);
// 	   	alert("divElements"+data);
    var mywindow = window.open("<%=request.getContextPath()%>/fare.jsp");
    
    mywindow.onload = function() {
    mywindow.document.body.innerHTML=divElements;
    mywindow.document.body.innerHTML=divElements;
   mywindow.print();
   mywindow.close();
    }
    $('#headerinfoid').show();
    $('#pid').show();
       $('#cid').show();
       $('#sid').show();
  
     $('#captionid').show();
}
</script>

 	         	<script type="text/javascript">               	
 	           function tabletoExcel(btnExport){ 
 	        		 
 	    		  $('#pid').hide();
 	    	       $('#cid').hide();
 	    	     $('#headerinfoid').hide();
 	    	     $('#header').show();
 	    	     $('#captionid').show();
 	    	     
 	    	     
 	    	     var inputHTML = "<table border='1'>";
 	             inputHTML += "<tr>";
 	             inputHTML += "<th  align='center'colspan='8'>Bangalore Metropolitan Transport Corporation</th>";
 	             inputHTML += "</tr>";
 	            inputHTML += "<tr>";
 	             inputHTML += "<th  align='Center'colspan='8'>Fare Chart Details</th>";
 	             inputHTML += "</tr>";
 	           
 	             inputHTML += "</table>";
 	    		    
 	          /*   var divElements = $(".sticky-enabled").removeAttr("style").html();
 	          	var data = $("#tempValue").val();
 	          	console.log(data);
 	          	
 	       	$("#ex").val(data);
 	       	 var headerHTML = document.getElementById("headerinfoid").innerHTML;
 	       	   	divElements = "<table width=200%>"+headerHTML+"</table>" + "<table width=200%>"+data+"</table>";
 	    	      */
 	    	    // var headerHTML =$(".sticky-enabled").removeAttr("style").html();
 	    	    var headerHTML = document.getElementById("headerinfoid").innerHTML;
 	    	    var data = $("#tempValue").val();
 	    	   $("#ex").val(data);
 	    	  data=data.replace(/[\u0080-\uffff]/g, "&"); 
 	    	 data=data.replace(/ &/g," ");
 	    	// data=data.replace(/     /g,"");
 	    	//data=data.Replace("(?<=\d+)\s+(?=\d+)", "");
 				var divElements =inputHTML+headerHTML+data;
 				
 	    	    console.log(divElements);
 				var downloadLink = document.createElement("a");
 	    	    downloadLink.href = 'data:application/vnd.ms-excel,' + encodeURIComponent(divElements);
 	    	    downloadLink.download = "Fare Chart.xls";
 	    	    document.body.appendChild(downloadLink);
 	    	    downloadLink.click();
 	    	    document.body.removeChild(downloadLink);
 	    	    
 	    	    $('#pid').show();
 	    	       $('#cid').show();
 	    	     $('#headerinfoid').show();
 	    	     $('#header').hide();
 	    	     $('#captionid').hide();
 	      }
    </script>



<script>


    function isInteger(n) {
        if (parseFloat(n) != parseInt(n, 10)) {
            return true;
        } else {
            return false;
        }
    }

    function isInt(n) {
        return typeof n === 'number' && n % 1 == 0;
    }

    function checkInt(val) {

        //var val=document.getElementById('version').value;

        if (isNaN(val) || isInt(val) || isInteger(val) || val < 0) {


            //document.getElementById('integerVal').innerHTML='Invalid value';
            return false;
        } else {
            return true;
        }
    }

   var erorFlag = false;
    var arr = [];
    $(document).ready(function () {
        $('.inputs').change(function () {


            //validate input value
            if (checkInt($(this).attr('value'))) {

                //validation of fare amount in triangle
                //01/09/14

                var vl = $(this).parent().closest('td').attr('id');

                var arr2 = vl.split(":");
                var c1 = parseInt(arr2[0]) - 1 + ':' + arr2[1];//up
                var c2 = parseInt(arr2[0]) + 1 + ':' + arr2[1];//dwn
                var c3 = arr2[0] + ':' + (parseInt(arr2[1]) - 1);//left
                var c4 = arr2[0] + ':' + (parseInt(arr2[1]) + 1);//right

                //alert('prev='+c1+' curr='+vl+' next='+c2+' lt='+c3+' rt='+c4);

                var pval = '', val = '', nval = '', lval = '', rval = '';


                var elem = document.getElementById('f' + c1);
                if (elem != null) {
                    pval = document.getElementById('f' + c1).value;
                }

                var elem = document.getElementById('f' + vl);
                if (elem != null) {
                    val = document.getElementById('f' + vl).value;
                }

                var elem = document.getElementById('f' + c2);
                if (elem != null) {
                    nval = document.getElementById('f' + c2).value;
                }

                var elem = document.getElementById('f' + c3);
                if (elem != null) {
                    lval = document.getElementById('f' + c3).value;
                }

                var elem = document.getElementById('f' + c4);
                if (elem != null) {
                    rval = document.getElementById('f' + c4).value;
                }

                //alert('up='+pval+' curr='+val+' down='+nval+' left='+lval+' right='+rval);

                //check for valid data
                //up
                console.log(!erorFlag);
                if (pval != "" && pval != null && !erorFlag) {
                    if (parseInt(val) < parseInt(pval) )  {
                        alert('Fare Amount should be Greater than or Equal to Previous Stop');
                        var self = $(this);
                        erorFlag = false;
                        
                        /* setTimeout(function () {
                            self.focus();
                        }, 1); */
                    }
                }else{
                erorFlag = true;
                }

                //down
                if (nval != "" && nval != null && !erorFlag) {
                    if (parseInt(val) > parseInt(nval)  ) {
                        alert('Fare Amount should be Smaller than or Equal to Next Stop');
                        var self = $(this);
                        erorFlag = false;
                       /*  setTimeout(function () {
                            self.focus();
                        }, 1); */
                    }
                }else{
                erorFlag = true;
                }

                //left
                /*if(lval!="" && lval!=null){
                 if(parseInt(val)<parseInt(lval)){
                 alert('Please enter proper value');
                 var self = $(this);
                 setTimeout(function(){
                 self.focus();
                 }, 1); 
                 }}
                 
                 //right
                 if(rval!="" && rval!=null){
                 if(parseInt(val)>parseInt(rval)){
                 alert('Please enter proper value');
                 var self = $(this);
                 setTimeout(function(){
                 self.focus();
                 }, 1); 
                 }}*/
                //////////////////////////01/09/14


                if (arr.indexOf($(this).attr('name')) == -1) {
                    arr.push($(this).attr('name'));

                }
                document.getElementById("updated_ids").value = arr;
                console.log(arr);
            } else {
                alert('Please Enter Proper Fare Amount');
                //$(this).val("");

                var self = $(this);
               /*  setTimeout(function () {
                    self.focus();
                }, 1); */
            }
        });
        
        $('.inputs').keyup(function () {
            if (checkInt($(this).attr('value'))) {
                var vl = $(this).parent().closest('td').attr('id');
                var arr2 = vl.split(":");
                var c1 = parseInt(arr2[0]) - 1 + ':' + arr2[1];//up
                var c2 = parseInt(arr2[0]) + 1 + ':' + arr2[1];//dwn
                var c3 = arr2[0] + ':' + (parseInt(arr2[1]) - 1);//left
                var c4 = arr2[0] + ':' + (parseInt(arr2[1]) + 1);//right
              var currentData = $(this).attr('id');
                var fOrT = currentData.substring(0,1);
                var dimention = currentData.substring(1, currentData.length).split(":");
                var endReached = true;
                for(var c=Number(dimention[0])+1,d=Number(dimention[1])+1;endReached==true;c++,d++){
               
                if(document.getElementById(fOrT+""+c+":"+d)==null){
                endReached = false;
               
                }else{
                document.getElementById(fOrT+""+c+":"+d).value=$(this).attr('value');
                var event = new Event('change');

                // Dispatch it.
                //element.dispatchEvent(event);
                document.getElementById(fOrT+""+c+":"+d).dispatchEvent(event);
                }
                }  
        }
        });
    });

    function passengerType() {

        var val = document.getElementById("passenger_type_id").value;
        document.forms[0].action = 'FarechartTriUpdate!getTriangleByPassengerType?pid=' + val;
        document.forms[0].submit();

    }

</script>
<form action="FarechartTriUpdate.action" class="form-horizontal" method="post">
    <div class="page-content-wrapper">
        <div class="page-content">
            <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
            <div class="modal fade" id="portlet-config" tabindex="-1"
                 role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"
                                    aria-hidden="true"></button>
                            <h4 class="modal-title">Modal title</h4>
                        </div>
                        <div class="modal-body">Widget settings form goes here</div>
                        <div class="modal-footer">
                            <button type="button" class="btn blue">Save changes</button>
                            WHEL	<button type="button" class="btn default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>

            <!-- END STYLE CUSTOMIZER -->
            <!-- BEGIN PAGE HEADER-->
            <div class="row">
                <div class="col-md-12">
                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                    <h3 class="page-title">
                        FARE CHART <small></small>
                    </h3>
                    <%-- <FONT color="red"> <s:actionerror/> </FONT> --%>
                    <FONT color="green"> <s:actionmessage /> </FONT>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->

            <%-- //<s:hidden name="busid" id="id" ></s:hidden> --%>
            <!-- <input type="hidden" name="busid" id="busid" value="22181"/> -->
            <div class="row">
                <div class="col-md-12">
                    <!-- BEGIN EXAMPLE TABLE PORTLET-->
                    <div id="printableArea"> 
				    <div id="printTable">
         	<div class="portlet box grey-cascade">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i>Fare Chart
					</div>
					<div class="actions">
					                         <button type="submit" id="sid" class="btn green" onclick="send()">Save</button>
                        <button type="button" id="cid" class="btn red" onclick = "javascript:location.href = 'ShowFareChartAction.action';">Cancel</button>
                       <button type="button" id="pid" class="btn blue" onclick="printDiv('data');">Print</button> 
                       <button type="button" id="eid" class="btn green" onclick="tabletoExcel('data');">Export</button> 
					</div>
				</div>
				</div>
                        <%int k=2; 
                        try{
                                  k=Integer.parseInt((String)(session.getAttribute("passengerType")));
                                  System.out.println("kkk>>>"+k);
                                  }
                                  catch(Exception e){
                                          e.printStackTrace();
                                          k=2;
                                  }
                        FareChartMasterAction fcma=new FareChartMasterAction();
                        String[] headerValue=fcma.createFareChartHeader((Integer)session.getAttribute("masterid"));

                        %>	
                        <div  id="headerinfoid">
                        <div class="portlet-body">
                            <div class="portlet box">
                                <FONT color="red"> <s:actionerror/> </FONT>
                                <table class="table table-bordered">
                                    <tr>
                                        <td>Route Number : <%=headerValue[0] %></td>
                                        <td>Fare chart Name : <%=headerValue[3] %> </td>
                                        <td>Ceiling Fare : <%=headerValue[9] %> </td>
                                    </tr> 

                                    <tr>
                                        <td>Service Type : <%=headerValue[1] %> </td>
                                        <td>Effective Start Date : <%=headerValue[4] %> </td>
                                        <td>Night Service : <%=headerValue[6] %></td>
                                    </tr>

                                    <tr>	
                                        <td> Rate Master Version : <%=headerValue[2] %></td>	 
                                        <td>Effective End Date : <%=headerValue[5] %>  </td>	
                                        <td>Peak Hours : <%=headerValue[7] %> </td>
                                    </tr>
                                    <tr>	
                                        <td> Flexi Fare : <%=headerValue[8] %></td>	 
                                        <td></td>	
                                        <td> </td>
                                    </tr>
                                </table>
                            </div>
                              </div>
                              </div>
                              <div id="ptype">
                            <div class="form-group">

                                <label class="col-md-3 control-label">Passenger Type:</label>
                                <div class="col-md-4">
                                    <s:select list="passengerTypeMap" id="passenger_type_id" name="fareChartMaster.passenger_type_id" 
                                              cssClass="select2_category form-control" value="%{#session.passengerType}" onchange="passengerType()"></s:select>
                                </div>
                                <!-- <div class="col-md-5">
                                        <label class="control-label">Auto update:</label>
                                    <INPUT type="checkbox" name="autoChild" value="1" checked/>Child
                                    <INPUT type="checkbox" name="autoSenior" value="3" checked/>Senior Citizen
                                </div> -->
                            </div>
                            </div>

                            <font color="red"><span id="integerVal"></span></font>
                            <div class="table-scrollable"  >

                                <INPUT type="hidden" name="updated_ids" id="updated_ids"/>
                                <input type="hidden" name="ceilValue" value=<%=headerValue[9] %> />
                                <div class="table-wrapper">
                                    <!-- <table class='table table-bordered'> -->
                                    <%
out.println("<input type=\"hidden\" name=\"id\" value=\""+session.getAttribute("masterid")+"\" />");%>
                                    <div class='component' id="data"><table class='overflow-y sticky-enabled;overflow-x' border="1">
                                            <!-- <div><table id='abc' class='gridview'> -->
                                            <%-- <tr>
                                                   <td>Bus Stops</td>
                                               <s:iterator value="busStops">
                                    <td><s:property value="id"/>-<s:property value="busStopName"/></td>
                                                   </s:iterator> 
                                                   
                                            </tr> --%>

                                            <%
 
                                              List<BusStops> bstops=(List<BusStops>)session.getAttribute("stop");
                                              List<FareChart> fchart=(List<FareChart>)session.getAttribute("farechart");
                                              Map<String,String> fmap=(Map)session.getAttribute("faremap");
                                              /* out.println("<tr><td>Bus Stops</td>");
                                              for(int b=0;b<bstops.size();b++){
                                                      out.println(" <td>  "+bstops.get(b).getId()+"</td>");
                                              }
                                              out.println(" </tr>  "); */
 												String tableString = "<table id='table1'  cellspacing='2px' font-size:8px; ><thead><tr><th rowspan='2'>Sr No.</th><th  rowspan='2'>Stop Name</th>";
                                               out.println("<thead><tr><th style=\"background-color:#f5f5f5;font-size:15px; \" rowspan=\"2\">Sr No.</th><th style=\"background-color:#f5f5f5;font-size:15px; \" rowspan=\"2\">Stop Name</th>");
                                              for(int b=0;b<bstops.size();b++)//for(int b=0;b<bstops.size();b++)
                                              {
                                                      if(bstops.get(b).getFareStage().equalsIgnoreCase("Yes") || bstops.get(b).getFareStage().equalsIgnoreCase("Y")){
                                                    	  tableString += "<th font-size:8px;>"+bstops.get(b).getBus_stop_code()+"</th>";
                                                    	  out.println(" <th style=\"background-color:#F4FA58;font-size:15px;\" title='"+bstops.get(b).getBusStopName()+"'>"+bstops.get(b).getBus_stop_code()+"</th>");
                                                      }else{
                                                    //   out.println(" <th hidden='true' style=\"background-color:#f5f5f5;font-size:15px;\" title='"+bstops.get(b).getBusStopName()+"'>"+bstops.get(b).getBus_stop_code()+"</th>");	   
                                                      }
   

                                              }
                                             
                                              
                                              tableString +="</tr></thead> <tr>";
                                              out.println(" </tr></thead> <tr>"); 
                                            //  out.println("<td hidden='true' rowspan=\"1\" style=\"background-color:#f5f5f5 \" ><div id=\"cell_head\"><div id=\"cell_head_text\" align=\"center\" style=\"border-right:1px solid #dddddd\"></div><div id=\"cell_head_text\"  style=\"border-left-color:#dddddd\" align=\"center\"></div></div></td>");
                                              //out.println("<td hidden='true' rowspan=\"1\" style=\"background-color:#f5f5f5 \" ><div id=\"cell_head\"><div id=\"cell_head_text\" align=\"center\" style=\"border-right:1px solid #dddddd\"></div><div id=\"cell_head_text\"  style=\"border-left-color:#dddddd\" align=\"center\"></div></div></td>");
        
                                              tableString +="<tr><td colspan='2' rowspan='1'></td>";
                                              out.println("<tr ><td colspan=\"2\" rowspan=\"1\"></td>");
                                              for(int b=0;b<bstops.size();b++)//for(int b=0;b<bstops.size();b++)
                                              {
                                                      if(bstops.get(b).getFareStage().equalsIgnoreCase("Yes") || bstops.get(b).getFareStage().equalsIgnoreCase("Y")){
                                                    	  tableString += " <td rowspan='1'><div ><div >Fare Amt</div><div >Toll Fee</div></div></td>";
                                                    	  out.println(" <td rowspan=\"1\" style=\"background-color:#F4FA58 \" ><div id=\"cell_head\"><div id=\"cell_head_text\" align=\"center\" style=\"border-right:1px solid #dddddd\">Fare Amt</div><div id=\"cell_head_text\"  style=\"border-left-color:#dddddd\" align=\"center\">Toll Fee</div></div></td>");
                                                      }else{
                                                 //   out.println(" <td hidden='true' rowspan=\"1\" style=\"background-color:#f5f5f5 \" ><div id=\"cell_head\"><div id=\"cell_head_text\" align=\"center\" style=\"border-right:1px solid #dddddd\">Fare Amt</div><div id=\"cell_head_text\"  style=\"border-left-color:#dddddd\" align=\"center\">Toll Fee</div></div></td>");
                                                    }
                                             }
                                              tableString +="</tr>";
                                              out.println(" </tr>");
 
                                              int cntStp=0,fst=0,bRev=bstops.size()-1,count=0;
                                              String textId="";
                                              tableString +="</tr>";
                                              tableString +="</tr>";
                                              for(int b=0;b<bstops.size();b++)//for(int b=bstops.size()-1;b>=0;b--)//
                                              {
                                                      count++;
                                                     if(fst==0 || cntStp==1)
                                                     {
                                                             if(bstops.get(b).getFareStage().equalsIgnoreCase("Yes") || bstops.get(b).getFareStage().equalsIgnoreCase("Y")){
                                                            	 tableString +="<tr><th >"+(b+1)+"</th><th >"+bstops.get(b).getBus_stop_code()+"</th>";
                                                            	 out.println("<tr><th style=\"background-color:#F4FA58 ;font-size:11px;\">"+(b+1)+"</th><th style=\"background-color:#F4FA58;font-size:11px;\" title='"+bstops.get(b).getBusStopName()+"'>"+bstops.get(b).getBus_stop_code()+"</th>");
                                                             }else{
                                                        //      out.println("<tr hidden='true'><th style=\"background-color:#f5f5f5 ;font-size:11px;\">"+(b+1)+"</th><th style=\"background-color:#f5f5f5;font-size:11px;\" title='"+bstops.get(b).getBusStopName()+"'>"+bstops.get(b).getBus_stop_code()+"</th>");	 
                                                             }
                                                             cntStp=2;
                                                     }
                                                     fst++;	 
                                                     for(int c=0;c<bstops.size();c++)// for(int c=bstops.size()-1;c>=0;c--)// 
                                                      {	 
                                                             if(bstops.get(b).getFareStage().equalsIgnoreCase("Yes") || bstops.get(b).getFareStage().equalsIgnoreCase("Y")){
                                                                     if(bstops.get(c).getFareStage().equalsIgnoreCase("Yes") || bstops.get(c).getFareStage().equalsIgnoreCase("Y")){
                                                                 		
                                                                    	 tableString += " <td id='"+c+':'+b+"'>  ";
                                                                    	 out.println(" <td style='background-color:#F4FA58' id='"+c+':'+b+"'>  ");
                                                                     }else{
                                                                    	 
                                                                    	     tableString += " <td  id='"+b+':'+c+"'>  ";
                                                                             out.println(" <td style='background-color:#F4FA58' id='"+b+':'+c+"'>  ");
                                                                     }
    
                                                             }else{
                                                                     if(bstops.get(c).getFareStage().equalsIgnoreCase("Yes") || bstops.get(c).getFareStage().equalsIgnoreCase("Y")){
                                                                       
                                                                    	 tableString += "<td id='"+c+':'+b+"'> ";
                                                                    	 out.println(" <td style='background-color:#F4FA58' id='"+c+':'+b+"'>  ");
                                                                             }else{
                                                                            	 tableString += "<td id='"+b+':'+c+"'>  ";
                                                                                     out.println(" <td id='"+b+':'+c+"'>  "); 
                                                                             }
                                                             }
                                                              textId=b+":"+c;
 
                                                              List tollName=new ArrayList();
                                                              //for(int k=1;k<4;k++)
                                                              //{
                                                                    String key=bstops.get(c).getId()+"-"+bstops.get(b).getId()+"-"+3;
                                                            String val=fmap.get(key);
       
                                                            key=bstops.get(c).getId()+"-"+bstops.get(b).getId()+"-"+2;
                                                            String val2=fmap.get(key);
       
                                                            key=bstops.get(c).getId()+"-"+bstops.get(b).getId()+"-"+1;
                                                            String val3=fmap.get(key);
       	       
                                                            if(val!=null)
                                                            {  // tollName.add(val.split(":")[2]);
       	
                                                            if(k==1){
                                                            	
                                                            	tableString +=val3.split(":")[0]+" &nbsp;&nbsp; ";
                                                                 out.println("<div id=\"cell_input\"><input style='width:22px' id=\"f"+textId+"\" class=\"inputs\" maxLength=\"8\" type=\"text\" name=\""+val3.split(":")[2]+":\" value=\""+val3.split(":")[0]+"\""
                                                                                            +" />");
                                                            	tableString += val3.split(":")[1];	       
                                                                    out.println("<input style='width:22px' id=\"t"+textId+"\" class=\"inputs\" maxLength=\"8\" type=\"text\" name=\"t:"+val3.split(":")[2]+"\" value=\""+val3.split(":")[1]+"\" readonly /></div>");	       
       	
                                    //out.println("child<input class=\"inputs\" type=\"text\" name=\""+val.split(":")[2]+"\" value=\""+val.split(":")[0]+"\" />");
                                                            }else{
                                                                    if(k==3){
       	                                                         
                                                                    	   tableString += val.split(":")[0]+" &nbsp;&nbsp; "; 
                                                                            out.println("<div id=\"cell_input\"><input style='width:30px' id=\"f"+textId+"\" class=\"inputs\" maxLength=\"8\" type=\"text\" name=\""+val.split(":")[2]+":\" value=\""+val.split(":")[0]+"\""
                                                                                                    +" />"); 
                                                                           tableString += val.split(":")[1];	       
                                                                            out.println("<input  style='width:30px' id=\"t"+textId+"\"  class=\"inputs\" maxLength=\"8\" type=\"text\" name=\"t:"+val.split(":")[2]+"\" value=\""+val.split(":")[1]+"\" /></div>");	       
       	
       	
                                    /* out.println("adult<div id=\"cell_input\"><input class=\"inputs\" type=\"text\" name=\""+val.split(":")[2]+"\" value=\""+val.split(":")[0]+"\""
                                                                    +" onchange=\"this.form."+tollName.get(0)+".value=this.value;this.form."+tollName.get(1)+".value=this.value; \" />");	       	
                                                                     */}
                                                            }
                                                                            if(k==2)
                                                                            {      
                                                                            	   tableString += val2.split(":")[0]+" &nbsp;&nbsp; "; 
                                                                                    out.println("<div id=\"cell_input\"><input style='width:30px' id=\"f"+textId+"\" class=\"inputs\" maxLength=\"8\" type=\"text\" name=\""+val3.split(":")[2]+":"+val2.split(":")[2]+":"+val.split(":")[2]+"\" value=\""+val2.split(":")[0]+"\""
                                                                                                    +" />");	       	
                                            /*        	
                                            out.println("sr<input class=\"inputs\" type=\"text\" name=\""+val.split(":")[2]+"\" value=\""+val.split(":")[0]+"\" />");	       	
                                            out.println("<input class=\"inputs\" type=\"text\" name=\"toll"+tollName.get(2)+"\" value=\""+val.split(":")[1]+"\" onchange=\"this.form.toll"+tollName.get(0)+".value=this.value;this.form.toll"+tollName.get(1)+".value=this.value; \"  /></div>");	       
                                            out.println("<input class=\"inputs\" type=\"hidden\" name=\"toll"+tollName.get(0)+"\" value=\""+val.split(":")[1]+"\"/>");	
                                            out.println("<input class=\"inputs\" type=\"hidden\" name=\"toll"+tollName.get(1)+"\" value=\""+val.split(":")[1]+"\"/>");
                                                     */	
                                           tableString += val2.split(":")[1];
                                            out.println("<input style='width:30px' id=\"t"+textId+"\" class=\"inputs\" maxLength=\"8\" type=\"text\" name=\"t:"+val3.split(":")[2]+":"+val2.split(":")[2]+":"+val.split(":")[2]+"\" value=\""+val2.split(":")[1]+"\" /></div>");	       
                                                                            }
                                                                            //textId++;
                                                            }
                                                              //}
                                                            tableString +=" </td>  ";
                                                              out.println(" </td>  "); 
 
                                                          if(c==bstops.size()-1){
                                                              cntStp=1;  
                                                              } 
                                                      }
    
                                                      if(cntStp==2)
                                                             {
                                                    	  tableString +="</tr>  ";
                                                              out.println(" </tr>  ");  
                                                                     cntStp=0;
                                                             }  
                                              }
                                              tableString += "<tr ><td colspan='2' rowspan='2'>Stop Name</td>";
                                              out.println("<tr  ><td colspan=\"2\" rowspan=\"2\">Stop Name</td>");
                                              for(int b=0;b<bstops.size();b++)//for(int b=0;b<bstops.size();b++)
                                              {
                                                      if(bstops.get(b).getFareStage().equalsIgnoreCase("Yes") || bstops.get(b).getFareStage().equalsIgnoreCase("Y")){
                                                    	 tableString +="<td >Fare Amount  Toll Fee </td>";	  
                                                    out.println(" <td  style=\"background-color:#F4FA58\"><div id=\"cell_head\"><div id=\"cell_head_text\" align=\"center\" style=\"border-right:1px solid #dddddd\">Fare Amount</div><div id=\"cell_head_text\"  style=\"border-left-color:#dddddd\" align=\"center\">Toll Fee</div></div></td>");
                                                      }else{
                                                         //     out.println(" <td hidden='true' style=\"background-color:#f5f5f5\"><div id=\"cell_head\"><div id=\"cell_head_text\" align=\"center\" style=\"border-right:1px solid #dddddd\">Fare Amount</div><div id=\"cell_head_text\"  style=\"border-left-color:#dddddd\" align=\"center\">Toll Fee</div></div></td>");
 
                                                      }
                                                      }
                                              tableString +="</tr>";
                                              out.println(" </tr>");
                                              for(int b=0;b<bstops.size();b++)//for(int b=0;b<bstops.size();b++)
                                              {
                                                      if(bstops.get(b).getFareStage().equalsIgnoreCase("Yes") || bstops.get(b).getFareStage().equalsIgnoreCase("Y")){
                                                    	  tableString += "<td >"+bstops.get(b).getBus_stop_code()+"</td>";
                                                          out.println(" <td  style=\"background-color:#F4FA58\" title='"+bstops.get(b).getBusStopName()+"'>"+bstops.get(b).getBus_stop_code()+"</td>");
                                                      }else{
                                                          //    out.println(" <td hidden='true' style=\"background-color:#f5f5f5\" title='"+bstops.get(b).getBusStopName()+"'>"+bstops.get(b).getBus_stop_code()+"</td>");  
                                                      }
                                              }
                                              tableString +=" </tr></table>";
                                              out.println(" </tr> <tr>"); 
 
                                              request.setAttribute("tableString", tableString);
 
                                            %>	
                                              
                                              
                                        </table>
                                        
                                        <div id='ex'></div>
                                        <input type="hidden" id="tempValue" name="tempValue" value="<%=request.getAttribute("tableString")%>">
                                        <script>$(document).ready(function () {
// $("#abc").freezeHeader({ 'height': '300px' });
                                                fixHeader();

                                                            });</script>
                                    </div>
                                </div>
                            </div>
                     
                   
                </div>
            <!--     <div class="form-actions fluid">
                    <div class="col-md-offset-3 col-md-5">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <button type="submit" id="sid" class="btn green" onclick="send()">Save</button>
                        <button type="button" id="cid" class="btn red" onclick = "javascript:location.href = 'ShowFareChartAction.action';">Cancel</button>
                       <button type="button" id="pid" class="btn blue" onclick="printDiv('data');">Print</button> 
                       <button type="button" id="eid" class="btn green" onclick="tabletoExcel('data');">Export</button> 
                    </div>
                </div> -->
                <!-- END EXAMPLE TABLE PORTLET-->
            </div>

        </div>

        <!-- END PAGE CONTENT-->
    </div>
</div>
</div>
</form>
	
