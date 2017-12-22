package com.ccc.model.dao.interfacedao;

import java.util.List;

import com.ccc.model.bean.WarningNoticeBean;
import com.ccc.model.bean.MemberBean;

public interface IWarningNoticeDAO {
	public WarningNoticeBean insert(WarningNoticeBean war);//新增
	public WarningNoticeBean update(WarningNoticeBean war);//修改
	public WarningNoticeBean delete(WarningNoticeBean war);//刪除
	public WarningNoticeBean getByPrimaryKey(int war_notid);//透過主鍵查詢
	public List<WarningNoticeBean> getAll();//查詢所有
	
	public List<WarningNoticeBean> getByMember(MemberBean mem);//透過會員ID查詢
	public List<WarningNoticeBean> deleteByMember(MemberBean mem);//透過會員ID刪除
	
	
}
