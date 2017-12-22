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
import com.ccc.model.service.MemberService;
import com.ccc.model.service.PhotoService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,	// 2MB
				maxFileSize = 1024 * 1024 * 10,			// 10MB
				maxRequestSize = 1024 * 1024 * 50)		// 50MB
public class ModifyPersonalInfoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

			MemberBean mb = (MemberBean)req.getSession().getAttribute("user");
//			String userId = (String)req.getSession().getAttribute("userId");
//			MemberBean mb = new MemberBean();
//			mb.setM_id(userId);
			CatBean cb = new CatBean();
			MemberService ms = new MemberService();

			String m_name = req.getParameter("name");
			String m_intro = req.getParameter("selfintro");
			String m_email = req.getParameter("email");
			java.sql.Date m_birth = null;
			if(req.getParameter("birthday")!=null && req.getParameter("birthday").trim().length()!=0)
				m_birth = java.sql.Date.valueOf(req.getParameter("birthday"));
			String m_sex = req.getParameter("gender");
			mb.setM_name(m_name);
			mb.setM_intro(m_intro);
			mb.setM_email(m_email);
			mb.setM_birth(m_birth);
			mb.setM_sex(m_sex);

			PhotoService phs = new PhotoService();
			List<PictureBean> lpicb = null;
			if(req.getPart("pic") != null) {
				if (req.getPart("pic").getSize() != 0) {
					lpicb = phs.uploadPhotos(mb, cb, req);
					req.setAttribute("memPhoto", lpicb);
					mb.setM_pic_path(lpicb.iterator().next().getPic_file_path());
				}				
			}

			mb = ms.modifyPersonalInfo(mb);
			req.setAttribute("m_info", mb);
			
			req.setAttribute("result", "修改成功");
			RequestDispatcher rd = req.getRequestDispatcher("/pages/Personal.jsp");
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