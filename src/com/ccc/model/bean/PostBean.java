package com.ccc.model.bean;

import java.util.HashSet;
import java.util.Set;

public class PostBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private int post_id;
	private int post_type;		// 1一般動態、2知識(醫療、飼養)、3貓咪店家、4活動
	private int post_property;	// 1所有、2好友、3封鎖
	private MemberBean memberBean;
	private java.sql.Timestamp post_time;
	private String post_content;
	private int like_count;
	private ActivityBean activityBean;
	private Set<ResponseBean> responses = new HashSet<ResponseBean>();
	private Set<FavoriteBean> favorites = new HashSet<FavoriteBean>();
	private Set<PostPictureBean> postPictures = new HashSet<PostPictureBean>();
	private Set<WhoLikeBean> whoLikes = new HashSet<WhoLikeBean>();
	private Set<ReportBean> reports = new HashSet<ReportBean>();

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public int getPost_type() {
		return post_type;
	}

	public void setPost_type(int post_type) {
		this.post_type = post_type;
	}

	public int getPost_property() {
		return post_property;
	}

	public void setPost_property(int post_property) {
		this.post_property = post_property;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}

	public java.sql.Timestamp getPost_time() {
		return post_time;
	}

	public void setPost_time(java.sql.Timestamp post_time) {
		this.post_time = post_time;
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

	public ActivityBean getActivityBean() {
		return activityBean;
	}

	public void setActivityBean(ActivityBean activityBean) {
		this.activityBean = activityBean;
	}

	// one-to-many ResponseBean
	public Set<ResponseBean> getResponses() {
		return responses;
	}

	public void setResponses(Set<ResponseBean> responses) {
		this.responses = responses;
	}

	// one-to-many FavoriteBean
	public Set<FavoriteBean> getFavorites() {
		return favorites;
	}

	public void setFavorites(Set<FavoriteBean> favorites) {
		this.favorites = favorites;
	}

	// one-to-many PostPictureBean
	public Set<PostPictureBean> getPostPictures() {
		return postPictures;
	}

	public void setPostPictures(Set<PostPictureBean> postPictures) {
		this.postPictures = postPictures;
	}

	// one-to-many WhoLikeBean public
	Set<WhoLikeBean> getWhoLikes() {
		return whoLikes;
	}

	public void setWhoLikes(Set<WhoLikeBean> whoLikes) {
		this.whoLikes = whoLikes;
	}

	// one-to-many ReportBean
	public Set<ReportBean> getReports() {
		return reports;
	}

	public void setReports(Set<ReportBean> reports) {
		this.reports = reports;
	}
}