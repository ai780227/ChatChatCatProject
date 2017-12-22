package com.ccc.model.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.ccc.model.bean.ResponseBean;
import com.ccc.model.bean.ResponseNoticeBean;
import com.ccc.model.dao.interfacedao.IResponseNoticeDAO;

@Transactional
public class ResponseNoticeDAO implements IResponseNoticeDAO {
	// hibernate
//	private final Session session = DefaultFactory.getSessionFactory().openSession();

	// spring
	private SessionFactory sessionFactory = null;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

//	@Transactional
	@Override
	public ResponseNoticeBean insert(ResponseNoticeBean rnb) {
		// hibernate
		/*Transaction tx = session.beginTransaction();
		try {
			session.save(rnb);
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
			session.save(rnb);
			return rnb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

//	@Transactional
	@Override
	public ResponseNoticeBean update(ResponseNoticeBean rnb) {
		// hibernate
		/*Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(rnb);
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
			session.saveOrUpdate(rnb);
			return rnb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

//	@Transactional
	@Override
	public ResponseNoticeBean delete(ResponseNoticeBean rnb) {
		// hibernate
		/*Transaction tx = session.beginTransaction();
		try {
			session.delete(rnb);
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
			session.delete(rnb);
			return rnb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@Override
	public ResponseNoticeBean getByPrimaryKey(int res_notid) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		ResponseNoticeBean rnb;
		try {
			rnb = (ResponseNoticeBean) session.get(ResponseNoticeBean.class, res_notid);
			return rnb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResponseNoticeBean> getAll() {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<ResponseNoticeBean> lrnb;
		try {
			lrnb = (List<ResponseNoticeBean>) session.createCriteria(ResponseNoticeBean.class).list();
			return lrnb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResponseNoticeBean> getResponseNoticeByM_id_to(String m_id_to) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<ResponseNoticeBean> lrnb;
		try {
			lrnb = (List<ResponseNoticeBean>) session.createCriteria(ResponseNoticeBean.class)
					.add(Restrictions.eq("m_id_to", m_id_to))
					.list();
			return lrnb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResponseNoticeBean> getResponseNoticeByMethod(String query) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<ResponseNoticeBean> lrnb;
		try {
			lrnb = (List<ResponseNoticeBean>) session.createQuery(query).list();
			return lrnb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResponseNoticeBean> getResponseNoticeByRes_read(int res_read) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<ResponseNoticeBean> lrnb;
		try {
			lrnb = (List<ResponseNoticeBean>) session.createCriteria(ResponseNoticeBean.class)
					.add(Restrictions.eq("res_read", res_read))
					.list();
			return lrnb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResponseNoticeBean> getResponseNoticeByResponseBean(ResponseBean resb) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<ResponseNoticeBean> lrnb;
		try {
			lrnb = (List<ResponseNoticeBean>) session.createCriteria(ResponseNoticeBean.class)
					.add(Restrictions.eq("responseBean", resb))
					.list();
			return lrnb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResponseNoticeBean> getResponseNoticeByM_id_from(String m_id_from) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<ResponseNoticeBean> lrnb;
		try {
			lrnb = (List<ResponseNoticeBean>) session.createCriteria(ResponseNoticeBean.class)
					.add(Restrictions.eq("m_id_from", m_id_from))
					.list();
			return lrnb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}
}