package Service;

import java.util.TimerTask;

import Common.SqlHelper;

public class NFDFlightDataTimerTask extends TimerTask {
	//ÿ��3��00��������û���states
	@Override
	public void run() { 
		System.out.println("��ʱ������:"+System.currentTimeMillis());
		String sqlStr = "update integral set states = 0 ";
		SqlHelper.executeUpdate(sqlStr);
	} 
}
