package com.ccc.model.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class MemberBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String m_id;
	private byte[] m_pwd;
	private String m_name;
	private String m_intro;
	private String m_email;
	private java.sql.Date m_birth;
	private String m_sex;
	private String m_pic_path;
	private int m_authority;
	private java.sql.Timestamp m_block_time;
	private int m_violation_count;
	private int cf_birth;
	private int cf_email;
	private int cf_intro;
	private int cf_sex;
	private int cf_post;
	private int cf_res;
	private int cf_act;
	private int cf_theme;

	// 關聯 Member to Cat 一對多
	private Set<CatBean> cats = new HashSet<CatBean>();
	public Set<CatBean> getCats() {
		return cats;
	}
	public void setCats(Set<CatBean> cats) {
		this.cats = cats;
	}
	// 關聯 Member to Picture 一對多
	private Set<PictureBean> pictures = new HashSet<PictureBean>();
	public Set<PictureBean> getPictures() {
		return pictures;
	}
	public void setPictures(Set<PictureBean> pictures) {
		this.pictures = pictures;
	}

	private Set<ReportBean> reports;
	private Set<WarningNoticeBean> warningNotice;
	private Set<PostBean> posts;
	private Set<ActivityBean> activities;
	// 關聯 Member to Activity 一對多
	public Set<ActivityBean> getActivities() {
		return activities;
	}
	public void setActivities(Set<ActivityBean> activities) {
		this.activities = activities;
	}
	// 關聯 Member to Report 一對多
	public Set<ReportBean> getReports() {
		return reports;
	}
	public void setReports(Set<ReportBean> reports) {
		this.reports = reports;
	}
	// 關聯 Member to WarningNotice 一對多
	public Set<WarningNoticeBean> getWarningNotice() {
		return warningNotice;
	}
	public void setWarningNotice(Set<WarningNoticeBean> warningNotice) {
		this.warningNotice = warningNotice;
	}
	// 關聯 Member to Post 一對多
	public Set<PostBean> getPosts() {
		return posts;
	}
	public void setPosts(Set<PostBean> posts) {
		this.posts = posts;
	}

	// //////////////////////////////////////以下是晸芳的///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// =============================================================================
	private Set<WhoLikeBean> W_m_id;
	public Set<WhoLikeBean> getW_m_id() {
		return W_m_id;
	}
	public void setW_m_id(Set<WhoLikeBean> w_m_id) {
		W_m_id = w_m_id;
	}
	private Set<MessageBean> SMB_m_id_to; // 接收者
	private Set<MessageBean> SMB_m_id_from; // 發送者
	public Set<MessageBean> getSMB_m_id_from() {
		return SMB_m_id_from;
	}
	public void setSMB_m_id_from(Set<MessageBean> sMB_m_id_from) {
		SMB_m_id_from = sMB_m_id_from;
	}
	public Set<MessageBean> getSMB_m_id_to() {
		return SMB_m_id_to;
	}
	public void setSMB_m_id_to(Set<MessageBean> sMB_m_id_to) {
		SMB_m_id_to = sMB_m_id_to;
	}
	// =====================================FriendshipBean a/b
	// getter/setter==========================================================================================
	private Set<FriendshipBean> SFsB_id_a;
	private Set<FriendshipBean> SFsB_id_b;
	public void setFriendshipBean_a(Set<FriendshipBean> SFsB_id_a) {
		this.SFsB_id_a = SFsB_id_a;
	}
	public void setFriendshipBean_b(Set<FriendshipBean> SFsB_id_b) {
		this.SFsB_id_b = SFsB_id_b;
	}
	public Set<FriendshipBean> getFriendshipBean_a() {
		return this.SFsB_id_a;
	}
	public Set<FriendshipBean> getFriendshipBean_b() {
		return this.SFsB_id_b;
	}

	// ==========================================FriendshipNoticeBean
	// m_id_to/m_id_from
	// getter/setter====================================================================
	private Set<FriendshipNoticeBean> SFsNB_m_id_to;
	private Set<FriendshipNoticeBean> SFsNB_m_id_from;
	public void setFriendshipNoticeBean_m_id_to(
			Set<FriendshipNoticeBean> SFsNB_m_id_to) {
		this.SFsNB_m_id_to = SFsNB_m_id_to;
	}
	public void setFriendshipNoticeBean_m_id_from(
			Set<FriendshipNoticeBean> SFsNB_m_id_from) {
		this.SFsNB_m_id_from = SFsNB_m_id_from;
	}
	public Set<FriendshipNoticeBean> getFriendshipNoticeBean_m_id_to() {
		return this.SFsNB_m_id_to;
	}
	public Set<FriendshipNoticeBean> getFriendshipNoticeBean_m_id_from() {
		return this.SFsNB_m_id_from;
	}

	// ===================================================================================================================================================================

	// //////////////////////////////////////////以上是晸芳的///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	//以下是阿凱的
	private Set<ActivityNoticeBean> nots = new HashSet<ActivityNoticeBean>();
	private Set<ActivityParticipateBean> pars = new HashSet<ActivityParticipateBean>();
	public Set<ActivityNoticeBean> getNots() {
		return nots;
	}
	public void setNots(Set<ActivityNoticeBean> nots) {
		this.nots = nots;
	}
	public Set<ActivityParticipateBean> getPars() {
		return pars;
	}
	public void setPars(Set<ActivityParticipateBean> pars) {
		this.pars = pars;
	}
	
	//以上是阿凱的///////////////////
	
	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public byte[] getM_pwd() {
		return m_pwd;
	}

	public void setM_pwd(byte[] m_pwd) {
		this.m_pwd = m_pwd;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_intro() {
		return m_intro;
	}

	public void setM_intro(String m_intro) {
		this.m_intro = m_intro;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public java.sql.Date getM_birth() {
		return m_birth;
	}

	public void setM_birth(java.sql.Date m_birth) {
		this.m_birth = m_birth;
	}

	public String getM_sex() {
		return m_sex;
	}

	public void setM_sex(String m_sex) {
		this.m_sex = m_sex;
	}

	public String getM_pic_path() {
		return m_pic_path;
	}

	public void setM_pic_path(String m_pic_path) {
		this.m_pic_path = m_pic_path;
	}

	public int getM_authority() {
		return m_authority;
	}

	public void setM_authority(int m_authority) {
		this.m_authority = m_authority;
	}

	public java.sql.Timestamp getM_block_time() {
		return m_block_time;
	}

	public void setM_block_time(java.sql.Timestamp m_block_time) {
		this.m_block_time = m_block_time;
	}

	public int getM_violation_count() {
		return m_violation_count;
	}

	public void setM_violation_count(int m_violation_count) {
		this.m_violation_count = m_violation_count;
	}

	public int getCf_birth() {
		return cf_birth;
	}

	public void setCf_birth(int cf_birth) {
		this.cf_birth = cf_birth;
	}

	public int getCf_email() {
		return cf_email;
	}

	public void setCf_email(int cf_email) {
		this.cf_email = cf_email;
	}

	public int getCf_intro() {
		return cf_intro;
	}

	public void setCf_intro(int cf_intro) {
		this.cf_intro = cf_intro;
	}

	public int getCf_sex() {
		return cf_sex;
	}

	public void setCf_sex(int cf_sex) {
		this.cf_sex = cf_sex;
	}

	public int getCf_post() {
		return cf_post;
	}

	public void setCf_post(int cf_post) {
		this.cf_post = cf_post;
	}

	public int getCf_res() {
		return cf_res;
	}

	public void setCf_res(int cf_res) {
		this.cf_res = cf_res;
	}

	public int getCf_act() {
		return cf_act;
	}

	public void setCf_act(int cf_act) {
		this.cf_act = cf_act;
	}

	public int getCf_theme() {
		return cf_theme;
	}

	public void setCf_theme(int cf_theme) {
		this.cf_theme = cf_theme;
	}
}
