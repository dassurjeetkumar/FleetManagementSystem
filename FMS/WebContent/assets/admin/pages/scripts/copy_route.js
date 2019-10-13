var busString;
var map, ren, ser;
var ren = [];
var polyline = [];
var directionResult = [];
var directionsDisplay;
var wayA = [];
var wayB = [];
var tempk, tempa;
var drArr = [];
var drsArr = [];
var pArr = [];
var drArrcnt = 0;
var pArrcnt = 0;
var temp = [];
var tempbusstop = [];
var tempnum = [];
var tempid = "";
var flag = "";
var image;
var z = 1;
var y = 1;
var v = 1;
var flightPathpath;
var encodeString;
var b = "b";
var a = "a";
var fare = "fare";
var substage1 = "substage";
var prestopid = "";
var tempbuusstop = [];
var frmbusid = "";
var tobusid = "";
var cntrl = 0;
var copycntrl=0;
var markers = [];
var waitflag = 0;

$(function() {

	var mapOptions = {
		zoom : 15,
		center : new google.maps.LatLng("12.97928309", "77.57205963")
	};
	map = new google.maps.Map(document.getElementById('gmap_marker'),
			mapOptions);
	$("#gmap_marker").css("height", "500px");
	google.maps.event.trigger(map, "resize");
	size = new google.maps.Size(0, 0);
	image = {
		url : 'assets/images/green.png',
		size : new google.maps.Size(1, 1),
		origin : new google.maps.Point(0, 0),
		anchor : new google.maps.Point(0, 32)
	};

});
function initialize(result, center, zoom2,colo) {
	center = center.split(",");
//	map.setCenter(new google.maps.LatLng(center[0], center[1]));
	var zoom1 = 0;
	zoom1 = zoom2;
	var myLatlng = new google.maps.LatLng(center[0], center[1]);

	busString = result;
	var infowindow = new google.maps.InfoWindow({
		content : "aaaa"
	});

	var locations = busString.split("@@@");

	var source = "", dest = "", name = "", devicecode = "", desc = "", nameka = "", loc = "", status = "", icon = "", area = "", landmark = "", icon1 = "", mcolor = "", dir = "", id = "", pointtype = "", farestage = "", busstationid = "";
	for (i = 0; i < locations.length; i++) {
		var dloc = locations[i].split("|");
		for ( var v = 0; v < dloc.length; v++) {

			if (dloc[v] === "null" || dloc[v] == "null") {
				dloc[v] = " ";
			}
			if (dloc[v] === "undefined" || dloc[v] == "undefined") {
				dloc[v] = " ";
			}
			if (dloc[v] == "") {
				dloc[v] = " ";
			}

		}
		name = dloc[0];
		source = dloc[1];
		dest = dloc[2];
		id = dloc[3];
		devicecode = dloc[4];
		nameka = dloc[5];
		desc = dloc[6];
		status = dloc[7];
		loc = dloc[8];
		dir = dloc[10];
		landmark = dloc[11];
		area = dloc[12];
		pointtype = dloc[14];
		farestage = dloc[15];
		busstationid = dloc[16]
		substage = dloc[17]
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
		if (dloc[13] == 'Y') {
			icon = "blue";
		}
		if (dloc[14] == "2") {
			icon = "brown";
		}
		if (dloc[14] == "13") {
			icon = "grey";
		}
		if(dloc[14]=="18"){
			  icon = "chart";  
		  }
		if(colo=="col"){
			icon = "Bus-Icon";  
		}  
		icon1 = "assets/images/" + icon + ".png";
		marker = new google.maps.Marker({
			position : new google.maps.LatLng(source, dest),
			map : map,
			animation : google.maps.Animation.DROP,
			icon : new google.maps.MarkerImage(icon1),
			title : name + " (" + $('<div/>').html(nameka).text() + ")"
					+ " Towards:" + dir,
			id : id,
			source : source,
			dest : dest,
			name : name,
			devicecode : devicecode,
			desc : desc,
			nameka : nameka,
			loc : loc,
			status : status,
			dir : dir,
			landmark : landmark,
			area : area,
			pointtype : pointtype,
			farestage : farestage,
			busstationid : busstationid,
			substage : substage
		});
		if(colo!="col"){
	    	  markers.push(marker);
			}  
		infowindow = new google.maps.InfoWindow();

		

		   google.maps.event.addListener(marker, 'click', (function(marker, i) {
		          return function() {
		        	 console.log("tempbuusstop"+tempbuusstop);
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
		        	/* if(z==1 || z==2){
		       		  if(marker.pointtype=="2"){
		       			  alert("Source Stop or Destination Stop should be Bus Stop");
		       			  z--;v--;y--;
		       			  return false;
		       		  }
		       	  }*/
		        	 
		        	 if(z%2==1){
		       		  frmbusid=marker.id;
		       		 
		       	  }
		        	 
		        	 if(z%2==0){
		        		 console.log("z value is"+z);
		        		 if(z>=5){
		        			 /*console.log(tempbuusstop);
		        			 console.log(tempbuusstop.indexOf(parseInt(frmbusid))+"============="+frmbusid);
		        			 console.log(tempbuusstop.indexOf(parseInt(marker.id))+"============="+marker.id);
		            		  if(tempbuusstop.indexOf(parseInt(frmbusid))!=-1 && (tempbuusstop.indexOf(parseInt(marker.id))!=-1)){
		            			  alert("Invalid Link Selection");
		            			  frmbusid="";
		            			  z=z-1;v=v-1;
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
		       			  return false;
		       		  }
		       	  }
		        	 var platform=0;
		        	if(marker.pointtype==6){
		        		 platform=1;
		        	 }
		        	 
		        	 if(z==1){
		       		 v=z;
		       		  var check="";
		       		$(".busstopid").each(function() {
		       			var compfact='uniquebusstop'+marker.id+'uniquebusstop';
		       			if($(this).html()==compfact){
		       				addbusstoprow=1;
		       			  
		       			}
		       			});
		       		if(addbusstoprow==0){
		       			if(platform==1){ 
		       				$('#bus_stops tr').last().after('<tr id="'+z+'"><td class="seq">'+z+'</td><td style="display: none;" id="busstopid'+z+'" class="busstopid" >uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage">0</td><td id="busstopname'+z+'" class="busstopname">'+marker.name+'</td><td> <input type="checkbox" class="fare" id="fare'+z+'" name="fare'+z+'" value="fare" onClick="checkstage('+z+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td> <input type="checkbox" class="substage" id="substage'+z+'" name="substage'+z+'" value="substage"onClick="checkstage('+z+',\''+substage1+'\',\''+fare+'\');"> </td><td style="display: none;"><select id="status'+z+'" class="status" ><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td><a data-toggle="modal" class="btn white" role="button" href="#routeaddmodel" onclick="addstop('+marker.id+','+z+','+marker.busstationid+')">Add Platform</a></td><td></td><td><a data-toggle="modal" class="btn white disaddroute" role="button" href="#routeaddmodel" onclick="AddCopyRoute('+marker.id+')">Add Route</a></td></tr>');
		       				 }else{ 
		       			    $('#bus_stops tr').last().after('<tr id="'+z+'"><td class="seq">'+z+'</td><td style="display: none;" id="busstopid'+z+'" class="busstopid">uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage">0</td><td id="busstopname'+z+'" class="busstopname">'+marker.name+'</td><td> <input type="checkbox" class="fare" id="fare'+z+'" name="fare'+z+'" value="fare" onClick="checkstage('+z+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td> <input type="checkbox" class="substage" id="substage'+z+'" name="substage'+z+'" value="substage" onClick="checkstage('+z+',\''+substage1+'\',\''+fare+'\')"> </td><td style="display: none;"><select class="status" id="status'+z+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td><td></td><td><a data-toggle="modal" class="btn white disaddroute" role="button" href="#routeaddmodel" onclick="AddCopyRoute('+marker.id+')">Add Route</a></td></tr>'); 
		       			    }
		       			tempbuusstop.push(parseInt(busstoplist[(copycntrl)][1]));
		        	 }
		       		  if(marker.farestage=="Y"){
		       			  $('#fare'+z).prop('checked', true);
		       		  }
		       		  if(marker.substage=="Y"){
		       			  $('#substage'+z).prop('checked', true);
		       		  }
		       	  }
		        	 /*if(z==3){
		       		  if($('#start_point_id').val()!=marker.id){
		       			  alert("Please Select Proper First Stop");
		       			  z--;v--;
		       			  return false;  
		       		  }
		       		  
		       	  }*/
		       	  
		       	  
		       	 /* if($('#end_point_id').val()==marker.id){
		   			 flag="comp";
		   			
		   		  }*/
		       	  
		       	  tempid=marker.id;
		        	  
		        	  if (wayA.length == wayB.length) {
		        		   var foundstopid=0;
		        		   var uniqueNames1 = [];
		     				var names1=tempbuusstop;
		     				$.each(names1, function(i, el){
		     				    if($.inArray(el, uniqueNames1) === -1) uniqueNames1.push(el);
		     				});
		     				tempbuusstop=uniqueNames1;
		        		  for(var s=0;s<tempbuusstop.length;s++){
		        			  if(tempbuusstop[s]==marker.id){
		        				  foundstopid=1;
		            		  }
		        		  }
		        		  if(foundstopid==0){
		        			  // return false;
		        		  }
		        		  if($("#end_point_id").val()==marker.id){
		        			 // return false;
		         		  }
		        		  
		          		 
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
		        		    
		        		    
		        		    $(".busstopid").each(function() {
	      	        			var compfact='uniquebusstop'+marker.id+'uniquebusstop';
	      	         			if($(this).html()==compfact){
	      	         				addbusstoprow=1;
	      	         			}
	      	         			});
	      	        		
	      	        		var findbusval=0;
	      	        		
    		               	$('#bus_stops tr td').each(function(){
    		               		
    		                       if($(this).text() == 'uniquebusstop' + prestopid +'uniquebusstop'){
    		                    	 //  console.log($(this).text() +"=========" +'uniquebusstop' + prestopid);
    		                    	   findbusval=1;
    		                       }
    		                       
    		                   });
    		              // 	console.log("'uniquebusstop' + prestopid "+'uniquebusstop' + prestopid +"========="+findbusval);
	      	         		if(addbusstoprow==0){
	      	         			if(findbusval==1){
	      	         				if(platform==1){
        	      	        			$("#bus_stops tr").eq(1).after('<tr id='+y+'><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" '+check+' onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" '+check+' onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td>  <a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+marker.id+','+z+','+marker.busstationid+')">Add Platform</a></td><td><button type="button" id="a'+y+'"class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\','+marker.id+')">Remove</button></td><td><a data-toggle="modal" class="btn white disaddroute" role="button" href="#routeaddmodel" onclick="AddCopyRoute('+marker.id+')">Add Route</a></td></tr>');	
        	      	        		}else{
        	      	        			$("#bus_stops tr").eq(1).after('<tr id='+y+'><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" '+check+' onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" '+check+' onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td><td><button type="button" id="a'+y+'"class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\','+marker.id+')">Remove</button></td><td><a data-toggle="modal" class="btn white disaddroute" role="button" href="#routeaddmodel" onclick="AddCopyRoute('+marker.id+')">Add Route</a></td></tr>');
        	      	        		}
	      	         			}else{
	      	         				if(platform==1){
	      	         					$("#bus_stops tr").eq(1).after('<tr id='+y+'><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" '+check+' onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" '+check+' onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td>  <a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+marker.id+','+z+','+marker.busstationid+')">Add Platform</a></td><td><button type="button" id="a'+y+'"class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\','+marker.id+')">Remove</button></td><td><a data-toggle="modal" class="btn white disaddroute" role="button" href="#routeaddmodel" onclick="AddCopyRoute('+marker.id+')">Add Route</a></td></tr>');	
        	      	        		}else{
        	      	        			$("#bus_stops tr").eq(1).after('<tr id='+y+'><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" '+check+' onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" '+check+' onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td><td><button type="button" id="a'+y+'"class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\','+marker.id+')">Remove</button></td><td><a data-toggle="modal" class="btn white disaddroute" role="button" href="#routeaddmodel" onclick="AddCopyRoute('+marker.id+')">Add Route</a></td></tr>');
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
	      	         		
	      	         		addbusstoprow=0;
		        		    
		        		    $('#selectbusstopname').html("<blink> <font color='blue'>Selected From Stop=>"+marker.name+"</font></blink>");
		        		    prestopid=marker.id;
		        	        } else {
		        	        	$(".busstopid").each(function() {
		        	       			
		        	       			if($(this).html()!=marker.id){
		        	       				 tempbuusstop.push(parseInt(marker.id));
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
		        	  	
		        	        		if(z>=1){
		        	        			v=(((z-2)/2)+1);
		        	        			var check=""; 
		        	        			$(".busstopid").each(function() {
		        	               			//alert($(this).html());
		        	               			var compfact='uniquebusstop'+marker.id+'uniquebusstop';
		        	               			if($(this).html()==compfact){
		        	               				addbusstoprow=1;
		        	               			}
		        	               			});
		        	        			var findbusval=0;
		        		               	$('#bus_stops tr td').each(function(){
		        		                       if($(this).text() == 'uniquebusstop' + prestopid+'uniquebusstop' ){
		        		                    	   findbusval=1;
		        		                       }
		        		                   });
		        		               	
		        	               		if(addbusstoprow==0){
		        	               			if(findbusval==1){
		        	               				if(platform==1){  		
				        	        				$("#bus_stops tr:contains('uniquebusstop" + prestopid + "uniquebusstop')").last().after('<tr id='+y+'><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" '+check+' onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" '+check+' onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td>  <td><a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+marker.id+','+z+','+marker.busstationid+')">Add Platform</a></td></td><td><button type="button" id="a'+y+'" class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\','+marker.id+')">Remove</button></td><td><a data-toggle="modal" class="btn white disaddroute" role="button" href="#routeaddmodel" onclick="AddCopyRoute('+marker.id+')">Add Route</a></td></tr>');	
				        	        			}else{
				        	        				//alert(prestopid);
				        	        				$("#bus_stops tr:contains('uniquebusstop" + prestopid + "uniquebusstop')").last().after('<tr id='+y+'><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" '+check+' onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" '+check+' onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td><td><button type="button" id="a'+y+'" class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\','+marker.id+')">Remove</button></td><td><a data-toggle="modal" class="btn white disaddroute" role="button" href="#routeaddmodel" onclick="AddCopyRoute('+marker.id+')">Add Route</a></td></tr>');	
				        	        			}
			        		               	}else{
			        		               		if(platform==1){  		
			        		               			$("#bus_stops tr:not(:contains('uniquebusstop" + prestopid + "uniquebusstop'))").eq(1).after('<tr id='+y+'><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" '+check+' onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" '+check+' onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td>  <td><a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+marker.id+','+z+','+marker.busstationid+')">Add Platform</a></td></td><td><button type="button" id="a'+y+'" class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\','+marker.id+')">Remove</button></td><td><a data-toggle="modal" class="btn white disaddroute" role="button" href="#routeaddmodel" onclick="AddCopyRoute('+marker.id+')">Add Route</a></td></tr>');	
				        	        			}else{
				        	        				//alert(prestopid);
				        	        				$("#bus_stops tr:not(:contains('uniquebusstop" + prestopid + "uniquebusstop'))").eq(1).after('<tr id='+y+'><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" '+check+' onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" '+check+' onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td><td><button type="button" id="a'+y+'" class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\','+marker.id+')">Remove</button></td><td><a data-toggle="modal" class="btn white disaddroute" role="button" href="#routeaddmodel" onclick="AddCopyRoute('+marker.id+')">Add Route</a></td></tr>');	
				        	        			}
			        		               	}
		        	        			
		        	               		
		        	        			if(marker.pointtype==2){
		       	        				// $('#fare'+y).prop('checked', false);
		       	        				// $('#substage'+y).prop('checked', false);
		       	        				// document.getElementById("fare"+y).disabled = true;
		          	        			//  document.getElementById("substage"+y).disabled = true;
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
                       if($(this).text() == 'unique' + prestopid+'unique' ){
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
		        	      	        	if(z>=1){
		        	      	        		v=(((z-2)/2)+1);
		        	      	        		var check="";
		        	      	        		$(".busstopid").each(function() {
		        	      	        			var compfact='uniquebusstop'+marker.id+'uniquebusstop';
		        	      	         			if($(this).html()==compfact){
		        	      	         				addbusstoprow=1;
		        	      	         			}
		        	      	         			});
		        	      	        		
		        	      	        		var findbusval=0;
		        	      	        		//console.log("'uniquebusstop' + prestopid "+'uniquebusstop' + prestopid );
			        		               	$('#bus_stops tr td').each(function(){
			        		                       if($(this).text() == 'uniquebusstop' + prestopid +'uniquebusstop'){
			        		                    	   findbusval=1;
			        		                       }
			        		                   });
		        	      	         		if(addbusstoprow==0){
		        	      	         			if(findbusval==1){
		        	      	         				if(platform==1){
				        	      	        			$("#bus_stops tr:contains('uniquebusstop" + prestopid + "uniquebusstop')").last().after('<tr id='+y+'><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" '+check+' onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" '+check+' onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td>  <a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+marker.id+','+z+','+marker.busstationid+')">Add Platform</a></td><td><button type="button" id="a'+y+'"class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\','+marker.id+')">Remove</button></td><td><a data-toggle="modal" class="btn white disaddroute" role="button" href="#routeaddmodel" onclick="AddCopyRoute('+marker.id+')">Add Route</a></td></tr>');	
				        	      	        		}else{
				        	      	        			$("#bus_stops tr:contains('uniquebusstop" + prestopid + "uniquebusstop')").last().after('<tr id='+y+'><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" '+check+' onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" '+check+' onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td><td><button type="button" id="a'+y+'"class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\','+marker.id+')">Remove</button></td><td><a data-toggle="modal" class="btn white disaddroute" role="button" href="#routeaddmodel" onclick="AddCopyRoute('+marker.id+')">Add Route</a></td></tr>');
				        	      	        		}
		        	      	         			}else{
		        	      	         				if(platform==1){
		        	      	         					$("#bus_stops tr:not(:contains('uniquebusstop" + prestopid + "uniquebusstop'))").eq(1).after('<tr id='+y+'><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" '+check+' onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" '+check+' onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td>  <a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+marker.id+','+z+','+marker.busstationid+')">Add Platform</a></td><td><button type="button" id="a'+y+'"class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\','+marker.id+')">Remove</button></td><td><a data-toggle="modal" class="btn white disaddroute" role="button" href="#routeaddmodel" onclick="AddCopyRoute('+marker.id+')">Add Route</a></td></tr>');	
				        	      	        		}else{
				        	      	        			$("#bus_stops tr:not(:contains('uniquebusstop" + prestopid + "uniquebusstop'))").eq(1).after('<tr id='+y+'><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+marker.id+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+marker.name+'</td><td><input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" '+check+' onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"></td><td><input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" '+check+' onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"></td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td><td><button type="button" id="a'+y+'"class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\','+marker.id+')">Remove</button></td><td><a data-toggle="modal" class="btn white disaddroute" role="button" href="#routeaddmodel" onclick="AddCopyRoute('+marker.id+')">Add Route</a></td></tr>');
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
		        	        	        	if(y<=0){
		        	        	        		
		        	        	        	ren[cntrl] = new google.maps.DirectionsRenderer( {'suppressMarkers': true ,'preserveViewport': true,'draggable':true ,polylineOptions: {strokeColor: "blue" ,strokeOpacity: 2.0,strokeWeight: 3}} );	
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
		        	                	                    waitflag=0;
		        	                	                  //  var newString = decodePath('encodedPath':"iienA{yjxM?THbB?P@V?lD@^D|ABv@F~@JXJxAJr@Ld@Tn@h@|@LNFHB@`@f@`@f@BBx@fArAxBN^Jb@?BDb@AZIb@Mb@Ud@KPeDjEEFKLKJA@EFUTGFcAx@{DfDYZOTORUZ?@OTwBzCQXQXOXITEHUp@CHKf@ADUz@CLKj@SjAAJGh@Gn@KhBQlBEb@CTKj@Q~@I\\Sp@_@nAgAbDGR]~@u@pBK\\m@jBITWt@o@lBOj@GPa@hAMb@Mb@WbA_@dBSv@_@xAENWbAIb@Ib@EVAJIh@?DEZAHEl@Ch@AJAVA\\C|@@XAZC\\Qj@CHOTKPIJGFED[HSFMBe@Rg@ROJMLQZS`@Uh@Sh@Id@Ef@E|@");
		        	                	                    ren[cntrl].setDirections(res);	
		        	                	                    drArr.push(ren[cntrl]);
		        	                	                    drsArr.push(res);
		        	                	                   // alert(y);
		        	                	                    if(y>0){
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
		        	                	                            if($(this).text() == 'unique' + prestopid+'unique' ){
		        	                	                            	findval=1;
		        	                	                            }
		        	                	                        });
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
		        	                	                    	
		        	                	                    	var coords = google.maps.geometry.encoding.decodePath(res.routes[0].overview_polyline);
		        	                	                        callback(coords);
		        	                	                      //  alert("hii"+"==>"+wayA[wayA.length-1].id);
		        	                	                    	$('#route_name').val(wayA[wayA.length-1].name+' To '+wayB[wayB.length-1].name);
		        	                	                    	$('#start_point_id').val(wayA[wayA.length-1].id);
		        	                	                    	$('#end_point_id').val(wayB[wayB.length-1].id);
		        	                	                    }
		        	                	                    y++;
		        	                	                    
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
		  				//$('#maptotdistm').html((parseFloat($('#maptotdistm').html())+parseFloat(ren[cntr].getDirections().routes[0].legs[0].distance.text.replace(" km",""))).toFixed(2));
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
var sval = "";
/*function resetMap(j, type, rowid) {
	sval = sval + rowid + ",";
	$('#skipval').val(sval);
	if (type == "G") {
		drArr[j].setMap(null);
	}
	if (type == "C") {
		pArr[j].setMap(null);
	}
	var tempdis = 0;
	var temptm = 0;
	$(".dkm").each(function() {
		tempdis = tempdis + parseFloat($(this).html());
	});
	tempdis = Math.round(tempdis);
	$('#maptotdistm').html(tempdis);
	$(".tm").each(function() {
		temptm = temptm + parseFloat($(this).html());
	});
	$('#maptottimesectm').html(temptm);
	var index = tempbuusstop.indexOf($('#busstopid' + rowid).html());
	tempbuusstop.splice(index, 1);

	$('#' + rowid + '').remove();
	$('#a' + rowid + '').remove();
	$('#b' + rowid + '').remove();
	tempnum.pop();
	$('#a' + (tempnum[(tempnum.length - 1)])).prop("disabled", false);
	flag = "";
	arrange_footer();

}*/

function resetMap(j,type,rowid, busstopid) {
    sval=sval+rowid+",";
	$('#skipval').val(sval);
	//alert(j+"========="+(parseInt(j)+1));
	   if(type=="G"){
		   //alert(drArr[j]);
		   //alert(drArr[parseInt(j)+1]);
		   if(typeof drArr[j]!== "undefined"){
			   drArr[j].setMap(null);   
		   }
		   if(typeof drArr[parseInt(j)-1]!== "undefined"){
			   drArr[parseInt(j)-1].setMap(null);  
			  // drArr[parseInt(j)+2].setMap(null);  
		   }
    	     
    	   
       }
    	if(type=="C"){
    		
    		if(typeof pArr[j]!== "undefined"){
    			pArr[j].setMap(null);   
 		   }
    		if(typeof pArr[parseint(j)-1]!== "undefined"){
    			pArr[parseint(j)-1].setMap(null);   
 		   }
    	   
    	   
       }
    	
    	 $('#maptotdistm').html((parseFloat($('#maptotdistm').html())-parseFloat($('#dkm'+rowid).html())).toFixed(2));
         $('#maptottimesectm').html( ((parseFloat($('#maptottimesectm').html()))-parseFloat($('#tm'+rowid).html())).toFixed(2));
      
        	 var index = tempbuusstop.indexOf(parseInt($('#busstopid'+rowid).html().replace("uniquebusstop","").replace("uniquebusstop","")));
        	// alert(index);
        	 tempbuusstop.splice(index, 1);
        	// alert("myArray"+myArray);
        	 $("#bus_stops_map tr td.removefrmstop:contains('unique" + busstopid  + "unique')").each(function() {
        		    if ($(this).text() == 'unique'+busstopid+'unique') {
        		        $(this).parent().remove();
        		    }  
        		});
        	 $("#bus_stops_map tr td.removetostop:contains('unique" + busstopid  + "unique')").each(function() {
     		    if ($(this).text() == 'unique'+busstopid+'unique') {
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
       
        
}

var route_points;
function callback(coords) {
	var i;
	route_points = "";
	route_points = route_points + "LINESTRING(";
	for (i = 0; i < coords.length; i++) {
		route_points = route_points + "" + coords[i].lat() + " "
				+ coords[i].lng() + ",";
	}
	route_points = route_points.substring(0, (route_points.length - 1))
	route_points = route_points + ")";
	$('#route_string').val(route_points);
}
var data = {};
function save_waypoints(cnt) {
	var w = [], wp;
	var rleg = ren[cnt].directions.routes[0].legs[0];
	data.start = {
		'lat' : rleg.start_location.lat(),
		'lng' : rleg.start_location.lng()
	};
	data.end = {
		'lat' : rleg.end_location.lat(),
		'lng' : rleg.end_location.lng()
	};
	var wp = rleg.via_waypoints;
	for ( var i = 0; i < wp.length; i++) {
		w[i] = [ wp[i].lat(), wp[i].lng() ]
	}
	;
	data.waypoints = w;

	var str = JSON.stringify(data)
	return str;
	
}

function reset_seq() {
	var seqno = 1;
	$(".seq").each(function() {
		$(this).html(seqno);
		seqno++;
	});
	
	$(".disaddroute").each(function() {
		$(this).hide();
		
	});
	
}

function check_stage1() {
	var farecnt = 0;
	var farearr = [];
	var stage = 0;
	$(".fare").each(function() {

		if ($(this).is(':checked')) {
			$('.cstage').html(farecnt);
			farearr.push("Y");
			} else {
			$('.cstage').html(farecnt);
			farearr.push("N");
		}

	});
	$(".cstage").each(function() {

		$(this).html(farecnt);
		if (farearr[stage] == "Y") {
			farecnt++;
		}

		stage++;

	});

}

function arrange_footer() {
	var tempdis = 0;
	var temptm = 0;
	$(".dkm").each(function() {
		tempdis = tempdis + parseFloat($(this).html());
	});
	tempdis = tempdis.toFixed(2);
	$('#maptotdistm').html(tempdis);
	$(".tm").each(function() {
		temptm = temptm + parseFloat($(this).html());
	});
	$('#maptottimesectm').html(temptm);
}

function clearMarkers() {
	setAllMap(null);
}
function setAllMap(map) {
	for ( var i = 0; i < markers.length; i++) {
		markers[i].setMap(map);
	}
	getStopsReLoad("12", "77");
}

var edit_arr = [];
var busstoplist;
var maplist;

function plot_edit_route(result) {
	edit_arr = JSON.parse(result);
	var result = JSON.parse(result);
	copycntrl=0;
	for ( var s = 0; s < result.routeplatrellen; s++) {
		var temp = "";
		temp = temp + result.routeplatrel[s][1] + "|"
				+ result.routeplatrel[s][3] + "|" + result.routeplatrel[s][4]
				+ "|" + result.routeplatrel[s][5] + "|"
				+ result.routeplatrel[s][6];
		platverarr.push(temp);
		$('#platformdetail tr')
				.last()
				.after(
						'<tr id="1"><td  style="display: none;">'
								+ result.routeplatrel[s][1]
								+ ""
								+ result.routeplatrel[s][3]
								+ ""
								+ result.routeplatrel[s][4]
								+ ""
								+ result.routeplatrel[s][5]
								+ ""
								+ result.routeplatrel[s][6]
								+ ""
								+ '</td><td><input type="hidden" class="busplatformid" id="busplatformid'
								+ platformcounter
								+ '" value="'
								+ result.routeplatrel[s][1]
								+ '">    </td>              <td><input type="hidden" class="depotp" id="depotp'
								+ platformcounter
								+ '" value="'
								+ result.routeplatrel[s][2]
								+ '">    </td><td><input type="hidden" class="floorp" id="floorp'
								+ platformcounter
								+ '" value="'
								+ result.routeplatrel[s][3]
								+ '"></td>    </td><td><input type="hidden" class="bayp" id="bayp'
								+ platformcounter
								+ '" value="'
								+ result.routeplatrel[s][4]
								+ '"></td>    </td><td><input type="hidden" class="platformp" id="platformp'
								+ platformcounter
								+ '" value="'
								+ result.routeplatrel[s][5]
								+ '"></td>    </td><td><input type="hidden" class="servicep" id="servicep'
								+ platformcounter + '" value="'
								+ result.routeplatrel[s][6] + '"></td></tr>');
		tableplatarr.push({
			id : result.routeplatrel[s][1],
			name : result.routeplatrel[s][1] + "" 
					+ result.routeplatrel[s][5] + ""
					+ result.routeplatrel[s][6] + "",
			planame : result.routeplatrel[s][8],
			sername : result.routeplatrel[s][7],
			depot : result.routeplatrel[s][2],
			floor : result.routeplatrel[s][3],
			bay : result.routeplatrel[s][4],
			platform : result.routeplatrel[s][5],
			service : result.routeplatrel[s][6]
		});
		uniqueid++;
	}
	busstoplist = edit_arr.routepoint;
	maplist = edit_arr.routemap;
	$("#pageLoader").show();
	plot_route();
}

function plot_route(){
	$('#loadercontent').html("");
	var percentload = Math.round((copycntrl / edit_arr.routepointlen) * 100);
	$('#loadercontent')
			.html(
					"<img src='assets/images/loader1.gif' align='top' style='margin-left:100px;'/> Please Wait ("
							+ percentload + "%)");
	if(edit_arr.routepointlen==copycntrl){
		check_stage1();
		$("#pageLoader").hide();
		arrange_footer();
		//alert("hiii");
		return;
		
	}
	map.setCenter(new google.maps.LatLng(busstoplist[copycntrl][9],busstoplist[copycntrl][10]));
	map.setZoom(14);
	var roadtype=$('#road').val();
	var cnt=copycntrl-1;
	//alert("hiiii"+cntrl);
	//alert(maplist);
	var b="b";
	var a="a";
	console.log("stop==="+busstoplist[(copycntrl)][1]);
	tempbuusstop.push(parseInt(busstoplist[(copycntrl)][1]));
	if(copycntrl==0){
		//alert(busstoplist);
		if(busstoplist[copycntrl][7]==6){
			$('#bus_stops tr').last().after('<tr id="'+y+'"><td class="seq">'+y+'</td><td class="busstopid" id="busstopid'+y+'" style="display: none;">uniquebusstop'+busstoplist[(copycntrl)][1]+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+busstoplist[(copycntrl)][6]+'</td><td>  <input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"> </td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td><a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+busstoplist[(copycntrl)][1]+','+z+','+busstoplist[(copycntrl)][8]+')">Add Platform</a></td> <td><button type="button" id="a'+y+'" class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\',\''+busstoplist[copycntrl][1]+'\')">Remove</button></td><td><a data-toggle="modal" class="btn white disaddroute" role="button" href="#routeaddmodel" onclick="AddCopyRoute('+busstoplist[(copycntrl)][1]+')">Add Route</a></td></tr>');
		}else{
			$('#bus_stops tr').last().after('<tr id="'+y+'"><td class="seq">'+y+'</td><td class="busstopid" id="busstopid'+y+'" style="display: none;">uniquebusstop'+busstoplist[(copycntrl)][1]+'uniquebusstop</td><td class="cstage"></td><td class="busstopname" id="busstopname'+y+'">'+busstoplist[(copycntrl)][6]+'</td><td>  <input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"> </td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td><td><button type="button" id="a'+y+'" class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\',\''+busstoplist[copycntrl][1]+'\')">Remove</button></td><td><a data-toggle="modal" class="btn white disaddroute" role="button" href="#routeaddmodel" onclick="AddCopyRoute('+busstoplist[(copycntrl)][1]+')">Add Route</a></td></tr>');	
		}  
		tempbuusstop.push(parseInt(busstoplist[(copycntrl)][1]));
		$('#fare'+y).prop('checked', true);
		 // document.getElementById("fare"+y).disabled = true;
		  $('#substage'+y).prop('checked', false);
		 // document.getElementById("substage"+y).disabled = true;
		  copycntrl++;
		  reset_seq();
		  //arrange_footer();
		  y++;z++;z++;
		  plot_route();
	}else{
		//alert("in else");
		var wp = [];
		var points=maplist;
		var path=points[copycntrl-1][14];
    	path=path.replace(/@/g, '"');
    	path=path.replace(/\*/g, ',');
    	path=JSON.parse(path);
		for(var i=0;i<path.waypoints.length;i++){
			wp[i] = {'location': new google.maps.LatLng(path.waypoints[i][0], path.waypoints[i][1]),'stopover':false }
		}
		
		if(points[copycntrl-1][15]=="GOOGLE"){
			////////////////////////google plot code//////////////////////
			
		ren[cnt] = new google.maps.DirectionsRenderer( {'suppressMarkers': true ,'preserveViewport': true,'draggable':true ,polylineOptions: {strokeColor: "red" ,strokeOpacity: 2.0,strokeWeight: 6}} );	
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
		/***********************************/
		wayA.push(new google.maps.Marker({
		  	  draggable: true,	
	          position: new google.maps.LatLng(path.start.lat, path.start.lng)
	         /* map: map,
	          icon: image,
	          source :marker.source,
			  dest :marker.dest,
			  status :marker.status,
			  name : marker.name,
			  id :marker.id,
			  pointtype :marker.pointtype*/
			  
	        }));  
		wayB.push(new google.maps.Marker({
		  	  draggable: true,	
	          position: new google.maps.LatLng(path.end.lat, path.end.lng)
	          /*map: map,
	          icon: image,
	          source :marker.source,
			  dest :marker.dest,
			  status :marker.status,
			  name : marker.name,
			  id :marker.id,
			  pointtype :marker.pointtype*/
			  
	        }));  
		/***********************************/
		ser.route({ 'origin': new google.maps.LatLng(path.start.lat,path.start.lng), 'destination':  new google.maps.LatLng(path.end.lat,path.end.lng),'waypoints': wp, 'travelMode': google.maps.DirectionsTravelMode.DRIVING},function(res,sts) {
			console.log(cnt+"===============>"+sts);
			if(sts=='OK') {
				
				if(busstoplist[copycntrl][7]==6){
					$('#bus_stops tr').last().after('<tr id="'+y+'"><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+busstoplist[copycntrl][1]+'uniquebusstop</td><td class="cstage"></td><td  class="busstopname" id="busstopname'+y+'">'+busstoplist[copycntrl][6]+'</td><td>  <input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"> </td><td style="display: none;"><select  class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td><a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+busstoplist[copycntrl][1]+','+z+','+busstoplist[copycntrl][8]+')">Add Platform</a></td>     <td><button type="button" id="a'+y+'" class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\',\''+busstoplist[copycntrl][1]+'\')">Remove</button></td><td><a data-toggle="modal" class="btn white disaddroute" role="button" href="#routeaddmodel" onclick="AddCopyRoute('+busstoplist[(copycntrl)][1]+')">Add Route</a></td></tr>');	
				}else{
					$('#bus_stops tr').last().after('<tr id="'+y+'"><td class="seq">'+y+'</td><td id="busstopid'+y+'" class="busstopid" style="display: none;">uniquebusstop'+busstoplist[copycntrl][1]+'uniquebusstop</td><td class="cstage"></td><td  class="busstopname" id="busstopname'+y+'">'+busstoplist[copycntrl][6]+'</td><td>  <input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"> </td><td style="display: none;"><select  class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td><td><button type="button" id="a'+y+'" class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\',\''+busstoplist[copycntrl][1]+'\')">Remove</button></td><td><a data-toggle="modal" class="btn white disaddroute" role="button" href="#routeaddmodel" onclick="AddCopyRoute('+busstoplist[(copycntrl)][1]+')">Add Route</a></td></tr>');
				}
				tempbuusstop.push(parseInt(busstoplist[(copycntrl)][1]));
				if(busstoplist[copycntrl][4]=="Y"){
          		  //alert("hiii");
          		  $('#fare'+y).prop('checked', true);
          	  }
          	if(busstoplist[copycntrl][5]=="Y"){
          		  //alert("hiii");
          		  $('#substage'+y).prop('checked', true);
          	  }
          	
				tempbuusstop.push(parseInt(busstoplist[(copycntrl)][1]));
				$('#bus_stops_map tr').last().before('<tr id="b'+y+'"><td colspan="7">'+maplist[copycntrl-1][16]+' To '+maplist[copycntrl-1][17]+'</td><td class="removefrmstop" style="display: none;">unique'+maplist[copycntrl-1][1]+'unique</td><td class="removetostop" style="display: none;">unique'+maplist[copycntrl-1][2]+'unique</td></tr>');
				$('#bus_stops_map tr').last().before('<tr id="a'+y+'"><td class="dkm" id="dkm'+y+'">'+maplist[copycntrl-1][7]/1000+'</td><td style="display: none;"  class="dm" id="dm'+y+'">'+maplist[copycntrl-1][7]+'</td><td  class="tm" id="tm'+y+'">'+ Math.round(maplist[copycntrl-1][10]/60)+'</td><td style="display: none;"  class="ts" id="ts'+y+'">'+maplist[copycntrl-1][10]+'</td><td><select  class="road_type" id="road_type'+y+'">'+roadtype+'</select></td><td  class="tdg" id="tdg'+y+'">'+(maplist[copycntrl-1][5]/1000)+'</td><td  class="tdv"  id="tdv'+y+'">'+maplist[copycntrl-1][6]+'</td><td class="tt"  id="tt'+y+'">'+ Math.round(maplist[copycntrl-1][10]/60)+'</td><td><input type="text" size="5" maxlength="6" class="tds" id="tds'+y+'" value="'+maplist[copycntrl-1][18]+'"></td><td><input type="text" size="5" maxlength="6" class="tts" id="tts'+y+'" value="'+maplist[copycntrl-1][19]+'" onKeyUp="isInteger(this)"></td><td><input type="hidden" class="polyp"  id="polyp'+y+'" value="'+maplist[copycntrl-1][13]+'"></td><td><input type="hidden" class="editpath" id="editpath'+y+'" value="'+maplist[copycntrl-1][14]+'"></td> <td><input type="hidden" class="start_bus_stop_id"   id="start_bus_stop_id'+y+'" value="'+maplist[copycntrl-1][1]+'"></td><td><input type="hidden" class="end_bus_stop_id"  id="end_bus_stop_id'+y+'" value="'+maplist[copycntrl-1][2]+'"></td><td style="display: none;">'+maplist[copycntrl-1][2]+'</td><td><input type="hidden" class="route_type"  id="route_type'+y+'" value="GOOGLE"></td><td class="removefrmstop" style="display: none;">unique'+maplist[copycntrl-1][1]+'unique</td><td class="removetostop" style="display: none;">unique'+maplist[copycntrl-1][2]+'unique</td></tr>');
				//$('#maptotdistm').html((   parseFloat($('#maptotdistm').html())    +   (maplist[7]/1000)).toFixed(2));
		       // $('#maptottimesectm').html(( parseInt($('#maptottimesectm').html())+  Math.round((maplist[10]/60) )) );
				drArrcnt++;
	                  var a=  ren[cnt].setDirections(res);
	                
	                  drArr[cntrl-1]=ren[cnt];
	                  drsArr[cntrl]=res;
	                  var strwaydata=save_waypoints(cnt);
	             	 
	             	strwaydata=strwaydata.replace(/"/g, '@');
	             	strwaydata=strwaydata.replace(/,/g, '*');
	             	cntrl++;
	             	copycntrl++;
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
			if(busstoplist[copycntrl][7]==6){
				$('#bus_stops tr').last().after('<tr id="'+y+'"><td class="seq">'+y+'</td><td  class="busstopid" id="busstopid'+y+'" style="display: none;">uniquebusstop'+busstoplist[copycntrl][1]+'uniquebusstop</td><td class="cstage"></td><td id="busstopname'+y+'" class="busstopname">'+busstoplist[copycntrl][6]+'</td><td>  <input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"> </td> </td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td><a data-toggle="modal" class="btn white" role="button" href="#myModal1" onclick="addstop('+busstoplist[copycntrl][1]+','+z+','+busstoplist[copycntrl][8]+')">Add Platform</a></td><td><button type="button" id="a'+y+'" class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\',\''+busstoplist[copycntrl][1]+'\')">Remove</button></td><td><a data-toggle="modal" class="btn white disaddroute" role="button" href="#routeaddmodel" onclick="AddCopyRoute('+busstoplist[(copycntrl)][1]+')">Add Route</a></td></tr>');
			}else{
				$('#bus_stops tr').last().after('<tr id="'+y+'"><td class="seq">'+y+'</td><td  class="busstopid" id="busstopid'+y+'" style="display: none;">uniquebusstop'+busstoplist[copycntrl][1]+'uniquebusstop</td><td class="cstage"></td><td id="busstopname'+y+'" class="busstopname">'+busstoplist[copycntrl][6]+'</td><td>  <input type="checkbox" class="fare" id="fare'+y+'" name="fare'+y+'" value="fare" onClick="checkstage('+y+',\''+fare+'\',\''+substage1+'\');check_stage1()"> </td><td>  <input type="checkbox" class="substage" id="substage'+y+'" name="substage'+y+'" value="substage" onClick="checkstage('+y+',\''+substage1+'\',\''+fare+'\')"> </td> </td><td style="display: none;"><select class="status" id="status'+y+'"><option value="ACTIVE">ACTIVE</option><option value="INACTIVE">INACTIVE</option></select></td><td></td><td><button type="button" id="a'+y+'" class="btn dark" onclick="resetMap(\''+drArrcnt+'\',\'G\',\''+y+'\',\''+busstoplist[copycntrl][1]+'\')">Remove</button></td><td><a data-toggle="modal" class="btn white disaddroute" role="button" href="#routeaddmodel" onclick="AddCopyRoute('+busstoplist[(copycntrl)][1]+')">Add Route</a></td></tr>');	
			}
			
			$('#bus_stops_map tr').last().before('<tr id="b'+y+'"><td colspan="7">'+maplist[copycntrl-1][16]+' To '+maplist[copycntrl-1][17]+'</td><td class="removefrmstop" style="display: none;">unique'+maplist[copycntrl-1][1]+'unique</td><td class="removetostop" style="display: none;">unique'+maplist[copycntrl-1][2]+'unique</td></tr>');
			//alert("point type"+busstoplist[7]);
			tempbuusstop.push(parseInt(busstoplist[(copycntrl)][1]));
			if(busstoplist[copycntrl][7]==2){
				 $('#fare'+y).prop('checked', false);
				 $('#substage'+y).prop('checked', false);
				 document.getElementById("fare"+y).disabled = true;
				 document.getElementById("substage"+y).disabled = true;
			}
			if(busstoplist[copycntrl][4]=="Y"){
        		  //alert("hiii");
        		  $('#fare'+y).prop('checked', true);
        	  }
        	if(busstoplist[copycntrl][5]=="Y"){
        		  //alert("hiii");
        		  $('#substage'+y).prop('checked', true);
        	  }
			
			//$('#bus_stops_map tr').last().before('<tr id='+a+y+'><td id="dkm'+y+'">'+maplist[7]/1000+'</td><td style="display: none;" id="dm'+y+'">'+maplist[7]+'</td><td id="tm'+y+'">'+maplist[10]/60+'</td><td style="display: none;" id="ts'+y+'">'+maplist[10]+'</td><td><select id="road_type'+y+'">'+roadtype+'</select></td><td id="tdg'+y+'">'+maplist[5]+'</td><td id="tdv'+y+'">'+maplist[6]/1000+'</td><td id="tt'+y+'">'+maplist[10]/60+'</td><td><input type="textbox" id="tds'+y+'"></td><td><input type="textbox" class="tts" id="tts'+y+'"></td><td><input type="text" id="polyp'+y+'" value=""></td><td><input type="hidden" id="start_bus_stop_id'+y+'" value="'+maplist[1]+'"></td><td><input type="hidden" id="end_bus_stop_id'+y+'" value="'+maplist[2]+'"></td><td style="display: none;">'+maplist[2]+'</td><td><input type="hidden" id="route_type'+y+'" value="GOOGLE"></td></tr>');
			$('#bus_stops_map tr').last().before('<tr id="a'+y+'"><td class="dkm" id="dkm'+y+'">'+maplist[copycntrl-1][7]/1000+'</td><td style="display: none;"  class="dm" id="dm'+y+'">'+maplist[copycntrl-1][7]+'</td><td  class="tm" id="tm'+y+'">'+ Math.round(maplist[copycntrl-1][10]/60)+'</td><td style="display: none;"  class="ts" id="ts'+y+'">'+maplist[copycntrl-1][10]+'</td><td><select  class="road_type" id="road_type'+y+'">'+roadtype+'</select></td><td  class="tdg" id="tdg'+y+'">'+(maplist[copycntrl-1][5])+'</td><td  class="tdv"  id="tdv'+y+'">'+maplist[copycntrl-1][6]/1000+'</td><td class="tt"  id="tt'+y+'">'+  Math.round(maplist[copycntrl-1][10]/60) +'</td><td><input type="textbox" class="tds" size="5"  maxlength="6" id="tds'+y+'" value="'+maplist[copycntrl-1][18]+'"></td><td><input type="textbox" size="5" maxlength="6" class="tts" id="tts'+y+'" value="'+maplist[copycntrl-1][19]+'" onKeyUp="isInteger(this)"></td><td><input type="hidden" class="polyp"  id="polyp'+y+'" value="'+maplist[copycntrl-1][13]+'"></td><td><input type="hidden" class="editpath" id="editpath'+y+'" value="'+maplist[copycntrl-1][14]+'"></td> <td><input type="hidden" class="start_bus_stop_id"   id="start_bus_stop_id'+y+'" value="'+maplist[copycntrl-1][1]+'"></td><td><input type="hidden" class="end_bus_stop_id"  id="end_bus_stop_id'+y+'" value="'+maplist[copycntrl-1][2]+'"></td><td style="display: none;">'+maplist[copycntrl-1][2]+'</td><td><input type="hidden" class="route_type"  id="route_type'+y+'" value="TRIMAX"></td><td class="removefrmstop" style="display: none;">unique'+maplist[copycntrl-1][1]+'unique</td><td class="removetostop" style="display: none;">unique'+maplist[copycntrl-1][2]+'unique</td></tr>');
			$('#maptotdistm').html((   parseFloat($('#maptotdistm').html())    +   (maplist[copycntrl-1][7]/1000)).toFixed(2));
	        $('#maptottimesectm').html(( parseInt($('#maptottimesectm').html())+  Math.round((maplist[copycntrl-1][10]/60)) ) );
			var flightPlanCoordinates = [
			                             new google.maps.LatLng(path.start.lat,path.start.lng),
			                             new google.maps.LatLng(path.end.lat,path.end.lng)

	  	  	                           ];
			polyline[cnt] = new google.maps.Polyline({
		        path: flightPlanCoordinates,
		        geodesic: true,
		        strokeColor: 'red',
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
			copycntrl++;
			reset_seq();
			
			y++;z++;z++;
         	//alert(cntrl);
         	plot_route();
////////////////////////connector end ///////////////////////////
		}
	}
	var uniqueNames1 = [];
		var names1=tempbuusstop;
		$.each(names1, function(i, el){
		    if($.inArray(el, uniqueNames1) === -1) uniqueNames1.push(el);
		});
		tempbuusstop=uniqueNames1;
	console.log("wayA.length"+wayA.length);
}
