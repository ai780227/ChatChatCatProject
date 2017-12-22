package com.ccc.model.bean;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;



public class ActivityParticipateBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private ActivityBean activity;
	private MemberBean member;
	private int act_participate;

	public ActivityBean getActivity() {
		return activity;
	}

	public void setActivity(ActivityBean activity) {
		this.activity = activity;
	}

	public MemberBean getMember() {
		return member;
	}

	public void setMember(MemberBean member) {
		this.member = member;
	}

	public int getAct_participate() {
		return act_participate;
	}

	public void setAct_participate(int act_participate) {
		this.act_participate = act_participate;
	}

	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof FriendshipBean)) {
			return false;
		}

		ActivityParticipateBean user = (ActivityParticipateBean) obj;
		return new EqualsBuilder().append(this.activity, user.getActivity())
				.append(this.member, user.getMember()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(this.activity).append(this.member)
				.toHashCode();
	}

}
