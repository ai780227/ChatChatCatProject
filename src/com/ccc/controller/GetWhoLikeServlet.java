package com.ccc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ccc.model.bean.PostBean;
import com.ccc.model.bean.WhoLikeBean;
import com.ccc.model.service.MemberService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,	// 2MB
				maxFileSize = 1024 * 1024 * 10,			// 10MB
				maxRequestSize = 1024 * 1024 * 50)		// 50MB
public class GetWhoLikeServlet extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			// data for testing
			int post_id = Integer.parseInt(req.getParameter("post_id"));
			PostBean pb = new PostBean();
			pb.setPost_id(post_id);

			MemberService ms = new MemberService();
			List<WhoLikeBean> lwlb = ms.getWhoLike(pb);
	//		req.setAttribute("post_id", post_id);
	//		req.setAttribute("whoLikes", lwlb);

	//		RequestDispatcher rd = req.getRequestDispatcher("/pages/response.jsp");
	//		rd.forward(req, resp);
	//		return;

			PrintWriter pw = resp.getWriter();
			JSONObject json = new JSONObject();
			JSONArray aWhoLike = new JSONArray();
			JSONObject whoLike;

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