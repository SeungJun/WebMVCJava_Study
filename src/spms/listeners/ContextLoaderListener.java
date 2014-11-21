package spms.listeners;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import spms.dao.MemberDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener{
	Connection conn; //리스너의 인스턴스 변수 
	
	@Override
	public void contextInitialized(ServletContextEvent event)//contextInitialized 메서드 호출 
	{
		try{
			ServletContext sc = event.getServletContext(); 
			
			Class.forName(sc.getInitParameter("driver"));//DB커넥션 객체 생성  
			conn = DriverManager.getConnection(
					sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password")); 

		//각 클래스에서 직접 MemberDao를 쓰는 대신 여기에 저장된 것을 꺼내쓴다. 
			MemberDao memberDao = new MemberDao(); //dao객체 생성
			memberDao.setConnection(conn); //db커넥션 객체를 dao에 주입한다. 
			
			sc.setAttribute("memberDao", memberDao); //서블릿들이 dao객체를 사용할 수 있도록 servletcontext에 저장. 
			
		}catch(Throwable e){
			e.printStackTrace();
		}
	}
	@Override
	public void contextDestroyed(ServletContextEvent event)//종료 메서드 호출 
	{
		try{
			conn.close();
		}catch(Exception e){}
	}
	

}
