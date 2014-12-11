package spms.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.controls.Controller;
import spms.controls.MemberAddController;
import spms.controls.MemberListController;
import spms.vo.Member;

//프런트 컨트롤러
@SuppressWarnings("serial")
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	@Override
	protected void service(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String servletPath = request.getServletPath();
		try {
			ServletContext sc = this.getServletContext();
			
			//프런트와 페이지 컨트롤러 사이에서 데이터나 객체 교환용 Map
			HashMap<String,Object>model = new HashMap<String,Object>();
			model.put("memberDao", sc.getAttribute("memberDao"));

			//      MemberListController pageControllerPath = null;
			String pageControllerPath= null; 
			//인터페이스타입의 참조변수 
			Controller pageController = null; 

			//회원 
			if("/member/list.do".equals(servletPath)) {
				//        pageControllerPath = "/member/list";
				pageController = new MemberListController(); 

			}else if("/member/add.do".equals(servletPath)) {
				//        pageControllerPath = "/member/add";
				pageController = new MemberAddController(); 
				if (request.getParameter("email") != null) {
					model.put("member", new Member()
					.setEmail(request.getParameter("email"))
					.setPassword(request.getParameter("password"))
					.setName(request.getParameter("name")));
				}
			} else if ("/member/update.do".equals(servletPath)) {
				        pageControllerPath = "/member/update";
//				pageController = new MemberListController(); 
				if (request.getParameter("email") != null) {
					model.put("member", new Member()
					.setNo(Integer.parseInt(request.getParameter("no")))
					.setEmail(request.getParameter("email"))
					.setName(request.getParameter("name")));
				}
			} else if ("/member/delete.do".equals(servletPath)) {
				        pageControllerPath = "/member/delete";
//				pageController = new MemberListController(); 

			} else if ("/auth/login.do".equals(servletPath)) {
				        pageControllerPath = "/auth/login";
//				pageController = new MemberListController(); 

			} else if ("/auth/logout.do".equals(servletPath)) {
				        pageControllerPath = "/auth/logout";
//				pageController = new MemberListController(); 
			}

			//      RequestDispatcher rd = request.getRequestDispatcher(pageControllerPath);
			//      rd.include(request, response);

			//페이지 컨트롤러 실행 
//			String viewUrl = (String)request.getAttribute("viewUrl");
			String viewUrl = pageController.execute(model);
			
			for(String key:model.keySet()){
				request.setAttribute(key, model.get(key));
			}
			if (viewUrl.startsWith("redirect:")){
				response.sendRedirect(viewUrl.substring(9));
				return;

			} else {
				//        rd = request.getRequestDispatcher(viewUrl);
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}

		} catch (Exception e) {
			throw new ServletException(e);
//			e.printStackTrace();
//			request.setAttribute("error", e);
//			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
//			rd.forward(request, response);
		}
	}
}
