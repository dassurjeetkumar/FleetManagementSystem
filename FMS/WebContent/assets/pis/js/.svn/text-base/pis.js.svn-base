var intervalID = null; // variable for set Interval
// var DEVICE_ID = 'banglore'; // varable for getting data
// var ID = '1';
// $(function() {
// var status = navigator.onLine; // used for check Connection
// if (status) {
// worker();
// checkFile()
// intervalID = setInterval(function() {
// worker();
// }, 10000); // set Interval for refresh page
// } else {
// alert("PIS SERVER DOESN'T EXIST !!!");
// }
// /*
// * $("#audioplayer")[0].play(); // Function for play Audio
// * $("#pause-button").click(function(){ $("#audioplayer")[0].pause();
// */
// /* }); */
// });
//
// // function used for get PIS Details
function worker(loc, lpf) {
	$.ajax({
		url : "getPisData.action",
		data : "loc=" + loc + "&lpf=" + lpf,
		method : "get",
		dataType : "json",
		success : function(data) {
			data = data["pisData"];
			if (data) {
				var len = data.length;
				var txt = "";
				if (len > 0) {
					for ( var i = 0; i < len; i++) {
						txt += "<tr><td>" + data[i][0] + "</td><td>"
								+ data[i][1] + "</td><td>" + data[i][2]
								+ "</td><td>" + data[i][3] + "</td>" + "<td>"
								+ data[i][4] + "</td>" + "<td>" + data[i][5]
								+ "</td></tr>";

					}
					if (txt != "") {
						$("#excelDataTable td").remove();
						$("#excelDataTable").append(txt);
					}
					// checkFile();
				} else {
					clearInterval(intervalID);
				}
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			setTimeout(worker, 0);
		}
	});
}
function checkFile() {
	var filename = 'Interrante-PopBounce.mp3';
	$.ajax({
		url : "downloadFile.action",
		data : "filename=" + filename,
		method : "get",
		dataType : "json",
		success : function(data) {

			$("#audioplayer")[0].play(); // Function for play Audio
			$("#pause-button").click(function() {
				$("#audioplayer")[0].pause();

			});
		}
	});
}

// Timer
function startTime() {
	var today = new Date();
	var h = today.getHours();
	var m = today.getMinutes();
	var s = today.getSeconds();
	m = checkTime(m);
	s = checkTime(s);
	document.getElementById('clockdisplay').innerHTML = "Bangalore:" + h + ":"
			+ m + ":" + s;
	var t = setTimeout(function() {
		startTime()
	}, 500);
}
function checkTime(i) {
	if (i < 10) {
		i = "0" + i
	}
	; // add zero in front of numbers < 10
	return i;
}
// Timer End
var pisData = function() {

	return {
		getLoc : function() {
			if (intervalID != null) {
				clearInterval(intervalID);
			}
			$.ajax({
				url : "getLoc.action",
				method : "post",
				dataType : "json",
				success : function(resp) {
					var ttlData = resp["locData"].length;
					for (i = 0; i < ttlData; i++) {
						$("#selLoc").append(
								new Option(resp["locData"][i][1],
										resp["locData"][i][0]));
					}
				},
				error : function() {
					console.log("Error Occured ");
				}
			});
		},
		getPf : function() {
			if (intervalID != null) {
				clearInterval(intervalID);
			}
			var loc = $("#selLoc").val();
			$('#selPf').find('option:not(:first)').remove();
			$.ajax({
				url : "getPf.action",
				data : "loc=" + loc,
				method : "post",
				dataType : "json",
				success : function(resp) {
					var ttlData = resp["pfData"].length;
					for (i = 0; i < ttlData; i++) {
						$("#selPf").append(
								new Option(resp["pfData"][i][1],
										resp["pfData"][i][1]));
					}
				},
				error : function() {
					console.log("Error Occured ");
				}
			});
		},
		getPisData : function() {
			if (intervalID != null) {
				clearInterval(intervalID);
			}
			var loc = $("#selLoc").val();
			var lpf = $("#selPf").val();
			if (loc != 0 && lpf != 0) {
				var status = navigator.onLine; // used for check Connection
				if (status) {
					worker(loc, lpf);
					intervalID = setInterval(function() {
						worker(loc, lpf);
					}, 10000); // set Interval for refresh page
				} else {
					alert("PIS SERVER DOESN'T EXIST !!!");
				}
			}
		}
	}
}();