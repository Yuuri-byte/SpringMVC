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
        //�ж��Ƿ��Ƕ�����ݣ�ֻ���Ƕ�����ݲ����ļ��ϴ���
        //������ݷ���true�����򷵻�false
		int playerNumber=0;
		String path="";
        if(ServletFileUpload.isMultipartContent(req)){
            //����FileItemFactory����ʵ����
            FileItemFactory factory = new DiskFileItemFactory();
            //�������ڽ������ݵĹ�����ServletFileUpload��
            ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
            try {
                //�����ϴ������ݣ��õ�ÿһ������FileItem
                List<FileItem> list = servletFileUpload.parseRequest(req);
                //�ж�ÿ����������ͨ���ͻ����ϴ����ļ�
                for(FileItem item : list){
                    if(item.isFormField()){
                        //��ͨ����
                        System.out.println("��������ݣ�"+item.getFieldName());
                        //ʹ��UTF-8��������ֹ����
                        System.out.println("�����value��"+item.getString("UTF-8"));
                        playerNumber=Integer.parseInt(item.getString("UTF-8"));
                        System.out.println("xuanshoubianhao="+playerNumber);
                    }else {
                        //�ϴ����ļ�
                        System.out.println("�����name��"+item.getFieldName());
                        System.out.println("�ϴ����ļ���Ϊ��"+item.getName());
                        //�����ļ�д��d�̸�Ŀ¼
                        item.write(new File("C:\\Users\\ASUS\\Desktop\\final\\codeSave\\"+playerNumber+".Chess.java"));
                        resp.getWriter().write("upload success!");
                        path="C:\\\\Users\\\\ASUS\\\\Desktop\\\\final\\\\codeSave\\\\"+playerNumber+".Chess.java";
                        
                        Connection cn = null;
                        PreparedStatement pst = null;
                        try {
                        Class.forName("com.mysql.cj.jdbc.Driver");//����MySQL��������
                        //���������ݿ������
                        String url = "jdbc:mysql://localhost:3307/final_chess?serverTimezone=GMT";
                        String user = "root";
                        String password = "1234";
                       
                        cn = DriverManager.getConnection(url,user,password);

                        pst = cn.prepareStatement("update code set pathFile='"+path+"' where playerNo="+playerNumber); //����PreparedStatement����
                        pst.executeUpdate(); //�����ݿ���������

          
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
