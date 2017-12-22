package com.ccc.model.demo;

import java.util.Iterator;
import java.util.List;

import com.ccc.model.bean.ActivityBean;

public class TestActivityDAO {
	public void printParm(ActivityBean value) {
		System.out.println("活動編號= " + value.getAct_id());
		System.out.println("活動發起人= " + value.getMember().getM_id());
		if (value.getAct_property() == 1) {
			System.out.println("活動性質= 公開活動");
		} else {
			System.out.println("活動性質= 私人活動");
		}
		System.out.println("活動標題= "+value.getAct_title());
		System.out.println("活動時間= "+value.getAct_time());
		System.out.println("活動地點= "+value.getAct_location());
		System.out.println("活動內容= "+value.getAct_content());
		System.out.println("活動截止日= "+value.getAct_deadline());
	}

	public void printList(List<ActivityBean> value) {
		Iterator<ActivityBean> acts = value.iterator();
		while (acts.hasNext()) {
			ActivityBean act = new ActivityBean();
			act = acts.next();
			printParm(act);
		}
	}
}
