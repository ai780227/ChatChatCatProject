package com.ccc.model.demo;

import java.util.Iterator;
import java.util.List;

import com.ccc.model.bean.ActivityParticipateBean;

public class TestActivityParticipateDAO {
	public void printParm(ActivityParticipateBean value){
		System.out.println("活動ID:" + value.getActivity().getAct_id());
		System.out.println("參加人:" + value.getMember().getM_id());
		if (value.getAct_participate() == 0) {
			System.out.println("參加意願:未確認");
		} else if (value.getAct_participate() == 1) {
			System.out.println("參加意願:接受");
		} else if (value.getAct_participate() == 2) {
			System.out.println("參加意願:拒絕");
		} else if (value.getAct_participate() == 3) {
			System.out.println("參加意願:不確定");
		}
	}
	public void printList(List<ActivityParticipateBean> value){
		Iterator<ActivityParticipateBean> pars = value.iterator();
		while(pars.hasNext()){
			ActivityParticipateBean par = new ActivityParticipateBean();
			par = pars.next();
			printParm(par);
		}
	}
}
