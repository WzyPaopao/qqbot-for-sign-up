package Service;

import Common.SqlHelper;

public class clear {
	public static boolean clearIntegral(){
		String sqlStr = "update integral set num = 0";
		return SqlHelper.executeUpdate(sqlStr);
	}
}
