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
<title>Insert title here</title>
</head>
<body>
<h1>タイトル画像</h1>
<h2>管理者用イベント一覧</h2>
<table border="5" frame="hsides">
	<tr>
		<th>イベント名</th>
		<th>幹事者名</th>
		<th>イベント作成者</th>
		<th>場所</th>
		<th>一人分の負担金</th>
		<th>締切日</th>
	</tr>
	<% for (int i= 1; i < 50; i++) { %>
	<tr>
		<td><a href="<%= redpepper.getEventUrl() %>"><%= redpepper.getEventName() %></a><td>
		<td><%= redpepper.getOrganizarName() %></td>
		<td><%= redpepper.getAutherName() %></td>
		<td><%= redpepper.getEventVenuet() %></td>
		<td><%= redpepper.getDeadlineYear() %>年
			<%= redpepper.getDeadlineMonth()+1 %>月
			<%= redpepper.getDeadlineDate() %>日</td>
	</tr>
	<% } %>
</tale>

</body>
</html>