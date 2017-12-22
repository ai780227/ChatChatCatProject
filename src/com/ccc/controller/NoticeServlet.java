package com.ccc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.ActivityNoticeBean;
import com.ccc.model.bean.FriendshipNoticeBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.bean.ResponseBean;
import com.ccc.model.bean.ResponseNoticeBean;
import com.ccc.model.bean.WarningNoticeBean;
import com.ccc.model.dao.ActivityDAO;
import com.ccc.model.dao.MemberDAO;
import com.ccc.model.dao.PostDAO;
import com.ccc.model.dao.ResponseDAO;
import com.ccc.model.dao.WarningNoticeDAO;
import com.ccc.model.service.NoticeService;

/**
 * Servlet implementation class NoticeServlet
 */
//@WebServlet("/NoticeServlet")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		HttpSession httpSess = request.getSession();	
		MemberBean user = (MemberBean) httpSess.getAttribute("user");		//取得目前使用者
		NoticeService noticeServ = new NoticeService();
		WebApplicationContext webAppContext = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		
//		System.out.println("NoticeServlet doGet");
		String noticeType = request.getParameter("NoticeType");
		if("FriendshipNotice".equals(noticeType)) {
			String friend_id = request.getParameter("mem_to");
			noticeServ.insertFriendshipNotice(user, friend_id, 0);
			pw.write("good");			
		} else if("ResponseNotice".equals(noticeType)) {
			ResponseDAO respdao = (ResponseDAO) webAppContext.getBean("ResponseDAO");
			ResponseBean respBean = respdao.getByPrimaryKey(1);
			noticeServ.insertResponseNotice(respBean);
			pw.write("good");
		} else if("ActivityNotice".equals(noticeType)) {
			ActivityDAO actdao = (ActivityDAO) webAppContext.getBean("ActivityDAO");
			ActivityBean actBean = actdao.getByPrimaryKey(1);
			noticeServ.insertActivityNotice(actBean);
			pw.write("good");			
		} else if("WarningNotice".equals(noticeType)) {
			WarningNoticeDAO wardao = (WarningNoticeDAO) webAppContext.getBean("WarningNoticeDAO");
			PostDAO postdao = (PostDAO) webAppContext.getBean("PostDAO");
			PostBean postBean = postdao.getByPrimaryKey(1);
			noticeServ.insertWarningNotice(postBean, "沒有理由，你就是被檢舉拉");
			pw.write("good");
		} else if("friend_notice_icon".equals(noticeType)) {			//取得加友通知
			List<FriendshipNoticeBean> fsNoticeList = noticeServ.getFriendshipNotice(user);
			
			StringBuffer strbuff = new StringBuffer();
			strbuff.append("[");
			int i = 0;
			for(FriendshipNoticeBean fsNotice : fsNoticeList) {
				strbuff.append("{");
				strbuff.append("'fri_notid':'"+ fsNotice.getFri_notid() +"'");
				strbuff.append(",");
				strbuff.append("'m_id_to':'"+ fsNotice.getMember_to().getM_id() +"'");
				strbuff.append(",");
				strbuff.append("'m_id_from':'"+ fsNotice.getMember_from().getM_id() +"'");
				strbuff.append(",");
				strbuff.append("'m_name_from':'"+ fsNotice.getM_name_from() +"'");
				strbuff.append(",");
				strbuff.append("'fri_read':'"+ fsNotice.getFri_read() +"'");
				strbuff.append(",");
				strbuff.append("'fri_type':'"+ fsNotice.getFri_type() +"'");
				strbuff.append("}");
				if(++i < fsNoticeList.size())
					strbuff.append(",");
			}
			strbuff.append("]");
			noticeServ.changeReadFriendshipNotice(fsNoticeList);		//改變為已讀
//			System.out.println(strbuff.toString());
			pw.write(strbuff.toString());
		} else if("response_notice_icon".equals(noticeType)) {			//取得回覆通知
			List<ResponseNoticeBean> respNoticeList = noticeServ.getResponseNotice(user);
			PostDAO postdao = (PostDAO) webAppContext.getBean("PostDAO");
			
			StringBuffer strbuff = new StringBuffer();
			strbuff.append("[");
			int i = 0;
			for(ResponseNoticeBean respNotice : respNoticeList) {
				PostBean post = postdao.getByPrimaryKey(respNotice.getPost_id());
				strbuff.append("{");
				strbuff.append("'res_notid':'"+ respNotice.getRes_notid() +"'");
				strbuff.append(",");
				strbuff.append("'m_id_to':'"+ respNotice.getM_id_to() +"'");
				strbuff.append(",");
				strbuff.append("'m_id_from':'"+ respNotice.getM_id_from() +"'");
				strbuff.append(",");
				strbuff.append("'m_name_from':'"+ respNotice.getM_name_from() +"'");
				strbuff.append(",");
				strbuff.append("'res_read':'"+ respNotice.getRes_read() +"'");
				strbuff.append(",");
				strbuff.append("'post_id':'"+ respNotice.getPost_id() +"'");
				strbuff.append(",");
				strbuff.append("'post_content':'"+ post.getPost_content() +"'");		//貼文內容
				strbuff.append(",");
				strbuff.append("'res_id':'"+ respNotice.getRes_notid() +"'");
				strbuff.append("}");
				if(++i < respNoticeList.size())
					strbuff.append(",");
			}
			strbuff.append("]");
			noticeServ.changeReadResponseNotice(respNoticeList);		//改變為已讀
//			System.out.println(strbuff.toString());
			pw.write(strbuff.toString());
		} else if("activity_notice_icon".equals(noticeType)) {			//取得活動通知
			List<ActivityNoticeBean> actNoticeList = noticeServ.getActivityNotice(user);
			ActivityDAO actdao = (ActivityDAO) webAppContext.getBean("ActivityDAO");
			MemberDAO memdao = (MemberDAO) webAppContext.getBean("MemberDAO");
			
			StringBuffer strbuff = new StringBuffer();
			int i = 0;
			Set<Integer> notice_actId_set = new HashSet<Integer>();		//記錄該會員的活動通知之活動id
			strbuff.append("[");
			for(ActivityNoticeBean actNotice : actNoticeList) {
				ActivityBean act = actdao.getByPrimaryKey(actNotice.getActivity().getAct_id());	//取得該通知所屬的活動
				MemberBean mem = memdao.getByPrimaryKey(act.getMember().getM_id());			//取得該活動創辦人
				String act_notContent;					//活動通知內容
				if(act.getAct_property() == 2) {			//如果該活動被刪除(property = 2)
					act_notContent = "cancel";
				} else {
					if((notice_actId_set.contains(actNotice.getActivity().getAct_id()))) {	//如果set中已存有此活動id，表示此通知為活動修改通知
						act_notContent = "update";
					} else {
						act_notContent = "invite";			//如果set中未有此活動id，表示此通知為活動邀請通知
						notice_actId_set.add(actNotice.getActivity().getAct_id());
					}
				}
				strbuff.append("{");
				strbuff.append("'act_notid':'"+ actNotice.getAct_notid() +"'");
				strbuff.append(",");
				strbuff.append("'act_id':'"+ actNotice.getActivity().getAct_id() +"'");
				strbuff.append(",");
				strbuff.append("'act_title':'"+ act.getAct_title() +"'");		//活動標題
				strbuff.append(",");
				strbuff.append("'act_read':'"+ actNotice.getAct_read() +"'");
				strbuff.append(",");
				strbuff.append("'act_time':'"+ actNotice.getAct_time().toString() +"'");
				strbuff.append(",");
				strbuff.append("'act_notContent':'"+ act_notContent +"'");
				strbuff.append(",");
				strbuff.append("'act_host':'"+ mem.getM_name() +"'");			//活動創辦人的名字
				strbuff.append("}");
				if(++i < actNoticeList.size())
					strbuff.append(",");
			}
			strbuff.append("]");
			noticeServ.changeReadActivityNotice(actNoticeList);		//改變為已讀
//			System.out.println(strbuff.toString());
			pw.write(strbuff.toString());
		} else if("warning_notice_icon".equals(noticeType)) {			//取得被警告通知
			List<WarningNoticeBean> warNoticeList = noticeServ.getWarningNotice(user);
			
			StringBuffer strbuff = new StringBuffer();
			strbuff.append("[");
			int i = 0;
			for(WarningNoticeBean warNotice : warNoticeList) {
				strbuff.append("{");
				strbuff.append("'war_notid':'"+ warNotice.getWar_notid() +"'");
				strbuff.append(",");
				strbuff.append("'post_content':'"+ warNotice.getPost_content() +"'");
				strbuff.append(",");
				strbuff.append("'rep_cause':'"+ warNotice.getRep_cause() +"'");
				strbuff.append(",");
				strbuff.append("'war_read':'"+ warNotice.getWar_read() +"'");
				strbuff.append("}");
				if(++i < warNoticeList.size())
					strbuff.append(",");
			}
			strbuff.append("]");
			noticeServ.changeReadWarningNotice(warNoticeList);		//改變為已讀
//			System.out.println(strbuff.toString());
			pw.write(strbuff.toString());
		} else if("notice_count".equals(noticeType)) {
			Map<String, Integer> unreadNoticeMap = noticeServ.getUnreadNoticeCount(user);
			JSONArray json = JSONArray.fromObject(unreadNoticeMap);
//			System.out.println(json.toString());
			pw.write(json.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
