package com.ccc.model.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class ActivityNoticeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int act_notid;
	private MemberBean member;
	private ActivityBean activity;
	private int act_read;
	private Timestamp act_time;

	public Timestamp getAct_time() {
		return act_time;
	}

	public void setAct_time(Timestamp act_time) {
		this.act_time = act_time;
	}

	public int getAct_notid() {
		return act_notid;
	}

	public void setAct_notid(int act_notid) {
		this.act_notid = act_notid;
	}

	public MemberBean getMember() {
		return member;
	}

	public void setMember(MemberBean member) {
		this.member = member;
	}

	public ActivityBean getActivity() {
		return activity;
	}

	public void setActivity(ActivityBean activity) {
		this.activity = activity;
	}

	public int getAct_read() {
		return act_read;
	}

	public void setAct_read(int act_read) {
		this.act_read = act_read;
	}

}
