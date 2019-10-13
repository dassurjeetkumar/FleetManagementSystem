
var busString;
var map, ren, ser;
var directionResult = [];
var directionsDisplay;
var wayA = [];
var wayB = [];
var tempk,tempa;
var drArr = [];
var drsArr = [];
var pArr = [];
var drArrcnt=0;
var pArrcnt=0;
var temp=[];
var tempnum=[];
var tempid="";
var flag="";
var image;
var z=0;var y=1;var v=0;
var flightPathpath;
var encodeString;
var b="b";
var a="a";
var fare="fare";
var substage1="substage";
var prestopid="";
var tempbuusstop=[];
var frmbusid="";
var tobusid="";
var markers=[];
var waitflag=0;

$(function(){
	
	var mapOptions = {
		    zoom: 15,
		    center: new google.maps.LatLng("12.97928309", "77.57205963")
		  };
	map = new google.maps.Map(document.getElementById('gmap_marker'), mapOptions);
	$("#gmap_marker").css("height", "1000px");
	google.maps.event.trigger(map, "resize");
	size=new google.maps.Size(0, 0);
	 image = {
		    url: 'images/beachflag.png',
		    // This marker is 20 pixels wide by 32 pixels tall.
		    size: new google.maps.Size(1, 1),
		    // The origin for this image is 0,0.
		    origin: new google.maps.Point(0,0),
		    // The anchor for this image is the base of the flagpole at 0,32.
		    anchor: new google.maps.Point(0, 32)
		  };
	 
	/* $('#gmap_marker').click(function() {
		  $('#gmap_marker').reveal();
		  google.maps.event.trigger(map, 'resize');
		       });*/
		         
	 

});
function initialize(result,center,zoom2,isStartOrEndStop) {
	 //alert("hiii"+center);
	center=center.split(",");
	//alert(center);
	map.setCenter(new google.maps.LatLng(center[0],center[1]));//{lat: center[0], lng: center[1]});
	//new google.maps.Marker({position: {lat: center[0], lng: center[1]}, map: map});
	//map.setZoom(30)
	var zoom1=0;
	zoom1=zoom2;	
  var myLatlng = new google.maps.LatLng( center[0],center[1]);
 
	busString=result;
	 /*var mapOptions = {
			    zoom: zoom1,
			    center: myLatlng
			  };*/
  
       var infowindow = new google.maps.InfoWindow({
      content: "aaaa"
    });

    var locations=busString.split("@@@");
  
    var source="",dest="",name="",devicecode="",desc="",nameka="",loc="",status="",icon="",area="",landmark="",icon1="",mcolor="",dir="",id="",pointtype="", farestage="", busstationid="";
   // alert(locations.length);
 
    for (i = 0; i < locations.length; i++) {
	  var dloc=locations[i].split("|");
	  for(var v=0;v<dloc.length;v++){
		
		   if(dloc[v]==="null" || dloc[v]=="null"){
	  	   dloc[v]=" ";
	       }
		   if(dloc[v]==="undefined" || dloc[v]=="undefined"){
				   dloc[v]=" ";
		       }
		   if(dloc[v]==""){
				   dloc[v]=" ";
		       }
		   
	  }
	      name =dloc[0];
		  source =dloc[1];
		  dest =dloc[2];
		  id=dloc[3];
		  devicecode=dloc[4];
		  nameka=dloc[5];
		  desc=dloc[6];
		  status=dloc[7];
		  loc=dloc[8];
		  dir=dloc[10];
		  landmark=dloc[11];
		  area=dloc[12];
		  pointtype=dloc[14];
		  farestage=dloc[15];
		  busstationid=dloc[16]
		  substage=dloc[17]
		  switch (status) {
          case "New":
              icon = "red";
              break;
          case "Inactive":
              icon = "yellow";
              break;
          case "Approved":
              icon = "green";
              break;
         
      }
		  if(dloc[13]=='Y'){
			  icon = "blue"; 
		  }
		  if(dloc[14]=="2"){
			  icon = "brown";  
		  }
		  if(dloc[14]=="18"){
			  icon = "chart";  
		  }
		  if(dloc[14]=="13"){
			  icon = "grey";  
		  }
		//  alert(isStartOrEndStop);
		  if(isStartOrEndStop=="true"){
			  icon = "start&endBusStop";
		  }
	  icon1 = "assets/images/"+icon+".png";
      marker = new google.maps.Marker({
          position: new google.maps.LatLng(source, dest),
          map: map,
          animation: google.maps.Animation.DROP,
          icon: new google.maps.MarkerImage(icon1),
          title: name+" ("+$('<div/>').html(nameka).text()+")"+" Towards:"+dir,
          id : id,
          source :source,
		  dest :dest,
		  name : name,
		  devicecode :devicecode,
		  desc :desc,
		  nameka :nameka,
		  loc : loc,
		  status :status,
		  dir :dir,
		  landmark :landmark,
		  area :area,
		  pointtype :pointtype,
		  farestage:farestage,
		  busstationid : busstationid,
		  substage : substage,
      });
      markers.push(marker);
      infowindow = new google.maps.InfoWindow();
      
      
      /*google.maps.event.addListener(marker, 'mouseover', (function(marker, i) {
          return function() {
        	  //alert(marker.name);
     	     infowindow.setContent(marker.name+" ("+$('<div/>').html(marker.nameka).text()+")"+" Towards:"+marker.dir); // contentString can be html as far as i  know whose style you can override
     	     infowindow.setPosition(marker.position);

     	     infowindow.open(map);
     	    setTimeout(function () { infowindow.close() }, 3000);

          }
      })(marker, i));
      google.maps.event.addListener(marker, 'mouseout', function(evt) {
    	  infowindow.close();
   });*/
      
      google.maps.event.addListener(marker, 'click', (function(marker, i) {
          return function() {
        	 // alert("pre"+tempbuusstop+"===="+marker.id);
        	//  tempbuusstop = $.unique( tempbuusstop );
        	  //alert("post"+tempbuusstop);
        	  //alert($('#road').val());
        	  
        	  check_stage1();
        	  arrange_footer();
        	  var roadtype=$('#road').val();
        	  reset_seq();
        	  z++;v++;
        	 // alert("z"+z);
        	 // console.log("wait flag===========>"+waitflag);
        	//  var latLng = marker.getPosition(); // returns LatLng object
        	 // console.log("LatLNG"+latLng);
        	 // map.setCenter(latLng);
        	  if(waitflag==1){
        		  alert("Waiting For Response From Google Map");
        		  z--;v--;y--;
    			  return false;
        	  }
        	  if(z==1 || z==2){
        		  if(marker.pointtype=="2"){
        			  alert("Source Stop or Destination Stop should be Bus Stop");
        			  z--;v--;y--;
        			  return false;
        		  }
        	  }
        	  if(z%2==1){
        		  frmbusid=marker.id;
        		 // alert("frmbusid"+frmbusid);
        	  }
        	 //alert(z+"======="+y+"=========="+v);
        	  if(z%2==0){
        		  
        		 // alert("tobusid"+tobusid);
        		  
        		  if(z>=5){
        			  console.log(tempbuusstop);
            		 /* if(tempbuusstop.indexOf(frmbusid)!=-1 && (tempbuusstop.indexOf(marker.id)!=-1 && parseInt($("#end_point_id").val())!=marker.id)){
            			  alert("Invalid Link Selection");
            			  frmbusid="";
            			  z=z-2;v=v-2;
            			  wayA.pop();
            			  $('#selectbusstopname').html("<blink> <font color='blue'>Selected From Stop=></font></blink>");
            			  for(var con=0;con<=y;con++){
		        		    	$('#a'+(con)).prop("disabled", false); 
		        		    }
            			  return false;
            		  }*/
        			  var start_bus_stop_id_M=[];
                 		var end_bus_stop_id_M=[];
                 		
                 		$(".start_bus_stop_id").each(function() {
                 		 start_bus_stop_id_M.push(parseInt($(this).val()));
                 		});
                 	
                 		$(".end_bus_stop_id").each(function() {
                 		 end_bus_stop_id_M.push(parseInt($(this).val()));
                 		});
                 		console.log(start_bus_stop_id_M);
                 		console.log(end_bus_stop_id_M);
                 		var busunqchkarr=0;
                 		var busunqchkarr1=0;
                 		for(var va=0;va<start_bus_stop_id_M.length;va++){
                 			if(start_bus_stop_id_M[va]==frmbusid && end_bus_stop_id_M[va]==marker.id ){
                 				alert("Link Is Already Present");
                 				frmbusid="";
               			    z=z-2;v=v-2;
               			    wayA.pop();
               			    $('#selectbusstopname').html("<blink> <font color='blue'>Selected From Stop=></font></blink>");
               			    for(var con=0;con<=y;con++){
             		    	       $('#a'+(con)).prop("disabled", false); 
             		            }
               			  return false;
                 				
                 			}
                 			if(start_bus_stop_id_M[va]==marker.id && end_bus_stop_id_M[va]== frmbusid){
                 				alert("Invalid Link Selection");
                 			    frmbusid="";
               			    z=z-2;v=v-2;
               			    wayA.pop();
               			    $('#selectbusstopname').html("<blink> <font color='blue'>Selected From Stop=></font></blink>");
               			    for(var con=0;con<=y;con++){
             		    	       $('#a'+(con)).prop("disabled", false); 
             		            }
               			  return false;
                 			}
                 			
                 			/*if(start_bus_stop_id_M[va]==frmbusid){
                 				busunqchkarr++;
                 			}
                 			if(end_bus_stop_id_M[va]== marker.id){
                 				busunqchkarr++;
                 			}
                 			if(start_bus_stop_id_M[va]==marker.id){
                 				busunqchkarr1++;
                 			}
                 			if(end_bus_stop_id_M[va]==frmbusid ){
                 				busunqchkarr1++;
                 			}
                 				if(busunqchkarr==2 || busunqchkarr1==2){
                 				alert("Invalid Stop Selection");
                 			    frmbusid="";
               			    z=z-2;v=v-2;
               			    wayA.pop();
               			    $('#selectbusstopname').html("<blink> <font color='blue'>Selected From Stop=></font></blink>");
               			    for(var con=0;con<=y;con++){
             		    	       $('#a'+(con)).prop("disabled", false); 
             		            }
               			  return false;
                 			}*/
                 		}
            		  }
        		  tobusid=marker.id;
        		  if(frmbusid==tobusid){
        			  alert("From And till Bus Stop should not be same");
        			  z=z-1;v=v-1;
        			 // wayA.pop();
        			//  wayB.pop();
        			  return false;
        		  }
        		 
        	  }
        	 var platform=0;
        	 //alert("hiiiii"+marker.pointtype);
        	 if(marker.pointtype==6){
        		 platform=1;
        	 }
        	 
        	 //alert(z);
        	 /*if(z==1 &&  $('#busstopid1').html()!=""){
        		 alert("hiii"+$('#busstopid1').html());
        		 z--;v--;
        		 tempbuusstop.pop();
        		 return false;
        	 }*/
        	  if(z==1){
        		  tempbuusstop.push(marker.id);
        		  v=z;
        		  var check="";
        		 
        		 if(platform==1){
        			 $('#bus_stops tr').last().after('<tr id="'+z+'"><td class="seq">'+z+'</td><td id="busstopid'+z+'" class="busstopid" style="display: none;">'+marker.id+'</td><td class="cstage">0</td><td id="busstopname'+z+'" class="busstopname">'+marker.name+'</td><td>  <input type="checkbox" class="fare" id="fare'+z+'" name="fare'+z+'" value="fare" onClick="checkstage('+z+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" class="substage" id="substage'+z+'" name="substage'+z+'" value="substage" onClick="checkstage('+z+',\''+substage1+'\',\''+fare+'\');"> </td><td style="display: none;"><select id="status'+z+'" class="status"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td><a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+marker.id+','+z+','+marker.busstationid+')">Add Platform</a></td></td><td></td></tr>'); 
        		 }else{
        			 //alert(fare+"===="+substage1);
        			 $('#bus_stops tr').last().after('<tr id="'+z+'"><td class="seq">'+z+'</td><td id="busstopid'+z+'" class="busstopid" style="display: none;">'+marker.id+'</td><td class="cstage">0</td><td id="busstopname'+z+'" class="busstopname">'+marker.name+'</td><td>  <input type="checkbox" class="fare" id="fare'+z+'" name="fare'+z+'" value="fare" onClick="checkstage('+z+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" class="substage" id="substage'+z+'" name="substage'+z+'" value="substage" onClick="checkstage('+z+',\''+substage1+'\',\''+fare+'\')"> </td><td style="display: none;"><select class="status" id="status'+z+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td></td><td></td></tr>');
        		 }
        		  
        		  //if(marker.farestage=="Y"){
        			  //alert("hiii");
        			  $('#fare'+z).prop('checked', true);
        			  document.getElementById("fare"+z).disabled = true;
        		 // }
        		  //if(marker.substage=="Y"){
        			  //alert("hiii");
        			  $('#substage'+z).prop('checked', false);
        			  document.getElementById("substage"+z).disabled = true;
        		  //}
        	  }
        	  if(z==3){
        		  //tempbuusstop.push(marker.id);
        		  //$('#start_point_id').val();
        		  //alert($('#start_point_id').val()+"=============="+marker.id);
        		  if($('#start_point_id').val()!=marker.id){
        			  alert("Please Select Proper First Stop");
        			  z--;v--;
        			  return false;  
        		  }
        		  
        	  }
        	  if(z==4){
        		  tempbuusstop.push(marker.id);
        	  }
        	  /*if(tempid==marker.id){
        		  return false;
        	  }*/
        	  if(z>=5){
        		 // alert(tempbuusstop+"===="+marker.id);
        		  
        		if((z%2)==1){
        			//alert(z+"=========="+temp[(temp.length-1)]+"================"+marker.id);
        		  /*if(temp[(temp.length-1)]!=marker.id) {
        			 alert("Please Select Proper Start Point");
        			 z--;v--;
       			  return false;   
        		 }*/
        		}
        		 // alert(flag+"hii");
        		  if(flag=="comp"){
        			//  return false;     
        		  }
        	  }
        	  if($('#end_point_id').val()==marker.id){
    			 flag="comp";
    			// alert(flag);
    		  }
        	  
        	  tempid=marker.id;
        	  if (wayA.length == wayB.length) {
        		  if(z>=3){
        			  var uniqueNames1 = [];
        				var names1=tempbuusstop;
        				$.each(names1, function(i, el){
        				    if($.inArray(el, uniqueNames1) === -1) uniqueNames1.push(el);
        				});
        				tempbuusstop=uniqueNames1;
        				
        			  if(parseInt($("#end_point_id").val())==parseInt(marker.id)){
        				  alert("Invalid Bus Stop");
            			  z--;v--;
            			  return false;
        			  }
        		}
        		  if(jQuery.inArray( marker.id, tempbuusstop )==-1 ){
        			  alert("Invalid Bus Stop");
        			  z--;v--;
        			  return false;
        		  }
        		  //tempbuusstop.push(marker.id);
        		    wayA.push(new google.maps.Marker({
        	          draggable: true,		
        	          position: new google.maps.LatLng(marker.source, marker.dest),
        	          map: map,
        	          /*icon: {
	        	            
        	              scaledSize: size // pixels
        	          },*/
        	          icon: image,

        	          source :marker.source,
        			  dest :marker.dest,
        			  status :marker.status,
        			  name : marker.name,
        			  id :marker.id,
        			  pointtype :marker.pointtype
        			  
        	        }));
        		    prestopid=marker.id;
        		    for(var con=0;con<=z;con++){
        		    	$('#a'+(con)).prop("disabled", true); 
        		    }
        		    $('#selectbusstopname').html("<blink> <font color='blue'>Selected From Stop=>"+marker.name+"</font></blink>");
        		//    temp.push(marker.id);
   	        	// tempnum.push(v);
        	        } else {
        	        	// alert("in not equal");
        	        	//alert(((z-2)/2));
        	        	if(z!=2){
        	        	//tempbuusstop.push(marker.id);
        	        	}
        	        	for(var w=0; w<=((z-2)/2);w++){
        	        	//$('#a'+w).prop("disabled", true);
        	        }
        	        	//alert("hiii"+v);
        	        	 temp.push(marker.id);
        	        	 
        	        	 
        	        	wayB.push(new google.maps.Marker({
        	        		  	  draggable: true,	
        	        	          position: new google.maps.LatLng(marker.source, marker.dest),
        	        	          map: map,
        	        	          /*icon: {
        	        	              
        	        	              scaledSize: size // pixels
        	        	          },*/
        	        	          icon: image,

        	        	          source :marker.source,
        	        			  dest :marker.dest,
        	        			  status :marker.status,
        	        			  name : marker.name,
        	        			  id :marker.id,
        	        			  pointtype :marker.pointtype
        	        			  
        	        	        }));   
        	        	for(var con=0;con<=z;con++){
            		    	$('#a'+(con)).prop("disabled", false); 
            		    }
        	        	$('#selectbusstopname').html("<blink> <font color='blue'>Selected Till Stop=>"+marker.name+"</font></blink>");
        	        	tempbuusstop.push(marker.id);
        	        	//alert("wayA[wayA.length-1].getStatus()"+wayA[0].status);
        	        	tempnum.push((((z-2)/2)+1));
        	        	//alert(tempnum);
        	        	if((wayA[wayA.length-1].pointtype=="2") || (wayB[wayB.length-1].pointtype=="2")){
        	        		//alert("hiiiii");
        	        		if(z>=3){
        	        			v=(((z-2)/2)+1);
        	        			var check="";
        	    // alert(z);   
        	        			if(platform==1){  		
        	        				$("#bus_stops tr:contains('" + prestopid + "')").last().after('<tr id='+(((z-2)/2)+1)+'><td class="seq">'+(((z-2)/2)+1)+'</td><td id="busstopid'+v+'" class="busstopid" style="display: none;">'+marker.id+'</td><td class="cstage"></td><td class="busstopname" id="busstopname'+v+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+v+'" name="fare'+v+'" value="fare" '+check+' onClick="checkstage('+v+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+v+'" name="substage'+v+'" value="substage" '+check+' onClick="checkstage('+v+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+v+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td>  <td><a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+marker.id+','+z+','+marker.busstationid+')">Add Platform</a></td></td><td><button type="button" id="a'+(((z-2)/2)+1)+'" class="btn dark" onclick="resetMap(\''+pArrcnt+'\',\'C\',\''+(((z-2)/2)+1)+'\')">Remove</button></td></tr>');	
        	        			}else{
        	        				//alert(prestopid);
        	        				$("#bus_stops tr:contains('" + prestopid + "')").last().after('<tr id='+(((z-2)/2)+1)+'><td class="seq">'+(((z-2)/2)+1)+'</td><td id="busstopid'+v+'" class="busstopid" style="display: none;">'+marker.id+'</td><td class="cstage"></td><td class="busstopname" id="busstopname'+v+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+v+'" name="fare'+v+'" value="fare" '+check+' onClick="checkstage('+v+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+v+'" name="substage'+v+'" value="substage" '+check+' onClick="checkstage('+v+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+v+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td><td><button type="button" id="a'+(((z-2)/2)+1)+'" class="btn dark" onclick="resetMap(\''+pArrcnt+'\',\'C\',\''+(((z-2)/2)+1)+'\')">Remove</button></td></tr>');	
        	        			}
        	        			if(marker.pointtype==2){
        	        				 $('#fare'+v).prop('checked', false);
        	        				 $('#substage'+v).prop('checked', false);
        	        				 document.getElementById("fare"+v).disabled = true;
           	        			  document.getElementById("substage"+v).disabled = true;
        	        			}else{
          	      //  alert("hiii");		  
          	        		 if(marker.farestage=="Y"){
          	        			  //alert("hiii");
          	        			  $('#fare'+v).prop('checked', true);
          	        		  }
          	        		if(marker.substage=="Y"){
        	        			  //alert("hiii");
        	        			  $('#substage'+v).prop('checked', true);
        	        		  }
        	        			}
        	        			  
        	        		}
        	  	var from = new google.maps.LatLng(wayA[wayA.length-1].source, wayA[wayA.length-1].dest);
        	  	var to   = new google.maps.LatLng(wayB[wayB.length-1].source, wayB[wayB.length-1].dest);
        	  	//alert(from.toSource()+"======"+to.toSource());
        	  	var d = google.maps.geometry.spherical.computeDistanceBetween(from, to);
        	  	d=Math.round(d);
      	      //alert(d);
      	       
        	  	var flightPlanCoordinates = [
        	  	                           new google.maps.LatLng(wayA[wayA.length-1].source, wayA[wayA.length-1].dest),
        	  	                           new google.maps.LatLng(wayB[wayB.length-1].source, wayB[wayB.length-1].dest),
        	  	                           ];
        	    var flightPath = new google.maps.Polyline({
        	        path: flightPlanCoordinates,
        	        geodesic: true,
        	        strokeColor: '#FF0000',
        	        strokeOpacity: 2.0,
        	        strokeWeight: 8
        	      });

        	      flightPath.setMap(map);
        	      
        	      //flightPath.push(new google.maps.LatLng(wayA[wayA.length-1].source, wayA[wayA.length-1].dest));
        	      //flightPath.push(new google.maps.LatLng(wayB[wayB.length-1].source, wayB[wayB.length-1].dest));
        	      
        	      pArr.push(flightPath);
        	       flightPathpath = flightPath.getPath();
        	       encodeString = google.maps.geometry.encoding.encodePath(flightPathpath);
        	     // alert("y==============="+encodeString);
        	       var strwaydata="{@start@:{@lat@:"+wayA[wayA.length-1].source+",@lng@:"+wayA[wayA.length-1].dest+"},@end@:{@lat@:"+wayB[wayB.length-1].source+",@lng@:"+wayB[wayB.length-1].dest+"},@waypoints@:[]}";
        	     // alert(strwaydata);
        	      strwaydata=strwaydata.replace(/,/g, '*');
        	      //alert("hiii val==="+$("#bus_stops_map tr:contains('" + prestopid + "')").last());
        	      if(y==2 || ($('#start_point_id').val()==wayA[wayA.length-1].id)){
        	    	  
        	    	  $('#bus_stops_map tr').eq(1).after('<tr id="b'+y+'"><td colspan="7">'+wayA[wayA.length-1].name+' To '+wayB[wayB.length-1].name+'</td></tr>');
        	    	  $('#bus_stops_map tr').eq(2).after('<tr id="a'+y+'"><td class="dkm" id="dkm'+v+'">'+(d/1000).toFixed(2)+'</td><td style="display: none;" class="dm" id="dm'+v+'">'+d.toFixed(2)+'</td><td class="tm" id="tm'+v+'">'+((d/1000)*1.21).toFixed(2)+'</td><td style="display: none;" class="ts" id="ts'+v+'">'+(((d/1000)*1.21)*60).toFixed(2)+'</td><td><select class="road_type" id="road_type'+v+'">'+roadtype+'</select></td><td class="tdg" id="tdg'+v+'">0</td><td class="tdv" id="tdv'+v+'">'+(d/1000).toFixed(2)+'</td><td class="tt" id="tt'+v+'">'+(((d/1000)*1.21)).toFixed(2)+'</td><td><input type="textbox" maxlength="6" size="5" class="tds" id="tds'+v+'"/></td>  <td><input type="textbox" size="5" maxlength="6" class="tts" id="tts'+v+'" onKeyUp="isInteger(this)"/></td><td><input type="hidden" class="polyp" id="polyp'+v+'" value="'+encodeString+'"></td>   <td><input type="hidden" class="editpath" id="editpath'+v+'" value="'+strwaydata+'"></td>  <td><input type="hidden" class="start_bus_stop_id" id="start_bus_stop_id'+v+'" value="'+wayA[wayA.length-1].id+'"></td><td><input type="hidden" class="end_bus_stop_id" id="end_bus_stop_id'+v+'" value="'+wayB[wayB.length-1].id+'"></td><td style="display: none;">'+wayB[wayB.length-1].id+'</td><td><input type="hidden" class="route_type" id="route_type'+v+'" value="TRIMAX"></td></tr>');
        	    	  
            	     
        	      } else{
        	      $("#bus_stops_map tr:contains('" + prestopid + "')").last().after('<tr id="a'+y+'"><td class="dkm" id="dkm'+v+'">'+(d/1000).toFixed(2)+'</td><td style="display: none;" class="dm" id="dm'+v+'">'+d.toFixed(2)+'</td><td class="tm" id="tm'+v+'">'+((d/1000)*1.21).toFixed(2)+'</td><td style="display: none;" class="ts" id="ts'+v+'">'+(((d/1000)*1.21)*60).toFixed(2)+'</td><td><select class="road_type" id="road_type'+v+'">'+roadtype+'</select></td><td class="tdg" id="tdg'+v+'">0</td><td class="tdv" id="tdv'+v+'">'+(d/1000).toFixed(2)+'</td><td class="tt" id="tt'+v+'">'+(((d/1000)*1.21)).toFixed(2)+'</td><td><input type="textbox" size="5" maxlength="6" class="tds" id="tds'+v+'"/></td>  <td><input type="textbox" size="5" maxlength="6" class="tts" id="tts'+v+'" onKeyUp="isInteger(this)"/></td><td><input type="hidden" class="polyp" id="polyp'+v+'" value="'+encodeString+'"></td>   <td><input type="hidden" class="editpath" id="editpath'+v+'" value="'+strwaydata+'"></td>  <td><input type="hidden" class="start_bus_stop_id" id="start_bus_stop_id'+v+'" value="'+wayA[wayA.length-1].id+'"></td><td><input type="hidden" class="end_bus_stop_id" id="end_bus_stop_id'+v+'" value="'+wayB[wayB.length-1].id+'"></td><td style="display: none;">'+wayB[wayB.length-1].id+'</td><td><input type="hidden" class="route_type" id="route_type'+v+'" value="TRIMAX"></td></tr>');
        	      $("#bus_stops_map tr:contains('" + prestopid + "')").last().after('<tr id="b'+y+'"><td colspan="7">'+wayA[wayA.length-1].name+' To '+wayB[wayB.length-1].name+'</td></tr>');
        	      }
        	    //  alert((d/1000).toFixed(2));
   //   alert((   parseFloat($('#maptotdistm').html())    +   (d/1000)).toFixed(2));
                 // $('#maptotdistm').html((   parseFloat($('#maptotdistm').html())    +   (d/1000)).toFixed(2));
                 // $('#maptottimesectm').html(( parseFloat($('#maptottimesectm').html())+((d/1000)*1.21) ).toFixed(2));
                  	var tempdis=0;
    				var temptm=0;
    				$(".dkm").each(function() {
    					tempdis=tempdis+parseFloat($(this).html());
    					});
    				tempdis=Math.round(tempdis);
    				$('#maptotdistm').html(tempdis);
    				$(".tm").each(function() {
    					temptm=temptm+parseFloat($(this).html());
    					});
                  //alert($('#maptottimesectm').html()+"===="+ren[cnt].getDirections().routes[0].legs[0].duration.text.replace(" min","").replace("s",""));
    				//alert(( (parseFloat($('#maptottimesectm').html()))   +parseFloat(parseFloat(ren[cnt].getDirections().routes[0].legs[0].duration.text.replace(" min","").replace("s","")))).toFixed(2));
    				$('#maptottimesectm').html(temptm);
                  y++;
        	        
        	      pArrcnt++;
        	      	        } else{
        	      	        	if(z>=3){
        	      	        		v=(((z-2)/2)+1);
        	      	        		var check="";
        	      	        		//alert("hiiiii");
        	      	        		//alert("hiiii val===0"+$("#bus_stops tr:contains('" + prestopid + "')").last().toSource());
        	      	        		if(platform==1){
        	      	        			$("#bus_stops tr:contains('" + prestopid + "')").last().after('<tr id='+(((z-2)/2)+1)+'><td class="seq">'+(((z-2)/2)+1)+'</td><td id="busstopid'+v+'" class="busstopid" style="display: none;">'+marker.id+'</td><td class="cstage"></td><td class="busstopname" id="busstopname'+v+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+v+'" name="fare'+v+'" value="fare" '+check+' onClick="checkstage('+v+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+v+'" name="substage'+v+'" value="substage" '+check+' onClick="checkstage('+v+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+v+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td>  <a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+marker.id+','+z+','+marker.busstationid+')">Add Platform</a></td><td><button type="button" id="a'+(((z-2)/2)+1)+'"class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+(((z-2)/2)+1)+'\')">Remove</button></td></tr>');	
        	      	        		}else{
        	      	        			$("#bus_stops tr:contains('" + prestopid + "')").last().after('<tr id='+(((z-2)/2)+1)+'><td class="seq">'+(((z-2)/2)+1)+'</td><td id="busstopid'+v+'" class="busstopid" style="display: none;">'+marker.id+'</td><td class="cstage"></td><td class="busstopname" id="busstopname'+v+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+v+'" name="fare'+v+'" value="fare" '+check+' onClick="checkstage('+v+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+v+'" name="substage'+v+'" value="substage" '+check+' onClick="checkstage('+v+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+v+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td><td><button type="button" id="a'+(((z-2)/2)+1)+'"class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+(((z-2)/2)+1)+'\')">Remove</button></td></tr>');
        	      	        		}
	            	        		  if(marker.farestage=="Y"){
	            	        			  //alert("hiii");
	            	        			  $('#fare'+v).prop('checked', true);
	            	        		  }  
	            	        		  if(marker.substage=="Y"){
	            	        			  //alert("hiii");
	            	        			  $('#substage'+v).prop('checked', true);
	            	        		  }  
        	      	        	}
        	      	        	//alert("val of y"+y);
        	        	        	if(y<=1){
        	        	        		
        	        	        	ren = new google.maps.DirectionsRenderer( {'suppressMarkers': true ,'preserveViewport': true,'draggable':true ,polylineOptions: {strokeColor: "blue" ,strokeOpacity: 2.0,strokeWeight: 3}} );	
        	        	        	}else{
        	        	        	ren = new google.maps.DirectionsRenderer( {'suppressMarkers': true ,'preserveViewport': true,'optimizeWaypoints' : true,'draggable':false ,polylineOptions: {strokeColor: "red" ,strokeOpacity: 2.0,strokeWeight: 8}} );
        	        	        	}
        	                		ren.setMap(map);
        	                		ren.setPanel(document.getElementById("directionsPanel"));
        	                			
        	                		drArrcnt++;
        	                		ser = new google.maps.DirectionsService();
        	                		
        	                		
        	                		waitflag=1;
        	                		ser.route({ 'origin': wayA[wayA.length-1].getPosition(), 'destination':  wayB[wayB.length-1].getPosition(), 'travelMode': google.maps.DirectionsTravelMode.DRIVING},function(res,sts) {
        	                			if(sts=='OK') {
        	                	                    directionResult.push(res);
        	                	                  //  var newString = decodePath('encodedPath':"iienA{yjxM?THbB?P@V?lD@^D|ABv@F~@JXJxAJr@Ld@Tn@h@|@LNFHB@`@f@`@f@BBx@fArAxBN^Jb@?BDb@AZIb@Mb@Ud@KPeDjEEFKLKJA@EFUTGFcAx@{DfDYZOTORUZ?@OTwBzCQXQXOXITEHUp@CHKf@ADUz@CLKj@SjAAJGh@Gn@KhBQlBEb@CTKj@Q~@I\\Sp@_@nAgAbDGR]~@u@pBK\\m@jBITWt@o@lBOj@GPa@hAMb@Mb@WbA_@dBSv@_@xAENWbAIb@Ib@EVAJIh@?DEZAHEl@Ch@AJAVA\\C|@@XAZC\\Qj@CHOTKPIJGFED[HSFMBe@Rg@ROJMLQZS`@Uh@Sh@Id@Ef@E|@");
        	                	                    ren.setDirections(res);	
        	                	                    drArr.push(ren);
        	                	                    drsArr.push(res);
        	                	                   // alert(res);
        	                	                    if(y>1){
        	                	                    	var strwaydata=save_waypoints();
        	                	                    	 //alert(strwaydata);
        	                	                    	strwaydata=strwaydata.replace(/"/g, '@');
        	                	                    	strwaydata=strwaydata.replace(/,/g, '*');
        	                	                    	//strwaydata=strwaydata.toSource();
        	                	                    	// alert(strwaydata);
        	                	                    	//alert(res.routes[0].overview_polyline);
        	                	                    	 //alert("hiii val==="+y);
        	                	                    if(y==2 || ($('#start_point_id').val()==wayA[wayA.length-1].id)){
        	                	                    	//alert("11");
        	                	                    	 $('#bus_stops_map tr').eq(1).after('<tr id='+b+y+'><td colspan="7">'+wayA[wayA.length-1].name+' To '+wayB[wayB.length-1].name+'</td></tr>');
        	                	                    	 $('#bus_stops_map tr').eq(2).after('<tr id='+a+y+'><td class="dkm" id="dkm'+v+'">'+parseFloat(res.routes[0].legs[0].distance.value)/1000+'</td><td style="display: none;" class="dm" id="dm'+v+'">'+res.routes[0].legs[0].distance.value+'</td><td class="tm" id="tm'+v+'">'+res.routes[0].legs[0].duration.text.replace(" min","").replace("s","")+'</td><td style="display: none;" class="ts" id="ts'+v+'">'+res.routes[0].legs[0].duration.value+'</td><td><select class="road_type" id="road_type'+v+'">'+roadtype+'</select></td><td class="tdg" id="tdg'+v+'">'+parseFloat(res.routes[0].legs[0].distance.value)/1000+'</td><td class="tdv" id="tdv'+v+'">0</td><td class="tt" id="tt'+v+'">'+res.routes[0].legs[0].duration.text.replace(" min","").replace("s","")+'</td><td><input type="textbox" size="5" maxlength="6" class="tds" id="tds'+v+'"></td><td><input type="textbox" size="5" maxlength="6" class="tts" id="tts'+v+'" onKeyUp="isInteger(this)"></td><td><input type="hidden" class="polyp" id="polyp'+v+'" value="'+res.routes[0].overview_polyline+'"></td>    <td><input type="hidden" class="editpath" id="editpath'+v+'" value="'+strwaydata+'"></td><td><input type="hidden" class="start_bus_stop_id" id="start_bus_stop_id'+v+'" value="'+wayA[wayA.length-1].id+'"></td><td><input type="hidden" class="end_bus_stop_id" id="end_bus_stop_id'+v+'" value="'+wayB[wayB.length-1].id+'"></td><td style="display: none;">'+wayB[wayB.length-1].id+'</td><td><input type="hidden" class="route_type" id="route_type'+v+'" value="GOOGLE"></td></tr>');
        	                	                    }else{
        	                	                    	//alert("22"+prestopid);
        	                	                    	 $("#bus_stops_map tr:contains('" + prestopid + "')").last().after('<tr id='+a+y+'><td class="dkm" id="dkm'+v+'">'+parseFloat(res.routes[0].legs[0].distance.value)/1000+'</td><td style="display: none;" class="dm" id="dm'+v+'">'+res.routes[0].legs[0].distance.value+'</td><td class="tm" id="tm'+v+'">'+res.routes[0].legs[0].duration.text.replace(" min","").replace("s","")+'</td><td style="display: none;" class="ts" id="ts'+v+'">'+res.routes[0].legs[0].duration.value+'</td><td><select class="road_type" id="road_type'+v+'">'+roadtype+'</select></td><td class="tdg" id="tdg'+v+'">'+parseFloat(res.routes[0].legs[0].distance.value)/1000+'</td><td class="tdv" id="tdv'+v+'">0</td><td class="tt" id="tt'+v+'">'+res.routes[0].legs[0].duration.text.replace(" min","").replace("s","")+'</td><td><input type="textbox" size="5" maxlength="6" class="tds" id="tds'+v+'"></td><td><input type="textbox" size="5" maxlength="6" class="tts" id="tts'+v+'" onKeyUp="isInteger(this)"></td><td><input type="hidden" class="polyp" id="polyp'+v+'" value="'+res.routes[0].overview_polyline+'"></td>    <td><input type="hidden" class="editpath" id="editpath'+v+'" value="'+strwaydata+'"></td><td><input type="hidden" class="start_bus_stop_id" id="start_bus_stop_id'+v+'" value="'+wayA[wayA.length-1].id+'"></td><td><input type="hidden" class="end_bus_stop_id" id="end_bus_stop_id'+v+'" value="'+wayB[wayB.length-1].id+'"></td><td style="display: none;">'+wayB[wayB.length-1].id+'</td><td><input type="hidden" class="route_type" id="route_type'+v+'" value="GOOGLE"></td></tr>');
        	                	                    	 $("#bus_stops_map tr:contains('" + prestopid + "')").last().after('<tr id='+b+y+'><td colspan="7">'+wayA[wayA.length-1].name+' To '+wayB[wayB.length-1].name+'</td></tr>');
        	                	                    	
        	                	                    }
        	                	                    //$('#maptotdistm').html((parseFloat($('#maptotdistm').html())+parseFloat(res.routes[0].legs[0].distance.text.replace(" km",""))).toFixed(2));
        	                	                    //$('#maptottimesectm').html(( (parseFloat($('#maptottimesectm').html()))   +parseFloat(res.routes[0].legs[0].duration.text.replace(" min","").replace("s",""))).toFixed(2));
        	                	                    var tempdis=0;
        	                	      				var temptm=0;
        	                	      				$(".dkm").each(function() {
        	                	      					tempdis=tempdis+parseFloat($(this).html());
        	                	      					});
        	                	      				tempdis=Math.round(tempdis);
        	                	      				$('#maptotdistm').html(tempdis);
        	                	      				$(".tm").each(function() {
        	                	      					temptm=temptm+parseFloat($(this).html());
        	                	      					});
        	                	                    //alert($('#maptottimesectm').html()+"===="+ren[cnt].getDirections().routes[0].legs[0].duration.text.replace(" min","").replace("s",""));
        	                	      				//alert(( (parseFloat($('#maptottimesectm').html()))   +parseFloat(parseFloat(ren[cnt].getDirections().routes[0].legs[0].duration.text.replace(" min","").replace("s","")))).toFixed(2));
        	                	      				$('#maptottimesectm').html(temptm);
        	                	                    y++;


        	                	                    }else{
        	                	                    	$('#route_name').val(wayA[wayA.length-1].name+' To '+wayB[wayB.length-1].name);
        	                	                    	//alert("hiiiiiiiiii"+wayA[wayA.length-1].id);
        	                	                    	$('#start_point_id').val(wayA[wayA.length-1].id);
        	                	                    	$('#end_point_id').val(wayB[wayB.length-1].id);
        	                	                    	 y++;
        	                	                    	var coords = google.maps.geometry.encoding.decodePath(res.routes[0].overview_polyline);
        	                	                        callback(coords);
        	                	                    	
        	                	                    }
        	                	                    waitflag=0;
        	                	                    } else {
        	                	                    directionResult.push(null);
        	                	                }
        	                		})
        	                		
        	                		
        	                		
        	                		
        	                		
        	                		google.maps.event.addListener(ren, 'directions_changed',
      	                				  function() {
        	                			if(v==1){
        	                				$('#polypath').val(ren.getDirections().routes[0].overview_polyline.points);
        	                				//polypath
        	                			}
      	                				if(v>2){
      	                					$('#polyp'+v).val(ren.getDirections().routes[0].overview_polyline.points);
      	                				}
      	                				
      	                		});
        	        	        	
        	        	        }
        	        	$('#counter').val(v);
        	        	//alert(v);
        	        	
        	        }
        	  reset_seq();
        	  arrange_footer();
        	  check_stage1();
          }
         // z++;
      })(marker, i));
      
      google.maps.event.addListener(marker, 'dragend', (function(marker, i) {
          return function() {
              	  var point = marker.getPosition();
        	  map.panTo(point);
          }
      })(marker, i));
  }
  
  google.maps.event.addListener(map, 'dragend', getDragedPoint);
  function getDragedPoint()
  {
     
	  if((tempk!=map.getCenter().lat()) && (tempa!=map.getCenter().lng())){
	
	  getStopsLoad(map.getCenter().lat(),map.getCenter().lng());  
	  tempk=map.getCenter().lat() ;
	  tempa=map.getCenter().lng();
	
  }
	
  }
}
var sval="";
function resetMap(j,type,rowid) {
    sval=sval+rowid+",";
	$('#skipval').val(sval);
	   if(type=="G"){
    	   drArr[j].setMap(null);   
       }
    	if(type=="C"){
    	   pArr[j].setMap(null);   
       }
    	//maptotdistm
    	//alert("km===="+parseFloat($('#dkm'+rowid).html()))
    	// $('#maptotdistm').html((parseFloat($('#maptotdistm').html())-parseFloat($('#dkm'+rowid).html())).toFixed(2));
         //$('#maptottimesectm').html( ((parseFloat($('#maptottimesectm').html()))-parseFloat($('#tm'+rowid).html())).toFixed(2));
    	var tempdis=0;
			var temptm=0;
			$(".dkm").each(function() {
				tempdis=tempdis+parseFloat($(this).html());
				});
			tempdis=Math.round(tempdis);
			$('#maptotdistm').html(tempdis);
			$(".tm").each(function() {
				temptm=temptm+parseFloat($(this).html());
				});
        //alert($('#maptottimesectm').html()+"===="+ren[cnt].getDirections().routes[0].legs[0].duration.text.replace(" min","").replace("s",""));
			//alert(( (parseFloat($('#maptottimesectm').html()))   +parseFloat(parseFloat(ren[cnt].getDirections().routes[0].legs[0].duration.text.replace(" min","").replace("s","")))).toFixed(2));
			$('#maptottimesectm').html(temptm);
        // tempbuusstop.remove($('#busstopid'+rowid).html());
        /* tempbuusstop = jQuery.grep(tempbuusstop, function(data){
        	 return data != $('#busstopid'+rowid).html();
        	 })*/
			//alert(tempbuusstop);
        	 var index = tempbuusstop.indexOf($('#busstopid'+rowid).html());
        	 //alert(index);
        	 tempbuusstop.splice(index, 1);
        	 //alert(tempbuusstop);
        	 // alert("myArray"+myArray);
        	 
       $('#'+rowid+'').remove();
       $('#a'+rowid+'').remove();
       $('#b'+rowid+'').remove();
     // alert(tempnum);
     //////////////////////////////  temp.pop();
       
       tempnum.pop();
       //temp.pop();
      // tempnum.pop();
     //  z--;v--;
     //  alert(tempnum+"pop aray"+tempnum[(tempnum.length-1)]);
       $('#a'+(tempnum[(tempnum.length-1)])).prop("disabled", false);
       flag="";
       arrange_footer();
       
        
}
var route_points;
function callback(coords){
    var i;
    route_points="";
    route_points=route_points+"LINESTRING(";
    for(i=0;i<coords.length;i++){
        //console.log(coords[i].lat()+','+coords[i].lng());
        route_points=route_points+""+coords[i].lat()+" "+coords[i].lng()+",";
    }
    route_points=route_points.substring(0,(route_points.length-1))
    route_points=route_points+")";
    //console.log(route_points);
    $('#route_string').val(route_points);
}
var data = {};
function save_waypoints()
{
	var w=[],wp;
	var rleg = ren.directions.routes[0].legs[0];
	data.start = {'lat': rleg.start_location.lat(), 'lng':rleg.start_location.lng()};
	data.end = {'lat': rleg.end_location.lat(), 'lng':rleg.end_location.lng()};
	var wp = rleg.via_waypoints	;
	for(var i=0;i<wp.length;i++)
		{
		w[i] = [wp[i].lat(),wp[i].lng()]	
		};
	data.waypoints = w;
	
	var str = JSON.stringify(data)
//alert(str);
	return str;
	/*var jax = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
	jax.open('POST','process.php');
	jax.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
	jax.send('command=save&mapdata='+str)
	jax.onreadystatechange = function(){ if(jax.readyState==4) {
		if(jax.responseText.indexOf('bien')+1)alert('Updated');
		else alert(jax.responseText)
	}}*/
}

function reset_seq(){
	var seqno=1;
	$(".seq").each(function() {
		$(this).html(seqno);
		seqno++;
			});	
}

function check_stage1(){
	//alert("hiii");
	var farecnt=0;
	var farearr=[];
	var stage=0;
	$(".fare").each(function() {

			if($(this).is(':checked')){
				$('.cstage').html(farecnt);
				farearr.push("Y");
				 //farecnt++
			}else{
				$('.cstage').html(farecnt);
				farearr.push("N");	
				}
			
		
		});
	//alert(farearr);
	$(".cstage").each(function() {

		
		//alert(farecnt);
		$(this).html(farecnt);
			// fare_P.push("Y");
			//alert(farearr[stage]);
			if(farearr[stage]=="Y"){
				 farecnt++;
				// alert(farecnt);
			}
			
		
		stage++;
	
	});
	
}

function arrange_footer(){
	var tempdis=0;
		var temptm=0;
		$(".dkm").each(function() {
			tempdis=tempdis+parseFloat($(this).html());
			});
		tempdis=tempdis.toFixed(2);
		$('#maptotdistm').html(tempdis);
		$(".tm").each(function() {
			temptm=temptm+parseFloat($(this).html());
			});
		$('#maptottimesectm').html(temptm);
}

function clearMarkers() {
	  setAllMap(null);
	}
function setAllMap(map) {
	  for (var i = 0; i < markers.length; i++) {
	    markers[i].setMap(map);
	  }
	  getStopsReLoad("12","77");
	}

