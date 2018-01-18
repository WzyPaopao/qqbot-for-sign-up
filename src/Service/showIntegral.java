package Service;

import java.sql.ResultSet;
import java.util.Formatter;

import Common.SqlHelper;

public class showIntegral {
	public static String show(){
		Formatter formatter = new Formatter(System.out);
		String sqlStr = "select * from integral order by num desc";
		ResultSet rs = SqlHelper.executeQuery(sqlStr);
		StringBuilder sb = new StringBuilder();
		
		sb.append("=====================\n");
		//formatter.format("%-18s | %-7s\n", "Name", "Score");
		try{
			while( rs.next() ){
				//System.out.println("----------------------------");
				//formatter.format("%-20s | %-7s\n", rs.getString("name"), rs.getInt("num"));
				sb.append(rs.getString("name")).append("£º").append(rs.getInt("num")).append("\n");
			}
			sb.append("=====================");
			
			System.out.println(sb.toString());
			
			return sb.toString();
		}
		catch( Exception e ){
			System.out.println(e);
			return "³ö´í£¬´íÎó´úÂë£º111";
		}
	}
}
