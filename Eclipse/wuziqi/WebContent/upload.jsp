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

 <form action="http://localhost:8080/final_chess/Upload" enctype="multipart/form-data" method="post">
        棋手编号：<input type="text" name="playerNum"/><br>
        算法文件：<input type="file" name="code"/><br>
        <input type="submit" value="上传"/>
    </form>
</body>
</html>