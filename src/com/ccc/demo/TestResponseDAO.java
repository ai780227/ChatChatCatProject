package com.ccc.model.demo;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.ResponseBean;
import com.ccc.model.dao.MemberDAO;
import com.ccc.model.dao.PostDAO;
import com.ccc.model.dao.ResponseDAO;

public class TestResponseDAO {
	public static void queryByResponseBean(ResponseBean resb) {
		if (resb != null) {
			System.out.println("res_id = " + resb.getRes_id());
			System.out.println("post_id = " + resb.getPostBean().getPost_id());
			System.out.println("m_id = " + resb.getMemberBean().getM_id());
			System.out.println("res_content = " + resb.getRes_content());
			System.out.println("res_time = " + resb.getRes_time());
		} else {
			System.out.println("There is nothing in the ResponseBean!");
		}
	}
	// spring
	private static final ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");

//	private static final ResponseDAO res = new ResponseDAO();
	private static final ResponseDAO res = (ResponseDAO) context.getBean("ResponseDAO");
	private static ResponseBean resb = null;

	public static void testGetByPrimaryKey(int res_id) {
		resb = res.getByPrimaryKey(res_id);
		TestResponseDAO.queryByResponseBean(resb);
	}

	public static void testGetAll() {
		List<ResponseBean> lresb = res.getAll();
		Iterator<ResponseBean> it = lresb.iterator();
		while(it.hasNext()){
			resb = (ResponseBean)it.next();
			TestResponseDAO.queryByResponseBean(resb);
			System.out.println();
		}
	}

	public static void testInsert() {
		resb = new ResponseBean();
//		PostBean pb = new PostBean();
//		MemberBean mb = new MemberBean();
//		pb.setPost_id(4);
		PostDAO post = (PostDAO) context.getBean("PostDAO");
		MemberDAO mem = (MemberDAO) context.getBean("MemberDAO");
		resb.setPostBean(post.getByPrimaryKey(3));
//		mb.setM_id("cecj002");
		resb.setMemberBean(mem.getByPrimaryKey("cecj002"));
		resb.setRes_content("我是貓+++");
		resb.setRes_time(new java.sql.Timestamp(System.currentTimeMillis()));
		res.insert(resb);

		// test if insert a ResponseNotice automatically or not when insert a Response
//		ResponseNoticeDAO rn = (ResponseNoticeDAO) context.getBean("ResponseNoticeDAO");
//		TestResponseNoticeDAO.insertByResponseBean(resb);
		// TestResponseNoticeDAO.testGetAll();
	}

	public static void testUpdate() {
		resb = res.getByPrimaryKey(3);
		resb.setRes_content("我是貓");
		res.update(resb);
	}

	public static void testDelete() {
		resb = res.getByPrimaryKey(44);
		res.delete(resb);
	}

	public static void testGetResponsesByMemberBean(){
		MemberBean mb = new MemberBean();
		mb.setM_id("cecj002");
		Iterator<ResponseBean> iresb = res.getResponsesByMemberBean(mb).iterator();
		if(!iresb.hasNext()){
			System.out.println("There is no response written by " + mb.getM_id());
		}
		while(iresb.hasNext()){
			resb = iresb.next();
			System.out.println("res_id = " + resb.getRes_id());
		}
	}

	public static void testGetResponsesByPostBean(){
		resb = res.getByPrimaryKey(2);
		Iterator<ResponseBean> iresb = res.getResponsesByPostBean(resb.getPostBean()).iterator();
		while(iresb.hasNext()){
			resb = iresb.next();
			System.out.println("res_id = " + resb.getRes_id());
		}
	}

	public static void main(String[] args) {
		// hibernate
//		Session session = DefaultFactory.getSessionFactory().openSession();

//		testGetByPrimaryKey(41);
		testGetAll();
//		testInsert();
//		testUpdate();
//		testDelete();
//		testGetResponsesByMemberBean();
//		testGetResponsesByPostBean();

		// hibernate
//		DefaultFactory.shutdown();
	}
}