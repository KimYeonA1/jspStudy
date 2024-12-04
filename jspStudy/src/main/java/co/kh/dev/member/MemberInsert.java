package co.kh.dev.member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JoinInsert
 */
//@WebServlet(name = "memberInsert", urlPatterns = { "/memberInsert" })
public class MemberInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public MemberInsert() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1-1. 전송된 값을 UTF-8 세팅.
		request.setCharacterEncoding("UTF-8");
		// 1-2. 정보 가져오기
		String name = request.getParameter("name");
		String uid = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String MEMBER_INSERT = "INSERT INTO MEMBER VALUES((select NVL(max(no),0)+1 from member),?,?,?,SYSDATE)";

		// 2. 테이블에 저장한다.(프로토콜)
		Connection con = null;
		boolean flag = false;
		String url = "jdbc:oracle:thin:@127.0.0.1:1521/xe";
		String id = "webuser";
		String pw = "123456";
		PreparedStatement pstmt = null; // 오라클에서 작업할 쿼리문 사용할게 하는 명령문
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, id, pw);
			
			pstmt = con.prepareStatement(MEMBER_INSERT);
			pstmt.setString(1, name);
			pstmt.setString(2, uid);
			pstmt.setString(3, pwd);
			System.out.println(name+uid+pwd);
			int count = pstmt.executeUpdate();
			flag=(count != 0) ? true : false;
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			if (con != null) {
				try {
					con.close();

				} catch (SQLException e) {
					System.out.println(e.toString());
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();

				} catch (SQLException e) {
					System.out.println(e.toString());
				}
			}
		}//finally
		if(flag) {
			System.out.println("입력성공");
			response.sendRedirect("memberList");
		}else {
			System.out.println("입력실패");
		}
	
	}

}
