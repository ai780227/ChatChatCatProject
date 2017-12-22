package com.ccc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.service.FriendService;
import com.ccc.model.service.LoginService;
import com.ccc.model.service.SendMailService;

/**
 * Servlet implementation class ForgotPasswordServlet
 */
//@WebServlet("/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		LoginService loginServ = new LoginService();
		Map<String, String> errorMsg = new HashMap<String, String>();
		request.setAttribute("errorMsg", errorMsg);
		
		//取得資料
		String m_id = request.getParameter("username");
		String m_email = request.getParameter("email");
		
		//驗證資料
		if(m_id==null || m_id.trim().length()==0) {
			errorMsg.put("errorIdEmpty", "必須輸入帳號");
		} else if(!loginServ.idExists(m_id)) {
			errorMsg.put("errorIdNonexists", "帳號錯誤");
		}
		if(m_email==null || m_email.trim().length()==0) {
			errorMsg.put("errorEmailEmpty", "必須輸入信箱");
		} else if(!loginServ.emailExists(m_email)) {
			errorMsg.put("errorEmailNonexists", "信箱錯誤");
		}
		//如果有錯，返回忘記密碼頁面
		if(!errorMsg.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ForgetPassword.jsp");
			rd.forward(request, response);
			return;
		}
		
		MemberBean mem = loginServ.forgotPassword(m_id, m_email);
		
		//如果mem不為null,表示帳號信箱正確
		//寄送新密碼至使用者信箱\
		if(mem != null) {
			String mail = mem.getM_email();
			String changedPwd = loginServ.autoChangePwd(mem);	//修改並取得新密碼
			SendMailService mailServ = new SendMailService();
			mailServ.sendMail(mem, changedPwd);					//寄送信件
			RequestDispatcher rd = request.getRequestDispatcher("/pages/SendPwdEamil.jsp");
			rd.forward(request, response);
			return;
		}
	}

}
