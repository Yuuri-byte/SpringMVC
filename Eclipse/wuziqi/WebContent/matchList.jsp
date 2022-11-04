<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="final_chess.jdbcAn"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<% 
jdbcAn codeL=new jdbcAn();
String sql="select * from code";
ResultSet rs=codeL.getResult(sql);

String path="";
rs.last();
int rowCount = rs.getRow(); 
int i=0;
int[] numList=new int[rowCount];
rs.beforeFirst();
while (rs.next()) {
numList[i++]=Integer.parseInt(rs.getString("codeNo"));
}

int temp=0;
Random rand = new Random();
int length = numList.length;
for (int j = length; j > 0; j--) {
	int randInd = rand.nextInt(i);
	//swap(arr, randInd, i - 1);
	temp = numList[randInd];
	numList[randInd] = numList[i - 1];
	numList[i - 1] = temp;
}
rs.beforeFirst();
System.out.println(Arrays.toString(numList));
int matchNum=0;
int a=4;
int lunkong=0;
if(numList.length>8)
{
	while(Math.pow(2,a)<numList.length){
		a++;
		System.out.print("a="+a);
	}
	lunkong=(int)Math.pow(2,a)-numList.length;
	for(int j=0;j<numList.length-lunkong;j=j+2){
		codeL.changeContent("insert into allmatch(BlackNo,WhiteNo) value ("+numList[j]+","+numList[j+1]+")");
	}
	for(int j=numList.length-1;j>=numList.length-lunkong;j--){
		codeL.changeContent("insert into allmatch(BlackNo,WhiteNo) value ("+numList[j]+",0)");
	}
}
else if(numList.length%2==0){
	for(int j=0;j<numList.length;j=j+2){
		codeL.changeContent("insert into allmatch(BlackNo,WhiteNo) value ("+numList[j]+","+numList[j+1]+")");
	}
}
else
	{for(int j=0;j<numList.length-1;j=j+2){
		codeL.changeContent("insert into allmatch(BlackNo,WhiteNo) value ("+numList[j]+","+numList[j+1]+")");
	}
		codeL.changeContent("insert into allmatch(BlackNo,WhiteNo) value ("+numList[numList.length-1]+",0)");}
%>
<%
jdbcAn matchL=new jdbcAn();
String sql2="select * from allmatch";
ResultSet rs2=matchL.getResult(sql2);

%>
<table border="2">
			<tr>
				<th>黑方编号</th>
				<th>白方编号</th>
			</tr>
<%
while (rs2.next()) {
	%>
	<tr>
		<td><%=rs2.getString("blackNo")%></td>
		<td><%=rs2.getString("whiteNo")%></td>
		<% 
		if(numList[1]!=0){
		%>
		
		<td><a href="row_column_input.jsp?blackNo=<%=rs2.getString("blackNo")%>&whiteNo=<%=rs2.getString("whiteNo")%>&matchNo=<%=rs2.getString("MatchNo")%>">观看对战</a></td>
	</tr>
	
	<%
		}}
	%>
	</center>
</body>
</html>