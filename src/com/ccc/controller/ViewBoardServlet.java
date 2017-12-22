package com.ccc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ccc.model.bean.BoardBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.service.BoardService;

/**
 * Servlet implementation class ViewBoardServlet
 */

public class ViewBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    public ViewBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
//		HttpSession session=request.getSession();
//		String m_id=(String) session.getAttribute("m_id");
//		System.out.println(m_id);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		
		BoardService bs=new BoardService();
		List<BoardBean> bolist=bs.viewAllBoard();
		
		try{
			if(!bolist.isEmpty()||bolist.size()!=0){
				JSONObject json=new JSONObject();
				JSONArray array=new JSONArray();
				JSONObject items=null;
				for(int i=0;i<bolist.size();i++){
					items=new JSONObject();
					items.put("board_id",bolist.get(i).getBoard_id());
					items.put("content", bolist.get(i).getBoard_content());
					items.put("time", bolist.get(i).getBoard_time().toString());
					array.add(items);
				}
				json.put("jsonArray", array);
				out.print(json.toString());
				
				System.out.println("jsonArray:"+array.toString());
				out.close();
			}
		}catch(NullPointerException e){
			out.print("");
			out.close();
			System.out.println("抓不到資料");
		}
		
//		request.setAttribute("boardlist", bolist);
//		if(bolist!=null){
//			System.out.println("不是空值");
//			 RequestDispatcher rs = request.getRequestDispatcher("/pages/Board.jsp");
//			 RequestDispatcher rd = request.getRequestDispatcher("/pages/HomePage.jsp");
//	         rs.forward(request, response);
//	         rd.forward(request, response);
//	         
//			}else{
//				RequestDispatcher rd = request.getRequestDispatcher("/pages/RecieveBoard_error.jsp");
//		        rd.forward(request, response);
//			}
		
	}

}
