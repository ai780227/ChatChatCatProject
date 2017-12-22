package com.ccc.model.dao.interfacedao;

import java.util.List;






import com.ccc.model.bean.BoardBean;
import com.ccc.model.bean.NewsBean;

public interface IBoardDAO {
	public BoardBean insert(BoardBean boardB);
	public BoardBean update(BoardBean boardB);
	public BoardBean delete(BoardBean boardB);
	public BoardBean getByPrimaryKey(int boardid);
	public List<BoardBean> getAll();
	
	public List<BoardBean> getByMgrId(String value);
	public List<BoardBean> getByDayToDay(String value1, String value2);
	public List<BoardBean> getByBoardContent(String value);
	void queryByBoardBean(BoardBean bb);
}
