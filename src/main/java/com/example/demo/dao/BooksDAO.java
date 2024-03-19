package com.example.demo.dao;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.BooksVO;
import com.example.demo.vo.CustomerVO;

@Repository
public interface BooksDAO extends JpaRepository<BooksVO, Integer> {
	
	//public List<BooksVO>findByBookname(String bookname);
	//우리가 생각하는 like가 아니고 equal로 동작함
	public List<BooksVO> findByBooknameLike(String bookname);
	
	public List<BooksVO> findByBooknameContaining(String bookname);
	public List<BooksVO> findByBookid(Integer bookid);
	public List<BooksVO> findByPrice(Integer price);
	public List<BooksVO> findByPublisherContaining(String publisher);
	
	public List<BooksVO>findAllByOrderByBookname();
	public List<BooksVO>findAllByOrderByBookid();
	public List<BooksVO>findAllByOrderByPrice();
	public List<BooksVO>findAllByOrderByPublisher();
	
	public List<BooksVO> findByBooknameContainingOrderByBookname(String bookname);
	public List<BooksVO> findByBooknameContainingOrderByBookid(String bookname);
	public List<BooksVO> findByBooknameContainingOrderByPrice(String bookname);
	public List<BooksVO> findByBooknameContainingOrderByPublisher(String bookname);
	
	public List<BooksVO> findByBookidOrderByBookname(Integer bookid);
	public List<BooksVO> findByBookidOrderByBookid(Integer bookid);
	public List<BooksVO> findByBookidOrderByPrice(Integer bookid);
	public List<BooksVO> findByBookidOrderByPublisher(Integer bookid);

	public List<BooksVO> findByPriceOrderByBookname(Integer price);
	public List<BooksVO> findByPriceOrderByBookid(Integer price);
	public List<BooksVO> findByPriceOrderByPrice(Integer price);
	public List<BooksVO> findByPriceOrderByPublisher(Integer price);
	
	public List<BooksVO> findByPublisherContainingOrderByBookname(String publisher);
	public List<BooksVO> findByPublisherContainingOrderByBookid(String publisher);
	public List<BooksVO> findByPublisherContainingOrderByPrice(String publisher);
	public List<BooksVO> findByPublisherContainingOrderByPublisher(String publisher);
}
