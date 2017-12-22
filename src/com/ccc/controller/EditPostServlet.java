package com.ccc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ccc.model.bean.CatBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PictureBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.service.PhotoService;
import com.ccc.model.service.PostService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,	// 2MB
				maxFileSize = 1024 * 1024 * 10,			// 10MB
				maxRequestSize = 1024 * 1024 * 50)		// 50MB
public class EditPostServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");

			// data for testing
			int post_id = Integer.parseInt(req.getParameter("post_id"));
			PostService ps = new PostService();
			PostBean pb = ps.viewAPost(post_id);

			MemberBean mb = (MemberBean)req.getSession().getAttribute("user");
//			String userId = (String)req.getSession().getAttribute("userId");
//			MemberBean mb = new MemberBean();
//			mb.setM_id(userId);
			CatBean cb = new CatBean();

			int post_type = Integer.parseInt(req.getParameter("post_type"));
			int post_property = Integer.parseInt(req.getParameter("post_property"));
			String post_content = req.getParameter("post_content");

			pb.setPost_type(post_type);
			pb.setPost_property(post_property);
			pb.setPost_content(post_content);

			pb = ps.editPost(pb);
			//req.setAttribute("post", pb);

			if (req.getPart("pic").getSize() != 0) {
				PhotoService phs = new PhotoService();
				List<PictureBean> lpicb = phs.uploadPhotos(mb, cb, req);
				if (lpicb != null) {
					ps.insertPostPicture(pb, lpicb);
					//req.setAttribute("postPictures", lpicb);
				}
			}

			RequestDispatcher rd = req.getRequestDispatcher("/pages/EditPost.jsp");
			rd.forward(req, resp);
			return;
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