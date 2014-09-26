package spms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import spms.vo.Member;

//JDBC SQL 쿼리 로직 클래스 : 하나의 테이블에 대응 - appdb
public class MemberDao {
	Connection conn; 
	
	public void setConnection(Connection connection){
		this.conn=connection; 
	}
	
	public List<Member> selectList() throws Exception{
		Statement stmt = null; 
		ResultSet rs = null; 
		
		try{
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
		}
	}
	
	public int insert(Member member)throws Exception
	{
		PreparedStatement stmt = null;
		
		try{
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
	    }
	}
	public int delete(int no)throws Exception{
		
		Statement stmt = null;

		try{
			stmt = conn.createStatement();
			return stmt.executeUpdate(
					"DELETE FROM MEMBERS WHERE MNO=" + no);
			
		} catch (Exception e) {
			throw new ServletException(e);
			
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			//try {if (conn != null) conn.close();} catch(Exception e) {}
		}
	}
	
	public Member selectOne(int no)throws Exception{
		Statement stmt = null; 
		ResultSet rs = null; 
		try{
			stmt = conn.createStatement(); 
			
			rs=stmt.executeQuery(
					"SELECT MNO,EMAIL,MNAME,CRE_DATE FROM MEMERS" +
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
		}
	}
	
	public int update(Member member)throws Exception{
		PreparedStatement stmt = null;
		try{
			stmt = conn.prepareStatement(
					"UPDATE MEMBERS SET EMAIL=?,MOD_DATE=now()"
					+ " WHERE MNO=?");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getName());
			stmt.setInt(3, member.getNo());
			
		}catch(Exception e){
			e.printStackTrace(); 
		}finally{
			try{if(stmt!=null)stmt.close();}catch(Exception e){}
			//try{if(conn!=null)conn.close();}catch(Exception e){}
		}
		return stmt.executeUpdate(); 
	}
	
	public Member exist(String email, String password)throws Exception{
		
	}
}
