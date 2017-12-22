package com.ccc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;












import java.util.Map;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ccc.model.bean.CatBean;
import com.ccc.model.bean.MemberBean;
import com.ccc.model.bean.PictureBean;
import com.ccc.model.dao.MemberDAO;
import com.ccc.model.service.PhotoService;
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,	// 2MB
				maxFileSize = 1024 * 1024 * 10,			// 10MB
				maxRequestSize = 1024 * 1024 * 50)		// 50MB

//@WebServlet("/PhotoServlet.do")
public class PhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public PhotoServlet() {
        super();
        
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");  
        response.setContentType("text/html;charset=utf-8");  
        PhotoService photoservice = new PhotoService();
		HttpSession httpSession = request.getSession();
		PrintWriter pw = response.getWriter();
		MemberBean memberBean = (MemberBean) httpSession.getAttribute("user");
		
		
	
        //處理照片類型 1)onloadPhoto->上傳照片  2)editPhotos->編輯照片 3)viewPhotos->瀏覽照片
        String photos_type = request.getParameter("photos");
//        System.out.println("photos_type="+photos_type);
//        呼叫photoservice
//        上傳照片
        if(photos_type.equals("onloadPhoto")){
        	if (request.getPart("pic").getSize() != 0) {
        		
        		List<PictureBean>  pictureBean_list = photoservice.uploadPhotos(memberBean,null, request);
        		if(!pictureBean_list.isEmpty() && pictureBean_list.size()!=0){
        			List<PictureBean> pictureBean_list_view = photoservice.glancePhotos(memberBean.getM_id());
        			request.setAttribute("member", memberBean);
        			request.setAttribute("photos", pictureBean_list_view);
        			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/PhotoLargeView.jsp");
        			requestDispatcher.forward(request, response);
        			
        		}else{
        			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/PhotoLargeView.jsp");
        			requestDispatcher.forward(request, response);
        		}	
			}else{
				List<PictureBean> pictureBean_list_view = photoservice.glancePhotos(memberBean.getM_id());
    			request.setAttribute("member", memberBean);
    			request.setAttribute("photos", pictureBean_list_view);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/PhotoLargeView.jsp");
    			requestDispatcher.forward(request, response);
			}
        	
        //上傳貓咪照片
        }else if(photos_type.equals("onloadCatPhoto")){
        		if (request.getPart("pic").getSize() != 0) {
        			int c_id =Integer.parseInt(request.getParameter("cat"));
        			CatBean catBean =new CatBean();
        			catBean.setC_id(c_id);
        		List<PictureBean>  pictureBean_list = photoservice.uploadPhotos(memberBean,catBean, request);
        		if(!pictureBean_list.isEmpty() && pictureBean_list.size()!=0){
        			Map<String , Object> pictureBean_map_view = photoservice.glanceCatPhotos(memberBean.getM_id());
    				List<PictureBean> pictureBean_list_view = (List<PictureBean>) pictureBean_map_view.get("CatPictures");
    				List<CatBean> catBean_list = (List<CatBean>) pictureBean_map_view.get("catBean_list");
    				//catBean_list
    				request.setAttribute("member", memberBean);
            		request.setAttribute("carBean_list", catBean_list);
            		request.setAttribute("Catphotos",pictureBean_list_view);
            		getServletContext().getRequestDispatcher(
    						"/pages/CatsPhotos.jsp").forward(request,response);
        			
        		}else{
        			getServletContext().getRequestDispatcher(
    						"/pages/CatsPhotos.jsp").forward(request,response);
        			
        		}	
			}else{
				Map<String , Object> pictureBean_map_view = photoservice.glanceCatPhotos(memberBean.getM_id());
				List<PictureBean> pictureBean_list_view = (List<PictureBean>) pictureBean_map_view.get("CatPictures");
				List<CatBean> catBean_list = (List<CatBean>) pictureBean_map_view.get("catBean_list");
				//catBean_list
				request.setAttribute("member", memberBean);
        		request.setAttribute("carBean_list", catBean_list);
        		request.setAttribute("Catphotos",pictureBean_list_view);
        		getServletContext().getRequestDispatcher(
						"/pages/CatsPhotos.jsp").forward(request,response);
			}
        		
		//編輯照片	
        }else if(photos_type.equals("editPhotos")){
        	try{
        		String a_photo = request.getParameter("photo_name");
            	int Pic_id =Integer.parseInt(request.getParameter("photo_id"));
//            	System.out.println("Pic_id:"+Pic_id);
            	Boolean isEdit = photoservice.editPhotos(Pic_id);
//            	System.out.println("isEdit"+isEdit);
            	if(isEdit){
            		String member_id =memberBean.getM_id();
            		try{
            			List<PictureBean> pictureBean_list_view = photoservice.glancePhotos(member_id);
                		request.setAttribute("member", memberBean);
                		request.setAttribute("photos", pictureBean_list_view);
                		
                		JSONObject json = new JSONObject(); 
    					JSONArray array = new JSONArray();  
    					JSONArray photos_array = new JSONArray();
    					JSONObject photos = null; 
    					JSONObject members = new JSONObject();
    					
    					 for (int i = 0; i < pictureBean_list_view.size(); i++) {  
    						 photos = new JSONObject();  
    						 photos.put("Pic_id",pictureBean_list_view.get(i).getPic_id());  
    						 photos.put("Pic_file_path", pictureBean_list_view.get(i).getPic_file_path()); 
    				            array.add(photos);       
    				    }
    					 members.put("memberBean", member_id);
    					 photos_array.add(members);  
    					 json.put("memberBean", photos_array);  
    					 json.put("jsonArray", array);  
    					 pw.print(json.toString());  

//    					 System.out.println("json memberBean :"+members.toString()); 
//    					 System.out.println("json array :"+array.toString());  
    					 pw.close(); 
            		}catch(NullPointerException e){
            		
            			pw.print("");  
            			pw.close(); 
            		}
            	}else{
            		pw.print("");  
        			pw.close(); 
//            		System.out.println("1.刪除失敗!");
            	}
        	}catch(NumberFormatException e){
        		pw.print("");  
    			pw.close(); 
//        		System.out.println("2.刪除失敗!");
        	}
        	
        	
        //瀏覽照片
        }else if(photos_type.equals("viewPhotos")){
//        	System.out.println("您進入了個人相本");
        	try{
        		List<PictureBean> pictureBean_list = photoservice.glancePhotos(memberBean.getM_id());
        		request.setAttribute("member", memberBean);
        		request.setAttribute("photos", pictureBean_list);
        		getServletContext().getRequestDispatcher(
						"/pages/Photo.jsp").forward(request,response);

        	}catch(NullPointerException e){
        		request.setAttribute("photos",null);
        		getServletContext().getRequestDispatcher(
						"/pages/Photo.jsp").forward(request,response);
        	}
        	
        	
        	
        }else if(photos_type.equals("viewPhotos_big")){
        	try{
        		List<PictureBean> pictureBean_list = photoservice.glancePhotos(memberBean.getM_id());
        		request.setAttribute("member", memberBean);
        		request.setAttribute("photos", pictureBean_list);
        		getServletContext().getRequestDispatcher(
						"/pages/PhotoLargeView.jsp").forward(request,response);
        	}catch(NullPointerException e){
        		request.setAttribute("photos",null);
        		getServletContext().getRequestDispatcher(
						"/pages/PhotoLargeView.jsp").forward(request,response);
        	}
        	
        }else if(photos_type.equals("viewPhotoscat")){
//        	System.out.println("cat photo");
        	try{
        		Map<String , Object> pictureBean_map = photoservice.glanceCatPhotos(memberBean.getM_id());
//        		System.out.println("pictureBean_map.size()"+pictureBean_map.size());
				List<PictureBean> pictureBean_list = (List<PictureBean>) pictureBean_map.get("CatPictures");
				List<CatBean> catBean_list = (List<CatBean>) pictureBean_map.get("catBean_list");
//				System.out.println("catBean_list.size()"+catBean_list.size());
				//catBean_list
				request.setAttribute("member", memberBean);
        		request.setAttribute("carBean_list", catBean_list);
        		request.setAttribute("Catphotos",pictureBean_list);
        		getServletContext().getRequestDispatcher(
						"/pages/CatsPhotos.jsp").forward(request,response);
        		
        	}catch(NullPointerException e){
        		e.printStackTrace();
        		request.setAttribute("photos",null);
        		getServletContext().getRequestDispatcher(
						"/pages/CatsPhotos.jsp").forward(request,response);
        	}
        }else if(photos_type.equals("viewFriendPhotoscat")){
//        	System.out.println("view friend cat photo");
        	try{
        		String friend_mid = request.getParameter("m_id");
        		
        		WebApplicationContext webAppContext = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        		MemberDAO memdao = (MemberDAO) webAppContext.getBean("MemberDAO");
        		MemberBean friendmem = memdao.getByPrimaryKey(friend_mid);
        		
        		Map<String , Object> pictureBean_map = photoservice.glanceCatPhotos(friend_mid);
//        		System.out.println("pictureBean_map.size()"+pictureBean_map.size());
				List<PictureBean> pictureBean_list = (List<PictureBean>) pictureBean_map.get("CatPictures");
				List<CatBean> catBean_list = (List<CatBean>) pictureBean_map.get("catBean_list");
//				System.out.println("catBean_list.size()"+catBean_list.size());
				//catBean_list
				request.setAttribute("member", friendmem);
        		request.setAttribute("carBean_list", catBean_list);
        		request.setAttribute("Catphotos",pictureBean_list);
        		getServletContext().getRequestDispatcher(
						"/pages/CatsPhotos.jsp").forward(request,response);
        		return;
        	}catch(NullPointerException e){
        		e.printStackTrace();
        		request.setAttribute("photos",null);
        		getServletContext().getRequestDispatcher(
						"/pages/CatsPhotos.jsp").forward(request,response);
        		return;
        	}
        }
        
        
        
        
        
        
        
	}



//	public String toJSON (List<PictureBean> pictureBean_list,MemberBean memberBean,HttpServletResponse response){
//		PrintWriter pw = response.getWriter();
//		JSONObject json = new JSONObject(); 
//		JSONArray array = new JSONArray();  
//		 JSONObject members = null;  
//		 for (int i = 0; i < pictureBean_list.size(); i++) {  
//	            members = new JSONObject();  
//	            members.put("Pic_id",pictureBean_list.get(i).getPic_id());  
//	            members.put("Pic_file_path", pictureBean_list.get(i).getPic_file_path()); 
//	            array.add(members);  
//	            
//	    }
//		 //json.put("account", member);  
//		 json.put("jsonArray", array);  
//		 pw.print(json.toString());  
//		
//		 System.out.println("json array :"+array.toString());  
//		 pw.close(); 
//	}

	
	private String generateToken(HttpServletRequest request) {
		Random ran = new Random();
		String formhash = String.valueOf(ran.nextInt());
		return formhash;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
