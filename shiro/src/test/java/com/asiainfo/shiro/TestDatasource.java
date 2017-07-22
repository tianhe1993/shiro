package com.asiainfo.shiro;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
  
  
public class TestDatasource {  
    public static Connection getMysqlConnection(){  
        String driver="com.mysql.jdbc.Driver";   
        String url="jdbc:mysql://localhost:3306/shiro"; 
        String name="root";
        String pwd="1234";
        try{  
            Class.forName(driver);  
            Connection conn=DriverManager.getConnection(url,name,pwd);
            return conn;  
        }catch(ClassNotFoundException e){  
            e.printStackTrace();  
            return null;  
        }catch(SQLException e){  
            e.printStackTrace();  
            return null;  
        }  
    }  
    
    public static Connection getOracleConnection(){  
        String driver="oracle.jdbc.driver.OracleDriver";   
//        String url="jdbc:oracle:thin:@localhost:1521:orcl";
        String url="jdbc:oracle:thin:@10.1.198.63:1521:ai4a";
//        String name="scott";
//        String pwd="123456";
        String name="uap30test";
        String pwd="uap30test";
        try{  
            Class.forName(driver);  
            Connection conn=DriverManager.getConnection(url,name,pwd);
            return conn;  
        }catch(ClassNotFoundException e){  
            e.printStackTrace();  
            return null;  
        }catch(SQLException e){  
            e.printStackTrace();  
            return null;  
        }  
    } 
      
    public static void closeAll(Connection conn,Statement ps,ResultSet rs){  
        try{  
            if(rs!=null){  
                rs.close();  
            }  
        }catch(SQLException e){  
            e.printStackTrace();  
        }  
        try{  
            if(ps!=null){  
                ps.close();  
            }  
        }catch(SQLException e){  
            e.printStackTrace();  
        }  
        try{  
            if(conn!=null){  
                conn.close();  
            }  
        }catch(SQLException e){  
            e.printStackTrace();      
        }  
    }  
      
    public static void main(String[] args) throws SQLException  
    {  
          
//      Connection cc=TestDatasource.getMysqlConnection();  
        Connection cc=TestDatasource.getMysqlConnection();  
          
        if(!cc.isClosed()){
        	System.out.println("Succeeded connecting to the Database!");  
            Statement statement = cc.createStatement();  
            String sql = "select * from users where username = 'zhang'";  
            ResultSet rs = statement.executeQuery(sql);  
            while(rs.next()) {  
                System.out.println("密码="+rs.getString("PASSWORD"));  
            }
            TestDatasource.closeAll(cc, statement, rs);
        }  
        
    }  
}  

