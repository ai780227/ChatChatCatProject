package com.ccc.model.dao.interfacedao;

import java.util.List;


import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.bean.ReportBean;


public interface IReportDAO {
	public ReportBean insert(ReportBean rep);//新增	
	public ReportBean update(ReportBean rep);//修改
	public ReportBean delete(ReportBean rep);//刪除
	public ReportBean getByPrimaryKey(int rep_id);//透過主鍵查詢
	public List<ReportBean> getAll();//查詢所有
	
	public List<ReportBean> getByMember(MemberBean mem);//透過會員ID查詢
	public List<ReportBean> deleteByPostID(PostBean post);//透過貼文ID刪除
	public List<ReportBean> deleteByMemberID(MemberBean mem);//透過會員ID刪除
}
