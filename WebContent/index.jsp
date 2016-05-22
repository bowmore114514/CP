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
 var str="";
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
 <!--
 <!--
BODY { background-color:  }  /* 全体の背景色 */
TABLE { background-color:  } /* 表の背景色 */
TD { background-color:  }    /* セルの背景色 */
-->
.scr {
   overflow: scroll;   /* スクロール表示 */
   width : 600px;
   height: 200px;
 }
-->
</STYLE>
</head>
<div style="text-align : center">
 <body onLoad="view()">
 <h1></h1>
<h2>タイトル</h2>

<DIV class="scr" style="text-align : center" >

	<table border="5" frame="hsides" align="center">
<thead>
	<tr>
		<th width="150">イベント名</th>
		<th width="100">幹事名</th>
		<th width="100">場所</th>
		<th width="80">締切日</th>
		<th width="400">備考</th>
	</tr>
</thead>
<tbody>

</tbody>

	</table>
</DIV>
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
		<td><textarea name="autherRemark" rows="3" cols="30"></textarea></td>
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
	<tr>
		<th>何次会まで行いますか：</th>
		<td><select name="numberOf" Event id="count2" onChange="view2()">
		<option value="0">1次会</option>
		<option value="1">2次会</option>
		<option value="2">3次会</option>
		<option value="3">4次会</option>
		<option value="4">5次会</option>
		</select></td>
	 <div id="hiddenArea2" style="display:none;">
	<table border="5" frame="hsides" align="center">
	<tr>
		<th>場所：</th>
		<td> <input type="text" name="eventVenue" size="40" maxlength="20"></td>
	</tr>
	<tr>
		<th>一人分費用：</th>
		<td><input type="number" name="pricePerPerson" size="40" maxlength="20"></td>
	</tr>
	<tr>
		<th> 備考：</th>
		<td><textarea name="autherRemark" rows="3" cols="30"></textarea></td>
	</tr>
	</table>
	</div>
	<div id="viewArea2"></div>
		</tr>
		<tr></td>
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
		<tr>
		<table border="5" frame="hsides"align="center">
			<th></th>
	 		<td><input type="number" name="year"  size="4" maxlength="4">年
				<input type="number"  name="month"  size="2" maxlength="2">月
				<input type="number"  name="day"  size="2"  maxlength="2">日
				<input type="number"  name="hour"  size="2" maxlength="2">時必須</td>
			</table>

		</tr>
 		</div>
 <div id="viewArea"></div>
<input  type="submit" value="イベントを作成する" >
</form>
</body>
</div>
</html>