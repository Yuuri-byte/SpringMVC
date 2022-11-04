<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="final_chess.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.swing.JOptionPane"%>
<%@ page import="java.util.concurrent.TimeUnit" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
TimeUnit.SECONDS.sleep(3);
boolean res=false;
res=createQipan.Win();
if(res==true&&jiashu==-1){
	JOptionPane.showMessageDialog(null,"白子获胜！");
	String winnerNo = rs2.getString("lastplayer");
	int winnernum=Integer.parseInt(winnerNo);
	createQipan.changeContent("update bisailunci set winner="+winnerNo+"where number=1");
	}
else if(res==true&&jiashu==1){
	JOptionPane.showMessageDialog(null,"黑子获胜！");
	String winnerNo = rs2.getString("firstplayer");
	int winnernum=Integer.parseInt(winnerNo);
	createQipan.changeContent("update bisailunci set winner="+winnerNo+"where number=1");
	}
else 
	//response.sendRedirect("loading.jsp");
	System.out.print("比赛继续");
%>
</body>
</html>