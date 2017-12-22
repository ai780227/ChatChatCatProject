package com.ccc.model.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.CatBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PictureBean;
import com.ccc.model.dao.CatDAO;
import com.ccc.model.dao.MemberDAO;
import com.ccc.model.dao.PictureDAO;

public class PhotoService {
	ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
	String fileName;

	// for test
	String path = "C:\\Security\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\ChatChatCatProject\\userImages";


	// 上傳照片
	public List<PictureBean> uploadPhotos(MemberBean memberBean,CatBean catBean, HttpServletRequest request) {
		int i=0;


		List<PictureBean> lpicb = new ArrayList<PictureBean>();
		PictureBean pictureBean;

		PictureDAO picture_DAO=(PictureDAO)context.getBean("PictureDAO");
		//抓會員ID
		String SAVE_mid = memberBean.getM_id();
		//取WEB應用程式的根目錄
		String appPath = request.getServletContext().getRealPath("");
		//根目錄+分離器(?)+會員ID
		String savePath = appPath + File.separator + "userImages" + File.separator + SAVE_mid;
		//建立會員專用的目錄
		File fileSaveDir = new File(savePath);
		//判斷目錄是否存在
		if (!fileSaveDir.exists()) {
			//建立目錄
			fileSaveDir.mkdirs();
		}
		try {
			for (Part part : request.getParts()) {
				if (part.getName().equals("pic") && !extractFileName(part).isEmpty()) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					String fileNameAll = extractFileName(part);
					// 取的附檔名
					fileNameAll=fileNameAll.substring(fileNameAll.length()-4);
					// 取得檔名
					fileName = sdf.format(new java.sql.Timestamp(System.currentTimeMillis())) + i + fileNameAll;
					System.out.println("fileName:"+fileName);
					// 對檔案名後方加上i以免發生重複檔案名稱的狀況
					i++;
					// 寫入檔案
					part.write(savePath + File.separator + fileName);
					// System.out.println(savePath + File.separator + fileName);
					// 判斷檔名是否為空字串或空值
					if (fileName != null || fileName != "") {
						
						pictureBean = new PictureBean();
						pictureBean.setMember(memberBean);
						if (catBean != null) {
							if(catBean.getC_id() !=null)
								pictureBean.setCat(catBean);
						}
						pictureBean.setPic_file_path(fileName);
					
						pictureBean = picture_DAO.insert(pictureBean);
					} else {
					
						return null;
					}
			
					lpicb.add(pictureBean);
				}

			}
		
			return lpicb;
		} catch (IllegalStateException e) {
			e.printStackTrace();

			return null;
		} catch (IOException e) {
			e.printStackTrace();

			return null;
		} catch (ServletException e) {
			e.printStackTrace();
	
			return null;
		}
	}

	//取得檔案名稱完整資料
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
	
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}




	//編輯照片 - 刪除照片
	public Boolean editPhotos(PictureBean pictureBean){
		boolean bool = false;
		PictureDAO pictureDAO=(PictureDAO)context.getBean("PictureDAO");
		File f = new File(path + "/" + pictureBean.getMember().getM_id() + "/" + pictureBean.getPic_file_path());
		bool =f.delete();
		PictureBean repictureBean =pictureDAO.delete(pictureBean);
		if(bool  &&  repictureBean!=null){
			return true;
		}else{
			return false;
		}
	}
	
	//編輯照片 - 刪除照片
		public Boolean editPhotos(int pic_id){
			boolean bool = false;
			PictureDAO pictureDAO=(PictureDAO)context.getBean("PictureDAO");
			PictureBean pictureBean =pictureDAO.getByPrimaryKey(pic_id);
			try{
				File f = new File(path + "/" + pictureBean.getMember().getM_id() + "/" + pictureBean.getPic_file_path());
				bool =f.delete();
				PictureBean repictureBean =pictureDAO.delete(pictureBean);
				if(bool  &&  repictureBean!=null){
					return true;
				}else{
					return false;
				}
			}catch(NullPointerException e){
				return false;
			}
			
		}

	//瀏覽照片
	public List<PictureBean> glancePhotos(String member_id){
		PictureDAO pictureDAO=(PictureDAO)context.getBean("PictureDAO");
		List<PictureBean> pictureBean_list=pictureDAO.getPicturesByM_id(member_id);
		if(pictureBean_list.size()!=0){
			return pictureBean_list;
		}else{
			return null;
		}
	}
	
	public Map<String,Object> glanceCatPhotos(String member_id){
		CatDAO cat_DAO =(CatDAO)context.getBean("CatDAO");
		PictureDAO picture_DAO=(PictureDAO)context.getBean("PictureDAO");
		//取得貓咪個資
	    List<CatBean> catBean_list = cat_DAO.getCatsByM_id(member_id);
	    List<PictureBean> reCatPictureBean_list =new ArrayList<PictureBean>();
	    Map<String,Object> reCatPictureBean_map = new HashMap<String,Object>();
	    

	    if(catBean_list.size()!=0 && !catBean_list.isEmpty()){
	    	for(int i =0;i<catBean_list.size();i++){
	    		//去的貓咪照片
	    		List<PictureBean> pictureBean_list = picture_DAO.getPicturesByC_id(catBean_list.get(i).getC_id());
	    		//System.out.println("CatPictures"+pictureBean_list.size());
	    		if(!pictureBean_list.isEmpty() && pictureBean_list.size()!=0){
	    			for(int j=0;j<pictureBean_list.size();j++){
	    				reCatPictureBean_list.add(pictureBean_list.get(j));
	    			}
	    		}else{
	    			reCatPictureBean_map.put("catBean_list",catBean_list);
	    			return reCatPictureBean_map;
	    		}
	    	}
	    }else{
	    	return null;
	    }
	    if(!reCatPictureBean_list.isEmpty()){
 	    	reCatPictureBean_map.put("CatPictures", reCatPictureBean_list);
 	    	reCatPictureBean_map.put("catBean_list",catBean_list);
 	    	return reCatPictureBean_map;
 	    }else{
 	    	return null;
 	    }
	}

	public static void main(String[] args) {
	}

}
