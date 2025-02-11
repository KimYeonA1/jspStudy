package co.kh.dev.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JoinList
 */
//@WebServlet(name = "memberList", urlPatterns = { "/memberList" })
public class MemberList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberList() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("<html>");
			out.println("<head><title>방명록 리스트</title></head>");
			out.println("<body>");
			String MEMBER_SELECT = "SELECT * FROM MEMBER ORDER BY NO";
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "webuser", "123456");
				pstmt = con.prepareStatement(MEMBER_SELECT);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int no = rs.getInt("NO");
					String name = rs.getString("NAME");
					String uid = rs.getString("ID");
					String pwd = rs.getString("PWD");
					java.sql.Date regdate = rs.getDate("regdate");
					out.println("<table align=center border=1>");
					out.println("<tr>");
					out.println("<th width=50>번호</th>");
					out.println("<td width=50 align=center>" + no + "</td>");
					out.println("<th width=100>이름</th>");
					out.println("<td width=100 align=center>" + name + "</td>");
					out.println("<th width=100>아이디</th>");
					out.println("<td width=150 align=center>" + uid + "</td>");
					out.println("<th width=100>비밀번호</th>");
					out.println("<td width=150 align=center>" + pwd + "</td>");
					out.println("<th width=100>가입날짜</th>");
					out.println("<td width=150 align=center>" + regdate + "</td>");
					out.println("</tr>");
					out.println("</table>");
					out.println("<p>");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
				} catch (SQLException e) {
				}
				try {
					if (con != null)
						con.close();
				} catch (SQLException e) {
				}
			}
			out.println("<p align=center><a href=/jspStudy/member/member.html>회원가입</a></p>");
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
	}
}
