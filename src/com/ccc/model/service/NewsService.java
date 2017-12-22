package com.ccc.model.service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ccc.model.bean.NewsBean;
import com.ccc.model.dao.NewsDAO;

public class NewsService {
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"beans.config.xml");
	NewsDAO newsDAO = (NewsDAO) context.getBean("NewsDAO");
	
	//新增新聞
	public NewsBean createNews(NewsBean news){
		return newsDAO.insert(news);
	}
	
	//編輯新聞
	public NewsBean editNews(NewsBean news){
		return newsDAO.update(news);
	}
	
	//刪除新聞
	public NewsBean deleteNews(NewsBean news){
		return newsDAO.delete(news);
	}
	
	//抓取新聞主鍵
	public NewsBean getByPrimaryKey(int news_id){
		return newsDAO.getByPrimaryKey(news_id);
	}
	
	//抓取所有新聞
	public List<NewsBean> getAll(){
		return newsDAO.getAll();
	}
	
	public List<NewsBean> getNewsOnPages(int pages) {
		String query = "from NewsBean order by news_time desc";
		List<NewsBean> newsList = newsDAO.getNewsOnPages(query, pages);
		return newsList;
	}
	
	public static void main(String[] args) {
		NewsService newsServ = new NewsService();
		List<NewsBean> newsList = newsServ.getNewsOnPages(3);
		for(NewsBean newsBean : newsList) {
			System.out.println(newsBean.getNews_title());
		}
	}
}
