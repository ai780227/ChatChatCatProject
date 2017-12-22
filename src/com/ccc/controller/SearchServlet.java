package com.ccc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.bean.PostPictureBean;
import com.ccc.model.bean.ResponseBean;
import com.ccc.model.bean.WhoLikeBean;
import com.ccc.model.service.MemberService;
import com.ccc.model.service.PostService;
import com.ccc.model.service.SearchService;

/**
 * Servlet implementation class SearchServlet
 */
//@WebServlet("/SearchServlet")

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		HttpSession httpSession = request.getSession();
		MemberBean memberBean_from = (MemberBean) httpSession.getAttribute("user");
		PrintWriter out = response.getWriter();

		//===================================測試用==========================================
		//抓取使用者的memberBean
//		ServletContext  session =request.getServletContext();
//		MemberBean memberBean_from=(MemberBean) session.getAttribute("user");
		//==================================以上測試用========================================

		//公開性質預設為
		int post_property = 0;
		// 貼文分類 0)所有 1)一般動態 2)知識 3)領養 4)貓咪店家 5)活動
		int post_type = Integer.parseInt(request.getParameter("post_type"));
		int page = Integer.parseInt((String)request.getParameter("page"));
		int size = Integer.parseInt((String)request.getParameter("size"));



		//如果貼文分類不是"新聞"、"活動"，就抓取公開性質"公開"、"私人"、(不抓取"封鎖")
		if(post_type!=5 && post_type!=6){

			 post_property = Integer.parseInt(request.getParameter("post_property"));
		}
		//建立一個回傳用postBean_list
		List<PostBean> postBean_list = new ArrayList<PostBean>();
		List<PostPictureBean> postPictureBean_list=null;
		// 建立SearchService
		SearchService searchService = new SearchService();
		//================================  貼文分類 0)所有  =================================================
		if (post_type==0) {
			// 取的搜尋關鍵字
			String search_str = request.getParameter("text");
			if(search_str.isEmpty()){
//				request.setAttribute("postK", null);
//				getServletContext().getRequestDispatcher("/pages/Search_Post_AllType.jsp").forward(
//						 request, response);
				out.print("");
			}else{
				try{
					// 搜尋貼文
					postBean_list = searchService.searchPost(search_str, post_type,
							memberBean_from, post_property,page,size);
					//System.out.println("postBean_list is :" + postBean_list.size());
					if (!postBean_list.isEmpty() && postBean_list.size()!=0) {
						postPictureBean_list =viewPostPicture(postBean_list);
//						request.setCharacterEncoding("UTF-8");
//						request.setAttribute("posts", postBean_list);
//						request.setAttribute("postPictures", postPictureBean_list);
//						request.setAttribute("post_type", post_type);
//						request.setAttribute("friend_type", post_property);
//						request.setAttribute("userId", memberBean_from.getM_id());
//						request.setAttribute("text", search_str);
//						getServletContext().getRequestDispatcher("/pages/Search_Post_AllType.jsp").forward(
//							 request, response);

						postPictureBean_list =viewPostPicture(postBean_list);

					}else {
						out.print("");

//						request.setAttribute("posts", null);
//						getServletContext().getRequestDispatcher("/pages/Search_Post_AllType.jsp").forward(
//								request, response);
					}
					toJSON(postBean_list, postPictureBean_list, response);
				}catch(NullPointerException e){
					out.print("");
//				request.setAttribute("posts", null);
//				getServletContext().getRequestDispatcher("/pages/Search_Post_AllType.jsp").forward(
//						request, response);
				}
			}
		//================================  貼文分類 1)一般動態  ================================================
		} else if (post_type==1) {
			// 取的搜尋關鍵字
			String search_str = request.getParameter("text");
			if(search_str.isEmpty()){
				request.setAttribute("posts", null);
			}else{
				try{
				postBean_list = searchService.searchPost(search_str, post_type,
						memberBean_from, post_property,page,size);
				//判斷回傳是否為空值
				if (!postBean_list.isEmpty() && postBean_list.size()!=0) {
					postPictureBean_list =viewPostPicture(postBean_list);
//					request.setCharacterEncoding("UTF-8");
//					request.setAttribute("posts", postBean_list);
//					request.setAttribute("postPictures", postPictureBean_list);
//					request.setAttribute("post_type", post_type);
//					request.setAttribute("friend_type", post_property);
//					request.setAttribute("userId", memberBean_from.getM_id());
//					request.setAttribute("text", search_str);
//					getServletContext().getRequestDispatcher("/pages/Search_Post_LiveType.jsp").forward(
//							request, response);
					postPictureBean_list =viewPostPicture(postBean_list);
				} else {
					out.print("");
//					request.setAttribute("posts", null);
//					getServletContext().getRequestDispatcher("/pages/Search_Post_LiveType.jsp").forward(
//							request, response);
				}
				toJSON(postBean_list, postPictureBean_list, response);
				}catch(NullPointerException e){
					out.print("");
//					request.setAttribute("posts", null);
//					getServletContext().getRequestDispatcher("/pages/Search_Post_LiveType.jsp").forward(
//							request, response);
				}
			}
		//================================  貼文分類 2)知識  =======================================================
		} else if (post_type==2) {
			// 取的搜尋關鍵字
			String search_str = request.getParameter("text");
			if(search_str.isEmpty()){
				request.setAttribute("posts", null);
			}else{
				try{
				postBean_list = searchService.searchPost(search_str, post_type,
						memberBean_from, post_property,page,size);
				if (!postBean_list.isEmpty() && postBean_list.size()!=0) {
					postPictureBean_list =viewPostPicture(postBean_list);
//					request.setCharacterEncoding("UTF-8");
//					request.setAttribute("posts", postBean_list);
//					request.setAttribute("postPictures", postPictureBean_list);
//					request.setAttribute("post_type", post_type);
//					request.setAttribute("friend_type", post_property);
//					request.setAttribute("userId", memberBean_from.getM_id());
//					request.setAttribute("text", search_str);
//					getServletContext().getRequestDispatcher("/pages/Search_Post_Knowledge.jsp").forward(
//							request, response);
					postPictureBean_list =viewPostPicture(postBean_list);
				} else {
					out.print("");
//					request.setAttribute("posts", null);
//					getServletContext().getRequestDispatcher("/pages/Search_Post_Knowledge.jsp").forward(
//							request, response);
					}
				toJSON(postBean_list, postPictureBean_list, response);
				}catch(NullPointerException e){
					out.print("");
//					request.setAttribute("posts", null);
//					getServletContext().getRequestDispatcher("/pages/Search_Post_Knowledge.jsp").forward(
//							request, response);
				}
			}
		//================================  貼文分類 3)領養  =======================================================
		} else if (post_type==3) {
			// 取的搜尋關鍵字
			String search_str = request.getParameter("text");
			if(search_str.isEmpty()){
				request.setAttribute("posts", null);
			}else{
				try{
				postBean_list = searchService.searchPost(search_str, post_type,
						memberBean_from, post_property,page,size);
				if (!postBean_list.isEmpty() && postBean_list.size()!=0) {
					postPictureBean_list =viewPostPicture(postBean_list);
//					request.setCharacterEncoding("UTF-8");
//					request.setAttribute("posts", postBean_list);
//					request.setAttribute("postPictures", postPictureBean_list);
//					request.setAttribute("post_type", post_type);
//					request.setAttribute("friend_type", post_property);
//					request.setAttribute("userId", memberBean_from.getM_id());
//					request.setAttribute("text", search_str);
//					getServletContext().getRequestDispatcher("/pages/Search_Post_Adoption.jsp").forward(
//							request, response);
					postPictureBean_list =viewPostPicture(postBean_list);
				} else {
					out.print("");
//					request.setAttribute("posts", null);
//					getServletContext().getRequestDispatcher("/pages/Search_Post_Adoption.jsp").forward(
//							request, response);
					}
				toJSON(postBean_list, postPictureBean_list, response);
				}catch(NullPointerException e){
					out.print("");
				}
//					request.setAttribute("posts", null);
//					getServletContext().getRequestDispatcher("/pages/Search_Post_Adoption.jsp").forward(
//							request, response);
//				}
			}
		//================================  貼文分類 4)貓咪店家  =======================================================
		} else if (post_type==4) {
			// 取的搜尋關鍵字
			String search_str = request.getParameter("text");
			if(search_str.isEmpty()){
				request.setAttribute("Search_Post", null);
			}else{
				try{
					postBean_list = searchService.searchPost(search_str, post_type,
							memberBean_from, post_property,page,size);
					if (!postBean_list.isEmpty() && postBean_list.size()!=0) {
						postPictureBean_list =viewPostPicture(postBean_list);
//						request.setCharacterEncoding("UTF-8");
//						request.setAttribute("posts", postBean_list);
//						request.setAttribute("postPictures", postPictureBean_list);
//						request.setAttribute("post_type", post_type);
//						request.setAttribute("friend_type", post_property);
//						request.setAttribute("userId", memberBean_from.getM_id());
//						request.setAttribute("text", search_str);
//						getServletContext().getRequestDispatcher("/pages/Search_Post_CatsStores.jsp").forward(
//								request, response);
						postPictureBean_list =viewPostPicture(postBean_list);
					} else {
						out.print("");
//						request.setAttribute("posts", null);
//						getServletContext().getRequestDispatcher("/pages/Search_Post_CatsStores.jsp").forward(
//								request, response);
					}
					toJSON(postBean_list, postPictureBean_list, response);
				}catch(NullPointerException e){
					out.print("");
//					request.setAttribute("posts", null);
//					getServletContext().getRequestDispatcher("/pages/Search_Post_CatsStores.jsp").forward(
//							request, response);
				}



			}
		}
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


	public List<PostPictureBean> viewPostPicture(List<PostBean> postBean_list){
		//建立一個PostPictureBean_list
		List<PostPictureBean> lppb = new ArrayList<PostPictureBean>();
		//建立一個迭代器
		Iterator<PostBean> ipb = postBean_list.iterator();
		//建立一個PostBean
		PostBean pb = new PostBean();
		PostService ps = new PostService();
		while (ipb.hasNext()) {
			pb = ipb.next();
			lppb.addAll(ps.viewPostPictures(pb));
		}
		return lppb;
	}




	//====================測試AJAX===============================
	@SuppressWarnings("unchecked")
	public void toJSON(List<PostBean> lpb, List<PostPictureBean> lppb, HttpServletResponse response) {
		try {
			PrintWriter pw = response.getWriter();
			JSONObject json = new JSONObject();
			JSONArray aPost = new JSONArray(), aPic = new JSONArray(), aRes = new JSONArray(), aWhoLike = new JSONArray();
			JSONObject post = null, pic = null, res = null, whoLike = null;
			MemberService ms = new MemberService();
			MemberBean mb = new MemberBean();
			PostBean pb = new PostBean();
			Iterator<PostBean> ipb = lpb.iterator();
			List<ResponseBean> lresb = new ArrayList<ResponseBean>();
			List<WhoLikeBean> lwlb = new ArrayList<WhoLikeBean>();
			while (ipb.hasNext()) {
				pb = ipb.next();
				lresb.addAll(ms.getResponse(pb));
				lwlb.addAll(ms.getWhoLike(pb));
			}
			for(int i = 0; i < lpb.size(); i++) {
				mb = ms.getMemberInfoByPost(lpb.get(i));
				post = new JSONObject();
				post.put("post_id", lpb.get(i).getPost_id());
				post.put("like_count", lpb.get(i).getLike_count());
				post.put("m_id", lpb.get(i).getMemberBean().getM_id());
				post.put("m_name", mb.getM_name());
				post.put("m_pic_path", mb.getM_pic_path());
				post.put("post_content", lpb.get(i).getPost_content());
				post.put("post_type", lpb.get(i).getPost_type());
				post.put("post_property", lpb.get(i).getPost_property());
				post.put("post_time", lpb.get(i).getPost_time().toString());
				if (lpb.get(i).getActivityBean() != null) {
					post.put("act_id", lpb.get(i).getActivityBean().getAct_id());
				}
				aPost.add(post);
			}
			json.put("posts", aPost);

			if(lppb != null) {
				for(int i = 0; i < lppb.size(); i++) {
					pic = new JSONObject();
					pic.put("post_id", lppb.get(i).getPostBean().getPost_id());
					pic.put("m_id", lppb.get(i).getPostBean().getMemberBean().getM_id());
					pic.put("pic_file_path", lppb.get(i).getPictureBean().getPic_file_path());
					aPic.add(pic);
				}
			}
			json.put("pics", aPic);

			if(lresb != null) {
				for(int i = 0; i < lresb.size(); i++) {
					res = new JSONObject();
					res.put("res_id", lresb.get(i).getRes_id());
					res.put("post_id", lresb.get(i).getPostBean().getPost_id());
					res.put("m_name", lresb.get(i).getMemberBean().getM_name());
					res.put("res_content", lresb.get(i).getRes_content());
					res.put("res_time", lresb.get(i).getRes_time().toString());
					aRes.add(res);
				}
			}
			json.put("res", aRes);

			if(lwlb != null) {
				for(int i = 0; i < lwlb.size(); i++) {
					whoLike = new JSONObject();
					whoLike.put("post_id", lwlb.get(i).getPost().getPost_id());
					whoLike.put("m_id", lwlb.get(i).getMember().getM_id());
					whoLike.put("m_name", lwlb.get(i).getM_name());
					aWhoLike.add(whoLike);
				}
			}
			json.put("whoLikes", aWhoLike);

			json.put("post_size", lpb.size());

			pw.print(json.toString());

//			System.out.println("json array :"+aPost.toString());
//			System.out.println("json object :"+json.toString());
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}


//================================  貼文分類 5)活動 ============================================================
//} else if (post_type==5) {
//	// 取的搜尋關鍵字
//	String search_str = request.getParameter("text");
//	System.out.println(search_str);
//	List<MemberBean> memberBean_list = searchService
//			.searchMember(search_str);
//	if (memberBean_list != null && memberBean_list.size()!=0) {
//		request.setAttribute("Members", memberBean_list);
//		for(int i =0;i<memberBean_list.size();i++){
//			 System.out.println(memberBean_list.get(i).getM_id());
//			 }
//		 getServletContext().getRequestDispatcher("/SearchRe.jsp").forward(
//		 request, response);
//	} else {
//		request.setAttribute("Members", null);
//	}
//

//================================  貼文分類 6)新聞  ============================================================
//else if(post_type==6){
//	String search_str = request.getParameter("text");
//	List<NewsBean> newBean_list=searchService.searchNews(search_str);
//	if (newBean_list != null && newBean_list.size()!=0) {
//		request.setAttribute("searchNews", newBean_list);
//		getServletContext().getRequestDispatcher("/pages/Search_Post_Adoption.jsp").forward(
//				 request, response);
//	} else {
//		request.setAttribute("searchNews", null);
//		getServletContext().getRequestDispatcher("/pages/Search_Post_Adoption.jsp").forward(
//				 request, response);
//	}
//
//}