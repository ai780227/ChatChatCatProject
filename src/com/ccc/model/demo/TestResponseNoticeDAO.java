package com.ccc.model.demo;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.ResponseBean;
import com.ccc.model.bean.ResponseNoticeBean;
import com.ccc.model.dao.ResponseDAO;
import com.ccc.model.dao.ResponseNoticeDAO;

public class TestResponseNoticeDAO {
	public static void queryByResponseNoticeBean(ResponseNoticeBean rnb) {
		if (rnb != null) {
			System.out.println("res_notid = " + rnb.getRes_notid());
			System.out.println("m_id_to = " + rnb.getM_id_to());
			System.out.println("m_id_from = " + rnb.getM_id_from());
			System.out.println("m_name_from = " + rnb.getM_name_from());
			System.out.println("post_id = " + rnb.getPost_id());
			System.out.println("res_id = " + rnb.getResponseBean().getRes_id());
			System.out.println("res_read = " + rnb.getRes_read());
		} else {
			System.out.println("There is nothing in the ResponseNoticeBean!");
		}
	}

	// 當insert一筆Response時，自動insert幾筆ResponseNotice(搭配ResponseDAO的insert方法使用)
	public static List<ResponseNoticeBean> insertByResponseBean(ResponseBean resb) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		// new一個ResponseNoticeDAO來insert ResponseNoticeBean物件
//		ResponseNoticeDAO rn = new ResponseNoticeDAO();
		ResponseNoticeDAO rn = (ResponseNoticeDAO) context.getBean("ResponseNoticeDAO");
		// 宣告一個ResponseNoticeBean來塞資料(可重複利用)
		ResponseNoticeBean rnb = null;
		// new一個ResponseDAO來得到由resb的內容產生的ResponseBean物件
		// (因resb只是塞有資料而已，不算是實體，因此無法與其他如MemberBean產生關聯來獲得如m_name等資料)
		ResponseDAO res = (ResponseDAO) context.getBean("ResponseDAO");
		// 藉由resb的res_id來得到該筆物件resb2，並取得resb2取得該筆物件的回覆者m_id_from及m_name_from
		// (因為可能會新增多筆ResponseNoticeBean物件，但回覆者均為同一人)
		ResponseBean resb2 = res.getByPrimaryKey(resb.getRes_id());
		String m_id_from = resb2.getMemberBean().getM_id();
		String m_name_from = resb2.getMemberBean().getM_name();
		// 藉由resb的post_id來得到所有相關的(同一則貼文的)ResponseBean物件
		List<ResponseBean> lrb = res.getResponsesByPostBean(resb.getPostBean());
		Iterator<ResponseBean> irb = lrb.iterator();
		// 因第一筆ResponseNoticeBean的m_id_to與之後的其他筆的取得方式不同，因此利用flag來作切換
		boolean flag = false;
		try {
			while(irb.hasNext()){
				rnb = new ResponseNoticeBean();
				// 設定第一筆ResponseNoticeBean之外的m_id_to
				String m_id_to = null;
				if (flag) {
					// 因第二筆ResponseNoticeBean的m_id_to=第一筆ResponseBean的m_id，
					// 在設定第第二筆ResponseNoticeBean資料時，需先保留第一筆ResponseBean的m_id，其他依此類推
					m_id_to = resb2.getMemberBean().getM_id();
				}
				resb2 = irb.next();
				if (!flag) {
					// 設定第一筆ResponseBean的m_id_to=resb2的貼文者的id
					rnb.setM_id_to(resb2.getPostBean().getMemberBean().getM_id());
					flag = true;
				} else {
					// 設定第一筆之後的ResponseBean的m_id_to
					rnb.setM_id_to(m_id_to);
				}
				rnb.setM_id_from(m_id_from);
				rnb.setM_name_from(m_name_from);
				rnb.setPost_id(resb2.getPostBean().getPost_id());
				rnb.setResponseBean(resb2);
				rnb.setRes_read(0);
				rn.insert(rnb);
			}
			return rn.getResponseNoticeByM_id_from(m_id_from);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// spring
	private static final ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");

//	private static final ResponseNoticeDAO rn = new ResponseNoticeDAO();
	private static final ResponseNoticeDAO rn = (ResponseNoticeDAO) context.getBean("ResponseNoticeDAO");
	private static ResponseNoticeBean rnb = null;

	public static void testGetByPrimaryKey(int res_notid) {
		rnb = rn.getByPrimaryKey(res_notid);
		TestResponseNoticeDAO.queryByResponseNoticeBean(rnb);
	}

	public static void testGetAll() {
		List<ResponseNoticeBean> lrnb = rn.getAll();
		Iterator<ResponseNoticeBean> it = lrnb.iterator();
		while(it.hasNext()) {
			rnb = (ResponseNoticeBean)it.next();
			TestResponseNoticeDAO.queryByResponseNoticeBean(rnb);
			System.out.println();
		}
	}

	public static void testInsert() {
		rnb = new ResponseNoticeBean();
		// hibernate
//		ResponseDAO res = new ResponseDAO();
		ResponseDAO res = (ResponseDAO) context.getBean("ResponseDAO");
		ResponseBean resb = res.getByPrimaryKey(3);
		rnb.setM_id_to(resb.getPostBean().getMemberBean().getM_id());
		rnb.setM_id_from(resb.getMemberBean().getM_id());
		rnb.setM_name_from(resb.getMemberBean().getM_name());
		rnb.setPost_id(resb.getPostBean().getPost_id());
		rnb.setResponseBean(resb);
		rnb.setRes_read(0);
		rn.insert(rnb);
	}

	public static void testUpdate() {
		rnb = rn.getByPrimaryKey(3);
		rnb.setRes_read(1);
		rn.update(rnb);
	}

	public static void testDelete() {
		rnb = rn.getByPrimaryKey(4);
		rn.delete(rnb);
	}

	public static void main(String[] args) {
		// hibernate
//		Session session = DefaultFactory.getSessionFactory().openSession();

//		testGetByPrimaryKey(2);
		testGetAll();
//		testInsert();
//		testUpdate();
//		testDelete();

		// hibernate
//		DefaultFactory.shutdown();
	}
}