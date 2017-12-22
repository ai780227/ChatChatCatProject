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

public class JoinOrQuit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//呼叫session
		HttpSession session =request.getSession();
		//取得該會員資料
		MemberBean mem = (MemberBean)session.getAttribute("user");
		//建立ActivityService物件
		ActivityService actService = new ActivityService();
		//找到該活動
		Integer act_id = Integer.valueOf(request.getParameter("Act_id"));
		System.out.println(act_id);
		ActivityBean act = actService.getByPrimaryKeyAct(act_id);
		//判斷動作
		if(request.getParameter("switch").equals("參加該活動")){
			actService.joinAct(mem, act);
			if(request.getParameter("where")!=null){
				request.setAttribute("fromViewAct", act);
				RequestDispatcher view = request.getRequestDispatcher("/ShowThisAct.do");
				view.forward(request, response);
				return;
			}else{
				System.out.println("---");
				RequestDispatcher view = request.getRequestDispatcher("/ForViewPubActPage.do");
				view.forward(request, response);
				return;
			}
		}else{
			actService.quitAct(mem, act);
			if(act.getAct_property()==1){
				RequestDispatcher view = request.getRequestDispatcher("/ForViewPubActPage.do");
				view.forward(request, response);
				return;
			}else{
				RequestDispatcher view = request.getRequestDispatcher("/ForViewPriActPage.do");
				view.forward(request, response);
				return;
			}
		}
	}

}
