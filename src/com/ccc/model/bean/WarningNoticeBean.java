package com.ccc.model.bean;

import java.io.Serializable;

import java.sql.Timestamp;


public class WarningNoticeBean implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	private int post_type;
	private Timestamp post_time;
	private String post_content;
	private int like_count;
	private String rep_cause;
	private int post_property;
	private MemberBean member;
	private int war_notid;
	private int war_read;
	private ActivityBean activity;
	
	
	
	public ActivityBean getActivity() {
		return activity;
	}
	public void setActivity(ActivityBean activity) {
		this.activity = activity;
	}
	public int getWar_notid() {
		return war_notid;
	}
	public void setWar_notid(int war_notid) {
		this.war_notid = war_notid;
	}
	public int getWar_read() {
		return war_read;
	}
	public void setWar_read(int war_read) {
		this.war_read = war_read;
	}
	public Timestamp getPost_time() {
		return post_time;
	}
	public void setPost_time(Timestamp post_time) {
		this.post_time = post_time;
	}
	public MemberBean getMember() {
		return member;
	}
	public void setMember(MemberBean member) {
		this.member = member;
	}
	
	public int getPost_type() {
		return post_type;
	}
	public void setPost_type(int post_type) {
		this.post_type = post_type;
	}

	public String getPost_content() {
		return post_content;
	}
	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	public String getRep_cause() {
		return rep_cause;
	}
	public void setRep_cause(String rep_cause) {
		this.rep_cause = rep_cause;
	}
	public int getPost_property() {
		return post_property;
	}
	public void setPost_property(int post_property) {
		this.post_property = post_property;
	}

	
}

