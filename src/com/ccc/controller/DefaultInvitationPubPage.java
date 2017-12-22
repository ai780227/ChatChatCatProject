package com.ccc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.dao.MemberDAO;
import com.ccc.model.service.ActivityService;

/**
 * Servlet implementation class Test
 */
public class DefaultInvitationPubPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 呼叫session
		HttpSession session = request.getSession();
		// 取得會員資料
		MemberBean mem = (MemberBean)session.getAttribute("user");	
		// 呼叫ActivityService
		ActivityService actService = new ActivityService();		
		//設定瀏覽參數
		Integer actp = Integer.valueOf(1);
		request.setAttribute("theActP", actp);
		// 瀏覽所有尚未過期且該會員已被邀請之公開活動(預設頁面)
		List<ActivityBean> pubActs = actService.viewTheInviteOfPub(mem);
		request.setAttribute("myActsInvite", pubActs);
		// 把活動比數放入request
		Integer length = pubActs.size();
		request.setAttribute("size", length);
		// 轉交頁面
		RequestDispatcher view = request
				.getRequestDispatcher("/pages/invitationReceived.jsp");
		view.forward(request, response);
	}

}
