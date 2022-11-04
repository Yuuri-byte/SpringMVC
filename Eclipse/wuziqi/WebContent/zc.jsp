<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //获取用户输入信息
    request.setCharacterEncoding("utf-8");
    String username=request.getParameter("username");
    String userpwd=request.getParameter("userpwd");
    String cuserpwd=request.getParameter("cuserpwd");
    //判断两次密码是否相同
    if (cuserpwd.equals(userpwd)) {  
        Connection conn = null;
        Statement stmt = null;
        try {
            //连接驱动
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //建立数据库连接
        String url="jdbc:mysql://localhost:3307/final_chess?serverTimezone=GMT";
        //数据库账号
        String user="root";
        //数据库密码
        String upwd="1234";
        try {
            conn= DriverManager.getConnection(url,user,upwd);
            stmt=conn.createStatement();
            String sql="insert into player(name,password) value ('"+username+"','"+userpwd+"')";
            
            int count = stmt.executeUpdate(sql);
            if (count>0){
                out.print("注册成功");
                out.print("<a href='logoin.jsp'>登录</a>");
            }
            
            else{
                out.print("注册失败");
            }
            //关闭资源
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    else {
        out.print("注册失败");
    }
%>
</body>
</html>

