 package spms.controls;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import spms.dao.MemberDao;
import spms.vo.Member;

//@SuppressWarnings("serial")
//@WebServlet("/member/update")
public class MemberUpdateController implements Controller{
//	@Override
//	protected void doGet(
//			HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {

	@Override
	public String execute(Map<String, Object>model) throws Exception{
		try {
//			ServletContext sc = this.getServletContext();
//			conn = (Connection)sc.getAttribute("conn"); 
			
//			MemberDao memberDao = new MemberDao(); 
//			memberDao.setConnection(conn);
			
			//servletcontext에 있는 memberdao 객체를 재사용. garbage가 생성되지 않는다. 
//			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			MemberDao memberDao = (MemberDao)model.get("memberDao");
			
//		    Member member = memberDao.selectOne(
//          Integer.parseInt(request.getParameter("no")));
			
			if(model.get("member")==null)
			{
				//회원 목록 데이터 보관한다.
				Integer no = (Integer)model.get("no");
				Member member = memberDao.selectOne(no);
				model.put("member", member);
				return "/member/MemberUpdateForm.jsp"; 
			}
			else{
				//VO에서 입력된값의 member객체를 조회하여 DAO에 업데이트한다.
				Member member = (Member)model.get("member");
				memberDao.update(member);
				return "redirect:list.do"; 
			}

			// request에 회원 목록 데이터 보관한다.
			//request.setAttribute("member", memberDao.selectOne(0));
//			request.setAttribute("member", member);
			
//			RequestDispatcher rd = request.getRequestDispatcher(
//					"/member/MemberUpdateForm.jsp");
//			rd.forward(request, response);
//			request.setAttribute("viewUrl", "/member/MemberUpdateForm.jsp");
			
		} catch (Exception e) {
			throw new ServletException(e);
//			e.printStackTrace();
//			request.setAttribute("error", e);
//		    RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
//		    rd.forward(request, response);
		} 
	
/*	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// CharacterEncodingFilter에서 처리
		request.setCharacterEncoding("UTF-8");*/

//		try {
//			ServletContext sc = this.getServletContext();
//			conn = (Connection)sc.getAttribute("conn");  
			
//			MemberDao memberDao = new MemberDao(); 
//			memberDao.setConnection(conn);
			
			//servletcontext에 있는 memberdao 객체를 재사용. garbage가 생성되지 않는다. 
//			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao"); 
			
			//VO에서 입력된값의 member객체를 조회하여 DAO에 업데이트한다. 
//			Member member = (Member)request.getAttribute("member");
//			memberDao.update(member);
//			response.sendRedirect("list");
//			request.setAttribute("viewUrl", "redirect:list.do");
			
		/*} catch (Exception e) {
			throw new ServletException(e);
			e.printStackTrace();
			request.setAttribute("error", e);
		    RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
		    rd.forward(request, response);*/
		
	}
}
