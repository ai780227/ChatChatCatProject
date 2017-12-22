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
import org.springframework.transaction.annotation.Transactional;

import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.NewsBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.dao.interfacedao.INewsDAO;

@Transactional
public class NewsDAO implements INewsDAO {
	Transaction tx = null;

	//Spring sessionFactory
	private SessionFactory sessionFactory = null;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
//===========================================insert=============================================================================================================
	public NewsBean insert(NewsBean newsB) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(newsB);
			return newsB;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
//==========================================update==============================================================================================================	
	public NewsBean update(NewsBean newsB) {
		Session session = sessionFactory.getCurrentSession();
//		String hql = "UPDATE NewsBean SET mgr_id=:mgr_id , news_title=:news_title , news_time=:news_time , news_content=:news_content , news_source=:news_source , news_link=:news_link WHERE news_id=:news_id";
//		Query query = session.createQuery(hql);
//		query.setInteger("news_id", newsB.getNews_id());
//		query.setString("mgr_id", newsB.getMgr_id());
//		query.setString("news_title", newsB.getNews_title());
//		query.setDate("news_time", newsB.getNews_time());
//		query.setString("news_content", newsB.getNews_content());
//		query.setString("news_source", newsB.getNews_source());
//		query.setString("news_link", newsB.getNews_link());		
		try {
//			query.executeUpdate();
			session.update(newsB);
			return newsB;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}


	}
	
//==========================================delete==============================================================================================================
	public NewsBean delete(NewsBean newsB) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "DELETE NewsBean WHERE news_id=:news_id";
		Query query = session.createQuery(hql);
		query.setInteger("news_id", newsB.getNews_id());
		try {
			query.executeUpdate();
			return newsB;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
//=======================================getByPrimaryKey=================================================================================================================
	@Override
	public NewsBean getByPrimaryKey(int news_id) {
		// spring
	
		Session session = sessionFactory.getCurrentSession();
		NewsBean nb;
	
			nb = (NewsBean) session.get(NewsBean.class, news_id);
//			if (nb == null) {
//				System.out.println("There is noting.");
//			}
			try {
			return nb;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
//=======================================getAll===================================================================================================================	
	@Override
	public List<NewsBean> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<NewsBean> listAll;
		Iterator listA = null;
		try {
		String sql = "from NewsBean News";
		Query query = session.createQuery(sql);
		listAll = (List<NewsBean>) query.list();
			if(listAll.size()!=0){
				return listAll;
			}else{
				return null;
			}
		}catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("錯誤");
		}
		return null;
	}

	@Override
	public List<NewsBean> getByMgrId(String value) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session.createQuery("from NewsBean where mgr_id=:mgr_id");
			query.setString("mgr_id", value);
			List<NewsBean> nws = query.list();
			return nws;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<NewsBean> getByTitle(String value) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session
					.createQuery("from NewsBean News where News.news_title LIKE :news_title");
			query.setString("news_title", "%" + value + "%");
			
			List<NewsBean> nws = query.list();
			
			return nws;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<NewsBean> getByDayToDay(String value1, String value2) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session
					.createQuery("from NewsBean where news_time BETWEEN '" + value1 + "' AND '" + value2 + "'");
			List<NewsBean> nws = query.list();
			return nws;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<NewsBean> getByNewsContent(String value) {
		Session session = sessionFactory.getCurrentSession();
		List<NewsBean> lnb;
		try {
			lnb = (List<NewsBean>) session.createQuery("from NewsBean where news_content like :news_content")
					.setString("news_content", "%" + value + "%")
					.list();
			return lnb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<NewsBean> getByNewsSource(String value) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session
					.createQuery("from NewsBean News where News.news_source LIKE:news_source");
			query.setString("news_source", "%" + value + "%");
			List<NewsBean> nws = query.list();
			return nws;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<NewsBean> getNewsByMethod(String query) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Query q = session.createQuery(query);
			List<NewsBean> nws = q.list();
			return nws;
		} catch (Exception e) {
			tx.rollback();
			return null;
		}
	}		
		
	@Override
	public void queryByNewsBean(NewsBean nb) {
		if (nb != null) {
			System.out.println("news_id = " + nb.getNews_id());
			System.out.println("mgr_id = " + nb.getMgr_id());
			System.out.println("news_title = " + nb.getNews_title());
			System.out.println("news_time = " + nb.getNews_time());
			System.out.println("news_content = " + nb.getNews_content());
			System.out.println("news_source = " + nb.getNews_source());
			System.out.println("news_link = " + nb.getNews_link());
		} else {
			System.out.println("There is nothing in the NewsBean!");
		}
	}
	
	
	/////////////////////////////////////////
	public List<NewsBean> getNewsOnPages(String query,int pages) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Query q = session.createQuery(query);
			List<NewsBean> nws = q.setMaxResults(pages).list();
			return nws;
		} catch (Exception e) {
			tx.rollback();
			return null;
		}
	}
}		
		
		
	

