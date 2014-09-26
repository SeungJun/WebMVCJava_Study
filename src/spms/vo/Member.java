package spms.vo;

import java.util.Date;

//각 모듈에서 뷰가 분리될 수 있도록 db정보값을 저장해두는 객체
public class Member {
	//총 6개의 DB튜플이 존재 
	protected int 		no;
	protected String 	name;
	protected String 	email;
	protected String 	password;
	protected Date		createdDate;
	protected Date		modifiedDate;
	
	public int getNo() {
		return no;
	}
	public Member setNo(int no) {
		this.no = no;
		return this;
	}
	public String getName() {
		return name;
	}
	public Member setName(String name) {
		this.name = name;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public Member setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public Member setPassword(String password) {
		this.password = password;
		return this;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public Member setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
		return this;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public Member setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
		return this;
	}
}
