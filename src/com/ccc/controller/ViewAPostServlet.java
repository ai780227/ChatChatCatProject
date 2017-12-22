package com.ccc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.bean.PostPictureBean;
import com.ccc.model.bean.ResponseBean;
import com.ccc.model.bean.WhoLikeBean;
import com.ccc.model.service.MemberService;
import com.ccc.model.service.PostService;

public class ViewAPostServlet extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			if(req.getParameter("post_id") != null) {
				if (req.getParameter("post_id") != "") {

					MemberBean mb;
					int post_id = Integer.parseInt(req.getParameter("post_id"));

					PostService ps = new PostService();
					PostBean pb = ps.viewAPost(post_id);

	//				req.setAttribute("post", pb);

					MemberService ms = new MemberService();
					mb = ms.getMemberInfoByPost(pb);
					List<PostPictureBean> lppb = ps.viewPostPictures(pb);
					List<ResponseBean> lresb = ms.getResponse(pb);
					List<WhoLikeBean> lwlb = ms.getWhoLike(pb);

					/*if (lppb != null) {
						req.setAttribute("postPictures", lppb);
					}
					if (lresb != null) {
						req.setAttribute("responses", lresb);
					}
					if (lwlb != null) {
						req.setAttribute("whoLikes", lwlb);
					}*/

//					RequestDispatcher rd = req.getRequestDispatcher("/pages/request.jsp");
//					rd.forward(req, resp);
//					return;

					PrintWriter pw = resp.getWriter();
					JSONObject json = new JSONObject();
					JSONArray aPic = new JSONArray(), aRes = new JSONArray(), aWhoLike = new JSONArray();
					JSONObject post = null, pic = null, res = null, whoLike = null;

					post = new JSONObject();
					post.put("post_id", pb.getPost_id());
					post.put("like_count", pb.getLike_count());
					post.put("m_id", pb.getMemberBean().getM_id());
					post.put("m_name", mb.getM_name());
					post.put("m_pic_path", mb.getM_pic_path());
					post.put("post_content", pb.getPost_content());
					post.put("post_type", pb.getPost_type());
					post.put("post_property", pb.getPost_property());
					post.put("post_time", pb.getPost_time().toString());
					if (pb.getActivityBean() != null) {
						post.put("act_id", pb.getActivityBean().getAct_id());
					}
					json.put("post", post);

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

					pw.print(json.toString());
					pw.close();
				}
			}

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