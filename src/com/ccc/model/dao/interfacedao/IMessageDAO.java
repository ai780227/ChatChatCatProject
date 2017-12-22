package com.ccc.model.dao.interfacedao;


import java.util.List;

import com.ccc.model.bean.MessageBean;

public interface IMessageDAO {
	public MessageBean insert(MessageBean mB);
	public MessageBean update(MessageBean mB);
	public MessageBean delete(MessageBean mB);
	public MessageBean getByPrimaryKey(int msg_id);
	public List<MessageBean> getAll();
	
	public List<MessageBean> getMessageByMethod(String query);
	//public List<FriendshipBean> getMembersByName(String name);
	//public List<FriendshipBean> getMembersByEmail(String email);
	//public List<FriendshipBean> getMembersByMethod(String query);
}
