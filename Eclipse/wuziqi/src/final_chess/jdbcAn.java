package final_chess;
import java.sql.*;
import java.io.*;
import java.util.*;
public class jdbcAn {
	int n=5;

    public Connection getConn() throws SQLException {
        String url = "jdbc:mysql://localhost:3307/final_chess?serverTimezone=GMT"; 
        String username = "root";
        String password = "1234";
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
           //System.out.println("数据库连接成功");
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return conn;
    }
    
    
    //关闭连接
    public void close(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException
    {
        try
        {
            rs.close();
            ps.close();
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    
    public ResultSet getResult(String sql) {
    	ResultSet rs=null;
    	Connection conn = null;
		try {
			conn = getConn();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			Statement stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return rs;
    }
    
    
    public int changeContent(String sql) {
    	int resultV=0;
    	Connection conn = null;
    	//sql="update zouqilunci set steps='123456'";
		try {
			conn = getConn();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Statement stmt = conn.createStatement();
			resultV=stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    	return resultV;
    }
    
    public void test() {
    	System.out.print("OK");
    	
    }
    
    public int[][] qipanStringtoInt(String chessbox){
    	String[] chessArray=chessbox.split(",");
    	int row=(int)Math.sqrt(chessArray.length);
    	int[][] chess2Array=new int[row][row];
    	for(int i=0;i<row;i++){
    		for(int j=0;j<row;j++){
    			chess2Array[i][j]=Integer.parseInt(chessArray[i*row+j]);
    					}
    	}
    	return chess2Array;
    }
    
    
    public String qipanInttoString(int[][] chessArray) {
    	String chessbox="";
    	int num=chessArray.length;
    	for(int i=0;i<num;i++) {
    		for(int j=0;j<num;j++) {
    			chessbox=chessbox+chessArray[i][j]+",";
    		}
    	}
    	//chessbox=chessbox.substring(1);
    	//System.out.print("函数改完的chessbox"+chessbox);
    	return chessbox;
    }
    
    
    public Boolean Win(int num) {
    	ResultSet rs=null;
    	Connection conn = null;
    	String chessbox="";
    	int row=0;
    	String laststep="";
		try {
			conn = getConn();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Statement stmt = conn.createStatement();
			rs=stmt.executeQuery("select * from bisailunci where number="+num);
			rs.next();
			chessbox=rs.getString("chessbox");
			row=Integer.parseInt(rs.getString("qipansize"));
			laststep=rs.getString("laststep");
			
			//System.out.print("函数的laststep="+laststep);
			//System.out.print("函数的行="+row);
			//System.out.print("函数的chessbox="+chessbox);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	jdbcAn win=new jdbcAn();
	 	int[][] chessArray=win.qipanStringtoInt(chessbox);
	 	String[] addluozi=laststep.split(",");
	 	int x=0,y=0;
	 	x=Integer.parseInt(addluozi[0])-1;
	 	y=Integer.parseInt(addluozi[1])-1;
	 	int player=chessArray[x][y];
	 	//System.out.print("player="+player);
	 	int count = 1;      //本身一点为 1
		int posX = 0;    
		int posY = 0;
		if(x!=0) {
		 for(posX = x-1; posX >=0 ; posX--) {
	            if (chessArray[posX][y] ==player) {
	                count++;
	                System.out.print(player+"选手count="+count+"\n");
	                if (count >= n) {
	                    return true;
	                }
	            }else {
	                break;
	            }
	           
	        }    //向右边遍历
		}
	        for(posX = x + 1; posX < row; posX++) {
	            if (chessArray[posX][y] == player) {
	                count++;
	                System.out.print(player+"选手count="+count+"\n");
	                if (count >= n) {
	                    return true;
	                }
	            }else {
	                break;
	            }

	        }
	        /**判断垂直方向上的胜负
	        /* 将垂直方向以传入的点y上的x轴作为分隔线分为两部分
	         * 先向上遍历，判断到的相同的连续的点  count++
	         */
	        if(y!=0) {
	        for(count=1,posY = y - 1; posY >= 0; posY--) {
	            if (chessArray[x][posY] ==player) {
	                count++;
	                System.out.print(player+"选手count="+count+"\n");
	                if (count >= n) {
	                    return true;
	                }
	            }else {
	                break;
	            }
	            
	        }//向下遍历
	        }
	        for(posY = y + 1; posY < row; posY++) {
	            if (chessArray[x][posY] == player) {
	                count++;
	                System.out.print(player+"选手count="+count+"\n");
	                if (count >= n) {
	                    return true;
	                }
	            }else {
	                break;
	            }
	          
	        }
	        /**判断左上右下方向上的胜负
	         * 以坐标点为分割线，将棋盘分为左右两个等腰三角形
	         * 先判断左边的
	         */
	        if(x!=0&&y!=0) {
	        for(count=1,posX = x - 1, posY = y - 1; posX >= 0 && posY >= 0; posX--, posY--) {
	            if (chessArray[posX][posY] ==player) {
	                count++;
	                System.out.print(player+"选手count="+count+"\n");
	                if (count >= n) {
	                    
	                    return true;
	                }
	            }else {
	                break;
	            }
	           
	        }//判断右边的
	        }
	        for(posX = x + 1, posY = y + 1; posX < row && posY < row; posX++, posY++) {
	            if (chessArray[posX][posY] ==player) {
	                count++;
	                System.out.print(player+"选手count="+count+"\n");
	                if (count >= n) {
	                   
	                    return true;
	                }
	            }else {
	                break;
	            }
	           
	        }
	        /**判断右下左下方向上的胜负
	         * 以坐标点为分割线，将棋盘分为左右两个等腰三角形
	         * 先判断左边的
	         */
	        if(y!=0) {
	        for(count=1,posX = x + 1, posY = y - 1; posX < row && posY >= 0; posX++, posY--) {
	            if (chessArray[posX][posY] == player) {
	                count++;
	                System.out.print(player+"选手count="+count+"\n");
	                if (count >= n) {
	                    return true;
	                }
	            }else {
	                break;
	            }
	           
	        }//判断右边的
	        }
	        if(x!=0) {
	        for(posX = x - 1, posY = y + 1; posX >= 0 && posY < row; posX--, posY++) {
	            if (chessArray[posX][posY] == player) {
	                count++;
	                System.out.print(player+"选手count="+count+"\n");
	                if (count >= n) {
	                    return true;
	                }
	            }else {
	                break;
	            }
	            
	        }}
	        return false;
	    }
   


}







