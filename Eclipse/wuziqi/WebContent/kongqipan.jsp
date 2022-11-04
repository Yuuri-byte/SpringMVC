<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="final_chess.*"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
String sql="select * from bisailunci where number="+application.getAttribute("alltheNum");//获取棋盘大小
jdbcAn createQipan=new jdbcAn();	
Connection conn=createQipan.getConn();
//out.print("数据库连接成功");
ResultSet rs=createQipan.getResult(sql);
rs.next();
String qipansize = rs.getString("qipansize");
int therow=Integer.parseInt(qipansize);//将棋盘大小赋值给变量
%>
	<canvas id="mycanvas" width="1000px" height="1000px"></canvas>
	<script type="text/javascript">
var chess = document.getElementById("mycanvas");
var context = chess.getContext('2d');
var row=<%=therow%>-1;
function drawChessBoard(){
	 for(var i=0;i<row+1;i++){
	 context.strokeStyle="#D6D1D1";
	 context.moveTo(15+i*30,15);
	 context.lineTo(15+i*30,30*row+15);
	 context.stroke();
	 context.moveTo(15,15+i*30);
	 context.lineTo(30*row+15,15+i*30);
	 context.stroke();
	// context.fillStyle = '#cdcdcd';
	// context.fillRect(30+row*30, 0, 200, 200);
	 }
	//context.fillStyle = '#cdcdcd';
	//context.fillRect(30+row*30, 0, 250, 250);
	}
drawChessBoard();//根据棋盘大小绘制棋盘
</script>

</body>
</html>