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
		
		//���ñ����ʽ
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		clearTheState cs = new clearTheState();
		
		//��ȡǩ����Ϣ
		/*Sign s = new Sign();
		Calendar c = Calendar.getInstance();
		s.setName( request.getParameter("name") );
		s.setDate( new SimpleDateFormat("yyyy-mm-dd").format(new Date().getTime()) );
		s.setTime( c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) );*/
		String name = request.getParameter("name");
		name = new String(name.getBytes("iso8859-1"),"UTF-8");
		
		
		signIn s = new signIn();
		
		String reg="'";  //�ж��ַ������Ƿ����ض��ַ���'
		if( name.indexOf(reg) != -1 ){
			out.print("ǩ��ʧ��\n������Ϣ��001");
		}
		
		try{
			//s.checkSign(name);
			int res = s.sign(name);
			
			if( res == 1 ){
				out.print("ǩ���ɹ�~");
			}
			else if( res == -2 ){
				out.print("���Ѿ�ǩ����~");
			}
			else if( res == -3 ){
				out.print("�㻹û��ע��Ŷ~");
			}
			else{
				out.print("ǩ��ʧ�ܣ�������~");
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
