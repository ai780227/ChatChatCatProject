package com.ccc.model.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.FavoriteBean;
import com.ccc.model.bean.FriendshipBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PictureBean;
import com.ccc.model.bean.PostBean;
import com.ccc.model.bean.PostPictureBean;
import com.ccc.model.bean.ReportBean;
import com.ccc.model.bean.ResponseBean;
import com.ccc.model.bean.WhoLikeBean;
import com.ccc.model.dao.FavoriteDAO;
import com.ccc.model.dao.FriendshipDAO;
import com.ccc.model.dao.PostDAO;
import com.ccc.model.dao.PostPictureDAO;
import com.ccc.model.dao.ReportDAO;
import com.ccc.model.dao.ResponseDAO;
import com.ccc.model.dao.WhoLikeDAO;

public class PostService {
	private static ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
	private static PostDAO post = (PostDAO) context.getBean("PostDAO");
	private static ReportDAO rep = (ReportDAO) context.getBean("ReportDAO");
	private static FavoriteDAO fav = (FavoriteDAO) context.getBean("FavoriteDAO");
	private static WhoLikeDAO wl = (WhoLikeDAO) context.getBean("WhoLikeDAO");
	private static ResponseDAO res = (ResponseDAO) context.getBean("ResponseDAO");
	private static FriendshipDAO fs = (FriendshipDAO) context.getBean("FriendshipDAO");
	private static PostPictureDAO pp = (PostPictureDAO) context.getBean("PostPictureDAO");

	// 貼文分類:0所有貼文、1一般動態、2知識(醫療、飼養)、3領養、4貓咪店家、5活動

	// 瀏覽單則貼文
	public PostBean viewAPost(int post_id) {
		return post.getByPrimaryKey(post_id);
	}

	// 瀏覽單則回應
		public ResponseBean viewAResponse(int res_id) {
			return res.getByPrimaryKey(res_id);
	}

	// 管理員閱覽貼文，活動以及封鎖貼文除外
		public List<PostBean> viewAllPostsByManager(int post_type, int page, int size) {
			if (post_type == 0) {
				return post.getPostsByMethodPerPage(
						"from PostBean order by post_time desc, post_id desc", page, size);
			} else {
				return post.getPostsByMethodPerPage(
						"from PostBean where post_type = " + post_type + " order by post_time desc, post_id desc", page, size);
			}
		}

	// 閱覽貼文，活動以及封鎖貼文除外
	public List<PostBean> viewAllPosts(MemberBean mb, int page, int size) {
		Iterator<FriendshipBean> ifsb = fs.getMyFriend(mb).iterator();
		String ids = "";
		if (ifsb != null) {
			while (ifsb.hasNext()){
				FriendshipBean fsb = ifsb.next();
				ids = ids + "'" + fsb.getMemberBean_b().getM_id() + "', ";
			}
		}
		return post.getPostsByMethodPerPage(
				"from PostBean where (m_id in (" + ids + "'" + mb.getM_id() + "', null) and post_property = 2 and post_type is not 5) or " +
									"(post_property = 1 and post_type is not 5) order by post_time desc, post_id desc", page, size);
	}

	// 閱覽他人貼文，活動以及封鎖貼文除外mb=本人,m_id=他人
	public List<PostBean> viewUserPosts(MemberBean mb, String m_id, int page, int size) {
		List<PostBean> lpb = new ArrayList<PostBean>();
		FriendshipBean fsb1 = fs.getByPrimaryKey(mb.getM_id(), m_id);
		FriendshipBean fsb2 = fs.getByPrimaryKey(m_id, mb.getM_id());
		if (fsb1 != null && fsb2 != null) {
			lpb = post.getPostsByMethodPerPage("from PostBean where m_id = '" + m_id + "' and post_type is not 5 order by post_time desc, post_id desc", page, size);
		} else if (mb.getM_id().equals(m_id)) {
			lpb = post.getPostsByMethodPerPage("from PostBean where m_id = '" + mb.getM_id() + "' and post_type is not 5 order by post_time desc, post_id desc", page, size);
		} else {
			lpb = post.getPostsByMethodPerPage("from PostBean where m_id = '" + m_id + "' and post_property = 1 and post_type is not 5 order by post_time desc, post_id desc", page, size);
		}
		return lpb;
	}

	// 用tag閱覽貼文
	public List<PostBean> viewPostsByTags(PostBean pb, MemberBean mb, int page, int size) {
		if (pb.getPost_property() == 0 && pb.getPost_type() == 0){
			return viewAllPosts(mb, page, size);
		} else if (pb.getPost_property() != 0 && pb.getPost_type() == 0) {
			if (pb.getPost_property() == 2) {
				Iterator<FriendshipBean> ifsb = fs.getMyFriend(mb).iterator();
				String ids = "";
				if (ifsb != null) {
					while (ifsb.hasNext()){
						FriendshipBean fsb = ifsb.next();
						ids = ids + "'" + fsb.getMemberBean_b().getM_id() + "', ";
					}
					return post.getPostsByMethodPerPage("from PostBean where m_id in (" + ids + "'" + mb.getM_id() + "', null) and post_property = 2 and post_type is not 5 order by post_time desc, post_id desc", page, size);
				}else {
					return null;
				}
			} else {
				return post.getPostsByMethodPerPage("from PostBean where post_property = 1 and post_type is not 5 order by post_time desc, post_id desc", page, size);
			}
		} else if (pb.getPost_property() == 0 && pb.getPost_type() != 0) {
			Iterator<FriendshipBean> ifsb = fs.getMyFriend(mb).iterator();
			String ids = "";
			if (ifsb != null) {
				while (ifsb.hasNext()){
					FriendshipBean fsb = ifsb.next();
					ids = ids + "'" + fsb.getMemberBean_b().getM_id() + "', ";
				}
			}
			return post.getPostsByMethodPerPage("from PostBean where (m_id in (" + ids + "'" + mb.getM_id() + "', null) and post_property = 2 and post_type = " + pb.getPost_type() + ") or " +
																	"(post_property = 1 and post_type = " + pb.getPost_type() + ") order by post_time desc, post_id desc", page, size);
		} else {
			if (pb.getPost_property() == 2) {
				Iterator<FriendshipBean> ifsb = fs.getMyFriend(mb).iterator();
				String ids = "";
				if (ifsb != null) {
					while (ifsb.hasNext()){
						FriendshipBean fsb = ifsb.next();
						ids = ids + "'" + fsb.getMemberBean_b().getM_id() + "', ";
					}
				}
				return post.getPostsByMethodPerPage("from PostBean where m_id in (" + ids + "'" + mb.getM_id() + "', null) and post_property = " + pb.getPost_property() + " and post_type = " + pb.getPost_type() + " order by post_time desc, post_id desc", page, size);
			} else {
				return post.getPostsByMethodPerPage("from PostBean where post_property = " + pb.getPost_property() + " and post_type = " + pb.getPost_type() + " order by post_time desc, post_id desc", page, size);
			}
		}
	}

	// 閱覽活動貼文，封鎖貼文除外
		public List<PostBean> viewActPosts(MemberBean mb, int act_id, int page, int size) {
			Iterator<FriendshipBean> ifsb = fs.getMyFriend(mb).iterator();
			String ids = "";
			if (ifsb != null) {
				while (ifsb.hasNext()){
					FriendshipBean fsb = ifsb.next();
					ids = ids + "'" + fsb.getMemberBean_b().getM_id() + "', ";
				}
			}
			return post.getPostsByMethodPerPage(
					"from PostBean where (m_id in (" + ids + "'" + mb.getM_id() + "', null) and post_property = 2 and post_type = 5 and act_id = '" + act_id + "') or " +
										"(post_property = 1 and post_type = 5 and act_id = '" + act_id + "') order by post_time desc, post_id desc", page, size);
		}

	// 發布貼文
	public PostBean creatPost(PostBean pb) {
		try {
			return post.insert(pb);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 刪除貼文
	public boolean deletePost(PostBean pb) {
		try {
			pb = post.getByPrimaryKey(pb.getPost_id());
			post.delete(pb);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 編輯貼文
	public PostBean editPost(PostBean pb) {
		try {
			PostBean pb_new = post.getByPrimaryKey(pb.getPost_id());
			pb_new.setPost_type(pb.getPost_type());
			pb_new.setPost_property(pb.getPost_property());
			pb_new.setPost_time(new java.sql.Timestamp(System.currentTimeMillis()));
			pb_new.setPost_content(pb.getPost_content());
			return post.update(pb_new);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

//	1.廣告性質之貼文2.人身攻擊之貼文（侮辱、毀謗、謾罵、歧視……）
//	3.與貓咪無關之貼文（政治、色情……）
//	4.傷害貓咪內容之貼文5.其他->填寫檢舉理由(rep_cause)
	// 檢舉貼文
	public boolean accusePost(ReportBean rb) {
		try {
			rep.insert(rb);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 回應貼文
	public ResponseBean createResponse(ResponseBean resb) {
		try {
			return res.insert(resb);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 編輯回應
	public ResponseBean editResponse(ResponseBean resb) {
		try {
			ResponseBean resb_new = res.getByPrimaryKey(resb.getRes_id());
			resb_new.setRes_content(resb.getRes_content());
			resb_new.setRes_time(new java.sql.Timestamp(System.currentTimeMillis()));
			return res.update(resb_new);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 刪除回應
	public boolean deleteResponse(ResponseBean resb) {
		try {
			resb = res.getByPrimaryKey(resb.getRes_id());
			res.delete(resb);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 加入最愛
	public boolean addPostToFavorite(FavoriteBean favb) {
		try {
			if (fav.getByPrimaryKey(favb.getPostBean().getPost_id(), favb.getMemberBean().getM_id()) == null) {
				fav.insert(favb);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// 按讚
	public boolean likePost(WhoLikeBean wlb) {
		try {
			if (wl.getByPrimaryKey(wlb.getPost().getPost_id(), wlb.getMember().getM_id()) == null) {
				wl.insert(wlb);
				PostBean pb = post.getByPrimaryKey(wlb.getPost().getPost_id());
				pb.setLike_count(pb.getLike_count() + 1);
				post.update(pb);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// insert貼文照片
	public List<PostPictureBean> insertPostPicture (PostBean pb, List<PictureBean> lpicb) {
		try {
			List<PostPictureBean> lppb = new ArrayList<PostPictureBean>();
			PostPictureBean ppb;
			PictureBean picb;
			Iterator<PictureBean> ippb = lpicb.iterator();
			while (ippb.hasNext()) {
				picb = ippb.next();
				ppb = new PostPictureBean();
				ppb.setPictureBean(picb);
				ppb.setPostBean(pb);
				lppb.add(pp.insert(ppb));
			}
			return lppb;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 瀏覽貼文照片
	public List<PostPictureBean> viewPostPictures (PostBean pb) {
		return pp.getByPostId(pb.getPost_id());
	}
}