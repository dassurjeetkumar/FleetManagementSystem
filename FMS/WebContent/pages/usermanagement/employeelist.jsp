<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE body PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<script>

function getDisplayEmployeeForm(){
	document.form.action="ShowEmployee.action";
	document.form.submit();
	
}


function getDeleteRecord(){
	var chks = document.getElementsByName('userid');
	var checkCount = 0;
	for (var i = 0; i < chks.length; i++)
	{
	if (chks[i].checked)
	{
	checkCount++;
	}
	}
	if (checkCount == 0)
	{
	alert("Please select at least one Employee .");
	return false;
	}
	if (checkCount >1)
	{
		alert("Please select  one Employee .");
		return false;
	}
	
	if (checkCount ==1)
	{
		document.form.action="DeleteEmployeeRecord.action";
		document.form.submit();
	}

	
}

function getmodifyrecord(){
	
	var chks = document.getElementsByName('userid');
	
	var checkCount = 0;
	for (var i = 0; i < chks.length; i++)
	{
	if (chks[i].checked)
	{
	checkCount++;
	}
	}
	if (checkCount == 0)
	{
	alert("Please select at least one Employee .");
	return false;
	}
	if (checkCount >1)
	{
		alert("Please select one Employee .");
		return false;
	}
	
	if (checkCount ==1)
	{
		document.form.action="ModifyEmployeeDetails.action";
		document.form.submit();
	}

}

</script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/assets/global/plugins/select2/select2.css"/>
<link rel="stylesheet" href="<%=request.getContextPath() %>/assets/global/plugins/data-tables/DT_bootstrap.css"/>

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
			<!-- /.modal -->
			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->
			<!-- BEGIN STYLE CUSTOMIZER -->
			<div class="theme-panel hidden-xs hidden-sm">
				<div class="toggler">
				</div>
				<div class="toggler-close">
				</div>
				<div class="theme-options">
					<div class="theme-option theme-colors clearfix">
						<span>
						THEME COLOR </span>
						<ul>
							<li class="color-default current tooltips" data-style="default" data-original-title="Default">
							</li>
							<li class="color-darkblue tooltips" data-style="darkblue" data-original-title="Dark Blue">
							</li>
							<li class="color-blue tooltips" data-style="blue" data-original-title="Blue">
							</li>
							<li class="color-grey tooltips" data-style="grey" data-original-title="Grey">
							</li>
							<li class="color-light tooltips" data-style="light" data-original-title="Light">
							</li>
							<li class="color-light2 tooltips" data-style="light2" data-html="true" data-original-title="Light 2">
							</li>
						</ul>
					</div>
					<div class="theme-option">
						<span>
						Layout </span>
						<select class="layout-option form-control input-small">
							<option value="fluid" selected="selected">Fluid</option>
							<option value="boxed">Boxed</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Header </span>
						<select class="page-header-option form-control input-small">
							<option value="fixed" selected="selected">Fixed</option>
							<option value="default">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Sidebar </span>
						<select class="sidebar-option form-control input-small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Sidebar Position </span>
						<select class="sidebar-pos-option form-control input-small">
							<option value="left" selected="selected">Left</option>
							<option value="right">Right</option>
						</select>
					</div>
					<div class="theme-option">
						<span>
						Footer </span>
						<select class="page-footer-option form-control input-small">
							<option value="fixed">Fixed</option>
							<option value="default" selected="selected">Default</option>
						</select>
					</div>
				</div>
			</div>
			<!-- END STYLE CUSTOMIZER -->
			<!-- BEGIN PAGE HEADER-->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN PAGE TITLE & BREADCRUMB-->
					<h3 class="page-title">
					Employee  Details <small></small>
					</h3>
					<ul class="page-breadcrumb breadcrumb">
						<li class="btn-group">
							<button type="button" class="btn blue dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="1000" data-close-others="true">
							<span>Actions</span><i class="fa fa-angle-down"></i>
							</button>
							<ul class="dropdown-menu pull-right" role="menu">
								<li>
									<a href="#">Action</a>
								</li>
								<li>
									<a href="#">Another action</a>
								</li>
								<li>
									<a href="#">Something else here</a>
								</li>
								<li class="divider">
								</li>
								<li>
									<a href="#">Separated link</a>
								</li>
							</ul>
						</li>
						<li>
							<i class="fa fa-home"></i>
							<a href="index.html">Home</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<a href="#">Data Tables</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<a href="#">Advanced Datatables</a>
						</li>
					</ul>
					<!-- END PAGE TITLE & BREADCRUMB-->
				</div>
			</div>
			<!-- END PAGE HEADER-->
			
			
			
			<!-- BEGIN PAGE CONTENT-->
			<form name="form" action="">
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet box blue-hoki">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-globe"></i>Employee List
							</div>
							
							
						    	<div class="tools">
							  <button id="sample_editable_1_new" class="btn blue"  onclick="getDisplayEmployeeForm()"> <i class="fa fa-plus"></i>Create </button>
							   <button id="sample_editable_1_new" class="btn blue"  onclick="return getmodifyrecord()"> <i class="fa fa-edit"></i>Edit </button>	
							   <button id="sample_editable_1_new" class="btn red"  onclick="return getDeleteRecord()"> <i class="fa fa-plus"></i>Delete </button>
								</div>
									
								
								<a href="javascript:;" class="reload">
								</a>
								<a href="javascript:;" class="remove">
								</a>
							</div>
						</div>
						<div class="portlet-body">
						<table class="table table-striped table-bordered table-hover" id="sample_2">

							<thead>
							<tr>
								<s:if test="hasActionErrors()">
					<font color="Red"> <s:actionerror />
					</font>
					</s:if>
					
					<s:if test="hasActionMessages()">
					<font color="green"> <s:actionmessage />
					
				</font>
					</s:if>
							</tr>
							<tr>
								<th>
									EmployeeType Id
								</th>
								
								<th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="sample_1" rowspan="1" colspan="1" style="width: 178px;" aria-sort="ascending" aria-label=" username : activate to sort column descending"> username </th>
								<th>
									Name in English
								</th>
								<th>
									Name in Kannada
								</th>
								<th>
									Section Type
									
								</th>
								<th>
									Employee Type
									
								</th>
								<th>
									Designation 
									
								</th>
								<th>
									 Parent org Name
								</th>
								
							</tr>
							</thead>
							<s:if test="%{employeelist.isEmpty()}">
						
							</s:if>
							<s:else>
							<tbody>
							<s:iterator value="employeelist" var="a" status="employeelistdetails">
							<tr>
						
							<td>
						
							<input type="checkbox" name="userid" value='<s:property value="user_id" />' id="userid" />
							<s:property value="user_id" /></td> 
							<td><s:property value="user_name" /></td> 
							<td><s:property value="name_in_english"/>
							<td><s:property value="name_in_kannada"/>
							<td><s:property value="section_type" /></td>
							<td><s:property value="employee_type" /></td>
							<td><s:property value="employee_designation" /></td> 
							<td><s:property value="parent_org_name" /></td> 
							</tr>
							</s:iterator>
							</s:else>
							
						
							</tbody>
							</table>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
					
					
					
				</div>
				</form>
			</div>
			<!-- END PAGE CONTENT-->
			
		</div>
	
	</body>
	</html>