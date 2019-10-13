<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<s:if test="%{formFourList.size()==0}">
		<center>
			<font color="red"> FormFour is not Available for This Schedule

			</font>
		</center>
	</s:if>
	<s:else>
		<table class="table table-striped table-bordered table-hover"
			id="weeklyid">
			<thead>
				<tr>hhhhhhhhhh
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
				<s:iterator value="formFourList" var="a" status="ctr">
					<tr id=<%=i%>>
						<td><s:property value="scheduleNumberName" /> <%-- ${fn:substring(effectiveStartDate, 5, 13)}-${fn:substring(effectiveEndDate, 5, 13)} 
				) --%></td>
						<td><s:property value="effectiveStartDate" /></td>
						<td><s:property value="effectiveEndDate" /></td>
						<td><input type="hidden"
							name="weeklychartList[<%=i%>].formFour.id"
							value='<s:property value="id" />' id="formFourid" /> <input
							type="hidden" name="formFourid" value='<s:property value="id" />'
							id="formFourid1" /> <input type="hidden" id="mon<%=i%>"
							name="abcName<%=i%>"
							value='<s:property value="scheduleNumberName"/>' /> <s:checkbox
								name="%{'weeklychartList[' + #ctr.index + '].mondaystatus'}"
								id="mon_%{#ctr.index}" fieldValue="true" value="false"
								theme="simple" onclick="isUncheck1(this.value);" /></td>
						<td><s:checkbox
								name="%{'weeklychartList[' + #ctr.index + '].tuesdaystatus'}"
								id="tue_%{#ctr.index}" fieldValue="true" value="false"
								theme="simple" onclick="isUncheck2(this.value)" /></td>
						<td><s:checkbox
								name="%{'weeklychartList[' + #ctr.index + '].wednesdaystatus'}"
								id="wed_%{#ctr.index}" fieldValue="true" value="false"
								theme="simple" onclick="isUncheck3(this.value)" /></td>
						<td><s:checkbox
								name="%{'weeklychartList[' + #ctr.index + '].thursdaystatus'}"
								id="thu_%{#ctr.index}" fieldValue="true" value="false"
								theme="simple" onclick="isUncheck4(this.value)" /></td>
						<td><s:checkbox
								name="%{'weeklychartList[' + #ctr.index + '].fridaystatus'}"
								id="fri_%{#ctr.index}" fieldValue="true" value="false"
								theme="simple" onclick="isUncheck5(this.value)" /></td>
						<td><s:checkbox
								name="%{'weeklychartList[' + #ctr.index + '].saturdaystatus'}"
								id="sat_%{#ctr.index}" fieldValue="true" value="false"
								theme="simple" onclick="isUncheck6(this.value)" /></td>
						<td><s:checkbox
								name="%{'weeklychartList[' + #ctr.index + '].sundaystatus'}"
								id="sun_%{#ctr.index}" fieldValue="true" value="false"
								theme="simple" onclick="isUncheck7(this.value)" /></td>
						<td><s:checkbox
								name="%{'weeklychartList[' + #ctr.index + '].holidaystatus'}"
								id="holi_%{#ctr.index}" fieldValue="true" value="false"
								theme="simple" onclick="isUncheck7(this.value)" /></td>
					</tr>
					<%
						i++;
					%>
				</s:iterator>
			</tbody>


		</table>
	</s:else>
</body>
</html>