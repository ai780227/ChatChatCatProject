package com.ccc.model.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.ManagerBean;
import com.ccc.model.dao.ManagerDAO;

public class ManagerLoginService {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
	ManagerDAO mgrdao = null;
	
	//登入管理員
	public ManagerBean loginManager(String mgr_id, String password) {
		mgrdao = (ManagerDAO) context.getBean("ManagerDAO");
		//利用mem_id抓member
		ManagerBean mgr = mgrdao.getByPrimaryKey(mgr_id);
		//如果mem有接到資料(不為null)，即表示帳號存在
		if(mgr != null) {
			//先轉成String
			String dbpwd = new String(mgr.getMgr_pwd());
			String pwd = getMD5Encoding(password);
			//驗證密碼是否正確
			if( dbpwd.equals(pwd) ) {
				//密碼正確，回傳member資料
				return mgr;
			} else {
				//密碼錯誤，回傳null
				return null;
			}
		} 
		else {
			//mem沒有接到資料，帳號不存在，回傳null
			return null;
		}
	}
	
	//轉換MD5
		public String getMD5Encoding(String str) {
			final StringBuffer buffer = new StringBuffer();
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] digest = md.digest(str.getBytes());
//				String md5str = new String(digest);
				
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ManagerLoginService login = new ManagerLoginService();
		ManagerBean mem = new ManagerBean();
		
		//登入管理員
		mem = login.loginManager("manager001", "pwd001");
		if(mem!=null) {
			System.out.println("登入成功");
		} else {
			System.out.println("帳號不存在或密碼錯誤");
		}		
		
	}


}
