package com.ccc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.dao.MemberDAO;
import com.ccc.model.service.ActivityService;

//@WebServlet("/ForCalendarServlet")
public class ForCalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		
		/*//取得session
		HttpSession session = request.getSession();
		//取得會員
		MemberBean mem = (MemberBean)session.getAttribute("user");*/
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		MemberDAO daoOfMem = (MemberDAO) context.getBean("MemberDAO");
		MemberBean mem = daoOfMem.getByPrimaryKey("cecj002");
		
		
		//建立ActivityService物件
		ActivityService actService = new ActivityService();
		//搜尋該會員準備參加的活動
		List<ActivityBean> readyToGOPub = actService.viewReadyToGoPublicActs(mem);
		List<ActivityBean> readyToGOPri = actService.viewReadyToGoPrivateActs(mem);
		//搜尋該會員已被邀請的活動
		List<ActivityBean> inviteOfPub = actService.viewTheInviteOfPub(mem);
		List<ActivityBean> inviteOfPri = actService.viewTheInviteOfPri(mem);
		//準備塞入整合後的活動
		List<ActivityBean> readyToGO = new ArrayList<ActivityBean>();
		List<ActivityBean> invite = new ArrayList<ActivityBean>();
		//塞入活動
		Iterator<ActivityBean> itgopub = readyToGOPub.iterator();
		while(itgopub.hasNext()){
			readyToGO.add(itgopub.next());
		}
		Iterator<ActivityBean> itgopri = readyToGOPri.iterator();
		while(itgopri.hasNext()){
			readyToGO.add(itgopri.next());
		}		
		Iterator<ActivityBean> itIPub = inviteOfPub.iterator();
		while(itIPub.hasNext()){
			invite.add(itIPub.next());
		}
		Iterator<ActivityBean> itIPri = inviteOfPri.iterator();
		while(itIPri.hasNext()){
			invite.add(itIPri.next());
		}
		/*=========================準備整合後的活動=============================*/
		
		if(readyToGO.size()!=0 && invite.size()==0){
			
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();

			for(int i=0;i<readyToGO.size();i++){
				JSONObject actsToGo = new JSONObject();
				actsToGo.put("act_id", readyToGO.get(i).getAct_id().toString());
				actsToGo.put("year", readyToGO.get(i).getAct_time().toString().substring(0, 4));
				//月份index從0開始所以要-1
				actsToGo.put("month", Integer.valueOf((readyToGO.get(i).getAct_time().toString().substring(5, 7)))-1);
				actsToGo.put("day", readyToGO.get(i).getAct_time().toString().substring(8, 10));
				array.add(actsToGo);
			}
			json.put("forToGo", array);
			out.print(json);

			
		}else if(readyToGO.size()==0 && invite.size()!=0){
			
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();

			for(int i=0;i<invite.size();i++){
				JSONObject actsInvite = new JSONObject();
				actsInvite.put("act_id", invite.get(i).getAct_id().toString());
				actsInvite.put("year", invite.get(i).getAct_time().toString().substring(0, 4));
				//月份index從0開始所以要-1
				actsInvite.put("month", Integer.valueOf(invite.get(i).getAct_time().toString().substring(5, 7))-1);
				actsInvite.put("day", invite.get(i).getAct_time().toString().substring(8, 10));
				array.add(actsInvite);
			}
			json.put("forInvite", array);
			out.print(json);

			
		}else if(readyToGO.size()!=0 && invite.size()!=0){
			
			JSONObject json = new JSONObject();
			JSONObject json1 = new JSONObject();
			JSONArray array = new JSONArray();
			JSONArray array2 = new JSONArray();
			
			for(int i=0;i<readyToGO.size();i++){
				JSONObject actsToGo = new JSONObject();
				actsToGo.put("act_id", readyToGO.get(i).getAct_id().toString());
				actsToGo.put("year", readyToGO.get(i).getAct_time().toString().substring(0, 4));
				//月份index從0開始所以要-1
				actsToGo.put("month", Integer.valueOf((readyToGO.get(i).getAct_time().toString().substring(5, 7)))-1);
				actsToGo.put("day", readyToGO.get(i).getAct_time().toString().substring(8, 10));
				array.add(actsToGo);
			}
					
			for(int i=0;i<invite.size();i++){
				JSONObject actsInvite = new JSONObject();
				actsInvite.put("act_id", invite.get(i).getAct_id().toString());
				actsInvite.put("year", invite.get(i).getAct_time().toString().substring(0, 4));
				//月份index從0開始所以要-1
				actsInvite.put("month", Integer.valueOf(invite.get(i).getAct_time().toString().substring(5, 7))-1);
				actsInvite.put("day", invite.get(i).getAct_time().toString().substring(8, 10));
				array2.add(actsInvite);
			}
			
			json.put("forToGo", array);	

			
			json1.put("forInvite", array2);

			
			out.print("["+json+","+json1+"]");	
		}else if(readyToGO.size()==0 && invite.size()==0){
			out.print("0");
		}
		out.close();
	}
}
