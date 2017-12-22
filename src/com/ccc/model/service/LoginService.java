package com.ccc.model.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.dao.MemberDAO;

public class LoginService {
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"beans.config.xml");
	MemberDAO memdao = (MemberDAO) context.getBean("MemberDAO");;

	// 登入會員
	public MemberBean loginMember(String mem_id, String password) {
		memdao = (MemberDAO) context.getBean("MemberDAO");
		// 利用mem_id抓member
		MemberBean mem = memdao.getByPrimaryKey(mem_id);
		// 如果mem有接到資料(不為null)，即表示帳號存在
		if (mem != null) {
			// 先轉成String
			String dbpwd = new String(mem.getM_pwd());
			String pwd = getMD5Encoding(password);
			// 確認MD5轉換是否成功
			if (pwd != null) {
				// 驗證密碼是否正確
				if (dbpwd.equals(pwd)) {
					// 密碼正確，回傳member資料
					return mem;
				} else {
					// 密碼錯誤，回傳null
					return null;
				}
			} else {
				// MD5轉換失敗，回傳null
				return null;
			}
		} else {
			// mem沒有接到資料，帳號不存在，回傳null
			return null;
		}
	}

	// 註冊會員
	public MemberBean registerMember(MemberBean register) {
		// memdao = (MemberDAO) context.getBean("MemberDAO");
		// 會員註冊，insert資料
		MemberBean mem = memdao.insert(register);
		mem = memdao.getByPrimaryKey(mem.getM_id());
		// 如果mem有接到資料(不為null)，即表示會員註冊成功
		if (mem != null) {
			// 會員註冊成功，回傳該會員資料
			return mem;
		} else {
			// mem沒有接到資料，發生exception，即註冊失敗，回傳null
			return null;
		}
	}

	// 忘記密碼，傳入會員帳號、信箱，進行驗證
	public MemberBean forgotPassword(String mem_id, String mem_email) {
		// memdao = (MemberDAO) context.getBean("MemberDAO");
		// 先利用帳號抓資料
		MemberBean mem = memdao.getByPrimaryKey(mem_id);
		// 如果mem有接到資料(不為null)，即表示帳號存在
		if (mem != null) {
			// 驗證信箱是否符合
			if (mem_email.equals(mem.getM_email())) {
				// 信箱正確，回傳會員資料
				return mem;
			} else {
				// 信箱錯誤，回傳null;
				return null;
			}
		} else {
			// mem沒接到資料，表示帳號不存在，回傳null
			return null;
		}
	}

	// 轉換MD5
	public String getMD5Encoding(String str) {
		final StringBuffer buffer = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(str.getBytes());
			// String md5str = new String(digest);

			for (int i = 0; i < digest.length; ++i) {
				final byte b = digest[i];
				final int value = (b & 0x7F) + (b < 0 ? 128 : 0);
				buffer.append(value < 16 ? "0" : "");
				buffer.append(Integer.toHexString(value));
			}
			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// 帳號是否已存在
	public Boolean idExists(String mem_id) {
		// memdao = (MemberDAO) context.getBean("MemberDAO");
		MemberBean mem = memdao.getByPrimaryKey(mem_id);
		// 帳號是否存在
		if (mem != null) {
			// 存在，回傳true
			return true;
		} else {
			// 不存在，回傳false
			return false;
		}
	}

	// 帳號是否已存在
	public Boolean emailExists(String mem_email) {
		// memdao = (MemberDAO) context.getBean("MemberDAO");
		String query = "from MemberBean where m_email='" + mem_email + "'";
		// List<MemberBean> memList = memdao.getMembersByEmail(mem_email);
		List<MemberBean> memList = memdao.getMembersByMethod(query);
		// 信箱是否用過
		if (memList != null) {
			if (!memList.isEmpty()) // 存在，回傳true
				return true;
		}
		// 不存在，回傳false
		return false;
	}	
	
	//解除封鎖帳號
	public MemberBean unlockMember(MemberBean mem) {
		mem.setM_block_time(null);		//封鎖時間改為null
		mem.setM_authority(1);			//權限改為普通會員
		mem.setM_violation_count(0);	//計數歸0
		mem = memdao.update(mem);
		return mem;
	}
	
	//系統幫忘記密碼的人，主動換密碼，並回傳密碼
	public String autoChangePwd(MemberBean mem) {
		String pwd = new String(mem.getM_pwd());
		String subpwd = pwd.substring(0,8);
		String md5pwd = getMD5Encoding(subpwd);
		mem.setM_pwd(md5pwd.getBytes());
		mem = memdao.update(mem);
		if(mem!=null)
			return subpwd;
		
		return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginService login = new LoginService();
		MemberBean mem = new MemberBean();

		// login.getMD5Encoding("pwd001");

		// 登入會員
//		mem = login.loginMember("cecj001", "pwd001");
//		if (mem != null) {
//			System.out.println("登入成功");
//		} else {
//			System.out.println("帳號不存在或密碼錯誤");
//		}

		// 註冊會員

//		String pwd = login.getMD5Encoding("pwd");
		// 確認MD5是否轉換成功
//		if (pwd != null) {
//			mem.setM_id("jack");
//			mem.setM_pwd(pwd.getBytes());
//			mem.setM_email("jack@gmail.com");
//			mem.setM_name("jack");
//			Calendar cal = Calendar.getInstance();
//			cal.set(Calendar.YEAR, 1988);
//			cal.set(Calendar.MONTH, 10 - 1);
//			cal.set(Calendar.DAY_OF_MONTH, 11 + 2);
//			mem.setM_birth(new java.sql.Date(cal.getTimeInMillis()));
//			if (login.idExists(mem.getM_id()) == false) {
//				mem = login.registerMember(mem);
//				if (mem != null) {
//					System.out.println("帳號註冊成功");
//				} else {
//					System.out.println("帳號註冊失敗");
//				}
//			} else {
//				System.out.println("帳號已存在");
//			}
//		}

		// 忘記密碼
		/*
		 * mem = login.forgotPassword("cecj001", "cecj001@gmail.com");
		 * if(mem!=null) { System.out.println("您的密碼為：" + new
		 * String(mem.getM_pwd())); } else { System.out.println("帳號或信箱輸入錯誤"); }
		 */
		
		//忘記密碼(mail 測試)
//		ApplicationContext context = new ClassPathXmlApplicationContext(
//				"beans.config.xml");
//		MemberDAO memdao = (MemberDAO) context.getBean("MemberDAO");;
//		mem = memdao.getByPrimaryKey("mailtest001");
//		String pwd = new String(mem.getM_pwd());
//		System.out.println("pwd = " + pwd);
//		System.out.println("pwd = " + pwd.substring(0,8));//4617c717
//		String md5pwd = login.getMD5Encoding(pwd.substring(0,8));
		
		System.out.println(login.getMD5Encoding("password001"));
		
	}

}
