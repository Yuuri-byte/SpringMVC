<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="final_chess.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="white.*"%>
<%@ page import="black.*"%>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



<% 
	
	
jdbcAn database=new jdbcAn();
//String chessbox=new String(request.getParameter("chessbox"));
//System.out.println(chessbox);
ResultSet rs=database.getResult("select * from bisailunci where number="+application.getAttribute("alltheNum"));
rs.next();
String chessbox = rs.getString("chessbox");
String[] chessArray=chessbox.split(",");
int row=(int)Math.sqrt(chessArray.length);
int[][] chess2Array=new int[row][row];
for(int i=0;i<row;i++){
	for(int j=0;j<row;j++){
		chess2Array[i][j]=Integer.parseInt(chessArray[i*row+j]);
	}
}//从数据库获取棋盘字符串，将字符串转化为字符数组，再转化为二维整形数组


int total_chess=0;
String zouqi="";
for(int i=0;i<row;i++){
	for(int j=0;j<row;j++){
		total_chess+=chess2Array[i][j];
	}
}
//System.out.print(total_chess);
if(total_chess==0){
	white.Chess aChess=new white.Chess();
	zouqi="("+aChess.luozi(chess2Array)+"),";
}else{
	black.Chess aChess=new black.Chess();
	zouqi="("+aChess.luozi(chess2Array)+"),";
	}
//将棋盘数组求和，等于0则该黑棋走棋，等于-1则该白棋走棋


System.out.print(zouqi);
int i=1;

String sqltext="update bisailunci set steps=CONCAT(steps,'"+zouqi+"') where number="+application.getAttribute("alltheNum");
//System.out.print(sqltext);
//database.changeContent("update bisailunci set row="+row);//将棋盘大小存入数据库

int num=database.changeContent(sqltext);//将获得的走棋结果存入数据库steps字段
//System.out.print("update"+num);
response.sendRedirect("luozi.jsp");
%>



</body>
</html>