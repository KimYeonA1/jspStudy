package co.kh.dev;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(name = "myFirstServlet", urlPatterns = { "/myFirstServlet" })
@WebServlet("/myFirstServlet")
public class MyFirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 디폴트 생성자
	public MyFirstServlet() {
		super();
		System.out.println("처음으로 한 번만 초기화");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// super.service(request, response);
		doGet(request, response);
		// requestTest(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("requestTest running");
		// 1. 사용자 요청 정보를 받아야한다. request 없으니까 생략한다.
		// 2. 데이타베이스를 연동해서 curd를 진행한다

		// 3.1 서버에서 클라이언트 문자를 보내기 위한 문자셋 진행한다.
		response.setCharacterEncoding("UTF-8");
		// 3.2 브라우저에게 text/html;charset = UTF-8)
		response.setContentType("text/html;charset = UTF-8");
		// 3.3 출력스트림을 결정한다. (1Byte, 2Byte)
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<body>");
		out.println("<h1>My First Servlet Program !!!!!</h1>");
		out.println("<br>");
		out.println(new java.util.Date());
		out.println("</body>");
		out.println("</html>");
		out.flush();

	}

}
