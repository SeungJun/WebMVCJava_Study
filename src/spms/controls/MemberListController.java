package spms.controls;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import spms.controls.Controller;
import spms.dao.MemberDao;

// UI 출력 코드를 제거하고, UI 생성 및 출력을 JSP에게 위임한다.
//@WebServlet("/member/list")
public class MemberListController implements Controller{

//	@Override
//	public void doGet(
//			HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
	
	@Override
	public String execute(Map<String, Object>model)throws Exception{
		try {
//			ServletContext sc = this.getServletContext();			
			//conn =(Connection)sc.getAttribute("conn"); 
			
			//MemberDao memberDao = new MemberDao();
			//memberDao.setConnection(conn);
			
			//servletcontext에 있는 memberdao 객체를 재사용. garbage가 생성되지 않는다. 
//			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			//MemberDao 객체는 프론트 컨트롤러의 Map객체에 들어있다. 
			MemberDao memberDao = (MemberDao)model.get("memberDao");
			//회원목록 데이터를 model에 저장한다. 
			model.put("members", memberDao.selectList());
			
			// request에 회원 목록 데이터 보관한다.
//			request.setAttribute("members", memberDao.selectList());
			
			//DispatcherServlet이 담당하게되는 부분 
			// JSP로 출력을 위임한다.
//			RequestDispatcher rd = request.getRequestDispatcher(
//					"/member/MemberList.jsp");
//			rd.include(request, response);
//			request.setAttribute("viewUrl", "/member/MemberList.jsp");
			
			return "/member/MemberList.jsp"; 
			
		} catch (Exception e) {
			throw new ServletException(e);
			//request.setAttribute("error", e);
			//RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp"); 
			//rd.forward(request, response);
			
		} /*finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
		}*/

	}
}
