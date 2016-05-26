<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Event,model.BordItems, dao.BordItems2, java.util.ArrayList"%>
<%
Event redpepper = (Event) session.getAttribute("event");
ArrayList<BordItems2> b2 = (ArrayList<BordItems2>) session.getAttribute("bordItems2");
ArrayList<ArrayList<Integer>> pFlagSet = (ArrayList<ArrayList<Integer>>) session.getAttribute("preferredFlagSet");
%>
<!DOCTYPE html >
<head>
<link rel="stylesheet" type="text/css" href="style.css" />
<div style="text-align : center"></div>
<meta charset=UTF-"8">
<title>タイトル</title>
<style type="text/css">
<link rel="stylesheet" type="text/css" href="style.css" />
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
		<th class="Reaper1">投稿締切日：</th>
		<td class="Reaper2"><%= redpepper.getDeadlineYear() %>年
			<%= redpepper.getDeadlineMonth()+1 %>月
			<%= redpepper.getDeadlineDate() %>日</td>
	</tr>
</table>
<table border="5" frame="hsides" align="center">
	<tr>
	</tr>
	<% for(int i = 0 ; i < redpepper.getPricePerPerson().size(); i++){ %>
	<tr><th></th></tr>
	<tr>
		<th class="Reaper1">一人分費用：</th>
		<td class="Reaper2"><%= redpepper.getPricePerPerson().get(i) %></td>
		<th class="Reaper1">場所：</th>
		<td class="Reaper2"><%= redpepper.getEventVenue().get(i) %></td>
	</tr>
	<% } %>
	<tr>
		<th class="Reaper1"> 備考：</th>
		<td class="Reaper3" colspan="3"><%= redpepper.getAutherRemark().get(0) %></td>
	</tr>
	<tr>
		<th class="Reaper1"> パスワード：</th>
		<td><input type="text" name="userPass" size="20" required maxlength="20">必須</td>
		<td colspan="2"><input  type="submit" value="イベントを編集する" ></td>
	</tr>
</table>

<h2> 参加者記入欄</h2>
<table border="5" frame="hsides" align="center">
<form action="/CarolinaReaper/EventEditServlet"  method="post">
<input type="hidden" name="eventid" value="<%= redpepper.getEventId() %>">
<input type="hidden" name="action" value="0">
	<tr>
		<th class="Reaper1">参加者名</th>
		<td class="Reaper2"><input type="text" name="userName" size="40" required maxlength="20">必須</td >
	</tr>
	<tr>
		<%-- <% for (int i = 0; i < Integer.parseInt(redpepper.getNumberOfEvent()); i++) {%> --%>
		<%-- <td class="Reaper1"><%= i +1 %>次回</td> --%>
	<tr>
		<th class="Reaper1">日程：</th>
		<td class="Reaper2">
			<table  class="table3" border=1>
				<tr>
					<th></th>
					<th>○</th>
					<th>△</th>
					<th>×</th>
				</tr>
				<tr>
					<% for (int j = 0; j < redpepper.getCandidate().size(); j++) {%>
					<td><%= Event.getYear(redpepper.getCandidate().get(j)) %>年
						<%= Event.getMonth(redpepper.getCandidateElement(j)) %>月
						<%= Event.getDate(redpepper.getCandidateElement(j)) %>日
						<%= Event.getHour(redpepper.getCandidateElement(j)) %>時</td>
					<td><input type="radio" name="preferredFlagSet<%= j%>" required value="1"></td>
					<td><input type="radio" name="preferredFlagSet<%= j%>" required value="2"></td>
					<td><input type="radio" name="preferredFlagSet<%= j%>" required value="0"></td>
				</tr>
				<% } %>
			</table>
		</td>
	</tr>
		<%-- <% } %> --%>
	<tr>
		<th class="Reaper1"> 備考：</th>
 		<td class="Reaper2"><textarea name=userRemark rows="3" cols="30"></textarea></td>
	</tr>
	<tr>
		<th class="Reaper1">参加者用パスワード：</th>
		<td class="Reaper2"><input type="text" name="userPass" size="20" required maxlength="20">必須
		<input  type="submit" name="decide" value="参加決定"></td>
	</tr>
	</form>
</table>




<h2>参加者一覧</h2>
<table>


<%   if(b2 != null && b2.size()!=0){ for (int i = 0; i < b2.size(); i++) { %>

<table border="5" frame="hsides" align="center">

<form action="/CarolinaReaper/EventEditServlet"  method="post">
<input type="hidden" name="itemid" value="<%=b2.get(i).getItemId() %>">
<input type="hidden" name="action" value="1">
	<tr>
		<th class="Reaper1">参加者名</th>
		<td><%= b2.get(i).getUserName() %></td >
		<input type="hidden" name="eventid" value="<%= redpepper.getEventId() %>">
	</tr>
	<tr>
		<th class="Reaper1">日程：</th>
		<td class="Reaper2">
			<table  class="table3" border=1>

				<% if(pFlagSet.size()!=0 && pFlagSet!= null && pFlagSet.get(0)!=null&&pFlagSet.get(0).size()!=0){ for (int k = 0; k < redpepper.getCandidate().size(); k++) { %>
					<tr><td><%= Event.getYear(redpepper.getCandidate().get(k)) %>年
						<%= Event.getMonth(redpepper.getCandidate().get(k)) %>
						<%= Event.getDate(redpepper.getCandidate().get(k)) %>日
						<%= Event.getHour(redpepper.getCandidate().get(k)) %>時</td>
					<% if(1 == pFlagSet.get(i).get(k)){ %>
					<td>○</td>
					<% }else if(2 == pFlagSet.get(i).get(k)){ %>
					<td>△</td>
					<% }else if(0 == pFlagSet.get(i).get(k)){ %>
					<td>×</td></tr>
					<% } %>
				<% }} %>
			</table>
		</td>
	</tr>
	<tr>
		<th class="Reaper1"> 備考：</th>
 		<td class="Reaper3"><%= b2.get(i).getUserRemark() %></td>
	</tr>
	<tr>
		<th class="Reaper1">編集パスワード入力：</th>
		<td class="Reaper2"><input type="text" name="userPass" size="20" required maxlength="20">必須
		<input  type="submit" name="edit" value="編集する"></td>
	</tr>
	</form>
</table>
<% }} %>
<p><a href="index.jsp">HOME</a>
</body>