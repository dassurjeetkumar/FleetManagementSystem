<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
<script type="text/javascript" src="//www.google.com/jsapi"></script>
<script src="https://code.jquery.com/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/responsive/1.0.7/js/dataTables.responsive.min.js" type="text/javascript"></script>
<script src="https://cdn.datatables.net/fixedheader/3.0.0/js/dataTables.fixedHeader.min.js" type="text/javascript"></script>
<script>
    function callEdit() {
       // alert("in  call edit");
        //var val;
        $(function() {
            var val = [];
            $(':checkbox:checked').each(function(i) {
                val = $(this).val();
            });
            if (check(0)) {
//                 alert("in if.....");
//                 alert("id is"+val);
                if(isEligibleForOpertion(val)){
                document.getElementById("editScheduleMapId").value=val;
                //alert(val);
                document.getElementById("editAction").submit();
                }
                else{
                    bootbox.alert("Please Select Valid Record");
                }
            }
        });

    }
   
    function callSave() {
        $(function() {
           
                document.getElementById("createAction").submit();
        });

    }
       
    function callDelete() {
//         var val;
//alert("in delete");
        $(function() {
            var val = [];
            $(':checkbox:checked').each(function(i) {
                val = $(this).val();
            });
            if (check(0)) {
                if(isEligibleForOpertion(val)){
                 //   alert("id to del is"+val);
                bootbox.confirm("Are you sure you want to delete this record?",function(result) {
                    if (result == true) {
                        document.getElementById("scheduleMapId").value=val;
                        document.getElementById("deleteAction").submit();
                    }
                });
                }
            else{
                bootbox.alert("Please Select Valid Record");
            }
            }
        });
    }
    function check() {
// alert("in check");
        var chckbxCount = $("input:checked").length;
        if (chckbxCount > 0 && chckbxCount > 1) {
            bootbox.alert("Select only one Schedule");
            return false;
        } else if (chckbxCount > 0 && chckbxCount == 1) {
            return true;
        } else {
            bootbox.alert("Please Select Schedule ");
            return false;
        }

    }
</script>
<%@page import="com.trimax.its.utility.model.AccessRights" %>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao" %>
    <%
        AccessRightsDao accessrightdao = new AccessRightsDao();
        AccessRights accessrights = new AccessRights();
        int usrsessionid = (Integer) request.getSession().getAttribute(
                "userid");
        accessrights = accessrightdao.accessRightsdetails(usrsessionid,
                "ScheduleMappingList.action");
        String access = accessrights.getACCESS();
        String create = accessrights.getCREATE();
        String edit = accessrights.getEDIT();
        String delete = accessrights.getDELETE();
        String read = accessrights.getREAD();
        String error = accessrights.getERROR();
        String viewdelete = accessrights.getVIEWDELETE();
        String status = accessrightdao.UserStatus(usrsessionid);

        String viewdeletedrecord = (String) request.getSession()
                .getAttribute("viewdeletedrecord");
    %>

<style>
.url {
    max-width: 150px;
    word-wrap: break-word;
}

</style>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/global/plugins/data-tables/DT_bootstrap.css" />
<form name="createAction" id='createAction' action="CreateScheduleMapping.action" method="post">
   
</form>
<form name="editAction" id='editAction' action="EditScheduleMapping.action" method="post">
    <input type="hidden" id='editScheduleMapId' name="editScheduleMapId"/>
</form>

<form name="deleteAction" id="deleteAction" action="DeleteScheduleMapping.action" method="post">
    <input type="hidden" id='scheduleMapId' name="scheduleMapId"/>
</form>



<div class="page-content-wrapper">
    <div class="page-content">
        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                <h3 class="page-title">
                    SCHEDULE MAPPING
                </h3>
            </div>
        </div>
       
<%-- <%if (access.equalsIgnoreCase("Y")){%> --%>
        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN EXAMPLE TABLE PORTLET-->
                <div class="portlet box grey-cascade">
                    <div class="portlet-title">
                        <div class="caption">

                            <i class="fa fa-globe"></i>View Schedule Mapping
                        </div>
                        <div class="actions">

                            <%--  <%if(edit.equalsIgnoreCase("Y")){ %>--%>
                                <a href="#" class="btn blue" onclick="callSave()"> <i
                                class="fa fa-pencil"></i> Create
                            </a> &nbsp;&nbsp;
<!--                             <a href="#" class="btn blue" onclick="callEdit()"> <i -->
<!--                                 class="fa fa-pencil"></i> Edit -->
<!--                             </a> &nbsp;&nbsp; -->
                            <%
                                /* } */
                            %>
<%-- <%if(delete.equalsIgnoreCase("Y")){ %> --%>
                            <a href="#" class="btn red" onclick="callDelete()"> <i
                                class="fa fa-times"></i> Delete
                            </a>&nbsp;&nbsp;
<%--                             <%}%> --%>

                        </div>
                    </div>
                   
                    <div class="portlet-body">
                        <form action="" method="post">
                        <B><font color="green"><s:actionmessage /></font></B>
                            <s:if test="hasActionErrors()">
                                <div class="alert alert-danger">
                               
                                    <button class="close" data-close="alert"></button>
                                <span id="errorMsg" style="color:red;word-wrap: break-word;"><s:actionerror/></span>
                                </div>
                                </s:if>
                                <b>
<%--                                 <span id="errorMsg" style="color:red;word-wrap: break-word;" ><s:actionerror/></span> --%>
                            </b>
                            <input type="hidden" id="" value="" name="vehicleTypeId">
                            <table class="table table-striped table-bordered table-hover"  id="scheduleMappingTableList">
<!--                                 id="scheduleMappingTableListservicecall"> -->
                                     
                                <thead>
                                    <tr>
                                        <th></th>
<!--                                         <th>Schedule Id</th> -->
<!--                                         <th>Schedule No.</th> -->
                                        <th>Schedule Name </th>
<!--                                         <th>Duty Type</th> -->
<!--                                         <th>Shift Type</th> -->
                                        <th>Vehicle No.</th>
                                        <th>Depot</th>
                                        <th>Division</th>
                                    <%--     <th>Status</th>
                                        <%if(viewdeletedrecord.equalsIgnoreCase("Y")) {%>   
<th>Record Status</th>
<%}%>
                                         --%>
                                    </tr>
                                </thead>
                           
                            </table>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<script>
$(document).ready(function() {
    window.history.pushState("","", "ScheduleMappingList.action");
   // var w=$('#errorMsg span').html();
       //alert(w);
      //  w=w.replace(/@/g,"<br>");

      // $('#errorMsg').html(''+w+'');
});
function isEligibleForOpertion(id){
     var isEligible = $('#isRocordEligible'+id).val();
     if(isEligible == undefined || isEligible=='Y'){
         return true;
     }else{
         return false;
     }
}



// $('#scheduleMappingTableListservicecall').dataTable({
//     "aaSorting": [
//                     [0, 'asc'],[1, 'asc'],[2, 'asc'],[3, 'asc']
//                 ],
//     "aLengthMenu": [
//         [10,25,50,100],
//         [10,25,50,100], // change per page values here
//     ],
//     // set the initial value
//     "iDisplayLength": 10,
//     "bProcessing" : true,
//     "bServerSide" : true,
//     "sAjaxSource" : "ScheduleMappingListAjaxServiceCall.action",
//     "sPaginationType": "bootstrap",
//     "oLanguage": {
//         "sLengthMenu": "_MENU_ records",
//         "oPaginate": {
//             "sPrevious": "Prev",
//             "sNext": "Next"
//         }
//     },
//    "aoColumnDefs": [
//         { 'bSortable': false, 'aTargets': [0] },
//         { "bSearchable": false, "aTargets": [ 0 ]} ,
//         { "sClass": "url", "aTargets": [ 3 ] },
//     ],
   
// });
//     jQuery('#scheduleMappingTableList_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
// jQuery('#scheduleMappingTableList_wrapper .dataTables_length select').addClass("form-control input-xsmall input-inline"); // modify table per page dropdown
// jQuery('#modelTypeTable_wrapper .dataTables_length select').select2(); // initialize select2 dropdown



</script>
<%-- <%}else{%> --%>
 


<%-- <%@ include file="/pages/admin/error.jsp" %> --%>


<%-- <%}%> --%>