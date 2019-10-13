<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script src="assets/global/plugins/jquery-1.11.0.min.js"
	type="text/javascript"></script>
<%@page import="com.trimax.its.utility.model.AccessRights"%>
<%@page import="com.trimax.its.utility.dao.AccessRightsDao"%>
<script src="assets/admin/pages/scripts/kannada.js"
	type="text/javascript"></script>
	
	
<script type="text/javascript">
function split_word(word)
{
  var syllables = new Array(0);
  var vowel_start_p = true;
  while (word.length) {
    re = new RegExp(vowels);
    var index = word.search(vowels);
    if (index == 0) {  //the vowel's at the start of word
      var matched = re.exec(word)[0]; //what is it?
      if (vowel_start_p) {
	syllables.push(("~"+matched)); //one more to the syllables
      } else {
	syllables.push(matched);
      }
      vowel_start_p = true;
      word = word.substring(matched.length);
    } else {
      re = new RegExp(consonants);
      var index = word.search(consonants);
      if (index == 0) {
	var matched = re.exec(word)[0];
	syllables.push(matched);
	vowel_start_p = false;
	word = word.substring(matched.length);

	//look ahead for virama setting
	var next = word.search(vowels);
	if (next != 0 || word.length == 0)
	  syllables.push('*');
      } else {
	syllables.push(word.charAt(0));
	word = word.substring(1);
      }
    }
  }
  return syllables;
}

function match_code(syllable_mcc)
{
  var matched = letter_codes[syllable_mcc];

  if (matched != null) return matched;
  return syllable_mcc;
}

function one_word(word_ow)
{
  if (!word_ow) return "";
  var syllables_ow = split_word(word_ow);
  var letters_ow = new Array(0);

  for (var i_ow = 0; i_ow < syllables_ow.length; i_ow++) {
    letters_ow.push(match_code(syllables_ow[i_ow]));
  }
  return letters_ow.join("");
}

function many_words(sentence)
{
  var regex = "((" + vowels + ")|(" + consonants + "))+";
  var words = new Array(0);
  while (sentence.length >= 1) {
    re = new RegExp("^``" + regex);
    var match = re.exec(sentence);
    if (match != null) {
      match = match[0];
      words.push("`");
      words.push(one_word(match.substring(2)));
      sentence = sentence.substring(match.length);
    } else {
      re = new RegExp("^`" + regex);
      match = re.exec(sentence);
      if (match != null) {
	match = match[0];
	words.push(match.substring(1));
	sentence = sentence.substring(match.length);
      } else {
	re = new RegExp("^" + regex);
	match = re.exec(sentence);
	if (match != null) {
	  match = match[0];
	  words.push(one_word(match));
	  sentence = sentence.substring(match.length);
	} else {
	  words.push(sentence.charAt(0));
	  sentence = sentence.substring(1);
	}
      }
    }
  }
  return words.join("");
}

function print_many_words(path1,path2,path3)
{
	
  var text_pmw = many_words(document.getElementById(path1).value);

  var ans = "";
  while (text_pmw.length) {
    var unicode_chars = /&#[0-9]+;/;
    re = unicode_chars;
    var matche = re.exec(text_pmw);
    if (matche != null) {
      matche = matche[0];
      search = text_pmw.search(unicode_chars);
      ans += text_pmw.substring(0, search);
      ans += String.fromCharCode(matche.match(/[0-9]+/));
      text_pmw = text_pmw.substring(search + matche.length);
    } else {
      ans += text_pmw.substring(0);
      text_pmw = "";
    }
  }

  document.getElementById(path2).value = ans;

  var html_txt = "";
  for (i=0; i<ans.length; i++) {
    var unicode_character = ans.charCodeAt(i);
    switch (unicode_character) {
    case 32:
      html_txt += " ";
      break;
    case 10:
    case 13:
      html_txt += "<br/>\n";
      break;
    default:
      html_txt += "&#" + unicode_character + ";";
    }
  }

  //document.getElementById("bus_stop_code_kannada").value = html_txt;
}







	// Load the Google Transliteration API
	google.load("elements", "1", {
		packages : "transliteration"
	});
	google.load("language", "1");

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
		//var ids = ["transl1", "transl2" ];
		var ids = [ "busStopNameKannada" ];
		control.makeTransliteratable(ids);

		var ids = [ "bus_stop_code_kannada" ];
		control.makeTransliteratable(ids);

		var ids = [ "kalias1" ];
		control.makeTransliteratable(ids);

		var ids = [ "kalias2" ];
		control.makeTransliteratable(ids);
		
		var ids = [ "kalias3" ];
		control.makeTransliteratable(ids);

		var ids = [ "kalias4" ];
		control.makeTransliteratable(ids);
		var ids = [ "kalias5" ];
		control.makeTransliteratable(ids);

		var ids = [ "kalias6" ];
		control.makeTransliteratable(ids);

		// Show the transliteration control which can be used to toggle between
		// English and Hindi.
		//control.showControl('translControl');
	}

	function convert() {
		//google.load("language", "1");
		//	alert("hiiii");
		//var a='["'+document.getElementById("busStopName").value+'"]';

		//alert(a);
		google.language
				.transliterate(
						[ document.getElementById("busStopName").value ],
						"en",
						"kn",
						function(result) {
							if (!result.error) {
								//var container = document.getElementById("transliteration");
								if (result.transliterations
										&& result.transliterations.length > 0
										&& result.transliterations[0].transliteratedWords.length > 0) {
									//alert(result.transliterations[0].transliteratedWords[0]);
									document
											.getElementById("busStopNameKannada").value = result.transliterations[0].transliteratedWords[0];
								}
							}
						});
	}

	function convertcode() {
		//google.load("language", "1");
		//	alert("hiiii");
		//var a='["'+document.getElementById("busStopName").value+'"]';

		//alert(a);
		google.language
				.transliterate(
						[ document.getElementById("bus_stop_code").value ],
						"en",
						"kn",
						function(result) {
							if (!result.error) {
								//var container = document.getElementById("transliteration");
								if (result.transliterations
										&& result.transliterations.length > 0
										&& result.transliterations[0].transliteratedWords.length > 0) {
									//alert(result.transliterations[0].transliteratedWords[0]);
									document
											.getElementById("bus_stop_code_kannada").value = result.transliterations[0].transliteratedWords[0];
								}
							}
						});
	}
	google.setOnLoadCallback(onLoad);

	$(document).ready(function() {
		convert();
		convertcode();
	});
</script>
</head>
<body>
	<%
		AccessRightsDao accessrightdao = new AccessRightsDao();
		AccessRights accessrights = new AccessRights();
		int usrsessionid = (Integer) request.getSession().getAttribute(
				"userid");
		accessrights = accessrightdao.accessRightsdetails(usrsessionid,
				"ShowBusStop.action");
		String access = accessrights.getACCESS();
		String create = accessrights.getCREATE();
		String edit = accessrights.getEDIT();
		String delete = accessrights.getDELETE();
		String read = accessrights.getREAD();
		String error = accessrights.getERROR();
		String viewdelete = accessrights.getVIEWDELETE();
		String viewdeletedrecord = (String) request.getSession()
				.getAttribute("viewdeletedrecord");
	%>
	<input type="text" id='a'>

	<div class="page-content-wrapper">
		<div class="page-content">
			<%
				if (access.equalsIgnoreCase("Y")) {
			%>
			<div class="tab-content">
				<div class="tab-pane active" id="tab_0">
					<%
						if (edit.equalsIgnoreCase("Y")) {
					%>
					<div class="portlet box grey-cascade">

						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-gift"></i>Edit Bus Stop
							</div>

						</div>

						<div class="portlet-body form">
							<s:if test="hasActionErrors()">
								<div class="alert alert-danger">
									<button class="close" data-close="alert"></button>
									<span> <s:actionerror />
									</span>
								</div>


							</s:if>
							<!-- BEGIN FORM-->
							<form action="AddEditedBusStop.action" class="form-horizontal"
								method="post" name="busstopmapform">
								<input type="hidden" name="bustops.id" id="busid"
									value="<s:property value="bustops.id"/>" /> <input
									type="hidden" name="requestType" id="requestType" value="text" />
								<div class="form-group">
									<label class="col-md-3 control-label">Bus Stop Name *</label>
									<div class="col-md-4">
										<input type="text" class="form-control"
											name="bustops.busStopName"
											 onFocus="javascript:print_many_words('busStopName','busStopNameKannada')"
                							 onKeyUp="javascript:print_many_words('busStopName','busStopNameKannada')"
											value="<s:property value="bustops.busStopName"/>"
											id="busStopName">
										<%-- <s:textfield name="bustops.busStopName" cssClass="form-control"></s:textfield> --%>

									</div>
									
								</div>

								<div class="form-group">

									<label class="col-md-3 control-label">Bus Stop Name
										(Kannada)</label>
									<div class="col-md-4">
										<input type="text" class="form-control"
											name="bustops.busStopNameKannada" id="busStopNameKannada"
											value='<s:property value="bustops.busStopNameKannada1"/>'
											>

									</div>
								</div>





								<div class="form-group">
									<label class="col-md-3 control-label">Bus Stop Code *</label>
									<div class="col-md-4">
										<input type="text" class="form-control"
											name="bustops.bus_stop_code"
											 onFocus="javascript:print_many_words('bus_stop_code','bus_stop_code_kannada')"
                							 onKeyUp="javascript:print_many_words('bus_stop_code','bus_stop_code_kannada')"
											value='<s:property value="bustops.bus_stop_code"/>'
											id="bus_stop_code">

									</div>
									
								</div>



								<div class="form-group">
									<label class="col-md-3 control-label">Bus Stop Code
										(Kannada)</label>
									<div class="col-md-4">
										<input type="text" class="form-control"
											name="bustops.bus_stop_code_kannada"
											value='<s:property value="bustops.busStopCodeKannada1"/>'
											id="bus_stop_code_kannada">

									</div>
								</div>

								<div class="form-group">
									<!-- <label class="col-md-3 control-label">Lattitude(Currrent)</label> -->
									<div class="col-md-9">
										<input class="hidden" placeholder="" type="text" id="latitude"
											name="bustops.latitude"
											value='<s:property value="bustops.latitude"/>'>
									</div>
								</div>

								<div class="form-group">
									<!-- <label class="col-md-3 control-label">Longitud(Currrent)</label> -->
									<!--  -->
									<div class="col-md-9">
										<input class="hidden" placeholder="" type="text"
											id="longitude" name="bustops.longitude"
											value='<s:property value="bustops.longitude"/>'>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Locality</label>
									<div class="col-md-4">
										<input type="text" class="form-control"
											name="bustops.locality"
											value='<s:property value="bustops.locality"/>'>

									</div>
								</div>

								<div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Landmark</label>
										<div class="col-md-4">
											<input type="text" class="form-control"
												name="bustops.landmark"
												value='<s:property value="bustops.landmark"/>'>

										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Is Sheltered</label>
									<div class="col-md-3">
										<select class="form-control" name="bustops.sheltered"
											id="sheltered">
											<option value="N">N</option>
											<option value="Y">Y</option>


										</select>
									</div>
								</div>

								<div class="form-group">
										<div class="row">
											<div class="col-md-3">
												<label class="col-md-10 control-label">Alias1 </label>
											</div>
											<div class="col-md-4">
												<!-- 
									<div class="col-md-9"> -->
												<input class="form-control input-sm" placeholder=""
													type="text" id="alias1" name="bustops.alias1" value='<s:property value="bustops.alias1"/>'
													maxlength="50" onblur="spclchar(this);">
												<!-- </div> -->
											</div>
											<div class="col-md-3">

												<button class="btn purple" type="button"
													onclick="convertcodeAlias1()">Convert</button>
											</div>
										</div>
									</div>

								<!-- <div class="form-group">
									<label class="col-md-3 control-label">Alias 1</label>
									<div class="col-md-4">
									<input class="form-control input-sm" placeholder=""
													type="text" id="alias4" name="bustops.alias1" 
													maxlength="80" onblur="spclchar(this);">
										

									</div>
								</div> -->
								<div class="form-group">
									<label class="col-md-3 control-label">Kannada Alias 1</label>
									<div class="col-md-4">
										<input class="form-control input-sm" type="text" id="kalias1"
											name="bustops.kalias1"
											value='<s:property value="bustops.kalias11"/>'>
									</div>
								</div>
								<div class="form-group">
										<div class="row">
											<div class="col-md-3">
												<label class="col-md-10 control-label">Alias2 </label>
											</div>
											<div class="col-md-4">
												<!-- 
									<div class="col-md-9"> -->
												<input class="form-control input-sm" placeholder=""
													type="text" id="alias2" name="bustops.alias2" value='<s:property value="bustops.alias2"/>'
													maxlength="80" onblur="spclchar(this);">
												<!-- </div> -->
											</div>
											<div class="col-md-3">

												<button class="btn purple" type="button"
													onclick="convertcodeAlias2()">Convert</button>
											</div>
										</div>
									</div>

								<%-- <div class="form-body">
									<div class="form-group">
										<label class="col-md-3 control-label">Alias 2</label>
										<div class="col-md-4">
											<input type="text" class="form-control" name="bustops.alias2"
												value='<s:property value="bustops.alias2"/>'>

										</div>
									</div> --%>

									<div class="form-group">
										<label class="col-md-3 control-label">Kannada Alias 2</label>
										<div class="col-md-4">
											<input class="form-control input-sm" type="text" id="kalias2"
												name="bustops.k_alias_2"
												value='<s:property value="bustops.k_alias_21"/>'>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-md-3">
												<label class="col-md-10 control-label">Alias3 </label>
											</div>
											<div class="col-md-4">
												<!-- 
									<div class="col-md-9"> -->
												<input class="form-control input-sm" placeholder=""
													type="text" id="alias3" name="bustops.alias3" value='<s:property value="bustops.alias3"/>'
													maxlength="80" onblur="spclchar(this);">
												<!-- </div> -->
											</div>
											<div class="col-md-3">

												<button class="btn purple" type="button"
													onclick="convertcodeAlias3()">Convert</button>
											</div>
										</div>
									</div>
									<%-- <div class="form-group">
										<label class="col-md-3 control-label">Alias 3</label>
										<div class="col-md-4">
											<input type="text" class="form-control" name="bustops.alias3"
												value='<s:property value="bustops.alias3"/>'>

										</div>
									</div> --%>

									<div class="form-group">
										<label class="col-md-3 control-label">Kannada Alias 3</label>
										<div class="col-md-4">
											<input class="form-control input-sm" type="text" id="kalias3"
												name="bustops.k_alias_3"
												value='<s:property value="bustops.k_alias_31"/>'>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-md-3">
												<label class="col-md-10 control-label">Alias4 </label>
											</div>
											<div class="col-md-4">
												<!-- 
									<div class="col-md-9"> -->
												<input class="form-control input-sm" placeholder=""
													type="text" id="alias4" name="bustops.alias4" value='<s:property value="bustops.alias4"/>'
													maxlength="80" onblur="spclchar(this);">
												<!-- </div> -->
											</div>
											<div class="col-md-3">

												<button class="btn purple" type="button"
													onclick="convertcodeAlias4()">Convert</button>
											</div>
										</div>
									</div>
									<%-- <div class="form-group">
										<label class="col-md-3 control-label">Alias 4</label>
										<div class="col-md-4">
											<input type="text" class="form-control" name="bustops.alias4"
												value='<s:property value="bustops.alias4"/>'>

										</div>
									</div> --%>
									<div class="form-group">
										<label class="col-md-3 control-label">Kannada Alias 4</label>
										<div class="col-md-4">
											<input class="form-control input-sm" type="text" id="kalias4"
												name="bustops.k_alias_4"
												value='<s:property value="bustops.k_alias_41"/>'>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-md-3">
												<label class="col-md-10 control-label">Alias5 </label>
											</div>
											<div class="col-md-4">
												<!-- 
									<div class="col-md-9"> -->
												<input class="form-control input-sm" placeholder=""
													type="text" id="alias5" name="bustops.alias5" value='<s:property value="bustops.alias5"/>'
													maxlength="80" onblur="spclchar(this);">
												<!-- </div> -->
											</div>
											<div class="col-md-3">

												<button class="btn purple" type="button"
													onclick="convertcodeAlias5()">Convert</button>
											</div>
										</div>
									</div>
									<%-- <div class="form-group">
										<label class="col-md-3 control-label">Alias 5</label>
										<div class="col-md-4">
											<input type="text" class="form-control" name="bustops.alias5"
												value='<s:property value="bustops.alias5"/>'>

										</div>
									</div> --%>
									<div class="form-group">
										<label class="col-md-3 control-label">Kannada Alias 5</label>
										<div class="col-md-4">
											<input class="form-control input-sm" type="text" id="kalias5"
												name="bustops.k_alias_5"
												value='<s:property value="bustops.k_alias_51"/>'>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-md-3">
												<label class="col-md-10 control-label">Alias6 </label>
											</div>
											<div class="col-md-4">
												<!-- 
									<div class="col-md-9"> -->
												<input class="form-control input-sm" placeholder=""
													type="text" id="alias6" name="bustops.alias6" value='<s:property value="bustops.alias6"/>'
													maxlength="80" onblur="spclchar(this);">
												<!-- </div> -->
											</div>
											<div class="col-md-3">

												<button class="btn purple" type="button"
													onclick="convertcodeAlias6()">Convert</button>
											</div>
										</div>
									</div>
									<%-- <div class="form-group">
										<label class="col-md-3 control-label">Alias 6</label>
										<div class="col-md-4">
											<input type="text" class="form-control" name="bustops.alias6"
												value='<s:property value="bustops.alias6"/>'>

										</div>
									</div> --%>
									<div class="form-group">
										<label class="col-md-3 control-label">Kannada Alias 6</label>
										<div class="col-md-4">
											<input class="form-control input-sm" type="text" id="kalias6"
												name="bustops.k_alias_6"
												value='<s:property value="bustops.k_alias_61"/>'>
										</div>
									</div>


									<div class="form-group">
										<label class="col-md-3 control-label">Toll Zone</label>
										<div class="col-md-3">
											<select class="form-control" name="bustops.tollZone"
												id="tollZone">
												<option value="N">N</option>
												<option value="Y">Y</option>


											</select>
										</div>
									</div>

									<div class="form-group" id="toll_fee_div">
										<label class="col-md-3 control-label">Toll Fee</label>
										<div class="col-md-3">
											<input class="form-control input-sm" placeholder=""
												type="text" id="toll_fee" name="bustops.toll_fee"
												maxlength="6" onblur="checkDecimal(this);"
												value='<s:property value="bustops.toll_fee"/>'>
										</div>
									</div>

									<!-- <div class="form-group">
									<label class="col-md-3 control-label">Code1</label>
									<div class="col-md-9">
										<input class="form-control input-sm"
											 type="text" id="code1"
											name="bustops.code1">
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label">Code2</label>
									<div class="col-md-9">
										<input class="form-control input-sm"
											 type="text" id="code2"
											name="bustops.code2">
									</div>
								</div> -->

									<div class="form-group">
										<label class="col-md-3 control-label">City Limit</label>
										<div class="col-md-3">
											<select class="form-control" name="bustops.cityLimit"
												id="cityLimit">
												<option value="N">N</option>
												<option value="Y">Y</option>
											</select>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Ward Number</label>
										<div class="col-md-4">
											<input type="text" class="form-control"
												name="bustops.wardNumber"
												value='<s:property value="bustops.wardNumber"/>'>

										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Area Population</label>
										<div class="col-md-4">
											<input type="text" class="form-control"
												name="bustops.areaPopulation"
												value='<s:property value="bustops.areaPopulation"/>'>

										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Towards</label>
										<div class="col-md-4">
											<input class="form-control input-sm" type="text"
												id="stop_direction" name="bustops.stop_direction"
												value='<s:property value="bustops.stop_direction"/>'>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Stop Size</label>
										<div class="col-md-4">
											<%-- <input type="text" class="form-control" name="bustops.stopSize"
													value='<s:property value="bustops.stopSize"/>'> --%>
											<select class="form-control input-sm" id="stopSize"
												name="bustops.stopSize">
												<option value="SMALL">SMALL</option>
												<option value="MEDIUM">MEDIUM</option>
												<option value="LARGE">LARGE</option>
											</select>

										</div>
									</div>



									<div class="form-group">
										<label class="col-md-3 control-label">Fare Stage</label>
										<div class="col-md-3">
											<select class="form-control" name="bustops.fareStage"
												id="fareStage">
												<option value="N">N</option>
												<option value="Y">Y</option>


											</select>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-3 control-label">Sub Stage</label>
										<div class="col-md-3">
											<select class="form-control input-sm" id="sub_stage"
												name="bustops.sub_stage">


											</select>
										</div>
									</div>


									<div class="form-group">
										<label class="col-md-3 control-label">Description</label>
										<div class="col-md-4">
											<textarea class="form-control" rows="3"
												name="bustops.description">
										<s:property value="bustops.description" />
									</textarea>

										</div>
									</div>

									<%-- <div class="form-group">
									<label class="col-md-3 control-label">BMTC Status</label>
									<div class="col-md-9">
										<select class="form-control input-sm" id="bmtc_status"
											name="bustops.bmtcStatus">
											
											<option value="New">New</option>
											<option value="Inactive">Inactive</option>
											<option value="Approved">Approved</option>
										</select>
									</div>
								</div> --%>


									<div class="form-group">
										<label class="col-md-3 control-label">Point Type</label>
										<div class="col-md-4">
											<select class="form-control input-sm" id="point_type_id"
												name="bustops.pointtype.point_type_id">
												<s:iterator value="listPoint" id="listPoint">
													<option value="<s:property value="point_type_id"/>">
														<s:property value="point_type_name" />
													</option>
												</s:iterator>
												<!-- <option value="New">New</option>
											<option value="Inactive">Inactive</option>
											<option value="Approved">Approved</option> -->
											</select>
										</div>
									</div>



									<%-- <div class="form-group" style="display: none;" id="org_chart_id_div">
									<label class="col-md-3 control-label">Bus Station</label>
									<div class="col-md-4">
										<select class="form-control input-sm" id="org_chart_id"
											name="bustops.bus_stations.org_chart_id">
											<option value="-1">Select</option>
											<s:iterator value="listOrgcharts" id="listOrgcharts">
											<option value="<s:property value="org_chart_id"/>"><s:property value="org_name"/></option>
											</s:iterator>
											
										</select>
									</div>
								</div> --%>

									<div class="form-group" style="display: none;"
										id="org_chart_id_div">
										<label class="col-md-3 control-label">Bus Station</label>
										<div class="col-md-4" id="org_chart_id_select">
											<%-- <select class="form-control input-sm" id="org_chart_id"
											name="bustops.bus_stations.org_chart_id">
											<option value="-1">Select</option>
											<s:iterator value="listOrgcharts" id="listOrgcharts">
											<option value="<s:property value="org_chart_id"/>"><s:property value="org_name"/></option>
											</s:iterator>
											
										</select> --%>
										</div>
									</div>




									<div class="form-group">
										<label class="col-md-3 control-label">Status</label>
										<div class="col-md-4">
											<select class="form-control input-sm" id="status"
												name="bustops.status">
												<s:if test="bustops.status=='Manual'">
													<option value="Manual">Manual</option>
												</s:if>
												<s:if test="bustops.status!='Manual'">
													<option value="New">New</option>
													<option value="Inactive">Inactive</option>
													<option value="Approved">Approved</option>
												</s:if>

											</select>
										</div>
									</div>
									<div class="form-actions fluid">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn blue">Save Changes</button>
											<button type="button" class="btn default" onclick="goView()">Close</button>
										</div>
									</div>
							</form>
							<!-- END FORM-->

							<%
								} else {
							%>
							<%@ include file="/pages/admin/error.jsp"%>
							<%
								}
							%>

							<%
								} else {
							%>



							<%@ include file="/pages/admin/error.jsp"%>


							<%
								}
							%>


						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		var busstaselvalid;
		//alert("<s:property value="bustops.pointtype.point_type_id"/>");

		var selid = [ "sheltered", "tollZone", "stopSize", "fareStage",
				"status", "point_type_id", "cityLimit", "sub_stage" ];
		var seval = [ "<s:property value="bustops.sheltered"/>",
				"<s:property value="bustops.tollZone"/>",
				"<s:property value="bustops.stopSize"/>",
				"<s:property value="bustops.fareStage"/>",
				"<s:property value="bustops.status"/>",
				"<s:property value="bustops.pointtype.point_type_id"/>",
				"<s:property value="bustops.cityLimit"/>",
				"<s:property value="bustops.sub_stage"/>" ];

		//alert(selid+"====="+seval);

		/* if('<s:property value="bustops.point_type_id"/>'=='6'){
			document.getElementById( 'org_chart_id_div' ).style.display = 'block';
		}else{
			document.getElementById( 'org_chart_id_div' ).style.display = 'none';
		}
		 */
		window.onload = function() {

			makeSelected(selid, seval);
			// alert("hiiii"+$('#point_type_id').val());
			getOrgList($('#point_type_id').val());
			var selid1 = [ "org_chart_id" ];
			//alert("<s:property value="bustops.tollZone"/>");
			var tollzoneval = "<s:property value="bustops.toll_zone"/>";
			//alert($('#tollZone option:selected').text());
			if (tollzoneval == 'Y'
					|| $('#tollZone option:selected').text() == 'Y') {
				document.busstopmapform.toll_fee.readOnly = false;
				document.getElementById("toll_fee").value = "<s:property value="bustops.toll_fee"/>";
			} else {
				document.busstopmapform.toll_fee.readOnly = true;
				document.getElementById("toll_fee").value = "0";
			}

			//alert(busstaselvalid);
			var seval1 = [ "<s:property value="bustops.bus_stations.org_chart_id"/>" ];
			//alert("hiiiii"+seval1);
			setTimeout(function() {
				makeSelectedByid(selid1, seval1);
			}, 3000);

		}
		busstaselvalid = "<s:property value="bustops.point_type_id"/>";

		$('#point_type_id').on("change", function() {
			getOrgList($('#point_type_id').val());
			/* /* if($('#point_type_id').val()=='6'){ */
			/* document.getElementById( 'org_chart_id_div' ).style.display = 'block';
			}else{
			document.getElementById( 'org_chart_id_div' ).style.display = 'none';
			} */
		});

		$('#tollZone').on("change", function() {
			//alert("hiiii");
			var valtoll = "";
			valtoll = $('#tollZone option:selected').text();
			// alert(valtoll);
			if (valtoll == 'Y') {
				//alert("hiiii");
				document.busstopmapform.toll_fee.readOnly = false;
				document.getElementById("toll_fee").value = "0";
			} else {
				document.busstopmapform.toll_fee.readOnly = true;
				document.getElementById("toll_fee").value = "0";
			}
		});

		document.getElementById('sub_stage').innerHTML = "";
		document.getElementById("sub_stage").value = "";
		var select1 = document.getElementById("sub_stage");
		if ($("#fareStage").val() == "Y") {
			select1.options[select1.options.length] = new Option('N', 'N');
		}
		if ($("#fareStage").val() == "N") {
			select1.options[select1.options.length] = new Option('N', 'N');
			select1.options[select1.options.length] = new Option('Y', 'Y');

		}

		$('#fareStage').on("change", function() {
			document.getElementById('sub_stage').innerHTML = "";
			document.getElementById("sub_stage").value = "";
			var val = $("#fareStage").val();
			var select1 = document.getElementById("sub_stage");
			//alert(val);
			if (val == "Y") {
				select1.options[select1.options.length] = new Option('N', 'N');
			}
			if (val == "N") {
				select1.options[select1.options.length] = new Option('N', 'N');
				select1.options[select1.options.length] = new Option('Y', 'Y');

			}
		});

		function goView() {
			document.forms[0].action = 'ShowBusStop.action';
			document.forms[0].submit();
		}

		function makeSelected(id, value) {
			//alert(id+"============"+value);
			for ( var k = 0; k < id.length; k++) {
				//alert("k====>"+k);
				//alert(document.getElementById(id[k]));
				var sel = document.getElementById(id[k]);
				var val = value[k];

				for ( var i = 0, j = sel.options.length; i < j; ++i) {
					//alert(sel.options[i].value+" ==="+ val);
					if (sel.options[i].value === val) {
						sel.selectedIndex = i;
						break;
					}
				}
			}
		}

		function makeSelectedByid(id, value) {
			//alert(id+"============"+value);
			for ( var k = 0; k < id.length; k++) {
				var sel = document.getElementById(id[k]);
				var val = value[k];

				for ( var i = 0, j = sel.options.length; i < j; ++i) {
					//alert(sel.options[i].value+" ==="+ val);
					if (sel.options[i].value === val) {
						sel.selectedIndex = i;
						break;
					}
				}
			}
		}

		function getOrgList(id) {
			$.ajax({
				type : 'POST',
				data : {
					id : id,
					method : '1',
				},
				url : "<s:url action='GetOrgList'/>",

				success : function(data) {
					//alert(data);
					$('#org_chart_id_select').html("");
					if (data != "") {
						$('#org_chart_id_div').show();
						$('#org_chart_id_select').html(data);
						var selid = [ "org_chart_id" ];

						//alert(busstaselvalid);
						var seval = [ busstaselvalid ];

						setTimeout(function() {
							makeSelectedByid(selid, seval);
						}, 3000);
					} else {
						$('#org_chart_id_div').hide();
					}
				},
				error : function(xhr, errmsg) {
				}
			});

		}

		function checkDecimal(el) {
			//alert(el.value);
			var v = parseFloat(el.value);
			if (isNaN(v)) {
				el.value = '0.00';
			} else {
				el.value = v.toFixed(2);
			}

		}
		function convertcodeAlias1() {
			//google.load("language", "1");
			//	alert("hiiii");
			//var a='["'+document.getElementById("busStopName").value+'"]';

			
			google.language
					.transliterate(
							[ document.getElementById("alias1").value ],
							"en",
							"kn",
							function(result) {
								if (!result.error) {
									//var container = document.getElementById("transliteration");
									if (result.transliterations
											&& result.transliterations.length > 0
											&& result.transliterations[0].transliteratedWords.length > 0) {
										//alert(result.transliterations[0].transliteratedWords[0]);
										document
												.getElementById("kalias1").value = result.transliterations[0].transliteratedWords[0];
									}
								}
							});
		}
		function convertcodeAlias1() {
			//google.load("language", "1");
			//	alert("hiiii");
			//var a='["'+document.getElementById("busStopName").value+'"]';

			
			google.language
					.transliterate(
							[ document.getElementById("alias1").value ],
							"en",
							"kn",
							function(result) {
								if (!result.error) {
									//var container = document.getElementById("transliteration");
									if (result.transliterations
											&& result.transliterations.length > 0
											&& result.transliterations[0].transliteratedWords.length > 0) {
										//alert(result.transliterations[0].transliteratedWords[0]);
										document
												.getElementById("kalias1").value = result.transliterations[0].transliteratedWords[0];
									}
								}
							});
		}
		function convertcodeAlias2() {
			google.language
					.transliterate(
							[ document.getElementById("alias2").value ],
							"en",
							"kn",
							function(result) {
								if (!result.error) {
									//var container = document.getElementById("transliteration");
									if (result.transliterations
											&& result.transliterations.length > 0
											&& result.transliterations[0].transliteratedWords.length > 0) {
										//alert(result.transliterations[0].transliteratedWords[0]);
										document
												.getElementById("kalias2").value = result.transliterations[0].transliteratedWords[0];
									}
								}
							});
		}
		function convertcodeAlias3() {
			google.language
					.transliterate(
							[ document.getElementById("alias3").value ],
							"en",
							"kn",
							function(result) {
								if (!result.error) {
									//var container = document.getElementById("transliteration");
									if (result.transliterations
											&& result.transliterations.length > 0
											&& result.transliterations[0].transliteratedWords.length > 0) {
										//alert(result.transliterations[0].transliteratedWords[0]);
										document
												.getElementById("kalias3").value = result.transliterations[0].transliteratedWords[0];
									}
								}
							});
		}
		function convertcodeAlias4() {
			//google.load("language", "1");
			//	alert("hiiii");
			//var a='["'+document.getElementById("busStopName").value+'"]';

			
			google.language
					.transliterate(
							[ document.getElementById("alias4").value ],
							"en",
							"kn",
							function(result) {
								if (!result.error) {
									//var container = document.getElementById("transliteration");
									if (result.transliterations
											&& result.transliterations.length > 0
											&& result.transliterations[0].transliteratedWords.length > 0) {
										//alert(result.transliterations[0].transliteratedWords[0]);
										document
												.getElementById("kalias4").value = result.transliterations[0].transliteratedWords[0];
									}
								}
							});
		}
		function convertcodeAlias5() {
			//google.load("language", "1");
			//	alert("hiiii");
			//var a='["'+document.getElementById("busStopName").value+'"]';

			
			google.language
					.transliterate(
							[ document.getElementById("alias5").value ],
							"en",
							"kn",
							function(result) {
								if (!result.error) {
									//var container = document.getElementById("transliteration");
									if (result.transliterations
											&& result.transliterations.length > 0
											&& result.transliterations[0].transliteratedWords.length > 0) {
										//alert(result.transliterations[0].transliteratedWords[0]);
										document
												.getElementById("kalias5").value = result.transliterations[0].transliteratedWords[0];
									}
								}
							});
		}
		function convertcodeAlias6() {
			//google.load("language", "1");
			//	alert("hiiii");
			//var a='["'+document.getElementById("busStopName").value+'"]';

			
			google.language
					.transliterate(
							[ document.getElementById("alias6").value ],
							"en",
							"kn",
							function(result) {
								if (!result.error) {
									//var container = document.getElementById("transliteration");
									if (result.transliterations
											&& result.transliterations.length > 0
											&& result.transliterations[0].transliteratedWords.length > 0) {
										//alert(result.transliterations[0].transliteratedWords[0]);
										document
												.getElementById("kalias6").value = result.transliterations[0].transliteratedWords[0];
									}
								}
							});
		}
		function spclchar(el) {
			var iChars = "!@$%^&*+=[]\\\’;,./{}|\":?~_";
			for ( var i = 0; i < el.value.length; i++) {
				if (iChars.indexOf(el.value.charAt(i)) != -1) {

					el.value = '';
					alert("Special Characters not allowed");
					return false;
				}
			}
			return true;
		}
	</script>
</body>
</html>

