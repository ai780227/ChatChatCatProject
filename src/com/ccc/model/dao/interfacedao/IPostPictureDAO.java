package com.ccc.model.dao.interfacedao;

import java.util.List;

import com.ccc.model.bean.PostPictureBean;

public interface IPostPictureDAO {
	public PostPictureBean insert(PostPictureBean postpic);
//	public PostPictureBean update(PostPictureBean postpic);
	public PostPictureBean delete(PostPictureBean postpic);
	public PostPictureBean getByPrimaryKey(int post_id, int pic_id);
	public List<PostPictureBean> getAll();	
	
	public List<PostPictureBean> getByPicId(int pic_id);
	public List<PostPictureBean> getByPostId(int post_id);
	public List<PostPictureBean> getPicturesByMethod(String query);	
}
