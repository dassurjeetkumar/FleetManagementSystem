<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

 <script src="assets/global/plugins/jquery-1.11.0.min.js"
    type="text/javascript"></script>
<script>     


</script>

<script>
       
function callFeedBack(){
    document.forms[0].action = 'getCustomerFeedBack.action';
    document.forms[0].submit();
}
function callComplaint(){
document.forms[0].action = 'getCustomerComplaint.action';
document.forms[0].submit();
}


    </script>
   


</head>
<body>

 <form>
 
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
       
            <div class="row">
                <div class="col-md-12">
                    <!-- BEGIN EXAMPLE TABLE PORTLET-->
                    <div class="portlet box grey-cascade">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-globe"></i>Customer FeedBack
                            </div>
                            <div class="actions">
                            <div class="btn-group">
                                <button type="button" class="btn blue" onclick="callFeedBack();"><i class="fa fa-eye"></i>&nbsp;FeedBack</button>
                                <button type="button" class="btn red" onclick="callComplaint();"><i class="fa fa-eye"></i>&nbsp;Complaint</button>
<!--                                 <button type="button" class="btn green" onclick="callSummary();"><i class="fa fa-eye"></i>&nbsp;Summary</button>
 -->                                   
                                        
                                        
                                        
                                        					<div class="btn-group">
							

								<a class="btn blue" href="#" data-toggle="dropdown">
									Tools <i class="fa fa-angle-down"></i>
								</a>
					
								
								
								<div id="sample_4_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">
								
								 
                                 <!--   <label>
										<button type="button" class="btn blue" onclick="window.open('','_new').location.href='/its/generateReport?id=VEHICLERPT'">
											<i class="fa fa-pencil"></i>
											&nbsp;Report
										</button>
									</label> -->
									<label>
										<button type="button" class="btn blue" onclick="callSummary()">
											<i class="fa fa-pencil"></i>
											&nbsp;Summary
										</button>
									</label>
									<label>
										<button type="button" class="btn blue" onclick="window.location.href='birtcustfeedback'">
											<i class="fa fa-pencil"></i>
											&nbsp;Customer Feed Back
										</button>
									</label>
										<label>
										<button type="button" class="btn blue" onclick="window.location.href='birtcustresponse'">
											<i class="fa fa-pencil"></i>
											&nbsp;Customer Response
										</button>
									</label>
										<label>
										<button type="button" class="btn blue" onclick="window.location.href='birtcustrating'">
											<i class="fa fa-pencil"></i>
											&nbsp;Crew Ratings
										</button>
									</label>	
								<!-- 	<label>
										<button type="button" class="btn blue" onclick="callDocking();">
											<i class="fa fa-pencil"></i>
											&nbsp;Docking
									</button>
									</label> -->	
						
						
								
							</div>
						
						</div>
						
						
						                   
                               
                                    </div>
                            </div>
                           
                        </div>
                       
                        <div class="portlet-body">
                       
                        <b>
                            <font color="green"> <s:actionmessage/></font></b>
                            <b><font color="red"> <s:actionerror/></font></b>
<!--                            <div id="vechicledefectsid"></div> -->
                          
                           <table class="table table-striped table-bordered table-hover"
                                id="customerFeedBackTable">
                               
                                <thead>
                                    <tr>
                                        <th style="width1: 8px;"></th>
                                        <th>Id</th>
                                        <th>Route Number </th>
                                        <th>Vehicle Number </th>
                                        <th>Depot Name</th>
                                        <th>Travel Time</th>
                                        <th>Mobile No</th>
                                        <th>Email id</th>
                                        <th>Floor Clean</th>
                                        <th>Seats Clean</th>
                                        <th>Windows Clean</th>
                                        <th>Doors clean</th>
                                        <th>Lighting Inside</th>
                                        <th>Seats Damage</th>
                                       
                                        <th>AC Working</th>
                                        <th>Crew Behavior</th>
                                        <th>Crew Helpful</th>
                                        <th>Crew Response</th>
                                        <th>Conductor Rating</th>
                                        <th>Driver Rating</th>
                                        <th>Trans_date</th>
                                        <th>Complaint_Ref_no</th>
                                        <th>BMTC_rating</th>
                                        <th>Complaint_Raised</th>
                                        <th>subcategory_id</th>
                                        <th>Caller Name</th>
                                        <th>Caller Remarks</th>
                                       
                                    </tr>
                                </thead>

                            </table>
                        
                           
                            </div>
                        </div>
                    </div>
                    <!-- END EXAMPLE TABLE PORTLET-->
                </div>
               
        </div>
    </div>
   
    </form>
   
   
                               
    </body>
    </html>