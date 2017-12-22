package com.ccc.model.dao.interfacedao;

import java.util.List;

import com.ccc.model.bean.FriendshipBean;
import com.ccc.model.bean.MemberBean;

public interface IFriendshipDAO {
	public FriendshipBean insert(FriendshipBean fsB);
	public FriendshipBean update(FriendshipBean fsB);
	public FriendshipBean delete(FriendshipBean fsB);
	public FriendshipBean getByPrimaryKey(String m_id_a,String m_id_b);
	
	
	public List<FriendshipBean> getAll();
	
	
	//public List<FriendshipBean> getMembersByName(String name);
	//public List<FriendshipBean> getMembersByEmail(String email);
	//public List<FriendshipBean> getMembersByMethod(String query);
	
}
