package Common;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class SqlHelper {
	private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String dbURL = "jdbc:sqlserver://139.199.10.42:1433;DatabaseName=qqbotStudy";
	private static String userName = "sa";
	//private static String userPwd = "123456";
	private static String userPwd = "WANGzhengyi1";
	  
	  
	private static Connection getCoonection(){
		  try{
		   Class.forName( driverName );
		   Connection conn = DriverManager.getConnection( dbURL,userName,userPwd );
		   return conn;
		  }
		  catch( Exception e ){
		   e.printStackTrace();
		   System.out.println("----------------连接失败");
		  } 
		  
		  return null;
	  }
	  
	public static ResultSet executeQuery( String SQL ){  
		  try{
		   Connection conn=getCoonection();
		   System.out.println("---------------连接数据库成功");
		   
		   Statement stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery( SQL );
		   
		   return  rs;
		  }
		  catch( Exception e ){
		   e.printStackTrace();
		   System.out.println("---------------查询失败");
		  }
		  
		  return null;
	}
	
	public static boolean executeUpdate( String SQL ){  
		  try{
		   Connection conn=getCoonection();
		   System.out.println("---------------连接数据库成功");  
		  
		   Statement stmt = conn.createStatement();
		   int result = stmt.executeUpdate( SQL );
		   
		   if( result > 0 )
			   return true;
		  }
		  catch( Exception e ){
		   e.printStackTrace();
		   System.out.println("---------------更新失败");
		  }
		  
		  return false;
	}
}
