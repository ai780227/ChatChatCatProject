package com.ccc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.service.ActivityService;

/**
 * Servlet implementation class ProcessedInvitation
 */

public class ProcessedInvitation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		//呼叫session
		HttpSession session = request.getSession();
		//呼叫ActivityService物件
		ActivityService actService = new ActivityService();
		//讀取該會員
		MemberBean mem = (MemberBean)session.getAttribute("user");
		//找到該活動
		Integer act_id = Integer.valueOf(request.getParameter("Act_id"));
		ActivityBean act = actService.getByPrimaryKeyAct(act_id);
		//處理請求
		if(request.getParameter("intention").equals("接受邀請")){
			actService.joinAct(mem, act);
			if(act.getAct_property()==0){
				RequestDispatcher view = request.getRequestDispatcher("/InvitationPriPage.do");
				view.forward(request, response);
				return;		
			}else{
				RequestDispatcher view = request.getRequestDispatcher("/DefaultInvitationPubPage.do");
				view.forward(request, response);
				return;	
			}
		}else{
			actService.quitAct(mem, act);
			if(act.getAct_property()==0){
				RequestDispatcher view = request.getRequestDispatcher("/InvitationPriPage.do");
				view.forward(request, response);
				return;		
			}else{
				RequestDispatcher view = request.getRequestDispatcher("/DefaultInvitationPubPage.do");
				view.forward(request, response);
				return;	
			}
		}
		
	}

}
