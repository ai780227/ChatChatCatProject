package com.ccc.model.bean;

import java.io.Serializable;
/*
 * 加友通知 FriendshipNoticeBean
 * table：加友通知 FriendshipNotice
 */
public class FriendshipNoticeBean implements Serializable{
	private int fri_notid;  //加友通知 ID
	private String m_name_from; //發送者的名
	private int fri_read; //read 
	private int fri_type; //加友通知類型 0=接收到加友通知, 1=對方已同意加友
	private MemberBean member_to;    //接收者-會員ID
	private MemberBean member_from;  //發送者-會員ID   
	
	public MemberBean getMember_to(){
		return member_to;
		
	}
	public void setMember_to(MemberBean member_to){
		this.member_to=member_to;
	}
	
	public MemberBean getMember_from(){
		return member_from;
		
	}
	public void setMember_from(MemberBean member_from){
		this.member_from=member_from;
	}

	public void setFri_notid(int fri_notid) {
		this.fri_notid = fri_notid;
	}

	public void setM_name_from(String m_name_from) {
		this.m_name_from = m_name_from;
	}

	public void setFri_read(int fri_read) {
		this.fri_read = fri_read;
	}

	public void setFri_type(int fri_type) {
		this.fri_type = fri_type;
	}

	public String getM_name_from() {
		return m_name_from;
	}

	public int getFri_read() {
		return fri_read;
	}

	public int getFri_type() {
		return fri_type;
	}

	public int getFri_notid() {
		return fri_notid;
	}

}
