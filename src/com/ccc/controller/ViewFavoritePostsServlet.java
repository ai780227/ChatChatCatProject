package com.ccc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ccc.model.bean.FavoriteBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.bean.PostPictureBean;
import com.ccc.model.bean.ResponseBean;
import com.ccc.model.bean.WhoLikeBean;
import com.ccc.model.service.MemberService;
import com.ccc.model.service.PostService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,	// 2MB
				maxFileSize = 1024 * 1024 * 10,			// 10MB
				maxRequestSize = 1024 * 1024 * 50)		// 50MB
public class ViewFavoritePostsServlet extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			resp.setContentType("text/html; charset=UTF-8");
			req.setCharacterEncoding("UTF-8");

			MemberBean mb = (MemberBean)req.getSession().getAttribute("user");
			int page = Integer.parseInt(req.getParameter("page"));
			int size = Integer.parseInt(req.getParameter("size"));

			MemberService ms = new MemberService();
			PostService ps = new PostService();
			List<FavoriteBean> lfb = ms.viewFavoritePosts(mb, page, size);
			List<PostBean> lpb = ms.getFavoritePost(lfb);
			Iterator<PostBean> ipb = lpb.iterator();
			PostBean pb;
			List<PostPictureBean> lppb = new ArrayList<PostPictureBean>();
			List<ResponseBean> lresb = new ArrayList<ResponseBean>();
			List<WhoLikeBean> lwlb = new ArrayList<WhoLikeBean>();
			while (ipb.hasNext()) {
				pb = ipb.next();
				lppb.addAll(ps.viewPostPictures(pb));
				lresb.addAll(ms.getResponse(pb));
				lwlb.addAll(ms.getWhoLike(pb));
			}
//-----------------------------------------------   JSON   -----------------------------------------------
			PrintWriter pw = resp.getWriter();
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
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}