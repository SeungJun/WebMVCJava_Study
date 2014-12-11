package spms.util;

import java.sql.Connection;
import java.sql.DriverManager; 
import java.util.ArrayList;

public class DBConnectionPool {
	String url; 
	String username; 
	String password; 
	//Connection객체를 보관할 ArrayList를 준비(Connecton Pool) 
	ArrayList<Connection> connList = new ArrayList<Connection>(); 
	
	//DB커넥션 생성에 필요한 매개변수 
	public DBConnectionPool(String driver, String url, String username, String password)
	throws Exception
	{
		this.url = url; 
		this.username = username; 
		this.password = password; 
		
		Class.forName(driver);
	}
	//ArrayList에 있는 커넥션 호출 
	public Connection getConnection() throws Exception{
		if(connList.size()>0){
			Connection conn = connList.get(0);
			if(conn.isValid(10))return conn;//유효성 체크  
		}
		//ArrayList에 보관된 객체가 없다면, DriverManager를 통해 새로 만들어 반환합니다. 
		return DriverManager.getConnection(url, username, password);
	}
	//커넥션 객체를 쓰고 풀에 반환하는 메서드 
	public void returnConnection(Connection conn)throws Exception{
		connList.add(conn);
	}
	//DB연결 모두 해제  
	public void closeAll(){
		for(Connection conn : connList){
			try{
				conn.close();
			}catch(Exception e){}
		}
	}

}
