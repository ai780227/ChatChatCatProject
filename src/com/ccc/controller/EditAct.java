package com.ccc.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.dao.MemberDAO;
import com.ccc.model.service.ActivityService;

/**
 * Servlet implementation class EditAct
 */

public class EditAct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 呼叫ActivityService
		ActivityService actService = new ActivityService();
		// 取得要編輯活動的act_id
		Integer act_id = Integer.valueOf(request.getParameter("act_id"));
		// 取得該活動
		ActivityBean act = actService.getByPrimaryKeyAct(act_id);
		// 編輯該活動
		act.setAct_title(request.getParameter("act_title"));
		act.setAct_location(request.getParameter("act_location"));
		act.setAct_content(request.getParameter("act_content"));
		// 處理日期轉換
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date act_time = sdf.parse(request.getParameter("act_time")
					.toString());
			act.setAct_time(new Timestamp(act_time.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
//			System.out.println("日期轉換錯誤");
		}
		// 更新此活動
		actService.editAct(act);
		//如果有點選擇好友,則新增活動關係人以及活動通知以及並且把好友名單放入request
		if(request.getParameterValues("friendsName")!=null){
			String[] formdata  = request.getParameterValues("friendsName");
			List<String> myFriendsId = Arrays.asList(formdata);
			actService.createPars(myFriendsId,act);
			actService.createNots(myFriendsId,act);
		}	
		// 轉移頁面
		RequestDispatcher view = request
				.getRequestDispatcher("/ForManageActPage.do");
		view.forward(request, response);
	}

}
