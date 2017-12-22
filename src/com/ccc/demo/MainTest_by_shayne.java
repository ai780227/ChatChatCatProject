package com.ccc.model.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.CatBean;
import com.ccc.model.bean.ManagerBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PictureBean;
import com.ccc.model.bean.PostPictureBean;
import com.ccc.model.dao.CatDAO;
import com.ccc.model.dao.ManagerDAO;
import com.ccc.model.dao.MemberDAO;
import com.ccc.model.dao.PictureDAO;
import com.ccc.model.dao.PostPictureDAO;

public class MainTest_by_shayne {
	ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
	
	private void testMember() {
		MemberDAO memDAO = (MemberDAO) context.getBean("MemberDAO");
		MemberBean mem = new MemberBean();
		List<MemberBean> members = null;
		
		//insert
//		mem.setM_id("jack");
//		mem.setM_pwd("pwd".getBytes());
//		mem.setM_email("jack@gmail.com");
//		mem.setM_name("jack");
		//日期方法1
//		mem.setM_birth(java.sql.Date.valueOf("1988-10-01")); //日的部分依然要作加減
		//日期方法2
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.YEAR, 1988);
//		cal.set(Calendar.MONTH, 10-1);
//		cal.set(Calendar.DAY_OF_MONTH, 11+2);
//		mem.setM_birth(new java.sql.Date(cal.getTimeInMillis()));
//		mem = memDAO.insert(mem);
		
		//get one
/*		mem = memDAO.getByPrimaryKey("cecj004");
		if(mem!=null){
			System.out.println(mem.getM_id() + ","
					+ new String(mem.getM_pwd()) + "," 
					+ mem.getM_name() + "," 
					+ mem.getM_birth().toString());

			CatDAO catDAO = (CatDAO) context.getBean("CatDAO");
//			CatBean cat = new CatBean();
//			List<CatBean> cats = catDAO.getCatsByM_id(mem.getM_id());
//			for(CatBean cat : cats) {
//				System.out.println("cat name=" + cat.getC_name());				
//			}
//			Iterator it = mem.getCats().iterator();
//			while(it.hasNext()) {
//				CatBean cat = (CatBean) it.next();
//				System.out.println("cat name=" + cat.getC_name());
//			}
		}
		else
			System.out.println("null");
*/
//		memDAO.delete(mem);
		
		//update
//		mem.setM_name("jack02");
//		mem = memDAO.update(mem);
//		mem = memDAO.getByPrimaryKey("jack");
//		System.out.println(mem.getM_name());
		
		//delete
//		memDAO.delete(mem);
		
		//get all
//		members = memDAO.getAll();
//		for (MemberBean member : members) {
//			System.out.println(member.getM_id() + ","
//					+ new String(member.getM_pwd()) + "," + member.getM_name() + "," + member.getM_birth());
//		}
		
		//get by method
//		members = memDAO.getMembersByName("楊");
		members = memDAO.getMembersByEmail("cecj001");
//		members = memDAO.getMembersByMethod("from MemberBean order by m_name");
		for (MemberBean member : members) {
			System.out.println(member.getM_id() + ","
					+ new String(member.getM_pwd()) + "," 
					+ member.getM_name() + "," 
					+ member.getM_birth());
		}
//		mem.getCats();		
	}
	private void testCat() {
		CatDAO catDAO = (CatDAO) context.getBean("CatDAO");
		CatBean cat = new CatBean();
		List<CatBean> cats = null;
	
		//insert
//		cat.setM_id("cecj001");
		MemberDAO memDAO = (MemberDAO) context.getBean("MemberDAO");
		MemberBean mem = new MemberBean();
		mem = memDAO.getByPrimaryKey("cecj001");
		cat.setMember(mem);
		cat.setC_name("大黑松小倆口 -關聯001");
//		catDAO.insert(cat);
		//get one
		cat = catDAO.getByPrimaryKey(1);
		System.out.println("------"+cat.getC_id() + "," + cat.getC_name());
		System.out.println("Member name=" + cat.getMember().getM_id());
		//update
		cat.setC_name("大黑松小倆口__");
		catDAO.update(cat);
		//delete
//		cat = catDAO.delete(cat);
		//get all
//		cats = catDAO.getAll();
//		for (CatBean kitty : cats) {
//			System.out.println(kitty.getC_id() + ","
//					+ kitty.getC_name() + "," + kitty.getC_breed());
//		}
		//get by m_id
//		cats = catDAO.getCatsByM_id("cecj001");
//		for (CatBean kitty : cats) {
//			System.out.println(kitty.getC_id() + ","
//					+ kitty.getC_name() + "," + kitty.getC_breed());
//		}
		//get by method
		cats = catDAO.getCatsByMethod("from CatBean order by c_name");
		for (CatBean kitty : cats) {
			System.out.println(kitty.getC_id() + ","
				+ kitty.getC_name() + "," + kitty.getC_breed());
		}	
		
	}
	private void testManager() {		
		ManagerDAO mgrDAO = (ManagerDAO) context.getBean("ManagerDAO");
		ManagerBean mgr = new ManagerBean();
		List<ManagerBean> mgrs = null;
		
		//insert
		mgr.setMgr_id("jack");
		mgr.setMgr_pwd("pwd".getBytes());
		mgr.setMgr_email("jack@gmail.com");
		mgr.setMgr_name("jack");
		mgrDAO.insert(mgr);
		//get one
		mgr = mgrDAO.getByPrimaryKey("jack");
		System.out.println(mgr.getMgr_name());
		//update
		mgr.setMgr_name("jack02");
		mgr = mgrDAO.update(mgr);
		mgr = mgrDAO.getByPrimaryKey("jack");
		System.out.println(mgr.getMgr_name());
		//delete
//		mgrDAO.delete(mgr);
		//get all
		mgrs = mgrDAO.getAll();
		for (ManagerBean manager : mgrs) {
			System.out.println(manager.getMgr_id() + ","
					+ new String(manager.getMgr_pwd()) + "," + manager.getMgr_name());
		}
		//get by method
		mgrs = mgrDAO.getManagersByMethod("from ManagerBean order by mgr_name");
		for (ManagerBean manager : mgrs) {
			System.out.println(manager.getMgr_id() + ","
					+ new String(manager.getMgr_pwd()) + "," 
					+ manager.getMgr_name() + "," 
					+ manager.getMgr_email());
		}	
	
	}
	private void testPicture() {
		PictureDAO picDAO = (PictureDAO) context.getBean("PictureDAO");
		PictureBean pic = new PictureBean();
//		PictureBean pic2 = new PictureBean();
		List<PictureBean> pics = null;
		CatDAO catDAO = (CatDAO) context.getBean("CatDAO");
		CatBean cat = new CatBean();
		MemberDAO memDAO = (MemberDAO) context.getBean("MemberDAO");
		MemberBean mem = new MemberBean();

		//insert
		mem = memDAO.getByPrimaryKey("cecj004");
		pic.setMember(mem);
		pic.setPic_file_path("/images/02.jpg");
		cat = catDAO.getByPrimaryKey(4);
		pic.setCat(cat);
		picDAO.insert(pic);
		
		//get one
		pic = picDAO.getByPrimaryKey(1);
		System.out.println(pic.getCat().getC_id() + "," + pic.getMember().getM_id() + "," + pic.getPic_file_path() + "," + pic.getPic_time());
//		//update
		pic = picDAO.getByPrimaryKey(1);
		pic.setPic_file_path("/pictures/abc.jpg");
		pic.setPic_time(new java.sql.Timestamp(System.currentTimeMillis()));
		picDAO.update(pic);
		//delete
//		picDAO.delete(pic);
		//get all
		pics = picDAO.getAll();
		for (PictureBean picture : pics) {
			String c_id = null;
			if(picture.getCat()!=null) {
				c_id = picture.getCat().getC_id().toString();
			}
			System.out.println(c_id + "," + picture.getMember().getM_id() + "," + 
								picture.getPic_file_path() + "," + picture.getPic_time());
		}
		//get by method
//		pics = picDAO.getPicturesByMethod("from PictureBean order by pic_time");
//		for (PictureBean picture : pics) {
//			System.out.println(picture.getC_id() + "," + picture.getM_id() + "," + 
//					picture.getPic_file_path() + "," + picture.getPic_time());
//		}
	
	}
	private void testPostPicture() {
		PostPictureDAO postpicDAO = (PostPictureDAO) context.getBean("PostPictureDAO");
		PostPictureBean postpic = new PostPictureBean();
		List<PostPictureBean> postpics = null;
		
		//insert
//		postpic.setPost_id(1);
//		postpic.setPic_id(2);
//		postpicDAO.insert(postpic);
		//update
		//get one
//		postpic = postpicDAO.getByPrimaryKey(1, 1);
//		System.out.println(postpic.getPost_id() + "," + postpic.getPic_id());
		//get all
//		postpics = postpicDAO.getByPicId(1);
//		for (PostPictureBean pp : postpics) {
//			System.out.println(pp.getPost_id() + "," + pp.getPic_id());
//		}		
		//delete
//		postpicDAO.delete(postpic);
	}

	public static void main(String[] args) {
		MainTest_by_shayne test = new MainTest_by_shayne();
		test.testMember();
//		test.testCat();
//		test.testManager();
//		test.testPicture();
//		test.testPostPicture();

	}

}
