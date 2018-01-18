package Service;

import java.sql.ResultSet;

import Common.SqlHelper;

public class signIn {
	//检查是否已签到
	private boolean checkSign( String name ){
		String sqlStr = "select * from integral where name = '" + name + "'";
		//String sqlStr = "select * from integral";
		ResultSet rs = SqlHelper.executeQuery(sqlStr);
		System.out.println("-----------name = " + name);
		
		int states = 0;
		String myName;
		
		try{
			System.out.println("-------++++++++++++-------------");
			while( rs.next() ){
				states = rs.getInt("states");
			}
			
			if(states == 1){
				return true;
			}
			else{
				return false;
			}
		}
		catch(Exception e){
			System.out.println(e);
			return false;
		}
	}
	
	//检查是否注册
	private int isReg( String name ){
		String sqlStr = "select * from people where name = '" + name + "'";
		ResultSet rs = SqlHelper.executeQuery(sqlStr);
		try{
			if( rs.next() == false ){
				return -1;
			}
			else return 1;
		}
		catch(Exception e){
			System.out.println(e);
			return -2;
		}
	}
	
	//签到并增加积分
	public int sign( String name ) throws Exception{
		if(isReg(name) == -1 ){
			return -3;
		}
		//boolean isCheck = checkSign(name);
		if( checkSign(name) == true ){
			//System.out.println("----------------------" + isCheck);
			return -2;
		}
		String sqlStr = "select count(*) total from integral where states = 1";
		SqlHelper sp = new SqlHelper();
		ResultSet rs = sp.executeQuery(sqlStr);
		int pNum = 0;    //已签到人数
		int score = 0;   //应获得的积分
		int num = 0;     //已获得的积分
		
		//查询已签到人数
		while( rs.next() ){
			pNum = rs.getInt("total");
		}
		
		System.out.println("----------已签到人数：" + pNum);
		
		//设定积分
		switch( pNum ){
			case 0: score = 4; break;
			case 1: score = 3; break;
			case 2: score = 2; break;
			default: score = 1;
		}
		
		//查询已有积分并增加
		sqlStr = "select num from integral where name = '" + name + "'";
		rs = sp.executeQuery(sqlStr);
		while( rs.next() ){
			num = rs.getInt("num");
		}
		num = num + score;
		System.out.println("----------目前分数应该为：" + num);
		
		//增加积分并改变签到状态
		sqlStr = "update integral set num = " + String.valueOf(num) + ", states = 1 where name = '" + name + "'" ;
		sp.executeUpdate(sqlStr);
		if( sp.executeUpdate(sqlStr) ){
			return 1;
		}
		else return -1;
	}
}
