package Service;

import java.sql.ResultSet;

import Common.SqlHelper;

public class signIn {
	//����Ƿ���ǩ��
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
	
	//����Ƿ�ע��
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
	
	//ǩ�������ӻ���
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
		int pNum = 0;    //��ǩ������
		int score = 0;   //Ӧ��õĻ���
		int num = 0;     //�ѻ�õĻ���
		
		//��ѯ��ǩ������
		while( rs.next() ){
			pNum = rs.getInt("total");
		}
		
		System.out.println("----------��ǩ��������" + pNum);
		
		//�趨����
		switch( pNum ){
			case 0: score = 4; break;
			case 1: score = 3; break;
			case 2: score = 2; break;
			default: score = 1;
		}
		
		//��ѯ���л��ֲ�����
		sqlStr = "select num from integral where name = '" + name + "'";
		rs = sp.executeQuery(sqlStr);
		while( rs.next() ){
			num = rs.getInt("num");
		}
		num = num + score;
		System.out.println("----------Ŀǰ����Ӧ��Ϊ��" + num);
		
		//���ӻ��ֲ��ı�ǩ��״̬
		sqlStr = "update integral set num = " + String.valueOf(num) + ", states = 1 where name = '" + name + "'" ;
		sp.executeUpdate(sqlStr);
		if( sp.executeUpdate(sqlStr) ){
			return 1;
		}
		else return -1;
	}
}
