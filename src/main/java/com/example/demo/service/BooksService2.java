package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.dao.BooksDAO;
import com.example.demo.vo.BooksVO;
import com.example.demo.vo.CustomerVO;

import lombok.Setter;

//@Service
//@Setter
public class BooksService2 {
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
		String cname = map.get("cname");
		String keyword = map.get("keyword");
		List<BooksVO> list = null;
		
		if(keyword != null && !keyword.isEmpty()){
			switch(cname){
				case "bookid": list= dao.findByBookid(Integer.parseInt(keyword));break;
				case "bookname": list= dao.findByBooknameContaining(keyword);break;
				case "publisher": list= dao.findByPublisherContaining(keyword);break;
				case "price": list= dao.findByPrice(Integer.parseInt(keyword));break;
			}
		}else{
			return dao.findAllByOrderByBookname();
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
