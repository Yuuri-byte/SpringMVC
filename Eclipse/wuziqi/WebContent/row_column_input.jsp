<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="java.nio.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="org.apache.commons.io.FileUtils" %>
<%@ page import="final_chess.jdbcAn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
int blackNo=Integer.parseInt(request.getParameter("blackNo"));
int whiteNo=Integer.parseInt(request.getParameter("whiteNo"));
int matchNo=Integer.parseInt(request.getParameter("matchNo"));
String blackPath="";
String whitePath="";
String blackDestStr="";
String whiteDestStr="";
String sql="select * from code where playerNo="+blackNo;
String sql2="select * from code where playerNo="+whiteNo;
System.out.print("hei="+blackNo+"bai="+whiteNo+"NUMBER="+matchNo);
jdbcAn getPath=new jdbcAn();
ResultSet rrs=getPath.getResult(sql);
ResultSet rrs2=getPath.getResult(sql2);
rrs.next();
rrs2.next();
blackPath=rrs.getString("pathFile");
whitePath=rrs2.getString("pathFile");
blackDestStr="C:\\Users\\ASUS\\Desktop\\wuziqi\\src\\black\\Chess.java";
whiteDestStr="C:\\Users\\ASUS\\Desktop\\wuziqi\\src\\white\\Chess.java";
File blackSource = new File(blackPath);
File whiteSource = new File(whitePath);
File blackDest = new File(blackDestStr);
File whiteDest = new File(whiteDestStr);
FileUtils.copyFile(blackSource, blackDest);
FileUtils.copyFile(whiteSource, whiteDest);


File file = new File(blackDestStr);
File file2 = new File(whiteDestStr);
File[] files =new File[2];
files[0]=file;
files[1]=file2;
int count=0;
String content="";
// 2.读取Java文件，然后在第一行加上package信息
    for (File f : files) {
            File tmp = File.createTempFile("tmp", null);
            FileOutputStream tmpOut = new FileOutputStream(tmp);
            FileInputStream tmpIn = new FileInputStream(tmp);
            RandomAccessFile raf = null;
            if(count==0){
            	content="package black;";
            	count++;
            }else content="package white;";
            try {
                raf = new RandomAccessFile(f, "rw");
                byte[] buf = new byte[64];
                int hasRead = 0;
                while ((hasRead = raf.read(buf)) > 0) {
                    // 把原有内容读入临时文件
                    tmpOut.write(buf, 0, hasRead);
                }
                raf.seek(0);
                raf.write(content.getBytes());
                // 追加临时文件内容
                System.out.println("count="+count+"号写入成功，content="+content);
                while ((hasRead = tmpIn.read(buf)) > 0) {
                    raf.write(buf, 0, hasRead);
                }
            } catch (IOException e) {
                System.out.println("写入失败！count="+count);
                e.printStackTrace();
            } 
        }
    

getPath.changeContent("insert into bisailunci(firstplayerNo,lastplayerNo,Number) value ("+blackNo+","+whiteNo+","+matchNo+")");


application.setAttribute("alltheNum", Integer.parseInt(request.getParameter("matchNo")));

%>
	<br>
	<form action="qipan.jsp" method="post">
		请输入行列数:<input type="text" name="row"> <br> <input
			type="submit" value="提交">
	</form>
	<br><a href="clearDB.jsp">清空本局数据</a>
</body>
</html>