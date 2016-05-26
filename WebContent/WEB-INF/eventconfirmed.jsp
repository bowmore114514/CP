<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Event" %>
<%
Event redpepper = (Event) session.getAttribute("event");
%>
<!DOCTYPE html >
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css" />
<div style="text-align : center">
<meta charset="UTF-8">
<title>イベント内容の確認</title>
<STYLE type="text/css"></STYLE>
<!--
th.Reaper1 { width: 120px; height: 40px; }
td.Reaper2 { width: 200px; height: 40px; }
td.Reaper3 { width: 200px; height: 40px; overflow: scroll;  }
-->
</head>
<body background="C:\Users\taiko\Desktop/14942842_M.jpg" bgproperties="fixed" text="#fafafa" background-size="contain">
<div style="text-align : center">
<h1>タイトル画像</h1>
<h2>イベント内容の確認</h2>
<table border="5" frame="hsides" align="center" bordercolor="#808080">
	<tr>
		<th class="Reaper1">イベント名：</th>
		<td class="Reaper2"><%= redpepper.getEventName() %></td>
	</tr>
	<tr>
		 <th class="Reaper1">幹事者名：</th>
		<td class="Reaper2"><%= redpepper.getOrganizarName() %></td >
	 </tr>
	<tr>
		<th class="Reaper1">イベント作成者</th>
		<td class="Reaper2"><%= redpepper.getAutherName() %></td >
	</tr>
	<tr>
		<th class="Reaper1">投稿締切日：</th>
		<td class="Reaper2"><%= redpepper.getDeadlineYear() %>年
			<%= redpepper.getDeadlineMonth()+1 %>月
			<%= redpepper.getDeadlineDate() %>日</td>
	</tr>
	<tr>
		<th class="Reaper1"> 備考：</th>
		<td class="Reaper2"><%= redpepper.getAutherRemark() %></td>
	</tr>
</table>
<table border="5" frame="hsides" align="center" bordercolor="#808080">

		<% for (int i = 0; i < Integer.parseInt(redpepper.getNumberOfEvent()); i++) {%>
		<a><%= (i)+1 %>次回
<table border="5" frame="hsides" align="center" bordercolor="#808080">
	<tr>
		<th class="Reaper1">場所：</th>
		<td class="Reaper2"><%= redpepper.getEventVenue().get(i) %></td>
	</tr>
	<tr>
		<th class="Reaper1">一人分費用：</th>
		<td class="Reaper2"><%= redpepper.getPricePerPerson().get(i) %></td>
	</tr>
</table>
	<% } %>

<table border="5" frame="hsides" align="center" bordercolor="#808080">
	<tr>
	<th class="Reaper1">日程候補：</th>
	<td class="Reaper2"><%= redpepper.getCandidate().size() %>日分の日程候補</td>
	</tr>
	<% for (int i = 0; i < redpepper.getCandidate().size(); i++) {%>
		<tr>
		<th></th>
		<td class="Reaper2"><%= Event.getYear(redpepper.getCandidateElement(i)) %>年
		<%= Event.getMonth(redpepper.getCandidateElement(i))+1 %>月
		<%= Event.getDate(redpepper.getCandidateElement(i)) %>日
		<%= Event.getHour(redpepper.getCandidateElement(i)) %>時</td>
		</tr>
	<% } %>

</table>
<p>URLを表示します
<a><input type="text" name="eventName" size="40" required maxlength="20" value="http//<%= redpepper.getEventUrl() %>.jp"></a>
<p><a href="EventConfirmation.java">HOME</a></p>
</div>
</body>
</html>