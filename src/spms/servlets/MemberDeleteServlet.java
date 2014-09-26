package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		//Statement stmt = null;
		try {
			ServletContext sc = this.getServletContext();
			conn =(Connection)sc.getAttribute("conn"); 
			/*Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
						sc.getInitParameter("url"),
						sc.getInitParameter("username"),
						sc.getInitParameter("password")); */
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
		
			//DAO에서 MNO튜플을 요청하여 삭제 쿼리를 요청 
			memberDao.delete(Integer.parseInt(request.getParameter("no"))); 
			
		/*	stmt = conn.createStatement();
			stmt.executeUpdate(
					"DELETE FROM MEMBERS WHERE MNO=" + 
					request.getParameter("no"));*/
			response.sendRedirect("list");
			
		} catch (Exception e) {
			throw new ServletException(e);
		} 
	}
}
