
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Event" %>
<%
Event redpepper = (Event) session.getAttribute("event");
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
<form action="/Carolina/EventInformation"  method="post">
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
</td>
	</tr>
	<tr>
		<th>投稿締切日：</th>
		<td><input type="number" name="deadlineYear" size="4" maxlength="4" value="<%= redpepper.getDeadlineYear() %>">年
			<input type="number" name="deadlineDayMonth" size="2"maxlength="2"value="<%= redpepper.getDeadlineMonth()%>">月
			<input type="number" name="deadlineDay" size="2" maxlength="2"value="<%= redpepper.getDeadlineDate() %>">日
	</tr>
	<tr>
	<% for (int i = 0; i < redpepper.getNumberOfEvent().size(); i++) {%>
		<th>場所：</th>
		<td><input type="text" name="eventName" size="40" required maxlength="20" value="<%= Event.get(redpepper.getEventVenue(i)) %>"></td>
	<tr>
		<th>一人分費用：</th>
		<td class="Reaper1"><input type="text" name="eventName" size="40" required maxlength="20" value="<%= Event.get(redpepper.getPricePerPerson(i)) %>"></td>
	</tr>
	<tr>
		<th> 備考：</th>
		<td class="Reaper1"><input type="text" name="eventName" size="40" required maxlength="20" value="<%= Event.get(redpepper.getAutherRemark(i)) %>"></td>
	</tr>
	<tr>
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
		　　削除<input type="radio" name="whtas"  value="1">
		</td>
		</tr>
		<% } %>
	<% } %>
</table>
	</tr>

		<th>候補日数</th></table>
		<tr>
		<td><select id="count" onChange="view()">
		<option value="1">1日分</option>
		<option value="2">2日分</option>
		<option value="3">3日分</option>
		<option value="4">4日分</option>
		<option value="5">5日分</option>
		<option value="6">6日分</option>
		<option value="7">7日分</option>
		<option value="8">8日分</option>
		<option value="9">9日分</option>
		<option value="10">10日分</option>
		<option value="11">11日分</option>
		<option value="12">12日分</option>
		<option value="13">13日分</option>
		<option value="14">14日分</option>
		<option value="15">15日分</option>
		<option value="16">16日分</option>
		<option value="17">17日分</option>
		<option value="18">18日分</option>
		<option value="19">19日分</option>
		<option value="20">20日分</option>
		<option value="21">21日分</option>
		<option value="22">22日分</option>
		<option value="23">23日分</option>
		<option value="24">24日分</option>
		<option value="25">25日分</option>
		<option value="26">26日分</option>
		<option value="27">27日分</option>
		<option value="28">28日分</option>
		<option value="29">29日分</option>
		<option value="30">30日分</option>
		</select><td>
		<div id="hiddenArea" style="display:none;">
		</tr>
		<table border="5" frame="hsides">

		<tr>
			<th></th>
	 		<td><input type="number" name="year"  size="4" maxlength="4">年
				<input type="number"  name="month"  size="2" maxlength="2">月
				<input type="number"  name="day"  size="2"  maxlength="2">日
				<input type="number"  name="hour"  size="2" maxlength="2">時必須</td>
		</tr>
		</table>
 		</div>
 <div id="viewArea"></div>
<input  type="submit" value="イベントを編集する" >

<h2>参加者一覧</h2>

<table border="5" frame="hsides">
	<tr>
		<th>参加者名</th>
		<% for(int i = 0; ) %>
		<td></td>
			<% for (int k = 0; k < redpepper.getCandidate().size(); k++) {%>
			<td><%= Event.getYear(redpepper.getCandidateElement(i)) %>年
				<%= Event.getMonth(redpepper.getCandidateElement(i))+1 %>月
				<%= Event.getDate(redpepper.getCandidateElement(i)) %>日
				<%= Event.getHour(redpepper.getCandidateElement(i)) %>時</td>
			<td><% if(0 == redpepper.getEventOpenFlag()){ %>
			<td>○</td>
			<% }else if(1 == redpepper.getEventOpenFlag()){ %>
			<td>△</td>
			<% }else if(2 == redpepper.getEventOpenFlag()){ %>
			<td>×</td>
			<% } %>
	</tr>
<p><a href="index.jsp">HOME</a>

</table>
</body>
</html>
