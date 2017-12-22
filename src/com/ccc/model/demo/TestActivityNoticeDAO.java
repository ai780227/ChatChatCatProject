package com.ccc.model.demo;

import java.util.Iterator;
import java.util.List;

import com.ccc.model.bean.ActivityNoticeBean;

public class TestActivityNoticeDAO {
	public void printParm(ActivityNoticeBean value){
		System.out.println("通知ID:"+ value.getAct_notid());
		System.out.println("會員ID:"+value.getMember().getM_id());
		System.out.println("活動ID:"+ value.getActivity().getAct_id());
		if(value.getAct_read()==0){
			System.out.println("讀取狀態:未讀");
		}else{
			System.out.println("讀取狀態:已讀");
		}
		System.out.println("活動通知時間:"+value.getAct_time());
	}
	
	public void printList(List<ActivityNoticeBean> value){
		Iterator<ActivityNoticeBean> nots = value.iterator();
		while (nots.hasNext()) {
			ActivityNoticeBean not = new ActivityNoticeBean();
			not = nots.next();
			printParm(not);			
		}
	}
}
