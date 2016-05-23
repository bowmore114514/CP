<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Event,java.util.List" %>
<!DOCTYPE html >
<html>
<head>
<meta charset=UTF-"8">
 <script language=javascript>
 function view(){
 var obj=document.getElementById('count');
 var str=""
 for(i=0;i<obj.value;i++){
 str+=document.getElementById('hiddenArea').innerHTML;
 }
 document.getElementById('viewArea').innerHTML=str;
 }
  function view2(){
 var obj=document.getElementById('count2');
 var str="";
 for(i=0;i<obj.value;i++){
 str+=document.getElementById('hiddenArea2').innerHTML;
 }
 document.getElementById('viewArea2').innerHTML=str;
 }
 </script>

<title>タイトル</title>
<STYLE type="text/css">
</STYLE>
</head>
<div style="text-align : center">
 <body onLoad="view()">
 <h1></h1>
<h2>タイトル</h>
<p><a href="/EventListView.java">イベント一覧へ</a>

<h2>イベント作成</h2>
<form action="/CarolinaReaper/EventCreation"  method="post">
<table border="5" frame="hsides" align="center">

	<tr>
		<th>イベント名：</th>
		<td><input type="text" name="eventName" size="40" required maxlength="20">必須</td>
	</tr>
	<tr>
		<th>幹事者名：</th>
		<td><input type="text" name="organizarName" size="40" required maxlength="20">必須</td >
	</tr>
	<tr>
		<th>イベント作成者</th>
		<td><input type="text" name="autherName" size="40"  maxlength="20"></td >
	</tr>
	<tr>
		<th>場所：</th>
		<td> <input type="text" name="eventVenue" size="40"  maxlength="20"></td>
	</tr>
	<tr>
		<th>一人分費用：</th>
		<td><input type="number" name="pricePerPerson" size="40" maxlength="20"></td>
	</tr>
	<tr>
		<th>投稿締切日：</th>
		<td><input type="number" name="deadlineYear" size="4" required maxlength="4" value="0000">年
			<input type="number" name="deadlineDayMonth" size="2"maxlength="2" required value="0">月
			<input type="number" name="deadlineDay" size="2" required maxlength="2" value="0">日  　　必須
	</tr>

	<tr>
		<th> 備考：</th>
		<td><textarea name="autherRemark0" rows="3" cols="30"></textarea></td>
	</tr>
		<tr>
		<th>イベント一覧に載せる</th>
		<td>　yes<input type="radio" name="eventOpenFlag" required value="1">
			no<input type="radio" name="eventOpenFlag" required value="0">　　必須</td>
	</tr>
	<tr>
		<th>幹事者用パスワード：</th>
		<td><input type="text" name="autherPass" size="20" required maxlength="20">必須</td>
	</tr>
	</table>

	<%for(int i=0;i<5;i++){ %>
	<table border="5" frame="hsides" align="center">
	<tr>
		<th>場所：</th>
		<td> <input type="text" name="eventVenue<%=String.valueOf(i) %>" size="40" maxlength="20"></td>
	</tr>
	<tr>
		<th>一人分費用：</th>
		<td><input type="number" name="pricePerPerson<%=String.valueOf(i) %>" size="40" maxlength="20"></td>
	</tr>
	<tr>
		<th> 備考：</th>
		<td><textarea name="autherRemark<%=String.valueOf(i+1) %>" rows="3" cols="30"></textarea></td>
	</tr>
	</table>
	<%} %>

		</tr>
		<tr></td>
	</tr>

		<th>候補日数</th></table>
		<%for(int i=0;i<25;i++){ %>
		<tr>
		<table border="5" frame="hsides"align="center">
			<th></th>
	 		<td><input type="number" name="year<%=String.valueOf(i) %>"  size="4" maxlength="4">年
				<input type="number"  name="month<%=String.valueOf(i) %>"  size="2" maxlength="2">月
				<input type="number"  name="day<%=String.valueOf(i) %>"  size="2"  maxlength="2">日
				<input type="number"  name="hour<%=String.valueOf(i) %>"  size="2" maxlength="2">時必須</td>
			</table>

		</tr>
		<%} %>

<input  type="submit" value="イベントを作成する" >
</form>
<p></p>


<input type="text" name="Masterpass" size="8" required maxlength="8">
<form action="/CarolinaReaper/MasterServlet"  method="post">

<input type="submit" value="管理者用ページ">
</body>
</div>
</html>