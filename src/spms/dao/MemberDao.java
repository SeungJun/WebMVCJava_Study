package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.sql.DataSource;//반드시 import

import spms.vo.Member;

//JDBC SQL 쿼리 로직 클래스 : 하나의 테이블에 대응 - appdb
//MemberDao 클래스에서 주목할 것은 connection 인스턴스 변수와 셋터 메서드 입니다. 
//여기선 ServletContext에 접근할 수 없기 때문에, ServletContext에 보관된 DBConnection 객체를 꺼낼 수 없다. 
//이를 해결하기 위해 외부로부터 Connection 객체를 주입 받기 위한 셋터 메서드와 인스턴스 변수를 준비 

public class MemberDao {
//	Connection conn; 
//	
//	public void setConnection(Connection connection)//외부로부터 주입된 객체. Dependecy Injection, IoC 
//	{
//		this.conn=connection; 
//	}
//	
//	DBConnectionPool connPool; 
//	
//	public void setDBConnectionPool(DBConnectionPool connPool){
//		this.connPool = connPool; 
//	}
	
	DataSource ds; //구현체가 아닌 인터페이스 사용. 유연성 향상
	
	public void setDataSource(DataSource ds)
	{
		this.ds = ds; 
		
	}
	
	
	public List<Member> selectList() throws Exception{
		Connection conn = null; 
		Statement stmt = null; 
		ResultSet rs = null; 
		
		try{
//			conn = connPool.getConnection();
			
			conn = ds.getConnection(); 
			stmt = conn.createStatement(); 
	
			rs = stmt.executeQuery(
					"SELECT MNO,MNAME,EMAIL,CRE_DATE" + 
					" FROM MEMBERS" +
					" ORDER BY MNO ASC");
			
			ArrayList<Member> members = new ArrayList<Member>();
			
			while(rs.next()){
				members.add(new Member()
				.setNo(rs.getInt("MNO"))
				.setName(rs.getString("MNAME"))
				.setEmail(rs.getString("EMAIL"))
				.setCreatedDate(rs.getDate("CRE_DATE"))); 
			}
			return members; 
			
//			while(rs.next()){
//				return new Member()
//				.setNo(rs.getInt("MNO"))
//				.setName(rs.getString("MNAME"))
//				.setEmail(rs.getString("EMAIL"))
//				.setCreatedDate(rs.getDate("CRE_DATE")); 
//			}
		}catch(Exception e){
			throw e; 
		}finally{
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(stmt!=null)stmt.close();}catch(Exception e){}
//			if(conn!=null)connPool.returnConnection(conn);
			try{if(conn!=null)conn.close();}catch(Exception e){}
		}
	}
	
	public int insert(Member member)throws Exception
	{
		Connection conn = null; 
		PreparedStatement stmt = null;
		
		try{
//			conn = connPool.getConnection();
			conn = ds.getConnection(); 
			stmt = conn.prepareStatement(
					"INSERT INTO MEMBERS(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)"
							+ " VALUES (?,?,?,NOW(),NOW())");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getName());
			return stmt.executeUpdate();
			
		}catch (Exception e) {
		      throw e;

	    } finally {
	    	try {if (stmt != null) stmt.close();} catch(Exception e) {}
//			if(conn!=null)connPool.returnConnection(conn);
			try{if(conn!=null)conn.close();}catch(Exception e){}

	    }
	}
	public int delete(int no)throws Exception{
		Connection conn = null; 
		Statement stmt = null;

		try{
//			conn = connPool.getConnection();
			conn = ds.getConnection();
			stmt = conn.createStatement();
			return stmt.executeUpdate(
					"DELETE FROM MEMBERS WHERE MNO=" + no);
			
		} catch (Exception e) {
			throw new ServletException(e);
			
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
//			if(conn!=null)connPool.returnConnection(conn);
			try{if(conn!=null)conn.close();}catch(Exception e){}

		}
	}
	
	public Member selectOne(int no)throws Exception{
		Connection conn = null; 
		Statement stmt = null; 
		ResultSet rs = null; 
		try{
//			conn = connPool.getConnection(); 
			conn = ds.getConnection(); 
			stmt = conn.createStatement(); 
			
			rs=stmt.executeQuery(
					"SELECT MNO,EMAIL,MNAME,CRE_DATE FROM MEMBERS" +
			" WHERE MNO="+ no); 
			
			if(rs.next()){
				return new Member()
				.setNo(rs.getInt("MNO"))
				.setEmail(rs.getString("EMAIL"))
				.setName(rs.getString("MNAME"))
				.setCreatedDate(rs.getDate("CRE_DATE")); 
			}
			else{
				throw new Exception("해당 번호희 회원을 찾을 수 없습니다."); 
			}
		}catch(Exception e){
			throw new ServletException(e); 
		}finally{
			try{if(rs!=null)rs.close();}catch(Exception e){}
			try{if(stmt!=null)stmt.close();}catch(Exception e){}
//			if(conn!=null)connPool.returnConnection(conn);
			try{if(conn!=null)conn.close();}catch(Exception e){}

		}
	}
	
	public int update(Member member)throws Exception{
		Connection conn = null; 
		PreparedStatement stmt = null;
		try{
//			conn = connPool.getConnection();
			conn = ds.getConnection(); 
			stmt = conn.prepareStatement(
					"UPDATE MEMBERS SET EMAIL=?,MNAME=?,MOD_DATE=now()"
					+ " WHERE MNO=?");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getName());
			stmt.setInt(3, member.getNo());
			return stmt.executeUpdate(); 
			
		}catch(Exception e){
			throw e; 
		}finally{
			try{if(stmt!=null)stmt.close();}catch(Exception e){}
			//try{if(conn!=null)conn.close();}catch(Exception e){}
//			if(conn!=null)connPool.returnConnection(conn);
			try{if(conn!=null)conn.close();}catch(Exception e){}

		}
	}
	
	public Member exist(String email, String password)throws Exception{
		Connection conn = null; 
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
//			conn = connPool.getConnection(); 
			conn = ds.getConnection(); 
		    stmt = conn.prepareStatement(
					"SELECT MNAME,EMAIL FROM MEMBERS"
					+ " WHERE EMAIL=? AND PWD=?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return new Member()
						.setEmail(rs.getString("EMAIL"))
						.setName(rs.getString("MNAME"));
			} else {return null; 
			}
		}catch(Exception e){
			throw e; 
		}
		finally {
			try {if (rs != null) rs.close();} catch (Exception e) {}
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
//			if(conn!=null)connPool.returnConnection(conn);
			try{if(conn!=null)conn.close();}catch(Exception e){}

		}
	}
}
