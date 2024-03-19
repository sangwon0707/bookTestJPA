package com.example.demo.service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.Test;
import com.example.demo.controller.BooksController;
import com.example.demo.dao.BooksDAO;
import com.example.demo.vo.BooksVO;
import com.example.demo.vo.CustomerVO;

@Service
public class BooksService {
	@Autowired
	private BooksDAO dao;
		
	public BooksVO findById(int bookid){
		//return dao.findBy(null, null)
		//jpa의 findById는 VO를 반환하지 않고
		//VO를 Optional로 포장해서 반환합니다.
		BooksVO b = null;
		Optional<BooksVO> o = dao.findById(bookid);
		if(o.isPresent()){
			b= o.get();
		}
		return b;
	}
	
//	public List<BooksVO> findAll(){
//		return dao.findAll();
//	}
	
//	if(keyword != null && !keyword.isEmpty()){
//	return dao.findByBooknameLike(keyword);
//	return dao.findByBooknameContaining(keyword);
//}
//return dao.findAllByOrderByBookname();
	public List<BooksVO> findAll(HashMap<String, String> map){
		if(map == null){
			return dao.findAll();
		}
		String cname = map.get("cname");
		String keyword = map.get("keyword");
		String sname = map.get("sname");
		System.out.println("서비스에서 sname:"+sname);
		List<BooksVO> list = null;
		if(keyword != null && !keyword.equals("")) {
			String methodName = "findBy"+cname;
			if(sname != null) {
				methodName += "OrderBy"+sname;
			}
			
			try {
				Class<?> cls = 
					Class.forName(dao.getClass().getName());
				if(cname.equals("Bookname") || cname.equals("Publisher")) {
					StringBuffer sb = new StringBuffer(methodName);
					int i = sb.length();
					if(sb.indexOf("OrderBy") != -1) {
						i = sb.indexOf("OrderBy");
					}
					methodName = sb.insert(i, "Containing").toString();					
					Method method = 
							cls.getDeclaredMethod(methodName, String.class);			
							list = (List<BooksVO>)method.invoke(dao, keyword);
				}else {
					Method method = 
							cls.getDeclaredMethod(methodName, Integer.class);							
							list = (List<BooksVO>)method.invoke(dao, new Integer(keyword) );
				}						
			}catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
			System.out.println("keyword:"+keyword);
			System.out.println("methodName:"+methodName);
		}
		else {
			String methodName = "findAllByOrderBy";
			if(sname != null) {
				methodName += sname;
			}else {
				methodName += "Bookname";
			}
			try {
				Class<?> cls = 
					Class.forName(dao.getClass().getName());
					Method method = 
							cls.getDeclaredMethod(methodName);			
							list = (List<BooksVO>)method.invoke(dao);										
			}catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}
		return list;
	}
	
	
	
	//pk에 해당하는 레코드가 이미 있으면 update를 수행하고
	//그렇지 않으면 insert를 수행합니다.
	public void save(BooksVO b){
		dao.save(b);
	}

	public void delete(int bookid) {
		dao.deleteById(bookid);
	}

}
