package com.ccc.model.bean;

import java.io.Serializable;
import java.sql.Date;

public class MessageBean implements Serializable {
	private int msg_id;
	private java.sql.Timestamp msg_time;
	private String msg_content;
	private MemberBean member_from;
	private MemberBean member_to;
	

	public MemberBean getMember_from() {
		return member_from;
	}

	public void setMember_from(MemberBean member_from) {
		this.member_from = member_from;
	}

	public MemberBean getMember_to() {
		return member_to;
	}

	public void setMember_to(MemberBean member_to) {
		this.member_to = member_to;
	}

	public int getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(int msg_id) {
		this.msg_id = msg_id;
	}
	
	public java.sql.Timestamp getMsg_time() {
		return msg_time;
	}

	public void setMsg_time(java.sql.Timestamp msg_time) {
		this.msg_time = msg_time;
	}

	public String getMsg_content() {
		return msg_content;
	}

	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}

}
