package com.ccc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.service.SearchService;

//@WebServlet("/SearchMembersServlet.do")
public class SearchMembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SearchMembersServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//request.getAttribute("term");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		String member=(String)request.getParameter("Member");
		int post_type=Integer.parseInt(request.getParameter("post_type"));
//				(String)request.getParameter("post_type");
		
		SearchService searchservice=new SearchService();
		//搜尋會員
		if(post_type==5){
			try{
				List<MemberBean> memberBean_list=searchservice.searchMember(member);
				
				if(!memberBean_list.isEmpty() || memberBean_list.size()!=0){
					
					JSONObject json = new JSONObject(); 
					JSONArray array = new JSONArray();  
					 JSONObject members = null;  
					 for (int i = 0; i < memberBean_list.size(); i++) {  
				            members = new JSONObject();  
				            members.put("member_id",memberBean_list.get(i).getM_id());  
				            members.put("member_name", memberBean_list.get(i).getM_name()); 
				            members.put("member_pic_path", memberBean_list.get(i).getM_pic_path());  
				            array.add(members);  
				            
				        }
					 json.put("account", member);  
					 json.put("jsonArray", array);  
					 pw.print(json.toString());  
					
//					 System.out.println("json array :"+array.toString());  
					 pw.close(); 
//					JSONArray json = JSONArray.fromObject(toJSON(memberBean_list)); 
//					System.out.println(json);
//					pw.print(json);
				}
			}catch(NullPointerException e){
				pw.print("");
				pw.close(); 
//				System.out.println("抓不到資料");
			}
			
		}
	}

	public String toJSON (List<MemberBean> memberBean_list){
		String tojson="";
		for(int i =0;i<memberBean_list.size();i++){
			if(i==0){
				tojson=tojson+"[";
			}else{
				tojson=tojson+",";
			}
			tojson=tojson+"{'m_name':'"+memberBean_list.get(i).getM_name()+"',"+"'m_id':'"+memberBean_list.get(i).getM_id()+"',"+"'m_pic_path':'"+memberBean_list.get(i).getM_pic_path()+"'}";
			if(i==memberBean_list.size()-1){
				tojson=tojson+"]";
			}
		
		}
//		System.out.println("測試tojson:"+tojson);
		return tojson;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
