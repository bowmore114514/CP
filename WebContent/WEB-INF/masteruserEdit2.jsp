<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Event,model.BordItems, dao.BordItems2, java.util.ArrayList" %>
<%
Event redpepper = (Event) session.getAttribute("event");
BordItems b1 = (BordItems) session.getAttribute("bordItems");
ArrayList<BordItems2> b2 = (ArrayList<BordItems2>) session.getAttribute("bordItems2");
ArrayList<ArrayList<Integer>> pFlagSet = (ArrayList<ArrayList<Integer>>) session.getAttribute("preferredFlagSet");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css" />
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
<h2>参加者情報</h2>
<table>


<% for (int i = 0; i < b2.size(); i++) {%>
<table border="5" frame="hsides" align="center">
<form action="/CarolinaReaper/"MasterUserEditServlet"  method="post">
	<tr>
		<th class="Reaper1">参加者名</th>
		<td><input type="text" name="userName" size="40" required maxlength="20" value="<%= b1.getUserName()%>"></td >
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
					<td><% if(1 == b1.getPreferredFlagSet().get(k)){ %>
					<td>○</td>
					<% }else if(2 == b1.getPreferredFlagSet().get(k)){ %>
					<td>△</td>
					<% }else if(0 == b1.getPreferredFlagSet().get(k)){ %>
					<td>×</td>
					<% } %>
				<% } %>
			</table>
		</td>
	</tr>
	<tr>
		<th class="Reaper1"> 備考：</th>
 		<td class="Reaper3"><textarea name=userRemark rows="3" cols="30"  value="<%= b1.getUserRemark() %>"></textarea></td>
	</tr>
	<tr>
		<td><input type="submit" value="編集する"></td>
	<form action="/CarolinaReaper/"MasterUserEditServlet"  method="post">
		<td><input type="submit"  name="delete" value="削除する"></td>
	</tr>
	</form>
</table>
<% } %>
<p><a href="index.jsp">HOME</a>
</body>
</html>