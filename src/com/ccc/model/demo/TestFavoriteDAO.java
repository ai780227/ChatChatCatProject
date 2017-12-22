package com.ccc.model.demo;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.FavoriteBean;
import com.ccc.model.dao.FavoriteDAO;
import com.ccc.model.dao.MemberDAO;
import com.ccc.model.dao.PostDAO;

public class TestFavoriteDAO {
	public static void queryByFavoriteBean(FavoriteBean fb) {
		if (fb != null) {
			System.out.println("post_id = " + fb.getPostBean().getPost_id());
			System.out.println("m_id = " + fb.getMemberBean().getM_id());
		} else {
			System.out.println("There is nothing in the FavoriteBean!");
		}
	}

	// spring
	private static final ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");

//	private static final FavoriteDAO fav = new FavoriteDAO();
	private static final FavoriteDAO fav = (FavoriteDAO) context.getBean("FavoriteDAO");
	private static FavoriteBean favb = null;

	public static void testGetByPrimaryKey(int post_id, String m_id) {
		favb = fav.getByPrimaryKey(post_id, m_id);
		TestFavoriteDAO.queryByFavoriteBean(favb);
	}

	public static void testGetAll() {
		List<FavoriteBean> lfavb = fav.getAll();
		Iterator<FavoriteBean> it = lfavb.iterator();
		while(it.hasNext()) {
			favb = (FavoriteBean)it.next();
			TestFavoriteDAO.queryByFavoriteBean(favb);
			System.out.println();
		}
	}

	public static void testInsert() {
		FavoriteDAO fav2 = (FavoriteDAO) context.getBean("FavoriteDAO");
		favb = new FavoriteBean();
		/*PostBean pb = new PostBean();
		MemberBean mb = new MemberBean();
		pb.setPost_id(6);
		favb.setPostBean(pb);
		mb.setM_id("cecj003");
		favb.setMemberBean(mb);*/
		PostDAO post = (PostDAO) context.getBean("PostDAO");
		favb.setPostBean(post.getByPrimaryKey(3));
		MemberDAO mem = (MemberDAO) context.getBean("MemberDAO");
		favb.setMemberBean(mem.getByPrimaryKey("cecj004"));
		fav2.insert(favb);
	}

	public static void testDelete() {
		favb = fav.getByPrimaryKey(3, "cecj004");
		fav.delete(favb);
	}

	public static void testGetFavoritesByPostBean() {
		PostDAO post = (PostDAO) context.getBean("PostDAO");
		Iterator<FavoriteBean> ifb = fav.getFavoritesByPostBean(post.getByPrimaryKey(1)).iterator();
		while(ifb.hasNext()) {
			favb = ifb.next();
			TestFavoriteDAO.queryByFavoriteBean(favb);
		}
	}

	public static void main(String[] args) {
		// hibernate
//		Session session = DefaultFactory.getSessionFactory().openSession();

//		testGetByPrimaryKey(1, "cecj005");
		testGetAll();
//		testInsert();
//		testDelete();
//		testGetFavoritesByPostBean();

		// hibernate
//		DefaultFactory.shutdown();
	}
}