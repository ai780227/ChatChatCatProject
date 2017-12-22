package com.ccc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.service.ActivityService;

public class ForSearchActServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		//取得session
		HttpSession session = request.getSession();
		//取得會員
		MemberBean mem = (MemberBean)session.getAttribute("user");
		// 建立ActivityService物件
		ActivityService actService = new ActivityService();
		//搜尋已經參加的活動
		List<ActivityBean> vrg = actService.viewReadyToGoPublicActs(mem);
		//取得網頁使用者輸入的字串
		String str = request.getParameter("Act");
		//結合地點標題的搜尋
		List<ActivityBean> as = actService.viewActivitiesByString(str);	
		try {
			//搜尋過後可以參加的活動
			List<ActivityBean> act_list = actService.viewAfterSearchICanJoin(vrg, as);
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			JSONObject acts = null;
			for (int i = 0; i < act_list.size(); i++) {
				acts = new JSONObject();
				acts.put("act_id", act_list.get(i).getAct_id().toString());
				acts.put("act_title", act_list.get(i).getAct_title());
				acts.put("act_time", act_list.get(i).getAct_time().toString());
				acts.put("act_location", act_list.get(i).getAct_location());
				acts.put("act_content", act_list.get(i).getAct_content());
				array.add(acts);
			}
			json.put("forSearch", array);
			out.print(json);
//			System.out.println("json array :" + array.toString());
		} catch (NullPointerException e) {
			out.print("");
//			System.out.println("沒有活動資料");
		} finally {
			out.close();
		}
	}
}
