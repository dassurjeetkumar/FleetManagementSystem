<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.ResourceBundle"%>
<title>Registration Page</title>
<script language="javascript" src="../js/validation.js"></script>
<!-- script language="javascript" src="../js/converter.js"></script>  
<script language="javascript" src="../js/kannada.js"></script>   -->
<script type="text/javascript" src="//www.google.com/jsapi"></script>

<script type="text/javascript">
	// Load the Google Transliteration API
	google.load("elements", "1", {
		packages : "transliteration"
	});
	function onLoad() {
		var options = {
			sourceLanguage : 'en', // or google.elements.transliteration.LanguageCode.ENGLISH,
			destinationLanguage : [ 'kn' ], // or [google.elements.transliteration.LanguageCode.HINDI],
			shortcutKey : 'ctrl+g',
			transliterationEnabled : true
		};
		// Create an instance on TransliterationControl with the required
		// options.
		var control = new google.elements.transliteration.TransliterationControl(
				options);

		// Enable transliteration in the textfields with the given ids.
		//var ids = ["transl1", "transl2" ]);
		var ids = [ "kannadaaddress", "kannadaName" ]);
		control.makeTransliteratable(ids);

		// Show the transliteration control which can be used to toggle between
		// English and Hindi.
		//control.showControl('translControl');
	}
	google.setOnLoadCallback(onLoad);
</script>
<SCRIPT type="text/javascript">
	function hideAll() {
		$("#existingcust").hide();
		//        var abc= document.getElementById("text").focus;
		$("#text").focus();

	}
	function ShowStatus() {
		value_pa = $("#exicuststatus").val();
		if (value_pa == 'Existing Customer') {
			$("#existingcust").show();
		} else {
			$("#existingcust").hide();
		}
	}

	function myFunction1() {
		var y = document.getElementById("text").value;// + ' '+ document.getElementById("text2").value+ ' '+ document.getElementById("text3").value;
		document.getElementById("kannadaName").value = y;

	}

	function myFunction2() { //MiddleNameHidden LastNameHidden
		// + ' '+ document.getElementById("text2").value+ ' '+ document.getElementById("text3").value;
		document.getElementById("MiddleNameHidden").value = document
				.getElementById('kannadaName').value;

		$('#text2').live(
				'keypress',
				function(e) {
					if (e.keyCode === 9) {
						e.preventDefault();
						var y = document.getElementById("text2").value;
						var y1 = y.split(" ", 1);
						document.getElementById("kannadaName").value = document
								.getElementById("MiddleNameHidden").value
								+ ' ' + y1;
						//                if(y1!==null && y1!=='')
						//                    {
						myFunction21();
						//                    }
						//                else{
						//                    $("#text3").focus();
						//                }
						//                document.getElementById("text3").value = document.getElementById("kannadaName").value + y1;
						//      document.getElementById("MiddleNameHidden").value = document.getElementById("kannadaName").value;
						//                $("#kannadaName").focus();
					}
				});
	}

	function myFunction21() {
		$("#kannadaName").focus();
		var y = document.getElementById("text2").value;
		if (y === null || y === '') {
			document.getElementById("kannadaName").value = document
					.getElementById("MiddleNameHidden").value;
		}
		$('#kannadaName').live('keypress', function(e) {
			if (e.keyCode === 9) {
				e.preventDefault();
				//                    document.getElementById("MiddleNameHidden").value = document.getElementById("kannadaName").value;
				$("#text3").focus();
			}
		});
	}

	function myFunction22() {
		$("#text3").focus();
	}

	function myFunction3() {
		var val = document.getElementById("MiddleNameHidden").value = document
				.getElementById('kannadaName').value;
		//        alert(val);
		$('#text3').live(
				'keypress',
				function(e) {
					if (e.keyCode === 9) {
						e.preventDefault();
						var y = document.getElementById("text3").value;
						var y1 = y.split(" ", 1);
						document.getElementById("kannadaName").value = document
								.getElementById("MiddleNameHidden").value
								+ ' ' + y1;
						$("#kannadaName").focus();
						$('#kannadaName').live('keypress', function(e) {
							if (e.keyCode === 9) {
								e.preventDefault();
								$("#address").focus();
							}
						});
					}
				});
	}

	//Function for conversion of kannada Address.....
	function myFunction4() {
		document.getElementById("kannadaaddress").value = document
				.getElementById("address").value
	}

	function isKannadaWord(word) {
		var re = new RegExp(/^[\u0C80-\u0CFF\s,-\/]+$/);
		var word1 = word.trim().split(" ");
		for ( var i = 0; i < word1.length; i++) {
			if (word1[i].match(re)) {

			} else {
				return false;
			}
		}
		return true;
	}

	function ExistValidate() {

		var fname = document.getElementById("text").value;
		var mname = document.getElementById("text2").value;
		var Lname = document.getElementById("text3").value;
		var mob = document.getElementById("mobileNumber").value;
		var kannadaname = document.getElementById("kannadaName").value;
		var kannadaadress = document.getElementById("kannadaaddress").value;
		var re = new RegExp(/^[\u0C80-\u0CFF]+$/);

		if (Number(fname.length + mname.length + Lname.length) > 16) {
			alert("Total 16 Characters required for First, Middle And Last Name Field.");
			return false;
		}

		if (kannadaname == '' || !isKannadaWord(kannadaname)) {
			alert("Please Enter Proper Kannada Name");
			return false;
		}

		if (kannadaadress == '' || !isKannadaWord(kannadaadress)) {
			alert("Please Enter Kannada Address");
			return false;
		}
		if (Number(fname.length + mname.length + Lname.length) > 16) {
			alert("Total 16 Characters required for First, Middle And Last Name Field.");
			return false;
		}
		if (mob.length > 0) {

			if (mob.length > 10 || mob.length < 10) {
				alert("Please Enter 10 Digit Mob No.");
				return false;
			}
		}
		var exicuststatus = document.getElementById("exicuststatus").value;
		var exepassidno = document.getElementById("exepassidno").value;
		var exevaliditydate = document.getElementById("exevaliditydate").value;

		if (exicuststatus == 'Existing Customer') {
			if (exepassidno == "") {
				alert("Please Enter Card ID No.");
				return false;
			}
			if (exevaliditydate == "") {
				alert("Please Enter Expiry Date.");
				return false;
			}
		}
	}

	window.history.forward();
	function noBack() {

		window.history.forward();
		$("#existingcust").hide();
	}
	$(function() {

		$("#exevaliditydate").datepicker({
			"minDate" : "0M,0D",
			"maxDate" : "+13M,0D"
		});
		$("#exevaliditydate").datepicker("option", "dateFormat", "dd/mm/yy");
		$("#exevaliditydate").datepicker("option", "changeMonth", "true");
		$("#exevaliditydate").datepicker("option", "changeYear", "true");
		$("#exevaliditydate").datepicker("option", "yearRange", "c-100:c+0");
		/*     $(function() {
		 $("#dob").datepicker({
		
		 });
		 $("#dob").datepicker("option", "dateFormat", "dd/mm/yy");
		 $("#dob").datepicker("option", "changeMonth", "true");
		 $("#dob").datepicker("option", "changeYear", "true");
		 $("#dob").datepicker("option", "yearRange", "c-100:c+0");
		 });
		 */

		$(document).ready(function() {
			var d = new Date();
			var curr_year = d.getFullYear();
			$("#dob").datepicker({
				yearRange : '1900:' + curr_year,
				changeMonth : true,
				changeYear : true,
				maxDate : '-1d',
				dateFormat : 'dd/mm/yy'
			});
		});

		$(document).ready(function() {
			$("#exicuststatus").change(function() {
				value_pa = $("#exicuststatus").val();

				$.ajax({
					type : "get",
					url : "registration/gen_dropdown.jsp",
					data : "exicuststatus=" + value_pa,
					success : function(result) {
						$("#userinfo").html(result);
					}
				})
			})
		})

		$(document).ready(function() {
			$("#dob").change(function() {
				value_pa = $("#dob").val();

				$.ajax({
					type : "get",
					url : "registration/gen_dropdown.jsp",
					data : "calculateage=" + value_pa,
					success : function(result) {
						$("#agediff").html(result);
					}
				})
			})
		})

	});
</SCRIPT>


<script type="text/javascript">
	$('#text').live('keypress', function(e) {
		if (e.keyCode === 9) {
			e.preventDefault();
			$("#kannadaName").focus();
		}
	});

	$('#kannadaName').live('keypress', function(e) {
		if (e.keyCode === 9) {
			e.preventDefault();
			$("#text2").focus();
		}
	});

	$('#text2').live('keypress', function(e) {
		if (e.keyCode === 9) {
			e.preventDefault();
			$("#kannadaName").focus();
		}

	});

	/*
	 $('#kannadaName').live('keypress', function(e) {
	 if (e.keyCode === 9) {
	 e.preventDefault();
	 $("#text3").focus();
	 }
	 });

	 $('#text3').live('keypress', function(e) {
	 if (e.keyCode === 9) {
	 e.preventDefault();
	 $("#kannadaName").focus();
	 }
	 });

	 $('#kannadaName').live('keypress', function(e) {
	 if (e.keyCode === 9) {
	 e.preventDefault();
	 $("#address").focus();
	 }
	 });
	 */
</script>
<script type="text/javascript">
	function changeColor(selected) {

		selected.style.backgroundColor = '#AFD7ED';

	}
	function offFocus(ofFocus) {

		ofFocus.style.backgroundColor = 'white';

	}
	function getCheck() {
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth() + 1; //January is 0!
		var yyyy = today.getFullYear();
		if (dd < 10) {
			dd = '0' + dd
		}
		if (mm < 10) {
			mm = '0' + mm
		}
		today = dd + '/' + mm + '/' + yyyy;
		document.getElementById("dob").value = today;

	}

	function validationCountChara() {
		var fname = document.getElementById("text").value;
		var mname = document.getElementById("text2").value;
		var Lname = document.getElementById("text3").value;
		alert(fname.length);
		return false;
	}
</script>
<sx:head />
<link href="../css/style.css" rel="stylesheet" />
</head>

<body onload="hideAll()">

	<h3 align="center">
		<s:if test="hasActionErrors()">
			<div class="errors">
				<font color="Red"> <s:actionerror /> </font>
			</div>
		</s:if>
	</h3>
	<h3 align="center">
		<s:if test="hasActionMessages()">
			<div class="messages">
				<font color="Red"> <s:actionmessage /> </font>
			</div>
		</s:if>
	</h3>
	<s:form action="registrationsubmit" name="frm" id="frm"
		theme="css_xhtml" validate="true">

		<fieldset>
			<legend>
				<font size="2"> <b>Personal Information</b></font>
			</legend>
			<table cellspacing="5" align="center" width="80%">
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td><font size="2"> ABC </font><font color="red">*</font> <input
						type="hidden" name="MiddleNameHidden" id="MiddleNameHidden" /> <input
						type="hidden" name="addKannada" id="addKannada" /> <input
						type="hidden" name="addEnglish" id="addEnglish" /> <input
						type="hidden" name="addEnglishHidden" id="addEnglishHidden" /></td>
					<td>
						<!--<s:textfield name="firstName" id="text"   required="true"
                            maxlength="16" onfocus='javascript:print_many_words(),changeColor(this);'
                            onkeyup='javascript:print_many_words()'  autocomplete="off"
                             onblur="offFocus(this)" size="16"></s:textfield>                            
                             --> <s:textfield name="firstName" id="text"
							required="true" maxlength="16" autocomplete="off" size="16"
							onkeyup="myFunction1()"></s:textfield>
					</td>

					<td><font size="2">ABC </font></td>
					<td>
						<!-- <s:textfield name="middleName"  required="true"
                            maxlength="16" onfocus='javascript:print_many_words_MiddleName(),changeColor(this);'
                            onkeyup='javascript:print_many_words_MiddleName()' autocomplete="off"
                            onblur="offFocus(this)" id="text2" size="16" /> -->
						<s:textfield name="middleName" required="true" maxlength="16"
							autocomplete="off" id="text2" size="16" onkeyup="myFunction2()" />
					</td>

					<td align="left"><font size="2">ABC </font></td>
					<td>
						<!--<s:textfield name="lastName" required="true"
                            maxlength="16" autocomplete="off" onfocus='javascript:print_many_words_LastName(),changeColor(this);'
                            onkeyup='javascript:print_many_words_LastName()'
                            onblur="offFocus(this)" id="text3" size="16" /> -->
						<s:textfield name="lastName" required="true" maxlength="16"
							autocomplete="off" id="text3" size="16" onkeyup="myFunction3()" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td align="left"><font size="2">Full Name (Kannada)</font></td>
					<td><textarea name="kannadaName" id="kannadaName" cols="20"></textarea>

					</td>
					<td><font size="2">Address</font><b><font color="red">*
						</td>
					<td>
						<!--<s:textarea name="address" id="address" maxLength="25" cols="20"
                            required="true" onfocus='javascript:print_many_words_Address(),changeColor(this);'
                            onkeyup='javascript:print_many_words_Address()'
                            onblur="offFocus(this)" />--> <s:textarea
							name="address" id="address" maxLength="25" cols="20"
							required="true" onkeyup="myFunction4();"
							onkeydown="myFunction4()" />
					</td>
					<td align="left"><font size="2"> Address (Kannada)</font></td>
					<td>
						<!-- <textarea name="kannadaaddress" id="kannadaaddress" cols="20" onKeyDown="toggleKBMode(event)"
                    onKeyPress="javascript:convertThis(event)" ></textarea>-->
						<textarea name="kannadaaddress" id="kannadaaddress" cols="20"></textarea>
					</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td><font size="2">ABC</font><font color="red">*</font></td>
					<td><s:select name="gender" list="{'Male','Female'}" /></td>

					<%-- <td><font size="2"><%=dateOfBirth%></font><font
                        color="red">*</font></td> --%>
					<td><font size="2">ABC</font><font color="red">*</font></td>
					<td><s:textfield id="dob" name="birthDate" readonly="true"
							autocomplete="off" onclick="getCheck()" size="16" /></td>
					<td><font size="2">Age :</font></td>
					<td><div id="agediff"></div></td>
				</tr>

				<tr>
					<td>
						<div style="visibility: hidden;">
							<input type="text" name="kannada_fname" id="kannada_Name"
								size="5" />
						</div>
					</td>
					<td>
						<div style="visibility: hidden;">
							<input type="text" name="kannada_mname" id="kannada_Name"
								size="5" />
						</div>
					</td>
					<td>
						<div style="visibility: hidden;">
							<input type="text" name="kannada_lName" id="kannada_Name"
								size="5" />
						</div>
					</td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>
				<font size="2"> <b>Contact Information</b></font>
			</legend>
			<table cellspacing="5" align="center" width="80%">
				<tr>

					<td><font size="2">ABC</font></td>
					<td><s:textfield name="mobileNumber" autocomplete="off"
							maxLength="10" onfocus="changeColor(this)"
							onblur="offFocus(this)" id="mobileNumber" /></td>

					<td><font size="2">&nbsp;ABC</font></td>
					<td><s:select name="exicuststatus"
							list="{'New Customer','Existing Customer'}"
							onChange="ShowStatus()" id="exicuststatus" /></td>
					<td><font size="2">&nbsp;Proof Detail</font></td>
					<td><input type='text' id="proofdetail" autocomplete="off"
						name="proofdetail" /></td>
				</tr>

			</table>
		</fieldset>
		<div id="existingcust">
			<fieldset>
				<legend>
					<font size="2"> <b>Existing Customer Information</b></font>
				</legend>
				<table align="center">
					<tr>
						<td>ABC</td>
						<td><input type='text' id="exepassidno" autocomplete="off"
							name="exepassidno" /></td>
						<td>ABC</td>
						<td><input type="text" id="exevaliditydate"
							name="exevaliditydate" readonly="true" autocomplete="off" /></td>
					</tr>
				</table>
			</fieldset>
		</div>
		<br>
		<center> <input type="submit" value="Register"
			onclick="return ExistValidate();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" value="Reset" onclick="hideAll();" /> </center>
		<fieldset>
			<legend>
				<font size="2"> <b>Collect Pass From</b></font>
			</legend>
			<table cellspacing="5" align="center" width="80%">
				<tr>

					<td><b> <s:property value="#session.posname" /></b></td>
				</tr>
				<tr>
					<td></td>
				</tr>

			</table>
		</fieldset>
	</s:form>
	<br>
	<br>
	<br>
	<br>
	<br>
	<%
		session.setAttribute("pathofImage", "N");
		session.setAttribute("include", "");
	%>
	<input type=hidden name=keybrd value=roman
		onclick="toggleKBMode(event,this)" checked>
	<input type=hidden onclick="showHelp(this)" checked="true" />
	<br>
</body>
</html>