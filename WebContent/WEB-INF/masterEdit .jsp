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
		<th>場所：</th>
		<td><input type="text" name="eventName" size="40" required maxlength="20" value="<%= redpepper.getEventVenue() %>"></td>
	</tr>
	<tr>
		<th>一人分費用：</th>
		<td><input type="text" name="eventName" size="40" required maxlength="20" value="<%= redpepper.getPricePerPerson() %>"></td>
	</tr>
	<tr>
		<th>イベント一覧に表示する：</th>
		<td><%= if(eventFlag == 1){
			out.prin("表示する")}
			else{
			out.prin("表示しない")} %>
		</td>
	</tr>
	<tr>
		<th>投稿締切日：</th>
		<td><input type="number" name="deadlineYear" size="4" maxlength="4" value="<%= redpepper.getDeadlineYear() %>">年
			<input type="number" name="deadlineDayMonth" size="2"maxlength="2"value="<%= redpepper.getDeadlineMonth()%>">月
			<input type="number" name="deadlineDay" size="2" maxlength="2"value="<%= redpepper.getDeadlineDate() %>">日
	</tr>
	<tr>
		<th> 備考：</th>
		<td class="Reaper1"><input type="text" name="eventName" size="40" required maxlength="20" value="<%= redpepper.getAutherRemark() %>"></td>
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
</table>
	</tr>
<h2>参加者一覧</h2>

<table border="5" frame="hsides">
	<tr>
		<th></th>
		<% for (int i = 0; i <参加者人数 ; i++) { %>
		<td><%=  (i) %>さん</td>
	<% } %>
	</tr>
	<% for (int i = 0; i < 登録日程数 ; i++) { %>
	<tr>
		<td><%= 登録日程 (i) %><td>
		<%= for(int j = 0; i < 登録日程数 ; i++) { %>
		<td>
			<%= if( 登録参加不参加 == 0){
			out.print("○") ;
			}
			else if( 登録参加不参加 == 1) {
			 out.print("△");
			 }
			else if( 登録参加不参加 == 2) {
			 out.print("×") ;
			} %>

		</td>
			<% } %>
	</tr>
	<% } %>

</table>
<input  type="submit" value="決定" >
<a href="/CarolinaReaper/MasterEditServlet" >このイベントを削除</a>
</body>
</html>