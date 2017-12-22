package com.ccc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.bean.WhoLikeBean;
import com.ccc.model.service.PostService;

public class LikePostServlet extends HttpServlet {

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			resp.setContentType("text/html; charset=UTF-8");
			req.setCharacterEncoding("UTF-8");

			// data for testing
//			ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
//			MemberDAO mem = (MemberDAO) context.getBean("MemberDAO");
			MemberBean mb = (MemberBean)req.getSession().getAttribute("user");
//			String userId = (String)req.getSession().getAttribute("userId");
//			MemberBean mb = new MemberBean();
//			mb.setM_id(userId);
//			mb = mem.getByPrimaryKey(mb.getM_id());
//			String path = req.getParameter("path");

			int post_id = Integer.parseInt(req.getParameter("post_id"));
			PostBean pb = new PostBean();
			pb.setPost_id(post_id);
			WhoLikeBean wlb = new WhoLikeBean();
			wlb.setMember(mb);
			wlb.setPost(pb);
			wlb.setM_name(mb.getM_name());

			PostService ps = new PostService();
			boolean likePost = ps.likePost(wlb);
//			req.setAttribute("likePost", likePost);

//			RequestDispatcher rd = req.getRequestDispatcher("/pages/" + path);
//			rd.forward(req, resp);
//			return;

			PrintWriter pw = resp.getWriter();
			JSONObject json = new JSONObject();
			json.put("likePost", likePost);
			json.put("likeUser", mb.getM_name());
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