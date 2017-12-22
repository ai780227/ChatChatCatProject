package com.ccc.model.dao.interfacedao;


import java.util.List;




import com.ccc.model.bean.WhoLikeBean;

public interface IWhoLikeDAO {
	public WhoLikeBean insert(WhoLikeBean WlB);
	public WhoLikeBean update(WhoLikeBean WlB);
	public WhoLikeBean delete(WhoLikeBean WlB);
	public WhoLikeBean getByPrimaryKey(int post_id,String m_id);
	public List<WhoLikeBean> getAll();
	public List<WhoLikeBean> getByPostId(int post_id);

	//public List<FriendshipBean> getMembersByName(String name);
	//public List<FriendshipBean> getMembersByEmail(String email);
	//public List<FriendshipBean> getMembersByMethod(String query);
}
