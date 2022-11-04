<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="final_chess.jdbcAn"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<%
jdbcAn playerL=new jdbcAn();
String sql="select * from player";
ResultSet rs=playerL.getResult(sql);
%>
	<table border="2">
			<tr>
				<th>选手编号</th>
				<th>选手姓名</th>
			</tr>
<%
while (rs.next()) {
	%>

	<tr>
		<td><%=rs.getInt("playerNo")%></td>
		<td><%=rs.getString("Name")%></td>
	</tr>
	<%
		}
	%>
	<form action="matchList.jsp" method="post">
	<input type="submit" value="进行对战匹配">
	</form>
</center>
</body>
</html>