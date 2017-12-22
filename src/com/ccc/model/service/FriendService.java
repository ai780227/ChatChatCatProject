package com.ccc.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.CatBean;
import com.ccc.model.bean.FriendshipBean;
import com.ccc.model.bean.FriendshipNoticeBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PictureBean;
import com.ccc.model.dao.CatDAO;
import com.ccc.model.dao.FriendshipDAO;
import com.ccc.model.dao.FriendshipNoticeDAO;
import com.ccc.model.dao.MemberDAO;
import com.ccc.model.dao.PictureDAO;

public class FriendService {
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"beans.config.xml");

	// 加入好友功能
	// 加好友途徑 好友貼文、好友個人版面
	// 傳入自己MemberBean 與 要欲加好友MemberBean
	public FriendshipBean addFriend(MemberBean member_me, String member_to) {
		// 建立FriendshipDAO
		FriendshipDAO friendship_DAO = (FriendshipDAO) context
				.getBean("FriendshipDAO");
		MemberDAO member_DAO = (MemberDAO) context.getBean("MemberDAO");
		// 好友的Bean
		MemberBean memberBean_to = member_DAO.getByPrimaryKey(member_to);
		// friendshipBean_YN 判斷是否已經加入好友內，對方可能尚未回應的情況
		FriendshipBean friendshipBean_YN = friendship_DAO.getByPrimaryKey(
				member_me.getM_id(), member_to);
		//代表已經加對方為好友了
		if (friendshipBean_YN != null) {
			if(friendshipBean_YN.getFriend_check()==0){
				friendshipBean_YN.setFriend_check(1);
				friendship_DAO.update(friendshipBean_YN);
				return friendshipBean_YN;
			}
			return null;
		} else {
			// 判斷自己的MemberBean與好友的MemberBean 是否存在
			if (member_me != null && member_to != null) {
				// 建立FriendshipBean
				FriendshipBean friendshipBean_from = new FriendshipBean();
				FriendshipBean friendshipBean_to = new FriendshipBean();
				// 建立回傳的FriendshipBean
				FriendshipBean refriendshipBean_from = new FriendshipBean();
				FriendshipBean refriendshipBean_to = new FriendshipBean();
				// 將自己MemberBean 放入FriendshipBean
				// 將欲加入好友MemberBean 放入FriendshipBean
				friendshipBean_from.setMemberBean_a(member_me);
				friendshipBean_from.setMemberBean_b(memberBean_to);
				friendshipBean_from.setFriend_check(1);
				// 執行DAO，並且把回傳物件放入refriendshipBean
				refriendshipBean_from = friendship_DAO.insert(friendshipBean_from);// 呼叫DAO加入會員
				friendshipBean_to.setMemberBean_a(memberBean_to);
				friendshipBean_to.setMemberBean_b(member_me);
				friendshipBean_to.setFriend_check(0);
				refriendshipBean_to = friendship_DAO.insert(friendshipBean_to);// 呼叫DAO加入會員
				// 判斷是否加入成功
				if (refriendshipBean_from == null && refriendshipBean_to==null) {
					return null;
				} else {
					return refriendshipBean_from;
				}
			} else {
				return null;
			}
		}

	}

	// 封鎖好友，解除封鎖 V2
	public List<String> blockadeFriend(MemberBean memberBean_me,
			String member_from, int friend_type) {
//		System.out.println("memberBean_me:"+memberBean_me.getM_id());
//		System.out.println("member_from:"+member_from);
//		System.out.println("friend_type:"+friend_type);
		List<String> remember_list = new ArrayList<String>();
		// 建立FriendshipDAO
		FriendshipDAO friendship_DAO = (FriendshipDAO) context
				.getBean("FriendshipDAO");
		//取得我的好友(雙方同意者)、封鎖的好友
		String HQL="from FriendshipBean where m_id_a='"+memberBean_me.getM_id()+"'";
		List<FriendshipBean> friendshipBean_list = friendship_DAO.getFriendsByMethod(HQL);
//		System.out.println("friendshipBean_list.size:"+friendshipBean_list.size());
		// 判斷自己MemberBean 與 好友MemberBean 是否存在 或者封鎖對方
		if(!friendshipBean_list.isEmpty()){
			for (int i = 0; i < friendshipBean_list.size(); i++) {
				FriendshipBean friednshipBean = friendshipBean_list.get(i);
				String friend_member_id = friednshipBean.getMemberBean_b()
						.getM_id();
//				System.out.println("friend_member_id:" + friend_member_id);
//				System.out.println("member_from:" + member_from);
				if (friend_member_id.equals(member_from)) {
					friednshipBean.setFriend_check(friend_type);
					friendship_DAO.update(friednshipBean); // 更新資訊
					remember_list.add(memberBean_me.getM_id());
					remember_list.add(member_from);

				} 
			}
			return remember_list;
		}else{
			return null;
		}
	}

	// 回應邀請
	// 取好友關係
	// member_to 邀請我的人
	// member_from 我
//	public FriendshipBean respondInvite(MemberBean memberBean_from,
//			String member_to, int fri_type) {
//		FriendshipDAO friendship_DAO = (FriendshipDAO) context
//				.getBean("FriendshipDAO");
//		FriendshipNoticeDAO friendshipnotice_DAO = (FriendshipNoticeDAO) context
//				.getBean("FriendshipNoticeDAO");
//		MemberDAO member_DAO = (MemberDAO) context.getBean("MemberDAO");
//		try {
//			if (fri_type == 1) { // 接受邀請好友
//				// 取的邀請我的人Bean
//				MemberBean memberBean_to = member_DAO
//						.getByPrimaryKey(member_to); // 發送者
//				// 建立新的好友關係Bean
//				FriendshipBean friendshipBean = new FriendshipBean();
//				// 將我放入a
//				friendshipBean.setMemberBean_a(memberBean_from);
//				// 將邀請我的放入b
//				friendshipBean.setMemberBean_b(memberBean_to);
//				friendshipBean.setFriend_check(fri_type);
//				FriendshipBean refriendshipBean = friendship_DAO.update(friendshipBean);
//				//String HQL="from FriendshipNotice where m_id_from='cecj005' and m_id_to='cecj002'";
//				//friendshipnotice_DAO.getFriendshipNoticeByMethod(HQL);
//				return refriendshipBean;
//			} else {
//				// 取的邀請我的人Bean
//				MemberBean memberBean_to = member_DAO
//						.getByPrimaryKey(member_to); // 發送者
//				// 取得我自己的ID
//				String member_from = memberBean_from.getM_id();
//				// 取得好友關係 邀請人>我
//				FriendshipBean friendshipBean_from = friendship_DAO.getByPrimaryKey(
//						member_to, member_from);
//				FriendshipBean friendshipBean_to = friendship_DAO.getByPrimaryKey(
//						member_from, member_to);
//				// 刪除好友關係 邀請人>我
//				FriendshipBean reFriendshipBean = friendship_DAO
//						.delete(friendshipBean_from);
//				friendship_DAO.delete(friendshipBean_to);
//				return reFriendshipBean;
//			}
//		} catch (Exception e) {
//			return null;
//		}
//
//	}
	
	//======================回應邀請V2======================================================================
	//memberBean_from使用者  member_to發送者
	public Map<String,Object> respondInvite(MemberBean memberBean_from,
			 int fri_type,int fri_notid){
		FriendshipDAO friendship_DAO = (FriendshipDAO) context
				.getBean("FriendshipDAO");
		FriendshipNoticeDAO friendshipnotice_DAO = (FriendshipNoticeDAO) context
				.getBean("FriendshipNoticeDAO");
		MemberDAO member_DAO = (MemberDAO) context.getBean("MemberDAO");
		Map<String, Object> fri_bean_notice_Map = new HashMap<String, Object>();
					
		if (fri_type == 1) { //接受好友邀清
			FriendshipNoticeBean refriendshipNotice = friendshipnotice_DAO.getByPrimaryKey(fri_notid);
			fri_bean_notice_Map.put("friendshipNotice", refriendshipNotice);
			//取得發送者的MemberBean
			MemberBean memberBean_to = member_DAO
					.getByPrimaryKey(refriendshipNotice.getMember_from().getM_id()); 
			//建立好友關係物件=>呼叫DAO使用與回傳
			FriendshipBean friendshipBean = new FriendshipBean();
			//使用者
			friendshipBean.setMemberBean_a(memberBean_from);
			//邀請人
			friendshipBean.setMemberBean_b(memberBean_to);
			//關係
			friendshipBean.setFriend_check(fri_type);
			//更新資料庫的好友關係
			FriendshipBean refriendshipBean = friendship_DAO.update(friendshipBean);
			fri_bean_notice_Map.put("friendshipBean", refriendshipBean);

			return fri_bean_notice_Map;
			//.......................
			
		}else if(fri_type == 0){ //拒絕好友邀請
			//else if(fri_type==0){.......}
			//取得邀請人的MemberBean
			FriendshipNoticeBean refriendshipNotice = friendshipnotice_DAO.getByPrimaryKey(fri_notid);
			fri_bean_notice_Map.put("friendshipNotice", refriendshipNotice);
			MemberBean memberBean_to = member_DAO
					.getByPrimaryKey(refriendshipNotice.getMember_from().getM_id()); 
			// 取得使用者ID
			String member_from = memberBean_from.getM_id();
			// 取得好友關係 邀請人>使用者
			FriendshipBean friendshipBean_from = friendship_DAO.getByPrimaryKey(
					memberBean_to.getM_id(), member_from);
//			System.out.println("=========================測試===========================");
//			System.out.println(friendshipBean_from.getMemberBean_a().getM_id());
//			System.out.println(friendshipBean_from.getMemberBean_b().getM_id());
//			System.out.println(friendshipBean_from.getFriend_check());
//			System.out.println("=========================測試===========================");
			//取得好友關係  使用者>邀請人
			FriendshipBean friendshipBean_to = friendship_DAO.getByPrimaryKey(
					member_from, memberBean_to.getM_id());
			// 刪除好友關係 邀請人>使用者
			FriendshipBean reFriendshipBean = friendship_DAO
					.delete(friendshipBean_from);
			//刪除好友關係 使用者>邀請人
			friendship_DAO.delete(friendshipBean_to);
			fri_bean_notice_Map.put("friendshipBean", reFriendshipBean);
			return fri_bean_notice_Map;
		}else{
			FriendshipNoticeBean refriendshipNotice = friendshipnotice_DAO.getByPrimaryKey(fri_notid);
			if(refriendshipNotice!=null){
				fri_bean_notice_Map.put("friendshipNotice", refriendshipNotice);
				return fri_bean_notice_Map;
			}else{
				return null;	
			}
			
		}
	}
	
	
	
	
	
	//====================瀏覽他人個版V2=====================================================================
	public Map<String, Object> viewPersonalPage(MemberBean memberBean_from,String member_to){
		//回傳物件
		Map<String, Object> member_map = new HashMap<String, Object>();
		MemberDAO member_DAO = (MemberDAO) context.getBean("MemberDAO");
		FriendshipDAO friendship_DAO = (FriendshipDAO) context.getBean("FriendshipDAO");
		PictureDAO picture_DAO=(PictureDAO)context.getBean("PictureDAO");
		CatDAO cat_DAO=(CatDAO)context.getBean("CatDAO");
		//取得好友的MemberBean
		MemberBean memberBean_to = member_DAO.getByPrimaryKey(member_to);
		//判斷對方是否與我為好友
		Boolean isMyfriend=false;
		int friend_check=0;
		//取得我的好友(我與對方都同意的好友)與被我封鎖的好友
		String HQL="from FriendshipBean where m_id_a='"+memberBean_from.getM_id()+"'";
		//取得好友(我與對方都同意的好友)與被我封鎖的好友
		String HQL2="from FriendshipBean where m_id_a='"+member_to+"'";
		
		List<FriendshipBean> friendshipBean_list=friendship_DAO.getFriendsByMethod(HQL);
		List<FriendshipBean> friendshipBean_list_to=friendship_DAO.getFriendsByMethod(HQL2);
		//System.out.println("friendshipBean_list:"+friendshipBean_list.size());
		//System.out.println("friendshipBean_list_to:"+friendshipBean_list_to.size());
		//判斷是否為空值
		if(!friendshipBean_list.isEmpty() && !friendshipBean_list_to.isEmpty()){ 
			for(int i=0;i<friendshipBean_list.size();i++){
				for(int j =0;j<friendshipBean_list_to.size();j++){
					//System.out.println("friendshipBean_list_to.get(j).getMemberBean_b().getM_id():"+friendshipBean_list_to.get(j).getMemberBean_b().getM_id());
					if(friendshipBean_list.get(i).getMemberBean_b().getM_id().equals(member_to)){
						isMyfriend=true;
					}
					if (friendshipBean_list_to.get(j).getMemberBean_b().getM_id().equals(memberBean_from.getM_id())) {
						friend_check=friendshipBean_list_to.get(j).getFriend_check();
						//System.out.println("friend_check:"+friend_check);
					}
				}
				
			}
				
			
		}
			
		
		//放入好友會員ID
		member_map.put("m_id", memberBean_to.getM_id());
		//放入好友名字
		member_map.put("m_name", memberBean_to.getM_name());
		//放入大頭照
		member_map.put("m_pic_path", memberBean_to.getM_pic_path());
		//取得好友的所有照片getPicturesByC_id int c_id
		//取得該ID的所有貓資訊
		List<CatBean> catBean_list=cat_DAO.getCatsByM_id(member_to);
//		System.out.println("catBean_list.size():"+catBean_list.size());
		member_map.put("m_cats",catBean_list);
		//建立回傳物件
		/*List<PictureBean> recatPictureBean_list =new ArrayList<PictureBean>(); 
		
		System.out.println();
		for(int i=0;i<catBean_list.size();i++){
			//取的該隻貓的所有照片
			List<PictureBean> catPictureBean_list=picture_DAO.getPicturesByC_id(catBean_list.get(i).getC_id());
			//recatPictureBean_list.add(e)
		}*/
		
		//取的好友的所有照片(不包括貓這片)
		List<PictureBean> pictureBean_list = picture_DAO.getPicturesByM_id(memberBean_to.getM_id());
		//放入所有照片
		member_map.put("m_pics",pictureBean_list);
		//放入整個memberBean_to
		//............................................................
		//判斷是否公開 0)不公開 1)公開 2)好友
		if (memberBean_to.getCf_birth() == 1) {
			if(memberBean_to.getM_birth() != null)
				member_map.put("m_birth", memberBean_to.getM_birth().toString());
			else
				member_map.put("m_birth", null);
		//只對好友顯示
		}else if (memberBean_to.getCf_birth() == 2){
			if(isMyfriend && friend_check==1){
				member_map.put("m_birth", memberBean_to.getM_birth().toString());
			}	
		}
		if (memberBean_to.getCf_email() == 1) {
			member_map.put("m_email", memberBean_to.getM_email());	
		}else if (memberBean_to.getCf_email() == 2) {
			//System.out.println("memberBean_to.getCf_email():"+memberBean_to.getCf_email());
			//System.out.println("isMyfriend:"+isMyfriend);
			if(isMyfriend && friend_check==1){
				member_map.put("m_email", memberBean_to.getM_email());
			}
		}
		// 他人簡介顯示
		if (memberBean_to.getCf_intro() == 1) {
			member_map.put("m_intro", memberBean_to.getM_intro());
		}else if (memberBean_to.getCf_intro() == 2) {
			if(isMyfriend && friend_check==1){
				member_map.put("m_intro", memberBean_to.getM_intro());
			}
		}
		// 他人性別顯示
		if (memberBean_to.getCf_sex() == 1) {
			member_map.put("m_sex", memberBean_to.getM_sex());
		} else if (memberBean_to.getCf_sex() == 2) {
			if(isMyfriend && friend_check==1){
				member_map.put("m_sex", memberBean_to.getM_sex());
			}
		}
		int x = 0;
		int y = 0;
//		System.out.println("friendshipBean_list:"+friendshipBean_list.size());
//		System.out.println("friendshipBean_list_to.size():"+friendshipBean_list_to.size());
		for (int i = 0; i < friendshipBean_list.size(); i++) {
//				System.out.println("friendshipBean_list_to:"+friendshipBean_list_to.get(i).getMemberBean_b().getM_id());
//				System.out.println("member_to:"+member_to);
				if (friendshipBean_list.get(i).getMemberBean_b().getM_id()
						.equals(member_to) ){
						x=	friendshipBean_list.get(i).getFriend_check();
				}
		}
			for (int k = 0; k < friendshipBean_list_to.size(); k++) {
					if (friendshipBean_list_to.get(k).getMemberBean_b().getM_id()
							.equals(memberBean_from.getM_id()) ){
					y=	friendshipBean_list_to.get(k).getFriend_check();
					}	
			}	
//			System.out.println("x:"+ x);
//			System.out.println("y:"+ y);
			if(x==0 && y==0){
				member_map.put("isMyFriend", "Addfriend");
			}else if(x==1 && y==0){
				member_map.put("isMyFriend", "Wait");
			}else if(x==1 && y==1){
				member_map.put("isMyFriend", "Friendsblockade");
			}else if(x==2 && y==1){
				member_map.put("isMyFriend", "Unblock");
			}else if(x==2 && y==2){
				member_map.put("isMyFriend", "Unblock");
			}else if(x==1 && y ==2){
				member_map.put("isMyFriend", "Friendsblockade");
			}else if(x==0 && y ==1){
				member_map.put("isMyFriend", "Addfriend");
			}

		return member_map;
		
		
		
		
//		//friendshipBean_list 我的好友列表
//		//friendshipBean_list_to 好友的好友列表
//		if (!friendshipBean_list.isEmpty()) {
//			for (int i = 0; i < friendshipBean_list.size(); i++) {
//				// 如果getMemberBean_b().getM_id() 等於member_to
//				if (friendshipBean_list.get(i).getMemberBean_b().getM_id()
//						.equals(member_to)) {
//					if (friendshipBean_list.get(i).getFriend_check() == 1) {
//						member_map.put("isMyFriend", "Friendsblockade");
//						break;
//					} else if (friendshipBean_list.get(i).getFriend_check() == 2) {
//						member_map.put("isMyFriend", "Unblock");
//						break;
//					}
//				} else {
//					System.out.println("不是好友!!");
//					member_map.put("isMyFriend", "Addfriend");
//				}
//			}
//		} else {
//			member_map.put("isMyFriend", "Wait");
//		}
	}

	//====================好友列表：雙方同意、封鎖中、確認中========================================================================
			public List<MemberBean> MyFriendName_All(MemberBean memberBean_from){
				FriendshipDAO friendship_DAO = (FriendshipDAO) context
						.getBean("FriendshipDAO");
				MemberDAO member_DAO = (MemberDAO) context.getBean("MemberDAO");
				//回傳物件
				List<MemberBean> memberBean_list= new ArrayList<MemberBean>();
					//搜尋所有我加的好友(我與對方都同意的情況之下)與被我封鎖的好友
					String HQL="from FriendshipBean where m_id_a ='"+memberBean_from.getM_id()+"' and NOT friend_check=0";
					List<FriendshipBean> friendshipBean_list = friendship_DAO.getFriendsByMethod(HQL);
					if(!friendshipBean_list.isEmpty()){
						for(int i =0;i<friendshipBean_list.size();i++){
							//取出我的好友會員ID
							String my_friendM_id =friendshipBean_list.get(i).getMemberBean_b().getM_id();
							MemberBean  my_friendMemberBean =member_DAO.getByPrimaryKey(my_friendM_id);
							memberBean_list.add(my_friendMemberBean);
						}
						return memberBean_list;
					}else{
						return null;
					}
				
			}
	//============================好友列表:雙方同意============================================================
			public List<MemberBean> MyFriendName(MemberBean memberBean_from){
				FriendshipDAO friendship_DAO = (FriendshipDAO) context
						.getBean("FriendshipDAO");
				MemberDAO member_DAO = (MemberDAO) context.getBean("MemberDAO");
				//只搜尋所有我家的好友(我與對方都同意的情況之下)
				List<FriendshipBean> friendshipBean_list = friendship_DAO.getMyFriend(memberBean_from);
				//回傳物件
				List<MemberBean> memberBean_list= new ArrayList<MemberBean>();
				if (!friendshipBean_list.isEmpty()) {
					for (int i = 0; i < friendshipBean_list.size(); i++) {
						// 取得好友的id
						String member_friend_id = friendshipBean_list.get(i)
								.getMemberBean_b().getM_id();
						// 取得會員id
						MemberBean memberBean_friend = member_DAO
								.getByPrimaryKey(member_friend_id);
						// 加入memberBean_list
						memberBean_list.add(memberBean_friend);
					}
					return memberBean_list;
				}else {
					return null;
				}
			}
		
		
		
	
	//========================================修改前的程式碼===================================================
	// 好友列表 我與對方 都同意為好友的情況下
/*	public List<MemberBean> MyFriendName(MemberBean member) {// 傳入自己的MemberBean
		// 建立friendship_DAO
		FriendshipDAO friendship_DAO = (FriendshipDAO) context
				.getBean("FriendshipDAO");
		// 建立member_DAO
		MemberDAO member_DAO = (MemberDAO) context.getBean("MemberDAO");
		// 取得好友名單
		List<FriendshipBean> friendshipBean_list = friendship_DAO
				.getMyFriend(member);

		// 建立回傳物件
		List<MemberBean> memberBean_list = new ArrayList<MemberBean>();
		// 判斷是否有好友
		if (friendshipBean_list.size() != 0) {
			for (int i = 0; i < friendshipBean_list.size(); i++) {
				// 取得好友的id
				String member_friend_id = friendshipBean_list.get(i)
						.getMemberBean_b().getM_id();
				// 取得會員id
				MemberBean memberBean_friend = member_DAO
						.getByPrimaryKey(member_friend_id);
				// 加入memberBean_list
				memberBean_list.add(memberBean_friend);
			}
			return memberBean_list;
		} else {
			return null;
		}
	}*/

	/*
	//===========================20140327薪資===========================================================================
	public List<MemberBean> MyFriendName_isMe(MemberBean memberBean_from) {
		// 建立friendship_DAO
		FriendshipDAO friendship_DAO = (FriendshipDAO) context
				.getBean("FriendshipDAO");
		// 建立member_DAO
		MemberDAO member_DAO = (MemberDAO) context.getBean("MemberDAO");
		//
		List<MemberBean> memberBean_list= new ArrayList<MemberBean>();
		//取的我自己的好友包含我封鎖中的
		//HQL
		String HQL="from FriendshipBean where m_id_a='"+memberBean_from.getM_id()+"'";
		List<FriendshipBean> friendshipBean_list=friendship_DAO.getFriendsByMethod(HQL);
		if(friendshipBean_list.isEmpty()){
			return null;
		}else{
			for(int i=0;i<friendshipBean_list.size();i++){
				String member_str=friendshipBean_list.get(i).getMemberBean_b().getM_id();
				MemberBean memberBean =member_DAO.getByPrimaryKey(member_str);
				memberBean_list.add(memberBean);
			}
			if(!memberBean_list.isEmpty()){
				return memberBean_list;
			}else
				return null;
		}
		*/

	// =========================================測試==========================================================
	public static void main(String args[]) {
//		 ApplicationContext context = new ClassPathXmlApplicationContext(
//		 "beans.config.xml");
//		 MemberDAO MemDAO = (MemberDAO) context.getBean("MemberDAO");
//		 MemberBean member_from = new MemberBean(); // 自己
//		 MemberBean member_to = new MemberBean(); // 對方
//		 member_from = MemDAO.getByPrimaryKey("cecj001"); // 自己
//		 member_to = MemDAO.getByPrimaryKey("cecj002"); // 對方 呼叫加入好友service
		// if(member_to==null){
		// System.out.println("XX");
		// }
//		 FriendService friendService = new FriendService();
		// List<MemberBean> ll =friendService.MyFriendName(member_from);
		// for(int i =0;i<ll.size();i++){
		// System.out.println(ll.get(i).getM_id());
		// }
		// for(int i =0;i<memberBean_list.size();i++){
		//
		// FriendshipDAO ff=(FriendshipDAO)context.getBean("FriendshipDAO");
		// List<FriendshipBean>
		// FXX=ff.getMyFriend(memberBean_list.get(i).getMemberBean_b());
		// System.out.println(FXX.get(i).getMemberBean_b().getM_name());
		// }
		// ============================= 測試 加入好友
		// =========================================
		// FriendshipBean BB=friendService.addFriend(member_from,member_to);
		// if(BB!=null){
		// System.out.println("加入成功請等待對方回應");
		// }else{
		// System.out.println("加入失敗");
		// }

		// ========================= 測試 封鎖 解除封鎖 好友
		// ========================================
		// FriendshipBean friendshipBean = new FriendshipBean();
		// friendshipBean.setMemberBean_a(member_from);
		// friendshipBean.setMemberBean_b(member_to);
		// friendshipBean.setFriend_check(2);
		// FriendshipBean BB = friendService.blockadeFriend(friendshipBean);
		// if (BB != null) {
		// System.out.println("封鎖/解除封鎖成功");
		// } else {
		// System.out.println("封鎖失敗");
		// }
		//

		// ============================= 測試 瀏覽他人個版
		// =========================================
		// String member_name="cecj002";
		// Map
		// memberBean_to=friendService.viewPersonalPage(member_from,member_name);
		// if(memberBean_to!=null){
		//
		// System.out.println("m_name："+memberBean_to.get("m_name"));
		// System.out.println("m_pic_path："+memberBean_to.get("m_pic_path"));
		// System.out.println("m_email："+memberBean_to.get("m_email"));
		// System.out.println("m_birth："+memberBean_to.get("m_birth"));
		// System.out.println("m_intro："+memberBean_to.get("m_intro"));
		// System.out.println("m_sex："+memberBean_to.get("m_sex"));
		// }else{
		// System.out.println("進入"+member_name+"失敗");
		// }

		// =============================== 回應邀請
		// ==============================================
		// MemberBean mem_from =new MemberBean(); //建立發送會員物件
		// MemberBean mem_to=new MemberBean(); //建立接搜會員物件
		// mem_from=mem_DAO.getByPrimaryKey("m_from"); //取得發送會員資料
		// mem_to=mem_DAO.getByPrimaryKey("m_to"); //取得接收會員資料
		// fribean.setMemberBean_a(mem_from); //將發送會員放入Bean
		// fribean.setMemberBean_b(mem_to); //將接收會員放入Bean
		// fribean.setFriend_check(2);
		
		
		//=================================
//		 List<MemberBean> memberBean_list=friendService.MyFriendName_isMe(member_from);
//		 for(int i =0;i<memberBean_list.size();i++){
//			 System.out.println(memberBean_list.get(i).getM_id());
//		 }
	}
}
