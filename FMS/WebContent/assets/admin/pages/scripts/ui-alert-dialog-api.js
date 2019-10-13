var UIAlertDialogApi = function () {

    var handleDialogs = function() {
    		
    	$('#edit').click(function(){
    		var cnt =  $(":checkbox:checked").length;
    		  var val ;
    		  if(cnt == 0){
    			  bootbox.alert("Please Select Checkbox To Edit");
    		  }
    		  else if(cnt>1){
    			  bootbox.alert("Please Select One Checkbox To Edit");
    		  }
    		  else{
    		  $("input[type='checkbox']:checked").each(function() {
    			   
    			   val = this.value;
    			});
    		  /*  var val = $("input[type='checkbox']").val();*/
    		   document.forms[0].action = 'EditBusStop.action?busid='+val;
    		 	 document.forms[0].submit();  
    		   
    		  }
    		
           
        });
    	
    	$('#delete').click(function(){
    		var cnt =  $(":checkbox:checked").length;
  		  var val ;
  		  if(cnt == 0){
  			bootbox.alert("Please Select Checkbox To Delete");
  		  }
  		  else if(cnt>1){
  			bootbox.alert("Please Select One Checkbox To Delete");
  		  }
  		  else{
  		  $("input[type='checkbox']:checked").each(function() {
  			   
  			   val = this.value;
  			   type = 'text';
  			});
  		  /*  var val = $("input[type='checkbox']").val();*/
  		bootbox.confirm("Are you sure you want to delete this Bus Stop?",
				function(result) {

					if (result == true) {
  		   document.forms[0].action = 'DeleteBusStopText.action?busid='+val+'&requestType='+type;
  		 	 document.forms[0].submit();  
					}
		});
  		   
  		  }    
        });
    	
        $('#demo_1').click(function(){
                bootbox.alert("Hello world!");    
            });
            //end #demo_1

            $('#demo_2').click(function(){
                bootbox.alert("Hello world!", function() {
                    alert("Hello world callback");
                });  
            });
            //end #demo_2
        
            $('#demo_3').click(function(){
                bootbox.confirm("Are you sure?", function(result) {
                   alert("Confirm result: "+result);
                }); 
            });
            //end #demo_3

            $('#demo_4').click(function(){
                bootbox.prompt("What is your name?", function(result) {
                    if (result === null) {
                        alert("Prompt dismissed");
                    } else {
                        alert("Hi <b>"+result+"</b>");
                    }
                });
            });
            //end #demo_6

            $('#demo_5').click(function(){
                bootbox.dialog({
                    message: "I am a custom dialog",
                    title: "Custom title",
                    buttons: {
                      success: {
                        label: "Success!",
                        className: "green",
                        callback: function() {
                          alert("great success");
                        }
                      },
                      danger: {
                        label: "Danger!",
                        className: "red",
                        callback: function() {
                          alert("uh oh, look out!");
                        }
                      },
                      main: {
                        label: "Click ME!",
                        className: "blue",
                        callback: function() {
                          alert("Primary button");
                        }
                      }
                    }
                });
            });
            //end #demo_7

    }

    var handleAlerts = function() {
        
        $('#alert_show').click(function(){

            Metronic.alert({
                container: $('#alert_container').val(), // alerts parent container(by default placed after the page breadcrumbs)
                place: $('#alert_place').val(), // append or prepent in container 
                type: $('#alert_type').val(),  // alert's type
                message: $('#alert_message').val(),  // alert's message
                close: $('#alert_close').is(":checked"), // make alert closable
                reset: $('#alert_reset').is(":checked"), // close all previouse alerts first
                focus: $('#alert_focus').is(":checked"), // auto scroll to the alert after shown
                closeInSeconds: $('#alert_close_in_seconds').val(), // auto close after defined seconds
                icon: $('#alert_icon').val() // put icon before the message
            });

        });

    }

    return {

        //main function to initiate the module
        init: function () {
            handleDialogs();
            handleAlerts();
        }
    };

}();