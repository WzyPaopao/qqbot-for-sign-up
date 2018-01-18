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
		//设置编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		//获取get请求数据
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
		
		String reg="'";  //判断字符串中是否含有特定字符串'
		if( p.getName().indexOf(reg) != -1 ){
			out.print("注册失败\n错误信息：001");
		}
		else{
			//注册返回码
			int res = registration.addUser(p);
			
			if( res == 1 ){
				out.print("注册成功~");
			}
			else if( res == -1 ){
				out.print("注册失败\n错误信息：002");
			}else if( res == -2 ){
				out.print("你已经注册过咯~");
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
