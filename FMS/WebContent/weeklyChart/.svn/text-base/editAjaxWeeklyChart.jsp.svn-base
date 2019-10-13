<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
function isUnc() {
	var rows = document.getElementById("weeklyid2").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length;
	var schname = "";
	var x;
	var cnt = 0;
	for (i = 0; i < rows; i++) {
		//var val = document.getElementById("mon" + (i)).value;
		x = document.getElementById("mon_" + (i)).checked;
		if (x == true) {
			cnt = cnt + 1;
			schname = document.getElementById("mon1" + (i)).value;
		}
	}

	for (i = 0; i < rows; i++) {
		//alert("hello"+x)
		var val = document.getElementById("mon1" + (i)).value;
		if (schname != val) {
			document.getElementById("mon_" + (i)).disabled = true;
		}

	}
	if (cnt == 0) {
		for (i = 0; i < rows; i++) {
			document.getElementById("mon_" + (i)).disabled = false;
		}
	}

}
</script>
</head>
<body> 
 <s:if test="%{weeeklylist.size()==0}">
									<center><font color="red">
									
									FormFour is not Available for This Schedule
									
									</font></center>
								</s:if>
								<s:else>
<table class="table table-striped table-bordered table-hover"
	id="weeklyid2">
	<thead>
		<tr>
			<th>Form Four Id</th>
			<th>Form Four Name</th>
			<th>Start Date</th>
			<th>End Date</th>
			<th>Monday</th>
			<th>Tuesday</th>
			<th>Wednesday</th>
			<th>Thursday</th>
			<th>Friday</th>
			<th>Saturday</th>
			<th>Sunday</th>
			<th>Holiday</th>
		</tr>
	</thead>
	<tbody>
      
		<%
			int i = 0;
		%>
		<s:iterator value="weeeklylist" id="li" var="a" status="ctr">
			<%-- <input type="text" name="weeklychartList[<%=i%>].getformfour.id" value="<s:property value='weeklychartList[<%=i%>].getformfour.id'/>"/>
			
			
								<input type="text" name="weeklychartList[<%=i%>].getformfour.id" value='<s:property value="#a.getformfour.scheduleNumberName"/>'/> --%>

				<tr id=<%=i%>>
				<td><s:property value="formfourid" /></td>
				<td><s:property value="formfourname" /></td>
				<td><s:property value="startdate" /></td>
				<td><s:property value="enddate" /></td>
				
				<td><input type="hidden" id="mon<%=i%>" name="abcName"
					value="<s:property value="weekly_chart_id"/>" /> 
					<input type="hidden"
					id="mon1<%=i%>" name="abcName<%=i%>"
					value='<s:property value="formfourname"/>' />
					<input type="hidden"
					id="formfour" name="formfourid"
					value='<s:property value="formfourid"/>' />
					<s:if test="monday==1">
						<s:checkbox  cssClass="checkmon" 
							name="%{'weeklychartList[' + #ctr.index + '].mondaystatus'}"
							id="mon_%{#ctr.index}" fieldValue="true" value="true" 
							theme="simple" onclick="isUncheck1()" />
					</s:if> <s:else>
									
					<s:checkbox  cssClass="checkmon"
							name="%{'weeklychartList[' + #ctr.index + '].mondaystatus'}"
							id="mon_%{#ctr.index}" fieldValue="false" value="false" 
							theme="simple" onclick="isUncheck1()" />
												
					</s:else></td>
				<td><s:if test="tuesday==1">
						<s:checkbox cssClass="checktue"
							name="%{'weeklychartList[' + #ctr.index + '].tuesdaystatus'}"
							id="tue_%{#ctr.index}" fieldValue="true" value="true"
							theme="simple" onclick="isUncheck2()" />
					</s:if> <s:else>
						<s:checkbox cssClass="checktue"
							name="%{'weeklychartList[' + #ctr.index + '].tuesdaystatus'}"
							id="tue_%{#ctr.index}" fieldValue="false" value="false" 
							theme="simple" onclick="isUncheck2()" />
					</s:else></td>
				<td><s:if test="wednesday==1">
						<s:checkbox cssClass="checkwed"
							name="%{'weeklychartList[' + #ctr.index + '].wednesdaystatus'}"
							id="wed_%{#ctr.index}" fieldValue="true" value="true"
							theme="simple" onclick="isUncheck3()" />
					</s:if> <s:else>
						<s:checkbox cssClass="checkwed"
							name="%{'weeklychartList[' + #ctr.index + '].wednesdaystatus'}"
							id="wed_%{#ctr.index}" fieldValue="false" value="false" 
							theme="simple" onclick="isUncheck3()" />
					</s:else></td>
				<td><s:if test="thursday==1">
						<s:checkbox cssClass="checkthu"
							name="%{'weeklychartList[' + #ctr.index + '].thursdaystatus'}"
							id="thu_%{#ctr.index}" fieldValue="true" value="true"
							theme="simple" onclick="isUncheck4()" />
					</s:if> <s:else>
						<s:checkbox cssClass="checkthu"
							name="%{'weeklychartList[' + #ctr.index + '].thursdaystatus'}"
							id="thu_%{#ctr.index}" fieldValue="false" value="false" 
							theme="simple" onclick="isUncheck4()" />
					</s:else></td>
				<td><s:if test="friday==1">
						<s:checkbox cssClass="checkfri"
							name="%{'weeklychartList[' + #ctr.index + '].fridaystatus'}"
							id="fri_%{#ctr.index}" fieldValue="true" value="true"
							theme="simple" onclick="isUncheck5()" />
					</s:if> <s:else>
						<s:checkbox cssClass="checkfri"
							name="%{'weeklychartList[' + #ctr.index + '].fridaystatus'}"
							id="fri_%{#ctr.index}" fieldValue="false" value="false" 
							theme="simple" onclick="isUncheck5()" />
					</s:else></td>
				<td><s:if test="saturday==1">
						<s:checkbox cssClass="checksat"
							name="%{'weeklychartList[' + #ctr.index + '].saturdaystatus'}"
							id="sat_%{#ctr.index}" fieldValue="true" value="true"
							theme="simple" onclick="isUncheck6()" />
					</s:if> <s:else>
						<s:checkbox cssClass="checksat"
							name="%{'weeklychartList[' + #ctr.index + '].saturdaystatus'}"
							id="sat_%{#ctr.index}" fieldValue="false" value="false" 
							theme="simple" onclick="isUncheck6()" />
					</s:else></td>
				<td><s:if test="sunday==1">
						<s:checkbox cssClass="checksun"
							name="%{'weeklychartList[' + #ctr.index + '].sundaystatus'}"
							id="sun_%{#ctr.index}" fieldValue="true" value="true"
							theme="simple" onclick="isUncheck7()" />
					</s:if> <s:else>
						<s:checkbox cssClass="checksun"
							name="%{'weeklychartList[' + #ctr.index + '].sundaystatus'}"
							id="sun_%{#ctr.index}" fieldValue="false" value="false" 
							theme="simple" onclick="isUncheck7()" />
					</s:else></td>
					
					<td><s:if test="holiday==1">
						<s:checkbox cssClass="checkholi"
							name="%{'weeklychartList[' + #ctr.index + '].holidaystatus'}"
							id="holi_%{#ctr.index}" fieldValue="true" value="true"
							theme="simple" onclick="isUncheck8()" />
					</s:if> <s:else>
						<s:checkbox cssClass="checkholi"
							name="%{'weeklychartList[' + #ctr.index + '].holidaystatus'}"
							id="holi_%{#ctr.index}" fieldValue="false" value="false" 
							theme="simple" onclick="isUncheck8()" />
					</s:else></td>
					
				
			</tr>
			<%
				i++;
			%>
		</s:iterator>
		
	</tbody>


</table></s:else>
</body>
</html>