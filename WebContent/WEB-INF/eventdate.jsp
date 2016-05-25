
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Event, dao.BordItems2, java.util.ArrayList" %>
<%
Event redpepper = (Event) session.getAttribute("event");
ArrayList<BordItems2> b2 = (ArrayList<BordItems2>) session.getAttribute("bordItems2");
ArrayList<ArrayList<Integer>> pFlagSet = (ArrayList<ArrayList<Integer>>) session.getAttribute("preferredFlagSet");
%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>イベント内容の確認</title>
<STYLE type="text/css">
td.Reaper1 { overflow: scroll;  }
</STYLE>
<script language=javascript>
 function view(){
 var obj=document.getElementById('count');
 var str="";
 for(i=0;i<obj.value;i++){
 str+=document.getElementById('hiddenArea').innerHTML;
 }
 document.getElementById('viewArea').innerHTML=str;
 }
 </script>

</head>
<body>
<h1>タイトル画像</h1>
<h2>イベント内容の確認</h2>
<form action="/Carolina/EventReEditing"  method="post">
<table border="5" frame="hsides">
	<tr>
		<th>イベント名：</th>
		<td><input type="text" name="eventName" size="40" required maxlength="20" value="<%= redpepper.getEventName() %>"></td>
	</tr>
	<tr>
		 <th>幹事者名：</th>
		<td><input type="text" name="eventName" size="40" required maxlength="20" value="<%= redpepper.getOrganizarName() %>"></td>
	 </tr>
	<tr>
		<th>イベント作成者</th>
		<td><input type="text" name="eventName" size="40" required maxlength="20" value="<%= redpepper.getAutherName() %>"></td>
	</tr>
	<tr>
		<th>イベント一覧に表示する：</th>
		<td>yes<input type="radio" name="eventOpenFlag" required value="1">
			no<input type="radio" name="eventOpenFlag" required value="0"></td>
	</tr>
		<tr>
		<th> 備考：</th>
		<td class="Reaper1"><input type="text" name="eventName" size="40" required maxlength="20" value="<%= redpepper.getAutherRemark().get(0) %>"></td>
	</tr>
	<tr>
		<th>投稿締切日：</th>
		<td><input type="number" name="deadlineYear" size="4" maxlength="4" value="<%= redpepper.getDeadlineYear() %>">年
			<input type="number" name="deadlineDayMonth" size="2"maxlength="2"value="<%= redpepper.getDeadlineMonth()%>">月
			<input type="number" name="deadlineDay" size="2" maxlength="2"value="<%= redpepper.getDeadlineDate() %>">日
	</tr>
	<tr>
	<% for (int i = 0; i < Integer.parseInt(redpepper.getNumberOfEvent()); i++) {%>
		<th>場所：</th>
		<td><input type="text" name="eventName" size="40" required maxlength="20" value="<%= redpepper.getEventVenue().get(i) %>"></td>
	<tr>
		<th>一人分費用：</th>
		<td class="Reaper1"><input type="text" name="eventName" size="40" required maxlength="20" value="<%= redpepper.getPricePerPerson().get(i)%>"></td>
	</tr>
	<tr>
	<th>登録済み日程候補：</th>
	<td>候補日の削除</td>
<% } %>
	</tr>
		<% for (int i = 0; i < redpepper.getCandidate().size(); i++) {%>
	<tr>
		<th></th>
		<td><%= Event.getYear(redpepper.getCandidateElement(i)) %>年
		<%= Event.getMonth(redpepper.getCandidateElement(i))+1 %>月
		<%= Event.getDate(redpepper.getCandidateElement(i)) %>日
		<%= Event.getHour(redpepper.getCandidateElement(i)) %>時
		</td>
	</tr>
		<% } %>

<h2>参加者一覧</h2>

<table border="5" frame="hsides">
	<tr>
		<th>参加者名</th>
		<% for(int i = 0; i < b2.size(); i++){ %>
		<td></td>
			<% for (int k = 0; k < redpepper.getCandidate().size(); k++) { %>
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
		<% } %>
	</tr>
</table>

<input type="submit"  value="編集を終了する"></form>

<form action="/Carolina/EventReEditing"  method="post">
<input type="submit"  name="delete" value="イベントの削除">

<form action="/Carolina/EventReEditing"  method="post">
<input type="submit"  name="null" value="イベントの確定">


<p><a href="EventConfirmation.java">HOME</a>


</table>
</body>
</html>
