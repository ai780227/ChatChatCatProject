package com.ccc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.service.ActivityService;

public class ForViewHisActPage extends HttpServlet {
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
		//瀏覽該會員歷史活動
		List<ActivityBean> acts = actService.viewHistoryActs(mem);
		//結果放入request
		request.setAttribute("Acts", acts);
		//查詢活動比數
		Integer length = acts.size();
		//活動比數放入request
		request.setAttribute("size", length);			
		//轉交頁面
		RequestDispatcher view = request.getRequestDispatcher("/pages/viewHistoryAct.jsp");
		view.forward(request, response);
	}

}
