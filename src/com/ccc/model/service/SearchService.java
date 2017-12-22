package com.ccc.model.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.FriendshipBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.dao.FriendshipDAO;
import com.ccc.model.dao.MemberDAO;
import com.ccc.model.dao.PostDAO;
import com.ccc.model.dao.PostPictureDAO;

public class SearchService {

	ApplicationContext context = new ClassPathXmlApplicationContext(
			"beans.config.xml");

	// 搜尋會員
	// 名字 或 Email
	public List<MemberBean> searchMember(String member_name_email) {
		//System.out.println("member_name_email:"+member_name_email);
		if(member_name_email==""){
			return null;
		}
		MemberDAO member_DAO = (MemberDAO) context.getBean("MemberDAO");
		String HQL_member = "from MemberBean where( m_email like '%"
				+ member_name_email + "%'  or m_name like '%"
				+ member_name_email + "%') ";
		List<MemberBean> memberBean_list = member_DAO
				.getMembersByMethod(HQL_member);
		if (memberBean_list.isEmpty()  ||  memberBean_list.size() == 0) {
			return null;
		} else {
			return memberBean_list;
		}
	}
	// 關鍵字(內容或姓名)、貼文分類( 0)所有 1)一般動態 2)知識 3)領養 4)店家 5)活動)、
			// 使用者MemberBean、公開性質( 1)公開 2)好友 3)封鎖(沒有))
			public List<PostBean> searchPost(String content_name, int post_type,
					MemberBean member_from, int post_property,int page,int size) {
				// 建立member_DAO
				MemberDAO member_DAO = (MemberDAO) context.getBean("MemberDAO");
				// 建立PostDAO
				PostDAO post_DAO = (PostDAO) context.getBean("PostDAO");
				// 建立PostPictureDAO
				PostPictureDAO postPicture_DAO = (PostPictureDAO) context.getBean("PostPictureDAO");
				// 建立FriendshipDAO
				FriendshipDAO friendship_DAO = (FriendshipDAO) context
						.getBean("FriendshipDAO");
				// 建立 回傳用物件
				List<PostBean> repostBean_list = new ArrayList<PostBean>();
				// 建立 postBean_list物件
				List<PostBean> postBean_list = new ArrayList<PostBean>();
				// 放HQL語法
				String HQL_content_name = "";
				// 公開性質: 2)好友
				if (post_property == 2) {
					// 貼文分類不是 0)所有貼文
					if (post_type != 0) {
						// 公開性質為 "好友" 的 "特定分類" 貼文
						HQL_content_name =  "from PostBean where (m_id in (from MemberBean where m_name like '%"+content_name+"%')or post_content like '%"+content_name+"%') and post_type="+ post_type+" and post_property=2  order by post_time desc";
						//postBean_list = post_DAO.getPostsByMethod(HQL_content_name);
						postBean_list = post_DAO.getPostsByMethodPerPage(HQL_content_name,page,size);
						// 判斷有無資料
						if (!postBean_list.isEmpty() && postBean_list.size() != 0) {
							// 取得使用者的好友
							List<FriendshipBean> friendshipBean_list = friendship_DAO
									.getMyFriend(member_from);
							//判斷是否友好友
							if(!friendshipBean_list.isEmpty() && friendshipBean_list.size()!=0){
								// 判斷抓到資料內的會員是否為你我的好友
								for (int i = 0; i < postBean_list.size(); i++) {
									for (int j = 0; j < friendshipBean_list.size(); j++) {
											String post_member_id = postBean_list.get(i).getMemberBean()
													.getM_id();
											int post_id = postBean_list.get(i).getPost_id();
//											List<PostPictureBean> postPictureBean_list = postPicture_DAO.getByPostId(post_id);
											//取的使用者的好友ID
											String friendship_to = friendshipBean_list.get(j)
													.getMemberBean_b().getM_id();
											// 如果貼文會員ID 等於 好友關係的會員ID 就放入回傳物件內   //取的相同姓名的ID
											if (post_member_id.equals(friendship_to)){
												repostBean_list.add(postBean_list.get(i));
											}
										}
									}
								}
							if(repostBean_list.size()!=0){
							//回傳   回傳物件
								return repostBean_list;
							}else{
								return null;
							}
						}else{
							return null;
						}
					//判斷類型 0)全部類型
					//判斷 所有"好友"貼文 的"全部類型"
					}else{
						HQL_content_name = " from PostBean  where  (m_id in ( from MemberBean where m_name like '%"+content_name+"%')or post_content like '%"+content_name+"%') and post_property=2 and post_type is not 5 order by post_time desc";
						//postBean_list = post_DAO.getPostsByMethod(HQL_content_name);
						postBean_list = post_DAO.getPostsByMethodPerPage(HQL_content_name,page,size);
						if (!postBean_list.isEmpty() && postBean_list.size() != 0) {
							// 取得使用者的好友
							List<FriendshipBean> friendshipBean_list = friendship_DAO
									.getMyFriend(member_from);
							if(!friendshipBean_list.isEmpty() && friendshipBean_list.size()!=0){
								// 判斷抓到資料內的會員是否為你我的好友
								if(friendshipBean_list.size()!=0){
									for (int i = 0; i < postBean_list.size(); i++) {
										for (int j = 0; j < friendshipBean_list.size(); j++) {
											String post_name = postBean_list.get(i).getMemberBean()
													.getM_id();
											String friendship_to = friendshipBean_list.get(j)
													.getMemberBean_b().getM_id();
											// 如果貼文會員ID 等於 好友關係的會員ID 就放入回傳物件內
											if (post_name.equals(friendship_to)) {
												repostBean_list.add(postBean_list.get(i));
											}

										}
									}
								}
							}
							if(repostBean_list.size()!=0){
								//回傳   回傳物件
								return repostBean_list;
							}else{
								return null;
							}
						}else {
							return null;
						}
					}
				//如果是 在公開頁面搜尋文章
				// 如果公開性質是：1公開
				} else if (post_property == 1) {
					//貼文類型不是0的話 (0是全部類型貼文)
					if(post_type!=0){
						HQL_content_name =  "from PostBean where (m_id in (from MemberBean where m_name like '%"+content_name+"%')or post_content like '%"+content_name+"%') and post_type="+ post_type+" and post_property=1 order by post_time desc";
//						postBean_list = post_DAO.getPostsByMethod(HQL_content_name);
						postBean_list = post_DAO.getPostsByMethodPerPage(HQL_content_name,page,size);
//						System.out.println("postBean_list.size():"+postBean_list.size());
						//判斷是否為空值
						if (!postBean_list.isEmpty() && postBean_list.size() != 0) {
							return postBean_list;
						}else{
							return null;
						}
						//抓取所有公開的貼文
					}else{

						HQL_content_name = " from PostBean  where  (m_id in ( from MemberBean where m_name like '%"+content_name+"%')or post_content like '%"+content_name+"%') and post_property=1 and post_type is not 5 order by post_time desc";
						postBean_list = post_DAO.getPostsByMethodPerPage(HQL_content_name,page,size);
						//判斷是否為空值
						if (!postBean_list.isEmpty() && postBean_list.size() != 0) {
							return postBean_list;
						}else{
							return null;
						}
					}
				//如果是在"公開頁面"搜尋是 "公開+好友" 貼文
				} else {
					Iterator<FriendshipBean> friendshipBean_list = friendship_DAO.getMyFriend(member_from).iterator();
					String HQL_content_name_all="";
					List<PostBean> repostBean_list_all =new ArrayList<PostBean>();
					String ids = "";
					if (friendshipBean_list != null) {
						while (friendshipBean_list.hasNext()){
							FriendshipBean fsb = friendshipBean_list.next();
							ids = ids + "'" + fsb.getMemberBean_b().getM_id() + "', ";
						}
					}
					//System.out.println("ids:"+ids);
					//全部類型
					if(post_type==0){
						//公開性質為公開 無分類
						HQL_content_name_all = "from PostBean where (m_id in (" + ids + "null) and post_property = 2 and post_type is not 5 and post_content like '%"+content_name+"%') or (post_property = 1 and post_type is not 5 and post_content like '%"+content_name+"%') order by post_time desc";
						//System.out.println("測試HQL_content_name_all:"+HQL_content_name_all);
					//不是全部類型
					}else{
						//公開性質為公開 分類
						HQL_content_name_all = "from PostBean where (m_id in (" + ids + "null) and post_property = 2 and post_content like '%"+content_name+"%' and post_type="+ post_type+") or (post_property = 1 and post_content like '%"+content_name+"%' and post_type="+ post_type+") order by post_time desc";
//						System.out.println("測試HQL_content_name_all:"+HQL_content_name_all);


					}
					repostBean_list_all=post_DAO.getPostsByMethodPerPage(HQL_content_name_all,page,size);
//					System.out.println();
					if(repostBean_list_all.size()!=0 && !repostBean_list_all.isEmpty()){
						return repostBean_list_all;
					}
						return null;
					}
				}




//			public List<PostPictureBean> searchPostPicture(int p_id){
//				PostPictureDAO postPicture_DAO =(PostPictureDAO) context.getBean("PostPictureDAO");
//				List<PostPictureBean> postPicutureBean_list=new ArrayList<PostPictureBean>();
//				List<PostPictureBean> postPictureBean_list =postPicture_DAO.getByPostId(p_id);
//				if(postPictureBean_list.size()!=0 && !postPictureBean_list.isEmpty()){
//					return postPicutureBean_list;
//				}else{
//					return null;
//				}
//			}
//====================================================================================================================
//	// 搜尋活動
//	// 針對活動的 標題、地點、會員ID搜尋 活動性質
//	public List<ActivityBean> searchActivity(String title_location_member,
//			int act_property, MemberBean member) {
//		// 建立 ActivityDAO
//		ActivityDAO activity_DAO = (ActivityDAO) context.getBean("ActivityDAO");
//		// 建立 ActivityParticipateDAO
//		ActivityParticipateDAO activityParticipate_DAO = (ActivityParticipateDAO) context
//				.getBean("ActivityParticipateDAO");
//		// 建立回傳的物件
//		List<ActivityBean> reactivityBean_list = new ArrayList<ActivityBean>();
//		// 公開性質 0私人 1公開
//		if (act_property == 1) {// 如果是公開活動
//			// 抓公開活動的HQL語法 條件：針對會員、地點、標題，至少其一
//			String HQL_title_location_time = "from ActivityBean where (act_title like '%"
//					+ title_location_member
//					+ "%' or act_location like '%"
//					+ title_location_member
//					+ "%' or m_id like '%"
//					+ title_location_member + "%') and act_property=1";
//			// 取得符合條件的公開貼文
//			List<ActivityBean> activity_list = activity_DAO
//					.getActivityByMethod(HQL_title_location_time);
//			// 如果activity_list 內有資料
//			if (activity_list.size() != 0) {
//				// 回傳所有 公開貼文的資料
//				return activity_list;
//			} else {
//				return null;
//			}
//		} else if (act_property == 0) { // 如果是私人活動
//			// 抓取會員自己參與的所有活動
//
//			List<ActivityParticipateBean> activityParticipateBean_list = activityParticipate_DAO
//					.getMemWasInvited(member);
//			// System.out.println(activityParticipateBean_list.size());
//			// 抓私人活動的HQL語法 條件：針對會員、地點、標題，至少其一
//			String HQL_title_location_time = "from ActivityBean where (act_title like '%"
//					+ title_location_member
//					+ "%' or act_location like '%"
//					+ title_location_member
//					+ "%' or m_id like '%"
//					+ title_location_member + "%') and act_property= 0";
//			// 這裡會抓所有的私人活動
//			List<ActivityBean> activity_list = activity_DAO
//					.getActivityByMethod(HQL_title_location_time);
//
//			if (activity_list.size() != 0) { // 判斷是否有資料
//				// 把所有的私人活動抓出來判斷是否有參加
//
//				for (int i = 0; i < activity_list.size(); i++) { // 依序執行私人活動
//					// System.out.println(activity_list.get(i).getAct_id());
//					for (int j = 0; j < activityParticipateBean_list.size(); j++) { // 不斷判斷活動關係
//						// 如果私人活動ID與活動關係的ID相同
//						int a = activity_list.get(i).getAct_id();
//						int b = activityParticipateBean_list.get(j)
//								.getActivity().getAct_id();
//						// System.out.println(a+","+b);
//						if (a == b) {
//							reactivityBean_list.add(activity_list.get(i));
//						}
//					}
//				}
//				return reactivityBean_list;
//			} else {
//				return null;
//			}
//		}
//		return null;
//	}
//
//
//
//
//
//
//
//
//	// 搜尋新聞
//	// 搜尋標題 、內文
//	public List<NewsBean> searchNews(String news_title_content) {
//		NewsDAO news_DAO = (NewsDAO) context.getBean("NewsDAO");
//		String HQL_new = "from NewsBean where(news_title like '%"
//				+ news_title_content + "%' or news_content like '%"
//				+ news_title_content + "%')";
//		List<NewsBean> newsBean_list = news_DAO.getNewsByMethod(HQL_new);
//
//		if (newsBean_list.size() == 0 || newsBean_list == null) {
//			return null;
//		} else {
//			return newsBean_list;
//		}
//	}


//
	// 關鍵字(內容或姓名)、貼文分類( 0)所有 1)一般動態 2)知識 3)領養 4)店家 5)活動)、
		// 使用者MemberBean、公開性質( 1)公開 2)好友 3)封鎖(沒有))
//		public List<PostBean> searchPost(String content_name, int post_type,
//				MemberBean member_from, int post_property) {
//			// 建立member_DAO
//			MemberDAO member_DAO = (MemberDAO) context.getBean("MemberDAO");
//			// 建立PostDAO
//			PostDAO post_DAO = (PostDAO) context.getBean("PostDAO");
//			// 建立PostPictureDAO
//			PostPictureDAO postPicture_DAO = (PostPictureDAO) context.getBean("PostPictureDAO");
//			// 建立FriendshipDAO
//			FriendshipDAO friendship_DAO = (FriendshipDAO) context
//					.getBean("FriendshipDAO");
//			// 建立 回傳用物件
//			List<PostBean> repostBean_list = new ArrayList<PostBean>();
//			// 建立 postBean_list物件
//			List<PostBean> postBean_list = new ArrayList<PostBean>();
//			// 放HQL語法
//			String HQL_content_name = "";
//			// 公開性質: 2)好友
//			if (post_property == 2) {
//				// 貼文分類不是 0)所有貼文
//				if (post_type != 0) {
//					// 公開性質為 "好友" 的 "特定分類" 貼文
//					HQL_content_name =  "from PostBean where (m_id in (from MemberBean where m_name like '%"+content_name+"%')or post_content like '%"+content_name+"%') and post_type="+ post_type+" and post_property=2  order by post_time";
//					postBean_list = post_DAO.getPostsByMethod(HQL_content_name);
//					// 判斷有無資料
//					if (!postBean_list.isEmpty() && postBean_list.size() != 0) {
//						// 取得使用者的好友
//						List<FriendshipBean> friendshipBean_list = friendship_DAO
//								.getMyFriend(member_from);
//						//判斷是否友好友
//						if(!friendshipBean_list.isEmpty() && friendshipBean_list.size()!=0){
//							// 判斷抓到資料內的會員是否為你我的好友
//							for (int i = 0; i < postBean_list.size(); i++) {
//								for (int j = 0; j < friendshipBean_list.size(); j++) {
//										String post_member_id = postBean_list.get(i).getMemberBean()
//												.getM_id();
//										int post_id = postBean_list.get(i).getPost_id();
//										List<PostPictureBean> postPictureBean_list = postPicture_DAO.getByPostId(post_id);
//										//取的使用者的好友ID
//										String friendship_to = friendshipBean_list.get(j)
//												.getMemberBean_b().getM_id();
//										// 如果貼文會員ID 等於 好友關係的會員ID 就放入回傳物件內   //取的相同姓名的ID
//										if (post_member_id.equals(friendship_to)){
//											repostBean_list.add(postBean_list.get(i));
//										}
//									}
//								}
//							}
//						if(repostBean_list.size()!=0){
//						//回傳   回傳物件
//							return repostBean_list;
//						}else{
//							return null;
//						}
//					}else{
//						return null;
//					}
//				//判斷類型 如果是0 就是全部類型
//				//以下是 判斷 所有"好友"貼文 的"全部類型"
//				}else{
//					HQL_content_name = " from PostBean  where  (m_id in ( from MemberBean where m_name like '%"+content_name+"%')or post_content like '%"+content_name+"%') and post_property=2 order by post_time";
//					postBean_list = post_DAO.getPostsByMethod(HQL_content_name);
//					if (!postBean_list.isEmpty() && postBean_list.size() != 0) {
//						// 取得使用者的好友
//						List<FriendshipBean> friendshipBean_list = friendship_DAO
//								.getMyFriend(member_from);
//						if(!friendshipBean_list.isEmpty() && friendshipBean_list.size()!=0){
//							// 判斷抓到資料內的會員是否為你我的好友
//							if(friendshipBean_list.size()!=0){
//								for (int i = 0; i < postBean_list.size(); i++) {
//									for (int j = 0; j < friendshipBean_list.size(); j++) {
//										String post_name = postBean_list.get(i).getMemberBean()
//												.getM_id();
//										String friendship_to = friendshipBean_list.get(j)
//												.getMemberBean_b().getM_id();
//										// 如果貼文會員ID 等於 好友關係的會員ID 就放入回傳物件內
//										if (post_name.equals(friendship_to)) {
//											repostBean_list.add(postBean_list.get(i));
//										}
//
//									}
//								}
//							}
//						}
//						if(repostBean_list.size()!=0){
//							//回傳   回傳物件
//							return repostBean_list;
//						}else{
//							return null;
//						}
//					}else {
//						return null;
//					}
//				}
//			//如果是 在公開頁面搜尋文章
//			// 如果公開性質是：1公開
//			} else if (post_property == 1) {
//				//貼文類型不是0的話 (0是全部類型貼文)
//				if(post_type!=0){
//					HQL_content_name =  "from PostBean where (m_id in (from MemberBean where m_name like '%"+content_name+"%')or post_content like '%"+content_name+"%') and post_type="+ post_type+" and post_property=1  order by post_time";
//					postBean_list = post_DAO.getPostsByMethod(HQL_content_name);
//					//判斷是否為空值
//					if (!postBean_list.isEmpty() && postBean_list.size() != 0) {
//						return postBean_list;
//					}else{
//						return null;
//					}
//					//抓取所有公開的貼文
//				}else{
//					HQL_content_name = " from PostBean  where  (m_id in ( from MemberBean where m_name like '%"+content_name+"%')or post_content like '%"+content_name+"%') and post_property=1 order by post_time";
//					postBean_list = post_DAO.getPostsByMethod(HQL_content_name);
//					//判斷是否為空值
//					if (!postBean_list.isEmpty() && postBean_list.size() != 0) {
//						return postBean_list;
//					}else{
//						return null;
//					}
//				}
//				//如果是在"公開頁面"搜尋是 公開+好友 貼文
//			} else {
//
//				String HQL_content_name_one="";
//				String HQL_content_name_two="";
//				if(post_type==0){
//					System.out.println("SearchService,hear.....");
//					//公開性質為公開 無分類
//					HQL_content_name_one = " from PostBean  where  (m_id in ( from MemberBean where m_name like '%"+content_name+"%')or post_content like '%"+content_name+"%')  and post_property=1 order by post_time";
//					//公開性質為好友 無分類
//					HQL_content_name_two = " from PostBean  where  (m_id in ( from MemberBean where m_name like '%"+content_name+"%')or post_content like '%"+content_name+"%')  and post_property=2 order by post_time";
//				}else{
//					//公開性質為公開 分類
//					HQL_content_name_one = " from PostBean  where  (m_id in ( from MemberBean where m_name like '%"+content_name+"%')or post_content like '%"+content_name+"%') and post_type="+post_type+" and post_property=1 order by post_time";
//					//公開性質為好友 分類
//					HQL_content_name_two = " from PostBean  where  (m_id in ( from MemberBean where m_name like '%"+content_name+"%')or post_content like '%"+content_name+"%') and post_type="+post_type+" and post_property=2 order by post_time";
//
//				}
//				List<PostBean> postBean_list_one = post_DAO.getPostsByMethod(HQL_content_name_one);
//				List<PostBean> postBean_list_two = post_DAO.getPostsByMethod(HQL_content_name_two);
//				List<FriendshipBean> friendshipBean_list = friendship_DAO.getMyFriend(member_from);
//				System.out.println("!friendshipBean_list.isEmpty():"+!friendshipBean_list.isEmpty());
//				if(!friendshipBean_list.isEmpty() && friendshipBean_list.size()!=0){
//					for (int i = 0; i < postBean_list_two.size(); i++) {
//						for (int j = 0; j < friendshipBean_list.size(); j++) {
//							String post_name = postBean_list_two.get(i).getMemberBean()
//									.getM_id();
//							String friendship_to = friendshipBean_list.get(j)
//									.getMemberBean_b().getM_id();
//							// 如果貼文會員ID 等於 好友關係的會員ID 就放入回傳物件內
//							if (post_name.equals(friendship_to)) {
//								postBean_list_one.add(postBean_list_two.get(i));
//							}
//						}
//					}
//					if(!postBean_list.isEmpty() && postBean_list.size() != 0){
//						//回傳   回傳物件
//						return postBean_list_one;
//					}else{
//						return postBean_list_one;
//					}
//				}else{
//					return postBean_list_one;
//				}
//			}
//		}
//=================================================================================================================================================
	public static void main(String args[]) {
//		 ApplicationContext context = new ClassPathXmlApplicationContext(
//		 "beans.config.xml");
//		 MemberDAO MemDAO = (MemberDAO) context.getBean("MemberDAO");
//
//		 MemberBean member_from = new MemberBean(); // 自己
//		 member_from = MemDAO.getByPrimaryKey("cecj001"); // 自己
//		 SearchService aa = new SearchService();
//
//		 System.out.println("====搜尋貼文測試====");
//
//		 List<PostBean> ll = aa.searchPost("阿",0,member_from, 1); //
//		 //關鍵字,貼文分類,自己,公開/好友/公開+好友
//		 if (ll !=null) {
//		 for(int i=0;i<ll.size();i++){
//		 System.out.println(ll.get(i).getPost_id());
//		 }
//
//		 } else {
//		 System.out.println("找不到相關貼文");
//		 }

		// System.out.println("====搜尋會員測試====");
		// List<MemberBean> memberBean_list=aa.searchMember("cecj06@");
		// if(memberBean_list==null||memberBean_list.size()==0){
		// System.out.println("沒這個人好嗎!?");
		// }else{
		// System.out.println(memberBean_list.get(0).getM_name());
		// }





		// System.out.println("====搜尋活動測試====");
		// List<ActivityBean>
		// //member_from->自己，有"參加或者被邀請(不管拒絕與否)"的那些 "私人活動" 針對"標題"或"地點"或"內容"
		// 是"貓"的。
		// activityBean_list=aa.searchActivity("貓",1,member_from);
		// if(activityBean_list.size()==0){
		// System.out.println("沒這個活動好嗎!?");
		// }else{
		// for(int i=0;i<activityBean_list.size();i++){
		// System.out.println(activityBean_list.get(i).getAct_title());
		// }
		// }

		// System.out.println("====搜尋新聞測試====");
		// List<NewsBean> newBean_list=aa.searchNew("TICASSS");
		// if(newBean_list==null||newBean_list.size()==0){
		// System.out.println("沒!?");
		// }else{
		// for(int i=0;i<newBean_list.size();i++){
		// System.out.println(newBean_list.get(i).getNews_title());
		// }
		//
		// }

	}

}
