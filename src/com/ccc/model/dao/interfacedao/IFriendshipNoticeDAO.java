package com.ccc.model.dao.interfacedao;

import java.util.List;


import com.ccc.model.bean.FriendshipNoticeBean;


public interface IFriendshipNoticeDAO {
	public FriendshipNoticeBean insert(FriendshipNoticeBean fsNB);
	public FriendshipNoticeBean update(FriendshipNoticeBean fsNB);
	public FriendshipNoticeBean delete(FriendshipNoticeBean fsNB);
	public FriendshipNoticeBean getByPrimaryKey(int fri_notid);
	public List<FriendshipNoticeBean> getAll();
	
	
	//public List<FriendshipBean> getMembersByName(String name);
	//public List<FriendshipBean> getMembersByEmail(String email);
	//public List<FriendshipBean> getMembersByMethod(String query);
}
