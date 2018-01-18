package Service;

import java.sql.ResultSet;

import Common.SqlHelper;
import Model.People;

public class registration {
	//检查是否已注册
	private static boolean checkUser( People p ){
		String sqlStr = "select * from people where name = '" + p.getName() + "'";
		ResultSet rs = SqlHelper.executeQuery(sqlStr);
		int counter = 0;
		
		try{
			while( rs.next() ){
				counter ++;
			}
		}
		catch( Exception e ){
			System.out.println(e);
		}
		
		if( counter == 0 ){
			return true;    //不存在该用户
		}
		else{
			return false;   //该用户已存在
		}
	}
	
	//注册
	public static int addUser( People p ){
		if( checkUser(p) ){
			System.out.println("-----------complete the check");
			String sqlStr = "insert into people values( '" + p.getName() + "', '" + p.getQq() + "' )";
			String sqlStr_2 = "insert into integral values('" + p.getName() + "', 0, 0)";
			if(SqlHelper.executeUpdate(sqlStr) && SqlHelper.executeUpdate(sqlStr_2)){
				return 1;
			}
			else{
				return -1;
			}
		}
		else{
			return -2;
		}
	}
}
