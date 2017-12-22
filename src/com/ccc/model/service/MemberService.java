package com.ccc.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.CatBean;
import com.ccc.model.bean.FavoriteBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PictureBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.bean.ResponseBean;
import com.ccc.model.bean.WhoLikeBean;
import com.ccc.model.dao.CatDAO;
import com.ccc.model.dao.FavoriteDAO;
import com.ccc.model.dao.MemberDAO;
import com.ccc.model.dao.PostDAO;
import com.ccc.model.dao.ResponseDAO;
import com.ccc.model.dao.WhoLikeDAO;

public class MemberService {
	private static ApplicationContext context = new ClassPathXmlApplicationContext(
			"beans.config.xml");
	private static CatDAO cat = (CatDAO) context.getBean("CatDAO");
	private static MemberDAO memdao = (MemberDAO) context.getBean("MemberDAO");
	// servlet呼叫request.getPrimary(Key); 建立MemberBean物件呼叫服務
	// memberBean->DAO.update->return memberBean
	// 修改個人資訊
	public MemberBean modifyPersonalInfo(MemberBean member) {
		MemberBean member_new = memdao.getByPrimaryKey(member.getM_id());
		member_new.setM_name(member.getM_name());
		member_new.setM_intro(member.getM_intro());
		member_new.setM_email(member.getM_email());
		member_new.setM_birth(member.getM_birth());
		member_new.setM_sex(member.getM_sex());
		member_new.setM_pic_path(member.getM_pic_path());
		memdao.update(member_new);
		return member;
	}

	// 更換個人照片
	public List<PictureBean> changePersonalPicture(MemberBean member) {
		if (member != null) {
			String m_id = member.getM_id();
			PhotoService photo = new PhotoService();
			List<PictureBean> piclist = photo.glancePhotos(m_id);
			return piclist;
		} else {
			return null;
		}
	}

	// 修改密碼服務
	// 判斷舊的密碼與新的密碼是否正確
	public Boolean judgeOldPassword(String pwd_old, MemberBean member) {// 代入輸入舊的密碼;會員物件
		if (pwd_old != null) {// 如果舊的密碼不是空值
			byte[] pwd = member.getM_pwd();// 從資料庫拿到舊的密碼，型態為byte
			String dbpwd = new String(pwd);// 將舊的密碼轉成字串
			LoginService login = new LoginService();// new Service物件
			String bufferPwd = login.getMD5Encoding(pwd_old);// 將輸入的舊密碼轉乘MD5
			if (dbpwd.equals(bufferPwd)) {// 將資料庫取出的密碼與輸入的密碼做比對
				return true;
			} else {
				return false;
			}
		} else {
			return null;
		}
	}

	// 輸入新的密碼
	public boolean changePassword(String pwd_new, MemberBean member) {// 代入輸入的新的密碼；會員物件
		LoginService login = new LoginService();// new Service物件
		String bufferPwd = login.getMD5Encoding(pwd_new);// 將新的密碼轉成MD5物件
		member.setM_pwd(bufferPwd.getBytes());// 將新的密碼轉成的MD5物件轉成Byte型態，並且set
		MemberDAO memdao = (MemberDAO) context.getBean("MemberDAO");// 取得MemberDAO物件
		memdao.update(member);// 更新密碼
		return true;
	}

	// 拿到回覆貼文的集合
	public List<ResponseBean> getResponse(PostBean post) {// 代入PostBean物件
		if (post != null) {// PostBean物件如果不是空值
			ResponseDAO responsedao = (ResponseDAO) context
					.getBean("ResponseDAO");// 拿到ResponseDAO物件
			List<ResponseBean> reslist = responsedao
					.getResponsesByPostBean(post);// 拿到某則貼文的回覆貼文物件
			return reslist;// 回傳list物件
		} else {
			return null;
		}
	}

	// 拿到某則貼文的人的會員物件
	public MemberBean getMemberInfoByPost(PostBean post) {// 代入PostBean物件
		if (post != null) {// 如果PostBean不是空值
			MemberDAO memberdao = (MemberDAO) context.getBean("MemberDAO");// 拿到MemberDAO物件
			PostDAO postdao = (PostDAO) context.getBean("PostDAO");
			post = postdao.getByPrimaryKey(post.getPost_id());
			String M_id = post.getMemberBean().getM_id();// 拿到某則貼文的人的
			MemberBean member = memberdao.getByPrimaryKey(M_id);// 拿到這個會員的物件
			return member;// 回傳MemberBean物件
		} else {
			return null;
		}
	}

	// 拿到喜歡某則貼文的人的集合
	public List<WhoLikeBean> getWhoLike(PostBean post) {// 代入PostBean物件
		if (post != null) {// 如果PostBean物件不是null
			int post_id = post.getPost_id();// 拿到貼文的post_id
			WhoLikeDAO wholikedao = (WhoLikeDAO) context.getBean("WhoLikeDAO");// 拿到WoLikeDAO物件
			List<WhoLikeBean> wholikelist = wholikedao.getByPostId(post_id);// 拿到喜歡這則貼文的人的集合
			return wholikelist;// 回傳list物件
		} else {
			return null;
		}
	}

	// 拿到我的最愛貼文集合所對應的貼文集合
	public List<PostBean> getFavoritePost(List<FavoriteBean> favlist) {// 代入List<FavoriteBean>物件
		if (favlist != null) {// List<FavoriteBean>不是空值
			PostDAO postdao = (PostDAO) context.getBean("PostDAO");// 拿到PostDAO物件
			List<PostBean> postlist = new ArrayList<PostBean>();// new List<PostBean>物件
			for (FavoriteBean fav : favlist) {
				int post_id = fav.getPostBean().getPost_id();// 拿到我的最愛貼文所對應的post_id
				PostBean post = postdao.getByPrimaryKey(post_id);// 拿到PostBean物件
				postlist.add(post);// 將PostBean物件一個個加入list
			}
			return postlist;
		} else {
			return null;
		}
	}

	// 拿到會員我的最愛的集合
	public List<FavoriteBean> viewFavoritePosts(MemberBean member, int page, int size) {
		FavoriteDAO fav = (FavoriteDAO) context.getBean("FavoriteDAO");
		List<FavoriteBean> favlist = fav.getFavoritesByMethodPerPage(
				"from FavoriteBean where m_id = '" + member.getM_id() + "' order by post_id desc", page, size);
		return favlist;
	}

	// 瀏覽貓咪資訊
	public List<CatBean> viewCatsInfo(MemberBean memberBean) {
		return cat.getCatsByM_id(memberBean.getM_id());
	}

	// 新增貓咪資訊
	public CatBean createCatInfo(CatBean catBean) {
		try {
			return cat.insert(catBean);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 編輯貓咪資訊
	public CatBean editCatInfo(CatBean catBean) {
		try {
			CatBean catBean_new = cat.getByPrimaryKey(catBean.getC_id());
			catBean_new.setC_name(catBean.getC_name());
			catBean_new.setC_age(catBean.getC_age());
			catBean_new.setC_breed(catBean.getC_breed());
			catBean_new.setC_sex(catBean.getC_sex());
			catBean_new.setC_intro(catBean.getC_intro());
			catBean_new.setC_pic_path(catBean.getC_pic_path());
			return cat.update(catBean_new);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// 移除貓咪資訊
	public boolean removeCatInfo(int c_id) {
		try {
			CatBean catBean = cat.getByPrimaryKey(c_id);
			cat.delete(catBean);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 瀏覽一則我的最愛貼文
	// 拿到我的最愛貼文物件所對應的貼文物件
	public PostBean viewFavoritePost(FavoriteBean fav) {// 代入FavoriteBean物件
		if (fav != null) {// 如果不是空值
			PostDAO postdao = (PostDAO) context.getBean("PostDAO");// 拿到PostDAO物件
			int post_id = fav.getPostBean().getPost_id();// 拿到某則我的最愛貼文的post_id
			PostBean post = postdao.getByPrimaryKey(post_id);// 拿到PostBean物件
			return post;// 回傳PostBean物件
		} else {
			return null;
		}
	}

	// 使用帳號設定
	// 修改帳號設定服務
	public MemberBean accountSettings(MemberBean member) {
		MemberBean member_new = memdao.getByPrimaryKey(member.getM_id());
		member_new.setCf_birth(member.getCf_birth());
		member_new.setCf_email(member.getCf_email());
		member_new.setCf_intro(member.getCf_intro());
		member_new.setCf_sex(member.getCf_sex());
		member_new.setCf_post(member.getCf_post());
		member_new.setCf_res(member.getCf_res());
		member_new.setCf_act(member.getCf_act());
		member_new.setCf_theme(member.getCf_theme());
		memdao.update(member_new);
		return member;
	}
}