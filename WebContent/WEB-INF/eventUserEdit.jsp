<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Event,model.BordItems, dao.BordItems2, dao.Event2,java.util.ArrayList" %>
<%
Event event = (Event) session.getAttribute("event");
BordItems item = (BordItems) session.getAttribute("bordItems");
%>

<head>
<div style="text-align : center"></div>
<meta charset=UTF-"8">
<title>投稿内容編集</title>
<style type="text/css">
<link rel="stylesheet" type="text/css" href="style.css" />
<!--
th.Reaper1 { width: 120px; height: 40px; }
td.Reaper2 { width: 200px; height: 40px; }
td.Reaper3 { width: 200px; height: 40px; overflow: scroll;  }
-->
</style>
</head>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>イベント内容</h2>
<table border="5" frame="hsides" align="center">
	<tr>
		<th class="Reaper1">イベント名：</th>
		<td class="Reaper2"><%= event.getEventName() %></td>
		<th class="Reaper1">幹事者名：</th>
		<td class="Reaper2"><%= event.getOrganizarName() %></td >
	 </tr>
	<tr>
		<th class="Reaper1">イベント作成者</th>
		<td class="Reaper2"><%= event.getAutherName() %></td >
		<th class="Reaper1">投稿締切日：</th>
		<td class="Reaper2"><%= event.getDeadlineYear() %>年
			<%= event.getDeadlineMonth()+1 %>月
			<%= event.getDeadlineDate() %>日</td>
	</tr>
</table>
<table border="5" frame="hsides" align="center">
<form action="/CarolinaReaper/EventUserEdit"  method="post">
<input type="hidden" name="eventid" value="<%= event.getEventId() %>">
<input type="hidden" name="itemtid" value="<%= item.getItemId() %>">
<input type="hidden" name="username" value="<%= item.getUserName() %>">
<input type="hidden" name="userregistday" >
<input type="hidden" name="action" value="0">
	<h3><%=item.getUserName() %></h3>


		<th class="Reaper1">日程：</th>
		<td class="Reaper2">
			<table  class="table3" border=1>
				<tr>
					<th> </th>
					<th>○</th>
					<th>△</th>
					<th>×</th>
				</tr>
				<tr>
					<% for (int j = 0; j < event.getCandidate().size(); j++) {%>
					<td><%= Event.getYear(event.getCandidate().get(j)) %>年
						<%= Event.getMonth(event.getCandidateElement(j)) %>月
						<%= Event.getDate(event.getCandidateElement(j)) %>日
						<%= Event.getHour(event.getCandidateElement(j)) %>時</td>
					<td><input type="radio" <%if(item.getPreferredFlagSet().get(j)==1){ %> checked <%} %> name="preferredFlagSet<%= j%>" required value="1"></td>
					<td><input type="radio" <%if(item.getPreferredFlagSet().get(j)==2){ %> checked <%} %> name="preferredFlagSet<%= j%>" required value="2"></td>
					<td><input type="radio" <%if(item.getPreferredFlagSet().get(j)==0){ %> checked <%} %> name="preferredFlagSet<%= j%>" required value="0"></td>
				</tr>
				<% } %>
			</table>
		</td>
		<%-- <% } %> --%>
	<tr>
		<th class="Reaper1"> 備考：</th>
 		<td class="Reaper2"><textarea name="userRemark" rows="3"  cols="30"><%=item.getUserRemark() %></textarea></td>
	</tr>

		<tr><td><input  type="submit" name="decide" value="編集"></td></tr>

	</form>
</table>

</body>
</html>