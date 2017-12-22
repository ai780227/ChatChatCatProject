package com.ccc.model.service;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.MessageBean;
import com.ccc.model.dao.MemberDAO;
import com.ccc.model.dao.MessageDAO;
import com.ccc.model.service.event.ChatroomEvent;

public class ChatroomService implements ApplicationContextAware{

	private static ApplicationContext appContext = null;
	
	@Override
	public void setApplicationContext(ApplicationContext appContext)
			throws BeansException {
		// TODO Auto-generated method stub
		this.appContext = appContext;		
	}
	
	//傳送訊息
	public void sendMessage(MessageBean msg) {
		appContext.publishEvent(new ChatroomEvent(msg));
	}
	
	//加入聊天記錄到資料庫
	public MessageBean insertMessage(String mfrom_id, String mto_id, String content) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.config.xml");
		MessageDAO msgdao = (MessageDAO) ctx.getBean("MessageDAO");
		MemberDAO memdao = (MemberDAO) ctx.getBean("MemberDAO");
		MessageBean msgbean = new MessageBean();
		
		MemberBean mfrom = memdao.getByPrimaryKey(mfrom_id);
		MemberBean mto = memdao.getByPrimaryKey(mto_id);
		msgbean.setMember_from(mfrom);
		msgbean.setMember_to(mto);
		msgbean.setMsg_content(content);
		
		msgbean = msgdao.insert(msgbean);
		if(msgbean!=null)
			return msgbean;
		else
			return null;
	}
	
	//取得與某人之聊天記錄
	public List<MessageBean> getEachOtherMessage(String self_id, String other_id, int page) {
		String query = "FROM MessageBean "
					 + "WHERE m_id_from = '"+self_id + "' AND m_id_to = '" + other_id 
					 + "' OR m_id_from = '"+other_id + "' AND m_id_to = '" + self_id 
					 + "' ORDER BY msg_time DESC";
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.config.xml");
		MessageDAO msgdao = (MessageDAO) ctx.getBean("MessageDAO");
		List<MessageBean> msglist = msgdao.getMessageByMethodOnPage(query, page);
		if(msglist!=null)
			return msglist;
		
		return null;
	}
	
	public static void main(String[] arg0) {
		ChatroomService cs = new ChatroomService();
		List<MessageBean> msglist = cs.getEachOtherMessage("cecj001", "cecj003", 10);
		if(msglist!=null) {
			for(MessageBean msg : msglist) {
				System.out.println(msg.getMember_from().getM_id() + " to " + msg.getMember_to().getM_id() + ":" 
						+ msg.getMsg_content() + " , " + msg.getMsg_time());
			}			
		} else {
			System.out.println("error or empty");
		}
	}

}
