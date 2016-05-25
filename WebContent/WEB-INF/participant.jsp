<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Event,model.BordItems, dao.BordItems2, java.util.ArrayList"%>
<%
Event redpepper = (Event) session.getAttribute("event");
ArrayList<BordItems2> b2 = (ArrayList<BordItems2>) session.getAttribute("bordItems2");
ArrayList<ArrayList<Integer>> pFlagSet = (ArrayList<ArrayList<Integer>>) session.getAttribute("preferredFlagSet");

%>
<!DOCTYPE html >
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
<h2> 参加者記入欄</h2>
<table border="5" frame="hsides" align="center">
<form action="/CarolinaReaper/EventPage"  method="post">
	<tr>
		<th class="Reaper1">参加者名</th>
		<td class="Reaper2"><input type="text" name="userName" size="40" required maxlength="20">必須</td >
	</tr>
	<tr>
		<% for (int i = 0; i < 1; i++) {%>
		<td class="Reaper1"><%= i +1 %>次回</td>
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
					<% for (int j = 0; j < 1; j++) {%>
					<td><%= Event.getYear(redpepper.getCandidate().get(i)) %>年
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
		<input  type="submit" name="decide
		" value="参加決定"></td>
	</tr>
	</form>
</table>




<h2>参加者一覧</h2>
<table>


<%   if(b2 != null && b2.size()!=0){ for (int i = 0; i < 1; i++) { %>

<table border="5" frame="hsides" align="center">
<form action="/CarolinaReaper/"eventEditServlet"  method="post">
	<tr>
		<th class="Reaper1">参加者名</th>
		<td><%= b2.get(i).getUserName() %></td >
	</tr>
	<tr>
		<th class="Reaper1">日程：</th>
		<td class="Reaper2">
			<table  class="table3" border=1>
				<tr>
				<% for (int k = 0; k < 1; k++) { %>
					<td><%= Event.getYear(redpepper.getCandidate().get(i)) %>年
						<%= Event.getMonth(redpepper.getCandidate().get(i)) %>
						<%= Event.getDate(redpepper.getCandidate().get(i)) %>日
						<%= Event.getHour(redpepper.getCandidate().get(i)) %>時</td>
					<td><% if(1 == pFlagSet.get(i).get(k)){ %>
					<td>○</td>
					<% }else if(2 == pFlagSet.get(i).get(k)){ %>
					<td>△</td>
					<% }else if(0 == pFlagSet.get(i).get(k)){ %>
					<td>×</td>
					<% } %>
				<% } %>
			</table>
		</td>
	</tr>
	<tr>
		<th class="Reaper1"> 備考：</th>
 		<td class="Reaper3"><%= b2.get(i).getUserRemark() %></td>
	</tr>
	<tr>
		<th class="Reaper1">編集パスワード入力：</th>
		<td class="Reaper2"><input type="text" name="userPass" size="20" required maxlength="20">必須
		<input  type="submit" name="edit" value="編集する"></td>
	</tr>
	</form>
</table>
<% }} %>
<p><a href="index.jsp">HOME</a>
</body>
</html>