package com.ccc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.service.MemberService;


public class ChangePwdservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePwdservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Map<String, String> errorMsg = new HashMap<String, String>();
		request.setAttribute("errorMsg", errorMsg);
		MemberService memServ = new MemberService();
		HttpSession httpSess = request.getSession();
		
		//接收資料
		String old_pwd=request.getParameter("old_pwd");
		String new_pwd = request.getParameter("new_pwd");
		String new_re_pwd = request.getParameter("re_pwd");
		MemberBean mem = (MemberBean) httpSess.getAttribute("user");
		Boolean bo=memServ.judgeOldPassword(old_pwd, mem);
//		System.out.println(bo);
		
		//驗證資料
		if(old_pwd==null||old_pwd.trim().length()==0){
			errorMsg.put("errorOldPwdEmpty", "必須輸入舊密碼");
		}
		if(old_pwd!="" && bo==false){//null包含空白mkui
			errorMsg.put("errorOldPwd", "舊密碼錯誤");
		}
		if(new_pwd==null || new_pwd.trim().length()==0) {
			errorMsg.put("errorPwdEmpty", "必須輸入新密碼");
		}
		if(new_re_pwd==null || new_re_pwd.trim().length()==0) {
			errorMsg.put("errorRePwdEmpty", "必須輸入確認新密碼");
		}else if(new_pwd!=null && new_re_pwd!=null) {
			if(!new_pwd.equals(new_re_pwd))
				errorMsg.put("errorPwdDifferent", "密碼必須一致");			
		}
		//如果有錯，返回忘記密碼頁面
		if(!errorMsg.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ChangePassword.jsp");
			rd.forward(request, response);
			return;
		}
		
		if(mem!=null) {
			memServ.changePassword(new_pwd, mem);
			response.sendRedirect(request.getContextPath()+"/pages/ChangePassword_success.jsp");
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ChangePassword.jsp");
			rd.forward(request, response);
			return;
		}
	}

}
