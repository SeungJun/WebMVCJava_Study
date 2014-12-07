package spms.listeners;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import spms.dao.MemberDao;
import spms.util.DBConnectionPool;

//웹앱의 시작과 종료 사건을 담당. MemberDao의 인스턴스 생성. 
//ServletContext 를 활용해서 데이터베이스 커넥션을 저장할 수 있습니다. 
//데이터베이스를 이용하는 모든 서블릿은 ServletContext에서 DB커넥션 객체를 얻을 수 있습니다

@WebListener
public class ContextLoaderListener implements ServletContextListener{
//	Connection conn; //리스너의 인스턴스 변수
//	DBConnectionPool connPool;
//	BasicDataSource ds; 
	
	//웹앱의 시작 contextInitialized호출 
	@Override
	public void contextInitialized(ServletContextEvent event)//contextInitialized 메서드 호출 
	{
		try{
			ServletContext sc = event.getServletContext(); 
			
//			Class.forName(sc.getInitParameter("driver"));//DB커넥션 객체 생성  
//			conn = DriverManager.getConnection(
//					sc.getInitParameter("url"),
//					sc.getInitParameter("username"),
//					sc.getInitParameter("password")); 
			
//			connPool = new DBConnectionPool( //DB커넥션풀 객체 생성 
//					sc.getInitParameter("driver"),
//					sc.getInitParameter("url"),
//					sc.getInitParameter("username"), 
//					sc.getInitParameter("password")); 
			
//			ds = new BasicDataSource(); 
//			ds.setDriverClassName(sc.getInitParameter("driver"));
//		    ds.setUrl(sc.getInitParameter("url"));
//		    ds.setUsername(sc.getInitParameter("username"));
//		    ds.setPassword(sc.getInitParameter("password"));
			
			InitialContext initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java.comp/env/jdbc/appdb"); 

		//각 클래스에서 직접 MemberDao를 쓰는 대신 여기에 저장된 것을 꺼내쓴다. 
			MemberDao memberDao = new MemberDao(); //dao객체 생성
//			memberDao.setConnection(conn); //db커넥션 객체를 dao에 주입한다.
//			memberDao.setDBConnectionPool(connPool); //커넥션풀 객체 주입
			memberDao.setDataSource(ds);
			
			sc.setAttribute("memberDao", memberDao); //서블릿들이 dao객체를 사용할 수 있도록 servletcontext에 저장. 
			
		}catch(Throwable e){
			e.printStackTrace();
		}
	}
	@Override
	public void contextDestroyed(ServletContextEvent event)//종료 메서드 호출 
	{
//		try{//DB객체 해제 
//			conn.close();
//		}catch(Exception e){}
//		connPool.closeAll(); //DB풀 해제
		
//		try{if(ds!=null)ds.close();}catch(SQLException e){}
	}
	

}
