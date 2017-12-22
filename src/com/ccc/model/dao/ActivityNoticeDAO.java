package com.ccc.model.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.ActivityNoticeBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.dao.interfacedao.IActivityNoticeDAO;

@Transactional
public class ActivityNoticeDAO implements IActivityNoticeDAO {
	
	//Spring sessionFactory
	private SessionFactory sessionFactory = null;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	public ActivityNoticeBean insert(ActivityNoticeBean value) {
//		Session session = sessionFactory.openSession();							//spring
		Session session = sessionFactory.getCurrentSession();
//		Session session = DefaultFactory.getSessionFactory().openSession();		//Hibernate
//		Transaction tx = session.beginTransaction();
		try {
			session.save(value);
//			tx.commit();
			return value;
		} catch (Exception ex) {
//			tx.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public ActivityNoticeBean update(ActivityNoticeBean value) {
//		Session session = sessionFactory.openSession();							//spring
		Session session = sessionFactory.getCurrentSession();
//		Session session = DefaultFactory.getSessionFactory().openSession();		//Hibernate
//		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(value);
//			tx.commit();
			return value;
		} catch (Exception ex) {
//			tx.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public ActivityNoticeBean delete(ActivityNoticeBean value) {
//		Session session = sessionFactory.openSession();							//spring
		Session session = sessionFactory.getCurrentSession();
//		Session session = DefaultFactory.getSessionFactory().openSession();		//Hibernate
//		Transaction tx = session.beginTransaction();
		try {
			session.delete(value);
//			tx.commit();
			return value;
		} catch (Exception ex) {
//			tx.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public ActivityNoticeBean getByPrimaryKey(int value) {
//		Session session = sessionFactory.openSession();							//spring
		Session session = sessionFactory.getCurrentSession();
//		Session session = DefaultFactory.getSessionFactory().openSession();		//Hibernate
//		Transaction tx = session.beginTransaction();
		try {
			ActivityNoticeBean not = (ActivityNoticeBean) session.get(
					ActivityNoticeBean.class, value);
//			if (not == null) {
//				System.out.println("There is noting.");
//			}
			return not;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ActivityNoticeBean> getAll() {
//		Session session = sessionFactory.openSession();							//spring
		Session session = sessionFactory.getCurrentSession();
//		Session session = DefaultFactory.getSessionFactory().openSession();		//Hibernate
//		Transaction tx = session.beginTransaction();
		try {
			Query query = session
					.createQuery("from ActivityNoticeBean ActivityNotice");
			List<ActivityNoticeBean> nots = query.list();
			return nots;
		} catch (Exception ex) {			
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ActivityNoticeBean> getMemActUnreadNotice(MemberBean value) {
//		Session session = sessionFactory.openSession();							//spring
		Session session = sessionFactory.getCurrentSession();
//		Session session = DefaultFactory.getSessionFactory().openSession();		//Hibernate
//		Transaction tx = session.beginTransaction();
		try {
			Query query = session
					.createQuery("from ActivityNoticeBean where m_id=:m_id and act_read = '0' order by act_time desc");
			query.setString("m_id", value.getM_id());
			List<ActivityNoticeBean> nots = query.list();			
			return nots;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ActivityNoticeBean> getMemAllActMsg(MemberBean value) {
//		Session session = sessionFactory.openSession();							//spring
		Session session = sessionFactory.getCurrentSession();
//		Session session = DefaultFactory.getSessionFactory().openSession();		//Hibernate
//		Transaction tx = session.beginTransaction();
		try {
			Query query = session
					.createQuery("from ActivityNoticeBean ActivityNotice where ActivityNotice.m_id = :m_id");
			query.setString("m_id", value.getM_id());
			List<ActivityNoticeBean> nots = query.list();
			return nots;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ActivityNoticeBean> getActivityNoticeByMethod(String query) {
//		Session session = sessionFactory.openSession();							//spring
		Session session = sessionFactory.getCurrentSession();
//		Session session = DefaultFactory.getSessionFactory().openSession();		//Hibernate
//		Transaction tx = session.beginTransaction();

		try {
			Query q = session.createQuery(query);
			List<ActivityNoticeBean> act = q.list();
			return act;
		} catch (RuntimeException e) {
//			tx.rollback();
			return null;
		}
	}


	@Override
	public ActivityNoticeBean getNotByMemAndAct(MemberBean mem,
			ActivityBean actIJoin) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session
					.createQuery("from ActivityNoticeBean where m_id=:m_id and act_id=:act_id");
			query.setString("m_id", mem.getM_id());
			query.setString("act_id", actIJoin.getAct_id().toString());
			ActivityNoticeBean not = (ActivityNoticeBean)query.uniqueResult();
			return not;			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ActivityNoticeBean> getThisActNotices(ActivityBean act) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session
					.createQuery("from ActivityNoticeBean where act_id=:act_id");
			query.setString("act_id", act.getAct_id().toString());
			List<ActivityNoticeBean> nots = query.list();
			return nots;			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
