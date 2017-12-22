package com.ccc.model.dao.interfacedao;

import java.util.List;

import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;

public interface IPostDAO {
	public PostBean insert(PostBean pb);
	public PostBean update(PostBean pb);
	public PostBean delete(PostBean pb);
	public PostBean getByPrimaryKey(int post_id);
	public List<PostBean> getAll();

	public List<PostBean> getPostsByMemberBean(MemberBean mb);
	public List<PostBean> getPostsByType(int post_type);
	public List<PostBean> getPostsByProperty(int post_property);
	public List<PostBean> getPostsByPost_content(String post_content);
	public List<PostBean> getPostsByLike_count(int like_count);
	public List<PostBean> getPostsByActivityBean(ActivityBean actb);
	public List<PostBean> getPostsByMethod(String query);
	public List<PostBean> getPostsByMethodPerPage(String query, int page, int size);
}
