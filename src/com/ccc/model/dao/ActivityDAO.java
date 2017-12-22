package com.ccc.model.dao;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import com.ccc.model.bean.ActivityBean;
import com.ccc.model.bean.ActivityParticipateBean;
import com.ccc.model.bean.FriendshipBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.dao.interfacedao.IActivityDAO;
import com.ccc.model.service.FriendService;

@Transactional
public class ActivityDAO implements IActivityDAO {

	// Spring sessionFactory
	private SessionFactory sessionFactory = null;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public ActivityBean insert(ActivityBean value) {
		// Session session = sessionFactory.openSession(); //spring
		Session session = sessionFactory.getCurrentSession();
		// Session session = DefaultFactory.getSessionFactory().openSession();
		// //Hibernate
		// Transaction tx = session.beginTransaction();
		try {
			session.save(value);
			// tx.commit();
			return value;
		} catch (Exception ex) {
			// tx.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public ActivityBean update(ActivityBean value) {
		// Session session = sessionFactory.openSession(); //spring
		Session session = sessionFactory.getCurrentSession();
		// Session session = DefaultFactory.getSessionFactory().openSession();
		// //Hibernate
		// Transaction tx = session.beginTransaction();
		Query query = session
				.createQuery("UPDATE ActivityBean SET act_title=:act_title,act_time=:act_time,act_location=:act_location,act_content=:act_content WHERE act_id=:act_id");
		query.setString("act_title", value.getAct_title());
		query.setString("act_time", (value.getAct_time()).toString());
		query.setString("act_location", value.getAct_location());
		query.setString("act_content", value.getAct_content());
		query.setString("act_id", (value.getAct_id()).toString());
		try {
			query.executeUpdate();
			// tx.commit();
			return value;
		} catch (Exception ex) {
			// tx.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public ActivityBean delete(ActivityBean value) {
		// Session session = sessionFactory.openSession(); //spring
		Session session = sessionFactory.getCurrentSession();
		// Session session = DefaultFactory.getSessionFactory().openSession();
		// //Hibernate
		// Transaction tx = session.beginTransaction();
		try {
			session.delete(value);
			// tx.commit();
			return value;
		} catch (Exception ex) {
			// tx.rollback();
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public ActivityBean getByPrimaryKey(int value) {
		// Session session = sessionFactory.openSession(); //spring
		Session session = sessionFactory.getCurrentSession();
		// Session session = DefaultFactory.getSessionFactory().openSession();
		// //Hibernate
		// Transaction tx = session.beginTransaction();
		try {
			ActivityBean act = (ActivityBean) session.get(ActivityBean.class,
					value);
//			if (act == null) {
//				System.out.println("There is noting.");
//			}
			return act;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ActivityBean> getAllPublicAct() {
		// Session session = sessionFactory.openSession(); //spring
		Session session = sessionFactory.getCurrentSession();
		// Session session = DefaultFactory.getSessionFactory().openSession();
		// //Hibernate
		// Transaction tx = session.beginTransaction();
		try {
			Query query = session
					.createQuery("from ActivityBean Activities where Activities.act_property='1'  and act_time>getdate() order by act_time asc");
			List<ActivityBean> act = query.list();
			return act;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ActivityBean> getPublicActByTheMember(String value) {
		// Session session = sessionFactory.openSession(); //spring
		Session session = sessionFactory.getCurrentSession();
		// Session session = DefaultFactory.getSessionFactory().openSession();
		// //Hibernate
		// Transaction tx = session.beginTransaction();
		try {
			// MemberBean mem = (MemberBean) session.get(MemberBean.class,
			// value);
			// Query query = session
			// .createQuery("from ActivityBean Activities where Activities.act_property='1' and Activities.m_id = :m_id");
			// query.setString("m_id", (mem.getM_id()).toString());
			// List<ActivityBean> act = query.list();
			Query query = session
					.createQuery("from ActivityBean where act_property='1' and m_id=:m_id  and act_time>getdate()");
			query.setString("m_id", value);
			List<ActivityBean> act = query.list();
			return act;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

/*	@Override
	public List<ActivityBean> getActFromTodayToTheDay(String value) {
		// Session session = sessionFactory.openSession(); //spring
		Session session = sessionFactory.getCurrentSession();
		// Session session = DefaultFactory.getSessionFactory().openSession();
		// //Hibernate
		// Transaction tx = session.beginTransaction();
		try {
			Query query = session
					.createQuery("from ActivityBean Activities where Activities.act_property='1' and act_time BETWEEN getdate() AND '"
							+ value + "'");
			List<ActivityBean> act = query.list();
			return act;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ActivityBean> getActLocation(String value) {
		// Session session = sessionFactory.openSession(); //spring
		Session session = sessionFactory.getCurrentSession();
		// Session session = DefaultFactory.getSessionFactory().openSession();
		// //Hibernate
		// Transaction tx = session.beginTransaction();
		try {
			Query query = session
					.createQuery("from ActivityBean Activities where Activities.act_property='1' and Activities.act_location LIKE:act_location");
			query.setString("act_location", "%" + value + "%");
			List<ActivityBean> act = query.list();
			return act;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ActivityBean> getActTitle(String value) {
		// Session session = sessionFactory.openSession(); //spring
		Session session = sessionFactory.getCurrentSession();
		// Session session = DefaultFactory.getSessionFactory().openSession();
		// //Hibernate
		// Transaction tx = session.beginTransaction();
		try {
			Query query = session
					.createQuery("from ActivityBean Activities where Activities.act_property='1' and Activities.act_title LIKE:act_title");
			query.setString("act_title", "%" + value + "%");
			List<ActivityBean> act = query.list();
			return act;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
*/
	@Override
	public List<ActivityBean> getActivityByMethod(String query) {
		// Session session = sessionFactory.openSession(); //spring
		Session session = sessionFactory.getCurrentSession();
		// Session session = DefaultFactory.getSessionFactory().openSession();
		// //Hibernate
		// Transaction tx = session.beginTransaction();
		Transaction tx = session.beginTransaction();
		try {
			Query q = session.createQuery(query);
			List<ActivityBean> act = q.list();
			return act;
		} catch (RuntimeException e) {
			tx.rollback();
			return null;
		}
	}

	@Override
	public List<ActivityBean> getPrivateActByTheMember(String value) {
		// Session session = sessionFactory.openSession(); //spring
		Session session = sessionFactory.getCurrentSession();
		// Session session = DefaultFactory.getSessionFactory().openSession();
		// //Hibernate
		// Transaction tx = session.beginTransaction();
		try {
			// MemberBean mem = (MemberBean) session.get(MemberBean.class,
			// value);
			// Query query = session
			// .createQuery("from ActivityBean Activities where Activities.act_property='1' and Activities.m_id = :m_id");
			// query.setString("m_id", (mem.getM_id()).toString());
			// List<ActivityBean> act = query.list();
			Query query = session
					.createQuery("from ActivityBean where act_property='0' and m_id=:m_id  and act_time>getdate()");
			query.setString("m_id", value);
			List<ActivityBean> act = query.list();
			return act;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ActivityBean> getReadyToGoPublicActs(MemberBean mem) {
		Session session = sessionFactory.getCurrentSession();
		Integer dataOfActId = null;
		ActivityBean act = null;
		try {
			// 查詢該會員活動關係為1的所有活動ID
			Query query = session
					.createQuery("from ActivityParticipateBean where m_id=:m_id and act_participate='1'");
			query.setString("m_id", mem.getM_id());
			List<ActivityParticipateBean> pars = query.list();
			Iterator<ActivityParticipateBean> dataOfP = pars.iterator();
			// 一個接act_Id的List物件
			List<Integer> actId = new ArrayList();
			while (dataOfP.hasNext()) {
				dataOfActId = dataOfP.next().getActivity().getAct_id();
				actId.add(dataOfActId);
			}
			Iterator<Integer> dataOfA = actId.iterator();
			// 一個接ActivityBean的List物件
			List<ActivityBean> acts = new ArrayList();
			while (dataOfA.hasNext()) {
				act = getByPrimaryKey(dataOfA.next());
				acts.add(act);
			}
			Iterator<ActivityBean> dataOfSA = acts.iterator();
			// 一個接搜尋過後的ActivityBean的List物件
			List<ActivityBean> actsSA = new ArrayList();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			while (dataOfSA.hasNext()) {
				act = dataOfSA.next();
				Timestamp act_time = act.getAct_time();
				Timestamp theSystemTime = new Timestamp(
						System.currentTimeMillis());
				// 搜尋條件為活動時間>系統時間以及為公開活動
				if (act_time.getTime() > theSystemTime.getTime()
						&& (act.getAct_property() == 1)) {
					actsSA.add(getByPrimaryKey(act.getAct_id()));
				}
			}
			return actsSA;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ActivityBean> getReadyToGoPrivateActs(MemberBean mem) {
		Session session = sessionFactory.getCurrentSession();
		Integer dataOfActId = null;
		ActivityBean act = null;
		try {
			// 查詢該會員活動關係為1的所有活動ID
			Query query = session
					.createQuery("from ActivityParticipateBean where m_id=:m_id and act_participate='1'");
			query.setString("m_id", mem.getM_id());
			List<ActivityParticipateBean> pars = query.list();
			Iterator<ActivityParticipateBean> dataOfP = pars.iterator();
			// 一個接act_Id的List物件
			List<Integer> actId = new ArrayList();
			while (dataOfP.hasNext()) {
				dataOfActId = dataOfP.next().getActivity().getAct_id();
				actId.add(dataOfActId);
			}
			Iterator<Integer> dataOfA = actId.iterator();
			// 一個接ActivityBean的List物件
			List<ActivityBean> acts = new ArrayList();
			while (dataOfA.hasNext()) {
				act = getByPrimaryKey(dataOfA.next());
				acts.add(act);
			}
			Iterator<ActivityBean> dataOfSA = acts.iterator();
			// 一個接搜尋過後的ActivityBean的List物件
			List<ActivityBean> actsSA = new ArrayList();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			while (dataOfSA.hasNext()) {
				act = dataOfSA.next();
				Timestamp act_time = act.getAct_time();
				Timestamp theSystemTime = new Timestamp(
						System.currentTimeMillis());
				// 搜尋條件為活動時間>系統時間以及為私人活動
				if (act_time.getTime() > theSystemTime.getTime()
						&& (act.getAct_property() == 0)) {
					actsSA.add(getByPrimaryKey(act.getAct_id()));
				}
			}
			return actsSA;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ActivityBean> viewTheInviteOfPri(MemberBean mem) {
		Session session = sessionFactory.getCurrentSession();
		Integer dataOfActId = null;
		ActivityBean act = null;
		try {
			// 查詢該會員活動關係為0的所有活動ID
			Query query = session
					.createQuery("from ActivityParticipateBean where m_id=:m_id and act_participate='0'");
			query.setString("m_id", mem.getM_id());
			List<ActivityParticipateBean> pars = query.list();
			Iterator<ActivityParticipateBean> dataOfP = pars.iterator();
			// 一個接act_Id的List物件
			List<Integer> actId = new ArrayList();
			while (dataOfP.hasNext()) {
				dataOfActId = dataOfP.next().getActivity().getAct_id();
				actId.add(dataOfActId);
			}
			Iterator<Integer> dataOfA = actId.iterator();
			// 一個接ActivityBean的List物件
			List<ActivityBean> acts = new ArrayList();
			while (dataOfA.hasNext()) {
				act = getByPrimaryKey(dataOfA.next());
				acts.add(act);
			}
			Iterator<ActivityBean> dataOfSA = acts.iterator();
			// 一個接搜尋過後的ActivityBean的List物件
			List<ActivityBean> actsSA = new ArrayList();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			while (dataOfSA.hasNext()) {
				act = dataOfSA.next();
				Timestamp act_time = act.getAct_time();
				Timestamp theSystemTime = new Timestamp(
						System.currentTimeMillis());
				// 搜尋條件為活動時間>系統時間以及為私人活動
				if (act_time.getTime() > theSystemTime.getTime()
						&& (act.getAct_property() == 0)) {
					actsSA.add(getByPrimaryKey(act.getAct_id()));
				}
			}
			return actsSA;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ActivityBean> viewTheInviteOfPub(MemberBean mem) {
		Session session = sessionFactory.getCurrentSession();
		Integer dataOfActId = null;
		ActivityBean act = null;
		try {
			// 查詢該會員活動關係為0的所有活動ID
			Query query = session
					.createQuery("from ActivityParticipateBean where m_id=:m_id and act_participate='0'");
			query.setString("m_id", mem.getM_id());
			List<ActivityParticipateBean> pars = query.list();
			Iterator<ActivityParticipateBean> dataOfP = pars.iterator();
			// 一個接act_Id的List物件
			List<Integer> actId = new ArrayList();
			while (dataOfP.hasNext()) {
				dataOfActId = dataOfP.next().getActivity().getAct_id();
				actId.add(dataOfActId);
			}
			Iterator<Integer> dataOfA = actId.iterator();
			// 一個接ActivityBean的List物件
			List<ActivityBean> acts = new ArrayList();
			while (dataOfA.hasNext()) {
				act = getByPrimaryKey(dataOfA.next());
				acts.add(act);
			}
			Iterator<ActivityBean> dataOfSA = acts.iterator();
			// 一個接搜尋過後的ActivityBean的List物件
			List<ActivityBean> actsSA = new ArrayList();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			while (dataOfSA.hasNext()) {
				act = dataOfSA.next();
				Timestamp act_time = act.getAct_time();
				Timestamp theSystemTime = new Timestamp(
						System.currentTimeMillis());
				// 搜尋條件為活動時間>系統時間以及為公開活動
				if (act_time.getTime() > theSystemTime.getTime()
						&& (act.getAct_property() == 1)) {
					actsSA.add(getByPrimaryKey(act.getAct_id()));
				}
			}
			return actsSA;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ActivityBean> getActByTheMember(MemberBean mem) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Query query = session
					.createQuery("from ActivityBean where m_id=:m_id and act_time>getdate() and act_property<>2 order by act_time ASC");
			query.setString("m_id", mem.getM_id());
			List<ActivityBean> act = query.list();
			return act;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<String> getMyFriendNotInMyAct(int act_id, MemberBean mem) {
		Session session = sessionFactory.getCurrentSession();
		try {
			// 查詢我的好友的ID並且塞入變數List<String> f
			FriendService friService = new FriendService();
			List<MemberBean> myFri = friService.MyFriendName(mem);
			Iterator<MemberBean> itm = myFri.iterator();
			List<String> f = new ArrayList<String>();
			while (itm.hasNext()) {
				f.add(itm.next().getM_id());
			}
			// 搜尋參加該活動的會員ID並且塞入變數List<String> p
			Query query1 = session
					.createQuery("from ActivityParticipateBean where act_id=:act_id");
			query1.setInteger("act_id", act_id);
			List<ActivityParticipateBean> pars = query1.list();
			List<String> p = new ArrayList<String>();
			//如果活動關係人是不是空的
			if (pars.isEmpty() == false) {
				Iterator<ActivityParticipateBean> itp = pars.iterator();
				while (itp.hasNext()) {
					p.add(itp.next().getMember().getM_id());
				}
				// 從好友名單f複製一個List<String> m_id
				List<String> m_id = f;
				// 比較兩個List<String>裡面的值,若無重複則加入到m_id
				for (int i = 0; i < f.size(); i++) {
					for (int j = 0; j < p.size(); j++) {
						// 如果重複則移除
						if (f.get(i).equals(p.get(j))) {
							m_id.remove(i);
						}
					}
				}
				
//				// 轉回List<MemberBean>
//				Iterator<String> its = m_id.iterator();
//				List<MemberBean> myFriendsNotInMyAct = new ArrayList<MemberBean>();
//				// 呼叫MemberDAO
//				MemberDAO memDAO = new MemberDAO();
//				while (its.hasNext()) {
//					myFriendsNotInMyAct.add(memDAO.getByPrimaryKey(its.next()));
//				}
				return m_id;
			} else {
				return f;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateProperty(ActivityBean value) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("UPDATE ActivityBean SET act_property='2' WHERE act_id=:act_id");
		query.setInteger("act_id", value.getAct_id());
		try {
			query.executeUpdate();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<ActivityBean> getActivitiesByString(String value) {
		Session session = sessionFactory.getCurrentSession();
			if(value.isEmpty()==false){
				Query query = session.createQuery("from ActivityBean where act_property='1' and act_time>getdate() and ((act_title like:act_title) or  (act_location like:act_location))");
				query.setString("act_title","%"+value+"%");
				query.setString("act_location","%"+value+"%");
				List<ActivityBean> acts = query.list();
				return acts;			
			}else{
				return null;
			}			
	}
}
