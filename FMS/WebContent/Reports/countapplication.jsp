<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
function jsFunction(){
	 $.ajax({
	        
         type: "get",
         url: '<%=request.getContextPath()%>/Ajaxcountapplication.action?line='+line,
         success: function(result) {
         	$('#pageLoader').hide();
            // document.getElementById('operatedid1').innerHTML=result;
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

				<div class="row">
					<div class="col-md-12">
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							APPLICATION DETAILS <small></small>
						</h3>

						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<div class="tab-pane active" id="tab_0">
					<div class="portlet box grey-cascade">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>APPLICATION DETAILS
							</div>
							<!-- <div class="tools">
								<a href="javascript:;" class="collapse"> </a> 
								 <a href="javascript:;" class="reload"> </a> 
							</div> -->
						</div>
						

						<div class="portlet-body form" >

							<div class="col-md-12" align="left" style="font-size: 1.1em">

								<%-- <span class="help-block" style="color: red; list-style: none"><s:actionerror /></span> --%>
								<span class="help-block" style="color: red; list-style: none"><s:actionmessage /></span>
							</div>
							
							<!-- BEGIN FORM-->
							<form action="" method="post" class="form-horizontal">
                           </div> </form>
					
					<div class="form-body">
					</div></div>
					
								<div class="form-group"> 
 									<label class="col-md-2 control-label">DATE<fontcolor="red"></font></label>  
 								<div id="cal" style="width: 200pi x" /></div>   
                                  <input type="text" id="Key" style="width: 200px" />
                                    </div>
				                 								
			                    
	
								
								<div class="form-group"> 
 									<label class="col-md-2 control-label">ID</font></label>  
 								<div id="id" style="width: 200px" /></div>   
                                  <input type="text" id="Key" style="width: 200px" />
                                   </div>
				                  
			                   			
							<div class="form-group"> 
 								<label class="col-md-2 control-label">APP-UP</font></label>  
 								    <div id="up" name=style="width: 200px" /></div>   
                                      <input type="text" id="Key" style="width: 200px" />
                                     </div>
				             
					
					
							<div class="form-group"> 
 								<label class="col-md-2 control-label">APP-DOWN</font></label>  
 								    <div id="down" style="width: 200px" /></div>   
                                      <input type="text" id="Key" style="width: 200px" />
                                     </div>
				             </div>
				          </div>
				      </div>
				             
                     
					<div id="earlyarrivalsummary"></div>

</body>
</html>
 