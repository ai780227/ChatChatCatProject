package com.ccc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.SysexMessage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ccc.model.bean.BoardBean;
import com.ccc.model.bean.ManagerBean;
import com.ccc.model.service.BoardService;


public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("BoardServlet doPost");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		BoardService bs=new BoardService();//new BoardService物件
		String content=request.getParameter("content");//拿到公告的內容
		System.out.println(content);
		
		//System.out.println(content);
		BoardBean bo=new BoardBean();//new BoardBean物件
		bo.setBoard_content(content);//編輯公告或新增公告設值
		
		Timestamp ts=new Timestamp(System.currentTimeMillis());//設定時間
		bo.setBoard_time(ts);
		
		HttpSession session=request.getSession();
		ManagerBean mgr = (ManagerBean) session.getAttribute("manager");
//		String mgr_id=mgr.getMgr_id();
//		System.out.println(mgr_id);
		bo.setMgr_id(mgr.getMgr_id());//設定管理員ID
	
		String btnType = request.getParameter("update");//判斷是刪除公告或新增公告
		System.out.println("btnType = " + btnType);
		
		BoardBean boa = null;
		if(btnType.equals("delete")){//假如按的按鈕是「刪除公告」
			System.out.println("進入刪除功能servlet");
			String board_id=request.getParameter("id");//拿到要刪除公告的ID
			int boa_id=Integer.parseInt(board_id.trim());//將型態為字串的公告ID轉成整數型態
			bo.setBoard_id(boa_id);//設定公告ID
			boa=bs.deleteBoard(bo);//刪除公告
		}else if(btnType.equals("確認修改")||btnType.equals("新增")){//假如按的按鈕是「確認修改」
			if(btnType.equals("確認修改")){
				System.out.println("else if 確認修改");
				String board_id=request.getParameter("id");//拿到要修改公告的ID
				System.out.println("board_id = " + board_id);
				int boa_id=Integer.parseInt(board_id.trim());//將型態為字串的公告ID轉成整數型態
				bo.setBoard_id(boa_id);//設定公告ID
				bs.deleteBoard(bo);//先刪除原先的公告
				boa=bs.AddBoard(bo);//加入修改的公告
			
			}else if(btnType.equals("新增")){
				System.out.println("確認新增");
				boa=bs.AddBoard(bo);
			}
			
			if(boa!=null){
				RequestDispatcher rs = request.getRequestDispatcher("/pages/ManagerBoard.jsp");
				if(rs==null){
				System.out.println("轉到原來頁面");
				}
				 rs.forward(request, response);
			}

			
			try{
				if(boa!=null){
//					JSONObject ob=new JSONObject();
//					JSONObject json=new JSONObject();
//					JSONArray array=new JSONArray();
//					json.put("id",boa.getBoard_id());
//					json.put("content",boa.getBoard_content());
//					json.put("time",boa.getBoard_time().toString());
//					array.add(json);
//					
//					ob.put("board", array);
//					System.out.println("jsonArray:"+array.toString());
					
					String boardContent=boa.getBoard_content();
					String boardTime=boa.getBoard_time().toString();
					int id=bo.getBoard_id();
					System.out.println("成功抓到編輯");
					
					out.print("<div class='block' id='board"+id+"'>"
							+"<div class='content' id='content'>"+boardContent+"</div>"							
							+"<img src='../images/delete.png' class='deleteiconinvisible'>"
							+"<img src='../images/edit.png' class='editiconinvisible' id='edit"+id+">"
							+"<div class='time'>"
							+"<span class='timetext'>時間:"+boardTime+"</span>"
							+"</div>"
							+"<div>"+id+"</div>"
							+"</div>"
							+"<input type='hidden' name='boid' value="+id+">"
							);
				}
			}catch(NullPointerException e){
				out.print("");
				System.out.println("抓不到資料");
			}finally{
				out.close();
			}
		}
		
		
	}

}
