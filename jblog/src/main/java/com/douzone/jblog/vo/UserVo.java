package com.douzone.jblog.vo;

public class UserVo {
	private long no;
	private String id;
	private String name;
	private String password;
	private long join_date;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getJoin_date() {
		return join_date;
	}
	public void setJoin_date(long join_date) {
		this.join_date = join_date;
	}
	@Override
	public String toString() {
		return "UserVo [no=" + no + ", id=" + id + ", name=" + name + ", password=" + password + ", join_date="
				+ join_date + "]";
	}
	
}
