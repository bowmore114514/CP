<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Event" %>
<%
Event redpepper = (Event) session.getAttribute("event");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div style="text-align : center">

<title>Insert title here</title>
<style type="text/css">
.table3 { border-collapse: collapse;
}
.table3 th {
  background-color: #cccccc;
}
td.Reaper4 { overflow: scroll;  }
<!--
th.Reaper1 { width: 120px; height: 40px; }
td.Reaper2 { width: 200px; height: 40px; }
td.Reaper3 { width: 200px; height: 40px; overflow: scroll;  }
-->
</style>
</head>
<body>
<h2> 参加者記入欄</hs>
<table border="5" frame="hsides" align="center">
<form action="/CarolinaReaper/"EventEditServlet"  method="post">
	<tr>
		<th class="Reaper1">参加者名</th>
		<td class="Reaper2"><input type="text" name="userName" size="40" required maxlength="20">必須</td >
	</tr>
	<tr>
		<% for (int i = 0; i < Integer.parseInt(redpepper.getNumberOfEvent()); i++) {%>
		<td class="Reaper1"><%= i %>次回</td>
	<tr>
		<th class="Reaper1">日程：</th>
		<td class="Reaper2">
			<table  class="table3" border=1>
				<tr>
					<th></th>
					<th>○</th>
					<th>△</th>
					<th>×</th>
				</tr>
				<tr>
					<% for (int j = 0; i < redpepper.getCandidate().size(); j++) {%>
					<td><%= Event.getYear(redpepper.getCandidateElement(i)) %>年
						<%= Event.getMonth(redpepper.getCandidateElement(i))+1 %>月
						<%= Event.getDate(redpepper.getCandidateElement(i)) %>日
						<%= Event.getHour(redpepper.getCandidateElement(i)) %>時</td>
					<td><input type="radio" name="eventOpenFlag" required value="0"></td>
					<td><input type="radio" name="eventOpenFlag" required value="1"></td>
					<td><input type="radio" name="eventOpenFlag" required value="2"></td>
				</tr>
				<% } %>
			</table>
		</td>
	</tr>
		<% } %>
	<tr>
		<th class="Reaper1"> 備考：</th>
 		<td class="Reaper2"><textarea name=userRemark rows="3" cols="30"></textarea></td>
	</tr>
	<tr>
		<th class="Reaper1">参加者用パスワード：</th>
		<td class="Reaper2"><input type="text" name="userPass" size="20" required maxlength="20">必須
		<input  type="submit" value="参加決定"></td>
	</tr>
	</form>
</table>




<h2>参加者一覧</h2>
<table>


<% for (int i = 0; i < ; i++) {%>
<table border="5" frame="hsides" align="center">
<form action="/CarolinaReaper/"eventEditServlet"  method="post">
	<tr>
		<th class="Reaper1">参加者名</th>
		<td><% redpepper.getUserName() %></td >
	</tr>
	<tr>
		<% for (int j = 0; j < redpepper.getNumberOfEvent().size(); j++) {%>
		<td class="Reaper1"><%= redpepper.getNumberOfEvent() %>次回</td>
	<tr>
		<th class="Reaper1">日程：</th>
		<td class="Reaper2">
			<table  class="table3" border=1>
				<tr>
				<% for (int k = 0; k < redpepper.getCandidate().size(); k++) {%>
					<td><%= Event.getYear(redpepper.getCandidateElement(i)) %>年
						<%= Event.getMonth(redpepper.getCandidateElement(i))+1 %>月
						<%= Event.getDate(redpepper.getCandidateElement(i)) %>日
						<%= Event.getHour(redpepper.getCandidateElement(i)) %>時</td>
					<td><% if(0 == redpepper.getEventOpenFlag()){ %>
					<td>○</td>
					<% }else if(1 == redpepper.getEventOpenFlag()){ %>
					<td>△</td>
					<% }else if(2 == redpepper.getEventOpenFlag()){ %>
					<td>×</td>
					<% } %>
				<% } %>
			</table>
		</td>
	</tr>
	<tr>
		<th class="Reaper1"> 備考：</th>
 		<td class="Reaper3"><% Event.get??????(redpepper.getUserRemak(i)) %></td>
	</tr>
	<% } %>
	<tr>
		<th class="Reaper1">編集パスワード入力：</th>
		<td class="Reaper2"><input type="text" name="userPass" size="20" required maxlength="20">必須
		<input  type="submit" value="編集する"></td>
	</tr>
	</form>
</table>
<% } %>
<p><a href="index.jsp">HOME</a>
</body>
</html>