package com.ccc.model.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ActivityBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer act_id;
	private MemberBean member;
	private int act_property;
	private String act_title;
	private java.sql.Timestamp act_time;
	private String act_location;
	private String act_content;
	private java.sql.Timestamp act_deadline;
	private Set<PostBean> posts = new HashSet<PostBean>();
	private Set<WarningNoticeBean> warningNotice  = new HashSet<WarningNoticeBean>();	
	private Set<ActivityNoticeBean> activityNotice = new HashSet<ActivityNoticeBean>();
	private Set<ActivityParticipateBean> activityParticipate = new HashSet<ActivityParticipateBean>();	
	
	///////////////竣評
	public Set<WarningNoticeBean> getWarningNotice() {
		return warningNotice;
	}
	public void setWarningNotice(Set<WarningNoticeBean> warningNotice) {
		this.warningNotice = warningNotice;
	}
	//////////////////凱	
	public Set<ActivityNoticeBean> getActivityNotice() {
		return activityNotice;
	}
	public void setActivityNotice(Set<ActivityNoticeBean> activityNotice) {
		this.activityNotice = activityNotice;
	}
	public Set<ActivityParticipateBean> getActivityParticipate() {
		return activityParticipate;
	}
	public void setActivityParticipate(Set<ActivityParticipateBean> activityParticipate) {
		this.activityParticipate = activityParticipate;
	}
	//////////////////凱  完
	public Integer getAct_id() {
		return act_id;
	}
	public void setAct_id(Integer act_id) {
		this.act_id = act_id;
	}
	public MemberBean getMember() {
		return member;
	}
	public void setMember(MemberBean member) {
		this.member = member;
	}
	public int getAct_property() {
		return act_property;
	}
	public void setAct_property(int act_property) {
		this.act_property = act_property;
	}
	public String getAct_title() {
		return act_title;
	}
	public void setAct_title(String act_title) {
		this.act_title = act_title;
	}
	public java.sql.Timestamp getAct_time() {
		return act_time;
	}
	public void setAct_time(java.sql.Timestamp act_time) {
		this.act_time = act_time;
	}
	public String getAct_location() {
		return act_location;
	}
	public void setAct_location(String act_location) {
		this.act_location = act_location;
	}
	public String getAct_content() {
		return act_content;
	}
	public void setAct_content(String act_content) {
		this.act_content = act_content;
	}
	public java.sql.Timestamp getAct_deadline() {
		return act_deadline;
	}
	public void setAct_deadline(java.sql.Timestamp act_deadline) {
		this.act_deadline = act_deadline;
	}
	public Set<PostBean> getPosts() {
		return posts;
	}
	public void setPosts(Set<PostBean> posts) {
		this.posts = posts;
	}

}
