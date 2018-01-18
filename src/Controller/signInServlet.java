package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.time.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Common.clearTheState;
import Model.Sign;
import Service.signIn;

/**
 * Servlet implementation class signInServlet
 */
@WebServlet("/signInServlet")
public class signInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//设置编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		clearTheState cs = new clearTheState();
		
		//获取签到信息
		/*Sign s = new Sign();
		Calendar c = Calendar.getInstance();
		s.setName( request.getParameter("name") );
		s.setDate( new SimpleDateFormat("yyyy-mm-dd").format(new Date().getTime()) );
		s.setTime( c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) );*/
		String name = request.getParameter("name");
		name = new String(name.getBytes("iso8859-1"),"UTF-8");
		
		
		signIn s = new signIn();
		
		String reg="'";  //判断字符串中是否含有特定字符串'
		if( name.indexOf(reg) != -1 ){
			out.print("签到失败\n错误信息：001");
		}
		
		try{
			//s.checkSign(name);
			int res = s.sign(name);
			
			if( res == 1 ){
				out.print("签到成功~");
			}
			else if( res == -2 ){
				out.print("你已经签到咯~");
			}
			else if( res == -3 ){
				out.print("你还没有注册哦~");
			}
			else{
				out.print("签到失败，请重试~");
			}
		}
		catch( Exception e ){
			System.out.println(e);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
