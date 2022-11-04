<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="final_chess.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.swing.JOptionPane"%>
<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="java.util.regex.*" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String sql1="truncate table player";
String sql2="truncate table code";
String sql3="truncate table bisailunci";
String sql4="truncate table allmatch";
jdbcAn clearDatabase=new jdbcAn();
clearDatabase.changeContent(sql1);
clearDatabase.changeContent(sql2);
clearDatabase.changeContent(sql3);
clearDatabase.changeContent(sql4);
%>
<script type="text/javascript">
alert("数据库清空完毕！");
</script>
</body>
</html>