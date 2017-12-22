package com.ccc.model.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.ActivityParticipateBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.dao.interfacedao.IActivityParticipateDAO;

@Transactional
public class ActivityParticipateDAO implements IActivityParticipateDAO {
	
	//Spring sessionFactory
	private SessionFactory sessionFactory = null;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public ActivityParticipateBean insert(ActivityParticipateBean value) {
		Session session = sessionFactory.getCurrentSession();

		try {
			session.save(value);
			return value;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public ActivityParticipateBean update(ActivityParticipateBean value) {
		Session session = sessionFactory.getCurrentSession();

		try {
			session.saveOrUpdate(value);
			return value;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public ActivityParticipateBean delete(ActivityParticipateBean actParti) {
		Session session = sessionFactory.getCurrentSession();
		try{
			session.delete(actParti);
			return actParti;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ActivityParticipateBean getByPrimaryKey(int actId, String mId) {
		
		Session session = sessionFactory.getCurrentSession();
		try{
			Query query = session.createQuery("from ActivityParticipateBean where act_id=:act_id and m_id=:m_id");
			query.setInteger("act_id", actId);
			query.setString("m_id", mId);
			ActivityParticipateBean apb = (ActivityParticipateBean) query.list().get(0);
			return apb;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<ActivityParticipateBean> getAll() {
		Session session = sessionFactory.getCurrentSession();

		try {
			Query query = session
					.createQuery("from ActivityParticipateBean ActivityParticipate");
			List<ActivityParticipateBean> pars = query.list();
			return pars;
		} catch (Exception ex) {		
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ActivityParticipateBean> getMemWasInvited(MemberBean value) {
		Session session = sessionFactory.getCurrentSession();

		try {
			Query query = session
					.createQuery("from ActivityParticipateBean where m_id = :m_id");
			query.setString("m_id", (value.getM_id()).toString());
			List<ActivityParticipateBean> pars = query.list();			
			return pars;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ActivityParticipateBean> getActRelated(ActivityBean value) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session
					.createQuery("from ActivityParticipateBean where act_id = :act_id");
			query.setInteger("act_id", value.getAct_id());
			List<ActivityParticipateBean> pars = query.list();
			return pars;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ActivityParticipateBean> getActivityParticipateByMethod(
			String query) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Query q = session.createQuery(query);
			List<ActivityParticipateBean> act = q.list();
			return act;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
		
	//回傳該活動有多少人參加
	@Override
	public Integer getCountOfTheActWhoJoin(ActivityBean act){
		Session session = sessionFactory.getCurrentSession();
		try {
			//select * from ActivityParticipate where act_participate = '1' and act_id = '63'
			Query query = session
					.createQuery("from ActivityParticipateBean where act_participate='1' and act_id =:act_id");
			query.setInteger("act_id", act.getAct_id());
			return query.list().size();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
}
