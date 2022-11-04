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
	<%
String total_row=new String(request.getParameter("row"));
int total_rows=Integer.parseInt(total_row);
String chessbox="";

for(int i=0;i<total_rows;i++){
	for(int j=0;j<total_rows;j++){
		chessbox+=",0";
	}
}//获取棋盘情况字符串
chessbox=chessbox.substring(1);
jdbcAn databaseA=new jdbcAn();
//databaseA.test();

databaseA.changeContent("UPDATE `final_chess`.`bisailunci` SET `chessbox`=" + "'" + chessbox + "'"+"where number="+application.getAttribute("alltheNum"));//将棋盘初试情况(全部为0)存入数据库
databaseA.changeContent("update bisailunci set steps='' where number="+application.getAttribute("alltheNum"));
int num=databaseA.changeContent("update bisailunci set qipansize='"+total_rows+"'where number="+application.getAttribute("alltheNum"));
//System.out.print("棋盘大小修改="+num);

    
%>

<a href="loading.jsp?chessbox=<%=chessbox%>">比赛开始</a>
</body>
</html>