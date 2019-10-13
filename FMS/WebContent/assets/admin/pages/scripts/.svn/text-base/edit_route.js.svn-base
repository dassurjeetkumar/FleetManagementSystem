
var busString;
var map, ren, ser;
var ren=[];
var polyline=[];
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
var tempbusstop=[];
var tempnum=[];
var tempid="";
var flag="";
var image;
var z=1;var y=1;var v=1;
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
var cntrl=0;
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
	 
	 
	 

});
function initialize(result,center,zoom2, colo) {
	center=center.split(",");
	var zoom1=0;
	zoom1=zoom2;	
	var myLatlng = new google.maps.LatLng( center[0],center[1]);
	busString=result;
	 var mapOptions = {
			    zoom: zoom1,
			    center: myLatlng
			  };
	 var infowindow = null;
      /* var infowindow = new google.maps.InfoWindow({
      content: "aaaa"
    });*/

    var locations=busString.split("@@@");
  
    var source="",dest="",name="",devicecode="",desc="",nameka="",loc="",status="",icon="",area="",landmark="",icon1="",mcolor="",dir="",id="",pointtype="", farestage="", busstationid="";
  
 
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
		  
		  if(dloc[14]=="13"){
			  icon = "grey";  
		  }
		  if(dloc[14]=="18"){
			  icon = "chart";  
		  }
		if(colo=="col"){
			icon = "Bus-Icon";  
		}  
		  
	  icon1 = "assets/images/"+icon+".png";
      marker = new google.maps.Marker({
          position: new google.maps.LatLng(source, dest),
          title: "hiii",
          map: map,
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
		  busstationid : busstationid
      });
      if(colo!="col"){
    	  markers.push(marker);
		}  
     
      
      /*infowindow = new google.maps.InfoWindow({
    	  content: name+" ("+$('<div/>').html(nameka).text()+")"+" Towards:"+dir
    	  });*/
      var contentString=name+" ("+$('<div/>').html(nameka).text()+")"+" Towards:"+dir;

     /* var infowindow = new google.maps.InfoWindow({
    	    content: name+" ("+$('<div/>').html(nameka).text()+")"+" Towards:"+dir,
    	    maxWidth:100
    	    });

    	    google.maps.event.addListener(marker, "mouseover", function() {
    	    infowindow.open(map, marker);
    	    });*/
      /*infowindow = new google.maps.InfoWindow();
      
      
      google.maps.event.addListener(marker, 'mouseover', (function(marker, i) {
          return function() {
        	  //alert(marker.name);
     	     infowindow.setContent(marker.name+" ("+$('<div/>').html(marker.nameka).text()+")"+" Towards:"+marker.dir); // contentString can be html as far as i  know whose style you can override
     	     infowindow.setPosition(marker.position);

     	     infowindow.open(map);
          }
      })(marker, i));
      google.maps.event.addListener(marker, 'mouseout', function(evt) {
    	  infowindow.close();
   });*/
      
      google.maps.event.addListener(marker, 'click', (function(marker, i) {
          return function() {
        	 
        	 check_stage1();
        	 var addbusstoprow=0;
        	 var roadtype=$('#road').val();
       	  		 reset_seq();
       	  	arrange_footer();
       	 console.log("wait flag===========>"+waitflag);
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
        	 //alert(tempbuusstop+"============"+marker.id);
        	 if(z%2==1){
       		  frmbusid=marker.id;
       		 // alert("frmbusid"+frmbusid);
       	  }
        	 
        	 if(z%2==0){
        		 if(z>=5){
           		  /*if(tempbuusstop.indexOf(frmbusid)!=-1 && (tempbuusstop.indexOf(marker.id)!=-1 && parseInt($("#end_point_id").val())!=marker.id)){
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
           		 start_bus_stop_id_M.push(parseInt($(this).val().replace('unique', '').replace('unique', '')));
           		});
           	
           		$(".end_bus_stop_id").each(function() {
           		 end_bus_stop_id_M.push(parseInt($(this).val().replace('unique', '').replace('unique', '')));
           		});
           		var busunqchkarr=0;
         		var busunqchkarr1=0;
           		for(var va=0;va<start_bus_stop_id_M.length;va++){
           			if(start_bus_stop_id_M[va]==frmbusid && end_bus_stop_id_M[va]==marker.id ){
           				alert("Link Is Already Present");
           				frmbusid="";
         			    z=z-1;v=v-1;
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
         			    z=z-1;v=v-1;
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
       			    z=z-1;v=v-1;
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
       		// alert(z);
       		 // alert(frmbusid+"tobusid"+tobusid);
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
        	 
        	 if(z==1){
       		 // tempbuusstop.push(marker.id);
       		  v=z;
       		  var check="";
       		$(".busstopid").each(function() {
       			var compfact='uniquebusstop'+marker.id+'uniquebusstop';
       			if($(this).html()==compfact){
       				addbusstoprow=1;
       			   // tempbuusstop.push(marker.id);
       			}
       			});
       		if(addbusstoprow==0){
       		 if(platform==1){
       			 $('#bus_stops tr').last().after('<tr id="'+z+'"><td class="seq">'+z+'</td><td style="display: none;" id="busstopid'+z+'" class="busstopid" >uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage">0</td><td id="busstopname'+z+'" class="busstopname">'+marker.name+'</td><td>  <input type="checkbox" class="fare" id="fare'+z+'" name="fare'+z+'" value="fare" onClick="checkstage('+z+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" class="substage" id="substage'+z+'" name="substage'+z+'" value="substage" onClick="checkstage('+z+',\''+substage1+'\',\''+fare+'\');"> </td><td style="display: none;"><select id="status'+z+'" class="status" ><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td><a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+marker.id+','+z+','+marker.busstationid+',\''+marker.name+'\')">Add Platform</a></td></td><td></td></tr>'); 
       		 }else{
       			 //alert(fare+"===="+substage1);
       			 $('#bus_stops tr').last().after('<tr id="'+z+'"><td class="seq">'+z+'</td><td style="display: none;" id="busstopid'+z+'" class="busstopid">uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage">0</td><td id="busstopname'+z+'" class="busstopname">'+marker.name+'</td><td>  <input type="checkbox" class="fare" id="fare'+z+'" name="fare'+z+'" value="fare" onClick="checkstage('+z+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" class="substage" id="substage'+z+'" name="substage'+z+'" value="substage" onClick="checkstage('+z+',\''+substage1+'\',\''+fare+'\')"> </td><td style="display: none;"><select class="status" id="status'+z+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td></td><td></td></tr>');
       		 }
        	 }
       		  if(marker.farestage=="Y"){
       			  //alert("hiii");
       			  $('#fare'+z).prop('checked', true);
       		  }
       		  if(marker.substage=="Y"){
       			  //alert("hiii");
       			  $('#substage'+z).prop('checked', true);
       		  }
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
       		 //tempbuusstop.push(marker.id);
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
          		}
        		  var uniqueNames1 = [];
  				var names1=tempbuusstop;
  				$.each(names1, function(i, el){
  				    if($.inArray(el, uniqueNames1) === -1) uniqueNames1.push(el);
  				});
  				tempbuusstop=uniqueNames1;
        		//  alert(tempbuusstop+"========="+marker.id);
        		  /*alert(jQuery.inArray( marker.id, tempbuusstop ));
        		  if(tempbuusstop[0]==marker.id){
        			  alert("hii");
        		  }*/
        		 // alert(tempbuusstop);
        		  tempbuusstop=tempbuusstop.toString().split(","); 
        		//  alert(marker.id);
        		  var markersid=(marker.id).replace('"',"");
        		//  alert("tempbuusstop.indexOf"+tempbuusstop.indexOf(markersid));
        		  if(tempbuusstop.indexOf(markersid)==-1){
        			  alert("Invalid Point");
          			  z--;v--;y--;
          			  return false;
        		  }
        		  var foundstopid=0;
        		  for(var s=0;s<tempbuusstop.length;s++){
        			  if(tempbuusstop[s]==marker.id){
        				  foundstopid=1;
            		  }
        		  }
        		  if(foundstopid==0){
        			  alert("Invalid Point");
        			 // z--;v--;
        			  return false;
        		  }
        		  if($("#end_point_id").val()==marker.id){
        			  alert("Invalid Point");
         			 // z--;v--;
         			  return false;
         		  }
        		  /*if(jQuery.inArray( marker.id, tempbuusstop )==-1 ){
        			  alert("Invalid Point");
        			  z--;v--;
        			  return false;
        		  }*/
          		 
        		    wayA.push(new google.maps.Marker({
        	          draggable: true,		
        	          position: new google.maps.LatLng(marker.source, marker.dest),
        	          map: map,
        	          icon: image,
        	          source :marker.source,
        			  dest :marker.dest,
        			  status :marker.status,
        			  name : marker.name,
        			  id :marker.id,
        			  pointtype :marker.pointtype
        			  
        	        }));
        		    for(var con=0;con<=y;con++){
        		    	$('#a'+(con)).prop("disabled", true); 
        		    }
        		    $('#selectbusstopname').html("<blink> <font color='blue'>Selected From Stop=>"+marker.name+"</font></blink>");
        		    prestopid=marker.id;
        	        } else {
        	        	$(".busstopid").each(function() {
        	       			
        	       			if($(this).html()!=marker.id){
        	       				 tempbuusstop.push(marker.id);
        	       			}
        	       			});
        	        	if(z!=2){
            	        	//tempbuusstop.push(marker.id);
            	        	}
            	        	for(var w=0; w<=((z-2)/2);w++){
            	        	//$('#a'+w).prop("disabled", true);
            	        }
            	        	//alert("hiii"+v);
            	        	if($("#start_point_id").val()==marker.id){
                  			  alert("Invalid Point");
                   			 // z--;v--;
                   			  return false;
                   		  }
            	        	 temp.push(marker.id);
        	        	wayB.push(new google.maps.Marker({
        	        		  	  draggable: true,	
        	        	          position: new google.maps.LatLng(marker.source, marker.dest),
        	        	          map: map,
        	        	          icon: image,
        	        	          source :marker.source,
        	        			  dest :marker.dest,
        	        			  status :marker.status,
        	        			  name : marker.name,
        	        			  id :marker.id,
        	        			  pointtype :marker.pointtype
        	        			  
        	        	        }));  
        	        	for(var con=0;con<=y;con++){
            		    	$('#a'+(con)).prop("disabled", false); 
            		    }
        	        	 $('#selectbusstopname').html("<blink> <font color='blue'>Selected Till Stop=>"+marker.name+"</font></blink>");
        	        	if((wayA[wayA.length-1].pointtype=="2") || (wayB[wayB.length-1].pointtype=="2")){
        	        		var findbustbl=0;
        	        		if(z>=3){
        	        			v=(((z-2)/2)+1);
        	        			var check=""; 
        	        			$(".busstopid").each(function() {
        	               			//alert($(this).html());
        	               			var compfact='uniquebusstop'+marker.id+'uniquebusstop';
        	               			if($(this).html()==compfact){
        	               				addbusstoprow=1;
        	               			}
        	               			
        	               			if($(this).text() == 'uniquebusstop' + prestopid+'uniquebusstop' ){
        	               				findbustbl=1;
        	                        }
        	               			
        	               			});
        	               		if(addbusstoprow==0){
        	        			if(platform==1){  		
        	        				if(findbustbl==1){
        	        				$("#bus_stops tr:contains('uniquebusstop" + prestopid + "uniquebusstop')").last().after('<tr id='+y+'><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" '+check+' onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" '+check+' onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td>  <td><a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+marker.id+','+z+','+marker.busstationid+',\''+marker.name+'\')">Add Platform</a></td></td><td><button type="button" id="a'+y+'" class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\','+marker.id+')">Remove</button></td></tr>');	
        	        				}else{
        	        					$("#bus_stops tr:contains('uniquebusstop" + prestopid + "uniquebusstop')").eq(1).after('<tr id='+y+'><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" '+check+' onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" '+check+' onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td>  <td><a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+marker.id+','+z+','+marker.busstationid+',\''+marker.name+'\')">Add Platform</a></td></td><td><button type="button" id="a'+y+'" class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\','+marker.id+')">Remove</button></td></tr>');	
        	        				}
        	        				}else{
        	        				//alert(prestopid);
        	        					if(findbustbl==1){
        	        				$("#bus_stops tr:contains('uniquebusstop" + prestopid + "uniquebusstop')").last().after('<tr id='+y+'><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" '+check+' onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" '+check+' onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td><td><button type="button" id="a'+y+'" class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\','+marker.id+')">Remove</button></td></tr>');	
        	        					}else{
        	        						$("#bus_stops tr:contains('uniquebusstop" + prestopid + "uniquebusstop')").eq(1).after('<tr id='+y+'><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" '+check+' onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" '+check+' onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td><td><button type="button" id="a'+y+'" class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\','+marker.id+')">Remove</button></td></tr>');
        	        					}
        	        					}
        	               		
        	        			if(marker.pointtype==2){
       	        				 $('#fare'+y).prop('checked', false);
       	        				 $('#substage'+y).prop('checked', false);
       	        				 document.getElementById("fare"+y).disabled = true;
          	        			  document.getElementById("substage"+y).disabled = true;
       	        			}else{
         	      //  alert("hiii");		  
         	        		 if(marker.farestage=="Y"){
         	        			  //alert("hiii");
         	        			  $('#fare'+y).prop('checked', true);
         	        		  }
         	        		if(marker.substage=="Y"){
       	        			  //alert("hiii");
       	        			  $('#substage'+y).prop('checked', true);
       	        		  }
       	        			}
        	               		}
          	        		 /*if(marker.farestage=="Y"){
          	        			  $('#fare'+y).prop('checked', true);
          	        		  }
          	        		if(marker.substage=="Y"){
      	        			  //alert("hiii");
      	        			  $('#substage'+y).prop('checked', true);
      	        		  }*/
        	        		}
        	  	var from = new google.maps.LatLng(wayA[wayA.length-1].source, wayA[wayA.length-1].dest);
        	  	var to   = new google.maps.LatLng(wayB[wayB.length-1].source, wayB[wayB.length-1].dest);
        	  	var d = google.maps.geometry.spherical.computeDistanceBetween(from, to);
        	  	d=Math.round(d);
        	  	/*$('#bus_stops_map tr').last().after('<tr><td colspan="7">'+wayA[wayA.length-1].name+' To '+wayB[wayB.length-1].name+'</td></tr>');
                $('#bus_stops_map tr').last().after('<tr><td id="dkm'+y+'">'+(d/1000)+'</td><td id="dm'+y+'">'+d+'</td><td id="tm'+y+'">'+((d/1000)*1.21)+'</td><td id="ts'+y+'">'+(((d/1000)*1.21)*60)+'</td><td><select id="road_type'+v+'"><option value="TAR">TAR</option><option value="MUD">MUD</option></select></td><td id="tdg'+y+'">0</td><td>'+d+'</td><td id="tdv'+y+'">'+(((d/1000)*1.21)*60)+'</td><td><input type="textbox"  id="tds'+y+'"/></td><td><input type="hidden" id="polyp'+y+'" value=""></td><td><input type="hidden" id="start_bus_stop_id'+y+'" value="'+wayA[wayA.length-1].id+'"></td><td><input type="hidden" id="end_bus_stop_id'+y+'" value="'+wayB[wayB.length-1].id+'"></td><td><input type="hidden" id="route_type'+y+'" value="TRIMAX"></td></tr>');
*/
        	  	cntrl++;
        	  	var flightPlanCoordinates = [
        	  	                           new google.maps.LatLng(wayA[wayA.length-1].source, wayA[wayA.length-1].dest),
        	  	                           new google.maps.LatLng(wayB[wayB.length-1].source, wayB[wayB.length-1].dest),
        	  	                           ];
        	    var flightPath = new google.maps.Polyline({
        	        path: flightPlanCoordinates,
        	        geodesic: true,
        	        strokeColor: 'red',
        	        strokeOpacity: 2.0,
        	        strokeWeight: 8
        	      });

        	      flightPath.setMap(map);
        	      drArr.push(flightPath);
        	      flightPathpath = flightPath.getPath();
       	       encodeString = google.maps.geometry.encoding.encodePath(flightPathpath);
       	     // alert("y==============="+encodeString);
       	       var strwaydata="{@start@:{@lat@:"+wayA[wayA.length-1].source+",@lng@:"+wayA[wayA.length-1].dest+"},@end@:{@lat@:"+wayB[wayB.length-1].source+",@lng@:"+wayB[wayB.length-1].dest+"},@waypoints@:[]}";
       	     // alert(strwaydata);
       	      strwaydata=strwaydata.replace(/,/g, '*');
       	      
       	 /*  if(y==2 || ($('#start_point_id').val()==wayA[wayA.length-1].id)){
 	    	  
 	    	  $('#bus_stops_map tr').eq(1).after('<tr id="b'+y+'"><td colspan="7">'+wayA[wayA.length-1].name+' To '+wayB[wayB.length-1].name+'</td></tr>');
 	    	  $('#bus_stops_map tr').eq(2).after('<tr id="a'+y+'"><td class="dkm" id="dkm'+y+'">'+(d/1000).toFixed(2)+'</td><td style="display: none;" class="dm" id="dm'+y+'">'+d.toFixed(2)+'</td><td class="tm" id="tm'+y+'">'+ Math.round(((d/1000)*1.21))+'</td><td style="display: none;" class="ts" id="ts'+y+'">'+ (((d/1000)*1.21)*60)+'</td><td><select class="road_type" id="road_type'+y+'">'+roadtype+'</select></td><td class="tdg" id="tdg'+y+'">0</td><td class="tdv" id="tdv'+y+'">'+(d/1000).toFixed(2)+'</td><td class="tt" id="tt'+y+'">'+ Math.round((((d/1000)*1.21)))+'</td><td><input type="textbox" class="tds" id="tds'+y+'"/></td>  <td><input type="textbox" class="tts" id="tts'+y+'"/></td><td><input type="hidden" class="polyp" id="polyp'+y+'" value="'+encodeString+'"></td>   <td><input type="hidden" class="editpath" id="editpath'+y+'" value="'+strwaydata+'"></td>  <td><input type="hidden" class="start_bus_stop_id" id="start_bus_stop_id'+y+'" value="'+wayA[wayA.length-1].id+'"></td><td><input type="hidden" class="end_bus_stop_id" id="end_bus_stop_id'+y+'" value="'+wayB[wayB.length-1].id+'"></td><td style="display: none;">'+wayB[wayB.length-1].id+'</td><td><input type="hidden" class="route_type" id="route_type'+y+'" value="TRIMAX"></td></tr>');
 	    	  
     	     
 	      } else{*/
 	    	 // alert("hiii");
       	   var findval=0;
       	$('#bus_stops_map tr td').each(function(){
               if($(this).text() == 'unique' + prestopid +'unique'){
               	findval=1;
               }
           });
       	if(findval==1){
       	      $("#bus_stops_map tr:contains('unique" + prestopid + "unique')").last().after('<tr id="b'+y+'"><td colspan="7">'+wayA[wayA.length-1].name+' To '+wayB[wayB.length-1].name+'</td><td class="removefrmstop" style="display: none;">unique'+wayA[wayA.length-1].id+'unique</td><td class="removetostop" style="display: none;">unique'+wayB[wayB.length-1].id+'unique</td></tr>');
 	    	  $("#bus_stops_map tr:contains('unique" + prestopid + "unique')").last().after('<tr id="a'+y+'"><td class="dkm" id="dkm'+y+'">'+(d/1000).toFixed(2)+'</td><td style="display: none;" class="dm" id="dm'+y+'">'+d.toFixed(2)+'</td><td class="tm" id="tm'+y+'">'+ Math.round(((d/1000)*1.21))+'</td><td style="display: none;" class="ts" id="ts'+y+'">'+(((d/1000)*1.21)*60).toFixed(2)+'</td><td><select class="road_type" id="road_type'+y+'">'+roadtype+'</select></td><td class="tdg" id="tdg'+y+'">0</td><td class="tdv" id="tdv'+y+'">'+(d/1000).toFixed(2)+'</td><td class="tt" id="tt'+y+'">'+ Math.round((((d/1000)*1.21)))+'</td><td><input type="textbox" size="5" maxlength="6" class="tds" id="tds'+y+'"/></td>  <td><input type="textbox" size="5" class="tts" id="tts'+y+'" onKeyUp="isInteger(this)"/></td><td><input type="hidden" class="polyp" id="polyp'+y+'" value="'+encodeString+'"></td>   <td><input type="hidden" class="editpath" id="editpath'+y+'" value="'+strwaydata+'"></td>  <td><input type="hidden" class="start_bus_stop_id" id="start_bus_stop_id'+y+'" value="'+wayA[wayA.length-1].id+'"></td><td><input type="hidden" class="end_bus_stop_id" id="end_bus_stop_id'+y+'" value="'+wayB[wayB.length-1].id+'"></td><td style="display: none;">'+wayB[wayB.length-1].id+'</td><td><input type="hidden" class="route_type" id="route_type'+y+'" value="TRIMAX"></td><td class="removefrmstop" style="display: none;">unique'+wayA[wayA.length-1].id+'unique</td><td class="removetostop" style="display: none;">unique'+wayB[wayB.length-1].id+'unique</td></tr>');
       	}else{
       	 $("#bus_stops_map tr:not(:contains('unique" + prestopid + "unique'))").eq(1).after('<tr id="a'+y+'"><td class="dkm" id="dkm'+y+'">'+(d/1000).toFixed(2)+'</td><td style="display: none;" class="dm" id="dm'+y+'">'+d.toFixed(2)+'</td><td class="tm" id="tm'+y+'">'+ Math.round(((d/1000)*1.21))+'</td><td style="display: none;" class="ts" id="ts'+y+'">'+(((d/1000)*1.21)*60).toFixed(2)+'</td><td><select class="road_type" id="road_type'+y+'">'+roadtype+'</select></td><td class="tdg" id="tdg'+y+'">0</td><td class="tdv" id="tdv'+y+'">'+(d/1000).toFixed(2)+'</td><td class="tt" id="tt'+y+'">'+ Math.round((((d/1000)*1.21)))+'</td><td><input type="textbox" size="5" maxlength="6" class="tds" id="tds'+y+'"/></td>  <td><input type="textbox" size="5" class="tts" id="tts'+y+'" onKeyUp="isInteger(this)"/></td><td><input type="hidden" class="polyp" id="polyp'+y+'" value="'+encodeString+'"></td>   <td><input type="hidden" class="editpath" id="editpath'+y+'" value="'+strwaydata+'"></td>  <td><input type="hidden" class="start_bus_stop_id" id="start_bus_stop_id'+y+'" value="'+wayA[wayA.length-1].id+'"></td><td><input type="hidden" class="end_bus_stop_id" id="end_bus_stop_id'+y+'" value="'+wayB[wayB.length-1].id+'"></td><td style="display: none;">'+wayB[wayB.length-1].id+'</td><td><input type="hidden" class="route_type" id="route_type'+y+'" value="TRIMAX"></td><td class="removefrmstop" style="display: none;">unique'+wayA[wayA.length-1].id+'unique</td><td class="removetostop" style="display: none;">unique'+wayB[wayB.length-1].id+'unique</td></tr>');
       		$("#bus_stops_map tr:not(:contains('unique" + prestopid + "unique'))").eq(1).after('<tr id="b'+y+'"><td colspan="7">'+wayA[wayA.length-1].name+' To '+wayB[wayB.length-1].name+'</td><td class="removefrmstop" style="display: none;">unique'+wayA[wayA.length-1].id+'unique</td><td class="removetostop" style="display: none;">unique'+wayB[wayB.length-1].id+'unique</td></tr>');
	    	 
     		
       	}
   	     // }
 	    //  alert((d/1000).toFixed(2));
//   alert((   parseFloat($('#maptotdistm').html())    +   (d/1000)).toFixed(2));
           $('#maptotdistm').html((   parseFloat($('#maptotdistm').html())    +   (d/1000)).toFixed(2));
           $('#maptottimesectm').html(( parseInt( Math.round($('#maptottimesectm').html())+((d/1000)*1.21) )));
           y++;
 	        
           drArrcnt++;
        	      	        } else{
        	      	        	if(z>=3){
        	      	        		v=(((z-2)/2)+1);
        	      	        		var check="";
        	      	        		var findbustbl=0;
        	      	        		$(".busstopid").each(function() {
        	      	        			var compfact='uniquebusstop'+marker.id+'uniquebusstop';
        	      	         			if($(this).html()==compfact){
        	      	         				addbusstoprow=1;
        	      	         			}
        	      	         			
        	      	         			if($(this).text() == 'uniquebusstop' + prestopid+'uniquebusstop' ){
            	               				findbustbl=1;
            	                        }
        	      	         			});
        	      	         		if(addbusstoprow==0){
        	      	        		if(platform==1){
        	      	        			if(findbustbl==1){
        	      	        			$("#bus_stops tr:contains('uniquebusstop" + prestopid + "uniquebusstop')").last().after('<tr id='+y+'><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" '+check+' onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" '+check+' onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td>  <a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+marker.id+','+z+','+marker.busstationid+',\''+marker.name+'\')">Add Platform</a></td><td><button type="button" id="a'+y+'"class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\','+marker.id+')">Remove</button></td></tr>');	
        	      	        			}else{
        	      	        				$("#bus_stops tr:contains('uniquebusstop" + prestopid + "uniquebusstop')").eq(1).after('<tr id='+y+'><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" '+check+' onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" '+check+' onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td>  <a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+marker.id+','+z+','+marker.busstationid+',\''+marker.name+'\')">Add Platform</a></td><td><button type="button" id="a'+y+'"class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\','+marker.id+')">Remove</button></td></tr>');
        	      	        			}
        	      	        			}else{
        	      	        				if(findbustbl==1){
        	      	        			$("#bus_stops tr:contains('uniquebusstop" + prestopid + "uniquebusstop')").last().after('<tr id='+y+'><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" '+check+' onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" '+check+' onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td><td><button type="button" id="a'+y+'"class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\','+marker.id+')">Remove</button></td></tr>');
        	      	        				}else{
        	      	        					$("#bus_stops tr:contains('uniquebusstop" + prestopid + "uniquebusstop')").eq(1).after('<tr id='+y+'><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" '+check+' onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" '+check+' onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td><td><button type="button" id="a'+y+'"class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\','+marker.id+')">Remove</button></td></tr>');
        	      	        				}
        	      	        				}
	            	        		  if(marker.farestage=="Y"){
	            	        			  //alert("hiii");
	            	        			  $('#fare'+y).prop('checked', true);
	            	        		  }  
	            	        		  if(marker.substage=="Y"){
	               	        			  //alert("hiii");
	               	        			  $('#substage'+y).prop('checked', true);
	               	        		  }
        	      	        	}
        	      	        	}
        	      	        	cntrl++;
        	        	        	if(y<=1){
        	        	        		
        	        	        	ren[cntrl] = new google.maps.DirectionsRenderer( {'suppressMarkers': true ,'preserveViewport': true,'draggable':true ,polylineOptions: {strokeColor: "red" ,strokeOpacity: 2.0,strokeWeight: 3}} );	
        	        	        	}else{
        	        	        	ren[cntrl] = new google.maps.DirectionsRenderer( {'suppressMarkers': true ,'preserveViewport': true,'optimizeWaypoints' : true,'draggable':true ,polylineOptions: {strokeColor: "red" ,strokeOpacity: 2.0,strokeWeight: 6}} );
        	        	        	}
        	        	        	chng_dir(cntrl);
        	        	        	/*google.maps.event.addListener(ren[cntrl], 'directions_changed',
        	        	      			  function() {
        	        	        		alert(cntrl)
        	        	      		var strwaydata=save_waypoints(cntrl);
        	        	          	 //alert(strwaydata);
        	        	          	strwaydata=strwaydata.replace(/"/g, '@');
        	        	          	strwaydata=strwaydata.replace(/,/g, '*');
        	        	      				//alert(y+"======="+ren[cntrl].getDirections().routes[0].overview_polyline);
        	        	      				$('#polyp'+(cntrl+1)).val(ren[cntrl].getDirections().routes[0].overview_polyline);
        	        	      				$('#editpath'+(cntrl+1)).val(strwaydata);
        	        	      			
        	        	      	});
        	                		ren[cntrl].setMap(map);*/
        	                		ren[cntrl].setPanel(document.getElementById("directionsPanel"));
        	                			
        	                		drArrcnt++;
        	                		ser = new google.maps.DirectionsService();
        	                		
        	                		waitflag=1;
        	                		
        	                		ser.route({ 'origin': wayA[wayA.length-1].getPosition(), 'destination':  wayB[wayB.length-1].getPosition(), 'travelMode': google.maps.DirectionsTravelMode.DRIVING},function(res,sts) {
        	                			if(sts=='OK') {
        	                	                    directionResult.push(res);
        	                	                  //  var newString = decodePath('encodedPath':"iienA{yjxM?THbB?P@V?lD@^D|ABv@F~@JXJxAJr@Ld@Tn@h@|@LNFHB@`@f@`@f@BBx@fArAxBN^Jb@?BDb@AZIb@Mb@Ud@KPeDjEEFKLKJA@EFUTGFcAx@{DfDYZOTORUZ?@OTwBzCQXQXOXITEHUp@CHKf@ADUz@CLKj@SjAAJGh@Gn@KhBQlBEb@CTKj@Q~@I\\Sp@_@nAgAbDGR]~@u@pBK\\m@jBITWt@o@lBOj@GPa@hAMb@Mb@WbA_@dBSv@_@xAENWbAIb@Ib@EVAJIh@?DEZAHEl@Ch@AJAVA\\C|@@XAZC\\Qj@CHOTKPIJGFED[HSFMBe@Rg@ROJMLQZS`@Uh@Sh@Id@Ef@E|@");
        	                	                    ren[cntrl].setDirections(res);	
        	                	                    drArr.push(ren[cntrl]);
        	                	                    drsArr.push(res);
        	                	                    
        	                	                    if(y>1){
        	                	                    	var strwaydata=save_waypoints(cntrl);
       	                	                    	 //alert(strwaydata);
       	                	                    	strwaydata=strwaydata.replace(/"/g, '@');
       	                	                    	strwaydata=strwaydata.replace(/,/g, '*');
        	                	                    	
        	                	                    	//alert(prestopid);
        	                	                    	//alert($("#bus_stops_map tr:contains('" + prestopid + "')").toSource());
        	                	                    	var b="b";
        	                	                    	var a="a";
        	                	                    	 var findval=0;
        	                	                        	$('#bus_stops_map tr td').each(function(){
        	                	                                if($(this).text() == 'unique' + prestopid +'unique' ){
        	                	                                	findval=1;
        	                	                                }
        	                	                            });
        	                	                        	//alert(findval);
        	                	                        	if(findval==1){
        	                	                    	$("#bus_stops_map tr:contains('unique" + prestopid + "unique')").last().after('<tr id="b'+y+'"><td colspan="7">'+wayA[wayA.length-1].name+' To '+wayB[wayB.length-1].name+'</td><td class="removefrmstop" style="display: none;">unique'+wayA[wayA.length-1].id+'unique</td><td class="removetostop" style="display: none;">unique'+wayB[wayB.length-1].id+'unique</td></tr>');
        	                	                    	$("#bus_stops_map tr:contains('unique" + prestopid + "unique')").last().after('<tr id="a'+y+'"><td class="dkm" id="dkm'+y+'">'+parseFloat(res.routes[0].legs[0].distance.value)/1000+'</td><td style="display: none;" class="dm" id="dm'+y+'">'+res.routes[0].legs[0].distance.value+'</td><td class="tm" id="tm'+y+'">'+ Math.round(res.routes[0].legs[0].duration.text.replace(" min","").replace("s",""))+'</td><td style="display: none;" class="ts" id="ts'+y+'">'+res.routes[0].legs[0].duration.value+'</td><td><select class="road_type" id="road_type'+y+'">'+roadtype+'</select></td><td class="tdg" id="tdg'+y+'">'+parseFloat(res.routes[0].legs[0].distance.value)/1000+'</td><td class="tdv" id="tdv'+y+'">0</td><td class="tt" id="tt'+y+'">'+ Math.round(res.routes[0].legs[0].duration.text.replace(" min","").replace("s",""))+'</td><td><input type="textbox" class="tds" size="5" maxlength="6" id="tds'+y+'"></td><td><input type="textbox" size="5" maxlength="6" class="tts" id="tts'+y+'" onKeyUp="isInteger(this)"></td><td><input type="hidden" class="polyp" id="polyp'+y+'" value="'+res.routes[0].overview_polyline+'"></td>    <td><input type="hidden" class="editpath" id="editpath'+y+'" value="'+strwaydata+'"></td><td><input type="hidden" class="start_bus_stop_id" id="start_bus_stop_id'+y+'" value="'+wayA[wayA.length-1].id+'"></td><td><input type="hidden" class="end_bus_stop_id" id="end_bus_stop_id'+y+'" value="'+wayB[wayB.length-1].id+'"></td><td style="display: none;">'+wayB[wayB.length-1].id+'</td><td><input type="hidden" class="route_type" id="route_type'+y+'" value="GOOGLE"></td><td class="removefrmstop" style="display: none;">unique'+wayA[wayA.length-1].id+'unique</td><td class="removetostop" style="display: none;">unique'+wayB[wayB.length-1].id+'unique</td></tr>');
        	                	                        	}else{
        	                	                        		$("#bus_stops_map tr:not(:contains('unique" + prestopid + "unique'))").eq(1).after('<tr id="a'+y+'"><td class="dkm" id="dkm'+y+'">'+parseFloat(res.routes[0].legs[0].distance.value)/1000+'</td><td style="display: none;" class="dm" id="dm'+y+'">'+res.routes[0].legs[0].distance.value+'</td><td class="tm" id="tm'+y+'">'+ Math.round(res.routes[0].legs[0].duration.text.replace(" min","").replace("s",""))+'</td><td style="display: none;" class="ts" id="ts'+y+'">'+res.routes[0].legs[0].duration.value+'</td><td><select class="road_type" id="road_type'+y+'">'+roadtype+'</select></td><td class="tdg" id="tdg'+y+'">'+parseFloat(res.routes[0].legs[0].distance.value)/1000+'</td><td class="tdv" id="tdv'+y+'">0</td><td class="tt" id="tt'+y+'">'+ Math.round(res.routes[0].legs[0].duration.text.replace(" min","").replace("s",""))+'</td><td><input type="textbox" class="tds" size="5" maxlength="6" id="tds'+y+'"></td><td><input type="textbox" size="5" maxlength="6" class="tts" id="tts'+y+'" onKeyUp="isInteger(this)"></td><td><input type="hidden" class="polyp" id="polyp'+y+'" value="'+res.routes[0].overview_polyline+'"></td>    <td><input type="hidden" class="editpath" id="editpath'+y+'" value="'+strwaydata+'"></td><td><input type="hidden" class="start_bus_stop_id" id="start_bus_stop_id'+y+'" value="'+wayA[wayA.length-1].id+'"></td><td><input type="hidden" class="end_bus_stop_id" id="end_bus_stop_id'+y+'" value="'+wayB[wayB.length-1].id+'"></td><td style="display: none;">'+wayB[wayB.length-1].id+'</td><td><input type="hidden" class="route_type" id="route_type'+y+'" value="GOOGLE"></td><td class="removefrmstop" style="display: none;">unique'+wayA[wayA.length-1].id+'unique</td><td class="removetostop" style="display: none;">unique'+wayB[wayB.length-1].id+'unique</td></tr>');
        	                	                        		$("#bus_stops_map tr:not(:contains('unique" + prestopid + "unique'))").eq(1).after('<tr id="b'+y+'"><td colspan="7">'+wayA[wayA.length-1].name+' To '+wayB[wayB.length-1].name+'</td><td class="removefrmstop" style="display: none;">unique'+wayA[wayA.length-1].id+'unique</td><td class="removetostop" style="display: none;">unique'+wayB[wayB.length-1].id+'unique</td></tr>');
                	                	                    	
                	                	                        		
        	                	                        	}
        	                	                    	$('#maptotdistm').html((parseFloat($('#maptotdistm').html())+parseFloat(res.routes[0].legs[0].distance.value)/1000).toFixed(2));
            	                	                    $('#maptottimesectm').html(( (parseFloat($('#maptottimesectm').html()))   +parseInt( Math.round(res.routes[0].legs[0].duration.text.replace(" min","").replace("s","")))));

            	                	                    
       	                	                    
        	                	                    }else{
        	                	                    	var coords = google.maps.geometry.encoding.decodePath(res.routes[0].overview_polyline.points);
        	                	                        callback(coords);
        	                	                    	$('#route_name').val(wayA[wayA.length-1].name+' To '+wayB[wayB.length-1].name);
        	                	                    	$('#start_point_id').val(wayA[wayA.length-1].id);
        	                	                    	$('#end_point_id').val(wayB[wayB.length-1].id);
        	                	                    }
        	                	                    y++;
        	                	                    waitflag=0;
        	                	                    } else {
        	                	                    directionResult.push(null);
        	                	                }
        	                		})
        	                		
        	                		
        	                		
        	                		
        	                		
        	                	/*	google.maps.event.addListener(ren[cntrl], 'directions_changed',
      	                				  function() {
        	                			if(v==1){
        	                				$('#polypath').val(ren[cntrl].getDirections().routes[0].overview_polyline.points);
        	                				polypath
        	                			}
      	                				if(v>2){
      	                					$('#polyp'+v).val(ren[cntrl].getDirections().routes[0].overview_polyline.points);
      	                				}
      	                				
      	                		});*/
        	        	        	
        	        	        }
        	        	$('#counter').val(v);
        	        	//alert(v);
        	        	v++;
        	        }
        	  reset_seq();
        	  arrange_footer();
        	  check_stage1();
        	  z++;
          }
          //y++;
      })(marker, i));
      
      google.maps.event.addListener(marker, 'dragend', (function(marker, i) {
          return function() {
              	  var point = marker.getPosition();
        	  map.panTo(point);
          }
      })(marker, i));
  }
  
  google.maps.event.addListener(map, 'dragend', getDragedPoint);
  
  function chng_dir(cntr){
	  google.maps.event.addListener(ren[cntr], 'directions_changed',
  			  function() {
    		//alert(cntr)
  		var strwaydata=save_waypoints(cntr);
      	 //alert(strwaydata);
      	strwaydata=strwaydata.replace(/"/g, '@');
      	strwaydata=strwaydata.replace(/,/g, '*');
  				//alert(y+"======="+ren[cntrl].getDirections().routes[0].overview_polyline);
  				$('#polyp'+(cntr+1)).val(ren[cntr].getDirections().routes[0].overview_polyline);
  				$('#editpath'+(cntr+1)).val(strwaydata);
  				$('#dkm'+(cntr+1)).val(parseFloat(ren[cntr].getDirections().routes[0].legs[0].distance.value)/1000);
  				$('#dm'+(cntr+1)).val(parseFloat(ren[cntr].getDirections().routes[0].legs[0].distance.value));
  				$('#tm'+(cntr+1)).val(parseFloat(ren[cntr].getDirections().routes[0].legs[0].duration.text.replace(" min","").replace("s","")));
  				$('#ts'+(cntr+1)).val(parseFloat(ren[cntr].getDirections().routes[0].legs[0].duration.value));
  				$('#tdg'+(cntr+1)).val(parseFloat(ren[cntr].getDirections().routes[0].legs[0].distance.value)/1000);
  				$('#tt'+(cntr+1)).val(parseFloat(ren[cntr].getDirections().routes[0].legs[0].duration.text.replace(" min","").replace("s","")));
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
  				//$('#maptotdistm').html((parseFloat($('#maptotdistm').html())+parseFloat(ren[cntr].getDirections().routes[0].legs[0].distance.value.replace(" km",""))).toFixed(2));
               // $('#maptottimesectm').html(( (parseFloat($('#maptottimesectm').html()))   +parseFloat(parseFloat(ren[cntr].getDirections().routes[0].legs[0].duration.text.replace(" min","").replace("s","")))).toFixed(2));

  			
  	});
		ren[cntr].setMap(map);
  }
  
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
function resetMap(j,type,rowid, busstopid) {
	try {
	disableremovebutton();
	sval=sval+rowid+",";
	$('#skipval').val(sval);
	//alert(j+"========="+(parseInt(j)+1));
	   if(type=="G"){
		   //alert(drArr[j]);
		   //alert(drArr[parseInt(j)+1]);
		   if(typeof drArr[j]!== "undefined"){
			   drArr[j].setMap(null);   
		   }
		   if(typeof drArr[parseInt(j)+1]!== "undefined"){
			   drArr[parseInt(j)+1].setMap(null);  
			  // drArr[parseInt(j)+2].setMap(null);  
		   }
    	     
    	   
       }
    	if(type=="C"){
    		
    		if(typeof pArr[j]!== "undefined"){
    			pArr[j].setMap(null);   
 		   }
    		if(typeof pArr[parseint(j)+1]!== "undefined"){
    			pArr[parseint(j)+1].setMap(null);   
 		   }
    	   
    	   
       }
    	
    	 $('#maptotdistm').html((parseFloat($('#maptotdistm').html())-parseFloat($('#dkm'+rowid).html())).toFixed(2));
         $('#maptottimesectm').html( ((parseFloat($('#maptottimesectm').html()))-parseFloat($('#tm'+rowid).html())).toFixed(2));
         	
              tempbuusstop=tempbuusstop.toString().split(","); 
        	 var index = tempbuusstop.indexOf(busstopid);
        	// alert(index);
        	// alert(tempbuusstop);
        	 tempbuusstop.splice(index, 1);
        	// alert(tempbuusstop);
        	// alert("myArray"+myArray);
        	 $("#bus_stops_map tr td.removefrmstop:contains('unique" + busstopid  + "unique')").each(function() {
        		    if ($(this).text() == 'unique'+busstopid+'unique') {
        		    	//alert("1");
        		    	$(this).parent().remove();
        		    }  
        		});
        	 $("#bus_stops_map tr td.removetostop:contains('unique" + busstopid  + "unique')").each(function() {
     		    if ($(this).text() == 'unique'+busstopid+'unique') {
     		       // alert("2");
     		    	$(this).parent().remove();
     		    }  
     		});
        	// $("#bus_stops_map tr td.removefrmstop:contains('" + busstopid + "')").remove();
        	// $("#bus_stops_map tr td.removetostop:contains('" + busstopid + "')").remove();
       $('#'+rowid+'').remove();
      // $('#a'+rowid+'').remove();
      // $('#b'+rowid+'').remove();
   
       reset_seq();
       arrange_footer();
       check_stage1();
       tempnum.pop();
      
       $('#a'+(tempnum[(tempnum.length-1)])).prop("disabled", false);
       flag="";
       
       enableremovebutton(); 
	}
	catch(err) {
		enableremovebutton(); 
	}
	finally {
		enableremovebutton(); 
	}
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
function save_waypoints(cnt)
{
	var w=[],wp;
	//alert("hiii"+cnt)
	//alert(ren.length+ren[cnt].toSource());
	var rleg = ren[cnt].directions.routes[0].legs[0];
	data.start = {'lat': rleg.start_location.lat(), 'lng':rleg.start_location.lng()};
	data.end = {'lat': rleg.end_location.lat(), 'lng':rleg.end_location.lng()};
	var wp = rleg.via_waypoints	;
	for(var i=0;i<wp.length;i++)
		{
		w[i] = [wp[i].lat(),wp[i].lng()]	
		};
	data.waypoints = w;
	
	var str = JSON.stringify(data)
//alert(cntrl+"===="+str);
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


var edit_arr=[];
var busstoplist;
var maplist;

function plot_edit_route(result){
	edit_arr=JSON.parse(result);
	var result=JSON.parse(result);
	for(var s=0;s<result.routeplatrellen;s++){
    	var temp="";
		temp=temp+result.routeplatrel[s][1]+"|"+result.routeplatrel[s][5]+"|"+result.routeplatrel[s][6];
		platverarr.push(temp);
    	//alert(platverarr);
    	   $('#platformdetail tr').last().after('<tr id="1"><td  style="display: block;">'+result.routeplatrel[s][1]+""+result.routeplatrel[s][5]+""+result.routeplatrel[s][6]+""+'</td><td><input type="hidden" class="busplatformid" id="busplatformid'+platformcounter+'" value="'+result.routeplatrel[s][1]+'">    </td>              <td><input type="hidden" class="depotp" id="depotp'+platformcounter+'" value="'+result.routeplatrel[s][2]+'">    </td><td><input type="hidden" class="floorp" id="floorp'+platformcounter+'" value="'+result.routeplatrel[s][3]+'"></td>    </td><td><input type="hidden" class="bayp" id="bayp'+platformcounter+'" value="'+result.routeplatrel[s][4]+'"></td>    </td><td><input type="hidden" class="platformp" id="platformp'+platformcounter+'" value="'+result.routeplatrel[s][5]+'"></td>    </td><td><input type="hidden" class="servicep" id="servicep'+platformcounter+'" value="'+result.routeplatrel[s][6]+'"></td></tr>');
	       tableplatarr.push({id:result.routeplatrel[s][1],name:result.routeplatrel[s][1]+""+result.routeplatrel[s][5]+""+result.routeplatrel[s][6]+"",planame:result.routeplatrel[s][8],sername:result.routeplatrel[s][7],   depot:result.routeplatrel[s][2],floor:result.routeplatrel[s][3],bay:result.routeplatrel[s][4],platform:result.routeplatrel[s][5],service:result.routeplatrel[s][6]});
	       uniqueid++;
    }
	//alert(edit_arr.toSource());
	busstoplist=edit_arr.routepoint;
	//busstoplist=busstoplist.toString().split(",");
	maplist=edit_arr.routemap;
	//maplist=maplist.toString().split(",");
	$("#pageLoader").show();
	plot_route();
}

function plot_route(){
	if(edit_arr.routepointlen==cntrl){
		check_stage1();
		$("#pageLoader").hide();
		arrange_footer();
		//alert("hiii");
		return;
		
	}
	map.setCenter(new google.maps.LatLng(busstoplist[0][9],busstoplist[0][10]));
	map.setZoom(14);
	var roadtype=$('#road').val();
	var cnt=cntrl-1;
	//alert("hiiii"+cntrl);
	//alert(maplist);
	var b="b";
	var a="a";
	if(cntrl==0){
		//alert(busstoplist);
		if(busstoplist[cntrl][7]==6){
			$('#bus_stops tr').last().after('<tr id="'+y+'"><td class="seq">'+y+'</td><td class="busstopid" id="busstopid'+y+'" style="display: none;">uniquebusstop'+busstoplist[(cntrl)][1]+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+busstoplist[(cntrl)][6]+'</td><td>  <input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"> </td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td><a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+busstoplist[(cntrl)][1]+','+z+','+busstoplist[(cntrl)][8]+',\''+busstoplist[(cntrl)][6]+'\')">Add Platform</a></td> <td></td></td><td></td></tr>');
		}else{
			$('#bus_stops tr').last().after('<tr id="'+y+'"><td class="seq">'+y+'</td><td class="busstopid" id="busstopid'+y+'" style="display: none;">uniquebusstop'+busstoplist[(cntrl)][1]+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+busstoplist[(cntrl)][6]+'</td><td>  <input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"> </td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td></td><td></td></tr>');	
		}  
		tempbuusstop.push(busstoplist[(cntrl)][1]);
		$('#fare'+y).prop('checked', true);
		  document.getElementById("fare"+y).disabled = true;
		  $('#substage'+y).prop('checked', false);
		  document.getElementById("substage"+y).disabled = true;
		  cntrl++;
		  reset_seq();
		  //arrange_footer();
		  y++;z++;z++;
		  plot_route();
	}else{
		//alert("in else");
		var wp = [];
		var points=maplist;
		var path=points[cntrl-1][14];
    	path=path.replace(/@/g, '"');
    	path=path.replace(/\*/g, ',');
    	path=JSON.parse(path);
		for(var i=0;i<path.waypoints.length;i++){
			wp[i] = {'location': new google.maps.LatLng(path.waypoints[i][0], path.waypoints[i][1]),'stopover':false }
		}
		
		if(points[cntrl-1][15]=="GOOGLE"){
			////////////////////////google plot code//////////////////////
			
		ren[cnt] = new google.maps.DirectionsRenderer( {'suppressMarkers': true ,'preserveViewport': true,'draggable':true ,polylineOptions: {strokeColor: "blue" ,strokeOpacity: 2.0,strokeWeight: 6}} );	
		//alert("hiii");
		google.maps.event.addListener(ren[cnt], 'directions_changed',
				  function() {
			var strwaydata=save_waypoints(cnt);
	    	 //alert(strwaydata);
	    	strwaydata=strwaydata.replace(/"/g, '@');
	    	strwaydata=strwaydata.replace(/,/g, '*');
					//alert(cnt+"======="+strwaydata);
	    	var tempdis=0;
	    	var temptm=0;
					$('#polyp'+(cnt+2)).val(ren[cnt].getDirections().routes[0].overview_polyline);
					$('#editpath'+(cnt+2)).val(strwaydata);
					$('#dkm'+(cnt+2)).html(parseFloat(ren[cnt].getDirections().routes[0].legs[0].distance.value)/1000);
	  				$('#dm'+(cnt+21)).html(parseFloat(ren[cnt].getDirections().routes[0].legs[0].distance.value));
	  				$('#tm'+(cnt+2)).html(parseFloat(ren[cnt].getDirections().routes[0].legs[0].duration.text.replace(" min","").replace("s","")));
	  				$('#ts'+(cnt+2)).html(parseFloat(ren[cnt].getDirections().routes[0].legs[0].duration.value));
	  				$('#tdg'+(cnt+2)).html(parseFloat(ren[cnt].getDirections().routes[0].legs[0].distance.value)/1000);
	  				$('#tt'+(cnt+2)).html(parseFloat(ren[cnt].getDirections().routes[0].legs[0].duration.text.replace(" min","").replace("s","")));
	  				
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
	  				//alert(temptm+"====="+tempdis)

				
		});
		ren[cnt].setMap(map);
		
		ser = new google.maps.DirectionsService();
//alert("google");
		ser.route({ 'origin': new google.maps.LatLng(path.start.lat,path.start.lng), 'destination':  new google.maps.LatLng(path.end.lat,path.end.lng),'waypoints': wp, 'travelMode': google.maps.DirectionsTravelMode.DRIVING, 'unitSystem': google.maps.UnitSystem.METRIC},function(res,sts) {
			console.log(cnt+"===============>"+sts);
			if(sts=='OK') {
				console.log("res.routes[0].legs[0].distance.value"+res.routes[0].legs[0].distance.value);
				if(busstoplist[cntrl][7]==6){
					$('#bus_stops tr').last().after('<tr id="'+y+'"><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+busstoplist[cntrl][1]+'uniquebusstop</td><td class="cstage"></td><td  class="busstopname" id="busstopname'+y+'">'+busstoplist[cntrl][6]+'</td><td>  <input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"> </td><td style="display: none;"><select  class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td></td>    <td><a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+busstoplist[cntrl][1]+','+z+','+busstoplist[cntrl][8]+',\''+busstoplist[(cntrl)][6]+'\')">Add Platform</a></td>     <td><button type="button" id="a'+y+'" class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\',\''+busstoplist[cntrl][1]+'\')">Remove</button></td></tr>');	
				}else{
					$('#bus_stops tr').last().after('<tr id="'+y+'"><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+busstoplist[cntrl][1]+'uniquebusstop</td><td class="cstage"></td><td  class="busstopname" id="busstopname'+y+'">'+busstoplist[cntrl][6]+'</td><td>  <input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"> </td><td style="display: none;"><select  class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td></td><td><button type="button" id="a'+y+'" class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\',\''+busstoplist[cntrl][1]+'\')">Remove</button></td></tr>');
				}
				if(busstoplist[cntrl][4]=="Y"){
          		  //alert("hiii");
          		  $('#fare'+y).prop('checked', true);
          	  }
          	if(busstoplist[cntrl][5]=="Y"){
          		  //alert("hiii");
          		  $('#substage'+y).prop('checked', true);
          	  }
          	
				tempbuusstop.push(busstoplist[(cntrl)][1]);
				$('#bus_stops_map tr').last().before('<tr id="b'+y+'"><td colspan="7">'+maplist[cntrl-1][16]+' To '+maplist[cntrl-1][17]+'</td><td class="removefrmstop" style="display: none;">unique'+maplist[cntrl-1][1]+'unique</td><td class="removetostop" style="display: none;">unique'+maplist[cntrl-1][2]+'unique</td></tr>');
				$('#bus_stops_map tr').last().before('<tr id="a'+y+'"><td class="dkm" id="dkm'+y+'">'+maplist[cntrl-1][7]/1000+'</td><td style="display: none;"  class="dm" id="dm'+y+'">'+maplist[cntrl-1][7]+'</td><td  class="tm" id="tm'+y+'">'+ Math.round(maplist[cntrl-1][10]/60)+'</td><td style="display: none;"  class="ts" id="ts'+y+'">'+maplist[cntrl-1][10]+'</td><td><select  class="road_type" id="road_type'+y+'">'+roadtype+'</select></td><td  class="tdg" id="tdg'+y+'">'+(maplist[cntrl-1][5]/1000)+'</td><td  class="tdv"  id="tdv'+y+'">'+maplist[cntrl-1][6]+'</td><td class="tt"  id="tt'+y+'">'+ Math.round(maplist[cntrl-1][10]/60)+'</td><td><input type="text" size="5" maxlength="6" class="tds" id="tds'+y+'" value="'+maplist[cntrl-1][18]+'"></td><td><input type="text" size="5" maxlength="6" class="tts" id="tts'+y+'" value="'+maplist[cntrl-1][19]+'" onKeyUp="isInteger(this)"></td><td><input type="hidden" class="polyp"  id="polyp'+y+'" value="'+maplist[cntrl-1][13]+'"></td><td><input type="hidden" class="editpath" id="editpath'+y+'" value="'+maplist[cntrl-1][14]+'"></td> <td><input type="hidden" class="start_bus_stop_id"   id="start_bus_stop_id'+y+'" value="'+maplist[cntrl-1][1]+'"></td><td><input type="hidden" class="end_bus_stop_id"  id="end_bus_stop_id'+y+'" value="'+maplist[cntrl-1][2]+'"></td><td style="display: none;">'+maplist[cntrl-1][2]+'</td><td><input type="hidden" class="route_type"  id="route_type'+y+'" value="GOOGLE"></td><td class="removefrmstop" style="display: none;">unique'+maplist[cntrl-1][1]+'unique</td><td class="removetostop" style="display: none;">unique'+maplist[cntrl-1][2]+'unique</td></tr>');
				//$('#maptotdistm').html((   parseFloat($('#maptotdistm').html())    +   (maplist[7]/1000)).toFixed(2));
		       // $('#maptottimesectm').html(( parseInt($('#maptottimesectm').html())+  Math.round((maplist[10]/60) )) );
				$("#road_type"+y+" option[value='"+maplist[cntrl-1][4].trim()+"']").prop('selected', true);
				drArrcnt++;
	                  var a=  ren[cnt].setDirections(res);
	                
	                  drArr[cntrl-1]=ren[cnt];
	                  drsArr[cntrl]=res;
	                  var strwaydata=save_waypoints(cnt);
	             	 
	             	strwaydata=strwaydata.replace(/"/g, '@');
	             	strwaydata=strwaydata.replace(/,/g, '*');
	             	cntrl++;
	             	//alert(cntrl);
	             	reset_seq();
	            	//alert(busstoplist[cntrl][4]);
	            	
	             //	arrange_footer();
	             	y++;z++;z++;
	             	plot_route();
	                    } else {
	                    directionResult.push(null);
	                    //cntrl--;
		             	//alert(cntrl);
	                    reset_seq();
	                  //  arrange_footer();
	                    setTimeout(function(){plot_route();}, 800);
		             	
	                }
		});
		//arrpos++;
			/////////////////////// google plot code end/////////////////
		}else{
			////////////////////////connector///////////////////////////
			if(busstoplist[cntrl][7]==6){
				$('#bus_stops tr').last().after('<tr id="'+y+'"><td class="seq">'+y+'</td><td  class="busstopid" id="busstopid'+y+'" style="display: none;">uniquebusstop'+busstoplist[cntrl][1]+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+busstoplist[cntrl][6]+'</td><td>  <input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"> </td> </td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td><a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+busstoplist[cntrl][1]+','+z+','+busstoplist[cntrl][8]+',\''+busstoplist[(cntrl)][6]+'\')">Add Platform</a></td><td><button type="button" id="a'+y+'" class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\',\''+busstoplist[cntrl][1]+'\')">Remove</button></td></tr>');
			}else{
				$('#bus_stops tr').last().after('<tr id="'+y+'"><td class="seq">'+y+'</td><td  class="busstopid" id="busstopid'+y+'" style="display: none;">uniquebusstop'+busstoplist[cntrl][1]+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+busstoplist[cntrl][6]+'</td><td>  <input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"> </td> </td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td><td><button type="button" id="a'+y+'" class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\',\''+busstoplist[cntrl][1]+'\')">Remove</button></td></tr>');	
			}
			
			$('#bus_stops_map tr').last().before('<tr id="b'+y+'"><td colspan="7">'+maplist[cntrl-1][16]+' To '+maplist[cntrl-1][17]+'</td><td class="removefrmstop" style="display: none;">unique'+maplist[cntrl-1][1]+'unique</td><td class="removetostop" style="display: none;">unique'+maplist[cntrl-1][2]+'unique</td></tr>');
			//alert("point type"+busstoplist[7]);
			tempbuusstop.push(busstoplist[(cntrl)][1]);
			if(busstoplist[cntrl][7]==2){
				 $('#fare'+y).prop('checked', false);
				 $('#substage'+y).prop('checked', false);
				 document.getElementById("fare"+y).disabled = true;
				 document.getElementById("substage"+y).disabled = true;
			}
			if(busstoplist[cntrl][4]=="Y"){
        		  //alert("hiii");
        		  $('#fare'+y).prop('checked', true);
        	  }
        	if(busstoplist[cntrl][5]=="Y"){
        		  //alert("hiii");
        		  $('#substage'+y).prop('checked', true);
        	  }
			
			//$('#bus_stops_map tr').last().before('<tr id='+a+y+'><td id="dkm'+y+'">'+maplist[7]/1000+'</td><td style="display: none;" id="dm'+y+'">'+maplist[7]+'</td><td id="tm'+y+'">'+maplist[10]/60+'</td><td style="display: none;" id="ts'+y+'">'+maplist[10]+'</td><td><select id="road_type'+y+'">'+roadtype+'</select></td><td id="tdg'+y+'">'+maplist[5]+'</td><td id="tdv'+y+'">'+maplist[6]/1000+'</td><td id="tt'+y+'">'+maplist[10]/60+'</td><td><input type="textbox" id="tds'+y+'"></td><td><input type="textbox" class="tts" id="tts'+y+'"></td><td><input type="text" id="polyp'+y+'" value=""></td><td><input type="hidden" id="start_bus_stop_id'+y+'" value="'+maplist[1]+'"></td><td><input type="hidden" id="end_bus_stop_id'+y+'" value="'+maplist[2]+'"></td><td style="display: none;">'+maplist[2]+'</td><td><input type="hidden" id="route_type'+y+'" value="GOOGLE"></td></tr>');
			$('#bus_stops_map tr').last().before('<tr id="a'+y+'"><td class="dkm" id="dkm'+y+'">'+maplist[cntrl-1][7]/1000+'</td><td style="display: none;"  class="dm" id="dm'+y+'">'+maplist[cntrl-1][7]+'</td><td  class="tm" id="tm'+y+'">'+ Math.round(maplist[cntrl-1][10]/60)+'</td><td style="display: none;"  class="ts" id="ts'+y+'">'+maplist[cntrl-1][10]+'</td><td><select  class="road_type" id="road_type'+y+'">'+roadtype+'</select></td><td  class="tdg" id="tdg'+y+'">'+(maplist[cntrl-1][5])+'</td><td  class="tdv"  id="tdv'+y+'">'+maplist[cntrl-1][6]/1000+'</td><td class="tt"  id="tt'+y+'">'+  Math.round(maplist[cntrl-1][10]/60) +'</td><td><input type="textbox" class="tds" size="5"  maxlength="6" id="tds'+y+'" value="'+maplist[cntrl-1][18]+'"></td><td><input type="textbox" size="5" maxlength="6" class="tts" id="tts'+y+'" value="'+maplist[cntrl-1][19]+'" onKeyUp="isInteger(this)"></td><td><input type="hidden" class="polyp"  id="polyp'+y+'" value="'+maplist[cntrl-1][13]+'"></td><td><input type="hidden" class="editpath" id="editpath'+y+'" value="'+maplist[cntrl-1][14]+'"></td> <td><input type="hidden" class="start_bus_stop_id"   id="start_bus_stop_id'+y+'" value="'+maplist[cntrl-1][1]+'"></td><td><input type="hidden" class="end_bus_stop_id"  id="end_bus_stop_id'+y+'" value="'+maplist[cntrl-1][2]+'"></td><td style="display: none;">'+maplist[cntrl-1][2]+'</td><td><input type="hidden" class="route_type"  id="route_type'+y+'" value="TRIMAX"></td><td class="removefrmstop" style="display: none;">unique'+maplist[cntrl-1][1]+'unique</td><td class="removetostop" style="display: none;">unique'+maplist[cntrl-1][2]+'unique</td></tr>');
			$('#maptotdistm').html((   parseFloat($('#maptotdistm').html())    +   (maplist[cntrl-1][7]/1000)).toFixed(2));
	        $('#maptottimesectm').html(( parseInt($('#maptottimesectm').html())+  Math.round((maplist[cntrl-1][10]/60)) ) );
			var flightPlanCoordinates = [
			                             new google.maps.LatLng(path.start.lat,path.start.lng),
			                             new google.maps.LatLng(path.end.lat,path.end.lng)

	  	  	                           ];
			polyline[cnt] = new google.maps.Polyline({
		        path: flightPlanCoordinates,
		        geodesic: true,
		        strokeColor: 'blue',
		        strokeOpacity: 2.0,
		        strokeWeight: 6
		       
		    });
			polyline[cnt].setMap( map ); 
			//drArr[arrpos]=ren[cnt];
			// alert("hiii"+arrpos);
	        drArr[cntrl-1]=polyline[cnt];
	       // drsArr[arrpos]=res;
			drArrcnt++;
			cntrl++;
			reset_seq();
			
			y++;z++;z++;
         	//alert(cntrl);
         	plot_route();
////////////////////////connector end ///////////////////////////
		}
	}

	
}

//var arrpos=0;
/*function setroute(os,cnt,type, busstoplist, maplist, showmap,arrpos)
{
	cntrl=cnt;
	//alert(cntrl);
	var roadtype=$('#road').val();
	tempbuusstop.push(busstoplist[1]);
	
	var b="b";
	var a="a";
	//alert(os.toSource());
	if(showmap==0){
	var wp = [];
	for(var i=0;i<os.waypoints.length;i++){
		wp[i] = {'location': new google.maps.LatLng(os.waypoints[i][0], os.waypoints[i][1]),'stopover':false }
	}
	//alert(maplist[14]);
	if(type=="GOOGLE"){	
		//alert("heyyy"+type)
		if(busstoplist[7]==6){
			$('#bus_stops tr').last().after('<tr id="'+y+'"><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">'+busstoplist[1]+'</td><td class="cstage"></td><td  class="busstopname" id="busstopname'+y+'">'+busstoplist[6]+'</td><td>  <input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"> </td><td style="display: none;"><select  class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td></td>    <td><a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+busstoplist[1]+','+z+','+busstoplist[8]+')">Add Platform</a></td>     <td><button type="button" id="a'+y+'" class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\',\''+busstoplist[1]+'\')">Remove</button></td></tr>');	
		}else{
			$('#bus_stops tr').last().after('<tr id="'+y+'"><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">'+busstoplist[1]+'</td><td class="cstage"></td><td  class="busstopname" id="busstopname'+y+'">'+busstoplist[6]+'</td><td>  <input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"> </td><td style="display: none;"><select  class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td></td><td><button type="button" id="a'+y+'" class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\',\''+busstoplist[1]+'\')">Remove</button></td></tr>');
		}
		$('#bus_stops_map tr').last().before('<tr id='+b+y+'><td colspan="7">'+maplist[16]+' To '+maplist[17]+'</td><td class="removefrmstop" style="display: none;">'+maplist[1]+'</td><td class="removetostop" style="display: none;">'+maplist[2]+'</td></tr>');
		$('#bus_stops_map tr').last().before('<tr id='+a+y+'><td class="dkm" id="dkm'+y+'">'+maplist[7]/1000+'</td><td style="display: none;"  class="dm" id="dm'+y+'">'+maplist[7]+'</td><td  class="tm" id="tm'+y+'">'+ Math.round(maplist[10]/60)+'</td><td style="display: none;"  class="ts" id="ts'+y+'">'+maplist[10]+'</td><td><select  class="road_type" id="road_type'+y+'">'+roadtype+'</select></td><td  class="tdg" id="tdg'+y+'">'+(maplist[5]/1000)+'</td><td  class="tdv"  id="tdv'+y+'">'+maplist[6]+'</td><td class="tt"  id="tt'+y+'">'+ Math.round(maplist[10]/60)+'</td><td><input type="text" size="5" class="tds" id="tds'+y+'"></td><td><input type="text" size="5" class="tts" id="tts'+y+'"></td><td><input type="hidden" class="polyp"  id="polyp'+y+'" value="'+maplist[13]+'"></td><td><input type="hidden" class="editpath" id="editpath'+y+'" value="'+maplist[14]+'"></td> <td><input type="hidden" class="start_bus_stop_id"   id="start_bus_stop_id'+y+'" value="'+maplist[1]+'"></td><td><input type="hidden" class="end_bus_stop_id"  id="end_bus_stop_id'+y+'" value="'+maplist[2]+'"></td><td style="display: none;">'+maplist[2]+'</td><td><input type="hidden" class="route_type"  id="route_type'+y+'" value="GOOGLE"></td><td class="removefrmstop" style="display: none;">'+maplist[1]+'</td><td class="removetostop" style="display: none;">'+maplist[2]+'</td></tr>');
		//$('#maptotdistm').html((   parseFloat($('#maptotdistm').html())    +   (maplist[7]/1000)).toFixed(2));
       // $('#maptottimesectm').html(( parseInt($('#maptottimesectm').html())+  Math.round((maplist[10]/60) )) );
		drArrcnt++;
	ren[cnt] = new google.maps.DirectionsRenderer( {'suppressMarkers': true ,'preserveViewport': true,'draggable':true ,polylineOptions: {strokeColor: "blue" ,strokeOpacity: 2.0,strokeWeight: 6}} );	
	//alert("hiii");
	google.maps.event.addListener(ren[cnt], 'directions_changed',
			  function() {
		var strwaydata=save_waypoints(cnt);
    	 //alert(strwaydata);
    	strwaydata=strwaydata.replace(/"/g, '@');
    	strwaydata=strwaydata.replace(/,/g, '*');
				//alert(cnt+"======="+strwaydata);
    	var tempdis=0;
    	var temptm=0;
				$('#polyp'+(cnt+1)).val(ren[cnt].getDirections().routes[0].overview_polyline);
				$('#editpath'+(cnt+1)).val(strwaydata);
				$('#dkm'+(cnt+1)).html(parseFloat(ren[cnt].getDirections().routes[0].legs[0].distance.value.replace(" km","")));
  				$('#dm'+(cnt+1)).html(parseFloat(ren[cnt].getDirections().routes[0].legs[0].distance.value));
  				$('#tm'+(cnt+1)).html(parseFloat(ren[cnt].getDirections().routes[0].legs[0].duration.text.replace(" min","").replace("s","")));
  				$('#ts'+(cnt+1)).html(parseFloat(ren[cnt].getDirections().routes[0].legs[0].duration.value));
  				$('#tdg'+(cnt+1)).html(parseFloat(ren[cnt].getDirections().routes[0].legs[0].distance.value.replace(" km","")));
  				$('#tt'+(cnt+1)).html(parseFloat(ren[cnt].getDirections().routes[0].legs[0].duration.text.replace(" min","").replace("s","")));
  				
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
  				//alert(temptm+"====="+tempdis)

			
	});
	ren[cnt].setMap(map);
	
	ser = new google.maps.DirectionsService();

	ser.route({ 'origin': new google.maps.LatLng(os.start.lat,os.start.lng), 'destination':  new google.maps.LatLng(os.end.lat,os.end.lng),'waypoints': wp, 'travelMode': google.maps.DirectionsTravelMode.DRIVING},function(res,sts) {
		console.log(cnt+"===============>"+sts);
		if(sts=='OK') {
                  var a=  ren[cnt].setDirections(res);
                
                  drArr[arrpos]=ren[cnt];
                  drsArr[arrpos]=res;
                  var strwaydata=save_waypoints(cnt);
             	 
             	strwaydata=strwaydata.replace(/"/g, '@');
             	strwaydata=strwaydata.replace(/,/g, '*');
             	
             	//alert(cntrl);
                  
                    } else {
                    directionResult.push(null);
                }
	});
	//arrpos++;
	}else{
		//alert("hiii"+type+"===="+os.start.lat+"=========="+os.start.lng)
		$('#bus_stops tr').last().after('<tr id="'+y+'"><td class="seq">'+y+'</td><td  class="busstopid" id="busstopid'+y+'" style="display: none;">'+busstoplist[1]+'</td><td class="cstage"></td><td id="busstopname'+y+'">'+busstoplist[6]+'</td><td>  <input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"> </td> </td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td></td><td><button type="button" id="a'+y+'" class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\',\''+busstoplist[1]+'\')">Remove</button></td></tr>');
		$('#bus_stops_map tr').last().before('<tr id='+b+y+'><td colspan="7">'+maplist[16]+' To '+maplist[17]+'</td><td class="removefrmstop" style="display: none;">'+maplist[1]+'</td><td class="removetostop" style="display: none;">'+maplist[2]+'</td></tr>');
		//alert("point type"+busstoplist[7]);
		if(busstoplist[7]==2){
			 $('#fare'+y).prop('checked', false);
			 $('#substage'+y).prop('checked', false);
			 document.getElementById("fare"+y).disabled = true;
			 document.getElementById("substage"+y).disabled = true;
		}
		
		//$('#bus_stops_map tr').last().before('<tr id='+a+y+'><td id="dkm'+y+'">'+maplist[7]/1000+'</td><td style="display: none;" id="dm'+y+'">'+maplist[7]+'</td><td id="tm'+y+'">'+maplist[10]/60+'</td><td style="display: none;" id="ts'+y+'">'+maplist[10]+'</td><td><select id="road_type'+y+'">'+roadtype+'</select></td><td id="tdg'+y+'">'+maplist[5]+'</td><td id="tdv'+y+'">'+maplist[6]/1000+'</td><td id="tt'+y+'">'+maplist[10]/60+'</td><td><input type="textbox" id="tds'+y+'"></td><td><input type="textbox" class="tts" id="tts'+y+'"></td><td><input type="text" id="polyp'+y+'" value=""></td><td><input type="hidden" id="start_bus_stop_id'+y+'" value="'+maplist[1]+'"></td><td><input type="hidden" id="end_bus_stop_id'+y+'" value="'+maplist[2]+'"></td><td style="display: none;">'+maplist[2]+'</td><td><input type="hidden" id="route_type'+y+'" value="GOOGLE"></td></tr>');
		$('#bus_stops_map tr').last().before('<tr id='+a+y+'><td class="dkm" id="dkm'+y+'">'+maplist[7]/1000+'</td><td style="display: none;"  class="dm" id="dm'+y+'">'+maplist[7]+'</td><td  class="tm" id="tm'+y+'">'+ Math.round(maplist[10]/60)+'</td><td style="display: none;"  class="ts" id="ts'+y+'">'+maplist[10]+'</td><td><select  class="road_type" id="road_type'+y+'">'+roadtype+'</select></td><td  class="tdg" id="tdg'+y+'">'+(maplist[5])+'</td><td  class="tdv"  id="tdv'+y+'">'+maplist[6]/1000+'</td><td class="tt"  id="tt'+y+'">'+  Math.round(maplist[10]/60) +'</td><td><input type="textbox" class="tds" size="5" id="tds'+y+'"></td><td><input type="textbox" size="5" class="tts" id="tts'+y+'"></td><td><input type="hidden" class="polyp"  id="polyp'+y+'" value="'+maplist[13]+'"></td><td><input type="hidden" class="editpath" id="editpath'+y+'" value="'+maplist[14]+'"></td> <td><input type="hidden" class="start_bus_stop_id"   id="start_bus_stop_id'+y+'" value="'+maplist[1]+'"></td><td><input type="hidden" class="end_bus_stop_id"  id="end_bus_stop_id'+y+'" value="'+maplist[2]+'"></td><td style="display: none;">'+maplist[2]+'</td><td><input type="hidden" class="route_type"  id="route_type'+y+'" value="TRIMAX"></td><td class="removefrmstop" style="display: none;">'+maplist[1]+'</td><td class="removetostop" style="display: none;">'+maplist[2]+'</td></tr>');
		$('#maptotdistm').html((   parseFloat($('#maptotdistm').html())    +   (maplist[7]/1000)).toFixed(2));
        $('#maptottimesectm').html(( parseInt($('#maptottimesectm').html())+  Math.round((maplist[10]/60)) ) );
		var flightPlanCoordinates = [
		                             new google.maps.LatLng(os.start.lat,os.start.lng),
		                             new google.maps.LatLng(os.end.lat,os.end.lng)

  	  	                           ];
		polyline[cnt] = new google.maps.Polyline({
	        path: flightPlanCoordinates,
	        geodesic: true,
	        strokeColor: 'blue',
	        strokeOpacity: 2.0,
	        strokeWeight: 6
	       
	    });
		polyline[cnt].setMap( map ); 
		//drArr[arrpos]=ren[cnt];
		// alert("hiii"+arrpos);
        drArr[arrpos]=polyline[cnt];
       // drsArr[arrpos]=res;
		drArrcnt++;
	}
	}else{
		if(busstoplist[7]==6){
			$('#bus_stops tr').last().after('<tr id="'+y+'"><td class="seq">'+y+'</td><td class="busstopid" id="busstopid'+y+'" style="display: none;">'+busstoplist[1]+'</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+busstoplist[6]+'</td><td>  <input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"> </td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td><a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+busstoplist[1]+','+z+','+busstoplist[8]+')">Add Platform</a></td> <td></td></td><td></td></tr>');
		}else{
			$('#bus_stops tr').last().after('<tr id="'+y+'"><td class="seq">'+y+'</td><td class="busstopid" id="busstopid'+y+'" style="display: none;">'+busstoplist[1]+'</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+busstoplist[6]+'</td><td>  <input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"> </td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td></td><td></td></tr>');	
		}  
		$('#fare'+y).prop('checked', true);
		  document.getElementById("fare"+y).disabled = true;
		  $('#substage'+y).prop('checked', false);
		  document.getElementById("substage"+y).disabled = true;
	}
	
	if(busstoplist[4]=="Y"){
		  //alert("hiii");
		  $('#fare'+y).prop('checked', true);
	  }
	if(busstoplist[5]=="Y"){
		  //alert("hiii");
		  $('#substage'+y).prop('checked', true);
	  }
	y++;z++;z++;
	check_stage1();
	setTimeout(function(){}, 1000);

}*/

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

function reset_seq(){
	//alert("in seq");
	var seqno=1;
	$(".seq").each(function() {
		$(this).html(seqno);
		seqno++;
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

function disableremovebutton(){
	for(var con=0;con<=y;con++){
    	$('#a'+(con)).prop("disabled", true); 
    }
}
function enableremovebutton(){
	for(var con=0;con<=y;con++){
    	$('#a'+(con)).prop("disabled", false); 
    }
}