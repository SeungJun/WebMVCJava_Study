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
	Connection conn;
	
	@Override
	public void contextInitialized(ServletContextEvent event){
		try{
			ServletContext sc = event.getServletContext(); 
			
			Class.forName(sc.getInitParameter("driver")); 
			conn = DriverManager.getConnection(
					sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password")); 

		//각 클래스에서 직접 MemberDao를 쓰는 대신 여기에 저장된 것을 꺼내쓴다. 
			MemberDao memberDao = new MemberDao(); 
			memberDao.setConnection(conn);
			
			sc.setAttribute("memberDao", memberDao);
		}catch(Throwable e){
			e.printStackTrace();
		}
	}
	@Override
	public void contextDestroyed(ServletContextEvent event){
		try{
			conn.close();
		}catch(Exception e){}
	}
	

}
