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

import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.bean.PostPictureBean;
import com.ccc.model.bean.ResponseBean;
import com.ccc.model.bean.WhoLikeBean;
import com.ccc.model.service.ActivityService;
import com.ccc.model.service.MemberService;
import com.ccc.model.service.PostService;

//@WebServlet("/ShowThisActServlet")
public class ShowThisActJSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		//建立session
		HttpSession session = request.getSession();
		//建立ActivityService物件
		ActivityService actService = new ActivityService();
		//抓取該活動ID
		Integer act_id = Integer.valueOf(request.getParameter("Act_id"));
		System.out.println(act_id);
		//抓取該活動
		ActivityBean act = actService.getByPrimaryKeyAct(act_id);
		//把該活動存入request
		request.setAttribute("thisAct", act);
		//取得該活動參加人數
		Integer length = actService.viewCountOfTheActWhoJoin(act);
		//把活動參加人數存入request
		request.setAttribute("count", length);

		//////////////////////////////////////////
		try {
			response.reset();
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");

//			MemberBean mb = (MemberBean)req.getSession().getAttribute("mb");
			int page, size, post_type, post_property = 0;
			List<PostBean> lpb;

			PostService ps = new PostService();
			PostBean pb;

			MemberBean mb = (MemberBean)session.getAttribute("user");
			System.out.println(mb.getM_id());
			page = Integer.parseInt(request.getParameter("page"));
			size = Integer.parseInt(request.getParameter("size"));

			post_type = 0;
			post_property = 5;
			pb = new PostBean();
			pb.setPost_type(post_type);
			pb.setPost_property(post_property);

			lpb = ps.viewActPosts(mb, act_id, page, size);
			request.setAttribute("post_type", post_type);
			request.setAttribute("post_property", post_property);
			request.setAttribute("act_id", act_id);
			request.setAttribute("posts", lpb);

			Iterator<PostBean> ipb = lpb.iterator();
			List<PostPictureBean> lppb = new ArrayList<PostPictureBean>();
			MemberService ms = new MemberService();
			List<ResponseBean> lresb = new ArrayList<ResponseBean>();
			List<WhoLikeBean> lwlb = new ArrayList<WhoLikeBean>();
			while (ipb.hasNext()) {
				pb = ipb.next();
				lppb.addAll(ps.viewPostPictures(pb));
				lresb.addAll(ms.getResponse(pb));
				lwlb.addAll(ms.getWhoLike(pb));
			}
//-----------------------------------------------   JSON   -----------------------------------------------
			PrintWriter pw = response.getWriter();
			JSONObject json = new JSONObject();
			JSONArray aPost = new JSONArray(), aPic = new JSONArray(), aRes = new JSONArray(), aWhoLike = new JSONArray();
			JSONObject post = null, pic = null, res = null, whoLike = null;
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
					res.put("m_id", lresb.get(i).getMemberBean().getM_id());
					res.put("m_pic_path", lresb.get(i).getMemberBean().getM_pic_path());
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
			pw.close();
//-------------------------------------------------   END   ------------------------------------------------
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		//////////////////////////////////////////


	}
}
