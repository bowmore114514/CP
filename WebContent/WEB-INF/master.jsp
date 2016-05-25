<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Event,java.util.List" %>
<%@ page import="dao.Event2" %>
<%@ page import="java.util.ArrayList" %>
<%
ArrayList<Event2> eventList = (ArrayList<Event2>) session.getAttribute("event");
%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>管理者用パージ</h1>
<h2>未確定イベント</h2>
<table border="5" frame="hsides" align="center">
	<tr>
		<th>イベント名</th>
		<th>投稿日時</th>
		<th>投稿者名</th>
		<th>締め切り</th>
<!-- 		<th>備考</th> -->
		<th>URL</th>
	</tr>
	<%
    for (Event2 event :eventList) {
    	if( event.getDeterminedFlag() == 0){
%>
	<tr>
		<td><%=event.getEventName()%></td>
		<td><%=event.getRegistYear() %>年<%=event.getRegistMonth() %>月<%=event.getRegistDate() %>日<%=event.getRegistHour() %>時<%=event.getRegistMinute() %>分</td>


		<td><%=event.getOrganizarName() %></td>


		<td><%=event.getDeadlineYear() %>年<%=event.getDeadlineMonth() %>月<%=event.getDeadlineDate() %>日<%=event.getDeadlineHour() %>時<%-- <%=event.getDeadlineMinute() %>分 --%></td></td>

<%-- 	<tr>
		<td><%=event.getAutherRemark() %></td>
	</tr> --%>

		<td><a href="/CarolinaReaper/MasterEventPage<%=event.getEventUrl() %>">/CarolinaReaper/MasterEventPage<%=event.getEventUrl() %></a></td>
</tr>
<%}}%>

</table>
<h2>確定イベント</h2>
<table border="5" frame="hsides" align="center">
	<tr>
		<th>イベント名</th>
		<th>投稿日時</th>
		<th>幹事者名</th>
		<th>締め切り</th>
		<!-- <th>備考</th> -->
		<th>URL</th>
	</tr>
	<%
    for (Event2 event :eventList) {
    	if(event.getDeterminedFlag() == 1){
%>
	<tr>
		<td><%=event.getEventName()%></td>
		<td><%=event.getRegistYear() %>年<%=event.getRegistMonth() %>月<%=event.getRegistDate() %>日<%=event.getRegistHour() %>時<%-- <%=event.getRegistMinute() --%> %>分</td>

		<td><%=event.getOrganizarName() %></td>

		<td><%=event.getDeadlineYear() %>年<%=event.getDeadlineMonth() %>月<%=event.getDeadlineDate() %>日<%=event.getDeadlineHour() %>時<%=event.getDeadlineMinute() %>分</td></td>

	<%-- <tr>
		<td><%=event.getAutherRemark() %></td>
	</tr> --%>

		<td><a href="/CarolinaReaper/MasterEventPage<%=event.getEventUrl() %>">/CarolinaReaper/MasterEventPage<%=event.getEventUrl() %></a></td>
	</tr>
<%}}%>

</table>
</body>
</html>