package com.ccc.model.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.FriendshipBean;
import com.ccc.model.bean.FriendshipNoticeBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.MessageBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.bean.WhoLikeBean;
import com.ccc.model.dao.FriendshipDAO;
import com.ccc.model.dao.FriendshipNoticeDAO;
import com.ccc.model.dao.MemberDAO;
import com.ccc.model.dao.MessageDAO;
import com.ccc.model.dao.PostDAO;
import com.ccc.model.dao.WhoLikeDAO;

public class MainTest_by_fang {
	public static void main(String args[]) {
		/*
		 * 需要Bean Member取會員ID Post 取POST的ID 需要DAO Member取會員ID Post取會員ID
		 */

		ApplicationContext context = new ClassPathXmlApplicationContext( // 一定要的
				"beans.config.xml");

		MemberBean memA = new MemberBean();// 我需要兩位會員ID
		MemberBean memB = new MemberBean(); // 我需要兩位會員ID
		// //////////////////////////////////////



		// //////////////////////////////FriendshipBeanDAO///////////////////////////////////////////////////////////////////

		FriendshipDAO fsBD = (FriendshipDAO) context
				.getBean("FriendshipDAO"); // 必需品1

		// ==============insert========================

//		 MemberDAO MemDA = (MemberDAO) context.getBean("MemberDAO");
//		 MemberDAO MemDB = (MemberDAO) context.getBean("MemberDAO");
//		 memA = MemDA.getByPrimaryKey("cecj003");
//		 memB = MemDA.getByPrimaryKey("cecj001");
//		 FriendshipBean fsB = new FriendshipBean();
//		 fsB.setMemberBean_a(memA); //加帳號請改
//		 fsB.setMemberBean_b(memB);
//		 fsB.setFriend_check(2); //可以不用 與社會是0
//		 fsBD.insert(fsB);

		// ===============update=====================
//		 MemberDAO MemDA = (MemberDAO) context.getBean("MemberDAO");
//		 MemberDAO MemDB = (MemberDAO) context.getBean("MemberDAO");
//		 memA = MemDA.getByPrimaryKey("cecj001");
//		 memB = MemDA.getByPrimaryKey("cecj003");
//		 FriendshipBean fsB = new FriendshipBean();
//		 fsB.setMemberBean_a(memA); //加帳號請改
//		 fsB.setMemberBean_b(memB);
//		 fsB.setFriend_check(1); //要改 才能判斷是否更新 不改也可以~沒意義
//		 FriendshipBean FB =fsBD.update(fsB);
//		 if(FB==null){
//		 System.out.println("失敗");
//		 }else{
//		 System.out.println(FB.getMemberBean_a().getM_id()+","+FB.getMemberBean_b().getM_id()+"修改成功");
//		 }
		// ===============delete=====================
//		 MemberDAO MemDA = (MemberDAO) context.getBean("MemberDAO");
//		 MemberDAO MemDB = (MemberDAO) context.getBean("MemberDAO");
//		 memA = MemDA.getByPrimaryKey("cecj001");
//		 memB = MemDA.getByPrimaryKey("cecj003");
//		 FriendshipBean fsB = new FriendshipBean(); // 必需品2
//		 fsB.setMemberBean_a(memA); //加帳號請改
//		 fsB.setMemberBean_b(memB);
//		 FriendshipBean FB=fsBD.delete(fsB);
//		 if(FB==null){
//		 System.out.println("失敗");
//		 }else{
//		 System.out.println(FB.getMemberBean_a().getM_id()+","+FB.getMemberBean_b().getM_id()+"修改成功");
//		 }

		// ===========getAll==================================
//		 System.out.println("==getAll==");
//		 for(int i=0;i<fsBD.getAll().size();i++){
//		 System.out.println(fsBD.getAll().get(i).getMemberBean_a().getM_id()
//		 +","+fsBD.getAll().get(i).getMemberBean_b().getM_id()
//		 +","+fsBD.getAll().get(i).getFriend_check());
//		 }

		// ===========getByPrimaryKey==========================
//		 System.out.println("==getByPrimaryKey==");
//		 FriendshipBean item=fsBD.getByPrimaryKey("cecj001","cecj002");
//		 if(item==null){
//		 System.out.println("失敗");
//		 }else{
//		 System.out.println(item.getMemberBean_a().getM_id()+","+item.getMemberBean_b().getM_id()+","+item.getFriend_check());
//		 }
		

		//====================getMyFriends==================================================================
//			System.out.println("==getMyFriends==");
//			 MemberDAO MemDA = (MemberDAO) context.getBean("MemberDAO");
//			 memA = MemDA.getByPrimaryKey("cecj002");
//			 List<FriendshipBean> fsbdList = fsBD.getMyFriend(memA);
//		if(fsbdList.size()==0){
//			System.out.println("沒好友啦");
//		}else{
//			for(int i=0; i<fsbdList.size(); i++){
//			 System.out.println(fsbdList.get(i).getMemberBean_a().getM_id()
//			 +","+fsbdList.get(i).getMemberBean_b().getM_id()
//			 +","+fsbdList.get(i).getFriend_check());
//			 }
//		}

		
		
		
		

		// //////////////////////完畢///////////////////////////////////////////////////////////////////////////////////////////

		// ///////////////////////////FriendshipNoticeBeanDAO//////////////////////////////////////////////////////////////////

		// ==================FriendshipNoticeBeanDAO=====================================
		FriendshipNoticeDAO fsNBD = (FriendshipNoticeDAO) context
				.getBean("FriendshipNoticeDAO"); // 必需品1
		FriendshipNoticeBean fsNB = new FriendshipNoticeBean(); // 必需品2

		// =====================insert========================================
//		MemberBean memA = new MemberBean();// 我需要兩位會員ID
//		MemberBean memB = new MemberBean(); // 我需要兩位會員ID	
//		 MemberDAO MemDA = (MemberDAO) context.getBean("MemberDAO");
//		 MemberDAO MemDB = (MemberDAO) context.getBean("MemberDAO");
//		 memA = MemDA.getByPrimaryKey("cecj001");
//		 memB = MemDA.getByPrimaryKey("cecj002");
//		 fsNB.setMember_to(memA); //必需品3 取會員A 的ID
//		 fsNB.setMember_from(memB); //必需品4 取會員B ID
//		 fsNB.setM_name_from("XXX"); //自訂譯名稱就是 發送著的名字
//		 FriendshipNoticeBean AA=fsNBD.insert(fsNB);
//		 if(AA==null){
//			 System.out.println("錯誤");
//		 }else{
//			 System.out.println(AA.getFri_notid()+","+AA.getMember_from().getM_id()+","+AA.getMember_to().getM_id());
//		 }
		 
		// ==========================update=========================================================================
//		MemberBean memA = new MemberBean();// 我需要兩位會員ID
//		MemberBean memB = new MemberBean(); // 我需要兩位會員ID	
//		 MemberDAO MemDA = (MemberDAO) context.getBean("MemberDAO");
//		 MemberDAO MemDB = (MemberDAO) context.getBean("MemberDAO");
//		 memA = MemDA.getByPrimaryKey("cecj001");
//		 memB = MemDA.getByPrimaryKey("cecj002");
//		 fsNB.setMember_to(memA); //必需品3 取會員A 的ID
//		 fsNB.setMember_from(memB); //必需品4 取會員B ID
//		 fsNB.setM_name_from("XXXOOO"); //自訂譯名稱就是 發送著的名字
//		 fsNB.setFri_read(1);//1已讀(加或者不加都會刪除) //預設是0
//		 fsNB.setFri_type(2); //預設是0
//		 FriendshipNoticeBean AA=fsNBD.update(fsNB);
//		 if(AA==null){
//		 System.out.println("失敗");
//		 }else{
//			 System.out.println(AA.getFri_notid()+","+AA.getMember_from().getM_id()+","+AA.getMember_to().getM_id());
//		 }

		// ========================delete==========================================================================
//		MemberBean memA = new MemberBean();// 我需要兩位會員ID
//		MemberBean memB = new MemberBean(); // 我需要兩位會員ID	
//		MemberDAO MemDA = (MemberDAO) context.getBean("MemberDAO");
//		 MemberDAO MemDB = (MemberDAO) context.getBean("MemberDAO");
//		 memA = MemDA.getByPrimaryKey("cecj001");
//		 memB = MemDA.getByPrimaryKey("cecj002");
//		 fsNB.setMember_to(memA); //必需品3 取會員A 的ID
//		 fsNB.setMember_from(memB); //必需品4 取會員B ID
//		 FriendshipNoticeBean AA=fsNBD.delete(fsNB);
//		 if(AA==null){
//		 System.out.println("失敗");
//		 }else{
//		 System.out.println(AA.getFri_notid()+","+AA.getMember_from().getM_id()+","+AA.getMember_to().getM_id());
//		 }
		// =========================getByPrimaryKey===============================================================
//		System.out.println("==getByPrimaryKey===");
//		 FriendshipNoticeBean AA=fsNBD.getByPrimaryKey(1);
//		 System.out.println(AA.getMember_from().getM_id());

		// ==============================getAll====================================================================
//			System.out.println("==getAll===");
//		 List<FriendshipNoticeBean> BB= fsNBD.getAll();
//		 System.out.println(BB.get(0).getMember_from().getM_id());

		// ////////////////////////////////////////////////完畢///////////////////////////////////////////////////////////////////////////////
		// ====================MessageBeanDAO===================================
		
		MessageDAO MessageDAO = (MessageDAO) context
				.getBean("MessageDAO");
		

//		// =======================insert===========================================
//		MemberBean memA = new MemberBean();// 我需要兩位會員ID
//		MemberBean memB = new MemberBean(); // 我需要兩位會員ID
//		MemberDAO MemDA = (MemberDAO) context.getBean("MemberDAO");
//		MemberDAO MemDB = (MemberDAO) context.getBean("MemberDAO");
//		MessageBean MB = new MessageBean();
//		memA = MemDA.getByPrimaryKey("cecj001");
//		memB = MemDA.getByPrimaryKey("cecj005");
//		 MB.setMember_from(memA);
//		 MB.setMember_to(memB);
//		 MB.setMsg_content("你好嗎?cecj003");
////		 MB.setMsg_time(java.sql.Date.valueOf("2014-03-02"));
//		 MessageBean MBtest= MessageDAO.insert(MB);
//		 if(MBtest==null){
//			 System.out.println("失敗");
//		 }else{
//			 System.out.println(MBtest.getMember_from().getM_id()+","+MBtest.getMember_to().getM_id());
//		 }
		// ===================delete=============================================================
//		MemberBean memA = new MemberBean();// 我需要兩位會員ID
//		MemberBean memB = new MemberBean(); // 我需要兩位會員ID
//		MemberDAO MemDA = (MemberDAO) context.getBean("MemberDAO");
//		MemberDAO MemDB = (MemberDAO) context.getBean("MemberDAO");
//		MessageBean MB = new MessageBean();
//		memA = MemDA.getByPrimaryKey("cecj001");
//		memB = MemDA.getByPrimaryKey("cecj005");
//		 MB.setMember_from(memA);
//		 MB.setMember_to(memB);
//		 MessageBean MBTest= MessageDAO.delete(MB);
//		 if(MBTest==null){
//			 System.out.println("刪除失敗");
//		 }else{
//			 System.out.println(MBTest.getMember_from().getM_id()+"對"+MBTest.getMember_to().getM_id()+"的所有流言刪除成功");
//		 }

		// =======================getByPrimaryKey=================================================
//		System.out.println("==getByPrimaryKey==");
//		 MessageBean AA= MessageDAO.getByPrimaryKey(2);
//		 System.out.println(AA.getMsg_id()+","+AA.getMember_from().getM_id());

		// ===========================getAll======================================================
//			System.out.println("==getAll==");
//		 List<MessageBean> ListMB=MessageDAO.getAll();
//		 System.out.println(ListMB.get(1).getMember_from().getM_id());
//		 for(int i =1 ;i<ListMB.size();i++){
//		 System.out.println(ListMB.get(i).getMember_from().getM_id()+","+ListMB.get(i).getMember_from().getM_id());
//		 }
		// ////////////////////////////////////////////完畢/////////////////////////////////////////////////////////////////////////


		// =====================WhoLikeBeanDAO======================================
	

		// =======================insert==========================================
//		MemberBean memA = new MemberBean();// 我需要兩位會員ID
//		MemberDAO MemDA = (MemberDAO) context.getBean("MemberDAO");
//		memA = MemDA.getByPrimaryKey("cecj001"); //取得會員ID
//		PostBean PB = new PostBean();
//		PostDAO poDA = (PostDAO) context.getBean("PostDAO");
//		PB = poDA.getByPrimaryKey(1); //選擇貼文ID
//		WhoLikeBean WB = new WhoLikeBean();
//		WhoLikeDAO whoLikeBD = (WhoLikeDAO) context.getBean("WhoLikeDAO");
//		 WB.setPost(PB); //放入貼文ID
//		 WB.setMember(memA); //放入會員ID
//		 WB.setM_name("簡阿芳"); //放入名子
//		WhoLikeBean WLBTest=whoLikeBD.insert(WB);
//		if(WLBTest==null){
//			System.out.println("加入失敗");
//		}else{
//			System.out.println("貼文編號:"+WLBTest.getPost().getPost_id()+"，會員："+WLBTest.getMember().getM_id()+"按讚成功");
//		}
		// =======================delete====================================
//		MemberBean memA = new MemberBean();// 我需要兩位會員ID
//		MemberDAO MemDA = (MemberDAO) context.getBean("MemberDAO");
//		memA = MemDA.getByPrimaryKey("cecj001"); //取會員ID
//		PostBean PB = new PostBean();
//		PostDAO poDA = (PostDAO) context.getBean("PostDAO");
//		PB = poDA.getByPrimaryKey(1); //去貼文ID
//		WhoLikeBean WB = new WhoLikeBean();
//		WhoLikeDAO whoLikeBD = (WhoLikeDAO) context.getBean("WhoLikeDAO");
//		WB.setMember(memA);//放入會員
//		WB.setPost(PB); //放入貼文
//		WhoLikeBean WLBTest=whoLikeBD.delete(WB); //刪除貼文的讚
//		if(WLBTest==null){
//			System.out.println("失敗");
//		}else{
//			System.out.println(WLBTest.getMember().getM_id()+"對"+WLBTest.getPost().getPost_id()+"刪除成功");
//		}
		//=======================getByPrimaryKey============================================================
//		System.out.println("==getByPrimaryKey==");
//		WhoLikeDAO whoLikeBD = (WhoLikeDAO) context.getBean("WhoLikeDAO");
//		 WhoLikeBean WB1=whoLikeBD.getByPrimaryKey(1,"cecj005");
//		 if(WB1==null){
//			 System.out.println("失敗");
//		 }else{
//			 System.out.println(WB1.getPost().getPost_id()+","+WB1.getMember().getM_id()+","+WB1.getM_name());
//		 }
		
		
		//=======================getAll()============================================================
//		System.out.println("==getAll==");
//		WhoLikeDAO whoLikeBD = (WhoLikeDAO) context.getBean("WhoLikeDAO");
//		 List<WhoLikeBean> WWW = whoLikeBD.getAll();
//		 for (int i = 0; i < WWW.size(); i++) {
//		
//		 System.out.println(WWW.get(i).getMember().getM_id());
//		 }

		//=======================getByPostId()============================================================
//		System.out.println("====getByPost_id");
//		WhoLikeDAO whoLikeBD = (WhoLikeDAO) context.getBean("WhoLikeDAO");
//		List<WhoLikeBean> www = whoLikeBD.getByPostId(1);
//		for(int i=0;i<www.size();i++){
//			System.out.println(www.get(i).getPost().getPost_id());
//			System.out.println(www.get(i).getMember().getM_id());
//			System.out.println(www.get(i).getM_name());
//			System.out.println("==========");
//		}
	}

}
