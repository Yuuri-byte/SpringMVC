package servletA;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.mysql.cj.xdevapi.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;



/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
public class Upload extends HttpServlet {
	@Override
	
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断是否是多段数据（只有是多段数据才是文件上传）
        //多段数据返回true，否则返回false
		int playerNumber=0;
		String path="";
        if(ServletFileUpload.isMultipartContent(req)){
            //创建FileItemFactory工厂实现类
            FileItemFactory factory = new DiskFileItemFactory();
            //创建用于解析数据的工具类ServletFileUpload类
            ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
            try {
                //解析上传的数据，得到每一个表单项FileItem
                List<FileItem> list = servletFileUpload.parseRequest(req);
                //判断每个表单项是普通类型还是上传的文件
                for(FileItem item : list){
                    if(item.isFormField()){
                        //普通表单项
                        System.out.println("表单项的内容："+item.getFieldName());
                        //使用UTF-8解析，防止乱码
                        System.out.println("表单项的value："+item.getString("UTF-8"));
                        playerNumber=Integer.parseInt(item.getString("UTF-8"));
                        System.out.println("xuanshoubianhao="+playerNumber);
                    }else {
                        //上传的文件
                        System.out.println("表单项的name："+item.getFieldName());
                        System.out.println("上传的文件名为："+item.getName());
                        //将此文件写到d盘根目录
                        item.write(new File("C:\\Users\\ASUS\\Desktop\\final\\codeSave\\"+playerNumber+".Chess.java"));
                        resp.getWriter().write("upload success!");
                        path="C:\\\\Users\\\\ASUS\\\\Desktop\\\\final\\\\codeSave\\\\"+playerNumber+".Chess.java";
                        
                        Connection cn = null;
                        PreparedStatement pst = null;
                        try {
                        Class.forName("com.mysql.cj.jdbc.Driver");//加载MySQL驱动程序
                        //建立与数据库的连接
                        String url = "jdbc:mysql://localhost:3307/final_chess?serverTimezone=GMT";
                        String user = "root";
                        String password = "1234";
                       
                        cn = DriverManager.getConnection(url,user,password);

                        pst = cn.prepareStatement("update code set pathFile='"+path+"' where playerNo="+playerNumber); //创建PreparedStatement对象
                        pst.executeUpdate(); //向数据库增加数据

          
                        } catch (Exception e) {
                        e.printStackTrace();}	
                        
                        
                    }
                }
      
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        }
        
        

    }

}
