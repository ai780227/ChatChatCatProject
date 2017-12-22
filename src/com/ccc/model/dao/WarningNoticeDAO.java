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

import com.ccc.model.bean.WarningNoticeBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.dao.interfacedao.IWarningNoticeDAO;

@Transactional
public class WarningNoticeDAO implements IWarningNoticeDAO {
	
	private SessionFactory sessionFactory = null;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	//加入一筆資料
	public WarningNoticeBean insert(WarningNoticeBean war) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.save(war);
			return war;
		} catch (Exception ex) {
			return null;
		}	
	}
	
	//更新一筆資料 
	public WarningNoticeBean update(WarningNoticeBean war) {
		Session session = sessionFactory.getCurrentSession();
		try{
			session.saveOrUpdate(war);
			return war;
		}catch (Exception ex) {
			throw ex;
		}
	}
	
	//刪除一筆資料
	public WarningNoticeBean delete(WarningNoticeBean war){//指能夠透過primaryKey刪除
		Session session = sessionFactory.getCurrentSession();
		try {
			session.delete(war);
			return war;
		} catch (Exception ex) {
			return null;
			}
		}
	
	//透過PrimaryKey查詢
	public WarningNoticeBean getByPrimaryKey(int war_notid) {
		WarningNoticeBean war = null;
		Session session = sessionFactory.getCurrentSession();
		try{
		Criteria criteria = session.createCriteria(WarningNoticeBean.class);
		criteria.add(Restrictions.eq("war_notid",war_notid));
		Iterator people = criteria.list().iterator();
		while (people.hasNext()) {
			war = (WarningNoticeBean) people.next();
			}
		return war;
		}catch (Exception ex) {
			return null;
		}
	}
	
	//getAll
	public List<WarningNoticeBean> getAll(){
		List<WarningNoticeBean> war;
		Session session = sessionFactory.getCurrentSession();
		try{
			Query query=session.createQuery("from WarningNoticeBean");
			war=query.list();
			return war;
		}catch (Exception ex) {
			return null;
		}
	}
	
	
	//刪除某會員資料
	@Override
	public List<WarningNoticeBean> deleteByMember(MemberBean mem){
		List<WarningNoticeBean> war;
		Session session = sessionFactory.getCurrentSession();
		try{
			String m_id=mem.getM_id();
			Query query=session.createQuery("from WarningNoticeBean where m_id=:m_id");
			query.setParameter("m_id",m_id);
			war=query.list();
			for(int i=0;i<war.size();i++){
			WarningNoticeBean wa=(WarningNoticeBean)war.get(i);
			session.delete(wa);
			}
			return war ;
		}catch (Exception ex) {
			return null;
		}
	}
	

	//透過會員查詢
	@Override
	public List<WarningNoticeBean> getByMember(MemberBean mem) {
		List<WarningNoticeBean> war;
		Session session = sessionFactory.getCurrentSession();
		try{
			String m_id=mem.getM_id();
			Query query=session.createQuery("from WarningNoticeBean where m_id=:m_id");
			query.setParameter("m_id",m_id);
			war = query.list();
			return war;
		}catch (Exception ex) {
			return null;
		}
	}


}
