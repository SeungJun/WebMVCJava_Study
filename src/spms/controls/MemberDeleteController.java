package spms.controls;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import spms.dao.MemberDao;

//@WebServlet("/member/delete")
public class MemberDeleteController implements Controller{
//	private static final long serialVersionUID = 1L;

	@Override
	public String execute(Map<String, Object>model)throws Exception{
		
//	public void doGet(
//			HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
		try {
//			ServletContext sc = this.getServletContext();
//			conn =(Connection)sc.getAttribute("conn"); 
			
			//servletcontext에 있는 memberdao 객체를 재사용. garbage가 생성되지 않는다. 
//			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			
			MemberDao memberDao = (MemberDao)model.get("memberDao");
			
//			MemberDao memberDao = new MemberDao();
//			memberDao.setConnection(conn);
		
			//DAO에서 MNO튜플을 요청하여 삭제 쿼리를 요청 
//			memberDao.delete(Integer.parseInt(request.getParameter("no")));
			
			Integer no = (Integer)model.get("no");
			memberDao.delete(no);
			
//			response.sendRedirect("list");
//			request.setAttribute("viewUrl", "redirect:list.do");
			return "redirect:list.do"; 
			
		} catch (Exception e) {
			throw new ServletException(e);
		} 
	}
}
