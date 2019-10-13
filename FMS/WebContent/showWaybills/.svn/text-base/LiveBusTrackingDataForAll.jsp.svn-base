<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<%-- <script src="assets/vts/js/RouteBoxer.js"></script> --%>
<%-- <script src="assets/vts/js/vtsAll.js"></script> --%>



<script src="https://maps.googleapis.com/maps/api/js?client=gme-trimaxitinfrastructure&sensor=false&libraries=places,drawing&signature=CpSOeA5B6SzC2rxqlzd2JwenGX0="></script>


</head>
<script>

function getDepot(orgId){
     $('#select2-chosen-2').html("Select");
     $('#select2-chosen-3').html("Select");
    //alert('Here');
     /* var selectedValue = $('#form-control').val(); */
     var val=document.getElementById('divisionlist1').value;
    // alert(val);
         if(val!=0) {
             $("#msg").html("Please wait...");
        $.ajax({
            type: "post",
<%--             url: '<%=request.getContextPath()%>/getDepot?val='+val, --%>
//             success: function(result) {
            //    alert(result);
                $("#msg").html("");
                document.getElementById('depotlist1').innerHTML=0;
                getVehicle("");
//             }
        });
     }
   
}
function getRefresh() {
    if ($("#depotlist").is(':checked')) {
        // plotVehicle();
    } else {
        clearInterval(intervalID);
    }
}
function getVehicle(depotID)
{
    $('#select2-chosen-3').html("Select");
    var val=document.getElementById('depotlist1').value;
     if(val!=0) {
         $("#msg").html("Please wait...");
       $.ajax({
       type: "POST",
       url: '<%=request.getContextPath()%>/getLiveVehicles?val='+val,
       success: function(result) {
           $("#msg").html("");
           document.getElementById('vehiclelist').innerHTML=result;
       }
   });
}
}
function getDeviceId()
{
    var val=document.getElementById('vehiclelist').value;
     if(val!=0) {
  $.ajax({
      type: "post",
      url: '<%=request.getContextPath()%>/getDevice?val=' + val,
                success : function(result) {
                    document.getElementById('devicelist').innerHTML = result;
                }
            });
        }
    }
   
   
</script>
<script>
 
    var geocoder;
    var map;
    var places;
    function initializeMap() {
        // create the geocoder
        geocoder = new google.maps.Geocoder();
        // set some default map details, initial center point, zoom and style
       
        var tmpLatLng = new google.maps.LatLng(12.9746, 77.5946);
        // create the map and reference the div#map-canvas container       
        <s:iterator value="list" id="list">
        //alert(<s:property value="LAT" />);       
        tmpLatLng = new google.maps.LatLng(<s:property value="LAT" />,
                <s:property value="LONGITUDE" />);
        // make and place map maker.
        var indMarker = new google.maps.Marker({
            map : map,
            position : tmpLatLng,
            icon : 'assets/images/Bus-Icon.png',
            title : '#' + "<s:property value="DEVICE_ID" />",
        });
//         var trafficLayer = new google.maps.TrafficLayer();
//     	trafficLayer.setMap(indMarker);
        contentString = "<s:property value="DEVICE_ID" />";
        google.maps.event.addListener(indMarker, 'click', function(
                contentString) {
            return function() {
                infowindow.setContent(contentString);//set the content
                infowindow.open(map, this);
             };
        });
        markers.push(indMarker);
        </s:iterator>
    }

    function getCordinatesvalue(value) {
        initialize();
        $("#vehicledetails").css("display", "block");
     var startdate  ='<s:property value="startdate"/>';
     var enddate  ='<s:property value="enddate"/>';
   
   
            $("#vehicledetails").css("display", "block");
            var vehicleID = document.getElementById("vehiclelist").value;
            var divisionlist=document.getElementById("divisionlist1").value;
            var depolist=document.getElementById("depotlist1").value;
//             alert("div =="+divisionlist+depolist);
            if(divisionlist1==0){
            	 plotVehicle();
                
            }
//             if (vehicleID == 'ALL') {
// //                 $("#vehicledetails").css("display", "none");
               
// //                 var divId1 = document.getElementById("start");
// //                 divId1.style.display='none';   
//                 plotVehicle();
//             }
//             var divId1 = document.getElementById("start");
//             divId1.style.display='';   
            $("#msg").html("Please wait...");
            getRefresh();
            getCordinates(vehicleID, 0, startdate,"",vehicleID);
//             getRefresh();
           
            $("#msg").html("");
       
       
    }
  
</script>
<div class="page-content-wrapper">
    <div class="page-content">
   
        <div class="row">
            <div class="col-md-6">
                <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                <h3 class="page-title">
                    Live Buses (All)<small> current status</small>
                </h3>
                <!-- END PAGE TITLE & BREADCRUMB-->
            </div>
        </div>
       
        <div class="portlet-body form">
            <div class="form-body">
                <div class="form-group">
                    <!-- <label class="col-md-3 control-label">Division Name: <font
                        color="red">*</font>
                    </label> -->

                    <div class="col-md-4">
                        <s:select list="divisionlist" id="divisionlist1"
                            name="orgchart.org_chart_id" 
                            cssClass="select2_category form-control" onchange="getCordinatesvalue(this.value)" onclick="getRefresh()"></s:select>
                                   
                    </div>
                
                    <div class="col-md-4" style="visibility: hidden">
                        <select  list="depotlist" id="depotlist1"   class="select2_category form-control"
                             headerKey="0" headerValue="All">
                             <option value="0">All</option>
                             </select>
                   
        </div>
       
   
<!--         <div class="portlet-body form"> -->
<!--             <div class="form-body"> -->
<!--                 <div class="form-group"> -->
                <div class="col-md-4" style="visibility: hidden">
<!--                     <div class="col-md-5" > -->
                     <s:select list="vehiclelistname" id="vehiclelist" 
                                                name="vehiclelist"
                                                 cssClass="select2_category form-control "
                                                 onchange="getCordinatesvalue(this.value)" headerKey="0" headerValue="All"></s:select>
               
                </div>
            </div>
          <div class="form-body">
                 <div class="form-group">
                    
                     <div class="col-md-4">
                        
                     </div>
                 </div>
             </div>
           

           


<!--                 <div class="col-md-4">        -->
<%--                 <span id="msg"></span> --%>

<!--             </div> -->
            
               <div class="col-md-5">       
                <span id="msg"></span>
                        <img src="assets/images/bus-map-icon.png" > Speed > 5
                        <img src="assets/images/Icon-Bus-Orange.png" > Speed < 5
                        <img src="assets/images/Icon-Bus-Red.gif" > Accident/Breakdown            
               
                </div>
                    <img src="assets/images/Icon-Bus-Blue.png" > Stationary
                    <img src="assets/images/grey.png" > NRD
                    <img src="assets/images/fourbyfour.png" 
                        onclick="getjeepVehicle()" /><blink>Track Jeep Vehicles</blink>
            </div>
            
            <div id='loadingmessage' style='display:none' align="right">
          Loading Please Wait...<img src='assets/images/loader1.gif' width="300px" align="right"/>
</div>

            <div class="form-group" id="mapdiv" >
                <table class="table table-striped">
                    <tr>
                        <td valign="top">
                            <div class="portlet-body" style="height: 1000px">
                                <div id="gmap_basic" class="gmaps" style="height: 100%"></div>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<form action="#" class="form-horizontal ">

    </div>
    </form>
<script>
    google.maps.event.addDomListener(window, 'load', initializeMap);
</script>