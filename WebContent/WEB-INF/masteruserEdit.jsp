<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Event, dao.BordItems2, java.util.ArrayList"%>
<%
Event redpepper = (Event) session.getAttribute("event");
ArrayList<BordItems2> b2 = (ArrayList<BordItems2>) session.getAttribute("bordItems2");
ArrayList<ArrayList<Integer>> pFlagSet = (ArrayList<ArrayList<Integer>>) session.getAttribute("preferredFlagSet");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div style="text-align : center">

<title>Insert title here</title>
<style type="text/css">
<link rel="stylesheet" type="text/css" href="style.css" />
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
<h2>参加者一覧</h2>


<% for (int i = 0; i < b2.size() ; i++) {%>
<table border="5" frame="hsides" align="center">
<form action="/CarolinaReaper/"MasterUserEditServlet"  method="get">
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
		<input  type="submit" value="編集する"></td>
	</tr>
	</form>
</table>
<% } %>
<p><a href="index.jsp">HOME</a>
</body>
</html>