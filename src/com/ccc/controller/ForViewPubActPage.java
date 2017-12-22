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

public class ForViewPubActPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//呼叫session
		HttpSession session =request.getSession();
		MemberBean mem = (MemberBean)session.getAttribute("user");
		//建立ActivityService物件
		ActivityService actService = new ActivityService();
		//判斷動作
		if(request.getParameter("property")==null){
			//預設頁面查詢所有尚未過期公開活動
			List<ActivityBean> acts = actService.viewICanJoinPubAct(mem);
			//結果放入request
			request.setAttribute("Acts", acts);
			//查詢活動比數
			Integer length = acts.size();
			//活動比數放入request
			request.setAttribute("size", length);
			//設定button控制參數
			Integer parameter = Integer.valueOf(0);
			request.setAttribute("param", parameter);
			//轉交頁面
			RequestDispatcher view = request.getRequestDispatcher("/pages/viewPubAct.jsp");
			view.forward(request, response);
		}else if(request.getParameter("property").equals("公開活動查詢")){
			//預設頁面查詢所有尚未過期公開活動
			List<ActivityBean> acts = actService.viewICanJoinPubAct(mem);
			//結果放入request
			request.setAttribute("Acts", acts);
			//查詢活動比數
			Integer length = acts.size();
			//活動比數放入request
			request.setAttribute("size", length);
			//設定button控制參數
			Integer parameter = Integer.valueOf(0);
			request.setAttribute("param", parameter);
			//轉交頁面
			RequestDispatcher view = request.getRequestDispatcher("/pages/viewPubAct.jsp");
			view.forward(request, response);
		}else if(request.getParameter("property").equals("ALL")){
			//預設頁面查詢所有尚未過期公開活動
			List<ActivityBean> acts = actService.viewICanJoinPubAct(mem);
			//結果放入request
			request.setAttribute("Acts", acts);
			//查詢活動比數
			Integer length = acts.size();
			//活動比數放入request
			request.setAttribute("size", length);
			//設定button控制參數
			Integer parameter = Integer.valueOf(0);
			request.setAttribute("param", parameter);
			//轉交頁面
			RequestDispatcher view = request.getRequestDispatcher("/pages/viewPubAct.jsp");
			view.forward(request, response);	
		}else if(request.getParameter("property").equals("準備參加活動")){
			//瀏覽該會員準備參加之尚未過期的公開活動(參加意願為1)
			List<ActivityBean> acts = actService.viewReadyToGoPublicActs(mem);
			//結果放入request
			request.setAttribute("Acts", acts);
			//查詢活動比數
			Integer length = acts.size();
			//活動比數放入request
			request.setAttribute("size", length);
			//設定button控制參數
			Integer parameter = Integer.valueOf(1);
			request.setAttribute("param", parameter);
			//轉交頁面
			RequestDispatcher view = request.getRequestDispatcher("/pages/viewPubAct.jsp");
			view.forward(request, response);
		}
	}
}
