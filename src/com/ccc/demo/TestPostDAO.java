package com.ccc.model.demo;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.dao.PostDAO;

public class TestPostDAO {
	public static void queryByPostBean(PostBean pb) {
		if (pb != null) {
			System.out.println("post_id = " + pb.getPost_id());
			System.out.println("post_type = " + pb.getPost_type());
			System.out.println("post_property = " + pb.getPost_property());
			System.out.println("m_id = " + pb.getMemberBean().getM_id());
			System.out.println("post_time = " + pb.getPost_time());
			System.out.println("post_content = " + pb.getPost_content());
			System.out.println("like_count = " + pb.getLike_count());
			if (pb.getActivityBean() != null) {
				System.out.println("act_id = " + pb.getActivityBean().getAct_id());
			} else {
				System.out.println("act_id = null");
			}
		} else {
			System.out.println("There is nothing in the PostBean!");
		}
	}

	// spring
	private static final ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");

//	private static final PostDAO post = new PostDAO();
	private static final PostDAO post = (PostDAO) context.getBean("PostDAO");
	private static PostBean postb = null;

	public static void testGetByPrimaryKey(int post_id) {
//		PostDAO post = new PostDAO();
		postb = post.getByPrimaryKey(post_id);
		TestPostDAO.queryByPostBean(postb);
	}

	public static void testGetAll() {
		List<PostBean> lpb = post.getAll();
		Iterator<PostBean> it = lpb.iterator();
		while(it.hasNext()) {
			postb = (PostBean)it.next();
			TestPostDAO.queryByPostBean(postb);
			System.out.println();
		}
	}

	public static void testInsert() {
		postb = new PostBean();
		MemberBean mb = new MemberBean();
		postb.setPost_type(1);
		postb.setPost_property(1);
		mb.setM_id("cecj001");
		postb.setMemberBean(mb);
		postb.setPost_time(new java.sql.Timestamp(System.currentTimeMillis()));
		postb.setPost_content("這隻貓好可愛!");
		post.insert(postb);
	}

	public static void testUpdate() {
		postb = post.getByPrimaryKey(3);
		postb.setPost_time(new java.sql.Timestamp(System.currentTimeMillis()));
		post.update(postb);
	}

	public static void testDelete() {
		postb = post.getByPrimaryKey(4);
		post.delete(postb);
	}

	public static void testGetPostsByM_id(){

	}

	public static void testGetPostsByType(){

	}

	public static void testGetPostsByProperty(){

	}

	public static void testGetPostsByPost_content(){
		Iterator<PostBean> ipb = post.getPostsByPost_content("好可愛").iterator();
		while(ipb.hasNext()) {
			postb = (PostBean)ipb.next();
			TestPostDAO.queryByPostBean(postb);
			System.out.println();
		}
	}

	public static void testGetPostsByLike_count(){

	}

	public static void main(String[] args) {
		// hibernate
//		Session session = DefaultFactory.getSessionFactory().openSession();

//		testGetByPrimaryKey(2);
		testGetAll();
//		testInsert();
//		testUpdate();
//		testDelete();
//		testGetPostsByPost_content();

		// hibernate
//		DefaultFactory.shutdown();
	}
}