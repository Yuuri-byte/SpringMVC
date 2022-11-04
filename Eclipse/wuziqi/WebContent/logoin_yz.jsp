<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
	
    //获取用户输入信息
    request.setCharacterEncoding("utf-8");
    String username=request.getParameter("username");
    String userpwd=request.getParameter("userpwd");
    Connection conn = null;
    Statement stmt = null;
    try {
        //连接驱动
        Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    //建立数据库连接
    String url = "jdbc:mysql://localhost:3307/final_chess?serverTimezone=GMT";
    //数据库账号
    String uname="root";
    //数据库密码
    String upwd="1234";

    try {
        conn = DriverManager.getConnection(url, uname, upwd);
        //创建发射器
        stmt = conn.createStatement();
        //创建sql语句
        String sql = "select * from final_chess.player where name='"+username+"' and password='"+userpwd+"'";
        //发送sql语句并接收结果
        ResultSet rs = stmt.executeQuery(sql);
     
        if (rs.next()) {
        	String playerNo=rs.getString("playerNo");
        	out.print("登陆成功,你的编号是"+playerNo+"号");
        	 stmt.executeUpdate("INSERT INTO code(playerNo) SELECT "+playerNo+" FROM dual WHERE not exists (select * from code where code.playerNo = "+playerNo+")");
        	 
        	%>
        	</br>
		请点击<a href="upload.jsp">此处</a>上传算法
		
        	<%
        	String sql2="select * from code where playerNo="+playerNo;
        	 ResultSet rs2 = stmt.executeQuery(sql2);
        	 rs2.next();
     		String path=rs2.getString("pathFile");
     		if(path!=null){
     			
     			//path=path.substring(2,path.length());
     		
     			System.out.print(path);
     			%></br>
     			点击<a href=<%=path%> >下载</a>算法
     			<%
     		}
        } else {
            out.print("登陆失败");
        }
        rs.close();
    }
    catch (SQLException e) {
        e.printStackTrace();
    }

%>

</body>
</html>
