
$(document).ready(function(){
// 	  
	  $("#b1").click(function(){
	    $("#ScrollCB").show();
	  // $("#ScrollCB").slideToggle(4000);
	   //$("#ScrollCB").toggle(4000);
	    $("#ScrollCB2").hide();
		  $("#ScrollCB3").hide();
		  $("#ScrollCB4").hide();
	  });
	  $("#b2").click(function(){
	    $("#ScrollCB2").show();
	    $("#ScrollCB").hide();
	    $("#ScrollCB3").hide();
	    $("#ScrollCB4").hide();
	 
	  });
	  
	  $("#b3").click(function(){
		  $("#ScrollCB3").show();
		    $("#ScrollCB2").hide();
		    $("#ScrollCB").hide();
		    $("#ScrollCB4").hide();
	
		   
		  });
	    $("#b4").click(function(){
		  $("#ScrollCB4").show();
		  $("#ScrollCB3").hide();
		    $("#ScrollCB2").hide();
		    $("#ScrollCB").hide();
		
		   
		  });
	    
	    
	    
	    
	    $("#close").click(function(){
		
			  $("#ScrollCB").hide();
						  		 			   
			  });
	    
	    $("#close1").click(function(){
			 // $("#ScrollCB4").show();
			
			  $("#ScrollCB2").hide();
			  
			  
			   
			  });
	    
	    $("#close2").click(function(){
			 // $("#ScrollCB4").show();
				  $("#ScrollCB3").hide();
			 			   
			  });
	    
	    $("#close3").click(function(){
			 // $("#ScrollCB4").show();
			  
			  $("#ScrollCB4").hide();
			  
			  //  $("#ScrollCB2").hide();
			   // $("#ScrollCB").hide();
			   
			  });
	});
	


var l=0;
var p=0;
function filterIN(){
	var text="";
	
	
		var i=0;
		var k=0;
		var filter=[];
		var filter1=[];
		var filter2=[];
		var filter3=[];
		var a;
		var d=0;
		var d1=0;
		var date;
		 var datefied="";
	
	 $(".filexp "  ).each(function() {
		 
		
		 filter.push($(this).val());
			//alert("filler"+filter);
				i++;
				
		if($(this).val()=="IN"){
			a=i;
		   //alert("ad"+a);
			
			//alert("filler fdf");
		
		}
		 
		
			
			}); 
	
	 var temp="";
	 $(".filinp").each(function() {
		 filter1.push($(this).val());
		// alert("FilterValue="+filter1+"k"+k)
		 k++;
		 if(a==k){
			// alert(" fivalue"+filter1[k-1]);
			 temp=filter1[k-1];
			var h=temp.search("'");
			
				 if(h!=-1){
					// alert("Please Enter Proper Value Without Using Quotes");
					 l++;
				 }
			 }
		         
		
		// alert("L"+l);
		 
		 
	 });
	 
	// Var==filterSelect;
	// $( "#denomi_no_"+rowid+" option:selected" ).text();
	 var temp="";
	 $(".filterSelect ").each(function() {
		
		 filter2.push($(this).val());
		 d++;
		//alert("FilterValue777="+filter2);
          var datereturn;    
		 if((datereturn=$(this).val().match(/date/i))||(datereturn=$(this).val().match(/effective/i))){// datefield=$(this).val().match(/effective/i)){
			// alert(" Date Search");
			 //datefied=($(this).val());
			 datefied=datereturn;
			// alert("DATe Field"+datefied);
			 date=d;
			 //temp=filter1[k-1];
//			var h=temp.search("'");
//			
//				 if(h!=-1){
//					// alert("Please Enter Proper Value Without Using Quotes");
//					 l++;
//				 }
			 }
	      
		
		 
		 
		 
	 });
	 
	 var temp1="";
	 var dateconvert="";
	
	 $(".filinp").each(function() {
		 filter3.push($(this).val());
		// alert("FilterValue="+filter1+"k"+k)
		 d1++;
		// alert("d"+d+"d1"+d1);
		// if){
		 if(parseInt(date)==parseInt(d1)){
			// alert(" fivalue"+filter1[k-1]);
			 temp1=filter3[d1-1];
			 var splitdatehyphen="";
			// alert("datee hii"+datefied);
		      if(datefied==datefied){
		    	  //alert("HHii");
			var value= DateValidator(temp1);
			if(value==true){
				dateconvert=temp1;
				var dateArray=[];
				//alert("&&"+dateconvert.search("-"));
				var kd=dateconvert.search("-");
				if(kd!=-1){
				
			
				 dateArray=dateconvert.split("-");
				 splitdatehyphen=dateArray[2]+"-"+dateArray[1]+"-"+dateArray[0];
				 
				 $(this).val(splitdatehyphen);
				// alert("splitted date="+splitdatehyphen);
	
				}
				else{
					
					dateArray=dateconvert.split("/");
					splitdatehyphen=dateArray[2]+"/"+dateArray[1]+"/"+dateArray[0];
				
					 $(this).val(splitdatehyphen);
				}
              
				
				//alert("Cooreect Fomat"+p);
				
			}else{
				 p++;
				//alert("INCooreect Fomat"+p);
				
			}
			 

			
			 
		         
		 }
		 
		 }
		 
	 });

	


	}


function AggColumn(){
	// class="aggColumn"
	
		 var aggsplit=[];
		 
		 $(".aggColumn").each(function() {
			 filter1.push($(this).val());
			// alert("FilterValue="+filter1+"k"+k)
			 k++;
			 if(a==k){
				// alert(" fivalue"+filter1[k-1]);
				 temp=filter1[k-1];
				var h=temp.search("'");
				
					 if(h!=-1){
						// alert("Please Enter Proper Value Without Using Quotes");
						 l++;
					 }
				 }
			         
			
			 
			 
			 
		 });
	
	
}
 function toggleF(id){
		var el = document.getElementById(id);

		if (el.style.display == "none"){
		 el.style.display = "block";
		} else {
		 el.style.display = "none";
		}
 }

 function report(sort) {
	//alert("Hii");
	 var singleValues = $( "#sort3" ).val();
	  
			if(singleValues==0){
				alert("Please Select Column To Sort:");
			}else{
			  document.getElementById("sort").value=sort;
			
						document.forms[0].action ="CustomReportAction";
			document.forms[0].submit();
			}
	
	
 }

 function DateValidator(datestr ){
	 
	 
	 var pattern=/^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/;

	return pattern.test(datestr);

	 
 }

 function report1(sort) {
	// alert("Hii1");
	        if($('.select:checked').length) {
	          var chkId = '';
	          $('.select:checked').each(function() {
	            chkId += $(this).val() + ",";
	          });
	          chkId =  chkId.slice(0,-1);
	         
	          document.getElementById("sort").value=sort;
				
				//getmachinedetails('ReportAction!getCustomReport');
				document.forms[0].action ="CustomReportAction";
				document.forms[0].submit();
	        }
	        else {
	          alert('Please Select Atleast One Column');
	        }
	   

	
	        
			
			
	
 }
 function reportFilter() {
	 
	 
	 
	 var select = $( "#f1-11" ).val();
	 var exp = $( "#f1-22" ).val();
	 var input = $( "#f1-33" ).val();
	 if(l>0){
		 alert("Please Enter Proper Value Without Using Quotes"); 
		 l=0;
	 }else if(p>0){
	 
		 alert("Please Enter Valid Date"); 
		 p=0;
	 }else if(select==0 && input=="" ){
		 alert("Please Select One Column To Search:");
	 }else if(select!=0 && input==""){
		 alert("Please Select One Column To Search:");
		 
	 }else if(select==0 && input!=""){
		 alert("Please Select One Column To Search:");
	 }else{
		 
	document.getElementById("filter").value="y";
	//getmachinedetails('ReportAction!getFilterReport');
	document.forms[0].action = "search";
	document.forms[0].submit();
 }
 }

 function filterAdd(id){
	 var el = document.getElementById(id);

		if (el.style.display == "none"){
		 el.style.display = "block";
		} else {
		 //el.style.display = "none";
		}
 }
 
 function cloneRow(){
		var row = document.getElementById("trDummy"); // find row to copy
		var table = document.getElementById("filterTable"); // find table to append to
		var clone = row.cloneNode(true); // copy children too
		clone.id = "trNew"; // change id or other attributes/contents
		clone.style.display = "block";	
		table.appendChild(clone); // add new row to end of table		
 }
 
 function cloneRowSort(){
		var row = document.getElementById("trSortDummy"); // find row to copy
		var table = document.getElementById("sortTable"); // find table to append to
		var clone = row.cloneNode(true); // copy children too
		clone.id = "trSort"; // change id or other attributes/contents
		
		clone.style.display = "block";	
		table.appendChild(clone); // add new row to end of table		
}
 
 function cloneRowAgg(){
	     // alert("d");
		var row = document.getElementById("traggDummy"); // find row to copy
		var table = document.getElementById("aggrTable"); // find table to append to
		var clone = row.cloneNode(true); // copy children too
		clone.id = "trNewAgg"; // change id or other attributes/contents
		clone.style.display = "block";	
		table.appendChild(clone); // add new row to end of table		
}
 
 function deleteRowSort(r) {
	
     var i = r.parentNode.parentNode.rowIndex;
     document.getElementById("sortTable").deleteRow(i);
 }
 
 function deleteRowFilter(r) {
	
     var i = r.parentNode.parentNode.rowIndex;
     document.getElementById("filterTable").deleteRow(i);
 }
 function deleteRowAgg(r) {
	// alert("d1");
     var i = r.parentNode.parentNode.rowIndex;
     document.getElementById("aggrTable").deleteRow(i);
 }
 
 var k=0;
 function reportAggregation() {
	  var ac=[];
		//alert("Val1"+k);
	  $(".aggColumn").each(function() {
			 
	 		
	    	 ac.push($(this).val());
	    	 
	    	 if($(this).val()=="0"){
	 			k++;
	 		
		//alert("Val"+k);
	 			//alert("filler fdf");
	 		
	 		}
	   	 // alert(ac);
	     });
//	  alert(ac);

	 var select = $("#aggColumnId").val();
	 var fun = $( "#aggfunc" ).val();
	// var input = $( "#f1-33" ).val();
	//alert("select"+select+"fun"+fun);
	 if(select==0 && fun==0 ){
		 alert("Please Select One Column To Aggregation:");
	 }else if(select!=0 && fun==0){
		 alert("Please Select One Column To  Aggregation: ");
		 
	 }else if(select==0 && input!=""){
		 alert("Please Select One Column To  Aggregation:");
	 }else if(k>1){
		  alert("Select Column"); 
		  k=0;
// 			alert("Val2"+k);
	 }else
     {
		 
	document.getElementById("aggregate").value="y";
	
	document.forms[0].action = "AggregationReport";
	document.forms[0].submit();
 }
 }
 
//  function removeRowSort(){
// 		var row = document.getElementById("trSortDummy"); // find row to copy
// 		var table = document.getElementById("sortTable"); // find table to append to
// 		var clone = row.(true); // copy children too
// 		clone.id = "trSort"; // change id or other attributes/contents
// 		clone.style.display = "block";	
// 		table.(clone); // add new row to end of table		
// }
 
 function getmachinedetails(link)
 {  
	 var data=$('#f1').serialize();
      //var data = "sort= &filter=&id=2&rate_master_id=rate_master_id&version_number=version_number&version_number_service_stype=version_number_service_stype&effective_start_date=effective_start_date&filterOperator=AND&filterColumn=0&filterExpression=%3D&filterValue=&filterOperator=AND&filterColumn=0&filterExpression=%3D&filterValue=";
     // alert('hi mv');
         $.ajax({
             url: link,
             type:'POST',
             data: data,
             dataType: 'html',
             success: function(records){
            	 
                     $('#dd').html(records);
                     
                   /*   $( "#dd").dialog({
                     modal:true,
                     height:450,
                     width:850
    
                 }); */
                /*  $( ".selector" ).dialog( "close" ); */
             } // End of success function of ajax form
             });    
        
 }
 


