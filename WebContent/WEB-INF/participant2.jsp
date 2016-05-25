<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Event, model.BordItems, dao.BordItems2, java.util.ArrayList" %>
<%
Event redpepper = (Event) session.getAttribute("event");
BordItems b1 = (BordItems) session.getAttribute("bordItems");
ArrayList<BordItems2> b2 = (ArrayList<BordItems2>) session.getAttribute("bordItems2");
ArrayList<ArrayList<Integer>> pFlagSet = (ArrayList<ArrayList<Integer>>) session.getAttribute("preferredFlagSet");


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
		<td class="Reaper2"><input type="text" name="userName" size="40" required maxlength="20" value="<%= b1.getUserName() %>">必須</td >
	</tr>
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
					<% for (int i = 0; i < redpepper.getCandidate().size(); i++) { %>
					<td><%= Event.getYear(redpepper.getCandidateElement(i)) %>年
						<%= Event.getMonth(redpepper.getCandidateElement(i))+1 %>月
						<%= Event.getDate(redpepper.getCandidateElement(i)) %>日
						<%= Event.getHour(redpepper.getCandidateElement(i)) %>時</td>
					<td><input type="radio" name="eventOpenFlag" required value="1"></td>
					<td><input type="radio" name="eventOpenFlag" required value="2"></td>
					<td><input type="radio" name="eventOpenFlag" required value="0"></td>
				</tr>
				<% } %>
			</table>
		</td>
	</tr>
	<tr>
		<th class="Reaper1"> 備考：</th>
 		<td class="Reaper2"><textarea name=userRemark rows="3" cols="30"  value="<%= b1.getUserRemark() %>"></textarea></td>
	</tr>
</table>
		<input  type="submit" value="編集を終える"></td>
	</form>


<h2>参加者一覧</h2>
<table>


<% for (int i = 0; i < b2.size() ; i++) {%>
<table border="5" frame="hsides" align="center">
	<tr>
		<th class="Reaper1">参加者名</th>
		<td><%= b2.get(i).getUserName() %></td >
	</tr>
	<tr>
		<th class="Reaper1">日程：</th>
		<td class="Reaper2">
			<table  class="table3" border=1>
				<tr>
				<% for (int k = 0; k < redpepper.getCandidate().size(); k++) {%>
					<td><%= Event.getYear(redpepper.getCandidateElement(k)) %>年
						<%= Event.getMonth(redpepper.getCandidateElement(k))+1 %>月
						<%= Event.getDate(redpepper.getCandidateElement(k)) %>日
						<%= Event.getHour(redpepper.getCandidateElement(k)) %>時</td>
					<td><% if(0 == pFlagSet.get(i).get(k)){ %>
					<td>○</td>
					<% }else if(1 == pFlagSet.get(i).get(k)){ %>
					<td>△</td>
					<% }else if(2 == pFlagSet.get(i).get(k)){ %>
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
</table>
<% } %>
<p><a href="index.jsp">HOME</a>
</body>
</html>