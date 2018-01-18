package Service;

import java.util.TimerTask;

import Common.SqlHelper;

public class NFDFlightDataTimerTask extends TimerTask {
	//每天3：00清空所有用户的states
	@Override
	public void run() { 
		System.out.println("定时器测试:"+System.currentTimeMillis());
		String sqlStr = "update integral set states = 0 ";
		SqlHelper.executeUpdate(sqlStr);
	} 
}
