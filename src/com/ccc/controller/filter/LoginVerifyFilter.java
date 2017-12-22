package com.ccc.controller.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ccc.model.bean.MemberBean;

/**
 * Servlet Filter implementation class LoginVerifyFilter
 */
//@WebFilter("/LoginVerifyFilter")
//@WebFilter({"/pages/*","*.do"})
@WebFilter("*.do")
public class LoginVerifyFilter implements Filter {
	
	ServletContext servletContext = null;
	String userId = null;

    /**
     * Default constructor. 
     */
    public LoginVerifyFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
//		System.out.println("filter");
		servletContext = request.getServletContext();
		HttpServletRequest req = null;
		HttpServletResponse resp = null;
		HttpSession session = null;
		MemberBean mb = null;
		
		//將 request 及 response 轉換成 
		//HttpServletRequest 及 HttpServletResponse
		if(request instanceof HttpServletRequest &&
				response instanceof HttpServletResponse) {
			req = (HttpServletRequest) request;
			resp = (HttpServletResponse) response;
		}		
		
		//取得session
		session = req.getSession();		
		//確認是否已登入，如果session中有userId，代表已登入
		if(isLogin(session) == true) {
			mb = (MemberBean) servletContext.getAttribute(userId);
			HttpSession httpsess = req.getSession();
//			String sid = (String) servletContext.getAttribute(userId);
//			if(mb!=null)
//				System.out.println( userId + " is already login");		//已登入，繼續動作
		} else {
			resp.sendRedirect(req.getContextPath() + "/index.jsp");		//尚未登入，返回首頁
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	
	//確認是否已登入
	private boolean isLogin(HttpSession session) {
		userId = null;
		MemberBean mb = null;
		if( session.getAttribute("user")!=null) {
			mb = (MemberBean) session.getAttribute("user");
			//如果mb不為null，代表已經登入，回傳true
			if(mb!=null) {
				userId = mb.getM_id();
				return true;
			}
		}
		return false;
	}

}
