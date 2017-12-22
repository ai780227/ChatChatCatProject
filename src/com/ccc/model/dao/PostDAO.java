package com.ccc.model.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.dao.interfacedao.IPostDAO;

@Transactional
public class PostDAO implements IPostDAO {
	// hibernate
//	private final Session session = DefaultFactory.getSessionFactory().openSession();

	// spring
	private SessionFactory sessionFactory = null;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

//	@Transactional
	@Override
	public PostBean insert(PostBean pb) {
		// hibernate
		/*Transaction tx = session.beginTransaction();
		try {
			session.save(pb);
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
			session.save(pb);
			return pb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

//	@Transactional
	@Override
	public PostBean update(PostBean pb) {
		// hibernate
		/*Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(pb);
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
			session.saveOrUpdate(pb);
			return pb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

//	@Transactional
	@Override
	public PostBean delete(PostBean pb) {
		// hibernate
		/*Transaction tx = session.beginTransaction();
		try {
			session.delete(pb);
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
			session.delete(pb);
			return pb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@Override
	public PostBean getByPrimaryKey(int post_id) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		PostBean pb;
		try {
			pb = (PostBean) session.get(PostBean.class, post_id);
			return pb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostBean> getAll() {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<PostBean> lpb;
		try {
			lpb = (List<PostBean>) session.createCriteria(PostBean.class).list();
			return lpb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostBean> getPostsByMemberBean(MemberBean mb) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<PostBean> lpb;
		try {
			lpb = (List<PostBean>) session.createCriteria(PostBean.class)
					.add(Restrictions.eq("memberBean", mb))
					.list();
			return lpb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostBean> getPostsByType(int post_type) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<PostBean> lpb;
		try {
			lpb = (List<PostBean>) session.createCriteria(PostBean.class)
					.add(Restrictions.eq("post_type", post_type))
					.list();
			return lpb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostBean> getPostsByProperty(int post_property) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<PostBean> lpb;
		try {
			lpb = (List<PostBean>) session.createCriteria(PostBean.class)
					.add(Restrictions.eq("post_property", post_property))
					.list();
			return lpb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostBean> getPostsByPost_content(String post_content) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<PostBean> lpb;
		try {
			lpb = (List<PostBean>) session.createQuery("from PostBean where post_content like :post_content")
					.setString("post_content", "%" + post_content + "%")
					.list();
			return lpb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostBean> getPostsByLike_count(int like_count) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<PostBean> lpb;
		try {
			lpb = (List<PostBean>) session.createCriteria(PostBean.class)
					.add(Restrictions.eq("like_count", like_count))
					.list();
			return lpb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return  null;
		}
//		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostBean> getPostsByMethod(String query) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<PostBean> lpb;
		try {
			lpb = (List<PostBean>) session.createQuery(query).list();
			return lpb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PostBean> getPostsByActivityBean(ActivityBean actb) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<PostBean> lpb;
		try {
			lpb = (List<PostBean>) session.createCriteria(PostBean.class)
					.add(Restrictions.eq("activityBean", actb))
					.list();
			return lpb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<PostBean> getPostsByMethodPerPage(String query, int page, int size) {
		// spring
//		Session session = sessionFactory.openSession();
		Session session = sessionFactory.getCurrentSession();
		List<PostBean> lpb;
		try {
			lpb = (List<PostBean>) session.createQuery(query).setFirstResult(page * size).setMaxResults(size).list();
			return lpb;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
//		session.close();
	}
}