<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*"%>
<%@ page import="final_chess.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<%
jdbcAn shujuku=new jdbcAn();
try {
    Connection conn=shujuku.getConn();
    out.println("数据库连接成功");
   // Statement stmt=conn.createStatement();
    //ResultSet rs=stmt.executeQuery("select * from bisailunci");    
}catch (Exception e)
{
    out.print("连接失败");
    e.printStackTrace();
}

%>


</body>
</html>