package com.ccc.model.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.ccc.model.bean.FavoriteBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.dao.interfacedao.IFavoriteDAO;

@Transactional
public class FavoriteDAO implements IFavoriteDAO {
	// hibernate
//	private final Session session = DefaultFactory.getSessionFactory().openSession();

	// spring
	private SessionFactory sessionFactory = null;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

//	@Transactional
	@Override
	public FavoriteBean insert(FavoriteBean fb) {
		// hibernate
		/*Transaction tx = session.beginTransaction();
		try {
			session.save(fb);
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
			session.save(fb);
			return fb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

//	@Transactional
	@Override
	public FavoriteBean delete(FavoriteBean fb) {
		// hibernate
		/*Transaction tx = session.beginTransaction();
		try {
			session.delete(fb);
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
			session.delete(fb);
			return fb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@Override
	public FavoriteBean getByPrimaryKey(int post_id, String m_id) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		FavoriteBean fb;
		try {
			fb = (FavoriteBean) session.createQuery("from FavoriteBean where post_id=:post_id and m_id=:m_id")
					.setInteger("post_id", post_id)
					.setString("m_id", m_id)
					.uniqueResult();
			return fb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FavoriteBean> getAll() {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<FavoriteBean> lfb;
		try {
			lfb = (List<FavoriteBean>) session.createCriteria(FavoriteBean.class).list();
			return lfb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FavoriteBean> getFavoritesByMemberBean(MemberBean mb) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<FavoriteBean> lfb;
		try {
			lfb = (List<FavoriteBean>) session.createCriteria(FavoriteBean.class)
					.add(Restrictions.eq("memberBean", mb))
					.list();
			return lfb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FavoriteBean> getFavoritesByPostBean(PostBean pb) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<FavoriteBean> lfb;
		try {
			lfb = (List<FavoriteBean>) session.createCriteria(FavoriteBean.class)
					.add(Restrictions.eq("postBean", pb))
					.list();
			return lfb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FavoriteBean> getFavoritesByMethod(String query) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<FavoriteBean> lfb;
		try {
			lfb = (List<FavoriteBean>) session.createQuery(query).list();
			return lfb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<FavoriteBean> getFavoritesByMethodPerPage(String query, int page, int size) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<FavoriteBean> lfb;
		try {
			lfb = (List<FavoriteBean>) session.createQuery(query).setFirstResult(page * size).setMaxResults(size).list();
			return lfb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}
}