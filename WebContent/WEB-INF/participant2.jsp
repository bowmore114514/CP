<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Event" %>

<%
Event redpepper = (Event) session.getAttribute("event");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.table3 { border-collapse: collapse;
}
.table3 th {
  background-color: #cccccc;
}
td.Reaper1 { overflow: scroll;  }

</style>
</head>
<body>
<h2> 参加者記入欄</h2>
<table border="5" frame="hsides">
<form action="/CarolinaReaper/"EventReEditing"  method="post">
	<tr>
		<th>参加者名</th>
		<td><input type="text" name="userName" size="40" required maxlength="20" value="<%= redpepper.getUserName() %>">必須</td >
	</tr>
	<tr>
		<th>日程：</th>
		<td>
			<table  class="table3" border=1>
				<tr>
					<th><th>
					<th>○</th>
					<th>△</th>
					<th>×</th>
				</tr>
				<tr>
					<% for (int i = 0; i < 0; i++) {%>
					<th>
					<td><%=  %>年
					<%= +1 %>月
					<%=  %>日
					<%=  %>時</td>
					<td><input type="radio" name="eventOpenFlag" required value="1"></td>
					<td><input type="radio" name="eventOpenFlag" required value="0"></td>
					<td><input type="radio" name="eventOpenFlag" required value="0"></td>
				</tr>
				<% } %>
			</table>
		</td>
	</tr>
	<tr>
		<th> 備考：</th>
 		<td><textarea input type="text" name=userRemark rows="3" cols="30 value="<%= redpepper.getUserRemark() %>"> </textarea></td>
	</tr>
	<tr>
		<th>参加者用パスワード：</th>
		<td><input type="text" name="userPass" size="20" required maxlength="20">必須
		<input  type="submit" value="編集終了"></td>
	</tr>
</form>
</table>




<h2>参加者達</h2>
<table>


<% for (int i = 0; i < 0; i++) {%>
<table border="5" frame="hsides">
	<tr>
		<th>参加者名</th>
		<td>織田信長</td >
	</tr>
	<tr>
		<th>日程：</th>
		<td>
			<table  class="table3" border=1>
				<tr>
					<th><th>
					<th>○</th>
					<th>△</th>
					<th>×</th>
				</tr>
				<tr>
					<% for (int j = 0; i < 0; i++) {%>
					<th>
					<td><%=  %>年
					<%= %>月
					<%= %>日
					<%= %>時</td>
					<td><input type="radio" name="eventOpenFlag" required value="1"></td>
					<td><input type="radio" name="eventOpenFlag" required value="2"></td>
					<td><input type="radio" name="eventOpenFlag" required value="3"></td>
				</tr>
				<% } %>
			</table>
		</td>
	</tr>
	<tr>
		<th> 備考：</th>
 		<td class="Reaper"></td>
	</tr>
</table>
<% } %>

</body>
</html>