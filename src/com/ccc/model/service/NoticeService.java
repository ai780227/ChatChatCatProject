package com.ccc.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.ActivityNoticeBean;
import com.ccc.model.bean.ActivityParticipateBean;
import com.ccc.model.bean.FriendshipNoticeBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.bean.ResponseBean;
import com.ccc.model.bean.ResponseNoticeBean;
import com.ccc.model.bean.WarningNoticeBean;
import com.ccc.model.dao.ActivityDAO;
import com.ccc.model.dao.ActivityNoticeDAO;
import com.ccc.model.dao.ActivityParticipateDAO;
import com.ccc.model.dao.FriendshipNoticeDAO;
import com.ccc.model.dao.PostDAO;
import com.ccc.model.dao.ResponseDAO;
import com.ccc.model.dao.ResponseNoticeDAO;
import com.ccc.model.dao.WarningNoticeDAO;
import com.ccc.model.service.event.ActivityNoticeEvent;
import com.ccc.model.service.event.FriendshipNoticeEvent;
import com.ccc.model.service.event.ResponseNoticeEvent;
import com.ccc.model.service.event.WarningNoticeEvent;

public class NoticeService implements ApplicationContextAware{
	
	private static ApplicationContext appContext = null;

	@Override
	public void setApplicationContext(ApplicationContext appContext)
			throws BeansException {
//		System.out.println("NoticeService setApplicationContext");
		// TODO Auto-generated method stub
		this.appContext = appContext;
	}
	
///////////////////////////////////////加友通知///////////////////////////////////////	
	//送出加友通知給接收者
	private void sendAddFriendNotice(FriendshipNoticeBean fsNotice) {
			appContext.publishEvent(new FriendshipNoticeEvent(fsNotice));
	}

	//新增加友通知  傳入值：(寄件者MemberBean物件，收件者M_ID，加友通知類型)
	public FriendshipNoticeBean insertFriendshipNotice(MemberBean memFrom, String memTo_id, int Fri_type) {
		FriendshipNoticeBean fsnbean = new FriendshipNoticeBean();
		FriendshipNoticeDAO fsndao = (FriendshipNoticeDAO) appContext.getBean("FriendshipNoticeDAO");
		MemberBean memTo = new MemberBean();
		
		memTo.setM_id(memTo_id);
		fsnbean.setMember_from(memFrom);					//寄件者
		fsnbean.setM_name_from(memFrom.getM_name());		//寄件者名字
		fsnbean.setMember_to(memTo);						//收件者
		fsnbean.setFri_type(Fri_type);						//0=接收到加友通知, 1=對方已同意加友
		fsnbean.setFri_read(0);								//預設未讀
		
		fsnbean = fsndao.insert(fsnbean);
		//如果新增成功，再發送通知給接收者
		if(fsnbean!=null) {
			sendAddFriendNotice(fsnbean);
			return fsnbean;
		}
		return null;
	}
	
	//取得加友通知  傳入值：(使用者MemberBean)
	public List<FriendshipNoticeBean> getFriendshipNotice(MemberBean user) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.config.xml");
		if(appContext!=null) {
			FriendshipNoticeDAO fsndao = (FriendshipNoticeDAO) appContext.getBean("FriendshipNoticeDAO");
			if( fsndao != null && user != null) {
				List<FriendshipNoticeBean> fsNoticeList = fsndao.getFriendshipNoticeByUser(user);
				if(fsNoticeList!=null)
					return fsNoticeList;
			}			
		} else {
//			System.out.println("appContext is null");
		}
		return null;
	}
	
	//加友通知修改為已讀
	public List<FriendshipNoticeBean> changeReadFriendshipNotice(List<FriendshipNoticeBean> fsNoticeList) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.config.xml");
		FriendshipNoticeDAO fsndao = (FriendshipNoticeDAO) appContext.getBean("FriendshipNoticeDAO");
		for(int i=0 ; i < fsNoticeList.size() ; i++) {
			fsNoticeList.get(i).setFri_read(1);
			fsndao.update(fsNoticeList.get(i));
		}
		return fsNoticeList;
	}
	
	//刪除一筆加友通知
	public void deleteFriendshipNotice(FriendshipNoticeBean fsNotice) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.config.xml");
		FriendshipNoticeDAO fsNoticeDAO = (FriendshipNoticeDAO) appContext.getBean("FriendshipNoticeDAO");
		fsNoticeDAO.delete(fsNotice);
	}
	
/////////////////////////////////////////回覆通知///////////////////////////////////////	
	//送出回應通知給接收者
	private void sendResponseNotice(Set<String> midSet) {
			appContext.publishEvent(new ResponseNoticeEvent(midSet));
	}
	
	//新增多筆回覆通知  傳入值：(回覆物件ResponseBean)
	//通知對象不包括此回覆者
	public void insertResponseNotice(ResponseBean respBean) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.config.xml");
		ResponseNoticeDAO respNoticedao = (ResponseNoticeDAO) appContext.getBean("ResponseNoticeDAO");
		ResponseDAO respdao = (ResponseDAO) appContext.getBean("ResponseDAO");
		PostDAO postdao = (PostDAO) appContext.getBean("PostDAO");
		respBean = respdao.getByPrimaryKey(respBean.getRes_id());
		String responseM_id = respBean.getMemberBean().getM_id();			//取得回覆者ID
		int post_id = respBean.getPostBean().getPost_id();					//取得回覆的貼文的ID
		
		Set<String> midSet = new HashSet<String>();							//建立一放置"回覆通知對象ID"之Set
		
		//取得  應收到回覆通知的會員帳號Set
		PostBean postbean = postdao.getByPrimaryKey(post_id);				//取得貼文者的ID
		if( !postbean.getMemberBean().getM_id().equals(responseM_id) ) {	//如果"回覆者"非"貼文者"
			midSet.add(postbean.getMemberBean().getM_id());					//將貼文者的ID加入Set
			//將回覆通知insert到database
			ResponseNoticeBean respNoticeBean = 
					setResponseNoticeBean(respBean, postbean.getMemberBean());			
			respNoticedao.insert(respNoticeBean);
		}
		//取得該貼文中的所有回覆
		List<ResponseBean> respList = respdao.getResponsesByPostBean(postbean);		
		for(ResponseBean respBean2 : respList) {
			if( !respBean2.getMemberBean().getM_id().equals(responseM_id) ) {	//如果非"回覆者"
				//將回覆通知insert到database
				ResponseNoticeBean respNoticeBean = 
						setResponseNoticeBean(respBean, respBean2.getMemberBean());		
				if(!midSet.contains(respBean2.getMemberBean().getM_id()))		//如果Set中尚未加入"其餘之回覆貼文者的ID"
					respNoticedao.insert(respNoticeBean);						//insert到database
				midSet.add(respBean2.getMemberBean().getM_id());				//將其餘之回覆貼文者的ID加入Set			
			}
		}			
		//呼叫 推送回覆通知 的method
		if(midSet !=null)
			if(!midSet.isEmpty())
				sendResponseNotice(midSet);
		
//		for(String mid : midSet) {
//			System.out.println("received meber : " + mid);
//		}
	}
	
	//放資料到ResponseNoticeBean  傳入值：(回覆物件ResponseBean，接收者MemberBean)
	private ResponseNoticeBean setResponseNoticeBean(ResponseBean respBean, MemberBean receiver) {
		
		ResponseNoticeBean respNoticeBean = new ResponseNoticeBean();		
		respNoticeBean.setM_id_from(respBean.getMemberBean().getM_id());		//寄件者ID
		respNoticeBean.setM_name_from(respBean.getMemberBean().getM_name());	//寄件者名
		respNoticeBean.setM_id_to(receiver.getM_id());							//收件者ID
		respNoticeBean.setRes_read(0);											//未讀
		respNoticeBean.setResponseBean(respBean);								//回覆物件
		respNoticeBean.setPost_id(respBean.getPostBean().getPost_id());			//貼文ID
		
		return respNoticeBean;
	}
	
	
	//取得回覆通知  傳入值：(使用者MemberBean)
	public List<ResponseNoticeBean> getResponseNotice(MemberBean user) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.config.xml");
		ResponseNoticeDAO respNoticedao = (ResponseNoticeDAO) appContext.getBean("ResponseNoticeDAO");
		if(user != null) {
			if( respNoticedao != null) {
				List<ResponseNoticeBean> respNoticeList = respNoticedao.getResponseNoticeByM_id_to(user.getM_id());
				if(respNoticeList!=null)
					return respNoticeList;
			}				
		}	
		return null;
	}
	
	//回覆通知修改為已讀
	public List<ResponseNoticeBean> changeReadResponseNotice(List<ResponseNoticeBean> respNoticeList) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.config.xml");
		ResponseNoticeDAO respNoticeDAO = (ResponseNoticeDAO) appContext.getBean("ResponseNoticeDAO");
		for(int i=0 ; i < respNoticeList.size() ; i++) {
			respNoticeList.get(i).setRes_read(1);
			respNoticeDAO.update(respNoticeList.get(i));
		}
		return respNoticeList;
	}
	
	//刪除一筆回覆通知
	public void deleteResponseNotice(ResponseNoticeBean respNotice) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.config.xml");
		ResponseNoticeDAO respNoticeDAO = (ResponseNoticeDAO) appContext.getBean("ResponseNoticeDAO");
		respNoticeDAO.delete(respNotice);
	}
		
/////////////////////////////////////////活動通知///////////////////////////////////////
	//送出活動通知給接收者
	private void sendActivityNotice(Set<String> midSet) {
		appContext.publishEvent(new ActivityNoticeEvent(midSet));
	}
	
	//加入多筆活動通知  傳入值：(活動 ActivityBean)
	public void insertActivityNotice(ActivityBean actBean) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.config.xml");
		ActivityNoticeDAO actNoticeDAO = (ActivityNoticeDAO) appContext.getBean("ActivityNoticeDAO");
		ActivityDAO actdao = (ActivityDAO) appContext.getBean("ActivityDAO");
		ActivityParticipateDAO apdao = (ActivityParticipateDAO) appContext.getBean("ActivityParticipateDAO");
		
		String query = "from ActivityParticipateBean where act_id='" + actBean.getAct_id() + "'";
		List<ActivityParticipateBean> apList = apdao.getActivityParticipateByMethod(query);			//取得該活動之所有受邀人
		
//		System.out.println(apList.get(0).getMember().getM_id());
		
		Set<String> midSet =  new HashSet<String>();									//建立一放置"回覆通知對象ID"之Set		
		if(apList!=null) {
			for(ActivityParticipateBean apBean : apList) {				//將該活動之受邀人Member ID 加入Set
				if(apBean.getMember()!=null) {
					if(! apBean.getMember().getM_id().equals(actBean.getMember().getM_id())) {		//如果此受邀人不為活動創辦人，才送出通知
						ActivityNoticeBean actNoticeBean = new ActivityNoticeBean();
						actNoticeBean.setMember(apBean.getMember());					//加入接收者
						actNoticeBean.setActivity(apBean.getActivity());				//加入活動
						actNoticeBean.setAct_read(0);									//預設未讀
						actNoticeBean.setAct_time(new java.sql.Timestamp(System.currentTimeMillis()));	//加入現在時間
						actNoticeBean = actNoticeDAO.insert(actNoticeBean);				//將活動通知insert入database
						
						if(actNoticeBean!=null)						
							midSet.add(apBean.getMember().getM_id());						
					}
				}
			}			
		}
		if(midSet !=null)
			if(!midSet.isEmpty())						//若受邀人不為空
				sendActivityNotice(midSet);				//推送活動通知
//		for(String mid : midSet) {
//			System.out.println("received member : " + mid);
//		}
	}
	
	//取得活動通知  傳入值：(使用者MemberBean)
	public List<ActivityNoticeBean> getActivityNotice(MemberBean user) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.config.xml");
		ActivityNoticeDAO actNoticeDAO = (ActivityNoticeDAO) appContext.getBean("ActivityNoticeDAO");
		if(user!=null) {
			String query = "from ActivityNoticeBean where m_id='" + user.getM_id() + "' order by act_time";
			List<ActivityNoticeBean> actNoticeList = actNoticeDAO.getActivityNoticeByMethod(query);		
			return actNoticeList;			
		}
		return null;
	}
	
	//活動通知修改為已讀
	public List<ActivityNoticeBean> changeReadActivityNotice(List<ActivityNoticeBean> actNoticeList) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.config.xml");
		ActivityNoticeDAO actNoticeDAO = (ActivityNoticeDAO) appContext.getBean("ActivityNoticeDAO");
		for(int i=0 ; i < actNoticeList.size() ; i++) {
			actNoticeList.get(i).setAct_read(1);
			actNoticeDAO.update(actNoticeList.get(i));
		}
		return actNoticeList;
	}
	
	//清空"該使用者"對"某活動"的所有通知
	public void clearActivityNotice(MemberBean user, ActivityBean actBean) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.config.xml");
		ActivityNoticeDAO actNoticeDAO = (ActivityNoticeDAO) appContext.getBean("ActivityNoticeDAO");
		List<ActivityNoticeBean> actNoticeList = getActivityNotice(user);	//取得"該使用者"對"某活動"的所有通知
		if(actNoticeList!=null) {
			for(int i=0; i < actNoticeList.size(); i++) {
				actNoticeDAO.delete(actNoticeList.get(i));
			}			
		}
	}
	
	//刪除一筆活動通知
	public void deleteActivityNotice(ActivityNoticeBean actNotice) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.config.xml");
		ActivityNoticeDAO actNoticeDAO = (ActivityNoticeDAO) appContext.getBean("ActivityNoticeDAO");
		actNoticeDAO.delete(actNotice);
	}
/////////////////////////////////////////被警告通知///////////////////////////////////////
	//送出被警告通知給接收者
	private void sendWarningNoticeEvent(WarningNoticeBean warNoticeBean) {
		appContext.publishEvent(new WarningNoticeEvent(warNoticeBean));
	}
	
	//加入被警告通知  傳入值：(被封鎖的貼文 PostBean， 被檢舉原因)
	public WarningNoticeBean insertWarningNotice(PostBean postBean, String cause) {
//		System.out.println("in insertWarningNotice");
		ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.config.xml");
		WarningNoticeDAO warNoticeDAO = (WarningNoticeDAO) appContext.getBean("WarningNoticeDAO");
		WarningNoticeBean warNotice = new WarningNoticeBean();
		if(postBean.getActivityBean()!=null)
			warNotice.setActivity(postBean.getActivityBean());		//複製 活動id，可為null
		warNotice.setLike_count(postBean.getLike_count());			//複製 讚總數
		warNotice.setMember(postBean.getMemberBean());				//複製 貼文者/警告通知接收者 id
		warNotice.setPost_content(postBean.getPost_content());		//複製 內文
		warNotice.setPost_property(postBean.getPost_property());	//複製 貼文公開性質(公開、好友)
		warNotice.setPost_time( postBean.getPost_time());			//複製 貼文時間
		warNotice.setPost_type(postBean.getPost_type());			//複製 貼文類型(一般、知識、所有貼文、...)
		warNotice.setRep_cause(cause);								//加入被檢舉原因
		warNotice.setWar_read(0);									//通知預設未讀 (0未讀，1已讀)
		warNotice = warNoticeDAO.insert(warNotice);
		//若新增通知成功，再推送通知給接收者
		if(warNotice!=null) {
			sendWarningNoticeEvent(warNotice);
			return warNotice;
		}
		return null;
	}
	
	//取得被警告通知  傳入值：(使用者MemberBean)
	public List<WarningNoticeBean> getWarningNotice(MemberBean user) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.config.xml");
		WarningNoticeDAO warNoticeDAO = (WarningNoticeDAO) appContext.getBean("WarningNoticeDAO");
		if(user!=null) {
			List<WarningNoticeBean> warNoticeList = warNoticeDAO.getByMember(user);
			return warNoticeList;			
		}
		return null;
	}
	
	//被警告通知修改為已讀
	public List<WarningNoticeBean> changeReadWarningNotice(List<WarningNoticeBean> warNoticeList) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.config.xml");
		WarningNoticeDAO warNoticeDAO = (WarningNoticeDAO) appContext.getBean("WarningNoticeDAO");
		for(int i=0 ; i < warNoticeList.size() ; i++) {
			warNoticeList.get(i).setWar_read(1);
			warNoticeDAO.update(warNoticeList.get(i));
		}
		return warNoticeList;
	}
	
	//刪除一筆被警告通知
	public void deleteWarningNotice(WarningNoticeBean warNotice) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.config.xml");
		WarningNoticeDAO warNoticeDAO = (WarningNoticeDAO) appContext.getBean("WarningNoticeDAO");
		warNoticeDAO.delete(warNotice);
	}
	
	
/////////////////////////////////////////取得所有通知之未讀個數///////////////////////////////////////
	public Map<String, Integer> getUnreadNoticeCount(MemberBean user) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.config.xml");
		
		int fsNoticeUnreadCount = 0;
		int respNoticeUnreadCount = 0;
		int actNoticeUnreadCount = 0;
		int warNoticeUnreadCount = 0;
		
		if(user != null) {
			List<FriendshipNoticeBean> fsNoticeList = getFriendshipNotice(user);
			List<ResponseNoticeBean> respNoticeList = getResponseNotice(user);
			List<ActivityNoticeBean> actNoticeList = getActivityNotice(user);
			List<WarningNoticeBean> warNoticeList = getWarningNotice(user);		
			
			for(FriendshipNoticeBean fsNoticeBean : fsNoticeList)
				if(fsNoticeBean.getFri_read() == 0)
					fsNoticeUnreadCount++;
			for(ResponseNoticeBean respNoticeBean : respNoticeList)
				if(respNoticeBean.getRes_read() == 0)
					respNoticeUnreadCount++;
			for(ActivityNoticeBean actNoticeBean : actNoticeList)
				if(actNoticeBean.getAct_read() == 0)
					actNoticeUnreadCount++;
			for(WarningNoticeBean warNoticeBean : warNoticeList)
				if(warNoticeBean.getWar_read() == 0)
					warNoticeUnreadCount++;			
		}
		
		Map<String, Integer> unreadNoticeMap = new HashMap<String, Integer>();
		unreadNoticeMap.put("fsNoticeUnreadCount", fsNoticeUnreadCount);
		unreadNoticeMap.put("respNoticeUnreadCount", respNoticeUnreadCount);
		unreadNoticeMap.put("actNoticeUnreadCount", actNoticeUnreadCount);
		unreadNoticeMap.put("warNoticeUnreadCount", warNoticeUnreadCount);
		
		return unreadNoticeMap;
	}
	
///////////////////////////////////////Main///////////////////////////////////////
	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.config.xml");
		NoticeService noticeServ = new NoticeService();
		MemberBean mb = new MemberBean();
//取得加友通知
//		mb.setM_id("cecj003");
//		List<FriendshipNoticeBean> fsNoticeList = noticeServ.getFriendshipNotice(mb);
//		if(fsNoticeList!=null) {
//			for(FriendshipNoticeBean fsnbean : fsNoticeList) {
//				System.out.println("FriendshipNotice - " + fsnbean.getMember_from().getM_id() + " " 
//									+ fsnbean.getM_name_from() + " to " + fsnbean.getMember_to().getM_id() );
//			}			
//		}
//測試"修改為已讀"
//		noticeServ.changeReadFriendshipNotice(fsNoticeList);
		
		
//取得回覆通知
//		mb.setM_id("cecj001");
//		List<ResponseNoticeBean> respNoticeList = noticeServ.getResponseNotice(mb);
//		if(respNoticeList!=null) {
//			for(ResponseNoticeBean respbean : respNoticeList) {
//				System.out.println("ResponseNotice - " + respbean.getM_id_from() + " " 
//									+ respbean.getM_name_from() + " to " + respbean.getM_id_to() );
//			}			
//		}	
//測試"修改為已讀"
//		noticeServ.changeReadResponseNotice(respNoticeList);
		
//假設已新增一筆回覆- res_id=1
//		ResponseDAO respdao = (ResponseDAO) appContext.getBean("ResponseDAO");
//		ResponseBean respBean = respdao.getByPrimaryKey(1);
//		noticeServ.insertResponseNotice(respBean);
		
//測試塞入多筆活動通知到database
//		ActivityDAO actdao = (ActivityDAO) appContext.getBean("ActivityDAO");
//		ActivityBean actBean = actdao.getByPrimaryKey(1);
//		noticeServ.insertActivityNotice(actBean);
		
//測試取得某人的活動通知
//		mb.setM_id("cecj003");
//		List<ActivityNoticeBean> actNoticeList = noticeServ.getActivityNotice(mb);
//		if(!actNoticeList.isEmpty()) {
//			for(ActivityNoticeBean actNoticeBean : actNoticeList) {
//				System.out.println("ActivityNotice - From '" + actNoticeBean.getActivity().getAct_id() + "' to" 
//									+ actNoticeBean.getMember().getM_id() + " , at " + actNoticeBean.getAct_time().toString() );
//			}			
//		}
//測試"修改為已讀"
//		noticeServ.changeReadActivityNotice(actNoticeList);
		
//測試取得某人的被警告通知
//		mb.setM_id("cecj001");
//		List<WarningNoticeBean> warNoticeList = noticeServ.getWarningNotice(mb);
//		if(warNoticeList!=null) {
//			for(WarningNoticeBean warNoticeBean : warNoticeList) {
//				System.out.println("WarningNotice to " + warNoticeBean.getMember().getM_id() + ",because : " + warNoticeBean.getRep_cause());
//				System.out.println("      content : " + warNoticeBean.getPost_content());
//			}
//		}
//測試"修改為已讀"
//		noticeServ.changeReadWarningNotice(warNoticeList);		
	}

}
