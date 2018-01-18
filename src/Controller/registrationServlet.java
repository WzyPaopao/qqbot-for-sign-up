package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.People;
import Service.registration;

/**
 * Servlet implementation class registrationServlet
 */
@WebServlet("/registrationServlet")
public class registrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registrationServlet() {
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
		
		//��ȡget��������
		People p = new People();
		String name = request.getParameter("name");
		String qq = request.getParameter("qq");
		name = new String(name.getBytes("iso8859-1"),"UTF-8");
		qq = new String(qq.getBytes("iso8859-1"),"UTF-8");
		System.out.println("--------------name = " + name);
		System.out.println("--------------qq = " + qq);
		p.setName( name );
		p.setQq( qq );
		
		//p.setName("al'an");
		//p.setQq("123");
		
		String reg="'";  //�ж��ַ������Ƿ����ض��ַ���'
		if( p.getName().indexOf(reg) != -1 ){
			out.print("ע��ʧ��\n������Ϣ��001");
		}
		else{
			//ע�᷵����
			int res = registration.addUser(p);
			
			if( res == 1 ){
				out.print("ע��ɹ�~");
			}
			else if( res == -1 ){
				out.print("ע��ʧ��\n������Ϣ��002");
			}else if( res == -2 ){
				out.print("���Ѿ�ע�����~");
			}
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
