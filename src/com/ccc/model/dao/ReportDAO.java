package com.ccc.model.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.bean.ReportBean;
import com.ccc.model.dao.interfacedao.IReportDAO;

@Transactional
public class ReportDAO implements IReportDAO {

	private SessionFactory sessionFactory = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// 加入一筆資料
	@Override
	public ReportBean insert(ReportBean rep) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(rep);
			return rep;
		} catch (Exception ex) {
			return null;
		}

	}

	// 更新一筆資料
	@Override
	public ReportBean update(ReportBean rep) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.saveOrUpdate(rep);
			return rep;
		} catch (Exception ex) {
			throw ex;
		}

	}

	// 刪除一筆資料
	@Override
	public ReportBean delete(ReportBean rep) {// 指能夠透過primaryKey刪除
		Session session = sessionFactory.getCurrentSession();
		try {
			session.delete(rep);
			return rep;
		} catch (Exception ex) {
			return null;
		}
	}

	// 透過PrimaryKey查詢
	@Override
	public ReportBean getByPrimaryKey(int rep_id) {
		ReportBean post = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			Criteria criteria = session.createCriteria(ReportBean.class);
			criteria.add(Restrictions.eq("rep_id", rep_id));
			Iterator people = criteria.list().iterator();
			while (people.hasNext()) {
				post = (ReportBean) people.next();				
			}
			return post;
		} catch (Exception ex) {
			return null;
		}

	}

	// getAll
	@Override
	public List<ReportBean> getAll() {
		List<ReportBean> rep;
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session.createQuery("from ReportBean order by rep_id desc");
			rep = query.list();
			return rep;
		} catch (Exception ex) {
			throw ex;
		}
	}

	// 刪除某貼文的檢舉
	@Override
	public List<ReportBean> deleteByPostID(PostBean post) {
		List<ReportBean> rep;
		int post_id = post.getPost_id();
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session
					.createQuery("from ReportBean where post_id=:post_id");
			query.setParameter("post_id", post_id);
			rep = query.list();
			for (int i = 0; i < rep.size(); i++) {
				ReportBean re = (ReportBean) rep.get(i);
				session.delete(re);
			}
			return rep;
		} catch (Exception ex) {
			return null;
		}
	}

	// 透過會員id刪除檢舉
	@Override
	public List<ReportBean> deleteByMemberID(MemberBean mem) {
		List<ReportBean> rep;
		String m_id = mem.getM_id();
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session
					.createQuery("from ReportBean where m_id=:m_id");
			query.setParameter("m_id", m_id);
			rep = query.list();
			for (int i = 0; i < rep.size(); i++) {
				ReportBean re = (ReportBean) rep.get(i);
				session.delete(re);
			}
			return rep;
		} catch (Exception ex) {
			return null;
		}
	}

	// 透過會員id查詢
	@Override
	public List<ReportBean> getByMember(MemberBean mem) {
		List<ReportBean> rep;
		String m_id = mem.getM_id();
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session
					.createQuery("from ReportBean where m_id=:m_id");
			// 取得所有資料
			query.setString("m_id", m_id);
			rep = query.list();
			return rep;
		} catch (Exception ex) {
			return null;
		}

	}

}
