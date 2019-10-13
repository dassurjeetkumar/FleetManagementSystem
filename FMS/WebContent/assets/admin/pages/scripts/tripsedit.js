
var tripEditFunctions = function () {	
	function tripInitialFunctions() { 

		$().ready(function() {
			
			/*$("#origin0").inputmask("d/m/y", {
	            autoUnmask: true
	        });*/
		$("#origin0").autocomplete("", {
			open : function() {
				$('#div .ui-menu').width(2000);
			},
			url : PATH + '/GetDepot.action',
			minChars : 2,
			max : 50,
			multiple : false,
			autoFill : true,
			formatItem: function(data) {
                return data[1];
            },
            formatResult: function(data) {
                return data[1];
            }
		}).result(function(event, data) {
            if (data) {
                var idd = this.id + 'A0';
                document.getElementById(idd).value = data[0];
          
            }
        });
	});

		

	$().ready(function() {
		$("#dest0").autocomplete("", {
			url : PATH + '/GetDepot.action',
			minChars : 2,
			max : 50,
			multiple : false,
			autoFill : true,
			formatItem: function(data) {
                return data[1];
            },
            formatResult: function(data) {
                return data[1];
            }
		}).result(function(event, data) {
            if (data) {
                var idd = this.id + 'A0';
                document.getElementById(idd).value = data[0];
                getGroupingStops(this.id.substring(4));
            }
        });
	});

	$().ready(function() {
		$("#crewChangelocation0").autocomplete("", {
			url : PATH + '/GetDepot.action',
			minChars : 2,
			max : 50,
			multiple : false,
			autoFill : true,
			formatItem: function(data) {
                return data[1];
            },
            formatResult: function(data) {
                return data[1];
            }
		});
	});
	
	$().ready(function() {
		$("#nighttriplocation0").autocomplete("", {
			url : PATH + '/GetDepot.action',
			minChars : 2,
			max : 50,
			multiple : false,
			autoFill : true,
			formatItem: function(data) {
                return data[1];
            },
            formatResult: function(data) {
                return data[1];
            }
		});
	});
	
	$().ready(function() {
		$("#breaklocation0").autocomplete("", {
			url : PATH + '/GetDepot.action',
			minChars : 2,
			max : 50,
			multiple : false,
			autoFill : true,
			formatItem: function(data) {
                return data[1];
            },
            formatResult: function(data) {
                return data[1];
            }
		});
	});

	
	$().ready(function() {
		
		var value = document.getElementById('txtbk').value;
		var elementsId;
		for (i = 0; i < value; i++) {
			if(i==value-1){
				elementsId += '#dest'+i;
			}
			else{
				elementsId += '#dest'+i+',';
			}
		}
		$(elementsId).autocomplete("", {
			url : PATH + '/GetDepot.action',
			minChars : 2,
			max : 50,
			multiple : false,
			autoFill : true,
			formatItem: function(data) {
                return data[1];
            },
            formatResult: function(data) {
                return data[1];
            }
		}).result(function(event, data) {
            if (data) {
                var idd = this.id + 'A'+this.id.substring(4);
                document.getElementById(idd).value = data[0];
                getGroupingStops(this.id.substring(4));
            }
        });
		
		
		elementsId='';
		
		for (i = 0; i < value; i++) {
			if(i==value-1){
				elementsId += '#crewChangelocation'+i;
			}
			else{
				elementsId += '#crewChangelocation'+i+',';
			}
		}
		
		
		
		$(elementsId).autocomplete("", {
			url : PATH + '/GetDepot.action',
			minChars : 2,
			max : 50,
			multiple : false,
			autoFill : true,
			formatItem: function(data) {
                return data[1];
            },
            formatResult: function(data) {
                return data[1];
            }
		});
		
		elementsId='';
		
		for (i = 0; i < value; i++) {
			if(i==value-1){
				elementsId += '#nighttriplocation'+i;
			}
			else{
				elementsId += '#nighttriplocation'+i+',';
			}
		}
		
		
		
		$(elementsId).autocomplete("", {
			url : PATH + '/GetDepot.action',
			minChars : 2,
			max : 50,
			multiple : false,
			autoFill : true,
			formatItem: function(data) {
                return data[1];
            },
            formatResult: function(data) {
                return data[1];
            }
		});
		
		elementsId='';
		
		for (i = 0; i < value; i++) {
			if(i==value-1){
				elementsId += '#breaklocation'+i;
			}
			else{
				elementsId += '#breaklocation'+i+',';
			}
		}
		$(elementsId).autocomplete("", {
			url : PATH + '/GetDepot.action',
			minChars : 2,
			max : 50,
			multiple : false,
			autoFill : true,
			formatItem: function(data) {
                return data[1];
            },
            formatResult: function(data) {
                return data[1];
            }
		});
		
		/*elementsId='';
		
		for (i = 0; i < value; i++) {
			if(i==value-1){
				elementsId += '#fromtime'+i;
			}
			else{
				elementsId += '#fromtime'+i+',';
			}
		}
		
		$(elementsId).inputmask("mask", {
			"mask": "99:99"
        });
		elementsId='';
		for (i = 0; i < value; i++) {
			if(i==value-1){
				elementsId += '#totime'+i;
			}
			else{
				elementsId += '#totime'+i+',';
			}
		}
		
		$(elementsId).inputmask("mask", {
			"mask": "99:99"
        });
		
		elementsId='';
		for (i = 0; i < value; i++) {
			if(i==value-1){
				elementsId += '#breakduration'+i;
			}
			else{
				elementsId += '#breakduration'+i+',';
			}
		}
		
		$(elementsId).inputmask("mask", {
			"mask": "99:99"
        });*/
		
		$(".timepicker-24i").inputmask("mask", {
			"mask": "99:99",
			"greedy" : "false"
        });
		
		
	});
	
	
	
	/*$('.timepicker-24ii').timepicker({
		defaultTime : false,
        autoclose: true,
        minuteStep: 5,
        showSeconds: false,
        showInputs: true,
        showMeridian: false
      
    });

	$('.timepicker-24i').timepicker({
		defaultTime : '00:00',
        autoclose: true,
        minuteStep: 5,
        showSeconds: false,
        showInputs: true,
        showMeridian: false
      
    });*/
	
	setInitialelements();
	
	}
	
	return {
	    //main function to initiate the module
	    init: function () {
	    	tripInitialFunctions();
	    }
	};
}();


function setInitialelements(){
	/*var id='';
	var rows = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	
	for(cnt=0;cnt<rows;cnt++){
		var value = document.getElementById('routelength'+cnt).value;
		if(value == '' || value == null){
		document.getElementById('routelength'+cnt).value = '0.0';
		}
		var selectedroute = document.getElementById('selectedRoute'+cnt).value;
		if(cnt==0){
			getRoutes(selectedroute);
			//setroutedistance('triptable1','0','0');
		}
		else{
			//getDepotsByNo('triptable1','0',cnt);
			//setroutedistance('triptable1','0',cnt);
		}
		
		id=cnt;
	}
	
	var myElem = document.getElementById('triptable2');
	
	if(myElem !=null){
		var row1 = document.getElementById("triptable2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		var totalrows = parseInt(row1)+parseInt(rows);
		for(j=id;j<parseInt(totalrows)-1;j++){
			alert('j-----> '+j);
			var value = document.getElementById('routelength'+j).value;
			if(value == '' || value == null){
			document.getElementById('routelength'+j).value = '0.0';
			}
			var selectedroute = document.getElementById('selectedRoute'+j).value;
			if(j==0){
				getRoutes(selectedroute);
			}
			else{
				//getDepotsByNo('triptable2',id,j);
				//setroutedistance('triptable2',id,j);
			}
		}
	}
	document.getElementById("schkms1").value='0.0';
	document.getElementById("totalkms1").value='0.0';*/
}

function getRoutes(routeno){
	var startpoint = document.getElementById('origin0A0').value;
	var endpoint = document.getElementById('dest0A0').value;
	var serviceid=document.getElementById('service1_id').value;
	var trip_type_id=document.getElementById('trip0').value;
//alert("serviceid");
	//var value = $('#trip0 option:selected').text();
	var action ='GetRoute.action';
	/*if(value.toLowerCase().replace(" ", "").indexOf('regulartrip')!=-1 )
	{
		action ='GetRoute.action';
	}
	else{
	action = 'GetDeadRoute.action';
	}*/
	
	$.ajax({
        type: "POST",
        url: action,
        data: "startpoint="+startpoint+'&endpoint='+endpoint+'&serviceid='+serviceid+'&triptypeid='+trip_type_id,
        success: function(response){
        	
        	var arr = response.split(',');
        	 var i=0;
        	var select = $('#route_no0');
        	select.empty();
        	$('#route_no0').append('<option value="0">Select</option>'); 
        	for (i = 0; i < arr.length-1; i++) {
        		var splitresult = arr[i].split('@');
        		var dates = splitresult[2]+' to '+splitresult[3];
        		if(splitresult[0] == routeno ){
        			$('#route_no0').append('<option value="'+splitresult[0]+'" selected="selected"  class="tooltips" title="'+dates+'">'+splitresult[1]+'</option>');
        			
        		}
        		else{
        		$('#route_no0').append('<option value="'+splitresult[0]+'" class="tooltips" title="'+dates+'">'+splitresult[1]+'</option>');
        		}
        		setroutedistance('triptable1','0','0');
        		/*getRouteById('triptable1','0','1');*/
        	}
        	
    
        }
	});
}




function getDepotsByNo(tableid,firstid,id,selectedroute){
	var startpoint = document.getElementById('origin'+id+'A'+id).value;
	var endpoint = document.getElementById('dest'+id+'A'+id).value;
	var elementid = '#route_no'+id;
	var destpoint = document.getElementById('dest'+id).value;
	var serviceid=document.getElementById('service1_id').value;
	var trip_type_id=document.getElementById('trip'+id).value;
	if(isEmpty(destpoint)){
		document.getElementById('dest'+id+'A'+id).value = 0;
	}

	//var value = $('#trip'+id+' option:selected').text();
	var action ='GetRoute.action';
//	if(value.toLowerCase().replace(" ", "").indexOf('deadtrip') != -1 ){
//		action = 'GetDeadRoute.action';
//	}
	/*if(value.toLowerCase().replace(" ", "").indexOf('regulartrip')!=-1 )
	{
		 action ='GetRoute.action';
	}
	else{
	action = 'GetDeadRoute.action';
	}*/
	$.ajax({
        type: "POST",
        url: action,
        data: "startpoint="+startpoint+'&endpoint='+endpoint+'&serviceid='+serviceid+'&triptypeid='+trip_type_id,
        success: function(response){
        	
        	var arr = response.split(',');
        	 var i=0;
        	 if(document.getElementById('selectedRoute'+id) !=null){
        	 var selectedroute = document.getElementById('selectedRoute'+id).value;
        	 }
        	 $(elementid).empty();
        	$(elementid).append('<option value="0">Select</option>'); 
        	for (i = 0; i < arr.length-1; i++) {
        		var splitresult = arr[i].split('@');
        		
        		var dates = splitresult[2]+' to '+splitresult[3];
        		if(splitresult[0] == selectedroute){
        			$(elementid).append('<option value="'+splitresult[0]+'" selected= "selected" class="tooltips" title="'+dates+'">'+splitresult[1]+'</option>');
        		}
        		else{
        			$(elementid).append('<option value="'+splitresult[0]+'" class="tooltips" title="'+dates+'">'+splitresult[1]+'</option>');	
        		}
        		
        	}
        	
        	//setroutedistance(tableid,firstid,id);
        	
        }
	});
}


function getGroupingStops(id){
	var buspoint = document.getElementById('dest'+id+'A'+id).value;
	var elementid = '#origin'+(parseInt(id)+1);
	var value = document.getElementById('dest'+id).value;
	
	if(!isEmpty(value) && buspoint!='0' && !isEmpty(buspoint)){
	$.ajax({
        type: "POST",
        url: "GetGroupStops.action",
        data: "buspoint="+buspoint,
        success: function(response){
        	if(response.length == 1){
        		alert('No Bus Stop Grouping Created');
        		
        	}
        	
        	var arr = response.split(',');
        	 var i=0;
        	 $(elementid).empty();
        	$(elementid).append('<option value="0">Select</option>'); 
        	for (i = 0; i < arr.length-1; i++) {
        		var splitresult = arr[i].split('|');
        		if(buspoint == splitresult[0]){
        			$(elementid).append('<option value="'+splitresult[0]+'" selected="selected">'+splitresult[1]+'</option>');
        			if(document.getElementById('origin'+(parseInt(id)+1)+'A'+(parseInt(id)+1)) != null){
        			document.getElementById('origin'+(parseInt(id)+1)+'A'+(parseInt(id)+1)).value = buspoint;
        			}
        		}
        		else{
        			
        			$(elementid).append('<option value="'+splitresult[0]+'">'+splitresult[1]+'</option>');
        		}
        	}
      

        
        }
	});
	}
}

function setOrigin(id){
	var origin = document.getElementById('origin'+id).value;
	document.getElementById('origin'+id+'A'+id).value = origin;
}

function getRouteById(tableid,orid,eleid){
	
	
	//alert(eleid);
	var prevDest = document.getElementById('dest'+orid).value;
	
	var prevDestid = document.getElementById('dest'+orid+'A'+orid).value;
	var rows = document.getElementById(tableid).getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	var lastid= $('#'+tableid+':last tr:last').attr("id");

	/*if(eleid != rows && tableid == 'triptable1'){
	document.getElementById('origin'+eleid).value = prevDest;
	document.getElementById('origin'+eleid+eleid).value = prevDestid;
	}
	if(parseInt(lastid)+1 != eleid && tableid == 'triptable2'){
		document.getElementById('origin'+eleid).value = prevDest;
	}*/
}

function addMinutes(time, minsToAdd) {
	  function z(n){ return (n<10? '0':'') + n;};
	  var bits = time.split(':');
	  var mins = bits[0]*60 + +bits[1] + +minsToAdd;

	  return z(mins%(24*60)/60 | 0) + ':' + z(mins%60);  
	}  

function subtractMinutes(time, minsToAdd) {
	  function z(n){return (n<10? '0':'') + n;};
	  var bits = time.split(':');
	  var mins = bits[0]*60 + +bits[1];
	  mins =Math.abs(mins - minsToAdd); 
	  return z(mins%(24*60)/60 | 0) + ':' + z(mins%60);  
	}  

function setJourneyTime(id){
	var schtype = document.getElementById('schtype').value;
	var fromtime = document.getElementById('fromtime'+id).value;
	var totime = document.getElementById('totime'+id).value;
	var crewchange = document.getElementById('crewchange').value;
	var min = t2m(fromtime);
	var time = 0;
	
	/*if(crewchange == 'yes' ){*/
		var destmin = t2m(totime);
		if(Number(destmin) < Number(min)){
			var result = subtractMinutes(fromtime,destmin);
			var resultmin = t2m(result);
			time = subtractMinutes("24:00",resultmin);
		/*}*/
		/*else{
			time =  subtractMinutes(totime,min);
		}*/
	}
	else{
		time = subtractMinutes(totime,min);
	}
	time=time.replace("-", ""); 
	$("#journeytime"+id).val(time);
	//document.getElementById('journeytime'+id).value = time;
}

function t2m(t) {
    var b = t.split(':');
    return b[0] * 60 + +b[1];
  }

//Convert minutes to hh:mm (returns string)
function m2t(m) {
  return z((m/60 | 0)) + ':' + z(m%60);
}

function z(n) {
    return (n<10? '0':'') + n;
  }
function setFromTime(id){
	
	var breakvalue = document.getElementById('break'+id).value;
	var totime = document.getElementById('totime'+id).value;
	var breakDuration = document.getElementById('breakduration'+id).value;
	var splitresult = breakvalue.split('-');
	var breaktime = splitresult[0];
	if(breakDuration == '' || breakDuration == null){
		breakDuration = '00:00';
	}
	var min = t2m(breakDuration);
	
	var time = addMinutes(totime,min);
	time=time.replace("-", ""); 
	id = parseInt(id)+1;
	/*$("#fromtime"+id).val(time);*/
	setTimeout(function() {
		$('#fromtime'+id).val(time);
    }, 10);
	//document.getElementById('fromtime'+id).value = time;
	
}

function setSpreadHours(tableId,firstid,id){
	var schtype = document.getElementById('schtype').value;
	var table1rows = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	if(tableId == "triptable2"){
		var table2rows = document.getElementById("triptable2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		table1rows = parseInt(table1rows)+parseInt(table2rows);
		table1rows = parseInt(table1rows)-2;
	}
	else{
		table1rows = parseInt(table1rows)-1;
	}
	var crewchange = document.getElementById('crewchange').value;
	if(crewchange == 'yes' ){
		if(tableId == 'triptable1'){
		table1rows = parseInt(table1rows)-1;
		}
	}
	
	for(var i=parseInt(table1rows);i>=firstid;i--){
		var desttime = document.getElementById('totime'+i).value;
		if(!(desttime == '00:00')){
			id = i;
			break;
		}
	}

	if(document.getElementById('fromtime'+firstid) != null){
		
	var fromtime = document.getElementById('fromtime'+firstid).value;
	var totime = document.getElementById('totime'+id).value;
	
	var min = t2m(fromtime);
	
	var spreadhours = 0;
	
	if(crewchange == 'yes' ){
		var destmin = t2m(totime);
		if(Number(destmin) < Number(min)){
			var result = subtractMinutes(fromtime,destmin);
			var resultmin = t2m(result);
			spreadhours = subtractMinutes("24:00",resultmin);
		}
		else{
			spreadhours =  subtractMinutes(totime,min);
		}
		
	}
	else{
		if(schtype.toLowerCase().replace(" ", "").indexOf('ns') != -1 ){
			var destmin = t2m(totime);
			if(Number(destmin) < Number(min)){
				var result = subtractMinutes(fromtime,destmin);
				var resultmin = t2m(result);
				spreadhours = subtractMinutes("24:00",resultmin);
			}
			else{
				spreadhours =  subtractMinutes(totime,min);
				}
		}
		else{
		spreadhours =  subtractMinutes(totime,min);
		}
	}
	
	var spreadmins = t2m(spreadhours);
	
	var signTime = '30';
	
	if(schtype.toLowerCase().replace(" ", "").indexOf('no') != -1 ){
		signTime = '15';
	}
	spreadhours = parseInt(spreadmins) + parseInt(signTime);
	
	spreadhours = m2t(spreadhours);
	spreadmins = t2m(spreadhours);
	/*document.getElementById('ot1').value = '00:00';*/
	/*if(Number(spreadmins)>Number(480)){
		var othours = subtractMinutes(spreadhours,480);
		
		if(tableId == 'triptable1'){
			$('#ot1').val(othours);
		}
		if(tableId == 'triptable2'){
			$('#ot2').val(othours);
		}
	}
	else{
		if(tableId == 'triptable1'){
			$('#ot1').val('00:00');
		}
		if(tableId == 'triptable2'){
			$('#ot2').val('00:00');
		}
	}*/
	if(tableId=='triptable1'){
		$('#spread1').val(spreadhours);
	}
	if(tableId=='triptable2'){
		$('#spread2').val(spreadhours);
		}
	}	
}

function setsteeringtime(tableid,firstid,id){
	var breakvalue;
	var journeyTime="0";
	var breaktime="0";
	var breakmins="0";
	var steeringhrs="0";
	var crewchange = document.getElementById('crewchange').value;
	var table1rows = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	if(crewchange == 'yes'){
		table1rows = parseInt(table1rows)-1;
	}
	for(i=0;i<table1rows;i++){
		if(i!=parseInt(table1rows)-1){
			var id1 = i+1;
			var mins = t2m(document.getElementById('fromtime'+id1).value);
			var tomins = t2m(document.getElementById('totime'+i).value);
			
			
		
			/*if(parseInt(table1rows)-1 == i){*/
			breakmins = subtractMinutes(document.getElementById('fromtime'+id1).value, tomins);
			//}

			breakmins = t2m(breakmins);
			breakvalue = document.getElementById('break'+i).value;
			var splitresult = breakvalue.split('-');
			if(Number(splitresult[2])!=1){
				breaktime = parseInt(breaktime)+parseInt(breakmins);
				
			}
		}
	}
	if(crewchange == 'yes'){
		table1rows = parseInt(table1rows)+1;
	}
	var startid = parseInt(table1rows)-1;
	
	var spreadhours = document.getElementById('spread1').value;
	steeringhrs = subtractMinutes(spreadhours, breaktime);
	 /*steeringhrs = m2t(steeringhrs);*/
		 document.getElementById('steering1').value=steeringhrs;
		 
		 steeringmins = t2m(steeringhrs);
		 if(Number(steeringmins)>Number(480)){
				var othours = subtractMinutes(steeringhrs,480);
					$('#ot1').val(othours);
				}
			else{
					$('#ot1').val('00:00');
			}
	 
	var myElem = document.getElementById('triptable2');
	
	if(myElem !=null){
		journeyTime="0";
		steeringhrs = "0";
		breaktime="0";
		breakmins="0";
		var table2rows = document.getElementById("triptable2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		table1rows = parseInt(table1rows)+parseInt(table2rows);
		table1rows = parseInt(table1rows)-1;
	
	
	for(i=startid;i<table1rows;i++){
		if(i!=parseInt(table1rows)-1){
			var id1 = i+1;
			var mins = t2m(document.getElementById('fromtime'+id1).value);
			var tomins = t2m(document.getElementById('totime'+i).value);
			var totime = document.getElementById('totime'+i).value;
			
			if(crewchange == 'yes' ){
				if(Number(tomins) > Number(mins)){
					var result = subtractMinutes(document.getElementById('fromtime'+id1).value,tomins);
					var resultmin = t2m(result);
					breakmins = subtractMinutes("24:00",resultmin);
				}
				else{
					breakmins = subtractMinutes(totime, mins);
				}
				
			}
			else{
				breakmins = subtractMinutes(totime, mins);
			}
			
			breakmins = t2m(breakmins);
			breakvalue = document.getElementById('break'+i).value;
			var splitresult = breakvalue.split('-');
			if(Number(splitresult[2])!=1){
				breaktime = parseInt(breaktime)+parseInt(breakmins);
				
			}
		}
	}
	
	spreadhours = document.getElementById('spread2').value;
	steeringhrs = subtractMinutes(spreadhours, breaktime);
	 document.getElementById('steering2').value=steeringhrs;
	 steeringmins = t2m(steeringhrs);
	 if(Number(steeringmins)>Number(480)){
			var othours = subtractMinutes(steeringhrs,480);
				$('#ot2').val(othours);
			}
		else{
				$('#ot2').val('00:00');
		}

	}
}



function addminstodest(tableid,id){

	var desttime = document.getElementById('totime'+id).value;
	var hours = '';
	var breaktime = document.getElementById('breakduration'+id).value;
	var breakmin = t2m(breaktime);
	if(!(breakmin == 0 || breakmin<=5)){
		hours = addMinutes(desttime, breakmin);

	}
	else{
		hours = addMinutes(desttime, 5);
	}
	
	id = parseInt(id)+1;
	var rows = document.getElementById(tableid).getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	var myElem = document.getElementById('fromtime'+id);
	if(myElem != null){
		setTimeout(function() {
			$('#fromtime'+id).val(hours);
	    }, 10);
		/*$('#fromtime'+id).val(hours);*/
	//document.getElementById('fromtime'+id).value = hours;
	}
}

function setrestforcrew(tableid,firstid,lastid){
	var rows = document.getElementById(tableid).getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	var result="";
	var value="";
	for(i=firstid;i<=lastid;i++){
		value = document.getElementById('break'+i).value;
		if(value!='00:00-0'){
			var splitresult = value.split('-');
			if(splitresult[4] == 1){
			if(i==rows-1){
				var id1 = parseInt(i)+1;
			result += document.getElementById('totime'+i).value + '-' + document.getElementById('fromtime'+id1).value; 
			}
			else{
				var id2 = parseInt(i)+1;
				result += document.getElementById('totime'+i).value + '-' + document.getElementById('fromtime'+id2).value+" ";
				
			}
			}
		}
	}
	
	if(tableid == 'triptable1'){
		$("#rest1").val(result);
		//document.getElementById('rest1').value = result;
	}
	if(tableid == 'triptable2'){
		$("#rest2").val(result);
		//document.getElementById('rest2').value = result;
	}
	
}

function restforcrew(){
	var rows = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	var result="";
	var value="";
	var table1rows = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	var crewchange = document.getElementById('crewchange').value;
	if(crewchange == 'yes'){
		table1rows = parseInt(table1rows)-1;
	}
	for(i=0;i<table1rows;i++){
		value = document.getElementById('break'+i).value;
		var splitresult = value.split('-');
		if(splitresult[4] == 1){
			if(i==rows-1){
				var id1 = parseInt(i)+1;
				if(document.getElementById('totime'+i)!=null && document.getElementById('fromtime'+id1)!=null){
			result += document.getElementById('totime'+i).value + '-' + document.getElementById('fromtime'+id1).value;
				}
			}
			else{
				var id2 = parseInt(i)+1;
				if(document.getElementById('totime'+i)!=null && document.getElementById('fromtime'+id2)!=null){
				result += document.getElementById('totime'+i).value + '-' + document.getElementById('fromtime'+id2).value+" ";
				}
			}
			}
	}
	$("#rest1").val(result);
//	document.getElementById('rest1').value = result;
	
	if(crewchange == 'yes'){
		table1rows = parseInt(table1rows)+1;
	}
	var startid = parseInt(table1rows)-1;
	
	var myElem = document.getElementById('triptable2');
	
	if(myElem !=null){
		var rows = document.getElementById("triptable2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		value = '';
		result = '';
		var table2rows = document.getElementById("triptable2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		table1rows = parseInt(table1rows)+parseInt(table2rows);
		table1rows = parseInt(table1rows)-1;
		for(i=startid;i<table1rows;i++){
			value = document.getElementById('break'+i).value;
			var splitresult = value.split('-');
			if(splitresult[4] == 1){
				if(i==rows-1){
					var id1 = parseInt(i)+1;
					if(document.getElementById('totime'+i)!=null && document.getElementById('fromtime'+id1)!=null){
				result += document.getElementById('totime'+i).value + '-' + document.getElementById('fromtime'+id1).value; 
				}
				}
				else{
					var id2 = parseInt(i)+1;
					if(document.getElementById('totime'+i)!=null && document.getElementById('fromtime'+id2)!=null){
					result += document.getElementById('totime'+i).value + '-' + document.getElementById('fromtime'+id2).value+" ";
					}
					
				}
				}
		}
		$("#rest2").val(result);
		//document.getElementById('rest2').value = result;
	}
	
}




function setroutedistance(tableid,firstid,id){
	var startstop = document.getElementById('origin'+id+'A'+id).value;
	var endstop = document.getElementById('dest'+id+'A'+id).value;
	var routeno = document.getElementById('route_no'+id).value;
	var elementid = 'routelength'+id;
	$.ajax({
        type: "POST",
        url: "GetDistance.action",
        data: "startstop="+startstop+'&endstop='+endstop+'&routeno='+routeno,
        success: function(response){
        	if(response.length != 1){
            	document.getElementById(elementid).value=response;
        	}
        	else{
        		document.getElementById(elementid).value='0.0';
        	}
        	document.getElementById("selectedRoute"+id).value =document.getElementById("route_no"+id).value;
        	setKms(tableid,firstid,id);
        	//setScheduleKms(tableid,firstid,id);
        	setDeadKm(tableid,firstid,id);
        }
	});
}

function setKms(tableid,firstid,lastid){
	
	
	var table1rows = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	if(tableid == "triptable2"){
		var table2rows = document.getElementById("triptable2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		table1rows = parseInt(table1rows)+parseInt(table2rows);
		table1rows = parseInt(table1rows)-1;
	}
	
	
	var rows = document.getElementById(tableid).getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	var schkms ='0.0';
	var deadkms='0.0';
	var crewchange =  document.getElementById('crewchange').value;
	if(crewchange == 'yes' && tableid == "triptable1"){
		table1rows = parseInt(table1rows)-1;
	}
	for(i=firstid;i<table1rows;i++){
		var ckd='';
		var value = $('#trip'+i+' option:selected').text();
		
		if(value.toLowerCase().replace(" ", "").indexOf('deadtrip') != -1 ){
		
		/*if(document.getElementById("deadtrip"+i)!=null){
		 ckd = document.getElementById("deadtrip"+i).checked;
		}
		if(!ckd){*/
			deadkms =parseFloat(deadkms) + parseFloat((document.getElementById('routelength'+i).value));
		}
		else{
			schkms =parseFloat(schkms) + parseFloat((document.getElementById('routelength'+i).value));
		
		}
	}
	
	schkms = Math.round(schkms * 100) / 100;
	deadkms = Math.round(deadkms * 100) / 100;
	if(isNaN(schkms)){
		schkms = 0.0;
	}
	if(tableid == 'triptable1'){
		document.getElementById('schkms1').value = Number((schkms).toFixed(1));
		document.getElementById('dead1').value = Number((deadkms).toFixed(1));
		document.getElementById('totalkms1').value = Number((parseFloat(schkms)+parseFloat(document.getElementById('dead1').value)+parseFloat(document.getElementById('schkms2').value)+parseFloat(document.getElementById('dead2').value)).toFixed(1));
		
		}
		if(tableid == 'triptable2'){
			document.getElementById('schkms2').value = Number((schkms).toFixed(1));
			document.getElementById('dead2').value = Number((deadkms).toFixed(1));
			document.getElementById('totalkms1').value = Number((parseFloat(schkms)+parseFloat(document.getElementById('dead2').value)+parseFloat(document.getElementById('schkms1').value)+parseFloat(document.getElementById('dead1').value)).toFixed(1));
		}
	
}



function setScheduleKms(tableid,firstid,lastid){
	var rows = document.getElementById(tableid).getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	var kms='0.0';
	var schkms ='0.0';
	for(i=firstid;i<=lastid;i++){
		var ckd = document.getElementById("deadtrip"+i).checked;
		if(!ckd){
			if(isNaN(document.getElementById('routelength'+i).value)){
				kms = '0.0';
			}
			else{
				kms = document.getElementById('routelength'+i).value;
			}
		schkms =parseFloat(schkms) + parseFloat((kms));
		}
	}
	
	schkms = Math.round(schkms * 100) / 100;
	if(tableid == 'triptable1'){
	document.getElementById('schkms1').value = schkms;
	//document.getElementById('dead1').value = deadkms;
	//alert('schkms --------> '+schkms+ '  dead1------> '+document.getElementById('dead1').value+'  schkms --------> '+parseFloat(document.getElementById('schkms2').value)+'   dead2--------> '+parseFloat(document.getElementById('dead2').value) );
	document.getElementById('totalkms1').value = parseFloat(schkms)+parseFloat(document.getElementById('dead1').value)+parseFloat(document.getElementById('schkms2').value)+parseFloat(document.getElementById('dead2').value);
	
	}
	if(tableid == 'triptable2'){
		document.getElementById('schkms2').value = schkms;
		//document.getElementById('dead2').value = deadkms;
		document.getElementById('totalkms1').value = parseFloat(schkms)+parseFloat(document.getElementById('dead2').value)+parseFloat(document.getElementById('schkms1').value)+parseFloat(document.getElementById('dead1').value);
	}

}

function setDeadKm(tableid,firstid,lastid){
	var rows = document.getElementById(tableid).getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	var deadkms='0.0';
	for(i=firstid;i<=lastid;i++){
		/*var ckd = document.getElementById("deadtrip"+i).checked;*/
		var value = $('#trip'+i+' option:selected').text();
		
		
		if(value.toLowerCase().replace(" ", "").indexOf('deadtrip') != -1 ){
			deadkms =parseFloat(deadkms) + parseFloat((document.getElementById('routelength'+i).value));
		}
	}
	if(tableid == 'triptable1'){
	document.getElementById('dead1').value = deadkms;
	}
	if(tableid == 'triptable2'){
		document.getElementById('dead2').value = deadkms;
	}
	//setScheduleKms(tableid,firstid,lastid);
}

function setTrip(id){
	
	var value = document.getElementById('trip'+id).value;
	if(document.getElementById('selectedTrip'+id)!=null){
	document.getElementById('selectedTrip'+id).value = value;
	}
}

function crewChange(id,formfourid,schid,nooftrips){
	var crewchangestatus = document.getElementById('crewchange').value;
	var nightchangestatus = document.getElementById('nightchange').value;
	var crewChangeId = document.getElementById('crewChangeId').value;
	/*var myElem = document.getElementById('fuelchange');
	var cflag=false;
	var sflag=false;
	if(crewChangeId == id){
		
	}*/
	
	
	var breakOptions = new Array();
	$("#break0 option").each(function()
			{
		var $this = $(this);
		breakOptions.push($this.val());
		breakOptions.push($this.text());    
		// add $(this).val() to your list
			});

	
	var tripOptions = new Array();
	
	$("#trip0 option").each(function()
			{
		var $this = $(this);
		tripOptions.push($this.val());
		tripOptions.push($this.text());    
		// add $(this).val() to your list
			});
	
	var customerOptions = new Array();
	
	$("#customer0 option").each(function()
			{
		var $this = $(this);
		customerOptions.push($this.val());
		customerOptions.push($this.text());    
		// add $(this).val() to your list
			});
	
var shiftTypeOptions = new Array();
	
	$("#shift0 option").each(function()
			{
		var $this = $(this);
		shiftTypeOptions.push($this.val());
		shiftTypeOptions.push($this.text());    
		// add $(this).val() to your list
			});
	
	var destvalue = document.getElementById('dest'+id).value;
	if(crewChangeId == id || isEmpty(crewChangeId)){
	if(crewchangestatus == 'no' && nightchangestatus == 'no'){
	var newRow = "<tr id='fuelchange'><td colspan='14' align='center'> CREW CHANGE, FUELLING MAINTENANCE AT DEPOT</td></tr>";
	$('#triptable1 > tbody > tr').eq(id).after(newRow);
	
	var rows = document.getElementById('triptable1').getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	newid=parseInt(id)+2;
	var resetValues = newid;
	
	
	var newtable = "<table class='table table-striped table-bordered table-hover' id='triptable2'> ";
	
	var j = parseInt(rows)-parseInt(newid);
	var rowid = parseInt(id)+1;
	var totalrows = parseInt(rows)+parseInt(j);
	
	var firstid = rowid;
	var table2id=1;
	var value='';
	var selectid1 ='';
	newtable += "<tbody>";
	for(i=0;i<j;i++){
		
		newtable += "<tr id='"+rowid+"'>";
		
		newtable += '<td><input type="text" name = "tripslist['+rowid+'].listItemNumber" class="form-control" value="'+(parseInt(rowid)+1)+'" style="width: 50px" id="listitem'+rowid+'" readonly="readonly"/></td></td>';
		
		newtable += '<td>';
		//newtable += '<input type="hidden" name="table2details['+table2id+'].tripNumber" value="'+table2id+'"/>';
		newtable += '<input type="hidden" id="formFour'+rowid+'" name="tripslist['+rowid+'].formFour.id" value="'+formfourid+'"/>';
		newtable += '<input type="hidden" id="scheduleid'+rowid+'" name="tripslist['+rowid+'].scheduleNumber.schedule_id" value="'+schid+'"/>';
		newtable += '<input type="hidden" id="noofTrips'+rowid+'" name="tripslist['+rowid+'].noofTrips" value="'+nooftrips+'"/>';
		
		value = document.getElementById('trip'+rowid).value;
		
		newtable +=	'<input type="text" name = "tripslist['+rowid+'].tripNumber" class="form-control" value="'+table2id+'" style="width: 50px" id="id'+rowid+'" readonly="readonly" />';
		
		if(document.getElementById('tripid'+rowid) == null){
			
		}
		value = document.getElementById('tripid'+rowid).value;
		newtable += '<input type="hidden" id="tripid'+rowid+'" name="tripslist['+rowid+'].id" value="'+value+'"/></td>';
		
		newtable += '<td><select id="trip'+rowid+'" class="form-control input-small" name="tripslist['+rowid+'].tripType.id" onchange="setTrip(this.id.substring(4)); selectForCharter(this.id.substring(4)); getDeadTripStops(this.id.substring(4));" value="'+rowid+'">';
		
		for(l=0;l<tripOptions.length;l=l+2){
			if(tripOptions[l] == value){
				newtable += '<option value="'+tripOptions[l]+'" selected="selected">'+tripOptions[l+1]+'</option>';
			}
			else{
			newtable += '<option value="'+tripOptions[l]+'">'+tripOptions[l+1]+'</option>';
			}
			
		}
		
		newtable += '</select></td>'
		/*value = document.getElementById('selectedTrip'+rowid).value;
		newtable += '</select><input type="hidden" id = "selectedTrip'+rowid+'" name="tripslist['+rowid+'].selectedTrip" value="'+value+'"/></td>';*/
		
		value = document.getElementById('customer'+rowid).value;
		newtable += '<td><select id="customer'+rowid+'" class="form-control input-small" name="tripslist['+rowid+'].customer.id" value="'+value+'"> ';
		
		for(d=0;d<customerOptions.length;d=d+2){
			if(value == customerOptions[d] ){
				newtable += '<option value="'+customerOptions[d]+'" selected="selected">'+customerOptions[d+1]+'</option>';
			}
			else{
			newtable += '<option value="'+customerOptions[d]+'">'+customerOptions[d+1]+'</option>';
			}
			
		}
		newtable += '</select></td>';

		/*if(!$("#deadtrip"+rowid).is(':checked')){
		newtable += '<td><div id="uniform-deadtrip'+rowid+'"><span>';
		newtable += '<input  type="checkbox" id="deadtrip'+rowid+'" value="true"  name="tripslist['+rowid+'].deadTrip" onclick="getDeadTripStops(this.id.substring(8));"/></span></div>';
		newtable += '<input id="__checkbox_deadtrip'+rowid+'" type="hidden" value="true" name="__checkbox_tripslist['+rowid+'].deadTrip">';
		newtable += '</td>';	
		}
		else{
			newtable += '<td><div id="uniform-deadtrip'+rowid+'"><span>';
			newtable += '<input  type="checkbox" id="deadtrip'+rowid+'"  checked="checked" value="true" name="tripslist['+rowid+'].deadTrip" onclick="getDeadTripStops(this.id.substring(8));"/></span></div>';
			newtable += '<input id="__checkbox_deadtrip'+rowid+'" type="hidden" value="true" name="__checkbox_tripslist['+rowid+'].deadTrip">';
			newtable += '</td>';	
		}*/
		
		if(i==0){
			var selectOptions = new Array();
			selectid1 = '#origin'+rowid+' option';
			$(selectid1).each(function()
					{
				var $this = $(this);
				selectOptions.push($this.val());
				selectOptions.push($this.text());  
				
				// add $(this).val() to your list
					});
			value = document.getElementById('origin'+rowid).value;
			console.log("value ------> "+value);
			
			newtable += '<td><select data-placeholder="Select..." id="origin'+rowid+'" name="tripslist[<%=i %>].origin" style="width: 100px" class="form-control input-medium" onchange="setOrigin(this.id.substring(6))" value="'+value+'">';
			for(var c=0;c<selectOptions.length;c=c+2){
				if(value == selectOptions[c] ){
				newtable += '<option value="'+selectOptions[c]+'" selected="selected">'+selectOptions[c+1]+'</option>';
				}
				else{
					newtable += '<option value="'+selectOptions[c]+'" >'+selectOptions[c+1]+'</option>';	
				}
				
			}
			newtable +='</select>';
			
			value = document.getElementById('origin'+rowid+'A'+rowid).value;
			newtable += '<input type="hidden" name="tripslist['+rowid+'].startPoint" value="'+value+'" id ="origin'+rowid+'A'+rowid+'"/></td>';
		}
		else{
			
			var selectOptions = new Array();
			selectid1 = '#origin'+rowid+' option';
			$(selectid1).each(function()
					{
				var $this = $(this);
				selectOptions.push($this.val());
				selectOptions.push($this.text());    
				// add $(this).val() to your list
					});
		value = document.getElementById('origin'+rowid).value;
		newtable += '<td><select data-placeholder="Select..." id="origin'+rowid+'" name="tripslist[<%=i %>].origin" style="width: 100px" class="form-control input-medium" onchange="setOrigin(this.id.substring(6));getDepotsByNo(\'triptable2\',\''+firstid+'\',this.id.substring(6));" value="'+value+'">';
		for(var c=0;c<selectOptions.length;c=c+2){
			if(selectOptions[c] == value){
				newtable += '<option value="'+selectOptions[c]+'" selected="selected">'+selectOptions[c+1]+'</option>';
			}
			else{
			newtable += '<option value="'+selectOptions[c]+'">'+selectOptions[c+1]+'</option>';
			}
			
		}
		newtable +='</select>';
		
		
		value = document.getElementById('origin'+rowid+'A'+rowid).value;
		newtable += '<input type="hidden" name="tripslist['+rowid+'].startPoint" value="'+value+'" id ="origin'+rowid+'A'+rowid+'"/></td>';
		}
		
		value = document.getElementById('dest'+rowid).value;
		newtable += '<td><input type="text" name = "tripslist['+rowid+'].destination" class="form-control input-medium destination" placeholder="Enter text" style="width: 170px" id="dest'+rowid+'" onblur="getDepotsByNo(\'triptable2\',\''+firstid+'\',this.id.substring(4));" value="'+value+'"/>';
		
		value = document.getElementById('dest'+rowid+'A'+rowid).value;
		newtable += '<input type="hidden" name="tripslist['+rowid+'].endPoint" value="'+value+'" id ="dest'+rowid+'A'+rowid+'"/></td>';
		
		value = document.getElementById('route_no'+rowid).value;
		var selectOptions1 = new Array();
		selectid1 = '#route_no'+rowid+' option';
		$(selectid1).each(function()
				{
			var $this = $(this);
			selectOptions1.push($this.val());
			selectOptions1.push($this.text());    
			// add $(this).val() to your list
				});
		newtable += '<td><select data-placeholder="Select..." id="route_no'+rowid+'" name="tripslist['+rowid+'].routeNumber.route_id" style="width: 100px" class="form-control input-small" onchange="getRouteById(\'triptable2\',this.id.substring(8),(parseInt(this.id.substring(8))+1));setroutedistance(\'triptable2\',\''+firstid+'\',this.id.substring(8));" value="'+value+'">';
		for(var c=0;c<selectOptions1.length;c=c+2){
			if(selectOptions1[c] == value){
			newtable += '<option value="'+selectOptions1[c]+'" selected="selected">'+selectOptions1[c+1]+'</option>';
			}
			else{
				newtable += '<option value="'+selectOptions1[c]+'">'+selectOptions1[c+1]+'</option>';
			}
			
		}
		newtable +='</select></td>';
		
		/*value = document.getElementById('selectedRoute'+rowid).value;
		newtable += '<input type="hidden" id = "selectedRoute'+rowid+'" name="tripslist['+rowid+'].selectedRoute" value="'+value+'"/></td>';*/
		
		value = document.getElementById('routelength'+rowid).value;
		newtable += '<td><input type="text" class="form-control" name="tripslist['+rowid+'].distance" style="width: 70px" id="routelength'+rowid+'"  value="'+value+'" onblur="setKms(\'triptable2\',\''+firstid+'\',this.id.substring(11));" onkeypress="run(this);"/></td>';
		
		value = document.getElementById('fromtime'+rowid).value;
		newtable += '<td><input type="text" class="form-control timepicker timepicker-24i" name="tripslist['+rowid+'].starttimeString" style="width: 70px" id="fromtime'+rowid+'"  onblur="checkTime(this);" value="'+value+'"/></td>';
		
		value = document.getElementById('totime'+rowid).value;
		newtable += '<td><input type="text" class="form-control timepicker timepicker-24i" name="tripslist['+rowid+'].endtimeString" id="totime'+rowid+'" style="width: 70px" onblur="checkTime(this);validateTime(\'triptable2\',\''+firstid+'\',this.id.substring(6));" value="'+value+'"/> <span class="input-group-btn"></span></td>';
		
		value = document.getElementById('journeytime'+rowid).value;
		newtable += '<td><input type="text" class="form-control" name="tripslist['+rowid+'].journeyTime" readonly="readonly" value="'+value+'" style="width: 70px" id="journeytime'+rowid+'" onfocus="setJourneyTime(this.id.substring(11));setSpreadHours(\'triptable2\',\''+firstid+'\',this.id.substring(11));addminstodest(\'triptable2\',this.id.substring(11));" ></td>';
		
		value = document.getElementById('break'+rowid).value;
		newtable += '<td><select name="tripslist['+rowid+'].breakTypeString" id="break'+rowid+'" class="form-control input-small" onchange="enablebreakLocation(this.id.substring(5));setFromTime(this.id.substring(5));restforcrew();setJourneyTime(parseInt(this.id.substring(5))+1);setsteeringtime(\'triptable2\',\''+firstid+'\',this.id.substring(5));setSpreadHours(\'triptable2\',\''+firstid+'\',this.id.substring(5));" value="'+value+'">';
		
		for(k=0;k<breakOptions.length;k=k+2){
			if(value == breakOptions[k]){
			newtable += '<option value="'+breakOptions[k]+'" selected="selected">'+breakOptions[k+1]+'</option>';
			}
			else{
				newtable += '<option value="'+breakOptions[k]+'">'+breakOptions[k+1]+'</option>';
			}
			
		}
		newtable += '</select></td>';
		
		value = document.getElementById('breakduration'+rowid).value;
		newtable += '<td><input type="text" class="form-control timepicker timepicker-24i" name="tripslist['+rowid+'].breaktimeString" style="width: 70px" id="breakduration'+rowid+'" onblur="checkTime(this);setFromTime(this.id.substring(13));restforcrew();setJourneyTime(parseInt(this.id.substring(13))+1);setsteeringtime(\'triptable2\',\''+firstid+'\',this.id.substring(13));setSpreadHours(\'triptable2\',\''+firstid+'\',this.id.substring(13));" value="'+value+'" />';
		
		value = document.getElementById('breaklocation'+rowid).value;
		newtable += '<input type="text" class="form-control" name="tripdetails['+rowid+'].breakLocation" style="width: 90px;display: none;" id="breaklocation'+rowid+'" readonly="readonly" value="'+value+'"/></td>';
		
		
		/*newtable += '<td><input type="checkbox" class="group-checkable" data-set="#sample_2 .checkboxes" id="deadtrip'+rowid+'" value="deadtrip" onclick="setDeadKm(\'triptable2\');"/></td>';*/
		
		newtable += '<td><div id="uniform-crewchange'+rowid+'"><span>';
		newtable += '<input  type="checkbox" id="crewchange'+rowid+'" value="true" onclick="enablecrewLocation(this.id.substring(10));setChange(this.id.substring(10),\''+formfourid+'\',\''+schid+'\',\''+nooftrips+'\');" name="tripslist['+rowid+'].crewChangeStatus"/></span></div>';
		newtable += '<input id="__checkbox_crewchange'+rowid+'" type="hidden" value="true" name="__checkbox_tripslist['+rowid+'].crewChangeStatus"></td>';
		
		value = document.getElementById('crewChangelocation'+rowid).value;
		newtable += '<td><input type="text" class="form-control" name="tripslist['+rowid+'].crewChangeLocation" style="width: 90px" id="crewChangelocation'+rowid+'" readonly="readonly" value="'+value+'"/></td>';
		
		newtable += '<td><div id="uniform-nighttrip'+rowid+'"><span>';
		newtable += '<input  type="checkbox" id="nighttrip'+rowid+'" value="true"  name="tripslist['+rowid+'].nightTrip" onclick="enablenightLocation(this.id.substring(9));setChange(this.id.substring(9),\''+formfourid+'\',\''+schid+'\',\''+nooftrips+'\');"/></span></div>';
		newtable += '<input id="__checkbox_nighttrip'+rowid+'" type="hidden" value="true" name="">';
		
		value = document.getElementById('nighttriplocation'+rowid).value;
		newtable += '<input type="text" class="form-control" name="tripdetails['+rowid+'].nightHaltLocation" style="width: 90px;display: none;" id="nighttriplocation'+rowid+'" readonly="readonly" value="'+value+'"/></td>';
		
		/*newtable += '<td><input type="checkbox" class="group-checkable" data-set="#sample_2 .checkboxes" id="crewchange'+rowid+'" value="crewchange" onclick="crewChange(\''+rowid+'\',\''+formfourid+'\',\''+schid+'\'\''+nooftrips+'\')"/></td>';*/
		
		value = document.getElementById('shift0').value;
		newtable += '<td><select id="shift'+rowid+'" class="form-control input-small" name="tripslist['+rowid+'].shiftType.id" onchange="setShiftType(this.id.substring(5)) getDeadTripStops(this.id.substring(5));" value="'+value+'">';
				
				for(k=0;k<shiftTypeOptions.length;k=k+2){
					if(value != shiftTypeOptions[k]){
					newtable += '<option value="'+shiftTypeOptions[k]+'" selected="selected">'+shiftTypeOptions[k+1]+'</option>';
					}
					else{
						newtable += '<option value="'+shiftTypeOptions[k]+'" >'+shiftTypeOptions[k+1]+'</option>';
					}
					
				}
				newtable += '</select></td>';
		
		value = document.getElementById('operationtype'+rowid).value;
		newtable += '<td><select id="operationtype'+rowid+'" class="form-control input-small" name="tripslist['+rowid+'].operationType">';
		
		if(value == 'Obligatory'){
		newtable +=	'<option value="Obligatory" selected="selected">Obligatory</option><option value="Non-Obligatory">Non-Obligatory</option>';
		}
		else{
			newtable +=	'<option value="Obligatory">Obligatory</option><option value="Non-Obligatory" selected="selected">Non-Obligatory</option>';
		}

		newtable += '</select></td>';
		
		value = document.getElementById('remarks'+rowid).value;
		newtable += '<td><input type="text" class="form-control" placeholder="Enter text" style="width: 100px" id="remarks'+rowid+'" name="tripslist['+rowid+'].remarks" value="'+value+'"></td>';
		
		value = document.getElementById('tripid'+rowid).value;
		newtable += '<td><a href="javascript:void(0)" class="btn default btn-xs black" id="del'+rowid+'" onclick="deleteRow(this,this.id.substring(3),\'triptable2\',\''+value+'\');restforcrew();" > <i class="fa fa-trash-o"></i> Delete </a> &nbsp; <a href="javascript:void(0)" id="add'+rowid+'" class="btn default btn-xs purple" onclick="addtrip(this.id.substring(3),\'triptable2\',\''+formfourid+'\',\''+schid+'\',\''+nooftrips+'\');"> <i class="fa fa-edit"></i> Add </a></td>';
		
		newtable += "</tr>";
		rowid = parseInt(rowid)+1;
		table2id = parseInt(table2id)+1;
	}
	newtable += "</tbody>";
	newtable += '<tfoot><tr style="visibility:hidden;"><th scope="col">#</th><th scope="col">Trip</th><th scope="col"  align="center">Trip</th><th scope="col" align="center">Customer</th><th scope="col" colspan="2" style="align: center">Place</th><th scope="col" align="center">Route</th><th scope="col" align="center">Distance</th><th scope="col" colspan="2" align="center">Timings</th><th scope="col" align="center">Running</th><th scope="col" align="center">Break</th><th scope="col" align="center">Break</th><th scope="col" align="center">Break</th><th scope="col" align="center">Change</th><th scope="col" align="center">Crew Change</th><th scope="col" align="center">Night</th><th scope="col" align="center">Duty</th><th scope="col" align="center">Operation</th><th scope="col" align="center">Remarks</th></tr></tfoot>';
	newtable += '</table>';
	newtable += '<script>Metronic.init();</script>';
	for(i=newid;i<rows;i++){
		$('#triptable1 > tbody > tr').eq(newid).remove();
		}
	$('#table2').html(newtable);
	
	rowid = parseInt(id)+1;
	
	disableCrewChange(id,totalrows);
	
	/*if($("#crewchange"+id).is(':checked')){
	$("#nighttrip"+id).attr("disabled", true);
	}
	else{
	$("#crewchange"+id).attr("disabled", true);	
	}*/
	document.getElementById('crewChangeId').value = id;
	document.getElementById('crewchange').value = 'yes';
	document.getElementById('nightchange').value = 'yes';
	setSpreadHours('triptable1','0','0');
	setSpreadHours('triptable2',parseInt(rowid),'0');
	setsteeringtime('triptable2','0','0');
	setKms('triptable1','0','0');
	setKms('triptable2',rowid,'0');
	for(i=0;i<j;i++){
		$("#dest"+rowid).autocomplete("", {
			url : PATH + '/GetDepot.action',
			minChars : 2,
			max : 50,
			multiple : false,
			autoFill : true,
			formatItem: function(data) {
                return data[1];
            },
            formatResult: function(data) {
                return data[1];
            }
		}).result(function(event, data) {
            if (data) {
                var idd = this.id +'A'+ this.id.substring(4);
                document.getElementById(idd).value = data[0];
                getGroupingStops(this.id.substring(4));
            }
        });
	
		
		$(".timepicker-24i").inputmask("mask", {
			"mask": "99:99"
        });
		rowid = parseInt(rowid)+1;
		
	}
	}
	
	if(crewchangestatus == 'yes' && !$("#crewchange"+id).is(':checked') && nightchangestatus == 'yes' && !$("#nighttrip"+id).is(':checked')){
		
		var triptable2rows = document.getElementById('triptable2').getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		
		var triptable1rows = document.getElementById('triptable1').getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		
		var totalrows = parseInt(triptable1rows) + parseInt(triptable2rows);
		var rowid1 = parseInt(id)+1;
		var newrows = '';
		var cnt=0;
		var firstid = rowid1;
		for(i=rowid1;i<parseInt(totalrows)-1;i++){
			
	
			newrows += "<tr id='"+rowid1+"'>";
			
			newrows += '<td><input type="text" name = "tripslist['+rowid1+'].listItemNumber" class="form-control" value="'+(parseInt(rowid1)+1)+'" style="width: 50px" id="listitem'+rowid1+'" readonly="readonly" /></td></td>';
			
			newrows += '<td>';
			//newrows += '<input type="hidden" name="table2details['+table2id+'].tripNumber" value="'+table2id+'"/>';
			newrows += '<input type="hidden" id="formFour'+rowid1+'" name="tripslist['+rowid1+'].formFour.id" value="'+formfourid+'"/>';
			newrows += '<input type="hidden" id="scheduleid'+rowid1+'" name="tripslist['+rowid1+'].scheduleNumber.schedule_id" value="'+schid+'"/>';
			newrows += '<input type="hidden" id="noofTrips'+rowid1+'" name="tripslist['+rowid1+'].noofTrips" value="'+nooftrips+'"/>';
			newrows +=	'<input type="text" name = "tripslist['+rowid1+'].tripNumber" class="form-control" value="'+(parseInt(rowid1)+1)+'" style="width: 50px" id="id'+rowid1+'" readonly="readonly" />';
			
			value = document.getElementById('tripid'+rowid1).value;
			newrows += '<input type="hidden" id="tripid'+rowid1+'" name="tripslist['+rowid1+'].id" value="'+value+'"/></td>';
			
			value = document.getElementById('trip'+rowid1).value;
			
			newrows += '<td><select id="trip'+rowid1+'" class="form-control input-small" name="tripslist['+rowid1+'].tripType.id" onchange="setTrip(this.id.substring(4)); selectForCharter(this.id.substring(4));getDeadTripStops(this.id.substring(4));" value="'+rowid1+'">';
			
			for(l=0;l<tripOptions.length;l=l+2){
				if(tripOptions[l] == value){
					newrows += '<option value="'+tripOptions[l]+'" selected="selected">'+tripOptions[l+1]+'</option>';
				}
				else{
				newrows += '<option value="'+tripOptions[l]+'">'+tripOptions[l+1]+'</option>';
				}
				
			}
			/*value = document.getElementById('selectedTrip'+rowid1).value;
			newrows += '</select><input type="hidden" id = "selectedTrip'+rowid1+'" name="tripslist['+rowid1+'].selectedTrip" value="'+value+'"/></td>';*/
			
			value = document.getElementById('customer'+rowid1).value;
			newrows += '<td><select id="customer'+rowid1+'" class="form-control input-small" name="tripslist['+rowid1+'].customer.id" value="'+value+'"> ';
			
			for(d=0;d<customerOptions.length;d=d+2){
				if(value == customerOptions[d] ){
					newrows += '<option value="'+customerOptions[d]+'" selected="selected">'+customerOptions[d+1]+'</option>';
				}
				else{
				newrows += '<option value="'+customerOptions[d]+'">'+customerOptions[d+1]+'</option>';
				}
				
			}
			newrows += '</select></td>';

			/*if(!$("#deadtrip"+rowid1).is(':checked')){
			newrows += '<td><div id="uniform-deadtrip'+rowid1+'"><span>';
			newrows += '<input  type="checkbox" id="deadtrip'+rowid1+'" value="true"  name="tripslist['+rowid1+'].deadTrip" onclick="getDeadTripStops(this.id.substring(8));"/></span></div>';
			newrows += '<input id="__checkbox_deadtrip'+rowid1+'" type="hidden" value="true" name="__checkbox_tripslist['+rowid1+'].deadTrip">';
			newrows += '</td>';	
			}
			else{
				newrows += '<td><div id="uniform-deadtrip'+rowid1+'"><span>';
				newrows += '<input  type="checkbox" id="deadtrip'+rowid1+'" value="true" checked="checked"  name="tripslist['+rowid1+'].deadTrip" onclick="getDeadTripStops(this.id.substring(8));"/></span></div>';
				newrows += '<input id="__checkbox_deadtrip'+rowid1+'" type="hidden" value="true" name="__checkbox_tripslist['+rowid1+'].deadTrip">';
				newrows += '</td>';	
			}*/
			
			if(i==0){
				var selectOptions = new Array();
				selectid1 = '#origin'+rowid1+' option';
				$(selectid1).each(function()
						{
					var $this = $(this);
					selectOptions.push($this.val());
					selectOptions.push($this.text());  
					
					// add $(this).val() to your list
						});
				value = document.getElementById('origin'+rowid1).value;
				newrows += '<td><select data-placeholder="Select..." id="origin'+rowid1+'" name="tripslist[<%=i %>].origin" style="width: 100px" class="form-control input-medium" onchange="setOrigin(this.id.substring(6));getDepotsByNo("triptable1","0",this.id.substring(6));" value="'+value+'">';
				for(var c=0;c<selectOptions.length;c=c+2){
					if(value == selectOptions[c] ){
					newrows += '<option value="'+selectOptions[c]+'" selected="selected">'+selectOptions[c+1]+'</option>';
					}
					else{
						newrows += '<option value="'+selectOptions[c]+'" >'+selectOptions[c+1]+'</option>';	
					}
					
				}
				newrows +='</select>';
				
				value = document.getElementById('origin'+rowid1+'A'+rowid1).value;
				newrows += '<input type="hidden" name="tripslist['+rowid1+'].startPoint" value="'+value+'" id ="origin'+rowid1+'A'+rowid1+'"/></td>';
			}
			else{
				
				var selectOptions = new Array();
				selectid1 = '#origin'+rowid1+' option';
				$(selectid1).each(function()
						{
					var $this = $(this);
					selectOptions.push($this.val());
					selectOptions.push($this.text());    
					// add $(this).val() to your list
						});
			value = document.getElementById('origin'+rowid1).value;
			newrows += '<td><select data-placeholder="Select..." id="origin'+rowid1+'" name="tripslist[<%=i %>].origin" style="width: 100px" class="form-control input-medium" onchange="setOrigin(this.id.substring(6))" value="'+value+'">';
			for(var c=0;c<selectOptions.length;c=c+2){
				if(selectOptions[c] == value){
					newrows += '<option value="'+selectOptions[c]+'" selected="selected">'+selectOptions[c+1]+'</option>';
				}
				else{
				newrows += '<option value="'+selectOptions[c]+'">'+selectOptions[c+1]+'</option>';
				}
				
			}
			newrows +='</select>';
			
			
			value = document.getElementById('origin'+rowid1+'A'+rowid1).value;
			newrows += '<input type="hidden" name="tripslist['+rowid1+'].startPoint" value="'+value+'" id ="origin'+rowid1+'A'+rowid1+'"/></td>';
			}
			
			value = document.getElementById('dest'+rowid1).value;
			newrows += '<td><input type="text" name = "tripslist['+rowid1+'].destination" class="form-control input-medium destination" placeholder="Enter text" style="width: 170px" id="dest'+rowid1+'" onblur="getDepotsByNo(\'triptable1\',\'0\',this.id.substring(4));" value="'+value+'"/>';
			
			value = document.getElementById('dest'+rowid1+'A'+rowid1).value;
			newrows += '<input type="hidden" name="tripslist['+rowid1+'].endPoint" value="'+value+'" id ="dest'+rowid1+'A'+rowid1+'"/></td>';
			
			value = document.getElementById('route_no'+rowid1).value;
			var selectOptions1 = new Array();
			selectid1 = '#route_no'+rowid1+' option';
			$(selectid1).each(function()
					{
				var $this = $(this);
				selectOptions1.push($this.val());
				selectOptions1.push($this.text());    
				// add $(this).val() to your list
					});
			newrows += '<td><select data-placeholder="Select..." id="route_no'+rowid1+'" name="tripslist['+rowid1+'].routeNumber.route_id" style="width: 100px" class="form-control input-small" onchange="getRouteById(\'triptable1\',this.id.substring(8),(parseInt(this.id.substring(8))+1));setroutedistance(\'triptable1\',\'0\',this.id.substring(8));" value="'+value+'">';
			for(var c=0;c<selectOptions1.length;c=c+2){
				if(selectOptions1[c] == value){
				newrows += '<option value="'+selectOptions1[c]+'" selected="selected">'+selectOptions1[c+1]+'</option>';
				}
				else{
					newrows += '<option value="'+selectOptions1[c]+'">'+selectOptions1[c+1]+'</option>';
				}
				
			}
			newrows +='</select></td>';
			
			/*value = document.getElementById('selectedRoute'+rowid1).value;
			newrows += '<input type="hidden" id = "selectedRoute'+rowid1+'" name="tripslist['+rowid1+'].selectedRoute" value="'+value+'"/></td>';*/
			
			value = document.getElementById('routelength'+rowid1).value;
			newrows += '<td><input type="text" class="form-control" name="tripslist['+rowid1+'].distance" style="width: 70px" id="routelength'+rowid1+'" onblur="setKms(\'triptable1\',\'0\',this.id.substring(11));" onkeypress="run(this);" value="'+value+'"/></td>';
			
			value = document.getElementById('fromtime'+rowid1).value;
			newrows += '<td><input type="text" class="form-control timepicker timepicker-24i" name="tripslist['+rowid1+'].starttimeString" style="width: 70px" id="fromtime'+rowid1+'"  onblur="checkTime(this);" value="'+value+'"/></td>';
			
			value = document.getElementById('totime'+rowid1).value;
			newrows += '<td><input type="text" class="form-control timepicker timepicker-24i" name="tripslist['+rowid1+'].endtimeString" id="totime'+rowid1+'" style="width: 70px" onblur="checkTime(this);validateTime(\'triptable1\',\'0\',this.id.substring(6));" value="'+value+'"/> <span class="input-group-btn"></span></td>';
			
			value = document.getElementById('journeytime'+rowid1).value;
			newrows += '<td><input type="text" class="form-control" name="tripslist['+rowid1+'].journeyTime" readonly="readonly" value="'+value+'" style="width: 70px" id="journeytime'+rowid1+'" onfocus="setJourneyTime(this.id.substring(11));setSpreadHours(\'triptable1\',\'0\',this.id.substring(11));addminstodest(\'triptable1\',this.id.substring(11));" ></td>';
			
			value = document.getElementById('break'+rowid1).value;
			newrows += '<td><select name="tripslist['+rowid1+'].breakTypeString" id="break'+rowid1+'" class="form-control input-small" onchange="enablebreakLocation(this.id.substring(5));setFromTime(this.id.substring(5));restforcrew();setJourneyTime(parseInt(this.id.substring(5))+1);setsteeringtime(\'triptable1\',\'0\',this.id.substring(5));setSpreadHours(\'triptable1\',\'0\',this.id.substring(5));" value="'+value+'">';
			
			for(k=0;k<breakOptions.length;k=k+2){
				if(value == breakOptions[k]){
				newrows += '<option value="'+breakOptions[k]+'" selected="selected">'+breakOptions[k+1]+'</option>';
				}
				else{
					newrows += '<option value="'+breakOptions[k]+'">'+breakOptions[k+1]+'</option>';
				}
				
			}
			newrows += '</select></td>';
			
			value = document.getElementById('breakduration'+rowid1).value;
			newrows += '<td><input type="text" class="form-control timepicker timepicker-24i" name="tripslist['+rowid1+'].breaktimeString" style="width: 70px" id="breakduration'+rowid1+'" onblur="checkTime(this);setFromTime(this.id.substring(13));restforcrew();setJourneyTime(parseInt(this.id.substring(13))+1);setsteeringtime(\'triptable1\',\'0\',this.id.substring(13));setSpreadHours(\'triptable1\',\'0\',this.id.substring(13));" value="'+value+'" />';
			
			value = document.getElementById('breaklocation'+rowid1).value;
			newrows += '<input type="text" class="form-control" name="tripdetails['+rowid1+'].breakLocation" style="width: 90px;display: none;" id="breaklocation'+rowid1+'" readonly="readonly" value="'+value+'"/></td>';
			
			
			/*newrows += '<td><input type="checkbox" class="group-checkable" data-set="#sample_2 .checkboxes" id="deadtrip'+rowid1+'" value="deadtrip" onclick="setDeadKm(\'triptable2\');"/></td>';*/
			
			newrows += '<td><div id="uniform-crewchange'+rowid1+'"><span>';
			newrows += '<input  type="checkbox" id="crewchange'+rowid1+'" value="true" onclick="enablecrewLocation(this.id.substring(10));setChange(this.id.substring(10),\''+formfourid+'\',\''+schid+'\',\''+nooftrips+'\');" name="tripslist['+rowid1+'].crewChangeStatus"/></span></div>';
			newrows += '<input id="__checkbox_crewchange'+rowid1+'" type="hidden" value="true" name="__checkbox_tripslist['+rowid1+'].crewChangeStatus"></td>';
			
			value = document.getElementById('crewChangelocation'+rowid1).value;
			newrows += '<td><input type="text" class="form-control" name="tripslist['+rowid1+'].crewChangeLocation" style="width: 90px" id="crewChangelocation'+rowid1+'" readonly="readonly" value="'+value+'"/></td>';
			
			newrows += '<td><div id="uniform-nighttrip'+rowid1+'"><span>';
			newrows += '<input  type="checkbox" id="nighttrip'+rowid1+'" value="true"  name="tripslist['+rowid1+'].nightTrip" onclick="enablenightLocation(this.id.substring(9));setChange(this.id.substring(9),\''+formfourid+'\',\''+schid+'\',\''+nooftrips+'\');"/></span></div>';
			newrows += '<input id="__checkbox_nighttrip'+rowid1+'" type="hidden" value="true" name="">';
			
			value = document.getElementById('nighttriplocation'+rowid1).value;
			newrows += '<input type="text" class="form-control" name="tripdetails['+rowid1+'].nightHaltLocation" style="width: 90px;display: none;" id="nighttriplocation'+rowid1+'" readonly="readonly" value="'+value+'"/></td>';
			
			/*newrows += '<td><input type="checkbox" class="group-checkable" data-set="#sample_2 .checkboxes" id="crewchange'+rowid1+'" value="crewchange" onclick="crewChange(\''+rowid1+'\',\''+formfourid+'\',\''+schid+'\'\''+nooftrips+'\')"/></td>';*/
			
			value = document.getElementById('shift0').value;
	newrows += '<td><select id="shift'+rowid1+'" class="form-control input-small" name="tripslist['+rowid1+'].shiftType.id" onchange="setShiftType(this.id.substring(5))" value="'+value+'">';
			
			for(k=0;k<shiftTypeOptions.length;k=k+2){
				if(value == shiftTypeOptions[k]){
				newrows += '<option value="'+shiftTypeOptions[k]+'" selected="selected">'+shiftTypeOptions[k+1]+'</option>';
				}
				else{
					newrows += '<option value="'+shiftTypeOptions[k]+'" >'+shiftTypeOptions[k+1]+'</option>';
				}
				
			}
			newrows += '</select></td>';
			
			value = document.getElementById('operationtype'+rowid1).value;
			newrows += '<td><select id="operationtype'+rowid1+'" class="form-control input-small" name="tripslist['+rowid1+'].operationType">';
			
			if(value == 'Obligatory'){
			newrows +=	'<option value="Obligatory" selected="selected">Obligatory</option><option value="Non-Obligatory">Non-Obligatory</option>';
			}
			else{
				newrows +=	'<option value="Obligatory">Obligatory</option><option value="Non-Obligatory" selected="selected">Non-Obligatory</option>';
			}

			newrows += '</select></td>';
			
			value = document.getElementById('remarks'+rowid1).value;
			newrows += '<td><input type="text" class="form-control" placeholder="Enter text" style="width: 100px" id="remarks'+rowid1+'" name="tripslist['+rowid1+'].remarks" value="'+value+'"></td>';
			
			value = document.getElementById('tripid'+rowid1).value;
			newrows += '<td><a href="javascript:void(0)" class="btn default btn-xs black" id="del'+rowid1+'" onclick="deleteRow(this,this.id.substring(3),\'triptable1\',\''+value+'\');restforcrew();" > <i class="fa fa-trash-o"></i> Delete </a> &nbsp; <a href="javascript:void(0)" id="add'+rowid1+'" class="btn default btn-xs purple" onclick="addtrip(this.id.substring(3),\'triptable1\',\''+formfourid+'\',\''+schid+'\',\''+nooftrips+'\');"> <i class="fa fa-edit"></i> Add </a></td>';
			
			newrows += "</tr>";
			rowid1 = parseInt(rowid1)+1;
			cnt = parseInt(cnt)+1;
		
			enableCrewChange(totalrows);
			
			
		}
		
		$('#triptable2').remove();
		$('#triptable1 > tbody > tr').eq(parseInt(id)+1).remove();
		$('#triptable1 tbody').append(newrows);
		Metronic.init();
		disableLastRow();
		/*$("#nighttrip"+id).attr("disabled", false);
		$("#crewchange"+id).attr("disabled", false);	*/

		document.getElementById('crewChangeId').value = '';
		document.getElementById('crewchange').value = 'no';
		document.getElementById('nightchange').value = 'no';
		setSpreadHours('triptable1','0','0');
		setsteeringtime('triptable1','0','0');
		
		document.getElementById('spread2').value = '00:00';
		document.getElementById('steering2').value = '00:00';
		document.getElementById('ot2').value = '00:00';
		
		document.getElementById('rest2').value = '';
		addminstodest("triptable1",id);
		rowid1 = parseInt(id)+1;
		
		for(i=0;i<triptable2rows;i++){
			$("#dest"+rowid1).autocomplete("", {
				url : PATH + '/GetDepot.action',
				minChars : 2,
				max : 50,
				multiple : false,
				autoFill : true,
				formatItem: function(data) {
	                return data[1];
	            },
	            formatResult: function(data) {
	                return data[1];
	            }
			}).result(function(event, data) {
	            if (data) {
	                var idd = this.id +'A'+this.id.substring(4);
	                document.getElementById(idd).value = data[0];
	                getGroupingStops(this.id.substring(4));
	            }
	        });
		
			
			$(".timepicker-24i").inputmask("mask", {
				"mask": "99:99"
	        });
			rowid1 = parseInt(rowid1)+1;
		}
		document.getElementById('schkms2').value = '0';
		document.getElementById('dead2').value = '0';
		setKms('triptable1','0',id);
	}
	}
	
	restforcrew();
}

function submitform(){
	
	var tablerows = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	var myElem = document.getElementById('triptable2');
	
	if(myElem !=null){
		var table2rows =   document.getElementById("triptable2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		tablerows = tablerows+table2rows;
	}
	for(i=0;i<(parseInt(tablerows)-1);i++){
		var validate = true;
		var startpoint = document.getElementById("origin"+i+"A"+i).value;
		var endpoint = document.getElementById("dest"+i+"A"+i).value;
		var startstop = document.getElementById("origin"+i).value;
		var endstop = document.getElementById("dest"+i).value;
		var route = document.getElementById("route_no"+i).value;
		//alert('startpoint -------> '+startpoint+'  endpoint -----> '+endpoint+'  route ---> '+route+'  || isEmpty(endpoint)  ---> '+isEmpty(endpoint));
		if((isEmpty(endstop) && endpoint == '0' && route == '0') || (!isEmpty(endstop) && endpoint != '0' && route != '0') || (isEmpty(endstop) && isEmpty(endpoint) && route == '0') || (!isEmpty(endstop) && !isEmpty(endpoint) && route != '0') ){
			validate = true;
			}
		else{
			alert("Please check the details of trip no. "+(i+1));
			validate = false;
			return false;
		}
		
	}
	if(validate == true){
		$('button').attr("disabled","disabled");
		document.tripform.action = 'SaveTripDetails.action';
		 document.tripform.submit();
	}
	
	
	/*var table1rows = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	for(i=0;i<table1rows;i++){
		var validate = true;
		var startpoint = document.getElementById("origin"+i+"A"+i).value;
		var endpoint = document.getElementById("dest"+i+"A"+i)value;
		var route = document.getElementById("triptable1").value;
		if(startpoint == '0' ){
			validate = false;
		}
		if(endpoint == '0' ){
			validate = false;
		}
		if(route == '0' ){
			validate = false;
		}
		if(validate == false){
			alert("Please check the details of trip no. "+i);
			return false;
		}
	}
	if(validate == true){*/
	 /*document.tripform.action = 'SaveTripDetails.action';
	 document.tripform.submit();*/
	//}
}

function enablebreakLocation(id){
	
	var value = document.getElementById('break'+id).value;
	var endstopvalue = document.getElementById('dest'+id).value;
	var elt = document.getElementById('break'+id);
	var textvalue = elt.options[elt.selectedIndex].text;


	if(textvalue.toLowerCase().replace(" ", "").indexOf('waitingtime') != -1 )
	{
		 $("#breaklocation"+id).attr("readonly", true);
		 $("#breaklocation"+id).val('');
		 //document.getElementById('breaklocation'+id).value = '';
		// $("#breakduration"+id).val('00:05');
		 setTimeout(function() {
			 $("#breakduration"+id).val('00:05');
		    }, 10);
		 
	}
	else{
		var splitString = value.split('-');
		//$("#breakduration"+id).val(splitString[0]);
		$("#breakduration"+id).val(splitString[0]);
		$("#breakduration"+id).attr("readonly", false);
		$("#breaklocation"+id).attr("readonly", false);
		//$("#breaklocation"+id).val(endstopvalue);
		 setTimeout(function() {
			 $("#breaklocation"+id).val(endstopvalue);
		    }, 10);
		
	//document.getElementById('breaklocation'+id).value = endstopvalue;
	}

}

function enablecrewLocation(id){
	
	var endstopvalue = document.getElementById('dest'+id).value;
	
	if($("#crewchange"+id).is(':checked')){
	 $("#crewChangelocation"+id).attr("readonly", false);
	 document.getElementById('crewChangelocation'+id).value = endstopvalue;
	}
	else{
		document.getElementById('crewChangelocation'+id).value = '';
		$("#crewChangelocation"+id).attr("readonly", true);	
	}

}

function enablenightLocation(id){
	var endstopvalue = document.getElementById('dest'+id).value;
	if($("#nighttrip"+id).is(':checked')){	 
		$("#nighttriplocation"+id).attr("readonly", false);
		document.getElementById('nighttriplocation'+id).value = endstopvalue;
	}
	else{
		$("#nighttriplocation"+id).attr("readonly", true);
		document.getElementById('nighttriplocation'+id).value = '';
	}

}

function selectForCharter(id){
	
	getDeadTripStops(id);
	
	var newOptions='';
	var charterOptions = new Array();
	var otheroptions = new Array();
	$("#charter option").each(function()
			{
		var $this = $(this);
		
			charterOptions.push($this.val());
			charterOptions.push($this.text());

		// add $(this).val() to your list
			});

	$("#charter option").each(function()
			{
		var $this = $(this);
		
		if($this.text() == 'BMTC'){
			otheroptions.push($this.val());
			otheroptions.push($this.text());
		}
		
		// add $(this).val() to your list
			});

	

	var value = $('#trip'+id+' option:selected').text();;
	var select = $('#customer'+id);
	select.empty();
	if(value == 'Chartered Service'){
		newOptions = '';
		for(var k=0;k<charterOptions.length;k=k+2){
			newOptions += '<option value="'+charterOptions[k]+'">'+charterOptions[k+1]+'</option>';
			
		}
		$("#customer"+id).append(newOptions);
	
	}
	else{
		
		newOptions = '';
		for(var a=0;a<otheroptions.length;a=a+2){
			newOptions += '<option value="'+otheroptions[a]+'">'+otheroptions[a+1]+'</option>';
			
		}
		$("#customer"+id).append(newOptions);
	}
}

function getDeadTripStops(id){
	//$('#origin0').data().autocomplete.term = null;
	var value = $('#trip'+id+' option:selected').text();

	if(value.toLowerCase().replace(" ", "").indexOf('deadtrip') != -1 ){
		$('#deadtripval'+id).val('1');
		document.getElementById("deadtripval"+id).value='1';
		if(id == 0){
			$("#origin0").unautocomplete();
			$("#origin0").autocomplete("", {
				open : function() {
					$('#div .ui-menu').width(2000);
				},
				url : PATH + '/GetDeadStops.action',
				minChars : 2,
				max : 50,
				multiple : false,
				autoFill : true,
				formatItem: function(data) {
	                return data[1];
	            },
	            formatResult: function(data) {
	                return data[1];
	            }
			}).result(function(event, data) {
	            if (data) {
	                var idd = this.id + 'A0';
	                document.getElementById(idd).value = data[0];
	          
	            }
	        });
		
			$("#dest0").unautocomplete();
			$("#dest0").autocomplete("", {
				open : function() {
					$('#div .ui-menu').width(2000);
				},
				url : PATH + '/GetDeadStops.action',
				minChars : 2,
				max : 50,
				multiple : false,
				autoFill : true,
				formatItem: function(data) {
	                return data[1];
	            },
	            formatResult: function(data) {
	                return data[1];
	            }
			}).result(function(event, data) {
	            if (data) {
	                var idd = this.id + 'A0';
	                document.getElementById(idd).value = data[0];
	                getGroupingStops(this.id.substring(4));
	            }
	        });
		
			
		}
		else{
			$("#dest"+id).unautocomplete();
			$("#dest"+id).autocomplete("", {
				open : function() {
					$('#div .ui-menu').width(2000);
				},
				url :  PATH + '/GetDeadStops.action',
				minChars : 2,
				max : 50,
				multiple : false,
				autoFill : true,
				formatItem: function(data) {
	                return data[1];
	            },
	            formatResult: function(data) {
	                return data[1];
	            }
			}).result(function(event, data) {
	            if (data) {
	                var idd = this.id +'A'+this.id.substring(4);
	                document.getElementById(idd).value = data[0];
	                getGroupingStops(this.id.substring(4));
	            }
	        });
		
		}
		if(id == '0'){
			document.getElementById('origin'+id).value='';
		}
		
		var elementid = '#route_no'+id;
		$(elementid).empty();
    	$(elementid).append('<option value="0">Select</option>');
		document.getElementById('dest'+id).value='';
		document.getElementById('dest'+id+'A'+id).value='0';
		document.getElementById('routelength'+id).value='0.0';
		var rows = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		var myElem = document.getElementById('triptable2');
		setKms('triptable1','0','0');
		if(myElem !=null){
		setKms('triptable2',parseInt(rows)-1,'0');
		}
	}
	else{
		$('#deadtripval'+id).val('0');
		if(id == 0){
			$("#origin0").unautocomplete();
			$("#origin0").autocomplete("", {
				open : function() {
					$('#div .ui-menu').width(2000);
				},
				url : PATH + '/GetDepot.action',
				minChars : 2,
				max : 50,
				multiple : false,
				autoFill : true,
				formatItem: function(data) {
	                return data[1];
	            },
	            formatResult: function(data) {
	                return data[1];
	            }
			}).result(function(event, data) {
	            if (data) {
	                var idd = this.id + 'A0';
	                document.getElementById(idd).value = data[0];
	          
	            }
	        });
			$("#dest0").unautocomplete();
			$("#dest0").autocomplete("", {
				open : function() {
					$('#div .ui-menu').width(2000);
				},
				url : PATH + '/GetDepot.action',
				minChars : 2,
				max : 50,
				multiple : false,
				autoFill : true,
				formatItem: function(data) {
	                return data[1];
	            },
	            formatResult: function(data) {
	                return data[1];
	            }
			}).result(function(event, data) {
	            if (data) {
	                var idd = this.id + 'A0';
	                document.getElementById(idd).value = data[0];
	                getGroupingStops(this.id.substring(4));
	            }
	        });
		
		}
		else{
			$("#dest"+id).unautocomplete();
			$("#dest"+id).autocomplete("", {
				open : function() {
					$('#div .ui-menu').width(2000);
				},
				url : PATH + '/GetDepot.action',
				minChars : 2,
				max : 50,
				multiple : false,
				autoFill : true,
				formatItem: function(data) {
	                return data[1];
	            },
	            formatResult: function(data) {
	                return data[1];
	            }
			}).result(function(event, data) {
	            if (data) {
	                var idd = this.id +'A'+this.id.substring(4);
	                document.getElementById(idd).value = data[0];
	                getGroupingStops(this.id.substring(4));
	            }
	        });
		
		}
		if(id == '0'){
			document.getElementById('origin'+id).value='';
		}
		
		var elementid = '#route_no'+id;
		$(elementid).empty();
    	$(elementid).append('<option value="0">Select</option>');
		document.getElementById('dest'+id).value='';
		document.getElementById('dest'+id+'A'+id).value='0';
		document.getElementById('routelength'+id).value='0.0';
		var rows = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		var myElem = document.getElementById('triptable2');
		setKms('triptable1','0','0');
		if(myElem !=null){
		setKms('triptable2',parseInt(rows)-1,'0');
		}
	}
	
	
}

function trim(str) {
	  return str.replace(/^\s+|\s+$/g, '');
	}


	function isEmpty(str) {
	  str = trim(str);
	  return ((str == null) || (str.length == 0));
	}
	
	function checkTime(ele) {
		 var id = ele.id;
		 
		  var value = document.getElementById(id).value;
		  var newtime='';
		  var arr = value.split(':');
		  var newhour='';
		  var newmin = '';
		  var res1 = arr[0].split('');
		 // alert('Res[0]-------> '+res1[0]+'  Res[1]------> '+res1[1]);
		  if(res1[0] == '_'){
			  newhour = '0'+res1[1];
		  }
		  else if(res1[1] == '_'){
			  newhour = '0'+res1[0];
		  }
		  
		  else if(res1[0] == undefined && res1[1] == undefined){
			  newhour = '0'+'0';
		  }
		  else{
			  newhour = res1[0]+res1[1];
		  }
		  if(isNaN(res1[1]) && isNaN(res1[0])){
				//alert('Inside------ ');
			  newhour = '0'+'0';
			  }
		  
		if(arr[1] == undefined){
			newmin = '0'+'0';
		}
		else{
		  var res2 = arr[1].split('');
		 // alert('Res[0]------->'+arr[1]+'Res[0]-------> '+res2[0]+'  Res[1]------> '+res2[1]+'  hh----'+isNaN(res2[1]));
		  if(res2[0] == '_'){
			  newmin = '0'+res2[1];
		  }
		  else  if(res2[1] == '_'){
			  newmin = '0'+res2[0];
		  }
		  else{
			  newmin = res2[0] + res2[1];
		  }
		  if(isNaN(res2[1]) && isNaN(res2[0])){
			//alert('Inside------ ');
			  newmin = '0'+'0';
		  }
		  
		}
		if(value == '24:00'){
			newtime = '24:00';
		}
		else{
		  if(parseInt(newhour)>23){
			  newtime = '24:';
		  }
		  else{
			  newtime=newhour+':';
		  }
		  
		  if(parseInt(newmin)>59){
			  newtime += '00';
		  }
		  else{
			  newtime += newmin;
		  }
		}
		$("#"+id).val(newtime);
		  //document.getElementById(id).value = newtime;
		}

	function addtrip(id,tableid,formfourid,schid,nooftrips){
		
		if(!($("#crewchange"+id).is(':checked')) && !$("#nighttrip"+id).is(':checked')){
			$(".transparentCover").show();
			$(".loading").show();
			/*setTimeout(function() {
				$(".transparentCover").show();
				$(".loading").show();
		    }, 500);*/
			
		     
			
			var crewchangeid = document.getElementById("crewChangeId").value;
		var newrows = '';
		var firstid ='';
		var breakOptions = new Array();
		$("#break0 option").each(function()
				{
			var $this = $(this);
			breakOptions.push($this.val());
			breakOptions.push($this.text());    
			// add $(this).val() to your list
				});

		
		var tripOptions = new Array();
		
		$("#trip0 option").each(function()
				{
			var $this = $(this);
			tripOptions.push($this.val());
			tripOptions.push($this.text());    
			// add $(this).val() to your list
				});
		
		var customerOptions = new Array();
		
		$("#customer0 option").each(function()
				{
			var $this = $(this);
			customerOptions.push($this.val());
			customerOptions.push($this.text());    
			// add $(this).val() to your list
				});
		
	var shiftTypeOptions = new Array();
		
		$("#shift0 option").each(function()
				{
			var $this = $(this);
			shiftTypeOptions.push($this.val());
			shiftTypeOptions.push($this.text());    
			// add $(this).val() to your list
				});
		
		var rowid1 = parseInt(id)+1;
		var triptablerows='';
		var listid='';
		if(tableid == 'triptable1'){
		triptablerows = document.getElementById('triptable1').getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		listid = parseInt(triptablerows);
		firstid = '0';
		}
		else{
		var triptable1 = document.getElementById('triptable1').getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		var triptable2 = document.getElementById('triptable2').getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		triptablerows = parseInt(triptable1)+parseInt(triptable2);
		listid = parseInt(triptablerows)-1;
		firstid = parseInt(triptable1)-1;
		}
		var rows = parseInt(rowid1)-1;
		var cnt = document.getElementById("listitem"+rows).value;
		newrows += "<tr id='"+rowid1+"'>";
		
		newrows += '<td><input type="text" name = "tripslist['+listid+'].listItemNumber" class="form-control" value="'+(parseInt(cnt)+1)+'" style="width: 50px" id="listitem'+rowid1+'" readonly="readonly" /></td>';
		
		newrows += '<td>';
		newrows += '<input type="hidden" id="formFour'+rowid1+'" name="tripslist['+listid+'].formFour.id" value="'+formfourid+'"/>';
		newrows += '<input type="hidden" id="scheduleid'+rowid1+'" name="tripslist['+listid+'].scheduleNumber.schedule_id" value="'+schid+'"/>';
		newrows += '<input type="hidden" id="noofTrips'+rowid1+'" name="tripslist['+listid+'].noofTrips" value="'+nooftrips+'"/>';
		newrows += '<input type="hidden" name="tripslist['+listid+'].id" id="tripid'+rowid1+'" value="0"/>';
		//console.log("ID"+id);
		
		newrows +=	'<input type="text" name = "tripslist['+listid+'].tripNumber" class="form-control" value="'+(parseInt(rowid1)+1)+'" style="width: 50px" id="id'+rowid1+'" readonly="readonly" /></td>';
		
		
		newrows += '<td><select id="trip'+rowid1+'" class="form-control input-small" name="tripslist['+listid+'].tripType.id" onchange="setTrip(this.id.substring(4)); getDeadTripStops(this.id.substring(4));selectForCharter(this.id.substring(4));">';
		
		for(l=0;l<tripOptions.length;l=l+2){
			newrows += '<option value="'+tripOptions[l]+'">'+tripOptions[l+1]+'</option>';
			
		}
		newrows += '</select>';
		newrows += '<input id="deadtripval'+rowid1+'" type="hidden" value="0" name="tripslist['+listid+'].isDreadTrip"></td>';
		/*//newrows += '<input type="hidden" id = "selectedTrip'+rowid1+'" name="tripslist['+listid+'].selectedTrip"/></td>';
*/		
		newrows += '<td><select id="customer'+rowid1+'" class="form-control input-small" name="tripslist['+listid+'].customer.id">';
		
		for(d=0;d<customerOptions.length;d=d+2){
			newrows += '<option value="'+customerOptions[d]+'">'+customerOptions[d+1]+'</option>';
			
		}
		newrows += '</select></td>';

		
		/*newrows += '<td><div id="uniform-deadtrip'+rowid1+'"><span>';
		newrows += '<input  type="checkbox" id="deadtrip'+rowid1+'" value="true"  name="tripslist['+listid+'].deadTrip" onclick="getDeadTripStops(this.id.substring(8));"/></span></div>';
		newrows += '<input id="__checkbox_deadtrip'+rowid1+'" type="hidden" value="false" name="__checkbox_tripslist['+listid+'].deadTrip"></td>';*/
		
		
		if(cnt==0){
			newrows += '<td><select data-placeholder="Select..." class="form-control input-medium" id="origin'+rowid1+'" name="tripslist[<%=i %>].origin" style="width: 100px" onchange="setOrigin(this.id.substring(6));getDepotsByNo(\''+tableid+'\',\''+firstid+'\',this.id.substring(6));"><option value="0">Select</option></select>';
			newrows += '<input type="hidden" name="tripslist['+listid+'].startPoint" value="0" id ="origin'+rowid1+'A'+rowid1+'"/></td>';
		}
		else{
		newrows += '<td><select data-placeholder="Select..." class="form-control input-medium" id="origin'+rowid1+'" name="tripslist[<%=i %>].origin" style="width: 100px" onchange="setOrigin(this.id.substring(6));getDepotsByNo(\''+tableid+'\',\''+firstid+'\',this.id.substring(6));"><option value="0">Select</option></select>';
		newrows += '<input type="hidden" name="tripslist['+listid+'].startPoint" value="0" id ="origin'+rowid1+'A'+rowid1+'"/></td>';
		}
		newrows += '<td><input type="text" name = "tripslist['+listid+'].destination" class="form-control input-medium destination" placeholder="Enter text" style="width: 170px" id="dest'+rowid1+'" onblur="getDepotsByNo(\''+tableid+'\',\''+firstid+'\',this.id.substring(4));" />';
		newrows += '<input type="hidden" name="tripslist['+listid+'].endPoint" value="0" id ="dest'+rowid1+'A'+rowid1+'"/></td>';
		newrows += '<td><select data-placeholder="Select..." class="form-control input-small" id="route_no'+rowid1+'" name="tripslist['+listid+'].routeNumber.route_id" style="width: 100px" onchange="getRouteById(\''+tableid+'\',this.id.substring(8),(parseInt(this.id.substring(8))+1));setroutedistance(\''+tableid+'\',\''+firstid+'\',this.id.substring(8));"> <option value="0">Select</option> </select> </td>';
		newrows += '<input type="hidden" id = "selectedRoute'+rowid1+'" name="tripslist['+listid+'].selectedRoute"/></td>';
		newrows += '<td><input type="text" class="form-control" name="tripslist['+listid+'].distance" style="width: 70px" id="routelength'+rowid1+'"  value="0.0" onblur="setKms(\''+tableid+'\',\''+firstid+'\',this.id.substring(11));" onkeypress="run(this);"/></td>';
		newrows += '<td><input type="text" class="form-control timepicker timepicker-24i" name="tripslist['+listid+'].starttimeString" style="width: 70px" id="fromtime'+rowid1+'" onblur="checkTime(this);" value="00:00"/></td>';
		newrows += '<td><input type="text" class="form-control timepicker timepicker-24i" name="tripslist['+listid+'].endtimeString" id="totime'+rowid1+'" style="width: 70px" onblur="checkTime(this);setJourneyTime(this.id.substring(6));setSpreadHours(\''+tableid+'\',\''+firstid+'\',this.id.substring(6));addminstodest(\''+tableid+'\',this.id.substring(6));setsteeringtime(\''+tableid+'\',\''+firstid+'\',this.id.substring(6));" value="00:00"/> <span class="input-group-btn"></span></td>';
		newrows += '<td><input type="text" class="form-control timepicker timepicker-24i" name="tripslist['+listid+'].journeyTime" readonly="readonly" value="00:00" style="width: 70px" id="journeytime'+rowid1+'" onfocus="setJourneyTime(this.id.substring(11));setSpreadHours(\''+tableid+'\',\''+firstid+'\',this.id.substring(11));addminstodest(\''+tableid+'\',this.id.substring(11));setsteeringtime(\''+tableid+'\',\''+firstid+'\',this.id.substring(11));"></td>';
		newrows += '<td><select name="tripslist['+listid+'].breakTypeString" id="break'+rowid1+'" class="form-control input-small" onchange="enablebreakLocation(this.id.substring(5));setFromTime(this.id.substring(5));restforcrew();setJourneyTime(parseInt(this.id.substring(5))+1);setsteeringtime(\''+tableid+'\',\''+firstid+'\',this.id.substring(5));setSpreadHours(\''+tableid+'\',\''+firstid+'\',this.id.substring(5));">';
		
		for(k=0;k<breakOptions.length;k=k+2){
			newrows += '<option value="'+breakOptions[k]+'">'+breakOptions[k+1]+'</option>';
			
		}
			
		newrows += '</select></td>';	
		
		
		newrows += '<td><input type="text" class="form-control timepicker timepicker-24i" name="tripslist['+listid+'].breaktimeString" style="width: 70px" id="breakduration'+rowid1+'"  value="00:05" onblur="checkTime(this);setFromTime(this.id.substring(13));restforcrew();setJourneyTime(parseInt(this.id.substring(5))+1);"/>';
		newrows += '<input type="text" class="form-control" name="tripdetails['+listid+'].breakLocation" style="width: 90px;display: none;" id="breaklocation'+rowid1+'" readonly="readonly" value=""/></td>';
		
		newrows += '<td><div id="uniform-crewchange'+rowid1+'"><span>';
		newrows += '<input  type="checkbox" id="crewchange'+rowid1+'" value="true" onclick="enablecrewLocation(this.id.substring(10));setChange(this.id.substring(10),\''+formfourid+'\',\''+schid+'\',\''+nooftrips+'\')" name="tripslist['+listid+'].crewChangeStatus"/></span></div>';
		newrows += '<input id="__checkbox_crewchange'+rowid1+'" type="hidden" value="true" name="__checkbox_tripslist['+listid+'].crewChangeStatus"></td>';
		
		
		newrows += '<td><input type="text" class="form-control" name="tripslist['+listid+'].crewChangeLocation" style="width: 90px" id="crewChangelocation'+rowid1+'" readonly="readonly" value=""/></td>';
		
		newrows += '<td><div id="uniform-nighttrip'+rowid1+'"><span>';
		newrows += '<input  type="checkbox" id="nighttrip'+rowid1+'" value="true"  name="tripslist['+listid+'].nightTrip" onclick="enablenightLocation(this.id.substring(9));setChange(this.id.substring(9),\''+formfourid+'\',\''+schid+'\',\''+nooftrips+'\')"/></span></div>';
		newrows += '<input id="__checkbox_nighttrip'+rowid1+'" type="hidden" value="true" name="">';
		
		newrows += '<input type="text" class="form-control" name="tripdetails['+listid+'].nightHaltLocation" style="width: 90px;display: none;" id="nighttriplocation'+rowid1+'" readonly="readonly" value=""/></td>';
		
		newrows += '<td><select id="shift'+rowid1+'" class="form-control input-small" name="tripslist['+listid+'].shiftType.id" onchange="setShiftType(this.id.substring(5))">';
		var shifttypevalue = document.getElementById('shift'+(parseInt(rowid1)-1)).value;
		
		
		for(k=0;k<shiftTypeOptions.length;k=k+2){
			
			if(shifttypevalue == shiftTypeOptions[k]){
				newrows += '<option value="'+shiftTypeOptions[k]+'" selected="selected">'+shiftTypeOptions[k+1]+'</option>';	
			}
			else{
			newrows += '<option value="'+shiftTypeOptions[k]+'">'+shiftTypeOptions[k+1]+'</option>';
			}
			
		}
		newrows += '</select></td>';
		
		newrows += '<td><select id="operationtype'+rowid1+'" class="form-control input-small" onchange="setTrip(this.id.substring(4)); name="tripslist['+listid+'].operationType">';
		newrows +=	'<option value="Obligatory">Obligatory</option><option value="Non-Obligatory">Non-Obligatory</option>';	
		newrows += '</select></td>';
		
		/*newrows += '<td><input type="checkbox" class="group-checkable" data-set="#sample_2 .checkboxes" id="deadtrip'+rowid1+'" value="deadtrip" onclick="setDeadKm(\'triptable1\');"/></td>';
		newrows += '<td><input type="checkbox" class="group-checkable" data-set="#sample_2 .checkboxes" id="crewchange'+rowid1+'" value="crewchange" onclick="crewChange(\''+rowid1+'\',\''+formfourid+'\',\''+schid+'\',\''+nooftrips+'\')"/></td>';*/
		newrows += '<td><input type="text" class="form-control" placeholder="Enter text" style="width: 100px" id="remarks'+rowid1+'" name="tripslist['+listid+'].remarks"></td>';
		newrows += '<td><a href="javascript:void(0)" class="btn default btn-xs black" id="del'+rowid1+'" onclick="deleteRow(this,this.id.substring(3));"> <i class="fa fa-trash-o"></i> Delete </a> &nbsp; <a href="javascript:void(0)" id="add'+rowid1+'" class="btn default btn-xs purple" onclick="addtrip(this.id.substring(3),\''+tableid+'\',\''+formfourid+'\',\''+schid+'\',\''+nooftrips+'\');"> <i class="fa fa-edit"></i> Add </a></td>';
		
		newrows += "</tr>";
		newrows += '<script>Metronic.init();</script>';
		
		var destid = '#dest'+rowid1;
		
var myElem = document.getElementById('triptable2');
		var crewid = document.getElementById('crewChangeId').value;
		if(myElem !=null){
			var triptable1rows = document.getElementById('triptable1').getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
			var triptable2rows = document.getElementById('triptable2').getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
			
			var pos='0';
			
			triptable1rows = parseInt(triptable1rows)-1;
			var totalrows = parseInt(triptable1rows)+parseInt(triptable2rows);
			setid(parseInt(id),totalrows,tableid);
			for(i=triptable1rows;i<id;i++){
				pos = parseInt(pos)+1;
			}
			var id1 = triptable1rows;
		}
		else{
			var triptable1rows = document.getElementById('triptable1').getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
			setid(id,triptable1rows,tableid);
		}
		if(tableid == 'triptable1'){
			$('#triptable1 > tbody > tr').eq(id).after(newrows);
		}
		else{
			
		$('#triptable2 > tbody > tr').eq(pos).after(newrows);
		
		}
		document.getElementById('id'+rowid1).value = parseInt(document.getElementById('id'+id).value)+1;
		document.getElementById('origin'+rowid1+'A'+rowid1).value = document.getElementById('dest'+(parseInt(rowid1)-1)+'A'+(parseInt(rowid1)-1)).value;
		
		document.getElementById('origin'+rowid1).value = document.getElementById('dest'+(parseInt(rowid1)-1)).value;
		settripno();
		setName();
		$(destid).autocomplete("", {
			url : PATH + '/GetDepot.action',
			minChars : 2,
			max : 50,
			multiple : false,
			autoFill : true,
			formatItem: function(data) {
                return data[1];
            },
            formatResult: function(data) {
                return data[1];
            }
		}).result(function(event, data) {
            if (data) {
            	 var idd = this.id + 'A'+this.id.substring(4);
                document.getElementById(idd).value = data[0];
                getGroupingStops(this.id.substring(4));
            }
        });
		
		$(".timepicker-24i").inputmask("mask", {
			"mask": "99:99"
        });
		disableLastRow();
		getGroupingStops(id);
		
		var crewchangeid = document.getElementById("crewChangeId").value;
		if(!isEmpty(crewchangeid)){
			$("#nighttrip"+rowid1).attr("disabled", true);
			$("#crewchange"+rowid1).attr("disabled", true);
		}
		disableLastRow();
		if(myElem !=null){
			var triptable1rows = document.getElementById('triptable1').getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
			var triptable2rows = document.getElementById('triptable2').getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
			
			var pos='0';
			
			triptable1rows = parseInt(triptable1rows)-1;
			var totalrows = parseInt(triptable1rows)+parseInt(triptable2rows);
			disableCrewChange(crewchangeid,totalrows);
		}
		/*else{
			var triptable1rows = document.getElementById('triptable1').getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
			disableCrewChange(crewchangeid,triptable1rows);
		}*/
	}
		setTimeout(function() {
			$(".transparentCover").hide();
		     
			$(".loading").hide();
	    }, 1000);
		//enableLink();
	}
	
	function settripno(){
		var table1rows = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		var table2rows = 0;
		var myElem = document.getElementById('triptable2');
		if(myElem !=null){
		table2rows = document.getElementById("triptable2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		table1rows = parseInt(table1rows) -1;
		}
		var cnt = 0;
		var newid= '';
		var elementid='';
		for(var i=1;i<=parseInt(table1rows);i++){
			elementid = 'id'+cnt;
			var myele = document.getElementById(elementid);
			if(myele != null){
				$('#'+elementid).val(parseInt(i));
			//document.getElementById(elementid).value=parseInt(i);
			}
			cnt = parseInt(cnt)+1;
		}
		
		for(var j=1;j<=parseInt(table2rows);j++){
			elementid = 'id'+cnt;
			var myele1 = document.getElementById(elementid);
			if(myele1 != null){
			document.getElementById(elementid).value=parseInt(j);
			$('#'+elementid).val(parseInt(j));
			}
			cnt = parseInt(cnt)+1;
		}
		
	}
	function setid(id,table1rows,tableid){
		
		/*var table1rows = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;*/
		//alert('id -------> '+id+' table1rows------> '+table1rows+'  tableid------> '+tableid);
		var newid= parseInt(id)+1;
		var elementid='';
		
		for(g=(parseInt(table1rows)-1);g>id;g--){
			
			elementid = '#formFour'+g;
			newid = 'formFour'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#scheduleid'+g;
			newid = 'scheduleid'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#noofTrips'+g;
			newid = 'noofTrips'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#tripid'+g;
			newid = 'tripid'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#listitem'+g;
			newid = 'listitem'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			//document.getElementById(newid).value=parseInt(g)+2;
			$('#'+newid).val(parseInt(g)+2);
			
			
			elementid = '#id'+g;
			newid = 'id'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			
			elementid = '#trip'+g;
			newid = 'trip'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#selectedTrip'+g;
			newid = 'selectedTrip'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			
			elementid = '#customer'+g;
			newid = 'customer'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			
			elementid = '#uniform-deadtrip'+g;
			newid = 'uniform-deadtrip'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#__checkbox_deadtrip'+g;
			newid = '__checkbox_deadtrip'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#deadtrip'+g;
			newid = 'deadtrip'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#deadtripval'+g;
			newid = 'deadtripval'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#origin'+g;
			newid = 'origin'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
		
			elementid = '#origin'+g+'A'+g;
			newid = 'origin'+(parseInt(g)+1)+'A'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#dest'+g;
			newid = 'dest'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#dest'+g+'A'+g;
			newid = 'dest'+(parseInt(g)+1)+'A'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#route_no'+g;
			newid = 'route_no'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#routelength'+g;
			newid = 'routelength'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			
			elementid = '#fromtime'+g;
			newid = 'fromtime'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#totime'+g;
			newid = 'totime'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#journeytime'+g;
			newid = 'journeytime'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#break'+g;
			newid = 'break'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			
			elementid = '#selectedRoute'+g;
			newid = 'selectedRoute'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			
			elementid = '#breakduration'+g;
			newid = 'breakduration'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#breaklocation'+g;
			newid = 'breaklocation'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#uniform-crewchange'+g;
			newid = 'uniform-crewchange'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#crewchange'+g;
			newid = 'crewchange'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#__checkbox_crewchange'+g;
			newid = '__checkbox_crewchange'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			
			elementid = '#crewChangelocation'+g;
			newid = 'crewChangelocation'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#uniform-nighttrip'+g;
			newid = 'uniform-nighttrip'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#nighttrip'+g;
			newid = 'nighttrip'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#__checkbox_nighttrip'+g;
			newid = '__checkbox_nighttrip'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#nighttriplocation'+g;
			newid = 'nighttriplocation'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#shift'+g;
			newid = 'shift'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#operationtype'+g;
			newid = 'operationtype'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#remarks'+g;
			newid = 'remarks'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#del'+g;
			newid = 'del'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
			
			elementid = '#add'+g;
			newid = 'add'+(parseInt(g)+1);
			$(elementid).attr('id',newid);
		}
	}
	
	function validateTime(tableid,startid,id){
		var schtype = document.getElementById('schtype').value;
		var fromtime = document.getElementById('fromtime'+id).value;
		var totime = document.getElementById('totime'+id).value;
		var fromsplit = fromtime.split(":");
		var tosplit = totime.split(":");
		var frommin	= t2m(fromtime);
		var tomin = t2m(totime);
		var result = subtractMinutes(totime,frommin);
		var intermin = t2m(result);
		var finalresult = subtractMinutes("24:00",intermin);
		var resultmin = t2m(finalresult); 
		var crewchangeid = document.getElementById('crewChangeId').value;
		var r;
		var flag = false;
		if(Number(resultmin)<720){
			flag = true;
		}
		if(Number(fromsplit[0])>Number(tosplit[0])){
			if(schtype.toLowerCase().replace(" ", "").indexOf('ns') != -1 || schtype.toLowerCase().replace(" ", "").indexOf('no') != -1){
				r = confirm("Press OK if it is Day 2.");
				if(r==false){
				document.getElementById('journeytime'+id).value="00:00";
				document.getElementById('totime'+id).value;
				if(schtype.toLowerCase().replace(" ", "").indexOf('ns') != -1 ){
					if(!flag){
						alert('End Time is Smaller Than Start Time');
						document.getElementById('fromtime'+id).focus();
						setTimeout(function() {
							document.getElementById('totime'+id).focus();
					    }, 100);
						return false;
					}
				}
				else{
				alert('End Time is Smaller Than Start Time');
				document.getElementById('fromtime'+id).focus();
				setTimeout(function() {
					document.getElementById('totime'+id).focus();
			    }, 100);
				return false;
				}
				}
				
			}
			else{
			
			document.getElementById('journeytime'+id).value="00:00";
			document.getElementById('totime'+id).value;
			if(schtype.toLowerCase().replace(" ", "").indexOf('ns') != -1 ){
				if(!flag){
					alert('End Time is Smaller Than Start Time');
					document.getElementById('fromtime'+id).focus();
					setTimeout(function() {
						document.getElementById('totime'+id).focus();
				    }, 100);
					return false;
				}
			}
			else{
			alert('End Time is Smaller Than Start Time');
			document.getElementById('fromtime'+id).focus();
			setTimeout(function() {
				document.getElementById('totime'+id).focus();
		    }, 100);
			return false;
			}
			
			}
		}
		
		if(Number(fromsplit[0]) == Number(tosplit[0])){
			if(Number(fromsplit[1]) > Number(tosplit[1])){
				if(schtype.toLowerCase().replace(" ", "").indexOf('ns') != -1 || schtype.toLowerCase().replace(" ", "").indexOf('no') != -1){
					r = confirm("Press OK if it is Day 2.");
					if(r==false){
					if(schtype.toLowerCase().replace(" ", "").indexOf('ns') != -1 ){
						if(!flag){
							alert('End Time is Smaller Than Start Time');
							document.getElementById('fromtime'+id).focus();
							setTimeout(function() {
								document.getElementById('totime'+id).focus();
						    }, 100);
							return false;
						}
					}
					else{
						$("#totime"+id).focus();
						alert('End Time is Smaller Than Start Time');
						document.getElementById('journeytime'+id).value="00:00";
						document.getElementById('fromtime'+id).focus();
						setTimeout(function() {
							document.getElementById('totime'+id).focus();
					    }, 100);
						return false;
					}
					}
				}
				else{
				
				if(schtype.toLowerCase().replace(" ", "").indexOf('ns') != -1 ){
					if(!flag){
						alert('End Time is Smaller Than Start Time');
						document.getElementById('fromtime'+id).focus();
						setTimeout(function() {
							document.getElementById('totime'+id).focus();
					    }, 100);
						return false;
					}
				}
				else{
					$("#totime"+id).focus();
					alert('End Time is Smaller Than Start Time');
					document.getElementById('journeytime'+id).value="00:00";
					document.getElementById('fromtime'+id).focus();
					setTimeout(function() {
						document.getElementById('totime'+id).focus();
				    }, 100);
					return false;
				}
				
				}
			}
		}
			
			setJourneyTime(id);
			setSpreadHours(tableid,startid,id);
			addminstodest(tableid,id);
			setsteeringtime(tableid,startid,id);
			
		
	}
	
	function validateStartTime(tableid,startid,id){
		var fromtime = document.getElementById('fromtime'+id).value;
		var newid = parseInt(id)-1;
		var totime = document.getElementById('totime'+newid).value;
		var fromsplit = fromtime.split(":");
		var tosplit = totime.split(":");
		var crewchangeid = document.getElementById('crewChangeId').value;
		var r;
		if(Number(fromsplit[0])<Number(tosplit[0])){
			if(Number(crewchangeid)<Number(id)){
				
				r = confirm("Press OK if it is Day 2.");
				if(r == false){
				document.getElementById('journeytime'+id).value="00:00";
				alert('End Time of Last Trip is Greater Than Start Time of Current Trip');
				document.getElementById('journeytime'+id).focus();
				setTimeout(function() {
					document.getElementById('fromtime'+id).focus();
			    }, 1);
				
			return false;
				}
			}
			else{
				
				if(Number(crewchangeid)<Number(id)){
					r = confirm("Press OK if it is Day 2.");
					if(r==false){
					document.getElementById('journeytime'+id).value="00:00";
					alert('End Time of Last Trip is Greater Than Start Time of Current Trip');
					document.getElementById('journeytime'+id).focus();
					setTimeout(function() {
						document.getElementById('fromtime'+id).focus();
				    }, 1);
					
				return false;
					}
				}
				else{
			document.getElementById('journeytime'+id).value="00:00";
			alert('End Time of Last Trip is Greater Than Start Time of Current Trip');
			document.getElementById('journeytime'+id).focus();
			setTimeout(function() {
				document.getElementById('fromtime'+id).focus();
		    }, 1);
			
		return false;
				}
		}
		}
		
		if(Number(fromsplit[0]) == Number(tosplit[0])){
			if(parseInt(fromsplit[1]) < parseInt(tosplit[1])){
			alert('End Time of Last Trip is Greater Than Start Time of Current Trip');
				document.getElementById('journeytime'+id).focus();
				setTimeout(function() {
					document.getElementById('fromtime'+id).focus();
			    }, 100);
				document.getElementById('journeytime'+id).value="00:00";
				return false;
			}
		}
		
		
	}


	function deleteRow(ele,id,tableid,tripid){
		if(!($("#crewchange"+id).is(':checked')) && !($("#nighttrip"+id).is(':checked'))){
			$(".transparentCover").show();
		     
			$(".loading1").show();
		var deletedids = document.getElementById("deletedTrips").value;
		if(tripid != undefined){
		deletedids = deletedids+tripid + ",";
		$("#deletedTrips").val(deletedids);
		}
		
		$(ele).closest('tr').remove();
		var rows = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		var table1rows = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		var myElem = document.getElementById('triptable2');
		
		if(myElem !=null){
			var table2rows = document.getElementById("triptable2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
			table1rows = parseInt(table1rows)+parseInt(table2rows);
			setDeletId(id,(parseInt(table1rows)-1),tableid);
			setsteeringtime('triptable2','0','0');
			setSpreadHours('triptable1','0','0');
			setSpreadHours('triptable2',rows,'0');
			setKms('triptable1','0','0');
			setKms('triptable2',parseInt(rows)-1,'0');
			
		}
		else{
			setDeletId(id,table1rows,tableid);
			setSpreadHours('triptable1','0','0');
			setsteeringtime('triptable1','0','0');
			setKms('triptable1','0','0');
		}
		
		settripno();
		setCrewChangeId();
		disableLastRow();
		var crewid = document.getElementById('crewChangeId').value;
		if(myElem !=null){
			var table2rows = document.getElementById("triptable2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
			table1rows = parseInt(table1rows)+parseInt(table2rows);
			disableCrewChange(crewid,table1rows);
		}
		getGroupingStops((parseInt(id)-1));
		setTimeout(function() {
			$(".transparentCover").hide();
		     
			$(".loading1").hide();
	    }, 1000);
		}
	}
	
	function setDeleteStatus(tripid,ele,id,tableid){
		$.ajax({
	        type: "POST",
	        url: "SetDeleteStatus.action",
	        data: "tripid="+tripid,
	        success: function(response){
	        	deleteRow(ele,id,tableid);
	        	
	        }
		});
	}
	
	function setDeletId(id,table1rows,tableid){
		
		var newid= '0';
		var elementid='';
			for(g=id;g<parseInt(table1rows);g++){
			
			elementid = '#listitem'+(parseInt(g)+1);
			newid = 'listitem'+g;
			newname = 'tripslist['+g+'].listItemNumber';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#tripid'+(parseInt(g)+1);
			newid = 'tripid'+g;
			newname = 'tripslist['+g+'].id';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			
			if(tableid == 'triptable1'){
			elementid = '#id'+(parseInt(g)+1);
			newid = 'id'+g;
			newname = 'tripslist['+g+'].tripNumber';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			}
			else{
				elementid = '#id'+(parseInt(g)+1);
				newid = 'id'+g;
				lastid = 'id'+(parseInt(g)-2);
				newname = 'tripslist['+g+'].tripNumber';
				$(elementid).attr('name',newname);
				$(elementid).attr('id',newid);
				
			}
			
			elementid = '#formFour'+(parseInt(g)+1);
			newid = 'formFour'+g;
			newname = 'tripslist['+g+'].formFour.id';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#scheduleid'+(parseInt(g)+1);
			newid = 'scheduleid'+g;
			newname = 'tripslist['+g+'].scheduleNumber.schedule_id';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#noofTrips'+(parseInt(g)+1);
			newid = 'noofTrips'+g;
			newname = 'tripslist['+g+'].noofTrips';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#tripid'+(parseInt(g)+1);
			newid = 'tripid'+g;
			newname = 'tripslist['+g+'].id';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);

			
			elementid = '#trip'+(parseInt(g)+1);
			newid = 'trip'+g;
			newname = 'tripslist['+g+'].tripType.id';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#deadtripval'+(parseInt(g)+1);
			newid = 'deadtripval'+g;
			newname = 'tripslist['+g+'].isDreadTrip';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#selectedTrip'+(parseInt(g)+1);
			newid = 'selectedTrip'+g;
			newname = 'tripslist['+g+'].selectedTrip';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			
			elementid = '#customer'+(parseInt(g)+1);
			newid = 'customer'+g;
			newname = 'tripslist['+g+'].customer.id';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			
			elementid = '#uniform-deadtrip'+(parseInt(g)+1);
			newid = 'uniform-deadtrip'+g;			
			$(elementid).attr('id',newid);
			
			elementid = '#__checkbox_deadtrip'+(parseInt(g)+1);
			newid = '__checkbox_deadtrip'+g;
			newname = '__checkbox_tripslist['+g+'].deadTrip';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			
			
			elementid = '#deadtrip'+(parseInt(g)+1);
			newid = 'deadtrip'+g;
			newname = 'tripslist['+g+'].deadTrip';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#deadtripval'+(parseInt(g)+1);
			newid = 'deadtripval'+g;
			newname = 'tripslist['+g+'].isDreadTrip';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#origin'+(parseInt(g)+1);
			newid = 'origin'+g;
			newname = 'tripslist['+g+'].origin';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			
			elementid = '#origin'+(parseInt(g)+1)+'A'+(parseInt(g)+1);
			newid = 'origin'+g+'A'+g;
			newname = 'tripslist['+g+'].startPoint';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			
			elementid = '#dest'+(parseInt(g)+1);
			newid = 'dest'+g;
			newname = 'tripslist['+g+'].destination';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#dest'+(parseInt(g)+1)+'A'+(parseInt(g)+1);
			newid = 'dest'+g+'A'+g;
			newname = 'tripslist['+g+'].endPoint';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#route_no'+(parseInt(g)+1);
			newid = 'route_no'+g;
			newname = 'tripslist['+g+'].routeNumber.route_id';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#routelength'+(parseInt(g)+1);
			newid = 'routelength'+g;
			newname = 'tripslist['+g+'].distance';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			
			elementid = '#fromtime'+(parseInt(g)+1);
			newid = 'fromtime'+g;
			newname = 'tripslist['+g+'].starttimeString';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#totime'+(parseInt(g)+1);
			newid = 'totime'+g;
			newname = 'tripslist['+g+'].endtimeString';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#journeytime'+(parseInt(g)+1);
			newid = 'journeytime'+g;
			newname = 'tripslist['+g+'].journeyTime';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#break'+(parseInt(g)+1);
			newid = 'break'+g;
			newname = 'tripslist['+g+'].breakTypeString';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			
			elementid = '#selectedRoute'+(parseInt(g)+1);
			newid = 'selectedRoute'+g;
			newname = 'tripslist['+g+'].selectedRoute';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			
			elementid = '#breakduration'+(parseInt(g)+1);
			newid = 'breakduration'+g;
			newname = 'tripslist['+g+'].breaktimeString';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#breaklocation'+(parseInt(g)+1);
			newid = 'breaklocation'+g;
			newname = 'tripslist['+g+'].breakLocation';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#uniform-crewchange'+(parseInt(g)+1);
			newid = 'uniform-crewchange'+g;
			$(elementid).attr('id',newid);
			
			elementid = '#crewchange'+(parseInt(g)+1);
			newid = 'crewchange'+g;
			newname = 'tripslist['+g+'].crewChangeStatus';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#__checkbox_crewchange'+(parseInt(g)+1);
			newid = '__checkbox_crewchange'+g;
			newname = '__checkbox_tripslist['+g+'].crewChangeStatus';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			
			elementid = '#crewChangelocation'+(parseInt(g)+1);
			newid = 'crewChangelocation'+g;
			newname = 'tripslist['+g+'].crewChangeLocation';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#uniform-nighttrip'+(parseInt(g)+1);
			newid = 'uniform-nighttrip'+g;
			$(elementid).attr('id',newid);
			
			elementid = '#nighttrip'+(parseInt(g)+1);
			newid = 'nighttrip'+g;
			newname = 'tripslist['+g+'].nightTrip';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#__checkbox_nighttrip'+(parseInt(g)+1);
			newid = '__checkbox_nighttrip'+g;
			newname = '__checkbox_tripslist[0].nightTrip';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#nighttriplocation'+(parseInt(g)+1);
			newid = 'nighttriplocation'+g;
			newname = 'tripslist['+g+'].nightHaltLocation';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#shift'+(parseInt(g)+1);
			newid = 'shift'+g;
			newname = 'tripslist['+g+'].shiftType.id';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#operationtype'+(parseInt(g)+1);
			newid = 'operationtype'+g;
			newname = 'tripslist['+g+'].operationType';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#remarks'+(parseInt(g)+1);;
			newid = 'remarks'+g;
			newname = 'tripslist['+g+'].remarks';
			$(elementid).attr('name',newname);
			$(elementid).attr('id',newid);
			
			elementid = '#add'+(parseInt(g)+1);
			newid = 'add'+g;
			$(elementid).attr('id',newid);
			
			elementid = '#del'+(parseInt(g)+1);
			newid = 'del'+g;
			$(elementid).attr('id',newid);
			
			
			newid = 'listitem'+(parseInt(g));
			var oldid = 'listitem'+(parseInt(g)-1);
			
			if(document.getElementById(oldid) == null){
				
				$('#'+newid).val(1);
				//document.getElementById(newid).value = 1;
			}
			else{
				$('#'+newid).val((parseInt(document.getElementById(oldid).value)+1));
			//document.getElementById(newid).value=(parseInt(document.getElementById(oldid).value)+1);
			}
			
			newid = 'id'+g;
			oldid = 'id'+(parseInt(g)-1);
			if(document.getElementById(oldid) == null){
				$('#'+newid).val(1);
				//document.getElementById(newid).value = 1;
			}
			else{
				$('#'+newid).val((parseInt(document.getElementById(oldid).value)+1));
			//document.getElementById(newid).value=(parseInt(document.getElementById(oldid).value)+1);
			}
			
		}
	
	}
	
	function setShiftType(id){
		var value = document.getElementById('shift'+id).value;
		
		var table1rows = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		var myElem = document.getElementById('triptable2');
		
		
		if(myElem !=null){
			var table2rows = document.getElementById("triptable2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
			table1rows = parseInt(table1rows)+parseInt(table2rows);
			table1rows = parseInt(table1rows)-1;
		}
		
		for(var i = (parseInt(id)+1);i<table1rows;i++){
			document.getElementById('shift'+i).value = value;
		}
	}
	
	function disableCrewChange(id,totalrows){
		for(var l=0;l<=totalrows;l++){
			if(parseInt(l)==parseInt(id)){
				if($("#crewchange"+id).is(':checked')){
					$("#nighttrip"+id).attr("disabled", true);
					}
					else{
					$("#crewchange"+id).attr("disabled", true);	
					}
			}
			else{
			$("#nighttrip"+l).attr("disabled", true);
			$("#crewchange"+l).attr("disabled", true);
			}
		}
		Metronic.init();
	}
	
	function enableCrewChange(totalrows){
		
		for(var l=0;l<=totalrows;l++){
			$("#nighttrip"+l).attr("disabled", false);
			$("#crewchange"+l).attr("disabled", false);	
		}	
	}
	
	
	function setCrewChangeId(){
		var table1rows = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		var myElem = document.getElementById('triptable2');
		if(myElem !=null){
			table1rows = parseInt(table1rows)-1;
		}
		for(var i=0;i<table1rows;i++){
			if($("#crewchange"+i).is(':checked') || $("#nighttrip"+i).is(':checked')){
				document.getElementById('crewChangeId').value = i;
			}
		}
	}
	
	function disableLastRow(){
		var table1rows = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		var myElem = document.getElementById('triptable2');
		
		
		if(myElem !=null){
			var table2rows = document.getElementById("triptable2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
			table1rows = parseInt(table1rows)+parseInt(table2rows);
			table1rows = parseInt(table1rows)-1;
		}
		
		for(var l=0;l<=(parseInt(table1rows)-2);l++){
			$("#nighttrip"+l).attr("disabled", false);
			$("#crewchange"+l).attr("disabled", false);
		}
		
		$("#nighttrip"+(parseInt(table1rows)-1)).attr("disabled", true);
		$("#crewchange"+(parseInt(table1rows)-1)).attr("disabled", true);
		Metronic.init();
	}
	
	function setName(){
		var table1rows = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		var myElem = document.getElementById('triptable2');
		
		
		if(myElem !=null){
			var table2rows = document.getElementById("triptable2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
			table1rows = parseInt(table1rows)+parseInt(table2rows);
			table1rows = parseInt(table1rows)-1;
		}
		
		for(var i=0;i<table1rows;i++){
			
			elementid = '#listitem'+i;
			newname = 'tripslist['+i+'].listItemNumber';
			$(elementid).attr('name',newname);
			
			elementid = '#id'+i;
			newname = 'tripslist['+i+'].tripNumber';
			$(elementid).attr('name',newname);
			
			elementid = '#formFour'+i;
			newname = 'tripslist['+i+'].formFour.id';
			$(elementid).attr('name',newname);
			
			elementid = '#tripid'+i;
			newname = 'tripslist['+i+'].id';
			$(elementid).attr('name',newname);
			
			elementid = '#scheduleid'+i;
			newname = 'tripslist['+i+'].scheduleNumber.schedule_id';
			$(elementid).attr('name',newname);
			
			elementid = '#noofTrips'+i;
			newname = 'tripslist['+i+'].noofTrips';
			$(elementid).attr('name',newname);
			
			elementid = '#trip'+i;
			newname = 'tripslist['+i+'].tripType.id';
			$(elementid).attr('name',newname);
			
			elementid = '#selectedTrip'+i;
			newname = 'tripslist['+i+'].selectedTrip';
			$(elementid).attr('name',newname);
			
			elementid = '#customer'+i;
			newname = 'tripslist['+i+'].customer.id';
			$(elementid).attr('name',newname);
			
			elementid = '#__checkbox_deadtrip'+i;
			newname = 'tripslist['+i+'].deadTrip';
			$(elementid).attr('name',newname);
			
			elementid = '#deadtripval'+i;
			newname = 'tripslist['+i+'].isDreadTrip';
			$(elementid).attr('name',newname);
			
			elementid = '#origin'+i;
			newname = 'tripslist['+i+'].origin';
			$(elementid).attr('name',newname);
			
			elementid = '#origin'+(i)+'A'+(i);
			newname = 'tripslist['+i+'].startPoint';
			$(elementid).attr('name',newname);
			
			elementid = '#dest'+(i);
			newname = 'tripslist['+i+'].destination';
			$(elementid).attr('name',newname);
			
			elementid = '#dest'+(i)+'A'+(i);
			newname = 'tripslist['+i+'].endPoint';
			$(elementid).attr('name',newname);
			
			elementid = '#trip'+i;
			newname = 'tripslist['+i+'].tripType.id';
			$(elementid).attr('name',newname);
			
			elementid = '#selectedTrip'+i;
			newname = 'tripslist['+i+'].selectedTrip';
			$(elementid).attr('name',newname);
			
			elementid = '#customer'+i;
			newname = 'tripslist['+i+'].customer.id';
			$(elementid).attr('name',newname);
			
			elementid = '#__checkbox_deadtrip'+i;
			newname = '__checkbox_tripslist['+i+'].deadTrip';
			$(elementid).attr('name',newname);
			
			
			elementid = '#route_no'+i;
			newname = 'tripslist['+i+'].routeNumber.route_id';
			$(elementid).attr('name',newname);
			
			elementid = '#routelength'+i;
			newname = 'tripslist['+i+'].distance';
			$(elementid).attr('name',newname);
			
			elementid = '#fromtime'+i;
			newname = 'tripslist['+i+'].starttimeString';
			$(elementid).attr('name',newname);
			
			elementid = '#totime'+i;
			newname = 'tripslist['+i+'].endtimeString';
			$(elementid).attr('name',newname);
			
			elementid = '#journeytime'+i;
			newname = 'tripslist['+i+'].journeyTime';
			$(elementid).attr('name',newname);
			
			elementid = '#break'+i;
			newname = 'tripslist['+i+'].breakTypeString';
			$(elementid).attr('name',newname);
			
			elementid = '#selectedRoute'+i;
			newname = 'tripslist['+i+'].selectedRoute';
			$(elementid).attr('name',newname);
			
			elementid = '#breakduration'+i;
			newname = 'tripslist['+i+'].breaktimeString';
			$(elementid).attr('name',newname);
			
			elementid = '#breaklocation'+i;
			newname = 'tripslist['+i+'].breakLocation';
			$(elementid).attr('name',newname);
			
			
			/*elementid = '#crewchange'+i;
			newname = 'tripslist['+i+'].crewChangeStatus';
			$(elementid).attr('name',newname);
			
			elementid = '#__checkbox_crewchange'+i;
			newname = '__checkbox_tripslist['+i+'].crewChangeStatus';
			$(elementid).attr('name',newname);*/
			
			elementid = '#crewchange'+i;
			newname = 'tripslist['+i+'].crewChangeStatus';
			$(elementid).attr('name',newname);

			
			elementid = '#__checkbox_crewchange'+i;
			newname = '__checkbox_tripslist['+i+'].crewChangeStatus';
			$(elementid).attr('name',newname);
			
			
			elementid = '#crewChangelocation'+i;
			newname = 'tripslist['+i+'].crewChangeLocation';
			$(elementid).attr('name',newname);
			
			elementid = '#nighttrip'+i;
			newname = 'tripslist['+i+'].nightTrip';
			$(elementid).attr('name',newname);
			
			elementid = '#__checkbox_nighttrip'+i;
			newname = '__checkbox_tripslist['+i+'].nightTrip';
			$(elementid).attr('name',newname);
			
			elementid = '#nighttriplocation'+i;
			newname = 'tripslist['+i+'].nightHaltLocation';
			$(elementid).attr('name',newname);
			
			elementid = '#shift'+i;
			newname = 'tripslist['+i+'].shiftType.id';
			$(elementid).attr('name',newname);
			
			elementid = '#operationtype'+i;
			newname = 'tripslist['+i+'].operationType';
			$(elementid).attr('name',newname);
			
			elementid = '#remarks'+i;
			newname = 'tripslist['+i+'].remarks';
			$(elementid).attr('name',newname);
			
			if($("#crewchange"+i).is(':checked') || $("#nighttrip"+i).is(':checked')){
				document.getElementById('crewChangeId').value = i;
				document.getElementById('__checkbox_crewchange'+i).value = false;
				
			}
			else{
				document.getElementById('__checkbox_crewchange'+i).value = true;
			}
			
		}
		
	}
	
	function setCrewChangeId(){
		var table1rows = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
		var myElem = document.getElementById('triptable2');
		
		
		if(myElem !=null){
			var table2rows = document.getElementById("triptable2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
			table1rows = parseInt(table1rows)+parseInt(table2rows);
			table1rows = parseInt(table1rows)-1;
		}
		
		for(var i=0;i<table1rows;i++){
			if($("#crewchange"+i).is(':checked') || $("#nighttrip"+i).is(':checked')){
				document.getElementById('crewChangeId').value = i;
			}
		}
	}
	
	
	function disableLink(){
		$('a').addClass('disabled');
	}
	
	function enableLink(){
		$('a').removeClass('disabled');
	}
	
	$('a').click(function(e) {
        if ($('a').hasClass('disabled'))
        {
            e.preventDefault();
            return false;
        } else {
            }
    });
	
	function reCalculateSteeringSpreadhours(){
		setSpreadHours('triptable1','0','0');
		
		
		var myElem = document.getElementById('triptable2');
		if(myElem !=null){
			var rows = document.getElementById("triptable1").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
			rows = (parseInt(rows)-1);
			setSpreadHours('triptable2',rows,'0');
		}
		setsteeringtime('triptable2','0','0');
	}