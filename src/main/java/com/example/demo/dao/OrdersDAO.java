package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.vo.OrdersVO;

import jakarta.transaction.Transactional;

public interface OrdersDAO extends JpaRepository<OrdersVO, Integer> {
	
	@Query(value = "select nvl(max(orderid),0)+1 from orders", 
			nativeQuery = true)
	public int getNextNo();
	
	@Modifying
	@Query(value ="insert into orders o(o.orderid,o.custid,o.bookid,o.saleprice,o.orderdate) values(:#{#o.orderid},:#{#o.customer.custid},:#{#o.book.bookid},:#{#o.saleprice},sysdate)",nativeQuery = true)
	@Transactional
	public void insert(@Param("o") OrdersVO o);
	
	public List<OrdersVO> findByCustomer_NameOrderByOrderid(String name);
	public List<OrdersVO> findByCustomer_Name(String name);
}
