<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Event,java.util.List" %>
<%
Event redpepper = (Event) session.getAttribute("event");
%>
<!DOCTYPE html >
<html>
<head>
<div style="text-align : center">
<meta charset=UTF-"8">
<title>タイトル</title>
<style type="text/css">

<!--
th.Reaper1 { width: 120px; height: 40px; }
td.Reaper2 { width: 200px; height: 40px; }
td.Reaper3 { width: 200px; height: 40px; overflow: scroll;  }
-->
</style>
</head>
<body>
<h2>イベント内容の確認</h2>
<table border="5" frame="hsides" align="center">
	<tr>
		<th class="Reaper1">イベント名：</th>
		<td class="Reaper2"><%= redpepper.getEventName() %></td>
		<th class="Reaper1">幹事者名：</th>
		<td class="Reaper2"><%= redpepper.getOrganizarName() %></td >
	 </tr>
	<tr>
		<th class="Reaper1">イベント作成者</th>
		<td class="Reaper2"><%= redpepper.getAutherName() %></td >
		<th class="Reaper1">場所：</th>
		<td class="Reaper2"><%= Event.get(redpepper.getEventVenue(i)) %></td>
	</tr>
	<tr>
		<th class="Reaper1">一人分費用：</th>
		<td class="Reaper2"><%= Event.get(redpepper.getPricePerPerson(i)) %></td>
		<th class="Reaper1">投稿締切日：</th>
		<td class="Reaper2"><%= redpepper.getDeadlineYear() %>年
			<%= redpepper.getDeadlineMonth()+1 %>月
			<%= redpepper.getDeadlineDate() %>日</td>
	</tr>
	<tr>
		<th class="Reaper1"> 備考：</th>
		<td class="Reaper3" colspan="3"><%= Event.get(redpepper.getAutherRemark(i)) %></td>
	</tr>
	<tr>
		<th class="Reaper1"> パスワード：</th>
		<td><input type="text" name="userPass" size="20" required maxlength="20">必須</td>
		<td colspan="2"><input  type="submit" value="イベントを編集する" ></td>
	</tr>
</table>
</body>
</html>