package com.ccc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.model.bean.FriendshipBean;
import com.ccc.model.bean.FriendshipNoticeBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.service.FriendService;
import com.ccc.model.service.NoticeService;

/**
 * Servlet implementation class FriendServlet
 */
//@WebServlet("/FriendServlet")
public class FriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FriendServlet() {
        super();
       
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	this.doPost(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("doPost");		
		request.setCharacterEncoding("UTF-8");
				HttpSession  HttpSess = request.getSession();
				MemberBean member_from=(MemberBean) HttpSess.getAttribute("user");
				response.setCharacterEncoding("UTF-8");
				PrintWriter pw = response.getWriter();
				//取得好友類型
				String friendship_type=request.getParameter("friendship_type");
//				System.out.println(request.getParameter("friendship_type"));
				//==================friendship_type:FriendsList==== 好友列表 ===========================================
				//建立好友系統service
				FriendService friendService =new FriendService();
				//好友列表
				if(friendship_type.equals("FriendsList")){
					//System.out.println("memberBean_list.isEmpty():"+memberBean_list==null);
					try{
						//取的所有好友
						List<MemberBean> memberBean_list =friendService.MyFriendName_All(member_from);
						request.setAttribute("MyFriend", memberBean_list);
						getServletContext().getRequestDispatcher("/pages/FriendManage.jsp").forward(request, response);	
						return;
					}catch(NullPointerException e){
						e.printStackTrace();
						getServletContext().getRequestDispatcher("/pages/FriendManage.jsp").forward(request, response);	
						return;
					}	
					
				//friendship_type 1)瀏覽他人個版 
				}else if(friendship_type.equals("PersonalPage")){
//					System.out.println("hear...................................................................................................");
					String member_id=request.getParameter("Member");
					Map<String,Object> member_map=friendService.viewPersonalPage(member_from, member_id);
					request.setAttribute("Member_friend",member_map);
					getServletContext().getRequestDispatcher("/pages/MemberPage.jsp").forward(request, response);	
					return;
				//加入好友、封鎖好友、解鎖好友
				}else if(friendship_type.equals("Blockadelifted")){
					//取的封鎖、解鎖字串
					String friendship=request.getParameter("friendship");
					String member_id=request.getParameter("Member");
					//加入好友
					if(friendship.equals("Addfriend")){
						//System.out.println("Addfriend..............");		
						//加入好友
						FriendshipBean  friendshipBean=friendService.addFriend(member_from, member_id);
						//如果已經加入好友就會退出
						if (friendshipBean == null) {
							//System.out.println("等待通知中...");
							Map<String, Object> member_map = friendService
									.viewPersonalPage(member_from, member_id);
							request.setAttribute("Member_friend", member_map);
							getServletContext().getRequestDispatcher("/pages/PersonalPage.jsp").forward(request,response);	
							return;
						} else {
						// 建立通知service
							NoticeService noticeService = new NoticeService();
							noticeService.insertFriendshipNotice(member_from,
									member_id, 0);
							// 返回當期頁面
							Map<String, Object> member_map = friendService
									.viewPersonalPage(member_from, member_id);
							request.setAttribute("Member_friend", member_map);
							pw.write((String) member_map.get("isMyFriend"));
							pw.close();
							return;
						}
					//好友封鎖
					}else if(friendship.equals("Friendsblockade")){
						//System.out.println("Friendsblockade..............");
						friendService.blockadeFriend(member_from, member_id, 2);
						Map<String,Object> member_map=friendService.viewPersonalPage(member_from, member_id);
						request.setAttribute("Member_friend",member_map);
	//					getServletContext().getRequestDispatcher("/pages/PersonalPage.jsp").forward(request, response);	
						pw.write((String) member_map.get("isMyFriend"));
						pw.close();
						return;
					//己除封鎖
					}else if(friendship.equals("Unblock")){	
						//System.out.println("Unblock..............");				
						friendService.blockadeFriend(member_from, member_id, 1);
						Map<String,Object> member_map=friendService.viewPersonalPage(member_from, member_id);
						request.setAttribute("Member_friend",member_map);
	//					getServletContext().getRequestDispatcher("/pages/PersonalPage.jsp").forward(request, response);	
						pw.write((String) member_map.get("isMyFriend"));
						pw.close();
						return;
					//等待中	
					}else if(friendship.equals("Wait")){
							//.......
					}
				//取的邀請
				}else if(friendship_type.equals("respondInvite")){
					//System.out.println("hear");
					NoticeService noticeService =new NoticeService();
					//fri_notid  好友通知ID的關鍵字
					//fsNotice_response 好友通知的關鍵字 0)拒絕 1)接受 2)確認
					int fri_notid=Integer.parseInt(request.getParameter("fri_notid"));
					int fsNotice_response=Integer.parseInt(request.getParameter("fsNotice_response"));
					//================測試=======================
					//String member_to="cecj001";
//					int fri_notid=51;
//					String YesOrNO=request.getParameter("YesOrNO");
					//System.out.println("YesOrNO:"+YesOrNO);
//					int fsNotice_response;
//					if(YesOrNO.equals("yes")){
//						fsNotice_response=1;
//					}else if(YesOrNO.equals("no")){
//						fsNotice_response=0;
//					}else{
//						fsNotice_response=2;
//					}
					//===============以上測試=====================
					//判斷使用者回應的值 0)拒絕 1)接受 2)確認(2如果好友答應為好友則回傳訊息給邀請人)
					if(fsNotice_response==0){
						//System.out.println("hear");
						Map<String, Object> friendshipBean_notice_map = friendService.respondInvite(member_from, fsNotice_response,fri_notid);
						noticeService.deleteFriendshipNotice((FriendshipNoticeBean)friendshipBean_notice_map.get("friendshipNotice"));
						//System.out.println("刪除通知完成");
					}else if(fsNotice_response==1){
						//System.out.println("hear");
						Map<String, Object> friendshipBean_notice_map = friendService.respondInvite(member_from, fsNotice_response,fri_notid);
						FriendshipNoticeBean fsNotice=(FriendshipNoticeBean)friendshipBean_notice_map.get("friendshipNotice");
						noticeService.deleteFriendshipNotice(fsNotice);
						noticeService.insertFriendshipNotice(member_from, fsNotice.getMember_from().getM_id(), 1);
						//System.out.println("加好友完成，並且成功送出回應!");
						List<MemberBean> friendsList = friendService.MyFriendName(member_from);	
						HttpSess.setAttribute("friends", friendsList);
					}else if(fsNotice_response==2){
						//System.out.println("hear");
						Map<String, Object> friendshipBean_notice_map = friendService.respondInvite(member_from, fsNotice_response,fri_notid);
						noticeService.deleteFriendshipNotice((FriendshipNoticeBean)friendshipBean_notice_map.get("friendshipNotice"));
						//System.out.println("確認!");
						List<MemberBean> friendsList = friendService.MyFriendName(member_from);	
						HttpSess.setAttribute("friends", friendsList);					
					}
					//===============================暫時測試==============================================================================
				}else if(friendship_type.equals("TestMember2")){
					//PrintWriter out = response.getWriter();
					getServletContext().getRequestDispatcher("/pages/TestMember2.jsp").forward(request, response);	
					return;
	}

	}
}
