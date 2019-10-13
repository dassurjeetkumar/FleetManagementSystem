
<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.trimax.its.fare.model.FareChart,com.trimax.its.transport.model.BusStops,com.trimax.its.fare.action.FareChartMasterAction"%>
<style> 
 .inputs  { 

background: #AE1E1 ; 
border: 1px solid #C8C8C8; 
color: #777; 
font: 13px Helvetica, Arial, sans-serif;
margin: 0 0 10px; 
width:60px; 
height:26px;
} 
 
.inputs:focus { 

background-color: #FFF; 
border: 1px solid #6ca9d2;/* #ED1C24; */ 
outline: none; 
} 

#cell_input
{
    position: relative;
    width: 65px;
    height: 24px;
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
    width: 70px;
    height: 24px;
    margin:-7px 0px -6px 0px;
}

#cell_head_text
{
    display:inline-block;
	width: 35px;
    height: 24px;
    font-size:11px;
}
</style> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>
$(document).ready(function(){
  	$("input").prop('disabled', true);
    
}); 

function isInteger(n) {
	if(parseFloat(n) != parseInt(n, 10)){
	   return true;
	}else{
		return false;
	}
	} 
	
function isInt(n) {
	   return typeof n === 'number' && n % 1 == 0;
	}

function checkInt(val){
	
	//var val=document.getElementById('version').value;
	
	if(isNaN(val) || isInt(val) || isInteger(val) || val<0){
		
	
	   //document.getElementById('integerVal').innerHTML='Invalid value';
	   return false;
	}else{
		return true;
	}
}

var arr=[];
$(document).ready(function () {
	$('.inputs').change(function(){
		
		
		//validate input value
		if(checkInt($(this).attr('value'))){
			
			//validation of fare amount in triangle
			//01/09/14

			var vl=$(this).parent().closest('td').attr('id');

			var arr2=vl.split(":");
			var c1=parseInt(arr2[0])-1+':'+arr2[1];//up
			var c2=parseInt(arr2[0])+1+':'+arr2[1];//dwn
			var c3=arr2[0]+':'+(parseInt(arr2[1])-1);//left
			var c4=arr2[0]+':'+(parseInt(arr2[1])+1);//right
			
			//alert('prev='+c1+' curr='+vl+' next='+c2+' lt='+c3+' rt='+c4);
			
			var pval='',val='',nval='',lval='',rval='';
			
			
			var elem=document.getElementById('f'+c1);
			if( elem != null){
			 pval=document.getElementById('f'+c1).value;
			}
			
			var elem=document.getElementById('f'+vl);
			if(elem != null){
			val=document.getElementById('f'+vl).value;
			}
			
			var elem=document.getElementById('f'+c2);
			if(elem != null){
			nval=document.getElementById('f'+c2).value;
			}
			
			var elem=document.getElementById('f'+c3);
			if(elem != null){
				lval=document.getElementById('f'+c3).value;
			}
			
			var elem=document.getElementById('f'+c4);
			if(elem != null){
				rval=document.getElementById('f'+c4).value;
			}
			
			//alert('up='+pval+' curr='+val+' down='+nval+' left='+lval+' right='+rval);
			
			//check for valid data
			//up
			if(pval!="" && pval!=null){
			if(parseInt(val)<parseInt(pval)){
				alert('Fare Amount should be Greater than or Equal to Previous Stop');
				var self = $(this);
		        setTimeout(function(){
		          self.focus();
		        }, 1); 
			}}
			
			//down
			if(nval!="" && nval!=null){
			if(parseInt(val)>parseInt(nval)){
				alert('Fare Amount should be Smaller than or Equal to Next Stop');
				var self = $(this);
		        setTimeout(function(){
		          self.focus();
		        }, 1); 
			}}
			
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
			
		
		if(arr.indexOf($(this).attr('name'))==-1){
		arr.push($(this).attr('name'));
	
		}
		document.getElementById("updated_ids").value=arr;
		}else{
			alert('Please Enter Proper Fare Amount');
			//$(this).val("");
			
			var self = $(this);
	        setTimeout(function(){
	          self.focus();
	        }, 1);     
		}
	});
 });

function passengerType(){
	     $("input").prop('disabled', true);
		var val=document.getElementById("passenger_type_id").value;
				document.forms[0].action = 'FarechartTriUpdate!getNonFlexiTriangleByPassengerType?pid='+ val;
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
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Fare Chart
								
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
					    
						<font color="red"><span id="integerVal"></span></font>
						 <div class="table-scrollable">
						 
						 <INPUT type="hidden" name="updated_ids" id="updated_ids"/>
						 <input type="hidden" name="ceilValue" value=<%=headerValue[9] %> />
						 <div class="table-wrapper">
							<table class="table table-bordered">
							 <%-- <tr>
							 	<td>Bus Stops</td>
							    <s:iterator value="busStops">
				                 <td><s:property value="id"/>-<s:property value="busStopName"/></td>
								</s:iterator> 
								
							 </tr> --%>
							 
	<%
	  out.println("<input type=\"hidden\" name=\"id\" value=\""+session.getAttribute("masterid")+"\" />");
	  List<BusStops> bstops=(List<BusStops>)session.getAttribute("stop");
	  List<FareChart> fchart=(List<FareChart>)session.getAttribute("farechart");
	  Map<String,String> fmap=(Map)session.getAttribute("faremap");
	  /* out.println("<tr><td>Bus Stops</td>");
	  for(int b=0;b<bstops.size();b++){
		  out.println(" <td>  "+bstops.get(b).getId()+"</td>");
	  }
	  out.println(" </tr>  "); */
	  
	   out.println("<tr><td rowspan=\"2\">Sr.No.</td><td rowspan=\"2\">Stop Name</td>");
	  for(int b=0;b<bstops.size();b++)//for(int b=0;b<bstops.size();b++)
	  {
		  if(bstops.get(b).getFareStage().equalsIgnoreCase("Yes") || bstops.get(b).getFareStage().equalsIgnoreCase("Y")){
		   out.println(" <td style=\"background-color:#BDBDBD;font-size:11px;\" title='"+bstops.get(b).getBusStopName()+"'>"+bstops.get(b).getBus_stop_code()+"</td>");
		  }else{
		   out.println(" <td style=\"background-color:#f5f5f5;font-size:11px;\" title='"+bstops.get(b).getBusStopName()+"'>"+bstops.get(b).getBus_stop_code()+"</td>");			    
		  }
	  }
	  out.println(" </tr> <tr>"); 
	  for(int b=0;b<bstops.size();b++)//for(int b=0;b<bstops.size();b++)
	  {
		  out.println(" <td style=\"background-color:#f5f5f5 \" ><div id=\"cell_head\"><div id=\"cell_head_text\" align=\"center\" style=\"border-right:1px solid #dddddd\">Fare Amt</div><div id=\"cell_head_text\"  style=\"border-left-color:#dddddd\" align=\"center\">Toll Fee</div></div></td>");
	  }
	  out.println(" </tr>");
	  
	  int cntStp=0,fst=0,bRev=bstops.size()-1,count=0;
	  String textId="";
	  
	  for(int b=0;b<bstops.size();b++)//for(int b=bstops.size()-1;b>=0;b--)//
	  {
		  count++;
		 if(fst==0 || cntStp==1)
		 {
			 if(bstops.get(b).getFareStage().equalsIgnoreCase("Yes") || bstops.get(b).getFareStage().equalsIgnoreCase("Y")){
			  out.println("<tr><td style=\"background-color:#BDBDBD ;font-size:11px;\">"+(b+1)+"</td><td style=\"background-color:#BDBDBD;font-size:11px;\" title='"+bstops.get(b).getBusStopName()+"'>"+bstops.get(b).getBus_stop_code()+"</td>");
			 }else{
			  out.println("<tr><td style=\"background-color:#f5f5f5 ;font-size:11px;\">"+(b+1)+"</td><td style=\"background-color:#f5f5f5;font-size:11px;\" title='"+bstops.get(b).getBusStopName()+"'>"+bstops.get(b).getBus_stop_code()+"</td>");				  
			 }
			 cntStp=2;
	  	 }
		 fst++;	 
		
		 for(int c=0;c<bstops.size();c++)// for(int c=bstops.size()-1;c>=0;c--)// 
		  {	 
			 if(bstops.get(b).getFareStage().equalsIgnoreCase("Yes") || bstops.get(b).getFareStage().equalsIgnoreCase("Y")){
			     out.println(" <td style='background-color:#BDBDBD' id='"+b+':'+c+"'>  ");
			 }else{
				 out.println(" <td id='"+b+':'+c+"'>  "); 
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
		        	out.println("<div id=\"cell_input\"><input style='width:30px' id=\"f"+textId+"\" class=\"inputs\" maxLength=\"8\" type=\"text\" name=\""+val3.split(":")[2]+":\" value=\""+val3.split(":")[0]+"\""
							+" />");	
		        	out.println("<input style='width:30px' id=\"t"+textId+"\" class=\"inputs\" maxLength=\"8\" type=\"text\" name=\"t:"+val3.split(":")[2]+"\" value=\""+val3.split(":")[1]+"\" /></div>");		        
		        	
//out.println("child<input class=\"inputs\" type=\"text\" name=\""+val.split(":")[2]+"\" value=\""+val.split(":")[0]+"\" />");
		        }else{
		        	if(k==3){
		        		
		        		out.println("<div id=\"cell_input\"><input style='width:30px' id=\"f"+textId+"\" class=\"inputs\" maxLength=\"8\" type=\"text\" name=\""+val.split(":")[2]+":\" value=\""+val.split(":")[0]+"\""
								+" />"); 
		        		out.println("<input style='width:30px' id=\"t"+textId+"\"  class=\"inputs\" maxLength=\"8\" type=\"text\" name=\"t:"+val.split(":")[2]+"\" value=\""+val.split(":")[1]+"\" /></div>");		        
		        		
		        		
/* out.println("adult<div id=\"cell_input\"><input class=\"inputs\" type=\"text\" name=\""+val.split(":")[2]+"\" value=\""+val.split(":")[0]+"\""
				+" onchange=\"this.form."+tollName.get(0)+".value=this.value;this.form."+tollName.get(1)+".value=this.value; \" />");	        		
		        	 */}
		        }
					if(k==2)
					{
						out.println("<div id=\"cell_input\"><input style='width:30px' id=\"f"+textId+"\" class=\"inputs\" maxLength=\"8\" type=\"text\" name=\""+val3.split(":")[2]+":"+val2.split(":")[2]+":"+val.split(":")[2]+"\" value=\""+val2.split(":")[0]+"\""
								+" />");	        		
	/* 					        						
	out.println("sr<input class=\"inputs\" type=\"text\" name=\""+val.split(":")[2]+"\" value=\""+val.split(":")[0]+"\" />");	        							
	out.println("<input class=\"inputs\" type=\"text\" name=\"toll"+tollName.get(2)+"\" value=\""+val.split(":")[1]+"\" onchange=\"this.form.toll"+tollName.get(0)+".value=this.value;this.form.toll"+tollName.get(1)+".value=this.value; \"  /></div>");		        
	out.println("<input class=\"inputs\" type=\"hidden\" name=\"toll"+tollName.get(0)+"\" value=\""+val.split(":")[1]+"\"/>");	
	out.println("<input class=\"inputs\" type=\"hidden\" name=\"toll"+tollName.get(1)+"\" value=\""+val.split(":")[1]+"\"/>");
		 */			
		 
	out.println("<input style='width:30px' id=\"t"+textId+"\" class=\"inputs\" maxLength=\"8\" type=\"text\" name=\"t:"+val3.split(":")[2]+":"+val2.split(":")[2]+":"+val.split(":")[2]+"\" value=\""+val2.split(":")[1]+"\" /></div>");		        
								
					
					}
					//textId++;
		        }
			  //}
			  out.println(" </td>  "); 
			  
		      if(c==bstops.size()-1){
		    	  cntStp=1;  
	  		  } 
		  }
		     
		  if(cntStp==2)
			 {
			  out.println(" </tr>  ");  
				 cntStp=0;
		  	 }  
	  }
	  out.println("<tr ><td colspan=\"2\" rowspan=\"2\">Stop Name</td>");
	  for(int b=0;b<bstops.size();b++)//for(int b=0;b<bstops.size();b++)
	  {
		  out.println(" <td style=\"background-color:#f5f5f5\"><div id=\"cell_head\"><div id=\"cell_head_text\" align=\"center\" style=\"border-right:1px solid #dddddd\">Fare Amount</div><div id=\"cell_head_text\"  style=\"border-left-color:#dddddd\" align=\"center\">Toll Fee</div></div></td>");
	  }
	  out.println(" </tr>");
	  for(int b=0;b<bstops.size();b++)//for(int b=0;b<bstops.size();b++)
	  {
		  if(bstops.get(b).getFareStage().equalsIgnoreCase("Yes") || bstops.get(b).getFareStage().equalsIgnoreCase("Y")){
		      out.println(" <td style=\"background-color:#BDBDBD\" title='"+bstops.get(b).getBusStopName()+"'>"+bstops.get(b).getBus_stop_code()+"</td>");
		  }else{
			  out.println(" <td style=\"background-color:#f5f5f5\" title='"+bstops.get(b).getBusStopName()+"'>"+bstops.get(b).getBus_stop_code()+"</td>");  
		  }
	  }
	  out.println(" </tr> <tr>"); 
	  
	 
	  
		%>		
							</table>
							</div>
						  </div>
						</div>
					</div>
				</div>
				<div class="form-actions fluid">
					<div class="col-md-offset-3 col-md-5">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<!-- 		<button type="submit" class="btn green" onclick="send()">Save</button> -->
						<button type="button" class="btn red" onclick = "javascript:location.href='ShowFareChartAction.action';">Cancel</button>
					</div>
				</div>
				<!-- END EXAMPLE TABLE PORTLET-->
			</div>
			
		</div>

		<!-- END PAGE CONTENT-->
	</div>
	
</form>