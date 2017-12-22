package com.ccc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ccc.model.bean.CatBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PictureBean;
import com.ccc.model.dao.CatDAO;
import com.ccc.model.service.MemberService;
import com.ccc.model.service.PhotoService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,	// 2MB
				maxFileSize = 1024 * 1024 * 10,			// 10MB
				maxRequestSize = 1024 * 1024 * 50)		// 50MB
public class CreateCatInfoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");

			HttpSession httpSess = req.getSession();
			MemberBean mb = (MemberBean) httpSess.getAttribute("user");
//			String userId = (String)req.getSession().getAttribute("userId");
//			MemberBean mb = new MemberBean();
//			mb.setM_id(userId);
			CatBean cb = new CatBean();

			String c_name = req.getParameter("c_name");
			String c_age = req.getParameter("c_age");
			String c_breed = req.getParameter("c_breed");
			String c_sex = req.getParameter("c_sex");
			String c_intro = req.getParameter("c_intro");
			cb.setMember(mb);
			cb.setC_name(c_name);
			cb.setC_age(c_age);
			cb.setC_breed(c_breed);
			cb.setC_sex(c_sex);
			cb.setC_intro(c_intro);
			MemberService ms = new MemberService();
			cb = ms.createCatInfo(cb);
			req.setAttribute("cat", cb);

			PhotoService phs = new PhotoService();
			List<PictureBean> lpicb = null;
			if (req.getPart("pic").getSize() != 0) {
				lpicb = phs.uploadPhotos(mb, cb, req);
				req.setAttribute("catPhoto", lpicb);
				cb.setC_pic_path(lpicb.iterator().next().getPic_file_path());
				ms.editCatInfo(cb);
			}

			List<CatBean> lcb = ms.viewCatsInfo(mb);
			req.setAttribute("cats", lcb);
			
			WebApplicationContext webAppCtx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			CatDAO catdao = (CatDAO) webAppCtx.getBean("CatDAO");
			List<CatBean> catList = catdao.getCatsByM_id(mb.getM_id());
			httpSess.setAttribute("cats", catList);			//也在sessiob中 塞入貓咪資料
			
			RequestDispatcher rd = req.getRequestDispatcher("/pages/CatManage.jsp");
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