package com.ccc.model.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.BoardBean;
import com.ccc.model.dao.BoardDAO;

public class BoardService {

	BoardDAO boarddao;//建立BoardDAO屬性

	public BoardService() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		this.boarddao = (BoardDAO) context.getBean("BoardDAO");//還傳BoardDAO物件
	}

	// 新增公告
	public BoardBean AddBoard(BoardBean board) {//代入BoardBean物件
		if (board != null) {//如果BoardBean物件不為null
			boarddao.insert(board);//BoardDAO物件呼叫insert(代入BoardBean物件)
			return board;//回傳BoardBean物件
		} else {
			return null;
		}
	}

	// 修改公告
	public BoardBean updateBoard(BoardBean board) {//代入BoardBean物件
		if (board!=null){//如果BoardBean物件不是null
			BoardBean boa = boarddao.update(board);//呼叫update回傳BoardBean物件
			return boa;//回傳BoardBean物件
		} else {
			return null;
		}
	}

	// 刪除公告
	public BoardBean deleteBoard(BoardBean board) {//代入BoardBean物件
		if (board != null) {//如果BoardBean物件不是null
			boarddao.delete(board);//呼叫delete回傳BoardBean物件
			return board;//回傳BoardBean物件
		} else {
			return null;
		}
	}

	// 瀏覽所有公告
	public List<BoardBean> viewAllBoard() {
		List<BoardBean> boa;
		boa = boarddao.getAll();//呼叫getAll回傳List<BoardBean>物件
		return boa;//回傳List<BoardBean>物件
	}

	// 瀏覽一則公告
	public BoardBean viewOneBoard(BoardBean board) {//代入BoardID
		if (board!= null) {//如果BoardBean物件不是null
			int board_id=board.getBoard_id();
			BoardBean bo = boarddao.getByPrimaryKey(board_id);//呼叫getByPrimaryKey回傳BoardBean物件
			return bo;//回傳BoardBean物件
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		BoardService b = new BoardService();

		// 測試新增公告
//		 BoardBean board=new BoardBean();
//		
//		 board.setMgr_id("manager001");//設置新的會員ID
//		 board.setBoard_content("哥吉拉來了");//設置新的公告內容
//		 Timestamp ts=new Timestamp(System.currentTimeMillis());
//		 board.setBoard_time(ts);//設置新的公告時間
//		 b.AddBoard(board);//新增公告

		// 測試修改公告
//		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
//		BoardDAO boarddao = (BoardDAO) context.getBean("BoardDAO");
//		BoardBean board=boarddao.getByPrimaryKey(5);//拿到board_id為5的BoardBean物件
//		board.setBoard_content("xxxxxx燒毀");//設置新的公告內容
//		 Timestamp ts=new Timestamp(System.currentTimeMillis());
//		board.setBoard_time(ts);//設置新的公告時間
//		 b.updateBoard(board);//更新時間

		 //測試瀏覽所有公告
//		 List<BoardBean> boardlist=b.viewAllBoard();
//		 for(BoardBean board:boardlist){
//		 System.out.println(board.getBoard_content());//公告內容
//		 System.out.println(board.getBoard_time());//公告時間
//		 }

		// 測試瀏覽一則公告
//		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
//		BoardDAO boarddao = (BoardDAO) context.getBean("BoardDAO");
//		BoardBean board=boarddao.getByPrimaryKey(2);
//		BoardBean boa = b.viewOneBoard(board);
//		System.out.println(board.getBoard_content());//公告內容
//		System.out.println(board.getBoard_time());//公告時間
	}

}
