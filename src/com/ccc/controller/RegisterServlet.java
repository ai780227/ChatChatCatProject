package com.ccc.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PictureBean;
import com.ccc.model.dao.MemberDAO;
import com.ccc.model.service.LoginService;
import com.ccc.model.service.PhotoService;

/**
 * Servlet implementation class RegisterServlet
 */
//@WebServlet("/RegisterServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,	// 2MB
					maxFileSize = 1024 * 1024 * 10,			// 10MB
					maxRequestSize = 1024 * 1024 * 50)		// 50MB
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		//取得資料
		String m_id = request.getParameter("username");
		String m_pwd = request.getParameter("pwd");
		String re_pwd = request.getParameter("pwd1");
		String m_email = request.getParameter("email");
		String m_name = request.getParameter("name");
		String m_sex = request.getParameter("gender");
		String m_birth = request.getParameter("birthday");
		String m_intro = request.getParameter("selfintro");
		String m_pic_path = request.getParameter("pic");
		String m_cat = request.getParameter("m_cat");
//		System.out.println("(*)m_id : " + m_id);
//		System.out.println("(*)m_pwd : " + m_pwd);
//		System.out.println("(*)re_pwd : " + re_pwd);
//		System.out.println("(*)m_email : " + m_email);
//		System.out.println("(*)m_name : " + m_name);
//		System.out.println("(*)m_pic_path : " + m_name.length());
//		System.out.println("(*)m_sex : " + m_sex);
//		System.out.println("(*)m_birth : " + m_birth);
//		System.out.println("(*)m_pic_path : " + m_pic_path);
		
		
		//驗證資料
		Map<String, String> errorMsg = new HashMap<String, String>();
		request.setAttribute("errorMsg", errorMsg);
		LoginService loginServ = new LoginService();
		
		if(m_id==null || m_id.trim().length()==0) {
			errorMsg.put("errorIdEmpty","必須輸入帳號");
		} else if(m_id.length() < 5 || m_id.length() > 12) {
			errorMsg.put("errorIdFormat", "帳號格式錯誤");
		}
		if(loginServ.idExists(m_id.trim())) {
			errorMsg.put("errorIdExists", "帳號已存在");
		}
		if(m_pwd==null || m_pwd.trim().length()==0) {
			errorMsg.put("errorPwdEmpty","必須輸入密碼");
		} else if( !correctPwdFormat(m_pwd)) {
			errorMsg.put("errorPwdFormat", "密碼格式錯誤");
		}
		if(re_pwd==null || re_pwd.trim().length()==0) {
			errorMsg.put("errorRePwdEmpty","必須輸入確認密碼");
		}
		if(m_pwd!=null && re_pwd!=null) {
			if(!m_pwd.equals(re_pwd))
				errorMsg.put("errorPwdDifferent", "密碼必須一致");
		}
		if(m_email==null || m_email.trim().length()==0) {
			errorMsg.put("errorEmailEmpty","必須輸入E-nail");
		} else if(!correctEmailFormat(m_email)) {
			errorMsg.put("errorEmailFormat", "信箱格式錯誤");
		}
		if(loginServ.emailExists(m_email.trim())) {
			errorMsg.put("errorEmailExists", "信箱已使用過");
		}
		if(m_name==null || m_name.trim().length()==0) {
			errorMsg.put("errorNameEmpty","必須輸入名稱");
		} else if(m_name.length() > 8) {
			errorMsg.put("errorNameFormat","名稱長度必須少於8");
		}
		
		//若有任何錯誤(errorMsg不為空)，導回註冊頁面
		if(!errorMsg.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("/pages/SignUp.jsp");
			rd.forward(request, response);
			return;
		}
		
		MemberBean mem = new MemberBean();
		PhotoService photoServ = new PhotoService();
		//塞入資料到MemberBean
		mem.setM_id(m_id);
		String m_pwd_MD5 = loginServ.getMD5Encoding(m_pwd);
		mem.setM_pwd(m_pwd_MD5.getBytes());
		mem.setM_email(m_email);
		if(m_name!=null && m_name.trim().length()!=0)
			mem.setM_name(m_name);
		if(m_sex!=null && m_sex.trim().length()!=0)
			mem.setM_sex(m_sex);
		if(m_birth!=null && m_birth.trim().length()!=0){		//日期轉換
			String[] birthArray = m_birth.split("-");
			int year = Integer.valueOf(birthArray[0]);
			int month = Integer.valueOf(birthArray[1]);
			int date = Integer.valueOf(birthArray[2]);
			Calendar cal = Calendar.getInstance();
			cal.set(year, month-1, date);
			mem.setM_birth(new Date(cal.getTimeInMillis()));
		}	
		if(m_intro!=null && m_intro.trim().length()!=0)
			mem.setM_intro(m_intro);		
		
		
		//加入會員資料到資料庫
		mem = loginServ.registerMember(mem);
		if(mem!=null) {	//加入成功，導回註冊結果頁面，給她返回首頁再登入
			
			//檢查使用者是否有上傳大頭照，有上傳的話，update
			List<PictureBean> picList = photoServ.uploadPhotos(mem, null, request);
			if(picList != null && !picList.isEmpty()) {
				mem.setM_pic_path(picList.get(0).getPic_file_path());
				WebApplicationContext webAppContext = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
				MemberDAO memdao = (MemberDAO) webAppContext.getBean("MemberDAO");
				memdao.update(mem);
			}
			
//			System.out.println("successed");
			request.setAttribute("SignUp_result", "success");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/SignUp_result.jsp");
			rd.forward(request, response);
			return;
		} else {	//註冊失敗，導回
			request.setAttribute("SignUp_result", "fail");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/SignUp_result.jsp");
			rd.forward(request, response);
			return;
		}
		
	}
	
	private boolean correctPwdFormat(String m_pwd) {
		int numCount = 0;			//數字計數
		int alphabetCount = 0;		//字母計數
		if(m_pwd.length() > 7 && m_pwd.length() < 18) {
			for(int i=0; i < m_pwd.length(); i++) {
				int c= (int) m_pwd.charAt(i);
//				System.out.println("c=" +c);
				if( (c >= 65 && c <= 90) || (c >= 97 && c <= 122))		//判斷 A-Z  || a~z
					alphabetCount++;
				if( c >= 48 && c <= 57 )			//判斷 0~9
					numCount++;
			}
//			System.out.println("numCount=" +numCount);
//			System.out.println("alphabetCount=" +alphabetCount);
			if(numCount!=0 && alphabetCount!=0)		//如果數字 & 字母 數量皆不為0，才回傳true
				return true;
		}
		return false;
	}
	
	private boolean correctEmailFormat(String m_email) {
		int atCount = 0;
		if(m_email.length() <= 50) {		//判斷長度
			for(int i=0; i < m_email.length(); i++) {
				int c = (int) m_email.charAt(i);
				if( c == 64) {
					atCount++;
				}
			}
			int atIndex = m_email.indexOf("@");
//			System.out.println("atIndex=" + atIndex);
			if( atIndex != -1) {
				String backside = m_email.substring(atIndex+1);
//				System.out.println("backside=" + backside);
				int dotIndex = backside.indexOf(".");
				if( dotIndex != -1) {
//					System.out.println("dotIndex=" + dotIndex);
					//(dotIndex!=0) && (dotIndex!=backside.length()-1)，表示 @xxx.xxx
					//atCount==1，表示只有一個"@"
					if((dotIndex!=0) && (dotIndex!=backside.length()-1) && (atCount==1))
						return true;					
				}				
			}			
		}
		return false;
	}

}
