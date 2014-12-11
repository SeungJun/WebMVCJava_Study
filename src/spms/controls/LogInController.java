package spms.controls;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import spms.dao.MemberDao;
import spms.vo.Member;

//@WebServlet("/auth/login")
public class LogInController implements Controller{
//	private static final long serialVersionUID = 1L;
	@Override
	public String execute(Map<String, Object>model) throws Exception{ 
//	protected void doGet(
//			HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		RequestDispatcher rd = request.getRequestDispatcher(
//				"/auth/LogInForm.jsp");
//		rd.forward(request, response);
//		request.setAttribute("viewUrl", "/auth/LogInForm.jsp");
//	}
		if(model.get("loginInfo")==null){
			return "auth/LogInForm.jsp"; 
		}
		else{
			try{
				MemberDao memberDao = (MemberDao)model.get("memberDao");
				Member loginfo = (Member)model.get("loginInfo");
				Member member = memberDao.exist(
						loginfo.getEmail(),
						loginfo.getPassword());
				if(member != null){
					HttpSession session = (HttpSession)model.get("session");
					session.setAttribute("member", member);
					return "redirect:../member/list.do"; 
				}else{
					return "/auth/LogInFail.jsp"; 
				}
			}catch(Exception e){
				throw new ServletException(e);
			}
			
		}
	
//	@Override
//	protected void doPost(
//			HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		try {
//			ServletContext sc = this.getServletContext();
//			conn = (Connection) sc.getAttribute("conn");  
			//servletcontext에 있는 memberdao 객체를 재사용. garbage가 생성되지 않는다. 
//			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao"); 
			
//			MemberDao memberDao = new MemberDao(); 
//			memberDao.setConnection(conn);
			
//			Member member = memberDao.exist(
//					request.getParameter("email"),
//					request.getParameter("password")); 
//			if(member !=null){
//				HttpSession session = request.getSession();
//				session.setAttribute("member", member);
//				response.sendRedirect("../member/list");
//				request.setAttribute("viewUrl", "redirect:../member/list.do");
//			}else {
////				RequestDispatcher rd = request.getRequestDispatcher(
////						"/auth/LogInFail.jsp");
////				rd.forward(request, response);
//				request.setAttribute("viewUrl", "/auth/LogInFail.jsp");
//			}
//		} catch (Exception e) {
//			throw new ServletException(e);
//		} 
	}
}
