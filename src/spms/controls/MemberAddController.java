package spms.controls;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/member/add")
public class MemberAddController implements Controller{
//	private static final long serialVersionUID = 1L;
//
//	@Override
//	protected void doGet(
//			HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
////		RequestDispatcher rd = request.getRequestDispatcher("/member/MemberForm.jsp");
////		rd.forward(request, response); 
//		request.setAttribute("viewUrl", "/member/MemberForm.jsp");
//	}
//	
//	@Override
//	protected void doPost(
//			HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// CharacterEncodingFilter에서 처리
//		request.setCharacterEncoding("UTF-8");
	@Override
	public String execute(Map<String, Object>model)throws Exception{
		if(model.get("member")==null)//입력폼을 요청할때 
		{
			return "/member/MemberForm.jsp";
		}
		else{//회원등록을 요청할때 
			MemberDao memberDao = (MemberDao)model.get("memberDao");
			
			//VO객체는 POST요청 
			Member member = (Member)model.get("member");
			memberDao.insert(member);
			
			return "redirect:list.do"; 
		}

		/*
		try {
			ServletContext sc = this.getServletContext();			
//			conn =(Connection)sc.getAttribute("conn"); 
			
			//servletcontext에 있는 memberdao 객체를 재사용. garbage가 생성되지 않는다. 
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao"); 
			
//			MemberDao memberDao = new MemberDao();
//			memberDao.setConnection(conn);
			
			//VO에다가 입력값을 던져주고 파라터값을 요청 DispatcherServlet으로 대체 
//			memberDao.insert(new Member()
//	        .setEmail(request.getParameter("email"))
//	        .setPassword(request.getParameter("password"))
//	        .setName(request.getParameter("name")));
			
			//클라이언트가 보낸 회원정보를 꺼내기위해 getParameter를 쓰는대신, 
			//프론트 컨트롤러가 준비해놓은 Member객체를 ServletRequest에서 호출 
			Member member = (Member)request.getAttribute("member");
			memberDao.insert(member);
			
//			response.sendRedirect("list");
			request.setAttribute("viewUrl", "redirect:list.do");
			
		} catch (Exception e) {
			throw new ServletException(e);
//			e.printStackTrace();
//			request.setAttribute("error", e);
//			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp"); 
//			rd.forward(request, response);
		}
		*/ 
	}
}
