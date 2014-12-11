package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

//@WebServlet("/auth/logout")
public class LogOutController implements Controller{
//	private static final long serialVersionUID = 1L;

	@Override
	public String execute(Map<String, Object>model)throws Exception{
		
//	protected void doGet(
//			HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		HttpSession session = request.getSession();
		HttpSession session = (HttpSession)model.get("session");
		session.invalidate(); //HttpSession 객체 무효화 
		
//		response.sendRedirect("login");
//		request.setAttribute("viewUrl", "redirect:login.do");
		return "redirect:login.do"; 
		
	}
}
