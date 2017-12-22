package com.ccc.model.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.bean.ResponseBean;
import com.ccc.model.dao.interfacedao.IResponseDAO;

@Transactional
public class ResponseDAO implements IResponseDAO {
	// hibernate
//	private final Session session = DefaultFactory.getSessionFactory().openSession();

	// spring
	private SessionFactory sessionFactory = null;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

//	@Transactional
	@Override
	public ResponseBean insert(ResponseBean resb) {
		// hibernate
		/*Transaction tx = session.beginTransaction();
		try {
			session.save(resb);
			tx.commit();
		} catch (HibernateException ex) {
			tx.rollback();
			throw ex;
		} finally {
			session.close();
		}*/

		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(resb);
			return resb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

//	@Transactional
	@Override
	public ResponseBean update(ResponseBean resb) {
		// hibernate
		/*Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(resb);
			tx.commit();
		} catch (HibernateException ex) {
			tx.rollback();
			throw ex;
		} finally {
			session.close();
		}*/

		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.saveOrUpdate(resb);
			return resb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

//	@Transactional
	@Override
	public ResponseBean delete(ResponseBean resb) {
		// hibernate
		/*Transaction tx = session.beginTransaction();
		try {
			session.delete(resb);
			tx.commit();
		} catch (HibernateException ex) {
			tx.rollback();
			throw ex;
		} finally {
			session.close();
		}*/

		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.delete(resb);
			return resb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@Override
	public ResponseBean getByPrimaryKey(int res_id) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		ResponseBean resb;
		try {
			resb = (ResponseBean) session.get(ResponseBean.class, res_id);
			return resb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResponseBean> getAll() {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<ResponseBean> lresb;
		try {
			lresb = (List<ResponseBean>) session.createCriteria(ResponseBean.class).list();
			return lresb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResponseBean> getResponsesByMemberBean(MemberBean mb) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<ResponseBean> lresb;
		try {
			lresb = (List<ResponseBean>) session.createCriteria(ResponseBean.class)
					.add(Restrictions.eq("memberBean", mb))
					.list();
			return lresb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResponseBean> getResponsesByPostBean(PostBean pb) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<ResponseBean> lresb;
		try {
			lresb = (List<ResponseBean>) session.createCriteria(ResponseBean.class)
					.add(Restrictions.eq("postBean", pb))
					.list();
			return lresb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResponseBean> getResponsesByMethod(String query) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<ResponseBean> lresb;
		try {
			lresb = (List<ResponseBean>) session.createQuery(query).list();
			return lresb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}
}