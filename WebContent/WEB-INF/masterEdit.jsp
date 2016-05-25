<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Event" %>
<%
Event redpepper = (Event) session.getAttribute("event");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>イベント内容の確認</title>
<STYLE type="text/css">
td.Reaper1 { overflow: scroll;  }
</STYLE>


</head>
<body>
<h1>タイトル画像</h1>
<h2>イベント内容の確認</h2>
<form action="/CarolinaReaper/MasterEditServlet<%=redpepper.getEventUrl() %>"  method="post">
 <input type="hidden" name="eventOpenFlag" value="<%=String.valueOf(redpepper.getEventOpenFlga()) %>" />
 <input type="hidden" name="eventid" value="<%=redpepper.getEventId() %>" >
<table border="5" frame="hsides">
	<tr>
		<th>イベント名：</th>
		<td><input type="text" name="eventName" size="40" required maxlength="20" value="<%= redpepper.getEventName() %>"></td>
	</tr>
	<tr>
		 <th>幹事者名：</th>
		<td><input type="text" name="organizarName" size="40" required maxlength="20" value="<%= redpepper.getOrganizarName() %>"></td>
	 </tr>
	<tr>
		<th>イベント作成者</th>
		<td><input type="text" name="autherName" size="40" required maxlength="20" value="<%= redpepper.getAutherName() %>"></td>
	</tr>

	<%for(int i=0;i<redpepper.getEventVenue().size();i++){ %>
	<tr>
		<th>場所<%if(i>0){%> (<%=i+1 %>次回)<%} %>：</th>
		<td><input type="text" name="eventVenue<%=String.valueOf(i) %>" size="40" required maxlength="20" value="<%= redpepper.getEventVenue().get(i) %>"></td>
	</tr>
	<%} %>

	<%for(int i=0;i<redpepper.getPricePerPerson().size();i++){ %>
	<tr>
		<th>一人分費用<%if(i>0){%> (<%=i+1 %>次回)<%} %>：</th>
		<td><input type="text" name="pricePerPerson<%=String.valueOf(i) %>" size="40" required maxlength="20" value="<%= redpepper.getPricePerPerson().get(i) %>"></td>
	</tr>
	<%} %>

	<tr>
		<th>イベント一覧に表示する：</th>
		<td><% if(redpepper.getEventOpenFlga() == 1){ %>
			表示する
			<% } else{ %>
			表示しない
			<% } %>
		</td>
	</tr>
	<tr>
		<th>投稿締切日：</th>
		<td><input type="number" name="deadlineYear" size="4" maxlength="4" value="<%= redpepper.getDeadlineYear() %>">年
			<input type="number" name="deadlineDayMonth" size="2"maxlength="2"value="<%= redpepper.getDeadlineMonth()%>">月
			<input type="number" name="deadlineDay" size="2" maxlength="2"value="<%= redpepper.getDeadlineDate() %>">日
	</tr>
	<%for(int i=0;i<redpepper.getAutherRemark().size();i++){ %>
	<tr>
		<th> 備考<%if(i>0){%> (<%=i+1 %>次回)<%} %>：</th>
		<td class="Reaper1"><input type="text" name="autherRemark<%=String.valueOf(i) %>" size="40" required maxlength="20" value="<%= redpepper.getAutherRemark().get(i) %>"></td>
	</tr>
	<%} %>

<%-- 	<tr>
	<th>登録済み日程候補：</th>
	<td>候補日の削除</td>

	</tr>
	<% for (int i = 0; i < redpepper.getCandidate().size(); i++) {%>
		<tr>
		<th></th>
		<td><%= Event.getYear(redpepper.getCandidateElement(i)) %>年
		<%= Event.getMonth(redpepper.getCandidateElement(i))+1 %>月
		<%= Event.getDate(redpepper.getCandidateElement(i)) %>日
		<%= Event.getHour(redpepper.getCandidateElement(i)) %>時
		　　削除<input type="radio" name="whtas<%=String.valueOf(i) %>"  value="1">
		</td>
		</tr>
	<% } %> --%>
</table>

更新：<input  name="action" type="radio" value="0" ><br>
イベント削除：<input  name="action" type="radio" value="1" ><br>
イベント確定：<input  name="action" type="radio" value="2" ><br>
<input type="submit" value="決定">
</form>
<%-- <form action="/CarolinaReaper/MasterEditServlet<%=redpepper.getEventUrl() %>"  method="post">
<input type="submit"  name="delete" value="イベントの削除">
</form>
<form action="/CarolinaReaper/MasterEditServlet<%=redpepper.getEventUrl() %>"  method="post">
<input type="submit"  name="con" value="イベントの確定">
</form> --%>
</body>
</html>